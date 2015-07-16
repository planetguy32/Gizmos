package me.planetguy.gizmos.content.devtools;

import java.lang.reflect.Field;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import me.planetguy.lib.prefab.ItemBase;
import me.planetguy.lib.util.Debug;

public class ItemDebugWandFieldwise extends ItemBase{

	public ItemDebugWandFieldwise() {
		super("debugWand");
	}
	
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World w, int x, int y, int z, int par7, float par8, float par9, float par10){
		TileEntity te=w.getTileEntity(x, y, z);
		Debug.dbg(w.getBlock(x, y, z)+":"+w.getBlockMetadata(x,y,z));
		player.addChatMessage(new ChatComponentText(w.getBlock(x, y, z)+":"+w.getBlockMetadata(x,y,z)+"\n"+Debug.dump(te)));
		Debug.details(te);
		return true;
	}
	
	public void registerIcons(IIconRegister ir){
		this.itemIcon=Items.stick.getIconFromDamage(0);
	}

}
