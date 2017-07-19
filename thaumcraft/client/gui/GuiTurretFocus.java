/*     */ package thaumcraft.client.gui;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.GuiButton;
/*     */ import net.minecraft.client.gui.inventory.GuiContainer;
/*     */ import net.minecraft.client.multiplayer.PlayerControllerMP;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.api.wands.ItemFocusBasic;
/*     */ import thaumcraft.client.gui.plugins.GuiToggleButton;
/*     */ import thaumcraft.common.container.ContainerTurretFocus;
/*     */ import thaumcraft.common.entities.construct.EntityTurretFocus;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class GuiTurretFocus extends GuiContainer
/*     */ {
/*     */   EntityTurretFocus turret;
/*     */   
/*     */   public GuiTurretFocus(InventoryPlayer par1InventoryPlayer, World world, EntityTurretFocus t)
/*     */   {
/*  29 */     super(new ContainerTurretFocus(par1InventoryPlayer, world, t));
/*  30 */     this.field_146999_f = 175;
/*  31 */     this.field_147000_g = 232;
/*  32 */     this.turret = t;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_73866_w_()
/*     */   {
/*  39 */     super.func_73866_w_();
/*     */     
/*  41 */     this.field_146292_n.add(new GuiToggleButton(1, this.field_147003_i + 90, this.field_147009_r + 13, 8, 8, "button.turretfocus.1", new Runnable() {
/*  42 */       public void run() { GuiToggleButton.toggled = GuiTurretFocus.this.turret.getTargetAnimal(); } }));
/*  43 */     this.field_146292_n.add(new GuiToggleButton(2, this.field_147003_i + 90, this.field_147009_r + 27, 8, 8, "button.turretfocus.2", new Runnable() {
/*  44 */       public void run() { GuiToggleButton.toggled = GuiTurretFocus.this.turret.getTargetMob(); } }));
/*  45 */     this.field_146292_n.add(new GuiToggleButton(3, this.field_147003_i + 90, this.field_147009_r + 41, 8, 8, "button.turretfocus.3", new Runnable() {
/*  46 */       public void run() { GuiToggleButton.toggled = GuiTurretFocus.this.turret.getTargetPlayer(); } }));
/*  47 */     this.field_146292_n.add(new GuiToggleButton(4, this.field_147003_i + 90, this.field_147009_r + 55, 8, 8, "button.turretfocus.4", new Runnable() {
/*  48 */       public void run() { GuiToggleButton.toggled = GuiTurretFocus.this.turret.getTargetFriendly(); }
/*     */     }));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  56 */   public static ResourceLocation tex = new ResourceLocation("thaumcraft", "textures/gui/gui_turret_focus.png");
/*     */   
/*     */ 
/*     */   protected void func_146979_b(int par1, int par2) {}
/*     */   
/*     */   protected void func_146976_a(float par1, int par2, int par3)
/*     */   {
/*  63 */     this.field_146297_k.field_71446_o.func_110577_a(tex);
/*  64 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  65 */     int k = (this.field_146294_l - this.field_146999_f) / 2;
/*  66 */     int l = (this.field_146295_m - this.field_147000_g) / 2;
/*  67 */     GL11.glEnable(3042);
/*  68 */     func_73729_b(k, l, 0, 0, this.field_146999_f, this.field_147000_g);
/*  69 */     int h = (int)(39.0F * (this.turret.func_110143_aJ() / this.turret.func_110138_aP()));
/*  70 */     func_73729_b(k + 24, l + 59, 192, 48, h, 6);
/*     */     
/*  72 */     if ((this.turret.func_70694_bm() != null) && ((this.turret.func_70694_bm().func_77973_b() instanceof ItemFocusBasic))) {
/*  73 */       GL11.glPushMatrix();
/*  74 */       GL11.glScaled(0.5D, 0.5D, 0.5D);
/*  75 */       String s = net.minecraft.util.StatCollector.func_74838_a("turretfocus.range");
/*  76 */       s = s.replaceFirst("%t", "" + (int)((ItemFocusBasic)this.turret.func_70694_bm().func_77973_b()).getTurretRange(this.turret.func_70694_bm()));
/*  77 */       func_73732_a(this.field_146289_q, s, (k + 44) * 2, (l + 9) * 2, 16777215);
/*  78 */       GL11.glPopMatrix();
/*     */     }
/*     */     
/*  81 */     GL11.glDisable(3042);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void func_146284_a(GuiButton button)
/*     */     throws IOException
/*     */   {
/*  89 */     if (button.field_146127_k == 1)
/*     */     {
/*  91 */       this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, 1);
/*     */ 
/*     */     }
/*  94 */     else if (button.field_146127_k == 2)
/*     */     {
/*  96 */       this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, 2);
/*     */ 
/*     */     }
/*  99 */     else if (button.field_146127_k == 3)
/*     */     {
/* 101 */       this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, 3);
/*     */ 
/*     */     }
/* 104 */     else if (button.field_146127_k == 4)
/*     */     {
/* 106 */       this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, 4);
/*     */     }
/*     */     else
/*     */     {
/* 110 */       super.func_146284_a(button);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/gui/GuiTurretFocus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */