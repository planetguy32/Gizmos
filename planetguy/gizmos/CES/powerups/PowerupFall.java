package planetguy.gizmos.CES.powerups;

import java.util.Random;

import planetguy.gizmos.Gizmos;
import planetguy.gizmos.CES.BlockCESBomb;
import planetguy.gizmos.CES.TileEntityCESBomb;

import net.minecraft.block.BlockSand;
import net.minecraft.world.World;

public class PowerupFall extends Powerup{

	@Override
	public String getName() {
		return "Fall if possible";
	}

	@Override
	public String getModName() {
		return Gizmos.modName;
	}
	
	@Override
    public void onBlockUpdate(World w, int x, int y, int z, Random rand,TileEntityCESBomb bomb) {
		System.out.println("Falling?");
		if(BlockSand.canFallBelow(w, x, y-1, z)){
			System.out.println("Falling!");
			BlockCESBomb.instance.explode(bomb);
		}
	}


}
