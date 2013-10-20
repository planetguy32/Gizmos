package planetguy.gizmos.multiblock;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import planetguy.gizmos.Gizmos;
import planetguy.simpleLoader.SLLoad;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

@SLLoad(name="composter")
public class BlockComposter extends BlockContainer {

	@SLLoad
	public BlockComposter(int id) {
		super(id,Material.ground);
		this.setUnlocalizedName("gizmos_Composter");
		GameRegistry.registerTileEntity(TileEntityComposter.class,"Gizmos_composter");
		LanguageRegistry.addName(this, "Composter");
		GameRegistry.addRecipe(new ItemStack(this), new Object[]{
			"fdf","fbf",
			Character.valueOf('d'),new ItemStack(Block.dirt),
			Character.valueOf('b'),new ItemStack(Item.bucketWater),
			Character.valueOf('f'),new ItemStack(Item.dyePowder,1,1)
		});
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityComposter();
	}
	
	@Override
	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer player, int idk, float what, float these, float are) {
		TileEntity tileEntity = w.getBlockTileEntity(x, y, z);
		if (tileEntity == null 
				||!(tileEntity instanceof TileEntityComposter) 
				|| player.isSneaking()) {
			return false;
		}
		TileEntityComposter composter=(TileEntityComposter) tileEntity;
		if(composter.isValidComposter()){ //center==null means this block is the center.
			player.openGui(Gizmos.instance, 2, w, x, y, z);
			return true;
		}
		return false;
	}

}
