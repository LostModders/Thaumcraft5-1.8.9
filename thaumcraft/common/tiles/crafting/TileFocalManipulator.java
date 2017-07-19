/*     */ package thaumcraft.common.tiles.crafting;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.wands.FocusUpgradeType;
/*     */ import thaumcraft.api.wands.ItemFocusBasic;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.tiles.TileThaumcraftInventory;
/*     */ 
/*     */ public class TileFocalManipulator extends TileThaumcraftInventory implements net.minecraft.util.ITickable
/*     */ {
/*  22 */   public AspectList aspects = new AspectList();
/*  23 */   public int size = 0;
/*  24 */   public int upgrade = -1;
/*  25 */   public int rank = -1;
/*     */   
/*     */   public TileFocalManipulator()
/*     */   {
/*  29 */     this.itemStacks = new ItemStack[1];
/*  30 */     this.syncedSlots = new int[] { 0 };
/*     */   }
/*     */   
/*     */   public void readCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/*  35 */     this.itemStacks = new ItemStack[1];
/*  36 */     this.syncedSlots = new int[] { 0 };
/*  37 */     super.readCustomNBT(nbttagcompound);
/*  38 */     this.aspects.readFromNBT(nbttagcompound);
/*  39 */     this.size = nbttagcompound.func_74762_e("size");
/*  40 */     this.upgrade = nbttagcompound.func_74762_e("upgrade");
/*  41 */     this.rank = nbttagcompound.func_74762_e("rank");
/*     */   }
/*     */   
/*     */   public void writeCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/*  46 */     super.writeCustomNBT(nbttagcompound);
/*  47 */     this.aspects.writeToNBT(nbttagcompound);
/*  48 */     nbttagcompound.func_74768_a("size", this.size);
/*  49 */     nbttagcompound.func_74768_a("upgrade", this.upgrade);
/*  50 */     nbttagcompound.func_74768_a("rank", this.rank);
/*     */   }
/*     */   
/*     */   @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*     */   public AxisAlignedBB getRenderBoundingBox()
/*     */   {
/*  56 */     return AxisAlignedBB.func_178781_a(this.field_174879_c.func_177958_n(), this.field_174879_c.func_177956_o(), this.field_174879_c.func_177952_p(), this.field_174879_c.func_177958_n() + 1, this.field_174879_c.func_177956_o() + 1, this.field_174879_c.func_177952_p() + 1);
/*     */   }
/*     */   
/*  59 */   int ticks = 0;
/*     */   
/*  61 */   public boolean reset = false;
/*     */   public static final int XP_MULT = 6;
/*     */   public static final int VIS_MULT = 100;
/*     */   
/*  65 */   public void func_70299_a(int par1, ItemStack par2ItemStack) { super.func_70299_a(par1, par2ItemStack);
/*     */     
/*  67 */     if (this.field_145850_b.field_72995_K) {
/*  68 */       this.reset = true;
/*     */     } else {
/*  70 */       this.aspects = new AspectList();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_73660_a()
/*     */   {
/*  77 */     boolean complete = false;
/*  78 */     if (!this.field_145850_b.field_72995_K)
/*     */     {
/*  80 */       if (this.rank < 0) {
/*  81 */         this.rank = 0;
/*     */       }
/*  83 */       this.ticks += 1;
/*  84 */       if (this.ticks % 5 == 0) {
/*  85 */         if ((this.size > 0) && ((this.aspects.visSize() <= 0) || (func_70301_a(0) == null))) {
/*  86 */           complete = true;
/*  87 */           this.field_145850_b.func_72908_a(this.field_174879_c.func_177958_n(), this.field_174879_c.func_177956_o(), this.field_174879_c.func_177952_p(), "thaumcraft:craftfail", 0.33F, 1.0F);
/*     */         }
/*     */         
/*  90 */         if ((!complete) && (this.size > 0)) {
/*  91 */           for (Aspect aspect : this.aspects.getAspectsSortedByAmount()) {
/*  92 */             int amt = Math.min(5, this.aspects.getAmount(aspect));
/*  93 */             if (amt > 0) {
/*  94 */               amt = thaumcraft.common.lib.aura.AuraHandler.drainAuraAvailable(this.field_145850_b, this.field_174879_c, aspect, amt);
/*  95 */               if (amt > 0) {
/*  96 */                 this.field_145850_b.func_175641_c(this.field_174879_c, func_145838_q(), 5, aspect.getColor());
/*  97 */                 this.aspects.reduce(aspect, amt);
/*  98 */                 this.field_145850_b.func_175689_h(this.field_174879_c);
/*  99 */                 func_70296_d();
/*     */               }
/*     */             }
/*     */           }
/* 103 */           if ((this.aspects.visSize() <= 0) && (func_70301_a(0) != null)) {
/* 104 */             complete = true;
/* 105 */             ItemFocusBasic focus = (ItemFocusBasic)func_70301_a(0).func_77973_b();
/* 106 */             boolean b = focus.applyUpgrade(func_70301_a(0), FocusUpgradeType.types[this.upgrade], this.rank);
/* 107 */             this.field_145850_b.func_72908_a(this.field_174879_c.func_177958_n(), this.field_174879_c.func_177956_o(), this.field_174879_c.func_177952_p(), "thaumcraft:wand", 1.0F, 1.0F);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 112 */     else if (this.size > 0) {
/* 113 */       Thaumcraft.proxy.getFX().drawGenericParticles(this.field_174879_c.func_177958_n() + 0.5D + (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.3F, this.field_174879_c.func_177956_o() + 1.4D + (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.3F, this.field_174879_c.func_177952_p() + 0.5D + (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.3F, 0.0D, 0.0D, 0.0D, 0.5F + this.field_145850_b.field_73012_v.nextFloat() * 0.4F, 1.0F - this.field_145850_b.field_73012_v.nextFloat() * 0.4F, 1.0F - this.field_145850_b.field_73012_v.nextFloat() * 0.4F, 0.8F, false, 112, 9, 1, 6 + this.field_145850_b.field_73012_v.nextInt(5), 0, 0.5F + this.field_145850_b.field_73012_v.nextFloat() * 0.3F, 0.0F, 0);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 127 */     if (complete)
/*     */     {
/* 129 */       this.size = 0;
/* 130 */       this.rank = -1;
/* 131 */       this.aspects = new AspectList();
/* 132 */       this.field_145850_b.func_175689_h(this.field_174879_c);
/* 133 */       func_70296_d();
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean startCraft(int id, EntityPlayer p) {
/* 138 */     if ((this.size > 0) || (func_70301_a(0) == null) || (!(func_70301_a(0).func_77973_b() instanceof ItemFocusBasic))) { return false;
/*     */     }
/* 140 */     ItemFocusBasic focus = (ItemFocusBasic)func_70301_a(0).func_77973_b();
/* 141 */     short[] s = focus.getAppliedUpgrades(func_70301_a(0));
/* 142 */     this.rank = 1;
/* 143 */     while ((this.rank <= 5) && 
/* 144 */       (s[(this.rank - 1)] != -1)) {
/* 143 */       this.rank += 1;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 149 */     int xp = this.rank * 6;
/* 150 */     if ((!p.field_71075_bZ.field_75098_d) && (p.field_71068_ca < xp)) { return false;
/*     */     }
/* 152 */     FocusUpgradeType[] ut = focus.getPossibleUpgradesByRank(func_70301_a(0), this.rank);
/* 153 */     if (ut == null) return false;
/* 154 */     boolean b = false;
/* 155 */     for (int a = 0; a < ut.length; a++) {
/* 156 */       if (ut[a].id == id) {
/* 157 */         b = true;
/* 158 */         break;
/*     */       }
/*     */     }
/* 161 */     if (!b) { return false;
/*     */     }
/* 163 */     if ((id > FocusUpgradeType.types.length - 1) || (FocusUpgradeType.types[id] == null) || (!focus.canApplyUpgrade(func_70301_a(0), p, FocusUpgradeType.types[id], this.rank))) {
/* 164 */       return false;
/*     */     }
/* 166 */     int amt = 100;
/* 167 */     for (int a = 1; a < this.rank; a++) amt = (int)(amt * 1.5D);
/* 168 */     AspectList tal = new AspectList();
/* 169 */     for (Aspect as : FocusUpgradeType.types[id].aspects.getAspects()) {
/* 170 */       tal.add(as, amt);
/*     */     }
/* 172 */     this.aspects = thaumcraft.api.aspects.AspectHelper.reduceToPrimals(tal);
/* 173 */     this.size = this.aspects.visSize();
/* 174 */     this.upgrade = id;
/* 175 */     if (!p.field_71075_bZ.field_75098_d) p.func_71013_b(xp);
/* 176 */     func_70296_d();
/* 177 */     this.field_145850_b.func_175689_h(this.field_174879_c);
/* 178 */     this.field_145850_b.func_72908_a(this.field_174879_c.func_177958_n(), this.field_174879_c.func_177956_o(), this.field_174879_c.func_177952_p(), "thaumcraft:craftstart", 0.25F, 1.0F);
/* 179 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_94041_b(int par1, ItemStack par2ItemStack)
/*     */   {
/* 188 */     if ((par2ItemStack != null) && ((par2ItemStack.func_77973_b() instanceof ItemFocusBasic)))
/* 189 */       return true;
/* 190 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_145842_c(int i, int j)
/*     */   {
/* 196 */     if (i == 5)
/*     */     {
/* 198 */       if (this.field_145850_b.field_72995_K) {
/* 199 */         Thaumcraft.proxy.getFX().visSparkle(this.field_174879_c.func_177958_n() + func_145831_w().field_73012_v.nextInt(3) - func_145831_w().field_73012_v.nextInt(3), this.field_174879_c.func_177956_o() + func_145831_w().field_73012_v.nextInt(3), this.field_174879_c.func_177952_p() + func_145831_w().field_73012_v.nextInt(3) - func_145831_w().field_73012_v.nextInt(3), this.field_174879_c.func_177958_n(), this.field_174879_c.func_177956_o() + 1, this.field_174879_c.func_177952_p(), j);
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 205 */       return true;
/*     */     }
/*     */     
/* 208 */     return super.func_145842_c(i, j);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/tiles/crafting/TileFocalManipulator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */