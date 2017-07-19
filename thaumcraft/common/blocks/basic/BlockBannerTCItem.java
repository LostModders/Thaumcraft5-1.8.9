/*     */ package thaumcraft.common.blocks.basic;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.MapColor;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.EnumDyeColor;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.common.blocks.ItemBlockTC;
/*     */ import thaumcraft.common.tiles.misc.TileBanner;
/*     */ 
/*     */ public class BlockBannerTCItem extends ItemBlockTC
/*     */ {
/*     */   public BlockBannerTCItem(Block block)
/*     */   {
/*  24 */     super(block);
/*     */   }
/*     */   
/*     */   public int func_82790_a(ItemStack stack, int renderPass)
/*     */   {
/*  29 */     if ((renderPass == 1) && 
/*  30 */       (stack.func_77942_o()) && (stack.func_77978_p().func_74764_b("color"))) {
/*  31 */       return EnumDyeColor.func_176766_a(stack.func_77978_p().func_74771_c("color")).func_176768_e().field_76291_p;
/*     */     }
/*     */     
/*  34 */     if (renderPass == 2) {
/*  35 */       if ((stack.func_77942_o()) && (stack.func_77978_p().func_74764_b("color")) && (stack.func_77978_p().func_74779_i("aspect") != null) && (Aspect.getAspect(stack.func_77978_p().func_74779_i("aspect")) != null))
/*     */       {
/*     */ 
/*  38 */         return Aspect.getAspect(stack.func_77978_p().func_74779_i("aspect")).getColor();
/*     */       }
/*  40 */       if ((stack.func_77942_o()) && (stack.func_77978_p().func_74764_b("color"))) {
/*  41 */         return EnumDyeColor.func_176766_a(stack.func_77978_p().func_74771_c("color")).func_176768_e().field_76291_p;
/*     */       }
/*     */     }
/*  44 */     return 16777215;
/*     */   }
/*     */   
/*     */   public void func_77624_a(ItemStack stack, EntityPlayer par2EntityPlayer, List list, boolean par4)
/*     */   {
/*  49 */     if ((stack.func_77942_o()) && (stack.func_77978_p().func_74779_i("aspect") != null) && (Aspect.getAspect(stack.func_77978_p().func_74779_i("aspect")) != null))
/*     */     {
/*  51 */       list.add(Aspect.getAspect(stack.func_77978_p().func_74779_i("aspect")).getName());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public String func_77667_c(ItemStack stack)
/*     */   {
/*  58 */     if ((stack.func_77942_o()) && (stack.func_77978_p().func_74764_b("color"))) {
/*  59 */       return super.func_77658_a() + "." + EnumDyeColor.func_176766_a(stack.func_77978_p().func_74771_c("color")).func_176610_l();
/*     */     }
/*  61 */     return super.func_77658_a() + ".cultist";
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_180614_a(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
/*     */   {
/*  67 */     if (side == EnumFacing.DOWN)
/*     */     {
/*  69 */       return false;
/*     */     }
/*  71 */     if (!worldIn.func_180495_p(pos).func_177230_c().func_149688_o().func_76220_a())
/*     */     {
/*  73 */       return false;
/*     */     }
/*     */     
/*     */ 
/*  77 */     pos = pos.func_177972_a(side);
/*     */     
/*  79 */     if (!playerIn.func_175151_a(pos, side, stack))
/*     */     {
/*  81 */       return false;
/*     */     }
/*  83 */     if (!net.minecraft.init.Blocks.field_180393_cK.func_176196_c(worldIn, pos))
/*     */     {
/*  85 */       return false;
/*     */     }
/*  87 */     if (worldIn.field_72995_K)
/*     */     {
/*  89 */       return true;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*  94 */     worldIn.func_180501_a(pos, BlocksTC.banner.func_176223_P(), 3);
/*     */     
/*  96 */     TileBanner tile = (TileBanner)worldIn.func_175625_s(pos);
/*  97 */     if (tile != null) {
/*  98 */       if (side == EnumFacing.UP)
/*     */       {
/* 100 */         int i = MathHelper.func_76128_c((playerIn.field_70177_z + 180.0F) * 16.0F / 360.0F + 0.5D) & 0xF;
/* 101 */         tile.setBannerFacing((byte)i);
/*     */       }
/*     */       else
/*     */       {
/* 105 */         tile.setWall(true);
/*     */         
/* 107 */         int i = 0;
/*     */         
/* 109 */         if (side == EnumFacing.NORTH)
/*     */         {
/* 111 */           i = 8;
/*     */         }
/*     */         
/* 114 */         if (side == EnumFacing.WEST)
/*     */         {
/* 116 */           i = 4;
/*     */         }
/*     */         
/* 119 */         if (side == EnumFacing.EAST)
/*     */         {
/* 121 */           i = 12;
/*     */         }
/*     */         
/* 124 */         tile.setBannerFacing((byte)i);
/*     */       }
/*     */       
/* 127 */       if (stack.func_77942_o()) {
/* 128 */         if (stack.func_77978_p().func_74779_i("aspect") != null) {
/* 129 */           tile.setAspect(Aspect.getAspect(stack.func_77978_p().func_74779_i("aspect")));
/*     */         }
/* 131 */         if (stack.func_77978_p().func_74764_b("color")) {
/* 132 */           tile.setColor(stack.func_77978_p().func_74771_c("color"));
/*     */         }
/*     */       }
/*     */       
/* 136 */       tile.func_70296_d();
/* 137 */       worldIn.func_175689_h(pos);
/*     */     }
/*     */     
/* 140 */     stack.field_77994_a -= 1;
/*     */     
/* 142 */     return true;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/basic/BlockBannerTCItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */