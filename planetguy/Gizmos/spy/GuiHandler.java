package planetguy.Gizmos.spy;


import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
        //returns an instance of the Container you made earlier
        @Override
        public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
                return new ContainerSpyLab(player.inventory, new InventorySpyLab(player.worldObj, (int) player.posX, (int) player.posY, (int) player.posZ));
        }

        //returns an instance of the Gui you made earlier
        @Override
        public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
                TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
                return new GuiSpyTable(player.inventory, new InventorySpyLab(player.worldObj, (int) player.posX, (int) player.posY, (int) player.posZ));

        }
}