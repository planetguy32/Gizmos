package planetguy.gizmos.tool;

import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import planetguy.gizmos.Gizmos;
import planetguy.simpleLoader.SLLoad;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

@SLLoad(name="deforestator",dependencies={"forestFire"},primacy=4)
public class ItemDeforester extends ItemInteractDevice{

	public ItemDeforester(int par1, boolean ignored){
		super(par1);
	}
	
	@SLLoad
	public ItemDeforester(int par1) {
		super(par1);
		this.setUnlocalizedName("deforester");
		LanguageRegistry.instance().addNameForObject(this, "en_US", "Deforester");
		GameRegistry.addShapedRecipe(new ItemStack(this), new Object[]{
			"gbg",
			"blb",
			"gbg",
			Character.valueOf('g'),new ItemStack(Block.gravel),
			Character.valueOf('b'),new ItemStack(Item.blazePowder),
			Character.valueOf('l'),new ItemStack(Item.flintAndSteel)});
	}
	
	public int id(){
		return Gizmos.forestFire.blockID;
	}

	@Override
	public boolean doEffect(int posX, int posY, int posZ, World theWorld, ItemStack me, EntityPlayer thePlayer) {
		int id = theWorld.getBlockId(posX, posY, posZ);

		if (id == 0)
		{
			theWorld.playSoundEffect((double)posX + 0.5D, (double)posY + 0.5D, (double)posZ + 0.5D, "fire.ignite", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
			theWorld.setBlock(posX, posY, posZ, Gizmos.forestFire.blockID);
			me.damageItem(1, thePlayer);
			return true;
		}
		return false;
	}

	@Override
	public boolean canDoEffect(int posX, int posY, int posZ, World theWorld, ItemStack me, EntityPlayer thePlayer) {
		return true;
	}
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer player, List tooltipLines, boolean advancedTooltipsActive){
		tooltipLines.add("Very quickly removes trees.");
		if(player.worldObj.getBiomeGenForCoords((int)player.posX, (int)player.posZ)==BiomeGenBase.jungle){
			tooltipLines.add("§4§LUse in jungle is a bad idea.");
		}
	}
	

	public void registerIcons(IconRegister ir){
		itemIcon=ir.registerIcon(Gizmos.modName+":"+"netherLighter");
	}


}


