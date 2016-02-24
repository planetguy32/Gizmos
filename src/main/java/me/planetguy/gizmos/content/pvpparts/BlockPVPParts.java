package me.planetguy.gizmos.content.pvpparts;

import net.minecraft.block.BlockBed;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import cofh.api.energy.IEnergyHandler;
import me.planetguy.lib.prefab.BlockBase;
import me.planetguy.lib.prefab.BlockContainerBase;

public class BlockPVPParts extends BlockContainerBase {

	public BlockPVPParts() {
		super(Material.cloth, "pvpParts", 
				TileEntityElectricSpawnpoint.class,
				TileEntitySpawnpoint.class);
	}
	
    public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int side, float px, float py, float pz) {
    	switch(w.getBlockMetadata(x, y, z)) {
    	case 0:
    	case 1:
    		return onBlockActivated0(w,x,y,z,p);
    	}
    	return false;
    }
    
    public boolean onBlockActivated0(World w, int x, int y, int z, EntityPlayer p) {
		if(w.isRemote)
			return true;
		ChunkCoordinates bedPosition=new ChunkCoordinates(x,y,z);
		ChunkCoordinates oldSpawn=p.getBedLocation(p.dimension);
		if(oldSpawn != null && !bedPosition.equals(oldSpawn)) {
			p.setSpawnChunk(bedPosition, false);
			p.addChatComponentMessage(new ChatComponentText("Spawn reset to ("+x+", "+y+", "+z+")"));
			return true;
		}
		return false;
    }
    
    public boolean isBed(IBlockAccess w, int x, int y, int z, EntityLivingBase e) {
		return w.getBlockMetadata(x, y, z)==0
				|| w.getBlockMetadata(x,y,z)==1;
    }

    public ChunkCoordinates getBedSpawnPosition(IBlockAccess world, int x, int y, int z, EntityPlayer player) {
    	TileEntity te=world.getTileEntity(x, y, z);
    	if(te instanceof TileEntityElectricSpawnpoint) {
    		if(((TileEntityElectricSpawnpoint)te).isValidSpawnPosition())
    			return BlockBed.func_149977_a((World) world, x, y, z, 0);
    	}
    	return null;
    }
    
    public IIcon getIcon(int side, int meta) {
		return blockIcon;
    }
    
	public  AxisAlignedBB getCollisionBoundingBoxFromPool(World w, int x, int y, int z){
		return null;
	}
	
	@Override
	public void onEntityCollidedWithBlock(World w, int x, int y, int z, Entity e){
    	switch(w.getBlockMetadata(x, y, z)) {
    	case 0:
    	case 1:
    		if(e instanceof EntityPlayer)
    			onBlockActivated0(w,x,y,z,(EntityPlayer) e);
    	}
	}
    
}
