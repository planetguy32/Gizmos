package planetguy.lapis;

public interface Entity {
	
	public Point roughPosition();
	
	public String name();
	
	public void accelerate(double dx, double dy, double dz);
	
}
