package me.planetguy.gizmos.content;

import java.lang.reflect.Field;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import me.planetguy.lib.prefab.ItemBase;
import me.planetguy.lib.util.Debug;

public class ItemDebugWand extends ItemBase{

	public ItemDebugWand() {
		super("debugWand");
		this.itemIcon=Items.stick.getIconFromDamage(0);
	}
	
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World w, int x, int y, int z, int par7, float par8, float par9, float par10){
		TileEntity te=w.getTileEntity(x, y, z);
		Debug.dbg(w.getTileEntity(x,y,z));
		for(Field f:te.getClass().getDeclaredFields()){
			try {
				Debug.dbg("   "+f.getName()+"     "+f.get(te));
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

}
