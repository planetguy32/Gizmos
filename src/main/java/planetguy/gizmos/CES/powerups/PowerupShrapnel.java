package planetguy.gizmos.CES.powerups;

import planetguy.gizmos.Gizmos;
import planetguy.gizmos.CES.CESExplosion;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;

public class PowerupShrapnel extends Powerup{

	@Override
	public String getName() {
		return "shrapnel";
	}

	@Override
	public String getModName() {
		return Gizmos.modName;
	}
	
	@Override
	public void onExplode(CESExplosion t){
		
	}

}
