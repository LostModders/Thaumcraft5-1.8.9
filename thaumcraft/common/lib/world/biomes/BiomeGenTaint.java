/*    */ package thaumcraft.common.lib.world.biomes;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.biome.BiomeGenBase;
/*    */ import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.common.config.Config;
/*    */ import thaumcraft.common.entities.monster.tainted.EntityTaintacle;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BiomeGenTaint
/*    */   extends BiomeGenBase
/*    */ {
/*    */   public BiomeGenTaint(int par1)
/*    */   {
/* 21 */     super(par1);
/* 22 */     this.field_76760_I.field_76832_z = 2;
/* 23 */     this.field_76760_I.field_76802_A = 64537;
/* 24 */     this.field_76760_I.field_76803_B = 2;
/* 25 */     this.field_76760_I.field_76799_E = 64537;
/* 26 */     func_76735_a("Tainted Land");
/* 27 */     func_76739_b(7160201);
/* 28 */     this.field_76762_K.clear();
/* 29 */     this.field_76761_J.clear();
/* 30 */     this.field_76755_L.clear();
/* 31 */     if (Config.spawnTaintacle) { this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(EntityTaintacle.class, 1, 1, 1));
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void func_180624_a(World world, Random random, BlockPos pos) {}
/*    */   
/*    */ 
/*    */   @SideOnly(Side.CLIENT)
/*    */   public int func_180627_b(BlockPos p_180627_1_)
/*    */   {
/* 43 */     return 7160201;
/*    */   }
/*    */   
/*    */ 
/*    */   @SideOnly(Side.CLIENT)
/*    */   public int func_180625_c(BlockPos p_180625_1_)
/*    */   {
/* 50 */     return 8154503;
/*    */   }
/*    */   
/*    */   public int func_76731_a(float par1)
/*    */   {
/* 55 */     return 8144127;
/*    */   }
/*    */   
/*    */ 
/*    */   public int getWaterColorMultiplier()
/*    */   {
/* 61 */     return 13373832;
/*    */   }
/*    */   
/*    */   public BiomeGenBase func_150566_k()
/*    */   {
/* 66 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/world/biomes/BiomeGenTaint.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */