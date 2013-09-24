package planetguy.gizmos.tool;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import planetguy.gizmos.Gizmos;
import planetguy.simpleLoader.SLLoad;
import net.minecraft.client.renderer.texture.IconRegister;
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
		LanguageRegistry.instance().addNameForObject(this, "en_US", "Canvas roll");
		GameRegistry.addShapedRecipe(new ItemStack(this), new Object[] { 
			"sss",
			"sts",
			"sss",
			Character.valueOf('s'),new ItemStack(Item.silk),
			Character.valueOf('t'),new ItemStack(Item.stick)
			});
	}
	
	@Override
	public void registerIcons(IconRegister ir){
		this.itemIcon=ir.registerIcon(Gizmos.modName+":clothRoll");
	}

}
