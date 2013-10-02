package planetguy.gizmos.CES;

import planetguy.gizmos.CES.powerups.Powerup;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityCESBombPrimed extends Entity{

	private CESContainer cesContainer;
	public float power=(float) 2D;
	
	public EntityCESBombPrimed(World par1World, CESContainer cesc) {
		super(par1World);
		this.cesContainer=cesc;
		for(Powerup p:cesContainer.getInstalledPowerups()){
			p.onEntityConstruct(this);
		}
	}

	public EntityCESBombPrimed(World w, CESContainer teBomb, int x, int y, int z) {
		this(w,teBomb);
		posX=x;
		posY=y;
		posZ=z;
	}

	@Override
	protected void entityInit() {
		
	}
	
	public void explode(){
		CESExplosion expl=new CESExplosion(worldObj, this, this.posX, this.posY, this.posZ, this.cesContainer);
	}
	
	public void onUpdate(){
		for(Powerup p:cesContainer.getInstalledPowerups()){
			p.onEntityUpdate(this);
		}
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		cesContainer.readFromNBT(tag);
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		cesContainer.writeToNBT(tag);
	}

}
