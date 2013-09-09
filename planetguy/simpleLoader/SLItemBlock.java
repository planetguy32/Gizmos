package planetguy.simpleLoader;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

public class SLItemBlock extends ItemBlockWithMetadata{
	
	/**
	 * Maximum metadata SLItemBlock supports. Can be changed as necessary.
	 */
	public static final int maxMetadata=16;
	
	public static Map<Integer, String[]> names=new HashMap<Integer, String[]>();
	public static Map<Integer, String[][]> tooltips=new HashMap<Integer, String[][]>();

	
	public String[] name=new String[maxMetadata];
	public String[][] tooltip=new String[maxMetadata][0];
	
	public SLItemBlock(int id, Block block) {
		super(id, block);
		System.out.println("Making SLItemBlock");
	}
	
	@Override
	public String getItemDisplayName(ItemStack stack){
		String n=name[stack.getItemDamage()];
		if(n!=null)return n;
		return super.getItemDisplayName(stack);
	}
	
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer player, List tooltipLines, boolean advancedTooltipsActive){
		int meta=itemStack.getItemDamage();
		for(String TTtext:tooltip[meta]){
			tooltipLines.add(TTtext);
		}
	}
	
	public static void registerString(int id, int meta, String name, String[] tooltip){
		String[] newnames=names.get(id);
		if(newnames==null)newnames=new String[maxMetadata];
		newnames[meta]=name;
		names.put(id, newnames);
		
		String[][] ttips=tooltips.get(id);
		if(ttips==null)ttips=new String[maxMetadata][0];
		ttips[meta]=tooltip;
		tooltips.put(id, ttips);
	}

}
