package me.planetguy.gizmos.content.admin;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import me.planetguy.lib.prefab.ItemBase;

public class ItemCreativeEnderPearl extends ItemBase {

	public ItemCreativeEnderPearl() {
		super("creativeEnderPearl");
	}

	public ItemStack onItemRightClick(ItemStack stk, World w, EntityPlayer p){
		w.playSoundAtEntity(p, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
		if (!w.isRemote){
			w.spawnEntityInWorld(new EntityEnderPearl(w, p));
		}
		return stk;
	}
}
