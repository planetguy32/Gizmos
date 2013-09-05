package planetguy.Gizmos.lastlaugh;

import planetguy.simpleLoader.SLLoad;
import planetguy.simpleLoader.SLProp;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

@SLLoad(name="lastLaugh")
public class ItemLastLaughArmor extends ItemArmor{

	public ItemLastLaughArmor(int id,EnumArmorMaterial par2EnumArmorMaterial, int renderIndex, int armorType) {
		super(id, par2EnumArmorMaterial, renderIndex, armorType);
		setUnlocalizedName("lastLaugh");
		LanguageRegistry.addName(this, "Last laugh armor");
	}
	
	@Override
	public Icon getIcon(ItemStack s, int pass){
		return Item.plateIron.getIcon(s, pass);
	}
	
	@SLLoad
	public ItemLastLaughArmor(int id){
		this(id, EnumArmorMaterial.IRON, 0,0);
	}
	
	public void explode(ItemStack stack, Entity e){
		//TODO hook up for CES when it works. For now handle in event watcher.
	}

}
