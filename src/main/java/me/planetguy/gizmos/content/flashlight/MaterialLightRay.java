package me.planetguy.gizmos.content.flashlight;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

final class MaterialLightRay extends Material {
	MaterialLightRay(MapColor p_i2116_1_) {
		super(p_i2116_1_);
		this.setReplaceable();
	}

	public boolean getCanBlockGrass(){return false;}

	public boolean blocksMovement(){return false;}
}