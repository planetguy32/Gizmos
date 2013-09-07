/*    */ package planetguy.gizmos.gravitybomb;
/*    */ 
/*    */ import java.util.logging.Level;

import net.minecraft.entity.Entity;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.world.World;
import cpw.mods.fml.common.FMLLog;

/*    */ public class EntityGravityBomb extends Entity
/*    */ {
	/*    */   protected int lifeSpan;
	/*    */   //protected int meta;
	protected double prevPosY2;
	protected double prevPosY3;
	
	/*    */   public static boolean canFallFrom(double x, double y, double z,Entity e) {
		/* 42 */     int id = e.worldObj.getBlockId((int)x - 1, (int)y - 1, (int)z);
		/*    */ 
		/* 49 */     return id == 0;
	/*    */   }
	/*    */ 
	/*    */   public EntityGravityBomb(World par1World, double par2, double par4, double par6)
	/*    */   {
		/* 20 */     this(par1World);
		/* 21 */     setPosition(par2, par4, par6);
		/* 22 */     float var8 = (float)(Math.random() * 3.141592653589793D * 2.0D);
		/* 23 */     this.motionX = 0.0D;
		/* 24 */     this.motionY = 0.0D;
		/* 25 */     this.motionZ = 0.0D;
		/* 26 */     this.prevPosX = par2;
		/* 27 */     this.prevPosY = par4;
		/* 28 */     this.prevPosZ = par6;
		this.prevPosY2=0;
		this.prevPosY3=0;

		/* 29 */     this.lifeSpan = 10000;
		//FMLLog.log(Level.SEVERE, "Meta passed in: "+meta, "");
		/* 30 */     //this.meta = meta;
	/*    */   }
	/*    */ 
	/*    */   public EntityGravityBomb(World par1World)
	/*    */   {
		/* 35 */     super(par1World);
		/* 36 */     this.preventEntitySpawning = true;
		/* 37 */     setSize(0.98F, 0.98F);
		/* 38 */     this.yOffset = (this.height / 2.0F);
	/*    */   }
	/*    */ 

	/*    */ 
	/*    */   public void onUpdate() {

		this.prevPosY=posY;
		this.prevPosY2=prevPosY;
		this.prevPosY3=prevPosY2;
		//FMLLog.log(Level.SEVERE, "Meta in update: "+this.meta, "");
		/* 53 */     this.lifeSpan --;
		/* 58 */     if (canFallFrom(this.posX, this.posY, this.posZ,this)) {
			/* 59 */       this.motionY -= 0.03999999910593033D;
			/* 60 */       moveEntity(this.motionX, this.motionY, this.motionZ);
		/*    */     } else {
			/* 62 */       this.worldObj.newExplosion(this, this.posX, this.posY, this.posZ, 4, true, true);
			//if(this.meta==0 || this.lifeSpan==0);{
			/* 63 */       setDead();

		/*    */     }
	/*    */   }
	/*    */ 
	/*    */   protected void entityInit()
	/*    */   {
	/*    */   }
	/*    */ 
	/*    */   protected void readEntityFromNBT(NBTTagCompound var1) //Don't save or load. Should probably fix...
	/*    */   {
	/*    */   }
	/*    */ 
	/*    */   protected void writeEntityToNBT(NBTTagCompound var1)
	/*    */   {
	/*    */   }
/*    */ }

/* Location:           C:\mcp\Mod backups\
 * Qualified Name:     planetguy.EvilToys.EntityGravityBomb
 * JD-Core Version:    0.6.2
 */