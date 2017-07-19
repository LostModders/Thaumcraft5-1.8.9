/*    */ package thaumcraft.common.entities.ai.misc;
/*    */ 
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.entity.ai.EntityAIBase;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.common.entities.monster.cult.EntityCultistCleric;
/*    */ 
/*    */ public class AIAltarFocus extends EntityAIBase
/*    */ {
/*    */   private EntityCultistCleric entity;
/*    */   private World world;
/* 12 */   int field_48399_a = 0;
/*    */   
/*    */   public AIAltarFocus(EntityCultistCleric par1EntityLiving)
/*    */   {
/* 16 */     this.entity = par1EntityLiving;
/* 17 */     this.world = par1EntityLiving.field_70170_p;
/* 18 */     func_75248_a(7);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean func_75250_a()
/*    */   {
/* 27 */     if ((!this.entity.getIsRitualist()) || (this.entity.func_180486_cf() == null))
/*    */     {
/* 29 */       return false;
/*    */     }
/*    */     
/*    */ 
/* 33 */     return true;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void func_75249_e() {}
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void func_75251_c() {}
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean func_75253_b()
/*    */   {
/* 60 */     return (this.entity.getIsRitualist()) && (this.entity.func_180486_cf() != null);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void func_75246_d()
/*    */   {
/* 69 */     if (this.entity.func_180486_cf() != null)
/*    */     {
/* 71 */       if ((this.entity.field_70173_aa % 40 == 0) && ((this.entity.func_180486_cf().func_177954_c(this.entity.field_70165_t, this.entity.field_70163_u, this.entity.field_70161_v) > 16.0D) || (this.world.func_180495_p(this.entity.func_180486_cf()).func_177230_c() != thaumcraft.api.blocks.BlocksTC.eldritch)))
/*    */       {
/* 73 */         this.entity.setIsRitualist(false);
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/ai/misc/AIAltarFocus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */