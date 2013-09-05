package planetguy.Gizmos.loader;

import java.util.ArrayList;

public abstract class LoaderNode {
	
	public static ArrayList<LoaderNode> registeredNodes=new ArrayList<LoaderNode>();
	

	
	public static LoaderNode inst;
	public static LoaderNode vanilla =new LNVanilla();
	
	public boolean alreadyLoaded=false;
	public LoaderNode[] depends;
	
	public LoaderNode(LoaderNode[] dependencies){
		depends=dependencies;
		inst=this;
		//LoaderNode.registeredNodes.add(this);
	}
		
	public void loadDependencies(){
		for(LoaderNode ln : depends){
			ln.loadRecursively();
		}
	}
	
	public final void loadRecursively(){
		//System.out.println("Node: "+this.getClass().getCanonicalName());
		if(alreadyLoaded){
			return;
		}
		loadDependencies();
		load();
		//this.alreadyLoaded=true; //Not going to be threadsafe, but in MC who cares?
	}
	
	public static void registerNode(LoaderNode ln){
		registeredNodes.add(ln);
	}
	
	public abstract void load();
	public abstract String getName();
	
	public static class LNVanilla extends LoaderNode{
		
		private final static LoaderNode[] dependOn={};
		
		
		public LNVanilla() {
			super(dependOn);			
			this.alreadyLoaded=true;
		}

		@Override
		public void load() {}

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
		registeredNodes.add(LoaderNodeFireExtinguisher.inst);
		registeredNodes.add(LoaderNodeExplosivesSystemBase.inst);
		registeredNodes.add(LoaderNodeLastLaughArmor.inst);
	}

}
