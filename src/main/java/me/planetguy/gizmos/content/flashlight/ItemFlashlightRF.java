package me.planetguy.gizmos.content.flashlight;

import cofh.api.energy.IEnergyContainerItem;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.common.registry.GameRegistry;
import me.planetguy.gizmos.Properties;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ItemFlashlightRF extends ItemFlashlightBase implements IEnergyContainerItem{
	
	public ItemFlashlightRF(){
		super("RF");
		this.setMaxDamage(this.getMaxDamage()*2);
	}
	
	@Override
	public int receiveEnergy(ItemStack container, int maxReceive,
			boolean simulate) {
		int fullCharges=maxReceive/Properties.rfPerFlashlightRecharge;
		int spareRF=maxReceive/Properties.rfPerFlashlightRecharge;
		if(container.getItemDamage() < fullCharges){
			spareRF=(fullCharges-container.getMaxDamage())*Properties.rfPerFlashlightRecharge;
		}
		if(!simulate){
			container.setItemDamage(container.getItemDamage()-fullCharges);
		}
		return spareRF;
	}

	@Override
	public int extractEnergy(ItemStack container, int maxExtract,
			boolean simulate) {
		return 0;
	}

	@Override
	public int getEnergyStored(ItemStack container) {
		return container.getItemDamage()*Properties.rfPerFlashlightRecharge;
	}

	@Override
	public int getMaxEnergyStored(ItemStack container) {
		return ItemFlashlightBase.maxDamage*Properties.rfPerFlashlightRecharge;
	}
	
	public void loadCrafting(){
		GameRegistry.addShapedRecipe(new ItemStack(this, 1, this.getMaxDamage()), new Object[]{
			" il",
			"ibi",
			"gi ",
			Character.valueOf('i'), new ItemStack(Items.iron_ingot),
			Character.valueOf('g'), new ItemStack(Items.redstone),
			Character.valueOf('b'), new ItemStack(Blocks.stone_button),
			Character.valueOf('l'), new ItemStack(Blocks.glass)
		});
	}

}
