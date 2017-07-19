/*     */ package thaumcraft.api.wands;
/*     */ 
/*     */ import java.text.DecimalFormat;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ 
/*     */ 
/*     */ public class ItemFocusBasic
/*     */   extends Item
/*     */ {
/*     */   public ItemFocusBasic(String id, ResourceLocation texture, int renderColor)
/*     */   {
/*  28 */     this.field_77777_bU = 1;
/*  29 */     this.canRepair = false;
/*  30 */     func_77656_e(0);
/*  31 */     this.id = id;
/*  32 */     this.texture = texture;
/*  33 */     foci.put(id, this);
/*  34 */     this.focusColor = renderColor;
/*     */   }
/*     */   
/*     */   public ItemFocusBasic(String id, int renderColor)
/*     */   {
/*  39 */     this(id, new ResourceLocation("thaumcraft", "items/wand/focus"), renderColor);
/*     */   }
/*     */   
/*  42 */   public static LinkedHashMap<String, ItemFocusBasic> foci = new LinkedHashMap();
/*     */   
/*     */   private String id;
/*     */   
/*     */   private ResourceLocation texture;
/*     */   
/*     */   public String getFocusId()
/*     */   {
/*  50 */     return this.id;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ResourceLocation getFocusTexture()
/*     */   {
/*  59 */     return this.texture;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  65 */   private int focusColor = 0;
/*     */   
/*     */ 
/*     */ 
/*     */   public int getFocusColor(ItemStack focusstack)
/*     */   {
/*  71 */     return this.focusColor;
/*     */   }
/*     */   
/*     */   public boolean func_77645_m()
/*     */   {
/*  76 */     return false;
/*     */   }
/*     */   
/*     */   public boolean canBePlacedInTurret() {
/*  80 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public float getTurretCorrection(ItemStack focusstack)
/*     */   {
/*  89 */     return 0.0F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public float getTurretRange(ItemStack focusstack)
/*     */   {
/*  96 */     return 32.0F;
/*     */   }
/*     */   
/*     */   public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean par4)
/*     */   {
/* 101 */     AspectList al = getVisCost(stack);
/* 102 */     if ((al != null) && (al.size() > 0)) {
/* 103 */       list.add(StatCollector.func_74838_a(isVisCostPerTick(stack) ? "item.Focus.cost2" : "item.Focus.cost1"));
/* 104 */       for (Aspect aspect : al.getAspectsSortedByName()) {
/* 105 */         DecimalFormat myFormatter = new DecimalFormat("#####.##");
/* 106 */         String amount = myFormatter.format(al.getAmount(aspect));
/* 107 */         list.add(" §" + aspect.getChatcolor() + aspect.getName() + "§r x " + amount);
/*     */       }
/*     */     }
/* 110 */     addFocusInformation(stack, player, list, par4);
/*     */   }
/*     */   
/*     */   public void addFocusInformation(ItemStack focusstack, EntityPlayer player, List list, boolean par4) {
/* 114 */     LinkedHashMap<Short, Integer> map = new LinkedHashMap();
/* 115 */     for (short id : getAppliedUpgrades(focusstack)) {
/* 116 */       if (id >= 0) {
/* 117 */         int amt = 1;
/* 118 */         if (map.containsKey(Short.valueOf(id))) {
/* 119 */           amt = ((Integer)map.get(Short.valueOf(id))).intValue() + 1;
/*     */         }
/* 121 */         map.put(Short.valueOf(id), Integer.valueOf(amt));
/*     */       }
/*     */     }
/* 124 */     for (Short id : map.keySet()) {
/* 125 */       list.add(EnumChatFormatting.DARK_PURPLE + FocusUpgradeType.types[id.shortValue()].getLocalizedName() + (((Integer)map.get(id)).intValue() > 1 ? " " + StatCollector.func_74838_a(new StringBuilder().append("enchantment.level.").append(map.get(id)).toString()) : ""));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isVisCostPerTick(ItemStack focusstack)
/*     */   {
/* 134 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public EnumRarity func_77613_e(ItemStack focusstack)
/*     */   {
/* 140 */     return EnumRarity.RARE;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static enum WandFocusAnimation
/*     */   {
/* 147 */     WAVE,  CHARGE;
/*     */     
/*     */     private WandFocusAnimation() {} }
/*     */   
/* 151 */   public WandFocusAnimation getAnimation(ItemStack focusstack) { return WandFocusAnimation.WAVE; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getSortingHelper(ItemStack focusstack)
/*     */   {
/* 158 */     String out = this.id;
/* 159 */     for (short id : getAppliedUpgrades(focusstack)) {
/* 160 */       out = out + id;
/*     */     }
/* 162 */     return out;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public AspectList getVisCost(ItemStack focusstack)
/*     */   {
/* 170 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getActivationCooldown(ItemStack focusstack)
/*     */   {
/* 177 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getMaxAreaSize(ItemStack focusstack)
/*     */   {
/* 184 */     return 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack focusstack, int rank)
/*     */   {
/* 191 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public short[] getAppliedUpgrades(ItemStack focusstack)
/*     */   {
/* 198 */     short[] l = { -1, -1, -1, -1, -1 };
/* 199 */     NBTTagList nbttaglist = getFocusUpgradeTagList(focusstack);
/* 200 */     if (nbttaglist == null)
/*     */     {
/* 202 */       return l;
/*     */     }
/*     */     
/*     */ 
/* 206 */     for (int j = 0; j < nbttaglist.func_74745_c(); j++)
/*     */     {
/* 208 */       if (j >= 5) break;
/* 209 */       l[j] = nbttaglist.func_150305_b(j).func_74765_d("id");
/*     */     }
/*     */     
/* 212 */     return l;
/*     */   }
/*     */   
/*     */   public boolean applyUpgrade(ItemStack focusstack, FocusUpgradeType type, int rank)
/*     */   {
/* 217 */     short[] upgrades = getAppliedUpgrades(focusstack);
/* 218 */     if ((upgrades[(rank - 1)] != -1) || (rank < 1) || (rank > 5)) {
/* 219 */       return false;
/*     */     }
/* 221 */     upgrades[(rank - 1)] = type.id;
/* 222 */     setFocusUpgradeTagList(focusstack, upgrades);
/* 223 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean canApplyUpgrade(ItemStack focusstack, EntityPlayer player, FocusUpgradeType type, int rank)
/*     */   {
/* 232 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean isUpgradedWith(ItemStack focusstack, FocusUpgradeType focusUpgradetype)
/*     */   {
/* 239 */     return getUpgradeLevel(focusstack, focusUpgradetype) > 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getUpgradeLevel(ItemStack focusstack, FocusUpgradeType focusUpgradetype)
/*     */   {
/* 246 */     short[] list = getAppliedUpgrades(focusstack);
/* 247 */     int level = 0;
/* 248 */     for (short id : list) {
/* 249 */       if (id == focusUpgradetype.id)
/*     */       {
/* 251 */         level++;
/*     */       }
/*     */     }
/* 254 */     return level;
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
/*     */   public boolean onFocusActivation(ItemStack wandstack, World world, EntityLivingBase entity, MovingObjectPosition movingobjectposition, int useCount)
/*     */   {
/* 268 */     return true;
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
/*     */   public boolean onFocusBlockStartBreak(ItemStack wandstack, BlockPos pos, EntityPlayer player)
/*     */   {
/* 288 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private NBTTagList getFocusUpgradeTagList(ItemStack focusstack)
/*     */   {
/* 296 */     return focusstack.func_77978_p() == null ? null : focusstack.func_77978_p().func_150295_c("upgrade", 10);
/*     */   }
/*     */   
/*     */   private void setFocusUpgradeTagList(ItemStack focusstack, short[] upgrades) {
/* 300 */     if (!focusstack.func_77942_o())
/* 301 */       focusstack.func_77982_d(new NBTTagCompound());
/* 302 */     NBTTagCompound nbttagcompound = focusstack.func_77978_p();
/* 303 */     NBTTagList tlist = new NBTTagList();
/* 304 */     nbttagcompound.func_74782_a("upgrade", tlist);
/* 305 */     for (short id : upgrades) {
/* 306 */       NBTTagCompound f = new NBTTagCompound();
/* 307 */       f.func_74777_a("id", id);
/* 308 */       tlist.func_74742_a(f);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/api/wands/ItemFocusBasic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */