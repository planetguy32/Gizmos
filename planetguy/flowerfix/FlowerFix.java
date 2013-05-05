package planetguy.flowerfix;

import java.util.logging.Level;

import planetguy.Gizmos.api.GizAPI;

import net.minecraft.block.Block;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

/**
 * Allows increasing vanilla flower spawn rates.
 * @author planetguy
 *
 */


@Mod(modid="planetguy_flowerfix", name="FlowerFix", version="1.0")
public class FlowerFix {
	
	private static int redFlowerWeight,yellowFlowerWeight;

	@Instance("planetguy_flowerfix")
	public static FlowerFix instance;
	
	@Mod.PreInit
	public static void loadConfig(FMLPreInitializationEvent event) throws Exception{
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		try{
			redFlowerWeight=config.get("Weights", "Red flower weight", 10).getInt(10);
			yellowFlowerWeight=config.get("Weights", "Yellow flower weight", 20).getInt(20);
		}catch (Exception e){
			FMLLog.log(Level.SEVERE,e,"BAD FLOWERFIX CONFIG IS BAD! Try deleting it.");
			throw e;
		}
		config.save();
		
	}
	
	@Mod.Init
	public final void load(FMLInitializationEvent e){
		if(yellowFlowerWeight>20){
			MinecraftForge.addGrassPlant(Block.plantYellow, 0, yellowFlowerWeight-20);
		}
		if(redFlowerWeight>10){
			MinecraftForge.addGrassPlant(Block.plantRed, 0, redFlowerWeight-10);
		}
		
	}
	
}
