package planetguy.Gizmos.loader;

import net.minecraftforge.common.MinecraftForge;
import planetguy.Gizmos.GizmosEventWatcher;

public class LoaderNodeBombItems extends LoaderNode {
	
	public static final LoaderNode inst=new LoaderNodeBombItems();
	public LoaderNode[] depends={LoaderNodeInserter.inst};
	
	public LoaderNodeBombItems() {
		super(new LoaderNode[0]);
		super.depends=depends;
	}

	@Override
	public void load() {
		MinecraftForge.EVENT_BUS.register(new GizmosEventWatcher());
	}

	@Override
	public String getName() {
		return "hiddenBombs";
	}

}
