package planetguy.util;

public class Point{ //Really simple point class

	public int x, y, z;

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

}
