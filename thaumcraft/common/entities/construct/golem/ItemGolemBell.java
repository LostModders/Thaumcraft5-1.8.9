/*     */ package thaumcraft.common.entities.construct.golem;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import thaumcraft.api.golems.ISealDisplayer;
/*     */ import thaumcraft.api.golems.seals.ISealEntity;
/*     */ import thaumcraft.api.golems.seals.SealPos;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.entities.construct.golem.seals.SealHandler;
/*     */ 
/*     */ public class ItemGolemBell
/*     */   extends Item implements ISealDisplayer
/*     */ {
/*     */   public ItemGolemBell()
/*     */   {
/*  25 */     func_77627_a(false);
/*  26 */     func_77637_a(Thaumcraft.tabTC);
/*  27 */     func_77625_d(1);
/*     */   }
/*     */   
/*     */ 
/*     */   public ItemStack func_77659_a(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn)
/*     */   {
/*  33 */     playerIn.func_71038_i();
/*  34 */     if (!worldIn.field_72995_K) {
/*  35 */       worldIn.func_72908_a(playerIn.field_70165_t, playerIn.field_70163_u, playerIn.field_70161_v, "random.orb", 0.6F, 1.0F + worldIn.field_73012_v.nextFloat() * 0.1F);
/*     */       
/*  37 */       ISealEntity se = getSeal(playerIn);
/*  38 */       if (se != null)
/*     */       {
/*  40 */         if (playerIn.func_70093_af()) {
/*  41 */           SealHandler.removeSealEntity(playerIn.field_70170_p, se.getSealPos(), false);
/*  42 */           playerIn.field_70170_p.func_72908_a(se.getSealPos().pos.func_177958_n(), se.getSealPos().pos.func_177956_o(), se.getSealPos().pos.func_177952_p(), "thaumcraft:zap", 1.0F, 1.0F);
/*     */         } else {
/*  44 */           playerIn.openGui(Thaumcraft.instance, 18, playerIn.field_70170_p, se.getSealPos().pos.func_177958_n(), se.getSealPos().pos.func_177956_o(), se.getSealPos().pos.func_177952_p());
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*  49 */     return super.func_77659_a(itemStackIn, worldIn, playerIn);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float par8, float par9, float par10)
/*     */   {
/*  55 */     player.func_71038_i();
/*  56 */     if (world.field_72995_K) return false;
/*  57 */     ISealEntity se = SealHandler.getSealEntity(world.field_73011_w.func_177502_q(), new SealPos(pos, side));
/*  58 */     if (se != null) {
/*  59 */       world.func_72908_a(player.field_70165_t, player.field_70163_u, player.field_70161_v, "random.orb", 0.6F, 1.0F + world.field_73012_v.nextFloat() * 0.1F);
/*  60 */       if (player.func_70093_af()) {
/*  61 */         SealHandler.removeSealEntity(world, se.getSealPos(), false);
/*  62 */         world.func_72908_a(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), "thaumcraft:zap", 1.0F, 1.0F);
/*     */       } else {
/*  64 */         player.openGui(Thaumcraft.instance, 18, world, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
/*     */       }
/*  66 */       return true;
/*     */     }
/*  68 */     return false;
/*     */   }
/*     */   
/*     */   public static ISealEntity getSeal(EntityPlayer playerIn) {
/*  72 */     float f = playerIn.field_70125_A;
/*  73 */     float f1 = playerIn.field_70177_z;
/*  74 */     double d0 = playerIn.field_70165_t;
/*  75 */     double d1 = playerIn.field_70163_u + playerIn.func_70047_e();
/*  76 */     double d2 = playerIn.field_70161_v;
/*  77 */     Vec3 vec0 = new Vec3(d0, d1, d2);
/*  78 */     float f2 = MathHelper.func_76134_b(-f1 * 0.017453292F - 3.1415927F);
/*  79 */     float f3 = MathHelper.func_76126_a(-f1 * 0.017453292F - 3.1415927F);
/*  80 */     float f4 = -MathHelper.func_76134_b(-f * 0.017453292F);
/*  81 */     float f5 = MathHelper.func_76126_a(-f * 0.017453292F);
/*  82 */     float f6 = f3 * f4;
/*  83 */     float f7 = f2 * f4;
/*  84 */     double d3 = 5.0D;
/*  85 */     Vec3 vec1 = vec0.func_72441_c(f6 * d3, f5 * d3, f7 * d3);
/*  86 */     Vec3 vec2 = new Vec3(f6 * d3, f5 * d3, f7 * d3);
/*     */     
/*  88 */     Vec3 vec3 = vec0.func_72441_c(vec2.field_72450_a / 10.0D, vec2.field_72448_b / 10.0D, vec2.field_72449_c / 10.0D);
/*     */     
/*  90 */     for (int a = 0; a < vec2.func_72433_c() * 10.0D; a++) {
/*  91 */       BlockPos pos = new BlockPos(vec3);
/*  92 */       MovingObjectPosition mop = collisionRayTrace(playerIn.field_70170_p, pos, vec0, vec1);
/*  93 */       if (mop != null) {
/*  94 */         ISealEntity se = SealHandler.getSealEntity(playerIn.field_70170_p.field_73011_w.func_177502_q(), new SealPos(pos, mop.field_178784_b));
/*  95 */         if (se != null) {
/*  96 */           return se;
/*     */         }
/*     */       }
/*  99 */       vec3 = vec3.func_72441_c(vec2.field_72450_a / 10.0D, vec2.field_72448_b / 10.0D, vec2.field_72449_c / 10.0D);
/*     */     }
/* 101 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   private static boolean isVecInsideYZBounds(Vec3 point, BlockPos pos)
/*     */   {
/* 107 */     return point != null;
/*     */   }
/*     */   
/*     */   private static boolean isVecInsideXZBounds(Vec3 point, BlockPos pos)
/*     */   {
/* 112 */     return point != null;
/*     */   }
/*     */   
/*     */   private static boolean isVecInsideXYBounds(Vec3 point, BlockPos pos)
/*     */   {
/* 117 */     return point != null;
/*     */   }
/*     */   
/*     */   private static MovingObjectPosition collisionRayTrace(World worldIn, BlockPos pos, Vec3 start, Vec3 end)
/*     */   {
/* 122 */     Vec3 vec3 = start.func_72429_b(end, pos.func_177958_n());
/* 123 */     Vec3 vec31 = start.func_72429_b(end, pos.func_177958_n() + 1);
/* 124 */     Vec3 vec32 = start.func_72435_c(end, pos.func_177956_o());
/* 125 */     Vec3 vec33 = start.func_72435_c(end, pos.func_177956_o() + 1);
/* 126 */     Vec3 vec34 = start.func_72434_d(end, pos.func_177952_p());
/* 127 */     Vec3 vec35 = start.func_72434_d(end, pos.func_177952_p() + 1);
/*     */     
/* 129 */     if (!isVecInsideYZBounds(vec3, pos))
/*     */     {
/* 131 */       vec3 = null;
/*     */     }
/*     */     
/* 134 */     if (!isVecInsideYZBounds(vec31, pos))
/*     */     {
/* 136 */       vec31 = null;
/*     */     }
/*     */     
/* 139 */     if (!isVecInsideXZBounds(vec32, pos))
/*     */     {
/* 141 */       vec32 = null;
/*     */     }
/*     */     
/* 144 */     if (!isVecInsideXZBounds(vec33, pos))
/*     */     {
/* 146 */       vec33 = null;
/*     */     }
/*     */     
/* 149 */     if (!isVecInsideXYBounds(vec34, pos))
/*     */     {
/* 151 */       vec34 = null;
/*     */     }
/*     */     
/* 154 */     if (!isVecInsideXYBounds(vec35, pos))
/*     */     {
/* 156 */       vec35 = null;
/*     */     }
/*     */     
/* 159 */     Vec3 vec36 = null;
/*     */     
/* 161 */     if ((vec3 != null) && ((vec36 == null) || (start.func_72436_e(vec3) < start.func_72436_e(vec36))))
/*     */     {
/* 163 */       vec36 = vec3;
/*     */     }
/*     */     
/* 166 */     if ((vec31 != null) && ((vec36 == null) || (start.func_72436_e(vec31) < start.func_72436_e(vec36))))
/*     */     {
/* 168 */       vec36 = vec31;
/*     */     }
/*     */     
/* 171 */     if ((vec32 != null) && ((vec36 == null) || (start.func_72436_e(vec32) < start.func_72436_e(vec36))))
/*     */     {
/* 173 */       vec36 = vec32;
/*     */     }
/*     */     
/* 176 */     if ((vec33 != null) && ((vec36 == null) || (start.func_72436_e(vec33) < start.func_72436_e(vec36))))
/*     */     {
/* 178 */       vec36 = vec33;
/*     */     }
/*     */     
/* 181 */     if ((vec34 != null) && ((vec36 == null) || (start.func_72436_e(vec34) < start.func_72436_e(vec36))))
/*     */     {
/* 183 */       vec36 = vec34;
/*     */     }
/*     */     
/* 186 */     if ((vec35 != null) && ((vec36 == null) || (start.func_72436_e(vec35) < start.func_72436_e(vec36))))
/*     */     {
/* 188 */       vec36 = vec35;
/*     */     }
/*     */     
/* 191 */     if (vec36 == null)
/*     */     {
/* 193 */       return null;
/*     */     }
/*     */     
/*     */ 
/* 197 */     EnumFacing enumfacing = null;
/*     */     
/* 199 */     if (vec36 == vec3)
/*     */     {
/* 201 */       enumfacing = EnumFacing.WEST;
/*     */     }
/*     */     
/* 204 */     if (vec36 == vec31)
/*     */     {
/* 206 */       enumfacing = EnumFacing.EAST;
/*     */     }
/*     */     
/* 209 */     if (vec36 == vec32)
/*     */     {
/* 211 */       enumfacing = EnumFacing.DOWN;
/*     */     }
/*     */     
/* 214 */     if (vec36 == vec33)
/*     */     {
/* 216 */       enumfacing = EnumFacing.UP;
/*     */     }
/*     */     
/* 219 */     if (vec36 == vec34)
/*     */     {
/* 221 */       enumfacing = EnumFacing.NORTH;
/*     */     }
/*     */     
/* 224 */     if (vec36 == vec35)
/*     */     {
/* 226 */       enumfacing = EnumFacing.SOUTH;
/*     */     }
/*     */     
/* 229 */     return new MovingObjectPosition(vec36.func_72441_c(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p()), enumfacing, pos);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/construct/golem/ItemGolemBell.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */