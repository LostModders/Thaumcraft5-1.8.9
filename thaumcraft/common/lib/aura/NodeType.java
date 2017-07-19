/*    */ package thaumcraft.common.lib.aura;
/*    */ 
/*    */ public abstract class NodeType
/*    */ {
/*    */   int id;
/*    */   
/*    */   public NodeType(int id) {
/*  8 */     this.id = id;
/*    */   }
/*    */   
/*    */   abstract void performTickEvent(EntityAuraNode paramEntityAuraNode);
/*    */   
/*    */   abstract void performPeriodicEvent(EntityAuraNode paramEntityAuraNode);
/*    */   
/*    */   abstract int calculateStrength(EntityAuraNode paramEntityAuraNode);
/*    */   
/* 17 */   public static NodeType[] nodeTypes = new NodeType[7];
/*    */   
/*    */   static {
/* 20 */     nodeTypes[0] = new NTNormal(0);
/* 21 */     nodeTypes[1] = new NTDark(1);
/* 22 */     nodeTypes[2] = new NTHungry(2);
/* 23 */     nodeTypes[3] = new NTPure(3);
/* 24 */     nodeTypes[4] = new NTTaint(4);
/* 25 */     nodeTypes[5] = new NTUnstable(5);
/* 26 */     nodeTypes[6] = new NTAstral(6);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/aura/NodeType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */