package me.planetguy.gizmos.content.pvpparts;

import net.minecraft.tileentity.TileEntity;

public class TileEntitySpawnpoint extends TileEntity implements ISpawnController {

	@Override
	public boolean spawnPlayerAt() {
		return true;
	}
	
	public String getName(){
		return "Base "+xCoord+", "+yCoord+", "+zCoord;
	}

}
