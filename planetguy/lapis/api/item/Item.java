package planetguy.lapis.api.item;

import planetguy.lapis.api.Recipe;

public abstract class Item {
	
	public Recipe[] getCraftingRecipes(){
		return new Recipe[]{};
	}
	
	public abstract String[] getNameForMeta();
	
	
	
}
