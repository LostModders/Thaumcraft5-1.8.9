/*    */ package thaumcraft.common.tiles.devices;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.ITickable;
/*    */ import net.minecraft.util.Vec3;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.api.blocks.TileThaumcraft;
/*    */ import thaumcraft.common.lib.aura.EntityAuraNode;
/*    */ import thaumcraft.common.lib.utils.EntityUtils;
/*    */ 
/*    */ public class TileNodeStabilizer extends TileThaumcraft implements ITickable
/*    */ {
/* 19 */   public int count = 0;
/* 20 */   int delay = 0;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 26 */   ArrayList<Entity> nodes = null;
/*    */   
/*    */   public void func_73660_a()
/*    */   {
/* 30 */     if ((this.nodes == null) || (this.delay % 100 == 0)) {
/* 31 */       this.nodes = EntityUtils.getEntitiesInRange(this.field_145850_b, this.field_174879_c.func_177958_n() + 0.5D, this.field_174879_c.func_177956_o() + 1.5D, this.field_174879_c.func_177952_p() + 0.5D, null, EntityAuraNode.class, 0.5D);
/*    */     }
/*    */     boolean notFirst;
/* 34 */     if (!gettingPower()) {
/* 35 */       notFirst = false;
/* 36 */       for (Entity e : this.nodes) {
/* 37 */         if ((e != null) && (!e.field_70128_L) && ((e instanceof EntityAuraNode))) {
/* 38 */           EntityAuraNode an = (EntityAuraNode)e;
/* 39 */           an.stablized = (!notFirst);
/* 40 */           Vec3 v1 = new Vec3(this.field_174879_c.func_177958_n() + 0.5D, this.field_174879_c.func_177956_o() + 1.5D, this.field_174879_c.func_177952_p() + 0.5D);
/* 41 */           Vec3 v2 = new Vec3(an.field_70165_t, an.field_70163_u, an.field_70161_v);
/* 42 */           double d = v1.func_72436_e(v2);
/* 43 */           if (d > 0.001D) {
/* 44 */             v1 = v1.func_178788_d(v2).func_72432_b();
/* 45 */             if (notFirst) {
/* 46 */               an.field_70159_w -= v1.field_72450_a / 750.0D;
/* 47 */               an.field_70181_x -= v1.field_72448_b / 750.0D;
/* 48 */               an.field_70179_y -= v1.field_72449_c / 750.0D;
/*    */             } else {
/* 50 */               an.field_70159_w += v1.field_72450_a / 1000.0D;
/* 51 */               an.field_70181_x += v1.field_72448_b / 1000.0D;
/* 52 */               an.field_70179_y += v1.field_72449_c / 1000.0D;
/*    */             }
/*    */           }
/* 55 */           else if (notFirst) {
/* 56 */             an.field_70181_x += 0.005D;
/*    */           }
/* 58 */           notFirst = true;
/*    */         }
/*    */       }
/*    */     }
/*    */     
/* 63 */     if ((this.field_145850_b.field_72995_K) && (this.nodes.size() > 0)) {
/* 64 */       if (!gettingPower()) {
/* 65 */         if (this.delay == 0) {
/* 66 */           this.count = 37;
/*    */         }
/* 68 */         if (this.count < 37) this.count += 1;
/*    */       }
/* 70 */       else if (this.count > 0) { this.count -= 1;
/*    */       }
/*    */     }
/*    */     
/* 74 */     if (this.delay == 0) {
/* 75 */       this.delay = this.field_145850_b.field_73012_v.nextInt(100);
/*    */     }
/*    */     
/* 78 */     this.delay += 1;
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public AxisAlignedBB getRenderBoundingBox()
/*    */   {
/* 84 */     return AxisAlignedBB.func_178781_a(func_174877_v().func_177958_n() - 0.3D, func_174877_v().func_177956_o() - 0.3D, func_174877_v().func_177952_p() - 0.3D, func_174877_v().func_177958_n() + 1.3D, func_174877_v().func_177956_o() + 1.3D, func_174877_v().func_177952_p() + 1.3D);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/tiles/devices/TileNodeStabilizer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */