package me.planetguy.gizmos.content.admin;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import me.planetguy.lib.prefab.ItemBase;

public class ItemStickOfWhacking extends ItemBase{

	public ItemStickOfWhacking() {
		super("stickOfWhacking");
	}
	
    public boolean hitEntity(ItemStack stack, EntityLivingBase e1, EntityLivingBase e2){
    	e1.attackEntityFrom(DamageSource.outOfWorld, 9001.0f);
    	return true;
    }


}
