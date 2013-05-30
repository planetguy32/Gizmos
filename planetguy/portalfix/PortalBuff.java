package planetguy.portalfix;

import java.awt.Point;
import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFlintAndSteel;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;


@Mod(modid="planetguy_PortalBuff", name="PortalBuff", version="1.0")
public class PortalBuff {

	@Instance("planetguy_PortalBuff")
	public static PortalBuff instance;

	@Init
	public final void load(FMLInitializationEvent ignored){
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
					int x=pos[0],y=pos[1],z=pos[2];
					points.clear();
					checked=0;
					if(!tryMakePortal(w,x,y,z,true)){
						points.clear();
						checked=0;
						tryMakePortal(w,x,y,z,false);
					}
					points.clear();
				}
			}catch(NullPointerException e){
				//e.printStackTrace();
			}
		}
		
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
		 * @param continueDir
		 */

		public boolean tryMakePortal(World w, int x, int y, int z, boolean checkConstantZPlane){
			if(checked>30)
				return false;
			//System.out.println("Checking portal at ("+x+","+y+","+z+")");
			if(points.contains(new Point(x,y,z)))
				return true;
			byte check=checkPortalAt(w,x,y,z);
			if(check==0){
				++checked;
				points.add(new Point(x,y,z));
				boolean[] vals={
						tryMakePortal(w,x,y+1,z, checkConstantZPlane),
						tryMakePortal(w,x,y-1,z, checkConstantZPlane),
						tryMakePortal(w,checkConstantZPlane ? x+1 : x,y,checkConstantZPlane ? z: z+1, checkConstantZPlane),
						tryMakePortal(w,checkConstantZPlane ? x-1 : x,y,checkConstantZPlane ? z: z-1, checkConstantZPlane)
				};
				boolean stillAlive=true;
				for(boolean b :  vals){
					stillAlive=stillAlive&&b;
				}
				if(stillAlive){
					w.setBlock(x, y, z, 90, checkConstantZPlane ? 1 : 0, 0x02 );
				}
				return stillAlive;

			}else if (check==1){
				return true;
			}else if (check==2)
				return false;
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
			case 0:
			case 51:
				return 0;
			case 49:
				return 1;
			default:
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
			public boolean equals(Object o){
				return o instanceof Point 
						&& ((Point)o).x==x
						&& ((Point)o).y==y
						&& ((Point)o).z==z;
			}

		}

	}

}
