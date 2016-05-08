package me.planetguy.gizmos;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;
import me.planetguy.gizmos.content.pvpparts.BlockSpawner;
import me.planetguy.lib.util.Debug;

public class FMLEventHandler {
	
	public static void init(){
		FMLCommonHandler.instance().bus().register(new FMLEventHandler());
	}
	
	private FMLEventHandler(){}
	
	@SubscribeEvent
	public void onRespawn(PlayerRespawnEvent e){
		Debug.mark();
		BlockSpawner.relocatePlayer(e.player);
	}

}
