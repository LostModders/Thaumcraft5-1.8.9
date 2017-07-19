/*    */ package thaumcraft.client.gui;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.inventory.GuiContainer;
/*    */ import net.minecraft.client.renderer.texture.TextureManager;
/*    */ import net.minecraft.entity.player.InventoryPlayer;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.common.container.ContainerTurretBasic;
/*    */ import thaumcraft.common.entities.construct.EntityTurretCrossbow;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class GuiTurretBasic
/*    */   extends GuiContainer
/*    */ {
/*    */   EntityTurretCrossbow turret;
/*    */   
/*    */   public GuiTurretBasic(InventoryPlayer par1InventoryPlayer, World world, EntityTurretCrossbow t)
/*    */   {
/* 23 */     super(new ContainerTurretBasic(par1InventoryPlayer, world, t));
/* 24 */     this.field_146999_f = 175;
/* 25 */     this.field_147000_g = 232;
/* 26 */     this.turret = t;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 33 */   ResourceLocation tex = new ResourceLocation("thaumcraft", "textures/gui/gui_turret_basic.png");
/*    */   
/*    */ 
/*    */   protected void func_146979_b(int par1, int par2) {}
/*    */   
/*    */   protected void func_146976_a(float par1, int par2, int par3)
/*    */   {
/* 40 */     this.field_146297_k.field_71446_o.func_110577_a(this.tex);
/* 41 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 42 */     int k = (this.field_146294_l - this.field_146999_f) / 2;
/* 43 */     int l = (this.field_146295_m - this.field_147000_g) / 2;
/* 44 */     GL11.glEnable(3042);
/* 45 */     func_73729_b(k, l, 0, 0, this.field_146999_f, this.field_147000_g);
/*    */     
/* 47 */     int h = (int)(39.0F * (this.turret.func_110143_aJ() / this.turret.func_110138_aP()));
/* 48 */     func_73729_b(k + 68, l + 59, 192, 48, h, 6);
/*    */     
/* 50 */     GL11.glDisable(3042);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/gui/GuiTurretBasic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */