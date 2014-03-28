package planetguy.lapis;

public interface World {
	
	public String getBlockName(Point pt);
	
	public void setBlock(String block, Point pt);
	
	public int getBlockMeta(Point p);
	
	public void setBlockMeta(Point p, int meta);
	
	public void scheduleCallback(Point p, int delayTicks);
	
}
