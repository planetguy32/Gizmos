package planetguy.Gizmos.unused;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityTunnelBeam extends EntityLiving {

	public EntityTunnelBeam(World par1World) {
		super(par1World);
	}
	public String getTexture()
    {
		return "/planetguy/EvilToys/tex.png";
    }

	@Override
	protected void entityInit() {
		
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound var1) {
		
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound var1) {
		
	}

}
