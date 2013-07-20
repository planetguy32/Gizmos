package planetguy.Gizmos.loader;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import planetguy.Gizmos.Gizmos;
import planetguy.Gizmos.CES.BlockBomb;
import planetguy.Gizmos.CES.powerups.Powerup;
import planetguy.Gizmos.CES.powerups.PowerupDebug;
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
		BlockBomb.instance=new BlockBomb(Gizmos.baseBombID);
		BlockBomb.instance.setCreativeTab(CreativeTabs.tabRedstone);
		GameRegistry.registerBlock(BlockBomb.instance, ItemBlock.class, "blockBombBase");
		Powerup.registerPowerup(new PowerupDebug());
		Powerup.registerPowerup(new PowerupForkBomb());

	}

	@Override
	public String getName() {
		return "explosiveSysBase";
	}

}
