package me.planetguy.gizmos.tool;

import java.util.List;

import me.planetguy.core.sl.SLLoad;
import me.planetguy.gizmos.Gizmos;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SLLoad(name="redstoneWand")
public class ItemRedstoneActivator extends ItemInteractDevice{

	@SLLoad
	public ItemRedstoneActivator(int par1) {
		LanguageRegistry.addName(this, "Redstone Activator");
		GameRegistry.addShapedRecipe(new ItemStack(this), new Object[]{
			" r",
			"s ",
			Character.valueOf('r'),new ItemStack(Blocks.redstone_torch),
			Character.valueOf('s'),new ItemStack(Items.stick),});
	}

	@Override
	public boolean doEffect(int x, int y, int z, World w,
			ItemStack me, EntityPlayer thePlayer) {
		w.setBlock(x,y,z, Gizmos.redstoneWandBlock);
		return false;
	}

	@Override
	public boolean canDoEffect(int x, int y, int z, World w,
			ItemStack me, EntityPlayer thePlayer) {
		return w.isAirBlock(x, y, z);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir){
		this.itemIcon=ir.registerIcon(Gizmos.modName+":redstoneActivator");
	}
	
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer player, List tooltipLines, boolean advancedTooltipsActive){
        if(!Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
        	tooltipLines.add("Hold <shift> for more");
        	return;
        }
		tooltipLines.add("Places temporary redstone emitters.");
		tooltipLines.add("Inspired by similar item by Emasher.");
	}
	

}
