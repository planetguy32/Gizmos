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
import net.minecraft.world.biome.BiomeGenJungle;


@SLLoad(name="minersLighter",dependencies={"superFire"},primacy=6)
public class ItemMinersLighter extends ItemDeforester{

	@SLLoad
	public ItemMinersLighter(int par1) {
		super(par1,false);
		this.setUnlocalizedName("minersLighter");
		LanguageRegistry.instance().addNameForObject(this, "en_US", "Miner's Lighter");
		GameRegistry.addShapedRecipe(new ItemStack(this), new Object[]{
			"gbg",
			"blb",
			"gbg",
			Character.valueOf('g'),new ItemStack(Block.sapling),
			Character.valueOf('b'),new ItemStack(Item.blazePowder),
			Character.valueOf('l'),new ItemStack(Item.flintAndSteel)});
	}

	@Override
	public int id(){
		return Gizmos.superFire.blockID;
	}
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer player, List tooltipLines, boolean advancedTooltipsActive){
		tooltipLines.add("Very quickly removes gravel and lava.");
		if(player.worldObj.getBiomeGenForCoords((int)player.posX, (int)player.posZ)==BiomeGenBase.hell){
			tooltipLines.add("§4§LFire doesn't spread in Nether.");
			tooltipLines.add("§4§LHack at your own risk.");
		}
	}
	@Override
	public void registerIcons(IconRegister ir){
		itemIcon=ir.registerIcon(Gizmos.modName+":"+"minersLighter");
	}
	
}


