package planetguy.Gizmos.tool;

import planetguy.Gizmos.ConfigHolder;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;



public class ItemMinersLighter extends ItemInteractDevice{

	public ItemMinersLighter(int par1) {
		super(par1);
		setIconIndex(3);
	}

	@Override
	public boolean doEffect(int posX, int posY, int posZ, World theWorld, ItemStack me, EntityPlayer thePlayer) {
        int id = theWorld.getBlockId(posX, posY, posZ);

        if (id == 0)
        {
            theWorld.playSoundEffect((double)posX + 0.5D, (double)posY + 0.5D, (double)posZ + 0.5D, "fire.ignite", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
            theWorld.setBlock(posX, posY, posZ, ConfigHolder.geoFireID);
            me.damageItem(1, thePlayer);
            return true;
        }
        return false;
	}

	@Override
	public boolean canDoEffect(int posX, int posY, int posZ, World theWorld, ItemStack me, EntityPlayer thePlayer) {
		return theWorld.getBiomeGenForCoords(posX, posZ).biomeName!="Hell";
	}
	
}
