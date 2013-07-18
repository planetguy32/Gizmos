package planetguy.Gizmos.spy;

import planetguy.Gizmos.Gizmos;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class SlotConcealmentItem extends Slot{
	
	public static final int TYPE_ITEM_IN=0;
	public static final int TYPE_TOOL_IN=1;
	public static final int TYPE_ITEM_OUT=2;
	public static final int TYPE_TOOL_OUT=3;
	
	private int type;

	public SlotConcealmentItem(IInventory par1iInventory, int par2, int par3, int par4, int type) {
		super(par1iInventory, par2, par3, par4);
		this.type=type;
	}
	
	public boolean isItemValid(ItemStack i){
		if(type==TYPE_TOOL_IN||type==TYPE_TOOL_OUT){
			return i.getMaxStackSize()==1;
		}else{
			return Gizmos.nerfHiding ? i.stackSize==1 : true;
		}
	}
	
	
	
	@Override
    public void onPickupFromSlot(EntityPlayer par1EntityPlayer, ItemStack me){
		super.onPickupFromSlot(par1EntityPlayer, me);
		if(type==TYPE_TOOL_IN){
			ItemStack otherItem=inventory.getStackInSlot(0);
			if(otherItem==null){
				System.out.println("No combining, stack is null");
				return;
			}
			//if(inventory.getStackInSlot(0).getTagCompound()==null){//&&me.getTagCompound()==null
				
				NBTTagCompound oldTag=new NBTTagCompound("tag");
				otherItem.writeToNBT(oldTag);
				me.setTagCompound(oldTag);
				inventory.setInventorySlotContents(0, null);
			//}
		}
	}
	
	@Override
    public void putStack(ItemStack par1ItemStack){	
        this.inventory.setInventorySlotContents(getSlotIndex(), par1ItemStack);
		if(type==TYPE_TOOL_OUT){
			try{
				ItemStack storingItem=inventory.getStackInSlot(3);
				if(storingItem!=null&&inventory.getStackInSlot(2)==null){
					NBTTagCompound tag=storingItem.getTagCompound();
					ItemStack a=ItemStack.loadItemStackFromNBT(tag);
					inventory.setInventorySlotContents(2,a);
					storingItem.setTagCompound(null);
				}
			}catch(NullPointerException npe){
				
			}
		}
	}
}