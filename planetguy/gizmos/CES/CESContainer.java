package planetguy.gizmos.CES;

import java.util.ArrayList;

import planetguy.gizmos.CES.powerups.Powerup;

import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class CESContainer { //TODO move logic here from TileEntity - more versatile.
	
	private ArrayList<Powerup> installedPowerups=new ArrayList<Powerup>();
	
	public void addPowerup(Powerup p){
		installedPowerups.add(p);
	}
	
    public void readFromNBT(NBTTagCompound tag){
    	NBTTagList powerups=tag.getTagList("powerups");
    	for(int i=0; i<powerups.tagCount(); i++){
    		NBTTagByte puTag=(NBTTagByte) powerups.tagAt(i);
    		byte id=puTag.data;
    		installedPowerups.add(Powerup.powerups[id]);
    	}
    }
	
    public void writeToNBT(NBTTagCompound tag){
    	NBTTagList list=new NBTTagList("powerups");
    	for(Powerup p:installedPowerups){
    		NBTTagByte puTag=new NBTTagByte("powerupID");
    		puTag.data=p.getID();
    		list.appendTag(puTag);
    	}
    }

	public Powerup[] getInstalledPowerups() {
		return (Powerup[]) installedPowerups.toArray(new Powerup[0]);
	}

	public CESContainer copy() {
		CESContainer bomb= new CESContainer();
		NBTTagCompound tag=new NBTTagCompound();
		this.writeToNBT(tag);
		bomb.readFromNBT(tag);
		return bomb;
	}
	
	public int getPowerupCopiesInstalled(Byte id){
		return 2;
	}

	public boolean tryAddPowerup(Powerup powerup) {
		if(powerup.isValidPowerup(installedPowerups.toArray(new Powerup[0]))){
			addPowerup(powerup);
			return true;
		}
		return false;
	}
}
