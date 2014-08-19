package me.planetguy.gizmos;


import me.planetguy.gizmos.inserter.ContainerInserter;
import me.planetguy.gizmos.inserter.GuiInserter;
import me.planetguy.gizmos.inserter.InventoryInserter;
import me.planetguy.gizmos.invUtils.BlockInvenswapperBase;
import me.planetguy.gizmos.invUtils.ContainerInvenswapper;
import me.planetguy.gizmos.invUtils.GuiInvenswapper;
import me.planetguy.gizmos.invUtils.TileEntityInvenswapper;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
	//returns an instance of the Container you made earlier
	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		switch(id){
		case 1:
			return new ContainerInvenswapper(player.inventory, (TileEntityInvenswapper) tileEntity);
		}
		return new ContainerInserter(player.inventory, new InventoryInserter(player.worldObj, (int) player.posX, (int) player.posY, (int) player.posZ));
	}

	//returns an instance of the Gui you made earlier
	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		Block block=world.getBlock(x, y, z);
		if(block instanceof BlockInvenswapperBase)
			return new GuiInvenswapper(player.inventory, (TileEntityInvenswapper) tileEntity);

		return new GuiInserter(player.inventory, new InventoryInserter(player.worldObj, (int) player.posX, (int) player.posY, (int) player.posZ));

	}
}