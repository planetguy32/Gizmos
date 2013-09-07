package planetguy.Gizmos.loader;

import net.minecraftforge.common.MinecraftForge;
import planetguy.Gizmos.spy.EventWatcherSpyItemUse;

public class LoaderNodeBombItems extends LoaderNode {
	
	public static final LoaderNode inst=new LoaderNodeBombItems();
	public LoaderNode[] depends={LoaderNodeInserter.inst};
	
	public LoaderNodeBombItems() {
		super(new LoaderNode[0]);
		super.depends=depends;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void load() {
		MinecraftForge.EVENT_BUS.register(new EventWatcherSpyItemUse());
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "hiddenBombs";
	}

}
