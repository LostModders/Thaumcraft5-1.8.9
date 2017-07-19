/*     */ package thaumcraft.common.tiles.essentia;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.codechicken.lib.raytracer.RayTracer;
/*     */ 
/*     */ public class TileTubeValve extends TileTube
/*     */ {
/*  17 */   public boolean allowFlow = true;
/*  18 */   boolean wasPoweredLastTick = false;
/*     */   
/*  20 */   public float rotation = 0.0F;
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_73660_a()
/*     */   {
/*  26 */     if ((!this.field_145850_b.field_72995_K) && (this.count % 5 == 0)) {
/*  27 */       boolean gettingPower = gettingPower();
/*  28 */       if ((this.wasPoweredLastTick) && (!gettingPower) && 
/*  29 */         (this.allowFlow != true)) {
/*  30 */         this.allowFlow = true;
/*  31 */         this.field_145850_b.func_72908_a(this.field_174879_c.func_177958_n(), this.field_174879_c.func_177956_o(), this.field_174879_c.func_177952_p(), "thaumcraft:squeek", 0.7F, 0.9F + this.field_145850_b.field_73012_v.nextFloat() * 0.2F);
/*  32 */         this.field_145850_b.func_175689_h(this.field_174879_c);
/*  33 */         func_70296_d();
/*     */       }
/*     */       
/*     */ 
/*  37 */       if ((!this.wasPoweredLastTick) && (gettingPower) && 
/*  38 */         (this.allowFlow)) {
/*  39 */         this.allowFlow = false;
/*  40 */         this.field_145850_b.func_72908_a(this.field_174879_c.func_177958_n(), this.field_174879_c.func_177956_o(), this.field_174879_c.func_177952_p(), "thaumcraft:squeek", 0.7F, 0.9F + this.field_145850_b.field_73012_v.nextFloat() * 0.2F);
/*  41 */         this.field_145850_b.func_175689_h(this.field_174879_c);
/*  42 */         func_70296_d();
/*     */       }
/*     */       
/*     */ 
/*  46 */       this.wasPoweredLastTick = gettingPower;
/*     */     }
/*     */     
/*  49 */     if (this.field_145850_b.field_72995_K) {
/*  50 */       if ((!this.allowFlow) && (this.rotation < 360.0F)) {
/*  51 */         this.rotation += 20.0F;
/*     */       }
/*  53 */       else if ((this.allowFlow) && (this.rotation > 0.0F)) {
/*  54 */         this.rotation -= 20.0F;
/*     */       }
/*     */     }
/*     */     
/*  58 */     super.func_73660_a();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean onWandRightClick(World world, ItemStack wandstack, EntityPlayer player, BlockPos bp, EnumFacing side)
/*     */   {
/*  65 */     MovingObjectPosition hit = RayTracer.retraceBlock(world, player, this.field_174879_c);
/*  66 */     if (hit == null) { return false;
/*     */     }
/*  68 */     if ((hit.subHit >= 0) && (hit.subHit < 6))
/*     */     {
/*  70 */       player.field_70170_p.func_72980_b(this.field_174879_c.func_177958_n(), this.field_174879_c.func_177956_o(), this.field_174879_c.func_177952_p(), "thaumcraft:tool", 0.5F, 0.9F + player.field_70170_p.field_73012_v.nextFloat() * 0.2F, false);
/*  71 */       player.func_71038_i();
/*  72 */       func_70296_d();
/*  73 */       world.func_175689_h(this.field_174879_c);
/*  74 */       this.openSides[hit.subHit] = (this.openSides[hit.subHit] == 0 ? 1 : false);
/*  75 */       EnumFacing dir = EnumFacing.field_82609_l[hit.subHit];
/*  76 */       TileEntity tile = this.field_145850_b.func_175625_s(this.field_174879_c.func_177972_a(dir));
/*  77 */       if ((tile != null) && ((tile instanceof TileTube))) {
/*  78 */         ((TileTube)tile).openSides[dir.func_176734_d().ordinal()] = this.openSides[hit.subHit];
/*  79 */         world.func_175689_h(this.field_174879_c.func_177972_a(dir));
/*  80 */         tile.func_70296_d();
/*     */       }
/*     */     }
/*     */     
/*  84 */     if (hit.subHit == 6)
/*     */     {
/*  86 */       player.field_70170_p.func_72980_b(this.field_174879_c.func_177958_n(), this.field_174879_c.func_177956_o(), this.field_174879_c.func_177952_p(), "thaumcraft:tool", 0.5F, 0.9F + player.field_70170_p.field_73012_v.nextFloat() * 0.2F, false);
/*  87 */       player.func_71038_i();
/*  88 */       int a = this.facing.ordinal();
/*  89 */       func_70296_d();
/*  90 */       do { a++; if (a >= 20) break;
/*  91 */       } while (canConnectSide(EnumFacing.field_82609_l[(a % 6)]));
/*  92 */       a %= 6;
/*  93 */       this.facing = EnumFacing.field_82609_l[a];
/*  94 */       world.func_175689_h(this.field_174879_c);
/*  95 */       func_70296_d();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 102 */     return !world.field_72995_K;
/*     */   }
/*     */   
/*     */ 
/*     */   public void readCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/* 108 */     super.readCustomNBT(nbttagcompound);
/* 109 */     this.allowFlow = nbttagcompound.func_74767_n("flow");
/* 110 */     this.wasPoweredLastTick = nbttagcompound.func_74767_n("hadpower");
/*     */   }
/*     */   
/*     */ 
/*     */   public void writeCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/* 116 */     super.writeCustomNBT(nbttagcompound);
/* 117 */     nbttagcompound.func_74757_a("flow", this.allowFlow);
/* 118 */     nbttagcompound.func_74757_a("hadpower", this.wasPoweredLastTick);
/*     */   }
/*     */   
/*     */   public boolean isConnectable(EnumFacing face)
/*     */   {
/* 123 */     return (face != this.facing) && (super.isConnectable(face));
/*     */   }
/*     */   
/*     */   public void setSuction(Aspect aspect, int amount)
/*     */   {
/* 128 */     if (this.allowFlow) super.setSuction(aspect, amount);
/*     */   }
/*     */   
/*     */   public boolean gettingPower() {
/* 132 */     return this.field_145850_b.func_175687_A(this.field_174879_c) > 0;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/tiles/essentia/TileTubeValve.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */