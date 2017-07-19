/*     */ package thaumcraft.common.tiles.devices;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagInt;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.ITickable;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fluids.FluidRegistry;
/*     */ import net.minecraftforge.fluids.FluidStack;
/*     */ import net.minecraftforge.fluids.IFluidHandler;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aura.AuraHelper;
/*     */ import thaumcraft.api.blocks.TileThaumcraft;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import vazkii.botania.api.item.IPetalApothecary;
/*     */ 
/*     */ 
/*     */ public class TileWaterJug
/*     */   extends TileThaumcraft
/*     */   implements ITickable
/*     */ {
/*  29 */   public int charge = 0;
/*  30 */   int zone = 0;
/*  31 */   int counter = 0;
/*  32 */   ArrayList<Integer> handlers = new ArrayList();
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_145839_a(NBTTagCompound nbt)
/*     */   {
/*  38 */     super.func_145839_a(nbt);
/*     */     
/*  40 */     this.charge = nbt.func_74762_e("charge");
/*     */     
/*  42 */     NBTTagList nbttaglist = nbt.func_150295_c("handlers", 3);
/*  43 */     this.handlers = new ArrayList();
/*  44 */     for (int i = 0; i < nbttaglist.func_74745_c(); i++)
/*     */     {
/*  46 */       NBTTagInt tag = (NBTTagInt)nbttaglist.func_179238_g(i);
/*  47 */       this.handlers.add(Integer.valueOf(tag.func_150287_d()));
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_145841_b(NBTTagCompound nbt)
/*     */   {
/*  53 */     super.func_145841_b(nbt);
/*     */     
/*  55 */     nbt.func_74768_a("charge", this.charge);
/*     */     
/*  57 */     NBTTagList nbttaglist = new NBTTagList();
/*  58 */     NBTTagInt tag; for (int i = 0; i < this.handlers.size(); i++)
/*     */     {
/*  60 */       tag = new NBTTagInt(((Integer)this.handlers.get(i)).intValue());
/*     */     }
/*  62 */     nbt.func_74782_a("handlers", nbttaglist);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_73660_a()
/*     */   {
/*  68 */     this.counter += 1;
/*  69 */     if (this.field_145850_b.field_72995_K) {
/*  70 */       if ((this.tcount > 0) || (this.field_145850_b.field_73012_v.nextInt(15) == 0)) {
/*  71 */         Thaumcraft.proxy.getFX().jarSplashFx(func_174877_v().func_177958_n() + 0.5D, func_174877_v().func_177956_o() + 1, func_174877_v().func_177952_p() + 0.5D);
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*  76 */       if (this.tcount > 0) {
/*  77 */         if (this.tcount % 5 == 0) {
/*  78 */           int x = this.zc / 5 % 5;
/*  79 */           int y = this.zc / 5 / 5 % 3;
/*  80 */           int z = this.zc % 5;
/*  81 */           Thaumcraft.proxy.getFX().waterTrailFx(func_174877_v(), func_174877_v().func_177982_a(x - 2, y - 1, z - 2), this.counter, 2650102, 0.1F);
/*     */         }
/*  83 */         this.tcount -= 1;
/*     */       }
/*     */     }
/*  86 */     else if (this.counter % 5 == 0) {
/*  87 */       this.zone += 1;
/*  88 */       int x = this.zone / 5 % 5;
/*  89 */       int y = this.zone / 5 / 5 % 3;
/*  90 */       int z = this.zone % 5;
/*  91 */       TileEntity te = this.field_145850_b.func_175625_s(func_174877_v().func_177982_a(x - 2, y - 1, z - 2));
/*  92 */       if ((te != null) && (((te instanceof IFluidHandler)) || ((te instanceof IPetalApothecary))) && 
/*  93 */         (!this.handlers.contains(Integer.valueOf(this.zone)))) {
/*  94 */         this.handlers.add(Integer.valueOf(this.zone));
/*  95 */         func_70296_d();
/*     */       }
/*     */       
/*     */ 
/*  99 */       int i = 0;
/* 100 */       while ((i < this.handlers.size()) && (this.charge > 0)) {
/* 101 */         int zz = ((Integer)this.handlers.get(i)).intValue();
/* 102 */         x = zz / 5 % 5;
/* 103 */         y = zz / 5 / 5 % 3;
/* 104 */         z = zz % 5;
/* 105 */         TileEntity tile = this.field_145850_b.func_175625_s(func_174877_v().func_177982_a(x - 2, y - 1, z - 2));
/* 106 */         if ((tile != null) && ((tile instanceof IFluidHandler))) {
/* 107 */           IFluidHandler fh = (IFluidHandler)tile;
/* 108 */           if (fh.canFill(EnumFacing.UP, FluidRegistry.WATER)) {
/* 109 */             int q = fh.fill(EnumFacing.UP, new FluidStack(FluidRegistry.WATER, 25), true);
/* 110 */             this.charge -= q;
/* 111 */             func_70296_d();
/* 112 */             if (q > 0) {
/* 113 */               this.field_145850_b.func_175641_c(func_174877_v(), func_145838_q(), 1, zz);
/* 114 */               break;
/*     */             }
/*     */           }
/* 117 */         } else if ((tile != null) && ((tile instanceof IPetalApothecary))) {
/* 118 */           IPetalApothecary pa = (IPetalApothecary)tile;
/* 119 */           if (!pa.hasWater()) {
/* 120 */             pa.setWater(true);
/* 121 */             this.field_145850_b.func_175641_c(func_174877_v(), func_145838_q(), 2, zz);
/* 122 */             this.charge -= 250;
/*     */           }
/*     */         } else {
/* 125 */           this.handlers.remove(i);
/* 126 */           func_70296_d();
/* 127 */           continue;
/*     */         }
/* 129 */         i++;
/*     */       }
/*     */       
/* 132 */       if ((this.charge <= 0) && 
/* 133 */         (AuraHelper.drainAura(func_145831_w(), func_174877_v(), Aspect.WATER, 1))) {
/* 134 */         this.charge += 1000;
/* 135 */         func_70296_d();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 143 */   int zc = 0;
/* 144 */   int tcount = 0;
/*     */   
/*     */ 
/*     */   public boolean func_145842_c(int i, int j)
/*     */   {
/* 149 */     if (i == 1)
/*     */     {
/* 151 */       if (this.field_145850_b.field_72995_K) {
/* 152 */         this.zc = j;
/* 153 */         this.tcount = 5;
/*     */       }
/* 155 */       return true;
/*     */     }
/* 157 */     return super.func_145842_c(i, j);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/tiles/devices/TileWaterJug.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */