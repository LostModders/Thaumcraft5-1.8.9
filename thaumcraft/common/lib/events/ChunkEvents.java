/*     */ package thaumcraft.common.lib.events;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.world.ChunkCoordIntPair;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraft.world.chunk.Chunk;
/*     */ import net.minecraftforge.event.world.ChunkDataEvent.Load;
/*     */ import net.minecraftforge.event.world.ChunkDataEvent.Save;
/*     */ import net.minecraftforge.event.world.ChunkWatchEvent.Watch;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.golems.seals.ISealEntity;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.entities.construct.golem.seals.SealEntity;
/*     */ import thaumcraft.common.entities.construct.golem.seals.SealHandler;
/*     */ import thaumcraft.common.lib.aura.AuraChunk;
/*     */ import thaumcraft.common.lib.aura.AuraHandler;
/*     */ 
/*     */ public class ChunkEvents
/*     */ {
/*     */   @SubscribeEvent
/*     */   public void chunkSave(ChunkDataEvent.Save event)
/*     */   {
/*  28 */     int dim = event.world.field_73011_w.func_177502_q();
/*  29 */     ChunkCoordIntPair loc = event.getChunk().func_76632_l();
/*     */     
/*  31 */     NBTTagCompound nbt = new NBTTagCompound();
/*  32 */     event.getData().func_74782_a("Thaumcraft", nbt);
/*  33 */     nbt.func_74757_a(Config.regenKey, true);
/*     */     
/*     */ 
/*  36 */     AuraChunk ac = AuraHandler.getAuraChunk(dim, loc.field_77276_a, loc.field_77275_b);
/*  37 */     if (ac != null) {
/*  38 */       nbt.func_74777_a("base", ac.getBase());
/*  39 */       ac.getCurrentAspects().writeToNBT(nbt, "current");
/*  40 */       if (!event.getChunk().func_177410_o()) {
/*  41 */         AuraHandler.removeAuraChunk(dim, loc.field_77276_a, loc.field_77275_b);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*  47 */     NBTTagList tagList = new NBTTagList();
/*  48 */     for (ISealEntity seal : SealHandler.getSealsInChunk(event.world, loc)) {
/*  49 */       NBTTagCompound sealnbt = seal.writeNBT();
/*  50 */       tagList.func_74742_a(sealnbt);
/*  51 */       if (!event.getChunk().func_177410_o()) {
/*  52 */         SealHandler.removeSealEntity(event.world, seal.getSealPos(), true);
/*     */       }
/*     */     }
/*     */     
/*  56 */     nbt.func_74782_a("seals", tagList);
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void chunkLoad(ChunkDataEvent.Load event)
/*     */   {
/*  62 */     int dim = event.world.field_73011_w.func_177502_q();
/*  63 */     ChunkCoordIntPair loc = event.getChunk().func_76632_l();
/*     */     
/*     */ 
/*  66 */     if (event.getData().func_74775_l("Thaumcraft").func_74764_b("base")) {
/*  67 */       NBTTagCompound nbt = event.getData().func_74775_l("Thaumcraft");
/*  68 */       short base = nbt.func_74765_d("base");
/*  69 */       AspectList current = new AspectList();
/*  70 */       current.readFromNBT(nbt, "current");
/*  71 */       AuraHandler.addAuraChunk(dim, event.getChunk(), base, current);
/*     */     } else {
/*  73 */       AuraHandler.generateAura(event.getChunk(), event.world.field_73012_v);
/*     */     }
/*     */     
/*     */ 
/*  77 */     if (event.getData().func_74775_l("Thaumcraft").func_74764_b("seals")) {
/*  78 */       NBTTagCompound nbt = event.getData().func_74775_l("Thaumcraft");
/*  79 */       NBTTagList tagList = nbt.func_150295_c("seals", 10);
/*  80 */       for (int a = 0; a < tagList.func_74745_c(); a++) {
/*  81 */         NBTTagCompound tasknbt = tagList.func_150305_b(a);
/*  82 */         SealEntity seal = new SealEntity();
/*  83 */         seal.readNBT(tasknbt);
/*  84 */         SealHandler.addSealEntity(event.world, seal);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*  90 */     if ((!event.getData().func_74775_l("Thaumcraft").func_74764_b(Config.regenKey)) && ((Config.regenAmber) || (Config.regenAura) || (Config.regenCinnibar) || (Config.regenCrystals) || (Config.regenStructure) || (Config.regenTrees)))
/*     */     {
/*  92 */       thaumcraft.common.Thaumcraft.log.warn("World gen was never run for chunk at " + event.getChunk().func_76632_l() + ". Adding to queue for regeneration.");
/*  93 */       ArrayList<thaumcraft.common.lib.world.ChunkLoc> chunks = (ArrayList)ServerTickEventsFML.chunksToGenerate.get(Integer.valueOf(dim));
/*  94 */       if (chunks == null) {
/*  95 */         ServerTickEventsFML.chunksToGenerate.put(Integer.valueOf(dim), new ArrayList());
/*  96 */         chunks = (ArrayList)ServerTickEventsFML.chunksToGenerate.get(Integer.valueOf(dim));
/*     */       }
/*  98 */       if (chunks != null) {
/*  99 */         chunks.add(new thaumcraft.common.lib.world.ChunkLoc(loc.field_77276_a, loc.field_77275_b));
/* 100 */         ServerTickEventsFML.chunksToGenerate.put(Integer.valueOf(dim), chunks);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @SubscribeEvent
/*     */   public void chunkWatch(ChunkWatchEvent.Watch event)
/*     */   {
/* 112 */     for (ISealEntity seal : SealHandler.getSealsInChunk(event.player.field_70170_p, event.chunk)) {
/* 113 */       thaumcraft.common.lib.network.PacketHandler.INSTANCE.sendTo(new thaumcraft.common.lib.network.misc.PacketSealToClient(seal), event.player);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/events/ChunkEvents.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */