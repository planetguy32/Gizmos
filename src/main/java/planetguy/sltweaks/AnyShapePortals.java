package planetguy.sltweaks;

import java.util.ArrayList;
import java.util.HashSet;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import planetguy.simpleLoader.CustomModuleLoader;
import planetguy.simpleLoader.SLLoad;
import planetguy.simpleLoader.SLProp;
import planetguy.util.Point;

@SLLoad(name="anyShapePortals")
public class AnyShapePortals extends CustomModuleLoader{

	@Override
	@SLLoad
	public void load() {
		MinecraftForge.EVENT_BUS.register(new PortalCreateListener());		
	}
	
	@SLProp(name="portalSizeLimit")
	public static int sizeLimit=100;

	public class PortalCreateListener {

		int checked=0;

		private HashSet<Point> points=new HashSet<Point>();

		@SubscribeEvent
		public void onInteract(PlayerInteractEvent pie){
			try{
				if(pie.entityPlayer.getCurrentEquippedItem().equals(Item.field_150901_e.getObject("flint_and_steel"))){
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
					System.out.println(sizeLimit);
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
			if(checked>sizeLimit)
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
					w.setBlock(x, y, z, Block.field_149771_c.getObject("portal"), checkConstantZPlane ? 1 : 0, 0x02 ); //Actually make a portal
				}
				return stillAlive;

			}else if (check==1){
				return true; //Hit obby: We're happy
			}else if (check==2)
				return false; //Hit invalid portal blocks: No portal can be made
			return true;
		}

		/**
		 * Checks at world, x, y, z coords if it is a valid portal block.
		 * @return 0 if you need to check the neighbors, 1 if not, 2 if it breaks the portal
		 */

		private byte checkPortalAt(World w, int x, int y, int z){
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
	}

}
