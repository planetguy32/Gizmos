package planetguy.gizmos.gravitybomb;

import java.util.logging.Level;

import cpw.mods.fml.common.FMLLog;
import net.minecraft.world.World;

public class EntityTunnelBomb extends EntityGravityBomb {
	
	public EntityTunnelBomb(World par1World){
		super(par1World);
	}
	public EntityTunnelBomb(World par1World, double par2, double par4,
			double par6, int lifespan) {
		super(par1World, par2, par4, par6);
		this.lifeSpan=lifespan;
	}
	
	@Override
	 public void onUpdate() {
			
		 this.prevPosY=posY;
		 this.prevPosY2=prevPosY;
		 this.prevPosY3=prevPosY2;
		 //FMLLog.log(Level.SEVERE, "Tunnel bomb alive!");
/* 53 */     this.lifeSpan --;
/* 58 */     if (canFallFrom(this.posX, this.posY, this.posZ,this)) {
/* 59 */       this.motionY -= 0.03999999910593033D;
/* 60 */       moveEntity(this.motionX, this.motionY, this.motionZ);
/*    */     } else {
			   //this.motionY=0D;
			   if(this.lifeSpan>0){
/* 62 */       this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, 4, true);
EntityTunnelBomb bomb = new EntityTunnelBomb(this.worldObj,
		   /* 79 */             posX, 
		   /* 80 */             posY,
		   posZ, this.lifeSpan);
		   /* 82 */           this.worldObj.spawnEntityInWorld(bomb);
			   }
/* 63 */       setDead();
		   }
/*    */     }
/*    */   }

	

