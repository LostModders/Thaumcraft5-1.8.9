/*    */ package thaumcraft.common.entities.ai.misc;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.api.blocks.BlocksTC;
/*    */ 
/*    */ public class AIConvertGrass extends net.minecraft.entity.ai.EntityAIBase
/*    */ {
/*    */   private EntityLiving entity;
/*    */   private World world;
/* 16 */   int field_48399_a = 0;
/*    */   
/*    */   public AIConvertGrass(EntityLiving par1EntityLiving)
/*    */   {
/* 20 */     this.entity = par1EntityLiving;
/* 21 */     this.world = par1EntityLiving.field_70170_p;
/* 22 */     func_75248_a(7);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean func_75250_a()
/*    */   {
/* 30 */     if (this.entity.func_70681_au().nextInt(250) != 0)
/*    */     {
/* 32 */       return false;
/*    */     }
/*    */     
/*    */ 
/* 36 */     BlockPos bp = new BlockPos(this.entity);
/* 37 */     IBlockState bs = this.world.func_180495_p(bp);
/* 38 */     return (bs.func_177230_c() == Blocks.field_150329_H) && (bs.func_177230_c().func_176201_c(bs) == 1);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void func_75249_e()
/*    */   {
/* 49 */     this.field_48399_a = 40;
/* 50 */     this.world.func_72960_a(this.entity, (byte)10);
/* 51 */     this.entity.func_70661_as().func_75499_g();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void func_75251_c()
/*    */   {
/* 59 */     this.field_48399_a = 0;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean func_75253_b()
/*    */   {
/* 67 */     return this.field_48399_a > 0;
/*    */   }
/*    */   
/*    */   public int func_48396_h()
/*    */   {
/* 72 */     return this.field_48399_a;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void func_75246_d()
/*    */   {
/* 80 */     this.field_48399_a = Math.max(0, this.field_48399_a - 1);
/*    */     
/* 82 */     if (this.field_48399_a == 4)
/*    */     {
/* 84 */       BlockPos bp = new BlockPos(this.entity);
/* 85 */       IBlockState bs = this.world.func_180495_p(bp);
/*    */       
/* 87 */       if (bs.func_177230_c() == Blocks.field_150329_H)
/*    */       {
/* 89 */         this.world.func_175718_b(2001, bp, Block.func_149682_b(Blocks.field_150349_c) + 4096);
/* 90 */         this.world.func_175698_g(bp);
/* 91 */         this.world.func_175656_a(bp, BlocksTC.taintFibre.func_176223_P());
/* 92 */         this.entity.func_70615_aA();
/*    */       }
/* 94 */       else if (this.world.func_180495_p(bp.func_177977_b()).func_177230_c() == Blocks.field_150349_c)
/*    */       {
/* 96 */         this.world.func_175718_b(2001, bp.func_177977_b(), Block.func_149682_b(Blocks.field_150349_c));
/* 97 */         this.world.func_175656_a(bp, BlocksTC.taintFibre.func_176223_P());
/* 98 */         this.entity.func_70615_aA();
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/ai/misc/AIConvertGrass.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */