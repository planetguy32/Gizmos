package me.planetguy.gizmos.content.clearblocks;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import me.planetguy.gizmos.Gizmos;
import me.planetguy.gizmos.Properties;
import me.planetguy.lib.prefab.ItemBase;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemRevealer extends ItemBase implements IRevealer {

	private IIcon closedIcon;
	
	public ItemRevealer() {
		super("revealer");
	}
	
    public ItemStack onItemRightClick(ItemStack stk, World w, EntityPlayer player) {
    	if(FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
    		BlockAiry.toggleReveal();
    	}
    	return stk;
    }
    
    public void registerIcons(IIconRegister ir){
    	super.registerIcons(ir);
    	closedIcon=ir.registerIcon(Properties.modID+":revealerClosed");
    }
    
    public IIcon getIconFromDamage(int meta){
        return BlockAiry.isRevealed() ? super.getIconFromDamage(meta) : closedIcon;
    }

}
