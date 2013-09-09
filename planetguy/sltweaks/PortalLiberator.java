package planetguy.sltweaks;

import java.util.ArrayList;

import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import planetguy.simpleLoader.CustomModuleLoader;
import planetguy.simpleLoader.SLLoad;

@SLLoad(name="anyShapePortals")
public class PortalLiberator extends CustomModuleLoader{

	@Override
	public void load() {
		MinecraftForge.EVENT_BUS.register(new PortalCreateListener());		
	}
	
public class PortalCreateListener {
		
		int checked=0;

		ArrayList<Point> points=new ArrayList<Point>();

		@ForgeSubscribe
		public void onInteract(PlayerInteractEvent pie){
			try{
				if(pie.entityPlayer.getCurrentEquippedItem().itemID==259){
					World w=pie.entityPlayer.worldObj;
					int[] pos=correct(pie.x, pie.y, pie.z, pie.face); 
					int x=pos[0],y=pos[1],z=pos[2];//Get block in which our click fell
					points.clear(); //Don't leave old checked points in here
					checked=0;
					if(!tryMakePortal(w,x,y,z,true)){ //Can't make YZ portal
						points.clear();
						checked=0;
						tryMakePortal(w,x,y,z,false); //Try to make XY portal
					}
					points.clear();
				}
			}catch(NullPointerException e){ //Equipped item could be null, in which case can't make portal
				//e.printStackTrace();
			}
		}
		/**
		 * Corrects direction
		 * @return an int[] with the x, y and z of the block on the side of the one clicked (like where flint and steels put their fire)
		 */
		public int[] correct(int x, int y, int z, int dir){

			if (dir == 0){--y;}
	        if (dir == 1){++y;}
	        if (dir == 2){--z;}
	        if (dir == 3){++z;}
	        if (dir == 4){--x;}
	        if (dir == 5){++x;}
	        
	        int[] pos={x,y,z};
	        return pos;
		}

		/**
		 * 
		 * @return whether its neighbors are valid portal blocks
		 */

		public boolean tryMakePortal(World w, int x, int y, int z, boolean checkConstantZPlane){
			if(checked>30)
				return false; //Don't check too many points - algorithm will go on forever otherwise
			//System.out.println("Checking portal at ("+x+","+y+","+z+")"); //Debug spam
			if(points.contains(new Point(x,y,z)))
				return true; //Don't re-check points - recursive base case
			byte check=checkPortalAt(w,x,y,z);
			if(check==0){ //Need to check neighbors
				++checked;
				points.add(new Point(x,y,z));
				boolean[] vals={
						tryMakePortal(w,x,y+1,z, checkConstantZPlane),  //Above
						tryMakePortal(w,x,y-1,z, checkConstantZPlane),  //Below
						tryMakePortal(w,checkConstantZPlane ? x+1 : x,y,checkConstantZPlane ? z: z+1, checkConstantZPlane), //+ in whichever plane we're searchig in
						tryMakePortal(w,checkConstantZPlane ? x-1 : x,y,checkConstantZPlane ? z: z-1, checkConstantZPlane)  //- in that plane
				};
				boolean stillAlive=true;
				for(boolean b :  vals){ //& all the values to see if all succeeded
					stillAlive=stillAlive&&b; 
				}
				if(stillAlive){
					w.setBlock(x, y, z, 90, checkConstantZPlane ? 1 : 0, 0x02 ); //Actually make a portal
				}
				return stillAlive;

			}else if (check==1){
				return true; //Hit obby: We're happy
			}else if (check==2)
				return false; //Hit invalid portal blocks: No portal can be made
			return checkConstantZPlane;
		}

		/**
		 * Checks at world, x, y, z coords if it is a valid portal block.
		 * @return 0 if you need to check the neighbors, 1 if not, 2 if it breaks the portal
		 */

		public byte checkPortalAt(World w, int x, int y, int z){
			int id=w.getBlockId(x, y, z);
			//System.out.println(id);
			switch(id){
			case 0: //Air or fire -> inside portal, need to check neighbors
			case 51:
				return 0;
			case 49: //Obby -> portal wall
				return 1;
			default: //Anything else -> can't make portal
				return 2; 
			}
		}

		private class Point{ //Really simple point class

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

	}

}
