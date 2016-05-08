package me.planetguy.gizmos.content.pvpparts;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import cofh.api.energy.IEnergyHandler;
import me.planetguy.lib.prefab.BlockBase;
import me.planetguy.lib.prefab.BlockContainerBase;
import me.planetguy.lib.util.Debug;
import me.planetguy.lib.util.Lang;

public class BlockSpawner extends BlockContainerBase {
	
	public static HashMap<UUID, List<ChunkCoordinates>> backSpawnPoints=new HashMap<UUID, List<ChunkCoordinates>>();

	private IIcon[] icons;
	
	private static Class<TileEntity>[] classes=new Class[]{
			TileEntityElectricSpawnpoint.class,
			TileEntitySpawnpoint.class,
			TileEntityElectricControlPoint.class,
			TileEntityControlPoint.class
	};
	
	public BlockSpawner() {
		super(Material.cloth, "pvpParts", classes);
		//You have to break the energy cell
		this.setResistance(1.0F).setHardness(1.0f).setStepSound(soundTypePiston);
	}
	
    public static boolean reset(EntityPlayer p){
    	ItemStack i=p.getHeldItem();
    	if(i != null && i.getItem() == Item.getItemFromBlock(Blocks.bedrock)) {
    		backSpawnPoints=new HashMap<UUID, List<ChunkCoordinates>>();
    		return true;
    	}
    	return false;
    }
    
    public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int side, float px, float py, float pz){
    	Debug.mark();
    	if(w.isRemote)
			return true;
    	if(reset(p))
    		return false;
		ChunkCoordinates bedPosition=new ChunkCoordinates(x,y,z);
		ChunkCoordinates oldSpawn=p.getBedLocation(p.dimension);
		if(oldSpawn == null || !bedPosition.equals(oldSpawn)) {
			List<ChunkCoordinates> a=backSpawnPoints.get(p.getUniqueID());
			ChatComponentText msg;
			if(a==null){
				a=new ArrayList<ChunkCoordinates>();
				backSpawnPoints.put(p.getUniqueID(), a);
			}
			if(a.size()==0){
				int metadata=w.getBlockMetadata(x, y, z);
				if(metadata > 1) {//are we a waypoint?
					p.addChatComponentMessage(new ChatComponentText("You cannot start at a waypoint!"));
					return false;
				}
			}
			if(a.size() == 0) {
				p.setSpawnChunk(bedPosition, false);
				p.addChatComponentMessage(new ChatComponentText("Base claimed: ("+x+", "+y+", "+z+")"));
			} else {
				p.addChatComponentMessage(new ChatComponentText("Waypoint claimed: ("+x+", "+y+", "+z+")"));
			}
			a.add(bedPosition);
		}
		return true;
    }
    
    public boolean isBed(IBlockAccess w, int x, int y, int z, EntityLivingBase e) {
		return false;
    }
    
    public ChunkCoordinates getBedSpawnPosition(IBlockAccess world, int x, int y, int z, EntityPlayer player) {
    	TileEntity te=world.getTileEntity(x, y, z);
    	if(		(te instanceof TileEntityElectricSpawnpoint //Is here good?
    			&& ((TileEntityElectricSpawnpoint)te).spawnPlayerAt())
    		|| te instanceof TileEntitySpawnpoint) {
    		return new ChunkCoordinates(x, y, z);
    	}
    	return null;
    }
   
    public void breakBlock(World w, int x, int y, int z, Block b, int meta){
    	if(!w.isRemote) {
    		ISpawnController te=(ISpawnController) w.getTileEntity(x, y, z);
    		ChatComponentText a=new ChatComponentText(Lang.translate(modid+":messageSpawnerLost"+meta)+": "+x+", "+y+", "+z);
    		for(EntityPlayer p:(List<EntityPlayer>) w.playerEntities){
    			p.addChatComponentMessage(a);
    		}
    	}
    }
    
    @Override
    public void registerBlockIcons(IIconRegister ir){
    	icons=new IIcon[classes.length];
    	for(int i=0; i<classes.length; i++){
    		icons[i]=ir.registerIcon(modid + ":spawner_"+i);
    	}
    }
    
    public IIcon getIcon(int side, int meta) {
		return icons[meta];
    }
    
	public static void relocatePlayer(EntityPlayer player){
		World world=player.worldObj;
		List<ChunkCoordinates> coords=backSpawnPoints.get(player.getUniqueID());
		if(coords != null){
			ChunkCoordinates c=null;
			for(Iterator<ChunkCoordinates> i=coords.iterator(); i.hasNext(); c=i.next()) {
				TileEntity te=world.getTileEntity(c.posX, c.posY, c.posZ);
				if(!(te instanceof ISpawnController)){
					//Clear out all further possible spawn points
					while(i.hasNext())
						i.remove();
					break;
				}
			}
			if(c!=null){ //found a further spawn point
				c=player.worldObj.getBlock(c.posX, c.posY, c.posZ)
						.getBedSpawnPosition(player.worldObj, c.posX, c.posY, c.posZ, player);
				player.setPositionAndUpdate(c.posX+0.5, c.posY, c.posZ+0.5);
			}
		}
	}
	
}
