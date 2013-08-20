package planetguy.Gizmos.CES.powerups;

import planetguy.Gizmos.Gizmos;
import planetguy.Gizmos.CES.EntityCESBombPrimed;
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
