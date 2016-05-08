package me.planetguy.gizmos.content.pvpparts;

import net.minecraft.tileentity.TileEntity;

public class TileEntityControlPoint extends TileEntity implements ISpawnController {

	@Override
	public boolean spawnPlayerAt() {
		return true;
	}
	
}
