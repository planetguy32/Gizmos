package planetguy.gizmos.motiontools;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import planetguy.gizmos.Gizmos;
import planetguy.simpleLoader.SLLoad;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

@SLLoad(name="launcher")
public class BlockLauncher extends Block{

	@SLLoad
	public BlockLauncher(int par1) {
		super(par1, Material.ice);
		this.setCreativeTab(CreativeTabs.tabRedstone);
		Gizmos.launcher=this.setUnlocalizedName("entityLauncher");
		GameRegistry.registerBlock(Gizmos.launcher, ItemBlock.class, "launcher");
		LanguageRegistry.instance().addName(Gizmos.launcher, "Launcher");
	}

	public void registerIcons(IconRegister ir){
		this.blockIcon=ir.registerIcon(Gizmos.modName+":collider");
	}
	
	public  AxisAlignedBB getCollisionBoundingBoxFromPool(World w, int x, int y, int z){
		return null;
	}
	
	@Override
	public void onEntityCollidedWithBlock(World w, int x, int y, int z, Entity e){
		//System.out.println(e);
		e.motionY+=Gizmos.launcherPower/5D;
	}

}
