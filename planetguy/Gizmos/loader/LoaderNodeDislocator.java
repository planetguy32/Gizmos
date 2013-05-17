package planetguy.Gizmos.loader;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import planetguy.Gizmos.Gizmos;
import planetguy.Gizmos.tool.ItemBlockTicker;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class LoaderNodeDislocator extends LoaderNode{
	
	public static LoaderNode inst=new LoaderNodeDislocator();
	
	public LoaderNodeDislocator(){
		super(new LoaderNode[0]);
	}
	
	@Override
	public void loadLocal() {
		ItemStack stackClock=new ItemStack(Item.pocketSundial);
		ItemStack iron = new ItemStack(Item.ingotIron);
		Gizmos.dislocator = new ItemBlockTicker(Gizmos.WandID).setUnlocalizedName("dislocator");
		ItemStack stackTicker=new ItemStack(Gizmos.dislocator,1,0); 
		GameRegistry.addRecipe(stackTicker, new Object[] {"ccc", "cic", "ccc",
				Character.valueOf('c'),stackClock,
				Character.valueOf('i'),iron});
		LanguageRegistry.addName(Gizmos.dislocator, "§5Temporal Dislocator");		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "dislocator";
	}

}
