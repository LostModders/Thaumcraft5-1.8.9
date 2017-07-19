/*    */ package thaumcraft.common.entities.monster;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.SharedMonsterAttributes;
/*    */ import net.minecraft.entity.ai.EntityAIHurtByTarget;
/*    */ import net.minecraft.entity.ai.EntityAITasks;
/*    */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*    */ import net.minecraft.entity.monster.EntityZombie;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.api.items.ItemsTC;
/*    */ 
/*    */ public class EntityBrainyZombie extends EntityZombie
/*    */ {
/*    */   protected void func_110147_ax()
/*    */   {
/* 17 */     super.func_110147_ax();
/* 18 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(25.0D);
/* 19 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(5.0D);
/* 20 */     func_110148_a(field_110186_bp).func_111128_a(0.0D);
/*    */   }
/*    */   
/*    */   public EntityBrainyZombie(World world)
/*    */   {
/* 25 */     super(world);
/* 26 */     this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, false, new Class[0]));
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public int func_70658_aO()
/*    */   {
/* 35 */     int var1 = super.func_70658_aO() + 3;
/*    */     
/* 37 */     if (var1 > 20)
/*    */     {
/* 39 */       var1 = 20;
/*    */     }
/*    */     
/* 42 */     return var1;
/*    */   }
/*    */   
/*    */   protected void func_70628_a(boolean flag, int i)
/*    */   {
/* 47 */     for (int a = 0; a < 3; a++) if (this.field_70170_p.field_73012_v.nextBoolean()) func_145779_a(Items.field_151078_bh, 1);
/* 48 */     if (this.field_70170_p.field_73012_v.nextInt(10) - i <= 4) func_70099_a(new net.minecraft.item.ItemStack(ItemsTC.brain), 1.5F);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/monster/EntityBrainyZombie.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */