package me.planetguy.gizmos.content.sugartools;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.world.World;
import me.planetguy.lib.prefab.ItemBase;

public class ItemSugarTool extends ItemBase {
	
	final ItemTool baseItem;
	
	private static ItemFood sugarTool=new ItemFood(1, false);
	
	public ItemSugarTool(ItemTool baseTool, String name) {
		super("sugar"+name);
		this.baseItem=baseTool;
	}
	
    public boolean onBlockDestroyed(ItemStack stk, World w, Block block, int x, int y, int z, EntityLivingBase user) {
    	if(stk.stackSize!=1)
    		stk.stackSize--;
    	return true;
    }
    
    public int getHarvestLevel(ItemStack stack, String toolClass) {
    	if(stack.stackSize != 1)
    		return baseItem.getHarvestLevel(stack, toolClass);
    	else
    		return 0;
    }
    
    public float getDigSpeed(ItemStack itemstack, Block block, int metadata) {
    	return baseItem.getDigSpeed(itemstack, block, metadata);
    }
    
    public ItemStack onEaten(ItemStack stk, World w, EntityPlayer p) {
        --stk.stackSize;
        p.getFoodStats().func_151686_a(sugarTool, stk);
        w.playSoundAtEntity(p, "random.burp", 0.5F, w.rand.nextFloat() * 0.1F + 0.9F);
        return stk;
    }
    
    /**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(ItemStack p_77626_1_)
    {
        return 64;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack p_77661_1_)
    {
        return EnumAction.eat;
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_)
    {
        if (p_77659_3_.canEat(false))
        {
            p_77659_3_.setItemInUse(p_77659_1_, this.getMaxItemUseDuration(p_77659_1_));
        }

        return p_77659_1_;
    }

	
}
