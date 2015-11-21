package me.planetguy.gizmos;

import me.planetguy.gizmos.content.flashlight.ItemFlashlightBase;
import net.minecraftforge.common.config.Configuration;

public class Properties {
	
	/*
	 * Compile-time properties...
	 */
	public static final String modID= "planetguy_Gizmos";
	
	public static final String version="${version}";
	
	/*
	 * Class-name properties, for annotations that require strings.
	 */
	
	public static final String clientProxy="me.planetguy.gizmos.ClientProxy";

	public static final String commonProxy="me.planetguy.gizmos.CommonProxy";
	
	/*
	 * Run-time properties
	 */
	
	public static Configuration configFile;

	public static boolean enableMinecartTweaks=true;
	
	public static boolean enableSpecialPortals=true;
	
	public static int maxPortalSize=100;

	public static int timeBombFuse=400;

	public static int telekinesisRange=10;

	public static String[] whitelist;

	public static boolean noStackingHiddenItems;

	public static int flashlightRechargePerGlowstone=25;

	public static int rfPerFlashlightRecharge=1000;
	
	public static boolean enableSimpleSetSpawn=true;
	
	//TODO configurable
	public static int[] ESP_RF_config = {50000, 5, 49000};
	
	public static void update() {
		enableMinecartTweaks=configFile.getBoolean("enablePullingOnFurnaceCart", Configuration.CATEGORY_GENERAL, enableMinecartTweaks, "Should sneaking while right-clicking a minecart start it moving towards you?");
		enableSpecialPortals=configFile.getBoolean("enableSpecialPortals", Configuration.CATEGORY_GENERAL, enableSpecialPortals, "Should free-form portals be allowed? (Note: Slightly buggy)");
		maxPortalSize=configFile.getInt("maxPortalSize", Configuration.CATEGORY_GENERAL, maxPortalSize, 0, Integer.MAX_VALUE, "Maximum size of freeform portals");
		enableSimpleSetSpawn=configFile.getBoolean("enableSimpleSetSpawn", Configuration.CATEGORY_GENERAL, enableSimpleSetSpawn, "Let players set their spawn point at a bed without sleeping?");
		flashlightRechargePerGlowstone=configFile.getInt("flashlightRechargePerGlowstone", Configuration.CATEGORY_GENERAL, flashlightRechargePerGlowstone, 0, ItemFlashlightBase.maxDamage, "How much use should glowstone repair on a flashlight?");
		ESP_RF_config=configFile.get(Configuration.CATEGORY_GENERAL, "electricSpawnPointRFCharacteristics",ESP_RF_config, "Array of 3 integers: first is max capacity, second is RF/t loss, third is RF required to respawn player").getIntList();
		if(configFile.hasChanged()){
			configFile.save();
		}
	}

}
