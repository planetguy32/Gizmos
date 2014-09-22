package me.planetguy.gizmos.content;

import java.util.ArrayList;
import java.util.HashSet;

import me.planetguy.gizmos.Properties;
import me.planetguy.lib.util.Debug;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class PortalHandler {

	public static void establishPortal(World w, int x, int y, int z, int side){
		if(!Properties.enableSpecialPortals)
			return;
		Debug.dbg("Establishing portal");
		int[] newPos=correct(x, y, z, side);
		x=newPos[0];
		y=newPos[1];
		z=newPos[2];
		int meta=1;
		HashSet<Point> pts=discoverPortalXYPlane(w, x, y, z);
		if(pts==null){
			pts=discoverPortalYZPlane(w, x, y, z);
			meta=0;
		}
		Debug.dbg("Points: "+pts);
		if(pts!=null){
			for(Point p:pts){
				w.setBlock(p.x, p.y, p.z, Blocks.portal, meta, 0x02);
			}
		}
	}
	
	private static HashSet<Point> discoverPortalXYPlane(World w, int x, int y, int z){
		HashSet<Point> seen=new HashSet<Point>();
		ArrayList<Point> toCheck=new ArrayList<Point>();
		toCheck.add(new Point(x, y, z));
		int checked=0;
		while(!toCheck.isEmpty()){
			if( checked>Properties.maxPortalSize){
				return null;
			}
			Point next=toCheck.remove(0);
			byte checkResult=checkPortalAt(w, next);
			checked++;
			if(checkResult==2){
				return null;
			}
			else if(checkResult==0){
				seen.add(next);
				for(Point p:new Point[]{
						new Point(next,  0, -1, 0),
						new Point(next,  0,  1, 0),
						new Point(next, -1,  0, 0),
						new Point(next,  1,  0, 0),
				}){
					if(!seen.contains(p)){
						toCheck.add(p);
					}
				}
			}
		}
		return seen;
	}
	
	private static HashSet<Point> discoverPortalYZPlane(World w, int x, int y, int z){
		HashSet<Point> seen=new HashSet<Point>();
		ArrayList<Point> toCheck=new ArrayList<Point>();
		toCheck.add(new Point(x, y, z));
		int checked=0;
		while(!toCheck.isEmpty()){
			if( checked>Properties.maxPortalSize){
				return null;
			}
			Point next=toCheck.remove(0);
			byte checkResult=checkPortalAt(w, next);
			checked++;
			if(checkResult==2){
				return null;
			}else if(checkResult==0){
				seen.add(next);
				for(Point p:new Point[]{
						new Point(next, 0, -1,  0),
						new Point(next, 0,  1,  0),
						new Point(next, 0,  0, -1),
						new Point(next, 0,  0,  1),
				}){
					if(!seen.contains(p)){
						toCheck.add(p);
					}
				}
			}
		}
		return seen;
	}
	
	//Checks portal status
	private static byte checkPortalAt(World w, Point p){
		Block block=w.getBlock(p.x, p.y, p.z);
		//System.out.println(id);
		if(block.equals(Blocks.obsidian))
			return 1;
		else if(block.equals(Blocks.air)||block.equals(Blocks.fire))
			return 0;
		else
			return 2;
	}
	
	//Corrects right-clicked block -> air block
	private static int[] correct(int x, int y, int z, int dir){

		if (dir == 0){--y;}
		if (dir == 1){++y;}
		if (dir == 2){--z;}
		if (dir == 3){++z;}
		if (dir == 4){--x;}
		if (dir == 5){++x;}

		int[] pos={x,y,z};
		return pos;
	}
	
	private static class Point{
		public final int x, y, z;
		
		public Point(Point p, int dx, int dy, int dz){
			this(p.x+dx, p.y+dy, p.z+dz);
		}
		
		public Point(int x, int y, int z){
			this.x=x; 
			this.y=y;
			this.z=z;
		}
		
		public int hashCode(){
			return this.toString().hashCode();
		}
		public boolean equals(Object o){
			if(!(o instanceof Point))
				return false;
			Point p=(Point)o;
			return x==p.x && y==p.y && z==p.z;
		}
		
		public String toString(){
			return "("+x+","+y+","+z+")";
		}
	}
	


}
