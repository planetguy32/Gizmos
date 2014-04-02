package planetguy.lapis.implement.forge.loader;

import java.util.Map;

import net.minecraft.world.WorldServer;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

public class LoaderCoremod implements IFMLLoadingPlugin {

	public static boolean runtimeDeobfEnabled = true;
	
	@Override
	@Deprecated
	public String[] getLibraryRequestClass() {
		return null;
	}

	@Override
	public String[] getASMTransformerClass() {
		return new String[]{
				"planetguy.lapis.implement.forge.loader.ContentDiscoverer",
				"planetguy.asmfixes.TransformerNoCrosslink"
		};
	}

	@Override
	public String getModContainerClass() {
		return "planetguy.lapis.implement.forge.loader.LoaderModContainer";
	}

	@Override
	public String getSetupClass() {
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data) {
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

}
