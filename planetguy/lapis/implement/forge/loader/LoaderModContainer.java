package planetguy.lapis.implement.forge.loader;

import java.util.Arrays;

import com.google.common.eventbus.EventBus;

import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.ModMetadata;

public class LoaderModContainer extends DummyModContainer{
	
	public LoaderModContainer(){
        super(new ModMetadata());
        ModMetadata md = super.getMetadata();
        md.modId = "planetguy_LapisClassDiscoverer";
        md.name = "LAPIS discoverer";
        md.version = "1.0";
        md.authorList = Arrays.asList(new String[]{"planetguy"});
        md.url = "https://github.com/planetguy32/Gizmos";
        md.description = "Finds LAPIS content and loads it.";
	}
	
	@Override
	public boolean registerBus(EventBus var1, LoadController var2){
		var1.register(this);
		return true;
	}

}
