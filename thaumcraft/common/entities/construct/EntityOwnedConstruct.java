/*     */ package thaumcraft.common.entities.construct;
/*     */ 
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IEntityOwnable;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.item.ItemNameTag;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.scoreboard.Team;
/*     */ import net.minecraft.server.management.PreYggdrasilConverter;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.CombatTracker;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.GameRules;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityOwnedConstruct extends EntityCreature implements IEntityOwnable
/*     */ {
/*     */   public EntityOwnedConstruct(World worldIn)
/*     */   {
/*  27 */     super(worldIn);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70088_a()
/*     */   {
/*  33 */     super.func_70088_a();
/*  34 */     this.field_70180_af.func_75682_a(16, Byte.valueOf((byte)0));
/*  35 */     this.field_70180_af.func_75682_a(17, "");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int func_70682_h(int air)
/*     */   {
/*  44 */     return air;
/*     */   }
/*     */   
/*     */   public boolean func_70648_aU()
/*     */   {
/*  49 */     return true;
/*     */   }
/*     */   
/*     */   protected String func_70639_aQ()
/*     */   {
/*  54 */     return "thaumcraft:cameraclack";
/*     */   }
/*     */   
/*     */ 
/*     */   protected String func_70621_aR()
/*     */   {
/*  60 */     return "thaumcraft:cameraclack";
/*     */   }
/*     */   
/*     */ 
/*     */   protected String func_70673_aS()
/*     */   {
/*  66 */     return "thaumcraft:tool";
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_70627_aG()
/*     */   {
/*  72 */     return 240;
/*     */   }
/*     */   
/*     */ 
/*     */   protected boolean func_70692_ba()
/*     */   {
/*  78 */     return false;
/*     */   }
/*     */   
/*     */   public void func_70071_h_()
/*     */   {
/*  83 */     super.func_70071_h_();
/*     */     
/*  85 */     if ((func_70638_az() != null) && (func_142014_c(func_70638_az()))) { func_70624_b(null);
/*     */     }
/*  87 */     if ((!this.field_70170_p.field_72995_K) && (!this.validSpawn)) {
/*  88 */       func_70106_y();
/*     */     }
/*     */   }
/*     */   
/*  92 */   boolean validSpawn = false;
/*     */   
/*     */   public void setValidSpawn() {
/*  95 */     this.validSpawn = true;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70014_b(NBTTagCompound tagCompound)
/*     */   {
/* 101 */     super.func_70014_b(tagCompound);
/*     */     
/* 103 */     tagCompound.func_74757_a("v", this.validSpawn);
/*     */     
/* 105 */     if (func_152113_b() == null)
/*     */     {
/* 107 */       tagCompound.func_74778_a("OwnerUUID", "");
/*     */     }
/*     */     else
/*     */     {
/* 111 */       tagCompound.func_74778_a("OwnerUUID", func_152113_b());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound tagCompound)
/*     */   {
/* 118 */     super.func_70037_a(tagCompound);
/*     */     
/* 120 */     this.validSpawn = tagCompound.func_74767_n("v");
/*     */     
/* 122 */     String s = "";
/*     */     
/* 124 */     if (tagCompound.func_150297_b("OwnerUUID", 8))
/*     */     {
/* 126 */       s = tagCompound.func_74779_i("OwnerUUID");
/*     */     }
/*     */     else
/*     */     {
/* 130 */       String s1 = tagCompound.func_74779_i("Owner");
/* 131 */       s = PreYggdrasilConverter.func_152719_a(s1);
/*     */     }
/*     */     
/* 134 */     if (s.length() > 0)
/*     */     {
/* 136 */       setOwnerId(s);
/* 137 */       setOwned(true);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean isOwned()
/*     */   {
/* 145 */     return (this.field_70180_af.func_75683_a(16) & 0x4) != 0;
/*     */   }
/*     */   
/*     */   public void setOwned(boolean tamed)
/*     */   {
/* 150 */     byte b0 = this.field_70180_af.func_75683_a(16);
/*     */     
/* 152 */     if (tamed)
/*     */     {
/* 154 */       this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)(b0 | 0x4)));
/*     */     }
/*     */     else
/*     */     {
/* 158 */       this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)(b0 & 0xFFFFFFFB)));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public String func_152113_b()
/*     */   {
/* 165 */     return this.field_70180_af.func_75681_e(17);
/*     */   }
/*     */   
/*     */   public void setOwnerId(String ownerUuid)
/*     */   {
/* 170 */     this.field_70180_af.func_75692_b(17, ownerUuid);
/*     */   }
/*     */   
/*     */   public EntityLivingBase getOwnerEntity()
/*     */   {
/*     */     try
/*     */     {
/* 177 */       UUID uuid = UUID.fromString(func_152113_b());
/* 178 */       return uuid == null ? null : this.field_70170_p.func_152378_a(uuid);
/*     */     }
/*     */     catch (IllegalArgumentException illegalargumentexception) {}
/*     */     
/* 182 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean isOwner(EntityLivingBase entityIn)
/*     */   {
/* 188 */     return entityIn == getOwnerEntity();
/*     */   }
/*     */   
/*     */ 
/*     */   public Team func_96124_cp()
/*     */   {
/* 194 */     if (isOwned())
/*     */     {
/* 196 */       EntityLivingBase entitylivingbase = getOwnerEntity();
/*     */       
/* 198 */       if (entitylivingbase != null)
/*     */       {
/* 200 */         return entitylivingbase.func_96124_cp();
/*     */       }
/*     */     }
/*     */     
/* 204 */     return super.func_96124_cp();
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_142014_c(EntityLivingBase otherEntity)
/*     */   {
/* 210 */     if (isOwned())
/*     */     {
/* 212 */       EntityLivingBase entitylivingbase1 = getOwnerEntity();
/*     */       
/* 214 */       if (otherEntity == entitylivingbase1)
/*     */       {
/* 216 */         return true;
/*     */       }
/*     */       
/* 219 */       if (entitylivingbase1 != null)
/*     */       {
/* 221 */         return entitylivingbase1.func_142014_c(otherEntity);
/*     */       }
/*     */     }
/*     */     
/* 225 */     return super.func_142014_c(otherEntity);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70645_a(DamageSource cause)
/*     */   {
/* 231 */     if ((!this.field_70170_p.field_72995_K) && (this.field_70170_p.func_82736_K().func_82766_b("showDeathMessages")) && (func_145818_k_()) && ((getOwnerEntity() instanceof EntityPlayerMP))) {
/* 232 */       ((EntityPlayerMP)getOwnerEntity()).func_145747_a(func_110142_aN().func_151521_b());
/*     */     }
/*     */     
/* 235 */     super.func_70645_a(cause);
/*     */   }
/*     */   
/*     */ 
/*     */   public Entity func_70902_q()
/*     */   {
/* 241 */     return getOwnerEntity();
/*     */   }
/*     */   
/*     */   public boolean func_70085_c(EntityPlayer player)
/*     */   {
/* 246 */     if ((player.func_70093_af()) || ((player.func_70694_bm() != null) && ((player.func_70694_bm().func_77973_b() instanceof ItemNameTag)))) return false;
/* 247 */     if ((!this.field_70170_p.field_72995_K) && (!isOwner(player))) {
/* 248 */       player.func_145747_a(new ChatComponentText("§5§o" + StatCollector.func_74838_a("tc.notowned")));
/* 249 */       return true;
/*     */     }
/*     */     
/* 252 */     return super.func_70085_c(player);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/construct/EntityOwnedConstruct.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */