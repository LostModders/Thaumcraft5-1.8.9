/*    */ package thaumcraft.common.lib.potions;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.potion.Potion;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ 
/*    */ public class PotionThaumarhia extends Potion
/*    */ {
/* 14 */   public static PotionThaumarhia instance = null;
/* 15 */   private int statusIconIndex = -1;
/*    */   
/*    */   public PotionThaumarhia(boolean par2, int par3)
/*    */   {
/* 19 */     super(new ResourceLocation("thaumarhia"), par2, par3);
/* 20 */     func_76399_b(0, 0);
/*    */   }
/*    */   
/*    */   public static void init()
/*    */   {
/* 25 */     instance.func_76390_b("potion.thaumarhia");
/* 26 */     instance.func_76399_b(7, 2);
/* 27 */     instance.func_76404_a(0.25D);
/*    */   }
/*    */   
/*    */   public boolean func_76398_f()
/*    */   {
/* 32 */     return true;
/*    */   }
/*    */   
/*    */   @SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*    */   public int func_76392_e()
/*    */   {
/* 38 */     Minecraft.func_71410_x().field_71446_o.func_110577_a(rl);
/* 39 */     return super.func_76392_e();
/*    */   }
/*    */   
/* 42 */   static final ResourceLocation rl = new ResourceLocation("thaumcraft", "textures/misc/potions.png");
/*    */   
/*    */   public void func_76394_a(EntityLivingBase target, int par2)
/*    */   {
/* 46 */     if ((!target.field_70170_p.field_72995_K) && (target.field_70170_p.field_73012_v.nextInt(15) == 0))
/*    */     {
/* 48 */       if (target.field_70170_p.func_175623_d(new BlockPos(target))) {
/* 49 */         target.field_70170_p.func_175656_a(new BlockPos(target), thaumcraft.api.blocks.BlocksTC.fluxGoo.func_176223_P());
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean func_76397_a(int par1, int par2)
/*    */   {
/* 57 */     return par1 % 20 == 0;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/potions/PotionThaumarhia.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */