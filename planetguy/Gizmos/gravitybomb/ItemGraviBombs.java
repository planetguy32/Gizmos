package planetguy.Gizmos.gravitybomb;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ItemGraviBombs extends ItemBlock{

	public ItemGraviBombs(int par1) {
		super(par1);
		setHasSubtypes(true);
	}
	public int getMetadata(int par1){
		return par1;
	}
	
	public String getItemNameIS(ItemStack stack){
		String name="";
		switch(stack.getItemDamage()){
		case 0:{
			name="gravityBomb";
			break;
		}
		case 1:{
			name="tunnelBomb";
			break;
		}
		default:name="idk";
		}
		return getUnlocalizedName()+"."+name;
		
	}

	public Item setItemName(String string) {
		// TODO Auto-generated method stub
		return this;
	}

}
