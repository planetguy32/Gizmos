package me.planetguy.gizmos.content;

import me.planetguy.lib.prefab.BlockBase;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockLauncher extends BlockBase{

	public static double launcherPower=10D;
	
	public BlockLauncher() {
		super(Material.ice, "launcher");
	}

	public  AxisAlignedBB getCollisionBoundingBoxFromPool(World w, int x, int y, int z){
		return null;
	}
	
	@Override
	public void onEntityCollidedWithBlock(World w, int x, int y, int z, Entity e){
		e.motionY+=launcherPower/5D;
	}
	
	public int countTooltipLines(){
		return 1;
	}

}
