/*   */ package planetguy.Gizmos.other;
/*   */ 
/*   */ import net.minecraftforge.client.MinecraftForgeClient;
/*   */ 
/*   */ public class ClientProxy extends CommonProxy
/*   */ {
/*   */   public void registerRenderers()
/*   */   {
/*   */     MinecraftForgeClient.preloadTexture(BLOCK_PNG);
/*   */   }
/*   */ }

/* Location:           C:\mcp\Mod backups\
 * Qualified Name:     planetguy.EvilToys.ClientProxy
 * JD-Core Version:    0.6.2
 */