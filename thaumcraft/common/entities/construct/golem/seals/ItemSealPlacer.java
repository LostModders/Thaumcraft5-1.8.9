/*    */ package thaumcraft.common.entities.construct.golem.seals;
/*    */ 
/*    */ import java.util.LinkedHashMap;
/*    */ import java.util.List;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.player.PlayerCapabilities;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.api.golems.ISealDisplayer;
/*    */ import thaumcraft.api.golems.seals.ISeal;
/*    */ import thaumcraft.api.items.ItemsTC;
/*    */ import thaumcraft.common.items.ItemGenericVariants;
/*    */ 
/*    */ public class ItemSealPlacer
/*    */   extends ItemGenericVariants implements ISealDisplayer
/*    */ {
/*    */   public ItemSealPlacer()
/*    */   {
/* 25 */     super(new String[] { "blank" });
/* 26 */     func_77625_d(64);
/* 27 */     func_77627_a(true);
/* 28 */     func_77656_e(0);
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*    */   {
/* 34 */     String[] vn = getVariantNames();
/* 35 */     for (int a = 0; a < vn.length; a++) {
/* 36 */       par3List.add(new ItemStack(this, 1, a));
/*    */     }
/*    */   }
/*    */   
/*    */   public String[] getVariantNames() {
/* 41 */     if (SealHandler.types.size() + 1 != this.variants.length) {
/* 42 */       String[] rs = SealHandler.getRegisteredSeals();
/* 43 */       String[] out = new String[rs.length + 1];
/* 44 */       out[0] = "blank";
/* 45 */       int indx = 1;
/* 46 */       for (String s : rs) {
/* 47 */         String[] sp = s.split(":");
/* 48 */         out[indx] = (sp.length > 1 ? sp[1] : sp[0]);
/* 49 */         indx++;
/*    */       }
/* 51 */       this.variants = out;
/*    */     }
/* 53 */     return this.variants;
/*    */   }
/*    */   
/*    */   public static ItemStack getSealStack(String sealKey) {
/* 57 */     String[] rs = SealHandler.getRegisteredSeals();
/* 58 */     int indx = 1;
/* 59 */     for (String s : rs) {
/* 60 */       if (s.equals(sealKey)) {
/* 61 */         return new ItemStack(ItemsTC.seals, 1, indx);
/*    */       }
/* 63 */       indx++;
/*    */     }
/* 65 */     return null;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public boolean func_180614_a(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
/*    */   {
/* 72 */     if ((world.field_72995_K) || (stack.func_77952_i() == 0)) { return false;
/*    */     }
/* 74 */     if (!player.func_175151_a(pos, side, stack)) { return false;
/*    */     }
/* 76 */     String[] rs = SealHandler.getRegisteredSeals();
/*    */     
/* 78 */     ISeal seal = null;
/*    */     try {
/* 80 */       seal = (ISeal)SealHandler.getSeal(rs[(stack.func_77952_i() - 1)]).getClass().newInstance();
/*    */     } catch (Exception e) {
/* 82 */       e.printStackTrace();
/*    */     }
/*    */     
/* 85 */     if ((seal == null) || (!seal.canPlaceAt(world, pos, side))) { return false;
/*    */     }
/* 87 */     if ((SealHandler.addSealEntity(world, pos, side, seal, player)) && 
/* 88 */       (!player.field_71075_bZ.field_75098_d)) { stack.field_77994_a -= 1;
/*    */     }
/* 90 */     return true;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/construct/golem/seals/ItemSealPlacer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */