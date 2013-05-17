package planetguy.Gizmos.loader;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import planetguy.Gizmos.Gizmos;
import planetguy.Gizmos.mobcollider.BlockAccelerator;
import planetguy.Gizmos.mobcollider.BlockLauncher;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class LoaderNodeVelocityManipulators extends LoaderNode {
	
	public static LoaderNode inst=new LoaderNodeVelocityManipulators();
	
	public LoaderNodeVelocityManipulators(){
		super(new LoaderNode[0]);
	}
	
	@Override
	public void load() {
		Gizmos.particleAccelerator=new BlockAccelerator(Gizmos.accelID).setUnlocalizedName("accelerator").setCreativeTab(CreativeTabs.tabRedstone);
		Gizmos.launcher=new BlockLauncher(Gizmos.launcherID).setUnlocalizedName("entityLauncher");
		GameRegistry.registerBlock(Gizmos.launcher, ItemBlock.class, "launcher");
		GameRegistry.registerBlock(Gizmos.particleAccelerator, ItemBlock.class, "accelerator");
		LanguageRegistry.instance().addName(Gizmos.launcher, "Launcher");
		LanguageRegistry.instance().addName(Gizmos.particleAccelerator, "Accelerator");
	}

	@Override
	public String getName() {
		return "accelerators";
	}

}
