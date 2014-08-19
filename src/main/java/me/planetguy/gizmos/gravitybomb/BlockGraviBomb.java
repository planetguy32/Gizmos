package me.planetguy.gizmos.gravitybomb;

import java.util.List;
import java.util.Random;

import me.planetguy.core.sl.SLItemBlock;
import me.planetguy.core.sl.SLLoad;
import me.planetguy.gizmos.Gizmos;
import me.planetguy.util.Debug;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


@SLLoad(name="GravityBomb",dependencies={"entityGravityBomb","entityTunnelBomb",})
public class BlockGraviBomb extends Block
{
	private int id;
	private IIcon topTex;
	private IIcon gBombTex;
	private IIcon tBombTex;
	private IIcon bottomTex;

	@SLLoad
	public BlockGraviBomb(int id){
		super(Material.tnt);
		LanguageRegistry.addName(this, "Gravity Bomb");
		this.id=id;
		SLItemBlock.registerString(this, 0, "Gravity Bomb", new String[] {"Falls as far as it can,","explodes where it lands"});
		SLItemBlock.registerString(this, 1, "Excavator Bomb", new String[] {"Falls for a set time,","explodes each time it lands"});
		try{
			setCreativeTab(CreativeTabs.tabRedstone);
			Gizmos.graviBombPrimed = new EntityGravityBomb(null);
			Gizmos.tunnelBombPrimed=new EntityTunnelBomb(null);
			//EntityRegistry.registerModEntity(EntityTunnelBeam.class, "Tunnel Beam", 199, this, 80, 3, true);
			EntityRegistry.registerModEntity(EntityGravityBomb.class, "GBomb", 201, Gizmos.instance, 80, 3, true);
			EntityRegistry.registerModEntity(EntityTunnelBomb.class, "TBomb", 202, Gizmos.instance, 80, 3, true);
			ItemStack itemStackGB = new ItemStack(this, 3, 0);
			ItemStack itemStackExcaBomb = new ItemStack(this, 1, 1);
			ItemStack tnt = new ItemStack(Blocks.tnt);
			ItemStack powder = new ItemStack(Items.blaze_powder);
			ItemStack iron = new ItemStack(Items.iron_ingot);
			ItemStack itemStackPick = new ItemStack(Items.iron_pickaxe);
			GameRegistry.addRecipe(itemStackGB, new Object[] { "xxx", "iyi", " i ", 
					Character.valueOf('x'),tnt, 
					Character.valueOf('y'), powder,
					Character.valueOf('i'), iron });
			GameRegistry.addRecipe(itemStackExcaBomb, new Object[] { " b ", "ibi", "pbp", 
					Character.valueOf('b'), itemStackGB, 
					Character.valueOf('i'), iron, 
					Character.valueOf('p'), itemStackPick });
		}catch(Exception e){
			e.printStackTrace();
		}
		//this.setRequiresSelfNotify();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister ir){
		Debug.dbg("GraviBomb textures loading");
		topTex=ir.registerIcon(Gizmos.modName+":"+"bombTop");
		gBombTex=ir.registerIcon(Gizmos.modName+":"+"" +"gravityBomb");
		tBombTex=ir.registerIcon(Gizmos.modName+":"+"excavatorBomb");
		bottomTex=ir.registerIcon(Gizmos.modName+":"+"bombBottom");
	}

	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
	{	
		for (int var4 = 0; var4 < 2; ++var4)
		{
			par3List.add(new ItemStack(this, 1, var4));
		}
	}

	/*
	public int idDropped(int par1, Random par2Random, int par3){
		return Gizmos.loader.IDMap.get("gravityBomb");
	}
	 */

	public String getTextureFile(){
		return "/planetguy/Gizmos/tex.png";
	}

	public int tickRate()
	{
		return 3;
	}

	public IIcon getIcon(int par1, int par2) {
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




	//Explosion code follows

	public void onBlockAdded(World par1World, int x, int par3, int par4)
	{
		par1World.scheduleBlockUpdate(x, par3, par4, this, tickRate());
	}

	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5) {
		par1World.scheduleBlockUpdate(par2, par3, par4, this, tickRate());
	}

	public static boolean canFallBelow(World par0World, int par1, int par2, int par3)
	{
		Block var4 = par0World.getBlock(par1, par2, par3);

		if (var4.isAir(par0World, par1, par2, par3))
		{
			return true;
		}
		if (var4 == Blocks.fire)
		{
			return true;
		}

		Material var5 = var4.getMaterial();
		return var5 == Material.water;
	}

	public void updateTick(World par1World, int par2, int par3, int par4, Random randomThingy)
	{
		int metadata=par1World.getBlockMetadata(par2, par3, par4);
		// FMLLog.log(Level.SEVERE, "My metadata: "+metadata, "");


		if ((canFallBelow(par1World, par2, par3 - 1, par4)) && (par3 >= 0))
		{
			byte var8 = 32;

			if (par1World.checkChunksExist(par2 - var8, par3 - var8, par4 - var8, par2 + var8, par3 + var8, par4 + var8))
			{
				if (!par1World.isRemote)
				{
					par1World.setBlockToAir(par2, par3, par4);
					switch(metadata){
					case 0:{
						//FMLLog.log(Level.SEVERE, "A gravity bomb!","");
						EntityGravityBomb bomb = new EntityGravityBomb(par1World, 
								par2 + 0.5F, 
								par3 + 0.5F, 
								par4 + 0.5F);
						par1World.spawnEntityInWorld(bomb);
						break;}
					case 1:{
						// FMLLog.log(Level.SEVERE, "A tunnel bomb!","");
						EntityTunnelBomb bomb = new EntityTunnelBomb(par1World, 
								par2 + 0.5F, 
								par3 + 0.5F, 
								par4 + 0.5F, 300);
						par1World.spawnEntityInWorld(bomb);
						break;}
					}

				}
			}
		}
	}
}


