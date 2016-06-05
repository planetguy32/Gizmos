package me.planetguy.gizmos.content.clearblocks;

import java.util.Random;

import codechicken.lib.vec.BlockCoord;
import codechicken.multipart.TileMultipart;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import me.planetguy.gizmos.content.flashlight.ItemFlashlightBase;
import me.planetguy.lib.prefab.BlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockLightRay extends BlockAiry{

	//Must be 0-15, since it's used as metadata
	public static final byte LIFESPAN=1;
	
	public static final byte TICK_RATE=4;
	
	//We don't actually glow on the server, to avoid wrecking the server's CPU
	public static final float LIGHT_VALUE = 
			FMLCommonHandler.instance().getEffectiveSide()==Side.CLIENT 
			? 1.0f
			: 0.0f;
	
	public BlockLightRay(){
		//can't use Material.air - MC drops your scheduled ticks (!!)
		super("lightRay");
		this.setLightLevel(LIGHT_VALUE);
		this.setTickRandomly(true);
	}
	
	//pretty fast - faster can be hard on performance, slower can lead to light sticking around after you turn away
	public int tickRate(World w){
		return TICK_RATE;
	}

	public void updateTick(World w, int x, int y, int z, Random rand){
		int meta=w.getBlockMetadata(x,y,z);
		if(meta>0){
			w.setBlockMetadataWithNotify(x, y, z, meta-1, ItemFlashlightBase.updateFlags);
			w.scheduleBlockUpdate(x, y, z, this, tickRate(w)); //No need to update air
		}else{
			w.setBlock(x, y, z, Blocks.air, 0, 0x03);
		}
	}

	//request callback when placed
	public void onBlockAdded(World w, int x, int y, int z){
		w.scheduleBlockUpdate(x, y, z, this, tickRate(w));
	}

	//update on neighbour change
	public void onNeighborBlockChange(World w, int x, int y, int z, Block b){
		w.scheduleBlockUpdate(x, y, z, this, tickRate(w));
	}
	
	public static void placeLightBlock(World w, int x, int y, int z){
		if(w.isAirBlock(x,y,z)){
			w.setBlock(x,y,z,ItemFlashlightBase.block,LIFESPAN,ItemFlashlightBase.updateFlags);
			w.scheduleBlockUpdate(x,y,z,ItemFlashlightBase.block, ItemFlashlightBase.block.tickRate(w));
		}
	}

}