package planetguy.Gizmos.loader;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumArmorMaterial;
import planetguy.Gizmos.Gizmos;
import planetguy.Gizmos.lastlaugh.ItemLastLaughArmor;
import cpw.mods.fml.common.registry.GameRegistry;

public class LoaderNodeLastLaughArmor extends LoaderNode{

	public static LoaderNodeLastLaughArmor inst=new LoaderNodeLastLaughArmor();
	
	public LoaderNodeLastLaughArmor() {
		super(new LoaderNode[]{});
	}

	@Override
	public void load() {
		Gizmos.lastLaughChestplate=new ItemLastLaughArmor(Gizmos.lastLaughChestplateID, EnumArmorMaterial.IRON, 2, 1);
		GameRegistry.registerItem(Gizmos.lastLaughChestplate, "lastLaughArmor", Gizmos.modName);
		Gizmos.lastLaughChestplate.setCreativeTab(CreativeTabs.tabDecorations);
	}

	@Override
	public String getName() {
		return "LastLaughArmor";
	}

}
