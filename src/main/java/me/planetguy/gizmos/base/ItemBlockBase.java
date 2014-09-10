package me.planetguy.gizmos.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.planetguy.gizmos.Properties;
import me.planetguy.gizmos.util.Lang;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class ItemBlockBase extends ItemBlockWithMetadata{

	public final IGizmosBlock block;
	
	public ItemBlockBase(Block block) {
		super(block, block);
		setHasSubtypes(true);
		this.block=((IGizmosBlock)block);
	}
	
	//Tooltip infrastructure - shared between ItemBase and ItemBlockBase
	@Override
	public final void addInformation(ItemStack stack, EntityPlayer player, List lines, 
			boolean advancedTooltipsActive){
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
			describe(stack, player, lines);
		}else{
			lines.add(Lang.translate(Properties.instructionsShowTooltip));
		}
	}

	public void describe(ItemStack stack, EntityPlayer player, List<String> lines){
		if(stack.getItemDamage()==0)
			for(int i=1; i<=countTooltipLines(); i++){
				lines.add(Lang.translate(block.getUnlocalizedName()+".tooltip."+i));
			}
		else
			for(int i=1; i<=countTooltipLines(); i++){
				lines.add(Lang.translate(block.getUnlocalizedName()+"."+stack.getItemDamage()+".tooltip."+i));
			}
	}
	
	public int countTooltipLines(){
		return block.countTooltipLines();
	}
	
	//Fix for multiple metas
    public String getUnlocalizedName(ItemStack stack) {
        if(stack.getItemDamage()!=0){
        	return super.getUnlocalizedName(stack)+"."+stack.getItemDamage();
        }else{
        	return super.getUnlocalizedName(stack);
        }
    }

}
