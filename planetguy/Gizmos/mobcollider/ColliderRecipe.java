package planetguy.Gizmos.mobcollider;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ColliderRecipe {
	
	private ItemStack[] items;
	private double velocity;
	private String a,b;
	
	public boolean removeEntities=true;
	
	public ColliderRecipe(ItemStack[] items, double minVelocity, Class c1, Class c2){
		this.items=items;
		this.velocity=minVelocity;
		this.a=c1.getName();
		this.b=c2.getName();
	}
	
	public boolean isValidRecipe(Entity a, Entity b, double speed){
		boolean test1=a.getClass().getName()==this.a&&b.getClass().getName()==this.b;
		boolean test2=a.getClass().getName()==this.b&&b.getClass().getName()==this.a;
		boolean test3=speed>=this.velocity;
		
		System.out.println("Recipe with test results "+ test1 + test2 +test3);
		
		return (test1||test2)&&test3;
	}
	
	public void useRecipe(Entity e){
		for(int i=0; i<items.length; i++){
			e.entityDropItem(items[i], 0);
		}
	}

}
