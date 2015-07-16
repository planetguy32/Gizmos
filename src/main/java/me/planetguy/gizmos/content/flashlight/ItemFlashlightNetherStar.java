package me.planetguy.gizmos.content.flashlight;

import cofh.api.energy.IEnergyContainerItem;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.common.registry.GameRegistry;
import me.planetguy.gizmos.Properties;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ItemFlashlightNetherStar extends ItemFlashlightBase {
	
	public ItemFlashlightNetherStar(){
		super("Nether");
	}
	
	public ItemFlashlightNetherStar(String s){
		super(s);
	}
	
	
	public void damageItem(ItemStack stk, EntityLivingBase e){
		//WE ARE INVINCIBLE AHAHAHAHAHA!!!
	}
	
	public void loadCrafting(){
		GameRegistry.addShapedRecipe(new ItemStack(this, 1, this.getMaxDamage()), new Object[]{
			" il",
			"ibi",
			"gi ",
			Character.valueOf('i'), new ItemStack(Items.iron_ingot),
			Character.valueOf('g'), new ItemStack(Items.nether_star),
			Character.valueOf('b'), new ItemStack(Blocks.stone_button),
			Character.valueOf('l'), new ItemStack(Blocks.glass)
		});
	}

}
