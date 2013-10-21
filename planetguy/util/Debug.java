package planetguy.util;

import java.lang.reflect.Field;
import java.util.logging.Level;

import cpw.mods.fml.common.FMLLog;

public class Debug {
	
	public static void dump(Class c, Object o){
		dbg("Class dump of: "+c.getName());
		for(Field f:c.getDeclaredFields()){
			try {
				f.setAccessible(true);
				dbg(">>Field:"+f.getName()+", value:"+f.get(o));
			} catch (Exception e) {
			}
		}
	}

	public static void dbg(String text){ //less-wordy way to print a message to console
		if(text==null)text="<null>";
		FMLLog.log("Gizmos",Level.INFO, text);
	}
	
}
