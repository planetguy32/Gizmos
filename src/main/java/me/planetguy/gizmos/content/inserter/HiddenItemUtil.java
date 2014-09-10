package me.planetguy.gizmos.content.inserter;

import me.planetguy.gizmos.Properties;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class HiddenItemUtil {
	
	public static final String hiddenItemTagName=Properties.modID+"_hiddenStack";
	
	/**
	 * @param in the ItemStack to check
	 * @return the ItemStack hidden (may be null)
	 */
	public static ItemStack getHiddenStack(ItemStack in){
		if(in.hasTagCompound()){
			NBTTagCompound tag=in.getTagCompound();
			if(tag.hasKey(hiddenItemTagName)){
				return ItemStack.loadItemStackFromNBT(tag.getCompoundTag(hiddenItemTagName));
			}
		}
		return null;
	}
	
	/**
	 * @param concealment the stack to hide things inside
	 * @param hidden the stack to be hidden
	 * @return the stack with the item hidden
	 */
	
	public static ItemStack hideItem(ItemStack concealment, ItemStack hidden){
		if(!concealment.hasTagCompound()){
			concealment.setTagCompound(new NBTTagCompound());
		}
		concealment.getTagCompound().setTag(hiddenItemTagName, hidden.writeToNBT(new NBTTagCompound()));
		return concealment;
	}
	
	/**
	 * Removes all hidden items from the stack passed in.
	 * @param in
	 * @return
	 */
	public static ItemStack clearHiddenItems(ItemStack in){
		if(in.hasTagCompound()){
			NBTTagCompound tag=in.getTagCompound();
			if(tag.hasKey(hiddenItemTagName)){
				tag.removeTag(hiddenItemTagName);
			}
		}
		return in;
	}

}
