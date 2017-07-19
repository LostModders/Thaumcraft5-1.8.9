/*    */ package thaumcraft.common.entities;
/*    */ 
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class EntityPermanentItem
/*    */   extends EntitySpecialItem
/*    */ {
/*    */   public EntityPermanentItem(World par1World)
/*    */   {
/* 11 */     super(par1World);
/*    */   }
/*    */   
/*    */   public EntityPermanentItem(World par1World, double par2, double par4, double par6, ItemStack par8ItemStack)
/*    */   {
/* 16 */     super(par1World);
/* 17 */     func_70105_a(0.25F, 0.25F);
/* 18 */     func_70107_b(par2, par4, par6);
/* 19 */     func_92058_a(par8ItemStack);
/* 20 */     this.field_70177_z = ((float)(Math.random() * 360.0D));
/* 21 */     this.field_70159_w = ((float)(Math.random() * 0.20000000298023224D - 0.10000000149011612D));
/* 22 */     this.field_70181_x = 0.20000000298023224D;
/* 23 */     this.field_70179_y = ((float)(Math.random() * 0.20000000298023224D - 0.10000000149011612D));
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void func_70071_h_()
/*    */   {
/* 30 */     super.func_70071_h_();
/* 31 */     func_174873_u();
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/EntityPermanentItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */