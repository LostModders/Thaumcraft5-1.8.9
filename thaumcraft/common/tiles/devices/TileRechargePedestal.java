/*     */ package thaumcraft.common.tiles.devices;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.util.ITickable;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.aspects.IAspectContainer;
/*     */ import thaumcraft.api.blocks.TileThaumcraft;
/*     */ import thaumcraft.api.items.IRechargable;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ 
/*     */ public class TileRechargePedestal extends TileThaumcraft implements net.minecraft.inventory.ISidedInventory, IAspectContainer, ITickable
/*     */ {
/*  26 */   private static final int[] slots = { 0 };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  31 */   private ItemStack[] inventory = new ItemStack[1];
/*     */   
/*     */   private String customName;
/*     */   
/*     */   @SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*     */   public AxisAlignedBB getRenderBoundingBox()
/*     */   {
/*  38 */     return AxisAlignedBB.func_178781_a(func_174877_v().func_177958_n(), func_174877_v().func_177956_o(), func_174877_v().func_177952_p(), func_174877_v().func_177958_n() + 1, func_174877_v().func_177956_o() + 1, func_174877_v().func_177952_p() + 1).func_72314_b(2.0D, 2.0D, 2.0D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int func_70302_i_()
/*     */   {
/*  49 */     return 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_70301_a(int par1)
/*     */   {
/*  58 */     return this.inventory[par1];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_70298_a(int par1, int par2)
/*     */   {
/*  70 */     if (this.inventory[par1] != null)
/*     */     {
/*     */ 
/*  73 */       this.field_145850_b.func_175689_h(this.field_174879_c);
/*  74 */       if (this.inventory[par1].field_77994_a <= par2)
/*     */       {
/*  76 */         ItemStack itemstack = this.inventory[par1];
/*  77 */         this.inventory[par1] = null;
/*  78 */         func_70296_d();
/*  79 */         return itemstack;
/*     */       }
/*     */       
/*     */ 
/*  83 */       ItemStack itemstack = this.inventory[par1].func_77979_a(par2);
/*     */       
/*  85 */       if (this.inventory[par1].field_77994_a == 0)
/*     */       {
/*  87 */         this.inventory[par1] = null;
/*     */       }
/*  89 */       func_70296_d();
/*  90 */       return itemstack;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*  95 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_70304_b(int par1)
/*     */   {
/* 106 */     if (this.inventory[par1] != null)
/*     */     {
/* 108 */       ItemStack itemstack = this.inventory[par1];
/* 109 */       this.inventory[par1] = null;
/* 110 */       func_70296_d();
/* 111 */       return itemstack;
/*     */     }
/*     */     
/*     */ 
/* 115 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70299_a(int par1, ItemStack par2ItemStack)
/*     */   {
/* 125 */     this.inventory[par1] = par2ItemStack;
/*     */     
/* 127 */     if ((par2ItemStack != null) && (par2ItemStack.field_77994_a > func_70297_j_()))
/*     */     {
/* 129 */       par2ItemStack.field_77994_a = func_70297_j_();
/*     */     }
/* 131 */     func_70296_d();
/* 132 */     this.field_145850_b.func_175689_h(this.field_174879_c);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_174889_b(EntityPlayer player) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_174886_c(EntityPlayer player) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int func_174887_a_(int id)
/*     */   {
/* 153 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_174885_b(int id, int value) {}
/*     */   
/*     */ 
/*     */   public int func_174890_g()
/*     */   {
/* 162 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_174888_l() {}
/*     */   
/*     */ 
/*     */   public String func_70005_c_()
/*     */   {
/* 171 */     return func_145818_k_() ? this.customName : "container.rechargepedestal";
/*     */   }
/*     */   
/*     */   public boolean func_145818_k_()
/*     */   {
/* 176 */     return (this.customName != null) && (this.customName.length() > 0);
/*     */   }
/*     */   
/*     */   public IChatComponent func_145748_c_()
/*     */   {
/* 181 */     return func_145818_k_() ? new ChatComponentText(func_70005_c_()) : new net.minecraft.util.ChatComponentTranslation(func_70005_c_(), new Object[0]);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void readCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/* 188 */     NBTTagList nbttaglist = nbttagcompound.func_150295_c("Items", 10);
/* 189 */     this.inventory = new ItemStack[func_70302_i_()];
/*     */     
/* 191 */     for (int i = 0; i < nbttaglist.func_74745_c(); i++)
/*     */     {
/* 193 */       NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
/* 194 */       byte b0 = nbttagcompound1.func_74771_c("Slot");
/*     */       
/* 196 */       if ((b0 >= 0) && (b0 < this.inventory.length))
/*     */       {
/* 198 */         this.inventory[b0] = ItemStack.func_77949_a(nbttagcompound1);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void writeCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/* 205 */     NBTTagList nbttaglist = new NBTTagList();
/*     */     
/* 207 */     for (int i = 0; i < this.inventory.length; i++)
/*     */     {
/* 209 */       if (this.inventory[i] != null)
/*     */       {
/* 211 */         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 212 */         nbttagcompound1.func_74774_a("Slot", (byte)i);
/* 213 */         this.inventory[i].func_77955_b(nbttagcompound1);
/* 214 */         nbttaglist.func_74742_a(nbttagcompound1);
/*     */       }
/*     */     }
/*     */     
/* 218 */     nbttagcompound.func_74782_a("Items", nbttaglist);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_145839_a(NBTTagCompound nbtCompound)
/*     */   {
/* 227 */     super.func_145839_a(nbtCompound);
/*     */     
/* 229 */     if (nbtCompound.func_74764_b("CustomName"))
/*     */     {
/* 231 */       this.customName = nbtCompound.func_74779_i("CustomName");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_145841_b(NBTTagCompound nbtCompound)
/*     */   {
/* 242 */     super.func_145841_b(nbtCompound);
/*     */     
/* 244 */     if (func_145818_k_())
/*     */     {
/* 246 */       nbtCompound.func_74778_a("CustomName", this.customName);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int func_70297_j_()
/*     */   {
/* 258 */     return 1;
/*     */   }
/*     */   
/*     */ 
/* 262 */   int counter = 0;
/*     */   
/*     */   public void func_73660_a()
/*     */   {
/* 266 */     if ((!func_145831_w().field_72995_K) && (this.counter++ % 10 == 0) && (func_70301_a(0) != null) && ((func_70301_a(0).func_77973_b() instanceof IRechargable))) {
/* 267 */       IRechargable wand = (IRechargable)func_70301_a(0).func_77973_b();
/* 268 */       Aspect aspect = wand.handleRecharge(func_145831_w(), func_70301_a(0), this.field_174879_c, null, 5);
/* 269 */       this.field_145850_b.func_175689_h(func_174877_v());
/* 270 */       func_70296_d();
/* 271 */       if (aspect != null) {
/* 272 */         this.field_145850_b.func_175641_c(this.field_174879_c, func_145838_q(), 5, aspect.getColor());
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_70300_a(EntityPlayer par1EntityPlayer)
/*     */   {
/* 282 */     return this.field_145850_b.func_175625_s(this.field_174879_c) == this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_94041_b(int par1, ItemStack stack)
/*     */   {
/* 291 */     return (stack != null) && ((stack.func_77973_b() instanceof IRechargable));
/*     */   }
/*     */   
/*     */   public int[] func_180463_a(EnumFacing side)
/*     */   {
/* 296 */     return slots;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_180462_a(int par1, ItemStack stack, EnumFacing par3)
/*     */   {
/* 302 */     return (func_70301_a(par1) == null) && ((stack.func_77973_b() instanceof IRechargable));
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_180461_b(int par1, ItemStack par2ItemStack, EnumFacing par3)
/*     */   {
/* 308 */     return true;
/*     */   }
/*     */   
/*     */   public AspectList getAspects()
/*     */   {
/* 313 */     if ((func_70301_a(0) != null) && ((func_70301_a(0).func_77973_b() instanceof IRechargable))) {
/* 314 */       IRechargable wand = (IRechargable)func_70301_a(0).func_77973_b();
/* 315 */       return wand.getAspectsInChargable(func_70301_a(0));
/*     */     }
/* 317 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setAspects(AspectList aspects) {}
/*     */   
/*     */ 
/*     */ 
/*     */   public int addToContainer(Aspect tag, int amount)
/*     */   {
/* 329 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean takeFromContainer(Aspect tag, int amount)
/*     */   {
/* 335 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean takeFromContainer(AspectList ot)
/*     */   {
/* 341 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean doesContainerContainAmount(Aspect tag, int amount)
/*     */   {
/* 347 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean doesContainerContain(AspectList ot)
/*     */   {
/* 353 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public int containerContains(Aspect tag)
/*     */   {
/* 359 */     return 0;
/*     */   }
/*     */   
/*     */   public boolean doesContainerAccept(Aspect tag)
/*     */   {
/* 364 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_145842_c(int i, int j)
/*     */   {
/* 370 */     if (i == 5)
/*     */     {
/* 372 */       if (this.field_145850_b.field_72995_K) {
/* 373 */         Thaumcraft.proxy.getFX().visSparkle(this.field_174879_c.func_177958_n() + func_145831_w().field_73012_v.nextInt(3) - func_145831_w().field_73012_v.nextInt(3), this.field_174879_c.func_177984_a().func_177956_o() + func_145831_w().field_73012_v.nextInt(3), this.field_174879_c.func_177952_p() + func_145831_w().field_73012_v.nextInt(3) - func_145831_w().field_73012_v.nextInt(3), this.field_174879_c.func_177958_n(), this.field_174879_c.func_177984_a().func_177956_o(), this.field_174879_c.func_177952_p(), j);
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 379 */       return true;
/*     */     }
/*     */     
/* 382 */     return super.func_145842_c(i, j);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/tiles/devices/TileRechargePedestal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */