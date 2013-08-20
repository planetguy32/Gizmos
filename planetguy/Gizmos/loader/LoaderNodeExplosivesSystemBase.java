package planetguy.Gizmos.loader;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import planetguy.Gizmos.Gizmos;
import planetguy.Gizmos.CES.BlockCESBomb;
import planetguy.Gizmos.CES.powerups.Powerup;
import planetguy.Gizmos.CES.powerups.PowerupDebug;
import planetguy.Gizmos.CES.powerups.PowerupExplodeOnImpact;
import planetguy.Gizmos.CES.powerups.PowerupFall;
import planetguy.Gizmos.CES.powerups.PowerupForkBomb;
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
		BlockCESBomb.instance=new BlockCESBomb(Gizmos.baseBombID);
		BlockCESBomb.instance.setCreativeTab(CreativeTabs.tabRedstone);
		GameRegistry.registerBlock(BlockCESBomb.instance, ItemBlock.class, "BlockCESBombBase");
		Powerup.registerPowerup(new PowerupDebug(),(byte) 0);
		Powerup.registerPowerup(new PowerupExplodeOnImpact(),(byte) 1);
		Powerup.registerPowerup(new PowerupFall(),(byte) 2);

	} 

	@Override
	public String getName() {
		return "explosiveSysBase";
	}

}
