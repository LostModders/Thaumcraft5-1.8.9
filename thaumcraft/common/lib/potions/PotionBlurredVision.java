/*    */ package thaumcraft.common.lib.potions;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.potion.Potion;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ 
/*    */ public class PotionBlurredVision extends Potion
/*    */ {
/* 12 */   public static PotionBlurredVision instance = null;
/* 13 */   private int statusIconIndex = -1;
/*    */   
/*    */   public PotionBlurredVision(boolean par2, int par3)
/*    */   {
/* 17 */     super(new ResourceLocation("blurred_vision"), par2, par3);
/* 18 */     func_76399_b(0, 0);
/*    */   }
/*    */   
/*    */   public static void init()
/*    */   {
/* 23 */     instance.func_76390_b("potion.blurred");
/* 24 */     instance.func_76399_b(5, 2);
/* 25 */     instance.func_76404_a(0.25D);
/*    */   }
/*    */   
/*    */   public boolean func_76398_f()
/*    */   {
/* 30 */     return true;
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public int func_76392_e()
/*    */   {
/* 36 */     Minecraft.func_71410_x().field_71446_o.func_110577_a(rl);
/* 37 */     return super.func_76392_e();
/*    */   }
/*    */   
/* 40 */   static final ResourceLocation rl = new ResourceLocation("thaumcraft", "textures/misc/potions.png");
/*    */   
/*    */   public void func_76394_a(EntityLivingBase target, int par2) {}
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/potions/PotionBlurredVision.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */