package planetguy.gizmos;

public class ReflectionHelper {
	
	//0: BlockFire 
	
	private static String[] reflObfuscated;
	private static String[] reflDeobfuscated={"iconArray"};
	public static String[] reflectionStrings;
	
	public static void initialize(boolean isObfuscated){
		if(isObfuscated)
			reflectionStrings=reflObfuscated;
		else 
			reflectionStrings=reflDeobfuscated;
	}

}
