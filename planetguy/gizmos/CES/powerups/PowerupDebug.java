package planetguy.gizmos.CES.powerups;

import planetguy.gizmos.Gizmos;
import planetguy.gizmos.CES.BlockCESBomb;
import planetguy.gizmos.CES.CESContainer;
import planetguy.gizmos.CES.TileEntityCESBomb;
import planetguy.util.Debug;
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
		Debug.dbg("Debug powerup activated");
		//BlockCESBomb bBomb=((BlockCESBomb) bomb.getBlockType());
		//bBomb.explode(bomb);
	}

}
