/*    */ package thaumcraft.client.gui;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.List;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.GuiButton;
/*    */ import net.minecraft.client.gui.inventory.GuiContainer;
/*    */ import net.minecraft.client.multiplayer.PlayerControllerMP;
/*    */ import net.minecraft.entity.player.InventoryPlayer;
/*    */ import net.minecraft.inventory.Container;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.client.gui.plugins.GuiToggleButton;
/*    */ import thaumcraft.common.container.ContainerTurretAdvanced;
/*    */ import thaumcraft.common.entities.construct.EntityTurretCrossbowAdvanced;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class GuiTurretAdvanced extends GuiContainer
/*    */ {
/*    */   EntityTurretCrossbowAdvanced turret;
/*    */   
/*    */   public GuiTurretAdvanced(InventoryPlayer par1InventoryPlayer, World world, EntityTurretCrossbowAdvanced t)
/*    */   {
/* 27 */     super(new ContainerTurretAdvanced(par1InventoryPlayer, world, t));
/* 28 */     this.field_146999_f = 175;
/* 29 */     this.field_147000_g = 232;
/* 30 */     this.turret = t;
/*    */   }
/*    */   
/*    */   public void func_73866_w_()
/*    */   {
/* 35 */     super.func_73866_w_();
/*    */     
/* 37 */     this.field_146292_n.add(new GuiToggleButton(1, this.field_147003_i + 90, this.field_147009_r + 13, 8, 8, "button.turretfocus.1", new Runnable() {
/* 38 */       public void run() { GuiToggleButton.toggled = GuiTurretAdvanced.this.turret.getTargetAnimal(); } }));
/* 39 */     this.field_146292_n.add(new GuiToggleButton(2, this.field_147003_i + 90, this.field_147009_r + 27, 8, 8, "button.turretfocus.2", new Runnable() {
/* 40 */       public void run() { GuiToggleButton.toggled = GuiTurretAdvanced.this.turret.getTargetMob(); } }));
/* 41 */     this.field_146292_n.add(new GuiToggleButton(3, this.field_147003_i + 90, this.field_147009_r + 41, 8, 8, "button.turretfocus.3", new Runnable() {
/* 42 */       public void run() { GuiToggleButton.toggled = GuiTurretAdvanced.this.turret.getTargetPlayer(); } }));
/* 43 */     this.field_146292_n.add(new GuiToggleButton(4, this.field_147003_i + 90, this.field_147009_r + 55, 8, 8, "button.turretfocus.4", new Runnable() {
/* 44 */       public void run() { GuiToggleButton.toggled = GuiTurretAdvanced.this.turret.getTargetFriendly(); }
/*    */     }));
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/* 51 */   ResourceLocation tex = new ResourceLocation("thaumcraft", "textures/gui/gui_turret_advanced.png");
/*    */   
/*    */ 
/*    */   protected void func_146979_b(int par1, int par2) {}
/*    */   
/*    */   protected void func_146976_a(float par1, int par2, int par3)
/*    */   {
/* 58 */     this.field_146297_k.field_71446_o.func_110577_a(this.tex);
/* 59 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 60 */     int k = (this.field_146294_l - this.field_146999_f) / 2;
/* 61 */     int l = (this.field_146295_m - this.field_147000_g) / 2;
/* 62 */     GL11.glEnable(3042);
/* 63 */     func_73729_b(k, l, 0, 0, this.field_146999_f, this.field_147000_g);
/*    */     
/* 65 */     int h = (int)(39.0F * (this.turret.func_110143_aJ() / this.turret.func_110138_aP()));
/* 66 */     func_73729_b(k + 30, l + 59, 192, 48, h, 6);
/*    */     
/* 68 */     GL11.glDisable(3042);
/*    */   }
/*    */   
/*    */   protected void func_146284_a(GuiButton button) throws IOException
/*    */   {
/* 73 */     if (button.field_146127_k == 1)
/*    */     {
/* 75 */       this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, 1);
/*    */ 
/*    */     }
/* 78 */     else if (button.field_146127_k == 2)
/*    */     {
/* 80 */       this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, 2);
/*    */ 
/*    */     }
/* 83 */     else if (button.field_146127_k == 3)
/*    */     {
/* 85 */       this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, 3);
/*    */ 
/*    */     }
/* 88 */     else if (button.field_146127_k == 4)
/*    */     {
/* 90 */       this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, 4);
/*    */     }
/*    */     else
/*    */     {
/* 94 */       super.func_146284_a(button);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/gui/GuiTurretAdvanced.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */