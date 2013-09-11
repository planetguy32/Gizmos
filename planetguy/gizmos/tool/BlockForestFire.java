package planetguy.gizmos.tool;

import com.google.common.collect.ImmutableList;

/**
 * A version of BlockSuperFire reconfigured to use wood and grass, not lava and gravel
 * @author planetguy
 *
 */
public class BlockForestFire extends BlockSuperFire{

	public BlockForestFire(int id, int texture) {
		super(id, texture);
		targetBlocks=ImmutableList.of(17, 18, 31, 106);
	}

}
