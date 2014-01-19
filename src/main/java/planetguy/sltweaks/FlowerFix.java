package planetguy.sltweaks;

import net.minecraft.block.Block;
import net.minecraftforge.common.MinecraftForge;
import planetguy.simpleLoader.CustomModuleLoader;
import planetguy.simpleLoader.SLLoad;
import planetguy.simpleLoader.SLProp;

@SLLoad(name="flowerFix")
public class FlowerFix extends CustomModuleLoader{

	@SLProp(name="redFlowerWeight")
	public static int redFlowerWeight;
	
	@SLProp(name="yellowFlowerWeight")
	public static int yellowFlowerWeight;
	
	@Override
	public void load() {
		if(yellowFlowerWeight>20){
			MinecraftForge.addGrassPlant(Block.plantYellow, 0, yellowFlowerWeight-20);
		}
		if(redFlowerWeight>10){
			MinecraftForge.addGrassPlant(Block.plantRed, 0, redFlowerWeight-10);
		}
	}

}
