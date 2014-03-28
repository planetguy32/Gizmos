package planetguy.lapis.api.item;

import java.util.Random;

public interface Drop {
	
	public static final Drop NULL_DROP=new SimpleDrop("null",0,0);
	
	public String[] getDrops(Random r);

}