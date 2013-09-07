package planetguy.gizmos.spy;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerSpyLab extends Container {

        protected IInventory inv;

        public ContainerSpyLab (InventoryPlayer inventoryPlayer, IInventory inv){
        	this.inv=inv;

                //the Slot constructor takes the IInventory and the slot number in that it binds to
                //and the x-y coordinates it resides on-screen
                addSlotToContainer(new SlotConcealmentItem(inv, 0, 62 + 18-18, 17, SlotConcealmentItem.TYPE_ITEM_IN));
                addSlotToContainer(new SlotConcealmentItem(inv, 1, 62 + 18-18, 17 + 18, SlotConcealmentItem.TYPE_TOOL_IN));
                addSlotToContainer(new SlotConcealmentItem(inv, 2, 62 + 18+18, 17, SlotConcealmentItem.TYPE_ITEM_OUT));
                addSlotToContainer(new SlotConcealmentItem(inv, 3, 62 + 18+18, 17 + 18, SlotConcealmentItem.TYPE_TOOL_OUT));

                
                bindPlayerInventory(inventoryPlayer);
        }

        @Override
        public boolean canInteractWith(EntityPlayer player) {
                return true;
        }
        
        public void onCraftGuiClosed(EntityPlayer player){
        	super.onContainerClosed(player);
        	for(int i=0; i<=3;i++){
        		if(inv.getStackInSlot(i)!=null){
        			player.dropPlayerItem(inv.getStackInSlot(i));
        		}
        	}
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
        	return null;
        	/*
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
                */
        }
}