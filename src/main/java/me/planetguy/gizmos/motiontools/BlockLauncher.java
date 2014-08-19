package me.planetguy.gizmos.motiontools;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import me.planetguy.core.sl.SLItemBlock;
import me.planetguy.core.sl.SLLoad;
import me.planetguy.core.sl.SLProp;
import me.planetguy.gizmos.Gizmos;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

@SLLoad(name="launcher")
public class BlockLauncher extends Block{

	@SLProp(name="launcherPower")
	public static double launcherPower=10D;
	
	@SLLoad
	public BlockLauncher() {
		super(Material.ice);
		LanguageRegistry.instance().addName(Gizmos.launcher, "Launcher");
		SLItemBlock.registerString(this, 0, "Launcher", new String[]{"Anything inside goes up, fast."});
	}

	public void registerIcons(IIconRegister ir){
		this.blockIcon=ir.registerIcon(Gizmos.modName+":collider");
	}
	
	public  AxisAlignedBB getCollisionBoundingBoxFromPool(World w, int x, int y, int z){
		return null;
	}
	
	@Override
	public void onEntityCollidedWithBlock(World w, int x, int y, int z, Entity e){
		//Debug.dbg(e);
		e.motionY+=launcherPower/5D;
	}

}
