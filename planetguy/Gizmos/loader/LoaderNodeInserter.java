package planetguy.Gizmos.loader;

import planetguy.Gizmos.Gizmos;
import planetguy.Gizmos.spy.BlockInserter;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class LoaderNodeInserter extends LoaderNode {

	public static LoaderNode inst=new LoaderNodeInserter();
	
	public static boolean isLoaded=false;
	
	//private final static LoaderNode[] depends={LoaderNodeLens.inst};
	
	public LoaderNodeInserter() {
		super(new LoaderNode[0]);
		super.depends=new LoaderNode[1];
		super.depends[0]=LoaderNodeLens.inst;
	}

	@Override
	public void load() {
		if(isLoaded)return;
		isLoaded=true;
		ItemStack lens=new ItemStack(Gizmos.spyLens);
		ItemStack wood=new ItemStack(Block.planks);
		ItemStack blockIron=new ItemStack(Block.blocksList[42]);
		ItemStack crafter=new ItemStack(Block.workbench);
		ItemStack chest=new ItemStack(Block.chest);
		
		Gizmos.spyDesk=new BlockInserter(Gizmos.spyLabID,6).setUnlocalizedName("spyLab");
		GameRegistry.registerBlock(Gizmos.spyDesk, ItemBlock.class, "spyLab");
        LanguageRegistry.instance().addName(Gizmos.spyDesk, "Inserter");		
		ItemStack itemSpyDesk=new ItemStack(Gizmos.spyDesk);
        GameRegistry.addRecipe(itemSpyDesk, new Object[] {"LWC", "III","B B",
        		Character.valueOf('L'),lens,
        		Character.valueOf('W'),crafter,
        		Character.valueOf('C'),chest,
        		Character.valueOf('I'),blockIron,
        		Character.valueOf('B'),wood});
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "inserter";
	}
	
	

}
