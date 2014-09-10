package me.planetguy.gizmos.base;

import java.util.HashMap;
import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import me.planetguy.gizmos.Gizmos;
import me.planetguy.gizmos.Properties;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class BlockBase extends Block implements IGizmosBlock{

	public static IGizmosItem load(Class<? extends BlockBase> clazz, HashMap<String, IGizmosItem> map){
		BlockBase block;
		try {
			block = clazz.newInstance();
			GameRegistry.registerBlock(block, ItemBlockBase.class, block.getName());
			map.put(block.getName(), block);
			return block;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private final String name;

	protected BlockBase(Material p_i45394_1_, String name) {
		super(p_i45394_1_);
		this.name=name;
		this.setBlockName(name);
	}
	
	public void registerBlockIcons(IIconRegister ir){
		this.blockIcon=ir.registerIcon(Properties.modID+":"+getName());
	}

	public int countTooltipLines() {
		return 2;
	}
	
	public void loadCrafting() {}

	public String getName() {
		return name;
	}

}
