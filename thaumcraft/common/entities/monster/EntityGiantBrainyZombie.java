/*    */ package thaumcraft.common.entities.monster;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.DataWatcher;
/*    */ import net.minecraft.entity.SharedMonsterAttributes;
/*    */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class EntityGiantBrainyZombie extends EntityBrainyZombie
/*    */ {
/*    */   public EntityGiantBrainyZombie(World world)
/*    */   {
/* 15 */     super(world);
/* 16 */     this.field_70728_aV = 15;
/*    */     
/* 18 */     func_70105_a(this.field_70130_N * (1.2F + getAnger()), this.field_70131_O * (1.2F + getAnger()));
/*    */     
/* 20 */     this.field_70714_bg.func_75776_a(2, new net.minecraft.entity.ai.EntityAILeapAtTarget(this, 0.4F));
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_70636_d()
/*    */   {
/* 26 */     super.func_70636_d();
/* 27 */     if (getAnger() > 1.0F) {
/* 28 */       setAnger(getAnger() - 0.002F);
/* 29 */       func_70105_a(0.6F * (1.2F + getAnger()), 1.8F * (1.2F + getAnger()));
/*    */     }
/*    */     
/* 32 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(7.0F + (getAnger() - 1.0F) * 5.0F);
/*    */   }
/*    */   
/*    */ 
/*    */   protected void func_70088_a()
/*    */   {
/* 38 */     super.func_70088_a();
/* 39 */     this.field_70180_af.func_75682_a(20, new Float(1.0F));
/*    */   }
/*    */   
/*    */   public float getAnger()
/*    */   {
/* 44 */     return this.field_70180_af.func_111145_d(20);
/*    */   }
/*    */   
/*    */   public void setAnger(float par1)
/*    */   {
/* 49 */     this.field_70180_af.func_75692_b(20, Float.valueOf(par1));
/*    */   }
/*    */   
/*    */   public boolean func_70097_a(DamageSource par1DamageSource, float par2)
/*    */   {
/* 54 */     setAnger(Math.min(2.0F, getAnger() + 0.1F));
/* 55 */     return super.func_70097_a(par1DamageSource, par2);
/*    */   }
/*    */   
/*    */ 
/*    */   protected void func_110147_ax()
/*    */   {
/* 61 */     super.func_110147_ax();
/* 62 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(60.0D);
/* 63 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(7.0D);
/*    */   }
/*    */   
/*    */ 
/*    */   protected void func_70628_a(boolean flag, int i)
/*    */   {
/* 69 */     for (int a = 0; a < 6; a++) if (this.field_70170_p.field_73012_v.nextBoolean()) func_145779_a(Items.field_151078_bh, 2);
/* 70 */     for (int a = 0; a < 6; a++) if (this.field_70170_p.field_73012_v.nextBoolean()) func_145779_a(Items.field_151078_bh, 2);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/monster/EntityGiantBrainyZombie.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */