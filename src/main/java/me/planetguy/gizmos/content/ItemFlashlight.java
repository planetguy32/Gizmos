package me.planetguy.gizmos.content;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.HashMap;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ServerTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.WorldTickEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.util.ForgeDirection;
import me.planetguy.gizmos.Gizmos;
import me.planetguy.lib.prefab.BlockBase;
import me.planetguy.lib.prefab.ItemBase;
import me.planetguy.lib.util.BlockRecord;
import me.planetguy.lib.util.Debug;

public class ItemFlashlight extends ItemBase{

	public static Block block;

	public ItemFlashlight() {
		super("flashlight");
		BlockBase.load(BlockLightRay.class, new HashMap<String, IPrefabItem>());
	}


	public void onUpdate(ItemStack stk, World w, Entity e, int p_77663_4_, boolean p_77663_5_){
		if(e instanceof EntityLivingBase && active(stk)){ //only EntityLivingBases have look directions, check if itemstack should be active
			if(e instanceof EntityPlayer && (((EntityLivingBase) e).getHeldItem() == null || ((EntityLivingBase) e).getHeldItem().getItem() != this))
				return; //if player is not holding this
			MovingObjectPosition pos=rayTrace((EntityLivingBase) e, 20);
			if(pos==null)return;
			ForgeDirection dir=ForgeDirection.getOrientation(pos.sideHit);
			placeLightBlock(w, pos.blockX+dir.offsetX, pos.blockY+dir.offsetY, pos.blockZ+dir.offsetZ);
		}
	}

	public void placeLightBlock(World w, int x, int y, int z){
		if(w.isAirBlock(x,y,z)){
			w.setBlock(x,y,z,block,1,0x02); //set to light ray with meta 1
			w.scheduleBlockUpdate(x,y,z,block, block.tickRate(w));
		}
	}
	
	public boolean active(ItemStack stk){
		return true;
	}

	/* =============================================================================
	 * Ray-tracing code based on EntityPlayer - included here so available on server
	 * =============================================================================
	 */

	public Vec3 getPosition(Entity p)
	{
		return Vec3.createVectorHelper(p.posX, p.posY, p.posZ);
	}

	public MovingObjectPosition rayTrace(EntityLivingBase p, double distance)
	{
		Vec3 position = getPosition(p);
		Vec3 look = p.getLook(1);
		Vec3 adjustedLook = position.addVector(look.xCoord * distance, look.yCoord * distance, look.zCoord * distance);
		return p.worldObj.func_147447_a(position, adjustedLook, false, false, true);
	}
	
	/*
	 * The actual light ray block. Works much like air.
	 * To avoid blinking, place it with metadata 1 - it sets its meta to 0 automatically if not refreshed, and if it updates while its meta is 0 it disappears.
	 */

	public static class BlockLightRay extends BlockBase{

		public BlockLightRay(){
			//can't use Material.air - MC drops your scheduled ticks
			super(new Material(Material.air.getMaterialMapColor()), "lightRay");
			this.setLightLevel(1.0f);
			this.setTickRandomly(true);
		}

		//pretty fast - faster can be hard on performance, slower can lead to light sticking around after you turn away
		public int tickRate(World w){
			return 2;
		}

		public void updateTick(World w, int x, int y, int z, Random rand){
			if(w.getBlockMetadata(x,y,z)!=0){
				w.setBlockMetadataWithNotify(x, y, z, 0, 0x02);
				w.scheduleBlockUpdate(x, y, z, this, tickRate(w));
			}else{
				w.setBlockToAir(x, y, z);
			}
		}

		//request callback when placed
		public void onBlockAdded(World w, int x, int y, int z){
			w.scheduleBlockUpdate(x, y, z, this, tickRate(w));
		}

		//same as air
		public int getRenderType(){
			return -1;
		}

		//not opaque cube
		public boolean isOpaqueCube(){
			return false;
		}

		//no AABB
		public AxisAlignedBB getCollisionBoundingBoxFromPool(World w, int x, int y, int z){
			return null;
		}

		//no drops
		public void dropBlockAsItemWithChance(World w, int x, int y, int z, int idk, float what, int thisis) {}

		//no collisions
		public boolean canCollideCheck(int p_149678_1_, boolean p_149678_2_){
			return false;
		}

		//update on neighbour change
		public void onNeighborBlockChange(World w, int x, int y, int z, Block b){
			w.scheduleBlockUpdate(x, y, z, this, tickRate(w));
		}

		//is air
		public boolean isAir(IBlockAccess world, int x, int y, int z){
			return true;
		}

	}
}
