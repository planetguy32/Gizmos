package planetguy.Gizmos.CES.powerups;

import planetguy.Gizmos.Gizmos;
import planetguy.Gizmos.CES.TileEntityBomb;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class PowerupForkBomb extends Powerup{

	@Override
	public String getName() {
		return "Fork bomb";
	}

	@Override
	public String getModName() {
		return Gizmos.modName;
	}

	@Override
	public Item getLinkedItem() {
		return Item.ghastTear;
	}
	
	@Override
    public void onBlockDestroyed(World w, int x, int y, int z, TileEntityBomb teBomb) {
		fork(w,x,y,z,teBomb);
	}

	private void fork(World w, int x, int y, int z, TileEntityBomb bomb){
    	if(w.getBlockMaterial(x+1, y, z)==Material.air){
    		w.setBlock(x+1, y, z, Gizmos.baseBombID);
    		w.setBlockTileEntity(x+1, y, z, bomb.copy());
    	}
    	if(w.getBlockMaterial(x-1, y, z)==Material.air){
    		w.setBlock(x-1, y, z, Gizmos.baseBombID);
    		w.setBlockTileEntity(x-1, y, z, bomb.copy());
    	}
    	if(w.getBlockMaterial(x, y+1, z)==Material.air){
    		w.setBlock(x, y+1, z, Gizmos.baseBombID);
    		w.setBlockTileEntity(x, y+1, z, bomb.copy());
    	}
    	if(w.getBlockMaterial(x, y-1, z)==Material.air){
    		w.setBlock(x, y-1, z, Gizmos.baseBombID);
    		w.setBlockTileEntity(x, y-1, z, bomb.copy());
    	}
    	if(w.getBlockMaterial(x, y, z+1)==Material.air){
    		w.setBlock(x, y, z+1, Gizmos.baseBombID);
    		w.setBlockTileEntity(x, y, z+1, bomb.copy());
    	}
    	if(w.getBlockMaterial(x, y, z-1)==Material.air){
    		w.setBlock(x, y, z-1, Gizmos.baseBombID);
    		w.setBlockTileEntity(x, y, z-1, bomb.copy());
    	}
    }
}
