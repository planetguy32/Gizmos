package planetguy.gizmos.tool;

import java.lang.reflect.Field;
import java.util.List;

import org.lwjgl.input.Keyboard;

import planetguy.gizmos.Gizmos;
import planetguy.simpleLoader.SLLoad;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
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
import net.minecraft.world.biome.BiomeGenBase;

@SLLoad(name="fireExtinguisher",primacy=5)

public class ItemFireExtinguisher extends Item{
	
	@SLLoad
	public ItemFireExtinguisher(int par1) {
		super();
		this.setMaxStackSize(1);
		this.setMaxDamage(100);
		this.func_149647_a(CreativeTabs.tabTools);
		this.func_149663_c("fireExtinguisher");
		LanguageRegistry.instance().addNameForObject(this, "en_US", "Fire extinguisher");
		GameRegistry.addRecipe(new ItemStack(this), new Object[] {
			" i ",
			"rbr",
			"rbr",
			Character.valueOf('i'),new ItemStack(Item.ingotIron),
			Character.valueOf('r'),new ItemStack(Item.dyePowder,1,14),
			Character.valueOf('b'),new ItemStack(Item.bucketWater)
		});
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir){
		this.itemIcon=ir.registerIcon(Gizmos.modName+":extinguisher");
	}
	
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer player, List tooltipLines, boolean advancedTooltipsActive){
        if(!Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
        	tooltipLines.add("Hold <shift> for more");
        	return;
        }
		tooltipLines.add("Cleans up fires. If it's deforestator");
		tooltipLines.add("or miner's lighter fire, act fast.");
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
