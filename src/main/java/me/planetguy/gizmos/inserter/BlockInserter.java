package me.planetguy.gizmos.inserter;

import java.util.Random;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import me.planetguy.core.sl.SLItemBlock;
import me.planetguy.core.sl.SLLoad;
import me.planetguy.core.sl.SLProp;
import me.planetguy.core.sl.SimpleLoader;
import me.planetguy.gizmos.Gizmos;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

@SLLoad(name="inserter",dependencies={"Lens"})
public class BlockInserter extends Block{
	
	@SLProp(name="doBlockDamage")
	public static boolean doBlockDamage=true;
	
	@SLProp(name="limitQuantityHideable")
	public static boolean nerfHiding=false;
	
	public IIcon sides;
	public IIcon top;

	@SLLoad
	public BlockInserter(){
		super(Material.iron);
		try {
			//Gizmos.loader.loadClass(ItemLens.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ItemStack lens=new ItemStack(Gizmos.Lens);
		ItemStack wood=new ItemStack(Blocks.planks);
		ItemStack blockIron=new ItemStack(Blocks.iron_block);
		ItemStack crafter=new ItemStack(Blocks.crafting_table);
		ItemStack chest=new ItemStack(Blocks.chest);
		LanguageRegistry.instance().addNameForObject(this, "en_US", "Inserter");
		ItemStack itemSpyDesk=new ItemStack(this);
        GameRegistry.addRecipe(itemSpyDesk, new Object[] {"LWC", "III","B B",
        		Character.valueOf('L'),lens,
        		Character.valueOf('W'),crafter,
        		Character.valueOf('C'),chest,
        		Character.valueOf('I'),blockIron,
        		Character.valueOf('B'),wood});
        SLItemBlock.registerString(this, 0, "Inserter", new String[]{"Hides items in other items,","or retrieves hidden items."});
	}

	
    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister ir){
    	//Debug.dbg("Spy lab textures loading");
    	top=ir.registerIcon(Gizmos.modName+":spyTop");
    	sides=ir.registerIcon(Gizmos.modName+":spySides");
    }
    
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int idk, float what, float these, float are) {
        //code to open gui explained later
        player.openGui(Gizmos.instance, 0, world, x, y, z);
        return true;
    }

	public void breakBlock(World world, int x, int y, int z, int par5, int par6) {
        //dropItems(world, x, y, z);
	}
	
	public IIcon getIcon(int par1, int par2) {
		if(par1==1){
			return top;
		}else{
			return sides;
		}
	}

}
