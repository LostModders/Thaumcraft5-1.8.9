/*     */ package thaumcraft.common.tiles.misc;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.ITickable;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
/*     */ import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.aura.AuraHelper;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.blocks.TileThaumcraft;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.entities.monster.boss.EntityCultistPortalGreater;
/*     */ import thaumcraft.common.entities.monster.boss.EntityEldritchGolem;
/*     */ import thaumcraft.common.entities.monster.boss.EntityEldritchWarden;
/*     */ import thaumcraft.common.entities.monster.boss.EntityTaintacleGiant;
/*     */ import thaumcraft.common.entities.monster.tainted.EntityTaintacle;
/*     */ import thaumcraft.common.lib.network.fx.PacketFXBlockMist;
/*     */ import thaumcraft.common.lib.utils.EntityUtils;
/*     */ import thaumcraft.common.lib.utils.Utils;
/*     */ import thaumcraft.common.lib.world.biomes.BiomeHandler;
/*     */ import thaumcraft.common.lib.world.dim.Cell;
/*     */ import thaumcraft.common.lib.world.dim.CellLoc;
/*     */ import thaumcraft.common.lib.world.dim.GenCommon;
/*     */ import thaumcraft.common.lib.world.dim.MapBossData;
/*     */ import thaumcraft.common.lib.world.dim.MazeHandler;
/*     */ 
/*     */ public class TileEldritchLock extends TileThaumcraft implements ITickable
/*     */ {
/*  42 */   public int count = -1;
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_73660_a()
/*     */   {
/*  48 */     if (this.count != -1) {
/*  49 */       this.count += 1;
/*  50 */       if (this.count % 5 == 0) {
/*  51 */         this.field_145850_b.func_72908_a(this.field_174879_c.func_177958_n(), this.field_174879_c.func_177956_o(), this.field_174879_c.func_177952_p(), "thaumcraft:pump", 1.0F, 1.0F);
/*     */       }
/*  53 */       if (this.count > 100)
/*     */       {
/*  55 */         doBossSpawn();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private void doBossSpawn()
/*     */   {
/*  63 */     this.field_145850_b.func_72908_a(this.field_174879_c.func_177958_n(), this.field_174879_c.func_177956_o(), this.field_174879_c.func_177952_p(), "thaumcraft:ice", 1.0F, 1.0F);
/*  64 */     if (!this.field_145850_b.field_72995_K)
/*     */     {
/*  66 */       int cx = this.field_174879_c.func_177958_n() >> 4;
/*  67 */       int cz = this.field_174879_c.func_177952_p() >> 4;
/*  68 */       int centerx = this.field_174879_c.func_177958_n() >> 4;
/*  69 */       int centerz = this.field_174879_c.func_177952_p() >> 4;
/*  70 */       int exit = 0;
/*  71 */       for (int a = -2; a <= 2; a++) {
/*  72 */         for (int b = -2; b <= 2; b++) {
/*  73 */           Cell c = MazeHandler.getFromHashMap(new CellLoc(cx + a, cz + b));
/*  74 */           if ((c != null) && (c.feature == 2)) {
/*  75 */             centerx = cx + a;
/*  76 */             centerz = cz + b;
/*     */           }
/*  78 */           if ((c != null) && (c.feature >= 2) && (c.feature <= 5) && ((c.north) || (c.south) || (c.east) || (c.west))) {
/*  79 */             exit = c.feature;
/*     */           }
/*     */         }
/*     */       }
/*  83 */       MapBossData mbd = (MapBossData)this.field_145850_b.func_72943_a(MapBossData.class, "BossMapData");
/*  84 */       if (mbd == null) {
/*  85 */         mbd = new MapBossData("BossMapData");
/*  86 */         mbd.bossCount = 0;
/*  87 */         mbd.func_76185_a();
/*  88 */         this.field_145850_b.func_72823_a("BossMapData", mbd);
/*     */       }
/*     */       
/*  91 */       mbd.bossCount += 1;
/*  92 */       if (this.field_145850_b.field_73012_v.nextFloat() < 0.25F) mbd.bossCount += 1;
/*  93 */       mbd.func_76185_a();
/*     */       
/*  95 */       switch (mbd.bossCount % 4) {
/*  96 */       case 0:  spawnGolemBossRoom(centerx, centerz, exit); break;
/*  97 */       case 1:  spawnWardenBossRoom(centerx, centerz, exit); break;
/*  98 */       case 2:  spawnCultistBossRoom(centerx, centerz, exit); break;
/*  99 */       case 3:  spawnTaintBossRoom(centerx, centerz, exit);
/*     */       }
/*     */       
/* 102 */       for (int a = -2; a <= 2; a++) {
/* 103 */         for (int b = -2; b <= 2; b++)
/* 104 */           for (int c = -2; c <= 2; c++)
/* 105 */             if (this.field_145850_b.func_180495_p(this.field_174879_c.func_177982_a(a, b, c)) == BlocksTC.eldritch.func_176203_a(5)) {
/* 106 */               thaumcraft.common.lib.network.PacketHandler.INSTANCE.sendToAllAround(new PacketFXBlockMist(this.field_174879_c.func_177982_a(a, b, c), 4194368), new NetworkRegistry.TargetPoint(this.field_145850_b.field_73011_w.func_177502_q(), this.field_174879_c.func_177958_n() + a, this.field_174879_c.func_177956_o() + b, this.field_174879_c.func_177952_p() + c, 32.0D));
/*     */               
/*     */ 
/* 109 */               this.field_145850_b.func_175698_g(this.field_174879_c.func_177982_a(a, b, c));
/*     */             }
/*     */       }
/* 112 */       this.field_145850_b.func_175698_g(this.field_174879_c);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 122 */   int[][] ped = { { 2, 2, 2 }, { 0, -1, 1 }, { 3, 3, 3 } };
/*     */   
/*     */   private void spawnWardenBossRoom(int cx, int cz, int exit) {
/* 125 */     for (int i = 0; i < this.field_145850_b.field_73010_i.size(); i++)
/*     */     {
/* 127 */       EntityPlayer ep = (EntityPlayer)this.field_145850_b.field_73010_i.get(i);
/* 128 */       if (ep.func_174818_b(this.field_174879_c) < 300.0D) {
/* 129 */         ep.func_145747_a(new ChatComponentText(StatCollector.func_74838_a("tc.boss.warden")));
/*     */       }
/*     */     }
/* 132 */     int x = cx * 16 + 16;
/* 133 */     int y = 50;
/* 134 */     int z = cz * 16 + 16;
/* 135 */     int x2 = x;
/* 136 */     int z2 = z;
/* 137 */     switch (exit) {
/* 138 */     case 2:  x2 += 8;z2 += 8; break;
/* 139 */     case 3:  x2 -= 8;z2 += 8; break;
/* 140 */     case 4:  x2 += 8;z2 -= 8; break;
/* 141 */     case 5:  x2 -= 8;z2 -= 8;
/*     */     }
/*     */     
/* 144 */     GenCommon.genObelisk(this.field_145850_b, new BlockPos(x2, y + 4, z));
/* 145 */     GenCommon.genObelisk(this.field_145850_b, new BlockPos(x, y + 4, z2));
/* 146 */     this.field_145850_b.func_175656_a(new BlockPos(x2, y + 2, z), BlocksTC.eldritch.func_176203_a(1));
/* 147 */     this.field_145850_b.func_175656_a(new BlockPos(x, y + 2, z2), BlocksTC.eldritch.func_176203_a(1));
/*     */     
/* 149 */     for (int a = -1; a <= 1; a++) {
/* 150 */       for (int b = -1; b <= 1; b++) {
/* 151 */         if ((a != 0) && (b != 0) && (this.field_145850_b.field_73012_v.nextFloat() < 0.9F)) {
/* 152 */           float rr = this.field_145850_b.field_73012_v.nextFloat();
/* 153 */           int md = rr < 0.3F ? 1 : rr < 0.1F ? 2 : 0;
/* 154 */           this.field_145850_b.func_175656_a(new BlockPos(x2 + a, y + 2, z + b), BlocksTC.lootUrn.func_176203_a(md));
/*     */         }
/* 156 */         if ((a != 0) && (b != 0) && (this.field_145850_b.field_73012_v.nextFloat() < 0.9F)) {
/* 157 */           float rr = this.field_145850_b.field_73012_v.nextFloat();
/* 158 */           int md = rr < 0.3F ? 1 : rr < 0.1F ? 2 : 0;
/* 159 */           this.field_145850_b.func_175656_a(new BlockPos(x + a, y + 2, z2 + b), BlocksTC.lootUrn.func_176203_a(md));
/*     */         }
/*     */       }
/*     */     }
/* 163 */     this.field_145850_b.func_175656_a(new BlockPos(x - 2, y + 2, z - 2), BlocksTC.pedestal.func_176203_a(2));
/* 164 */     this.field_145850_b.func_175656_a(new BlockPos(x - 2, y + 2, z + 2), BlocksTC.pedestal.func_176203_a(2));
/* 165 */     this.field_145850_b.func_175656_a(new BlockPos(x + 2, y + 2, z + 2), BlocksTC.pedestal.func_176203_a(2));
/* 166 */     this.field_145850_b.func_175656_a(new BlockPos(x + 2, y + 2, z - 2), BlocksTC.pedestal.func_176203_a(2));
/*     */     
/*     */ 
/* 169 */     for (int a = 0; a < 3; a++) {
/* 170 */       for (int b = 0; b < 3; b++) {
/* 171 */         if (this.ped[a][b] < 0) {
/* 172 */           this.field_145850_b.func_175656_a(new BlockPos(x2 - 1 + b, y + 2, z2 - 1 + a), BlocksTC.stone.func_176203_a(7));
/*     */         } else {
/* 174 */           this.field_145850_b.func_175656_a(new BlockPos(x2 - 1 + b, y + 2, z2 - 1 + a), BlocksTC.stairsAncient.func_176203_a(this.ped[a][b]));
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 180 */     EntityEldritchWarden boss = new EntityEldritchWarden(this.field_145850_b);
/* 181 */     double d0 = this.field_174879_c.func_177958_n() - (x2 + 0.5D);
/* 182 */     double d1 = this.field_174879_c.func_177956_o() - (y + 3 + boss.func_70047_e());
/* 183 */     double d2 = this.field_174879_c.func_177952_p() - (z2 + 0.5D);
/* 184 */     double d3 = MathHelper.func_76133_a(d0 * d0 + d2 * d2);
/* 185 */     float f = (float)(Math.atan2(d2, d0) * 180.0D / 3.141592653589793D) - 90.0F;
/* 186 */     float f1 = (float)-(Math.atan2(d1, d3) * 180.0D / 3.141592653589793D);
/* 187 */     boss.func_70012_b(x2 + 0.5D, y + 3, z2 + 0.5D, f, f1);
/* 188 */     boss.func_180482_a(this.field_145850_b.func_175649_E(this.field_174879_c), (IEntityLivingData)null);
/* 189 */     boss.func_175449_a(new BlockPos(x, y + 2, z), 32);
/* 190 */     this.field_145850_b.func_72838_d(boss);
/*     */   }
/*     */   
/*     */   private void spawnGolemBossRoom(int cx, int cz, int exit)
/*     */   {
/* 195 */     for (int i = 0; i < this.field_145850_b.field_73010_i.size(); i++)
/*     */     {
/* 197 */       EntityPlayer ep = (EntityPlayer)this.field_145850_b.field_73010_i.get(i);
/* 198 */       if (ep.func_174818_b(this.field_174879_c) < 300.0D) {
/* 199 */         ep.func_145747_a(new ChatComponentText(StatCollector.func_74838_a("tc.boss.golem")));
/*     */       }
/*     */     }
/* 202 */     int x = cx * 16 + 16;
/* 203 */     int y = 50;
/* 204 */     int z = cz * 16 + 16;
/* 205 */     int x2 = 0;
/* 206 */     int z2 = 0;
/* 207 */     switch (exit) {
/* 208 */     case 2:  x2 = 8;z2 = 8; break;
/* 209 */     case 3:  x2 = -8;z2 = 8; break;
/* 210 */     case 4:  x2 = 8;z2 = -8; break;
/* 211 */     case 5:  x2 = -8;z2 = -8;
/*     */     }
/*     */     
/* 214 */     GenCommon.genObelisk(this.field_145850_b, new BlockPos(x + x2, y + 4, z + z2));
/* 215 */     GenCommon.genObelisk(this.field_145850_b, new BlockPos(x - x2, y + 4, z + z2));
/* 216 */     GenCommon.genObelisk(this.field_145850_b, new BlockPos(x + x2, y + 4, z - z2));
/* 217 */     this.field_145850_b.func_175656_a(new BlockPos(x + x2, y + 2, z + z2), BlocksTC.eldritch.func_176203_a(1));
/* 218 */     this.field_145850_b.func_175656_a(new BlockPos(x - x2, y + 2, z + z2), BlocksTC.eldritch.func_176203_a(1));
/* 219 */     this.field_145850_b.func_175656_a(new BlockPos(x + x2, y + 2, z - z2), BlocksTC.eldritch.func_176203_a(1));
/*     */     
/* 221 */     for (int a = 0; a < 3; a++) {
/* 222 */       for (int b = 0; b < 3; b++) {
/* 223 */         if (this.ped[a][b] < 0) {
/* 224 */           this.field_145850_b.func_175656_a(new BlockPos(x - 1 + b, y + 2, z - 1 + a), BlocksTC.stone.func_176203_a(7));
/*     */         } else {
/* 226 */           this.field_145850_b.func_175656_a(new BlockPos(x - 1 + b, y + 2, z - 1 + a), BlocksTC.stairsAncient.func_176203_a(this.ped[a][b]));
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 231 */     for (int a = -10; a <= 10; a++) {
/* 232 */       for (int b = -10; b <= 10; b++) {
/* 233 */         if (((a < -2) && (b < -2)) || ((a > 2) && (b > 2)) || ((a < -2) && (b > 2)) || ((a > 2) && (b < -2) && (this.field_145850_b.field_73012_v.nextFloat() < 0.15F) && (this.field_145850_b.func_175623_d(new BlockPos(x + a, y + 2, z + b)))))
/*     */         {
/* 235 */           float rr = this.field_145850_b.field_73012_v.nextFloat();
/* 236 */           int md = rr < 0.2F ? 1 : rr < 0.05F ? 2 : 0;
/* 237 */           this.field_145850_b.func_175656_a(new BlockPos(x + a, y + 2, z + b), this.field_145850_b.field_73012_v.nextFloat() < 0.3F ? BlocksTC.lootCrate.func_176203_a(md) : BlocksTC.lootUrn.func_176203_a(md));
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 242 */     EntityEldritchGolem boss = new EntityEldritchGolem(this.field_145850_b);
/* 243 */     double d0 = this.field_174879_c.func_177958_n() - (x + 0.5D);
/* 244 */     double d1 = this.field_174879_c.func_177956_o() - (y + 3 + boss.func_70047_e());
/* 245 */     double d2 = this.field_174879_c.func_177952_p() - (z + 0.5D);
/* 246 */     double d3 = MathHelper.func_76133_a(d0 * d0 + d2 * d2);
/* 247 */     float f = (float)(Math.atan2(d2, d0) * 180.0D / 3.141592653589793D) - 90.0F;
/* 248 */     float f1 = (float)-(Math.atan2(d1, d3) * 180.0D / 3.141592653589793D);
/* 249 */     boss.func_70012_b(x + 0.5D, y + 3, z + 0.5D, f, f1);
/* 250 */     boss.func_180482_a(this.field_145850_b.func_175649_E(this.field_174879_c), (IEntityLivingData)null);
/* 251 */     this.field_145850_b.func_72838_d(boss);
/*     */   }
/*     */   
/*     */   private void spawnCultistBossRoom(int cx, int cz, int exit)
/*     */   {
/* 256 */     for (int i = 0; i < this.field_145850_b.field_73010_i.size(); i++)
/*     */     {
/* 258 */       EntityPlayer ep = (EntityPlayer)this.field_145850_b.field_73010_i.get(i);
/* 259 */       if (ep.func_174818_b(this.field_174879_c) < 300.0D) {
/* 260 */         ep.func_145747_a(new ChatComponentText(StatCollector.func_74838_a("tc.boss.crimson")));
/*     */       }
/*     */     }
/*     */     
/* 264 */     int x = cx * 16 + 16;
/* 265 */     int y = 50;
/* 266 */     int z = cz * 16 + 16;
/*     */     
/* 268 */     for (int a = -4; a <= 4; a++) {
/* 269 */       for (int b = -4; b <= 4; b++) {
/* 270 */         if (((Math.abs(a) != 2) && (Math.abs(b) != 2)) || ((!this.field_145850_b.field_73012_v.nextBoolean()) && (
/* 271 */           ((Math.abs(a) != 3) && (Math.abs(b) != 3)) || ((this.field_145850_b.field_73012_v.nextFloat() <= 0.33F) && (
/* 272 */           ((Math.abs(a) != 4) && (Math.abs(b) != 4)) || (this.field_145850_b.field_73012_v.nextFloat() <= 0.25F))))))
/* 273 */           this.field_145850_b.func_175656_a(new BlockPos(x + b, y + 1, z + a), BlocksTC.stone.func_176203_a(10));
/*     */       }
/*     */     }
/* 276 */     for (int a = 0; a < 5; a++) {
/* 277 */       for (int b = 0; b < 5; b++)
/* 278 */         if ((a == 0) || (a == 4) || (b == 0) || (b == 4)) {
/* 279 */           this.field_145850_b.func_175656_a(new BlockPos(x - 8 + b * 4, y + 2, z - 8 + a * 4), BlocksTC.stone.func_176203_a(2));
/* 280 */           this.field_145850_b.func_175656_a(new BlockPos(x - 8 + b * 4, y + 3, z - 8 + a * 4), BlocksTC.stone.func_176203_a(11));
/* 281 */           this.field_145850_b.func_175656_a(new BlockPos(x - 8 + b * 4, y + 4, z - 8 + a * 4), BlocksTC.slabStone.func_176203_a(2));
/*     */           
/* 283 */           this.field_145850_b.func_175656_a(new BlockPos(x - 8 + b * 4, y + 10, z - 8 + a * 4), BlocksTC.stone.func_176203_a(2));
/* 284 */           this.field_145850_b.func_175656_a(new BlockPos(x - 8 + b * 4, y + 9, z - 8 + a * 4), BlocksTC.stone.func_176203_a(11));
/* 285 */           this.field_145850_b.func_175656_a(new BlockPos(x - 8 + b * 4, y + 8, z - 8 + a * 4), BlocksTC.slabStone.func_176203_a(10));
/*     */         }
/*     */     }
/* 288 */     EntityCultistPortalGreater boss = new EntityCultistPortalGreater(this.field_145850_b);
/* 289 */     boss.func_70012_b(x + 0.5D, y + 2, z + 0.5D, 0.0F, 0.0F);
/* 290 */     this.field_145850_b.func_72838_d(boss);
/*     */   }
/*     */   
/*     */   private void spawnTaintBossRoom(int cx, int cz, int exit)
/*     */   {
/* 295 */     for (int i = 0; i < this.field_145850_b.field_73010_i.size(); i++)
/*     */     {
/* 297 */       EntityPlayer ep = (EntityPlayer)this.field_145850_b.field_73010_i.get(i);
/* 298 */       if (ep.func_174818_b(this.field_174879_c) < 300.0D) {
/* 299 */         ep.func_145747_a(new ChatComponentText(StatCollector.func_74838_a("tc.boss.taint")));
/*     */       }
/*     */     }
/*     */     
/* 303 */     int x = cx * 16 + 16;
/* 304 */     int y = 50;
/* 305 */     int z = cz * 16 + 16;
/*     */     
/* 307 */     AuraHelper.addAura(func_145831_w(), new BlockPos(x, y, z), thaumcraft.api.aspects.Aspect.FLUX, Config.AURABASE * 4);
/*     */     
/* 309 */     for (int a = -12; a <= 12; a++) {
/* 310 */       for (int b = -12; b <= 12; b++) {
/* 311 */         Utils.setBiomeAt(this.field_145850_b, new BlockPos(x + b, 0, z + a), BiomeHandler.biomeTaint);
/*     */         
/* 313 */         for (int c = 0; c < 9; c++) {
/* 314 */           if ((this.field_145850_b.func_175623_d(new BlockPos(x + b, y + 2 + c, z + a))) && (thaumcraft.common.lib.utils.BlockUtils.isAdjacentToSolidBlock(this.field_145850_b, new BlockPos(x + b, y + 2 + c, z + a))))
/*     */           {
/* 316 */             if (this.field_145850_b.field_73012_v.nextInt(3) != 0)
/* 317 */               this.field_145850_b.func_175656_a(new BlockPos(x + b, y + 2 + c, z + a), BlocksTC.taintFibre.func_176223_P());
/*     */           }
/*     */         }
/* 320 */         if (this.field_145850_b.field_73012_v.nextFloat() < 0.15D) {
/* 321 */           this.field_145850_b.func_175656_a(new BlockPos(x + b, y + 2, z + a), BlocksTC.taintBlock.func_176203_a(1));
/* 322 */           if (this.field_145850_b.field_73012_v.nextFloat() < 0.2D) {
/* 323 */             this.field_145850_b.func_175656_a(new BlockPos(x + b, y + 3, z + a), BlocksTC.taintBlock.func_176203_a(1));
/*     */           }
/*     */         }
/* 326 */         if (((Math.abs(a) != 4) && (Math.abs(b) != 4)) || ((!this.field_145850_b.field_73012_v.nextBoolean()) && (
/* 327 */           ((Math.abs(a) < 5) && (Math.abs(b) < 5)) || ((this.field_145850_b.field_73012_v.nextFloat() <= 0.33F) && (
/* 328 */           ((Math.abs(a) < 7) && (Math.abs(b) < 7)) || (this.field_145850_b.field_73012_v.nextFloat() <= 0.25F)))))) {
/* 329 */           this.field_145850_b.func_175656_a(new BlockPos(x + b, y + 1, z + a), BlocksTC.taintBlock.func_176203_a(0));
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 334 */     EntityTaintacle boss1 = this.field_145850_b.func_175659_aa() != EnumDifficulty.HARD ? new EntityTaintacle(this.field_145850_b) : new EntityTaintacleGiant(this.field_145850_b);
/* 335 */     boss1.func_70012_b(x + 0.5D, y + 3, z + 0.5D, 0.0F, 0.0F);
/* 336 */     EntityUtils.makeChampion(boss1, true);
/* 337 */     this.field_145850_b.func_72838_d(boss1);
/*     */     
/* 339 */     EntityTaintacle boss2 = this.field_145850_b.field_73012_v.nextBoolean() ? new EntityTaintacle(this.field_145850_b) : new EntityTaintacleGiant(this.field_145850_b);
/* 340 */     boss2.func_70012_b(x + 3.5D, y + 3, z + 3.5D, 0.0F, 0.0F);
/* 341 */     EntityUtils.makeChampion(boss2, true);
/* 342 */     this.field_145850_b.func_72838_d(boss2);
/*     */     
/* 344 */     EntityTaintacle boss3 = (boss2 instanceof EntityTaintacleGiant) ? new EntityTaintacle(this.field_145850_b) : new EntityTaintacleGiant(this.field_145850_b);
/* 345 */     boss3.func_70012_b(x - 2.5D, y + 3, z + 3.5D, 0.0F, 0.0F);
/* 346 */     EntityUtils.makeChampion(boss3, true);
/* 347 */     this.field_145850_b.func_72838_d(boss3);
/*     */     
/* 349 */     EntityTaintacle boss4 = this.field_145850_b.field_73012_v.nextBoolean() ? new EntityTaintacle(this.field_145850_b) : new EntityTaintacleGiant(this.field_145850_b);
/* 350 */     boss4.func_70012_b(x + 3.5D, y + 3, z - 2.5D, 0.0F, 0.0F);
/* 351 */     EntityUtils.makeChampion(boss4, true);
/* 352 */     this.field_145850_b.func_72838_d(boss4);
/*     */     
/* 354 */     EntityTaintacle boss5 = (boss4 instanceof EntityTaintacleGiant) ? new EntityTaintacle(this.field_145850_b) : new EntityTaintacleGiant(this.field_145850_b);
/* 355 */     boss5.func_70012_b(x - 2.5D, y + 3, z - 2.5D, 0.0F, 0.0F);
/* 356 */     EntityUtils.makeChampion(boss5, true);
/* 357 */     this.field_145850_b.func_72838_d(boss5);
/*     */   }
/*     */   
/*     */ 
/*     */   public double func_145833_n()
/*     */   {
/* 363 */     return 9216.0D;
/*     */   }
/*     */   
/*     */   @SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*     */   public AxisAlignedBB getRenderBoundingBox()
/*     */   {
/* 369 */     return AxisAlignedBB.func_178781_a(func_174877_v().func_177958_n() - 2.25D, func_174877_v().func_177956_o() - 2.25D, func_174877_v().func_177952_p() - 2.25D, func_174877_v().func_177958_n() + 3.25D, func_174877_v().func_177956_o() + 3.25D, func_174877_v().func_177952_p() + 3.25D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/* 374 */   byte facing = 0;
/*     */   
/*     */   public byte getLockFacing() {
/* 377 */     return this.facing;
/*     */   }
/*     */   
/*     */   public void setLockFacing(byte face) {
/* 381 */     this.facing = face;
/* 382 */     this.field_145850_b.func_175689_h(this.field_174879_c);
/* 383 */     func_70296_d();
/*     */   }
/*     */   
/*     */   public void readCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/* 388 */     this.facing = nbttagcompound.func_74771_c("facing");
/* 389 */     this.count = nbttagcompound.func_74765_d("count");
/*     */   }
/*     */   
/*     */   public void writeCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/* 394 */     nbttagcompound.func_74774_a("facing", this.facing);
/* 395 */     nbttagcompound.func_74777_a("count", (short)this.count);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/tiles/misc/TileEldritchLock.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */