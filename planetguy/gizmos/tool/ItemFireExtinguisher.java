package planetguy.gizmos.tool;

import java.lang.reflect.Field;

import planetguy.gizmos.Gizmos;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class ItemFireExtinguisher extends Item{
	
	public ItemFireExtinguisher(int par1) {
		super(par1);
		this.setMaxStackSize(1);
		this.setMaxDamage(100);
		this.setCreativeTab(CreativeTabs.tabTools);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister ir){
		this.itemIcon=ir.registerIcon(Gizmos.modName+":extinguisher");
	}
	
	@Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World w, int x, int y, int z, int par7, float par8, float par9, float par10){
		w=player.worldObj;
		
		final int count=3; //Used to be a parameter system (onTickInUse or something)
		final int countSq=count*count;
		for(int a=-count; a<count;a++){
			for(int b=-count; b<count;b++){
				for(int c=-count; c<count;c++){
					//if(!(a*a+b*b+c*c<countSq))break;
					//System.out.println("("+(a+x)+","+(b+y)+","+(c+z)+")");
					if(w.getBlockMaterial(a+x, b+y, c+z)==Material.fire){
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
    public boolean func_111207_a(ItemStack ext, EntityPlayer player, EntityLivingBase target){
        boolean result=false;
        if(target.isBurning()){
        	target.extinguish();
        	result=true;
        }
        if(target instanceof EntityBlaze){
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
