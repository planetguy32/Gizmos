package planetguy.Gizmos.loader;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import planetguy.Gizmos.Gizmos;
import planetguy.Gizmos.timebomb.BlockTimeBomb;
import planetguy.Gizmos.timebomb.ItemTimeBomb;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class LoaderNodeTimeBombs extends LoaderNode{

	public static LoaderNode inst=new LoaderNodeTimeBombs();
	
	private final static LoaderNode[] depends={};
	
	public LoaderNodeTimeBombs() {
		super(new LoaderNode[0]);
	}

	@Override
	public void load() {
		Gizmos.timeBomb=new BlockTimeBomb(Gizmos.timeExplosivesID);
		GameRegistry.registerBlock(Gizmos.timeBomb,ItemTimeBomb.class,"timeBombs");
		//Item.itemsList[ Gizmos.timeExplosivesID] = new ItemTimeBomb( Gizmos.timeExplosivesID-256).setItemName("timeBombs");
		final String[] oreNames = {"Time bomb", "Fork bomb", "?", "?", "?", "?", "?", "?", "?", "?", "?", "?", "?", "?", "Fork bomb", "Time bomb"};
		for (int re = 0; re < 16; re++){
			ItemStack oreStack = new ItemStack(Gizmos.timeBomb, 1, re);
			LanguageRegistry.addName(oreStack, oreNames[re]);
		}	
		ItemStack itemStackTB=new ItemStack(Gizmos.timeBomb,1,0); 
		ItemStack itemStackFB=new ItemStack(Gizmos.timeBomb,1,1);
		ItemStack endStone=new ItemStack(Block.whiteStone);
		
		GameRegistry.addShapelessRecipe(itemStackTB, Block.tnt, Item.pocketSundial);
		
		GameRegistry.addRecipe(itemStackFB, new Object[]{
				"EEE","ETE","EEE", 
				Character.valueOf('T'),itemStackTB,
				Character.valueOf('E'),endStone});
	}

	@Override
	public String getName() {
		return "timeBombs";
	}

}
