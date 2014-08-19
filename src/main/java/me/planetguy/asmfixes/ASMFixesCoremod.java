package me.planetguy.asmfixes;

import java.util.Map;

import net.minecraft.world.WorldServer;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin.MCVersion;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

@MCVersion("1.6.4")
public class ASMFixesCoremod implements IFMLLoadingPlugin {

	public static boolean runtimeDeobfEnabled = true;
	
	@Override
	public String[] getASMTransformerClass() {
		return new String[]{"planetguy.asmfixes.TransformerNoCrosslink"};
	}

	@Override
	public String getModContainerClass() {
		return "planetguy.asmfixes.ASMFixesModContainer";
	}

	@Override
	public String getSetupClass() {
		return null;
	}

    @Override
    public void injectData(Map<String, Object> data)
    {
            runtimeDeobfEnabled = (Boolean)data.get("runtimeDeobfuscationEnabled");
    }
    
    /**
     * Small helper for injected ASM code.
     * @param world
     * @return
     */
	public static short getMaximumRange(WorldServer world){
		return (short) (world.provider.dimensionId!=-1 ? 128 : 16);
	}

	@Override
	public String getAccessTransformerClass() {
		return null;
	}


}
