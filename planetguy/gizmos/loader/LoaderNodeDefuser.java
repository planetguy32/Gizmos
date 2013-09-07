package planetguy.gizmos.loader;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import planetguy.gizmos.Gizmos;
import planetguy.gizmos.tool.ItemBombDefuser;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class LoaderNodeDefuser extends LoaderNode{

	public static LoaderNode inst=new LoaderNodeDefuser();
	public LoaderNode[] depends=new LoaderNode[1];
	
	
	public LoaderNodeDefuser() {
		super(new LoaderNode[0]);
		this.depends[0]=LoaderNodeLens.inst;
	}
	
	@Override
	public void load() {
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
		return "defuser";
	}

}
