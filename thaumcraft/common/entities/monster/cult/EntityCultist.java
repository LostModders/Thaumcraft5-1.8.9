/*     */ package thaumcraft.common.entities.monster.cult;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.pathfinding.PathNavigateGround;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.entities.monster.boss.EntityCultistLeader;
/*     */ 
/*     */ public class EntityCultist extends EntityMob
/*     */ {
/*     */   public EntityCultist(World p_i1745_1_)
/*     */   {
/*  26 */     super(p_i1745_1_);
/*  27 */     func_70105_a(0.6F, 1.8F);
/*  28 */     this.field_70728_aV = 10;
/*  29 */     ((PathNavigateGround)func_70661_as()).func_179688_b(true);
/*     */     
/*  31 */     for (int i = 0; i < this.field_82174_bp.length; i++)
/*     */     {
/*  33 */       this.field_82174_bp[i] = 0.05F;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_110147_ax()
/*     */   {
/*  40 */     super.func_110147_ax();
/*  41 */     func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(32.0D);
/*  42 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.3D);
/*  43 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(4.0D);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70088_a()
/*     */   {
/*  49 */     super.func_70088_a();
/*     */   }
/*     */   
/*     */   public boolean func_98052_bS()
/*     */   {
/*  54 */     return false;
/*     */   }
/*     */   
/*     */   protected boolean func_70814_o()
/*     */   {
/*  59 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   protected Item func_146068_u()
/*     */   {
/*  65 */     return Item.func_150899_d(0);
/*     */   }
/*     */   
/*     */   protected void func_70628_a(boolean flag, int i)
/*     */   {
/*  70 */     int r = this.field_70146_Z.nextInt(10);
/*  71 */     if (r <= 1) {
/*  72 */       func_70099_a(new ItemStack(ItemsTC.voidSeed), 1.5F);
/*     */     }
/*  74 */     else if (r <= 3 + i) {
/*  75 */       func_70099_a(new ItemStack(ItemsTC.coin), 1.5F);
/*     */     }
/*  77 */     super.func_70628_a(flag, i);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_82164_bB()
/*     */   {
/*  83 */     func_70099_a(new ItemStack(ItemsTC.knowledgeFragment), 1.0F);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_180481_a(DifficultyInstance diff) {}
/*     */   
/*     */ 
/*     */   protected void func_180483_b(DifficultyInstance diff) {}
/*     */   
/*     */   public IEntityLivingData func_180482_a(DifficultyInstance diff, IEntityLivingData data)
/*     */   {
/*  94 */     func_180481_a(diff);
/*  95 */     func_180483_b(diff);
/*     */     
/*  97 */     return super.func_180482_a(diff, data);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean func_70692_ba()
/*     */   {
/* 106 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound nbt)
/*     */   {
/* 115 */     super.func_70037_a(nbt);
/* 116 */     if (nbt.func_74764_b("HomeD")) {
/* 117 */       func_175449_a(new BlockPos(nbt.func_74762_e("HomeX"), nbt.func_74762_e("HomeY"), nbt.func_74762_e("HomeZ")), nbt.func_74762_e("HomeD"));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70014_b(NBTTagCompound nbt)
/*     */   {
/* 124 */     super.func_70014_b(nbt);
/* 125 */     if ((func_180486_cf() != null) && (func_110174_bM() > 0.0F)) {
/* 126 */       nbt.func_74768_a("HomeD", (int)func_110174_bM());
/* 127 */       nbt.func_74768_a("HomeX", func_180486_cf().func_177958_n());
/* 128 */       nbt.func_74768_a("HomeY", func_180486_cf().func_177956_o());
/* 129 */       nbt.func_74768_a("HomeZ", func_180486_cf().func_177952_p());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_142014_c(EntityLivingBase el)
/*     */   {
/* 136 */     return ((el instanceof EntityCultist)) || ((el instanceof EntityCultistLeader));
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70686_a(Class clazz)
/*     */   {
/* 142 */     if ((clazz == EntityCultistCleric.class) || (clazz == EntityCultistLeader.class) || (clazz == EntityCultistKnight.class))
/*     */     {
/*     */ 
/* 145 */       return false; }
/* 146 */     return super.func_70686_a(clazz);
/*     */   }
/*     */   
/*     */   public void func_70656_aK()
/*     */   {
/* 151 */     if (this.field_70170_p.field_72995_K) {
/* 152 */       for (int i = 0; i < 20; i++)
/*     */       {
/* 154 */         double d0 = this.field_70146_Z.nextGaussian() * 0.05D;
/* 155 */         double d1 = this.field_70146_Z.nextGaussian() * 0.05D;
/* 156 */         double d2 = this.field_70146_Z.nextGaussian() * 0.05D;
/* 157 */         double d3 = 2.0D;
/*     */         
/* 159 */         Thaumcraft.proxy.getFX().cultistSpawn(this.field_70165_t + this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F - this.field_70130_N + d0 * d3, this.field_70163_u + this.field_70146_Z.nextFloat() * this.field_70131_O + d1 * d3, this.field_70161_v + this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F - this.field_70130_N + d2 * d3, d0, d1, d2);
/*     */       }
/*     */       
/*     */     }
/*     */     else
/*     */     {
/* 165 */       this.field_70170_p.func_72960_a(this, (byte)20);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/monster/cult/EntityCultist.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */