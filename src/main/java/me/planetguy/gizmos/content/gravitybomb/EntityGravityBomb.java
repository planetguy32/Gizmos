package me.planetguy.gizmos.content.gravitybomb;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityGravityBomb extends Entity
{
	protected int lifeSpan;

	public static boolean canFallFrom(double x, double y, double z,Entity e) {
		return e.worldObj.isAirBlock((int)(x-0.5), (int)(y - 1), (int)(z-0.5));

	}

	public EntityGravityBomb(World par1World, double par2, double par4, double par6)
	{
		this(par1World);
		setPosition(par2, par4, par6);
		float var8 = (float)(Math.random() * 3.141592653589793D * 2.0D);
		this.motionX = 0.0D;
		this.motionY = 0.0D;
		this.motionZ = 0.0D;
		this.prevPosX = par2;
		this.prevPosY = par4;
		this.prevPosZ = par6;
		this.lifeSpan = 10000;
	}

	public EntityGravityBomb(World par1World)
	{
		super(par1World);
		this.preventEntitySpawning = true;
		setSize(0.98F, 0.98F);
		this.yOffset = (this.height / 2.0F);
	}



	public void onUpdate() {

		this.prevPosY=posY;
		this.lifeSpan --;
		if (canFallFrom(this.posX, this.posY, this.posZ,this)) {
			this.motionY -= 0.03999999910593033D;
			moveEntity(this.motionX, this.motionY, this.motionZ);
		} else {
			this.worldObj.newExplosion(this, this.posX, this.posY, this.posZ, 4, true, true);
			setDead();
		}
	}

	protected void entityInit(){
		
	}

	protected void readEntityFromNBT(NBTTagCompound tag)
	{
		lifeSpan=tag.getInteger("lifeSpan");
	}

	protected void writeEntityToNBT(NBTTagCompound tag)
	{
		tag.setInteger("lifeSpan", lifeSpan);
	}
}

