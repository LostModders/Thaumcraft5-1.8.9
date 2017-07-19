/*    */ package thaumcraft.common.tiles;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.nbt.NBTTagList;
/*    */ import thaumcraft.api.blocks.TileThaumcraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TileOwned
/*    */   extends TileThaumcraft
/*    */ {
/* 13 */   public String owner = "";
/* 14 */   public ArrayList<String> accessList = new ArrayList();
/*    */   
/* 16 */   public boolean safeToRemove = false;
/*    */   
/*    */ 
/*    */ 
/*    */   public void func_145839_a(NBTTagCompound nbttagcompound)
/*    */   {
/* 22 */     super.func_145839_a(nbttagcompound);
/* 23 */     this.owner = nbttagcompound.func_74779_i("owner");
/*    */     
/* 25 */     NBTTagList var2 = nbttagcompound.func_150295_c("access", 10);
/* 26 */     this.accessList = new ArrayList();
/*    */     
/* 28 */     for (int var3 = 0; var3 < var2.func_74745_c(); var3++)
/*    */     {
/* 30 */       NBTTagCompound var4 = var2.func_150305_b(var3);
/* 31 */       this.accessList.add(var4.func_74779_i("name"));
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_145841_b(NBTTagCompound nbttagcompound)
/*    */   {
/* 38 */     super.func_145841_b(nbttagcompound);
/*    */     
/*    */ 
/* 41 */     NBTTagList var2 = new NBTTagList();
/*    */     
/* 43 */     for (int var3 = 0; var3 < this.accessList.size(); var3++)
/*    */     {
/* 45 */       NBTTagCompound var4 = new NBTTagCompound();
/* 46 */       var4.func_74778_a("name", (String)this.accessList.get(var3));
/* 47 */       var2.func_74742_a(var4);
/*    */     }
/* 49 */     nbttagcompound.func_74782_a("access", var2);
/*    */   }
/*    */   
/*    */ 
/*    */   public void readCustomNBT(NBTTagCompound nbttagcompound)
/*    */   {
/* 55 */     this.owner = nbttagcompound.func_74779_i("owner");
/*    */   }
/*    */   
/*    */ 
/*    */   public void writeCustomNBT(NBTTagCompound nbttagcompound)
/*    */   {
/* 61 */     nbttagcompound.func_74778_a("owner", this.owner);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/tiles/TileOwned.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */