/*    */ package thaumcraft.api.aspects;
/*    */ 
/*    */ import java.lang.reflect.Method;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraftforge.fml.common.FMLLog;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AspectSourceHelper
/*    */ {
/*    */   static Method drainEssentia;
/*    */   static Method findEssentia;
/*    */   
/*    */   public static boolean drainEssentia(TileEntity tile, Aspect aspect, EnumFacing direction, int range)
/*    */   {
/*    */     try
/*    */     {
/* 26 */       if (drainEssentia == null) {
/* 27 */         Class fake = Class.forName("thaumcraft.common.lib.events.EssentiaHandler");
/* 28 */         drainEssentia = fake.getMethod("drainEssentia", new Class[] { TileEntity.class, Aspect.class, EnumFacing.class, Integer.TYPE });
/*    */       }
/* 30 */       return ((Boolean)drainEssentia.invoke(null, new Object[] { tile, aspect, direction, Integer.valueOf(range) })).booleanValue();
/*    */     } catch (Exception ex) {
/* 32 */       FMLLog.warning("[Thaumcraft API] Could not invoke thaumcraft.common.lib.events.EssentiaHandler method drainEssentia", new Object[0]);
/*    */     }
/* 34 */     return false;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static boolean findEssentia(TileEntity tile, Aspect aspect, EnumFacing direction, int range)
/*    */   {
/*    */     try
/*    */     {
/* 48 */       if (findEssentia == null) {
/* 49 */         Class fake = Class.forName("thaumcraft.common.lib.events.EssentiaHandler");
/* 50 */         findEssentia = fake.getMethod("findEssentia", new Class[] { TileEntity.class, Aspect.class, EnumFacing.class, Integer.TYPE });
/*    */       }
/* 52 */       return ((Boolean)findEssentia.invoke(null, new Object[] { tile, aspect, direction, Integer.valueOf(range) })).booleanValue();
/*    */     } catch (Exception ex) {
/* 54 */       FMLLog.warning("[Thaumcraft API] Could not invoke thaumcraft.common.lib.events.EssentiaHandler method findEssentia", new Object[0]);
/*    */     }
/* 56 */     return false;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/api/aspects/AspectSourceHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */