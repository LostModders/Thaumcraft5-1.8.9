/*     */ package thaumcraft.common.entities.monster;
/*     */ 
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.monster.EntitySpider;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityMindSpider extends EntitySpider implements thaumcraft.api.entities.IEldritchMob
/*     */ {
/*     */   public EntityMindSpider(World par1World)
/*     */   {
/*  18 */     super(par1World);
/*  19 */     func_70105_a(0.7F, 0.5F);
/*  20 */     this.field_70728_aV = 1;
/*     */   }
/*     */   
/*     */ 
/*     */   public float func_70047_e()
/*     */   {
/*  26 */     return 0.45F;
/*     */   }
/*     */   
/*  29 */   private int lifeSpan = Integer.MAX_VALUE;
/*     */   
/*     */   protected int func_70693_a(EntityPlayer p_70693_1_)
/*     */   {
/*  33 */     return isHarmless() ? 0 : super.func_70693_a(p_70693_1_);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_110147_ax()
/*     */   {
/*  39 */     super.func_110147_ax();
/*  40 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(1.0D);
/*  41 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0D);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70088_a()
/*     */   {
/*  47 */     super.func_70088_a();
/*  48 */     this.field_70180_af.func_75682_a(22, new Byte((byte)0));
/*  49 */     this.field_70180_af.func_75682_a(23, new String(""));
/*     */   }
/*     */   
/*     */   public String getViewer()
/*     */   {
/*  54 */     return this.field_70180_af.func_75681_e(23);
/*     */   }
/*     */   
/*     */   public void setViewer(String player) {
/*  58 */     this.field_70180_af.func_75692_b(23, String.valueOf(player));
/*     */   }
/*     */   
/*     */   public boolean isHarmless()
/*     */   {
/*  63 */     return this.field_70180_af.func_75683_a(22) != 0;
/*     */   }
/*     */   
/*     */   public void setHarmless(boolean h) {
/*  67 */     if (h) this.lifeSpan = 1200;
/*  68 */     this.field_70180_af.func_75692_b(22, Byte.valueOf((byte)(h ? 1 : 0)));
/*     */   }
/*     */   
/*     */   protected float func_70647_i()
/*     */   {
/*  73 */     return 0.7F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/*  80 */     super.func_70071_h_();
/*  81 */     if ((!this.field_70170_p.field_72995_K) && (this.field_70173_aa > this.lifeSpan)) { func_70106_y();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   protected Item func_146068_u()
/*     */   {
/*  88 */     return Item.func_150899_d(0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void func_70628_a(boolean p_70628_1_, int p_70628_2_) {}
/*     */   
/*     */ 
/*     */   public boolean func_145773_az()
/*     */   {
/*  98 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   protected boolean func_70041_e_()
/*     */   {
/* 104 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_70652_k(net.minecraft.entity.Entity p_70652_1_)
/*     */   {
/* 109 */     if (isHarmless()) {
/* 110 */       return false;
/*     */     }
/* 112 */     return super.func_70652_k(p_70652_1_);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 118 */     super.func_70037_a(par1NBTTagCompound);
/* 119 */     this.field_70180_af.func_75692_b(22, Byte.valueOf(par1NBTTagCompound.func_74771_c("harmless")));
/* 120 */     this.field_70180_af.func_75692_b(23, String.valueOf(par1NBTTagCompound.func_74779_i("viewer")));
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70014_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 126 */     super.func_70014_b(par1NBTTagCompound);
/* 127 */     par1NBTTagCompound.func_74774_a("harmless", this.field_70180_af.func_75683_a(22));
/* 128 */     par1NBTTagCompound.func_74778_a("viewer", this.field_70180_af.func_75681_e(23));
/*     */   }
/*     */   
/*     */ 
/*     */   public IEntityLivingData func_180482_a(DifficultyInstance p_180482_1_, IEntityLivingData p_180482_2_)
/*     */   {
/* 134 */     return p_180482_2_;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/monster/EntityMindSpider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */