/*     */ package thaumcraft.common.lib.events;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
/*     */ import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.IAspectSource;
/*     */ import thaumcraft.api.internal.WorldCoordinates;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.fx.PacketFXEssentiaSource;
/*     */ import thaumcraft.common.tiles.devices.TileMirrorEssentia;
/*     */ 
/*     */ public class EssentiaHandler
/*     */ {
/*     */   static final int DELAY = 10000;
/*  23 */   private static HashMap<WorldCoordinates, ArrayList<WorldCoordinates>> sources = new HashMap();
/*  24 */   private static HashMap<WorldCoordinates, Long> sourcesDelay = new HashMap();
/*     */   
/*     */   public static boolean drainEssentia(TileEntity tile, Aspect aspect, EnumFacing direction, int range, int ext) {
/*  27 */     return drainEssentia(tile, aspect, direction, range, false, ext);
/*     */   }
/*     */   
/*     */   public static boolean drainEssentia(TileEntity tile, Aspect aspect, EnumFacing direction, int range, boolean ignoreMirror, int ext) {
/*  31 */     WorldCoordinates tileLoc = new WorldCoordinates(tile.func_174877_v(), tile.func_145831_w().field_73011_w.func_177502_q());
/*  32 */     if (!sources.containsKey(tileLoc)) {
/*  33 */       getSources(tile.func_145831_w(), tileLoc, direction, range);
/*  34 */       if (sources.containsKey(tileLoc))
/*  35 */         return drainEssentia(tile, aspect, direction, range, ignoreMirror, ext);
/*  36 */       return false;
/*     */     }
/*  38 */     ArrayList<WorldCoordinates> es = (ArrayList)sources.get(tileLoc);
/*  39 */     for (WorldCoordinates source : es) {
/*  40 */       TileEntity sourceTile = tile.func_145831_w().func_175625_s(source.pos);
/*  41 */       if ((sourceTile == null) || (!(sourceTile instanceof IAspectSource))) break;
/*  42 */       IAspectSource as = (IAspectSource)sourceTile;
/*  43 */       if ((!as.isBlocked()) && ((!ignoreMirror) || (!(sourceTile instanceof TileMirrorEssentia))))
/*     */       {
/*  45 */         if (as.takeFromContainer(aspect, 1))
/*     */         {
/*  47 */           PacketHandler.INSTANCE.sendToAllAround(new PacketFXEssentiaSource(tile.func_174877_v(), (byte)(tile.func_174877_v().func_177958_n() - source.pos.func_177958_n()), (byte)(tile.func_174877_v().func_177956_o() - source.pos.func_177956_o()), (byte)(tile.func_174877_v().func_177952_p() - source.pos.func_177952_p()), aspect.getColor(), ext), new NetworkRegistry.TargetPoint(tile.func_145831_w().field_73011_w.func_177502_q(), tile.func_174877_v().func_177958_n(), tile.func_174877_v().func_177956_o(), tile.func_174877_v().func_177952_p(), 32.0D));
/*     */           
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  53 */           return true;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*  59 */     sources.remove(tileLoc);
/*  60 */     sourcesDelay.put(tileLoc, Long.valueOf(System.currentTimeMillis() + 10000L));
/*     */     
/*  62 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean drainEssentiaWithConfirmation(TileEntity tile, Aspect aspect, EnumFacing direction, int range, boolean ignoreMirror, int ext) {
/*  66 */     WorldCoordinates tileLoc = new WorldCoordinates(tile.func_174877_v(), tile.func_145831_w().field_73011_w.func_177502_q());
/*  67 */     if (!sources.containsKey(tileLoc)) {
/*  68 */       getSources(tile.func_145831_w(), tileLoc, direction, range);
/*  69 */       if (sources.containsKey(tileLoc))
/*  70 */         return drainEssentiaWithConfirmation(tile, aspect, direction, range, ignoreMirror, ext);
/*  71 */       return false;
/*     */     }
/*  73 */     ArrayList<WorldCoordinates> es = (ArrayList)sources.get(tileLoc);
/*  74 */     for (WorldCoordinates source : es) {
/*  75 */       TileEntity sourceTile = tile.func_145831_w().func_175625_s(source.pos);
/*  76 */       if ((sourceTile == null) || (!(sourceTile instanceof IAspectSource)))
/*     */         break;
/*  78 */       IAspectSource as = (IAspectSource)sourceTile;
/*  79 */       if ((!as.isBlocked()) && ((!ignoreMirror) || (!(sourceTile instanceof TileMirrorEssentia))))
/*     */       {
/*  81 */         if (as.doesContainerContainAmount(aspect, 1)) {
/*  82 */           las = sourceTile;
/*  83 */           lasp = aspect;
/*  84 */           lat = tile;
/*  85 */           lext = ext;
/*  86 */           return true;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*  92 */     sources.remove(tileLoc);
/*  93 */     sourcesDelay.put(tileLoc, Long.valueOf(System.currentTimeMillis() + 10000L));
/*     */     
/*  95 */     return false; }
/*     */   
/*  97 */   private static TileEntity lat = null;
/*  98 */   private static TileEntity las = null;
/*  99 */   private static Aspect lasp = null;
/* 100 */   private static int lext = 0;
/*     */   
/* 102 */   public static void confirmDrain() { if ((las != null) && (lasp != null) && (lat != null)) {
/* 103 */       IAspectSource as = (IAspectSource)las;
/* 104 */       if (as.takeFromContainer(lasp, 1)) {
/* 105 */         PacketHandler.INSTANCE.sendToAllAround(new PacketFXEssentiaSource(lat.func_174877_v(), (byte)(lat.func_174877_v().func_177958_n() - las.func_174877_v().func_177958_n()), (byte)(lat.func_174877_v().func_177956_o() - las.func_174877_v().func_177956_o()), (byte)(lat.func_174877_v().func_177952_p() - las.func_174877_v().func_177952_p()), lasp.getColor(), lext), new NetworkRegistry.TargetPoint(lat.func_145831_w().field_73011_w.func_177502_q(), lat.func_174877_v().func_177958_n(), lat.func_174877_v().func_177956_o(), lat.func_174877_v().func_177952_p(), 32.0D));
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 111 */     las = null;
/* 112 */     lasp = null;
/* 113 */     lat = null;
/*     */   }
/*     */   
/*     */   public static boolean addEssentia(TileEntity tile, Aspect aspect, EnumFacing direction, int range, boolean ignoreMirror, int ext) {
/* 117 */     WorldCoordinates tileLoc = new WorldCoordinates(tile.func_174877_v(), tile.func_145831_w().field_73011_w.func_177502_q());
/* 118 */     if (!sources.containsKey(tileLoc)) {
/* 119 */       getSources(tile.func_145831_w(), tileLoc, direction, range);
/* 120 */       if (sources.containsKey(tileLoc))
/* 121 */         return addEssentia(tile, aspect, direction, range, ignoreMirror, ext);
/* 122 */       return false;
/*     */     }
/* 124 */     ArrayList<WorldCoordinates> es = (ArrayList)sources.get(tileLoc);
/* 125 */     ArrayList<WorldCoordinates> empties = new ArrayList();
/*     */     
/*     */ 
/* 128 */     for (WorldCoordinates source : es) {
/* 129 */       TileEntity sourceTile = tile.func_145831_w().func_175625_s(source.pos);
/* 130 */       if ((sourceTile == null) || (!(sourceTile instanceof IAspectSource)))
/*     */         break;
/* 132 */       IAspectSource as = (IAspectSource)sourceTile;
/* 133 */       if ((!as.isBlocked()) && ((!ignoreMirror) || (!(sourceTile instanceof TileMirrorEssentia))))
/*     */       {
/* 135 */         if ((as.doesContainerAccept(aspect)) && ((as.getAspects() == null) || (as.getAspects().visSize() == 0))) {
/* 136 */           empties.add(source);
/*     */         }
/* 138 */         else if ((as.doesContainerAccept(aspect)) && (as.addToContainer(aspect, 1) <= 0)) {
/* 139 */           PacketHandler.INSTANCE.sendToAllAround(new PacketFXEssentiaSource(source.pos, (byte)(source.pos.func_177958_n() - tile.func_174877_v().func_177958_n()), (byte)(source.pos.func_177956_o() - tile.func_174877_v().func_177956_o()), (byte)(source.pos.func_177952_p() - tile.func_174877_v().func_177952_p()), aspect.getColor(), ext), new NetworkRegistry.TargetPoint(tile.func_145831_w().field_73011_w.func_177502_q(), tile.func_174877_v().func_177958_n(), tile.func_174877_v().func_177956_o(), tile.func_174877_v().func_177952_p(), 32.0D));
/*     */           
/*     */ 
/*     */ 
/*     */ 
/* 144 */           return true;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 152 */     for (WorldCoordinates source : empties) {
/* 153 */       TileEntity sourceTile = tile.func_145831_w().func_175625_s(source.pos);
/* 154 */       if ((sourceTile == null) || (!(sourceTile instanceof IAspectSource))) break;
/* 155 */       IAspectSource as = (IAspectSource)sourceTile;
/* 156 */       if ((as.doesContainerAccept(aspect)) && (as.addToContainer(aspect, 1) <= 0)) {
/* 157 */         PacketHandler.INSTANCE.sendToAllAround(new PacketFXEssentiaSource(source.pos, (byte)(source.pos.func_177958_n() - tile.func_174877_v().func_177958_n()), (byte)(source.pos.func_177956_o() - tile.func_174877_v().func_177956_o()), (byte)(source.pos.func_177952_p() - tile.func_174877_v().func_177952_p()), aspect.getColor(), ext), new NetworkRegistry.TargetPoint(tile.func_145831_w().field_73011_w.func_177502_q(), tile.func_174877_v().func_177958_n(), tile.func_174877_v().func_177956_o(), tile.func_174877_v().func_177952_p(), 32.0D));
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 162 */         return true;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 169 */     sources.remove(tileLoc);
/* 170 */     sourcesDelay.put(tileLoc, Long.valueOf(System.currentTimeMillis() + 10000L));
/*     */     
/* 172 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean findEssentia(TileEntity tile, Aspect aspect, EnumFacing direction, int range, boolean ignoreMirror) {
/* 176 */     WorldCoordinates tileLoc = new WorldCoordinates(tile.func_174877_v(), tile.func_145831_w().field_73011_w.func_177502_q());
/* 177 */     if (!sources.containsKey(tileLoc)) {
/* 178 */       getSources(tile.func_145831_w(), tileLoc, direction, range);
/* 179 */       if (sources.containsKey(tileLoc))
/* 180 */         return findEssentia(tile, aspect, direction, range, ignoreMirror);
/* 181 */       return false;
/*     */     }
/* 183 */     ArrayList<WorldCoordinates> es = (ArrayList)sources.get(tileLoc);
/* 184 */     for (WorldCoordinates source : es) {
/* 185 */       TileEntity sourceTile = tile.func_145831_w().func_175625_s(source.pos);
/* 186 */       if ((sourceTile == null) || (!(sourceTile instanceof IAspectSource)))
/*     */         break;
/* 188 */       IAspectSource as = (IAspectSource)sourceTile;
/* 189 */       if ((!as.isBlocked()) && ((!ignoreMirror) || (!(sourceTile instanceof TileMirrorEssentia))))
/*     */       {
/* 191 */         if (as.doesContainerContainAmount(aspect, 1)) {
/* 192 */           return true;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 201 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean canAcceptEssentia(TileEntity tile, Aspect aspect, EnumFacing direction, int range, boolean ignoreMirror) {
/* 205 */     WorldCoordinates tileLoc = new WorldCoordinates(tile.func_174877_v(), tile.func_145831_w().field_73011_w.func_177502_q());
/* 206 */     if (!sources.containsKey(tileLoc)) {
/* 207 */       getSources(tile.func_145831_w(), tileLoc, direction, range);
/* 208 */       if (sources.containsKey(tileLoc))
/* 209 */         return findEssentia(tile, aspect, direction, range, ignoreMirror);
/* 210 */       return false;
/*     */     }
/* 212 */     ArrayList<WorldCoordinates> es = (ArrayList)sources.get(tileLoc);
/* 213 */     for (WorldCoordinates source : es) {
/* 214 */       TileEntity sourceTile = tile.func_145831_w().func_175625_s(source.pos);
/* 215 */       if ((sourceTile == null) || (!(sourceTile instanceof IAspectSource))) break;
/* 216 */       if ((!ignoreMirror) || (!(sourceTile instanceof TileMirrorEssentia))) {
/* 217 */         IAspectSource as = (IAspectSource)sourceTile;
/* 218 */         if ((!as.isBlocked()) && (as.doesContainerAccept(aspect))) {
/* 219 */           return true;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 228 */     return false;
/*     */   }
/*     */   
/*     */   private static void getSources(World world, WorldCoordinates tileLoc, EnumFacing direction, int range)
/*     */   {
/* 233 */     if (sourcesDelay.containsKey(tileLoc)) {
/* 234 */       long d = ((Long)sourcesDelay.get(tileLoc)).longValue();
/* 235 */       if (d <= System.currentTimeMillis()) {
/* 236 */         sourcesDelay.remove(tileLoc);
/*     */       } else {
/* 238 */         return;
/*     */       }
/*     */     }
/*     */     
/* 242 */     TileEntity sourceTile = world.func_175625_s(tileLoc.pos);
/*     */     
/* 244 */     ArrayList<WorldCoordinates> sourceList = new ArrayList();
/* 245 */     int start = 0;
/* 246 */     if (direction == null) {
/* 247 */       start = -range;
/* 248 */       direction = EnumFacing.UP;
/*     */     }
/*     */     
/*     */ 
/* 252 */     int xx = 0;
/* 253 */     int yy = 0;
/* 254 */     int zz = 0;
/* 255 */     for (int aa = -range; aa <= range; aa++) {
/* 256 */       for (int bb = -range; bb <= range; bb++) {
/* 257 */         for (int cc = start; cc < range; cc++)
/*     */         {
/* 259 */           if ((aa != 0) || (bb != 0) || (cc != 0))
/*     */           {
/* 261 */             xx = tileLoc.pos.func_177958_n();
/* 262 */             yy = tileLoc.pos.func_177956_o();
/* 263 */             zz = tileLoc.pos.func_177952_p();
/*     */             
/* 265 */             if (direction.func_96559_d() != 0) {
/* 266 */               xx += aa;
/* 267 */               yy += cc * direction.func_96559_d();
/* 268 */               zz += bb;
/*     */             }
/* 270 */             else if (direction.func_82601_c() == 0) {
/* 271 */               xx += aa;
/* 272 */               yy += bb;
/* 273 */               zz += cc * direction.func_82599_e();
/*     */             } else {
/* 275 */               xx += cc * direction.func_82601_c();
/* 276 */               yy += aa;
/* 277 */               zz += bb;
/*     */             }
/*     */             
/* 280 */             TileEntity te = world.func_175625_s(new BlockPos(xx, yy, zz));
/* 281 */             if ((te != null) && ((te instanceof IAspectSource)))
/*     */             {
/* 283 */               if ((!(sourceTile instanceof TileMirrorEssentia)) || (!(te instanceof TileMirrorEssentia)) || 
/* 284 */                 (sourceTile.func_174877_v().func_177958_n() != ((TileMirrorEssentia)te).linkX) || (sourceTile.func_174877_v().func_177956_o() != ((TileMirrorEssentia)te).linkY) || (sourceTile.func_174877_v().func_177952_p() != ((TileMirrorEssentia)te).linkZ) || (sourceTile.func_145831_w().field_73011_w.func_177502_q() != ((TileMirrorEssentia)te).linkDim))
/*     */               {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 291 */                 sourceList.add(new WorldCoordinates(new BlockPos(xx, yy, zz), world.field_73011_w.func_177502_q())); } }
/*     */           } }
/*     */       }
/*     */     }
/* 295 */     if (sourceList.size() > 0) {
/* 296 */       ArrayList<WorldCoordinates> sourceList2 = new ArrayList();
/* 297 */       for (WorldCoordinates wc : sourceList) {
/* 298 */         double dist = wc.getDistanceSquaredToWorldCoordinates(tileLoc);
/* 299 */         if (!sourceList2.isEmpty()) {
/* 300 */           for (int a = 0;; a++) { if (a >= sourceList2.size()) break label560;
/* 301 */             double d2 = ((WorldCoordinates)sourceList2.get(a)).getDistanceSquaredToWorldCoordinates(tileLoc);
/* 302 */             if (dist < d2) {
/* 303 */               sourceList2.add(a, wc);
/* 304 */               break;
/*     */             }
/*     */           }
/*     */         }
/* 308 */         sourceList2.add(wc); }
/*     */       label560:
/* 310 */       sources.put(tileLoc, sourceList2);
/*     */     }
/*     */     else {
/* 313 */       sourcesDelay.put(tileLoc, Long.valueOf(System.currentTimeMillis() + 10000L));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/* 318 */   public static void refreshSources(TileEntity tile) { sources.remove(new WorldCoordinates(tile.func_174877_v(), tile.func_145831_w().field_73011_w.func_177502_q())); }
/*     */   
/*     */   public static class EssentiaSourceFX {
/*     */     public BlockPos start;
/*     */     public BlockPos end;
/*     */     public int color;
/*     */     public int ext;
/*     */     
/*     */     public EssentiaSourceFX(BlockPos start, BlockPos end, int color, int ext) {
/* 327 */       this.start = start;
/* 328 */       this.end = end;
/* 329 */       this.color = color;
/* 330 */       this.ext = ext;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 338 */   public static ConcurrentHashMap<String, EssentiaSourceFX> sourceFX = new ConcurrentHashMap();
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/events/EssentiaHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */