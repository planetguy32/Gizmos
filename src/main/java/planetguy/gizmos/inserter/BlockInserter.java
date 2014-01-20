package planetguy.gizmos.inserter;

import java.util.Random;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import planetguy.gizmos.Gizmos;
import planetguy.simpleLoader.SLItemBlock;
import planetguy.simpleLoader.SLLoad;
import planetguy.simpleLoader.SLProp;
import planetguy.simpleLoader.SimpleLoader;
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
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

@SLLoad(name="inserter",primacy=7)
public class BlockInserter extends Block{
	
	@SLProp(name="doBlockDamage")
	public static boolean doBlockDamage=true;
	
	@SLProp(name="limitQuantityHideable")
	public static boolean nerfHiding=false;
	
	public IIcon sides;
	public IIcon top;

	@SLLoad
	public BlockInserter(int id){
		this(id, 0);
		try {
			//Gizmos.loader.loadClass(ItemLens.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.func_149663_c("spyLab");
		ItemStack lens=new ItemStack(Gizmos.Lens);
		ItemStack wood=new ItemStack((Block)Block.field_149771_c.getObject("planks);
		ItemStack blockIron=new ItemStack((Block)Block.field_149771_c.getObject("blocksList[42]);
		ItemStack crafter=new ItemStack((Block)Block.field_149771_c.getObject("workbench);
		ItemStack chest=new ItemStack((Block)Block.field_149771_c.getObject("chest);
		this.func_149663_c("inserter");
		LanguageRegistry.instance().addNameForObject(this, "en_US", "Inserter");
		ItemStack itemSpyDesk=new ItemStack(this);
        GameRegistry.addRecipe(itemSpyDesk, new Object[] {"LWC", "III","B B",
        		Character.valueOf('L'),lens,
        		Character.valueOf('W'),crafter,
        		Character.valueOf('C'),chest,
        		Character.valueOf('I'),blockIron,
        		Character.valueOf('B'),wood});
        SLItemBlock.registerString(id, 0, "Inserter", new String[]{"Hides items in other items,","or retrieves hidden items."});
	}
	
    public BlockInserter (int id, int texture) {
        super(id, Material.iron);
        //Debug.dbg("New spy lab!");
        setHardness(2.0F);
        setResistance(5.0F);
        func_149663_c("blockSpyLab");
        func_149647_a(CreativeTabs.tabTools);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister ir){
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
