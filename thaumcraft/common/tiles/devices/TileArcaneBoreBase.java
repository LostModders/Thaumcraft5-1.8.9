/*     */ package thaumcraft.common.tiles.devices;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.IEssentiaTransport;
/*     */ import thaumcraft.api.blocks.TileThaumcraft;
/*     */ import thaumcraft.api.wands.IWandable;
/*     */ import thaumcraft.common.blocks.BlockTCDevice;
/*     */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*     */ 
/*     */ public class TileArcaneBoreBase
/*     */   extends TileThaumcraft implements IWandable, IEssentiaTransport
/*     */ {
/*     */   public boolean onWandRightClick(World world, ItemStack wandstack, EntityPlayer player, BlockPos pos, EnumFacing side)
/*     */   {
/*  23 */     if (side.func_176736_b() >= 0) {
/*  24 */       ((BlockTCDevice)func_145838_q()).updateFacing(world, func_174877_v(), side);
/*  25 */       player.field_70170_p.func_72980_b(pos.func_177958_n() + 0.5D, pos.func_177956_o() + 0.5D, pos.func_177952_p() + 0.5D, "thaumcraft:tool", 0.3F, 1.9F + player.field_70170_p.field_73012_v.nextFloat() * 0.2F, false);
/*  26 */       player.func_71038_i();
/*  27 */       func_70296_d();
/*     */     }
/*  29 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public void onUsingWandTick(ItemStack wandstack, EntityPlayer player, int count) {}
/*     */   
/*     */   public void onWandStoppedUsing(ItemStack wandstack, World world, EntityPlayer player, int count) {}
/*     */   
/*     */   boolean drawEssentia()
/*     */   {
/*  39 */     for (EnumFacing facing : EnumFacing.field_176754_o) {
/*  40 */       TileEntity te = ThaumcraftApiHelper.getConnectableTile(this.field_145850_b, this.field_174879_c, facing);
/*  41 */       if (te != null) {
/*  42 */         IEssentiaTransport ic = (IEssentiaTransport)te;
/*  43 */         if (!ic.canOutputTo(facing.func_176734_d())) return false;
/*  44 */         if ((ic.getSuctionAmount(facing.func_176734_d()) < getSuctionAmount(facing)) && (ic.takeEssentia(Aspect.ENTROPY, 1, facing.func_176734_d()) == 1))
/*     */         {
/*  46 */           return true;
/*     */         }
/*     */       }
/*     */     }
/*  50 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isConnectable(EnumFacing face)
/*     */   {
/*  55 */     return (face != EnumFacing.UP) && (face != EnumFacing.DOWN);
/*     */   }
/*     */   
/*     */   public boolean canInputFrom(EnumFacing face)
/*     */   {
/*  60 */     return isConnectable(face);
/*     */   }
/*     */   
/*     */   public boolean canOutputTo(EnumFacing face)
/*     */   {
/*  65 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setSuction(Aspect aspect, int amount) {}
/*     */   
/*     */ 
/*     */   public Aspect getSuctionType(EnumFacing face)
/*     */   {
/*  74 */     return Aspect.ENTROPY;
/*     */   }
/*     */   
/*     */   public int getSuctionAmount(EnumFacing face)
/*     */   {
/*  79 */     return face != BlockStateUtils.getFacing(func_145832_p()) ? 128 : 0;
/*     */   }
/*     */   
/*     */   public int takeEssentia(Aspect aspect, int amount, EnumFacing face)
/*     */   {
/*  84 */     return 0;
/*     */   }
/*     */   
/*     */   public int addEssentia(Aspect aspect, int amount, EnumFacing face)
/*     */   {
/*  89 */     return 0;
/*     */   }
/*     */   
/*     */   public Aspect getEssentiaType(EnumFacing face)
/*     */   {
/*  94 */     return null;
/*     */   }
/*     */   
/*     */   public int getEssentiaAmount(EnumFacing face)
/*     */   {
/*  99 */     return 0;
/*     */   }
/*     */   
/*     */   public int getMinimumSuction()
/*     */   {
/* 104 */     return 0;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/tiles/devices/TileArcaneBoreBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */