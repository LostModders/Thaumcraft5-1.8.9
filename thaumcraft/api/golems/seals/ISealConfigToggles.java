/*    */ package thaumcraft.api.golems.seals;
/*    */ 
/*    */ public abstract interface ISealConfigToggles
/*    */ {
/*    */   public abstract SealToggle[] getToggles();
/*    */   
/*    */   public abstract void setToggle(int paramInt, boolean paramBoolean);
/*    */   
/*    */   public static class SealToggle
/*    */   {
/*    */     public boolean value;
/*    */     public String key;
/*    */     public String name;
/*    */     
/*    */     public SealToggle(boolean value, String key, String name)
/*    */     {
/* 17 */       this.value = value;
/* 18 */       this.key = key;
/* 19 */       this.name = name;
/*    */     }
/*    */     
/*    */     public boolean getValue() {
/* 23 */       return this.value;
/*    */     }
/*    */     
/*    */     public void setValue(boolean value) {
/* 27 */       this.value = value;
/*    */     }
/*    */     
/*    */     public String getKey() {
/* 31 */       return this.key;
/*    */     }
/*    */     
/*    */     public String getName() {
/* 35 */       return this.name;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/api/golems/seals/ISealConfigToggles.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */