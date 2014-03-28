package planetguy.lapis.implement.forge;

import planetguy.lapis.Entity;
import planetguy.lapis.Point;
import net.minecraft.entity.player.EntityPlayer;

public class ForgePlayer implements Entity {
	
	private final EntityPlayer player;
	
	public ForgePlayer (EntityPlayer player){
		this.player=player;
	}

	@Override
	public Point roughPosition() {
		return new Point((int)player.posX, (int)player.posY, (int)player.posZ);
	}

	@Override
	public String name() {
		return player.username;
	}

	@Override
	public void accelerate(double dx, double dy, double dz) {
		
	}

}
