package me.planetguy.gizmos.content;

import java.util.List;
import java.util.Random;

import me.planetguy.gizmos.Gizmos;
import me.planetguy.gizmos.Properties;
import me.planetguy.gizmos.base.BlockBase;
import me.planetguy.remaininmotion.util.general.Debug;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
/**
 * Block class for the time bomb and fork bomb
 * @author bill
 *
 */
public class BlockTimeBomb extends BlockBase{

	private IIcon topTex;
	private IIcon bottomTex;
	private IIcon[] sideIIcons=new IIcon[16];
	
	public static boolean allowForkBomb=true;

	public BlockTimeBomb() {
		super(Material.tnt, "timeBomb");
        //this.setTickRandomly(true);
        //fuse*=5/2;//Simplified 20/8: 20 ticks/sec, 8 updates to explode
		
	} 
	
	public int countTooltipLines(){
		return 2;
	}
	
	public void loadCrafting(){
		ItemStack itemStackTB=new ItemStack(this,1,0); 
		ItemStack itemStackFB=new ItemStack(this,1,1);
		ItemStack endStone=new ItemStack(Blocks.end_stone);
		
		GameRegistry.addShapelessRecipe(itemStackTB, Blocks.tnt, Items.clock);
		
		GameRegistry.addRecipe(itemStackFB, new Object[]{
				"EEE","ETE","EEE", 
				Character.valueOf('T'),itemStackTB,
				Character.valueOf('E'),endStone});
	}
	
	public int tickRate(){
		return Properties.timeBombFuse; 
	}
	
	public void registerBlockIcons(IIconRegister ir){
		topTex=ir.registerIcon(Properties.modID+":"+"bombTop");
		bottomTex=ir.registerIcon(Properties.modID+":"+"bombBottom");
		for(int i=0; i<8; i++){
			IIcon ico=ir.registerIcon(Properties.modID+":timeBomb"+(i+1));
			sideIIcons[2*i]=ico;
			sideIIcons[2*i+1]=ico;
		}
		this.blockIcon=sideIIcons[0];
	}
	
	
	
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta){
		if(side==0||side==1){
			return topTex;
		}
        return sideIIcons[meta];
    }
  
    @Override
    public void onBlockAdded(World par1World, int par2, int par3, int par4){
    	par1World.scheduleBlockUpdate(par2, par3, par4, this, this.tickRate());
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void getSubBlocks(Item id,CreativeTabs par2CreativeTabs, List items){
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
    		w.scheduleBlockUpdate(x,y,z,this, this.tickRate());
    	}    	
	}    
	
	@Override
    public void onBlockDestroyedByPlayer(World w, int x, int y, int z, int meta) {
    	fork(w,x,y,z,meta);
    	if(meta%2==0){
    		w.setBlockToAir(x, y, z);
    	}
    }

    
	@Override
    public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9){
    	fork(w,x,y,z);
    	return true;
    }
    
    public void onBlockDestroyedByExplosion(World par1World, int par2, int par3, int par4, Explosion par5Explosion)
    {
        if (!par1World.isRemote)
        {
            EntityTNTPrimed entitytntprimed = new EntityTNTPrimed(par1World, (double)((float)par2 + 0.5F), (double)((float)par3 + 0.5F), (double)((float)par4 + 0.5F), par5Explosion.getExplosivePlacedBy());
            entitytntprimed.fuse = par1World.rand.nextInt(entitytntprimed.fuse / 4) + entitytntprimed.fuse / 8;
            par1World.spawnEntityInWorld(entitytntprimed);
        }
    }

    /* The fork method for the fork bomb variant of the time bomb. Copies the bomb to all available spaces 
     * around it.
     */
    
    private void fork(World w, int x, int y, int z, int meta){
    	if(meta%2!=1){
    		return;
    	}
    	if(w.getBlock(x+1, y, z).getMaterial()==Material.air){
    		w.setBlock(x+1, y, z, this, meta, 0x02);
    	}
    	if(w.getBlock(x-1, y, z).getMaterial()==Material.air){
    		w.setBlock(x-1, y, z, this, meta, 0x02);
    	}
    	if(w.getBlock(x, y+1, z).getMaterial()==Material.air){
    		w.setBlock(x, y+1, z, this, meta, 0x02);
    	}
    	if(w.getBlock(x, y-1, z).getMaterial()==Material.air){
    		w.setBlock(x, y-1, z, this, meta, 0x02);
    	}
    	if(w.getBlock(x, y, z+1).getMaterial()==Material.air){
    		w.setBlock(x, y, z+1, this, meta, 0x02);
    	}
    	if(w.getBlock(x, y, z-1).getMaterial()==Material.air){
    		w.setBlock(x, y, z-1, this, meta, 0x02);
    	}
    }
    	
    private void fork(World w, int x, int y, int z){
    	fork(w,x,y,z,w.getBlockMetadata(x, y, z));
    }


}
