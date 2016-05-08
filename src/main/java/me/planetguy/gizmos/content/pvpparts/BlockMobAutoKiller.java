package me.planetguy.gizmos.content.pvpparts;

import java.util.List;

import me.planetguy.lib.prefab.BlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockMobAutoKiller extends BlockBase {
	
	private final int RADIUS=25;
	
	public BlockMobAutoKiller() {
		super(Material.iron, "mobKiller");
	}
	
	public void onNeighborBlockChange(World w, int x, int y, int z, Block block) {
        boolean power = w.isBlockIndirectlyGettingPowered(x, y, z) 
        		|| w.isBlockIndirectlyGettingPowered(x, y + 1, z);
        if(power){
        	AxisAlignedBB box=AxisAlignedBB.getBoundingBox(
        			-RADIUS,-RADIUS,-RADIUS,
        			RADIUS,RADIUS,RADIUS);
        	for(Object e:w.getEntitiesWithinAABB(Entity.class,box)) {
        		if(!(e instanceof EntityPlayer)){
        			((Entity)e).setDead();
        		}
        	}
        }
    }
	
    public void getSubBlocks(Item i, CreativeTabs tab, List ls){
    	ls.add(new ItemStack(this));
    }
	
}
