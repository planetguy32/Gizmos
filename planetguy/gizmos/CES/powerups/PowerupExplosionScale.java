package planetguy.gizmos.CES.powerups;

import planetguy.gizmos.Gizmos;
import planetguy.gizmos.CES.EntityCESBombPrimed;
import net.minecraft.item.Item;

public class PowerupExplosionScale extends Powerup{

	@Override
	public String getName() {
		return "explosionScale";
	}

	@Override
	public String getModName() {
		return Gizmos.modName;
	}
	
    public void onEntityConstruct(EntityCESBombPrimed explosion){
    	explosion.power+=1.5;
    }

}
