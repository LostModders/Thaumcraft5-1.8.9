/*    */ package thaumcraft.common.entities.construct;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.common.items.ItemGenericVariants;
/*    */ 
/*    */ public class ItemTurretPlacer extends ItemGenericVariants
/*    */ {
/*    */   public ItemTurretPlacer()
/*    */   {
/* 15 */     super(new String[] { "basic", "focus", "magnet", "advanced" });
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, BlockPos pos, net.minecraft.util.EnumFacing side, float hitX, float hitY, float hitZ)
/*    */   {
/* 23 */     IBlockState bs = world.func_180495_p(pos);
/*    */     
/* 25 */     if (!bs.func_177230_c().func_149688_o().func_76220_a()) return false;
/* 26 */     if (world.field_72995_K) { return false;
/*    */     }
/* 28 */     pos = pos.func_177972_a(side);
/* 29 */     bs = world.func_180495_p(pos);
/*    */     
/* 31 */     if (!player.func_175151_a(pos, side, stack)) { return false;
/*    */     }
/* 33 */     if (!bs.func_177230_c().func_176200_f(world, pos)) { return false;
/*    */     }
/* 35 */     if ((stack.func_77952_i() != 1) && (!world.func_180495_p(pos.func_177977_b()).func_177230_c().func_149688_o().func_76220_a())) { return false;
/*    */     }
/* 37 */     world.func_175698_g(pos);
/* 38 */     EntityOwnedConstruct turret = null;
/* 39 */     switch (stack.func_77952_i()) {
/* 40 */     case 0:  turret = new EntityTurretCrossbow(world, pos); break;
/* 41 */     case 1:  turret = new EntityTurretFocus(world, pos, side); break;
/* 42 */     case 2:  turret = new EntityNodeMagnet(world, pos); break;
/* 43 */     case 3:  turret = new EntityTurretCrossbowAdvanced(world, pos);
/*    */     }
/*    */     
/* 46 */     if (turret != null) {
/* 47 */       world.func_72838_d(turret);
/* 48 */       turret.setOwned(true);
/* 49 */       turret.setValidSpawn();
/* 50 */       turret.setOwnerId(player.func_110124_au().toString());
/* 51 */       if (!player.field_71075_bZ.field_75098_d) { stack.field_77994_a -= 1;
/*    */       }
/*    */     }
/*    */     
/* 55 */     return true;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/construct/ItemTurretPlacer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */