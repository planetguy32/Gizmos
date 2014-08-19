package me.planetguy.gizmos;

import java.lang.reflect.Field;

import me.planetguy.core.sl.CustomModuleLoader;
import me.planetguy.core.sl.SLItemBlock;
import me.planetguy.core.sl.SimpleLoader;
import me.planetguy.gizmos.gravitybomb.EntityTunnelBomb;
import me.planetguy.util.Debug;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;

import com.google.common.collect.ImmutableList;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

/**
 * 
 * @author planetguy
 *The Gizmos main class. Contains references to some blocks and items.
 *
 */
@Mod(modid="planetguy_Gizmos", name="Gizmos", version="3.0")
public class Gizmos {
	
	//Holds instances for all items/blocks/etc. Required by SimpleLoader.
	public static Block GravityBomb;
	public static Entity graviBombPrimed;
	public static EntityTunnelBomb tunnelBombPrimed;
	
	public static Block superFire;
	public static Block forestFire;
	public static Item deforestator;
	public static Item minersLighter;
	
	public static Block composter;
	
	public static Block RedstoneResponsiveWool;
	
	public static Block inserter;
	public static Item Lens;
	
	public static Block telekinesisCatalyst;
	
	public static Block invenswapper,invenswapperTop;
	
	public static Block accelerator;
	public static Block launcher;
	
	public static Block CESBomb;
	
	public static Item redstoneWand;
	public static Block redstoneWandBlock;
	
	public static Block timeBombs;	
	public static Item bombDefuser;
	public static Item buildTool;
	
	public static CustomModuleLoader flowerFix;
	public static CustomModuleLoader anyShapePortals;
	
	public static CustomModuleLoader eventHandler;
	
	public static Item fireExtinguisher;
	public static Item lastLaugh;
	public static Item lastLaughChestplate;
	public static Item temporalDislocator;
	
	public static Item arrowNova;
	public static Entity entityArrowNova;
	
	public static Item luncher;
	
	public static CreativeTabs tabGizmos;
	
	public static final String modName="planetguy_gizmos";
	
	@Instance("planetguy_Gizmos")
	public static Gizmos instance;
	
	public static SimpleLoader loader;
	
	public static float accelRate;
	
	private String[] ctabBlacklist=ImmutableList.of("superFire","forestFire","invenswapperTop","redstoneWandBlock","arrowNova").toArray(new String[0]);
	
	//Callback from Forge
	@EventHandler
	public static void loadConfig(FMLPreInitializationEvent event) throws Exception{
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		loader=new SimpleLoader(config);
		config.save();
	}
	
	@EventHandler
	public void load(FMLInitializationEvent ignored) throws Exception{
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
        loader.finishLoading();
	}
	
	@EventHandler
	public final void postInit(FMLPostInitializationEvent e){
        loader.setupCreativeTab("tabGizmos",ctabBlacklist,
        		new ItemStack(GravityBomb,1,1),
        		new ItemStack(Blocks.tnt));
	}


}
