/*     */ package thaumcraft.common.lib.events;
/*     */ 
/*     */ import java.text.DecimalFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.CopyOnWriteArrayList;
/*     */ import java.util.concurrent.LinkedBlockingQueue;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
/*     */ import net.minecraftforge.fml.common.FMLCommonHandler;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
/*     */ import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;
/*     */ import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import thaumcraft.api.wands.IWand;
/*     */ import thaumcraft.api.wands.ItemFocusBasic;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.entities.construct.golem.seals.SealHandler;
/*     */ import thaumcraft.common.entities.construct.golem.tasks.TaskHandler;
/*     */ import thaumcraft.common.lib.aura.AuraHandler;
/*     */ import thaumcraft.common.lib.aura.AuraThread;
/*     */ import thaumcraft.common.lib.network.fx.PacketFXBlockBamf;
/*     */ import thaumcraft.common.lib.utils.BlockUtils;
/*     */ import thaumcraft.common.lib.utils.PosXY;
/*     */ import thaumcraft.common.lib.world.ChunkLoc;
/*     */ import thaumcraft.common.lib.world.ThaumcraftWorldGenerator;
/*     */ 
/*     */ public class ServerTickEventsFML
/*     */ {
/*     */   long lastcheck;
/*     */   HashMap<Integer, Integer> serverTicks;
/*     */   DecimalFormat myFormatter;
/*     */   
/*     */   public ServerTickEventsFML()
/*     */   {
/*  52 */     this.lastcheck = 0L;
/*  53 */     this.serverTicks = new HashMap();
/*     */     
/*  55 */     this.myFormatter = new DecimalFormat("#######.##");
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*  59 */   public void serverWorldTick(TickEvent.WorldTickEvent event) { if (event.side == net.minecraftforge.fml.relauncher.Side.CLIENT) { return;
/*     */     }
/*  61 */     int dim = event.world.field_73011_w.func_177502_q();
/*     */     
/*     */ 
/*  64 */     if (event.phase == TickEvent.Phase.START)
/*     */     {
/*  66 */       if (Thaumcraft.proxy.getAuraThread() == null) {
/*  67 */         Thaumcraft.proxy.setAuraThread(new AuraThread());
/*     */       }
/*     */     }
/*     */     else {
/*  71 */       if (!this.serverTicks.containsKey(Integer.valueOf(dim))) { this.serverTicks.put(Integer.valueOf(dim), Integer.valueOf(0));
/*     */       }
/*  73 */       int ticks = ((Integer)this.serverTicks.get(Integer.valueOf(dim))).intValue();
/*     */       
/*  75 */       tickChunkRegeneration(event);
/*     */       
/*  77 */       tickBlockSwap(event.world);
/*     */       
/*  79 */       ArrayList<Integer[]> nbe = (ArrayList)thaumcraft.common.tiles.devices.TileArcaneEar.noteBlockEvents.get(Integer.valueOf(dim));
/*  80 */       if (nbe != null) { nbe.clear();
/*     */       }
/*     */       
/*  83 */       if (ticks % 20 == 0)
/*     */       {
/*  85 */         CopyOnWriteArrayList<PosXY> dc = (CopyOnWriteArrayList)AuraHandler.dirtyChunks.get(Integer.valueOf(dim));
/*  86 */         if ((dc != null) && (dc.size() > 0)) {
/*  87 */           for (PosXY pos : dc) {
/*  88 */             event.world.func_175646_b(new BlockPos(pos.x * 16, 5, pos.y * 16), null);
/*     */           }
/*  90 */           dc.clear();
/*     */         }
/*     */         
/*     */ 
/*  94 */         if (AuraHandler.taintTrigger.containsKey(Integer.valueOf(dim))) {
/*  95 */           if (!Config.wuss)
/*  96 */             TaintEvents.taintEvent(event.world, (BlockPos)AuraHandler.taintTrigger.get(Integer.valueOf(dim)));
/*  97 */           AuraHandler.taintTrigger.remove(Integer.valueOf(dim));
/*     */         }
/*     */         
/*     */ 
/* 101 */         TaskHandler.clearSuspendedOrExpiredTasks(event.world);
/*     */       }
/*     */       
/* 104 */       SealHandler.tickSealEntities(event.world);
/*     */       
/* 106 */       this.serverTicks.put(Integer.valueOf(dim), Integer.valueOf(ticks + 1));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void tickChunkRegeneration(TickEvent.WorldTickEvent event)
/*     */   {
/* 116 */     int dim = event.world.field_73011_w.func_177502_q();
/* 117 */     int count = 0;
/* 118 */     ArrayList<ChunkLoc> chunks = (ArrayList)chunksToGenerate.get(Integer.valueOf(dim));
/* 119 */     if ((chunks != null) && (chunks.size() > 0)) {
/* 120 */       for (int a = 0; a < 10; a++) {
/* 121 */         chunks = (ArrayList)chunksToGenerate.get(Integer.valueOf(dim));
/* 122 */         if ((chunks == null) || (chunks.size() <= 0)) break;
/* 123 */         count++;
/* 124 */         ChunkLoc loc = (ChunkLoc)chunks.get(0);
/* 125 */         long worldSeed = event.world.func_72905_C();
/* 126 */         Random fmlRandom = new Random(worldSeed);
/* 127 */         long xSeed = fmlRandom.nextLong() >> 3;
/* 128 */         long zSeed = fmlRandom.nextLong() >> 3;
/* 129 */         fmlRandom.setSeed(xSeed * loc.chunkXPos + zSeed * loc.chunkZPos ^ worldSeed);
/* 130 */         Thaumcraft.instance.worldGen.worldGeneration(fmlRandom, loc.chunkXPos, loc.chunkZPos, event.world, false);
/* 131 */         chunks.remove(0);
/* 132 */         chunksToGenerate.put(Integer.valueOf(dim), chunks);
/*     */       }
/*     */     }
/* 135 */     if (count > 0) {
/* 136 */       FMLCommonHandler.instance().getFMLLogger().log(org.apache.logging.log4j.Level.INFO, "[Thaumcraft] Regenerated " + count + " chunks. " + Math.max(0, chunks.size()) + " chunks left");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void tickBlockSwap(World world)
/*     */   {
/* 144 */     int dim = world.field_73011_w.func_177502_q();
/* 145 */     LinkedBlockingQueue<VirtualSwapper> queue = (LinkedBlockingQueue)swapList.get(Integer.valueOf(dim));
/* 146 */     if (queue != null) {
/* 147 */       boolean didSomething = false;
/* 148 */       while (!didSomething) {
/* 149 */         VirtualSwapper vs = (VirtualSwapper)queue.poll();
/* 150 */         if (vs != null) {
/* 151 */           IBlockState bs = world.func_180495_p(vs.pos);
/* 152 */           IWand wand = null;
/* 153 */           ItemFocusBasic focus = null;
/* 154 */           ItemStack focusStack = null;
/* 155 */           if ((vs.player.field_71071_by.func_70301_a(vs.wand) != null) && ((vs.player.field_71071_by.func_70301_a(vs.wand).func_77973_b() instanceof IWand)))
/*     */           {
/* 157 */             wand = (IWand)vs.player.field_71071_by.func_70301_a(vs.wand).func_77973_b();
/* 158 */             focusStack = wand.getFocusStack(vs.player.field_71071_by.func_70301_a(vs.wand));
/* 159 */             focus = wand.getFocus(vs.player.field_71071_by.func_70301_a(vs.wand));
/*     */           }
/* 161 */           if ((world.canMineBlockBody(vs.player, vs.pos)) && (bs.func_177230_c().func_176195_g(world, vs.pos) >= 0.0F) && (!vs.target.func_77969_a(new ItemStack(bs.func_177230_c(), 1, bs.func_177230_c().func_176201_c(bs)))) && (wand != null) && (focus != null) && (!net.minecraftforge.event.ForgeEventFactory.onPlayerInteract(vs.player, PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK, world, vs.pos, net.minecraft.util.EnumFacing.UP).isCanceled()) && (wand.consumeAllVis(vs.player.field_71071_by.func_70301_a(vs.wand), vs.player, focus.getVisCost(focusStack), false, false)))
/*     */           {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 167 */             int slot = thaumcraft.common.lib.utils.InventoryUtils.isPlayerCarrying(vs.player, vs.target);
/* 168 */             if (vs.player.field_71075_bZ.field_75098_d) slot = 1;
/* 169 */             if (((vs.bSource == null) || (vs.bSource == bs)) && (slot >= 0)) {
/* 170 */               didSomething = true;
/* 171 */               if (!vs.player.field_71075_bZ.field_75098_d) {
/* 172 */                 int fortune = wand.getFocusTreasure(vs.player.field_71071_by.func_70301_a(vs.wand));
/* 173 */                 boolean silk = wand.getFocus(vs.player.field_71071_by.func_70301_a(vs.wand)).isUpgradedWith(wand.getFocusStack(vs.player.field_71071_by.func_70301_a(vs.wand)), thaumcraft.api.wands.FocusUpgradeType.silktouch);
/*     */                 
/* 175 */                 vs.player.field_71071_by.func_70298_a(slot, 1);
/*     */                 
/* 177 */                 List<ItemStack> ret = new ArrayList();
/* 178 */                 if ((silk) && (bs.func_177230_c().canSilkHarvest(world, vs.pos, bs, vs.player)))
/*     */                 {
/* 180 */                   ItemStack itemstack = BlockUtils.createStackedBlock(bs);
/* 181 */                   if (itemstack != null)
/*     */                   {
/* 183 */                     ret.add(itemstack);
/*     */                   }
/*     */                 } else {
/* 186 */                   ret = bs.func_177230_c().getDrops(world, vs.pos, bs, fortune);
/*     */                 }
/*     */                 
/* 189 */                 if (ret.size() > 0) { for (ItemStack is : ret) {
/* 190 */                     if (!vs.player.field_71071_by.func_70441_a(is)) {
/* 191 */                       world.func_72838_d(new EntityItem(world, vs.pos.func_177958_n() + 0.5D, vs.pos.func_177956_o() + 0.5D, vs.pos.func_177952_p() + 0.5D, is));
/*     */                     }
/*     */                   }
/*     */                 }
/* 195 */                 wand.consumeAllVis(vs.player.field_71071_by.func_70301_a(vs.wand), vs.player, focus.getVisCost(focusStack), true, false);
/*     */               }
/* 197 */               Block tb = Block.func_149634_a(vs.target.func_77973_b());
/* 198 */               world.func_180501_a(vs.pos, tb.func_176203_a(vs.target.func_77952_i()), 3);
/* 199 */               tb.func_180633_a(world, vs.pos, tb.func_176203_a(vs.target.func_77952_i()), vs.player, vs.target);
/* 200 */               thaumcraft.common.lib.network.PacketHandler.INSTANCE.sendToAllAround(new PacketFXBlockBamf(vs.pos, vs.bSource == null ? 10551200 : 12632319, true, false, false), new net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint(world.field_73011_w.func_177502_q(), vs.pos.func_177958_n(), vs.pos.func_177956_o(), vs.pos.func_177952_p(), 32.0D));
/*     */               
/*     */ 
/*     */ 
/*     */ 
/* 205 */               if (vs.lifespan > 0) {
/* 206 */                 for (int xx = -1; xx <= 1; xx++) {
/* 207 */                   for (int yy = -1; yy <= 1; yy++) {
/* 208 */                     for (int zz = -1; zz <= 1; zz++) {
/* 209 */                       if (((xx != 0) || (yy != 0) || (zz != 0)) && (world.func_180495_p(vs.pos.func_177982_a(xx, yy, zz)) == vs.bSource) && (BlockUtils.isBlockExposed(world, vs.pos.func_177982_a(xx, yy, zz))))
/*     */                       {
/*     */ 
/* 212 */                         queue.offer(new VirtualSwapper(vs.pos.func_177982_a(xx, yy, zz), vs.bSource, vs.target, vs.lifespan - 1, vs.player, vs.wand)); }
/*     */                     }
/*     */                   }
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */         } else {
/* 220 */           didSomething = true;
/*     */         }
/*     */       }
/* 223 */       swapList.put(Integer.valueOf(dim), queue);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void addSwapper(World world, BlockPos pos, IBlockState bs, ItemStack target, int life, EntityPlayer player, int wand) {
/* 228 */     int dim = world.field_73011_w.func_177502_q();
/*     */     
/* 230 */     if ((bs != null) && ((bs.func_177230_c() == net.minecraft.init.Blocks.field_150350_a) || (bs.func_177230_c().func_176195_g(world, pos) < 0.0F) || (target.func_77969_a(new ItemStack(bs.func_177230_c(), 1, bs.func_177230_c().func_176201_c(bs)))))) {
/* 231 */       return;
/*     */     }
/* 233 */     LinkedBlockingQueue<VirtualSwapper> queue = (LinkedBlockingQueue)swapList.get(Integer.valueOf(dim));
/* 234 */     if (queue == null) {
/* 235 */       swapList.put(Integer.valueOf(dim), new LinkedBlockingQueue());
/* 236 */       queue = (LinkedBlockingQueue)swapList.get(Integer.valueOf(dim));
/*     */     }
/* 238 */     queue.offer(new VirtualSwapper(pos, bs, target, life, player, wand));
/* 239 */     world.func_72956_a(player, "thaumcraft:wand", 0.25F, 1.0F);
/* 240 */     swapList.put(Integer.valueOf(dim), queue);
/*     */   }
/*     */   
/*     */ 
/* 244 */   public static Map<Integer, LinkedBlockingQueue<VirtualSwapper>> swapList = new HashMap();
/* 245 */   public static HashMap<Integer, ArrayList<ChunkLoc>> chunksToGenerate = new HashMap();
/*     */   
/*     */   public static class VirtualSwapper {
/* 248 */     int lifespan = 0;
/*     */     BlockPos pos;
/*     */     IBlockState bSource;
/*     */     ItemStack target;
/* 252 */     int wand = 0;
/* 253 */     EntityPlayer player = null;
/*     */     
/* 255 */     VirtualSwapper(BlockPos pos, IBlockState bs, ItemStack t, int life, EntityPlayer p, int wand) { this.pos = pos;
/* 256 */       this.bSource = bs;
/* 257 */       this.target = t;
/* 258 */       this.lifespan = life;
/* 259 */       this.player = p;
/* 260 */       this.wand = wand;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/events/ServerTickEventsFML.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */