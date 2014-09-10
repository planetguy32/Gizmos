package me.planetguy.gizmos.content;

import me.planetguy.gizmos.Gizmos;
import me.planetguy.gizmos.base.BlockBase;
import me.planetguy.gizmos.base.ItemBlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.LanguageRegistry;

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
