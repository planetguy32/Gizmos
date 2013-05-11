package planetguy.Gizmos.timebomb;

import java.util.List;
import java.util.Random;

import planetguy.Gizmos.ConfigHolder;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class BlockTimeBomb extends Block{

	private final int fuse;

	public BlockTimeBomb(int id) {
		super(id, Material.tnt);
        //this.setTickRandomly(true);
        this.setCreativeTab(CreativeTabs.tabRedstone);
        fuse=ConfigHolder.timeExplosivesFuse*5/2;//Simplified 20/8: 20 ticks/sec, 8 updates to explode
	}
	
	
	
	public int getBlockTextureFromSideAndMetadata(int par1, int par2) {
		if(par1==0||par1==1){
			return 1;
		}else{
			return 64+par2-par2%2;
		}
	}
	
	public int tickRate(){
		return fuse; 
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
    		w.setBlock(x, y, z,0);
    		explode(w,x,y,z);
    	}else{
    		w.setBlockMetadataWithNotify(x, y, z, curMeta);
    		w.scheduleBlockUpdate(x,y,z,this.blockID, this.tickRate());
    	}    	
	}    
	
	public void explode(World w, int x, int y, int z){
		w.spawnEntityInWorld(new EntityTNTPrimed(w, (double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F)));
	}
	
    public void onBlockDestroyedByPlayer(World w, int x, int y, int z, int meta) {
    	fork(w,x,y,z,meta);
    	if(meta%2==0){
    		super.onBlockDestroyedByPlayer(w, x, y, z, meta);
    	}
    }
    
	public String getTextureFile(){
		  return "/planetguy/Gizmos/tex.png";
	}
    
    public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9){
    	fork(w,x,y,z);
    	return true;
    }
    
    public void onBlockDestroyedByExplosion(World par1World, int par2, int par3, int par4, Explosion par5Explosion)
    {
        if (!par1World.isRemote)
        {
            EntityTNTPrimed entitytntprimed = new EntityTNTPrimed(par1World, (double)((float)par2 + 0.5F), (double)((float)par3 + 0.5F), (double)((float)par4 + 0.5F));
            entitytntprimed.fuse = par1World.rand.nextInt(entitytntprimed.fuse / 4) + entitytntprimed.fuse / 8;
            par1World.spawnEntityInWorld(entitytntprimed);
        }
    }

    /* The fork method for the fork bomb variant of the time bomb. Copies the bomb to all available spaces 
     * around it.
     */
    
    private void fork(World w, int x, int y, int z, int meta){
    	if(meta%2!=1||!ConfigHolder.allowFB){
    		return;
    	}
    	if(w.getBlockMaterial(x+1, y, z)==Material.air){
    		w.setBlockAndMetadataWithNotify(x+1, y, z, ConfigHolder.timeExplosivesID, meta);
    	}
    	if(w.getBlockMaterial(x-1, y, z)==Material.air){
    		w.setBlockAndMetadataWithNotify(x-1, y, z, ConfigHolder.timeExplosivesID, meta);
    	}
    	if(w.getBlockMaterial(x, y+1, z)==Material.air){
    		w.setBlockAndMetadataWithNotify(x, y+1, z, ConfigHolder.timeExplosivesID, meta);
    	}
    	if(w.getBlockMaterial(x, y-1, z)==Material.air){
    		w.setBlockAndMetadataWithNotify(x, y-1, z, ConfigHolder.timeExplosivesID, meta);
    	}
    	if(w.getBlockMaterial(x, y, z+1)==Material.air){
    		w.setBlockAndMetadataWithNotify(x, y, z+1, ConfigHolder.timeExplosivesID, meta);
    	}
    	if(w.getBlockMaterial(x, y, z-1)==Material.air){
    		w.setBlockAndMetadataWithNotify(x, y, z-1, ConfigHolder.timeExplosivesID, meta);
    	}
    }
    	
    private void fork(World w, int x, int y, int z){
    	fork(w,x,y,z,w.getBlockMetadata(x, y, z));
    }


}
