package me.planetguy.sltweaks;

import me.planetguy.core.sl.CustomModuleLoader;
import me.planetguy.core.sl.SLLoad;
import me.planetguy.core.sl.SLProp;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

@SLLoad(name="flowerFix")
public class FlowerFix extends CustomModuleLoader{

	@SLProp(name="redFlowerWeight")
	public static int redFlowerWeight;
	
	@SLProp(name="yellowFlowerWeight")
	public static int yellowFlowerWeight;
	
	@Override
	public void load() {
		if(yellowFlowerWeight>20){
			MinecraftForge.addGrassSeed(new ItemStack(Blocks.yellow_flower), yellowFlowerWeight-20);
		}
		if(redFlowerWeight>10){
			MinecraftForge.addGrassSeed(new ItemStack(Blocks.red_flower), redFlowerWeight-10);
		}
	}

}
