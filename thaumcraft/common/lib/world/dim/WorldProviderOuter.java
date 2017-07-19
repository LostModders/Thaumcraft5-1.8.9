/*     */ package thaumcraft.common.lib.world.dim;
/*     */ 
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraft.world.chunk.Chunk;
/*     */ import net.minecraft.world.chunk.IChunkProvider;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.lib.world.biomes.BiomeHandler;
/*     */ 
/*     */ public class WorldProviderOuter extends WorldProvider
/*     */ {
/*     */   public String func_80007_l()
/*     */   {
/*  20 */     return "The Outer Lands";
/*     */   }
/*     */   
/*     */ 
/*     */   public String getWelcomeMessage()
/*     */   {
/*  26 */     return "Entering The Outer Lands";
/*     */   }
/*     */   
/*     */ 
/*     */   public String getDepartMessage()
/*     */   {
/*  32 */     return "Leaving The Outer Lands";
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean shouldMapSpin(String entity, double x, double y, double z)
/*     */   {
/*  38 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean canBlockFreeze(BlockPos pos, boolean byWater)
/*     */   {
/*  44 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean canSnowAt(BlockPos pos, boolean checkLight)
/*     */   {
/*  50 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean canDoLightning(Chunk chunk)
/*     */   {
/*  56 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean canDoRainSnowIce(Chunk chunk)
/*     */   {
/*  62 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_76572_b()
/*     */   {
/*  69 */     this.field_76578_c = new net.minecraft.world.biome.WorldChunkManagerHell(BiomeHandler.biomeEldritchLands, 0.0F);
/*  70 */     this.field_76574_g = Config.dimensionOuterId;
/*  71 */     this.field_76576_e = true;
/*     */   }
/*     */   
/*     */ 
/*     */   public IChunkProvider func_76555_c()
/*     */   {
/*  77 */     return new ChunkProviderOuter(this.field_76579_a, this.field_76579_a.func_72905_C(), true);
/*     */   }
/*     */   
/*     */ 
/*     */   public float func_76563_a(long p_76563_1_, float p_76563_3_)
/*     */   {
/*  83 */     return 0.0F;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public float[] func_76560_a(float p_76560_1_, float p_76560_2_)
/*     */   {
/*  90 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public Vec3 func_76562_b(float p_76562_1_, float p_76562_2_)
/*     */   {
/*  97 */     int i = 10518688;
/*  98 */     float f2 = MathHelper.func_76134_b(p_76562_1_ * 3.1415927F * 2.0F) * 2.0F + 0.5F;
/*     */     
/* 100 */     if (f2 < 0.0F)
/*     */     {
/* 102 */       f2 = 0.0F;
/*     */     }
/*     */     
/* 105 */     if (f2 > 1.0F)
/*     */     {
/* 107 */       f2 = 1.0F;
/*     */     }
/*     */     
/* 110 */     float f3 = (i >> 16 & 0xFF) / 255.0F;
/* 111 */     float f4 = (i >> 8 & 0xFF) / 255.0F;
/* 112 */     float f5 = (i & 0xFF) / 255.0F;
/* 113 */     f3 *= (f2 * 0.0F + 0.15F);
/* 114 */     f4 *= (f2 * 0.0F + 0.15F);
/* 115 */     f5 *= (f2 * 0.0F + 0.15F);
/* 116 */     return new Vec3(f3, f4, f5);
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_76561_g()
/*     */   {
/* 123 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_76567_e()
/*     */   {
/* 129 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_76569_d()
/*     */   {
/* 135 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public float func_76571_f()
/*     */   {
/* 142 */     return 1.0F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_76566_a(int x, int z)
/*     */   {
/* 149 */     return this.field_76579_a.func_175703_c(new BlockPos(x, 0, z)).func_149688_o().func_76230_c();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int func_76557_i()
/*     */   {
/* 156 */     return 50;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_76568_b(int p_76568_1_, int p_76568_2_)
/*     */   {
/* 163 */     return true;
/*     */   }
/*     */   
/*     */   public String func_177498_l()
/*     */   {
/* 168 */     return "OL";
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/world/dim/WorldProviderOuter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */