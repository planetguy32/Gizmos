package planetguy.simpleLoader;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;

import com.jcraft.jorbis.Block;

public class ObfuscatedClassHelper {
	
	public final Class block,
	item,
	entity;
	
	public ObfuscatedClassHelper(boolean isObfuscated) throws ClassNotFoundException{
		if(isObfuscated){
			block=Class.forName("aqw");
			item=Class.forName("yb");
			entity=Class.forName("nm");
		}else{
			block=Block.class;
			item=Item.class;
			entity=Entity.class;
		}
	}

}
