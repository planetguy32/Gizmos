package me.planetguy.gizmos.content.hologram;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import me.planetguy.lib.prefab.BlockContainerBase;

public class BlockHologramProjector extends BlockContainerBase {

	public static BlockHologramProjector instance;
	
	public BlockHologramProjector() {
		super(Material.circuits, "hologramProjector", new Class[] {
				TileEntityHologramProjector.class
		});
		instance=this;
	}

}
