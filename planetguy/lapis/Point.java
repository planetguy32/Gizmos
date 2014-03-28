package planetguy.lapis;

public class Point{ //Really simple point class

	public final int x, y, z;

	public Point(int x, int y, int z){
		this.x=x;
		this.y=y;
		this.z=z;
	}

	@Override
	public boolean equals(Object o){ //Overridden so that check x,y,z not memory address
		return o instanceof Point 
				&& ((Point)o).x==x
				&& ((Point)o).y==y
				&& ((Point)o).z==z;
	}
	
	@Override
	public int hashCode(){ //Overridden so that == hash codes if .equals(o)
		return 23*x+27*y+29*z;
	}
	
	public Point[] adjacent(){
		return new Point[]{
				new Point(x-1,y,z),
				new Point(x+1,y,z),
				new Point(x,y-1,z),
				new Point(x,y+1,z),
				new Point(x,y,z-1),
				new Point(x,y,z+1)
		};
	}
	
	public Point plus(Point p){
		return new Point(x+p.x, y+p.y, z+p.z);
	}

}
