package planetguy.lapis.implement.forge;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import planetguy.lapis.Block;
import planetguy.lapis.Point;

public class BlockWrapper extends net.minecraft.block.Block{
	
	private final Block lb;
	
	public BlockWrapper(int id, Block lb){
		super(id, MinecraftDataUtil.getMaterial(lb.getMaterial()));
		this.lb=lb;
	}
	
	public void updateTick(World w, int x, int y, int z, Random r){
		lb.onTick(MinecraftDataUtil.toLapis(w), new Point(x,y,z));
	}

	 public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int par6, float par7, float par8, float par9){
		 return lb.onRightClickBlock(MinecraftDataUtil.toLapis(w), new Point(x,y,z), new ForgePlayer(p));
	 }
}
