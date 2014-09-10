package me.planetguy.gizmos;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import me.planetguy.gizmos.content.PortalHandler;
import me.planetguy.gizmos.util.Debug;
import me.planetguy.gizmos.util.Reflection;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.item.EntityMinecartFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.minecart.MinecartInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class GEventHandler {
	
	public static void init(){
		MinecraftForge.EVENT_BUS.register(new GEventHandler());
	}
	
	private GEventHandler(){}
	
	@SubscribeEvent
	public void onMinecartEvent(MinecartInteractEvent ev){
		if(!Properties.enableMinecartTweaks)return; //ignore if turned off
		EntityMinecart cart=ev.minecart;
		if(cart instanceof EntityMinecartFurnace){
			EntityPlayer player=ev.player;
	        if(player.isSneaking()){ 
	        	//invert motion if player is sneaking - allows making furnace cart come
	        	EntityMinecartFurnace fc=(EntityMinecartFurnace) cart;
	        	fc.pushX = -(fc.posX - player.posX);
	        	fc.pushZ = -(fc.posZ - player.posZ);
	        	ev.setCanceled(true); //stop normal handling - we took care of it
	        }
		}
	}
	
	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent evt){
		Debug.dbg("Event: "+evt);
		if(evt.modID.equals(Properties.modID)){
			Properties.update();
		}
	}
	
	@SubscribeEvent
	public void onInteract(PlayerInteractEvent pie){
		ItemStack i=pie.entityPlayer.getCurrentEquippedItem();
		if(i!=null && i.getItem().equals(Items.flint_and_steel)){
			PortalHandler.establishPortal(pie.world, pie.x, pie.y, pie.z, pie.face);
		}
	}

}
