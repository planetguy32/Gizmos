package me.planetguy.gizmos.timebomb;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import me.planetguy.core.sl.SLItemBlock;
import me.planetguy.core.sl.SLLoad;
import me.planetguy.core.sl.SLProp;
import me.planetguy.gizmos.Gizmos;
import me.planetguy.util.Debug;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
/**
 * Block class for the time bomb and fork bomb
 * @author bill
 *
 */
@SLLoad(name="timeBombs")
public class BlockTimeBomb extends Block{

	private IIcon topTex;
	private IIcon bottomTex;
	private IIcon[] sideIIcons=new IIcon[16];
	
	@SLProp(name = "timeBombFuse")
	public static int fuse=60;
	
	public static boolean allowForkBomb=true;

	@SLLoad
	public BlockTimeBomb() {
		super(Material.tnt);
		Debug.dbg("Loading time bomb...");
        //this.setTickRandomly(true);
        this.setCreativeTab(CreativeTabs.tabRedstone);
        fuse*=5/2;//Simplified 20/8: 20 ticks/sec, 8 updates to explode
		Gizmos.timeBombs=this;
		//Item.itemsList[ this.blockID] = new ItemTimeBomb( this.blockID-256).setItemName("timeBombs");
		/*
		final String[] oreNames = {"Time bomb", "Fork bomb", "?", "?", "?", "?", "?", "?", "?", "?", "?", "?", "?", "?", "Fork bomb", "Time bomb"};
		for (int re = 0; re < 16; re++){
			ItemStack oreStack = new ItemStack(Gizmos.timeBomb, 1, re);
			LanguageRegistry.addName(oreStack, oreNames[re]);
		}*/	
		SLItemBlock.registerString(this, 0, "Time bomb", new String[]{"Counts down to destruction"});
		SLItemBlock.registerString(this, 1, "Fork bomb", new String[]{"Counts down to destruction,","duplicates if disturbed"});
		ItemStack itemStackTB=new ItemStack(Gizmos.timeBombs,1,0); 
		ItemStack itemStackFB=new ItemStack(Gizmos.timeBombs,1,1);
		ItemStack endStone=new ItemStack(Blocks.end_stone);
		
		GameRegistry.addShapelessRecipe(itemStackTB, Blocks.tnt, Items.clock);
		
		GameRegistry.addRecipe(itemStackFB, new Object[]{
				"EEE","ETE","EEE", 
				Character.valueOf('T'),itemStackTB,
				Character.valueOf('E'),endStone});
	} 
	
	@SLLoad
	public static void doStuff(){
		final String[] oreNames = {"Time bomb", "Fork bomb", "?", "?", "?", "?", "?", "?", "?", "?", "?", "?", "?", "?", "Fork bomb", "Time bomb"};
		for (int re = 0; re < 16; re++){
			ItemStack oreStack = new ItemStack(Gizmos.timeBombs, 1, re);
			LanguageRegistry.addName(oreStack, oreNames[re]);
		}
	}
	
	public int tickRate(){
		return fuse; 
	}
	
	public void registerIcons(IIconRegister ir){
		Debug.dbg("Time bomb textures loading");
		topTex=ir.registerIcon(Gizmos.modName+":"+"bombTop");
		bottomTex=ir.registerIcon(Gizmos.modName+":"+"bombBottom");
		sideIIcons[0] =ir.registerIcon(Gizmos.modName+":"+"timeBomb1");
		sideIIcons[1] =sideIIcons[0];
		sideIIcons[2] =ir.registerIcon(Gizmos.modName+":"+"timeBomb2");
		sideIIcons[3] =sideIIcons[2];
		sideIIcons[4] =ir.registerIcon(Gizmos.modName+":"+"timeBomb3");
		sideIIcons[5] =sideIIcons[4];
		sideIIcons[6] =ir.registerIcon(Gizmos.modName+":"+"timeBomb4");
		sideIIcons[7] =sideIIcons[6];
		sideIIcons[8] =ir.registerIcon(Gizmos.modName+":"+"timeBomb5");
		sideIIcons[9] =sideIIcons[8];
		sideIIcons[10]=ir.registerIcon(Gizmos.modName+":"+"timeBomb6");
		sideIIcons[11]=sideIIcons[10];
		sideIIcons[12]=ir.registerIcon(Gizmos.modName+":"+"timeBomb7");
		sideIIcons[13]=sideIIcons[12];
		sideIIcons[14]=ir.registerIcon(Gizmos.modName+":"+"timeBomb8");
		sideIIcons[15]=sideIIcons[14];
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
