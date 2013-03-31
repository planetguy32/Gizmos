package planetguy.Gizmos.other;

import planetguy.Gizmos.ConfigHolder;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockIce;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;

public class BlockAccelerator extends BlockIce{
	
	public BlockAccelerator(int par1) {
		super(par1);
		this.slipperiness=ConfigHolder.accelRate;
		// TODO Auto-generated constructor stub
	}

}
