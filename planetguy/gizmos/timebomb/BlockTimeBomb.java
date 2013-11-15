package planetguy.gizmos.timebomb;

import java.util.List;
import java.util.Random;


import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
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
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import planetguy.gizmos.Gizmos;
import planetguy.simpleLoader.SLItemBlock;
import planetguy.simpleLoader.SLLoad;
import planetguy.simpleLoader.SLProp;
/**
 * Block class for the time bomb and fork bomb
 * @author bill
 *
 */
@SLLoad(name="timeBombs",primacy=13)
public class BlockTimeBomb extends Block{

	private Icon topTex;
	private Icon bottomTex;
	private Icon[] sideIcons=new Icon[16];
	
	@SLProp(name = "timeBombFuse")
	public static int fuse=60;
	
	public static boolean allowForkBomb=true;

	@SLLoad
	public BlockTimeBomb(int id) {
		super(id, Material.tnt);
		System.out.println("Loading time bomb...");
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
		SLItemBlock.registerString(id, 0, "Time bomb", new String[]{"Counts down to destruction"});
		SLItemBlock.registerString(id, 1, "Fork bomb", new String[]{"Counts down to destruction,","duplicates if disturbed"});
		ItemStack itemStackTB=new ItemStack(Gizmos.timeBombs,1,0); 
		ItemStack itemStackFB=new ItemStack(Gizmos.timeBombs,1,1);
		ItemStack endStone=new ItemStack(Block.whiteStone);
		
		GameRegistry.addShapelessRecipe(itemStackTB, Block.tnt, Item.pocketSundial);
		
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
	
	public void registerIcons(IconRegister ir){
		System.out.println("Time bomb textures loading");
		topTex=ir.registerIcon(Gizmos.modName+":"+"bombTop");
		bottomTex=ir.registerIcon(Gizmos.modName+":"+"bombBottom");
		sideIcons[0] =ir.registerIcon(Gizmos.modName+":"+"timeBomb1");
		sideIcons[1] =sideIcons[0];
		sideIcons[2] =ir.registerIcon(Gizmos.modName+":"+"timeBomb2");
		sideIcons[3] =sideIcons[2];
		sideIcons[4] =ir.registerIcon(Gizmos.modName+":"+"timeBomb3");
		sideIcons[5] =sideIcons[4];
		sideIcons[6] =ir.registerIcon(Gizmos.modName+":"+"timeBomb4");
		sideIcons[7] =sideIcons[6];
		sideIcons[8] =ir.registerIcon(Gizmos.modName+":"+"timeBomb5");
		sideIcons[9] =sideIcons[8];
		sideIcons[10]=ir.registerIcon(Gizmos.modName+":"+"timeBomb6");
		sideIcons[11]=sideIcons[10];
		sideIcons[12]=ir.registerIcon(Gizmos.modName+":"+"timeBomb7");
		sideIcons[13]=sideIcons[12];
		sideIcons[14]=ir.registerIcon(Gizmos.modName+":"+"timeBomb8");
		sideIcons[15]=sideIcons[14];
		this.blockIcon=sideIcons[0];
	}
	
	
	
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int meta){
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
	
	@Override
    public void onBlockDestroyedByPlayer(World w, int x, int y, int z, int meta) {
    	fork(w,x,y,z,meta);
    	if(meta%2==0){
    		w.destroyBlock(x, y, z, true);
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
    	if(w.getBlockMaterial(x+1, y, z)==Material.air){
    		w.setBlock(x+1, y, z, this.blockID, meta, 0x02);
    	}
    	if(w.getBlockMaterial(x-1, y, z)==Material.air){
    		w.setBlock(x-1, y, z, this.blockID, meta, 0x02);
    	}
    	if(w.getBlockMaterial(x, y+1, z)==Material.air){
    		w.setBlock(x, y+1, z, this.blockID, meta, 0x02);
    	}
    	if(w.getBlockMaterial(x, y-1, z)==Material.air){
    		w.setBlock(x, y-1, z, this.blockID, meta, 0x02);
    	}
    	if(w.getBlockMaterial(x, y, z+1)==Material.air){
    		w.setBlock(x, y, z+1, this.blockID, meta, 0x02);
    	}
    	if(w.getBlockMaterial(x, y, z-1)==Material.air){
    		w.setBlock(x, y, z-1, this.blockID, meta, 0x02);
    	}
    }
    	
    private void fork(World w, int x, int y, int z){
    	fork(w,x,y,z,w.getBlockMetadata(x, y, z));
    }


}
