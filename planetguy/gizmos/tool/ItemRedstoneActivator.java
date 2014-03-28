package planetguy.gizmos.tool;

import java.util.List;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import planetguy.gizmos.Gizmos;
import planetguy.simpleLoader.SLLoad;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

@SLLoad(name="redstoneWand",primacy=10)
public class ItemRedstoneActivator extends ItemOffsetInteractDevice{

	@SLLoad
	public ItemRedstoneActivator(int par1) {
		super(par1);
		this.setUnlocalizedName("redstoneActivator");
		LanguageRegistry.addName(this, "Redstone Activator");
		GameRegistry.addShapedRecipe(new ItemStack(this), new Object[]{
			" r",
			"s ",
			Character.valueOf('r'),new ItemStack(Block.torchRedstoneActive),
			Character.valueOf('s'),new ItemStack(Item.stick),});
	}

	@Override
	public boolean doEffect(int x, int y, int z, World w,
			ItemStack me, EntityPlayer thePlayer) {
		w.setBlock(x,y,z, Gizmos.redstoneWandBlock.blockID);
		return false;
	}

	@Override
	public boolean canDoEffect(int x, int y, int z, World w,
			ItemStack me, EntityPlayer thePlayer) {
		return w.isAirBlock(x, y, z);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister ir){
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
