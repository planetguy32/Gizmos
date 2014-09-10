package me.planetguy.gizmos.content;

import java.util.List;

import me.planetguy.gizmos.Gizmos;
import me.planetguy.gizmos.base.ItemBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemFireExtinguisher extends ItemBase{
	
	public ItemFireExtinguisher() {
		super("fireExtinguisher");
		this.setMaxStackSize(1);
		this.setMaxDamage(100);
	}
	
	public int countTooltipLines(){
		return 2;
	}

	public void loadCrafting(){
		GameRegistry.addRecipe(new ItemStack(this), new Object[] {
			" i ",
			"rbr",
			"rbr",
			Character.valueOf('i'),new ItemStack(Items.iron_ingot),
			Character.valueOf('r'),new ItemStack(Items.dye,1,14),
			Character.valueOf('b'),new ItemStack(Items.water_bucket)
		});
	}
	
	@Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World w, int x, int y, int z, int par7, float par8, float par9, float par10){
		w=player.worldObj;
		if(player.isBurning()){
			player.extinguish();
			stack.damageItem(1, player);
		}
		
		final int count=3; //Used to be a parameter system (onTickInUse or something)
		final int countSq=count*count;
		for(int a=-count; a<count;a++){
			for(int b=-count; b<count;b++){
				for(int c=-count; c<count;c++){
					//if(!(a*a+b*b+c*c<countSq))break;
					//Debug.dbg("("+(a+x)+","+(b+y)+","+(c+z)+")");
					if(w.getBlock(a+x, b+y, c+z).getMaterial()==Material.fire){
						w.setBlockToAir(a+x, b+y, c+z);
						stack.damageItem(1, player);
						for(int i=0; i<10; i++){
							w.spawnParticle("explode", a+x, b+y, c+z, 0,0.1,0);
						}
					}
				}
			}
		}
		return true;
	}

	
	@Override
    public boolean itemInteractionForEntity(ItemStack ext, EntityPlayer player, EntityLivingBase target){
        boolean result=false;
        if(player.isBurning()){
        	player.extinguish();
        	result=true;
        }else if(target.isBurning()){
        	target.extinguish();
        	result=true;
        }else if(target instanceof EntityBlaze){
        	target.attackEntityFrom(DamageSource.magic, 10);
        	result=true;
        }
        if(result){
        	ext.damageItem(1, target);
			for(int i=0; i<10; i++){
				target.worldObj.spawnParticle("explode", target.posX, target.posY, target.posZ, 0,0.1,0);
			}
        }
		return result;
    }

}
