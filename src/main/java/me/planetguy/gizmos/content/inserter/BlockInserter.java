package me.planetguy.gizmos.content.inserter;

import me.planetguy.gizmos.Gizmos;
import me.planetguy.gizmos.Properties;
import me.planetguy.gizmos.base.BlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockInserter extends BlockBase{
	
	public IIcon sides;
	public IIcon top;

	public BlockInserter(){
		super(Material.iron, "inserter");

        //SLItemBlock.registerString(this, 0, "Inserter", new String[]{"Hides items in other items,","or retrieves hidden items."});
	}
	
	public void loadCrafting(){
		ItemStack wood=new ItemStack(Blocks.planks);
		ItemStack blockIron=new ItemStack(Blocks.iron_block);
		ItemStack crafter=new ItemStack(Blocks.crafting_table);
		ItemStack chest=new ItemStack(Blocks.chest);
		ItemStack itemSpyDesk=new ItemStack(this);
        GameRegistry.addRecipe(itemSpyDesk, new Object[] {"W C", "III","B B",
        		Character.valueOf('W'),crafter,
        		Character.valueOf('C'),chest,
        		Character.valueOf('I'),blockIron,
        		Character.valueOf('B'),wood});
	}

	
    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister ir){
    	//Debug.dbg("Spy lab textures loading");
    	top=ir.registerIcon(Properties.modID+":spyTop");
    	sides=ir.registerIcon(Properties.modID+":spySides");
    }
    
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int idk, float what, float these, float are) {
        player.openGui(Gizmos.instance, 0, world, x, y, z);
        return true;
    }

	public IIcon getIcon(int par1, int par2) {
		if(par1==1){
			return top;
		}else{
			return sides;
		}
	}

}
