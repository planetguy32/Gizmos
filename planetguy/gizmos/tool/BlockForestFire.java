package planetguy.gizmos.tool;

import planetguy.gizmos.Gizmos;
import planetguy.simpleLoader.SLLoad;

import com.google.common.collect.ImmutableList;

/**
 * A version of BlockSuperFire reconfigured to use wood and grass, not lava and gravel
 * @author planetguy
 *
 */
@SLLoad(name="forestFire",isTechnical=true)

public class BlockForestFire extends BlockSuperFire{

	@SLLoad
	public BlockForestFire(int id) {
		super(id);
		Gizmos.forestFire=this;
		targetBlocks=ImmutableList.of(17, 18, 31, 106);
	}

}
