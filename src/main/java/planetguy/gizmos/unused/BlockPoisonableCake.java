package planetguy.gizmos.unused;

import net.minecraft.block.BlockCake;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class BlockPoisonableCake extends BlockCake {

	private boolean isPoisoned=false;
	
	public BlockPoisonableCake(int par1) {
		super(par1);
	}
	
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9){
        this.eatCakeSlice(par1World, par2, par3, par4, par5EntityPlayer);
        return true;
    }

    public void onBlockClicked(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer){
        this.eatCakeSlice(par1World, par2, par3, par4, par5EntityPlayer);
    }
	
	private void eatCakeSlice(World par1World, int par2, int par3, int par4, EntityPlayer player){
		System.out.println("Poison cake! Oh noes!");
		player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 60, 1));
	}

}
