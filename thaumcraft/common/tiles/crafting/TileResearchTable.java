/*     */ package thaumcraft.common.tiles.crafting;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.network.NetworkManager;
/*     */ import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.blocks.TileThaumcraft;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.items.resources.ItemResearchNotes;
/*     */ import thaumcraft.common.lib.research.ResearchManager;
/*     */ import thaumcraft.common.lib.research.ResearchManager.HexEntry;
/*     */ import thaumcraft.common.lib.research.ResearchNoteData;
/*     */ import thaumcraft.common.lib.utils.HexUtils.Hex;
/*     */ 
/*     */ public class TileResearchTable extends TileThaumcraft implements net.minecraft.inventory.IInventory
/*     */ {
/*  26 */   public ItemStack[] contents = new ItemStack[2];
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void readCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/*  33 */     NBTTagList var2 = nbttagcompound.func_150295_c("Inventory", 10);
/*  34 */     this.contents = new ItemStack[func_70302_i_()];
/*  35 */     for (int var3 = 0; var3 < Math.min(2, var2.func_74745_c()); var3++)
/*     */     {
/*  37 */       NBTTagCompound var4 = var2.func_150305_b(var3);
/*  38 */       int var5 = var4.func_74771_c("Slot") & 0xFF;
/*     */       
/*  40 */       if ((var5 >= 0) && (var5 < this.contents.length))
/*     */       {
/*  42 */         this.contents[var5] = ItemStack.func_77949_a(var4);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void writeCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/*  50 */     NBTTagList var2 = new NBTTagList();
/*  51 */     for (int var3 = 0; var3 < this.contents.length; var3++)
/*     */     {
/*  53 */       if (this.contents[var3] != null)
/*     */       {
/*  55 */         NBTTagCompound var4 = new NBTTagCompound();
/*  56 */         var4.func_74774_a("Slot", (byte)var3);
/*  57 */         this.contents[var3].func_77955_b(var4);
/*  58 */         var2.func_74742_a(var4);
/*     */       }
/*     */     }
/*  61 */     nbttagcompound.func_74782_a("Inventory", var2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_70296_d()
/*     */   {
/*  68 */     super.func_70296_d();
/*  69 */     gatherResults();
/*     */   }
/*     */   
/*  72 */   EntityPlayer researcher = null;
/*     */   
/*  74 */   public ResearchNoteData data = null;
/*     */   
/*  76 */   public void gatherResults() { this.data = null;
/*  77 */     if ((this.contents[1] != null) && ((this.contents[1].func_77973_b() instanceof ItemResearchNotes))) {
/*  78 */       this.data = ResearchManager.getData(this.contents[1]);
/*     */     }
/*     */   }
/*     */   
/*     */   public void placeAspect(int q, int r, Aspect aspect, EntityPlayer player) {
/*  83 */     if (this.data == null) gatherResults();
/*  84 */     if (!ResearchManager.consumeInkFromTable(this.contents[0], false)) {
/*  85 */       return;
/*     */     }
/*  87 */     if ((this.contents[1] != null) && ((this.contents[1].func_77973_b() instanceof ItemResearchNotes)) && (this.data != null) && (this.contents[1].func_77952_i() == 0))
/*     */     {
/*     */ 
/*  90 */       boolean r1 = ResearchManager.isResearchComplete(player.func_70005_c_(), "RESEARCHER1");
/*  91 */       boolean r2 = ResearchManager.isResearchComplete(player.func_70005_c_(), "RESEARCHER2");
/*     */       
/*  93 */       HexUtils.Hex hex = new HexUtils.Hex(q, r);
/*  94 */       ResearchManager.HexEntry he = null;
/*  95 */       if (aspect != null) {
/*  96 */         he = new ResearchManager.HexEntry(aspect, 2);
/*  97 */         if ((r2) && (this.field_145850_b.field_73012_v.nextFloat() < 0.1F)) {
/*  98 */           this.field_145850_b.func_72956_a(player, "random.orb", 0.2F, 0.9F + player.field_70170_p.field_73012_v.nextFloat() * 0.2F);
/*     */         }
/*     */         else {
/* 101 */           this.data.aspects.remove(aspect, 1);
/*     */         }
/*     */       } else {
/* 104 */         float f = this.field_145850_b.field_73012_v.nextFloat();
/* 105 */         if ((((ResearchManager.HexEntry)this.data.hexEntries.get(hex.toString())).aspect != null) && (((r1) && (f < 0.25F)) || ((r2) && (f < 0.5F))))
/*     */         {
/* 107 */           this.field_145850_b.func_72956_a(player, "random.orb", 0.2F, 0.9F + player.field_70170_p.field_73012_v.nextFloat() * 0.2F);
/*     */           
/* 109 */           this.data.aspects.add(((ResearchManager.HexEntry)this.data.hexEntries.get(hex.toString())).aspect, 1);
/*     */         }
/* 111 */         he = new ResearchManager.HexEntry(null, 0);
/*     */       }
/*     */       
/* 114 */       this.data.hexEntries.put(hex.toString(), he);
/* 115 */       this.data.hexes.put(hex.toString(), hex);
/*     */       
/* 117 */       updateNoteAndConsumeInk();
/*     */       
/* 119 */       if ((!this.field_145850_b.field_72995_K) && (ResearchManager.checkResearchCompletion(this.contents[1], this.data, player.func_70005_c_()))) {
/* 120 */         this.contents[1].func_77964_b(1);
/* 121 */         if (Config.researchAmount > 0) this.contents[1].field_77994_a = Config.researchAmount;
/* 122 */         this.field_145850_b.func_175641_c(func_174877_v(), func_145838_q(), 1, 1);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void updateNoteAndConsumeInk()
/*     */   {
/* 129 */     ResearchManager.updateData(this.contents[1], this.data);
/* 130 */     ResearchManager.consumeInkFromTable(this.contents[0], true);
/* 131 */     this.field_145850_b.func_175689_h(func_174877_v());
/* 132 */     func_70296_d();
/*     */   }
/*     */   
/*     */   public int func_70302_i_()
/*     */   {
/* 137 */     return 2;
/*     */   }
/*     */   
/*     */   public ItemStack func_70301_a(int var1)
/*     */   {
/* 142 */     return this.contents[var1];
/*     */   }
/*     */   
/*     */   public ItemStack func_70298_a(int var1, int var2)
/*     */   {
/* 147 */     if (this.contents[var1] != null)
/*     */     {
/*     */ 
/*     */ 
/* 151 */       if (this.contents[var1].field_77994_a <= var2)
/*     */       {
/* 153 */         ItemStack var3 = this.contents[var1];
/* 154 */         this.contents[var1] = null;
/* 155 */         func_70296_d();
/* 156 */         return var3;
/*     */       }
/*     */       
/*     */ 
/* 160 */       ItemStack var3 = this.contents[var1].func_77979_a(var2);
/*     */       
/* 162 */       if (this.contents[var1].field_77994_a == 0)
/*     */       {
/* 164 */         this.contents[var1] = null;
/*     */       }
/*     */       
/* 167 */       func_70296_d();
/* 168 */       return var3;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 173 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public ItemStack func_70304_b(int var1)
/*     */   {
/* 179 */     if (this.contents[var1] != null)
/*     */     {
/* 181 */       ItemStack var2 = this.contents[var1];
/* 182 */       this.contents[var1] = null;
/* 183 */       return var2;
/*     */     }
/*     */     
/*     */ 
/* 187 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70299_a(int var1, ItemStack var2)
/*     */   {
/* 193 */     this.contents[var1] = var2;
/*     */     
/* 195 */     if ((var2 != null) && (var2.field_77994_a > func_70297_j_()))
/*     */     {
/* 197 */       var2.field_77994_a = func_70297_j_();
/*     */     }
/*     */     
/* 200 */     func_70296_d();
/*     */   }
/*     */   
/*     */   public String func_70005_c_()
/*     */   {
/* 205 */     return "Research Table";
/*     */   }
/*     */   
/*     */   public int func_70297_j_()
/*     */   {
/* 210 */     return 64;
/*     */   }
/*     */   
/*     */   public boolean func_70300_a(EntityPlayer var1)
/*     */   {
/* 215 */     return this.field_145850_b.func_175625_s(func_174877_v()) == this;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_145818_k_()
/*     */   {
/* 221 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_94041_b(int i, ItemStack itemstack)
/*     */   {
/* 226 */     if (itemstack == null) return false;
/* 227 */     switch (i) {
/* 228 */     case 0:  if ((itemstack.func_77973_b() instanceof thaumcraft.api.items.IScribeTools)) return true;
/*     */       break; case 1:  if ((itemstack.func_77973_b() == ItemsTC.researchNotes) && (itemstack.func_77952_i() == 0)) return true;
/*     */       break; }
/* 231 */     return false;
/*     */   }
/*     */   
/*     */   public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
/*     */   {
/* 236 */     super.onDataPacket(net, pkt);
/* 237 */     if ((this.field_145850_b != null) && (this.field_145850_b.field_72995_K)) {
/* 238 */       this.field_145850_b.func_175689_h(func_174877_v());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_145842_c(int i, int j)
/*     */   {
/* 245 */     if (i == 1)
/*     */     {
/* 247 */       if (this.field_145850_b.field_72995_K) {
/* 248 */         this.field_145850_b.func_72980_b(func_174877_v().func_177958_n(), func_174877_v().func_177956_o(), func_174877_v().func_177952_p(), "thaumcraft:learn", 1.0F, 1.0F, false);
/*     */       }
/* 250 */       return true;
/*     */     }
/*     */     
/*     */ 
/* 254 */     return super.func_145842_c(i, j);
/*     */   }
/*     */   
/*     */   public net.minecraft.util.IChatComponent func_145748_c_() {
/* 258 */     return null;
/*     */   }
/*     */   
/*     */   public void func_174889_b(EntityPlayer player) {}
/*     */   
/*     */   public void func_174886_c(EntityPlayer player) {}
/*     */   
/*     */   public int func_174887_a_(int id)
/*     */   {
/* 267 */     return 0;
/*     */   }
/*     */   
/*     */   public void func_174885_b(int id, int value) {}
/*     */   
/*     */   public int func_174890_g() {
/* 273 */     return 0;
/*     */   }
/*     */   
/*     */   public void func_174888_l() {}
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/tiles/crafting/TileResearchTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */