/*     */ package thaumcraft.common.entities.construct;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityLookHelper;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.scoreboard.Team;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.client.FMLClientHandler;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aura.AuraHelper;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.api.research.ResearchHelper;
/*     */ import thaumcraft.api.wands.IWand;
/*     */ import thaumcraft.client.fx.other.FXSonic;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.entities.monster.EntityWisp;
/*     */ import thaumcraft.common.lib.aura.AuraHandler;
/*     */ import thaumcraft.common.lib.aura.EntityAuraNode;
/*     */ import thaumcraft.common.lib.utils.EntityUtils;
/*     */ 
/*     */ public class EntityNodeMagnet extends EntityOwnedConstruct
/*     */ {
/*  35 */   EntityAuraNode nodeTarget = null;
/*     */   
/*     */   public EntityNodeMagnet(World worldIn) {
/*  38 */     super(worldIn);
/*  39 */     func_70105_a(0.9F, 0.9F);
/*     */   }
/*     */   
/*     */   public EntityNodeMagnet(World worldIn, BlockPos pos) {
/*  43 */     this(worldIn);
/*  44 */     func_70080_a(pos.func_177958_n() + 0.5D, pos.func_177956_o(), pos.func_177952_p() + 0.5D, 0.0F, 0.0F);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70088_a()
/*     */   {
/*  50 */     super.func_70088_a();
/*  51 */     this.field_70180_af.func_75682_a(19, Byte.valueOf((byte)0));
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_142014_c(EntityLivingBase otherEntity)
/*     */   {
/*  57 */     return func_142012_a(otherEntity.func_96124_cp());
/*     */   }
/*     */   
/*     */ 
/*     */   public Team func_96124_cp()
/*     */   {
/*  63 */     if (isOwned())
/*     */     {
/*  65 */       EntityLivingBase entitylivingbase = getOwnerEntity();
/*     */       
/*  67 */       if (entitylivingbase != null)
/*     */       {
/*  69 */         return entitylivingbase.func_96124_cp();
/*     */       }
/*     */     }
/*     */     
/*  73 */     return super.func_96124_cp();
/*     */   }
/*     */   
/*     */ 
/*     */   public float func_70047_e()
/*     */   {
/*  79 */     return 0.8125F;
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_110147_ax()
/*     */   {
/*  85 */     super.func_110147_ax();
/*  86 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(15.0D);
/*  87 */     func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(32.0D);
/*     */   }
/*     */   
/*     */   public void func_70071_h_()
/*     */   {
/*  92 */     super.func_70071_h_();
/*     */     
/*  94 */     if (!this.field_70170_p.field_72995_K) {
/*  95 */       this.field_70177_z = this.field_70759_as;
/*     */       
/*  97 */       if (this.field_70173_aa % 50 == 0) {
/*  98 */         func_70691_i(1.0F);
/*     */         
/* 100 */         ArrayList<Entity> list = EntityUtils.getEntitiesInRange(this.field_70170_p, func_180425_c(), this, EntityMob.class, 32.0D);
/* 101 */         if (list != null) {
/* 102 */           for (Entity e : list) {
/* 103 */             EntityMob mob = (EntityMob)e;
/* 104 */             if ((this.field_70146_Z.nextBoolean()) && 
/* 105 */               (mob.func_70638_az() == null)) {
/* 106 */               mob.func_70624_b(this);
/*     */             }
/*     */           }
/*     */         }
/*     */         
/*     */ 
/* 112 */         if ((getOwnerEntity() != null) && ((getOwnerEntity() instanceof EntityPlayer)) && (!ResearchHelper.isResearchComplete(getOwnerEntity().func_70005_c_(), "!NODEMAGNETDANGER")))
/*     */         {
/* 114 */           ResearchHelper.completeResearch((EntityPlayer)getOwnerEntity(), "!NODEMAGNETDANGER");
/*     */         }
/*     */       }
/*     */       
/* 118 */       if ((this.field_70173_aa % 10 == 0) && (getCharge() < 10)) {
/* 119 */         rechargeVis();
/*     */       }
/*     */     }
/*     */     
/* 123 */     if ((this.nodeTarget != null) && (this.nodeTarget.field_70128_L)) {
/* 124 */       this.nodeTarget = null;
/*     */     }
/*     */     
/* 127 */     if ((this.nodeTarget != null) && (getCharge() > 0)) {
/* 128 */       if (!this.field_70170_p.field_72995_K) {
/* 129 */         func_70671_ap().func_75650_a(this.nodeTarget.field_70165_t, this.nodeTarget.field_70163_u + this.nodeTarget.func_70047_e(), this.nodeTarget.field_70161_v, 10.0F, func_70646_bf());
/*     */ 
/*     */ 
/*     */       }
/* 133 */       else if (this.field_70173_aa % 10 == 0) {
/* 134 */         showFX();
/*     */       }
/*     */       
/* 137 */       double gap = func_70032_d(this.nodeTarget);
/* 138 */       if ((gap >= 1.0D) && (gap <= 32.0D) && (!this.nodeTarget.stablized)) {
/* 139 */         double mx = this.field_70165_t - this.nodeTarget.field_70165_t;
/* 140 */         double my = this.field_70163_u - this.nodeTarget.field_70163_u;
/* 141 */         double mz = this.field_70161_v - this.nodeTarget.field_70161_v;
/* 142 */         mx /= gap * (200 + this.nodeTarget.getNodeSize() * 3);
/* 143 */         my /= gap * (200 + this.nodeTarget.getNodeSize() * 3);
/* 144 */         mz /= gap * (200 + this.nodeTarget.getNodeSize() * 3);
/* 145 */         this.nodeTarget.field_70159_w += mx;
/* 146 */         this.nodeTarget.field_70181_x += my;
/* 147 */         this.nodeTarget.field_70179_y += mz;
/* 148 */         if (!this.field_70170_p.field_72995_K) {
/* 149 */           setCharge((byte)(getCharge() - 1));
/* 150 */           if (this.field_70146_Z.nextFloat() < 0.01F)
/* 151 */             if (this.field_70146_Z.nextFloat() < 0.2F) {
/* 152 */               EntityWisp wisp = new EntityWisp(this.field_70170_p);
/* 153 */               wisp.func_70012_b(this.nodeTarget.field_70165_t, this.nodeTarget.field_70163_u, this.nodeTarget.field_70161_v, 0.0F, 0.0F);
/* 154 */               wisp.setType(this.nodeTarget.getAspectTag());
/* 155 */               this.field_70170_p.func_72838_d(wisp);
/*     */             } else {
/* 157 */               AuraHelper.pollute(this.field_70170_p, this.nodeTarget.func_180425_c(), 1, true);
/*     */             }
/*     */         }
/* 160 */         this.rs += 0.36F;
/*     */       } else {
/* 162 */         this.nodeTarget = null;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 168 */     if (this.nodeTarget == null) {
/* 169 */       this.rs -= 0.5F;
/*     */     }
/*     */     
/* 172 */     if ((this.nodeTarget == null) && (this.field_70173_aa % 20 == 0)) {
/* 173 */       ArrayList<Entity> list = EntityUtils.getEntitiesInRange(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, this, EntityAuraNode.class, 32.0D);
/* 174 */       Entity closest = null;
/* 175 */       double d = Double.MAX_VALUE;
/* 176 */       for (Entity e : list)
/* 177 */         if (!((EntityAuraNode)e).stablized) {
/* 178 */           double gap = func_70068_e(e);
/* 179 */           if (gap < d) {
/* 180 */             d = gap;
/* 181 */             closest = e;
/*     */           }
/*     */         }
/* 184 */       if (closest != null) {
/* 185 */         this.nodeTarget = ((EntityAuraNode)closest);
/*     */       }
/*     */     }
/*     */     
/* 189 */     if (this.rs > 36.0F) this.rs = 36.0F;
/* 190 */     if (this.rs < 0.0F) { this.rs = 0.0F;
/*     */     }
/* 192 */     this.rot += this.rs;
/* 193 */     if (this.rot > 360.0F) {
/* 194 */       this.rot -= 360.0F;
/* 195 */       if (this.field_70170_p.field_72995_K) this.field_70170_p.func_72980_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, "thaumcraft:pump", 0.7F, 1.0F, false);
/*     */     }
/*     */   }
/*     */   
/* 199 */   float rs = 0.0F;
/* 200 */   public float rot = 0.0F;
/*     */   
/*     */   @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*     */   private void showFX() {
/* 204 */     FXSonic fb = new FXSonic(Thaumcraft.proxy.getClientWorld(), this.nodeTarget.field_70165_t, this.nodeTarget.field_70163_u, this.nodeTarget.field_70161_v, this, 10);
/* 205 */     FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(fb);
/*     */   }
/*     */   
/*     */   public boolean func_70097_a(DamageSource source, float amount)
/*     */   {
/* 210 */     this.field_70177_z = ((float)(this.field_70177_z + func_70681_au().nextGaussian() * 45.0D));
/* 211 */     this.field_70125_A = ((float)(this.field_70125_A + func_70681_au().nextGaussian() * 20.0D));
/* 212 */     return super.func_70097_a(source, amount);
/*     */   }
/*     */   
/*     */   protected void rechargeVis() {
/* 216 */     for (Aspect aspect : ) {
/* 217 */       if (AuraHandler.drainAura(this.field_70170_p, func_180425_c(), aspect, 1)) {
/* 218 */         setCharge((byte)(getCharge() + 3));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70104_M()
/*     */   {
/* 226 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70067_L()
/*     */   {
/* 232 */     return true;
/*     */   }
/*     */   
/*     */   public boolean func_70085_c(EntityPlayer player)
/*     */   {
/* 237 */     if ((player.func_70093_af()) || ((player.func_70694_bm() != null) && ((player.func_70694_bm().func_77973_b() instanceof net.minecraft.item.ItemNameTag)))) return false;
/* 238 */     if ((!this.field_70170_p.field_72995_K) && (isOwner(player)) && (!this.field_70128_L)) {
/* 239 */       if ((player.func_70694_bm() != null) && ((player.func_70694_bm().func_77973_b() instanceof IWand))) {
/* 240 */         func_85030_a("thaumcraft:zap", 1.0F, 1.0F);
/* 241 */         func_70099_a(new ItemStack(ItemsTC.turretPlacer, 1, 2), 0.5F);
/* 242 */         func_70106_y();
/* 243 */         player.func_71038_i();
/*     */       }
/* 245 */       return true;
/*     */     }
/*     */     
/* 248 */     return super.func_70085_c(player);
/*     */   }
/*     */   
/*     */   public void func_70653_a(Entity p_70653_1_, float p_70653_2_, double p_70653_3_, double p_70653_5_)
/*     */   {
/* 253 */     super.func_70653_a(p_70653_1_, p_70653_2_, p_70653_3_ / 10.0D, p_70653_5_ / 10.0D);
/* 254 */     if (this.field_70181_x > 0.1D) { this.field_70181_x = 0.1D;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound nbt)
/*     */   {
/* 262 */     super.func_70037_a(nbt);
/* 263 */     this.field_70180_af.func_75692_b(19, Byte.valueOf(nbt.func_74771_c("charge")));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_70014_b(NBTTagCompound nbt)
/*     */   {
/* 270 */     super.func_70014_b(nbt);
/* 271 */     nbt.func_74774_a("charge", this.field_70180_af.func_75683_a(19));
/*     */   }
/*     */   
/*     */   public byte getCharge()
/*     */   {
/* 276 */     return this.field_70180_af.func_75683_a(19);
/*     */   }
/*     */   
/*     */   public void setCharge(byte c) {
/* 280 */     this.field_70180_af.func_75692_b(19, Byte.valueOf(c));
/*     */   }
/*     */   
/*     */   public void func_70091_d(double x, double y, double z)
/*     */   {
/* 285 */     super.func_70091_d(x / 5.0D, y, z / 5.0D);
/* 286 */     float c = (float)((Math.abs(this.field_70159_w) + Math.abs(this.field_70181_x) + Math.abs(this.field_70179_y)) / 3.0D);
/* 287 */     if (c > 0.25D) { func_70076_C();
/* 288 */     } else if (this.field_70146_Z.nextFloat() < c / 20.0F) { func_70076_C();
/*     */     }
/*     */   }
/*     */   
/*     */   protected void func_70076_C()
/*     */   {
/* 294 */     func_70097_a(DamageSource.field_76380_i, 40.0F);
/*     */   }
/*     */   
/*     */   protected void func_70628_a(boolean p_70628_1_, int p_70628_2_)
/*     */   {
/* 299 */     float b = p_70628_2_ * 0.15F;
/* 300 */     if (this.field_70146_Z.nextFloat() < 0.2F + b) func_70099_a(new ItemStack(ItemsTC.mind, 1, 1), 0.5F);
/* 301 */     if (this.field_70146_Z.nextFloat() < 0.2F + b) func_70099_a(new ItemStack(ItemsTC.morphicResonator), 0.5F);
/* 302 */     if (this.field_70146_Z.nextFloat() < 0.2F + b) func_70099_a(new ItemStack(BlocksTC.crystalAir), 0.5F);
/* 303 */     if (this.field_70146_Z.nextFloat() < 0.2F + b) func_70099_a(new ItemStack(BlocksTC.crystalFire), 0.5F);
/* 304 */     if (this.field_70146_Z.nextFloat() < 0.2F + b) func_70099_a(new ItemStack(BlocksTC.crystalWater), 0.5F);
/* 305 */     if (this.field_70146_Z.nextFloat() < 0.2F + b) func_70099_a(new ItemStack(BlocksTC.crystalEarth), 0.5F);
/* 306 */     if (this.field_70146_Z.nextFloat() < 0.2F + b) func_70099_a(new ItemStack(BlocksTC.crystalOrder), 0.5F);
/* 307 */     if (this.field_70146_Z.nextFloat() < 0.2F + b) func_70099_a(new ItemStack(BlocksTC.crystalEntropy), 0.5F);
/* 308 */     if (this.field_70146_Z.nextFloat() < 0.2F + b) func_70099_a(new ItemStack(BlocksTC.crystalTaint), 0.5F);
/* 309 */     if (this.field_70146_Z.nextFloat() < 0.5F + b) func_70099_a(new ItemStack(ItemsTC.gear), 0.5F);
/* 310 */     if (this.field_70146_Z.nextFloat() < 0.5F + b) func_70099_a(new ItemStack(ItemsTC.plate), 0.5F);
/* 311 */     if (this.field_70146_Z.nextFloat() < 0.5F + b) { func_70099_a(new ItemStack(BlocksTC.plank), 0.5F);
/*     */     }
/*     */   }
/*     */   
/*     */   public int func_70646_bf()
/*     */   {
/* 317 */     return 20;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/construct/EntityNodeMagnet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */