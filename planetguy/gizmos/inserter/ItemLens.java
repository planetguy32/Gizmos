package planetguy.gizmos.inserter;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import planetguy.gizmos.Gizmos;
import planetguy.gizmos.GizmosItem;
import planetguy.simpleLoader.SLLoad;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

@SLLoad(name="Lens")
public class ItemLens extends GizmosItem{
	
	@SLLoad
	public ItemLens(int id) {
		super(id);
		Gizmos.spyLens=this;
		this.setUnlocalizedName("spyLens");
		ItemStack glass=new ItemStack(Block.glass);
		ItemStack iron = new ItemStack(Item.ingotIron);
        LanguageRegistry.instance().addName(this, "Lens");
		ItemStack lens=new ItemStack(this);
		GameRegistry.addRecipe(lens, new Object[] { " i ", "igi", " i ", 
				Character.valueOf('g'), glass,
				Character.valueOf('i'), iron });
	}
	
	public void registerIcons(IconRegister ir){
		itemIcon=ir.registerIcon(Gizmos.modName+":spyLens");
	}
}
