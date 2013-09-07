package planetguy.gizmos.invUtils;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import planetguy.gizmos.Gizmos;
import planetguy.simpleLoader.SLLoad;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

@SLLoad(name="*invenswapperTop",hasMetadata=true)
public class BlockInvenswapperTop extends Block {

	private Icon[] icons=new Icon[2];

	@SLLoad
	public BlockInvenswapperTop(int par1) {
		super(par1, Material.air);
		setUnlocalizedName("Gizmos_InvenswapperTop");
        this.setBlockBounds(0,0,0,0,0,0);
        setResistance(100f);
	}
	
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister ir){
    	icons[0]=ir.registerIcon(Gizmos.modName+":invenswapperTopIn");
    	icons[1]=ir.registerIcon(Gizmos.modName+":invenswapperTopOut");
    	//icons[2]=ir.registerIcon("Gizmos:blank");
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int meta){
		return icons[meta];    	
    }

	@Override
	public boolean isOpaqueCube(){
		return false;
	}
	
	@Override
    public boolean renderAsNormalBlock(){
        return false;
    }
    
    @Override
    public int getRenderType(){
        return 1;
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
		if(w.getBlockMetadata(x, y, z)==0){
			for(int i=0;i<ip.mainInventory.length;i++){
				ItemStack stack=ip.mainInventory[i];
				ItemStack newStack=tileEntity.addStack(stack);
				ip.mainInventory[i]=newStack;
			}
		}else{
			int tePos=0;
			for(int i=0;i<ip.mainInventory.length;i++){
				ItemStack stack=ip.mainInventory[i];
				if(tePos==9)return;
				if(stack==null){
					ip.mainInventory[i]=tileEntity.getStackInSlot(tePos);
					tileEntity.setInventorySlotContents(tePos, null);
					tePos++;
				}
			}
		}

	}

}
