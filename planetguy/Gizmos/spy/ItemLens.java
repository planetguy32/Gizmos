package planetguy.Gizmos.spy;

import planetguy.Gizmos.ConfigHolder;
import planetguy.Gizmos.GizmosItem;
import net.minecraft.item.Item;

public class ItemLens extends GizmosItem{
	
	public ItemLens(int id) {
		super(id);
		this.setItemName("spyLens");
		setIconIndex(7);
	}
	
	public String getTextureFile(){
		return "/planetguy/Gizmos/tex.png";
	}
	
}
