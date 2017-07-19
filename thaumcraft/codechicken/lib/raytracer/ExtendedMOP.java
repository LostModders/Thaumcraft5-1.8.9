/*    */ package thaumcraft.codechicken.lib.raytracer;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.util.MovingObjectPosition;
/*    */ import thaumcraft.codechicken.lib.vec.BlockCoord;
/*    */ import thaumcraft.codechicken.lib.vec.Vector3;
/*    */ 
/*    */ public class ExtendedMOP
/*    */   extends MovingObjectPosition
/*    */   implements Comparable<ExtendedMOP>
/*    */ {
/*    */   public double dist;
/*    */   
/*    */   public ExtendedMOP(Entity entity, Vector3 hit, Object data, double dist)
/*    */   {
/* 17 */     super(entity, hit.vec3());
/* 18 */     setData(data);
/* 19 */     this.dist = dist;
/*    */   }
/*    */   
/*    */   public ExtendedMOP(Vector3 hit, int side, BlockCoord pos, Object data, double dist) {
/* 23 */     super(hit.vec3(), side >= 0 ? EnumFacing.values()[side] : EnumFacing.UP, pos.pos());
/* 24 */     setData(data);
/* 25 */     this.dist = dist;
/*    */   }
/*    */   
/*    */   public void setData(Object data) {
/* 29 */     if ((data instanceof Integer))
/* 30 */       this.subHit = ((Integer)data).intValue();
/* 31 */     this.hitInfo = data;
/*    */   }
/*    */   
/*    */   public int compareTo(ExtendedMOP o)
/*    */   {
/* 36 */     return this.dist < o.dist ? -1 : this.dist == o.dist ? 0 : 1;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/codechicken/lib/raytracer/ExtendedMOP.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */