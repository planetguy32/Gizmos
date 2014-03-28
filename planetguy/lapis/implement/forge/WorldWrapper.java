package planetguy.lapis.implement.forge;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.world.World;
import planetguy.lapis.Point;

public class WorldWrapper implements planetguy.lapis.World {

	private World w;
	
	@Override
	public String getBlockName(Point pt) {
		return MinecraftDataUtil.blockIds.inverse().get(w.getBlockId(pt.x, pt.y,pt.z));
	}
	
	public int getBlockMeta(Point pt){
		return w.getBlockMetadata(pt.x, pt.y,pt.z);
	}
	
	public void setBlockMeta(Point pt, int meta){
		w.setBlockMetadataWithNotify(pt.x, pt.y,pt.z, meta, 0x04);
	}
	
	@Override
	public void setBlock(String block, Point pt) {
		w.setBlock(pt.x, pt.y, pt.z, MinecraftDataUtil.blockIds.get(block));
	}

	@Override
	public void scheduleCallback(Point p, int delayTicks) {
		w.scheduleBlockUpdate(p.x, p.y, p.z, w.getBlockId(p.x, p.y, p.z), delayTicks);
	}

}
