/*    */ package thaumcraft.common.lib.world;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.world.gen.structure.StructureVillagePieces.PieceWeight;
/*    */ import net.minecraft.world.gen.structure.StructureVillagePieces.Start;
/*    */ import net.minecraft.world.gen.structure.StructureVillagePieces.Village;
/*    */ import net.minecraftforge.fml.common.registry.VillagerRegistry.IVillageCreationHandler;
/*    */ import thaumcraft.common.lib.world.objects.ComponentBankerHome;
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
/*    */ 
/*    */ 
/*    */ public class VillageBankerManager
/*    */   implements VillagerRegistry.IVillageCreationHandler
/*    */ {
/*    */   public StructureVillagePieces.PieceWeight getVillagePieceWeight(Random random, int i)
/*    */   {
/* 39 */     return new StructureVillagePieces.PieceWeight(ComponentBankerHome.class, 25, MathHelper.func_76136_a(random, 0 + i, 1 + i));
/*    */   }
/*    */   
/*    */ 
/*    */   public Class<?> getComponentClass()
/*    */   {
/* 45 */     return ComponentBankerHome.class;
/*    */   }
/*    */   
/*    */ 
/*    */   public StructureVillagePieces.Village buildComponent(StructureVillagePieces.PieceWeight villagePiece, StructureVillagePieces.Start startPiece, List pieces, Random random, int p1, int p2, int p3, EnumFacing p4, int p5)
/*    */   {
/* 51 */     return ComponentBankerHome.buildComponent(startPiece, pieces, random, p1, p2, p3, p4, p5);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/world/VillageBankerManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */