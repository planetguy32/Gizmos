package planetguy.Gizmos.CES;

import planetguy.Gizmos.CES.powerups.Powerup;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityExplosion extends Entity{

	public Powerup[] powerups;
	private TileEntityBomb bomb;
	
	public EntityExplosion(World par1World, TileEntityBomb teBomb) {
		super(par1World);
		bomb=teBomb;
		this.powerups=bomb.getInstalledPowerups();
	}

	public EntityExplosion(World w, TileEntityBomb teBomb, int x, int y, int z) {
		this(w,teBomb);
		posX=x;
		posY=y;
		posZ=z;
	}

	@Override
	protected void entityInit() {
		
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {
		
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {
		
	}

}
