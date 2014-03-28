package planetguy.lapis;

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
	
	
}
