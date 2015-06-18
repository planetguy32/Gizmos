package me.planetguy.minimods.arrowtweak;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.WorldTickEvent;

//@Mod(modid="planetguy_BetterFlamingArrows", version="1.0")
public class FlamingArrows {
	
	public final class FAEntityViewer {
		//arrow spawn event handler
		@SubscribeEvent
		public void handle(EntityJoinWorldEvent event) {
			Entity e=event.entity;
			if(e instanceof EntityArrow) {
				if(e.isBurning()) {
					World world=event.world;
					if(!burningArrowCache.containsKey(world))
						burningArrowCache.put(world, new HashSet<EntityArrow>());
					HashSet<EntityArrow> arrows=burningArrowCache.get(world);
					synchronized(arrows) {
						arrows.add((EntityArrow) e);
					}
				}
			}
		}
	}

	public final class FATickHandler {
		//tick handler - places the fire
		@SubscribeEvent
		public void handle(WorldTickEvent wte) {
			if(burningArrowCache.containsKey(wte.world)) {
				HashSet<EntityArrow> arrows=burningArrowCache.get(wte.world);
				synchronized(arrows) {
					Iterator<EntityArrow> iter=arrows.iterator();
					while(iter.hasNext()) {
						Entity e=iter.next();
						if(e.worldObj==wte.world) {
							try {
								if(arrowDotIsInGround.getBoolean(e)) {
									iter.remove();
									int x=(int) (e.posX-.5);
									int y=(int) (e.posY);
									int z=(int) (e.posZ-.5);
									if(wte.world.getBlock(x,y,z) ==Blocks.air)
										wte.world.setBlock(x,y,z, Blocks.fire);
								}
							} catch (Exception except) {
								except.printStackTrace();
							}
						}
					}
				}
			}
		}
	}

	private HashMap<World, HashSet<EntityArrow>> burningArrowCache=new HashMap<World, HashSet<EntityArrow>>();
	
	final Field arrowDotIsInGround;
	
	public FlamingArrows() {
		Field iig=null;
		try {
			for(Field f:EntityArrow.class.getDeclaredFields()) {
				System.out.println(f);
				if(!f.isAccessible() && !f.isSynthetic() && f.getType()==boolean.class) {
					iig=f;
					f.setAccessible(true);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(iig==null)
			throw new RuntimeException("Heuristic failed to find isInGround field!");
		arrowDotIsInGround=iig;
	}
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		
		FMLCommonHandler.instance().bus().register(new FATickHandler());
		
		MinecraftForge.EVENT_BUS.register(new FAEntityViewer());
	
	}
	
	

}
