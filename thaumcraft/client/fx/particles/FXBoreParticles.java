/*     */ package thaumcraft.client.fx.particles;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.particle.EntityFX;
/*     */ import net.minecraft.client.renderer.ItemModelMesher;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.renderer.entity.RenderItem;
/*     */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*     */ import net.minecraft.client.settings.GameSettings;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.client.FMLClientHandler;
/*     */ 
/*     */ @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*     */ public class FXBoreParticles extends EntityFX
/*     */ {
/*     */   private Block blockInstance;
/*     */   private Item itemInstance;
/*     */   private int metadata;
/*     */   private int side;
/*     */   private double targetX;
/*     */   private double targetY;
/*     */   private double targetZ;
/*     */   
/*     */   public FXBoreParticles(World par1World, double par2, double par4, double par6, double tx, double ty, double tz, Block par14Block, int par15)
/*     */   {
/*  33 */     super(par1World, par2, par4, par6, 0.0D, 0.0D, 0.0D);
/*  34 */     this.blockInstance = par14Block;
/*     */     try {
/*  36 */       func_180435_a(Minecraft.func_71410_x().func_175599_af().func_175037_a().func_178087_a(Item.func_150898_a(par14Block), par15));
/*     */     } catch (Exception e) {
/*  38 */       func_180435_a(Minecraft.func_71410_x().func_175599_af().func_175037_a().func_178087_a(Item.func_150898_a(Blocks.field_150348_b), 0));
/*  39 */       this.field_70547_e = 0;
/*     */     }
/*     */     
/*  42 */     this.field_70545_g = par14Block.field_149763_I;
/*  43 */     this.field_70552_h = (this.field_70553_i = this.field_70551_j = 0.6F);
/*  44 */     this.field_70544_f = (this.field_70146_Z.nextFloat() * 0.3F + 0.4F);
/*  45 */     this.side = par15;
/*     */     
/*  47 */     this.targetX = tx;
/*  48 */     this.targetY = ty;
/*  49 */     this.targetZ = tz;
/*     */     
/*  51 */     double dx = tx - this.field_70165_t;
/*  52 */     double dy = ty - this.field_70163_u;
/*  53 */     double dz = tz - this.field_70161_v;
/*  54 */     int base = (int)(MathHelper.func_76133_a(dx * dx + dy * dy + dz * dz) * 3.0F);
/*  55 */     if (base < 1) base = 1;
/*  56 */     this.field_70547_e = (base / 2 + this.field_70146_Z.nextInt(base));
/*     */     
/*  58 */     float f3 = 0.01F;
/*  59 */     this.field_70159_w = ((float)this.field_70170_p.field_73012_v.nextGaussian() * f3);
/*  60 */     this.field_70181_x = ((float)this.field_70170_p.field_73012_v.nextGaussian() * f3);
/*  61 */     this.field_70179_y = ((float)this.field_70170_p.field_73012_v.nextGaussian() * f3);
/*     */     
/*     */ 
/*  64 */     this.field_70545_g = 0.2F;
/*  65 */     this.field_70145_X = false;
/*     */     
/*  67 */     Entity renderentity = FMLClientHandler.instance().getClient().func_175606_aa();
/*  68 */     int visibleDistance = 64;
/*  69 */     if (!FMLClientHandler.instance().getClient().field_71474_y.field_74347_j) visibleDistance = 32;
/*  70 */     if (renderentity.func_70011_f(this.field_70165_t, this.field_70163_u, this.field_70161_v) > visibleDistance) { this.field_70547_e = 0;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public FXBoreParticles(World par1World, double par2, double par4, double par6, double tx, double ty, double tz, Item item, int par15)
/*     */   {
/*  77 */     super(par1World, par2, par4, par6, 0.0D, 0.0D, 0.0D);
/*  78 */     this.itemInstance = item;
/*  79 */     func_180435_a(Minecraft.func_71410_x().func_175599_af().func_175037_a().func_178087_a(item, par15));
/*  80 */     this.field_70545_g = Blocks.field_150431_aC.field_149763_I;
/*  81 */     this.field_70552_h = (this.field_70553_i = this.field_70551_j = 0.6F);
/*  82 */     this.field_70544_f = (this.field_70146_Z.nextFloat() * 0.3F + 0.4F);
/*  83 */     this.side = par15;
/*     */     
/*  85 */     this.targetX = tx;
/*  86 */     this.targetY = ty;
/*  87 */     this.targetZ = tz;
/*     */     
/*  89 */     double dx = tx - this.field_70165_t;
/*  90 */     double dy = ty - this.field_70163_u;
/*  91 */     double dz = tz - this.field_70161_v;
/*  92 */     int base = (int)(MathHelper.func_76133_a(dx * dx + dy * dy + dz * dz) * 3.0F);
/*  93 */     if (base < 1) base = 1;
/*  94 */     this.field_70547_e = (base / 2 + this.field_70146_Z.nextInt(base));
/*     */     
/*  96 */     float f3 = 0.01F;
/*  97 */     this.field_70159_w = ((float)this.field_70170_p.field_73012_v.nextGaussian() * f3);
/*  98 */     this.field_70181_x = ((float)this.field_70170_p.field_73012_v.nextGaussian() * f3);
/*  99 */     this.field_70179_y = ((float)this.field_70170_p.field_73012_v.nextGaussian() * f3);
/*     */     
/*     */ 
/* 102 */     this.field_70545_g = 0.2F;
/* 103 */     this.field_70145_X = false;
/*     */     
/* 105 */     Entity renderentity = FMLClientHandler.instance().getClient().func_175606_aa();
/* 106 */     int visibleDistance = 64;
/* 107 */     if (!FMLClientHandler.instance().getClient().field_71474_y.field_74347_j) visibleDistance = 32;
/* 108 */     if (renderentity.func_70011_f(this.field_70165_t, this.field_70163_u, this.field_70161_v) > visibleDistance) { this.field_70547_e = 0;
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_70071_h_()
/*     */   {
/* 114 */     this.field_70169_q = this.field_70165_t;
/* 115 */     this.field_70167_r = this.field_70163_u;
/* 116 */     this.field_70166_s = this.field_70161_v;
/*     */     
/*     */ 
/*     */ 
/* 120 */     if ((this.field_70546_d++ >= this.field_70547_e) || ((MathHelper.func_76128_c(this.field_70165_t) == MathHelper.func_76128_c(this.targetX)) && (MathHelper.func_76128_c(this.field_70163_u) == MathHelper.func_76128_c(this.targetY)) && (MathHelper.func_76128_c(this.field_70161_v) == MathHelper.func_76128_c(this.targetZ))))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/* 125 */       func_70106_y();
/* 126 */       return;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 131 */     if (!this.field_70145_X) func_145771_j(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/* 132 */     func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
/*     */     
/* 134 */     this.field_70159_w *= 0.985D;
/* 135 */     this.field_70181_x *= 0.985D;
/* 136 */     this.field_70179_y *= 0.985D;
/*     */     
/* 138 */     double dx = this.targetX - this.field_70165_t;
/* 139 */     double dy = this.targetY - this.field_70163_u;
/* 140 */     double dz = this.targetZ - this.field_70161_v;
/* 141 */     double d13 = 0.3D;
/* 142 */     double d11 = MathHelper.func_76133_a(dx * dx + dy * dy + dz * dz);
/*     */     
/* 144 */     if (d11 < 4.0D) {
/* 145 */       this.field_70544_f *= 0.9F;
/* 146 */       d13 = 0.6D;
/*     */     }
/*     */     
/* 149 */     dx /= d11;
/* 150 */     dy /= d11;
/* 151 */     dz /= d11;
/*     */     
/* 153 */     this.field_70159_w += dx * d13;
/* 154 */     this.field_70181_x += dy * d13;
/* 155 */     this.field_70179_y += dz * d13;
/*     */     
/* 157 */     this.field_70159_w = MathHelper.func_76131_a((float)this.field_70159_w, -0.35F, 0.35F);
/* 158 */     this.field_70181_x = MathHelper.func_76131_a((float)this.field_70181_x, -0.35F, 0.35F);
/* 159 */     this.field_70179_y = MathHelper.func_76131_a((float)this.field_70179_y, -0.35F, 0.35F);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int func_70537_b()
/*     */   {
/* 166 */     return 1;
/*     */   }
/*     */   
/*     */ 
/*     */   public FXBoreParticles getObjectColor(BlockPos pos)
/*     */   {
/* 172 */     if ((this.blockInstance != null) && (this.field_70170_p.func_180495_p(pos) == this.blockInstance)) {
/* 173 */       if ((this.blockInstance == Blocks.field_150349_c) && (this.side != 1))
/*     */       {
/* 175 */         return this;
/*     */       }
/*     */       
/*     */ 
/*     */       try
/*     */       {
/* 181 */         int var4 = this.blockInstance.func_176202_d(this.field_70170_p, pos);
/* 182 */         this.field_70552_h *= (var4 >> 16 & 0xFF) / 255.0F;
/* 183 */         this.field_70553_i *= (var4 >> 8 & 0xFF) / 255.0F;
/* 184 */         this.field_70551_j *= (var4 & 0xFF) / 255.0F;
/*     */       } catch (Exception e) {}
/* 186 */       return this;
/*     */     }
/*     */     try
/*     */     {
/* 190 */       int var4 = this.itemInstance.func_82790_a(new net.minecraft.item.ItemStack(this.itemInstance, 1, this.metadata), 0);
/* 191 */       this.field_70552_h *= (var4 >> 16 & 0xFF) / 255.0F;
/* 192 */       this.field_70553_i *= (var4 >> 8 & 0xFF) / 255.0F;
/* 193 */       this.field_70551_j *= (var4 & 0xFF) / 255.0F;
/*     */     } catch (Exception e) {}
/* 195 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_180434_a(WorldRenderer p_180434_1_, Entity p_180434_2_, float p_180434_3_, float p_180434_4_, float p_180434_5_, float p_180434_6_, float p_180434_7_, float p_180434_8_)
/*     */   {
/* 204 */     float f6 = (this.field_94054_b + this.field_70548_b / 4.0F) / 16.0F;
/* 205 */     float f7 = f6 + 0.015609375F;
/* 206 */     float f8 = (this.field_94055_c + this.field_70549_c / 4.0F) / 16.0F;
/* 207 */     float f9 = f8 + 0.015609375F;
/* 208 */     float f10 = 0.1F * this.field_70544_f;
/*     */     
/* 210 */     if (this.field_70550_a != null)
/*     */     {
/* 212 */       f6 = this.field_70550_a.func_94214_a(this.field_70548_b / 4.0F * 16.0F);
/* 213 */       f7 = this.field_70550_a.func_94214_a((this.field_70548_b + 1.0F) / 4.0F * 16.0F);
/* 214 */       f8 = this.field_70550_a.func_94207_b(this.field_70549_c / 4.0F * 16.0F);
/* 215 */       f9 = this.field_70550_a.func_94207_b((this.field_70549_c + 1.0F) / 4.0F * 16.0F);
/*     */     }
/* 217 */     int i = func_70070_b(p_180434_3_);
/* 218 */     int j = i >> 16 & 0xFFFF;
/* 219 */     int k = i & 0xFFFF;
/* 220 */     float f11 = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * p_180434_3_ - field_70556_an);
/* 221 */     float f12 = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * p_180434_3_ - field_70554_ao);
/* 222 */     float f13 = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * p_180434_3_ - field_70555_ap);
/* 223 */     p_180434_1_.func_181662_b(f11 - p_180434_4_ * f10 - p_180434_7_ * f10, f12 - p_180434_5_ * f10, f13 - p_180434_6_ * f10 - p_180434_8_ * f10).func_181673_a(f6, f9).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, 1.0F).func_181671_a(j, k).func_181675_d();
/*     */     
/* 225 */     p_180434_1_.func_181662_b(f11 - p_180434_4_ * f10 + p_180434_7_ * f10, f12 + p_180434_5_ * f10, f13 - p_180434_6_ * f10 + p_180434_8_ * f10).func_181673_a(f6, f8).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, 1.0F).func_181671_a(j, k).func_181675_d();
/*     */     
/* 227 */     p_180434_1_.func_181662_b(f11 + p_180434_4_ * f10 + p_180434_7_ * f10, f12 + p_180434_5_ * f10, f13 + p_180434_6_ * f10 + p_180434_8_ * f10).func_181673_a(f7, f8).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, 1.0F).func_181671_a(j, k).func_181675_d();
/*     */     
/* 229 */     p_180434_1_.func_181662_b(f11 + p_180434_4_ * f10 - p_180434_7_ * f10, f12 - p_180434_5_ * f10, f13 + p_180434_6_ * f10 - p_180434_8_ * f10).func_181673_a(f7, f9).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, 1.0F).func_181671_a(j, k).func_181675_d();
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/fx/particles/FXBoreParticles.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */