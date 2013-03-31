package planetguy.Gizmos;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

import planetguy.Gizmos.spy.BlockPoisonableCake;

import com.jcraft.jorbis.Block;

public class SpyReflector {
	
	public static void doStuff(){
		try {
			for(Field f : Block.class.getDeclaredFields()){
				System.out.println(f.getName());
				if(f.getName().equals("blocksList")){
					
				}
			}
		} catch (Exception e) {
			System.out.println("Reflection failed! Cannot poison cakes.");
			e.printStackTrace();
		}
	}

}
