/*     */ package thaumcraft.common.lib.world.dim;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.LongHashMap;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.Teleporter;
/*     */ import net.minecraft.world.Teleporter.PortalPosition;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraft.world.WorldServer;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ 
/*     */ 
/*     */ public class TeleporterThaumcraft
/*     */   extends Teleporter
/*     */ {
/*     */   private final WorldServer worldServerInstance;
/*     */   private final Random random;
/*  24 */   private static final LongHashMap destinationCoordinateCache = new LongHashMap();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  30 */   private static final List destinationCoordinateKeys = new ArrayList();
/*     */   
/*     */   public TeleporterThaumcraft(WorldServer par1WorldServer)
/*     */   {
/*  34 */     super(par1WorldServer);
/*  35 */     this.worldServerInstance = par1WorldServer;
/*  36 */     this.random = new Random(par1WorldServer.func_72905_C());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_180266_a(Entity entityIn, float rotationYaw)
/*     */   {
/*  45 */     if (this.worldServerInstance.field_73011_w.func_177502_q() != 1)
/*     */     {
/*  47 */       if (!func_180620_b(entityIn, rotationYaw))
/*     */       {
/*  49 */         func_85188_a(entityIn);
/*  50 */         func_180620_b(entityIn, rotationYaw);
/*     */       }
/*     */       
/*     */ 
/*     */     }
/*  55 */     else if (!func_180620_b(entityIn, rotationYaw))
/*     */     {
/*  57 */       int i = MathHelper.func_76128_c(entityIn.field_70165_t);
/*  58 */       int k = MathHelper.func_76128_c(entityIn.field_70161_v);
/*  59 */       BlockPos j = this.worldServerInstance.func_175645_m(new BlockPos(i, 0, k));
/*  60 */       byte b0 = 1;
/*  61 */       byte b1 = 0;
/*  62 */       entityIn.func_70012_b(i, j.func_177956_o() + 4.0D, k, entityIn.field_70177_z, 0.0F);
/*  63 */       entityIn.field_70159_w = (entityIn.field_70181_x = entityIn.field_70179_y = 0.0D);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_180620_b(Entity entityIn, float rotationYaw)
/*     */   {
/*  75 */     short short1 = 128;
/*  76 */     double d3 = -1.0D;
/*  77 */     Object object = BlockPos.field_177992_a;
/*  78 */     int l = MathHelper.func_76128_c(entityIn.field_70165_t);
/*  79 */     int i1 = MathHelper.func_76128_c(entityIn.field_70161_v);
/*  80 */     int chunkX = l >> 4;
/*  81 */     int chunkZ = i1 >> 4;
/*  82 */     String hs = chunkX + ":" + chunkZ + ":" + this.worldServerInstance.field_73011_w.func_177502_q();
/*  83 */     long j1 = hs.hashCode();
/*  84 */     boolean flag = true;
/*     */     
/*     */ 
/*     */ 
/*  88 */     if (destinationCoordinateCache.func_76161_b(j1))
/*     */     {
/*  90 */       Teleporter.PortalPosition portalposition = (Teleporter.PortalPosition)destinationCoordinateCache.func_76164_a(j1);
/*  91 */       d3 = 0.0D;
/*  92 */       object = portalposition;
/*  93 */       portalposition.field_85087_d = this.worldServerInstance.func_82737_E();
/*  94 */       flag = false;
/*     */     }
/*     */     else
/*     */     {
/*  98 */       for (int k1 = l - short1; k1 <= l + short1; k1++)
/*     */       {
/* 100 */         double d5 = k1 + 0.5D - entityIn.field_70165_t;
/*     */         
/* 102 */         for (int l1 = i1 - short1; l1 <= i1 + short1; l1++)
/*     */         {
/* 104 */           double d6 = l1 + 0.5D - entityIn.field_70161_v;
/*     */           
/* 106 */           for (int i2 = this.worldServerInstance.func_72940_L() - 1; i2 >= 0; i2--)
/*     */           {
/* 108 */             BlockPos blockpos = new BlockPos(k1, i2, l1);
/* 109 */             if (this.worldServerInstance.func_180495_p(blockpos) == BlocksTC.eldritch.func_176203_a(6))
/*     */             {
/*     */ 
/* 112 */               double d4 = i2 + 0.5D - entityIn.field_70163_u;
/* 113 */               double d7 = d5 * d5 + d4 * d4 + d6 * d6;
/*     */               
/* 115 */               if ((d3 < 0.0D) || (d7 < d3))
/*     */               {
/* 117 */                 d3 = d7;
/* 118 */                 object = blockpos;
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 126 */     if (d3 >= 0.0D)
/*     */     {
/* 128 */       if (flag)
/*     */       {
/* 130 */         destinationCoordinateCache.func_76163_a(j1, new Teleporter.PortalPosition(this, (BlockPos)object, this.worldServerInstance.func_82737_E()));
/* 131 */         destinationCoordinateKeys.add(Long.valueOf(j1));
/*     */       }
/*     */       
/* 134 */       double d8 = ((BlockPos)object).func_177958_n() + 0.5D + (this.worldServerInstance.field_73012_v.nextBoolean() ? 1 : -1);
/* 135 */       double d9 = ((BlockPos)object).func_177956_o();
/* 136 */       double d4 = ((BlockPos)object).func_177952_p() + 0.5D + (this.worldServerInstance.field_73012_v.nextBoolean() ? 1 : -1);
/*     */       
/* 138 */       entityIn.field_70159_w = (entityIn.field_70181_x = entityIn.field_70179_y = 0.0D);
/*     */       
/* 140 */       entityIn.func_70012_b(d8, d9, d4, entityIn.field_70177_z, entityIn.field_70125_A);
/* 141 */       return true;
/*     */     }
/*     */     
/*     */ 
/* 145 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_85188_a(Entity par1Entity)
/*     */   {
/* 152 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_85189_a(long par1)
/*     */   {
/* 162 */     if (par1 % 100L == 0L)
/*     */     {
/* 164 */       Iterator iterator = destinationCoordinateKeys.iterator();
/* 165 */       long j = par1 - 600L;
/*     */       
/* 167 */       while (iterator.hasNext())
/*     */       {
/* 169 */         Long olong = (Long)iterator.next();
/* 170 */         Teleporter.PortalPosition portalposition = (Teleporter.PortalPosition)destinationCoordinateCache.func_76164_a(olong.longValue());
/*     */         
/* 172 */         if ((portalposition == null) || (portalposition.field_85087_d < j))
/*     */         {
/* 174 */           iterator.remove();
/* 175 */           destinationCoordinateCache.func_76159_d(olong.longValue());
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/world/dim/TeleporterThaumcraft.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */