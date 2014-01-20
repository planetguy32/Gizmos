package planetguy.gizmos.tool;

import cpw.mods.fml.common.registry.LanguageRegistry;
import planetguy.simpleLoader.SLLoad;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

@SLLoad(name="arrowNova", dependencies={"entityArrowNova"},primacy=8)
public class ItemArrowNova extends Item{

	@SLLoad
	public ItemArrowNova(int par1) {
		super();
		this.func_149663_c("arrowNova");
		LanguageRegistry.addName(this, "Arrow-nova");
	}
	
	@Override
    public ItemStack onItemRightClick(ItemStack stack, World w, EntityPlayer p){
		
		
		return stack;
	}
	
	
	
	

}
