/*     */ package thaumcraft.common.tiles.crafting;
/*     */ 
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.blocks.TileThaumcraft;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ 
/*     */ public class TilePedestal extends TileThaumcraft implements net.minecraft.inventory.ISidedInventory
/*     */ {
/*  20 */   private static final int[] slots = { 0 };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  25 */   private ItemStack[] inventory = new ItemStack[1];
/*     */   
/*     */   private String customName;
/*     */   
/*     */   @SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*     */   public AxisAlignedBB getRenderBoundingBox()
/*     */   {
/*  32 */     return AxisAlignedBB.func_178781_a(func_174877_v().func_177958_n(), func_174877_v().func_177956_o(), func_174877_v().func_177952_p(), func_174877_v().func_177958_n() + 1, func_174877_v().func_177956_o() + 2, func_174877_v().func_177952_p() + 1);
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
/*  43 */     return 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_70301_a(int par1)
/*     */   {
/*  52 */     return this.inventory[par1];
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
/*     */   public ItemStack func_70298_a(int par1, int par2)
/*     */   {
/*  65 */     if (this.inventory[par1] != null)
/*     */     {
/*  67 */       if (!this.field_145850_b.field_72995_K) {
/*  68 */         this.field_145850_b.func_175689_h(this.field_174879_c);
/*     */       }
/*     */       
/*     */ 
/*  72 */       if (this.inventory[par1].field_77994_a <= par2)
/*     */       {
/*  74 */         ItemStack itemstack = this.inventory[par1];
/*  75 */         this.inventory[par1] = null;
/*  76 */         func_70296_d();
/*  77 */         return itemstack;
/*     */       }
/*     */       
/*     */ 
/*  81 */       ItemStack itemstack = this.inventory[par1].func_77979_a(par2);
/*     */       
/*  83 */       if (this.inventory[par1].field_77994_a == 0)
/*     */       {
/*  85 */         this.inventory[par1] = null;
/*     */       }
/*  87 */       func_70296_d();
/*  88 */       return itemstack;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*  93 */     return null;
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
/* 104 */     if (this.inventory[par1] != null)
/*     */     {
/* 106 */       ItemStack itemstack = this.inventory[par1];
/* 107 */       this.inventory[par1] = null;
/* 108 */       return itemstack;
/*     */     }
/*     */     
/*     */ 
/* 112 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70299_a(int par1, ItemStack par2ItemStack)
/*     */   {
/* 122 */     this.inventory[par1] = par2ItemStack;
/*     */     
/* 124 */     if ((par2ItemStack != null) && (par2ItemStack.field_77994_a > func_70297_j_()))
/*     */     {
/* 126 */       par2ItemStack.field_77994_a = func_70297_j_();
/*     */     }
/* 128 */     func_70296_d();
/* 129 */     if (!this.field_145850_b.field_72995_K) {
/* 130 */       this.field_145850_b.func_175689_h(this.field_174879_c);
/*     */     }
/*     */   }
/*     */   
/*     */   public void setInventorySlotContentsFromInfusion(int par1, ItemStack par2ItemStack)
/*     */   {
/* 136 */     this.inventory[par1] = par2ItemStack;
/* 137 */     func_70296_d();
/* 138 */     if (!this.field_145850_b.field_72995_K) {
/* 139 */       this.field_145850_b.func_175689_h(this.field_174879_c);
/*     */     }
/*     */   }
/*     */   
/*     */   public String func_70005_c_()
/*     */   {
/* 145 */     return func_145818_k_() ? this.customName : "container.pedestal";
/*     */   }
/*     */   
/*     */   public boolean func_145818_k_()
/*     */   {
/* 150 */     return (this.customName != null) && (this.customName.length() > 0);
/*     */   }
/*     */   
/*     */   public IChatComponent func_145748_c_()
/*     */   {
/* 155 */     return func_145818_k_() ? new net.minecraft.util.ChatComponentText(func_70005_c_()) : new net.minecraft.util.ChatComponentTranslation(func_70005_c_(), new Object[0]);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void readCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/* 164 */     NBTTagList nbttaglist = nbttagcompound.func_150295_c("Items", 10);
/* 165 */     this.inventory = new ItemStack[func_70302_i_()];
/*     */     
/* 167 */     for (int i = 0; i < nbttaglist.func_74745_c(); i++)
/*     */     {
/* 169 */       NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
/* 170 */       byte b0 = nbttagcompound1.func_74771_c("Slot");
/*     */       
/* 172 */       if ((b0 >= 0) && (b0 < this.inventory.length))
/*     */       {
/* 174 */         this.inventory[b0] = ItemStack.func_77949_a(nbttagcompound1);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void writeCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/* 181 */     NBTTagList nbttaglist = new NBTTagList();
/*     */     
/* 183 */     for (int i = 0; i < this.inventory.length; i++)
/*     */     {
/* 185 */       if (this.inventory[i] != null)
/*     */       {
/* 187 */         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 188 */         nbttagcompound1.func_74774_a("Slot", (byte)i);
/* 189 */         this.inventory[i].func_77955_b(nbttagcompound1);
/* 190 */         nbttaglist.func_74742_a(nbttagcompound1);
/*     */       }
/*     */     }
/*     */     
/* 194 */     nbttagcompound.func_74782_a("Items", nbttaglist);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_145839_a(NBTTagCompound nbtCompound)
/*     */   {
/* 203 */     super.func_145839_a(nbtCompound);
/*     */     
/* 205 */     if (nbtCompound.func_74764_b("CustomName"))
/*     */     {
/* 207 */       this.customName = nbtCompound.func_74779_i("CustomName");
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
/* 218 */     super.func_145841_b(nbtCompound);
/*     */     
/* 220 */     if (func_145818_k_())
/*     */     {
/* 222 */       nbtCompound.func_74778_a("CustomName", this.customName);
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
/* 234 */     return 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_70300_a(EntityPlayer par1EntityPlayer)
/*     */   {
/* 243 */     return this.field_145850_b.func_175625_s(this.field_174879_c) == this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_174889_b(EntityPlayer player) {}
/*     */   
/*     */ 
/*     */   public void func_174886_c(EntityPlayer player) {}
/*     */   
/*     */ 
/*     */   public boolean func_94041_b(int par1, ItemStack par2ItemStack)
/*     */   {
/* 256 */     return true;
/*     */   }
/*     */   
/*     */   public int[] func_180463_a(EnumFacing side)
/*     */   {
/* 261 */     return slots;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_180462_a(int par1, ItemStack par2ItemStack, EnumFacing par3)
/*     */   {
/* 267 */     return func_70301_a(par1) == null;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_180461_b(int par1, ItemStack par2ItemStack, EnumFacing par3)
/*     */   {
/* 273 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_145842_c(int i, int j)
/*     */   {
/* 279 */     if (i == 11)
/*     */     {
/* 281 */       if (this.field_145850_b.field_72995_K) {
/* 282 */         Thaumcraft.proxy.getFX().drawBamf(this.field_174879_c.func_177984_a(), 0.75F, 0.0F, 0.5F, true, true, false);
/*     */       }
/* 284 */       return true;
/*     */     }
/*     */     
/* 287 */     if (i == 12)
/*     */     {
/* 289 */       if (this.field_145850_b.field_72995_K) {
/* 290 */         Thaumcraft.proxy.getFX().drawBamf(this.field_174879_c.func_177984_a(), true, true, false);
/*     */       }
/* 292 */       return true;
/*     */     }
/*     */     
/*     */ 
/* 296 */     return super.func_145842_c(i, j);
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_174887_a_(int id)
/*     */   {
/* 302 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_174885_b(int id, int value) {}
/*     */   
/*     */ 
/*     */ 
/*     */   public int func_174890_g()
/*     */   {
/* 314 */     return 0;
/*     */   }
/*     */   
/*     */   public void func_174888_l() {}
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/tiles/crafting/TilePedestal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */