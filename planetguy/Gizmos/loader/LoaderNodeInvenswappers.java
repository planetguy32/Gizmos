package planetguy.Gizmos.loader;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBlockWithMetadata;
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
		// TODO Auto-generated constructor stub
	}

	@Override
	public void load() {
		Gizmos.invenswapperTop=new BlockInvenswapperTop(Gizmos.invenswapperTopID);
		Gizmos.invenswapperBase=new BlockInvenswapperBase(Gizmos.invenswapperBottomID).setCreativeTab(CreativeTabs.tabDecorations);
		GameRegistry.registerTileEntity(TileEntityInvenswapper.class, "Gizmos.invenswapper");
		GameRegistry.registerBlock(Gizmos.invenswapperTop, ItemBlock.class,"invenswapperTop");
		GameRegistry.registerBlock(Gizmos.invenswapperBase, ItemBlockWithMetadata.class,"invenswapperBase");
		LanguageRegistry.addName(Gizmos.invenswapperBase, "Invenswapper base");
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "invenswappers";
	}

}
