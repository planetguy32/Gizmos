package planetguy.simpleLoader;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * SimpleLoader dependency tree class
 * @author planetguy
 *
 */

public class SLDependencyTree {
	
	public HashMap<String,SLDependNode> nodes=new HashMap<String,SLDependNode>();
	
	/**
	 * Gets a requested node. If the node doesn't exist, returns null.
	 * @param name node to request
	 * @return the requested node
	 */
	public SLDependNode getNode(String name){
		SLDependNode node= nodes.get(name);
		if(node!=null)return node;
		return null;
	}
	
	/**
	 * Adds node from SLLoad annotation and class
	 */
	
	public void addNode(SLLoad slload, Class c){
		SLDependNode node=new SLDependNode();
		SLDependNode[] depends=new SLDependNode[slload.dependencies().length];
		for(int i=0; i<slload.dependencies().length; i++){
			depends[i]=getNode(slload.dependencies()[i]);
			depends[i].dependedOnBy.add(node);
		}
		node.dependencies=depends;
	}
	
	/**
	 * Helper class for SL dependency management.
	 * @author planetguy
	 *
	 */
	public static class SLDependNode{
		
		public Class c;
		public String name;
		public SLDependNode[] dependencies;
		public ArrayList<SLDependNode> dependedOnBy=new ArrayList<SLDependNode>();
		
	}

}
