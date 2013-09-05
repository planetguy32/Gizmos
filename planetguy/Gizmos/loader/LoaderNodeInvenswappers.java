package planetguy.Gizmos.loader;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import planetguy.Gizmos.Gizmos;
import planetguy.Gizmos.invUtils.BlockInvenswapperBase;
import planetguy.Gizmos.invUtils.BlockInvenswapperTop;
import planetguy.Gizmos.invUtils.TileEntityInvenswapper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class LoaderNodeInvenswappers extends LoaderNode{

	public static LoaderNode inst=new LoaderNodeInvenswappers(new LoaderNode[0]);
	
	public LoaderNodeInvenswappers(LoaderNode[] depends) {
		super(depends);
	}

	@Override
	public void load() {
		Gizmos.invenswapperTop=new BlockInvenswapperTop(Gizmos.invenswapperTopID);
		Gizmos.invenswapperBase=new BlockInvenswapperBase(Gizmos.invenswapperBottomID).setCreativeTab(CreativeTabs.tabDecorations);
		GameRegistry.registerTileEntity(TileEntityInvenswapper.class, "Gizmos.invenswapper");
		GameRegistry.registerBlock(Gizmos.invenswapperTop, ItemBlock.class,"invenswapperTop");
		GameRegistry.registerBlock(Gizmos.invenswapperBase, ItemBlockWithMetadata.class,"invenswapperBase");
		LanguageRegistry.addName(Gizmos.invenswapperBase, "Invenswapper base");
		GameRegistry.addRecipe(new ItemStack(Gizmos.invenswapperBase,1,0), new Object[]{
			"php","lsl","lcl",
			Character.valueOf('h'),new ItemStack(Block.hopperBlock),
			Character.valueOf('p'),new ItemStack(Block.pressurePlateIron),
			Character.valueOf('l'),new ItemStack(Block.blockLapis),
			Character.valueOf('s'),new ItemStack(Block.pistonBase),
			Character.valueOf('c'),new ItemStack(Block.chest)
		});
		GameRegistry.addRecipe(new ItemStack(Gizmos.invenswapperBase,1,1), new Object[]{
			"pcp","lsl","lhl",
			Character.valueOf('h'),new ItemStack(Block.hopperBlock),
			Character.valueOf('p'),new ItemStack(Block.pressurePlateIron),
			Character.valueOf('l'),new ItemStack(Block.blockLapis),
			Character.valueOf('s'),new ItemStack(Block.pistonBase),
			Character.valueOf('c'),new ItemStack(Block.chest)
		});
		GameRegistry.addShapelessRecipe(new ItemStack(Gizmos.invenswapperBase,1,0), new ItemStack(Gizmos.invenswapperBase,1,1));
		GameRegistry.addShapelessRecipe(new ItemStack(Gizmos.invenswapperBase,1,1), new ItemStack(Gizmos.invenswapperBase,1,0));

	}

	@Override
	public String getName() {
		return "invenswappers";
	}

}
