package planetguy.gizmos;

import planetguy.util.Debug;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

public class GizmosItem extends Item {

	public GizmosItem(int par1) {
		super();
	}
	
	public void registerTexture(IIconRegister ir){
		Debug.dbg("Generic item textures loading");
		itemIcon=ir.registerIcon(Gizmos.modName+":"+this.getUnlocalizedName());
	}

}
