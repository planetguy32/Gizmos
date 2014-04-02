package planetguy.lapis.implement.forge.loader;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import planetguy.simpleLoader.SLLoad;
import planetguy.util.Debug;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockLoader {
	
	public void loadBlock(Class c) throws Exception{
		Debug.dbg("[SL] Loading "+c.getName());
		SLLoad slload=(SLLoad) c.getAnnotation(SLLoad.class);
		Object block = null;
		Constructor[] cons=c.getConstructors();
		for(Constructor con : cons){
			if(con.isAnnotationPresent(SLLoad.class)){
				try{
					block=con.newInstance();
					Field f=modcontainer.getClass().getDeclaredField(getModuleName(c));
					f.set(modcontainer,block);
				}catch(Exception e){
					e.printStackTrace();
					Debug.dbg(c.getName());
					throw(e);
				}

				GameRegistry.registerBlock((Block) block, 
						(Class<? extends ItemBlock>)
						(slload.itemClass()!="net.minecraft.item.ItemBlockWithMetadata"? 
								Class.forName(slload.itemClass()) 
								: ItemBlock.class),
								modname+"."+getModuleName(c));
			}
		}
		for(Method m:c.getMethods()){
			if(m.isAnnotationPresent(SLLoad.class)){
				m.invoke(block);
			}
		}
	}

}
