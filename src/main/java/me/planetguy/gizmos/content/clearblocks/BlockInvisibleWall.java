package me.planetguy.gizmos.content.clearblocks;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockInvisibleWall extends BlockAiry {

	public BlockInvisibleWall() {
		super("invisibleWall");
		setBlockUnbreakable();
		setResistance(6000000.0F);
		setStepSound(soundTypePiston);
		disableStats();
	}

	//Like any other clear
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World w, int x, int y, int z) {
		return AxisAlignedBB.getBoundingBox(x,y,z,x+1,y+1,z+1);
	}
	
	public boolean canCollideCheck(int p_149678_1_, boolean p_149678_2_){
		return true;
	}
	
}
