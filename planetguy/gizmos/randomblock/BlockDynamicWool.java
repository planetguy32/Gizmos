package planetguy.gizmos.randomblock;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import planetguy.gizmos.Gizmos;
import planetguy.simpleLoader.SLItemBlock;
import planetguy.simpleLoader.SLLoad;
import planetguy.util.Debug;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRedstoneLogic;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

@SLLoad(name="RedstoneResponsiveWool", primacy=3)
public class BlockDynamicWool extends Block{

	public Method getSignalStrength;

	@SLLoad
	public BlockDynamicWool(int id){
		super(id, Material.cloth);
		this.setUnlocalizedName(Gizmos.modName+":dynamicWool");
		SLItemBlock.registerString(id, 0, "Dynamic wool", new String[]{"Redstone makes it", "change colours"});
		try {
			getSignalStrength=BlockRedstoneLogic.class.getDeclaredMethod("getInputStrength", World.class, int.class, int.class, int.class, int.class);
			getSignalStrength.setAccessible(true);
		} catch(Exception e) {
		}
	}
	
	public int getSignalStrength(World par1World, int x, int y, int z, int direction){
        int x2=x,y2=y,z2=z;
        if(direction==0)y2++;
        if(direction==1)y2--;
        if(direction==2)x2++;
        if(direction==3)x2--;
        if(direction==4)z2++;
        if(direction==5)z2--;
        
        int l1 = par1World.getIndirectPowerLevelTo(x2, y2, z2, direction);
        return l1 >= 15 ? l1 : Math.max(l1, par1World.getBlockId(x2, y2, z2) == Block.redstoneWire.blockID ? par1World.getBlockMetadata(x2, y2, z2) : 0);
	}

	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List l){
		l.add(new ItemStack(this));
	}

	@Override
	public void onNeighborBlockChange(World w, int x, int y, int z, int idk){
		//notifyNeighbors(w,x,y,z);
		w.scheduleBlockUpdate(x, y, z, this.blockID, 1);
	}

	@Override
	public Icon getIcon(int side, int meta){
		return Block.cloth.getIcon(side, meta);
	}

	public void onBlockAdded(World w, int x, int y, int z){
		if(w.isRemote)//don't care if world isn't local
			return;
		w.scheduleBlockUpdate(x, y, z, this.blockID, 4);
	}
	
	public int signalStrength(World w, int x, int y, int z, int side) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Integer[] nums={getSignalStrength( w, x, y, z, 0),
				getSignalStrength( w, x, y, z, 1),
				getSignalStrength( w, x, y, z, 2),
				getSignalStrength( w, x, y, z, 3),
				getSignalStrength( w, x, y, z, 4),
				getSignalStrength( w, x, y, z, 5),
		};
		int max=Collections.max(Arrays.asList(nums));
		return max;
	}


	public void updateTick(World w, int x, int y, int z, Random random){
		try {
			w.setBlock(x, y, z, this.blockID, signalStrength(w,x,y,z,0), 0x02);
		} catch (Throwable e) {
			e.printStackTrace();
		}finally{}
		//w.scheduleBlockUpdate(x, y, z, this.blockID, 4);
	}

	//Should redstone connect?
	public boolean canProvidePower(){
		return true; //Yes, always.
	}

	//Cookie clicker save MS4wMzkzfHwxMzc5MjU4NjYxMDAzO05hTjsxMzgzNjEyNTY5MzM2fDExMTExMXwxNzU3MjU2NDAyMzg2NzcuMjU7MTI5MjY3OTQ3NzIxNTgzNzQ7OTE0NjsxODs0NjExMjE2NTA4Mjk1LjYzNDs0NjcyOy0xOy0xOzA7MDs0OzY1NDg7MDstMTswOzA7NDY4MzcxMjEzNDIuMDYwMzM7MHwyMDAsMjEwLDIwNzE3MTEwNjk3MTYwLDA7MjAwLDIwMSw2NjQ4MzU1Nzk0NDczNiwwOzE4MCwyMDEsNzE3NTAzNjAxNSwwOzE2NywxNzgsMjQ1MDE4OTEyNDYsMDsxMzIsMTM3LDgzMzg5NDE2OTk5LDA7MTA5LDEyMywyMDg5MzY2MTkyMzMsMDsxMDgsMTIwLDc4MjAzNDQ5NDQwMSwwOzEwMiwxMTgsMTI2MzcwMTY1ODM5NjUsMDsxMDEsMTA0LDE0MzY0NDQwNDA2NDMwNCwwOzg5LDg5LDgyMjg0NDg1NTI5MjIyMSwwO3w0NTAzNTk5NjI3MzcwNDk1OzQ1MDM1OTk2MjczNzA0OTU7NDUwMzU5OTYyNzM3MDExMTs0NTAzNTczNzMwMjk0MjcxOzIyNTM1ODY1MTg1MDc1MTk7MTM3NDM4OTUzNDczfDQ1MDM1OTc2MTQxMDQ1NzU7MjI1NDg1NDg3MDI2OTk1MTsxMDM5%21END%21


}
