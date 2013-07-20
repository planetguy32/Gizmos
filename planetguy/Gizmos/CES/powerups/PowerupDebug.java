package planetguy.Gizmos.CES.powerups;

import planetguy.Gizmos.Gizmos;
import planetguy.Gizmos.CES.BlockBomb;
import planetguy.Gizmos.CES.TileEntityBomb;
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
	public Item getLinkedItem() {
		return Item.compass;
	}
	
	@Override
	public void onRightClick(TileEntityBomb bomb, int side, ItemStack stk){
		BlockBomb bBomb=((BlockBomb) bomb.getBlockType());
		bBomb.explode(bomb);
	}

}
