package planetguy.gizmos;

import java.lang.reflect.Field;
import java.util.logging.Logger;

import planetguy.gizmos.inserter.BlockInserter;
import planetguy.gizmos.tool.ItemLastLaughArmor;
import planetguy.simpleLoader.CustomModuleLoader;
import planetguy.simpleLoader.SLLoad;
import planetguy.util.Debug;


import cpw.mods.fml.common.FMLLog;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.Event;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;


@SLLoad(name="eventHandler")
public class GizmosEventWatcher extends CustomModuleLoader{
	
	@ForgeSubscribe
	public void checkIfBombedItemShouldExplode(PlayerInteractEvent pie){
		
		//Debug.dbg("onInteract called"); //SO NOISY
		EntityPlayer player=pie.entityPlayer;
		ItemStack item=player.getHeldItem();
		if(item==null){
			return;
		}
		NBTTagCompound tag = item.getTagCompound();
		//Debug.dbg(tag==null);
		try{
			//Debug.dbg(tag.getShort("id"));
			int x=0;
			boolean testSoFar=true;
			//while(testSoFar){
			ItemStack a=ItemStack.loadItemStackFromNBT(tag);
			if(a.getItem()==Item.gunpowder&&BlockInserter.doBlockDamage){			
				World w=player.worldObj;
				w.newExplosion(player, player.posX, player.posY, player.posZ, 4, true, true);
				player.destroyCurrentEquippedItem();
				testSoFar=false;
				player.attackEntityFrom(DamageSource.magic, 20);
					
				//Debug.dbg("Surprise! >:)");
			        //player.openGui(ContentLoader.cl, 0, player.worldObj, (int) player.posX, (int) player.posY, (int) player.posZ);
				//}
				//x++;
			}else if(tag.getShort("id")==376){
				player.attackEntityFrom(DamageSource.wither, 20);
			}
		}catch(Exception e){
			//Can't do that I guess
		}	
	}
	
	@ForgeSubscribe
	public void checkIfLastLaughSuitShouldExplode(LivingDeathEvent ev){
		if(!(ev.entity instanceof EntityPlayer))return;
		EntityPlayer player=(EntityPlayer) ev.entity;
		ItemStack[] stacks=player.inventory.armorInventory;
		float pow=0;
		for(ItemStack s:stacks){
			if(s!=null && s.getItem() instanceof ItemLastLaughArmor){
				++pow;
			}
		}
		if(pow>0){
			player.worldObj.createExplosion(player, player.posX, player.posY, player.posZ, 4, true);
		}
	}

	@Override
	public void load() {
		MinecraftForge.EVENT_BUS.register(this);
	}

}