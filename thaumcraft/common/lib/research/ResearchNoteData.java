/*    */ package thaumcraft.common.lib.research;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import thaumcraft.api.aspects.AspectList;
/*    */ import thaumcraft.common.lib.utils.HexUtils.Hex;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ResearchNoteData
/*    */ {
/*    */   public String key;
/*    */   public int color;
/* 13 */   public HashMap<String, ResearchManager.HexEntry> hexEntries = new HashMap();
/* 14 */   public HashMap<String, HexUtils.Hex> hexes = new HashMap();
/*    */   public boolean complete;
/*    */   public int copies;
/* 17 */   public AspectList aspects = new AspectList();
/*    */   
/* 19 */   public boolean isComplete() { return this.complete; }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/research/ResearchNoteData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */