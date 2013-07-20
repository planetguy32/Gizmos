package planetguy.Gizmos.CES;

import cpw.mods.fml.common.registry.LanguageRegistry;
import planetguy.Gizmos.CES.powerups.Powerup;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockBomb extends BlockContainer{

	public static BlockBomb instance;
	
	public BlockBomb(int blockID) {
		super(blockID, Material.tnt);
		LanguageRegistry.addName(this, "CES base [TEST]");
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityBomb();
	}
	
	public void explode(TileEntityBomb teBomb){
		World w=teBomb.worldObj;
		int x=teBomb.xCoord;
		int y=teBomb.yCoord;
		int z=teBomb.zCoord;
		w.spawnEntityInWorld(new EntityExplosion(w, teBomb, x, y, z));
		w.setBlockTileEntity(x, y, z, null);
		w.setBlock(x, y, z, 0);
	}

	//Pass event to all powerups
	@Override
    public void breakBlock(World w, int x, int y, int z, int idk, int idk2){
    	TileEntityBomb teBomb=((TileEntityBomb) w.getBlockTileEntity(x,y,z));
    	if(teBomb==null){
    		System.out.println("TE is null when bomb destroyed!");
    		super.breakBlock(w, x, y, z, idk, idk2);
    		return;
    	}
    	for(Powerup p: teBomb.getInstalledPowerups()){
    		if(p!=null){
    			p.onBlockDestroyed(w,x,y,z,teBomb);
    		}
    	}
    	super.breakBlock(w, x, y, z, idk, idk2);
    }
    
	@Override
    public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer player, int side, float par7, float par8, float par9){
		ItemStack stk=player.getHeldItem();
		TileEntityBomb bomb=((TileEntityBomb) w.getBlockTileEntity(x, y, z));
		if(stk==null){
			onBlockActivatedPassEvent(bomb,side,stk);
			return true;
		}
		Item i=stk.getItem();
		if(Powerup.powerupRegistry.containsKey(i)){
			Powerup p=Powerup.powerupRegistry.get(i);
			bomb.addPowerup(p);
			p.onPowerupAdded(w, x, y, z);
			System.out.println("Adding powerup: "+p.getName());
		}
		
		return true;
	}
	
	private void onBlockActivatedPassEvent(TileEntityBomb bomb, int side,ItemStack stk){
		for(Powerup p : bomb.getInstalledPowerups()){
			if(p!=null)p.onRightClick(bomb, side, stk);
		}
	}
}
