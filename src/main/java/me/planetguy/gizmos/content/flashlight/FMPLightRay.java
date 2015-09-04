package me.planetguy.gizmos.content.flashlight;

import me.planetguy.gizmos.Properties;
import codechicken.multipart.MultiPartRegistry;
import codechicken.multipart.MultiPartRegistry.IPartFactory;
import codechicken.multipart.TMultiPart;

public class FMPLightRay extends TMultiPart implements IPartFactory {
	
	static {
		MultiPartRegistry.registerParts(new FMPLightRay(), new String[] {Properties.modID + "_LightRay"});
	}

	short metadata=BlockLightRay.LIFESPAN * BlockLightRay.TICK_RATE;
	
	@Override
	public String getType() {
		return Properties.modID + "_LightRay";
	}
	
	public int getLightValue() {
		return (int) BlockLightRay.LIGHT_VALUE * 16;
	}
	
	public void update() {
		metadata--;
		if(metadata==0) {
			this.tile().remPart(this);
		}
	}

	@Override
	public TMultiPart createPart(String arg0, boolean arg1) {
		return new FMPLightRay();
	}
	
}
