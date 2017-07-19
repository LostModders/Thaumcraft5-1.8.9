/*     */ package thaumcraft.common.tiles.devices;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.WeakHashMap;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.ITickable;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import thaumcraft.common.blocks.IBlockEnabled;
/*     */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*     */ 
/*     */ public class TileArcaneEar extends TileEntity implements ITickable
/*     */ {
/*  20 */   public byte note = 0;
/*  21 */   public byte tone = 0;
/*  22 */   public int redstoneSignal = 0;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_145841_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/*  29 */     super.func_145841_b(par1NBTTagCompound);
/*  30 */     par1NBTTagCompound.func_74774_a("note", this.note);
/*  31 */     par1NBTTagCompound.func_74774_a("tone", this.tone);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_145839_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/*  39 */     super.func_145839_a(par1NBTTagCompound);
/*  40 */     this.note = par1NBTTagCompound.func_74771_c("note");
/*  41 */     this.tone = par1NBTTagCompound.func_74771_c("tone");
/*     */     
/*  43 */     if (this.note < 0)
/*     */     {
/*  45 */       this.note = 0;
/*     */     }
/*     */     
/*  48 */     if (this.note > 24)
/*     */     {
/*  50 */       this.note = 24;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_73660_a()
/*     */   {
/*  57 */     if (!this.field_145850_b.field_72995_K) {
/*  58 */       if (this.redstoneSignal > 0) {
/*  59 */         this.redstoneSignal -= 1;
/*  60 */         if (this.redstoneSignal == 0) {
/*  61 */           EnumFacing facing = BlockStateUtils.getFacing(func_145832_p()).func_176734_d();
/*  62 */           TileEntity tileentity = this.field_145850_b.func_175625_s(this.field_174879_c);
/*  63 */           this.field_145850_b.func_180501_a(this.field_174879_c, this.field_145850_b.func_180495_p(this.field_174879_c).func_177226_a(IBlockEnabled.ENABLED, Boolean.valueOf(false)), 3);
/*  64 */           if (tileentity != null)
/*     */           {
/*  66 */             tileentity.func_145829_t();
/*  67 */             this.field_145850_b.func_175690_a(this.field_174879_c, tileentity);
/*     */           }
/*  69 */           this.field_145850_b.func_180496_d(this.field_174879_c, func_145838_q());
/*  70 */           this.field_145850_b.func_180496_d(this.field_174879_c.func_177972_a(facing), func_145838_q());
/*  71 */           this.field_145850_b.func_175689_h(this.field_174879_c);
/*     */         }
/*     */       }
/*  74 */       ArrayList<Integer[]> nbe = (ArrayList)noteBlockEvents.get(Integer.valueOf(this.field_145850_b.field_73011_w.func_177502_q()));
/*  75 */       if (nbe != null) {
/*  76 */         for (Integer[] dat : nbe) {
/*  77 */           if ((dat[3].intValue() == this.tone) && (dat[4].intValue() == this.note) && 
/*  78 */             (func_145835_a(dat[0].intValue() + 0.5D, dat[1].intValue() + 0.5D, dat[2].intValue() + 0.5D) <= 4096.0D)) {
/*  79 */             EnumFacing facing = BlockStateUtils.getFacing(func_145832_p()).func_176734_d();
/*  80 */             triggerNote(this.field_145850_b, this.field_174879_c, false);
/*  81 */             this.redstoneSignal = 10;
/*  82 */             TileEntity tileentity = this.field_145850_b.func_175625_s(this.field_174879_c);
/*  83 */             this.field_145850_b.func_180501_a(this.field_174879_c, this.field_145850_b.func_180495_p(this.field_174879_c).func_177226_a(IBlockEnabled.ENABLED, Boolean.valueOf(true)), 3);
/*  84 */             if (tileentity != null)
/*     */             {
/*  86 */               tileentity.func_145829_t();
/*  87 */               this.field_145850_b.func_175690_a(this.field_174879_c, tileentity);
/*     */             }
/*  89 */             this.field_145850_b.func_180496_d(this.field_174879_c, func_145838_q());
/*  90 */             this.field_145850_b.func_180496_d(this.field_174879_c.func_177972_a(facing), func_145838_q());
/*  91 */             this.field_145850_b.func_175689_h(this.field_174879_c);
/*  92 */             break;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/* 100 */   public static WeakHashMap<Integer, ArrayList<Integer[]>> noteBlockEvents = new WeakHashMap();
/*     */   
/*     */   public void updateTone() {
/*     */     try {
/* 104 */       EnumFacing facing = BlockStateUtils.getFacing(func_145832_p()).func_176734_d();
/* 105 */       Material var5 = this.field_145850_b.func_180495_p(this.field_174879_c.func_177972_a(facing)).func_177230_c().func_149688_o();
/* 106 */       this.tone = 0;
/* 107 */       if (var5 == Material.field_151576_e)
/*     */       {
/* 109 */         this.tone = 1;
/*     */       }
/*     */       
/* 112 */       if (var5 == Material.field_151595_p)
/*     */       {
/* 114 */         this.tone = 2;
/*     */       }
/*     */       
/* 117 */       if (var5 == Material.field_151592_s)
/*     */       {
/* 119 */         this.tone = 3;
/*     */       }
/*     */       
/* 122 */       if (var5 == Material.field_151575_d)
/*     */       {
/* 124 */         this.tone = 4;
/*     */       }
/* 126 */       func_70296_d();
/*     */     }
/*     */     catch (Exception e) {}
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void changePitch()
/*     */   {
/* 136 */     this.note = ((byte)((this.note + 1) % 25));
/* 137 */     func_70296_d();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void triggerNote(World world, BlockPos pos, boolean sound)
/*     */   {
/* 145 */     byte var6 = -1;
/* 146 */     if (sound) {
/* 147 */       EnumFacing facing = BlockStateUtils.getFacing(func_145832_p()).func_176734_d();
/* 148 */       Material var5 = world.func_180495_p(pos.func_177972_a(facing)).func_177230_c().func_149688_o();
/* 149 */       var6 = 0;
/* 150 */       if (var5 == Material.field_151576_e)
/*     */       {
/* 152 */         var6 = 1;
/*     */       }
/*     */       
/* 155 */       if (var5 == Material.field_151595_p)
/*     */       {
/* 157 */         var6 = 2;
/*     */       }
/*     */       
/* 160 */       if (var5 == Material.field_151592_s)
/*     */       {
/* 162 */         var6 = 3;
/*     */       }
/*     */       
/* 165 */       if (var5 == Material.field_151575_d)
/*     */       {
/* 167 */         var6 = 4;
/*     */       }
/*     */     }
/* 170 */     world.func_175641_c(pos, func_145838_q(), var6, this.note);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/tiles/devices/TileArcaneEar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */