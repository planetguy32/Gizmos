package planetguy.gizmos;

import java.util.logging.Logger;

import planetguy.gizmos.tool.ItemLastLaughArmor;

import cpw.mods.fml.common.FMLLog;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;

public class GizmosEventWatcher {
	

	
	@ForgeSubscribe
	public void checkIfBombedItemShouldExplode(PlayerInteractEvent pie){
		
		//System.out.println("onInteract called"); //SO NOISY
		EntityPlayer player=pie.entityPlayer;
		ItemStack item=player.getHeldItem();
		if(item==null){
			return;
		}
		NBTTagCompound tag = item.getTagCompound();
		//System.out.println(tag==null);
		try{
			//System.out.println(tag.getShort("id"));
			int x=0;
			boolean testSoFar=true;
			//while(testSoFar){
			if(tag.getShort("id")==289&&!Gizmos.serverSafeMode){
				
				World w=player.worldObj;
				w.newExplosion(player, player.posX, player.posY, player.posZ, 4, true, true);
				player.destroyCurrentEquippedItem();
				testSoFar=false;
				player.attackEntityFrom(DamageSource.magic, 20);
					
				//System.out.println("Surprise! >:)");
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
	
	

}