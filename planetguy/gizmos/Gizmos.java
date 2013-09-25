package planetguy.gizmos;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.logging.Level;

import com.google.common.collect.ImmutableList;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import planetguy.gizmos.CES.BlockCESBomb;
import planetguy.gizmos.CES.powerups.Powerup;
import planetguy.gizmos.CES.powerups.PowerupDebug;
import planetguy.gizmos.CES.powerups.PowerupExplodeOnImpact;
import planetguy.gizmos.CES.powerups.PowerupFall;
import planetguy.gizmos.gravitybomb.EntityTunnelBomb;
import planetguy.simpleLoader.CustomModuleLoader;
import planetguy.simpleLoader.SLItemBlock;
import planetguy.simpleLoader.SimpleLoader;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

/**
 * 
 * @author planetguy
 *The Gizmos main class. Contains references to some blocks and items.
 *
 */
@Mod(modid="planetguy_Gizmos", name="Gizmos", version="2.0")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
public class Gizmos {
	
	//Holds instances for all items/blocks/etc. Deprecated with SimpleLoader?
	public static Block GravityBomb;
	public static Entity graviBombPrimed;
	public static EntityTunnelBomb tunnelBombPrimed;
	
	public static Block superFire;
	public static Block forestFire;
	public static Item deforestator;
	public static Item minersLighter;
	
	public static Block inserter;
	public static Item Lens;
	
	public static Block telekinesisCatalyst;
	
	public static Block invenswapper,invenswapperTop;
	
	public static Block accelerator;
	public static Block launcher;
	
	public static Block CESBomb;
	
	public static Block timeBombs;	
	public static Item bombDefuser;
	public static Item buildTool;
	
	public static CustomModuleLoader flowerFix;
	public static CustomModuleLoader railRecipeReverter;
	public static CustomModuleLoader anyShapePortals;
	
	public static Item fireExtinguisher;
	public static Item lastLaugh;
	public static Item rCanvas;
	public static Item rCanvasBag;
	public static Item lastLaughChestplate;
	public static Item temporalDislocator;
	
	public static CreativeTabs tabGizmos;
	
	public static final String modName="planetguy_gizmos";
	
	static{
		FMLLog.makeLog("Gizmos");
	}
	
	@Instance("planetguy_Gizmos")
	public static Gizmos instance;
	
	public static SimpleLoader loader;
	
	public static float accelRate;
	
	private ImmutableList<String> creativeTabBlacklistedThings=ImmutableList.of("superFire","forestFire","invenswapperTop");
	
	//Callback from Forge
	@PreInit
	public static void loadConfig(FMLPreInitializationEvent event) throws Exception{
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		loader=new SimpleLoader("Gizmos",Gizmos.instance,config);

		
		config.save();
	}
	
	
	@Init
	public final void load(FMLInitializationEvent ignored) throws Exception{
		
        NetworkRegistry.instance().registerGuiHandler(this, new GuiHandler());
        loader.loadClasses();
    	if(GravityBomb==null)GravityBomb=Block.blocksList[46];
        tabGizmos=new CreativeTabs("tabGizmos"){
        	private ItemStack gb=new ItemStack(GravityBomb,1,1);
        	public ItemStack getIconItemStack(){
        		return gb;
        	}
        	
            public String getTranslatedTabLabel(){
            	return "Gizmos stuff";
            }

        };
       
        //debug: after SL initializes everything, print out what is and isn't null. Makes sure references get to this class.
        Class c=this.getClass();
        for(Field f:c.getDeclaredFields()){
        	boolean isBlock=f.getType().equals(Block.class);
        	boolean isItem=f.getType().equals(Item.class);
        	if(isBlock||isItem){
        		try{
        			if(creativeTabBlacklistedThings.contains(f.getName()))throw new Exception();
        			if(isItem){
        				Item i=(Item) f.get(this);
        				i.setCreativeTab(tabGizmos);
        			}else if(isBlock){
        				Block b=(Block) f.get(this);
        				b.setCreativeTab(tabGizmos);
        			}
        			
        		}catch(Exception e){}
        	}
        	dbg(">>Field:"+f.getName()+", value:"+f.get(this));
        }
	}
	
	public static void dbg(String text){ //less-wordy way to print a message to console
		if(text==null)text="<null>";
		FMLLog.log("Gizmos",Level.INFO, text);
	}
	
	@PostInit
	public final void postInit(FMLPostInitializationEvent e){
		SLItemBlock.loadLanguages();
	}

}
