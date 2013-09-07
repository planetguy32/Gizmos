package planetguy.gizmos;


import planetguy.gizmos.invUtils.ContainerInvenswapper;
import planetguy.gizmos.invUtils.GuiInvenswapper;
import planetguy.gizmos.invUtils.TileEntityInvenswapper;
import planetguy.gizmos.spy.ContainerSpyLab;
import planetguy.gizmos.spy.GuiSpyTable;
import planetguy.gizmos.spy.InventorySpyLab;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
	//returns an instance of the Container you made earlier
	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		if(id==1){
			//System.out.println("Invenswapper GUI selected");
			return new ContainerInvenswapper(player.inventory, (TileEntityInvenswapper) tileEntity);
		}
		return new ContainerSpyLab(player.inventory, new InventorySpyLab(player.worldObj, (int) player.posX, (int) player.posY, (int) player.posZ));
	}

	//returns an instance of the Gui you made earlier
	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		int blockID=world.getBlockId(x, y, z);
		if(id==1){
			//System.out.println("Invenswapper GUI selected");
			return new GuiInvenswapper(player.inventory, (TileEntityInvenswapper) tileEntity);
		}
		return new GuiSpyTable(player.inventory, new InventorySpyLab(player.worldObj, (int) player.posX, (int) player.posY, (int) player.posZ));

	}
}