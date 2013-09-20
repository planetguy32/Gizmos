package planetguy.gizmos.invUtils;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import planetguy.simpleLoader.SLLoad;
import planetguy.simpleLoader.SLProp;

@SLLoad(name="telekinesisCatalyst")
public class BlockTelekinesisCatalyst extends Block{

	@SLProp(name = "telekinesisCatalystReach")
	public static int maxBlockReach=10;

	@SLLoad
	public BlockTelekinesisCatalyst(int id) {
		super(id, Material.wood);
		// TODO Auto-generated constructor stub
		this.setUnlocalizedName("telekinesisCatalyst");
		this.setCreativeTab(CreativeTabs.tabRedstone);
	}

	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer player, int side, float par7, float par8, float par9){
		try{
			boolean advance=true;
			int count=0;
			Block b = null;
			while(advance&&count<maxBlockReach){
				++count;
				switch(side){
				case 0:
					++y;
					break;
				case 1:
					--y;
					break;
				case 4:
					++x;
					break;
				case 5:
					--x;
					break;
				case 2:
					++z;
					break;
				case 3:
					--z;
					break;
				}
				b=Block.blocksList[w.getBlockId(x, y, z)];
				if(!w.isAirBlock(x, y, z))
					advance=false;
			}
			if(!advance)
				return b.onBlockActivated(w, x, y, z, player, side, par7, par8, par9);
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}



}
