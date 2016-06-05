package me.planetguy.gizmos.content.clearblocks;

import java.util.List;
import java.util.Random;

import me.planetguy.lib.prefab.BlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockSmoker extends BlockAiry {

	public BlockSmoker() {
		super("smokeMaker");
		this.setTickRandomly(true);
	}
	
	private final String[] fx=new String[]{
		"magicCrit",
		"portal",
		"smoke",
		"flame",
		"heart",
		"explode",
		"largeexplode",
		"hugeexplosion",
		"depthsuspend",
		"largesmoke",
		"snowballpoof",
		"reddust",
		"happyVillager",
		"note",
		
	};
	
	public int tickRate(World w){
		return 3;
	}
	
    public void randomDisplayTick(World w, int x, int y, int z, Random rng){
		int meta=w.getBlockMetadata(x, y, z);
		w.spawnParticle(fx[meta], x+0.5, y+0.5, z+0.5, rng.nextGaussian()*0.3, 0.2+rng.nextGaussian()*0.3, rng.nextGaussian()*0.3);
	}
	
	public IIcon getIcon(int side, int meta){
		return Blocks.glowstone.getIcon(0, 0);
	}
	
    public void getSubBlocks(Item item, CreativeTabs tab, List ls){
    	for(int i=0; i<fx.length; i++)
    		ls.add(new ItemStack(this, 1, i));
    }
    
	public int countTooltipLines(){
		return 0;
	}
    
}
