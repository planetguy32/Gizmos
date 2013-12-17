package planetguy.portalmod;

import java.util.Arrays;

import com.google.common.eventbus.EventBus;

import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.ModMetadata;

public class NoCrosslinkModContainer extends DummyModContainer{

	public NoCrosslinkModContainer(){
            super(new ModMetadata());
            ModMetadata md = super.getMetadata();
            md.modId = "planetguy_NoCrosslink";
            md.name = "NoCrosslink";
            md.version = "1.0";
            md.authorList = Arrays.asList(new String[]{"planetguy"});
            md.url = "https://github.com/planetguy32/Gizmos";
            md.description = "Scales portal range finding in the Nether, to fix portals cross-linking.";
    }
	
	@Override
	public boolean registerBus(EventBus var1, LoadController var2){
		var1.register(this);
		return true;
	}
}
