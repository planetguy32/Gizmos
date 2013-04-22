/*    */ package planetguy.Gizmos.other;
/*    */ 
/*    */ import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
/*    */ 
/*    */ public class CommonProxy
/*    */   implements IGuiHandler
/*    */ {
/*  9 */   //public static String BLOCK_PNG = "/planetguy/EvilToys/tex.png";
/*    */ 
/*    */   public void registerRenderers() {
/*    */   }
/*    */ @Override
/*    */   public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
/* 15 */     return null;
/*    */   }
/*    */ @Override
/*    */   public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
/*    */   {
/* 20 */     return null;
/*    */   }
/*    */

} 

/* Location:           C:\mcp\Mod backups\
 * Qualified Name:     planetguy.EvilToys.CommonProxy
 * JD-Core Version:    0.6.2
 */