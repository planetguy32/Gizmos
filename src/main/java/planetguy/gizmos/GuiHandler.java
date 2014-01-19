package planetguy.gizmos;


import planetguy.gizmos.industry.ContainerMultiMachine;
import planetguy.gizmos.industry.GuiMultiMachine;
import planetguy.gizmos.industry.TileEntityMultiMachine;
import planetguy.gizmos.inserter.ContainerInserter;
import planetguy.gizmos.inserter.GuiInserter;
import planetguy.gizmos.inserter.InventoryInserter;
import planetguy.gizmos.invUtils.ContainerInvenswapper;
import planetguy.gizmos.invUtils.GuiInvenswapper;
import planetguy.gizmos.invUtils.TileEntityInvenswapper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
	//returns an instance of the Container you made earlier
	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		switch(id){
		case 2:
			return new ContainerMultiMachine(player.inventory,(TileEntityMultiMachine) tileEntity);
		case 1:
			return new ContainerInvenswapper(player.inventory, (TileEntityInvenswapper) tileEntity);
		}
		return new ContainerInserter(player.inventory, new InventoryInserter(player.worldObj, (int) player.posX, (int) player.posY, (int) player.posZ));
	}

	//returns an instance of the Gui you made earlier
	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		int blockID=world.getBlockId(x, y, z);
		switch(id){
		case 2:
			return new GuiMultiMachine(player.inventory, (TileEntityMultiMachine) tileEntity);
		case 1:
			//Debug.dbg("Invenswapper GUI selected");
			return new GuiInvenswapper(player.inventory, (TileEntityInvenswapper) tileEntity);
		}
		return new GuiInserter(player.inventory, new InventoryInserter(player.worldObj, (int) player.posX, (int) player.posY, (int) player.posZ));

	}
}