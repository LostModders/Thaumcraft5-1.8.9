/*     */ package thaumcraft.common.tiles.devices;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.ITickable;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.client.fx.ParticleEngine;
/*     */ import thaumcraft.client.fx.particles.FXSpark;
/*     */ import thaumcraft.common.blocks.devices.BlockAuraTotem;
/*     */ import thaumcraft.common.blocks.devices.BlockAuraTotem.TotemType;
/*     */ import thaumcraft.common.tiles.TileThaumcraftInventory;
/*     */ 
/*     */ public class TileAuraTotem extends TileThaumcraftInventory implements ITickable
/*     */ {
/*  22 */   public byte type = -1;
/*     */   public int time;
/*  24 */   public int maxTime = 500;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void readCustomNBT(NBTTagCompound nbt)
/*     */   {
/*  32 */     super.readCustomNBT(nbt);
/*  33 */     this.type = nbt.func_74771_c("type");
/*     */   }
/*     */   
/*     */   public void writeCustomNBT(NBTTagCompound nbt)
/*     */   {
/*  38 */     super.writeCustomNBT(nbt);
/*  39 */     nbt.func_74774_a("type", this.type);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_145839_a(NBTTagCompound nbtCompound)
/*     */   {
/*  45 */     super.func_145839_a(nbtCompound);
/*  46 */     this.time = nbtCompound.func_74762_e("time");
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_145841_b(NBTTagCompound nbtCompound)
/*     */   {
/*  52 */     super.func_145841_b(nbtCompound);
/*  53 */     nbtCompound.func_74768_a("time", this.time);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int getTimeScaled(int par1)
/*     */   {
/*  59 */     return this.time * par1 / this.maxTime;
/*     */   }
/*     */   
/*  62 */   private int count = 0;
/*  63 */   protected int zoneOuter = -1;
/*  64 */   protected int zoneInner = -1;
/*  65 */   protected int zonePure = -1;
/*     */   
/*  67 */   int cd = 10;
/*     */   
/*     */ 
/*     */   public void func_73660_a()
/*     */   {
/*  72 */     this.count += 1;
/*     */     
/*  74 */     if (this.zoneOuter < 0) { checkPoles();
/*     */     }
/*  76 */     if ((!this.field_145850_b.field_72995_K) && (this.count % this.cd == 0))
/*     */     {
/*  78 */       if (this.time > 0)
/*     */       {
/*  80 */         this.time -= 1;
/*     */         
/*  82 */         performMagnet();
/*     */       }
/*     */       else {
/*  85 */         if (this.type >= 0) {
/*  86 */           func_70296_d();
/*  87 */           this.field_145850_b.func_175689_h(func_174877_v());
/*  88 */           this.field_145850_b.func_175641_c(func_174877_v(), func_145838_q(), 2, -1);
/*     */         }
/*  90 */         this.type = -1;
/*  91 */         if ((func_70301_a(0) != null) && (func_70301_a(0).func_77973_b() == ItemsTC.shard)) {
/*  92 */           switch (func_70301_a(0).func_77952_i()) {
/*  93 */           case 0:  this.type = 1; break;
/*  94 */           case 1:  this.type = 2; break;
/*  95 */           case 2:  this.type = 3; break;
/*  96 */           case 3:  this.type = 4; break;
/*  97 */           case 4:  this.type = 5; break;
/*  98 */           case 5:  this.type = 6; break;
/*  99 */           case 7:  this.type = 0; break;
/* 100 */           case 6:  this.type = 7;
/*     */           }
/* 102 */           if (this.type >= 0) {
/* 103 */             func_70298_a(0, 1);
/* 104 */             this.time = this.maxTime;
/* 105 */             func_70296_d();
/* 106 */             this.field_145850_b.func_175689_h(func_174877_v());
/* 107 */             this.field_145850_b.func_175641_c(func_174877_v(), func_145838_q(), 2, this.type);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 113 */     if ((this.field_145850_b.field_72995_K) && (this.type >= 0) && (this.count % 20 == 0))
/*     */     {
/* 115 */       drawEffect();
/*     */     }
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   protected void drawEffect() {
/* 121 */     int a = func_145831_w().field_73012_v.nextInt(this.zoneOuter + this.zoneInner + 1);
/* 122 */     FXSpark ef = new FXSpark(func_145831_w(), func_174877_v().func_177958_n() + func_145831_w().field_73012_v.nextFloat(), func_174877_v().func_177956_o() - a + func_145831_w().field_73012_v.nextFloat(), func_174877_v().func_177952_p() + func_145831_w().field_73012_v.nextFloat(), 0.5F);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 127 */     ef.func_70538_b(0.65F + func_145831_w().field_73012_v.nextFloat() * 0.1F, 1.0F, 1.0F);
/* 128 */     ef.func_82338_g(0.8F);
/* 129 */     ParticleEngine.instance.addEffect(func_145831_w(), ef);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void performMagnet() {}
/*     */   
/*     */   public boolean func_94041_b(int par1, ItemStack stack)
/*     */   {
/* 137 */     return (stack != null) && (stack.func_77973_b() == ItemsTC.shard);
/*     */   }
/*     */   
/*     */   public Aspect getAspect() {
/* 141 */     switch (this.type) {
/* 142 */     default:  return null;
/* 143 */     case 1:  return Aspect.AIR;
/* 144 */     case 2:  return Aspect.FIRE;
/* 145 */     case 3:  return Aspect.WATER;
/* 146 */     case 4:  return Aspect.EARTH;
/* 147 */     case 5:  return Aspect.ORDER;
/* 148 */     case 6:  return Aspect.ENTROPY; }
/* 149 */     return Aspect.FLUX;
/*     */   }
/*     */   
/*     */   public void checkPoles()
/*     */   {
/* 154 */     this.zoneOuter = 0;
/* 155 */     this.zoneInner = 0;
/* 156 */     this.zonePure = 0;
/* 157 */     BlockPos p = func_174877_v().func_177977_b();
/* 158 */     IBlockState bs = func_145831_w().func_180495_p(p);
/* 159 */     int c = 0;
/* 160 */     while ((bs.func_177230_c() == thaumcraft.api.blocks.BlocksTC.auraTotem) && (c < 5)) {
/* 161 */       if (bs.func_177229_b(BlockAuraTotem.TYPE) == BlockAuraTotem.TotemType.POLE_OUTER) {
/* 162 */         this.zoneOuter += 1;
/* 163 */         c++;
/*     */       }
/* 165 */       if (bs.func_177229_b(BlockAuraTotem.TYPE) == BlockAuraTotem.TotemType.POLE_INNER) {
/* 166 */         this.zoneInner += 1;
/* 167 */         c++;
/*     */       }
/* 169 */       if (bs.func_177229_b(BlockAuraTotem.TYPE) == BlockAuraTotem.TotemType.POLE_PURE) {
/* 170 */         this.zonePure += 1;
/* 171 */         c++;
/*     */       }
/* 173 */       p = p.func_177977_b();
/* 174 */       bs = func_145831_w().func_180495_p(p);
/*     */     }
/* 176 */     this.cd = (10 - (this.zoneInner + this.zoneOuter));
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_145842_c(int i, int j)
/*     */   {
/* 182 */     if (i == 1)
/*     */     {
/* 184 */       if (this.field_145850_b.field_72995_K) {
/* 185 */         checkPoles();
/*     */       }
/* 187 */       return true;
/*     */     }
/* 189 */     if (i == 2)
/*     */     {
/* 191 */       if (this.field_145850_b.field_72995_K) {
/* 192 */         this.type = ((byte)j);
/* 193 */         func_145831_w().func_175704_b(func_174877_v(), func_174877_v());
/*     */       }
/* 195 */       return true;
/*     */     }
/* 197 */     return super.func_145842_c(i, j);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/tiles/devices/TileAuraTotem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */