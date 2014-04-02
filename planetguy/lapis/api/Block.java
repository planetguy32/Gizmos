package planetguy.lapis.api;

import planetguy.lapis.api.item.Drop;
import planetguy.lapis.api.item.Recipe;

public abstract class Block {
	
	public Block(){};
	
	public String getMaterial(){
		return "ground";
	};
	
	public boolean onRightClickBlock(World w, Point pt, Entity pl){return false;}
	
	public abstract String[] getIconFromMeta();
	
	public Recipe[] getCraftingRecipes(){
		return new Recipe[]{};
	}
	
	public abstract String[] getNameForMeta();
	
	public void onPlaced(World w,Point p){}
	
	public void onTick(World w,Point p){}
	
	public Drop onExploded(World w, Point p){
		return Drop.NULL_DROP;
	}
	
	public Drop onBroken(World w, Point p){
		return Drop.NULL_DROP;
	}; 
}
