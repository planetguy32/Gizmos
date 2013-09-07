package planetguy.gizmos.loader;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import planetguy.gizmos.Gizmos;
import planetguy.gizmos.CES.BlockCESBomb;
import planetguy.gizmos.CES.powerups.Powerup;
import planetguy.gizmos.CES.powerups.PowerupDebug;
import planetguy.gizmos.CES.powerups.PowerupExplodeOnImpact;
import planetguy.gizmos.CES.powerups.PowerupFall;
import planetguy.gizmos.CES.powerups.PowerupForkBomb;
import cpw.mods.fml.common.registry.GameRegistry;

public class LoaderNodeExplosivesSystemBase extends LoaderNode {

	public static final LoaderNode inst=new LoaderNodeExplosivesSystemBase();

	private static LoaderNode[] lns={};
	
	public LoaderNodeExplosivesSystemBase() {
		super(lns);
		this.depends=new LoaderNode[]{};
	}

	@Override
	public void load() {


	} 

	@Override
	public String getName() {
		return "explosiveSysBase";
	}

}
