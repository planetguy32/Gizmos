package planetguy.gizmos.tool;

import java.util.List;

import planetguy.gizmos.Gizmos;
import planetguy.simpleLoader.SLLoad;
import planetguy.simpleLoader.SLProp;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.biome.BiomeGenBase;

//@SLLoad(name="lastLaugh")
public class ItemLastLaughArmor extends ItemArmor{

	public ItemLastLaughArmor(int id,EnumArmorMaterial par2EnumArmorMaterial, int renderIndex, int armorType) {
		super(id, par2EnumArmorMaterial, renderIndex, armorType);
		setUnlocalizedName("lastLaugh");
		Gizmos.lastLaughChestplate=this;
		LanguageRegistry.addName(this, "Last laugh armor");
		this.itemIcon=Item.plateIron.getIcon(new ItemStack(Item.plateIron), 0);
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
	
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer player, List tooltipLines, boolean advancedTooltipsActive){
		tooltipLines.add("He who laughs last, laughs longest.");
	}

}
