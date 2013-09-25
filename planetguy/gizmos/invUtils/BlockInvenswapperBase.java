package planetguy.gizmos.invUtils;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import planetguy.gizmos.Gizmos;
import planetguy.simpleLoader.SLItemBlock;
import planetguy.simpleLoader.SLLoad;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

@SLLoad(name="invenswapper",dependencies={"invenswapperTop"})
public class BlockInvenswapperBase extends BlockContainer{
	
	private Icon[] icons=new Icon[3];

	@SLLoad
	public BlockInvenswapperBase(int par1) {
		super(par1, Material.iron);
		setHardness(2.0F);
		setResistance(5.0F);
		setUnlocalizedName("Gizmos_Invenswapper");
		setCreativeTab(CreativeTabs.tabDecorations);
		LanguageRegistry.addName(this, "Invenswapper base");
		SLItemBlock.registerString(par1, 0, "Invenswapper (in)", new String[] {"Sneak while on top of it to","put items into it."});
		SLItemBlock.registerString(par1, 1, "Invenswapper (out)", new String[] {"Sneak while on top of it to","take all the items inside it."});
		GameRegistry.registerTileEntity(TileEntityInvenswapper.class, "Gizmos.invenswapper");
	}
	
	@SLLoad
	public void addCrafting(){
		GameRegistry.addRecipe(new ItemStack(this,1,0), new Object[]{
			"php","lsl","lcl",
			Character.valueOf('h'),new ItemStack(Block.hopperBlock),
			Character.valueOf('p'),new ItemStack(Block.pressurePlateIron),
			Character.valueOf('l'),new ItemStack(Block.blockLapis),
			Character.valueOf('s'),new ItemStack(Block.pistonBase),
			Character.valueOf('c'),new ItemStack(Block.chest)
		});
		GameRegistry.addRecipe(new ItemStack(this,1,1), new Object[]{
			"pcp","lsl","lhl",
			Character.valueOf('h'),new ItemStack(Block.hopperBlock),
			Character.valueOf('p'),new ItemStack(Block.pressurePlateIron),
			Character.valueOf('l'),new ItemStack(Block.blockLapis),
			Character.valueOf('s'),new ItemStack(Block.pistonBase),
			Character.valueOf('c'),new ItemStack(Block.chest)
		});
		GameRegistry.addShapelessRecipe(new ItemStack(this,1,0), new ItemStack(this,1,1));
		GameRegistry.addShapelessRecipe(new ItemStack(this,1,1), new ItemStack(this,1,0));
	}
	
	@Override
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
	{
	    for (int var4 = 0; var4 < 2; ++var4)
	    {
	        par3List.add(new ItemStack(par1, 1, var4));
	    }
	}

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister ir){
    	icons[0]=ir.registerIcon(Gizmos.modName+":invenswapperBottomIn");
    	icons[1]=ir.registerIcon(Gizmos.modName+":invenswapperBottomOut");
    	icons[2]=ir.registerIcon("hopper_inside");
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int meta){
		if(side==0||side==1)return icons[2];
		return icons[meta];    	
    }
    
    @Override
	public void onBlockAdded(World w, int x, int y, int z){
    	if(w.getBlockMaterial(x, y+1, z)==Material.air){
    		w.setBlock(x, y+1, z, BlockInvenswapperTop.id,w.getBlockMetadata(x, y, z), 0x02);
    	}
    }
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int idk, float what, float these, float are) {
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		if (tileEntity == null || player.isSneaking()) {
			return false;
		}
		//code to open gui explained later
		player.openGui(Gizmos.instance, 1, world, x, y, z);
		return true;
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, int par5, int par6) {
		dropItems(world, x, y, z);
		world.setBlockToAir(x,y+1,z);
		super.breakBlock(world, x, y, z, par5, par6);
	}

	private void dropItems(World world, int x, int y, int z){
		Random rand = new Random();

		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		if (!(tileEntity instanceof IInventory)) {
			return;
		}
		IInventory inventory = (IInventory) tileEntity;

		for (int i = 0; i < inventory.getSizeInventory(); i++) {
			ItemStack item = inventory.getStackInSlot(i);

			if (item != null && item.stackSize > 0) {
				float rx = rand.nextFloat() * 0.8F + 0.1F;
				float ry = rand.nextFloat() * 0.8F + 0.1F;
				float rz = rand.nextFloat() * 0.8F + 0.1F;

				EntityItem entityItem = new EntityItem(world,
						x + rx, y + ry, z + rz,
						new ItemStack(item.itemID, item.stackSize, item.getItemDamage()));

				float factor = 0.05F;
				entityItem.motionX = rand.nextGaussian() * factor;
				entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
				entityItem.motionZ = rand.nextGaussian() * factor;
				world.spawnEntityInWorld(entityItem);
				item.stackSize = 0;
			}
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityInvenswapper();
	}
}