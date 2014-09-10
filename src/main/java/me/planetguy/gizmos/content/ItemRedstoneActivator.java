package me.planetguy.gizmos.content;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRedstoneOre;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemRedstoneActivator extends ItemInteractDevice{

	private Block rsWand=new BlockRedstoneWand();
	
	public ItemRedstoneActivator() {
		super("redstoneActivator");
		GameRegistry.registerBlock(rsWand, "redstoneActivatorHelperBlock");
	}

	@Override
	public boolean doEffect(int x, int y, int z, World w,
			ItemStack me, EntityPlayer thePlayer) {
		w.setBlock(x,y,z, rsWand);
		return false;
	}
	
	public int countTooltipLines(){
		return 2;
	}

	@Override
	public boolean canDoEffect(int x, int y, int z, World w,
			ItemStack me, EntityPlayer thePlayer) {
		return w.isAirBlock(x, y, z);
	}
	
	public void loadCrafting(){
		GameRegistry.addShapedRecipe(new ItemStack(this), new Object[]{
			" r",
			"s ",
			Character.valueOf('r'),new ItemStack(Blocks.redstone_torch),
			Character.valueOf('s'),new ItemStack(Items.stick),});
	}
	
	public class BlockRedstoneWand extends BlockRedstoneOre{

		public BlockRedstoneWand() {
			super(true);	
		}
		
		public void onBlockAdded(World w, int x, int y, int z){
			w.scheduleBlockUpdate(x, y, z, this, 20);
		}

		//On tick, vanish
		public void updateTick(World w, int x, int y, int z, Random par5Random){
			w.setBlockToAir(x, y, z);
		}
		
		//Always power
	    public int isProvidingWeakPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5){
	        return 15;
	    }
	    
	    //Yes indeed, power other blocks
	    public boolean canProvidePower(){
	    	return true;
	    }
	    
	    //Vanish if r-clicked
		public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer player, int idk, float what, float these, float are)  {
			w.setBlockToAir(x,y,z);
			return true;
		}
		
		public void registerIcons(IIconRegister ir){
			this.blockIcon=Blocks.wool.getIcon(0, 14); //Snarf the icon from red wool.
		}
	}

}
