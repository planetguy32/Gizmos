package me.planetguy.gizmos.content.inserter;

import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import me.planetguy.gizmos.base.ItemBase;
import me.planetguy.gizmos.util.Lang;

public class ItemBuildTool extends ItemBase{

	private Item pick=Items.diamond_pickaxe;
	
	public ItemBuildTool() {
		super("buildTool");
		this.setMaxStackSize(1);
		this.setMaxDamage(pick.getMaxDamage());
		this.setHarvestLevel(pick.getToolClasses(new ItemStack(pick)).toArray(new String[0])[0], 3);
	}
	
	public void loadCrafting(){
		GameRegistry.addShapelessRecipe(new ItemStack(this), new Object[]{
			new ItemStack(Items.diamond_pickaxe),
			new ItemStack(Blocks.piston),
			new ItemStack(Blocks.chest),
			});
	}
	
	public int countTooltipLines(){
		return 2;
	}
	
	public void describe(ItemStack stack, EntityPlayer player, List<String> lines){
		String a=Lang.translate("item.buildTool.tooltipDynamic");
		ItemStack hidden=HiddenItemUtil.getHiddenStack(stack);
		if(hidden!=null)
			lines.add(a.replace("<name>", hidden.getDisplayName()).replace("<number>", hidden.stackSize+""));
		super.describe(stack, player, lines);
	}
	
	public boolean onItemUse(ItemStack me, EntityPlayer player, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10){
		try{
			ItemStack hidden=HiddenItemUtil.getHiddenStack(me);
			HiddenItemUtil.clearHiddenItems(me);
			hidden.tryPlaceItemIntoWorld(player, par3World, par4, par5, par6, par7, par8, par9, par10);
			if(hidden.stackSize==0){
				ItemStack[] stacks=player.inventory.mainInventory;
				boolean hasFoundReplace=false;
				for(int i=0; i<stacks.length; i++){
					if((hidden.getItem().equals(stacks[i].getItem())&&hidden.getItemDamage()==stacks[i].getItemDamage())){
						hidden=stacks[i];
						stacks[i]=null;
						hasFoundReplace=true;
						break;
					}
				}
				if(!hasFoundReplace)hidden=null;
			}
			HiddenItemUtil.hideItem(me, hidden);
		}catch(Exception e){
			e.printStackTrace(); //Causes alarm if there is no item inside
		}
		return true;
	}
	
	public float getDigSpeed(ItemStack stack, Block block, int metadata){
		if(stack.getItemDamage()>=(this.getMaxDamage()-1))
			return 0.1f;
		return pick.getDigSpeed(stack, block, metadata);
	}
	

}
