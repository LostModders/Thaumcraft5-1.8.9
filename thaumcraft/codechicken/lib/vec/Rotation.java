/*     */ package thaumcraft.codechicken.lib.vec;
/*     */ 
/*     */ import java.math.BigDecimal;
/*     */ import java.math.MathContext;
/*     */ import java.math.RoundingMode;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.codechicken.lib.math.MathHelper;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Rotation
/*     */   extends Transformation
/*     */ {
/*  19 */   public static Transformation[] quarterRotations = { new RedundantTransformation(), new VariableTransformation(new Matrix4(0.0D, 0.0D, -1.0D, 0.0D, 0.0D, 1.0D, 0.0D, 0.0D, 1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D))new VariableTransformationnew Matrix4
/*     */   {
/*     */     public void apply(Vector3 vec)
/*     */     {
/*  23 */       double d1 = vec.x;double d2 = vec.z;
/*  24 */       vec.x = (-d2);vec.z = d1; }
/*     */     
/*  26 */     public Transformation inverse() { return Rotation.quarterRotations[3];
/*     */     }
/*  19 */   }, new VariableTransformation(new Matrix4(-1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D, 0.0D, 0.0D, 0.0D, 0.0D, -1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D))new VariableTransformationnew Matrix4
/*     */   {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public void apply(Vector3 vec)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*  30 */       vec.x = (-vec.x);vec.z = (-vec.z); }
/*     */     
/*  32 */     public Transformation inverse() { return this;
/*     */     }
/*  19 */   }, new VariableTransformation(new Matrix4(0.0D, 0.0D, 1.0D, 0.0D, 0.0D, 1.0D, 0.0D, 0.0D, -1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D))
/*     */   {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public void apply(Vector3 vec)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  36 */       double d1 = vec.x;double d2 = vec.z;
/*  37 */       vec.x = d2;vec.z = (-d1); }
/*     */     
/*  39 */     public Transformation inverse() { return Rotation.quarterRotations[1];
/*     */     }
/*  19 */   } };
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
/*  43 */   public static Transformation[] sideRotations = { new RedundantTransformation(), new VariableTransformation(new Matrix4(1.0D, 0.0D, 0.0D, 0.0D, 0.0D, -1.0D, 0.0D, 0.0D, 0.0D, 0.0D, -1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D))new VariableTransformationnew Matrix4
/*     */   {
/*     */     public void apply(Vector3 vec)
/*     */     {
/*  47 */       vec.y = (-vec.y);vec.z = (-vec.z); }
/*     */     
/*  49 */     public Transformation inverse() { return this;
/*     */     }
/*  43 */   }, new VariableTransformation(new Matrix4(1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, -1.0D, 0.0D, 0.0D, 1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D))new VariableTransformationnew Matrix4
/*     */   {
/*     */ 
/*     */ 
/*     */ 
/*     */     public void apply(Vector3 vec)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*  53 */       double d1 = vec.y;double d2 = vec.z;
/*  54 */       vec.y = (-d2);vec.z = d1; }
/*     */     
/*  56 */     public Transformation inverse() { return Rotation.sideRotations[3];
/*     */     }
/*  43 */   }, new VariableTransformation(new Matrix4(1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D, 0.0D, 0.0D, -1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D))new VariableTransformationnew Matrix4
/*     */   {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public void apply(Vector3 vec)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  60 */       double d1 = vec.y;double d2 = vec.z;
/*  61 */       vec.y = d2;vec.z = (-d1); }
/*     */     
/*  63 */     public Transformation inverse() { return Rotation.sideRotations[2];
/*     */     }
/*  43 */   }, new VariableTransformation(new Matrix4(0.0D, 1.0D, 0.0D, 0.0D, -1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D))new VariableTransformationnew Matrix4
/*     */   {
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
/*     */     public void apply(Vector3 vec)
/*     */     {
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
/*  67 */       double d0 = vec.x;double d1 = vec.y;
/*  68 */       vec.x = d1;vec.y = (-d0); }
/*     */     
/*  70 */     public Transformation inverse() { return Rotation.sideRotations[5];
/*     */     }
/*  43 */   }, new VariableTransformation(new Matrix4(0.0D, -1.0D, 0.0D, 0.0D, 1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D))
/*     */   {
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
/*     */     public void apply(Vector3 vec)
/*     */     {
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
/*  74 */       double d0 = vec.x;double d1 = vec.y;
/*  75 */       vec.x = (-d1);vec.y = d0; }
/*     */     
/*  77 */     public Transformation inverse() { return Rotation.sideRotations[4];
/*     */     }
/*  43 */   } };
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  81 */   public static Vector3[] axes = { new Vector3(0.0D, -1.0D, 0.0D), new Vector3(0.0D, 1.0D, 0.0D), new Vector3(0.0D, 0.0D, -1.0D), new Vector3(0.0D, 0.0D, 1.0D), new Vector3(-1.0D, 0.0D, 0.0D), new Vector3(1.0D, 0.0D, 0.0D) };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  89 */   public static int[] sideRotMap = { 3, 4, 2, 5, 3, 5, 2, 4, 1, 5, 0, 4, 1, 4, 0, 5, 1, 2, 0, 3, 1, 3, 0, 2 };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  97 */   public static int[] rotSideMap = { -1, -1, 2, 0, 1, 3, -1, -1, 2, 0, 3, 1, 2, 0, -1, -1, 3, 1, 2, 0, -1, -1, 1, 3, 2, 0, 1, 3, -1, -1, 2, 0, 3, 1, -1, -1 };
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
/* 108 */   public static int[] sideRotOffsets = { 0, 2, 2, 0, 1, 3 };
/*     */   public double angle;
/*     */   
/* 111 */   public static int rotateSide(int s, int r) { return sideRotMap[(s << 2 | r)]; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static int rotationTo(int s1, int s2)
/*     */   {
/* 118 */     if ((s1 & 0x6) == (s2 & 0x6))
/* 119 */       throw new IllegalArgumentException("Faces " + s1 + " and " + s2 + " are opposites");
/* 120 */     return rotSideMap[(s1 * 6 + s2)];
/*     */   }
/*     */   
/*     */ 
/*     */   public Vector3 axis;
/*     */   
/*     */   private Quat quat;
/*     */   public static int getSidedRotation(EntityPlayer player, int side)
/*     */   {
/* 129 */     Vector3 look = new Vector3(player.func_70676_i(1.0F));
/* 130 */     double max = 0.0D;
/* 131 */     int maxr = 0;
/* 132 */     for (int r = 0; r < 4; r++) {
/* 133 */       Vector3 axis = axes[rotateSide(side ^ 0x1, r)];
/* 134 */       double d = look.scalarProject(axis);
/* 135 */       if (d > max) {
/* 136 */         max = d;
/* 137 */         maxr = r;
/*     */       }
/*     */     }
/* 140 */     return maxr;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static Transformation sideOrientation(int s, int r)
/*     */   {
/* 147 */     return quarterRotations[((r + sideRotOffsets[s]) % 4)].with(sideRotations[s]);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static int getSideFromLookAngle(EntityLivingBase entity)
/*     */   {
/* 155 */     Vector3 look = new Vector3(entity.func_70676_i(1.0F));
/* 156 */     double max = 0.0D;
/* 157 */     int maxs = 0;
/* 158 */     for (int s = 0; s < 6; s++) {
/* 159 */       double d = look.scalarProject(axes[s]);
/* 160 */       if (d > max) {
/* 161 */         max = d;
/* 162 */         maxs = s;
/*     */       }
/*     */     }
/* 165 */     return maxs;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Rotation(double angle, Vector3 axis)
/*     */   {
/* 174 */     this.angle = angle;
/* 175 */     this.axis = axis;
/*     */   }
/*     */   
/*     */   public Rotation(double angle, double x, double y, double z) {
/* 179 */     this(angle, new Vector3(x, y, z));
/*     */   }
/*     */   
/*     */   public Rotation(Quat quat) {
/* 183 */     this.quat = quat;
/*     */     
/* 185 */     this.angle = (Math.acos(quat.s) * 2.0D);
/* 186 */     if (this.angle == 0.0D) {
/* 187 */       this.axis = new Vector3(0.0D, 1.0D, 0.0D);
/*     */     } else {
/* 189 */       double sa = Math.sin(this.angle * 0.5D);
/* 190 */       this.axis = new Vector3(quat.x / sa, quat.y / sa, quat.z / sa);
/*     */     }
/*     */   }
/*     */   
/*     */   public void apply(Vector3 vec)
/*     */   {
/* 196 */     if (this.quat == null) {
/* 197 */       this.quat = Quat.aroundAxis(this.axis, this.angle);
/*     */     }
/* 199 */     vec.rotate(this.quat);
/*     */   }
/*     */   
/*     */   public void applyN(Vector3 normal)
/*     */   {
/* 204 */     apply(normal);
/*     */   }
/*     */   
/*     */   public void apply(Matrix4 mat)
/*     */   {
/* 209 */     mat.rotate(this.angle, this.axis);
/*     */   }
/*     */   
/*     */   public Quat toQuat() {
/* 213 */     if (this.quat == null)
/* 214 */       this.quat = Quat.aroundAxis(this.axis, this.angle);
/* 215 */     return this.quat;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void glApply()
/*     */   {
/* 221 */     GlStateManager.func_179114_b((float)(this.angle * 57.29577951308232D), (float)this.axis.x, (float)this.axis.y, (float)this.axis.z);
/*     */   }
/*     */   
/*     */   public Transformation inverse()
/*     */   {
/* 226 */     return new Rotation(-this.angle, this.axis);
/*     */   }
/*     */   
/*     */   public Transformation merge(Transformation next)
/*     */   {
/* 231 */     if ((next instanceof Rotation)) {
/* 232 */       Rotation r = (Rotation)next;
/* 233 */       if (r.axis.equalsT(this.axis)) {
/* 234 */         return new Rotation(this.angle + r.angle, this.axis);
/*     */       }
/* 236 */       return new Rotation(toQuat().copy().multiply(r.toQuat()));
/*     */     }
/*     */     
/* 239 */     return null;
/*     */   }
/*     */   
/*     */   public boolean isRedundant()
/*     */   {
/* 244 */     return MathHelper.between(-1.0E-5D, this.angle, 1.0E-5D);
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 249 */     MathContext cont = new MathContext(4, RoundingMode.HALF_UP);
/* 250 */     return "Rotation(" + new BigDecimal(this.angle, cont) + ", " + new BigDecimal(this.axis.x, cont) + ", " + new BigDecimal(this.axis.y, cont) + ", " + new BigDecimal(this.axis.z, cont) + ")";
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/codechicken/lib/vec/Rotation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */