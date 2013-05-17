package planetguy.Gizmos.loader;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import planetguy.Gizmos.Gizmos;
import planetguy.Gizmos.tool.BlockForestFire;
import planetguy.Gizmos.tool.BlockSuperFire;
import planetguy.Gizmos.tool.ItemDeforester;
import planetguy.Gizmos.tool.ItemMinersLighter;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class LoaderNodeFire extends LoaderNode{
	
	public static LoaderNode inst=new LoaderNodeFire();
	
	public LoaderNodeFire() {
		super(new LoaderNode[0]);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void load() {
		ItemStack itemStackFlintAndSteel= new ItemStack(Item.flintAndSteel);
		ItemStack powder = new ItemStack(Item.blazePowder);
		ItemStack sapling=new ItemStack(Block.sapling);
		ItemStack gravel=new ItemStack(Block.gravel);
		
		Gizmos.deforestator = new ItemDeforester(Gizmos.netherLighterID).setUnlocalizedName("netherLighter");
		Gizmos.mlighter = new ItemMinersLighter(Gizmos.minerLighterID).setUnlocalizedName("minersLighter");
		Gizmos.geoFire = new BlockSuperFire(Gizmos.geoFireID, 31).setUnlocalizedName("doomFire").setHardness(0.0F).setLightValue(1.0F);
		Gizmos.forestFire = new BlockForestFire(Gizmos.forestFireID, 31).setUnlocalizedName("woodFire").setHardness(0.0F).setLightValue(1.0F);

		GameRegistry.registerBlock(Gizmos.geoFire, ItemBlock.class, "doomFire");
		
		ItemStack itemStackNetherLighter = new ItemStack(Gizmos.deforestator,1,0);
		GameRegistry.addRecipe(itemStackNetherLighter, new Object[]{ "brb", "rfr", "brb",
				'b',powder,
				'r',sapling,
				'f',itemStackFlintAndSteel});
		ItemStack itemStackMinerLighter = new ItemStack(Gizmos.mlighter,1,0);
		GameRegistry.addRecipe(itemStackMinerLighter, new Object[]{ "brb", "rfr", "brb",
				'b',powder,
				'r',gravel,
				'f',itemStackFlintAndSteel});
		LanguageRegistry.addName(Gizmos.deforestator, "Deforestator");
		LanguageRegistry.addName(Gizmos.mlighter, "Miner's lighter");		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "superFire";
	}

}
