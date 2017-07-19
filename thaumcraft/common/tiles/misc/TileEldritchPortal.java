/*     */ package thaumcraft.common.tiles.misc;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.server.management.ServerConfigurationManager;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.ITickable;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraftforge.fml.common.FMLCommonHandler;
/*     */ import thaumcraft.api.blocks.TileThaumcraft;
/*     */ import thaumcraft.api.internal.WorldCoordinates;
/*     */ import thaumcraft.api.research.ResearchHelper;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.items.tools.ItemSinisterStone;
/*     */ import thaumcraft.common.lib.world.dim.TeleporterThaumcraft;
/*     */ 
/*     */ public class TileEldritchPortal extends TileThaumcraft implements ITickable
/*     */ {
/*     */   public double func_145833_n()
/*     */   {
/*  26 */     return 9216.0D;
/*     */   }
/*     */   
/*     */   @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*     */   public AxisAlignedBB getRenderBoundingBox()
/*     */   {
/*  32 */     return AxisAlignedBB.func_178781_a(func_174877_v().func_177958_n() - 1, func_174877_v().func_177956_o() - 1, func_174877_v().func_177952_p() - 1, func_174877_v().func_177958_n() + 2, func_174877_v().func_177956_o() + 2, func_174877_v().func_177952_p() + 2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  38 */   public boolean open = false;
/*  39 */   public int opencount = -1;
/*  40 */   private int count = 0;
/*     */   
/*  42 */   private WorldCoordinates lastLoc = null;
/*     */   
/*     */   public void func_73660_a()
/*     */   {
/*  46 */     this.count += 1;
/*     */     
/*  48 */     if ((this.field_145850_b.field_72995_K) && (this.lastLoc == null)) {
/*  49 */       this.lastLoc = new WorldCoordinates(func_174877_v(), this.field_145850_b.field_73011_w.func_177502_q());
/*  50 */       ItemSinisterStone.eldritchPortals.put(this.lastLoc, Long.valueOf(System.currentTimeMillis()));
/*     */     }
/*     */     
/*  53 */     if ((this.field_145850_b.field_73011_w.func_177502_q() == Config.dimensionOuterId) && (!this.open)) {
/*  54 */       this.open = true;
/*     */     }
/*  56 */     if ((this.field_145850_b.field_72995_K) && (this.open) && ((this.count % 250 == 0) || (this.count == 0))) {
/*  57 */       this.field_145850_b.func_72980_b(func_174877_v().func_177958_n() + 0.5D, func_174877_v().func_177956_o() + 0.5D, func_174877_v().func_177952_p() + 0.5D, "thaumcraft:evilportal", 1.0F, 1.0F, false);
/*     */     }
/*  59 */     if ((this.field_145850_b.field_72995_K) && (this.open) && (this.opencount < 30)) {
/*  60 */       this.opencount += 1;
/*     */     }
/*  62 */     if ((!this.field_145850_b.field_72995_K) && (this.open) && (this.count % 5 == 0)) {
/*  63 */       List ents = this.field_145850_b.func_72872_a(EntityPlayerMP.class, AxisAlignedBB.func_178781_a(func_174877_v().func_177958_n(), func_174877_v().func_177956_o(), func_174877_v().func_177952_p(), func_174877_v().func_177958_n() + 1, func_174877_v().func_177956_o() + 1, func_174877_v().func_177952_p() + 1).func_72314_b(0.5D, 1.0D, 0.5D));
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*  68 */       if (ents.size() > 0) {
/*  69 */         for (Object e : ents) {
/*  70 */           EntityPlayerMP player = (EntityPlayerMP)e;
/*  71 */           if ((player.field_70154_o == null) && (player.field_70153_n == null))
/*     */           {
/*     */ 
/*  74 */             MinecraftServer mServer = FMLCommonHandler.instance().getMinecraftServerInstance();
/*     */             
/*  76 */             if (player.field_71088_bW > 0)
/*     */             {
/*  78 */               player.field_71088_bW = 100;
/*     */             }
/*  80 */             else if (player.field_71093_bK != Config.dimensionOuterId)
/*     */             {
/*  82 */               player.field_71088_bW = 100;
/*     */               
/*  84 */               player.field_71133_b.func_71203_ab().transferPlayerToDimension(player, Config.dimensionOuterId, new TeleporterThaumcraft(mServer.func_71218_a(Config.dimensionOuterId)));
/*     */               
/*     */ 
/*     */ 
/*  88 */               if (!ResearchHelper.isResearchComplete(player.func_70005_c_(), "ENTEROUTER")) {
/*  89 */                 ResearchHelper.completeResearch(player, "ENTEROUTER");
/*     */               }
/*     */             }
/*     */             else
/*     */             {
/*  94 */               player.field_71088_bW = 100;
/*  95 */               player.field_71133_b.func_71203_ab().transferPlayerToDimension(player, 0, new TeleporterThaumcraft(mServer.func_71218_a(0)));
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void readCustomNBT(NBTTagCompound nbt)
/*     */   {
/* 107 */     this.open = nbt.func_74767_n("open");
/*     */   }
/*     */   
/*     */   public void writeCustomNBT(NBTTagCompound nbt)
/*     */   {
/* 112 */     nbt.func_74757_a("open", this.open);
/*     */   }
/*     */   
/*     */   public void func_145843_s()
/*     */   {
/* 117 */     if ((this.field_145850_b.field_72995_K) && (this.lastLoc != null)) {
/* 118 */       ItemSinisterStone.eldritchPortals.remove(this.lastLoc);
/*     */     }
/* 120 */     super.func_145843_s();
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/tiles/misc/TileEldritchPortal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */