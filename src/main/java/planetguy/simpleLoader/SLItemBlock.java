package planetguy.simpleLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.lwjgl.input.Keyboard;

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
	public static List<SLItemBlock> slItemBlocks=new ArrayList<SLItemBlock>();

	public String[] name=new String[maxMetadata];
	public String[][] tooltip=new String[maxMetadata][0];

	public SLItemBlock(int id, Block block) {
		super(id, block);
		setHasSubtypes(true);
		slItemBlocks.add(this);
	}

	@Override
	public String getItemDisplayName(ItemStack stack){
		if(name==null)return super.getItemDisplayName(stack);
		String n=name[stack.getItemDamage()];
		if(n!=null)return n;
		return super.getItemDisplayName(stack);
	}

	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer player, List tooltipLines, boolean showMore){
        if(!Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
        	tooltipLines.add("Hold <shift> for more");
        	return;
        }
		try{
			int meta=itemStack.getItemDamage();
			for(String TTtext:tooltip[meta]){
				tooltipLines.add(TTtext);
			}
		}catch(Exception e){
			return;
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack stk){
		try{
			return name[0]+"("+name[stk.getItemDamage()]+")";
		}catch(Exception e){
			return super.getUnlocalizedName(stk);
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

	public static void loadLanguages(){
		for(SLItemBlock slib : slItemBlocks.toArray(new SLItemBlock[0])){
			slib.name=names.get(slib.getBlockID());
			slib.tooltip=tooltips.get(slib.getBlockID());
		}
	}

}
