/*     */ package thaumcraft.common.entities.construct.golem.seals;
/*     */ 
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.golems.seals.ISeal;
/*     */ import thaumcraft.api.golems.seals.ISealConfigFilter;
/*     */ import thaumcraft.api.golems.seals.ISealEntity;
/*     */ import thaumcraft.api.golems.seals.ISealGui;
/*     */ import thaumcraft.common.entities.construct.golem.gui.SealBaseContainer;
/*     */ import thaumcraft.common.entities.construct.golem.gui.SealBaseGUI;
/*     */ 
/*     */ public abstract class SealFiltered implements ISeal, ISealGui, ISealConfigFilter
/*     */ {
/*     */   public void readCustomNBT(NBTTagCompound nbt)
/*     */   {
/*  23 */     NBTTagList nbttaglist = nbt.func_150295_c("Items", 10);
/*  24 */     this.filter = new ItemStack[getFilterSize()];
/*  25 */     for (int i = 0; i < nbttaglist.func_74745_c(); i++)
/*     */     {
/*  27 */       NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
/*  28 */       byte b0 = nbttagcompound1.func_74771_c("Slot");
/*     */       
/*  30 */       if ((b0 >= 0) && (b0 < this.filter.length))
/*     */       {
/*  32 */         this.filter[b0] = ItemStack.func_77949_a(nbttagcompound1);
/*     */       }
/*     */     }
/*  35 */     this.blacklist = nbt.func_74767_n("bl");
/*     */   }
/*     */   
/*     */   public void writeCustomNBT(NBTTagCompound nbt)
/*     */   {
/*  40 */     NBTTagList nbttaglist = new NBTTagList();
/*  41 */     for (int i = 0; i < this.filter.length; i++)
/*     */     {
/*  43 */       if (this.filter[i] != null)
/*     */       {
/*  45 */         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/*  46 */         nbttagcompound1.func_74774_a("Slot", (byte)i);
/*  47 */         this.filter[i].func_77955_b(nbttagcompound1);
/*  48 */         nbttaglist.func_74742_a(nbttagcompound1);
/*     */       }
/*     */     }
/*  51 */     nbt.func_74782_a("Items", nbttaglist);
/*  52 */     nbt.func_74757_a("bl", this.blacklist);
/*     */   }
/*     */   
/*     */   public Object returnContainer(World world, EntityPlayer player, BlockPos pos, EnumFacing side, ISealEntity seal)
/*     */   {
/*  57 */     return new SealBaseContainer(player.field_71071_by, world, seal);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public Object returnGui(World world, EntityPlayer player, BlockPos pos, EnumFacing side, ISealEntity seal)
/*     */   {
/*  63 */     return new SealBaseGUI(player.field_71071_by, world, seal);
/*     */   }
/*     */   
/*     */   public int[] getGuiCategories() {
/*  67 */     return new int[] { 0 };
/*     */   }
/*     */   
/*     */   public int getFilterSize() {
/*  71 */     return 1;
/*     */   }
/*     */   
/*  74 */   ItemStack[] filter = new ItemStack[getFilterSize()];
/*     */   
/*     */   public ItemStack[] getInv() {
/*  77 */     return this.filter;
/*     */   }
/*     */   
/*     */   public ItemStack getFilterSlot(int i)
/*     */   {
/*  82 */     return this.filter[i];
/*     */   }
/*     */   
/*     */   public void setFilterSlot(int i, ItemStack stack)
/*     */   {
/*  87 */     this.filter[i] = (stack == null ? null : stack.func_77946_l());
/*     */   }
/*     */   
/*  90 */   boolean blacklist = true;
/*     */   
/*     */   public boolean isBlacklist()
/*     */   {
/*  94 */     return this.blacklist;
/*     */   }
/*     */   
/*     */   public void setBlacklist(boolean black)
/*     */   {
/*  99 */     this.blacklist = black;
/*     */   }
/*     */   
/*     */   public boolean hasStacksizeLimiters()
/*     */   {
/* 104 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/construct/golem/seals/SealFiltered.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */