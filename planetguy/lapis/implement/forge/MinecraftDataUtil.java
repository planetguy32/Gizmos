package planetguy.lapis.implement.forge;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class MinecraftDataUtil {
	
	public static BiMap<String, Integer> blockIds=HashBiMap.create(100);
	public static Map<World, WorldWrapper> worldMap=new HashMap<World, WorldWrapper>();
	
	public static void crawlBlocks(){
		for(Block b:Block.blocksList){
			if(b!=null){
				blockIds.put(b.getUnlocalizedName(), b.blockID);
			}
		}
	}
	
	public static Material getMaterial(String s){
		try {
			return (Material) Material.class.getDeclaredField(s).get(null);
		} catch (Exception e){
			return Material.ground;
		}
	}
	
	public static WorldWrapper toLapis(World w){
		return worldMap.get(w);
	}

}
