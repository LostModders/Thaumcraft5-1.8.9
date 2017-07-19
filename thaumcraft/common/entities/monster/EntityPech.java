/*     */ package thaumcraft.common.entities.monster;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.enchantment.Enchantment;
/*     */ import net.minecraft.enchantment.EnchantmentData;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.IRangedAttackMob;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIArrowAttack;
/*     */ import net.minecraft.entity.ai.EntityAIAvoidEntity;
/*     */ import net.minecraft.entity.ai.EntityAILookIdle;
/*     */ import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
/*     */ import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
/*     */ import net.minecraft.entity.ai.EntityAIOpenDoor;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.EntityAIWander;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest2;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.projectile.EntityArrow;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemEnchantedBook;
/*     */ import net.minecraft.item.ItemNameTag;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.pathfinding.PathNavigateGround;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraftforge.common.BiomeDictionary;
/*     */ import net.minecraftforge.common.BiomeDictionary.Type;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.aspects.IEssentiaContainerItem;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.api.wands.IWand;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.entities.ai.combat.AIAttackOnCollide;
/*     */ import thaumcraft.common.entities.ai.pech.AIPechItemEntityGoto;
/*     */ import thaumcraft.common.entities.ai.pech.AIPechTradePlayer;
/*     */ import thaumcraft.common.entities.projectile.EntityPechBlast;
/*     */ import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;
/*     */ import thaumcraft.common.lib.utils.InventoryUtils;
/*     */ 
/*     */ public class EntityPech extends EntityMob implements IRangedAttackMob
/*     */ {
/*     */   public String func_70005_c_()
/*     */   {
/*  72 */     if (func_145818_k_())
/*     */     {
/*  74 */       return func_95999_t();
/*     */     }
/*  76 */     switch (getPechType()) {
/*  77 */     case 0:  return StatCollector.func_74838_a("entity.Thaumcraft.Pech.name");
/*  78 */     case 1:  return StatCollector.func_74838_a("entity.Thaumcraft.Pech.1.name");
/*  79 */     case 2:  return StatCollector.func_74838_a("entity.Thaumcraft.Pech.2.name");
/*     */     }
/*  81 */     return StatCollector.func_74838_a("entity.Thaumcraft.Pech.name");
/*     */   }
/*     */   
/*     */ 
/*  85 */   public ItemStack[] loot = new ItemStack[9];
/*     */   
/*  87 */   public boolean trading = false;
/*  88 */   public boolean updateAINextTick = false;
/*     */   
/*  90 */   private EntityAIArrowAttack aiArrowAttack = new EntityAIArrowAttack(this, 0.6D, 20, 50, 15.0F);
/*  91 */   private EntityAIArrowAttack aiBlastAttack = new EntityAIArrowAttack(this, 0.6D, 20, 30, 15.0F);
/*  92 */   private AIAttackOnCollide aiMeleeAttack = new AIAttackOnCollide(this, EntityLivingBase.class, 0.6D, false);
/*  93 */   private EntityAIAvoidEntity aiAvoidPlayer = new EntityAIAvoidEntity(this, EntityPlayer.class, 8.0F, 0.5D, 0.6D);
/*     */   
/*     */   public EntityPech(World world)
/*     */   {
/*  97 */     super(world);
/*  98 */     func_70105_a(0.6F, 1.8F);
/*  99 */     ((PathNavigateGround)func_70661_as()).func_179690_a(true);
/*     */     
/* 101 */     this.field_70714_bg.func_75776_a(0, new net.minecraft.entity.ai.EntityAISwimming(this));
/* 102 */     this.field_70714_bg.func_75776_a(1, new AIPechTradePlayer(this));
/*     */     
/* 104 */     this.field_70714_bg.func_75776_a(3, new AIPechItemEntityGoto(this));
/*     */     
/* 106 */     this.field_70714_bg.func_75776_a(5, new EntityAIOpenDoor(this, true));
/*     */     
/* 108 */     this.field_70714_bg.func_75776_a(6, new EntityAIMoveTowardsRestriction(this, 0.5D));
/* 109 */     this.field_70714_bg.func_75776_a(6, new EntityAIMoveThroughVillage(this, 1.0D, false));
/*     */     
/* 111 */     this.field_70714_bg.func_75776_a(9, new EntityAIWander(this, 0.6D));
/* 112 */     this.field_70714_bg.func_75776_a(9, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
/* 113 */     this.field_70714_bg.func_75776_a(10, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
/* 114 */     this.field_70714_bg.func_75776_a(11, new EntityAILookIdle(this));
/*     */     
/* 116 */     this.field_70715_bh.func_75776_a(1, new net.minecraft.entity.ai.EntityAIHurtByTarget(this, false, new Class[0]));
/* 117 */     this.field_70715_bh.func_75776_a(2, new thaumcraft.common.entities.ai.combat.AINearestAttackableTargetPech(this, EntityPlayer.class, true));
/*     */     
/* 119 */     if ((world != null) && (!world.field_72995_K))
/*     */     {
/* 121 */       setCombatTask();
/*     */     }
/*     */     
/* 124 */     this.field_82174_bp[0] = 0.2F;
/*     */   }
/*     */   
/*     */   public void setCombatTask()
/*     */   {
/* 129 */     this.field_70714_bg.func_85156_a(this.aiMeleeAttack);
/* 130 */     this.field_70714_bg.func_85156_a(this.aiArrowAttack);
/* 131 */     this.field_70714_bg.func_85156_a(this.aiBlastAttack);
/* 132 */     ItemStack itemstack = func_70694_bm();
/*     */     
/* 134 */     if ((itemstack != null) && (itemstack.func_77973_b() == Items.field_151031_f))
/*     */     {
/* 136 */       this.field_70714_bg.func_75776_a(2, this.aiArrowAttack);
/*     */ 
/*     */     }
/* 139 */     else if ((itemstack != null) && (itemstack.func_77973_b() == ItemsTC.wand))
/*     */     {
/* 141 */       this.field_70714_bg.func_75776_a(2, this.aiBlastAttack);
/*     */     }
/*     */     else
/*     */     {
/* 145 */       this.field_70714_bg.func_75776_a(2, this.aiMeleeAttack);
/*     */     }
/*     */     
/* 148 */     if (isTamed()) {
/* 149 */       this.field_70714_bg.func_85156_a(this.aiAvoidPlayer);
/*     */     } else {
/* 151 */       this.field_70714_bg.func_75776_a(4, this.aiAvoidPlayer);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_82196_d(EntityLivingBase entitylivingbase, float f)
/*     */   {
/* 158 */     if (getPechType() == 2) {
/* 159 */       EntityArrow entityarrow = new EntityArrow(this.field_70170_p, this, entitylivingbase, 1.6F, 14 - this.field_70170_p.func_175659_aa().func_151525_a() * 4);
/*     */       
/* 161 */       int i = EnchantmentHelper.func_77506_a(Enchantment.field_77345_t.field_77352_x, func_70694_bm());
/*     */       
/* 163 */       int j = EnchantmentHelper.func_77506_a(Enchantment.field_77344_u.field_77352_x, func_70694_bm());
/*     */       
/* 165 */       entityarrow.func_70239_b(f * 2.0F + this.field_70146_Z.nextGaussian() * 0.25D + this.field_70170_p.func_175659_aa().func_151525_a() * 0.11F);
/*     */       
/*     */ 
/* 168 */       if (i > 0)
/*     */       {
/* 170 */         entityarrow.func_70239_b(entityarrow.func_70242_d() + i * 0.5D + 0.5D);
/*     */       }
/*     */       
/* 173 */       if (j > 0)
/*     */       {
/* 175 */         entityarrow.func_70240_a(j);
/*     */       }
/*     */       
/* 178 */       func_85030_a("random.bow", 1.0F, 1.0F / (func_70681_au().nextFloat() * 0.4F + 0.8F));
/* 179 */       this.field_70170_p.func_72838_d(entityarrow);
/*     */     }
/* 181 */     else if (getPechType() == 1) {
/* 182 */       EntityPechBlast blast = new EntityPechBlast(this.field_70170_p, this, 1, 0, this.field_70146_Z.nextFloat() < 0.1F);
/* 183 */       double d0 = entitylivingbase.field_70165_t + entitylivingbase.field_70159_w - this.field_70165_t;
/* 184 */       double d1 = entitylivingbase.field_70163_u + entitylivingbase.func_70047_e() - 1.500000023841858D - this.field_70163_u;
/* 185 */       double d2 = entitylivingbase.field_70161_v + entitylivingbase.field_70179_y - this.field_70161_v;
/* 186 */       float f1 = MathHelper.func_76133_a(d0 * d0 + d2 * d2);
/* 187 */       blast.func_70186_c(d0, d1 + f1 * 0.1F, d2, 1.5F, 4.0F);
/* 188 */       func_85030_a("thaumcraft:ice", 0.4F, 1.0F + this.field_70146_Z.nextFloat() * 0.1F);
/* 189 */       this.field_70170_p.func_72838_d(blast);
/*     */     }
/*     */     
/* 192 */     func_71038_i();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_70062_b(int par1, ItemStack par2ItemStack)
/*     */   {
/* 199 */     super.func_70062_b(par1, par2ItemStack);
/*     */     
/* 201 */     if ((!this.field_70170_p.field_72995_K) && (par1 == 0))
/*     */     {
/* 203 */       this.updateAINextTick = true;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_82164_bB()
/*     */   {
/* 213 */     super.func_82164_bB();
/* 214 */     switch (this.field_70146_Z.nextInt(20)) {
/*     */     case 0: case 12: 
/* 216 */       ItemStack wand = new ItemStack(ItemsTC.wand);
/* 217 */       ItemStack focus = new ItemStack(ItemsTC.focusPech);
/* 218 */       ((IWand)wand.func_77973_b()).setFocus(wand, focus);
/* 219 */       ((IWand)wand.func_77973_b()).addVis(wand, Aspect.EARTH, 2 + this.field_70146_Z.nextInt(6), true);
/* 220 */       ((IWand)wand.func_77973_b()).addVis(wand, Aspect.ENTROPY, 2 + this.field_70146_Z.nextInt(6), true);
/* 221 */       ((IWand)wand.func_77973_b()).addVis(wand, Aspect.WATER, 2 + this.field_70146_Z.nextInt(6), true);
/* 222 */       ((IWand)wand.func_77973_b()).addVis(wand, Aspect.AIR, this.field_70146_Z.nextInt(4), true);
/* 223 */       ((IWand)wand.func_77973_b()).addVis(wand, Aspect.FIRE, this.field_70146_Z.nextInt(4), true);
/* 224 */       ((IWand)wand.func_77973_b()).addVis(wand, Aspect.ORDER, this.field_70146_Z.nextInt(4), true);
/* 225 */       func_70062_b(0, wand);
/* 226 */       break;
/*     */     case 1: 
/* 228 */       func_70062_b(0, new ItemStack(Items.field_151052_q)); break;
/*     */     case 3: 
/* 230 */       func_70062_b(0, new ItemStack(Items.field_151049_t)); break;
/*     */     case 5: 
/* 232 */       func_70062_b(0, new ItemStack(Items.field_151040_l)); break;
/*     */     case 6: 
/* 234 */       func_70062_b(0, new ItemStack(Items.field_151036_c)); break;
/*     */     case 7: 
/* 236 */       func_70062_b(0, new ItemStack(Items.field_151112_aM)); break;
/*     */     case 8: 
/* 238 */       func_70062_b(0, new ItemStack(Items.field_151050_s)); break;
/*     */     case 9: 
/* 240 */       func_70062_b(0, new ItemStack(Items.field_151035_b)); break;
/*     */     case 2: case 4: case 10: case 11: case 13: 
/* 242 */       func_70062_b(0, new ItemStack(Items.field_151031_f));
/*     */     }
/*     */     
/*     */   }
/*     */   
/*     */   public IEntityLivingData func_180482_a(DifficultyInstance diff, IEntityLivingData data)
/*     */   {
/* 249 */     func_82164_bB();
/*     */     
/* 251 */     ItemStack itemstack = func_70694_bm();
/* 252 */     if ((itemstack != null) && (itemstack.func_77973_b() == ItemsTC.wand))
/*     */     {
/* 254 */       setPechType(1);
/* 255 */       this.field_82174_bp[0] = 0.1F;
/*     */     }
/* 257 */     else if (itemstack != null)
/*     */     {
/* 259 */       if (itemstack.func_77973_b() == Items.field_151031_f) setPechType(2);
/* 260 */       func_180483_b(diff);
/*     */     }
/*     */     
/* 263 */     float f = diff.func_180170_c();
/* 264 */     func_98053_h(this.field_70146_Z.nextFloat() < 0.75F * f);
/*     */     
/* 266 */     return super.func_180482_a(diff, data);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_70601_bi()
/*     */   {
/* 273 */     BiomeGenBase biome = this.field_70170_p.func_180494_b(new net.minecraft.util.BlockPos(this));
/* 274 */     boolean magicBiome = false;
/* 275 */     if (biome != null) {
/* 276 */       magicBiome = BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.MAGICAL);
/*     */     }
/* 278 */     int count = 0;
/*     */     try {
/* 280 */       List l = this.field_70170_p.func_72872_a(EntityPech.class, func_174813_aQ().func_72314_b(16.0D, 16.0D, 16.0D));
/* 281 */       if (l != null) count = l.size();
/*     */     }
/*     */     catch (Exception e) {}
/* 284 */     if ((this.field_70170_p.field_73011_w.func_177502_q() != Config.overworldDim) && (biome.field_76756_M != Config.biomeMagicalForestID) && (biome.field_76756_M != Config.biomeEerieID))
/*     */     {
/* 286 */       magicBiome = false;
/*     */     }
/* 288 */     if (biome.field_76756_M == Config.biomeTaintID) { magicBiome = false;
/*     */     }
/* 290 */     return (count < 4) && (magicBiome) && (super.func_70601_bi());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public float func_70047_e()
/*     */   {
/* 297 */     return this.field_70131_O * 0.66F;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70636_d()
/*     */   {
/* 303 */     super.func_70636_d();
/*     */   }
/*     */   
/*     */   protected void func_70088_a()
/*     */   {
/* 308 */     super.func_70088_a();
/* 309 */     this.field_70180_af.func_75682_a(13, new Byte((byte)0));
/* 310 */     this.field_70180_af.func_75682_a(14, new Short((short)0));
/* 311 */     this.field_70180_af.func_75682_a(16, new Byte((byte)0));
/*     */   }
/*     */   
/*     */   public int getPechType()
/*     */   {
/* 316 */     return this.field_70180_af.func_75683_a(13);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getAnger()
/*     */   {
/* 324 */     return this.field_70180_af.func_75693_b(14);
/*     */   }
/*     */   
/*     */   public boolean isTamed()
/*     */   {
/* 329 */     return this.field_70180_af.func_75683_a(16) == 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPechType(int par1)
/*     */   {
/* 337 */     this.field_70180_af.func_75692_b(13, Byte.valueOf((byte)par1));
/*     */   }
/*     */   
/*     */   public void setAnger(int par1)
/*     */   {
/* 342 */     this.field_70180_af.func_75692_b(14, Short.valueOf((short)par1));
/*     */   }
/*     */   
/*     */   public void setTamed(boolean par1)
/*     */   {
/* 347 */     this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)(par1 ? 1 : 0)));
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_110147_ax()
/*     */   {
/* 353 */     super.func_110147_ax();
/* 354 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(30.0D);
/* 355 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(6.0D);
/* 356 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.5D);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70014_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 362 */     super.func_70014_b(par1NBTTagCompound);
/*     */     
/* 364 */     par1NBTTagCompound.func_74774_a("PechType", (byte)getPechType());
/*     */     
/* 366 */     par1NBTTagCompound.func_74777_a("Anger", (short)getAnger());
/*     */     
/* 368 */     par1NBTTagCompound.func_74757_a("Tamed", isTamed());
/*     */     
/*     */ 
/* 371 */     NBTTagList nbttaglist = new NBTTagList();
/*     */     
/* 373 */     for (int i = 0; i < this.loot.length; i++)
/*     */     {
/* 375 */       NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/*     */       
/* 377 */       if (this.loot[i] != null)
/*     */       {
/* 379 */         this.loot[i].func_77955_b(nbttagcompound1);
/*     */       }
/*     */       
/* 382 */       nbttaglist.func_74742_a(nbttagcompound1);
/*     */     }
/* 384 */     par1NBTTagCompound.func_74782_a("Loot", nbttaglist);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 390 */     super.func_70037_a(par1NBTTagCompound);
/*     */     
/* 392 */     if (par1NBTTagCompound.func_74764_b("PechType"))
/*     */     {
/* 394 */       byte b0 = par1NBTTagCompound.func_74771_c("PechType");
/* 395 */       setPechType(b0);
/*     */     }
/*     */     
/* 398 */     setAnger(par1NBTTagCompound.func_74765_d("Anger"));
/*     */     
/* 400 */     setTamed(par1NBTTagCompound.func_74767_n("Tamed"));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 405 */     if (par1NBTTagCompound.func_74764_b("Loot"))
/*     */     {
/* 407 */       NBTTagList nbttaglist = par1NBTTagCompound.func_150295_c("Loot", 10);
/* 408 */       for (int i = 0; i < this.loot.length; i++)
/*     */       {
/* 410 */         this.loot[i] = ItemStack.func_77949_a(nbttaglist.func_150305_b(i));
/*     */       }
/*     */     }
/*     */     
/* 414 */     this.updateAINextTick = true;
/*     */   }
/*     */   
/*     */ 
/*     */   protected boolean func_70692_ba()
/*     */   {
/*     */     try
/*     */     {
/* 422 */       if (this.loot == null) return true;
/* 423 */       int q = 0;
/* 424 */       for (ItemStack is : this.loot) {
/* 425 */         if ((is != null) && (is.field_77994_a > 0)) q++;
/*     */       }
/* 427 */       return q < 5; } catch (Exception e) {}
/* 428 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_110164_bC()
/*     */   {
/* 434 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_70628_a(boolean flag, int i)
/*     */   {
/* 443 */     for (int a = 0; a < this.loot.length; a++) {
/* 444 */       if ((this.loot[a] != null) && (this.field_70170_p.field_73012_v.nextFloat() < 0.33F)) {
/* 445 */         func_70099_a(this.loot[a].func_77946_l(), 1.5F);
/*     */       }
/* 447 */       else if (this.field_70170_p.field_73012_v.nextFloat() > 0.33F) {
/* 448 */         func_70099_a(new ItemStack(ItemsTC.coin), 1.5F);
/*     */       }
/*     */     }
/*     */     
/* 452 */     Aspect[] aspects = (Aspect[])Aspect.getPrimalAspects().toArray(new Aspect[0]);
/* 453 */     for (int a = 0; a < 1 + i; a++) {
/* 454 */       if (this.field_70146_Z.nextBoolean()) {
/* 455 */         ItemStack is = new ItemStack(ItemsTC.wispyEssence);
/* 456 */         ((IEssentiaContainerItem)is.func_77973_b()).setAspects(is, new AspectList().add(aspects[this.field_70146_Z.nextInt(aspects.length)], 2));
/* 457 */         func_70099_a(is, 1.5F);
/*     */       }
/*     */     }
/* 460 */     if (this.field_70170_p.field_73012_v.nextInt(50) < 1 + i) { func_70099_a(new ItemStack(ItemsTC.knowledgeFragment), 1.5F);
/*     */     }
/* 462 */     super.func_70628_a(flag, i);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*     */   public void func_70103_a(byte par1)
/*     */   {
/* 470 */     if (par1 == 16)
/*     */     {
/* 472 */       this.mumble = 3.1415927F;
/*     */ 
/*     */     }
/* 475 */     else if (par1 == 17)
/*     */     {
/* 477 */       this.mumble = 6.2831855F;
/*     */ 
/*     */     }
/* 480 */     else if (par1 == 18)
/*     */     {
/* 482 */       for (int i = 0; i < 5; i++)
/*     */       {
/* 484 */         double d0 = this.field_70146_Z.nextGaussian() * 0.02D;
/* 485 */         double d1 = this.field_70146_Z.nextGaussian() * 0.02D;
/* 486 */         double d2 = this.field_70146_Z.nextGaussian() * 0.02D;
/* 487 */         this.field_70170_p.func_175688_a(EnumParticleTypes.VILLAGER_HAPPY, this.field_70165_t + this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F - this.field_70130_N, this.field_70163_u + 0.5D + this.field_70146_Z.nextFloat() * this.field_70131_O, this.field_70161_v + this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F - this.field_70130_N, d0, d1, d2, new int[0]);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 493 */     if (par1 == 19)
/*     */     {
/* 495 */       for (int i = 0; i < 5; i++)
/*     */       {
/* 497 */         double d0 = this.field_70146_Z.nextGaussian() * 0.02D;
/* 498 */         double d1 = this.field_70146_Z.nextGaussian() * 0.02D;
/* 499 */         double d2 = this.field_70146_Z.nextGaussian() * 0.02D;
/* 500 */         this.field_70170_p.func_175688_a(EnumParticleTypes.VILLAGER_ANGRY, this.field_70165_t + this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F - this.field_70130_N, this.field_70163_u + 0.5D + this.field_70146_Z.nextFloat() * this.field_70131_O, this.field_70161_v + this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F - this.field_70130_N, d0, d1, d2, new int[0]);
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 505 */       this.mumble = 6.2831855F;
/*     */     }
/*     */     else
/*     */     {
/* 509 */       super.func_70103_a(par1);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/* 515 */   public float mumble = 0.0F;
/*     */   
/*     */   public void func_70642_aH()
/*     */   {
/* 519 */     if (!this.field_70170_p.field_72995_K) {
/* 520 */       if (this.field_70146_Z.nextInt(3) == 0) {
/* 521 */         List list = this.field_70170_p.func_72839_b(this, func_174813_aQ().func_72314_b(4.0D, 2.0D, 4.0D));
/*     */         
/* 523 */         for (int i = 0; i < list.size(); i++)
/*     */         {
/* 525 */           Entity entity1 = (Entity)list.get(i);
/*     */           
/* 527 */           if ((entity1 instanceof EntityPech))
/*     */           {
/* 529 */             this.field_70170_p.func_72960_a(this, (byte)17);
/* 530 */             func_85030_a("thaumcraft:pech_trade", func_70599_aP(), func_70647_i());
/* 531 */             return;
/*     */           }
/*     */         }
/*     */       }
/*     */       
/* 536 */       this.field_70170_p.func_72960_a(this, (byte)16);
/*     */     }
/* 538 */     super.func_70642_aH();
/*     */   }
/*     */   
/*     */   public int func_70627_aG()
/*     */   {
/* 543 */     return 120;
/*     */   }
/*     */   
/*     */ 
/*     */   protected float func_70599_aP()
/*     */   {
/* 549 */     return 0.4F;
/*     */   }
/*     */   
/*     */ 
/*     */   protected String func_70639_aQ()
/*     */   {
/* 555 */     return "thaumcraft:pech_idle";
/*     */   }
/*     */   
/*     */ 
/*     */   protected String func_70621_aR()
/*     */   {
/* 561 */     return "thaumcraft:pech_hit";
/*     */   }
/*     */   
/*     */ 
/*     */   protected String func_70673_aS()
/*     */   {
/* 567 */     return "thaumcraft:pech_death";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void becomeAngryAt(Entity par1Entity)
/*     */   {
/* 574 */     if (getAnger() <= 0) {
/* 575 */       this.field_70170_p.func_72960_a(this, (byte)19);
/* 576 */       func_85030_a("thaumcraft:pech_charge", func_70599_aP(), func_70647_i());
/*     */     }
/* 578 */     func_70624_b((EntityLivingBase)par1Entity);
/* 579 */     setAnger(400 + this.field_70146_Z.nextInt(400));
/* 580 */     setTamed(false);
/*     */     
/* 582 */     this.updateAINextTick = true;
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_70658_aO()
/*     */   {
/* 588 */     int i = super.func_70658_aO() + 2;
/*     */     
/* 590 */     if (i > 20)
/*     */     {
/* 592 */       i = 20;
/*     */     }
/*     */     
/* 595 */     return i;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70097_a(DamageSource damSource, float par2)
/*     */   {
/* 601 */     if (func_180431_b(damSource))
/*     */     {
/* 603 */       return false;
/*     */     }
/*     */     
/*     */ 
/* 607 */     Entity entity = damSource.func_76346_g();
/*     */     
/* 609 */     if ((entity instanceof EntityPlayer))
/*     */     {
/* 611 */       List list = this.field_70170_p.func_72839_b(this, func_174813_aQ().func_72314_b(32.0D, 16.0D, 32.0D));
/*     */       
/* 613 */       for (int i = 0; i < list.size(); i++)
/*     */       {
/* 615 */         Entity entity1 = (Entity)list.get(i);
/*     */         
/* 617 */         if ((entity1 instanceof EntityPech))
/*     */         {
/* 619 */           EntityPech entitypech = (EntityPech)entity1;
/* 620 */           entitypech.becomeAngryAt(entity);
/*     */         }
/*     */       }
/*     */       
/* 624 */       becomeAngryAt(entity);
/*     */     }
/*     */     
/* 627 */     return super.func_70097_a(damSource, par2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 633 */   int chargecount = 0;
/*     */   
/*     */   public void func_70071_h_()
/*     */   {
/* 637 */     if (this.mumble > 0.0F) this.mumble *= 0.75F;
/* 638 */     if (getAnger() > 0) { setAnger(getAnger() - 1);
/*     */     }
/* 640 */     if ((getAnger() > 0) && (func_70638_az() != null)) {
/* 641 */       if (this.chargecount > 0) this.chargecount -= 1;
/* 642 */       if (this.chargecount == 0) {
/* 643 */         this.chargecount = 100;
/* 644 */         func_85030_a("thaumcraft:pech_charge", func_70599_aP(), func_70647_i());
/*     */       }
/* 646 */       this.field_70170_p.func_72960_a(this, (byte)17);
/*     */     }
/*     */     
/* 649 */     if ((this.field_70170_p.field_72995_K) && (this.field_70146_Z.nextInt(15) == 0) && (getAnger() > 0)) {
/* 650 */       double d0 = this.field_70146_Z.nextGaussian() * 0.02D;
/* 651 */       double d1 = this.field_70146_Z.nextGaussian() * 0.02D;
/* 652 */       double d2 = this.field_70146_Z.nextGaussian() * 0.02D;
/* 653 */       this.field_70170_p.func_175688_a(EnumParticleTypes.VILLAGER_ANGRY, this.field_70165_t + this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F - this.field_70130_N, this.field_70163_u + 0.5D + this.field_70146_Z.nextFloat() * this.field_70131_O, this.field_70161_v + this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F - this.field_70130_N, d0, d1, d2, new int[0]);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 659 */     if ((this.field_70170_p.field_72995_K) && (this.field_70146_Z.nextInt(25) == 0) && (isTamed())) {
/* 660 */       double d0 = this.field_70146_Z.nextGaussian() * 0.02D;
/* 661 */       double d1 = this.field_70146_Z.nextGaussian() * 0.02D;
/* 662 */       double d2 = this.field_70146_Z.nextGaussian() * 0.02D;
/* 663 */       this.field_70170_p.func_175688_a(EnumParticleTypes.VILLAGER_HAPPY, this.field_70165_t + this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F - this.field_70130_N, this.field_70163_u + 0.5D + this.field_70146_Z.nextFloat() * this.field_70131_O, this.field_70161_v + this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F - this.field_70130_N, d0, d1, d2, new int[0]);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 669 */     super.func_70071_h_();
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70619_bc()
/*     */   {
/* 675 */     if (this.updateAINextTick) {
/* 676 */       this.updateAINextTick = false;
/* 677 */       setCombatTask();
/*     */     }
/*     */     
/* 680 */     super.func_70619_bc();
/* 681 */     if (this.field_70173_aa % 40 == 0)
/*     */     {
/* 683 */       func_70691_i(1.0F);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean canPickup(ItemStack entityItem)
/*     */   {
/* 690 */     if (entityItem == null) return false;
/* 691 */     if ((!isTamed()) && (valuedItems.containsKey(Integer.valueOf(Item.func_150891_b(entityItem.func_77973_b()))))) {
/* 692 */       return true;
/*     */     }
/* 694 */     for (int a = 0; a < this.loot.length; a++) {
/* 695 */       if ((this.loot[a] != null) && (this.loot[a].field_77994_a <= 0)) this.loot[a] = null;
/* 696 */       if (this.loot[a] == null) return true;
/* 697 */       if ((InventoryUtils.areItemStacksEqualStrict(entityItem, this.loot[a])) && (entityItem.field_77994_a + this.loot[a].field_77994_a <= this.loot[a].func_77976_d()))
/* 698 */         return true;
/*     */     }
/* 700 */     return false;
/*     */   }
/*     */   
/*     */   public ItemStack pickupItem(ItemStack entityItem) {
/* 704 */     if (entityItem == null) { return entityItem;
/*     */     }
/* 706 */     if ((!isTamed()) && (isValued(entityItem)))
/*     */     {
/* 708 */       if (this.field_70146_Z.nextInt(10) < getValue(entityItem)) {
/* 709 */         setTamed(true);
/* 710 */         this.updateAINextTick = true;
/* 711 */         this.field_70170_p.func_72960_a(this, (byte)18);
/*     */       }
/*     */       
/* 714 */       entityItem.field_77994_a -= 1;
/*     */       
/* 716 */       if (entityItem.field_77994_a <= 0) {
/* 717 */         return null;
/*     */       }
/* 719 */       return entityItem;
/*     */     }
/*     */     
/*     */ 
/* 723 */     for (int a = 0; a < this.loot.length; a++) {
/* 724 */       if ((this.loot[a] != null) && (this.loot[a].field_77994_a <= 0)) this.loot[a] = null;
/* 725 */       if ((entityItem != null) && (entityItem.field_77994_a > 0) && (this.loot[a] != null) && (this.loot[a].field_77994_a < this.loot[a].func_77976_d()) && (InventoryUtils.areItemStacksEqualStrict(entityItem, this.loot[a])))
/*     */       {
/*     */ 
/*     */ 
/* 729 */         if (entityItem.field_77994_a + this.loot[a].field_77994_a <= this.loot[a].func_77976_d()) {
/* 730 */           this.loot[a].field_77994_a += entityItem.field_77994_a;
/* 731 */           return null;
/*     */         }
/* 733 */         int sz = Math.min(entityItem.field_77994_a, this.loot[a].func_77976_d() - this.loot[a].field_77994_a);
/* 734 */         this.loot[a].field_77994_a += sz;
/* 735 */         entityItem.field_77994_a -= sz;
/*     */       }
/*     */       
/* 738 */       if ((entityItem != null) && (entityItem.field_77994_a <= 0)) { entityItem = null;
/*     */       }
/*     */     }
/*     */     
/* 742 */     for (int a = 0; a < this.loot.length; a++) {
/* 743 */       if ((this.loot[a] != null) && (this.loot[a].field_77994_a <= 0)) this.loot[a] = null;
/* 744 */       if ((entityItem != null) && (entityItem.field_77994_a > 0) && (this.loot[a] == null)) {
/* 745 */         this.loot[a] = entityItem.func_77946_l();
/* 746 */         return null;
/*     */       }
/*     */     }
/* 749 */     if ((entityItem != null) && (entityItem.field_77994_a <= 0)) entityItem = null;
/* 750 */     return entityItem;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_70085_c(EntityPlayer player)
/*     */   {
/* 758 */     if ((player.func_70093_af()) || ((player.func_70694_bm() != null) && ((player.func_70694_bm().func_77973_b() instanceof ItemNameTag))))
/* 759 */       return false;
/* 760 */     if (isTamed()) {
/* 761 */       if (!this.field_70170_p.field_72995_K) {
/* 762 */         player.openGui(Thaumcraft.instance, 1, this.field_70170_p, func_145782_y(), 0, 0);
/*     */       }
/* 764 */       return true;
/*     */     }
/*     */     
/* 767 */     return super.func_70085_c(player);
/*     */   }
/*     */   
/*     */   public boolean isValued(ItemStack item) {
/* 771 */     if (item == null) return false;
/* 772 */     boolean value = valuedItems.containsKey(Integer.valueOf(Item.func_150891_b(item.func_77973_b())));
/* 773 */     if (!value) {
/* 774 */       AspectList al = ThaumcraftCraftingManager.getObjectTags(item);
/* 775 */       if (al.getAmount(Aspect.DESIRE) > 1)
/* 776 */         value = true;
/*     */     }
/* 778 */     return value;
/*     */   }
/*     */   
/*     */   public int getValue(ItemStack item) {
/* 782 */     if (item == null) return 0;
/* 783 */     int value = valuedItems.containsKey(Integer.valueOf(Item.func_150891_b(item.func_77973_b()))) ? ((Integer)valuedItems.get(Integer.valueOf(Item.func_150891_b(item.func_77973_b())))).intValue() : 0;
/* 784 */     if (value == 0) {
/* 785 */       AspectList al = ThaumcraftCraftingManager.getObjectTags(item);
/* 786 */       value = Math.min(32, al.getAmount(Aspect.DESIRE));
/*     */     }
/* 788 */     return value;
/*     */   }
/*     */   
/* 791 */   static HashMap<Integer, Integer> valuedItems = new HashMap();
/* 792 */   public static HashMap<Integer, ArrayList<List>> tradeInventory = new HashMap();
/*     */   
/*     */   static {
/* 795 */     valuedItems.put(Integer.valueOf(Item.func_150891_b(Items.field_151043_k)), Integer.valueOf(2));
/* 796 */     valuedItems.put(Integer.valueOf(Item.func_150891_b(Items.field_151153_ao)), Integer.valueOf(2));
/* 797 */     valuedItems.put(Integer.valueOf(Item.func_150891_b(Items.field_151079_bi)), Integer.valueOf(3));
/* 798 */     valuedItems.put(Integer.valueOf(Item.func_150891_b(Items.field_151045_i)), Integer.valueOf(4));
/* 799 */     valuedItems.put(Integer.valueOf(Item.func_150891_b(Items.field_151166_bC)), Integer.valueOf(5));
/*     */     
/*     */ 
/*     */ 
/* 803 */     ArrayList<List> forInv = new ArrayList();
/*     */     
/* 805 */     forInv.add(Arrays.asList(new Object[] { Integer.valueOf(1), new ItemStack(ItemsTC.clusters, 1, 0) }));
/* 806 */     forInv.add(Arrays.asList(new Object[] { Integer.valueOf(1), new ItemStack(ItemsTC.clusters, 1, 1) }));
/* 807 */     forInv.add(Arrays.asList(new Object[] { Integer.valueOf(1), new ItemStack(ItemsTC.clusters, 1, 6) }));
/* 808 */     if (Config.foundCopperIngot)
/* 809 */       forInv.add(Arrays.asList(new Object[] { Integer.valueOf(1), new ItemStack(ItemsTC.clusters, 1, 2) }));
/* 810 */     if (Config.foundTinIngot)
/* 811 */       forInv.add(Arrays.asList(new Object[] { Integer.valueOf(1), new ItemStack(ItemsTC.clusters, 1, 3) }));
/* 812 */     if (Config.foundSilverIngot)
/* 813 */       forInv.add(Arrays.asList(new Object[] { Integer.valueOf(1), new ItemStack(ItemsTC.clusters, 1, 4) }));
/* 814 */     if (Config.foundLeadIngot)
/* 815 */       forInv.add(Arrays.asList(new Object[] { Integer.valueOf(1), new ItemStack(ItemsTC.clusters, 1, 5) }));
/* 816 */     forInv.add(Arrays.asList(new Object[] { Integer.valueOf(2), new ItemStack(Items.field_151072_bj) }));
/* 817 */     forInv.add(Arrays.asList(new Object[] { Integer.valueOf(2), new ItemStack(BlocksTC.sapling, 1, 0) }));
/* 818 */     forInv.add(Arrays.asList(new Object[] { Integer.valueOf(2), new ItemStack(Items.field_151068_bn, 1, 8201) }));
/* 819 */     forInv.add(Arrays.asList(new Object[] { Integer.valueOf(2), new ItemStack(Items.field_151068_bn, 1, 8194) }));
/* 820 */     forInv.add(Arrays.asList(new Object[] { Integer.valueOf(3), new ItemStack(Items.field_151062_by) }));
/* 821 */     forInv.add(Arrays.asList(new Object[] { Integer.valueOf(3), new ItemStack(Items.field_151062_by) }));
/* 822 */     forInv.add(Arrays.asList(new Object[] { Integer.valueOf(3), new ItemStack(ItemsTC.knowledgeFragment) }));
/* 823 */     forInv.add(Arrays.asList(new Object[] { Integer.valueOf(3), new ItemStack(Items.field_151153_ao, 1, 0) }));
/* 824 */     forInv.add(Arrays.asList(new Object[] { Integer.valueOf(3), new ItemStack(Items.field_151068_bn, 1, 8265) }));
/* 825 */     forInv.add(Arrays.asList(new Object[] { Integer.valueOf(3), new ItemStack(Items.field_151068_bn, 1, 8262) }));
/* 826 */     forInv.add(Arrays.asList(new Object[] { Integer.valueOf(4), new ItemStack(ItemsTC.thaumiumPick) }));
/* 827 */     forInv.add(Arrays.asList(new Object[] { Integer.valueOf(4), new ItemStack(ItemsTC.thaumiumAxe) }));
/* 828 */     forInv.add(Arrays.asList(new Object[] { Integer.valueOf(4), new ItemStack(ItemsTC.thaumiumHoe) }));
/* 829 */     forInv.add(Arrays.asList(new Object[] { Integer.valueOf(5), new ItemStack(Items.field_151153_ao, 1, 1) }));
/* 830 */     forInv.add(Arrays.asList(new Object[] { Integer.valueOf(5), new ItemStack(BlocksTC.sapling, 1, 1) }));
/* 831 */     tradeInventory.put(Integer.valueOf(0), forInv);
/*     */     
/*     */ 
/* 834 */     ArrayList<List> forMag = new ArrayList();
/*     */     
/* 836 */     for (int a = 0; a < 6; a++)
/* 837 */       forMag.add(Arrays.asList(new Object[] { Integer.valueOf(1), new ItemStack(ItemsTC.shard, 1, a) }));
/* 838 */     forMag.add(Arrays.asList(new Object[] { Integer.valueOf(1), new ItemStack(ItemsTC.knowledgeFragment) }));
/* 839 */     forMag.add(Arrays.asList(new Object[] { Integer.valueOf(2), new ItemStack(ItemsTC.knowledgeFragment) }));
/* 840 */     forMag.add(Arrays.asList(new Object[] { Integer.valueOf(2), new ItemStack(Items.field_151068_bn, 1, 8193) }));
/* 841 */     forMag.add(Arrays.asList(new Object[] { Integer.valueOf(2), new ItemStack(Items.field_151068_bn, 1, 8261) }));
/* 842 */     forMag.add(Arrays.asList(new Object[] { Integer.valueOf(3), new ItemStack(Items.field_151062_by) }));
/* 843 */     forMag.add(Arrays.asList(new Object[] { Integer.valueOf(3), new ItemStack(Items.field_151062_by) }));
/* 844 */     forMag.add(Arrays.asList(new Object[] { Integer.valueOf(3), Items.field_151134_bR.func_92111_a(new EnchantmentData(Config.enchHaste, 1)) }));
/* 845 */     forMag.add(Arrays.asList(new Object[] { Integer.valueOf(3), new ItemStack(Items.field_151153_ao, 1, 0) }));
/* 846 */     forMag.add(Arrays.asList(new Object[] { Integer.valueOf(3), new ItemStack(Items.field_151068_bn, 1, 8225) }));
/* 847 */     forMag.add(Arrays.asList(new Object[] { Integer.valueOf(3), new ItemStack(Items.field_151068_bn, 1, 8229) }));
/* 848 */     forMag.add(Arrays.asList(new Object[] { Integer.valueOf(4), new ItemStack(ItemsTC.clothBoots) }));
/* 849 */     forMag.add(Arrays.asList(new Object[] { Integer.valueOf(4), new ItemStack(ItemsTC.clothChest) }));
/* 850 */     forMag.add(Arrays.asList(new Object[] { Integer.valueOf(4), new ItemStack(ItemsTC.clothLegs) }));
/* 851 */     forMag.add(Arrays.asList(new Object[] { Integer.valueOf(5), new ItemStack(Items.field_151153_ao, 1, 1) }));
/* 852 */     forMag.add(Arrays.asList(new Object[] { Integer.valueOf(5), Items.field_151134_bR.func_92111_a(new EnchantmentData(Config.enchRepair, 1)) }));
/* 853 */     forMag.add(Arrays.asList(new Object[] { Integer.valueOf(5), new ItemStack(ItemsTC.focusPech) }));
/* 854 */     forMag.add(Arrays.asList(new Object[] { Integer.valueOf(5), new ItemStack(ItemsTC.amuletVis, 1, 0) }));
/* 855 */     tradeInventory.put(Integer.valueOf(1), forMag);
/*     */     
/*     */ 
/* 858 */     ArrayList<List> forArc = new ArrayList();
/*     */     
/* 860 */     for (int a = 0; a < 15; a++)
/* 861 */       forArc.add(Arrays.asList(new Object[] { Integer.valueOf(1), new ItemStack(BlocksTC.candle, 1, a) }));
/* 862 */     for (int a = 0; a < 6; a++)
/* 863 */       forArc.add(Arrays.asList(new Object[] { Integer.valueOf(2), new ItemStack(ItemsTC.primalArrows, 8, a) }));
/* 864 */     forArc.add(Arrays.asList(new Object[] { Integer.valueOf(2), new ItemStack(Items.field_151073_bk) }));
/* 865 */     forArc.add(Arrays.asList(new Object[] { Integer.valueOf(2), new ItemStack(Items.field_151068_bn, 1, 8194) }));
/* 866 */     forArc.add(Arrays.asList(new Object[] { Integer.valueOf(2), new ItemStack(Items.field_151068_bn, 1, 8201) }));
/* 867 */     forArc.add(Arrays.asList(new Object[] { Integer.valueOf(2), Items.field_151134_bR.func_92111_a(new EnchantmentData(Enchantment.field_77345_t, 1)) }));
/* 868 */     forArc.add(Arrays.asList(new Object[] { Integer.valueOf(3), new ItemStack(Items.field_151062_by) }));
/* 869 */     forArc.add(Arrays.asList(new Object[] { Integer.valueOf(3), new ItemStack(Items.field_151062_by) }));
/* 870 */     forArc.add(Arrays.asList(new Object[] { Integer.valueOf(3), new ItemStack(ItemsTC.knowledgeFragment) }));
/* 871 */     forArc.add(Arrays.asList(new Object[] { Integer.valueOf(3), new ItemStack(Items.field_151068_bn, 1, 8270) }));
/* 872 */     forArc.add(Arrays.asList(new Object[] { Integer.valueOf(3), new ItemStack(Items.field_151068_bn, 1, 8225) }));
/* 873 */     forArc.add(Arrays.asList(new Object[] { Integer.valueOf(3), new ItemStack(Items.field_151153_ao, 1, 0) }));
/* 874 */     forArc.add(Arrays.asList(new Object[] { Integer.valueOf(5), new ItemStack(Items.field_151153_ao, 1, 1) }));
/* 875 */     forArc.add(Arrays.asList(new Object[] { Integer.valueOf(4), new ItemStack(ItemsTC.boneBow) }));
/* 876 */     forArc.add(Arrays.asList(new Object[] { Integer.valueOf(5), new ItemStack(ItemsTC.ringRunic, 1, 0) }));
/* 877 */     forArc.add(Arrays.asList(new Object[] { Integer.valueOf(5), Items.field_151134_bR.func_92111_a(new EnchantmentData(Enchantment.field_77343_v, 1)) }));
/* 878 */     forArc.add(Arrays.asList(new Object[] { Integer.valueOf(5), Items.field_151134_bR.func_92111_a(new EnchantmentData(Enchantment.field_77342_w, 1)) }));
/* 879 */     tradeInventory.put(Integer.valueOf(2), forArc);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/monster/EntityPech.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */