package planetguy.gizmos.multiblock;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

/**
 * Tile entity consisting of a vector (x,y,z) pointing to the "real" tile entity in the MultiMachine.
 * @author planetguy
 *
 */
public class TileEntityParentVector extends TileEntity{
	
	/**
	 * Whether or not the TE has the offsets stored into dx,dy,dz
	 */
	public boolean hasParentData;
	
	/**
	 * The distances that must be added in each dimension to get from here to the parent TE.
	 */
	public int dx,dy,dz;
	
	@Override
	public void writeToNBT(NBTTagCompound tag){
		tag.setInteger("dx", dx);
		tag.setInteger("dy", dy);
		tag.setInteger("dz", dz);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag){
		dx=tag.getInteger("dx");
		dy=tag.getInteger("dy");
		dz=tag.getInteger("dz");
	}

}
