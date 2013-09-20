package planetguy.gizmos.motiontools;

import planetguy.gizmos.Gizmos;
import planetguy.simpleLoader.SLLoad;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockIce;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.Icon;

@SLLoad(name="accelerator")
public class BlockAccelerator extends Block{
	
	@SLLoad
	public BlockAccelerator(int par1) {
		super(par1,Material.glass);
		this.slipperiness=Gizmos.accelRate;
		Gizmos.particleAccelerator=this.setUnlocalizedName("accelerator").setCreativeTab(CreativeTabs.tabRedstone);
		GameRegistry.registerBlock(Gizmos.particleAccelerator, ItemBlock.class, "accelerator");
		LanguageRegistry.instance().addName(Gizmos.particleAccelerator, "Accelerator");
	}
	
	public void registerIcons(IconRegister ir){
		this.blockIcon=ir.registerIcon(Gizmos.modName+":accelerator");
	}

}
