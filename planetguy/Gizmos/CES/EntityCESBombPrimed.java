package planetguy.Gizmos.CES;

import planetguy.Gizmos.CES.powerups.Powerup;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityCESBombPrimed extends Entity{

	private TileEntityCESBomb bomb;
	public float power=(float) 2D;
	
	public EntityCESBombPrimed(World par1World, TileEntityCESBomb teBomb) {
		super(par1World);
		bomb=teBomb;
		for(Powerup p:bomb.getInstalledPowerups()){
			p.onEntityConstruct(this);
		}
	}

	public EntityCESBombPrimed(World w, TileEntityCESBomb teBomb, int x, int y, int z) {
		this(w,teBomb);
		posX=x;
		posY=y;
		posZ=z;
	}

	@Override
	protected void entityInit() {
		
	}
	
	public void explode(){
		CESExplosion expl=new CESExplosion(worldObj, this, this.posX, this.posY, this.posZ, power);
	}
	
	public void onUpdate(){
		for(Powerup p:bomb.getInstalledPowerups()){
			p.onEntityUpdate(this);
		}
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound tag) {
		bomb=new TileEntityCESBomb();
		bomb.readFromNBT(tag);
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound tag) {
		bomb.writeToNBT(tag);
	}

}
