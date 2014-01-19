package planetguy.gizmos.unused;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Random;

import planetguy.gizmos.Gizmos;
import planetguy.gizmos.motiontools.ColliderRecipe;
import planetguy.util.Debug;


import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

public class BlockColliderCore  extends Block{
	
	private final int tickRate=50;
	
	public ArrayList<ColliderRecipe> recipes = new ArrayList<ColliderRecipe>(20);
	
	public Entity current1, current2;

	public BlockColliderCore(int id) {
		super(id, Material.web);
		this.setCreativeTab(CreativeTabs.tabRedstone);
	}

	public void registerIcons(IconRegister ir){
		this.blockIcon=ir.registerIcon("Gizmos:collider");
	}
	
	public int tickRate(World world){
		return tickRate;
	}
	
	public void onBlockAdded(World par1World, int par2, int par3, int par4){
	//	par1World.scheduleBlockUpdate(par2, par3, par4, Gizmos.colliderID, tickRate);
	}
	
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World w, int x, int y, int z){
		return null;
	}
	
	public boolean isOpaqueCube(){
		return false;
	}
	
	private void doCollisionReaction(){	
		double deltaVx=current1.motionX-current2.motionX;
		double deltaVy=current1.motionY-current2.motionY;
		double deltaVz=current1.motionZ-current2.motionZ;
		double netDeltaV=Math.sqrt(sq(deltaVx)+sq(deltaVy)+sq(deltaVz));
		if(netDeltaV>=0.8){
			Debug.dbg("netDeltaV: "+netDeltaV); //Debug message
			for(int i=0; i<recipes.size(); i++){
				ColliderRecipe r=recipes.get(i);
				if(r.isValidRecipe(current1, current2, netDeltaV)){
					r.useRecipe(current1);
					if(r.removeEntities){
						current1.setDead();
						current2.setDead();
					}
				}
			}
		}
	}
	
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random){
		//Debug.dbg("Clearing entities in collider core");
		current1=null;
		current2=null;
		//par1World.scheduleBlockUpdate(par2, par3, par4, Gizmos.colliderID, tickRate);
	}
	
	private double sq(double a){
		return a*a;
	}
	
	public void addColliderRecipe(ColliderRecipe r){
		recipes.add(r);
	}
	
	public void onEntityCollidedWithBlock(World w, int x, int y, int z, Entity e){
		if(current1==null){
			current1=e;
		}else{
			current2=e;
		}
		if(current1 instanceof EntityLiving &&! (current1 instanceof EntityPlayer)
		 &&current2 instanceof EntityLiving &&! (current2 instanceof EntityPlayer)){
			doCollisionReaction();
		}
		//Debug.dbg("Mobs inside!");
	}
	
	
}
