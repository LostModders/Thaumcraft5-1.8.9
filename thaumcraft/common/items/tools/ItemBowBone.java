/*     */ package thaumcraft.common.items.tools;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.resources.model.ModelResourceLocation;
/*     */ import net.minecraft.enchantment.Enchantment;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.entity.projectile.EntityArrow;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.event.entity.player.ArrowLooseEvent;
/*     */ import net.minecraftforge.event.entity.player.ArrowNockEvent;
/*     */ import net.minecraftforge.fml.common.eventhandler.EventBus;
/*     */ import thaumcraft.api.items.IRepairable;
/*     */ 
/*     */ public class ItemBowBone extends net.minecraft.item.ItemBow implements IRepairable, thaumcraft.common.items.IVariantItems
/*     */ {
/*     */   public ItemBowBone()
/*     */   {
/*  24 */     this.field_77777_bU = 1;
/*  25 */     func_77656_e(512);
/*     */   }
/*     */   
/*     */   public String[] getVariantNames()
/*     */   {
/*  30 */     return new String[] { "base", "pulling_0", "pulling_1", "pulling_2" };
/*     */   }
/*     */   
/*     */   public int[] getVariantMeta()
/*     */   {
/*  35 */     return new int[] { 0, 1, 2, 3 };
/*     */   }
/*     */   
/*     */   public void onUsingTick(ItemStack stack, EntityPlayer player, int count)
/*     */   {
/*  40 */     int ticks = func_77626_a(stack) - count;
/*  41 */     if (ticks > 18) { player.func_71034_by();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_77615_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4)
/*     */   {
/*  50 */     int j = func_77626_a(par1ItemStack) - par4;
/*     */     
/*  52 */     ArrowLooseEvent event = new ArrowLooseEvent(par3EntityPlayer, par1ItemStack, j);
/*  53 */     MinecraftForge.EVENT_BUS.post(event);
/*  54 */     if (event.isCanceled())
/*     */     {
/*  56 */       return;
/*     */     }
/*  58 */     j = event.charge;
/*     */     
/*  60 */     boolean flag = (par3EntityPlayer.field_71075_bZ.field_75098_d) || (EnchantmentHelper.func_77506_a(Enchantment.field_77342_w.field_77352_x, par1ItemStack) > 0);
/*     */     
/*  62 */     if ((flag) || (par3EntityPlayer.field_71071_by.func_146028_b(Items.field_151032_g)))
/*     */     {
/*  64 */       float f = j / 10.0F;
/*  65 */       f = (f * f + f * 2.0F) / 3.0F;
/*     */       
/*  67 */       if (f < 0.1D)
/*     */       {
/*  69 */         return;
/*     */       }
/*     */       
/*  72 */       if (f > 1.0F)
/*     */       {
/*  74 */         f = 1.0F;
/*     */       }
/*     */       
/*  77 */       EntityArrow entityarrow = new EntityArrow(par2World, par3EntityPlayer, f * 2.5F);
/*  78 */       entityarrow.func_70239_b(entityarrow.func_70242_d() + 0.5D);
/*     */       
/*  80 */       int k = EnchantmentHelper.func_77506_a(Enchantment.field_77345_t.field_77352_x, par1ItemStack);
/*     */       
/*  82 */       if (k > 0)
/*     */       {
/*  84 */         entityarrow.func_70239_b(entityarrow.func_70242_d() + k * 0.5D + 0.5D);
/*     */       }
/*     */       
/*  87 */       int l = EnchantmentHelper.func_77506_a(Enchantment.field_77344_u.field_77352_x, par1ItemStack);
/*     */       
/*  89 */       if (l > 0)
/*     */       {
/*  91 */         entityarrow.func_70240_a(l);
/*     */       }
/*     */       
/*  94 */       if (EnchantmentHelper.func_77506_a(Enchantment.field_77343_v.field_77352_x, par1ItemStack) > 0)
/*     */       {
/*  96 */         entityarrow.func_70015_d(100);
/*     */       }
/*     */       
/*  99 */       par1ItemStack.func_77972_a(1, par3EntityPlayer);
/* 100 */       par2World.func_72956_a(par3EntityPlayer, "random.bow", 1.0F, 1.0F / (field_77697_d.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
/*     */       
/* 102 */       if (flag)
/*     */       {
/* 104 */         entityarrow.field_70251_a = 2;
/*     */       }
/*     */       else
/*     */       {
/* 108 */         par3EntityPlayer.field_71071_by.func_146026_a(Items.field_151032_g);
/*     */       }
/*     */       
/* 111 */       if (!par2World.field_72995_K)
/*     */       {
/* 113 */         par2World.func_72838_d(entityarrow);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
/*     */   {
/* 125 */     ArrowNockEvent event = new ArrowNockEvent(par3EntityPlayer, par1ItemStack);
/* 126 */     MinecraftForge.EVENT_BUS.post(event);
/* 127 */     if (event.isCanceled())
/*     */     {
/* 129 */       return event.result;
/*     */     }
/*     */     
/* 132 */     if ((par3EntityPlayer.field_71075_bZ.field_75098_d) || (par3EntityPlayer.field_71071_by.func_146028_b(Items.field_151032_g)) || (EnchantmentHelper.func_77506_a(Enchantment.field_77342_w.field_77352_x, par1ItemStack) > 0))
/*     */     {
/*     */ 
/* 135 */       par3EntityPlayer.func_71008_a(par1ItemStack, func_77626_a(par1ItemStack));
/*     */     }
/*     */     
/* 138 */     return par1ItemStack;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int func_77619_b()
/*     */   {
/* 147 */     return 3;
/*     */   }
/*     */   
/*     */   public ModelResourceLocation getModel(ItemStack stack, EntityPlayer player, int useRemaining)
/*     */   {
/* 152 */     ModelResourceLocation modelresourcelocation = new ModelResourceLocation("Thaumcraft:bone_bow_base", "inventory");
/*     */     
/* 154 */     if ((stack.func_77973_b() == this) && (player.func_71011_bu() != null))
/*     */     {
/* 156 */       if (func_77626_a(stack) - useRemaining >= 13)
/*     */       {
/* 158 */         modelresourcelocation = new ModelResourceLocation("Thaumcraft:bone_bow_pulling_2", "inventory");
/*     */ 
/*     */       }
/* 161 */       else if (func_77626_a(stack) - useRemaining > 7)
/*     */       {
/* 163 */         modelresourcelocation = new ModelResourceLocation("Thaumcraft:bone_bow_pulling_1", "inventory");
/*     */ 
/*     */       }
/* 166 */       else if (func_77626_a(stack) - useRemaining > 0)
/*     */       {
/* 168 */         modelresourcelocation = new ModelResourceLocation("Thaumcraft:bone_bow_pulling_0", "inventory");
/*     */       }
/*     */     }
/*     */     
/* 172 */     return modelresourcelocation;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/items/tools/ItemBowBone.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */