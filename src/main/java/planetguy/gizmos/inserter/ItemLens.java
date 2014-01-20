package planetguy.gizmos.inserter;

import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import planetguy.gizmos.Gizmos;
import planetguy.gizmos.GizmosItem;
import planetguy.simpleLoader.SLLoad;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.biome.BiomeGenBase;

/**
 * Item used by other items as a crafting ingredient.
 * @author planetguy
 *
 */
@SLLoad(name="Lens",primacy=1)
public class ItemLens extends GizmosItem{
	
	/**Initializes item and adds recipe
	 * 
	 * @param id item ID to use
	 */
	@SLLoad
	public ItemLens(int id) {
		super(id);
		Gizmos.Lens=this;
		this.func_149663_c("spyLens");
		ItemStack glass=new ItemStack((Block)Block.field_149771_c.getObject("glass);
		ItemStack iron = new ItemStack(Item.ingotIron);
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
