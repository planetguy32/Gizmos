package me.planetguy.gizmos.content.clearblocks;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import me.planetguy.lib.prefab.BlockBase;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPersistentGlowingAir extends BlockAiry {
	
	public BlockPersistentGlowingAir(){
		super("persistentLightRay");
		this.setLightLevel(1.0f);
	}
	
	public void registerIcons(IIconRegister ir) {}
	
	@Override
	public IIcon getIcon(int side,int meta){
		return Blocks.glass.getIcon(side, meta);
	}

}
