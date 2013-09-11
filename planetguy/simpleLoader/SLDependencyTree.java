package planetguy.simpleLoader;

import java.util.ArrayList;
import java.util.HashMap;

public class SLDependencyTree {
	
	public HashMap<String,SLDependNode> nodes=new HashMap<String,SLDependNode>();
	
	public SLDependNode getNode(String name){
		SLDependNode node= nodes.get(name);
		if(node!=null)return node;
		return null;
	}
	
	public void addNode(SLLoad slload, Class c){
		SLDependNode node=new SLDependNode();
		SLDependNode[] depends=new SLDependNode[slload.dependencies().length];
		for(int i=0; i<slload.dependencies().length; i++){
			depends[i]=getNode(slload.dependencies()[i]);
			depends[i].dependedOnBy.add(node);
		}
		node.dependencies=depends;
	}
	
	public static class SLDependNode{
		
		public String name;
		public SLDependNode[] dependencies;
		public ArrayList<SLDependNode> dependedOnBy=new ArrayList<SLDependNode>();
		
	}

}
