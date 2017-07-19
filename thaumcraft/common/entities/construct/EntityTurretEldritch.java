/*     */ package thaumcraft.common.entities.construct;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.EnumCreatureAttribute;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIHurtByTarget;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.entities.IEldritchMob;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ 
/*     */ public class EntityTurretEldritch extends EntityTurretFocus implements IEldritchMob
/*     */ {
/*  25 */   private static final Predicate attackEntitySelector = new Predicate()
/*     */   {
/*     */     public boolean func_180027_a(Entity p_180027_1_)
/*     */     {
/*  29 */       return ((p_180027_1_ instanceof EntityLivingBase)) && (!(p_180027_1_ instanceof IEldritchMob)) && (((EntityLivingBase)p_180027_1_).func_70668_bt() != EnumCreatureAttribute.UNDEAD);
/*     */     }
/*     */     
/*     */     public boolean apply(Object p_apply_1_)
/*     */     {
/*  34 */       return func_180027_a((Entity)p_apply_1_);
/*     */     }
/*     */   };
/*     */   
/*     */   public EntityTurretEldritch(World worldIn) {
/*  39 */     super(worldIn);
/*  40 */     this.field_70715_bh.field_75782_a.clear();
/*  41 */     this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, false, new Class[0]));
/*  42 */     this.field_70715_bh.func_75776_a(2, new net.minecraft.entity.ai.EntityAINearestAttackableTarget(this, EntityLivingBase.class, 5, true, false, attackEntitySelector));
/*  43 */     this.maxVis = 50;
/*  44 */     this.field_70728_aV = 10;
/*     */   }
/*     */   
/*     */   public EntityTurretEldritch(World worldIn, BlockPos pos, EnumFacing face) {
/*  48 */     super(worldIn, pos, face);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70686_a(Class clazz)
/*     */   {
/*  54 */     return !IEldritchMob.class.isAssignableFrom(clazz);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_142014_c(EntityLivingBase otherEntity)
/*     */   {
/*  60 */     return ((otherEntity instanceof IEldritchMob)) || (func_142012_a(otherEntity.func_96124_cp()));
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_110147_ax()
/*     */   {
/*  66 */     super.func_110147_ax();
/*  67 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(55.0D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int func_70658_aO()
/*     */   {
/*  74 */     return 8;
/*     */   }
/*     */   
/*     */   protected void doUpdateStuff() {
/*  78 */     if ((func_70638_az() != null) && ((func_70638_az() instanceof IEldritchMob))) { func_70624_b(null);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   protected void dropFocus() {}
/*     */   
/*     */ 
/*     */   public IEntityLivingData func_180482_a(DifficultyInstance diff, IEntityLivingData data)
/*     */   {
/*  88 */     for (Aspect aspect : ) {
/*  89 */       this.vis.add(aspect, 10 + this.field_70146_Z.nextInt(10));
/*     */     }
/*     */     
/*  92 */     switch (this.field_70146_Z.nextInt(10)) {
/*  93 */     case 4: case 5:  func_70062_b(0, new ItemStack(ItemsTC.focusShock)); break;
/*  94 */     case 6: case 7:  func_70062_b(0, new ItemStack(ItemsTC.focusFrost)); break;
/*  95 */     case 8: case 9:  func_70062_b(0, new ItemStack(ItemsTC.focusFire)); break;
/*  96 */     default:  func_70062_b(0, new ItemStack(ItemsTC.focusShard));
/*     */     }
/*     */     
/*  99 */     updateFocus();
/*     */     
/* 101 */     return super.func_180482_a(diff, data);
/*     */   }
/*     */   
/*     */   public boolean func_70085_c(EntityPlayer player)
/*     */   {
/* 106 */     return false;
/*     */   }
/*     */   
/*     */   protected void func_70628_a(boolean p_70628_1_, int p_70628_2_)
/*     */   {
/* 111 */     float b = p_70628_2_ * 0.15F;
/* 112 */     if (this.field_70146_Z.nextFloat() < 0.3F + b) func_70099_a(new ItemStack(ItemsTC.brain), 0.5F);
/* 113 */     if (this.field_70146_Z.nextFloat() < 0.3F + b) func_70099_a(new ItemStack(ItemsTC.ingots, 1, 1), 0.5F);
/* 114 */     if (this.field_70146_Z.nextFloat() < 0.3F + b) func_70099_a(new ItemStack(ItemsTC.ingots, 1, 1), 0.5F);
/* 115 */     if ((this.field_70146_Z.nextFloat() < 0.15F + b / 2.0F) && (func_70694_bm() != null)) func_70099_a(func_70694_bm(), 0.5F);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/construct/EntityTurretEldritch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */