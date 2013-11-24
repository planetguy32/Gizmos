package planetguy.gizmos;

import planetguy.util.Debug;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class GizmosItem extends Item {

	public GizmosItem(int par1) {
		super(par1);
	}
	
	public void registerTexture(IconRegister ir){
		Debug.dbg("Generic item textures loading");
		itemIcon=ir.registerIcon(Gizmos.modName+":"+this.getUnlocalizedName());
	}

}
