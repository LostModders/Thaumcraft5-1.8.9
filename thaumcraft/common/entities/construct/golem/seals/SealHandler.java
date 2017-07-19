/*     */ package thaumcraft.common.entities.construct.golem.seals;
/*     */ 
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.CopyOnWriteArrayList;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.world.ChunkCoordIntPair;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import thaumcraft.api.golems.seals.ISeal;
/*     */ import thaumcraft.api.golems.seals.ISealEntity;
/*     */ import thaumcraft.api.golems.seals.SealPos;
/*     */ import thaumcraft.api.golems.tasks.Task;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.entities.construct.golem.tasks.TaskHandler;
/*     */ import thaumcraft.common.lib.aura.AuraHandler;
/*     */ import thaumcraft.common.lib.utils.PosXY;
/*     */ 
/*     */ public class SealHandler
/*     */ {
/*  28 */   public static LinkedHashMap<String, ISeal> types = new LinkedHashMap();
/*  29 */   private static int lastID = 0;
/*     */   
/*     */   public static void registerSeal(ISeal seal) {
/*  32 */     if (types.containsKey(seal.getKey())) {
/*  33 */       Thaumcraft.log.error("Attempting to register Seal [" + seal.getKey() + "] twice. Ignoring.");
/*     */     } else {
/*  35 */       types.put(seal.getKey(), seal);
/*     */     }
/*     */   }
/*     */   
/*     */   public static String[] getRegisteredSeals() {
/*  40 */     return (String[])types.keySet().toArray(new String[0]);
/*     */   }
/*     */   
/*     */   public static ISeal getSeal(String key) {
/*  44 */     return (ISeal)types.get(key);
/*     */   }
/*     */   
/*     */ 
/*  48 */   public static ConcurrentHashMap<Integer, ConcurrentHashMap<SealPos, SealEntity>> sealEntities = new ConcurrentHashMap();
/*     */   
/*     */   public static CopyOnWriteArrayList<SealEntity> getSealsInChunk(World world, ChunkCoordIntPair chunk) {
/*  51 */     CopyOnWriteArrayList<SealEntity> out = new CopyOnWriteArrayList();
/*  52 */     ConcurrentHashMap<SealPos, SealEntity> list = (ConcurrentHashMap)sealEntities.get(Integer.valueOf(world.field_73011_w.func_177502_q()));
/*  53 */     if ((list != null) && (list.size() > 0)) {
/*  54 */       for (SealEntity se : list.values())
/*  55 */         if ((se.getSeal() != null) && (se.getSealPos() != null)) {
/*  56 */           ChunkCoordIntPair cc = new ChunkCoordIntPair(se.sealPos.pos.func_177958_n() >> 4, se.sealPos.pos.func_177952_p() >> 4);
/*  57 */           if (cc.equals(chunk))
/*  58 */             out.add(se);
/*     */         }
/*     */     }
/*  61 */     return out;
/*     */   }
/*     */   
/*     */   public static void removeSealEntity(World world, SealPos pos, boolean quiet) {
/*  65 */     if (!sealEntities.containsKey(Integer.valueOf(world.field_73011_w.func_177502_q()))) {
/*  66 */       sealEntities.put(Integer.valueOf(world.field_73011_w.func_177502_q()), new ConcurrentHashMap());
/*     */     }
/*  68 */     ConcurrentHashMap<SealPos, SealEntity> se = (ConcurrentHashMap)sealEntities.get(Integer.valueOf(world.field_73011_w.func_177502_q()));
/*  69 */     if (se != null)
/*     */     {
/*  71 */       SealEntity seal = (SealEntity)se.remove(pos);
/*     */       try
/*     */       {
/*  74 */         if ((!world.field_72995_K) && (seal != null) && (seal.seal != null))
/*  75 */           seal.seal.onRemoval(world, pos.pos, pos.face);
/*  76 */         if ((!quiet) && (seal != null) && (!world.field_72995_K)) {
/*  77 */           String[] rs = getRegisteredSeals();
/*  78 */           int indx = 1;
/*  79 */           for (String s : rs) {
/*  80 */             if (s.equals(seal.getSeal().getKey())) {
/*  81 */               world.func_72838_d(new EntityItem(world, pos.pos.func_177958_n() + 0.5D + pos.face.func_82601_c() / 1.7F, pos.pos.func_177956_o() + 0.5D + pos.face.func_96559_d() / 1.7F, pos.pos.func_177952_p() + 0.5D + pos.face.func_82599_e() / 1.7F, new net.minecraft.item.ItemStack(ItemsTC.seals, 1, indx)));
/*     */               
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  90 */               break;
/*     */             }
/*  92 */             indx++;
/*     */           }
/*     */         }
/*     */       } catch (Exception e) {
/*  96 */         Thaumcraft.log.warn("Removing invalid seal at " + pos.pos);
/*     */       }
/*     */       
/*  99 */       ConcurrentHashMap<Integer, Task> ts = TaskHandler.getTasks(world.field_73011_w.func_177502_q());
/* 100 */       for (Task task : ts.values()) {
/* 101 */         if ((task.getSealPos() != null) && (task.getSealPos().equals(pos))) {
/* 102 */           task.setSuspended(true);
/*     */         }
/*     */       }
/*     */       
/* 106 */       if (!world.field_72995_K) thaumcraft.common.lib.network.PacketHandler.INSTANCE.sendToDimension(new thaumcraft.common.lib.network.misc.PacketSealToClient(new SealEntity(world, pos, null)), world.field_73011_w.func_177502_q());
/* 107 */       if (!quiet) markChunkAsDirty(world.field_73011_w.func_177502_q(), pos.pos);
/*     */     }
/*     */   }
/*     */   
/*     */   public static ISealEntity getSealEntity(int dim, SealPos pos)
/*     */   {
/* 113 */     if (!sealEntities.containsKey(Integer.valueOf(dim)))
/* 114 */       sealEntities.put(Integer.valueOf(dim), new ConcurrentHashMap());
/* 115 */     if (pos == null) return null;
/* 116 */     ConcurrentHashMap<SealPos, SealEntity> se = (ConcurrentHashMap)sealEntities.get(Integer.valueOf(dim));
/* 117 */     if (se != null) {
/* 118 */       return (ISealEntity)se.get(pos);
/*     */     }
/*     */     
/* 121 */     return null;
/*     */   }
/*     */   
/*     */   public static boolean addSealEntity(World world, BlockPos pos, EnumFacing face, ISeal seal, EntityPlayer player) {
/* 125 */     if (!sealEntities.containsKey(Integer.valueOf(world.field_73011_w.func_177502_q()))) {
/* 126 */       sealEntities.put(Integer.valueOf(world.field_73011_w.func_177502_q()), new ConcurrentHashMap());
/*     */     }
/* 128 */     ConcurrentHashMap<SealPos, SealEntity> se = (ConcurrentHashMap)sealEntities.get(Integer.valueOf(world.field_73011_w.func_177502_q()));
/* 129 */     SealPos sp = new SealPos(pos, face);
/*     */     
/* 131 */     if (se.containsKey(sp)) {
/* 132 */       return false;
/*     */     }
/*     */     
/* 135 */     SealEntity sealent = new SealEntity(world, sp, seal);
/* 136 */     sealent.setOwner(player.func_110124_au().toString());
/* 137 */     se.put(sp, sealent);
/*     */     
/* 139 */     if (!world.field_72995_K) {
/* 140 */       sealent.syncToClient(world);
/* 141 */       markChunkAsDirty(world.field_73011_w.func_177502_q(), pos);
/*     */     }
/*     */     
/* 144 */     return true;
/*     */   }
/*     */   
/*     */   public static boolean addSealEntity(World world, SealEntity seal) {
/* 148 */     if ((world == null) || (sealEntities == null)) return false;
/* 149 */     if (!sealEntities.containsKey(Integer.valueOf(world.field_73011_w.func_177502_q()))) {
/* 150 */       sealEntities.put(Integer.valueOf(world.field_73011_w.func_177502_q()), new ConcurrentHashMap());
/*     */     }
/* 152 */     ConcurrentHashMap<SealPos, SealEntity> se = (ConcurrentHashMap)sealEntities.get(Integer.valueOf(world.field_73011_w.func_177502_q()));
/* 153 */     if (se.containsKey(seal.getSealPos())) {
/* 154 */       return false;
/*     */     }
/*     */     
/* 157 */     se.put(seal.getSealPos(), seal);
/*     */     
/* 159 */     if (!world.field_72995_K) {
/* 160 */       seal.syncToClient(world);
/* 161 */       markChunkAsDirty(world.field_73011_w.func_177502_q(), seal.getSealPos().pos);
/*     */     }
/* 163 */     return true;
/*     */   }
/*     */   
/* 166 */   static int count = 0;
/*     */   
/* 168 */   public static void tickSealEntities(World world) { if (!sealEntities.containsKey(Integer.valueOf(world.field_73011_w.func_177502_q()))) {
/* 169 */       sealEntities.put(Integer.valueOf(world.field_73011_w.func_177502_q()), new ConcurrentHashMap());
/*     */     }
/* 171 */     ConcurrentHashMap<SealPos, SealEntity> se = (ConcurrentHashMap)sealEntities.get(Integer.valueOf(world.field_73011_w.func_177502_q()));
/* 172 */     count += 1;
/* 173 */     for (SealEntity sealEntity : se.values()) {
/* 174 */       if (world.func_175667_e(sealEntity.sealPos.pos)) {
/*     */         try {
/* 176 */           boolean tick = true;
/* 177 */           if ((count % 20 == 0) && 
/* 178 */             (!sealEntity.seal.canPlaceAt(world, sealEntity.sealPos.pos, sealEntity.sealPos.face))) {
/* 179 */             removeSealEntity(world, sealEntity.sealPos, false);
/* 180 */             tick = false;
/*     */           }
/*     */           
/* 183 */           if (tick) sealEntity.tickSealEntity(world);
/*     */         } catch (Exception e) {
/* 185 */           removeSealEntity(world, sealEntity.sealPos, false);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static void markChunkAsDirty(int dim, BlockPos bp) {
/* 192 */     PosXY pos = new PosXY(bp.func_177958_n() >> 4, bp.func_177952_p() >> 4);
/* 193 */     if (!AuraHandler.dirtyChunks.containsKey(Integer.valueOf(dim)))
/* 194 */       AuraHandler.dirtyChunks.put(Integer.valueOf(dim), new CopyOnWriteArrayList());
/* 195 */     CopyOnWriteArrayList<PosXY> dc = (CopyOnWriteArrayList)AuraHandler.dirtyChunks.get(Integer.valueOf(dim));
/* 196 */     if (!dc.contains(pos)) {
/* 197 */       dc.add(pos);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/construct/golem/seals/SealHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */