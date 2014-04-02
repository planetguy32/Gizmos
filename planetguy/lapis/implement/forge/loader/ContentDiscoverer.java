package planetguy.lapis.implement.forge.loader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.objectweb.asm.ClassReader;

import net.minecraft.launchwrapper.IClassTransformer;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.MCVersion;

public class ContentDiscoverer implements IClassTransformer{
	
	public static HashMap<String,ArrayList<String>> classNames=new HashMap<String,ArrayList<String>>();
	Pattern p = Pattern.compile("^.*?(?= word)");//Cache pattern for package name separation
		
	public static void load(Context modContext){
		ArrayList<String> thisModClasses=classNames.get(modContext.packageName());
		
		classNames.remove(modContext.packageName());//Destroy the reference once it's done, don't waste memory.
	}

	@Override
	public byte[] transform(String arg0, String arg1, byte[] bytecode) {
		ClassReader cr=new ClassReader(bytecode);
		for(String s:cr.getInterfaces()){
			if(s.contains("planetguy.lapis.api")){
				putInClassNames(cr);
				break; //Don't keep checking interfaces if find a LAPIS one. We already know it's probably ours.
			}
		}
		if(cr.getSuperName().contains("planetguy.lapis.api")){
			putInClassNames(cr);
		}
		return bytecode;
	}


	
	private void putInClassNames(ClassReader cr){
	    Matcher m = p.matcher(cr.getClassName());
	    while (m.find()) {
	        System.out.println("matched "+ m.group());
	    }
		String packge=cr.getClassName();
		if(classNames.containsKey(packge.substring(0, 15))){//map by digest of class package.
			
		}
	}
}
