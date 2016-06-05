package me.planetguy.gizmos.content.clearblocks;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public final class MaterialSolidAir extends Material {
	public static final Material instance = new MaterialSolidAir();

	MaterialSolidAir() {
		super(Material.air.getMaterialMapColor());
		this.setReplaceable();
	}

	public boolean getCanBlockGrass(){return false;}

	public boolean blocksMovement(){return false;}
	
	public boolean isReplaceable(){
		return !BlockAiry.isRevealed();
	}
}