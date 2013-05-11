package planetguy.Gizmos.tool;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemBuildTool extends ItemPickaxe{
	
	private final int myID;

	public ItemBuildTool(int par1) {
		super(par1, EnumToolMaterial.EMERALD);
		myID=par1;
		setIconIndex(10);
		// TODO Auto-generated constructor stub
	}
	
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer player, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10){
		try{
			ItemStack theRealThis=player.inventory.getCurrentItem();
			NBTTagCompound tag=theRealThis.getTagCompound();
			int id=tag.getShort("id");
			int count=tag.getByte("Count");
			int meta=tag.getShort("Damage");
			ItemStack a=new ItemStack(id,count,meta);
			a.tryPlaceItemIntoWorld(player, par3World, par4, par5, par6, par7, par8, par9, par10);
			if(a.stackSize==0){
				boolean b=false;
				ItemStack[] inv=player.inventory.mainInventory;
				for(int i=0;  i<inv.length; i++){
					if(inv[i].itemID==id){
						a=inv[i];
						inv[i]=null;
						b=true;
						break;
					}
				}
				
				if(!b){
					a=null;
				}	
			}
				NBTTagCompound futureTag=new NBTTagCompound("Planetguy-spy");
				if(a!=null){
					a.writeToNBT(futureTag);
				}
				NBTTagCompound oldTag=theRealThis.getTagCompound();
				NBTTagCompound combinedTag=(NBTTagCompound) NBTTagCompound.newTag((byte) 10,"");
				combinedTag.setCompoundTag("spydata", futureTag);
				theRealThis.setTagCompound(futureTag);
			
		}catch(NullPointerException eitherHasNoHiddenOrIsUsedUp){
			//System.out.println("NPE handling build tool - prob no big deal.");
		}
		return true;
	}
	
	public String getTextureFile(){
		  return "/planetguy/Gizmos/tex.png";
	}

}
