/*    */ package thaumcraft.common.items.consumables;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.entity.IEntityLivingData;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.EnumRarity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.EnumChatFormatting;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.common.entities.construct.EntityTurretEldritch;
/*    */ import thaumcraft.common.items.ItemGenericVariants;
/*    */ import thaumcraft.common.lib.world.dim.GenCommon;
/*    */ 
/*    */ public class ItemCreativePlacer extends ItemGenericVariants
/*    */ {
/*    */   public ItemCreativePlacer()
/*    */   {
/* 23 */     super(new String[] { "obelisk", "node", "caster" });
/*    */   }
/*    */   
/*    */   public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean par4)
/*    */   {
/* 28 */     super.func_77624_a(stack, player, list, par4);
/* 29 */     list.add(EnumChatFormatting.DARK_PURPLE + "Creative only");
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
/*    */   {
/* 37 */     IBlockState bs = world.func_180495_p(pos);
/*    */     
/* 39 */     if (!bs.func_177230_c().func_149688_o().func_76220_a()) return false;
/* 40 */     if (world.field_72995_K) { return false;
/*    */     }
/* 42 */     pos = pos.func_177972_a(side);
/* 43 */     bs = world.func_180495_p(pos);
/*    */     
/* 45 */     if (!player.func_175151_a(pos, side, stack)) { return false;
/*    */     }
/* 47 */     if (!bs.func_177230_c().func_176200_f(world, pos)) { return false;
/*    */     }
/* 49 */     if ((stack.func_77952_i() == 0) && (!world.func_180495_p(pos.func_177977_b()).func_177230_c().func_149688_o().func_76220_a())) { return false;
/*    */     }
/* 51 */     world.func_175698_g(pos);
/*    */     
/* 53 */     switch (stack.func_77952_i()) {
/* 54 */     case 0:  GenCommon.genObelisk(world, pos); break;
/* 55 */     case 1:  thaumcraft.common.lib.world.ThaumcraftWorldGenerator.spawnNode(world, pos, -1, 1.0F); break;
/*    */     case 2: 
/* 57 */       EntityTurretEldritch turret = new EntityTurretEldritch(world, pos, side);
/* 58 */       if (turret != null) {
/* 59 */         turret.func_180482_a(world.func_175649_E(pos), (IEntityLivingData)null);
/* 60 */         turret.setValidSpawn();
/* 61 */         world.func_72838_d(turret);
/*    */       }
/*    */       break;
/*    */     }
/*    */     
/* 66 */     return true;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public EnumRarity func_77613_e(ItemStack itemstack)
/*    */   {
/* 73 */     return EnumRarity.EPIC;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/items/consumables/ItemCreativePlacer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */