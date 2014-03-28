package planetguy.gizmos.invUtils;

import planetguy.simpleLoader.SLLoad;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.Icon;

@SLLoad(name="mudpackMud",primacy=16)
public class BlockMudpackMud extends Block{

	@SLLoad
	public BlockMudpackMud(int par1) {
		super(par1, Material.ground);
		initializeBlock();
	}
	
	public void initializeBlock(){
		this.blockIcon=Block.dirt.getBlockTextureFromSide(0);
	}
	
	public Icon getIcon(int id, int meta){
		return this.blockIcon;
	}
	
}
