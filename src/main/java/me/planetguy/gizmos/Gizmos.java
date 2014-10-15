package me.planetguy.gizmos;

import java.util.ArrayList;
import java.util.HashMap;

import me.planetguy.gizmos.content.BlockAccelerator;
import me.planetguy.gizmos.content.BlockDynamicWool;
import me.planetguy.gizmos.content.BlockLauncher;
import me.planetguy.gizmos.content.BlockLogger;
import me.planetguy.gizmos.content.BlockTimeBomb;
import me.planetguy.gizmos.content.ItemBombDefuser;
import me.planetguy.gizmos.content.ItemDebugWand;
import me.planetguy.gizmos.content.ItemFireExtinguisher;
import me.planetguy.gizmos.content.ItemRedstoneActivator;
import me.planetguy.gizmos.content.ItemTemporalDislocator;
import me.planetguy.gizmos.content.gravitybomb.BlockGravityBomb;
import me.planetguy.gizmos.content.gravitybomb.EntityGravityBomb;
import me.planetguy.gizmos.content.gravitybomb.EntityTunnelBomb;
import me.planetguy.gizmos.content.inserter.BlockInserter;
import me.planetguy.gizmos.content.inserter.ItemBuildTool;
import me.planetguy.gizmos.content.inventory.BlockInvenswapperBase;
import me.planetguy.gizmos.content.inventory.BlockTelekinesisCatalyst;
import me.planetguy.gizmos.content.inventory.ItemLuncher;
import me.planetguy.lib.prefab.BlockBase;
import me.planetguy.lib.prefab.BlockContainerBase;
import me.planetguy.lib.prefab.CreativeTabPrefab;
import me.planetguy.lib.prefab.IPrefabItem;
import me.planetguy.lib.prefab.ItemBase;
import me.planetguy.lib.prefab.Prefabs;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = Properties.modID, guiFactory="me.planetguy.gizmos.ConfigGUI", version="3.0")
public class Gizmos {

	@Instance(Properties.modID)
	public static Object instance;
	
	HashMap<String, IPrefabItem> content=new HashMap<String, IPrefabItem>();
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent pie){
		Properties.configFile=new Configuration(pie.getSuggestedConfigurationFile());
		Properties.update();
		
		GEventHandler.init();
		
		Prefabs.initialize(Properties.modID);
		
		load(ItemLuncher.class);
		
		load(BlockAccelerator.class);
		
		load(BlockLauncher.class);
		
		load(BlockDynamicWool.class);
		
		load(BlockTimeBomb.class);
		
		load(ItemRedstoneActivator.class);
		
		load(ItemFireExtinguisher.class);
		
		load(BlockGravityBomb.class);
		
		load(BlockInserter.class);
		
		load(BlockTelekinesisCatalyst.class);
		load(BlockInvenswapperBase.class);
		
		load(ItemBuildTool.class);
		load(ItemTemporalDislocator.class);
		load(ItemBombDefuser.class);
		
		load(BlockLogger.class);
		
		load(ItemDebugWand.class);
		
		EntityRegistry.registerModEntity(EntityGravityBomb.class, "GBomb", 201, this, 80, 3, true);
		EntityRegistry.registerModEntity(EntityTunnelBomb.class,  "TBomb", 202, this, 80, 3, true);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent ie){
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
		CreativeTabs tab=new CreativeTabPrefab("gizmosTab", new ItemStack((Block) content.get("gravityBomb"), 1, 1));
		for(IPrefabItem item:content.values()){
			item.setCreativeTab(tab);
		}
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent pie){
		for(IPrefabItem item:content.values()){
			item.loadCrafting();
		}
	}
	
	private void load(Class c){
		if(ItemBase.class.isAssignableFrom(c)){
			ItemBase.load(c, content);
		}else if(BlockBase.class.isAssignableFrom(c)){
			BlockBase.load(c, content);
		}else if(BlockContainerBase.class.isAssignableFrom(c)){
			BlockContainerBase.load(c, content);
		}else{
			throw new RuntimeException("Failed to load "+c+": Not a legal class type!");
		}
	}
	
	
	
	
	
}
