package me.planetguy.gizmos.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;


/* =============================================================================
 * Ray-tracing code based on client-side code in EntityPlayer - included here so available on server
 * =============================================================================
 */
public class Raycast {

	public static Vec3 getPosition(Entity p)
	{
		return Vec3.createVectorHelper(p.posX, p.posY, p.posZ);
	}

	public static MovingObjectPosition rayTrace(EntityLivingBase p, double distance)
	{
		Vec3 position = getPosition(p).addVector(0, p.getEyeHeight(), 0);
		Vec3 look = p.getLook(1);
		Vec3 adjustedLook = position.addVector(look.xCoord * distance, look.yCoord * distance, look.zCoord * distance);
		return p.worldObj.func_147447_a(position, adjustedLook, false, false, true);
	}

}
