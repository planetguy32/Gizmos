package planetguy.Gizmos.spy;

import java.util.logging.Logger;

import cpw.mods.fml.common.FMLLog;

import planetguy.Gizmos.ContentLoader;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;

public class EventWatcherBombUse {
	
	@ForgeSubscribe
	public void checkIfBombedItemShouldExplode(PlayerInteractEvent pie){
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
			if(tag.getShort("id")==289){
				
				World w=player.worldObj;
				w.newExplosion(player, player.posX, player.posY, player.posZ, 4, true, true);
				player.destroyCurrentEquippedItem();
				testSoFar=false;
				player.setEntityHealth(0);
					
				//System.out.println("Surprise! >:)");
			        //player.openGui(ContentLoader.cl, 0, player.worldObj, (int) player.posX, (int) player.posY, (int) player.posZ);
				//}
				//x++;
			}
		}catch(Exception e){
			//Can't do that I guess
		}	
	}
	
	

}
