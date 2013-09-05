package planetguy.Gizmos.CES.powerups;

import java.util.Random;

import net.minecraft.block.BlockSand;
import net.minecraft.world.World;
import planetguy.Gizmos.Gizmos;
import planetguy.Gizmos.CES.BlockCESBomb;
import planetguy.Gizmos.CES.TileEntityCESBomb;

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
		if(true){//BlockSand.canFallBelow(w, x, y, z)){
			System.out.println("Falling!");
			BlockCESBomb.instance.explode(bomb);
		}
	}


}
