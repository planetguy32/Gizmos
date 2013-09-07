package planetguy.gizmos.CES;

import java.util.ArrayList;

import cpw.mods.fml.common.registry.GameRegistry;

import planetguy.gizmos.Gizmos;
import planetguy.gizmos.CES.powerups.Powerup;
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
	
	public TileEntityCESBomb(){
		super();
		this.cesContainer=new CESContainer();
	}

	public CESContainer cesContainer;
	
	public TileEntityCESBomb copy(){
		NBTTagCompound tag=new NBTTagCompound();
		super.writeToNBT(tag);
		TileEntityCESBomb bomb=new TileEntityCESBomb();
		bomb.readFromNBT(tag);
		return bomb;
	}


}
