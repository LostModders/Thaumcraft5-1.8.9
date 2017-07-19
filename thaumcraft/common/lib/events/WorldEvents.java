/*    */ package thaumcraft.common.lib.events;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.WeakHashMap;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.WorldProvider;
/*    */ import net.minecraftforge.event.world.BlockEvent.MultiPlaceEvent;
/*    */ import net.minecraftforge.event.world.BlockEvent.PlaceEvent;
/*    */ import net.minecraftforge.event.world.NoteBlockEvent.Play;
/*    */ import net.minecraftforge.event.world.WorldEvent.Load;
/*    */ import net.minecraftforge.event.world.WorldEvent.Save;
/*    */ import net.minecraftforge.event.world.WorldEvent.Unload;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ import thaumcraft.common.lib.aura.AuraHandler;
/*    */ import thaumcraft.common.lib.world.dim.Cell;
/*    */ import thaumcraft.common.lib.world.dim.MazeHandler;
/*    */ import thaumcraft.common.tiles.devices.TileArcaneEar;
/*    */ 
/*    */ public class WorldEvents
/*    */ {
/*    */   @SubscribeEvent
/*    */   public void worldLoad(WorldEvent.Load event)
/*    */   {
/* 26 */     if (!event.world.field_72995_K) {
/* 27 */       if (event.world.field_73011_w.func_177502_q() == 0) { MazeHandler.loadMaze(event.world);
/*    */       }
/* 29 */       AuraHandler.addAuraWorld(event.world.field_73011_w.func_177502_q());
/*    */     }
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void worldSave(WorldEvent.Save event) {
/* 35 */     if ((!event.world.field_72995_K) && 
/* 36 */       (event.world.field_73011_w.func_177502_q() == 0)) { MazeHandler.saveMaze(event.world);
/*    */     }
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void worldUnload(WorldEvent.Unload event)
/*    */   {
/* 43 */     if (event.world.field_72995_K) { return;
/*    */     }
/* 45 */     thaumcraft.common.entities.construct.golem.seals.SealHandler.sealEntities.remove(Integer.valueOf(event.world.field_73011_w.func_177502_q()));
/*    */     
/* 47 */     AuraHandler.removeAuraWorld(event.world.field_73011_w.func_177502_q());
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void placeBlockEvent(BlockEvent.PlaceEvent event)
/*    */   {
/* 53 */     if (isNearActiveBoss(event.world, event.player, event.pos.func_177958_n(), event.pos.func_177956_o(), event.pos.func_177952_p())) event.setCanceled(true);
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void placeBlockEvent(BlockEvent.MultiPlaceEvent event) {
/* 58 */     if (isNearActiveBoss(event.world, event.player, event.pos.func_177958_n(), event.pos.func_177956_o(), event.pos.func_177952_p())) event.setCanceled(true);
/*    */   }
/*    */   
/*    */   private boolean isNearActiveBoss(World world, EntityPlayer player, int x, int y, int z) {
/* 62 */     if ((world.field_73011_w.func_177502_q() == thaumcraft.common.config.Config.dimensionOuterId) && (player != null) && (!player.field_71075_bZ.field_75098_d)) {
/* 63 */       int xx = x >> 4;
/* 64 */       int zz = z >> 4;
/* 65 */       Cell c = MazeHandler.getFromHashMap(new thaumcraft.common.lib.world.dim.CellLoc(xx, zz));
/* 66 */       if ((c != null) && (c.feature >= 2) && (c.feature <= 5)) {
/* 67 */         ArrayList<net.minecraft.entity.Entity> list = thaumcraft.common.lib.utils.EntityUtils.getEntitiesInRange(world, x, y, z, null, thaumcraft.common.entities.monster.boss.EntityThaumcraftBoss.class, 32.0D);
/* 68 */         if ((list != null) && (list.size() > 0)) return true;
/*    */       }
/*    */     }
/* 71 */     return false;
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void noteEvent(NoteBlockEvent.Play event)
/*    */   {
/* 77 */     if (event.world.field_72995_K) { return;
/*    */     }
/* 79 */     if (!TileArcaneEar.noteBlockEvents.containsKey(Integer.valueOf(event.world.field_73011_w.func_177502_q()))) {
/* 80 */       TileArcaneEar.noteBlockEvents.put(Integer.valueOf(event.world.field_73011_w.func_177502_q()), new ArrayList());
/*    */     }
/*    */     
/* 83 */     ArrayList<Integer[]> list = (ArrayList)TileArcaneEar.noteBlockEvents.get(Integer.valueOf(event.world.field_73011_w.func_177502_q()));
/*    */     
/* 85 */     list.add(new Integer[] { Integer.valueOf(event.pos.func_177958_n()), Integer.valueOf(event.pos.func_177956_o()), Integer.valueOf(event.pos.func_177952_p()), Integer.valueOf(event.instrument.ordinal()), Integer.valueOf(event.getVanillaNoteId()) });
/*    */     
/* 87 */     TileArcaneEar.noteBlockEvents.put(Integer.valueOf(event.world.field_73011_w.func_177502_q()), list);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/events/WorldEvents.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */