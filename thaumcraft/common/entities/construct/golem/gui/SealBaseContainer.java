/*     */ package thaumcraft.common.entities.construct.golem.gui;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.ICrafting;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.golems.seals.ISealConfigArea;
/*     */ import thaumcraft.api.golems.seals.ISealConfigFilter;
/*     */ import thaumcraft.api.golems.seals.ISealConfigToggles;
/*     */ import thaumcraft.api.golems.seals.ISealEntity;
/*     */ import thaumcraft.api.golems.seals.ISealGui;
/*     */ import thaumcraft.common.container.InventoryFake;
/*     */ import thaumcraft.common.container.SlotGhost;
/*     */ 
/*     */ public class SealBaseContainer extends Container
/*     */ {
/*     */   private World worldObj;
/*  26 */   ISealEntity seal = null;
/*  27 */   EntityPlayer player = null;
/*     */   InventoryFake temp;
/*     */   int[] categories;
/*  30 */   int category = -1;
/*     */   InventoryPlayer pinv;
/*     */   
/*     */   public SealBaseContainer(InventoryPlayer iinventory, World par2World, ISealEntity seal)
/*     */   {
/*  35 */     this.worldObj = par2World;
/*  36 */     this.player = iinventory.field_70458_d;
/*  37 */     this.pinv = iinventory;
/*  38 */     this.seal = seal;
/*     */     
/*  40 */     if ((seal.getSeal() instanceof ISealGui)) {
/*  41 */       this.categories = ((ISealGui)seal.getSeal()).getGuiCategories();
/*     */     } else {
/*  43 */       this.categories = new int[] { 0 };
/*     */     }
/*     */     
/*  46 */     setupCategories();
/*     */   }
/*     */   
/*     */   void setupCategories()
/*     */   {
/*  51 */     this.field_75153_a = Lists.newArrayList();
/*  52 */     this.field_75151_b = Lists.newArrayList();
/*     */     
/*  54 */     this.t = 0;
/*     */     
/*  56 */     if (this.category < 0) { this.category = this.categories[0];
/*     */     }
/*  58 */     switch (this.category) {
/*     */     case 1: 
/*  60 */       setupFilterInventory();
/*     */     }
/*     */     
/*     */     
/*  64 */     bindPlayerInventory(this.pinv);
/*     */   }
/*     */   
/*  67 */   int t = 0;
/*     */   private byte lastPriority;
/*     */   
/*  70 */   private void setupFilterInventory() { if ((this.seal.getSeal() instanceof ISealConfigFilter)) {
/*  71 */       int s = ((ISealConfigFilter)this.seal.getSeal()).getFilterSize();
/*  72 */       int sx = 16 + (s - 1) % 3 * 12;
/*  73 */       int sy = 16 + (s - 1) / 3 * 12;
/*  74 */       int middleX = 88;
/*  75 */       int middleY = 72;
/*  76 */       this.temp = new InventoryFake(((ISealConfigFilter)this.seal.getSeal()).getInv());
/*  77 */       for (int a = 0; a < s; a++) {
/*  78 */         int x = a % 3;
/*  79 */         int y = a / 3;
/*  80 */         func_75146_a(new SlotGhost(this.temp, a, middleX + x * 24 - sx + 8, middleY + y * 24 - sy + 8));
/*  81 */         this.t += 1;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
/*  87 */     for (int i = 0; i < 3; i++) {
/*  88 */       for (int j = 0; j < 9; j++) {
/*  89 */         func_75146_a(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 150 + i * 18));
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*  94 */     for (int i = 0; i < 9; i++) {
/*  95 */       func_75146_a(new Slot(inventoryPlayer, i, 8 + i * 18, 208));
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean func_75145_c(EntityPlayer var1)
/*     */   {
/* 101 */     return true;
/*     */   }
/*     */   
/*     */   public boolean func_75140_a(EntityPlayer player, int par2)
/*     */   {
/* 106 */     if ((par2 >= 0) && (par2 < this.categories.length)) {
/* 107 */       this.category = this.categories[par2];
/* 108 */       setupCategories();
/* 109 */       return true;
/*     */     }
/*     */     
/* 112 */     if ((this.category == 3) && ((this.seal.getSeal() instanceof ISealConfigToggles)) && (par2 >= 30) && (par2 < 30 + ((ISealConfigToggles)this.seal.getSeal()).getToggles().length))
/*     */     {
/* 114 */       ISealConfigToggles cp = (ISealConfigToggles)this.seal.getSeal();
/* 115 */       cp.setToggle(par2 - 30, true);
/* 116 */       return true;
/*     */     }
/*     */     
/* 119 */     if ((this.category == 3) && ((this.seal.getSeal() instanceof ISealConfigToggles)) && (par2 >= 60) && (par2 < 60 + ((ISealConfigToggles)this.seal.getSeal()).getToggles().length))
/*     */     {
/* 121 */       ISealConfigToggles cp = (ISealConfigToggles)this.seal.getSeal();
/* 122 */       cp.setToggle(par2 - 60, false);
/* 123 */       return true;
/*     */     }
/*     */     
/* 126 */     if ((this.category == 0) && (par2 >= 25) && (par2 <= 26)) {
/* 127 */       this.seal.setLocked(par2 == 25);
/* 128 */       return true;
/*     */     }
/*     */     
/* 131 */     if ((par2 >= 27) && (par2 <= 28)) {
/* 132 */       this.seal.setRedstoneSensitive(par2 == 27);
/* 133 */       return true;
/*     */     }
/*     */     
/* 136 */     if ((this.category == 1) && ((this.seal.getSeal() instanceof ISealConfigFilter)) && (par2 >= 20) && (par2 <= 21)) {
/* 137 */       ISealConfigFilter cp = (ISealConfigFilter)this.seal.getSeal();
/* 138 */       cp.setBlacklist(par2 == 20);
/* 139 */       return true;
/*     */     }
/*     */     
/* 142 */     if ((par2 == 80) && (this.seal.getPriority() > -5)) {
/* 143 */       this.seal.setPriority((byte)(this.seal.getPriority() - 1));
/* 144 */       return true;
/*     */     }
/* 146 */     if ((par2 == 81) && (this.seal.getPriority() < 5)) {
/* 147 */       this.seal.setPriority((byte)(this.seal.getPriority() + 1));
/* 148 */       return true;
/*     */     }
/*     */     
/* 151 */     if ((par2 == 82) && (this.seal.getColor() > 0)) {
/* 152 */       this.seal.setColor((byte)(this.seal.getColor() - 1));
/* 153 */       return true;
/*     */     }
/* 155 */     if ((par2 == 83) && (this.seal.getColor() < 16)) {
/* 156 */       this.seal.setColor((byte)(this.seal.getColor() + 1));
/* 157 */       return true;
/*     */     }
/*     */     
/* 160 */     if ((this.seal.getSeal() instanceof ISealConfigArea)) {
/* 161 */       if ((par2 == 90) && (this.seal.getArea().func_177956_o() > 1)) {
/* 162 */         this.seal.setArea(this.seal.getArea().func_177982_a(0, -1, 0));
/* 163 */         return true;
/*     */       }
/* 165 */       if ((par2 == 91) && (this.seal.getArea().func_177956_o() < 8)) {
/* 166 */         this.seal.setArea(this.seal.getArea().func_177982_a(0, 1, 0));
/* 167 */         return true;
/*     */       }
/*     */       
/* 170 */       if ((par2 == 92) && (this.seal.getArea().func_177958_n() > 1)) {
/* 171 */         this.seal.setArea(this.seal.getArea().func_177982_a(-1, 0, 0));
/* 172 */         return true;
/*     */       }
/* 174 */       if ((par2 == 93) && (this.seal.getArea().func_177958_n() < 8)) {
/* 175 */         this.seal.setArea(this.seal.getArea().func_177982_a(1, 0, 0));
/* 176 */         return true;
/*     */       }
/*     */       
/* 179 */       if ((par2 == 94) && (this.seal.getArea().func_177952_p() > 1)) {
/* 180 */         this.seal.setArea(this.seal.getArea().func_177982_a(0, 0, -1));
/* 181 */         return true;
/*     */       }
/* 183 */       if ((par2 == 95) && (this.seal.getArea().func_177952_p() < 8)) {
/* 184 */         this.seal.setArea(this.seal.getArea().func_177982_a(0, 0, 1));
/* 185 */         return true;
/*     */       }
/*     */     }
/* 188 */     return super.func_75140_a(player, par2);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_75132_a(ICrafting par1ICrafting)
/*     */   {
/* 194 */     super.func_75132_a(par1ICrafting);
/* 195 */     par1ICrafting.func_71112_a(this, 0, this.seal.getPriority());
/* 196 */     par1ICrafting.func_71112_a(this, 4, this.seal.getColor());
/*     */   }
/*     */   
/*     */ 
/*     */   private byte lastColor;
/*     */   
/*     */   private int lastAreaX;
/*     */   
/*     */   private int lastAreaY;
/*     */   private int lastAreaZ;
/*     */   public void func_75142_b()
/*     */   {
/* 208 */     super.func_75142_b();
/*     */     
/* 210 */     for (int i = 0; i < this.field_75149_d.size(); i++)
/*     */     {
/* 212 */       ICrafting icrafting = (ICrafting)this.field_75149_d.get(i);
/*     */       
/* 214 */       if (this.lastPriority != this.seal.getPriority())
/*     */       {
/* 216 */         icrafting.func_71112_a(this, 0, this.seal.getPriority());
/*     */       }
/*     */       
/* 219 */       if (this.lastAreaX != this.seal.getArea().func_177958_n()) icrafting.func_71112_a(this, 1, this.seal.getArea().func_177958_n());
/* 220 */       if (this.lastAreaY != this.seal.getArea().func_177956_o()) icrafting.func_71112_a(this, 2, this.seal.getArea().func_177956_o());
/* 221 */       if (this.lastAreaZ != this.seal.getArea().func_177952_p()) { icrafting.func_71112_a(this, 3, this.seal.getArea().func_177952_p());
/*     */       }
/* 223 */       if (this.lastColor != this.seal.getColor())
/*     */       {
/* 225 */         icrafting.func_71112_a(this, 4, this.seal.getColor());
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 230 */     this.lastPriority = this.seal.getPriority();
/* 231 */     this.lastColor = this.seal.getColor();
/* 232 */     this.lastAreaX = this.seal.getArea().func_177958_n();
/* 233 */     this.lastAreaY = this.seal.getArea().func_177956_o();
/* 234 */     this.lastAreaZ = this.seal.getArea().func_177952_p();
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_75137_b(int par1, int par2)
/*     */   {
/* 241 */     if (par1 == 0) this.seal.setPriority((byte)par2);
/* 242 */     if (par1 == 1) this.seal.setArea(new BlockPos(par2, this.seal.getArea().func_177956_o(), this.seal.getArea().func_177952_p()));
/* 243 */     if (par1 == 2) this.seal.setArea(new BlockPos(this.seal.getArea().func_177958_n(), par2, this.seal.getArea().func_177952_p()));
/* 244 */     if (par1 == 3) this.seal.setArea(new BlockPos(this.seal.getArea().func_177958_n(), this.seal.getArea().func_177956_o(), par2));
/* 245 */     if (par1 == 4) this.seal.setColor((byte)par2);
/*     */   }
/*     */   
/*     */   public ItemStack func_75144_a(int slotId, int clickedButton, int mode, EntityPlayer playerIn)
/*     */   {
/* 250 */     if (slotId >= 0)
/*     */     {
/* 252 */       Slot slot = (Slot)this.field_75151_b.get(slotId);
/* 253 */       InventoryPlayer inventoryplayer = playerIn.field_71071_by;
/* 254 */       ItemStack ic = null;
/* 255 */       if (inventoryplayer.func_70445_o() != null) { ic = inventoryplayer.func_70445_o().func_77946_l();
/*     */       }
/* 257 */       if ((slot != null) && ((slot instanceof SlotGhost))) {
/* 258 */         boolean filter = ((ISealConfigFilter)this.seal.getSeal()).hasStacksizeLimiters();
/* 259 */         if (!playerIn.field_70170_p.field_72995_K) {
/* 260 */           if (clickedButton == 1) {
/* 261 */             if (!filter) {
/* 262 */               slot.func_75215_d((ItemStack)null);
/*     */             }
/* 264 */             else if (ic == null) {
/* 265 */               if (mode == 1) {
/* 266 */                 slot.func_75215_d((ItemStack)null);
/*     */               }
/* 268 */               else if (slot.func_75216_d()) {
/* 269 */                 slot.func_75211_c().field_77994_a -= 1;
/* 270 */                 if (slot.func_75211_c().field_77994_a < 0) {
/* 271 */                   slot.func_75215_d(null);
/*     */                 }
/*     */               }
/*     */             }
/* 275 */             else if ((slot.func_75216_d()) && (slot.func_75211_c().field_77994_a == 0)) {
/* 276 */               slot.func_75215_d(null);
/*     */             }
/* 278 */             else if ((slot.func_75216_d()) && (ItemStack.func_179545_c(ic, slot.func_75211_c())) && (ItemStack.func_77970_a(ic, slot.func_75211_c()))) {
/* 279 */               slot.func_75211_c().field_77994_a -= ic.field_77994_a;
/*     */             }
/*     */             
/*     */ 
/*     */           }
/* 284 */           else if (ic == null) {
/* 285 */             if ((filter) && (slot.func_75216_d())) slot.func_75211_c().field_77994_a += 1;
/*     */           } else {
/* 287 */             if (!filter) {
/* 288 */               ic.field_77994_a = 1;
/*     */             }
/* 290 */             else if ((slot.func_75216_d()) && (ItemStack.func_179545_c(ic, slot.func_75211_c())) && (ItemStack.func_77970_a(ic, slot.func_75211_c()))) {
/* 291 */               ic.field_77994_a += slot.func_75211_c().field_77994_a;
/*     */             } else {
/* 293 */               ic.field_77994_a = 0;
/*     */             }
/*     */             
/* 296 */             slot.func_75215_d(ic);
/*     */           }
/*     */           
/*     */ 
/* 300 */           if ((slot.func_75216_d()) && (slot.func_75211_c().field_77994_a < 0)) slot.func_75211_c().field_77994_a = 0;
/* 301 */           func_75142_b();
/*     */         }
/*     */         
/* 304 */         return null;
/*     */       }
/*     */     }
/*     */     
/* 308 */     return super.func_75144_a(slotId, clickedButton, mode, playerIn);
/*     */   }
/*     */   
/*     */ 
/*     */   public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int par2)
/*     */   {
/* 314 */     ItemStack itemstack = null;
/* 315 */     Slot slot = (Slot)this.field_75151_b.get(par2);
/*     */     
/* 317 */     if ((slot != null) && (slot.func_75216_d()))
/*     */     {
/* 319 */       ItemStack itemstack1 = slot.func_75211_c();
/* 320 */       itemstack = itemstack1.func_77946_l();
/*     */       
/* 322 */       if (itemstack1.field_77994_a == 0)
/*     */       {
/* 324 */         slot.func_75215_d((ItemStack)null);
/*     */       }
/*     */       else
/*     */       {
/* 328 */         slot.func_75218_e();
/*     */       }
/*     */       
/* 331 */       if (itemstack1.field_77994_a == itemstack.field_77994_a)
/*     */       {
/* 333 */         return null;
/*     */       }
/*     */       
/* 336 */       slot.func_82870_a(par1EntityPlayer, itemstack1);
/*     */     }
/*     */     
/* 339 */     return itemstack;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/construct/golem/gui/SealBaseContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */