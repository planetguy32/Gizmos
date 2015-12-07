package me.planetguy.gizmos;

import me.planetguy.gizmos.content.hologram.TESRHologramProjector;
import me.planetguy.gizmos.content.hologram.TileEntityHologramProjector;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy{
	
	public void loadRendering() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHologramProjector.class, new TESRHologramProjector());
	}

}
