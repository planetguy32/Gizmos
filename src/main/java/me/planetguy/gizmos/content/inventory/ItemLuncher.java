package me.planetguy.gizmos.content.inventory;

import java.util.List;

import me.planetguy.gizmos.Gizmos;
import me.planetguy.gizmos.Properties;
import me.planetguy.gizmos.base.ItemBase;
import me.planetguy.gizmos.util.Lang;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.FoodStats;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ItemLuncher extends ItemBase{

	public static int MAX_FOOD_CARRIED=100;
	
	public ItemLuncher() {
		super("luncher");
		this.setMaxDamage(MAX_FOOD_CARRIED);
		this.setNoRepair();
		this.maxStackSize=1;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World w, EntityPlayer player){
		int lunchCarried=MAX_FOOD_CARRIED-stack.getItemDamage();
		
		FoodStats stats=player.getFoodStats();
		int playerHunger=20-stats.getFoodLevel();
		
		//feed the player until either they are full or the luncher is out of food
		int toFeedToPlayer=Math.min(lunchCarried, playerHunger);
		
		if(toFeedToPlayer>0){ //if player is hungry and luncher isn't empty
			w.playSoundAtEntity(player, "random.burp", 0.5F, w.rand.nextFloat() * 0.1F + 0.9F);
			stats.addStats(toFeedToPlayer, 0);
			stack.setItemDamage(stack.getItemDamage()+toFeedToPlayer);
			//Debug.dbg("Luncher omnomnomnom! Ate "+toFeedToPlayer);
		}else{
			int foodGathered=0;
			boolean full=false;
			for(int i=0; i<player.inventory.mainInventory.length; i++){
				ItemStack stk=player.inventory.mainInventory[i];
				if(stk!=null && stk.getItem() instanceof ItemFood){ //check if food
					ItemFood item=(ItemFood) stk.getItem();
					int foodPerItem=item.func_150905_g(stk);
					int numberCanConsume=(int) Math.floor( (double)stack.getItemDamage() /foodPerItem );
					int eating=stack.getItemDamage()-foodPerItem*Math.min(numberCanConsume, stk.stackSize);
					//Debug.dbg("Can eat "+numberCanConsume + " of " + stk.stackSize +". Eating "+eating );
					stack.setItemDamage(Math.max(eating,0));
					if(numberCanConsume>=stk.stackSize){
						player.inventory.mainInventory[i]=null;
					}else{
						stk.stackSize-=numberCanConsume;
					}
				}
				if(full)break;
			}
		}
		return stack;
	}
	
	@Override
	public String replaceInTooltips(String s, ItemStack stack, EntityPlayer player) {
		return s.replace("####", ""+(MAX_FOOD_CARRIED-stack.getItemDamage())/2.0);
	}
	
	public int countTooltipLines(){
		return 2;
	}
	
	public void loadCrafting(){
		//Since the recipe uses bread, the luncher should start with one bread's worth of food in it.
		GameRegistry.addRecipe(new ItemStack(this,1,this.getMaxDamage()-((ItemFood)Items.bread).func_150905_g(null)), 
				new Object[]{
			"lsl",
			"lcl",
			"lll",
			Character.valueOf('l'), new ItemStack(Items.leather),
			Character.valueOf('s'), new ItemStack(Items.bread),
			Character.valueOf('c'), new ItemStack(Blocks.chest)
		});
	}
		
}
