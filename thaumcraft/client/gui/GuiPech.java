/*    */ package thaumcraft.client.gui;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.inventory.GuiContainer;
/*    */ import net.minecraft.client.multiplayer.PlayerControllerMP;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.InventoryPlayer;
/*    */ import net.minecraft.inventory.Container;
/*    */ import net.minecraft.inventory.Slot;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.common.entities.monster.EntityPech;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class GuiPech extends GuiContainer
/*    */ {
/*    */   EntityPech pech;
/*    */   
/*    */   public GuiPech(InventoryPlayer par1InventoryPlayer, World world, EntityPech pech)
/*    */   {
/* 25 */     super(new thaumcraft.common.container.ContainerPech(par1InventoryPlayer, world, pech));
/* 26 */     this.field_146999_f = 175;
/* 27 */     this.field_147000_g = 232;
/* 28 */     this.pech = pech;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 37 */   ResourceLocation tex = new ResourceLocation("thaumcraft", "textures/gui/gui_pech.png");
/*    */   
/*    */ 
/*    */   protected void func_146979_b(int par1, int par2) {}
/*    */   
/*    */   protected void func_146976_a(float par1, int par2, int par3)
/*    */   {
/* 44 */     this.field_146297_k.field_71446_o.func_110577_a(this.tex);
/* 45 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 46 */     int var5 = (this.field_146294_l - this.field_146999_f) / 2;
/* 47 */     int var6 = (this.field_146295_m - this.field_147000_g) / 2;
/* 48 */     GL11.glEnable(3042);
/* 49 */     func_73729_b(var5, var6, 0, 0, this.field_146999_f, this.field_147000_g);
/*    */     
/*    */ 
/* 52 */     if ((this.pech.isValued(this.field_147002_h.func_75139_a(0).func_75211_c())) && (this.field_147002_h.func_75139_a(0).func_75211_c() != null) && (this.field_147002_h.func_75139_a(1).func_75211_c() == null) && (this.field_147002_h.func_75139_a(2).func_75211_c() == null) && (this.field_147002_h.func_75139_a(3).func_75211_c() == null) && (this.field_147002_h.func_75139_a(4).func_75211_c() == null))
/*    */     {
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 59 */       func_73729_b(var5 + 67, var6 + 24, 176, 0, 25, 25);
/*    */     }
/*    */     
/* 62 */     GL11.glDisable(3042);
/*    */   }
/*    */   
/*    */   protected void func_73864_a(int mx, int my, int par3) throws java.io.IOException
/*    */   {
/* 67 */     super.func_73864_a(mx, my, par3);
/*    */     
/* 69 */     int gx = (this.field_146294_l - this.field_146999_f) / 2;
/* 70 */     int gy = (this.field_146295_m - this.field_147000_g) / 2;
/*    */     
/*    */ 
/* 73 */     int var7 = mx - (gx + 67);
/* 74 */     int var8 = my - (gy + 24);
/*    */     
/* 76 */     if ((var7 >= 0) && (var8 >= 0) && (var7 < 25) && (var8 < 25) && (this.pech.isValued(this.field_147002_h.func_75139_a(0).func_75211_c())) && (this.field_147002_h.func_75139_a(0).func_75211_c() != null) && (this.field_147002_h.func_75139_a(1).func_75211_c() == null) && (this.field_147002_h.func_75139_a(2).func_75211_c() == null) && (this.field_147002_h.func_75139_a(3).func_75211_c() == null) && (this.field_147002_h.func_75139_a(4).func_75211_c() == null))
/*    */     {
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 85 */       this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, 0);
/*    */       
/* 87 */       playButton();
/* 88 */       return;
/*    */     }
/*    */   }
/*    */   
/*    */   private void playButton() {
/* 93 */     this.field_146297_k.func_175606_aa().field_70170_p.func_72980_b(this.field_146297_k.func_175606_aa().field_70165_t, this.field_146297_k.func_175606_aa().field_70163_u, this.field_146297_k.func_175606_aa().field_70161_v, "thaumcraft:pech_dice", 0.5F, 0.95F + this.field_146297_k.func_175606_aa().field_70170_p.field_73012_v.nextFloat() * 0.1F, false);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/gui/GuiPech.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */