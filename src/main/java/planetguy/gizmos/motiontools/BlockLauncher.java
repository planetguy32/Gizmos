package planetguy.gizmos.motiontools;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import planetguy.gizmos.Gizmos;
import planetguy.simpleLoader.SLItemBlock;
import planetguy.simpleLoader.SLLoad;
import planetguy.simpleLoader.SLProp;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

@SLLoad(name="launcher",primacy=10)
public class BlockLauncher extends Block{

	@SLProp(name="launcherPower")
	public static double launcherPower=10D;
	
	@SLLoad
	public BlockLauncher(int par1) {
		super(par1, Material.ice);
		this.func_149647_a(CreativeTabs.tabRedstone);
		Gizmos.launcher=this.func_149663_c("entityLauncher");
		LanguageRegistry.instance().addName(Gizmos.launcher, "Launcher");
		SLItemBlock.registerString(par1, 0, "Launcher", new String[]{"Anything inside goes up, fast."});
	}

	public void registerIcons(IIconRegister ir){
		this.field_149761_L=ir.registerIcon(Gizmos.modName+":collider");
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
