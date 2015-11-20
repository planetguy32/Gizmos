package me.planetguy.gizmos.content.pvpparts;

import net.minecraft.block.BlockBed;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import cofh.api.energy.IEnergyHandler;
import me.planetguy.lib.prefab.BlockBase;
import me.planetguy.lib.prefab.BlockContainerBase;

public class BlockElectricSpawnpoint extends BlockContainerBase {

	protected BlockElectricSpawnpoint() {
		super(Material.cloth, "electricSpawnpoint", TileEntityElectricSpawnpoint.class);
	}
	
    public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int side, float px, float py, float pz) {
		if(w.isRemote)
			return false;
		ChunkCoordinates bedPosition=new ChunkCoordinates(x,y,z);
		p.setSpawnChunk(bedPosition, false);
		p.addChatComponentMessage(new ChatComponentText("Spawn reset to ("+x+", "+y+", "+z+")"));
		return true;
    }

    public ChunkCoordinates getBedSpawnPosition(IBlockAccess world, int x, int y, int z, EntityPlayer player) {
    	TileEntity te=world.getTileEntity(x, y, z);
    	if(te instanceof TileEntityElectricSpawnpoint) {
    		return ((TileEntityElectricSpawnpoint)te).getBedSpawnPosition();
    	}
    	return null;
    }
	
	

}
