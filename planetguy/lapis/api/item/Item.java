package planetguy.lapis.api.item;


public abstract class Item {
	
	public Recipe[] getCraftingRecipes(){
		return new Recipe[]{};
	}
	
	public abstract String[] getNameForMeta();
	
	
	
}
