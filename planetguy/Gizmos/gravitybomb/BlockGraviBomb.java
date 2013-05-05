/*    */ package planetguy.Gizmos.gravitybomb;
/*    */ 
/*    */ import java.util.List;
import java.util.Random;
import java.util.logging.Level;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;

import planetguy.Gizmos.ConfigHolder;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;


import net.minecraftforge.common.ForgeDirection;
import static net.minecraftforge.common.ForgeDirection.*;
/*    */ 

/*    */ public class BlockGraviBomb extends Block
/*    */ {
	private int metadata;
	private Icon topTex;
	private Icon gBombTex;
	private Icon tBombTex;
	private Icon bottomTex;
	
/*    */   public BlockGraviBomb(int id)
/*    */   {
/* 21 */     super(id,  Material.tnt);
/* 22 */     setCreativeTab(CreativeTabs.tabRedstone);
			 //this.setRequiresSelfNotify();
/*    */   }

@Override
@SideOnly(Side.CLIENT)
public void registerIcons(IconRegister ir){
	System.out.println("GraviBomb textures loading");
	topTex=ir.registerIcon(ConfigHolder.modName+":"+"bombTop");
	gBombTex=ir.registerIcon(ConfigHolder.modName+":"+"" +"gravityBomb");
	tBombTex=ir.registerIcon(ConfigHolder.modName+":"+"excavatorBomb");
	bottomTex=ir.registerIcon(ConfigHolder.modName+":"+"bombBottom");
}

@SideOnly(Side.CLIENT)
public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
{
    for (int var4 = 0; var4 < 2; ++var4)
    {
        par3List.add(new ItemStack(par1, 1, var4));
    }
}

public int idDropped(int par1, Random par2Random, int par3){
		return ConfigHolder.gravityExplosivesID;
}

/*    */   public String getTextureFile(){
			  return "/planetguy/Gizmos/tex.png";
			}

/*    */   public int tickRate()
/*    */   {
/* 31 */     return 3;
/*    */   }

	public Icon getIcon(int par1, int par2) {
		if(par1==0){
			return bottomTex;
		}else if(par1==1){
			return topTex;
		}else if(par2==0){
			return gBombTex;
		}else{
			return tBombTex;
		}
	}


/*    */ 

		//Explosion code follows

/*    */   public void onBlockAdded(World par1World, int x, int par3, int par4)
/*    */   {
/* 37 */     par1World.scheduleBlockUpdate(x, par3, par4, this.blockID, tickRate());
/*    */   }
/*    */ 
/*    */   public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5) {
/* 41 */     par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, tickRate());
/*    */   }
/*    */ 
/*    */   public static boolean canFallBelow(World par0World, int par1, int par2, int par3)
/*    */   {
/* 47 */     int var4 = par0World.getBlockId(par1, par2, par3);
/*    */ 
/* 49 */     if (var4 == 0)
/*    */     {
/* 51 */       return true;
/*    */     }
/* 53 */     if (var4 == Block.fire.blockID)
/*    */     {
/* 55 */       return true;
/*    */     }
/*    */ 
/* 59 */     Material var5 = Block.blocksList[var4].blockMaterial;
/* 60 */     return var5 == Material.water;
/*    */   }
/*    */ 
/*    */   public void updateTick(World par1World, int par2, int par3, int par4, Random randomThingy)
/*    */   {
	metadata=par1World.getBlockMetadata(par2, par3, par4);
	// FMLLog.log(Level.SEVERE, "My metadata: "+metadata, "");

	
/* 69 */     if ((canFallBelow(par1World, par2, par3 - 1, par4)) && (par3 >= 0))
/*    */     {
/* 71 */       byte var8 = 32;
/*    */ 
/* 73 */       if (par1World.checkChunksExist(par2 - var8, par3 - var8, par4 - var8, par2 + var8, par3 + var8, par4 + var8))
/*    */       {
/* 75 */         if (!par1World.isRemote)
/*    */         {
/* 77 */           par1World.setBlock(par2, par3, par4, 0);
				   switch(metadata){
				   case 0:{
					   //FMLLog.log(Level.SEVERE, "A gravity bomb!","");
					   EntityGravityBomb bomb = new EntityGravityBomb(par1World, 
						   /* 79 */             par2 + 0.5F, 
						   /* 80 */             par3 + 0.5F, 
						   /* 81 */             par4 + 0.5F);
						   /* 82 */           par1World.spawnEntityInWorld(bomb);
						   break;}
				   case 1:{
					  // FMLLog.log(Level.SEVERE, "A tunnel bomb!","");
					   EntityTunnelBomb bomb = new EntityTunnelBomb(par1World, 
						   /* 79 */             par2 + 0.5F, 
						   /* 80 */             par3 + 0.5F, 
						   /* 81 */             par4 + 0.5F, 300);
						   /* 82 */           par1World.spawnEntityInWorld(bomb);
						   break;}
				   }
/* 78 */           
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\mcp\Mod backups\
 * Qualified Name:     planetguy.EvilToys.BlockGraviBomb
 * JD-Core Version:    0.6.2
 */