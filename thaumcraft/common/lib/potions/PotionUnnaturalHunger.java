/*    */ package thaumcraft.common.lib.potions;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.potion.Potion;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ 
/*    */ public class PotionUnnaturalHunger extends Potion
/*    */ {
/* 13 */   public static PotionUnnaturalHunger instance = null;
/* 14 */   private int statusIconIndex = -1;
/*    */   
/*    */   public PotionUnnaturalHunger(boolean par2, int par3)
/*    */   {
/* 18 */     super(new ResourceLocation("unnatural_hunger"), par2, par3);
/* 19 */     func_76399_b(0, 0);
/*    */   }
/*    */   
/*    */   public static void init()
/*    */   {
/* 24 */     instance.func_76390_b("potion.unhunger");
/* 25 */     instance.func_76399_b(7, 1);
/* 26 */     instance.func_76404_a(0.25D);
/*    */   }
/*    */   
/*    */   public boolean func_76398_f()
/*    */   {
/* 31 */     return true;
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public int func_76392_e()
/*    */   {
/* 37 */     Minecraft.func_71410_x().field_71446_o.func_110577_a(rl);
/* 38 */     return super.func_76392_e();
/*    */   }
/*    */   
/* 41 */   static final ResourceLocation rl = new ResourceLocation("thaumcraft", "textures/misc/potions.png");
/*    */   
/*    */   public void func_76394_a(EntityLivingBase target, int par2)
/*    */   {
/* 45 */     if ((!target.field_70170_p.field_72995_K) && ((target instanceof EntityPlayer)))
/*    */     {
/* 47 */       ((EntityPlayer)target).func_71020_j(0.025F * (par2 + 1));
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean func_76397_a(int par1, int par2)
/*    */   {
/* 54 */     return true;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/potions/PotionUnnaturalHunger.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */