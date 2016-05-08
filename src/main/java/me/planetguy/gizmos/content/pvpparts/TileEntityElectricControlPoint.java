package me.planetguy.gizmos.content.pvpparts;

public class TileEntityElectricControlPoint extends TileEntityElectricSpawnpoint implements ISpawnController {
	
	public String getName(){
		return "Control point "+xCoord+", "+yCoord+", "+zCoord;
	}
	
}
