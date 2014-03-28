package planetguy.gizmos.invUtils;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import planetguy.gizmos.Gizmos;
import planetguy.lapis.api.Point;
import planetguy.simpleLoader.SLLoad;
import planetguy.simpleLoader.SLProp;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

@SLLoad(name="mudpack",primacy=10)
public class ItemMudpack extends Item{

	@SLProp(name="Mudpack capacity, in blocks")
	public static int MAX_MUD_CARRIED=500;

	@SLLoad
	public ItemMudpack(int par1) {
		super(par1);
		this.setUnlocalizedName("mudpack");
		LanguageRegistry.instance().addNameForObject(this, "en_US", "Mudpack");
		this.setMaxDamage(MAX_MUD_CARRIED);
		this.setNoRepair();
		this.maxStackSize=1;
		//Since the recipe uses dirt, the mudpack should start with one dirt in it.
		GameRegistry.addRecipe(new ItemStack(this,1,this.getMaxDamage()-1 ), 
				new Object[]{
			"lsl",
			"lcl",
			"lll",
			Character.valueOf('l'), new ItemStack(Item.leather),
			Character.valueOf('s'), new ItemStack(Block.dirt),
			Character.valueOf('c'), new ItemStack(Block.chest)
		});
	}

	public boolean onItemUse(ItemStack stack, EntityPlayer p, World w, int x, int y, int z, int side, float par8, float par9, float par10){

		if(w.getBlockId(x,y,z)==Gizmos.mudpackMud.blockID&&!p.isSneaking()){
			//r-clicking first block.
			Queue<Point> blocks=new ArrayDeque<Point>();
			blocks.add(new Point(x,y,z));
			while(!blocks.isEmpty()){
				stack.setItemDamage(stack.getItemDamage()-1);
				Point pt=blocks.poll();
				if(w.getBlockId(pt.x+1, pt.y, pt.z)==Gizmos.mudpackMud.blockID)
					blocks.add(new Point(pt.x+1, pt.y, pt.z));
				if(w.getBlockId(pt.x-1, pt.y, pt.z)==Gizmos.mudpackMud.blockID)
					blocks.add(new Point(pt.x-1, pt.y, pt.z));

				if(w.getBlockId(pt.x, pt.y+1, pt.z)==Gizmos.mudpackMud.blockID)
					blocks.add(new Point(pt.x, pt.y+1, pt.z));
				if(w.getBlockId(pt.x, pt.y-1, pt.z)==Gizmos.mudpackMud.blockID)
					blocks.add(new Point(pt.x, pt.y-1, pt.z));

				if(w.getBlockId(pt.x, pt.y, pt.z+1)==Gizmos.mudpackMud.blockID)
					blocks.add(new Point(pt.x, pt.y, pt.z+1));

				if(w.getBlockId(pt.x, pt.y, pt.z-1)==Gizmos.mudpackMud.blockID)
					blocks.add(new Point(pt.x, pt.y, pt.z-1));
				w.setBlockToAir(pt.x, pt.y, pt.z);
			}
		}else if(w.getBlockId(x,y,z)==Block.dirt.blockID){
			w.setBlock(x, y, x,0);
			stack.setItemDamage(stack.getItemDamage()-1);
		}else{
			if(stack.getItemDamage()<MAX_MUD_CARRIED){
				stack.setItemDamage(stack.getItemDamage()+1);
				putMud(w,x,y,z,side);
			}
			
		}
		return true;
	}

	public void putMud(World w, int x, int y, int z, int side){
		if (side == 0){
			--y;
		}

		if (side == 1){
			++y;
		}

		if (side == 2){
			--z;
		}

		if (side == 3){
			++z;
		}

		if (side == 4){
			--x;
		}

		if (side == 5){
			++x;
		}
		w.setBlock(x, y, z, Gizmos.mudpackMud.blockID);
		//w.setBlockMetadataWithNotify(x, y, z, 1, 0x04);
	}

	public void addInformation(ItemStack luncher, EntityPlayer player, List tooltipLines, 
			boolean advancedTooltipsActive){
		tooltipLines.add("Stores mud. R-click to use.");
		tooltipLines.add("Contains "+(MAX_MUD_CARRIED-luncher.getItemDamage())+" mud.");
	}



}
