package planetguy.Gizmos.CES.powerups;

import java.util.HashMap;

import planetguy.Gizmos.CES.TileEntityBomb;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;


public abstract class Powerup {
	
	public static HashMap<Item,Powerup> powerupRegistry=new HashMap<Item,Powerup>();
	public static HashMap<Powerup,Item> yrtsigeRpurewop=new HashMap<Powerup,Item>();

	
	/**Registers powerups.
	 * 
	 * @param p the powerup to register.
	 * @param modname the name of the mod registering the powerup.
	 * @throws InvalidPowerupException 
	 */
	
	public static void registerPowerup(Powerup p) throws IllegalArgumentException{
		powerupRegistry.put(p.getLinkedItem(), p);
		yrtsigeRpurewop.put(p, p.getLinkedItem());
	}
			
	public abstract String getName();
	
	public abstract String getModName();
	
	/**Gets the maximum level of the powerup
	 * 
	 * @return the maximum level allowed to put on the powerup
	 */
	
	public int getMaxLevel(){
		return 1;
	}
	
	/**
	 * 
	 * @return the item that, when used on a base bomb, adds the powerup to the bomb
	 */
	
	public abstract Item getLinkedItem();
	
	/**
	 * Allows powerups to say if they can be added to an existing bomb. By default powerups can only be added if there isn't already that powerup in place.
	 * 
	 * @param level the powerup levels for each 
	 * @return whether the powerup can be applied to something
	 */
	
	public boolean isValidPowerup(Powerup[] powerups){
		int countSeen=0;
		for(Powerup p:powerups){
			if(p.getName()==this.getName())++countSeen;
		}
		return countSeen<=this.getMaxLevel();
	}

	/**Wrapped event from block
	 *  
	 * 
	 */
    public void onBlockDestroyed(World w, int x, int y, int z, TileEntityBomb teBomb) {
    	
    }
    
    /**Wrapped event. Fired when powerup is installed.
     * 
     */ 
    public void onPowerupAdded(World w, int x, int y, int z){
    	
    }
    
    /**Wrapped event. 
     * 
     * @param bomb the bomb tile entity
     * @param side the side right clicked
     * @param stack the stack the player is holding when they right click. Can be null.
     */
    public void onRightClick(TileEntityBomb bomb, int side, ItemStack stack){
		return;
    }

	
	//TODO collect all needed events from objects contained and wrap them 
}
