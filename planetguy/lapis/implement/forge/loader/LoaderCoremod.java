package planetguy.lapis.implement.forge.loader;

import java.util.Map;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

public class LoaderCoremod implements IFMLLoadingPlugin {

	@Override
	@Deprecated
	public String[] getLibraryRequestClass() {
		return null;
	}

	@Override
	public String[] getASMTransformerClass() {
		return new String[]{
				"planetguy.lapis.implement.forge.loader.Loader"
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
		
	}

}
