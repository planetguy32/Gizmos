package planetguy.Gizmos.loader;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import planetguy.Gizmos.Gizmos;
import planetguy.Gizmos.timebomb.ItemBombDefuser;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class LoaderNodeDefuser extends LoaderNode{

	public static LoaderNode inst=new LoaderNodeDefuser();
	private static LoaderNode[] depends={LoaderNodeLens.inst};
	
	public LoaderNodeDefuser() {
		super(depends);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void loadLocal() {
		ItemStack shears=new ItemStack(Item.shears);
		ItemStack stick=new ItemStack(Item.stick);
		ItemStack lens=new ItemStack(Gizmos.spyLens);
		Gizmos.defuser=new ItemBombDefuser(Gizmos.defuserID).setMaxDamage(10).setCreativeTab(CreativeTabs.tabTools).setUnlocalizedName("defuser");
		LanguageRegistry.addName(Gizmos.defuser, "Bomb defuser");
		ItemStack ISDefuser=new ItemStack(Gizmos.defuser);
		GameRegistry.addRecipe(ISDefuser, new Object[]{
				" sl",
				" ks",
				"k  ",
				Character.valueOf('s'),shears,
				Character.valueOf('k'),stick,
				Character.valueOf('l'),lens});
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "defuser";
	}

}
