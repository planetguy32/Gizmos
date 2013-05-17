package planetguy.Gizmos.loader;

import java.util.ArrayList;

public abstract class LoaderNode {
	
	public static ArrayList<LoaderNode> registeredNodes=new ArrayList<LoaderNode>();
	

	
	public static LoaderNode inst;
	public static LoaderNode vanilla =new LNVanilla();
	
	public static boolean alreadyLoaded=false;
	public LoaderNode[] depends;
	
	public LoaderNode(LoaderNode[] depends){
		this.depends=depends;
		this.inst=this;
		//LoaderNode.registeredNodes.add(this);
	}
	
	public void load(){
		this.alreadyLoaded=true;
		if(depends==null){
			this.loadLocal();
			return;
		}
		for(LoaderNode ln : depends){
			if(!ln.alreadyLoaded)
				ln.load();
		}
		this.loadLocal();
	}
	
	public abstract void loadLocal();
	public abstract String getName();
	
	public static class LNVanilla extends LoaderNode{
		
		private final static LoaderNode[] dependOn={};
		
		
		public LNVanilla() {
			super(dependOn);			
			this.alreadyLoaded=true;
		}

		@Override
		public void loadLocal() {}

		@Override
		public String getName() {
			return "vanilla";
		}
		
	}
	
	static{
		registeredNodes.add(LoaderNodeBombItems.inst);
		registeredNodes.add(LoaderNode.vanilla.inst);
		registeredNodes.add(LoaderNodeBuildTool.inst);
		registeredNodes.add(LoaderNodeDefuser.inst);
		registeredNodes.add(LoaderNodeDislocator.inst);
		registeredNodes.add(LoaderNodeFire.inst);
		registeredNodes.add(LoaderNodeGravityBombs.inst);
		registeredNodes.add(LoaderNodeInserter.inst);
		registeredNodes.add(LoaderNodeInvenswappers.inst);
		registeredNodes.add(LoaderNodeLens.inst);
		registeredNodes.add(LoaderNodeTimeBombs.inst);
		registeredNodes.add(LoaderNodeVelocityManipulators.inst);
	}

}
