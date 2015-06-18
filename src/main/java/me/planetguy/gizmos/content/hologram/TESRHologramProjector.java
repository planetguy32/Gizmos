package me.planetguy.gizmos.content.hologram;

import org.lwjgl.opengl.GL11;

import me.planetguy.lib.util.Debug;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;

public class TESRHologramProjector extends TileEntitySpecialRenderer {

	RenderBlocks rb=new RenderBlocks();
	
	@Override
	public void renderTileEntityAt(TileEntity teBase, double x,	double y, double z, float time) {
		
		Minecraft.getMinecraft().renderEngine.bindTexture(Minecraft.getMinecraft().renderEngine.getResourceLocation(0));
		GL11.glPushMatrix();
		
		rb.blockAccess=teBase.getWorldObj();
		rb.renderAllFaces=true;
		Tessellator.instance.startDrawingQuads();
		if(rb.blockAccess==null)
			throw new NullPointerException(teBase+"");
		TileEntityHologramProjector te=(TileEntityHologramProjector) teBase;
		//te.transforms.apply(te);
		int bx=teBase.xCoord;
		int by=teBase.yCoord;
		int bz=teBase.zCoord;
		Block b=teBase.getWorldObj().getBlock(bx, by-1, bz);
		
		rb.renderBlockByRenderType(b, bx, by, bz);
		
		GL11.glTranslated(x-(int) x, y-(int)y+1, z-(int)z);
		GL11.glRotated(0.1, 0, 1, 0);
		
		Tessellator.instance.draw();
		
		GL11.glPopMatrix();
	}

}
