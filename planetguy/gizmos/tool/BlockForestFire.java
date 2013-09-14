package planetguy.gizmos.tool;

import planetguy.simpleLoader.SLLoad;

import com.google.common.collect.ImmutableList;

/**
 * A version of BlockSuperFire reconfigured to use wood and grass, not lava and gravel
 * @author planetguy
 *
 */
@SLLoad(name="forestFire",isTechnical=true)

public class BlockForestFire extends BlockSuperFire{

	public BlockForestFire(int id, int texture) {
		super(id, texture);
		targetBlocks=ImmutableList.of(17, 18, 31, 106);
	}

}
