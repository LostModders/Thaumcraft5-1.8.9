/*    */ package thaumcraft.common.items.tools;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.EnumRarity;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.ChatComponentTranslation;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.util.MovingObjectPosition;
/*    */ import net.minecraft.util.StatCollector;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ import thaumcraft.api.aspects.AspectList;
/*    */ import thaumcraft.api.aspects.IAspectContainer;
/*    */ import thaumcraft.api.aspects.IEssentiaTransport;
/*    */ import thaumcraft.codechicken.lib.raytracer.RayTracer;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ import thaumcraft.common.tiles.essentia.TileTubeBuffer;
/*    */ 
/*    */ public class ItemResonator extends Item
/*    */ {
/*    */   public ItemResonator()
/*    */   {
/* 26 */     func_77625_d(1);
/* 27 */     func_77627_a(false);
/* 28 */     func_77656_e(0);
/* 29 */     func_77637_a(Thaumcraft.tabTC);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public EnumRarity func_77613_e(ItemStack itemstack)
/*    */   {
/* 36 */     return EnumRarity.UNCOMMON;
/*    */   }
/*    */   
/*    */   public boolean func_77636_d(ItemStack par1ItemStack)
/*    */   {
/* 41 */     return par1ItemStack.func_77942_o();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public boolean onItemUseFirst(ItemStack itemstack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float par8, float par9, float par10)
/*    */   {
/* 48 */     net.minecraft.tileentity.TileEntity tile = world.func_175625_s(pos);
/* 49 */     if ((tile != null) && ((tile instanceof IEssentiaTransport))) {
/* 50 */       if (world.field_72995_K) {
/* 51 */         player.func_71038_i();
/* 52 */         return super.onItemUseFirst(itemstack, player, world, pos, side, par8, par9, par10);
/*    */       }
/* 54 */       IEssentiaTransport et = (IEssentiaTransport)tile;
/*    */       
/* 56 */       MovingObjectPosition hit = RayTracer.retraceBlock(world, player, pos);
/* 57 */       if ((hit != null) && (hit.subHit >= 0) && (hit.subHit < 6))
/*    */       {
/* 59 */         side = EnumFacing.field_82609_l[hit.subHit];
/*    */       }
/*    */       
/* 62 */       if ((!(tile instanceof TileTubeBuffer)) && (et.getEssentiaType(side) != null)) {
/* 63 */         player.func_145747_a(new ChatComponentTranslation("tc.resonator1", new Object[] { "" + et.getEssentiaAmount(side), et.getEssentiaType(side).getName() }));
/*    */       }
/* 65 */       else if (((tile instanceof TileTubeBuffer)) && (((IAspectContainer)tile).getAspects().size() > 0)) {
/* 66 */         for (Aspect aspect : ((IAspectContainer)tile).getAspects().getAspectsSortedByName()) {
/* 67 */           player.func_145747_a(new ChatComponentTranslation("tc.resonator1", new Object[] { "" + ((IAspectContainer)tile).getAspects().getAmount(aspect), aspect.getName() }));
/*    */         }
/*    */       }
/*    */       
/*    */ 
/* 72 */       String s = StatCollector.func_74838_a("tc.resonator3");
/* 73 */       if (et.getSuctionType(side) != null)
/* 74 */         s = et.getSuctionType(side).getName();
/* 75 */       player.func_145747_a(new ChatComponentTranslation("tc.resonator2", new Object[] { "" + et.getSuctionAmount(side), s }));
/*    */       
/*    */ 
/* 78 */       world.func_72908_a(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), "thaumcraft:alembicknock", 0.5F, 1.9F + world.field_73012_v.nextFloat() * 0.1F);
/*    */       
/*    */ 
/* 81 */       return true;
/*    */     }
/*    */     
/* 84 */     return false;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/items/tools/ItemResonator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */