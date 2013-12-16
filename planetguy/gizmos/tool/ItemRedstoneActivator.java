package planetguy.gizmos.tool;

import planetguy.gizmos.Gizmos;
import planetguy.simpleLoader.SLLoad;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

@SLLoad(name="redstoneWand",primacy=10)
public class ItemRedstoneActivator extends ItemInteractDevice{

	@SLLoad
	public ItemRedstoneActivator(int par1) {
		super(par1);
	}

	@Override
	public boolean doEffect(int x, int y, int z, World w,
			ItemStack me, EntityPlayer thePlayer) {
		w.setBlock(x,y,z, Gizmos.redstoneWandBlock.blockID);
		return false;
	}

	@Override
	public boolean canDoEffect(int x, int y, int z, World w,
			ItemStack me, EntityPlayer thePlayer) {
		return w.isAirBlock(x, y, z);
	}
	

}
