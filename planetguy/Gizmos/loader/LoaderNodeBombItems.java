package planetguy.Gizmos.loader;

import net.minecraftforge.common.MinecraftForge;
import planetguy.Gizmos.spy.EventWatcherSpyItemUse;

public class LoaderNodeBombItems extends LoaderNode {
	
	public static final LoaderNode inst=new LoaderNodeBombItems();
	private final static LoaderNode[] depends={LoaderNodeInserter.inst};
	
	public LoaderNodeBombItems() {
		super(depends);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void loadLocal() {
		MinecraftForge.EVENT_BUS.register(new EventWatcherSpyItemUse());
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "hiddenBombs";
	}

}
