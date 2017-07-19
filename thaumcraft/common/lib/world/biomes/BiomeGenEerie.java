/*    */ package thaumcraft.common.lib.world.biomes;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.monster.EntityEnderman;
/*    */ import net.minecraft.entity.monster.EntityWitch;
/*    */ import net.minecraft.entity.passive.EntityBat;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.world.biome.BiomeGenBase;
/*    */ import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.common.config.Config;
/*    */ import thaumcraft.common.entities.monster.EntityBrainyZombie;
/*    */ import thaumcraft.common.entities.monster.EntityGiantBrainyZombie;
/*    */ 
/*    */ public class BiomeGenEerie extends BiomeGenBase
/*    */ {
/*    */   public BiomeGenEerie(int par1)
/*    */   {
/* 20 */     super(par1);
/* 21 */     this.field_76762_K.clear();
/* 22 */     this.field_76762_K.add(new BiomeGenBase.SpawnListEntry(EntityBat.class, 3, 1, 1));
/*    */     
/* 24 */     this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(EntityWitch.class, 8, 1, 1));
/* 25 */     this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(EntityEnderman.class, 4, 1, 1));
/* 26 */     if (Config.spawnAngryZombie) {
/* 27 */       this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(EntityBrainyZombie.class, 32, 1, 1));
/* 28 */       this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(EntityGiantBrainyZombie.class, 8, 1, 1));
/*    */     }
/* 30 */     if (Config.spawnWisp)
/* 31 */       this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(thaumcraft.common.entities.monster.EntityWisp.class, 3, 1, 1));
/* 32 */     if (Config.spawnElder) {
/* 33 */       this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(thaumcraft.common.entities.monster.EntityEldritchGuardian.class, 1, 1, 1));
/*    */     }
/* 35 */     this.field_76760_I.field_76832_z = 2;
/* 36 */     this.field_76760_I.field_76802_A = 1;
/* 37 */     this.field_76760_I.field_76803_B = 2;
/* 38 */     func_76732_a(0.5F, 0.5F);
/* 39 */     func_76735_a("Eerie");
/* 40 */     func_76739_b(4212800);
/*    */   }
/*    */   
/*    */ 
/*    */   @SideOnly(Side.CLIENT)
/*    */   public int func_180627_b(BlockPos p_180627_1_)
/*    */   {
/* 47 */     return 4212800;
/*    */   }
/*    */   
/*    */ 
/*    */   @SideOnly(Side.CLIENT)
/*    */   public int func_180625_c(BlockPos p_180625_1_)
/*    */   {
/* 54 */     return 4212800;
/*    */   }
/*    */   
/*    */   public int func_76731_a(float par1)
/*    */   {
/* 59 */     return 2237081;
/*    */   }
/*    */   
/*    */   public int getWaterColorMultiplier()
/*    */   {
/* 64 */     return 3035999;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public BiomeGenBase func_150566_k()
/*    */   {
/* 71 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/world/biomes/BiomeGenEerie.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */