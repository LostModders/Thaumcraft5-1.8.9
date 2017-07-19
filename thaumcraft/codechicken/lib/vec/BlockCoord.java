/*     */ package thaumcraft.codechicken.lib.vec;
/*     */ 
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import thaumcraft.codechicken.lib.math.MathHelper;
/*     */ import thaumcraft.codechicken.lib.util.Copyable;
/*     */ 
/*     */ public class BlockCoord implements Comparable<BlockCoord>, Copyable<BlockCoord>
/*     */ {
/*  10 */   public static final BlockCoord[] sideOffsets = { new BlockCoord(0, -1, 0), new BlockCoord(0, 1, 0), new BlockCoord(0, 0, -1), new BlockCoord(0, 0, 1), new BlockCoord(-1, 0, 0), new BlockCoord(1, 0, 0) };
/*     */   
/*     */ 
/*     */   public int x;
/*     */   
/*     */ 
/*     */   public int y;
/*     */   
/*     */   public int z;
/*     */   
/*     */ 
/*     */   public BlockCoord(int x, int y, int z)
/*     */   {
/*  23 */     this.x = x;
/*  24 */     this.y = y;
/*  25 */     this.z = z;
/*     */   }
/*     */   
/*     */   public BlockCoord(BlockPos pos) {
/*  29 */     this(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
/*     */   }
/*     */   
/*     */   public BlockCoord(Vector3 v) {
/*  33 */     this(MathHelper.floor_double(v.x), MathHelper.floor_double(v.y), MathHelper.floor_double(v.z));
/*     */   }
/*     */   
/*     */   public BlockCoord(TileEntity tile) {
/*  37 */     this(tile.func_174877_v());
/*     */   }
/*     */   
/*     */   public BlockCoord(int[] ia) {
/*  41 */     this(ia[0], ia[1], ia[2]);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public BlockCoord() {}
/*     */   
/*     */ 
/*     */   public static BlockCoord fromAxes(int[] ia)
/*     */   {
/*  51 */     return new BlockCoord(ia[2], ia[0], ia[1]);
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj)
/*     */   {
/*  56 */     if (!(obj instanceof BlockCoord))
/*  57 */       return false;
/*  58 */     BlockCoord o2 = (BlockCoord)obj;
/*  59 */     return (this.x == o2.x) && (this.y == o2.y) && (this.z == o2.z);
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/*  64 */     return (this.x ^ this.z) * 31 + this.y;
/*     */   }
/*     */   
/*     */   public int compareTo(BlockCoord o) {
/*  68 */     if (this.x != o.x) return this.x < o.x ? 1 : -1;
/*  69 */     if (this.y != o.y) return this.y < o.y ? 1 : -1;
/*  70 */     if (this.z != o.z) return this.z < o.z ? 1 : -1;
/*  71 */     return 0;
/*     */   }
/*     */   
/*     */   public Vector3 toVector3Centered() {
/*  75 */     return new Vector3(this.x + 0.5D, this.y + 0.5D, this.z + 0.5D);
/*     */   }
/*     */   
/*     */   public BlockCoord multiply(int i) {
/*  79 */     this.x *= i;
/*  80 */     this.y *= i;
/*  81 */     this.z *= i;
/*  82 */     return this;
/*     */   }
/*     */   
/*     */   public double mag() {
/*  86 */     return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
/*     */   }
/*     */   
/*     */   public int mag2() {
/*  90 */     return this.x * this.x + this.y * this.y + this.z * this.z;
/*     */   }
/*     */   
/*     */   public boolean isZero() {
/*  94 */     return (this.x == 0) && (this.y == 0) && (this.z == 0);
/*     */   }
/*     */   
/*     */   public boolean isAxial() {
/*  98 */     return (this.y == 0) || (this.z == 0);
/*     */   }
/*     */   
/*     */   public BlockCoord add(BlockCoord coord2) {
/* 102 */     this.x += coord2.x;
/* 103 */     this.y += coord2.y;
/* 104 */     this.z += coord2.z;
/* 105 */     return this;
/*     */   }
/*     */   
/*     */   public BlockCoord add(int i, int j, int k) {
/* 109 */     this.x += i;
/* 110 */     this.y += j;
/* 111 */     this.z += k;
/* 112 */     return this;
/*     */   }
/*     */   
/*     */   public BlockCoord sub(BlockCoord coord2) {
/* 116 */     this.x -= coord2.x;
/* 117 */     this.y -= coord2.y;
/* 118 */     this.z -= coord2.z;
/* 119 */     return this;
/*     */   }
/*     */   
/*     */   public BlockCoord sub(int i, int j, int k) {
/* 123 */     this.x -= i;
/* 124 */     this.y -= j;
/* 125 */     this.z -= k;
/* 126 */     return this;
/*     */   }
/*     */   
/*     */   public BlockCoord offset(int side) {
/* 130 */     return offset(side, 1);
/*     */   }
/*     */   
/*     */   public BlockCoord offset(int side, int amount) {
/* 134 */     BlockCoord offset = sideOffsets[side];
/* 135 */     this.x += offset.x * amount;
/* 136 */     this.y += offset.y * amount;
/* 137 */     this.z += offset.z * amount;
/* 138 */     return this;
/*     */   }
/*     */   
/*     */   public BlockCoord inset(int side) {
/* 142 */     return inset(side, 1);
/*     */   }
/*     */   
/*     */   public BlockCoord inset(int side, int amount) {
/* 146 */     return offset(side, -amount);
/*     */   }
/*     */   
/*     */   public int getSide(int side) {
/* 150 */     switch (side) {
/*     */     case 0: 
/*     */     case 1: 
/* 153 */       return this.y;
/*     */     case 2: 
/*     */     case 3: 
/* 156 */       return this.z;
/*     */     case 4: 
/*     */     case 5: 
/* 159 */       return this.x;
/*     */     }
/* 161 */     throw new IndexOutOfBoundsException("Switch Falloff");
/*     */   }
/*     */   
/*     */   public BlockCoord setSide(int s, int v) {
/* 165 */     switch (s) {
/*     */     case 0: 
/*     */     case 1: 
/* 168 */       this.y = v;
/* 169 */       break;
/*     */     case 2: 
/*     */     case 3: 
/* 172 */       this.z = v;
/* 173 */       break;
/*     */     case 4: 
/*     */     case 5: 
/* 176 */       this.x = v;
/* 177 */       break;
/*     */     default: 
/* 179 */       throw new IndexOutOfBoundsException("Switch Falloff");
/*     */     }
/* 181 */     return this;
/*     */   }
/*     */   
/*     */   public int[] intArray() {
/* 185 */     return new int[] { this.x, this.y, this.z };
/*     */   }
/*     */   
/*     */   public BlockPos pos() {
/* 189 */     return new BlockPos(this.x, this.y, this.z);
/*     */   }
/*     */   
/*     */   public BlockCoord copy() {
/* 193 */     return new BlockCoord(this.x, this.y, this.z);
/*     */   }
/*     */   
/*     */   public BlockCoord set(int i, int j, int k) {
/* 197 */     this.x = i;
/* 198 */     this.y = j;
/* 199 */     this.z = k;
/* 200 */     return this;
/*     */   }
/*     */   
/*     */   public BlockCoord set(BlockCoord coord) {
/* 204 */     return set(coord.x, coord.y, coord.z);
/*     */   }
/*     */   
/*     */   public BlockCoord set(BlockPos pos) {
/* 208 */     return set(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
/*     */   }
/*     */   
/*     */   public BlockCoord set(int[] ia) {
/* 212 */     return set(ia[0], ia[1], ia[2]);
/*     */   }
/*     */   
/*     */   public BlockCoord set(TileEntity tile) {
/* 216 */     return set(tile.func_174877_v());
/*     */   }
/*     */   
/*     */   public int toSide() {
/* 220 */     if (!isAxial()) return -1;
/* 221 */     if (this.y < 0) return 0;
/* 222 */     if (this.y > 0) return 1;
/* 223 */     if (this.z < 0) return 2;
/* 224 */     if (this.z > 0) return 3;
/* 225 */     if (this.x < 0) return 4;
/* 226 */     if (this.x > 0) { return 5;
/*     */     }
/* 228 */     return -1;
/*     */   }
/*     */   
/*     */   public int absSum() {
/* 232 */     return (this.x < 0 ? -this.x : this.x) + (this.y < 0 ? -this.y : this.y) + (this.z < 0 ? -this.z : this.z);
/*     */   }
/*     */   
/*     */ 
/*     */   public String toString()
/*     */   {
/* 238 */     return "(" + this.x + ", " + this.y + ", " + this.z + ")";
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/codechicken/lib/vec/BlockCoord.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */