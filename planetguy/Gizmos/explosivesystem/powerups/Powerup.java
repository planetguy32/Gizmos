package planetguy.Gizmos.explosivesystem.powerups;

import planetguy.Gizmos.explosivesystem.EntityEvent;
import planetguy.Gizmos.explosivesystem.PosEvent;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract class Powerup {
	
	public static Powerup[] powerups=new Powerup[256];
	
	/**Registers powerups.
	 * 
	 * @param p the powerup to register.
	 * @param modname the name of the mod registering the powerup.
	 * @throws InvalidPowerupException 
	 */
	
	public static void registerPowerup(Powerup p, String modname) throws IllegalArgumentException{
		if(powerups[p.getID()]!=null){
			throw new IllegalArgumentException("Mod "+modname+" tried to register the powerup "+p.getName() +" at occupied ID " + p.getID());
		}else{
			powerups[p.getID()]=p;
		}
	}
		
	public abstract byte getID();
	
	public abstract String getName();
	
	public abstract Item getLinkedItem();
	
	/**
	 * Allows powerups to say if they can be added to an existing bomb
	 * 
	 * @param level the powerup levels for each 
	 * @return whether the powerup can be applied to something
	 */
	
	public boolean isValidPowerup(int[] level){
		return level[getID()]==0;
	}
		
	/**
	 * Allows powerups to handle events dependent on the block's position. 
	 * 
	 * @param pe The positional event to process
	 * @return whether you did something or not
	 */
	public boolean onPosEvent(World w, double x, double y, double z, PosEvent pe){
		return false;
	}
	
	/**Allows powerups to handle events dependent on their position and an entity.
	 * 
	 * @param e the entity triggering the event
	 * @param ee which entity-dependent event is being triggered
	 * @return whether the event has been reacted to
	 */
	public boolean onEntityEvent(World w, double x, double y, double z, Entity e, EntityEvent ee){
		return false;
	}
	
	/**
	 * Called when the bomb is exploding. The coords are the explosion position.
	 * 
	 * @return whether the powerup cares
	 */
	
	public boolean onTriggered(World w, Entity e){
		return false;
	}

}
