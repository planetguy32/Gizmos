package planetguy.simpleLoader;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.text.html.parser.Entity;


import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.launchwrapper.LaunchClassLoader;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

/**Class containing logic to allow easily-created content modules with dependency management.
 *  
 * @author planetguy
 *
 */

public class SimpleLoader {

	/**
	 * 
	 * @return the classes that match 
	 */
	private Class[] discoverSLModules() throws Exception{

		File mcdir=Minecraft.getMinecraft().mcDataDir;

		String pathToDir=mcdir.getAbsolutePath(); 
		String s=pathToDir.substring(0, pathToDir.length()-1)+"mods/"; //cd mods/
		LinkedList<String> filenames=new LinkedList<String>();

		File modsdir=new File(s);

		//Add all classes either 2 or 3 folders deeo in the mod zip file to the list of class names

		for(File modzip:modsdir.listFiles()){
			for(File g:modzip.listFiles()){
				for(File h:g.listFiles()){
					for(File maybeclass:h.listFiles()){						
						if(maybeclass.getName().endsWith(".class")||maybeclass.getName().endsWith(".java")){
							filenames.add(g.getName()+"."+h.getName()+"."+maybeclass.getName().replaceAll("\\.java", ""));
						}else{
							for(File level2classes:maybeclass.listFiles()){
								if(level2classes.getName().endsWith(".class")||level2classes.getName().endsWith(".java")){
									filenames.add(g.getName()+"."+h.getName()+"."+maybeclass.getName()+"."+level2classes.getName().replaceAll("\\.java", ""));
								}
							}
						}
					}
				}

			}
		}

		//System.out.println(filenames); //debug
		Iterator<String> i=filenames.iterator();

		//collect all the class names from the list
		LinkedList<Class> classesFound=new LinkedList<Class>();
		
		
		while(i.hasNext()){
			try{ //if it isn't possible to load a class from a name, ignore the name
				String classname=i.next();
				
				if(!classname.startsWith("planetguy"))continue;
				Class c=Class.forName(classname);
				System.out.println("[SL] class "+c.getName()+", @"+c.getAnnotation(SLLoad.class));
				if(c.getAnnotation(SLLoad.class)!=null){ //if it isn't marked @SLLoad ignore it
					
					classesFound.add(c);
				}
			}catch(ClassNotFoundException cnfe){continue;}
		}
		//System.out.println(classesFound);//debug
		return classesFound.toArray(new Class[0]);
	}



	public final Class[] moduleClasses;
	public final Class[] blocks,items,entities,custom;
	public final String modname;
	/**
	 * Indicates how the banList should be used.
	 * 
	 * 0: soft blacklist - if a depends b and only b is banned, both will load
	 * 1: hard blacklist - if a depends b and only b is banned, neither will load
	 * 2: soft whitelist - if a depends b and only a is listed, both will load 
	 * 3: hard whitelist - if a depends b and only a is listed, neither will load 
	 */
	private int banMode; 
	private String[] banList;

	/**
	 * IDMap contains all the config data loaded. 
	 */

	public HashMap<String,Integer> IDMap=new HashMap<String,Integer>();

	public SimpleLoader(String modname) throws Exception{
		moduleClasses=discoverSLModules();
		blocks=filterClassesBySuper(Block.class);
		items=filterClassesBySuper(Item.class);
		entities=filterClassesBySuper(Entity.class);
		custom=filterClassesBySuper(CustomModuleLoader.class);
		this.modname=modname;
		System.out.println(formatClasses(moduleClasses));
	}

	private static String getModuleName(Class c){ //simple utility method
		Annotation a=c.getAnnotation(SLLoad.class);
		SLLoad sll=(SLLoad) a;
		return sll.name();
	}
	
	private String formatClasses(Class[] classes){
		String s="[";
		for(Class c:classes){
			s+=c.getName()+",";
		}s+="]";
		return s;
	}

	private boolean canLoad(Class c){
		return canLoad(getModuleName(c));
	}
	
	private boolean canLoad(String s){
		//TODO hook up banning with dependency system
		if(banMode==0){//soft blacklist
			
		}
		return true; //for now anyway
	}

	public String[] getModuleNames(){
		LinkedList<String> moduleNames=new LinkedList<String>();
		for(Class c:moduleClasses){
			moduleNames.add(getModuleName(c));
		}
		return moduleNames.toArray(new String[0]);
	}

	public void setupAndReadConfig(Configuration config) throws Exception{
		Property banModeProp=config.get("Framework", "Ban list mode", 0);
		banModeProp.comment="Ban mode: Even -> dependency overrides list state; 0-1 -> blacklist mode";
		banMode=banModeProp.getInt(0);
		banList=config.get("Framework","Ban list", new String[0]).getStringList();
		
		//and get config values for the game content...
		int currentID=3980;
		for(Class c:blocks){ //go through blocks and ask the config for an ID for each
			if(!canLoad(c))continue;
			int id=config.getBlock(getModuleName(c), currentID).getInt(currentID);
			IDMap.put(getModuleName(c), id);
			++currentID;
			System.out.println(currentID);
		}
		currentID=8100;
		for(Class c:items){ //do the same for items
			if(!canLoad(c))continue;
			int id=config.getItem(getModuleName(c), currentID).getInt(currentID);
			IDMap.put(getModuleName(c), id);
			++currentID;
		}
		for(Class c:custom){
			if(!canLoad(c))continue;
			CustomModuleLoader cml=(CustomModuleLoader) c.newInstance();
			cml.load();
		}
		for(Class c:moduleClasses){
			if(!canLoad(c))continue;
			for(Field f:c.getDeclaredFields()){
				SLProp prop=f.getAnnotation(SLProp.class);
				if(prop!=null){
					String category=prop.category();
					String key=prop.name();
					Object defaultVal=f.get(null); //do field operations with null, assuming static field.
					//Get config props for items that request them.
					if(f.getGenericType().equals(Integer.TYPE)){
						f.setInt(null, config.get(category, key, (Integer) defaultVal).getInt());
					}else if(f.getGenericType().equals(Double.TYPE)){
						f.setDouble(null, config.get(category, key, (Double) defaultVal).getDouble((Double) defaultVal));
					}else if(f.getGenericType().toString()=="class java.lang.String"){
						f.set(null, config.get(category, key, (String) defaultVal).getString());
					}else{
						f.set(null, config.get(category, key, (String[]) defaultVal).getStringList());
					}

				}
			}
		}
	}

	private Class[] filterClassesBySuper(Class superclass){
		ArrayList<Class> classes=new ArrayList<Class>();
		for(Class c:moduleClasses){
			if(superclass.isAssignableFrom(c)){
				classes.add(c);
			}
		}
		return classes.toArray(new Class[0]);
	}

	public void loadClasses() throws Exception{
		System.out.println("[SL] Loading classes...");
		//register blocks
		System.out.println("[SL] Loading blocks...");
		for(Class c:blocks){
			System.out.println("[SL] Loading "+c.getName());
			SLLoad slload=(SLLoad) c.getAnnotation(SLLoad.class);
			Object item = null;
			if(!canLoad(c))continue;
			Constructor[] cons=c.getConstructors();
			for(Constructor con : cons){
				if(con.isAnnotationPresent(SLLoad.class)){
					item=con.newInstance(IDMap.get(getModuleName(c)));
					
					GameRegistry.registerBlock((Block) item, slload.hasMetadata()? ItemBlockWithMetadata.class : ItemBlock.class, modname+"."+getModuleName(c));
				}
			}
			for(Method m:c.getMethods()){
				if(m.isAnnotationPresent(SLLoad.class)){
					m.invoke(item);
				}
			}
		}
		System.out.println("[SL] Loading items...");
		//register items
		for(Class c:items){
			System.out.println("[SL] Loading "+c.getName());
			Object item = null;
			if(!canLoad(c))continue;
			Constructor[] cons=c.getConstructors();
			for(Constructor con : cons){
				if(con.isAnnotationPresent(SLLoad.class)){
					item=con.newInstance(IDMap.get(getModuleName(c)));
					GameRegistry.registerItem((Item)item, modname+"."+getModuleName(c));
				}
			}
			for(Method m:c.getMethods()){
				if(m.isAnnotationPresent(SLLoad.class)){
					m.invoke(item);
				}
			}
		}
		System.out.println("[SL] Loading custom modules...");
		//load custom modules
		for(Class c:custom){
			System.out.println("[SL] Loading "+c.getName());
			if(!canLoad(c))continue;
			CustomModuleLoader cml=(CustomModuleLoader) c.newInstance();
			cml.load();
		}
	}


}
