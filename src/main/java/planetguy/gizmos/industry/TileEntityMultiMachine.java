package planetguy.gizmos.industry;

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
 * A tile entity for the multi-machine.
 * @author bill
 *
 */
public class TileEntityMultiMachine extends TileEntity implements IInventory{
	
	
	
	//The main item pool. Items here are acted on by all available recipes.
	public ItemStack[] itemPool=new ItemStack[9];
	
	//If an item matches this, move it to finished.
	public ItemStack[] finishedFilter=new ItemStack[3];

	//The items that define the processes applied go here.
	public ItemStack[] processors=new ItemStack[3];
	
	//Items that have been matched and are ready to be removed.
	public ItemStack[] finished=new ItemStack[9];
	
	public boolean isEmpty;
	public int accumulatedComposting;
	
	public static ImmutableList<Integer> compostableItems;
	
	public TileEntityMultiMachine(){
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
		return itemPool.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		ItemStack returnval=itemPool[i];
		itemPool[i]=null;
		return returnval;
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		if(itemPool[i].stackSize<j)
			j=itemPool[i].stackSize;
		itemPool[i].stackSize-=j;
		return new ItemStack(itemPool[i].getItem(),itemPool[i].getItemDamage(),j);
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		return itemPool[i];
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		itemPool[i]=itemstack;
	}

	@Override
	public String func_145825_b() {
		return "Composter";
	}

	@Override
	public boolean func_145818_k_() {
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

		NBTTagList tagList = tagC(NBTTagList)ompound.getTag("Inventory");
		for (int i = 0; i < tagList.tagCount(); i++) {
			NBTTagCompound tag = (NBTTagCompound) tagList.tagAt(i);
			byte slot = tag.getByte("Slot");
			if (slot >= 0 && slot < itemPool.length) {
				itemPool[slot] = ItemStack.loadItemStackFromNBT(tag);
			}
		}
		this.accumulatedComposting=tagCompound.getInteger("accumulatedWork");
	}

	@Override
	public void writeToNBT(NBTTagCompound tagCompound) {
		super.writeToNBT(tagCompound);

		tagCompound.setInteger("accumulatedWork", this.accumulatedComposting);
		NBTTagList itemList = new NBTTagList().);
		for (int i = 0; i < itemPool.length; i++) {
			ItemStack stack = itemPool[i];
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
		int itemsCanProcess=accumulatedComposting/1000;
		int itemsAlreadyConverted=0;
		for(int i=0; i<itemPool.length-1; i++){
			if(itemsAlreadyConverted==itemsCanProcess)
				break;
			
		}
	}

}
