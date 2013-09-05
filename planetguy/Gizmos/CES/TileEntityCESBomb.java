package planetguy.Gizmos.CES;

import java.util.ArrayList;

import cpw.mods.fml.common.registry.GameRegistry;

import planetguy.Gizmos.Gizmos;
import planetguy.Gizmos.CES.powerups.Powerup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;

public class TileEntityCESBomb extends TileEntity implements Cloneable{
	
	static{
		GameRegistry.registerTileEntity(TileEntityCESBomb.class, Gizmos.modName+"_customBomb");
	}
	
	private ArrayList<Powerup> installedPowerups=new ArrayList<Powerup>();
	
	public TileEntityCESBomb(){
		super();
	}
	
	public void addPowerup(Powerup p){
		installedPowerups.add(p);
	}
	
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Powerups");
        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
           NBTTagByte powerup=(NBTTagByte) nbttaglist.tagAt(i);
           installedPowerups.add(Powerup.powerups[powerup.data]);
        }
    }
	
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        NBTTagList powerups = new NBTTagList();
        for(Powerup p:installedPowerups.toArray(new Powerup[0])){
        	powerups.appendTag(new NBTTagByte("powerup",p.getID()));
        }
        par1NBTTagCompound.setTag("Powerups", powerups);
    }

	public Powerup[] getInstalledPowerups() {
		return (Powerup[]) installedPowerups.toArray(new Powerup[0]);
	}

	public TileEntity copy() {
		TileEntityCESBomb bomb= new TileEntityCESBomb();
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
