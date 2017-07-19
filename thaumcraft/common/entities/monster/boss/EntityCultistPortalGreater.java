/*     */ package thaumcraft.common.entities.monster.boss;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
/*     */ import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.common.entities.monster.cult.EntityCultist;
/*     */ import thaumcraft.common.entities.monster.cult.EntityCultistCleric;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.fx.PacketFXBlockArc;
/*     */ import thaumcraft.common.lib.utils.EntityUtils;
/*     */ import thaumcraft.common.tiles.misc.TileBanner;
/*     */ 
/*     */ public class EntityCultistPortalGreater extends EntityMob implements net.minecraft.entity.boss.IBossDisplayData
/*     */ {
/*     */   public EntityCultistPortalGreater(World par1World)
/*     */   {
/*  38 */     super(par1World);
/*  39 */     this.field_70178_ae = true;
/*  40 */     this.field_70728_aV = 30;
/*  41 */     func_70105_a(1.5F, 3.0F);
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_70658_aO()
/*     */   {
/*  47 */     return 5;
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70088_a()
/*     */   {
/*  53 */     super.func_70088_a();
/*     */   }
/*     */   
/*  56 */   int stage = 0;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70014_b(NBTTagCompound nbt)
/*     */   {
/*  64 */     super.func_70014_b(nbt);
/*  65 */     nbt.func_74768_a("stage", this.stage);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound nbt)
/*     */   {
/*  75 */     super.func_70037_a(nbt);
/*  76 */     this.stage = nbt.func_74762_e("stage");
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_110147_ax()
/*     */   {
/*  82 */     super.func_110147_ax();
/*  83 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(500.0D);
/*  84 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(0.0D);
/*  85 */     func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0D);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70067_L()
/*     */   {
/*  91 */     return true;
/*     */   }
/*     */   
/*     */   public boolean func_70104_M()
/*     */   {
/*  96 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70091_d(double par1, double par3, double par5) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70636_d() {}
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_70112_a(double par1)
/*     */   {
/* 113 */     return par1 < 4096.0D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_70070_b(float par1)
/*     */   {
/* 121 */     return 15728880;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public float func_70013_c(float par1)
/*     */   {
/* 130 */     return 1.0F;
/*     */   }
/*     */   
/* 133 */   int stagecounter = 200;
/*     */   
/*     */   public void func_70071_h_()
/*     */   {
/* 137 */     super.func_70071_h_();
/* 138 */     if (!this.field_70170_p.field_72995_K) {
/* 139 */       if (this.stagecounter > 0) {
/* 140 */         this.stagecounter -= 1;
/* 141 */         if ((this.stagecounter == 160) && (this.stage == 0)) {
/* 142 */           this.field_70170_p.func_72960_a(this, (byte)16);
/* 143 */           for (EnumFacing dir : EnumFacing.field_176754_o) {
/* 144 */             BlockPos bp = new BlockPos((int)this.field_70165_t - dir.func_82601_c() * 6, (int)this.field_70163_u, (int)this.field_70161_v + dir.func_82599_e() * 6);
/* 145 */             this.field_70170_p.func_180501_a(bp, BlocksTC.banner.func_176203_a(1), 3);
/* 146 */             TileEntity te = this.field_70170_p.func_175625_s(new BlockPos((int)this.field_70165_t - dir.func_82601_c() * 6, (int)this.field_70163_u, (int)this.field_70161_v + dir.func_82599_e() * 6));
/* 147 */             if ((te != null) && ((te instanceof TileBanner))) {
/* 148 */               int face = 0;
/*     */               
/* 150 */               switch (dir.ordinal()) {
/* 151 */               case 2:  face = 8; break;
/* 152 */               case 3:  face = 0; break;
/* 153 */               case 4:  face = 12; break;
/* 154 */               case 5:  face = 4; break;
/*     */               }
/* 156 */               ((TileBanner)te).setBannerFacing((byte)face);
/* 157 */               PacketHandler.INSTANCE.sendToAllAround(new PacketFXBlockArc(new BlockPos((int)this.field_70165_t - dir.func_82601_c() * 6, (int)this.field_70163_u, (int)this.field_70161_v + dir.func_82599_e() * 6), this, 0.5F + this.field_70146_Z.nextFloat() * 0.2F, 0.0F, 0.0F), new NetworkRegistry.TargetPoint(this.field_70170_p.field_73011_w.func_177502_q(), this.field_70165_t, this.field_70163_u, this.field_70161_v, 32.0D));
/*     */               
/*     */ 
/* 160 */               func_85030_a("thaumcraft:wandfail", 1.0F, 1.0F);
/*     */             }
/*     */           }
/*     */         }
/*     */         
/* 165 */         if ((this.stagecounter > 20) && (this.stagecounter < 150) && (this.stage == 0) && (this.stagecounter % 13 == 0)) {
/* 166 */           int a = (int)this.field_70165_t + this.field_70146_Z.nextInt(5) - this.field_70146_Z.nextInt(5);
/* 167 */           int b = (int)this.field_70161_v + this.field_70146_Z.nextInt(5) - this.field_70146_Z.nextInt(5);
/* 168 */           BlockPos bp = new BlockPos(a, (int)this.field_70163_u, b);
/* 169 */           if ((a != (int)this.field_70165_t) && (b != (int)this.field_70161_v) && (this.field_70170_p.func_175623_d(bp))) {
/* 170 */             this.field_70170_p.func_72960_a(this, (byte)16);
/* 171 */             float rr = this.field_70170_p.field_73012_v.nextFloat();
/* 172 */             int md = rr < 0.2F ? 1 : rr < 0.05F ? 2 : 0;
/* 173 */             this.field_70170_p.func_175656_a(bp, BlocksTC.lootCrate.func_176203_a(md));
/* 174 */             PacketHandler.INSTANCE.sendToAllAround(new PacketFXBlockArc(new BlockPos(a, (int)this.field_70163_u, b), this, 0.5F + this.field_70146_Z.nextFloat() * 0.2F, 0.0F, 0.0F), new NetworkRegistry.TargetPoint(this.field_70170_p.field_73011_w.func_177502_q(), this.field_70165_t, this.field_70163_u, this.field_70161_v, 32.0D));
/*     */             
/*     */ 
/* 177 */             func_85030_a("thaumcraft:wandfail", 1.0F, 1.0F);
/*     */           }
/*     */         }
/*     */       }
/* 181 */       else if (this.field_70170_p.func_72890_a(this, 48.0D) != null)
/*     */       {
/* 183 */         this.field_70170_p.func_72960_a(this, (byte)16);
/*     */         
/* 185 */         switch (this.stage) {
/*     */         case 0: case 1: case 2: case 3: case 4: 
/* 187 */           this.stagecounter = (15 + this.field_70146_Z.nextInt(10 - this.stage) - this.stage);
/* 188 */           spawnMinions();
/* 189 */           break;
/*     */         case 12: 
/* 191 */           this.stagecounter = (50 + getTiming() * 2 + this.field_70146_Z.nextInt(50));
/* 192 */           spawnBoss();
/* 193 */           break;
/*     */         case 5: case 6: case 7: case 8: case 9: case 10: case 11: default: 
/* 195 */           int t = getTiming();
/* 196 */           this.stagecounter = (t + this.field_70146_Z.nextInt(5 + t / 3));
/* 197 */           spawnMinions();
/*     */         }
/*     */         
/*     */         
/* 201 */         this.stage += 1;
/*     */       }
/*     */       else {
/* 204 */         this.stagecounter = (30 + this.field_70146_Z.nextInt(30));
/*     */       }
/*     */       
/* 207 */       if (this.stage < 12) { func_70691_i(1.0F);
/*     */       }
/*     */     }
/*     */     
/* 211 */     if (this.pulse > 0) this.pulse -= 1;
/*     */   }
/*     */   
/*     */   int getTiming() {
/* 215 */     List<Entity> l = EntityUtils.getEntitiesInRange(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, this, EntityCultist.class, 32.0D);
/* 216 */     return l.size() * 20;
/*     */   }
/*     */   
/*     */   void spawnMinions() {
/* 220 */     EntityCultist cultist = null;
/* 221 */     if (this.field_70146_Z.nextFloat() > 0.33D) {
/* 222 */       cultist = new thaumcraft.common.entities.monster.cult.EntityCultistKnight(this.field_70170_p);
/*     */     } else {
/* 224 */       cultist = new EntityCultistCleric(this.field_70170_p);
/*     */     }
/* 226 */     cultist.func_70107_b(this.field_70165_t + this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat(), this.field_70163_u + 0.25D, this.field_70161_v + this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat());
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 231 */     cultist.func_180482_a(this.field_70170_p.func_175649_E(new BlockPos(cultist.func_180425_c())), (IEntityLivingData)null);
/*     */     
/* 233 */     cultist.func_175449_a(func_180425_c(), 32);
/* 234 */     this.field_70170_p.func_72838_d(cultist);
/* 235 */     cultist.func_70656_aK();
/* 236 */     cultist.func_85030_a("thaumcraft:wandfail", 1.0F, 1.0F);
/*     */     
/* 238 */     if (this.stage > 12) {
/* 239 */       func_70097_a(DamageSource.field_76380_i, 5 + this.field_70146_Z.nextInt(5));
/*     */     }
/*     */   }
/*     */   
/*     */   void spawnBoss() {
/* 244 */     EntityCultistLeader cultist = new EntityCultistLeader(this.field_70170_p);
/* 245 */     cultist.func_70107_b(this.field_70165_t + this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat(), this.field_70163_u + 0.25D, this.field_70161_v + this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat());
/*     */     
/*     */ 
/*     */ 
/* 249 */     cultist.func_180482_a(this.field_70170_p.func_175649_E(new BlockPos(cultist.func_180425_c())), (IEntityLivingData)null);
/* 250 */     cultist.func_175449_a(func_180425_c(), 32);
/*     */     
/* 252 */     this.field_70170_p.func_72838_d(cultist);
/* 253 */     cultist.func_70656_aK();
/* 254 */     cultist.func_85030_a("thaumcraft:wandfail", 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */   public void func_70100_b_(EntityPlayer p)
/*     */   {
/* 259 */     if ((func_70068_e(p) < 3.0D) && (p.func_70097_a(DamageSource.func_76354_b(this, this), 8.0F)))
/*     */     {
/* 261 */       func_85030_a("thaumcraft:zap", 1.0F, (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.1F + 1.0F);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   protected float func_70599_aP()
/*     */   {
/* 268 */     return 0.75F;
/*     */   }
/*     */   
/*     */   public int func_70627_aG()
/*     */   {
/* 273 */     return 540;
/*     */   }
/*     */   
/*     */   protected String func_70639_aQ()
/*     */   {
/* 278 */     return "thaumcraft:monolith";
/*     */   }
/*     */   
/*     */ 
/*     */   protected String func_70621_aR()
/*     */   {
/* 284 */     return "thaumcraft:zap";
/*     */   }
/*     */   
/*     */   protected String func_70673_aS()
/*     */   {
/* 289 */     return "thaumcraft:shock";
/*     */   }
/*     */   
/*     */ 
/*     */   protected Item func_146068_u()
/*     */   {
/* 295 */     return Item.func_150899_d(0);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70628_a(boolean flag, int fortune)
/*     */   {
/* 301 */     EntityUtils.entityDropSpecialItem(this, new net.minecraft.item.ItemStack(ItemsTC.primordialPearl, 1 + this.field_70146_Z.nextInt(2)), this.field_70131_O / 2.0F);
/*     */   }
/*     */   
/* 304 */   public int pulse = 0;
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_70103_a(byte msg)
/*     */   {
/* 310 */     if (msg == 16)
/*     */     {
/* 312 */       this.pulse = 10;
/*     */     }
/*     */     else
/*     */     {
/* 316 */       super.func_70103_a(msg);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70690_d(PotionEffect p_70690_1_) {}
/*     */   
/*     */ 
/*     */   public void func_180430_e(float distance, float damageMultiplier) {}
/*     */   
/*     */ 
/*     */   public void func_70645_a(DamageSource p_70645_1_)
/*     */   {
/* 329 */     if (!this.field_70170_p.field_72995_K) {
/* 330 */       this.field_70170_p.func_72885_a(this, this.field_70165_t, this.field_70163_u, this.field_70161_v, 2.0F, false, false);
/*     */     }
/*     */     
/* 333 */     super.func_70645_a(p_70645_1_);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/monster/boss/EntityCultistPortalGreater.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */