package planetguy.lapis.implement.forge.loader;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import net.minecraftforge.common.Configuration;
import planetguy.gizmos.Gizmos;
import planetguy.gizmos.SLGeneratedLoader;
import planetguy.simpleLoader.SimpleLoader;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class LoaderModContainer extends DummyModContainer{
	
	Map<String, Integer> classToIdMap=new HashMap<String, Integer>();
	
	public LoaderModContainer(){
        super(new ModMetadata());
        ModMetadata md = super.getMetadata();
        md.modId = "planetguy_LAPIS";
        md.name = "LAPIS";
        md.version = "0.1";
        md.authorList = Arrays.asList(new String[]{"planetguy"});
        md.url = "https://github.com/planetguy32/Gizmos";
        md.description = "The LAPIS API for Portable Ingame Stuff. An attempt to bring together the stable interface of Bukkit and the power of Forge.";
	}
	
	@Override
	public boolean registerBus(EventBus var1, LoadController var2){
		FMLLog.log("LAPIS", Level.SEVERE, "LAPIS");
		var1.register(this);
		return true;
	}
	
	//Callback from Forge
	@Subscribe
	public static void loadConfig(FMLPreInitializationEvent event) throws Exception{
		FMLLog.log("LAPIS", Level.SEVERE, "LAPIS at configs");
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		for(String s:ContentDiscoverer.classNames.keySet()){
			for(String classFqcn:ContentDiscoverer.classNames.get(s)){
				Class lapisClass=Class.forName(classFqcn);
				FMLLog.log("LAPIS", Level.SEVERE, "Discovered class "+lapisClass+" with fqcn "+classFqcn+" in package "+s);
			}
		}
		config.save();
	}
	
	@Subscribe
	public void load(FMLInitializationEvent ignored) throws Exception{
		
	}

}
