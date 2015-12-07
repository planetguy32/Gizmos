package me.planetguy.gizmos.content.inventory;

import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import me.planetguy.lib.prefab.BlockBase;
import me.planetguy.lib.prefab.BlockContainerBase;
import me.planetguy.lib.util.SidedIcons;
import me.planetguy.lib.util.transformations.Rotator;

public class BlockInventoryMultiplexer extends BlockContainerBase {

	public SidedIcons icons;
	
	public BlockInventoryMultiplexer() {
		super(Material.wood, "inventoryMultiplexer", TileEntityInventoryMultiplexer.class);
	}
	
	@Override //not meta-sensitive
	public ItemStack getDrop(int metadata) {
		return new ItemStack(this);
	}
	
	@Override
	public TileEntity createNewTileEntity(World w, int meta) {
		return new TileEntityInventoryMultiplexer();
	}
	
	@Override
	public void registerBlockIcons(IIconRegister r) {
		icons=new SidedIcons(modid, getName(), r);
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		return icons.getIcon(meta, side);
	}
	
	@Override
	public void onBlockPlacedBy(World w, int x, int y, int z, EntityLivingBase placer, ItemStack stk) {
		super.onBlockPlacedBy(w, x, y, z, placer, stk);
		w.setBlockMetadataWithNotify(x, y, z, BlockPistonBase.determineOrientation(w, x, y, z, placer), 2);
	}
	
	@Override
	public boolean rotateBlock(World w, int x, int y, int z, ForgeDirection axis) {
		return w.setBlockMetadataWithNotify(x, y, z, Rotator.newSide(w.getBlockMetadata(x, y, z), axis), 2);
	}
	
}
