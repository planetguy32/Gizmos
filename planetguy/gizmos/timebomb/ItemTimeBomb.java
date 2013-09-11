package planetguy.gizmos.timebomb;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
/**
 * Time bomb item class. Is probably responsible for time bombs and fork bombs taking the name of whatever item was last registered.
 * 
 * @author planetguy
 */
public class ItemTimeBomb extends ItemBlock {

	public ItemTimeBomb(int par1) {
		super(par1);
		setHasSubtypes(true);
	}
	
	///WTF?
	public int getMetadata(int meta){
		return meta;
	}
	
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
	{
	    for (int var4 = 0; var4 < 2; ++var4)
	    {
	        par3List.add(new ItemStack(par1, 1, var4));
	    }
	}
	
	//Doesn't work. 
	public String getItemNameIS(ItemStack stack){
		String name="";
		switch(stack.getItemDamage()%2){
		case 0:{
			name="timeBomb";
			break;
		}
		case 1:{
			name="forkBomb";
			break;
		}
		default:name="idk";
		}
		return getUnlocalizedName()+"."+name;
	}

	/**
	 * Sets the name of an item.
	 * @param string the name
	 * @return the item (now renamed).
	 */
	public Item setItemName(String string) {
		this.setUnlocalizedName(string);
		return this;
	}

}
