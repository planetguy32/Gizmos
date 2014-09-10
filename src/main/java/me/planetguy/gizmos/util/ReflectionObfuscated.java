package me.planetguy.gizmos.util;

import java.util.HashMap;

import me.planetguy.gizmos.util.Debug;

public class ReflectionObfuscated extends Reflection{

	public ReflectionObfuscated(){
		Debug.dbg("Obfuscated Minecraft detected!");
		
		map("getOrCreateChunkWatcher", "func_72690_a");
	}
	
	HashMap<String, String> obfRemapper=new HashMap<String, String>();
	
	public String remap(String s){
		if(obfRemapper.containsKey(s))
			return obfRemapper.get(s);
		else
			return s;
	}
	
	public void map(String dev, String obf){
		obfRemapper.put(dev, obf);
	}

}
