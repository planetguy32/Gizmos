package me.planetguy.gizmos.content.clearblocks;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import me.planetguy.lib.prefab.BlockBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockAiry extends BlockBase {

	private static boolean revealActive=false;
	
	protected BlockAiry(String name) {
		super(MaterialSolidAir.instance,  name);
	}
	
	public static void toggleReveal(){
		revealActive=!revealActive;
	}
	
	public static boolean isRevealed(){
		return revealActive;
	}
	
	//same as air
	public int getRenderType(){
		return isRevealed() ? 0 : -1;
	}

	//not opaque cube
	public boolean isOpaqueCube(){
		return false;
	}

	//no AABB
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World w, int x, int y, int z){
		return isRevealed() ? super.getCollisionBoundingBoxFromPool(w,x,y,z) : null;
	}

	//no collisions
	public boolean canCollideCheck(int p_149678_1_, boolean p_149678_2_){
		return isRevealed();
	}

	//is air
	public boolean isAir(IBlockAccess world, int x, int y, int z){
		return !isRevealed();
	}
	
}
