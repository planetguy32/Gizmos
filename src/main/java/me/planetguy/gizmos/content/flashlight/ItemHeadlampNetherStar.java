package me.planetguy.gizmos.content.flashlight;

import cpw.mods.fml.common.registry.GameRegistry;
import me.planetguy.gizmos.Gizmos;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemHeadlampNetherStar extends ItemFlashlightNetherStar{
	
	public ItemHeadlampNetherStar(){
		super("HeadlampNether");
	}
	
	//runs from head slot
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack){
    	super.onUpdate(player.inventory.armorInventory[3], world, player, 0, false);
    }
    
    
    //do not update on our own
    public void onUpdate(ItemStack stk, World w, Entity e, int a, boolean b){
    	
    }
    
    //valid only as helmet
    public boolean isValidArmor(ItemStack stack, int armorType, Entity entity){
    	return armorType==0;
    }
    
    public boolean isNotActuallyUsing(Entity e){
    	return e instanceof EntityLivingBase
    			&&
    			(e.getLastActiveItems()[3]==null || e.getLastActiveItems()[3].getItem()!=this);
    }
    
    public void loadCrafting(){
    	GameRegistry.addShapedRecipe(new ItemStack(this), new Object[]{
    		"lfl",
    		'l', new ItemStack(Items.leather),
    		'f', new ItemStack((Item) Gizmos.content.get("flashlightNether"))
    	});
    }

}
