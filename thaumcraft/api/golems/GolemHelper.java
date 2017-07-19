/*    */ package thaumcraft.api.golems;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.WorldProvider;
/*    */ import thaumcraft.api.ThaumcraftApi;
/*    */ import thaumcraft.api.golems.seals.ISeal;
/*    */ import thaumcraft.api.golems.seals.ISealEntity;
/*    */ import thaumcraft.api.golems.seals.SealPos;
/*    */ import thaumcraft.api.golems.tasks.Task;
/*    */ import thaumcraft.api.internal.IInternalMethodHandler;
/*    */ 
/*    */ 
/*    */ public class GolemHelper
/*    */ {
/*    */   public static void registerSeal(ISeal seal)
/*    */   {
/* 23 */     ThaumcraftApi.internalMethods.registerSeal(seal);
/*    */   }
/*    */   
/*    */   public static ISeal getSeal(String key) {
/* 27 */     return ThaumcraftApi.internalMethods.getSeal(key);
/*    */   }
/*    */   
/*    */   public static ItemStack getSealStack(String key) {
/* 31 */     return ThaumcraftApi.internalMethods.getSealStack(key);
/*    */   }
/*    */   
/*    */   public static ISealEntity getSealEntity(int dim, SealPos pos) {
/* 35 */     return ThaumcraftApi.internalMethods.getSealEntity(dim, pos);
/*    */   }
/*    */   
/*    */   public static void addGolemTask(int dim, Task task) {
/* 39 */     ThaumcraftApi.internalMethods.addGolemTask(dim, task);
/*    */   }
/*    */   
/* 42 */   public static HashMap<Integer, ArrayList<ProvisionRequest>> provisionRequests = new HashMap();
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static void requestProvisioning(World world, ISealEntity seal, ItemStack stack)
/*    */   {
/* 51 */     if (!provisionRequests.containsKey(Integer.valueOf(world.field_73011_w.func_177502_q())))
/* 52 */       provisionRequests.put(Integer.valueOf(world.field_73011_w.func_177502_q()), new ArrayList());
/* 53 */     ArrayList<ProvisionRequest> list = (ArrayList)provisionRequests.get(Integer.valueOf(world.field_73011_w.func_177502_q()));
/* 54 */     ProvisionRequest pr = new ProvisionRequest(seal, stack.func_77946_l());
/* 55 */     if (!list.contains(pr)) {
/* 56 */       list.add(pr);
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static BlockPos getPosInArea(ISealEntity seal, int count)
/*    */   {
/* 67 */     int xx = 1 + (seal.getArea().func_177958_n() - 1) * (seal.getSealPos().face.func_82601_c() == 0 ? 2 : 1);
/* 68 */     int yy = 1 + (seal.getArea().func_177956_o() - 1) * (seal.getSealPos().face.func_96559_d() == 0 ? 2 : 1);
/* 69 */     int zz = 1 + (seal.getArea().func_177952_p() - 1) * (seal.getSealPos().face.func_82599_e() == 0 ? 2 : 1);
/*    */     
/* 71 */     int qx = seal.getSealPos().face.func_82601_c() != 0 ? seal.getSealPos().face.func_82601_c() : 1;
/* 72 */     int qy = seal.getSealPos().face.func_96559_d() != 0 ? seal.getSealPos().face.func_96559_d() : 1;
/* 73 */     int qz = seal.getSealPos().face.func_82599_e() != 0 ? seal.getSealPos().face.func_82599_e() : 1;
/*    */     
/* 75 */     int y = qy * (count / zz / xx) % yy + seal.getSealPos().face.func_96559_d();
/* 76 */     int x = qx * (count / zz) % xx + seal.getSealPos().face.func_82601_c();
/* 77 */     int z = qz * count % zz + seal.getSealPos().face.func_82599_e();
/*    */     
/* 79 */     BlockPos p = seal.getSealPos().pos.func_177982_a(x - (seal.getSealPos().face.func_82601_c() == 0 ? xx / 2 : 0), y - (seal.getSealPos().face.func_96559_d() == 0 ? yy / 2 : 0), z - (seal.getSealPos().face.func_82599_e() == 0 ? zz / 2 : 0));
/*    */     
/*    */ 
/*    */ 
/*    */ 
/* 84 */     return p;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static AxisAlignedBB getBoundsForArea(ISealEntity seal)
/*    */   {
/* 94 */     return AxisAlignedBB.func_178781_a(seal.getSealPos().pos.func_177958_n(), seal.getSealPos().pos.func_177956_o(), seal.getSealPos().pos.func_177952_p(), seal.getSealPos().pos.func_177958_n() + 1, seal.getSealPos().pos.func_177956_o() + 1, seal.getSealPos().pos.func_177952_p() + 1).func_72317_d(seal.getSealPos().face.func_82601_c(), seal.getSealPos().face.func_96559_d(), seal.getSealPos().face.func_82599_e()).func_72321_a(seal.getSealPos().face.func_82601_c() != 0 ? (seal.getArea().func_177958_n() - 1) * seal.getSealPos().face.func_82601_c() : 0.0D, seal.getSealPos().face.func_96559_d() != 0 ? (seal.getArea().func_177956_o() - 1) * seal.getSealPos().face.func_96559_d() : 0.0D, seal.getSealPos().face.func_82599_e() != 0 ? (seal.getArea().func_177952_p() - 1) * seal.getSealPos().face.func_82599_e() : 0.0D).func_72314_b(seal.getSealPos().face.func_82601_c() == 0 ? seal.getArea().func_177958_n() - 1 : 0.0D, seal.getSealPos().face.func_96559_d() == 0 ? seal.getArea().func_177956_o() - 1 : 0.0D, seal.getSealPos().face.func_82599_e() == 0 ? seal.getArea().func_177952_p() - 1 : 0.0D);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/api/golems/GolemHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */