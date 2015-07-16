package me.planetguy.gizmos.content.flashlight;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.HashMap;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ServerTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.WorldTickEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.util.ForgeDirection;
import me.planetguy.gizmos.Gizmos;
import me.planetguy.lib.prefab.BlockBase;
import me.planetguy.lib.prefab.ItemBase;
import me.planetguy.lib.prefab.IPrefabItem;
import me.planetguy.lib.util.BlockRecord;
import me.planetguy.lib.util.Debug;

public abstract class ItemFlashlightBase extends ItemBase{

	public static final int updateFlags=0x03;
	
	public static Block block;

	public static final int maxDamage=1000;

	public ItemFlashlightBase(String type) {
		super("flashlight"+type);
		if(block==null)
			block=(Block) Gizmos.helper.loadBlock(BlockLightRay.class, new HashMap<String, IPrefabItem>());
		this.setMaxStackSize(1);
		this.setMaxDamage(maxDamage);
	}


	public void onUpdate(ItemStack stk, World w, Entity e, int p_77663_4_, boolean p_77663_5_){
		if(e instanceof EntityLivingBase  //only EntityLivingBases have look directions - cannot otherwise aim light beam
				&& active(stk, (EntityLivingBase) e) //cast is checked
				&& w instanceof WorldServer){ //do not run placement code on client - cleanup code is server-side-only
			
			if(isNotActuallyUsing(e))
				return; //if player is not holding this
			MovingObjectPosition pos=rayTrace((EntityLivingBase) e, 20);
			if(pos==null)return;
			ForgeDirection dir=ForgeDirection.getOrientation(pos.sideHit);
			BlockLightRay.placeLightBlock(w, pos.blockX+dir.offsetX, pos.blockY+dir.offsetY, pos.blockZ+dir.offsetZ);
		}
	}

	public boolean active(ItemStack stk, EntityLivingBase e) {
		if(stk.hasTagCompound()&&stk.getItemDamage() < this.getMaxDamage()){
			NBTTagCompound tag=stk.getTagCompound();
			if(tag.hasKey("active")){
				if(tag.getBoolean("active")){
					use(stk, e);
					return true;
				}
			}
		}
		return false;
	}
	
	public ItemStack onItemRightClick(ItemStack stk, World w, EntityPlayer p){
		Gizmos.helper.playSound(w, p.posX, p.posY, p.posZ, "switch", 1.0f, 1.0f);
		if(stk.hasTagCompound()){
			NBTTagCompound tag=stk.getTagCompound();
			if(tag.hasKey("active")){
				if(tag.getBoolean("active")){
					tag.setBoolean("active", false);
				}else{
					tag.setBoolean("active", true);
				}
			}
			
		}else{
			NBTTagCompound tag=new NBTTagCompound();
			tag.setBoolean("active", true);
			stk.setTagCompound(tag);
		}
		return stk;
	}
	
	public boolean use(ItemStack stk, EntityLivingBase e){
		if(stk.getItemDamage()<this.getMaxDamage()){
			damageItem(stk,e);
			return true;
		}else{
			return false;
		}
	}
	
	public void damageItem(ItemStack stk, EntityLivingBase e){
		stk.damageItem(1, e);
	}
	
	public boolean isNotActuallyUsing(Entity e){
		return (
				e instanceof EntityPlayer 
				&& (((EntityLivingBase) e).getHeldItem() == null 
					|| ((EntityLivingBase) e).getHeldItem().getItem() != this)
				);
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
		Vec3 position = getPosition(p).addVector(0, p.getEyeHeight()/2, 0);
		Vec3 look = p.getLook(1);
		Vec3 adjustedLook = position.addVector(look.xCoord * distance, look.yCoord * distance, look.zCoord * distance);
		return p.worldObj.func_147447_a(position, adjustedLook, false, false, true);
	}
	
	/*
	 * The actual light ray block. Works much like air.
	 * To avoid blinking, place it with metadata 1 - it sets its meta to 0 automatically if not refreshed, and if it updates while its meta is 0 it disappears.
	 */
}
