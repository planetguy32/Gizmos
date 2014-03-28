package planetguy.lapis.item;

import planetguy.lapis.Recipe;

public abstract class Item {
	
	public Recipe[] getCraftingRecipes(){
		return new Recipe[]{};
	}
	
	public abstract String[] getNameForMeta();
	
	
	
}
