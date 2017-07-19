/*     */ package thaumcraft.common.container;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.ICrafting;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.WeightedRandomChestContent;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.ChestGenHooks;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.common.entities.monster.EntityPech;
/*     */ 
/*     */ 
/*     */ public class ContainerPech
/*     */   extends Container
/*     */ {
/*     */   private EntityPech pech;
/*     */   private InventoryPech inventory;
/*     */   private EntityPlayer player;
/*     */   private final World theWorld;
/*     */   
/*     */   public ContainerPech(InventoryPlayer par1InventoryPlayer, World par3World, EntityPech par2IMerchant)
/*     */   {
/*  32 */     this.pech = par2IMerchant;
/*  33 */     this.theWorld = par3World;
/*  34 */     this.player = par1InventoryPlayer.field_70458_d;
/*  35 */     this.inventory = new InventoryPech(par1InventoryPlayer.field_70458_d, par2IMerchant, this);
/*  36 */     this.pech.trading = true;
/*  37 */     func_75146_a(new Slot(this.inventory, 0, 36, 29));
/*     */     
/*     */ 
/*     */ 
/*  41 */     for (int i = 0; i < 2; i++) {
/*  42 */       for (int j = 0; j < 2; j++) {
/*  43 */         func_75146_a(new SlotOutput(this.inventory, 1 + j + i * 2, 106 + 18 * j, 20 + 18 * i));
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*  48 */     for (i = 0; i < 3; i++)
/*     */     {
/*  50 */       for (int j = 0; j < 9; j++)
/*     */       {
/*  52 */         func_75146_a(new Slot(par1InventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
/*     */       }
/*     */     }
/*     */     
/*  56 */     for (i = 0; i < 9; i++)
/*     */     {
/*  58 */       func_75146_a(new Slot(par1InventoryPlayer, i, 8 + i * 18, 142));
/*     */     }
/*     */   }
/*     */   
/*     */   public InventoryPech getMerchantInventory()
/*     */   {
/*  64 */     return this.inventory;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_75132_a(ICrafting par1ICrafting)
/*     */   {
/*  70 */     super.func_75132_a(par1ICrafting);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75142_b()
/*     */   {
/*  81 */     super.func_75142_b();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_75140_a(EntityPlayer par1EntityPlayer, int par2)
/*     */   {
/*  88 */     if (par2 == 0) {
/*  89 */       generateContents();
/*  90 */       return true;
/*     */     }
/*  92 */     return super.func_75140_a(par1EntityPlayer, par2);
/*     */   }
/*     */   
/*  95 */   ChestGenHooks chest = ChestGenHooks.getInfo("dungeonChest");
/*     */   
/*     */   private boolean hasStuffInPack() {
/*  98 */     for (ItemStack stack : this.pech.loot) {
/*  99 */       if ((stack != null) && (stack.field_77994_a > 0))
/* 100 */         return true;
/*     */     }
/* 102 */     return false;
/*     */   }
/*     */   
/*     */   private void generateContents()
/*     */   {
/* 107 */     if ((!this.theWorld.field_72995_K) && (this.inventory.func_70301_a(0) != null) && (this.inventory.func_70301_a(1) == null) && (this.inventory.func_70301_a(2) == null) && (this.inventory.func_70301_a(3) == null) && (this.inventory.func_70301_a(4) == null))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/* 112 */       if (this.pech.isValued(this.inventory.func_70301_a(0))) {
/* 113 */         int value = this.pech.getValue(this.inventory.func_70301_a(0));
/* 114 */         if (this.theWorld.field_73012_v.nextInt(100) <= value / 2) {
/* 115 */           this.pech.setTamed(false);
/* 116 */           this.pech.updateAINextTick = true;
/* 117 */           this.pech.func_85030_a("thaumcraft:pech_trade", 0.4F, 1.0F);
/*     */         }
/*     */         
/* 120 */         if (this.theWorld.field_73012_v.nextInt(5) == 0) {
/* 121 */           value += this.theWorld.field_73012_v.nextInt(3);
/*     */         }
/* 123 */         else if (this.theWorld.field_73012_v.nextBoolean()) {
/* 124 */           value -= this.theWorld.field_73012_v.nextInt(3);
/*     */         }
/*     */         
/* 127 */         ArrayList<List> pos = (ArrayList)EntityPech.tradeInventory.get(Integer.valueOf(this.pech.getPechType()));
/* 128 */         while (value > 0) {
/* 129 */           int am = Math.min(5, Math.max((value + 1) / 2, this.theWorld.field_73012_v.nextInt(value) + 1));
/*     */           
/*     */ 
/*     */ 
/* 133 */           value -= am;
/*     */           
/* 135 */           if ((am == 1) && (this.theWorld.field_73012_v.nextBoolean()) && (hasStuffInPack())) {
/* 136 */             ArrayList<Integer> loot = new ArrayList();
/* 137 */             for (int a = 0; a < this.pech.loot.length; a++) {
/* 138 */               if ((this.pech.loot[a] != null) && (this.pech.loot[a].field_77994_a > 0))
/* 139 */                 loot.add(Integer.valueOf(a));
/*     */             }
/* 141 */             int r = ((Integer)loot.get(this.theWorld.field_73012_v.nextInt(loot.size()))).intValue();
/* 142 */             ItemStack is = this.pech.loot[r].func_77946_l();
/* 143 */             is.field_77994_a = 1;
/* 144 */             func_75135_a(is, 1, 5, false);
/* 145 */             this.pech.loot[r].field_77994_a -= 1;
/* 146 */             if (this.pech.loot[r].field_77994_a <= 0) this.pech.loot[r] = null;
/*     */           }
/* 148 */           else if ((am >= 4) && (this.theWorld.field_73012_v.nextBoolean())) {
/* 149 */             List<WeightedRandomChestContent> contents = this.chest.getItems(this.theWorld.field_73012_v);
/* 150 */             WeightedRandomChestContent wc = null;
/* 151 */             int cc = 0;
/*     */             do {
/* 153 */               wc = (WeightedRandomChestContent)contents.get(this.theWorld.field_73012_v.nextInt(contents.size()));
/* 154 */               cc++;
/* 155 */             } while ((cc < 50) && ((wc.field_76297_b == null) || (wc.field_76292_a > 5)));
/* 156 */             if ((wc == null) || (wc.field_76297_b == null)) {
/* 157 */               value += am;
/*     */             }
/*     */             else {
/* 160 */               ItemStack is = wc.field_76297_b.func_77946_l();
/* 161 */               is.func_77980_a(this.theWorld, this.player, 0);
/* 162 */               func_75135_a(is, 1, 5, false);
/*     */             }
/* 164 */           } else { List it = null;
/*     */             do {
/* 166 */               it = (List)pos.get(this.theWorld.field_73012_v.nextInt(pos.size()));
/* 167 */             } while (((Integer)it.get(0)).intValue() != am);
/* 168 */             ItemStack is = ((ItemStack)it.get(1)).func_77946_l();
/* 169 */             is.func_77980_a(this.theWorld, this.player, 0);
/* 170 */             func_75135_a(is, 1, 5, false);
/*     */           }
/*     */         }
/*     */         
/*     */ 
/* 175 */         this.inventory.func_70298_a(0, 1);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_75137_b(int par1, int par2) {}
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_75145_c(EntityPlayer par1EntityPlayer)
/*     */   {
/* 189 */     return this.pech.isTamed();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int par2)
/*     */   {
/* 198 */     ItemStack itemstack = null;
/* 199 */     Slot slot = (Slot)this.field_75151_b.get(par2);
/*     */     
/* 201 */     if ((slot != null) && (slot.func_75216_d()))
/*     */     {
/* 203 */       ItemStack itemstack1 = slot.func_75211_c();
/* 204 */       itemstack = itemstack1.func_77946_l();
/*     */       
/* 206 */       if (par2 == 0)
/*     */       {
/* 208 */         if (!func_75135_a(itemstack1, 5, 41, true))
/*     */         {
/* 210 */           return null;
/*     */         }
/*     */         
/*     */       }
/* 214 */       else if ((par2 >= 1) && (par2 < 5))
/*     */       {
/* 216 */         if (!func_75135_a(itemstack1, 5, 41, true))
/*     */         {
/* 218 */           return null;
/*     */         }
/*     */       }
/* 221 */       else if (par2 != 0)
/*     */       {
/* 223 */         if ((par2 >= 5) && (par2 < 41))
/*     */         {
/*     */ 
/* 226 */           if (!func_75135_a(itemstack1, 0, 1, true))
/*     */           {
/* 228 */             return null;
/*     */           }
/*     */         }
/*     */       }
/*     */       
/* 233 */       if (itemstack1.field_77994_a == 0)
/*     */       {
/* 235 */         slot.func_75215_d((ItemStack)null);
/*     */       }
/*     */       else
/*     */       {
/* 239 */         slot.func_75218_e();
/*     */       }
/*     */       
/* 242 */       if (itemstack1.field_77994_a == itemstack.field_77994_a)
/*     */       {
/* 244 */         return null;
/*     */       }
/*     */       
/* 247 */       slot.func_82870_a(par1EntityPlayer, itemstack1);
/*     */     }
/*     */     
/* 250 */     return itemstack;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75134_a(EntityPlayer par1EntityPlayer)
/*     */   {
/* 259 */     super.func_75134_a(par1EntityPlayer);
/* 260 */     this.pech.trading = false;
/*     */     
/* 262 */     if (!this.theWorld.field_72995_K)
/*     */     {
/*     */ 
/* 265 */       for (int a = 0; a < 5; a++)
/*     */       {
/* 267 */         ItemStack itemstack = this.inventory.func_70304_b(a);
/*     */         
/* 269 */         if (itemstack != null)
/*     */         {
/* 271 */           EntityItem ei = par1EntityPlayer.func_71019_a(itemstack, false);
/* 272 */           if (ei != null) {
/* 273 */             ei.func_145799_b("PechDrop");
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean func_75135_a(ItemStack p_75135_1_, int p_75135_2_, int p_75135_3_, boolean p_75135_4_)
/*     */   {
/* 285 */     boolean flag1 = false;
/* 286 */     int k = p_75135_2_;
/*     */     
/* 288 */     if (p_75135_4_)
/*     */     {
/* 290 */       k = p_75135_3_ - 1;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 296 */     if (p_75135_1_.func_77985_e())
/*     */     {
/* 298 */       while ((p_75135_1_.field_77994_a > 0) && (((!p_75135_4_) && (k < p_75135_3_)) || ((p_75135_4_) && (k >= p_75135_2_))))
/*     */       {
/* 300 */         Slot slot = (Slot)this.field_75151_b.get(k);
/* 301 */         ItemStack itemstack1 = slot.func_75211_c();
/*     */         
/* 303 */         if ((itemstack1 != null) && (itemstack1.func_77973_b() == p_75135_1_.func_77973_b()) && ((!p_75135_1_.func_77981_g()) || (p_75135_1_.func_77952_i() == itemstack1.func_77952_i())) && (ItemStack.func_77970_a(p_75135_1_, itemstack1)))
/*     */         {
/* 305 */           int l = itemstack1.field_77994_a + p_75135_1_.field_77994_a;
/*     */           
/* 307 */           if (l <= p_75135_1_.func_77976_d())
/*     */           {
/* 309 */             p_75135_1_.field_77994_a = 0;
/* 310 */             itemstack1.field_77994_a = l;
/* 311 */             slot.func_75218_e();
/* 312 */             flag1 = true;
/*     */           }
/* 314 */           else if (itemstack1.field_77994_a < p_75135_1_.func_77976_d())
/*     */           {
/* 316 */             p_75135_1_.field_77994_a -= p_75135_1_.func_77976_d() - itemstack1.field_77994_a;
/* 317 */             itemstack1.field_77994_a = p_75135_1_.func_77976_d();
/* 318 */             slot.func_75218_e();
/* 319 */             flag1 = true;
/*     */           }
/*     */         }
/*     */         
/* 323 */         if (p_75135_4_)
/*     */         {
/* 325 */           k--;
/*     */         }
/*     */         else
/*     */         {
/* 329 */           k++;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 334 */     if (p_75135_1_.field_77994_a > 0)
/*     */     {
/* 336 */       if (p_75135_4_)
/*     */       {
/* 338 */         k = p_75135_3_ - 1;
/*     */       }
/*     */       else
/*     */       {
/* 342 */         k = p_75135_2_;
/*     */       }
/*     */       
/* 345 */       while (((!p_75135_4_) && (k < p_75135_3_)) || ((p_75135_4_) && (k >= p_75135_2_)))
/*     */       {
/* 347 */         Slot slot = (Slot)this.field_75151_b.get(k);
/* 348 */         ItemStack itemstack1 = slot.func_75211_c();
/*     */         
/* 350 */         if (itemstack1 == null)
/*     */         {
/* 352 */           slot.func_75215_d(p_75135_1_.func_77946_l());
/* 353 */           slot.func_75218_e();
/* 354 */           p_75135_1_.field_77994_a = 0;
/* 355 */           flag1 = true;
/* 356 */           break;
/*     */         }
/*     */         
/* 359 */         if (p_75135_4_)
/*     */         {
/* 361 */           k--;
/*     */         }
/*     */         else
/*     */         {
/* 365 */           k++;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 370 */     return flag1;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/container/ContainerPech.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */