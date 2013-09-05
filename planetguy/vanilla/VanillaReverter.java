package planetguy.vanilla;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(name="VanillaRecipeReverter",modid="VanillaRecipeReverter",version="1.0",dependencies="after:Railcraft")
public class VanillaReverter {
	
	@Instance("VanillaRecipeReverter")
	public static VanillaReverter instance;
	
	@Init
	public final void load(FMLInitializationEvent e){
		ItemStack iron=new ItemStack(Item.ingotIron);
		ItemStack gold=new ItemStack(Item.ingotGold);
		ItemStack rs=new ItemStack(Item.redstone);
		ItemStack rsTorch=new ItemStack(Block.redstoneLampActive);
		ItemStack stick=new ItemStack(Item.stick);
		ItemStack pplate=new ItemStack(Block.pressurePlateStone);
		ItemStack vRailBasic=new ItemStack(Block.rail,16);
		ItemStack vRailAccel=new ItemStack(Block.railPowered,6);
		ItemStack vRailSense=new ItemStack(Block.railDetector,6);
		ItemStack vRailActvt=new ItemStack(Block.railActivator,6);
		GameRegistry.addRecipe(vRailBasic, new Object[]{
				"i i",
				"isi",
				"i i",
				Character.valueOf('i'),iron,
				Character.valueOf('s'),stick});
		GameRegistry.addRecipe(vRailAccel, new Object[]{
				"g g",
				"gsg",
				"grg",
				Character.valueOf('g'),gold,
				Character.valueOf('s'),stick,
				Character.valueOf('r'),rs});
		GameRegistry.addRecipe(vRailSense, new Object[]{
				"i i",
				"ipi",
				"iri",
				Character.valueOf('i'),iron,
				Character.valueOf('p'),pplate,
				Character.valueOf('r'),rs});
		GameRegistry.addRecipe(vRailActvt, new Object[]{
				"isi",
				"iti",
				"isi",
				Character.valueOf('i'),iron,
				Character.valueOf('s'),stick,
				Character.valueOf('t'),rsTorch});
	}

}
