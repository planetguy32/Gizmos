package me.planetguy.gizmos.motiontools;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import me.planetguy.core.sl.SLItemBlock;
import me.planetguy.core.sl.SLLoad;
import me.planetguy.core.sl.SLProp;
import me.planetguy.gizmos.Gizmos;
import net.minecraft.block.Block;
import net.minecraft.block.BlockIce;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.IIcon;

@SLLoad(name="accelerator")
public class BlockAccelerator extends Block{
	
	@SLProp(name = "acceleratorRate")
	public static double accelRate=1.16158634964;
	
	@SLLoad
	public BlockAccelerator() {
		super(Material.glass);
		this.slipperiness=(float) accelRate;
		LanguageRegistry.instance().addName(Gizmos.accelerator, "Accelerator");
		SLItemBlock.registerString(this, 0, "Accelerator", new String[]{"Anything on top speeds up."});
	}
	
	public void registerIcons(IIconRegister ir){
		this.blockIcon=ir.registerIcon(Gizmos.modName+":accelerator");
	}

}
