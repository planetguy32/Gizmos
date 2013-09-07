package planetguy.Gizmos.loader;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import planetguy.Gizmos.Gizmos;
import planetguy.Gizmos.gravitybomb.BlockGraviBomb;
import planetguy.Gizmos.gravitybomb.EntityGravityBomb;
import planetguy.Gizmos.gravitybomb.EntityTunnelBomb;
import planetguy.Gizmos.gravitybomb.ItemGraviBombs;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class LoaderNodeGravityBombs extends LoaderNode{
	
	public static LoaderNode inst=new LoaderNodeGravityBombs();
	
	public LoaderNodeGravityBombs(){
		super(new LoaderNode[0]);
	}

	@Override
	public void load() {
		Gizmos.graviBomb = new BlockGraviBomb( Gizmos.gravityExplosivesID).setUnlocalizedName("graviBomb").setHardness(0.0F).setResistance(0.0F);
		Item.itemsList[ Gizmos.gravityExplosivesID] = new ItemGraviBombs( Gizmos.gravityExplosivesID-256).setItemName("graviBomb");
		Gizmos.graviBombPrimed = new EntityGravityBomb(null);
		Gizmos.tunnelBombPrimed=new EntityTunnelBomb(null);
		//EntityRegistry.registerModEntity(EntityTunnelBeam.class, "Tunnel Beam", 199, this, 80, 3, true);
		EntityRegistry.registerModEntity(EntityGravityBomb.class, "GBomb", 201, Gizmos.instance, 80, 3, true);
		EntityRegistry.registerModEntity(EntityTunnelBomb.class, "TBomb", 202, Gizmos.instance, 80, 3, true);
		ItemStack itemStackGB = new ItemStack(Gizmos.graviBomb, 3, 0);
		ItemStack itemStackExcaBomb = new ItemStack(Gizmos.graviBomb, 1, 1);
		ItemStack tnt = new ItemStack(Block.tnt);
		ItemStack powder = new ItemStack(Item.blazePowder);
		ItemStack iron = new ItemStack(Item.ingotIron);
		ItemStack itemStackPick = new ItemStack(Item.pickaxeIron);
		GameRegistry.addRecipe(itemStackGB, new Object[] { "xxx", "iyi", " i ", 
				Character.valueOf('x'),tnt, 
				Character.valueOf('y'), powder,
				Character.valueOf('i'), iron });
		GameRegistry.addRecipe(itemStackExcaBomb, new Object[] { " b ", "ibi", "pbp", 
				Character.valueOf('b'), itemStackGB, 
				Character.valueOf('i'), iron, 
				Character.valueOf('p'), itemStackPick });
		final String[] oreNames = {"Gravity bomb", "Tunnel bomb", "?", "?", "?", "?", "?", "?", "?", "?", "?", "?", "?", "?", "Tunnel Bomb", "Gravity Bomb"};

		for (int re = 0; re < 16; re++){
			ItemStack oreStack = new ItemStack(Gizmos.graviBomb, 1, re);
			LanguageRegistry.addName(oreStack, oreNames[oreStack.getItemDamage()]);
		}
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "gravityBombs";
	}
}
