package planetguy.Gizmos.spy;

import java.lang.reflect.Array;
import java.lang.reflect.Field;


import com.jcraft.jorbis.Block;

public class SpyReflector {
	
	public static void doStuff(){
		try {
			for(Field f : Block.class.getDeclaredFields()){
				
			}
		} catch (Exception e) {
			System.out.println("Reflection failed! Cannot poison cakes.");
			e.printStackTrace();
		}
	}

}
