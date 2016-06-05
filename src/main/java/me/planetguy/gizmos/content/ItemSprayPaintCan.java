package me.planetguy.gizmos.content;

import java.util.List;

import me.planetguy.gizmos.Gizmos;
import me.planetguy.gizmos.Properties;
import me.planetguy.gizmos.content.clearblocks.BlockAiry;
import me.planetguy.gizmos.util.Raycast;
import me.planetguy.lib.prefab.ItemBase;
import net.minecraft.block.Block;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ItemSprayPaintCan extends ItemBase {

	public static final int range=15;
	
	private IIcon[] icons=new IIcon[16];
	
	public ItemSprayPaintCan() {
		super("sprayPaint");
	}
	
	
	public void paintBlock(World w, int x, int y, int z, ForgeDirection side, int colour){
		Block b=w.getBlock(x,y,z);
		if(b==Blocks.glass || b==Blocks.stained_glass){
			w.setBlock(x,y,z, Blocks.stained_glass, colour, 0x03);
		}else if(b==Blocks.carpet){
			w.setBlock(x,y,z,Blocks.carpet, colour, 0x03);
		}else if(b==Blocks.glass_pane || b==Blocks.stained_glass_pane){
			w.setBlock(x,y,z,Blocks.stained_glass_pane, colour, 0x03);
		}else if(b==Blocks.hardened_clay || b==Blocks.stained_hardened_clay){
			w.setBlock(x,y,z,Blocks.stained_hardened_clay,colour, 0x03);
		} else {
			b.recolourBlock(w,x,y,z, side, colour);
		}
	}
	
	public int getMaxDamage(){
		return 300 * 16;
	}
	
	public int getDisplayDamage(ItemStack stack){
        return stack.getItemDamage() & ~(15);
    }
	
    public ItemStack onItemRightClick(ItemStack stk, World w, EntityPlayer p)
    {
    	if(p instanceof AbstractClientPlayer)
    		return stk;
    	MovingObjectPosition target=Raycast.rayTrace(p, range);
    	if(target==null)
    		return stk;
    	stk.damageItem(16,p);
    	paintBlock(w,target.blockX,target.blockY,target.blockZ,
    			//If it goes the whole length, sideHit is -1 and we'd crash here.
    			target.sideHit == -1 ? ForgeDirection.UP : ForgeDirection.values()[target.sideHit]
    					,stk.getItemDamage() & 15);
    	Gizmos.helper.playSound(w, p.posX, p.posY, p.posZ, "spray", 1.0f, 1.0f);
    	if(stk.getItemDamage() > getMaxDamage())
    		return null;
    	else
    		return stk;
    }
    
    public void registerIcons(IIconRegister ir){
    	for(int i=0; i<icons.length; i++)
    		icons[i]=ir.registerIcon(Properties.modID+":sprayPaint"+i);
    }
    
    public IIcon getIconFromDamage(int meta){
        return icons[meta & 15];
    }
    
    public void getSubItems(Item item, CreativeTabs tab, List results) {
    	for(int i=0; i<16; i++)
    		results.add(new ItemStack(this, 1, i));
    }

	public int countTooltipLines(){
		return 1;
	}
	
}
