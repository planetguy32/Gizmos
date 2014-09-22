package me.planetguy.gizmos.content;

import java.util.Random;

import me.planetguy.lib.prefab.ItemBase;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemTemporalDislocator extends ItemBase{
	
	private final int TICKS_PER_MINUTE=20*60;
	private Random r=new Random();

	public ItemTemporalDislocator() {
		super("temporalDislocator");
		this.maxStackSize = 1;
		this.setMaxDamage(64);
	}

	public void loadCrafting(){
		ItemStack stackClock=new ItemStack(Items.clock);
		ItemStack iron = new ItemStack(Items.iron_ingot);
		ItemStack stackTicker=new ItemStack(this,1,0);
		GameRegistry.addRecipe(stackTicker, new Object[] {"ccc", "cic", "ccc",
				Character.valueOf('c'),stackClock,
				Character.valueOf('i'),iron});
		//LanguageRegistry.addName(this, "§5Temporal Dislocator");	
	}
	//Ticks tile entity as many times as it would normally tick in a whole minute.
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World w, int par4, int par5, int par6, int par7, float par8, float par9, float par10){
		try{
			TileEntity t=w.getTileEntity(par4, par5, par6);
			if(t!=null){ //if it has a tile entity tick it
				for(int i=0; i<TICKS_PER_MINUTE; i++){
					t.updateEntity();
				}
			}else{//otherwise tick the block
				Block b=w.getBlock(par4, par5, par6);
				for(int i=0; i<TICKS_PER_MINUTE; i++){
					b.updateTick(w, par4, par5, par6, r);
				}
			}
			par1ItemStack.damageItem(1, par2EntityPlayer);
		}catch(Exception ignore){
		}
		return false;
	}

}
