/*    */ package thaumcraft.client.fx.strips;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.util.ArrayList;
/*    */ import net.minecraft.util.Vec3;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SwipeTriStrip
/*    */ {
/* 12 */   ArrayList<Vec3> texcoord = new ArrayList();
/* 13 */   ArrayList<Vec3> tristrip = new ArrayList();
/*    */   int batchSize;
/*    */   Vec3 perp;
/* 16 */   public float thickness = 30.0F;
/* 17 */   public float endcap = 8.5F;
/* 18 */   public Color color = new Color(16777215);
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/fx/strips/SwipeTriStrip.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */