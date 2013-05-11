package planetguy.Gizmos.mobcollider;

import planetguy.Gizmos.ConfigHolder;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockIce;
import net.minecraft.block.material.Material;


public class BlockAccelerator extends Block{
	
	public BlockAccelerator(int par1) {
		super(par1,11,Material.glass);
		this.slipperiness=ConfigHolder.accelRate;
	}
	
	public String getTextureFile(){
		return "/planetguy/Gizmos/tex.png";
	} 
	

}
