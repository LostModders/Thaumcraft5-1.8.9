/*     */ package thaumcraft.common.lib.aura;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.profiler.Profiler;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectHelper;
/*     */ import thaumcraft.common.config.Config;
/*     */ 
/*     */ public class EntityAuraNode extends Entity
/*     */ {
/*  22 */   private int tickCounter = -1;
/*     */   
/*     */   public EntityAuraNode(World worldIn) {
/*  25 */     super(worldIn);
/*  26 */     func_70105_a(0.5F, 0.5F);
/*  27 */     this.field_70178_ae = true;
/*  28 */     this.field_70155_l = 4.0D;
/*  29 */     this.field_70145_X = true;
/*     */   }
/*     */   
/*  32 */   int checkDelay = -1;
/*     */   
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/*  37 */     if (getNodeSize() == 0) randomizeNode();
/*  38 */     if (this.tickCounter < 0) { this.tickCounter = this.field_70146_Z.nextInt(200);
/*     */     }
/*  40 */     this.field_70170_p.field_72984_F.func_76320_a("entityBaseTick");
/*     */     
/*  42 */     this.field_70169_q = this.field_70165_t;
/*  43 */     this.field_70167_r = this.field_70163_u;
/*  44 */     this.field_70166_s = this.field_70161_v;
/*     */     
/*  46 */     NodeType.nodeTypes[getNodeType()].performTickEvent(this);
/*     */     
/*  48 */     if (this.tickCounter++ > 200)
/*     */     {
/*  50 */       this.tickCounter = 0;
/*  51 */       NodeType.nodeTypes[getNodeType()].performPeriodicEvent(this);
/*     */     }
/*     */     
/*  54 */     checkAdjacentNodes();
/*     */     
/*  56 */     if ((this.field_70159_w != 0.0D) || (this.field_70181_x != 0.0D) || (this.field_70179_y != 0.0D)) {
/*  57 */       this.field_70159_w *= 0.8D;
/*  58 */       this.field_70181_x *= 0.8D;
/*  59 */       this.field_70179_y *= 0.8D;
/*  60 */       super.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
/*     */     }
/*     */     
/*  63 */     this.field_70170_p.field_72984_F.func_76319_b();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*  68 */   ArrayList<Entity> neighbours = null;
/*  69 */   public boolean stablized = false;
/*     */   
/*     */   private void checkAdjacentNodes() {
/*  72 */     if ((this.neighbours == null) || (this.checkDelay < this.field_70173_aa)) {
/*  73 */       this.neighbours = thaumcraft.common.lib.utils.EntityUtils.getEntitiesInRange(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, this, EntityAuraNode.class, 32.0D);
/*  74 */       this.checkDelay = (this.field_70173_aa + 750);
/*     */     }
/*     */     
/*  77 */     if (!this.stablized) {
/*     */       try {
/*  79 */         Iterator<Entity> i = this.neighbours.iterator();
/*  80 */         while (i.hasNext()) {
/*  81 */           Entity e = (Entity)i.next();
/*  82 */           if ((e == null) || (e.field_70128_L) || (!(e instanceof EntityAuraNode))) {
/*  83 */             i.remove();
/*     */           } else {
/*  85 */             EntityAuraNode an = (EntityAuraNode)e;
/*  86 */             if (!an.stablized) {
/*  87 */               double xd = this.field_70165_t - an.field_70165_t;
/*  88 */               double yd = this.field_70163_u - an.field_70163_u;
/*  89 */               double zd = this.field_70161_v - an.field_70161_v;
/*  90 */               double d = xd * xd + yd * yd + zd * zd;
/*  91 */               if ((d < (getNodeSize() + an.getNodeSize()) * 1.5D) && (d > 0.1D)) {
/*  92 */                 float tq = (getNodeSize() + an.getNodeSize()) * 1.5F;
/*  93 */                 float xm = (float)(-xd / d / tq * (an.getNodeSize() / 50.0D));
/*  94 */                 float ym = (float)(-yd / d / tq * (an.getNodeSize() / 50.0D));
/*  95 */                 float zm = (float)(-zd / d / tq * (an.getNodeSize() / 50.0D));
/*  96 */                 this.field_70159_w += xm;
/*  97 */                 this.field_70181_x += ym;
/*  98 */                 this.field_70179_y += zm;
/*  99 */               } else if ((d <= 0.1D) && (getNodeSize() >= an.getNodeSize()) && (!this.field_70170_p.field_72995_K)) {
/* 100 */                 int bonus = (int)Math.sqrt(an.getNodeSize());
/* 101 */                 int n = getNodeSize() + bonus;
/* 102 */                 setNodeSize(n);
/*     */                 
/* 104 */                 if (this.field_70146_Z.nextInt(100) < Math.sqrt(an.getNodeSize())) {
/* 105 */                   Aspect a = AspectHelper.getCombinationResult(getAspect(), an.getAspect());
/* 106 */                   if (a != null) { setAspectTag(a.getTag());
/*     */                   }
/*     */                 }
/* 109 */                 if (((getNodeType() == 0) && (an.getNodeType() != 0) && (this.field_70146_Z.nextInt(3) == 0)) || ((getNodeType() != 0) && (an.getNodeType() != 0) && (this.field_70146_Z.nextInt(100) < Math.sqrt(an.getNodeSize() / 2))))
/*     */                 {
/* 111 */                   setNodeType(an.getNodeType());
/*     */                 }
/* 113 */                 an.func_70106_y();
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */       catch (Exception e) {}
/*     */     }
/* 121 */     if (this.field_70173_aa % 50 == 0) {
/* 122 */       this.stablized = ((this.field_70170_p.func_180495_p(func_180425_c().func_177977_b()).func_177230_c() == thaumcraft.api.blocks.BlocksTC.nodeStabilizer) && (!this.field_70170_p.func_175640_z(func_180425_c().func_177977_b())));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getNodeSize()
/*     */   {
/* 131 */     return this.field_70180_af.func_75679_c(8);
/*     */   }
/*     */   
/*     */   public void setNodeSize(int p)
/*     */   {
/* 136 */     this.field_70180_af.func_75692_b(8, Integer.valueOf(p));
/*     */   }
/*     */   
/*     */   public String getAspectTag() {
/* 140 */     return this.field_70180_af.func_75681_e(9);
/*     */   }
/*     */   
/*     */   public Aspect getAspect() {
/* 144 */     return Aspect.getAspect(getAspectTag());
/*     */   }
/*     */   
/*     */   public void setAspectTag(String t) {
/* 148 */     this.field_70180_af.func_75692_b(9, String.valueOf(t));
/*     */   }
/*     */   
/*     */   public int getNodeType() {
/* 152 */     return this.field_70180_af.func_75683_a(10);
/*     */   }
/*     */   
/*     */   public void setNodeType(int p)
/*     */   {
/* 157 */     this.field_70180_af.func_75692_b(10, Byte.valueOf((byte)net.minecraft.util.MathHelper.func_76125_a(p, 0, NodeType.nodeTypes.length - 1)));
/*     */   }
/*     */   
/*     */   protected void func_70088_a()
/*     */   {
/* 162 */     this.field_70180_af.func_75682_a(8, Integer.valueOf(0));
/* 163 */     this.field_70180_af.func_75682_a(9, String.valueOf(""));
/* 164 */     this.field_70180_af.func_75682_a(10, Byte.valueOf((byte)0));
/*     */   }
/*     */   
/*     */   public void randomizeNode() {
/* 168 */     setNodeSize(2 + Config.AURABASE / 3 + this.field_70146_Z.nextInt(2 + Config.AURABASE / 3));
/*     */     
/* 170 */     if ((this.field_70146_Z.nextInt(Config.specialNodeRarity) == 0) && (NodeType.nodeTypes.length > 1)) {
/* 171 */       setNodeType(1 + this.field_70146_Z.nextInt(NodeType.nodeTypes.length - 1));
/* 172 */       if ((getNodeType() == 4) && (this.field_70146_Z.nextFloat() < 0.33D)) setNodeType(0);
/* 173 */       if ((!Config.genTaint) && (getNodeType() == 4)) setNodeType(0);
/*     */     } else {
/* 175 */       setNodeType(0);
/*     */     }
/*     */     
/* 178 */     ArrayList<Aspect> al = Aspect.getPrimalAspects();
/* 179 */     if (this.field_70146_Z.nextInt(20) == 0) {
/* 180 */       al = Aspect.getCompoundAspects();
/*     */     }
/* 182 */     setAspectTag(((Aspect)al.get(this.field_70146_Z.nextInt(al.size()))).getTag());
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_96092_aw()
/*     */   {
/* 188 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_180427_aV()
/*     */   {
/* 194 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_85031_j(Entity entityIn)
/*     */   {
/* 200 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70097_a(DamageSource source, float amount)
/*     */   {
/* 206 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70024_g(double x, double y, double z) {}
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_70091_d(double x, double y, double z) {}
/*     */   
/*     */ 
/*     */ 
/*     */   protected boolean func_142008_O()
/*     */   {
/* 222 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   private void onBroken(Entity entity) {}
/*     */   
/*     */ 
/*     */   protected void func_70037_a(NBTTagCompound tagCompound)
/*     */   {
/* 231 */     setNodeSize(tagCompound.func_74762_e("size"));
/* 232 */     setNodeType(tagCompound.func_74771_c("type"));
/* 233 */     setAspectTag(tagCompound.func_74779_i("aspect"));
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70014_b(NBTTagCompound tagCompound)
/*     */   {
/* 239 */     tagCompound.func_74768_a("size", getNodeSize());
/* 240 */     tagCompound.func_74774_a("type", (byte)getNodeType());
/* 241 */     tagCompound.func_74778_a("aspect", getAspectTag());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_180426_a(double p_180426_1_, double p_180426_3_, double p_180426_5_, float p_180426_7_, float p_180426_8_, int p_180426_9_, boolean p_180426_10_)
/*     */   {
/* 249 */     func_70107_b(p_180426_1_, p_180426_3_, p_180426_5_);
/* 250 */     func_70101_b(p_180426_7_, p_180426_8_);
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_90999_ad()
/*     */   {
/* 257 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/aura/EntityAuraNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */