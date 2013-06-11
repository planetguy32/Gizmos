package planetguy.Gizmos.addon;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeDirection;
import buildcraft.BuildCraftTransport;
import buildcraft.api.core.IIconProvider;
import buildcraft.transport.Pipe;
import buildcraft.transport.PipeTransport;
import buildcraft.transport.PipeTransportItems;
import buildcraft.transport.pipes.PipeItemsCobblestone;
import buildcraft.transport.pipes.PipeLogic;

public class PipeLapis extends PipeItemsCobblestone{

	public final static Character SEC=(char) 245; //Section sign
	
	public static void loadSelf(){
		System.out.println("ASDFASDFASDFASDFASDFASDF");
		BuildCraftTransport.createPipe(8099, (Class<? extends Pipe>) PipeLapis.class, "Smart "+SEC+"m"+"tubes"+SEC+"r"+"pipes", new ItemStack(Block.blockLapis), new ItemStack(Block.glass), new ItemStack(Block.blockLapis));
	}
	
	public PipeLapis(int itemID) {
		super(itemID);
	}

	@Override
	public IIconProvider getIconProvider() {
		return IconProviderGizPipes.inst;
	}

	
	public static class PLLapis extends PipeLogic{
		
		private ArrayList<Point> checked=new ArrayList<Point>();
		
		
		
		@Override
		public boolean inputOpen(ForgeDirection in){
			return true;
		}
		
	}


}
