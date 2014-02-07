package planetguy.gizmos.CES;

import java.util.Random;

import planetguy.gizmos.Gizmos;
import planetguy.gizmos.CES.powerups.Powerup;
import planetguy.gizmos.CES.powerups.PowerupDebug;
import planetguy.gizmos.CES.powerups.PowerupExplodeOnImpact;
import planetguy.gizmos.CES.powerups.PowerupFall;
import planetguy.simpleLoader.SLItemBlock;
import planetguy.simpleLoader.SLLoad;
import planetguy.util.Debug;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

//@SLLoad(name="CESBomb", primacy=1)
public class BlockCESBomb extends BlockContainer{

	public static BlockCESBomb instance;
	
	@SLLoad
	public BlockCESBomb(int blockID) {
		super(blockID, Material.);
		BlockCESBomb.instance=(BlockCESBomb) this.func_149647_a(CreativeTabs.tabRedstone);
		LanguageRegistry.addName(this, "CES base [TEST]");
		SLItemBlock.registerString(blockID, 0, "CES base [TEST]",new String[]{"Preview of super seecret project.","Hint: It's a bomb."});
	}
		
	@SLLoad
	public static void loadMore(){
		Powerup.registerPowerup(new PowerupDebug(),(byte) 0);
		Powerup.registerPowerup(new PowerupExplodeOnImpact(),(byte) 1);
		Powerup.registerPowerup(new PowerupFall(),(byte) 2);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		TileEntityCESBomb te=new TileEntityCESBomb();
		te.getCESContainer().addPowerup(Powerup.powerups[0]);
		te.getCESContainer().addPowerup(Powerup.powerups[1]);
		return te;
	}
	
	public void explode(TileEntityCESBomb teBomb){
		World w=teBomb.worldObj;
		int x=teBomb.xCoord;
		int y=teBomb.yCoord;
		int z=teBomb.zCoord;
		w.spawnEntityInWorld(new EntityCESBombPrimed(w, teBomb.cesContainer, x, y, z));
		w.setBlockTileEntity(x, y, z, null);
		w.setBlock(x, y, z, 0);
	}

	//Pass event to all powerups
	@Override
    public void breakBlock(World w, int x, int y, int z, int idk, int idk2){
    	TileEntityCESBomb teBomb=((TileEntityCESBomb) w.func_147438_o(x,y,z));
    	if(teBomb==null){
    		Debug.dbg("TE is null when bomb destroyed!");
    		super.breakBlock(w, x, y, z, idk, idk2);
    		return;
    	}
    	for(Powerup p: teBomb.cesContainer.getInstalledPowerups()){
    		if(p!=null){
    			p.onBlockDestroyed(w,x,y,z,teBomb);
    		}
    	}
    	super.breakBlock(w, x, y, z, idk, idk2);
    }
	
	@Override
    public void onNeighborBlockChange(World w, int x, int y, int z, int neighbor){
		TileEntityCESBomb bomb=((TileEntityCESBomb)(w.func_147438_o(x, y, z)));
		for(Powerup p:bomb.cesContainer.getInstalledPowerups()){
			p.onNeighborBlockChange(w,  x,  y,  z, neighbor, bomb);
		}
		w.scheduleBlockUpdate(x, y, z, this.blockID, 2);
	}
	
	@Override
    public void updateTick(World w, int x, int y, int z, Random rand) {
		TileEntityCESBomb bomb=((TileEntityCESBomb)(w.func_147438_o(x, y, z)));
		for(Powerup p:bomb.cesContainer.getInstalledPowerups()){
			p.onBlockUpdate(w,  x,  y,  z, rand, bomb);
		}
    }

	@Override
	public void onBlockAdded(World w, int x, int y, int z){
		w.scheduleBlockUpdate(x, y, z, this.blockID, 2);
	}
    
	@Override
    public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer player, int side, float par7, float par8, float par9){
		ItemStack stk=player.getHeldItem();
		TileEntityCESBomb bomb=((TileEntityCESBomb) w.func_147438_o(x, y, z));
		if(stk==null||stk.itemID!=345){
			onBlockActivatedPassEvent(bomb,side,stk);
			return true;
		}else{
			for(Powerup p : bomb.cesContainer.getInstalledPowerups()){
				Debug.dbg(p.getName());
			}
		}
		bomb.cesContainer.tryAddPowerup(Powerup.powerups[1]);
		bomb.cesContainer.tryAddPowerup(Powerup.powerups[2]);
		return true;
	}
	
	private void onBlockActivatedPassEvent(TileEntityCESBomb bomb, int side,ItemStack stk){
		for(Powerup p : bomb.cesContainer.getInstalledPowerups()){
			if(p!=null)p.onRightClick(bomb, side, stk);
		}
	}

	@Override
	public TileEntity func_149915_a(World var1, int var2) {
		// TODO Auto-generated method stub
		return null;
	}
}
