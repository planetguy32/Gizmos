package me.planetguy.gizmos.content.flashlight;

import java.util.Random;

import me.planetguy.lib.prefab.BlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockLightRay extends BlockBase{

	//Must be 0-15, since it's used as metadata
	public static final byte LIFESPAN=10;
	
	public BlockLightRay(){
		//can't use Material.air - MC drops your scheduled ticks (!!)
		super(
				new MaterialLightRay(Material.air.getMaterialMapColor())
				, "lightRay");
		this.setLightLevel(1.0f);
		this.setTickRandomly(true);
	}

	//pretty fast - faster can be hard on performance, slower can lead to light sticking around after you turn away
	public int tickRate(World w){
		return 3;
	}

	public void updateTick(World w, int x, int y, int z, Random rand){
		int meta=w.getBlockMetadata(x,y,z);
		if(meta>0){
			w.setBlockMetadataWithNotify(x, y, z, meta-1, ItemFlashlightBase.updateFlags);
		}else{
			w.setBlock(x, y, z, Blocks.air, 0, 0x03);
		}
		w.scheduleBlockUpdate(x, y, z, this, tickRate(w));
	}

	//request callback when placed
	public void onBlockAdded(World w, int x, int y, int z){
		w.scheduleBlockUpdate(x, y, z, this, tickRate(w));
	}

	//same as air
	public int getRenderType(){
		return -1;
	}

	//not opaque cube
	public boolean isOpaqueCube(){
		return false;
	}

	//no AABB
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World w, int x, int y, int z){
		return null;
	}

	//no drops
	public void dropBlockAsItemWithChance(World w, int x, int y, int z, int idk, float what, int thisis) {}

	//no collisions
	public boolean canCollideCheck(int p_149678_1_, boolean p_149678_2_){
		return false;
	}

	//update on neighbour change
	public void onNeighborBlockChange(World w, int x, int y, int z, Block b){
		w.scheduleBlockUpdate(x, y, z, this, tickRate(w));
	}

	//is air
	public boolean isAir(IBlockAccess world, int x, int y, int z){
		return false;
	}
	
	public void registerIcons(IIconRegister ir) {}

	public static void placeLightBlock(World w, int x, int y, int z){
		if(w.isAirBlock(x,y,z)){
			w.setBlock(x,y,z,ItemFlashlightBase.block,LIFESPAN,ItemFlashlightBase.updateFlags); //set to light ray with meta 1
			w.scheduleBlockUpdate(x,y,z,ItemFlashlightBase.block, ItemFlashlightBase.block.tickRate(w));
		}
	}

}