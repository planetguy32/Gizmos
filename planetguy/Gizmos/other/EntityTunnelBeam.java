package planetguy.Gizmos.other;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityTunnelBeam extends EntityLiving {

	public EntityTunnelBeam(World par1World) {
		super(par1World);
		// TODO Auto-generated constructor stub
	}
	public String getTexture()
    {
		return "/planetguy/EvilToys/tex.png";
    }

	@Override
	protected void entityInit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound var1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound var1) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int getMaxHealth() {
		// TODO Auto-generated method stub
		return 1;
	}

}
