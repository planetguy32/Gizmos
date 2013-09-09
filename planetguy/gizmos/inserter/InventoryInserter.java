package planetguy.gizmos.inserter;

import java.util.Random;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class InventoryInserter implements IInventory {


    private ItemStack[] inv;
    
    private World world;
    private int x, y, z;

    public InventoryInserter(World w, int x, int y, int z){
            inv = new ItemStack[4];
            this.world=w;
            this.x=x;
            this.y=y;
            this.z=z;
    }
   
    @Override
    public int getSizeInventory() {
            return inv.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
            return inv[slot];
    }
   
    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
            inv[slot] = stack;
            if (stack != null && stack.stackSize > getInventoryStackLimit()) {
                    stack.stackSize = getInventoryStackLimit();
            }              
    }

    @Override
    public ItemStack decrStackSize(int slot, int amt) {
            ItemStack stack = getStackInSlot(slot);
            if (stack != null) {
                    if (stack.stackSize <= amt) {
                            setInventorySlotContents(slot, null);
                    } else {
                            stack = stack.splitStack(amt);
                            if (stack.stackSize == 0) {
                                    setInventorySlotContents(slot, null);
                            }
                    }
            }
            return stack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
            ItemStack stack = getStackInSlot(slot);
            if (stack != null) {
                    setInventorySlotContents(slot, null);
            }
            return stack;
    }
   
    @Override
    public int getInventoryStackLimit() {
            return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
            return true;
    }

    @Override
    public void openChest() {}

    @Override
    public void closeChest() {
    	System.out.println("Closed");
    	Random rand = new Random();

        IInventory inventory = this;
        
        for (int i = 0; i < inventory.getSizeInventory(); i++) {
        	ItemStack item = inventory.getStackInSlot(i);
        	
        	if (item != null && item.stackSize > 0) {
        		float rx = rand.nextFloat() * 0.8F + 0.1F;
        		float ry = rand.nextFloat() * 0.8F + 0.1F;
        		float rz = rand.nextFloat() * 0.8F + 0.1F;
        		
        		EntityItem entityItem = new EntityItem(world,
        					x + rx, y + ry, z + rz,
        					new ItemStack(item.itemID, item.stackSize, item.getItemDamage()));
        		
                if (item.hasTagCompound()) {
                	//entityItem.getEntityData().setTagCompound((NBTTagCompound) item.getTagCompound().copy());
                }	
                
                float factor = 0.05F;
                entityItem.motionX = rand.nextGaussian() * factor;
                entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
                entityItem.motionZ = rand.nextGaussian() * factor;
                world.spawnEntityInWorld(entityItem);
                item.stackSize = 0;
        	}
        }
    }
    		
    
    @Override
    public String getInvName() {
    	return "planetguy.spyLab";
    }
    
    @Override
    public void onInventoryChanged() {
    	
    }

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return false;
	}
}