package planetguy.gizmos.motiontools;

import planetguy.gizmos.Gizmos;
import planetguy.simpleLoader.SLItemBlock;
import planetguy.simpleLoader.SLLoad;
import planetguy.simpleLoader.SLProp;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockIce;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.IIcon;

@SLLoad(name="accelerator", primacy=4)
public class BlockAccelerator extends Block{
	
	@SLProp(name = "acceleratorRate")
	public static double accelRate=1.16158634964;
	
	@SLLoad
	public BlockAccelerator(int par1) {
		super(par1,Material.glass);
		this.slipperiness=(float) accelRate;
		Gizmos.accelerator=this.func_149663_c("accelerator").func_149647_a(CreativeTabs.tabRedstone);
		LanguageRegistry.instance().addName(Gizmos.accelerator, "Accelerator");
		SLItemBlock.registerString(par1, 0, "Accelerator", new String[]{"Anything on top speeds up."});
	}
	
	public void registerIcons(IIconRegister ir){
		this.field_149761_L=ir.registerIcon(Gizmos.modName+":accelerator");
	}

}
