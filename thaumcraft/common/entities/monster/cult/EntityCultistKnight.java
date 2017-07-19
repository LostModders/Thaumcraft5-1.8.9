/*    */ package thaumcraft.common.entities.monster.cult;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.enchantment.EnchantmentHelper;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.SharedMonsterAttributes;
/*    */ import net.minecraft.entity.ai.EntityAILookIdle;
/*    */ import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
/*    */ import net.minecraft.entity.ai.EntityAIOpenDoor;
/*    */ import net.minecraft.entity.ai.EntityAIRestrictOpenDoor;
/*    */ import net.minecraft.entity.ai.EntityAISwimming;
/*    */ import net.minecraft.entity.ai.EntityAITasks;
/*    */ import net.minecraft.entity.ai.EntityAIWander;
/*    */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*    */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.world.DifficultyInstance;
/*    */ import net.minecraft.world.EnumDifficulty;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.api.items.ItemsTC;
/*    */ import thaumcraft.common.entities.ai.combat.AIAttackOnCollide;
/*    */ import thaumcraft.common.entities.ai.combat.AICultistHurtByTarget;
/*    */ 
/*    */ public class EntityCultistKnight extends EntityCultist
/*    */ {
/*    */   public EntityCultistKnight(World p_i1745_1_)
/*    */   {
/* 30 */     super(p_i1745_1_);
/* 31 */     this.field_70714_bg.func_75776_a(0, new EntityAISwimming(this));
/* 32 */     this.field_70714_bg.func_75776_a(3, new AIAttackOnCollide(this, EntityLivingBase.class, 1.0D, false));
/* 33 */     this.field_70714_bg.func_75776_a(4, new EntityAIRestrictOpenDoor(this));
/* 34 */     this.field_70714_bg.func_75776_a(5, new EntityAIOpenDoor(this, true));
/* 35 */     this.field_70714_bg.func_75776_a(6, new EntityAIMoveTowardsRestriction(this, 0.8D));
/* 36 */     this.field_70714_bg.func_75776_a(7, new EntityAIWander(this, 0.8D));
/* 37 */     this.field_70714_bg.func_75776_a(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
/* 38 */     this.field_70714_bg.func_75776_a(8, new EntityAILookIdle(this));
/* 39 */     this.field_70715_bh.func_75776_a(1, new AICultistHurtByTarget(this, true));
/* 40 */     this.field_70715_bh.func_75776_a(2, new net.minecraft.entity.ai.EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   protected void func_110147_ax()
/*    */   {
/* 48 */     super.func_110147_ax();
/* 49 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(30.0D);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected void func_180481_a(DifficultyInstance diff)
/*    */   {
/* 58 */     func_70062_b(4, new ItemStack(ItemsTC.crimsonPlateHelm));
/* 59 */     func_70062_b(3, new ItemStack(ItemsTC.crimsonPlateChest));
/* 60 */     func_70062_b(2, new ItemStack(ItemsTC.crimsonPlateLegs));
/* 61 */     func_70062_b(1, new ItemStack(ItemsTC.crimsonBoots));
/*    */     
/* 63 */     if (this.field_70146_Z.nextFloat() < (this.field_70170_p.func_175659_aa() == EnumDifficulty.HARD ? 0.05F : 0.01F))
/*    */     {
/* 65 */       int i = this.field_70146_Z.nextInt(5);
/* 66 */       if (i == 0)
/*    */       {
/* 68 */         func_70062_b(0, new ItemStack(ItemsTC.voidSword));
/* 69 */         func_70062_b(4, new ItemStack(ItemsTC.crimsonRobeHelm));
/*    */       }
/*    */       else
/*    */       {
/* 73 */         func_70062_b(0, new ItemStack(ItemsTC.thaumiumSword));
/* 74 */         if (this.field_70146_Z.nextBoolean()) {
/* 75 */           func_70062_b(4, null);
/*    */         }
/*    */       }
/*    */     } else {
/* 79 */       func_70062_b(0, new ItemStack(Items.field_151040_l));
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   protected void func_180483_b(DifficultyInstance diff)
/*    */   {
/* 86 */     float f = diff.func_180170_c();
/* 87 */     if ((func_70694_bm() != null) && (this.field_70146_Z.nextFloat() < 0.25F * f))
/*    */     {
/* 89 */       EnchantmentHelper.func_77504_a(this.field_70146_Z, func_70694_bm(), (int)(5.0F + f * this.field_70146_Z.nextInt(18)));
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/monster/cult/EntityCultistKnight.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */