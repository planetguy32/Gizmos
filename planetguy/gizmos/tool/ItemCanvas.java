package planetguy.gizmos.tool;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import planetguy.simpleLoader.SLLoad;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

@SLLoad(name="rCanvas")
public class ItemCanvas extends Item{

	@SLLoad
	public ItemCanvas(int id) {
		super(id);
		this.setUnlocalizedName("canvasRoll");
		this.setCreativeTab(CreativeTabs.tabMaterials);
		LanguageRegistry.instance().addName(this, "Canvas sheet");
		GameRegistry.addShapedRecipe(new ItemStack(this), new Object[] { 
			"sss",
			"sts",
			"sss",
			Character.valueOf('s'),new ItemStack(Item.silk),
			Character.valueOf('t'),new ItemStack(Item.stick)
			});
	}

}
