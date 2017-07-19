/*     */ package thaumcraft.common.tiles.devices;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.tileentity.TileEntityFurnace;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.ITickable;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
/*     */ import net.minecraftforge.fml.relauncher.ReflectionHelper;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.blocks.TileThaumcraft;
/*     */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TileBellows
/*     */   extends TileThaumcraft
/*     */   implements ITickable
/*     */ {
/*     */   @SideOnly(Side.CLIENT)
/*     */   public AxisAlignedBB getRenderBoundingBox()
/*     */   {
/*  28 */     return AxisAlignedBB.func_178781_a(func_174877_v().func_177958_n() - 0.3D, func_174877_v().func_177956_o() - 0.3D, func_174877_v().func_177952_p() - 0.3D, func_174877_v().func_177958_n() + 1.3D, func_174877_v().func_177956_o() + 1.3D, func_174877_v().func_177952_p() + 1.3D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*  33 */   public float inflation = 1.0F;
/*  34 */   boolean direction = false;
/*  35 */   boolean firstrun = true;
/*  36 */   public int delay = 0;
/*     */   
/*     */   public void func_73660_a()
/*     */   {
/*  40 */     if (this.field_145850_b.field_72995_K) {
/*  41 */       if (BlockStateUtils.isEnabled(func_145832_p())) {
/*  42 */         if (this.firstrun)
/*  43 */           this.inflation = (0.35F + this.field_145850_b.field_73012_v.nextFloat() * 0.55F);
/*  44 */         this.firstrun = false;
/*     */         
/*  46 */         if ((this.inflation > 0.35F) && (!this.direction)) this.inflation -= 0.075F;
/*  47 */         if ((this.inflation <= 0.35F) && (!this.direction))
/*     */         {
/*  49 */           this.direction = true;
/*     */         }
/*     */         
/*  52 */         if ((this.inflation < 1.0F) && (this.direction)) this.inflation += 0.025F;
/*  53 */         if ((this.inflation >= 1.0F) && (this.direction)) {
/*  54 */           this.direction = false;
/*  55 */           this.field_145850_b.func_72980_b(this.field_174879_c.func_177958_n(), this.field_174879_c.func_177956_o(), this.field_174879_c.func_177952_p(), "mob.ghast.fireball", 0.01F, 0.5F + (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.2F, false);
/*     */         }
/*     */         
/*     */       }
/*     */     }
/*  60 */     else if (BlockStateUtils.isEnabled(func_145832_p())) {
/*  61 */       this.delay += 1;
/*  62 */       if (this.delay >= 2) {
/*  63 */         this.delay = 0;
/*  64 */         TileEntity tile = this.field_145850_b.func_175625_s(this.field_174879_c.func_177972_a(BlockStateUtils.getFacing(func_145832_p())));
/*  65 */         if ((tile != null) && ((tile instanceof TileEntityFurnace))) {
/*  66 */           TileEntityFurnace tf = (TileEntityFurnace)tile;
/*  67 */           int ct = getCooktime(tf);
/*  68 */           if ((ct > 0) && (ct < 199)) setCooktime(tf, ct + 1);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void setCooktime(TileEntityFurnace ent, int hit)
/*     */   {
/*     */     try {
/*  77 */       ObfuscationReflectionHelper.setPrivateValue(TileEntityFurnace.class, ent, Integer.valueOf(hit), new String[] { "cookTime", "field_174906_k", "k" });
/*     */     } catch (Exception e) {}
/*     */   }
/*     */   
/*     */   public int getCooktime(TileEntityFurnace ent) {
/*     */     try {
/*  83 */       return ((Integer)ReflectionHelper.getPrivateValue(TileEntityFurnace.class, ent, new String[] { "cookTime", "field_174906_k", "k" })).intValue();
/*     */     } catch (Exception e) {}
/*  85 */     return 0;
/*     */   }
/*     */   
/*     */   public static int getBellows(World world, BlockPos pos, EnumFacing[] directions)
/*     */   {
/*  90 */     int bellows = 0;
/*  91 */     for (EnumFacing dir : directions) {
/*  92 */       TileEntity tile = world.func_175625_s(pos.func_177972_a(dir));
/*     */       try {
/*  94 */         if ((tile != null) && ((tile instanceof TileBellows)) && (BlockStateUtils.getFacing(tile.func_145832_p()) == dir.func_176734_d()) && (BlockStateUtils.isEnabled(tile.func_145832_p())))
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/*  99 */           bellows++;
/*     */         }
/*     */       } catch (Exception e) {}
/*     */     }
/* 103 */     return bellows;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/tiles/devices/TileBellows.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */