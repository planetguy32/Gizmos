package planetguy.Gizmos.loader;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import planetguy.Gizmos.Gizmos;
import planetguy.Gizmos.spy.ItemLens;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class LoaderNodeLens extends LoaderNode {

	public static LoaderNode inst=new LoaderNodeLens();
	
	public static final LoaderNode[] depends={LoaderNode.vanilla};
	
	public LoaderNodeLens() {
		super(depends);
	}

	@Override
	public void loadLocal() {
		ItemStack glass=new ItemStack(Block.glass);
		ItemStack iron = new ItemStack(Item.ingotIron);
		Gizmos.spyLens=new ItemLens(Gizmos.lensID).setCreativeTab(CreativeTabs.tabMaterials);
        LanguageRegistry.instance().addName(Gizmos.spyLens, "Lens");
		ItemStack lens=new ItemStack(Gizmos.spyLens);
		GameRegistry.addRecipe(lens, new Object[] { " i ", "igi", " i ", 
				Character.valueOf('g'), glass,
				Character.valueOf('i'), iron });
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "lens";
	}

}
