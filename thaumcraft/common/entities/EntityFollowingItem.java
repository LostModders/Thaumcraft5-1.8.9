/*     */ package thaumcraft.common.entities;
/*     */ 
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ 
/*     */ public class EntityFollowingItem extends EntitySpecialItem implements net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData
/*     */ {
/*  14 */   double targetX = 0.0D;
/*  15 */   double targetY = 0.0D;
/*  16 */   double targetZ = 0.0D;
/*  17 */   int type = 3;
/*  18 */   public Entity target = null;
/*  19 */   int field_70292_b = 20;
/*  20 */   public double gravity = 0.03999999910593033D;
/*     */   
/*     */   public EntityFollowingItem(World par1World, double par2, double par4, double par6, ItemStack par8ItemStack)
/*     */   {
/*  24 */     super(par1World);
/*  25 */     func_70105_a(0.25F, 0.25F);
/*  26 */     func_70107_b(par2, par4, par6);
/*  27 */     func_92058_a(par8ItemStack);
/*  28 */     this.field_70177_z = ((float)(Math.random() * 360.0D));
/*     */   }
/*     */   
/*     */ 
/*     */   public EntityFollowingItem(World par1World, double par2, double par4, double par6, ItemStack par8ItemStack, Entity target, int t)
/*     */   {
/*  34 */     this(par1World, par2, par4, par6, par8ItemStack);
/*  35 */     this.target = target;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  40 */     this.field_70145_X = true;
/*     */   }
/*     */   
/*     */   public EntityFollowingItem(World par1World, double par2, double par4, double par6, ItemStack par8ItemStack, double tx, double ty, double tz)
/*     */   {
/*  45 */     this(par1World, par2, par4, par6, par8ItemStack);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public EntityFollowingItem(World par1World)
/*     */   {
/*  53 */     super(par1World);
/*  54 */     func_70105_a(0.25F, 0.25F);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/*  60 */     if (this.target != null) {
/*  61 */       this.targetX = this.target.field_70165_t;
/*  62 */       this.targetY = (this.target.func_174813_aQ().field_72338_b + this.target.field_70131_O / 2.0F);
/*  63 */       this.targetZ = this.target.field_70161_v;
/*     */     }
/*     */     
/*  66 */     if ((this.targetX != 0.0D) || (this.targetY != 0.0D) || (this.targetZ != 0.0D)) {
/*  67 */       float xd = (float)(this.targetX - this.field_70165_t);
/*  68 */       float yd = (float)(this.targetY - this.field_70163_u);
/*  69 */       float zd = (float)(this.targetZ - this.field_70161_v);
/*  70 */       if (this.field_70292_b > 1) { this.field_70292_b -= 1;
/*     */       }
/*  72 */       double distance = net.minecraft.util.MathHelper.func_76129_c(xd * xd + yd * yd + zd * zd);
/*     */       
/*  74 */       if (distance > 0.5D) {
/*  75 */         distance *= this.field_70292_b;
/*  76 */         this.field_70159_w = (xd / distance);
/*  77 */         this.field_70181_x = (yd / distance);
/*  78 */         this.field_70179_y = (zd / distance);
/*     */       } else {
/*  80 */         this.field_70159_w *= 0.10000000149011612D;
/*  81 */         this.field_70181_x *= 0.10000000149011612D;
/*  82 */         this.field_70179_y *= 0.10000000149011612D;
/*  83 */         this.targetX = 0.0D;
/*  84 */         this.targetY = 0.0D;
/*  85 */         this.targetZ = 0.0D;
/*  86 */         this.target = null;
/*  87 */         this.field_70145_X = false;
/*     */       }
/*  89 */       if (this.field_70170_p.field_72995_K) {
/*  90 */         float h = (float)((func_174813_aQ().field_72337_e - func_174813_aQ().field_72338_b) / 2.0D) + net.minecraft.util.MathHelper.func_76126_a(func_174872_o() / 10.0F + this.field_70290_d) * 0.1F + 0.1F;
/*     */         
/*     */ 
/*  93 */         if (this.type != 10) {
/*  94 */           Thaumcraft.proxy.getFX().sparkle((float)this.field_70169_q + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.125F, (float)this.field_70167_r + h + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.125F, (float)this.field_70166_s + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.125F, this.type);
/*     */ 
/*     */         }
/*     */         else
/*     */         {
/*  99 */           Thaumcraft.proxy.getFX().crucibleBubble((float)this.field_70169_q + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.125F, (float)this.field_70167_r + h + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.125F, (float)this.field_70166_s + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.125F, 0.33F, 0.33F, 1.0F);
/*     */         }
/*     */         
/*     */       }
/*     */       
/*     */     }
/*     */     else
/*     */     {
/* 107 */       this.field_70181_x -= this.gravity;
/*     */     }
/*     */     
/* 110 */     super.func_70071_h_();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70014_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 120 */     super.func_70014_b(par1NBTTagCompound);
/* 121 */     par1NBTTagCompound.func_74777_a("type", (short)this.type);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 129 */     super.func_70037_a(par1NBTTagCompound);
/* 130 */     this.type = par1NBTTagCompound.func_74765_d("type");
/*     */   }
/*     */   
/*     */   public void writeSpawnData(ByteBuf data)
/*     */   {
/* 135 */     if (this.target != null) {
/* 136 */       data.writeInt(this.target == null ? -1 : this.target.func_145782_y());
/* 137 */       data.writeDouble(this.targetX);
/* 138 */       data.writeDouble(this.targetY);
/* 139 */       data.writeDouble(this.targetZ);
/* 140 */       data.writeByte(this.type);
/*     */     }
/*     */   }
/*     */   
/*     */   public void readSpawnData(ByteBuf data)
/*     */   {
/*     */     try {
/* 147 */       int ent = data.readInt();
/* 148 */       if (ent > -1) {
/* 149 */         this.target = this.field_70170_p.func_73045_a(ent);
/*     */       }
/* 151 */       this.targetX = data.readDouble();
/* 152 */       this.targetY = data.readDouble();
/* 153 */       this.targetZ = data.readDouble();
/* 154 */       this.type = data.readByte();
/*     */     }
/*     */     catch (Exception e) {}
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/EntityFollowingItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */