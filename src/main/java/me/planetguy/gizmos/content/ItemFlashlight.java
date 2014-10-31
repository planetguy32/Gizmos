package me.planetguy.gizmos.content;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

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
	}


	public void onUpdate(ItemStack stk, World w, Entity e, int p_77663_4_, boolean p_77663_5_){
		if(e instanceof EntityLivingBase&&w instanceof WorldServer){ //run only on server side - fix non-ticking light beams?
			if(e instanceof EntityPlayer && (((EntityLivingBase) e).getHeldItem() == null || ((EntityLivingBase) e).getHeldItem().getItem() != this))
				return; //if player is not holding this
			MovingObjectPosition pos=rayTrace((EntityLivingBase) e, 20);
			if(pos==null)return;
			ForgeDirection dir=ForgeDirection.getOrientation(pos.sideHit);
			if(w.isAirBlock(pos.blockX+dir.offsetX, pos.blockY+dir.offsetY, pos.blockZ+dir.offsetZ)){
				w.setBlock(pos.blockX+dir.offsetX, pos.blockY+dir.offsetY, pos.blockZ+dir.offsetZ, block, 1, 0x02);
				w.scheduleBlockUpdate(pos.blockX+dir.offsetX, pos.blockY+dir.offsetY, pos.blockZ+dir.offsetZ, block, block.tickRate(w));
			}
		}
	}

	/* ================================================
	 * Server-side ray-tracing code - from EntityPlayer
	 * ================================================
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

	public static class BlockLightRay extends BlockBase{

		public BlockLightRay(){
			super(new Material(Material.air.getMaterialMapColor()), "lightRay");
			this.setLightLevel(1.0f);
			this.setTickRandomly(true);
			block=this;
		}

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
		public void dropBlockAsItemWithChance(World p_149690_1_, int p_149690_2_, int p_149690_3_, int p_149690_4_, int p_149690_5_, float p_149690_6_, int p_149690_7_) {}

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
