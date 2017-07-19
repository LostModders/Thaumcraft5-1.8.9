/*    */ package thaumcraft.client.lib;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import net.minecraft.client.model.PositionTextureVertex;
/*    */ import net.minecraft.client.renderer.Tessellator;
/*    */ import net.minecraft.client.renderer.WorldRenderer;
/*    */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*    */ import net.minecraft.util.Vec3;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TexturedQuadTC
/*    */ {
/*    */   public PositionTextureVertex[] vertexPositions;
/*    */   public int nVertices;
/*    */   private boolean invertNormal;
/*    */   
/*    */   public TexturedQuadTC(PositionTextureVertex[] vertices)
/*    */   {
/* 20 */     this.vertexPositions = vertices;
/* 21 */     this.nVertices = vertices.length;
/*    */   }
/*    */   
/*    */   public TexturedQuadTC(PositionTextureVertex[] vertices, int texcoordU1, int texcoordV1, int texcoordU2, int texcoordV2, float textureWidth, float textureHeight)
/*    */   {
/* 26 */     this(vertices);
/* 27 */     float f2 = 0.0F / textureWidth;
/* 28 */     float f3 = 0.0F / textureHeight;
/* 29 */     vertices[0] = vertices[0].func_78240_a(texcoordU2 / textureWidth - f2, texcoordV1 / textureHeight + f3);
/* 30 */     vertices[1] = vertices[1].func_78240_a(texcoordU1 / textureWidth + f2, texcoordV1 / textureHeight + f3);
/* 31 */     vertices[2] = vertices[2].func_78240_a(texcoordU1 / textureWidth + f2, texcoordV2 / textureHeight - f3);
/* 32 */     vertices[3] = vertices[3].func_78240_a(texcoordU2 / textureWidth - f2, texcoordV2 / textureHeight - f3);
/*    */   }
/*    */   
/*    */   public void flipFace()
/*    */   {
/* 37 */     PositionTextureVertex[] apositiontexturevertex = new PositionTextureVertex[this.vertexPositions.length];
/*    */     
/* 39 */     for (int i = 0; i < this.vertexPositions.length; i++)
/*    */     {
/* 41 */       apositiontexturevertex[i] = this.vertexPositions[(this.vertexPositions.length - i - 1)];
/*    */     }
/*    */     
/* 44 */     this.vertexPositions = apositiontexturevertex;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void draw(WorldRenderer renderer, float scale, int bright, int color, float alpha)
/*    */   {
/* 53 */     if (bright != -99) {
/* 54 */       renderer.func_181668_a(7, UtilsFX.VERTEXFORMAT_POS_TEX_CO_LM_NO);
/*    */     } else {
/* 56 */       renderer.func_181668_a(7, DefaultVertexFormats.field_181712_l);
/*    */     }
/* 58 */     Color c = new Color(color);
/*    */     
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 71 */     int aa = bright;
/* 72 */     int j = aa >> 16 & 0xFFFF;
/* 73 */     int k = aa & 0xFFFF;
/*    */     
/* 75 */     for (int i = 0; i < 4; i++)
/*    */     {
/* 77 */       PositionTextureVertex positiontexturevertex = this.vertexPositions[i];
/* 78 */       if (bright != -99) {
/* 79 */         renderer.func_181662_b(positiontexturevertex.field_78243_a.field_72450_a * scale, positiontexturevertex.field_78243_a.field_72448_b * scale, positiontexturevertex.field_78243_a.field_72449_c * scale).func_181673_a(positiontexturevertex.field_78241_b, positiontexturevertex.field_78242_c).func_181669_b(c.getRed(), c.getGreen(), c.getBlue(), (int)(alpha * 255.0F)).func_181671_a(j, k).func_181663_c(0.0F, 0.0F, 1.0F).func_181675_d();
/*    */ 
/*    */ 
/*    */       }
/*    */       else
/*    */       {
/*    */ 
/*    */ 
/* 87 */         renderer.func_181662_b(positiontexturevertex.field_78243_a.field_72450_a * scale, positiontexturevertex.field_78243_a.field_72448_b * scale, positiontexturevertex.field_78243_a.field_72449_c * scale).func_181673_a(positiontexturevertex.field_78241_b, positiontexturevertex.field_78242_c).func_181669_b(c.getRed(), c.getGreen(), c.getBlue(), (int)(alpha * 255.0F)).func_181663_c(0.0F, 0.0F, 1.0F).func_181675_d();
/*    */       }
/*    */     }
/*    */     
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 96 */     Tessellator.func_178181_a().func_78381_a();
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/lib/TexturedQuadTC.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */