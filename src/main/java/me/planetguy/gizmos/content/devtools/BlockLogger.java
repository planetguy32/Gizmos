package me.planetguy.gizmos.content.devtools;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import me.planetguy.lib.prefab.BlockBase;
import me.planetguy.lib.util.Debug;

/**
 * 
 * Logs calls to its methods.
 *
 */
public class BlockLogger extends BlockBase{

	public BlockLogger() {
		super(Material.rock, "loggingBlock");
	}
	
	public void breakBlock(World w, int x, int y, int z, Block b, int m)
    {
		Debug.dbg("breakBlock");
		super.breakBlock(w, x, y, z, b, m);
    }
	
    public void onNeighborBlockChange(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_){
    	Debug.dbg("onNeighborBlockChange");
    	super.onNeighborBlockChange(p_149695_1_, p_149695_2_, p_149695_3_, p_149695_4_, p_149695_5_);
    }


}
