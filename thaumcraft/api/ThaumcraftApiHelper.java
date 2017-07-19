/*     */ package thaumcraft.api;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.oredict.OreDictionary;
/*     */ import thaumcraft.api.aspects.IEssentiaTransport;
/*     */ 
/*     */ public class ThaumcraftApiHelper
/*     */ {
/*     */   public static boolean areItemsEqual(ItemStack s1, ItemStack s2)
/*     */   {
/*  26 */     if ((s1.func_77984_f()) && (s2.func_77984_f()))
/*     */     {
/*  28 */       return s1.func_77973_b() == s2.func_77973_b();
/*     */     }
/*  30 */     return (s1.func_77973_b() == s2.func_77973_b()) && (s1.func_77952_i() == s2.func_77952_i());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void markRunicDirty(Entity entity)
/*     */   {
/*  40 */     ThaumcraftApi.internalMethods.markRunicDirty(entity);
/*     */   }
/*     */   
/*     */   public static boolean containsMatch(boolean strict, ItemStack[] inputs, List<ItemStack> targets) {
/*     */     ItemStack input;
/*  45 */     for (input : inputs)
/*     */     {
/*  47 */       for (ItemStack target : targets)
/*     */       {
/*  49 */         if (OreDictionary.itemMatches(target, input, strict))
/*     */         {
/*  51 */           return true;
/*     */         }
/*     */       }
/*     */     }
/*  55 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean areItemStacksEqualForCrafting(ItemStack stack0, Object in)
/*     */   {
/*  60 */     if ((stack0 == null) && (in != null)) return false;
/*  61 */     if ((stack0 != null) && (in == null)) return false;
/*  62 */     if ((stack0 == null) && (in == null)) { return true;
/*     */     }
/*  64 */     if ((in instanceof Object[])) { return true;
/*     */     }
/*  66 */     if ((in instanceof String)) {
/*  67 */       List<ItemStack> l = OreDictionary.getOres((String)in);
/*  68 */       return containsMatch(false, new ItemStack[] { stack0 }, l);
/*     */     }
/*     */     
/*  71 */     if ((in instanceof ItemStack))
/*     */     {
/*  73 */       boolean t1 = areItemStackTagsEqualForCrafting(stack0, (ItemStack)in);
/*  74 */       if (!t1) return false;
/*  75 */       return OreDictionary.itemMatches((ItemStack)in, stack0, false);
/*     */     }
/*     */     
/*  78 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean areItemStackTagsEqualForCrafting(ItemStack slotItem, ItemStack recipeItem)
/*     */   {
/*  83 */     if ((recipeItem == null) || (slotItem == null)) return false;
/*  84 */     if ((recipeItem.func_77978_p() != null) && (slotItem.func_77978_p() == null)) return false;
/*  85 */     if (recipeItem.func_77978_p() == null) { return true;
/*     */     }
/*  87 */     Iterator iterator = recipeItem.func_77978_p().func_150296_c().iterator();
/*  88 */     while (iterator.hasNext())
/*     */     {
/*  90 */       String s = (String)iterator.next();
/*  91 */       if (slotItem.func_77978_p().func_74764_b(s)) {
/*  92 */         if (!slotItem.func_77978_p().func_74781_a(s).toString().equals(recipeItem.func_77978_p().func_74781_a(s).toString()))
/*     */         {
/*  94 */           return false;
/*     */         }
/*     */       } else {
/*  97 */         return false;
/*     */       }
/*     */     }
/*     */     
/* 101 */     return true;
/*     */   }
/*     */   
/*     */   public static TileEntity getConnectableTile(World world, BlockPos pos, EnumFacing face)
/*     */   {
/* 106 */     TileEntity te = world.func_175625_s(pos.func_177972_a(face));
/* 107 */     if (((te instanceof IEssentiaTransport)) && (((IEssentiaTransport)te).isConnectable(face.func_176734_d()))) {
/* 108 */       return te;
/*     */     }
/* 110 */     return null;
/*     */   }
/*     */   
/*     */   public static TileEntity getConnectableTile(IBlockAccess world, BlockPos pos, EnumFacing face) {
/* 114 */     TileEntity te = world.func_175625_s(pos.func_177972_a(face));
/* 115 */     if (((te instanceof IEssentiaTransport)) && (((IEssentiaTransport)te).isConnectable(face.func_176734_d()))) {
/* 116 */       return te;
/*     */     }
/* 118 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static MovingObjectPosition rayTraceIgnoringSource(World world, Vec3 v1, Vec3 v2, boolean bool1, boolean bool2, boolean bool3)
/*     */   {
/* 150 */     if ((!Double.isNaN(v1.field_72450_a)) && (!Double.isNaN(v1.field_72448_b)) && (!Double.isNaN(v1.field_72449_c)))
/*     */     {
/* 152 */       if ((!Double.isNaN(v2.field_72450_a)) && (!Double.isNaN(v2.field_72448_b)) && (!Double.isNaN(v2.field_72449_c)))
/*     */       {
/* 154 */         int i = MathHelper.func_76128_c(v2.field_72450_a);
/* 155 */         int j = MathHelper.func_76128_c(v2.field_72448_b);
/* 156 */         int k = MathHelper.func_76128_c(v2.field_72449_c);
/* 157 */         int l = MathHelper.func_76128_c(v1.field_72450_a);
/* 158 */         int i1 = MathHelper.func_76128_c(v1.field_72448_b);
/* 159 */         int j1 = MathHelper.func_76128_c(v1.field_72449_c);
/* 160 */         IBlockState block = world.func_180495_p(new BlockPos(l, i1, j1));
/*     */         
/* 162 */         MovingObjectPosition movingobjectposition2 = null;
/* 163 */         int k1 = 200;
/*     */         
/* 165 */         while (k1-- >= 0)
/*     */         {
/* 167 */           if ((Double.isNaN(v1.field_72450_a)) || (Double.isNaN(v1.field_72448_b)) || (Double.isNaN(v1.field_72449_c)))
/*     */           {
/* 169 */             return null;
/*     */           }
/*     */           
/* 172 */           if ((l != i) || (i1 != j) || (j1 != k))
/*     */           {
/*     */ 
/*     */ 
/*     */ 
/* 177 */             boolean flag6 = true;
/* 178 */             boolean flag3 = true;
/* 179 */             boolean flag4 = true;
/* 180 */             double d0 = 999.0D;
/* 181 */             double d1 = 999.0D;
/* 182 */             double d2 = 999.0D;
/*     */             
/* 184 */             if (i > l)
/*     */             {
/* 186 */               d0 = l + 1.0D;
/*     */             }
/* 188 */             else if (i < l)
/*     */             {
/* 190 */               d0 = l + 0.0D;
/*     */             }
/*     */             else
/*     */             {
/* 194 */               flag6 = false;
/*     */             }
/*     */             
/* 197 */             if (j > i1)
/*     */             {
/* 199 */               d1 = i1 + 1.0D;
/*     */             }
/* 201 */             else if (j < i1)
/*     */             {
/* 203 */               d1 = i1 + 0.0D;
/*     */             }
/*     */             else
/*     */             {
/* 207 */               flag3 = false;
/*     */             }
/*     */             
/* 210 */             if (k > j1)
/*     */             {
/* 212 */               d2 = j1 + 1.0D;
/*     */             }
/* 214 */             else if (k < j1)
/*     */             {
/* 216 */               d2 = j1 + 0.0D;
/*     */             }
/*     */             else
/*     */             {
/* 220 */               flag4 = false;
/*     */             }
/*     */             
/* 223 */             double d3 = 999.0D;
/* 224 */             double d4 = 999.0D;
/* 225 */             double d5 = 999.0D;
/* 226 */             double d6 = v2.field_72450_a - v1.field_72450_a;
/* 227 */             double d7 = v2.field_72448_b - v1.field_72448_b;
/* 228 */             double d8 = v2.field_72449_c - v1.field_72449_c;
/*     */             
/* 230 */             if (flag6)
/*     */             {
/* 232 */               d3 = (d0 - v1.field_72450_a) / d6;
/*     */             }
/*     */             
/* 235 */             if (flag3)
/*     */             {
/* 237 */               d4 = (d1 - v1.field_72448_b) / d7;
/*     */             }
/*     */             
/* 240 */             if (flag4)
/*     */             {
/* 242 */               d5 = (d2 - v1.field_72449_c) / d8;
/*     */             }
/*     */             
/* 245 */             if (d3 == -0.0D)
/*     */             {
/* 247 */               d3 = -1.0E-4D;
/*     */             }
/*     */             
/* 250 */             if (d4 == -0.0D)
/*     */             {
/* 252 */               d4 = -1.0E-4D;
/*     */             }
/*     */             
/* 255 */             if (d5 == -0.0D)
/*     */             {
/* 257 */               d5 = -1.0E-4D;
/*     */             }
/*     */             
/*     */             EnumFacing enumfacing;
/*     */             
/* 262 */             if ((d3 < d4) && (d3 < d5))
/*     */             {
/* 264 */               EnumFacing enumfacing = i > l ? EnumFacing.WEST : EnumFacing.EAST;
/* 265 */               v1 = new Vec3(d0, v1.field_72448_b + d7 * d3, v1.field_72449_c + d8 * d3);
/*     */             }
/* 267 */             else if (d4 < d5)
/*     */             {
/* 269 */               EnumFacing enumfacing = j > i1 ? EnumFacing.DOWN : EnumFacing.UP;
/* 270 */               v1 = new Vec3(v1.field_72450_a + d6 * d4, d1, v1.field_72449_c + d8 * d4);
/*     */             }
/*     */             else
/*     */             {
/* 274 */               enumfacing = k > j1 ? EnumFacing.NORTH : EnumFacing.SOUTH;
/* 275 */               v1 = new Vec3(v1.field_72450_a + d6 * d5, v1.field_72448_b + d7 * d5, d2);
/*     */             }
/*     */             
/* 278 */             l = MathHelper.func_76128_c(v1.field_72450_a) - (enumfacing == EnumFacing.EAST ? 1 : 0);
/* 279 */             i1 = MathHelper.func_76128_c(v1.field_72448_b) - (enumfacing == EnumFacing.UP ? 1 : 0);
/* 280 */             j1 = MathHelper.func_76128_c(v1.field_72449_c) - (enumfacing == EnumFacing.SOUTH ? 1 : 0);
/*     */             
/* 282 */             IBlockState block1 = world.func_180495_p(new BlockPos(l, i1, j1));
/*     */             
/* 284 */             if ((!bool2) || (block1.func_177230_c().func_180640_a(world, new BlockPos(l, i1, j1), block1) != null))
/*     */             {
/* 286 */               if (block1.func_177230_c().func_176209_a(block1, bool1))
/*     */               {
/* 288 */                 MovingObjectPosition movingobjectposition1 = block1.func_177230_c().func_180636_a(world, new BlockPos(l, i1, j1), v1, v2);
/*     */                 
/* 290 */                 if (movingobjectposition1 != null)
/*     */                 {
/* 292 */                   return movingobjectposition1;
/*     */                 }
/*     */               }
/*     */               else
/*     */               {
/* 297 */                 movingobjectposition2 = new MovingObjectPosition(net.minecraft.util.MovingObjectPosition.MovingObjectType.MISS, v1, enumfacing, new BlockPos(l, i1, j1));
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/* 302 */         return bool3 ? movingobjectposition2 : null;
/*     */       }
/*     */       
/*     */ 
/* 306 */       return null;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 311 */     return null;
/*     */   }
/*     */   
/*     */   public static Object getNBTDataFromId(NBTTagCompound nbt, byte id, String key)
/*     */   {
/* 316 */     switch (id) {
/* 317 */     case 1:  return Byte.valueOf(nbt.func_74771_c(key));
/* 318 */     case 2:  return Short.valueOf(nbt.func_74765_d(key));
/* 319 */     case 3:  return Integer.valueOf(nbt.func_74762_e(key));
/* 320 */     case 4:  return Long.valueOf(nbt.func_74763_f(key));
/* 321 */     case 5:  return Float.valueOf(nbt.func_74760_g(key));
/* 322 */     case 6:  return Double.valueOf(nbt.func_74769_h(key));
/* 323 */     case 7:  return nbt.func_74770_j(key);
/* 324 */     case 8:  return nbt.func_74779_i(key);
/* 325 */     case 9:  return nbt.func_150295_c(key, 10);
/* 326 */     case 10:  return nbt.func_74781_a(key);
/* 327 */     case 11:  return nbt.func_74759_k(key); }
/* 328 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public static int setByteInInt(int data, byte b, int index)
/*     */   {
/* 334 */     ByteBuffer bb = ByteBuffer.allocate(4);
/* 335 */     bb.putInt(0, data);
/* 336 */     bb.put(index, b);
/* 337 */     return bb.getInt(0);
/*     */   }
/*     */   
/*     */   public static byte getByteInInt(int data, int index) {
/* 341 */     ByteBuffer bb = ByteBuffer.allocate(4);
/* 342 */     bb.putInt(0, data);
/* 343 */     return bb.get(index);
/*     */   }
/*     */   
/*     */   public static long setByteInLong(long data, byte b, int index)
/*     */   {
/* 348 */     ByteBuffer bb = ByteBuffer.allocate(8);
/* 349 */     bb.putLong(0, data);
/* 350 */     bb.put(index, b);
/* 351 */     return bb.getLong(0);
/*     */   }
/*     */   
/*     */   public static byte getByteInLong(long data, int index) {
/* 355 */     ByteBuffer bb = ByteBuffer.allocate(8);
/* 356 */     bb.putLong(0, data);
/* 357 */     return bb.get(index);
/*     */   }
/*     */   
/*     */   public static int setNibbleInInt(int data, int nibble, int nibbleIndex)
/*     */   {
/* 362 */     int shift = nibbleIndex * 4;
/* 363 */     return data & (15 << shift ^ 0xFFFFFFFF) | nibble << shift;
/*     */   }
/*     */   
/*     */   public static int getNibbleInInt(int data, int nibbleIndex) {
/* 367 */     return data >> (nibbleIndex << 2) & 0xF;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/api/ThaumcraftApiHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */