package planetguy.gizmos.tool;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPoweredOre;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import planetguy.simpleLoader.SLLoad;

@SLLoad(name="redstoneWandBlock",primacy=13)
public class BlockRedstoneWand extends BlockPoweredOre{

	@SLLoad
	public BlockRedstoneWand(int par1) {
		super(par1);
	}
	
	public boolean isOpaqueCube(){
		return false;
	}
	
	public int getRenderType(){
		return Block.grass.getRenderType();
	}
	
	public void onBlockAdded(World w, int x, int y, int z){
		w.scheduleBlockUpdate(x, y, z, this.blockID, 20);
	}

	//On tick, vanish
	public void updateTick(World w, int x, int y, int z, Random par5Random){
		w.setBlock(x, y, z, 0);
	}
	
	//Always power
    public int isProvidingWeakPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5){
        return 15;
    }
    
    //Yes indeed, power other blocks
    public boolean canProvidePower(){
    	return true;
    }
    
    //Vanish if r-clicked
	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer player, int idk, float what, float these, float are)  {
		w.setBlock(x,y,z,0);
		return true;
	}
	
	public void registerIcons(IconRegister ir){
		this.blockIcon=Block.cloth.getIcon(0, 14); //Snarf the icon from red wool.
	}

}
