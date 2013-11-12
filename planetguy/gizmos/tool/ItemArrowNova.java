package planetguy.gizmos.tool;

import planetguy.simpleLoader.SLLoad;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

@SLLoad(name="arrowNova", dependencies={"entityArrowNova"})
public class ItemArrowNova extends Item{

	@SLLoad
	public ItemArrowNova(int par1) {
		super(par1);
	}
	
	@Override
    public ItemStack onItemRightClick(ItemStack stack, World w, EntityPlayer p){
		
		
		return stack;
	}
	
	
	
	

}
