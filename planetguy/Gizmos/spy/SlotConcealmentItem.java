package planetguy.Gizmos.spy;

import planetguy.Gizmos.ContentLoader;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
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
			return i.stackSize==1;
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
			NBTTagCompound futureTag=new NBTTagCompound("Planetguy-spy");
			if(inventory.getStackInSlot(0).getTagCompound()==null&&me.getTagCompound()==null){
				
				inventory.getStackInSlot(0).writeToNBT(futureTag);
				NBTTagCompound oldTag=me.getTagCompound();
				NBTTagCompound combinedTag=(NBTTagCompound) NBTTagCompound.newTag((byte) 10,"");
				combinedTag.setCompoundTag("spydata", futureTag);
				me.setTagCompound(futureTag);
				inventory.setInventorySlotContents(0, null);
			}
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
					int id=tag.getShort("id");
					int meta=tag.getShort("damage");
					inventory.setInventorySlotContents(2, new ItemStack(id,1,meta));
					storingItem.setTagCompound(null);
				}
			}catch(NullPointerException npe){
				//Can't pull an item out of that
			}
		}
	}
}
