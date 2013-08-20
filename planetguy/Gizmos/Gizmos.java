package planetguy.Gizmos;

import java.util.ArrayList;
import java.util.logging.Level;

import com.google.common.collect.ImmutableList;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import planetguy.Gizmos.gravitybomb.EntityTunnelBomb;
import planetguy.Gizmos.loader.LoaderNode;
import planetguy.Gizmos.loader.LoaderNodeBombItems;
import planetguy.Gizmos.loader.LoaderNodeBuildTool;
import planetguy.Gizmos.loader.LoaderNodeDefuser;
import planetguy.Gizmos.loader.LoaderNodeDislocator;
import planetguy.Gizmos.loader.LoaderNodeFire;
import planetguy.Gizmos.loader.LoaderNodeGravityBombs;
import planetguy.Gizmos.loader.LoaderNodeInserter;
import planetguy.Gizmos.loader.LoaderNodeInvenswappers;
import planetguy.Gizmos.loader.LoaderNodeLens;
import planetguy.Gizmos.loader.LoaderNodeTimeBombs;
import planetguy.Gizmos.loader.LoaderNodeVelocityManipulators;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

/**
 * 
 * @author planetguy
 *The Gizmos main class and API (sort of). Contains references to all blocks, items and IDs.
 *
 */
@Mod(modid="planetguy_Gizmos", name="Gizmos", version="1.1")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
public class Gizmos {
	
	public static Block graviBomb;
	public static Entity graviBombPrimed;
	public static EntityTunnelBomb tunnelBombPrimed;
	
	public static Item dislocator;

	public static Block geoFire,forestFire;
	public static Item deforestator;
	public static Item mlighter;
	
	public static Block spyDesk;
	public static Item spyLens;
	
	public static ItemStack IStimeBomb;

	public static Block invenswapperBase,invenswapperTop;
	
	public static Block particleAccelerator;
	public static Block colliderCore;
	public static Block launcher;
	
	public static Block timeBomb;
	public static Item defuser;
	public static Item buildTool;
	
	public static Item fireExtinguisher;
	
	public static int gravityExplosivesID;
	public static int geoFireID;
	public static int netherLighterID;
	public static int minerLighterID;
	public static int WandID;
	public static int spyLabID;
	public static int lensID;
	public static final String modName="planetguy_Gizmos";
	public static boolean serverSafeMode;
	public static boolean nerfHiding=false;
	public static int accelID;
	public static float accelRate;
	public static int colliderID;
	public static int launcherID;
	public static double launcherPower;
	public static int timeExplosivesID;
	public static int timeExplosivesFuse;
	public static boolean allowFB;
	public static int[] defuseableIDs;
	public static int defuserID;
	public static int buildToolID;
	public static int forestFireID;
	public static int invenswapperTopID;
	public static int invenswapperBottomID;
	public static int fireExtID;
	public static int baseBombID;
	
	public static ImmutableList<String> bannedItems;
	
	static{
		FMLLog.makeLog("Gizmos");
	}
	
	@Instance("planetguy_Gizmos")
	public static Gizmos instance;
	
	@PreInit
	public static void loadConfig(FMLPreInitializationEvent event) throws Exception{
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		try{

			Gizmos.gravityExplosivesID = config.getBlock("Explosives ID", 3981).getInt();
			Gizmos.geoFireID = config.getBlock("Superfire ID", 3982).getInt();
			Gizmos.spyLabID = config.getBlock("Spy lab ID", 3983).getInt();
			Gizmos.accelID = config.getBlock("Accelerator ID", 3984).getInt();
			Gizmos.forestFireID = config.getBlock("Forest fire ID", 3985).getInt();
			Gizmos.launcherID = config.getBlock("Launcher ID", 3986).getInt();
			Gizmos.timeExplosivesID = config.getBlock("Time bomb ID", 3987).getInt();
			Gizmos.invenswapperTopID = config.getBlock("Invenswapper ID", 3988).getInt();
			Gizmos.invenswapperBottomID = config.getBlock("Invenswapper base ID", 3989).getInt();
			Gizmos.baseBombID = config.getBlock("Advanced bomb system ID", 3990).getInt();

			
			
			Gizmos.netherLighterID = config.getItem("Deforestator ID", 8100).getInt();
			Gizmos.minerLighterID = config.getItem("Mineral igniter ID", 8101).getInt();
			Gizmos.WandID = config.getItem("Temporal Dislocator ID", 8102).getInt();
			Gizmos.lensID = config.getItem("Spy lens ID", 8103).getInt();
			Gizmos.defuserID=config.getItem("Defuser ID", 8104).getInt();
			Gizmos.buildToolID=config.getItem("Build tool ID", 8105).getInt();
			Gizmos.fireExtID=config.getItem("Fire extinguisher ID", 8106).getInt();
			
			boolean isObfuscated=config.get("Developer", "Use deobfuscated mode", false).getBoolean(false);
			ReflectionHelper.initialize(isObfuscated);
			
			Gizmos.allowFB=config.get("Nerfs and bans", "Allow fork bombs to fork", true).getBoolean(true);
			Gizmos.accelRate = (float) config.get("Nerfs and bans", "Accelerator rate", 1.16158634964).getDouble(1.16158634964);
			Gizmos.serverSafeMode = config.get("Nerfs and bans", "Safe server mode",false).getBoolean(false);
			Gizmos.nerfHiding = config.get("Nerfs and bans", "Limit stack size to hide",false).getBoolean(false);
			Gizmos.launcherPower=config.get("Nerfs and bans", "Mob launcher power", 10D).getDouble(10D);
			int[] dangerous={46,Gizmos.gravityExplosivesID,Gizmos.timeExplosivesID};
			Gizmos.defuseableIDs=config.get("Nerfs and bans", "IDs of defuseable", dangerous).getIntList();
			Gizmos.timeExplosivesFuse=config.get("Nerfs and bans", "Time bomb fuse, seconds", 60).getInt(60);
			String[] bannedNodes={};
			bannedNodes=config.get("Nerfs and bans", "Ye great banned module list", bannedNodes).getStringList();
			bannedItems=ImmutableList.copyOf(bannedNodes);

			//ConfigHolder.modName=config.get("Nerfs and bans", "Mod zip file name", "Gizmos_v0.4").getString();
		}catch (Exception e){
			dbg("BAD GIZMOS CONFIG IS BAD! Try deleting it.");
			throw e;
		}
		config.save();
	}
	
	@Init
	public final void load(FMLInitializationEvent ignored) throws Exception{
		

		
        NetworkRegistry.instance().registerGuiHandler(this, new GuiHandler());

		for(Object ln : LoaderNode.registeredNodes.toArray()){
			dbg(((LoaderNode) ln).getName());
		}
		for(String s: bannedItems){
			for(int i=0; i<LoaderNode.registeredNodes.size(); i++){
				//dbg("Banning module: "+s+", checking "+LoaderNode.registeredNodes.get(i));
				//dbg("Currently at "+LoaderNode.registeredNodes.get(i).getName());
				if(s.equalsIgnoreCase(LoaderNode.registeredNodes.get(i).getName())){
					dbg("Found it!");
					LoaderNode.registeredNodes.remove(i);
					break;
				}
			}
		}
		dbg(LoaderNode.registeredNodes.toString());
		for(Object ln : LoaderNode.registeredNodes.toArray()){
			((LoaderNode) ln).loadRecursively();
		}	
	}
	
	public static void dbg(String text){
		FMLLog.getLogger().log(Level.INFO, text);
	}

}
