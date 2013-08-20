package planetguy.Gizmos.CES.powerups;

import planetguy.Gizmos.Gizmos;
import planetguy.Gizmos.CES.BlockCESBomb;
import planetguy.Gizmos.CES.TileEntityCESBomb;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class PowerupDebug extends Powerup{

	@Override
	public String getName() {
		return "Debug";
	}

	@Override
	public String getModName() {
		return Gizmos.modName;
	}
	
	@Override
	public void onRightClick(TileEntityCESBomb bomb, int side, ItemStack stk){
		System.out.println("Debug powerup activated");
		//BlockCESBomb bBomb=((BlockCESBomb) bomb.getBlockType());
		//bBomb.explode(bomb);
	}

}
