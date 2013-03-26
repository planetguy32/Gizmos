package planetguy.Gizmos.spy;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;

public class EnchantmentBomb extends EnchantmentSpyBase{
	
	public EnchantmentBomb(int id){
		super(id);
		this.setName("bomb");
	}
	
    public int getMaxLevel(){
    	return 1;
    }

}
