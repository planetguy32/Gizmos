package planetguy.gizmos.rp;

import cpw.mods.fml.common.registry.LanguageRegistry;
import planetguy.gizmos.Gizmos;
import planetguy.simpleLoader.SLLoad;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

@SLLoad(name="rCanvasBag",dependencies={"rCanvas"})
public class ItemBag extends Item{

	@SLLoad
	public ItemBag(int par1) {
		super(par1);
		this.setUnlocalizedName("rCanvasBag");
		LanguageRegistry.instance().addNameForObject(this, "en_US", "Canvas bag");
		this.setMaxStackSize(1);
	}
	
	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10){
		player.openGui(Gizmos.instance, 2, world, x, y, z);
		return true;
	}
}
