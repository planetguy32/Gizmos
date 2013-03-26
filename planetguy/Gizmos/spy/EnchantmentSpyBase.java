package planetguy.Gizmos.spy;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemStack;

public abstract class EnchantmentSpyBase extends Enchantment{
	
	public EnchantmentSpyBase(int id){
		super(id,1,EnumEnchantmentType.all);
	}
   
	public int getMinEnchantability(int par1){
       return 1001;
    }
	
	public int getMaxEnchantability(int par1){
       return 1000;
    }
    
    public boolean canApplyAtEnchantingTable(ItemStack stack)
    {
        return stack.getMaxStackSize()==1;
        //TODO way to apply it other than enchanting table, then this should return false
    }

}
