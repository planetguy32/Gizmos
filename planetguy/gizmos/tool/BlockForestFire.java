package planetguy.gizmos.tool;

import com.google.common.collect.ImmutableList;

public class BlockForestFire extends BlockSuperFire{

	public BlockForestFire(int id, int texture) {
		super(id, texture);
		targetBlocks=ImmutableList.of(17, 18, 31, 106);
	}

}
