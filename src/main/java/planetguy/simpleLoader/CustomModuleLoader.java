package planetguy.simpleLoader;

import net.minecraftforge.common.Configuration;

/**Used to make modules with custom loading behavior, for example, blocks with GUIs that need registering.
 * 
 * Note that to be noticed by SimpleLoader, classes must be annotated with @SLLoad
 * 
 * @author planetguy
 *
 */

public abstract class CustomModuleLoader {
	
	public CustomModuleLoader(){}
	
	public abstract void load();
	
	
}
