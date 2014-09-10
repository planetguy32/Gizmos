package me.planetguy.gizmos.content;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import me.planetguy.gizmos.base.ItemBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBombDefuser extends ItemBase{

	public ItemBombDefuser() {
		super("bombDefuser");
		this.setMaxDamage(10);
	}
	public void loadCrafting(){
		ItemStack shears=new ItemStack(Items.shears);
		ItemStack stick=new ItemStack(Items.stick);
		ItemStack ISDefuser=new ItemStack(this);
		GameRegistry.addRecipe(ISDefuser, new Object[]{
				" sl",
				" ks",
				"k ",
				Character.valueOf('s'),shears,
				Character.valueOf('k'),stick,
				Character.valueOf('l'),new ItemStack(Items.redstone)});
	}
	//Removes any block at the specified coordinates if it's in the config as defusable or if it uses Material.tnt
	public boolean onItemUse(ItemStack stack, EntityPlayer thePlayer, World w, int x, int y, int z, int par7, float par8, float par9, float par10){
		if(w.getBlock(x, y, z).getMaterial()==Material.tnt){
			defuse(w,x,y,z,thePlayer,stack);
			return true;
		}
		return false;
	}
	//Damages item and removes target block
	private void defuse(World w, int x, int y, int z, EntityPlayer p, ItemStack stk){
		stk.damageItem(1, p);
		w.setBlockToAir(x, y, z);
	}

}
