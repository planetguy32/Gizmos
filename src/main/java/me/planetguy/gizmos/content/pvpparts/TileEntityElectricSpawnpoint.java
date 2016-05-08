package me.planetguy.gizmos.content.pvpparts;

import cofh.api.energy.IEnergyHandler;
import me.planetguy.gizmos.Properties;
import me.planetguy.lib.util.Debug;
import net.minecraft.block.BlockBed;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityElectricSpawnpoint extends TileEntity implements IEnergyHandler, ISpawnController {

	int energy=0;
	
	//Used to lazily simulate energy loss
	long lastTouchedTick=0;
	
    public void writeToNBT(NBTTagCompound t) {
    	t.setInteger("e",energy);
    	t.setLong("ltt", lastTouchedTick);
    }
    
    public void readFromNBT(NBTTagCompound t) {
    	energy=t.getInteger("e");
    	lastTouchedTick=t.getLong("ltt");
    }

    public void updateEnergy() {
    	//check if world time changes, so we don't get stuck
    	if(worldObj.getTotalWorldTime() < lastTouchedTick) {
    		lastTouchedTick=0;
    	}
    	
    	long ticksPassed=worldObj.getTotalWorldTime()-lastTouchedTick;
    	lastTouchedTick=worldObj.getTotalWorldTime();
    	energy=Math.max(0, energy-Properties.ESP_RF_config[1]);
    }
	
	@Override
	public boolean canConnectEnergy(ForgeDirection from) {
		return true;
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive,
			boolean simulate) {
		updateEnergy();
		int toTake=Math.min(maxReceive, getMaxEnergyStored(from)-energy);
		if(!simulate) {
			energy+=toTake;
		}
		return toTake;
	}

	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract,
			boolean simulate) {
		return 0;
	}

	@Override
	public int getEnergyStored(ForgeDirection from) {
		updateEnergy();
		return energy;
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from) {
		return Properties.ESP_RF_config[0];
	}
	
	public boolean canUpdate() {
		return false;
	}

	public boolean spawnPlayerAt() {
		updateEnergy();
		if(energy>=Properties.ESP_RF_config[2]) {
			energy-=Properties.ESP_RF_config[2];
			return true;
		}else {
			return false;
		}
	}
	
	public String getName(){
		return "Base "+xCoord+", "+yCoord+", "+zCoord;
	}

}
