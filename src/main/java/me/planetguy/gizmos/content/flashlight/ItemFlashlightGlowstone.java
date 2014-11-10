package me.planetguy.gizmos.content.flashlight;

import cpw.mods.fml.common.registry.GameRegistry;
import me.planetguy.gizmos.Gizmos;
import me.planetguy.gizmos.Properties;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemFlashlightGlowstone extends ItemFlashlightBase{
	
	private final class GlowstoneFlashlightRecipe implements IRecipe {
		ItemStack flashlight=new ItemStack(ItemFlashlightGlowstone.this);

		@Override
		public boolean matches(InventoryCrafting inv, World w) {
			boolean foundFlashlight=false;
			int glowstoneDustFound=0;
			for(int i=0; i<inv.getSizeInventory(); i++){
				ItemStack stk=inv.getStackInSlot(i);
				if(stk!=null ){
					if(stk.getItem() == ItemFlashlightGlowstone.this){
						if(foundFlashlight) //return false if multiple flashlights
							return false;
						foundFlashlight=true;
					}else if(stk.getItem()==Items.glowstone_dust){
						glowstoneDustFound++;
					}else{
						return false; //unknown item
					}
				}
			}
			return foundFlashlight && glowstoneDustFound>0;
		}

		@Override
		public ItemStack getCraftingResult(InventoryCrafting inv) {
			ItemStack flashlight = null;
			int glowstoneDustFound=0;
			for(int i=0; i<inv.getSizeInventory(); i++){
				ItemStack stk=inv.getStackInSlot(i);
				if(stk!=null ){
					if(stk.getItem() == ItemFlashlightGlowstone.this){
						flashlight=stk;
					}else if(stk.getItem()==Items.glowstone_dust){
						glowstoneDustFound++;
					}else{
						return null; //unknown item
					}
				}
			}
			if(flashlight==null || glowstoneDustFound < 1)
				return null;
			
			int damage=flashlight.getItemDamage();
			int newDamage=damage-Properties.flashlightRechargePerGlowstone*glowstoneDustFound;
			
			if(newDamage<0) //no negative damage
				newDamage=0;
			
			ItemStack stk=flashlight.copy();
			stk.setItemDamage(newDamage);
			return stk;
		}

		@Override
		public int getRecipeSize() {
			return 2;
		}

		@Override
		public ItemStack getRecipeOutput() {
			return flashlight;
		}
	}

	
	
	public ItemFlashlightGlowstone(){
		super("Glowstone");
	}
	
	public ItemFlashlightGlowstone(String s){
		super(s);
	}
	
	public void loadCrafting(){
		this.loadRechargeRecipe();
		GameRegistry.addShapedRecipe(new ItemStack(this, 1, this.getMaxDamage()), new Object[]{
			" il",
			"ibi",
			"gi ",
			Character.valueOf('i'), new ItemStack(Items.iron_ingot),
			Character.valueOf('g'), new ItemStack(Items.glowstone_dust),
			Character.valueOf('b'), new ItemStack(Blocks.stone_button),
			Character.valueOf('l'), new ItemStack(Blocks.glass)
		});
	}
	
	public void loadRechargeRecipe(){
		IRecipe recipe=new GlowstoneFlashlightRecipe();
		GameRegistry.addRecipe(recipe);
	}

}
