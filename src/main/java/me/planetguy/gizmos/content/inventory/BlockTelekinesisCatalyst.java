package me.planetguy.gizmos.content.inventory;

import me.planetguy.gizmos.Gizmos;
import me.planetguy.gizmos.Properties;
import me.planetguy.gizmos.base.BlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class BlockTelekinesisCatalyst extends BlockBase{

	public BlockTelekinesisCatalyst() {
		super(Material.wood, "telekinesisCatalyst");
		// TODO Auto-generated constructor stub
		//LanguageRegistry.instance().addStringLocalization("tile.telekinesisCatalyst.name", "Telekinesis Catalyst");
		//SLItemBlock.registerString(this, 0, "Telekinesis catalyst", new String[]{"Right-clicking uses whatever block is", "opposite, through up to "+maxBlockReach+" air."});
	}

	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer player, int side, float par7, float par8, float par9){
		try{
			boolean advance=true;
			int count=0;
			Block b = null;
			while(advance&&count<Properties.telekinesisRange){
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
				b=w.getBlock(x, y, z);
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
