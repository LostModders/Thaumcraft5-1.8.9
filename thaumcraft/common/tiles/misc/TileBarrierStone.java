/*    */ package thaumcraft.common.tiles.misc;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.ITickable;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.api.blocks.BlocksTC;
/*    */ 
/*    */ public class TileBarrierStone extends net.minecraft.tileentity.TileEntity implements ITickable
/*    */ {
/* 17 */   int count = 0;
/*    */   
/*    */   public boolean gettingPower() {
/* 20 */     return this.field_145850_b.func_175687_A(this.field_174879_c) > 0;
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_73660_a()
/*    */   {
/* 26 */     if (!this.field_145850_b.field_72995_K) {
/* 27 */       if (this.count == 0) { this.count = this.field_145850_b.field_73012_v.nextInt(100);
/*    */       }
/* 29 */       if ((this.count % 5 == 0) && (!gettingPower())) {
/* 30 */         List<EntityLivingBase> targets = this.field_145850_b.func_72872_a(EntityLivingBase.class, AxisAlignedBB.func_178781_a(this.field_174879_c.func_177958_n(), this.field_174879_c.func_177956_o(), this.field_174879_c.func_177952_p(), this.field_174879_c.func_177958_n() + 1, this.field_174879_c.func_177956_o() + 3, this.field_174879_c.func_177952_p() + 1).func_72314_b(0.1D, 0.1D, 0.1D));
/*    */         
/* 32 */         if (targets.size() > 0) {
/* 33 */           for (EntityLivingBase e : targets) {
/* 34 */             if ((!e.field_70122_E) && (!(e instanceof EntityPlayer))) {
/* 35 */               e.func_70024_g(-MathHelper.func_76126_a((e.field_70177_z + 180.0F) * 3.1415927F / 180.0F) * 0.2F, -0.1D, MathHelper.func_76134_b((e.field_70177_z + 180.0F) * 3.1415927F / 180.0F) * 0.2F);
/*    */             }
/*    */           }
/*    */         }
/*    */       }
/* 40 */       if (++this.count % 100 == 0) {
/* 41 */         if ((this.field_145850_b.func_180495_p(this.field_174879_c.func_177981_b(1)) != BlocksTC.barrier.func_176223_P()) && (this.field_145850_b.func_175623_d(this.field_174879_c.func_177981_b(1)))) {
/* 42 */           this.field_145850_b.func_180501_a(this.field_174879_c.func_177981_b(1), BlocksTC.barrier.func_176223_P(), 3);
/*    */         }
/* 44 */         if ((this.field_145850_b.func_180495_p(this.field_174879_c.func_177981_b(2)) != BlocksTC.barrier.func_176223_P()) && (this.field_145850_b.func_175623_d(this.field_174879_c.func_177981_b(2)))) {
/* 45 */           this.field_145850_b.func_180501_a(this.field_174879_c.func_177981_b(2), BlocksTC.barrier.func_176223_P(), 3);
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/tiles/misc/TileBarrierStone.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */