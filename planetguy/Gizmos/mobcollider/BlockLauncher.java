package planetguy.Gizmos.mobcollider;

import planetguy.Gizmos.ConfigHolder;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockLauncher extends Block{

	public BlockLauncher(int par1) {
		super(par1,12, Material.ice);
		this.setCreativeTab(CreativeTabs.tabRedstone);
	}

	public String getTextureFile(){
		return "/planetguy/Gizmos/tex.png";
	} 
	
	public  AxisAlignedBB getCollisionBoundingBoxFromPool(World w, int x, int y, int z){
		return null;
	}
	
	public void onEntityCollidedWithBlock(World w, int x, int y, int z, Entity e){
		System.out.println(e);
		e.motionY+=ConfigHolder.launcherPower/5D;
	}

}
