package planetguy.gizmos.multiblock;

import com.google.common.collect.ImmutableList;

import planetguy.util.Point;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

/**
 * A tile entity for the composter based on a hopefully better-performing model of an ultra-lightweight onTick method.
 * @author bill
 *
 */
public class TileEntityComposter extends TileEntity implements IInventory{
	
	public ItemStack[] inventory=new ItemStack[27];
	
	public boolean isEmpty;
	public int accumulatedComposting;
	
	public static ImmutableList<Integer> compostableItems;
	
	public TileEntityComposter(){
		super();
	}
	
	/**
	 * 
	 * @return whether the composter is surrounded by wood and covered on top (no sky light)
	 */
	public boolean isValidComposter(){
		if(worldObj.canBlockSeeTheSky(xCoord, yCoord, zCoord))return false; //Return false if has sky light.
		for(int x=-1; x<=1; x++){
			for(int y=-1; y<=1; y++){
				for(int z=-1; z<=1; z++){
					if(x==0&&y==0&&z==0)continue; //Ignore center block. We don't care if it's wood or not.
					if(worldObj.getBlockMaterial(xCoord+x, yCoord+y, zCoord+z)!=Material.wood
							&&worldObj.isBlockNormalCube(xCoord+x, yCoord+y, zCoord+z)){ 
						return false; //if block isn't solid wooden cube return false
					}
				}
			}
		}
		return true;
	}
	
	@Override
	public int getSizeInventory() {
		return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		ItemStack returnval=inventory[i];
		inventory[i]=null;
		return returnval;
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		if(inventory[i].stackSize<j)
			j=inventory[i].stackSize;
		inventory[i].stackSize-=j;
		return new ItemStack(inventory[i].getItem(),inventory[i].getItemDamage(),j);
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		return inventory[i];
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		inventory[i]=itemstack;
	}

	@Override
	public String getInvName() {
		return "Composter";
	}

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return isValidComposter();
	}

	@Override
	public void openChest() {}

	@Override
	public void closeChest() {}

	@Override
	public void readFromNBT(NBTTagCompound tagCompound) {
		super.readFromNBT(tagCompound);

		NBTTagList tagList = tagCompound.getTagList("Inventory");
		for (int i = 0; i < tagList.tagCount(); i++) {
			NBTTagCompound tag = (NBTTagCompound) tagList.tagAt(i);
			byte slot = tag.getByte("Slot");
			if (slot >= 0 && slot < inventory.length) {
				inventory[slot] = ItemStack.loadItemStackFromNBT(tag);
			}
		}
		this.accumulatedComposting=tagCompound.getInteger("accumulatedWork");
	}

	@Override
	public void writeToNBT(NBTTagCompound tagCompound) {
		super.writeToNBT(tagCompound);

		tagCompound.setInteger("accumulatedWork", this.accumulatedComposting);
		NBTTagList itemList = new NBTTagList();
		for (int i = 0; i < inventory.length; i++) {
			ItemStack stack = inventory[i];
			if (stack != null) {
				NBTTagCompound tag = new NBTTagCompound();
				tag.setByte("Slot", (byte) i);
				stack.writeToNBT(tag);
				itemList.appendTag(tag);
			}
		}
		tagCompound.setTag("Inventory", itemList);
	}
	
	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return compostableItems.contains(itemstack);
	}
	
	/**
	 * Updates on tick.
	 */
	
	@Override
    public void updateEntity(){
		if(!this.isEmpty)++this.accumulatedComposting; //Increase composting counted up
    }
	
	/**
	 * The real update method that changes items.
	 */
	
	public void updateInventoryContents(){
		
	}

}
