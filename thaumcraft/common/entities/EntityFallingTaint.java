/*     */ package thaumcraft.common.entities;
/*     */ 
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.crash.CrashReportCategory;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ 
/*     */ public class EntityFallingTaint extends Entity implements net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData
/*     */ {
/*     */   public IBlockState fallTile;
/*     */   BlockPos oldPos;
/*     */   public int fallTime;
/*     */   private int fallHurtMax;
/*     */   private float fallHurtAmount;
/*     */   
/*     */   public IBlockState getBlock()
/*     */   {
/*  26 */     return this.fallTile;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public EntityFallingTaint(World par1World)
/*     */   {
/*  42 */     super(par1World);
/*  43 */     this.fallTime = 0;
/*  44 */     this.fallHurtMax = 40;
/*  45 */     this.fallHurtAmount = 2.0F;
/*     */   }
/*     */   
/*     */   public EntityFallingTaint(World par1World, double par2, double par4, double par6, IBlockState par8, BlockPos o)
/*     */   {
/*  50 */     super(par1World);
/*  51 */     this.fallTime = 0;
/*  52 */     this.fallHurtMax = 40;
/*  53 */     this.fallHurtAmount = 2.0F;
/*  54 */     this.fallTile = par8;
/*  55 */     this.field_70156_m = true;
/*  56 */     func_70105_a(0.98F, 0.98F);
/*  57 */     func_70107_b(par2, par4, par6);
/*  58 */     this.field_70159_w = 0.0D;
/*  59 */     this.field_70181_x = 0.0D;
/*  60 */     this.field_70179_y = 0.0D;
/*  61 */     this.field_70169_q = par2;
/*  62 */     this.field_70167_r = par4;
/*  63 */     this.field_70166_s = par6;
/*  64 */     this.oldPos = o;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean func_70041_e_()
/*     */   {
/*  74 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void func_70088_a() {}
/*     */   
/*     */ 
/*     */   public void writeSpawnData(ByteBuf data)
/*     */   {
/*  84 */     data.writeInt(Block.func_149682_b(this.fallTile.func_177230_c()));
/*  85 */     data.writeByte(this.fallTile.func_177230_c().func_176201_c(this.fallTile));
/*     */   }
/*     */   
/*     */   public void readSpawnData(ByteBuf data)
/*     */   {
/*     */     try {
/*  91 */       this.fallTile = Block.func_149729_e(data.readInt()).func_176203_a(data.readByte());
/*     */     }
/*     */     catch (Exception e) {}
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_70067_L()
/*     */   {
/* 102 */     return !this.field_70128_L;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/* 111 */     if ((this.fallTile == null) || (this.fallTile == Blocks.field_150350_a))
/*     */     {
/* 113 */       func_70106_y();
/*     */     }
/*     */     else
/*     */     {
/* 117 */       this.field_70169_q = this.field_70165_t;
/* 118 */       this.field_70167_r = this.field_70163_u;
/* 119 */       this.field_70166_s = this.field_70161_v;
/* 120 */       this.fallTime += 1;
/* 121 */       this.field_70181_x -= 0.03999999910593033D;
/* 122 */       func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
/* 123 */       this.field_70159_w *= 0.9800000190734863D;
/* 124 */       this.field_70181_x *= 0.9800000190734863D;
/* 125 */       this.field_70179_y *= 0.9800000190734863D;
/*     */       
/* 127 */       BlockPos bp = new BlockPos(this);
/*     */       
/* 129 */       if (!this.field_70170_p.field_72995_K)
/*     */       {
/*     */ 
/*     */ 
/* 133 */         if (this.fallTime == 1)
/*     */         {
/*     */ 
/* 136 */           if (this.field_70170_p.func_180495_p(this.oldPos) != this.fallTile)
/*     */           {
/* 138 */             func_70106_y();
/* 139 */             return;
/*     */           }
/*     */           
/* 142 */           this.field_70170_p.func_175698_g(this.oldPos);
/*     */         }
/*     */         
/* 145 */         if ((this.field_70122_E) || (this.field_70170_p.func_180495_p(bp.func_177977_b()) == BlocksTC.fluxGoo))
/*     */         {
/* 147 */           this.field_70159_w *= 0.699999988079071D;
/* 148 */           this.field_70179_y *= 0.699999988079071D;
/* 149 */           this.field_70181_x *= -0.5D;
/*     */           
/* 151 */           if ((this.field_70170_p.func_180495_p(bp).func_177230_c() != Blocks.field_150331_J) && (this.field_70170_p.func_180495_p(bp).func_177230_c() != Blocks.field_180384_M) && (this.field_70170_p.func_180495_p(bp).func_177230_c() != Blocks.field_150332_K))
/*     */           {
/*     */ 
/*     */ 
/* 155 */             this.field_70170_p.func_72956_a(this, "thaumcraft:gore", 0.5F, ((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F + 1.0F) * 0.8F);
/* 156 */             func_70106_y();
/*     */             
/* 158 */             if ((!canPlace(bp)) || (thaumcraft.common.blocks.world.taint.BlockTaint.canFallBelow(this.field_70170_p, bp.func_177977_b())) || (!this.field_70170_p.func_175656_a(bp, this.fallTile))) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           }
/*     */           
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         }
/* 171 */         else if (((this.fallTime > 100) && (!this.field_70170_p.field_72995_K) && ((bp.func_177956_o() < 1) || (bp.func_177956_o() > 256))) || (this.fallTime > 600))
/*     */         {
/*     */ 
/* 174 */           func_70106_y();
/*     */         }
/*     */       }
/* 177 */       else if ((this.field_70122_E) || (this.fallTime == 1))
/*     */       {
/* 179 */         for (int j = 0; j < 10; j++)
/*     */         {
/* 181 */           thaumcraft.common.Thaumcraft.proxy.getFX().taintLandFX(this);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private boolean canPlace(BlockPos pos)
/*     */   {
/* 190 */     return (this.field_70170_p.func_180495_p(pos).func_177230_c() == BlocksTC.taintFibre) || (this.field_70170_p.func_180495_p(pos).func_177230_c() == BlocksTC.fluxGoo) || (this.field_70170_p.func_175716_a(this.fallTile.func_177230_c(), pos, true, net.minecraft.util.EnumFacing.UP, (Entity)null, (net.minecraft.item.ItemStack)null));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_180430_e(float distance, float damageMultiplier) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_70014_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 206 */     Block block = this.fallTile != null ? this.fallTile.func_177230_c() : Blocks.field_150350_a;
/* 207 */     ResourceLocation resourcelocation = (ResourceLocation)Block.field_149771_c.func_177774_c(block);
/* 208 */     par1NBTTagCompound.func_74778_a("Block", resourcelocation == null ? "" : resourcelocation.toString());
/* 209 */     par1NBTTagCompound.func_74774_a("Data", (byte)block.func_176201_c(this.fallTile));
/*     */     
/* 211 */     par1NBTTagCompound.func_74774_a("Time", (byte)this.fallTime);
/* 212 */     par1NBTTagCompound.func_74776_a("FallHurtAmount", this.fallHurtAmount);
/* 213 */     par1NBTTagCompound.func_74768_a("FallHurtMax", this.fallHurtMax);
/* 214 */     par1NBTTagCompound.func_74772_a("Old", this.oldPos.func_177986_g());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_70037_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 223 */     int i = par1NBTTagCompound.func_74771_c("Data") & 0xFF;
/*     */     
/* 225 */     if (par1NBTTagCompound.func_150297_b("Block", 8))
/*     */     {
/* 227 */       this.fallTile = Block.func_149684_b(par1NBTTagCompound.func_74779_i("Block")).func_176203_a(i);
/*     */     }
/* 229 */     else if (par1NBTTagCompound.func_150297_b("TileID", 99))
/*     */     {
/* 231 */       this.fallTile = Block.func_149729_e(par1NBTTagCompound.func_74762_e("TileID")).func_176203_a(i);
/*     */     }
/*     */     else
/*     */     {
/* 235 */       this.fallTile = Block.func_149729_e(par1NBTTagCompound.func_74771_c("Tile") & 0xFF).func_176203_a(i);
/*     */     }
/*     */     
/* 238 */     this.fallTime = (par1NBTTagCompound.func_74771_c("Time") & 0xFF);
/* 239 */     this.oldPos = BlockPos.func_177969_a(par1NBTTagCompound.func_74763_f("Old"));
/*     */     
/* 241 */     if (par1NBTTagCompound.func_74764_b("HurtEntities"))
/*     */     {
/* 243 */       this.fallHurtAmount = par1NBTTagCompound.func_74760_g("FallHurtAmount");
/* 244 */       this.fallHurtMax = par1NBTTagCompound.func_74762_e("FallHurtMax");
/*     */     }
/*     */     
/*     */ 
/* 248 */     if (this.fallTile == null)
/*     */     {
/* 250 */       this.fallTile = Blocks.field_150354_m.func_176223_P();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_85029_a(CrashReportCategory par1CrashReportCategory)
/*     */   {
/* 257 */     super.func_85029_a(par1CrashReportCategory);
/* 258 */     par1CrashReportCategory.func_71507_a("Immitating block ID", Integer.valueOf(Block.func_149682_b(this.fallTile.func_177230_c())));
/* 259 */     par1CrashReportCategory.func_71507_a("Immitating block data", Integer.valueOf(this.fallTile.func_177230_c().func_176201_c(this.fallTile)));
/*     */   }
/*     */   
/*     */ 
/*     */   @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*     */   public World getWorld()
/*     */   {
/* 266 */     return this.field_70170_p;
/*     */   }
/*     */   
/*     */ 
/*     */   @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*     */   public boolean func_90999_ad()
/*     */   {
/* 273 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/EntityFallingTaint.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */