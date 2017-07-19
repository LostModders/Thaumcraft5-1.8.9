/*     */ package thaumcraft.common.tiles;
/*     */ 
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.ISidedInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.ChatComponentTranslation;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.blocks.TileThaumcraft;
/*     */ 
/*     */ public class TileThaumcraftInventory extends TileThaumcraft implements ISidedInventory
/*     */ {
/*  17 */   protected ItemStack[] itemStacks = new ItemStack[1];
/*     */   protected String customName;
/*  19 */   protected int[] syncedSlots = new int[0];
/*     */   
/*     */ 
/*     */   public int func_70302_i_()
/*     */   {
/*  24 */     return this.itemStacks.length;
/*     */   }
/*     */   
/*     */ 
/*     */   public ItemStack func_70301_a(int par1)
/*     */   {
/*  30 */     return this.itemStacks[par1];
/*     */   }
/*     */   
/*     */ 
/*     */   public ItemStack func_70298_a(int par1, int par2)
/*     */   {
/*  36 */     if (this.itemStacks[par1] != null)
/*     */     {
/*     */ 
/*     */ 
/*  40 */       if (this.itemStacks[par1].field_77994_a <= par2)
/*     */       {
/*  42 */         ItemStack itemstack = this.itemStacks[par1];
/*  43 */         this.itemStacks[par1] = null;
/*  44 */         func_70296_d();
/*  45 */         return itemstack;
/*     */       }
/*     */       
/*     */ 
/*  49 */       ItemStack itemstack = this.itemStacks[par1].func_77979_a(par2);
/*     */       
/*  51 */       if (this.itemStacks[par1].field_77994_a == 0)
/*     */       {
/*  53 */         this.itemStacks[par1] = null;
/*     */       }
/*  55 */       func_70296_d();
/*  56 */       return itemstack;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*  61 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ItemStack func_70304_b(int par1)
/*     */   {
/*  68 */     if (this.itemStacks[par1] != null)
/*     */     {
/*  70 */       ItemStack itemstack = this.itemStacks[par1];
/*  71 */       this.itemStacks[par1] = null;
/*  72 */       func_70296_d();
/*  73 */       return itemstack;
/*     */     }
/*     */     
/*     */ 
/*  77 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_70299_a(int par1, ItemStack par2ItemStack)
/*     */   {
/*  84 */     this.itemStacks[par1] = par2ItemStack;
/*     */     
/*  86 */     if ((par2ItemStack != null) && (par2ItemStack.field_77994_a > func_70297_j_()))
/*     */     {
/*  88 */       par2ItemStack.field_77994_a = func_70297_j_();
/*     */     }
/*  90 */     func_70296_d();
/*     */   }
/*     */   
/*     */ 
/*     */   public String func_70005_c_()
/*     */   {
/*  96 */     return func_145818_k_() ? this.customName : "container.thaumcraft";
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_145818_k_()
/*     */   {
/* 102 */     return (this.customName != null) && (this.customName.length() > 0);
/*     */   }
/*     */   
/*     */   public IChatComponent func_145748_c_()
/*     */   {
/* 107 */     return func_145818_k_() ? new ChatComponentText(func_70005_c_()) : new ChatComponentTranslation(func_70005_c_(), new Object[0]);
/*     */   }
/*     */   
/*     */ 
/*     */   private boolean isSyncedSlot(int slot)
/*     */   {
/* 113 */     for (int s : this.syncedSlots) {
/* 114 */       if (s == slot) return true;
/*     */     }
/* 116 */     return false;
/*     */   }
/*     */   
/*     */   public void readCustomNBT(NBTTagCompound nbtCompound)
/*     */   {
/* 121 */     NBTTagList nbttaglist = nbtCompound.func_150295_c("ItemsSynced", 10);
/* 122 */     this.itemStacks = new ItemStack[func_70302_i_()];
/* 123 */     for (int i = 0; i < nbttaglist.func_74745_c(); i++)
/*     */     {
/* 125 */       if (isSyncedSlot(i)) {
/* 126 */         NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
/* 127 */         byte b0 = nbttagcompound1.func_74771_c("Slot");
/*     */         
/* 129 */         if ((b0 >= 0) && (b0 < this.itemStacks.length))
/*     */         {
/* 131 */           this.itemStacks[b0] = ItemStack.func_77949_a(nbttagcompound1);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void writeCustomNBT(NBTTagCompound nbtCompound)
/*     */   {
/* 139 */     NBTTagList nbttaglist = new NBTTagList();
/* 140 */     for (int i = 0; i < this.itemStacks.length; i++)
/*     */     {
/* 142 */       if ((this.itemStacks[i] != null) && (isSyncedSlot(i)))
/*     */       {
/* 144 */         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 145 */         nbttagcompound1.func_74774_a("Slot", (byte)i);
/* 146 */         this.itemStacks[i].func_77955_b(nbttagcompound1);
/* 147 */         nbttaglist.func_74742_a(nbttagcompound1);
/*     */       }
/*     */     }
/* 150 */     nbtCompound.func_74782_a("ItemsSynced", nbttaglist);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_145839_a(NBTTagCompound nbtCompound)
/*     */   {
/* 156 */     super.func_145839_a(nbtCompound);
/*     */     
/* 158 */     if (nbtCompound.func_74764_b("CustomName"))
/*     */     {
/* 160 */       this.customName = nbtCompound.func_74779_i("CustomName");
/*     */     }
/*     */     
/* 163 */     NBTTagList nbttaglist = nbtCompound.func_150295_c("Items", 10);
/* 164 */     for (int i = 0; i < nbttaglist.func_74745_c(); i++)
/*     */     {
/* 166 */       if (!isSyncedSlot(i)) {
/* 167 */         NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
/* 168 */         byte b0 = nbttagcompound1.func_74771_c("Slot");
/*     */         
/* 170 */         if ((b0 >= 0) && (b0 < this.itemStacks.length))
/*     */         {
/* 172 */           this.itemStacks[b0] = ItemStack.func_77949_a(nbttagcompound1);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_145841_b(NBTTagCompound nbtCompound)
/*     */   {
/* 182 */     super.func_145841_b(nbtCompound);
/*     */     
/* 184 */     if (func_145818_k_())
/*     */     {
/* 186 */       nbtCompound.func_74778_a("CustomName", this.customName);
/*     */     }
/*     */     
/* 189 */     NBTTagList nbttaglist = new NBTTagList();
/* 190 */     for (int i = 0; i < this.itemStacks.length; i++)
/*     */     {
/* 192 */       if ((this.itemStacks[i] != null) && (!isSyncedSlot(i)))
/*     */       {
/* 194 */         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 195 */         nbttagcompound1.func_74774_a("Slot", (byte)i);
/* 196 */         this.itemStacks[i].func_77955_b(nbttagcompound1);
/* 197 */         nbttaglist.func_74742_a(nbttagcompound1);
/*     */       }
/*     */     }
/* 200 */     nbtCompound.func_74782_a("Items", nbttaglist);
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_70297_j_()
/*     */   {
/* 206 */     return 64;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70300_a(EntityPlayer par1EntityPlayer)
/*     */   {
/* 212 */     return this.field_145850_b.func_175625_s(func_174877_v()) == this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_94041_b(int par1, ItemStack par2ItemStack)
/*     */   {
/* 219 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public int[] func_180463_a(EnumFacing par1)
/*     */   {
/* 225 */     return new int[] { 0 };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_174889_b(EntityPlayer player) {}
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_174886_c(EntityPlayer player) {}
/*     */   
/*     */ 
/*     */ 
/*     */   public int func_174887_a_(int id)
/*     */   {
/* 240 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_174885_b(int id, int value) {}
/*     */   
/*     */   public int func_174890_g()
/*     */   {
/* 248 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_174888_l() {}
/*     */   
/*     */ 
/*     */   public boolean func_180462_a(int par1, ItemStack par2ItemStack, EnumFacing par3)
/*     */   {
/* 258 */     return func_94041_b(par1, par2ItemStack);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_180461_b(int par1, ItemStack par2ItemStack, EnumFacing par3)
/*     */   {
/* 264 */     return true;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/tiles/TileThaumcraftInventory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */