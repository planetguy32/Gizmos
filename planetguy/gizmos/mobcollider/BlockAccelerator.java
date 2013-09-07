package planetguy.gizmos.mobcollider;

import planetguy.gizmos.Gizmos;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockIce;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;

public class BlockAccelerator extends Block{
	
	public BlockAccelerator(int par1) {
		super(par1,Material.glass);
		this.slipperiness=Gizmos.accelRate;
	}
	
	public void registerIcons(IconRegister ir){
		this.blockIcon=ir.registerIcon(Gizmos.modName+":accelerator");
	}

}
