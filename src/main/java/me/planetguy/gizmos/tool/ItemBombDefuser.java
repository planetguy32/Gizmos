package me.planetguy.gizmos.tool;

import java.util.List;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import me.planetguy.core.sl.SLLoad;
import me.planetguy.core.sl.SLProp;
import me.planetguy.gizmos.Gizmos;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

/**
 * Something to counter fork bombs.
 * @author planetguy
 *
 */
@SLLoad(name="bombDefuser",dependencies={"Lens"})
public class ItemBombDefuser extends Item{


	@SLProp(name = "explosivesID")
	public static Block[] explosivesID={Blocks.tnt};;
	
	@SLLoad
	public ItemBombDefuser(int par1) {
		super();
		ItemStack shears=new ItemStack(Items.shears);
		ItemStack stick=new ItemStack(Items.stick);
		ItemStack lens=new ItemStack(Gizmos.Lens);
		this.setMaxDamage(10);
		LanguageRegistry.addName(this, "Bomb defuser");
		ItemStack ISDefuser=new ItemStack(this);
		GameRegistry.addRecipe(ISDefuser, new Object[]{
				" sl",
				" ks",
				"k  ",
				Character.valueOf('s'),shears,
				Character.valueOf('k'),stick,
				Character.valueOf('l'),lens});
	}
	
	public void registerIcons(IIconRegister ir){
		itemIcon=ir.registerIcon(Gizmos.modName+":"+"defuser");
	}

	//Removes any block at the specified coordinates if it's in the config as defusable or if it uses Material.tnt
	public boolean onItemUse(ItemStack stack, EntityPlayer thePlayer, World w, int x, int y, int z, int par7, float par8, float par9, float par10){
		if(w.getBlock(x, y, z).getMaterial()==Material.tnt){
			defuse(w,x,y,z,thePlayer,stack);
			return true;
		}
		Block id=w.getBlock(x, y, z);
		for(int i=0; i<explosivesID.length;i++){
			if(explosivesID[i]==id&&thePlayer.isSneaking()){
	            defuse(w,x,y,z,thePlayer,stack);
				return true;
			}
		}
		return false;
	}
	
	//Damages item and removes target block
	private void defuse(World w, int x, int y, int z, EntityPlayer p, ItemStack stk){
		stk.damageItem(1, p);
		w.setBlockToAir(x, y, z);
	}
	
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer player, List tooltipLines, boolean advancedTooltipsActive){
        if(!Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
        	tooltipLines.add("Hold <shift> for more");
        	return;
        }
		tooltipLines.add("SWAT team approved! Sneak if using");
		tooltipLines.add("on fork bomb, or it's useless.");
	}
 

}
