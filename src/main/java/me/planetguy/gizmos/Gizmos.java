package me.planetguy.gizmos;

import java.util.ArrayList;
import java.util.HashMap;

import me.planetguy.gizmos.content.BlockAccelerator;
import me.planetguy.gizmos.content.BlockDynamicWool;
import me.planetguy.gizmos.content.BlockLauncher;
import me.planetguy.gizmos.content.BlockTimeBomb;
import me.planetguy.gizmos.content.ItemBombDefuser;
import me.planetguy.gizmos.content.ItemFireExtinguisher;
import me.planetguy.gizmos.content.ItemRedstoneActivator;
import me.planetguy.gizmos.content.ItemSprayPaintCan;
import me.planetguy.gizmos.content.ItemTemporalDislocator;
import me.planetguy.gizmos.content.admin.ItemCreativeEnderPearl;
import me.planetguy.gizmos.content.admin.ItemStickOfWhacking;
import me.planetguy.gizmos.content.clearblocks.BlockInvisibleWall;
import me.planetguy.gizmos.content.clearblocks.BlockPersistentGlowingAir;
import me.planetguy.gizmos.content.clearblocks.BlockSmoker;
import me.planetguy.gizmos.content.clearblocks.ItemRevealer;
import me.planetguy.gizmos.content.devtools.BlockLogger;
import me.planetguy.gizmos.content.devtools.ItemDebugWandFieldwise;
import me.planetguy.gizmos.content.devtools.ItemDebugWandNBT;
import me.planetguy.gizmos.content.devtools.ItemDebugWandReloading;
import me.planetguy.gizmos.content.flashlight.ItemFlashlightBase;
import me.planetguy.gizmos.content.flashlight.ItemFlashlightGlowstone;
import me.planetguy.gizmos.content.flashlight.ItemFlashlightNetherStar;
import me.planetguy.gizmos.content.flashlight.ItemFlashlightRF;
import me.planetguy.gizmos.content.flashlight.ItemHeadlampGlowstone;
import me.planetguy.gizmos.content.flashlight.ItemHeadlampNetherStar;
import me.planetguy.gizmos.content.flashlight.ItemHeadlampRF;
import me.planetguy.gizmos.content.gravitybomb.BlockGravityBomb;
import me.planetguy.gizmos.content.gravitybomb.EntityGravityBomb;
import me.planetguy.gizmos.content.gravitybomb.EntityTunnelBomb;
import me.planetguy.gizmos.content.hologram.BlockHologramProjector;
import me.planetguy.gizmos.content.inserter.BlockInserter;
import me.planetguy.gizmos.content.inserter.ItemBuildTool;
import me.planetguy.gizmos.content.inventory.BlockInvenswapperBase;
import me.planetguy.gizmos.content.inventory.BlockInventoryMultiplexer;
import me.planetguy.gizmos.content.inventory.BlockTelekinesisCatalyst;
import me.planetguy.gizmos.content.inventory.ItemLuncher;
import me.planetguy.gizmos.content.pvpparts.BlockMobAutoKiller;
import me.planetguy.gizmos.content.pvpparts.BlockSpawner;
import me.planetguy.lib.PLHelper;
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
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = Properties.modID, guiFactory="me.planetguy.gizmos.ConfigGUI", version=Properties.version, dependencies="required-after:planetguyLib@[1.9,)")
public class Gizmos {

	@Instance(Properties.modID)
	public static Gizmos instance;
	
	@SidedProxy(clientSide=Properties.clientProxy, serverSide=Properties.commonProxy )
	public static CommonProxy proxy;
	
	public static PLHelper helper;
	
	public static HashMap<String, IPrefabItem> content=new HashMap<String, IPrefabItem>();
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent pie){
		
		helper=new PLHelper(Properties.modID);
		
		Properties.configFile=new Configuration(pie.getSuggestedConfigurationFile());
		Properties.update();
		
		ForgeEventHandler.init();
		FMLEventHandler.init();
		
		Prefabs.initialize(Properties.modID);
		
		helper.load(ItemLuncher.class, content);
		
		helper.load(BlockAccelerator.class, content);
		
		helper.load(BlockLauncher.class, content);
		
		helper.load(BlockDynamicWool.class, content);
		
		helper.load(BlockTimeBomb.class, content);
		
		helper.load(ItemRedstoneActivator.class, content);
		
		helper.load(ItemFireExtinguisher.class, content);
		
		helper.load(BlockGravityBomb.class, content);
		
		helper.load(BlockInserter.class, content);
		
		helper.load(BlockTelekinesisCatalyst.class, content);
		helper.load(BlockInvenswapperBase.class, content);
		
		helper.load(ItemBuildTool.class, content);
		helper.load(ItemTemporalDislocator.class, content);
		helper.load(ItemBombDefuser.class, content);
		
		helper.load(BlockLogger.class, content);
		
		helper.load(ItemDebugWandFieldwise.class, content);
		helper.load(ItemDebugWandNBT.class, content);
		helper.load(ItemDebugWandReloading.class, content);

		helper.load(ItemFlashlightGlowstone.class, content);
		helper.load(ItemFlashlightRF.class, content);
		helper.load(ItemFlashlightNetherStar.class, content);
		
		helper.load(ItemHeadlampRF.class, content);
		helper.load(ItemHeadlampGlowstone.class, content);
		helper.load(ItemHeadlampNetherStar.class, content);
		
		helper.load(ItemStickOfWhacking.class, content);
		helper.load(ItemCreativeEnderPearl.class, content);
		
		helper.load(BlockHologramProjector.class, content);
		helper.load(BlockInventoryMultiplexer.class, content);
		
		helper.load(BlockSpawner.class, content);
		helper.load(BlockMobAutoKiller.class, content);
		
		helper.load(BlockPersistentGlowingAir.class, content);
		helper.load(BlockSmoker.class, content);
		helper.load(BlockInvisibleWall.class, content);
		helper.load(ItemRevealer.class, content);
		
		helper.load(ItemSprayPaintCan.class, content);
		
		EntityRegistry.registerModEntity(EntityGravityBomb.class, "GBomb", 201, this, 80, 3, true);
		EntityRegistry.registerModEntity(EntityTunnelBomb.class,  "TBomb", 202, this, 80, 3, true);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent ie){
		proxy.loadRendering();
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
		CreativeTabs tab=new CreativeTabPrefab(Properties.modID+":gizmosTab", new ItemStack((Block) content.get("gravityBomb"), 1, 1));
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
	
}
