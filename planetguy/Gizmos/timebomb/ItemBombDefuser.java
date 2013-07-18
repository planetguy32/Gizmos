package planetguy.Gizmos.timebomb;

import planetguy.Gizmos.Gizmos;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBombDefuser extends Item{

	private int[] explosivesID;
	
	public ItemBombDefuser(int par1) {
		super(par1);
		// TODO Auto-generated constructor stub
	}
	
	public void registerIcons(IconRegister ir){
		itemIcon=ir.registerIcon(Gizmos.modName+":"+"defuser");
	}

	
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer thePlayer, World w, int x, int y, int z, int par7, float par8, float par9, float par10){
		int id=w.getBlockId(x, y, z);
		for(int i=0; i<Gizmos.defuseableIDs.length;i++){
			if(Gizmos.defuseableIDs[i]==id&&thePlayer.isSneaking()){
	            par1ItemStack.damageItem(1, thePlayer);
				w.setBlockToAir(x, y, z);
				return true;
			}
		}
		return false;
	}
 

}
