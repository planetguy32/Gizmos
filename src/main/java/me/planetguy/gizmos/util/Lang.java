package me.planetguy.gizmos.util;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import me.planetguy.gizmos.Properties;
import me.planetguy.gizmos.base.IGizmosItem;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Lang {
	
	public static String translate(String s){
		return LanguageRegistry.instance().getStringLocalization(s);
	}
	
}
