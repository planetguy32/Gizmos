package me.planetguy.gizmos;

import java.util.Set;

import cpw.mods.fml.client.IModGuiFactory;
import cpw.mods.fml.client.IModGuiFactory.RuntimeOptionCategoryElement;
import cpw.mods.fml.client.IModGuiFactory.RuntimeOptionGuiHandler;
import cpw.mods.fml.client.config.GuiConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;

public class ConfigGUI implements IModGuiFactory{
	
	public static class GGuiConfig extends GuiConfig {
	    public GGuiConfig(GuiScreen parent) {
	        super(parent,
	                new ConfigElement(Properties.configFile.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(),
	                "Gizmos", false, false, GuiConfig.getAbridgedConfigPath(Properties.configFile.toString()));
	    }
	}
	
		
	@Override
	public void initialize(Minecraft minecraftInstance) {

	}

	@Override
	public Class<? extends GuiScreen> mainConfigGuiClass() {
		return GGuiConfig.class;
	}

	@Override
	public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
		return null;
	}

	@Override
	public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element) {
		return null;
	}

}
