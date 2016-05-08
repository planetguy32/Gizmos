package me.planetguy.gizmos.content.pvpparts;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import me.planetguy.gizmos.util.BlockAiry;
import me.planetguy.gizmos.util.MaterialSolidAir;
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
		super("presistentLightRay");
		this.setLightLevel(1.0f);
	}
	
	//same as air
	public int getRenderType(){
		if(FMLCommonHandler.instance().getEffectiveSide()==Side.SERVER
				||  !Keyboard.isKeyDown(Keyboard.KEY_F11))
			return -1;
		else
			return 0;
	}

	public void registerIcons(IIconRegister ir) {}
	
	@Override
	public IIcon getIcon(int side,int meta){
		return Blocks.glass.getIcon(side, meta);
	}

}
