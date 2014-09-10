package me.planetguy.gizmos.content;

import me.planetguy.gizmos.Gizmos;
import me.planetguy.gizmos.base.BlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class BlockAccelerator extends BlockBase{
	
	public static double accelRate=1.16158634964;
	
	public BlockAccelerator() {
		super(Material.glass, "accelerator");
		this.slipperiness=(float) accelRate;
	}
	
	public int countTooltipLines(){
		return 1;
	}
	
}
