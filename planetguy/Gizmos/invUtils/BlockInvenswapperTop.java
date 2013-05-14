package planetguy.Gizmos.invUtils;

import java.util.Random;

import planetguy.Gizmos.ConfigHolder;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockInvenswapperTop extends Block {

	public BlockInvenswapperTop(int par1) {
		super(par1, Material.air);
		setUnlocalizedName("Gizmos_InvenswapperTop");
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void registerIcons(IconRegister ir){
		this.blockIcon=ir.registerIcon("Gizmos:blank");
	}

	@Override
	public boolean isOpaqueCube(){
		return false;
	}
	
	@Override
    public int quantityDropped(Random par1Random){
		return 0;
	}
	
	public void breakBlock(World w, int x, int y, int z, int par5, int par6) {
		try{
			Block.blocksList[w.getBlockId(x,y-1,z)].breakBlock(w,x,y-1,z, par5, par6);
		}catch(NullPointerException npe){
			w.setBlockToAir(x,y-1,z);
		}
		super.breakBlock(w, x, y, z, par5, par6);
	}
	
	public  AxisAlignedBB getCollisionBoundingBoxFromPool(World w, int x, int y, int z){
		return null;
	}
	
	public void onEntityCollidedWithBlock(World w, int x, int y, int z, Entity e){
		if(!(e instanceof EntityPlayer)){
			return;
		}
		TileEntityInvenswapper tileEntity =(TileEntityInvenswapper) w.getBlockTileEntity(x, y-1, z);
		if(tileEntity==null){
			w.setBlockToAir(x, y, z);
			return;
		}
		EntityPlayer player=(EntityPlayer) e;
		if(!e.isSneaking()){
			return;
		}
		InventoryPlayer ip=player.inventory;
		for(int i=0;i<ip.mainInventory.length;i++){
			ItemStack stack=ip.mainInventory[i];
			ItemStack newStack=tileEntity.addStack(stack);
			ip.mainInventory[i]=newStack;
		}
		
	}

}
