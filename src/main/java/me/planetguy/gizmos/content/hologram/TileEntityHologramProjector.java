package me.planetguy.gizmos.content.hologram;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityHologramProjector extends TileEntity{
	
	public Transforms transforms=Transforms.NULL;
	
    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return (INFINITE_EXTENT_AABB);
    }

    @Override
    public boolean shouldRenderInPass(int Pass) {
        return (true);
    }

}
