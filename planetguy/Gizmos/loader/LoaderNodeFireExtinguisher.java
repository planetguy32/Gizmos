package planetguy.Gizmos.loader;

import planetguy.Gizmos.Gizmos;
import planetguy.Gizmos.tool.ItemFireExtinguisher;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class LoaderNodeFireExtinguisher extends LoaderNode {

	private final static LoaderNode[] nothing={};
	
	public static LoaderNode inst=new LoaderNodeFireExtinguisher();
	
	public LoaderNodeFireExtinguisher() {
		super(nothing);
	}

	@Override
	public void load() {
		Gizmos.fireExtinguisher=new ItemFireExtinguisher(Gizmos.fireExtID);
		GameRegistry.registerItem(Gizmos.fireExtinguisher, "fireExtinguisher");
		LanguageRegistry.instance().addName(Gizmos.fireExtinguisher, "Fire extinguisher");
		
		//Crafting
		ItemStack bucket=new ItemStack(Item.bucketWater);
		ItemStack iron=new ItemStack(Item.ingotIron);
		ItemStack rose= new ItemStack(Item.dyePowder);
		rose.setItemDamage(1);
		GameRegistry.addRecipe(new ItemStack(Gizmos.fireExtinguisher), new Object[]{
			" i ",
			"rbr",
			"rbr",
			Character.valueOf('i'),iron,
			Character.valueOf('b'),bucket,
			Character.valueOf('r'),rose
		});
		
	}
	
	@Override
	public String getName() {
		return "fireExtinguisher";
	}

}
