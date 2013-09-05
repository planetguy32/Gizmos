package planetguy.Gizmos.CES.powerups;

import java.util.HashMap;
import java.util.Random;

import planetguy.Gizmos.CES.CESExplosion;
import planetguy.Gizmos.CES.EntityCESBombPrimed;
import planetguy.Gizmos.CES.TileEntityCESBomb;


import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;


public abstract class Powerup {
	
	public static Powerup[] powerups=new Powerup[256];

	
	/**Registers powerups.
	 * 
	 * @param p the powerup to register.
	 * @param id the ID to register the powerup at
	 */
	
	public static void registerPowerup(Powerup p, byte id) throws IllegalArgumentException{
		powerups[id]=p;
		p.id=id;
	}
		
	private byte id;
	
	public byte getID(){
		return id;
	}
			
	public abstract String getName();
	
	public abstract String getModName();
	
	/**Gets the maximum level of the powerup
	 * 
	 * @return how many times the powerup can be added to a CES bomb
	 */
	
	public int getMaxLevel(){
		return 1;
	}	
	
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
		return countSeen<this.getMaxLevel();
	}

	/**Wrapped event from block
	 *  
	 * 
	 */
    public void onBlockDestroyed(World w, int x, int y, int z, TileEntityCESBomb teBomb) {
    	
    }
    
    /**Wrapped event. 
     * 
     * @param bomb the bomb tile entity
     * @param side the side right clicked
     * @param stack the stack the player is holding when they right click. Can be null.
     */
    public void onRightClick(TileEntityCESBomb bomb, int side, ItemStack stack){
		return;
    }
    
    public void onBlockUpdate(World w, int x, int y, int z, Random rand, TileEntityCESBomb bomb) {
    	
    }

    
    /**
     * Called from the explosion's constructor.
     */
    
    public void onEntityConstruct(EntityCESBombPrimed explosion){
    	
    }
    
    
    public void onExplode(CESExplosion explosion){
    	
    }

	public void onNeighborBlockChange(World w, int x, int y, int z,
			int neighbor, TileEntityCESBomb bomb) {
		
	}

	/**
	 * Wrapped event.
	 * @param entityCESBombPrimed
	 */
	public void onEntityUpdate(EntityCESBombPrimed entityCESBombPrimed) {
		
	}

	
	//TODO collect all needed events from objects contained and wrap them 
}
