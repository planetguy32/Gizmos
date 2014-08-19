package me.planetguy.gizmos.inserter;

import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import me.planetguy.core.sl.SLLoad;
import me.planetguy.gizmos.Gizmos;
import me.planetguy.gizmos.GizmosItem;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.biome.BiomeGenBase;

/**
 * Item used by other items as a crafting ingredient.
 * @author planetguy
 *
 */
@SLLoad(name="Lens")
public class ItemLens extends GizmosItem{
	
	/**Initializes item and adds recipe
	 * 
	 * @param id item ID to use
	 */
	@SLLoad
	public ItemLens() {
		super();
		Gizmos.Lens=this;
		this.setUnlocalizedName("spyLens");
		ItemStack glass=new ItemStack(Blocks.glass);
		ItemStack iron = new ItemStack(Items.iron_ingot);
        LanguageRegistry.instance().addName(this, "Lens");
		ItemStack lens=new ItemStack(this);
		GameRegistry.addRecipe(lens, new Object[] { " i ", "igi", " i ", 
				Character.valueOf('g'), glass,
				Character.valueOf('i'), iron });
	}
	
	public void registerIcons(IIconRegister ir){
		itemIcon=ir.registerIcon(Gizmos.modName+":spyLens");
	}
	
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer player, List tooltipLines, boolean advancedTooltipsActive){
		tooltipLines.add("Crafting ingredient");
	}
}
