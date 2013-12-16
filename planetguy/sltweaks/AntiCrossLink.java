package planetguy.sltweaks;

import java.lang.reflect.Field;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPortal;
import net.minecraftforge.event.world.WorldEvent;
import planetguy.simpleLoader.CustomModuleLoader;
import planetguy.simpleLoader.SLLoad;
import planetguy.util.Debug;

@SLLoad(name="AntiPortalCrossLink")
public class AntiCrossLink extends CustomModuleLoader{
	
	@Override
	public void load() {
		BlockPortal portal=new ACLPortal(Block.portal.blockID);
		try {
			Field portalField=Block.class.getDeclaredField("portal");
		} catch (Exception e){
			Debug.dbg("Error! Not loading ACL portals!");
		}
		
	}
	
	private class ACLPortal extends BlockPortal{

		public ACLPortal(int par1) {
			super(par1);
		}
		
	}

}
