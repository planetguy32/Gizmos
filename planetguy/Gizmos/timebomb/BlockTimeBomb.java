package planetguy.Gizmos.timebomb;

import java.util.List;
import java.util.Random;

import planetguy.Gizmos.ConfigHolder;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockTimeBomb extends Block{

	private Icon topTex;
	private Icon bottomTex;
	private Icon[] sideIcons=new Icon[16];

	public BlockTimeBomb(int id) {
		super(id, Material.tnt);
        //this.setTickRandomly(true);
        this.setCreativeTab(CreativeTabs.tabRedstone);
		// TODO Auto-generated constructor stub
	}
	
	public int tickRate(){
		return 60;
	}
	
	public void registerIcons(IconRegister ir){
		System.out.println("Time bomb textures loading");
		topTex=ir.registerIcon(ConfigHolder.modName+":"+"bombTop");
		bottomTex=ir.registerIcon(ConfigHolder.modName+":"+"bombBottom");
		sideIcons[0] =ir.registerIcon(ConfigHolder.modName+":"+"timeBomb1");
		sideIcons[1] =ir.registerIcon(ConfigHolder.modName+":"+"timeBomb1");
		sideIcons[2] =ir.registerIcon(ConfigHolder.modName+":"+"timeBomb2");
		sideIcons[3] =ir.registerIcon(ConfigHolder.modName+":"+"timeBomb2");
		sideIcons[4] =ir.registerIcon(ConfigHolder.modName+":"+"timeBomb3");
		sideIcons[5] =ir.registerIcon(ConfigHolder.modName+":"+"timeBomb3");
		sideIcons[6] =ir.registerIcon(ConfigHolder.modName+":"+"timeBomb4");
		sideIcons[7] =ir.registerIcon(ConfigHolder.modName+":"+"timeBomb4");
		sideIcons[8] =ir.registerIcon(ConfigHolder.modName+":"+"timeBomb5");
		sideIcons[9] =ir.registerIcon(ConfigHolder.modName+":"+"timeBomb5");
		sideIcons[10]=ir.registerIcon(ConfigHolder.modName+":"+"timeBomb6");
		sideIcons[11]=ir.registerIcon(ConfigHolder.modName+":"+"timeBomb6");
		sideIcons[12]=ir.registerIcon(ConfigHolder.modName+":"+"timeBomb7");
		sideIcons[13]=ir.registerIcon(ConfigHolder.modName+":"+"timeBomb7");
		sideIcons[14]=ir.registerIcon(ConfigHolder.modName+":"+"timeBomb8");
		sideIcons[15]=ir.registerIcon(ConfigHolder.modName+":"+"timeBomb8");
	}

	
    @SideOnly(Side.CLIENT)
    public Icon getBlockTextureFromSideAndMetadata(int side, int meta){
		if(side==0||side==1){
			return topTex;
		}
		
        return sideIcons[meta];
    }
  
    @Override
    public void onBlockAdded(World par1World, int par2, int par3, int par4){
    	par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, this.tickRate());
    }
    
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int id, CreativeTabs par2CreativeTabs, List items){
    	items.add(new ItemStack(id, 1, 0));
    	items.add(new ItemStack(id, 1, 1));
    }

	@Override
	 public void updateTick(World w, int x, int y, int z, Random r){
		int curMeta=w.getBlockMetadata(x,y,z);
    	curMeta+=2;
    	if(curMeta>15){
    		w.setBlockToAir(x, y, z);
    		w.spawnEntityInWorld(new EntityTNTPrimed(w, (double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), (EntityLiving) null));
    	}else{
    		w.setBlockMetadataWithNotify(x, y, z, curMeta, 0x02);
    		w.scheduleBlockUpdate(x,y,z,this.blockID, this.tickRate());
    	}
    	
    	
	}    
	
    public void onBlockDestroyedByPlayer(World w, int x, int y, int z, int meta) {
    	fork(w,x,y,z,meta);
    	if(meta%2==0){
    		w.destroyBlock(x, y, z, true);
    	}
    }

    
    public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9){
    	fork(w,x,y,z);
    	return true;
    }

    /** The fork method for the fork bomb variant of the time bomb. Moves the bomb to all available spaces 
     * around it, or if there isn't space leaves it where it is.
     * 
     */
    
    private void fork(World w, int x, int y, int z, int meta){
    	if(meta%2!=1||!ConfigHolder.allowFB){
    		return;
    	}
    	boolean hasReplaced=false;
    	if(w.getBlockMaterial(x+1, y, z)==Material.air){
    		w.setBlock(x+1, y, z, ConfigHolder.timeExplosivesID, meta, 0x02);
    		hasReplaced=true;
    	}
    	if(w.getBlockMaterial(x-1, y, z)==Material.air){
    		w.setBlock(x-1, y, z, ConfigHolder.timeExplosivesID, meta, 0x02);
    		hasReplaced=true;
    	}
    	if(w.getBlockMaterial(x, y+1, z)==Material.air){
    		w.setBlock(x, y+1, z, ConfigHolder.timeExplosivesID, meta, 0x02);
    		hasReplaced=true;
    	}
    	if(w.getBlockMaterial(x, y-1, z)==Material.air){
    		w.setBlock(x, y-1, z, ConfigHolder.timeExplosivesID, meta, 0x02);
    		hasReplaced=true;
    	}
    	if(w.getBlockMaterial(x, y, z+1)==Material.air){
    		w.setBlock(x, y, z+1, ConfigHolder.timeExplosivesID, meta, 0x02);
    		hasReplaced=true;
    	}
    	if(w.getBlockMaterial(x, y, z-1)==Material.air){
    		w.setBlock(x, y, z-1, ConfigHolder.timeExplosivesID, meta, 0x02);
    		hasReplaced=true;
    	}
    	if(hasReplaced){
    		w.setBlockToAir(x, y, z);
    	}
    }
    	
    private void fork(World w, int x, int y, int z){
    	fork(w,x,y,z,w.getBlockMetadata(x, y, z));
    }


}
