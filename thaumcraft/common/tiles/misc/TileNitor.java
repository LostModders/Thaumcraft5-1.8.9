/*    */ package thaumcraft.common.tiles.misc;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.material.MapColor;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.item.EnumDyeColor;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.ITickable;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.api.blocks.BlocksTC;
/*    */ import thaumcraft.client.fx.FXDispatcher;
/*    */ import thaumcraft.common.CommonProxy;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ import thaumcraft.common.blocks.basic.BlockNitor;
/*    */ 
/*    */ public class TileNitor extends net.minecraft.tileentity.TileEntity implements ITickable
/*    */ {
/*    */   public void func_73660_a()
/*    */   {
/* 20 */     if (this.field_145850_b.field_72995_K) {
/* 21 */       IBlockState state = this.field_145850_b.func_180495_p(func_174877_v());
/* 22 */       if (state.func_177230_c() == BlocksTC.nitor) {
/* 23 */         if (this.field_145850_b.field_73012_v.nextInt(9 - Thaumcraft.proxy.getFX().particleCount(2)) == 0) {
/* 24 */           Thaumcraft.proxy.getFX().wispFX5(this.field_174879_c.func_177958_n() + 0.5F, this.field_174879_c.func_177956_o() + 0.5F, this.field_174879_c.func_177952_p() + 0.5F, this.field_174879_c.func_177958_n() + 0.3F + this.field_145850_b.field_73012_v.nextFloat() * 0.4F, this.field_174879_c.func_177956_o() + 0.5F, this.field_174879_c.func_177952_p() + 0.3F + this.field_145850_b.field_73012_v.nextFloat() * 0.4F, 0.5F, true, -0.025F, ((EnumDyeColor)state.func_177229_b(BlockNitor.COLOR)).func_176768_e().field_76291_p);
/*    */         }
/*    */         
/*    */ 
/*    */ 
/* 29 */         if (this.field_145850_b.field_73012_v.nextInt(15 - Thaumcraft.proxy.getFX().particleCount(4)) == 0) {
/* 30 */           Thaumcraft.proxy.getFX().wispFX3(this.field_174879_c.func_177958_n() + 0.5F, this.field_174879_c.func_177956_o() + 0.5F, this.field_174879_c.func_177952_p() + 0.5F, this.field_174879_c.func_177958_n() + 0.4F + this.field_145850_b.field_73012_v.nextFloat() * 0.2F, this.field_174879_c.func_177956_o() + 0.5F, this.field_174879_c.func_177952_p() + 0.4F + this.field_145850_b.field_73012_v.nextFloat() * 0.2F, 0.25F, 1, true, -0.02F);
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/tiles/misc/TileNitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */