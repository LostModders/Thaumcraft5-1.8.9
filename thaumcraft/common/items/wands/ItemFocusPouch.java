/*     */ package thaumcraft.common.items.wands;
/*     */ 
/*     */ import baubles.api.BaubleType;
/*     */ import baubles.api.IBauble;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ 
/*     */ public class ItemFocusPouch
/*     */   extends Item
/*     */   implements IBauble
/*     */ {
/*     */   public ItemFocusPouch()
/*     */   {
/*  22 */     func_77625_d(1);
/*  23 */     func_77627_a(false);
/*  24 */     func_77656_e(0);
/*  25 */     func_77637_a(Thaumcraft.tabTC);
/*     */   }
/*     */   
/*     */   public boolean func_77651_p()
/*     */   {
/*  30 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public EnumRarity func_77613_e(ItemStack itemstack)
/*     */   {
/*  36 */     return EnumRarity.UNCOMMON;
/*     */   }
/*     */   
/*     */   public boolean func_77636_d(ItemStack par1ItemStack)
/*     */   {
/*  41 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
/*     */   {
/*  48 */     if (!par2World.field_72995_K) {
/*  49 */       par3EntityPlayer.openGui(Thaumcraft.instance, 5, par2World, MathHelper.func_76128_c(par3EntityPlayer.field_70165_t), MathHelper.func_76128_c(par3EntityPlayer.field_70163_u), MathHelper.func_76128_c(par3EntityPlayer.field_70161_v));
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  55 */     return super.func_77659_a(par1ItemStack, par2World, par3EntityPlayer);
/*     */   }
/*     */   
/*     */   public ItemStack[] getInventory(ItemStack item) {
/*  59 */     ItemStack[] stackList = new ItemStack[18];
/*     */     
/*  61 */     if (item.func_77942_o()) {
/*  62 */       NBTTagList var2 = item.func_77978_p().func_150295_c("Inventory", 10);
/*  63 */       for (int var3 = 0; var3 < var2.func_74745_c(); var3++)
/*     */       {
/*  65 */         NBTTagCompound var4 = var2.func_150305_b(var3);
/*  66 */         int var5 = var4.func_74771_c("Slot") & 0xFF;
/*     */         
/*  68 */         if ((var5 >= 0) && (var5 < stackList.length))
/*     */         {
/*  70 */           stackList[var5] = ItemStack.func_77949_a(var4);
/*     */         }
/*     */       }
/*     */     }
/*  74 */     return stackList;
/*     */   }
/*     */   
/*     */   public void setInventory(ItemStack item, ItemStack[] stackList) {
/*  78 */     NBTTagList var2 = new NBTTagList();
/*     */     
/*  80 */     for (int var3 = 0; var3 < stackList.length; var3++)
/*     */     {
/*  82 */       if (stackList[var3] != null)
/*     */       {
/*  84 */         NBTTagCompound var4 = new NBTTagCompound();
/*  85 */         var4.func_74774_a("Slot", (byte)var3);
/*  86 */         stackList[var3].func_77955_b(var4);
/*  87 */         var2.func_74742_a(var4);
/*     */       }
/*     */     }
/*     */     
/*  91 */     item.func_77983_a("Inventory", var2);
/*     */   }
/*     */   
/*     */   public BaubleType getBaubleType(ItemStack itemstack)
/*     */   {
/*  96 */     return BaubleType.BELT;
/*     */   }
/*     */   
/*     */ 
/*     */   public void onWornTick(ItemStack itemstack, EntityLivingBase player) {}
/*     */   
/*     */ 
/*     */   public void onEquipped(ItemStack itemstack, EntityLivingBase player) {}
/*     */   
/*     */ 
/*     */   public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {}
/*     */   
/*     */   public boolean canEquip(ItemStack itemstack, EntityLivingBase player)
/*     */   {
/* 110 */     return true;
/*     */   }
/*     */   
/*     */   public boolean canUnequip(ItemStack itemstack, EntityLivingBase player)
/*     */   {
/* 115 */     return true;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/items/wands/ItemFocusPouch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */