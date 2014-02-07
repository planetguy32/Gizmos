package planetguy.util;

import java.lang.reflect.Field;
import java.util.logging.Level;

import cpw.mods.fml.common.FMLLog;

public class Debug {
	
	public static boolean enable=false;
	
	public static void dump(Class c, Object o){
		if(!enable)return;
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
		if(!enable)return;
		if(text==null)text="<null>";
		FMLLog.log("Gizmos",Level.INFO, "", (Object) text);
	}
	
}
