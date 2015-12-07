package me.planetguy.gizmos.content.hologram;

import net.minecraft.tileentity.TileEntity;

public abstract class Transforms {
	
	public abstract void apply(TileEntityHologramProjector startPos);
	
	public static Transforms NULL=new Transforms() {
		public void apply(TileEntityHologramProjector startPos) {}
	};

}
