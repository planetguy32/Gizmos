package planetguy.Gizmos;
 import java.util.logging.Level;

import planetguy.Gizmos.*;
import planetguy.Gizmos.gravitybomb.BlockGraviBomb;
import planetguy.Gizmos.gravitybomb.EntityGravityBomb;
import planetguy.Gizmos.gravitybomb.EntityTunnelBomb;
import planetguy.Gizmos.gravitybomb.ItemGraviBombs;
import planetguy.Gizmos.invUtils.BlockInvenswapperBase;
import planetguy.Gizmos.invUtils.BlockInvenswapperTop;
import planetguy.Gizmos.invUtils.TileEntityInvenswapper;
import planetguy.Gizmos.mobcollider.BlockAccelerator;
import planetguy.Gizmos.mobcollider.BlockLauncher;
import planetguy.Gizmos.mobcollider.ColliderRecipe;
import planetguy.Gizmos.spy.BlockInserter;
import planetguy.Gizmos.spy.EventWatcherSpyItemUse;
import planetguy.Gizmos.spy.ItemLens;
import planetguy.Gizmos.timebomb.BlockTimeBomb;
import planetguy.Gizmos.timebomb.ItemBombDefuser;
import planetguy.Gizmos.timebomb.ItemTimeBomb;
import planetguy.Gizmos.tool.BlockForestFire;
import planetguy.Gizmos.tool.BlockSuperFire;
import planetguy.Gizmos.tool.ItemBuildTool;
import planetguy.Gizmos.tool.ItemBlockTicker;
import planetguy.Gizmos.tool.ItemDeforester;
import planetguy.Gizmos.tool.ItemMinersLighter;
import planetguy.Gizmos.unused.BlockColliderCore;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
 import cpw.mods.fml.common.registry.LanguageRegistry;
 import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
 import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityCow;
 import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.SidedProxy;
 
/*
@Mod(modid="planetguy_Gizmos", name="Gizmos", version="0.6")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
public class ContentLoader{
	
	//@SidedProxy(clientSide="planetguy.Gizmos.ClientProxy", serverSide="planetguy.Gizmos.CommonProxy")
	//public static CommonProxy proxy;
		   
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
	
	public static boolean allowGravityBombs, allowFire, allowDislocator,allowInvenswappers,
		allowBombItems, allowAccelerator,allowTimeBomb,allowForkBomb,allowBuildTool;
	

		
	@Instance("planetguy_Gizmos")
	public static ContentLoader instance;
	
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
			//ConfigHolder.colliderID = config.getBlock("Collider ID", 3985).getInt(); Probably will never be implemented
			Gizmos.launcherID = config.getBlock("Launcher ID", 3986).getInt();
			Gizmos.timeExplosivesID = config.getBlock("Time bomb ID", 3987).getInt();
			Gizmos.invenswapperTopID = config.getBlock("Invenswapper ID", 3988).getInt();
			Gizmos.invenswapperBottomID = config.getBlock("Invenswapper base ID", 3989).getInt();
			
			
			Gizmos.netherLighterID = config.getItem("Deforestator ID", 8100).getInt();
			Gizmos.minerLighterID = config.getItem("Mineral igniter ID", 8101).getInt();
			Gizmos.WandID = config.getItem("Temporal Dislocator ID", 8102).getInt();
			Gizmos.lensID = config.getItem("Spy lens ID", 8103).getInt();
			Gizmos.defuserID=config.getItem("Defuser ID", 8104).getInt();
			Gizmos.buildToolID=config.getItem("Build tool ID", 8105).getInt();
			
			allowGravityBombs=config.get("Nerfs and bans", "Allow gravity explosives", true).getBoolean(true);
			allowFire=config.get("Nerfs and bans", "Allow extra fire", true).getBoolean(true);
			allowBombItems=config.get("Nerfs and bans", "Allow spy desk module",true).getBoolean(true);
			allowDislocator=config.get("Nerfs and bans", "Temporal Dislocator allowed", true).getBoolean(true);
			allowAccelerator=config.get("Nerfs and bans", "Allow accelerator block", true).getBoolean(true);
			allowTimeBomb=config.get("Nerfs and bans", "Allow time bomb and fork bomb", true).getBoolean(true);
			allowInvenswappers=config.get("Nerfs and bans", "Allow invenswappers", true).getBoolean(true);


			Gizmos.allowFB=config.get("Nerfs and bans", "Allow fork bombs to fork", true).getBoolean(true);
			Gizmos.accelRate = (float) config.get("Nerfs and bans", "Accelerator rate", 1.16158634964).getDouble(1.16158634964);
			Gizmos.serverSafeMode = config.get("Nerfs and bans", "Safe server mode",false).getBoolean(false);
			Gizmos.nerfHiding = config.get("Nerfs and bans", "Limit stack size to hide",false).getBoolean(false);
			Gizmos.launcherPower=config.get("Nerfs and bans", "Mob launcher power", 10D).getDouble(10D);
			int[] dangerous={46,Gizmos.gravityExplosivesID,Gizmos.timeExplosivesID};
			Gizmos.defuseableIDs=config.get("Nerfs and bans", "IDs of defuseable", dangerous).getIntList();
			Gizmos.timeExplosivesFuse=config.get("Nerfs and bans", "Time bomb fuse, seconds", 60).getInt(60);


			//ConfigHolder.modName=config.get("Nerfs and bans", "Mod zip file name", "Gizmos_v0.4").getString();
		}catch (Exception e){
			FMLLog.log(Level.SEVERE,e,"BAD GIZMOS CONFIG IS BAD! Try deleting it.");
			throw e;
		}
		config.save();
	}

	@Init
	public final void load(FMLInitializationEvent ignored){
		load2(ignored);
	}
	
	public final void 
	
	public final void load2(FMLInitializationEvent ignored){
		//proxy.registerRenderers();
		
		
		//Get our Vanilla itemstacks ready for crafting
		ItemStack tnt = new ItemStack(Block.tnt);
		ItemStack powder = new ItemStack(Item.blazePowder);
		ItemStack iron = new ItemStack(Item.ingotIron);
		ItemStack itemStackPick = new ItemStack(Item.pickaxeIron);
		ItemStack itemStackFlintAndSteel= new ItemStack(Item.flintAndSteel);
		ItemStack redstone = new ItemStack(Item.redstone);
		ItemStack stackClock=new ItemStack(Item.pocketSundial);
		ItemStack sapling=new ItemStack(Block.sapling);
		ItemStack gravel=new ItemStack(Block.gravel);
		ItemStack glass=new ItemStack(Block.glass);
		ItemStack wood=new ItemStack(Block.planks);
		ItemStack blockIron=new ItemStack(Block.blocksList[42]);
		ItemStack crafter=new ItemStack(Block.workbench);
		ItemStack chest=new ItemStack(Block.chest);
		ItemStack endStone=new ItemStack(Block.whiteStone);
		ItemStack shears=new ItemStack(Item.shears);
		ItemStack stick=new ItemStack(Item.stick);

        NetworkRegistry.instance().registerGuiHandler(this, new GuiHandler());
		
		//First comes common crafting...
		if(allowBombItems||allowTimeBomb){ //Check
			spyLens=new ItemLens(Gizmos.lensID).setCreativeTab(CreativeTabs.tabMaterials);
			ItemStack lens=new ItemStack(spyLens);
	        LanguageRegistry.instance().addName(spyLens, "Spy lens");
			GameRegistry.addRecipe(lens, new Object[] { " i ", "igi", " i ", 
					Character.valueOf('g'), glass,
					Character.valueOf('i'), iron });
			
			//Might need lens for bomb items...
			if(allowBombItems){ //Check
				//this.bomb=new EnchantmentBomb(136);
				spyDesk=new BlockInserter(Gizmos.spyLabID,6).setUnlocalizedName("spyLab");
				buildTool=new ItemBuildTool(Gizmos.buildToolID).setUnlocalizedName("buildTool").setCreativeTab(CreativeTabs.tabTools);
				GameRegistry.registerBlock(spyDesk, ItemBlock.class, "spyLab");
				
		        LanguageRegistry.instance().addName(spyDesk, "Inserter");
		        LanguageRegistry.instance().addName(buildTool, "Builder's Tool");
				
				MinecraftForge.EVENT_BUS.register(new EventWatcherSpyItemUse());

				ItemStack itemSpyDesk=new ItemStack(spyDesk);
		        GameRegistry.addRecipe(itemSpyDesk, new Object[] {"LWC", "III","B B",
		        		Character.valueOf('L'),lens,
		        		Character.valueOf('W'),crafter,
		        		Character.valueOf('C'),chest,
		        		Character.valueOf('I'),blockIron,
		        		Character.valueOf('B'),wood});
		        
		        ItemStack itStkBuildTool=new ItemStack(buildTool);
		        ItemStack iSDPcx=new ItemStack(Item.pickaxeDiamond);
		        ItemStack iSPist=new ItemStack(Block.pistonBase);
		        
		        //IT LIVES!! No more duping, either!
		        GameRegistry.addRecipe(itStkBuildTool, new Object[]{"  c"," p ","d  ", 
		        		Character.valueOf('c'),chest,
		        		Character.valueOf('p'),iSPist,
		        		Character.valueOf('d'),iSDPcx});
		        
		        
			}
			
			//Or time bombs (for making the defuser)
			if(allowTimeBomb){ //Check
				timeBomb=new BlockTimeBomb(Gizmos.timeExplosivesID);
				GameRegistry.registerBlock(timeBomb,ItemTimeBomb.class,"timeBombs");
				Item.itemsList[ Gizmos.timeExplosivesID] = new ItemTimeBomb( Gizmos.timeExplosivesID-256).setItemName("timeBombs");
				defuser=new ItemBombDefuser(Gizmos.defuserID).setMaxDamage(10).setCreativeTab(CreativeTabs.tabTools).setUnlocalizedName("defuser");
				
				LanguageRegistry.addName(defuser, "Bomb defuser");
				final String[] oreNames = {"Time bomb", "Fork bomb", "?", "?", "?", "?", "?", "?", "?", "?", "?", "?", "?", "?", "Fork bomb", "Time bomb"};

				for (int re = 0; re < 16; re++){
					ItemStack oreStack = new ItemStack(timeBomb, 1, re);
					LanguageRegistry.addName(oreStack, oreNames[re]);
				}	
				//A fat lot of good THAT did... Still need a way to tell time bombs from fork bombs.
				
				//LET'S CRAFT!!!
				ItemStack itemStackTB=new ItemStack(timeBomb,1,0); 
				ItemStack itemStackFB=new ItemStack(timeBomb,1,1);
				ItemStack ISDefuser=new ItemStack(defuser);
				
				GameRegistry.addRecipe(ISDefuser, new Object[]{
						" sl",
						" ks",
						"k  ",
						Character.valueOf('s'),shears,
						Character.valueOf('k'),stick,
						Character.valueOf('l'),lens});
				
				GameRegistry.addShapelessRecipe(itemStackTB, Block.tnt, Item.pocketSundial);
				
				GameRegistry.addRecipe(itemStackFB, new Object[]{
						"EEE","ETE","EEE", 
						Character.valueOf('T'),itemStackTB,
						Character.valueOf('E'),endStone});
				

			}
			
		}
		
		if(allowInvenswappers){ //Checkeroo
			invenswapperTop=new BlockInvenswapperTop(Gizmos.invenswapperTopID);
			invenswapperBase=new BlockInvenswapperBase(Gizmos.invenswapperBottomID).setCreativeTab(CreativeTabs.tabDecorations);
			GameRegistry.registerTileEntity(TileEntityInvenswapper.class, "Gizmos.invenswapper");
			GameRegistry.registerBlock(invenswapperTop, ItemBlock.class,"invenswapperTop");
			GameRegistry.registerBlock(invenswapperBase, ItemBlockWithMetadata.class,"invenswapperBase");
			LanguageRegistry.addName(invenswapperBase, "Invenswapper base");


		}
		
		//Fire module
		if(allowFire&&!(Gizmos.serverSafeMode)){ //Check
			deforestator = new ItemDeforester(Gizmos.netherLighterID).setUnlocalizedName("netherLighter");
			mlighter = new ItemMinersLighter(Gizmos.minerLighterID).setUnlocalizedName("minersLighter");
			geoFire = new BlockSuperFire(Gizmos.geoFireID, 31).setUnlocalizedName("doomFire").setHardness(0.0F).setLightValue(1.0F);
			forestFire = new BlockForestFire(Gizmos.forestFireID, 31).setUnlocalizedName("woodFire").setHardness(0.0F).setLightValue(1.0F);

			GameRegistry.registerBlock(geoFire, ItemBlock.class, "doomFire");
			
			ItemStack itemStackNetherLighter = new ItemStack(deforestator,1,0);
			GameRegistry.addRecipe(itemStackNetherLighter, new Object[]{ "brb", "rfr", "brb",
					'b',powder,
					'r',sapling,
					'f',itemStackFlintAndSteel});
			ItemStack itemStackMinerLighter = new ItemStack(mlighter,1,0);
			GameRegistry.addRecipe(itemStackMinerLighter, new Object[]{ "brb", "rfr", "brb",
					'b',powder,
					'r',gravel,
					'f',itemStackFlintAndSteel});
			LanguageRegistry.addName(deforestator, "Deforestator");
			LanguageRegistry.addName(mlighter, "Miner's lighter");
		}
		
		//Temporal dislocator module
		if(allowDislocator){ //Check
			dislocator = new ItemBlockTicker(Gizmos.WandID).setUnlocalizedName("dislocator");
			ItemStack stackTicker=new ItemStack(dislocator,1,0); 
			GameRegistry.addRecipe(stackTicker, new Object[] {"ccc", "cic", "ccc",
					Character.valueOf('c'),stackClock,
					Character.valueOf('i'),iron});
			LanguageRegistry.addName(dislocator, "§5Temporal Dislocator");
		}
		
		//Explosives module
		if(allowGravityBombs&&!(Gizmos.serverSafeMode)){//check
			graviBomb = new BlockGraviBomb( Gizmos.gravityExplosivesID).setUnlocalizedName("graviBomb").setHardness(0.0F).setResistance(0.0F);
			Item.itemsList[ Gizmos.gravityExplosivesID] = new ItemGraviBombs( Gizmos.gravityExplosivesID-256).setItemName("graviBomb");
			graviBombPrimed = new EntityGravityBomb(null);
			tunnelBombPrimed=new EntityTunnelBomb(null);
			//EntityRegistry.registerModEntity(EntityTunnelBeam.class, "Tunnel Beam", 199, this, 80, 3, true);
			EntityRegistry.registerModEntity(EntityGravityBomb.class, "GBomb", 201, this, 80, 3, true);
			EntityRegistry.registerModEntity(EntityTunnelBomb.class, "TBomb", 202, this, 80, 3, true);
			ItemStack itemStackGB = new ItemStack(graviBomb, 3, 0);
			ItemStack itemStackExcaBomb = new ItemStack(graviBomb, 1, 1);
			GameRegistry.addRecipe(itemStackGB, new Object[] { "xxx", "iyi", " i ", 
					Character.valueOf('x'),tnt, 
					Character.valueOf('y'), powder,
					Character.valueOf('i'), iron });
			GameRegistry.addRecipe(itemStackExcaBomb, new Object[] { " b ", "ibi", "pbp", 
					Character.valueOf('b'), itemStackGB, 
					Character.valueOf('i'), iron, 
					Character.valueOf('p'), itemStackPick });
			final String[] oreNames = {"Gravity bomb", "Tunnel bomb", "?", "?", "?", "?", "?", "?", "?", "?", "?", "?", "?", "?", "Tunnel Bomb", "Gravity Bomb"};

			for (int re = 0; re < 16; re++){
				ItemStack oreStack = new ItemStack(graviBomb, 1, re);
				LanguageRegistry.addName(oreStack, oreNames[oreStack.getItemDamage()]);
			}
		}
		
		//Spy module

		
		if(allowAccelerator){
			
			particleAccelerator=new BlockAccelerator(Gizmos.accelID).setUnlocalizedName("accelerator").setCreativeTab(CreativeTabs.tabRedstone);
			launcher=new BlockLauncher(Gizmos.launcherID).setUnlocalizedName("entityLauncher");
			GameRegistry.registerBlock(launcher, ItemBlock.class, "launcher");

			
			//BlockColliderCore core=new BlockColliderCore(ConfigHolder.colliderID);
			//colliderCore=(Block) core.setUnlocalizedName("colliderCore");
			
			GameRegistry.registerBlock(particleAccelerator, ItemBlock.class, "accelerator");
			//GameRegistry.registerBlock(colliderCore, ItemBlock.class, "colliderCore");
			
			ItemStack[] stacks={new ItemStack(334,64,0)};
			//ColliderRecipe cowCowHighSpeed=new ColliderRecipe(stacks, 1.0D, EntityCow.class, EntityCow.class);
			//core.addColliderRecipe(cowCowHighSpeed);

			LanguageRegistry.instance().addName(launcher, "Launcher");
			LanguageRegistry.instance().addName(particleAccelerator, "Accelerator");
			//LanguageRegistry.instance().addName(colliderCore, "Collider core");
		}

		

	    //EntityRegistry.registerModEntity(EntityGravityBomb.class, "Lit Gravity Bomb", 222, planetguy.EvilToys.ContentLoader, 0, 0, false);
   }
	
	@PostInit
	public final void loadAfterEverything(FMLPostInitializationEvent foo){
		//System.out.println("PostInit called");
		//SpyReflector.doStuff();
	}
 }
 */
