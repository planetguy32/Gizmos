package planetguy.gizmos.invUtils;

import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import planetguy.gizmos.Gizmos;
import planetguy.simpleLoader.SLLoad;
import planetguy.simpleLoader.SLProp;
import planetguy.util.Debug;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.FoodStats;
import net.minecraft.world.World;

@SLLoad(name="luncher",primacy=9)
public class ItemLuncher extends Item{

	@SLProp(name="Luncher capacity, in half food units")
	public static int MAX_FOOD_CARRIED=100;
	
	@SLLoad
	public ItemLuncher(int id) {
		super(id);
		this.func_149663_c("luncher");
		LanguageRegistry.instance().addNameForObject(this, "en_US", "Luncher");
		this.setMaxDamage(MAX_FOOD_CARRIED);
		this.setNoRepair();
		this.maxStackSize=1;
		//Since the recipe uses bread, the luncher should start with one bread's worth of food in it.
		GameRegistry.addRecipe(new ItemStack(this,1,this.getMaxDamage()-((ItemFood)Item.bread).getHealAmount()), 
				new Object[]{
			"lsl",
			"lcl",
			"lll",
			Character.valueOf('l'), new ItemStack(Item.leather),
			Character.valueOf('s'), new ItemStack(Item.bread),
			Character.valueOf('c'), new ItemStack((Block)Block.field_149771_c.getObject("chest)
		});
	}
	
	@Override
	public void registerIcons(IIconRegister ir){
		this.itemIcon=ir.registerIcon(Gizmos.modName+":luncher");
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
					int foodPerItem=item.getHealAmount();
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
	
	public void addInformation(ItemStack luncher, EntityPlayer player, List tooltipLines, 
			boolean advancedTooltipsActive){
		tooltipLines.add("Stores food. R-click to use.");
		tooltipLines.add("Contains "+(MAX_FOOD_CARRIED-luncher.getItemDamage())/2.0 +" food.");
	}
		


}
