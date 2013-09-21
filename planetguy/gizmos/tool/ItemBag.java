package planetguy.gizmos.tool;

import planetguy.simpleLoader.SLLoad;
import net.minecraft.item.Item;

@SLLoad(name="rCanvasBag",dependencies={"rCanvas"})
public class ItemBag extends Item{

	@SLLoad
	public ItemBag(int par1) {
		super(par1);
		
	}

}
