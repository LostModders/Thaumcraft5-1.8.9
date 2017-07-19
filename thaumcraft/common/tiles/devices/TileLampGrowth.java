/*     */ package thaumcraft.common.tiles.devices;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.IGrowable;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.network.NetworkManager;
/*     */ import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.ITickable;
/*     */ import net.minecraft.world.EnumSkyBlock;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
/*     */ import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.IEssentiaTransport;
/*     */ import thaumcraft.api.blocks.TileThaumcraft;
/*     */ import thaumcraft.common.blocks.IBlockEnabled;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.fx.PacketFXBlockMist;
/*     */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*     */ import thaumcraft.common.lib.utils.CropUtils;
/*     */ 
/*     */ public class TileLampGrowth
/*     */   extends TileThaumcraft implements IEssentiaTransport, ITickable
/*     */ {
/*  36 */   private boolean reserve = false;
/*  37 */   public int charges = -1;
/*     */   
/*     */ 
/*     */   public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
/*     */   {
/*  42 */     super.onDataPacket(net, pkt);
/*  43 */     if ((this.field_145850_b != null) && 
/*  44 */       (this.field_145850_b.field_72995_K)) {
/*  45 */       this.field_145850_b.func_180500_c(EnumSkyBlock.BLOCK, func_174877_v());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_73660_a()
/*     */   {
/*  52 */     if (!this.field_145850_b.field_72995_K) {
/*  53 */       if (this.charges <= 0) {
/*  54 */         if (this.reserve) {
/*  55 */           this.charges = 100;
/*  56 */           this.reserve = false;
/*  57 */           func_70296_d();
/*  58 */           this.field_145850_b.func_175689_h(func_174877_v());
/*     */         }
/*  60 */         else if (drawEssentia()) {
/*  61 */           this.charges = 100;
/*  62 */           func_70296_d();
/*  63 */           this.field_145850_b.func_175689_h(func_174877_v());
/*     */         }
/*  65 */         if (this.charges <= 0) {
/*  66 */           if (BlockStateUtils.isEnabled(func_145832_p()))
/*     */           {
/*  68 */             this.field_145850_b.func_180501_a(this.field_174879_c, this.field_145850_b.func_180495_p(func_174877_v()).func_177226_a(IBlockEnabled.ENABLED, Boolean.valueOf(false)), 3);
/*     */           }
/*     */         }
/*  71 */         else if ((!gettingPower()) && (!BlockStateUtils.isEnabled(func_145832_p())))
/*     */         {
/*  73 */           this.field_145850_b.func_180501_a(this.field_174879_c, this.field_145850_b.func_180495_p(func_174877_v()).func_177226_a(IBlockEnabled.ENABLED, Boolean.valueOf(true)), 3);
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*  78 */       if ((!this.reserve) && 
/*  79 */         (drawEssentia())) {
/*  80 */         this.reserve = true;
/*     */       }
/*     */       
/*     */ 
/*  84 */       if (this.charges == 0) {
/*  85 */         this.charges = -1;
/*  86 */         this.field_145850_b.func_175689_h(func_174877_v());
/*     */       }
/*     */       
/*  89 */       if ((!gettingPower()) && (this.charges > 0)) {
/*  90 */         updatePlant();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   boolean isPlant(BlockPos bp)
/*     */   {
/*  98 */     Block b = this.field_145850_b.func_180495_p(bp).func_177230_c();
/*  99 */     boolean flag = b instanceof IGrowable;
/* 100 */     Material mat = b.func_149688_o();
/* 101 */     return ((flag) || (mat == Material.field_151570_A) || (mat == Material.field_151585_k)) && (mat != Material.field_151577_b);
/*     */   }
/*     */   
/* 104 */   int lx = 0;
/* 105 */   int ly = 0;
/* 106 */   int lz = 0;
/* 107 */   Block lid = Blocks.field_150350_a;
/* 108 */   int lmd = 0;
/*     */   
/* 110 */   ArrayList<BlockPos> checklist = new ArrayList();
/*     */   
/*     */   private void updatePlant() {
/* 113 */     IBlockState bs = this.field_145850_b.func_180495_p(new BlockPos(this.lx, this.ly, this.lz));
/* 114 */     if ((this.lid != bs.func_177230_c()) || (this.lmd != bs.func_177230_c().func_176201_c(bs))) {
/* 115 */       EntityPlayer p = this.field_145850_b.func_72977_a(this.lx, this.ly, this.lz, 32.0D);
/* 116 */       if (p != null) {
/* 117 */         PacketHandler.INSTANCE.sendToAllAround(new PacketFXBlockMist(new BlockPos(this.lx, this.ly, this.lz), 4259648), new NetworkRegistry.TargetPoint(this.field_145850_b.field_73011_w.func_177502_q(), this.lx, this.ly, this.lz, 32.0D));
/*     */       }
/*     */       
/*     */ 
/* 121 */       this.lid = bs.func_177230_c();
/* 122 */       this.lmd = bs.func_177230_c().func_176201_c(bs);
/*     */     }
/*     */     
/* 125 */     int distance = 6;
/*     */     
/* 127 */     if (this.checklist.size() == 0) {
/* 128 */       for (int a = -distance; a <= distance; a++) {
/* 129 */         for (int b = -distance; b <= distance; b++)
/* 130 */           this.checklist.add(func_174877_v().func_177982_a(a, distance, b));
/*     */       }
/* 132 */       Collections.shuffle(this.checklist, this.field_145850_b.field_73012_v);
/*     */     }
/*     */     
/* 135 */     int x = ((BlockPos)this.checklist.get(0)).func_177958_n();
/* 136 */     int y = ((BlockPos)this.checklist.get(0)).func_177956_o();
/* 137 */     int z = ((BlockPos)this.checklist.get(0)).func_177952_p();
/* 138 */     this.checklist.remove(0);
/* 140 */     for (; 
/* 140 */         y >= this.field_174879_c.func_177956_o() - distance; y--) {
/* 141 */       BlockPos bp = new BlockPos(x, y, z);
/* 142 */       if ((!this.field_145850_b.func_175623_d(bp)) && (isPlant(bp)) && (func_145835_a(x + 0.5D, y + 0.5D, z + 0.5D) < distance * distance) && (!CropUtils.isGrownCrop(this.field_145850_b, bp)) && (CropUtils.doesLampGrow(this.field_145850_b, bp)))
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/* 147 */         this.charges -= 1;
/*     */         
/* 149 */         this.lx = x;
/* 150 */         this.ly = y;
/* 151 */         this.lz = z;
/* 152 */         IBlockState bs2 = this.field_145850_b.func_180495_p(bp);
/* 153 */         this.lid = bs2.func_177230_c();
/* 154 */         this.lmd = bs2.func_177230_c().func_176201_c(bs2);
/*     */         
/* 156 */         this.field_145850_b.func_175684_a(bp, this.lid, 1);
/* 157 */         return;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void readCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/* 166 */     this.reserve = nbttagcompound.func_74767_n("reserve");
/* 167 */     this.charges = nbttagcompound.func_74762_e("charges");
/*     */   }
/*     */   
/*     */ 
/*     */   public void writeCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/* 173 */     nbttagcompound.func_74757_a("reserve", this.reserve);
/* 174 */     nbttagcompound.func_74768_a("charges", this.charges);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/* 179 */   int drawDelay = 0;
/*     */   
/*     */   boolean drawEssentia() {
/* 182 */     if (++this.drawDelay % 5 != 0) return false;
/* 183 */     TileEntity te = ThaumcraftApiHelper.getConnectableTile(this.field_145850_b, func_174877_v(), BlockStateUtils.getFacing(func_145832_p()));
/* 184 */     if (te != null) {
/* 185 */       IEssentiaTransport ic = (IEssentiaTransport)te;
/* 186 */       if (!ic.canOutputTo(BlockStateUtils.getFacing(func_145832_p()).func_176734_d())) return false;
/* 187 */       if ((ic.getSuctionAmount(BlockStateUtils.getFacing(func_145832_p()).func_176734_d()) < getSuctionAmount(BlockStateUtils.getFacing(func_145832_p()))) && (ic.takeEssentia(Aspect.PLANT, 1, BlockStateUtils.getFacing(func_145832_p()).func_176734_d()) == 1))
/*     */       {
/*     */ 
/* 190 */         return true;
/*     */       }
/*     */     }
/* 193 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isConnectable(EnumFacing face)
/*     */   {
/* 198 */     return face == BlockStateUtils.getFacing(func_145832_p());
/*     */   }
/*     */   
/*     */   public boolean canInputFrom(EnumFacing face)
/*     */   {
/* 203 */     return face == BlockStateUtils.getFacing(func_145832_p());
/*     */   }
/*     */   
/*     */   public boolean canOutputTo(EnumFacing face)
/*     */   {
/* 208 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setSuction(Aspect aspect, int amount) {}
/*     */   
/*     */ 
/*     */   public int getMinimumSuction()
/*     */   {
/* 217 */     return 0;
/*     */   }
/*     */   
/*     */   public Aspect getSuctionType(EnumFacing face)
/*     */   {
/* 222 */     return Aspect.PLANT;
/*     */   }
/*     */   
/*     */   public int getSuctionAmount(EnumFacing face)
/*     */   {
/* 227 */     return (face == BlockStateUtils.getFacing(func_145832_p())) && ((!this.reserve) || (this.charges <= 0)) ? 128 : 0;
/*     */   }
/*     */   
/*     */   public Aspect getEssentiaType(EnumFacing loc)
/*     */   {
/* 232 */     return null;
/*     */   }
/*     */   
/*     */   public int getEssentiaAmount(EnumFacing loc)
/*     */   {
/* 237 */     return 0;
/*     */   }
/*     */   
/*     */   public int takeEssentia(Aspect aspect, int amount, EnumFacing loc)
/*     */   {
/* 242 */     return 0;
/*     */   }
/*     */   
/*     */   public int addEssentia(Aspect aspect, int amount, EnumFacing loc)
/*     */   {
/* 247 */     return 0;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/tiles/devices/TileLampGrowth.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */