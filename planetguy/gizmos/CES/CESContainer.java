package planetguy.gizmos.CES;

import java.util.ArrayList;

import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import planetguy.gizmos.CES.powerups.Powerup;

public class CESContainer { //TODO move logic here from TileEntity - more versatile.
	
	private ArrayList<Powerup> installedPowerups=new ArrayList<Powerup>();
	
	public void addPowerup(Powerup p){
		installedPowerups.add(p);
	}
	
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Powerups");
        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
           NBTTagByte powerup=(NBTTagByte) nbttaglist.tagAt(i);
           installedPowerups.add(Powerup.powerups[powerup.data]);
        }
    }
	
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        NBTTagList powerups = new NBTTagList();
        for(Powerup p:installedPowerups.toArray(new Powerup[0])){
        	powerups.appendTag(new NBTTagByte("powerup",p.getID()));
        }
        par1NBTTagCompound.setTag("Powerups", powerups);
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

	public boolean tryAddPowerup(Powerup powerup) {
		if(powerup.isValidPowerup(installedPowerups.toArray(new Powerup[0]))){
			addPowerup(powerup);
			return true;
		}
		return false;
	}
}
