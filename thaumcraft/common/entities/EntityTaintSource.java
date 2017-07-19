/*     */ package thaumcraft.common.entities;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityTaintSource
/*     */   extends Entity
/*     */ {
/*  16 */   int lifespan = 100;
/*     */   
/*     */   public EntityTaintSource(World par1World)
/*     */   {
/*  20 */     super(par1World);
/*  21 */     this.field_70158_ak = true;
/*  22 */     this.field_70155_l = 10.0D;
/*  23 */     this.field_70156_m = true;
/*  24 */     this.field_70159_w = 0.0D;
/*  25 */     this.field_70181_x = 0.0D;
/*  26 */     this.field_70179_y = 0.0D;
/*  27 */     this.field_70178_ae = true;
/*  28 */     func_70105_a(0.98F, 0.98F);
/*     */   }
/*     */   
/*     */   public EntityTaintSource(World par1World, BlockPos pos, int lifespan)
/*     */   {
/*  33 */     this(par1World);
/*  34 */     func_70107_b(pos.func_177958_n() + 0.5D, pos.func_177956_o() + 0.5D, pos.func_177952_p() + 0.5D);
/*  35 */     this.field_70169_q = (pos.func_177958_n() + 0.5D);
/*  36 */     this.field_70167_r = (pos.func_177956_o() + 0.5D);
/*  37 */     this.field_70166_s = (pos.func_177952_p() + 0.5D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean func_70041_e_()
/*     */   {
/*  45 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_70088_a() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_70067_L()
/*     */   {
/*  58 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/*  66 */     super.func_70071_h_();
/*  67 */     if ((!this.field_70170_p.field_72995_K) && (this.lifespan-- < 0)) {
/*  68 */       func_70106_y();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70097_a(DamageSource source, float amount)
/*     */   {
/*  75 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_70104_M()
/*     */   {
/*  80 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_70075_an()
/*     */   {
/*  85 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_180430_e(float distance, float damageMultiplier) {}
/*     */   
/*     */ 
/*     */   protected void func_70014_b(NBTTagCompound nbt)
/*     */   {
/*  94 */     nbt.func_74768_a("lifespan", this.lifespan);
/*     */   }
/*     */   
/*     */   protected void func_70037_a(NBTTagCompound nbt)
/*     */   {
/*  99 */     this.lifespan = nbt.func_74762_e("lifespan");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_90999_ad()
/*     */   {
/* 107 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/EntityTaintSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */