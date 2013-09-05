package planetguy.Gizmos.spy;

import planetguy.Gizmos.Gizmos;
import planetguy.Gizmos.GizmosItem;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.Icon;

public class ItemLens extends GizmosItem{
	
	public ItemLens(int id) {
		super(id);
		this.setUnlocalizedName("spyLens");
	}
	
	public void registerIcons(IconRegister ir){
		itemIcon=ir.registerIcon(Gizmos.modName+":spyLens");
	}
}
