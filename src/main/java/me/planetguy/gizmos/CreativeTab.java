package me.planetguy.gizmos;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

final class CreativeTab extends CreativeTabs {
	private final ItemStack gb;

	CreativeTab(String label, ItemStack gb) {
		super(label);
		this.gb = gb;
	}

	public ItemStack func_151244_d(){
		return gb;
	}

	public String getTranslatedTabLabel(){
		return "Gizmos";
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {
		return null;
	}
}