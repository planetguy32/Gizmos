package planetguy.Gizmos.CES;

import java.util.ArrayList;

import cpw.mods.fml.common.registry.GameRegistry;

import planetguy.Gizmos.Gizmos;
import planetguy.Gizmos.CES.powerups.Powerup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;

public class TileEntityBomb extends TileEntity implements Cloneable{
	
	static{
		GameRegistry.registerTileEntity(TileEntityBomb.class, Gizmos.modName+"_customBomb");
	}
	
	private ArrayList<ItemStack> powerupItems=new ArrayList<ItemStack>();
	
	private ArrayList<Powerup> installedPowerups=new ArrayList<Powerup>();
	
	public TileEntityBomb(){
		super();
	}
	
	public void addPowerup(Powerup p){
		Item i=p.getLinkedItem();
		boolean hasPlacedInStack=false;
		for(ItemStack stack:getPowerupItems()){
			if(stack!=null){
				if(stack.getItem()==i){
					hasPlacedInStack=true;
					stack.stackSize++;
				}
			}
		}
		if(!hasPlacedInStack){//There isn't already a stack to add the powerup to
			ItemStack pu=new ItemStack(i);
			installedPowerups.add(p);
			powerupItems.add(pu);
		}
	}
	
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items");

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
            int j = nbttagcompound1.getByte("Slot") & 255;

            if (j >= 0 && j < this.getPowerupItems().length)
            {
                this.powerupItems.add(ItemStack.loadItemStackFromNBT(nbttagcompound1));
            }
        }
        for(ItemStack stk : powerupItems){
        	installedPowerups.add(Powerup.powerupRegistry.get(stk.getItem()));
        }
    }
	
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.getPowerupItems().length; ++i)
        {
            if (this.getPowerupItems()[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.getPowerupItems()[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }
        par1NBTTagCompound.setTag("Items", nbttaglist);
    }

	private ItemStack[] getPowerupItems() {
		return (ItemStack[]) powerupItems.toArray(new ItemStack[0]);
	}

	public Powerup[] getInstalledPowerups() {
		return (Powerup[]) installedPowerups.toArray(new Powerup[0]);
	}

	public TileEntity copy() {
		TileEntityBomb bomb= new TileEntityBomb();
		NBTTagCompound tag=new NBTTagCompound();
		this.writeToNBT(tag);
		bomb.readFromNBT(tag);
		return bomb;
	}

}
