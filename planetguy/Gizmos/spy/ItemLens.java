package planetguy.Gizmos.spy;

import planetguy.Gizmos.ConfigHolder;
import planetguy.Gizmos.GizmosItem;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.Icon;

public class ItemLens extends GizmosItem{
	
	public ItemLens(int id) {
		super(id);
		this.setUnlocalizedName("spyLens");
	}
	
	public String getTextureFile(){
		return "/planetguy/Gizmos/tex.png";
	}
	
	public void registerTexture(IconRegister ir){
		iconIndex=ir.registerIcon("Gizmos:spyLens");
	}
}
