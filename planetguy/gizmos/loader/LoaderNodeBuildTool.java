package planetguy.gizmos.loader;

import planetguy.gizmos.Gizmos;
import planetguy.gizmos.tool.ItemBuildTool;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class LoaderNodeBuildTool extends LoaderNode{

	public static LoaderNode inst=new LoaderNodeBuildTool();
	public static LoaderNode[] depends={LoaderNodeInserter.inst};
	
	public LoaderNodeBuildTool() {
		super(new LoaderNode[0]);
		super.depends=new LoaderNode[1];
		super.depends[0]=LoaderNodeInserter.inst;
	}

	@Override
	public void load() {
		Gizmos.buildTool=new ItemBuildTool(Gizmos.buildToolID).setUnlocalizedName("buildTool").setCreativeTab(CreativeTabs.tabTools);

        ItemStack itStkBuildTool=new ItemStack(Gizmos.buildTool);
        ItemStack iSDPcx=new ItemStack(Item.pickaxeDiamond);
        ItemStack iSPist=new ItemStack(Block.pistonBase);
		ItemStack chest=new ItemStack(Block.chest);
		
        GameRegistry.addRecipe(itStkBuildTool, new Object[]{"  c"," p ","d  ", 
        		Character.valueOf('c'),chest,
        		Character.valueOf('p'),iSPist,
        		Character.valueOf('d'),iSDPcx});		
	}

	@Override
	public String getName() {
		return "buildTool";
	}
	


}
