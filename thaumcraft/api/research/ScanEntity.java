/*    */ package thaumcraft.api.research;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import thaumcraft.api.ThaumcraftApi.EntityTagsNBT;
/*    */ import thaumcraft.api.ThaumcraftApiHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ScanEntity
/*    */   implements IScanThing
/*    */ {
/*    */   String research;
/*    */   Class entityClass;
/*    */   ThaumcraftApi.EntityTagsNBT[] NBTData;
/* 18 */   boolean inheritedClasses = false;
/*    */   
/*    */   public ScanEntity(String research, Class entityClass, boolean inheritedClasses) {
/* 21 */     this.research = research;
/* 22 */     this.entityClass = entityClass;
/* 23 */     this.inheritedClasses = inheritedClasses;
/*    */   }
/*    */   
/*    */   public ScanEntity(String research, Class entityClass, boolean inheritedClasses, ThaumcraftApi.EntityTagsNBT... nbt) {
/* 27 */     this.research = research;
/* 28 */     this.entityClass = entityClass;
/* 29 */     this.inheritedClasses = inheritedClasses;
/* 30 */     this.NBTData = nbt;
/*    */   }
/*    */   
/*    */   public boolean checkThing(EntityPlayer player, Object obj)
/*    */   {
/* 35 */     if ((obj != null) && (((!this.inheritedClasses) && (this.entityClass == obj.getClass())) || ((this.inheritedClasses) && (this.entityClass.isInstance(obj)))))
/*    */     {
/* 37 */       if ((this.NBTData != null) && (this.NBTData.length > 0)) {
/* 38 */         boolean b = true;
/* 39 */         NBTTagCompound tc = new NBTTagCompound();
/* 40 */         ((Entity)obj).func_70109_d(tc);
/* 41 */         for (ThaumcraftApi.EntityTagsNBT nbt : this.NBTData) {
/* 42 */           if ((!tc.func_74764_b(nbt.name)) || (!ThaumcraftApiHelper.getNBTDataFromId(tc, tc.func_150299_b(nbt.name), nbt.name).equals(nbt.value))) {
/* 43 */             return false;
/*    */           }
/*    */         }
/*    */       }
/* 47 */       return true;
/*    */     }
/* 49 */     return false;
/*    */   }
/*    */   
/*    */   public String getResearchKey()
/*    */   {
/* 54 */     return this.research;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/api/research/ScanEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */