package planetguy.gizmos.rp;

import planetguy.gizmos.Gizmos;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class ContainerBag extends Container{

	private EntityPlayer player;
	private ItemStack bag;

	public ContainerBag(EntityPlayer player, ItemStack stack){
		super();
		this.player=player;
		this.bag=stack;
		for (int y = 0; y < 3; ++y)
		{
			for (int x = 0; x < 9; ++x)
			{
				this.addSlotToContainer(new Slot(wrapStack(stack), x + y * 9, 8 + x * 18, 18 + y * 18));
			}
		}
		bindPlayerInventory(player.inventory);

	}

	public IInventory wrapStack(final ItemStack stk){
		return new IInventory(){

			private ItemStack[] chestContents=loadItems();

			private ItemStack[] loadItems(){
				try{
					NBTTagCompound tag=stk.getTagCompound();
					if(tag==null)tag=new NBTTagCompound();
					
					NBTTagList nbttaglist = tag.getTagList("Items");
					this.chestContents = new ItemStack[this.getSizeInventory()];

					for (int i = 0; i < nbttaglist.tagCount(); ++i)
					{
						NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
						int j = nbttagcompound1.getByte("Slot") & 255;

						if (j >= 0 && j < this.chestContents.length)
						{
							this.chestContents[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
						}
					}
				}catch(Exception e){
					e.printStackTrace();
					chestContents=new ItemStack[27];
				}
				return chestContents;
			}

			private void saveItems(){

				try{
					NBTTagCompound tag=stk.getTagCompound();
					if(tag==null)tag=new NBTTagCompound();
					NBTTagList nbttaglist = new NBTTagList();

					for (int i = 0; i < this.chestContents.length; ++i)
					{
						if (this.chestContents[i] != null)
						{
							NBTTagCompound nbttagcompound1 = new NBTTagCompound();
							nbttagcompound1.setByte("Slot", (byte)i);
							this.chestContents[i].writeToNBT(nbttagcompound1);
							nbttaglist.appendTag(nbttagcompound1);
						}
					}
					tag.setTag("Items", nbttaglist);
					stk.setTagCompound(tag);
				}catch(Exception e){
					e.printStackTrace();
				}

			}

			@Override public int getSizeInventory() {return 27;}

			@Override
			public ItemStack getStackInSlot(int i) {
				loadItems();
				return chestContents[i];
			}

			@Override
			public ItemStack decrStackSize(int i, int j) {
				return null;
			}

			@Override
			public ItemStack getStackInSlotOnClosing(int i) {
				ItemStack s=chestContents[i];
				chestContents[i]=null;
				return s;
			}

			@Override
			public void setInventorySlotContents(int slot, ItemStack itemstack) {
				if(chestContents==null)loadItems();
				chestContents[slot]=itemstack;
				saveItems();
			}

			@Override
			public String getInvName() {
				return "Canvas Bag";
			}

			@Override
			public boolean isInvNameLocalized() {return false;}

			@Override
			public int getInventoryStackLimit() {
				return 64;
			}

			@Override
			public void onInventoryChanged() {
				
			}

			@Override
			public boolean isUseableByPlayer(EntityPlayer entityplayer) {return true;}

			@Override
			public void openChest() {
				loadItems();

			}

			@Override
			public void closeChest() {
				saveItems();
			}

			@Override
			public boolean isItemValidForSlot(int i, ItemStack itemstack) {
				return itemstack.getItem()!=Gizmos.rCanvasBag;
			}
			
		};
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return true;
	}

	protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9,
						8 + j * 18, 84 + i * 18));
			}
		}

		for (int i = 0; i < 9; i++) {
			addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
		}
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
		ItemStack stack = null;
		Slot slotObject = (Slot) inventorySlots.get(slot);

		//null checks and checks if the item can be stacked (maxStackSize > 1)
		if (slotObject != null && slotObject.getHasStack()) {
			ItemStack stackInSlot = slotObject.getStack();
			stack = stackInSlot.copy();

			//merges the item into player inventory since its in the tileEntity
			if (slot < 9) {
				if (!this.mergeItemStack(stackInSlot, 9, 45, true)) {
					return null;
				}
			}
			//places it into the tileEntity is possible since its in the player inventory
			else if (!this.mergeItemStack(stackInSlot, 0, 9, false)) {
				return null;
			}

			if (stackInSlot.stackSize == 0) {
				slotObject.putStack(null);
			} else {
				slotObject.onSlotChanged();
			}

			if (stackInSlot.stackSize == stack.stackSize) {
				return null;
			}
			slotObject.onPickupFromSlot(player, stackInSlot);
		}
		return stack;
	}
}

