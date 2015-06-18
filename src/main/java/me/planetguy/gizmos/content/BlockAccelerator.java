package me.planetguy.gizmos.content;

import me.planetguy.lib.prefab.BlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

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
