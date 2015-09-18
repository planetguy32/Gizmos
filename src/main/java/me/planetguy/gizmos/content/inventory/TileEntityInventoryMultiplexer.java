package me.planetguy.gizmos.content.inventory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import me.planetguy.lib.util.SidedInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityInventoryMultiplexer extends TileEntity implements ISidedInventory {
	
	//controlled by updateFor()
	public static int newSlot; //slot in inventory
	public static ISidedInventory newInv; //inventory
	public static ForgeDirection side; //side to access inventory from
	
	private int tick=-1;
	private List<InvInt> cachedData;
	
	@Override
    public boolean canUpdate() {
        return false;
    }
	
	class InvInt{
		public ISidedInventory inv;
		public ForgeDirection direction;
	}
	
	public List<InvInt> getConnectedInventories(){
		int thisTick=MinecraftServer.getServer().getTickCounter();
		if(tick != thisTick) {
			updateCachedData();
		}
		return cachedData;
	}
	
	public void updateCachedData() {
		ArrayList<InvInt> invs=new ArrayList<InvInt>(6);
		for(ForgeDirection dir:ForgeDirection.VALID_DIRECTIONS) {
			if(dir.ordinal() != worldObj.getBlockMetadata(xCoord, yCoord, zCoord)) {
				InvInt ii=new InvInt();
				ISidedInventory neighbour=SidedInventory.get(worldObj.getTileEntity(xCoord+dir.offsetX, yCoord+dir.offsetY, zCoord+dir.offsetZ));
				if(neighbour != null) {
					ii.inv=neighbour;
					ii.direction=dir;
					invs.add(ii);
				}
			}
		}
		invs.trimToSize();
		this.cachedData=invs;
		this.tick=MinecraftServer.getServer().getTickCounter();
	}
	
	public void updateFor(int slot) {
		newInv=null;
		for(InvInt inv:getConnectedInventories()) {
			int newSlot=slot-inv.inv.getSizeInventory();
			if(newSlot < 0) {
				newInv=inv.inv;
				this.newSlot=slot;
				side=inv.direction;
				return;
			} else {
				slot = newSlot;
			}
		}
	}
	
	@Override
	public int getSizeInventory() {
		int slots=0;
		ChunkCoordinates here=new ChunkCoordinates(xCoord, yCoord, zCoord);
		Set<ChunkCoordinates> recursionCheck=new HashSet<ChunkCoordinates>();
		recursionCheck.add(here);
		for(InvInt inv:getConnectedInventories()) {
			if(inv.inv instanceof TileEntityInventoryMultiplexer) {
				slots += ((TileEntityInventoryMultiplexer)inv.inv).getSizeInventory(recursionCheck);
			} else {
				slots += inv.inv.getSizeInventory();
			}
		}
		return slots;
	}
	
	public int getSizeInventory(Set<ChunkCoordinates> recursionCheck) {
		int slots=0;
		ChunkCoordinates here=new ChunkCoordinates(xCoord, yCoord, zCoord);
		if(!recursionCheck.contains(here)) {
			recursionCheck.add(here);
			for(InvInt inv:getConnectedInventories()) {
				if(inv.inv instanceof TileEntityInventoryMultiplexer) {
					slots += ((TileEntityInventoryMultiplexer)inv.inv).getSizeInventory(recursionCheck);
				} else {
					slots += inv.inv.getSizeInventory();
				}
			}
		}
		return slots;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		updateFor(slot);
		try {
			return newInv==null ? null : newInv.getStackInSlot(newSlot);
		}catch(StackOverflowError e) { //if built in loop, this happens with HoloInventory
			return null;
		}
	}

	@Override
	public ItemStack decrStackSize(int slot, int n) {
		updateFor(slot);
		return newInv==null ? null : newInv.decrStackSize(newSlot, n);
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		updateFor(slot);
		return newInv==null ? null : newInv.getStackInSlotOnClosing(newSlot);
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack s) {
		updateFor(slot);
		if(newInv!=null)
			newInv.setInventorySlotContents(newSlot, s);
	}

	@Override
	public String getInventoryName() {
		return "InventoryMultiplexer";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
		return false;
	}

	// we don't use these
	@Override
	public void openInventory() {}
	@Override
	public void closeInventory() {}

	
	@Override
	public boolean isItemValidForSlot(int slot, ItemStack s) {
		updateFor(slot);
		if(newInv!=null)
			return newInv.isItemValidForSlot(newSlot, s);
		else
			return false;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		if(side == worldObj.getBlockMetadata(xCoord, yCoord, zCoord)) {
			ArrayList<Integer> ints=new ArrayList<Integer>();
			int slotOffset=0;
			for(InvInt inv:getConnectedInventories()) {
				//add that inv's getAccessibleSlotsFromSide(), shifted with an offset
				for(int i:inv.inv.getAccessibleSlotsFromSide(inv.direction.getOpposite().ordinal())) {
					ints.add(i+slotOffset);
				}
				slotOffset += inv.inv.getSizeInventory();
			}
			return unbox(ints.toArray(new Integer[0]));
		} else {
			return new int[0];
		}
	}

	private int[] unbox(Integer[] array) {
		int[] a=new int[array.length];
		for(int i=0; i<a.length; i++) {
			a[i]=array[i];
		}
		return a;
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack stk,
			int side) {
		if(side == worldObj.getBlockMetadata(xCoord, yCoord, zCoord)) {
			updateFor(slot);
			if(newInv != null)
				return newInv.canInsertItem(newSlot, stk, this.side.ordinal());
		}
		return false;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack stk,
			int side) {
		if(side == worldObj.getBlockMetadata(xCoord, yCoord, zCoord)) {
			updateFor(slot);
			if(newInv != null)
				return newInv.canExtractItem(newSlot, stk, this.side.ordinal());
		}
		return false;
	}

}
