/*     */ package thaumcraft.common.tiles.crafting;
/*     */ 
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagLong;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.ITickable;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.IEssentiaTransport;
/*     */ import thaumcraft.api.golems.IGolemProperties;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.entities.construct.golem.GolemProperties;
/*     */ import thaumcraft.common.lib.utils.InventoryUtils;
/*     */ import thaumcraft.common.tiles.TileThaumcraftInventory;
/*     */ 
/*     */ public class TileGolemBuilder extends TileThaumcraftInventory implements ITickable, IEssentiaTransport
/*     */ {
/*  28 */   public long golem = -1L;
/*  29 */   public int cost = 0;
/*  30 */   public int maxCost = 0;
/*  31 */   boolean bufferedEssentia = false;
/*     */   
/*     */   public TileGolemBuilder()
/*     */   {
/*  35 */     this.itemStacks = new ItemStack[1];
/*  36 */     this.syncedSlots = new int[] { 0 };
/*     */   }
/*     */   
/*     */   public void readCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/*  41 */     this.itemStacks = new ItemStack[1];
/*  42 */     this.syncedSlots = new int[] { 0 };
/*  43 */     super.readCustomNBT(nbttagcompound);
/*  44 */     this.golem = nbttagcompound.func_74763_f("golem");
/*  45 */     this.cost = nbttagcompound.func_74762_e("cost");
/*  46 */     this.maxCost = nbttagcompound.func_74762_e("mcost");
/*  47 */     if (this.golem >= 0L) {
/*     */       try {
/*  49 */         this.props = GolemProperties.fromLong(this.golem);
/*  50 */         this.components = this.props.generateComponents();
/*     */       } catch (Exception e) {
/*  52 */         this.props = null;
/*  53 */         this.components = null;
/*  54 */         this.cost = 0;
/*  55 */         this.golem = -1L;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void writeCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/*  63 */     super.writeCustomNBT(nbttagcompound);
/*  64 */     nbttagcompound.func_74772_a("golem", this.golem);
/*  65 */     nbttagcompound.func_74768_a("cost", this.cost);
/*  66 */     nbttagcompound.func_74768_a("mcost", this.maxCost);
/*     */   }
/*     */   
/*     */   @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*     */   public AxisAlignedBB getRenderBoundingBox()
/*     */   {
/*  72 */     return AxisAlignedBB.func_178781_a(this.field_174879_c.func_177958_n() - 1, this.field_174879_c.func_177956_o(), this.field_174879_c.func_177952_p() - 1, this.field_174879_c.func_177958_n() + 2, this.field_174879_c.func_177956_o() + 2, this.field_174879_c.func_177952_p() + 2);
/*     */   }
/*     */   
/*  75 */   int ticks = 0;
/*  76 */   public int press = 0;
/*     */   
/*     */ 
/*     */   public void func_73660_a()
/*     */   {
/*  81 */     boolean complete = false;
/*  82 */     if (!this.field_145850_b.field_72995_K)
/*     */     {
/*  84 */       this.ticks += 1;
/*  85 */       if ((this.ticks % 5 == 0) && 
/*  86 */         (!complete) && (this.cost > 0) && (this.golem >= 0L))
/*     */       {
/*  88 */         if ((this.bufferedEssentia) || (drawEssentia())) {
/*  89 */           this.bufferedEssentia = false;
/*  90 */           this.cost -= 1;
/*  91 */           func_70296_d();
/*     */         }
/*     */         
/*  94 */         if (this.cost <= 0) {
/*  95 */           ItemStack placer = new ItemStack(ItemsTC.golemPlacer);
/*  96 */           placer.func_77983_a("props", new NBTTagLong(this.golem));
/*     */           
/*  98 */           if ((func_70301_a(0) == null) || ((func_70301_a(0).field_77994_a < func_70301_a(0).func_77976_d()) && (func_70301_a(0).func_77969_a(placer)) && (ItemStack.func_77970_a(func_70301_a(0), placer))))
/*     */           {
/*     */ 
/*     */ 
/* 102 */             if (func_70301_a(0) == null) {
/* 103 */               func_70299_a(0, placer.func_77946_l());
/*     */             } else {
/* 105 */               func_70301_a(0).field_77994_a += 1;
/*     */             }
/* 107 */             complete = true;
/* 108 */             this.field_145850_b.func_72908_a(this.field_174879_c.func_177958_n(), this.field_174879_c.func_177956_o(), this.field_174879_c.func_177952_p(), "thaumcraft:wand", 1.0F, 1.0F);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     else {
/* 114 */       if ((this.press < 90) && (this.cost > 0) && (this.golem > 0L)) {
/* 115 */         this.press += 6;
/* 116 */         if (this.press >= 60) {
/* 117 */           this.field_145850_b.func_72980_b(this.field_174879_c.func_177958_n() + 0.5D, this.field_174879_c.func_177956_o() + 0.5D, this.field_174879_c.func_177952_p() + 0.5D, "random.fizz", 0.66F, 1.0F + this.field_145850_b.field_73012_v.nextFloat() * 0.1F, false);
/* 118 */           for (int a = 0; a < Thaumcraft.proxy.getFX().particleCount(8); a++) {
/* 119 */             Thaumcraft.proxy.getFX().drawVentParticles(this.field_174879_c.func_177958_n() + 0.5D, this.field_174879_c.func_177956_o() + 1, this.field_174879_c.func_177952_p() + 0.5D, this.field_145850_b.field_73012_v.nextGaussian() * 0.1D, 0.0D, this.field_145850_b.field_73012_v.nextGaussian() * 0.1D, 11184810);
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 125 */       if ((this.press >= 90) && (this.field_145850_b.field_73012_v.nextInt(8) == 0)) {
/* 126 */         Thaumcraft.proxy.getFX().drawVentParticles(this.field_174879_c.func_177958_n() + 0.5D, this.field_174879_c.func_177956_o() + 1, this.field_174879_c.func_177952_p() + 0.5D, this.field_145850_b.field_73012_v.nextGaussian() * 0.1D, 0.0D, this.field_145850_b.field_73012_v.nextGaussian() * 0.1D, 11184810);
/*     */         
/*     */ 
/*     */ 
/* 130 */         this.field_145850_b.func_72980_b(this.field_174879_c.func_177958_n() + 0.5D, this.field_174879_c.func_177956_o() + 0.5D, this.field_174879_c.func_177952_p() + 0.5D, "random.fizz", 0.1F, 1.0F + this.field_145850_b.field_73012_v.nextFloat() * 0.1F, false);
/*     */       }
/* 132 */       if ((this.press > 0) && ((this.cost <= 0) || (this.golem == -1L))) {
/* 133 */         if (this.press >= 90) {
/* 134 */           for (int a = 0; a < Thaumcraft.proxy.getFX().particleCount(5); a++) {
/* 135 */             Thaumcraft.proxy.getFX().drawVentParticles(this.field_174879_c.func_177958_n() + 0.5D, this.field_174879_c.func_177956_o() + 1, this.field_174879_c.func_177952_p() + 0.5D, this.field_145850_b.field_73012_v.nextGaussian() * 0.1D, 0.0D, this.field_145850_b.field_73012_v.nextGaussian() * 0.1D, 11184810);
/*     */           }
/*     */         }
/*     */         
/*     */ 
/* 140 */         this.press -= 3;
/*     */       }
/*     */     }
/*     */     
/* 144 */     if (complete)
/*     */     {
/* 146 */       this.cost = 0;
/* 147 */       this.golem = -1L;
/* 148 */       this.field_145850_b.func_175689_h(this.field_174879_c);
/* 149 */       func_70296_d();
/*     */     }
/*     */   }
/*     */   
/* 153 */   IGolemProperties props = null;
/* 154 */   ItemStack[] components = null;
/*     */   
/*     */   public boolean startCraft(long id, EntityPlayer p)
/*     */   {
/* 158 */     ItemStack placer = new ItemStack(ItemsTC.golemPlacer);
/* 159 */     placer.func_77983_a("props", new NBTTagLong(id));
/*     */     
/* 161 */     if ((func_70301_a(0) == null) || ((func_70301_a(0).field_77994_a < func_70301_a(0).func_77976_d()) && (func_70301_a(0).func_77969_a(placer)) && (ItemStack.func_77970_a(func_70301_a(0), placer))))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/* 166 */       this.golem = id;
/* 167 */       this.props = GolemProperties.fromLong(this.golem);
/* 168 */       this.components = this.props.generateComponents();
/*     */       
/* 170 */       for (ItemStack stack : this.components) {
/* 171 */         if (!InventoryUtils.isPlayerCarryingAmount(p, stack, true)) {
/* 172 */           this.cost = 0;
/* 173 */           this.props = null;
/* 174 */           this.components = null;
/* 175 */           this.golem = -1L;
/* 176 */           return false;
/*     */         }
/*     */       }
/*     */       
/* 180 */       this.cost = (this.props.getTraits().size() * 2);
/* 181 */       for (ItemStack stack : this.components) {
/* 182 */         this.cost += stack.field_77994_a;
/* 183 */         InventoryUtils.consumeInventoryItems(p, stack, true, true);
/*     */       }
/*     */       
/* 186 */       this.maxCost = this.cost;
/*     */       
/* 188 */       func_70296_d();
/* 189 */       this.field_145850_b.func_175689_h(this.field_174879_c);
/* 190 */       this.field_145850_b.func_72908_a(this.field_174879_c.func_177958_n(), this.field_174879_c.func_177956_o(), this.field_174879_c.func_177952_p(), "thaumcraft:craftstart", 0.25F, 1.0F);
/* 191 */       return true;
/*     */     }
/* 193 */     this.cost = 0;
/* 194 */     this.props = null;
/* 195 */     this.components = null;
/* 196 */     this.golem = -1L;
/* 197 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_94041_b(int par1, ItemStack par2ItemStack)
/*     */   {
/* 204 */     if ((par2ItemStack != null) && ((par2ItemStack.func_77973_b() instanceof thaumcraft.common.entities.construct.golem.ItemGolemPlacer)))
/* 205 */       return true;
/* 206 */     return false;
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
/*     */   boolean drawEssentia()
/*     */   {
/* 230 */     for (EnumFacing face : EnumFacing.field_82609_l) {
/* 231 */       net.minecraft.tileentity.TileEntity te = ThaumcraftApiHelper.getConnectableTile(this.field_145850_b, func_174877_v(), face);
/* 232 */       if (te != null) {
/* 233 */         IEssentiaTransport ic = (IEssentiaTransport)te;
/* 234 */         if (!ic.canOutputTo(face.func_176734_d())) return false;
/* 235 */         if ((ic.getSuctionAmount(face.func_176734_d()) < getSuctionAmount(face)) && (ic.takeEssentia(Aspect.MECHANISM, 1, face.func_176734_d()) == 1))
/*     */         {
/* 237 */           return true;
/*     */         }
/*     */       }
/*     */     }
/* 241 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isConnectable(EnumFacing face)
/*     */   {
/* 246 */     return (face.func_176736_b() >= 0) || (face == EnumFacing.DOWN);
/*     */   }
/*     */   
/*     */   public boolean canInputFrom(EnumFacing face)
/*     */   {
/* 251 */     return isConnectable(face);
/*     */   }
/*     */   
/*     */   public boolean canOutputTo(EnumFacing face)
/*     */   {
/* 256 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setSuction(Aspect aspect, int amount) {}
/*     */   
/*     */ 
/*     */   public int getMinimumSuction()
/*     */   {
/* 265 */     return 0;
/*     */   }
/*     */   
/*     */   public Aspect getSuctionType(EnumFacing face)
/*     */   {
/* 270 */     return Aspect.MECHANISM;
/*     */   }
/*     */   
/*     */   public int getSuctionAmount(EnumFacing face)
/*     */   {
/* 275 */     return (this.cost > 0) && (this.golem >= 0L) ? 128 : 0;
/*     */   }
/*     */   
/*     */   public Aspect getEssentiaType(EnumFacing loc)
/*     */   {
/* 280 */     return null;
/*     */   }
/*     */   
/*     */   public int getEssentiaAmount(EnumFacing loc)
/*     */   {
/* 285 */     return 0;
/*     */   }
/*     */   
/*     */   public int takeEssentia(Aspect aspect, int amount, EnumFacing facing)
/*     */   {
/* 290 */     return 0;
/*     */   }
/*     */   
/*     */   public int addEssentia(Aspect aspect, int amount, EnumFacing facing)
/*     */   {
/* 295 */     if ((!this.bufferedEssentia) && (this.cost > 0) && (this.golem >= 0L) && (aspect == Aspect.MECHANISM)) {
/* 296 */       this.bufferedEssentia = true;
/* 297 */       return 1;
/*     */     }
/* 299 */     return 0;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/tiles/crafting/TileGolemBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */