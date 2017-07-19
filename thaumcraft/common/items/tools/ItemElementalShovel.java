/*     */ package thaumcraft.common.items.tools;
/*     */ 
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.Block.SoundType;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.Item.ToolMaterial;
/*     */ import net.minecraft.item.ItemSpade;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.items.IArchitect;
/*     */ import thaumcraft.api.items.IArchitect.EnumAxis;
/*     */ import thaumcraft.api.items.IRepairable;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.lib.enchantment.EnumInfusionEnchantment;
/*     */ import thaumcraft.common.lib.utils.InventoryUtils;
/*     */ 
/*     */ public class ItemElementalShovel extends ItemSpade implements IRepairable, IArchitect
/*     */ {
/*  38 */   private static final Block[] isEffective = { Blocks.field_150349_c, Blocks.field_150346_d, Blocks.field_150354_m, Blocks.field_150351_n, Blocks.field_150431_aC, Blocks.field_150433_aE, Blocks.field_150435_aG, Blocks.field_150458_ak, Blocks.field_150425_aM, Blocks.field_150391_bh };
/*     */   
/*     */ 
/*     */ 
/*     */   public ItemElementalShovel(Item.ToolMaterial enumtoolmaterial)
/*     */   {
/*  44 */     super(enumtoolmaterial);
/*     */   }
/*     */   
/*     */   public java.util.Set<String> getToolClasses(ItemStack stack)
/*     */   {
/*  49 */     return ImmutableSet.of("shovel");
/*     */   }
/*     */   
/*     */   public EnumRarity func_77613_e(ItemStack itemstack)
/*     */   {
/*  54 */     return EnumRarity.RARE;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack)
/*     */   {
/*  60 */     return par2ItemStack.func_77969_a(new ItemStack(ItemsTC.ingots, 1, 0)) ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_180614_a(ItemStack itemstack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float par8, float par9, float par10)
/*     */   {
/*  67 */     IBlockState bs = world.func_180495_p(pos);
/*  68 */     net.minecraft.tileentity.TileEntity te = world.func_175625_s(pos);
/*  69 */     if (te == null)
/*     */     {
/*  71 */       for (int aa = -1; aa <= 1; aa++) {
/*  72 */         for (int bb = -1; bb <= 1; bb++)
/*     */         {
/*  74 */           int xx = 0;
/*  75 */           int yy = 0;
/*  76 */           int zz = 0;
/*     */           
/*  78 */           byte o = getOrientation(itemstack);
/*  79 */           if (o == 1) {
/*  80 */             yy = bb;
/*  81 */             if (side.ordinal() <= 1) {
/*  82 */               int l = MathHelper.func_76128_c(player.field_70177_z * 4.0F / 360.0F + 0.5D) & 0x3;
/*  83 */               if ((l == 0) || (l == 2)) {
/*  84 */                 xx = aa;
/*     */               } else {
/*  86 */                 zz = aa;
/*     */               }
/*  88 */             } else if (side.ordinal() <= 3) {
/*  89 */               zz = aa;
/*     */             } else {
/*  91 */               xx = aa;
/*     */             }
/*  93 */           } else if (o == 2)
/*     */           {
/*  95 */             if (side.ordinal() <= 1) {
/*  96 */               int l = MathHelper.func_76128_c(player.field_70177_z * 4.0F / 360.0F + 0.5D) & 0x3;
/*  97 */               yy = bb;
/*  98 */               if ((l == 0) || (l == 2)) {
/*  99 */                 xx = aa;
/*     */               } else {
/* 101 */                 zz = aa;
/*     */               }
/*     */             } else {
/* 104 */               zz = bb;
/* 105 */               xx = aa;
/*     */             }
/*     */           }
/* 108 */           else if (side.ordinal() <= 1) {
/* 109 */             xx = aa;
/* 110 */             zz = bb;
/* 111 */           } else if (side.ordinal() <= 3) {
/* 112 */             xx = aa;
/* 113 */             yy = bb;
/*     */           } else {
/* 115 */             zz = aa;
/* 116 */             yy = bb;
/*     */           }
/*     */           
/*     */ 
/* 120 */           BlockPos p2 = pos.func_177972_a(side).func_177982_a(xx, yy, zz);
/* 121 */           IBlockState b2 = world.func_180495_p(p2);
/*     */           
/* 123 */           if ((world.func_175623_d(p2)) || (b2 == Blocks.field_150395_bd) || (b2 == Blocks.field_150329_H) || (b2.func_177230_c().func_149688_o() == Material.field_151586_h) || (b2 == Blocks.field_150330_I) || (b2.func_177230_c().func_176200_f(world, p2)))
/*     */           {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 129 */             if ((player.field_71075_bZ.field_75098_d) || (InventoryUtils.consumeInventoryItem(player, Item.func_150898_a(bs.func_177230_c()), bs.func_177230_c().func_176201_c(bs))))
/*     */             {
/*     */ 
/*     */ 
/* 133 */               world.func_72980_b(p2.func_177958_n(), p2.func_177956_o(), p2.func_177952_p(), bs.func_177230_c().field_149762_H.func_150495_a(), 0.6F, 0.9F + world.field_73012_v.nextFloat() * 0.2F, false);
/*     */               
/*     */ 
/* 136 */               world.func_175656_a(p2, bs);
/* 137 */               itemstack.func_77972_a(1, player);
/*     */               
/* 139 */               if (world.field_72995_K) {
/* 140 */                 Thaumcraft.proxy.getFX().drawBamf(p2, 8401408, false, false, false);
/*     */               }
/* 142 */               player.func_71038_i();
/* 143 */             } else if ((bs.func_177230_c() == Blocks.field_150349_c) && ((player.field_71075_bZ.field_75098_d) || (InventoryUtils.consumeInventoryItem(player, Item.func_150898_a(Blocks.field_150346_d), 0))))
/*     */             {
/*     */ 
/*     */ 
/* 147 */               world.func_72980_b(p2.func_177958_n(), p2.func_177956_o(), p2.func_177952_p(), bs.func_177230_c().field_149762_H.func_150495_a(), 0.6F, 0.9F + world.field_73012_v.nextFloat() * 0.2F, false);
/*     */               
/*     */ 
/*     */ 
/* 151 */               world.func_175656_a(p2, Blocks.field_150346_d.func_176223_P());
/* 152 */               itemstack.func_77972_a(1, player);
/* 153 */               if (world.field_72995_K) {
/* 154 */                 Thaumcraft.proxy.getFX().drawBamf(p2, 8401408, false, false, false);
/*     */               }
/* 156 */               player.func_71038_i();
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 164 */     return false;
/*     */   }
/*     */   
/*     */   private boolean isEffectiveAgainst(Block block) {
/* 168 */     for (int var3 = 0; var3 < isEffective.length; var3++) {
/* 169 */       if (isEffective[var3] == block) {
/* 170 */         return true;
/*     */       }
/*     */     }
/* 173 */     return false;
/*     */   }
/*     */   
/* 176 */   EnumFacing side = EnumFacing.DOWN;
/*     */   
/*     */   @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*     */   public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*     */   {
/* 181 */     ItemStack w1 = new ItemStack(this);
/* 182 */     EnumInfusionEnchantment.addInfusionEnchantment(w1, EnumInfusionEnchantment.DESTRUCTIVE, 1);
/* 183 */     par3List.add(w1);
/*     */   }
/*     */   
/*     */   public void func_77663_a(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
/*     */   {
/* 188 */     super.func_77663_a(stack, worldIn, entityIn, itemSlot, isSelected);
/*     */     
/* 190 */     if (entityIn.field_70173_aa % 100 == 0) {
/* 191 */       List<EnumInfusionEnchantment> list = EnumInfusionEnchantment.getInfusionEnchantments(stack);
/* 192 */       if (!list.contains(EnumInfusionEnchantment.DESTRUCTIVE)) {
/* 193 */         EnumInfusionEnchantment.addInfusionEnchantment(stack, EnumInfusionEnchantment.DESTRUCTIVE, 1);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ArrayList<BlockPos> getArchitectBlocks(ItemStack focusstack, World world, BlockPos pos, EnumFacing side, EntityPlayer player)
/*     */   {
/* 202 */     ArrayList<BlockPos> b = new ArrayList();
/* 203 */     if (!player.func_70093_af()) { return b;
/*     */     }
/* 205 */     for (int aa = -1; aa <= 1; aa++) {
/* 206 */       for (int bb = -1; bb <= 1; bb++) {
/* 207 */         int xx = 0;
/* 208 */         int yy = 0;
/* 209 */         int zz = 0;
/* 210 */         byte o = getOrientation(focusstack);
/* 211 */         if (o == 1) {
/* 212 */           yy = bb;
/* 213 */           if (side.ordinal() <= 1) {
/* 214 */             int l = MathHelper.func_76128_c(player.field_70177_z * 4.0F / 360.0F + 0.5D) & 0x3;
/* 215 */             if ((l == 0) || (l == 2)) {
/* 216 */               xx = aa;
/*     */             } else {
/* 218 */               zz = aa;
/*     */             }
/* 220 */           } else if (side.ordinal() <= 3) {
/* 221 */             zz = aa;
/*     */           } else {
/* 223 */             xx = aa;
/*     */           }
/* 225 */         } else if (o == 2)
/*     */         {
/* 227 */           if (side.ordinal() <= 1) {
/* 228 */             int l = MathHelper.func_76128_c(player.field_70177_z * 4.0F / 360.0F + 0.5D) & 0x3;
/* 229 */             yy = bb;
/* 230 */             if ((l == 0) || (l == 2)) {
/* 231 */               xx = aa;
/*     */             } else {
/* 233 */               zz = aa;
/*     */             }
/*     */           } else {
/* 236 */             zz = bb;
/* 237 */             xx = aa;
/*     */           }
/*     */         }
/* 240 */         else if (side.ordinal() <= 1) {
/* 241 */           xx = aa;
/* 242 */           zz = bb;
/* 243 */         } else if (side.ordinal() <= 3) {
/* 244 */           xx = aa;
/* 245 */           yy = bb;
/*     */         } else {
/* 247 */           zz = aa;
/* 248 */           yy = bb;
/*     */         }
/*     */         
/* 251 */         BlockPos p2 = pos.func_177972_a(side).func_177982_a(xx, yy, zz);
/* 252 */         IBlockState b2 = world.func_180495_p(p2);
/* 253 */         if ((world.func_175623_d(p2)) || (b2 == Blocks.field_150395_bd) || (b2 == Blocks.field_150329_H) || (b2.func_177230_c().func_149688_o() == Material.field_151586_h) || (b2 == Blocks.field_150330_I) || (b2.func_177230_c().func_176200_f(world, p2)))
/*     */         {
/*     */ 
/*     */ 
/* 257 */           b.add(p2);
/*     */         }
/*     */       }
/*     */     }
/* 261 */     return b;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean showAxis(ItemStack stack, World world, EntityPlayer player, EnumFacing side, IArchitect.EnumAxis axis)
/*     */   {
/* 267 */     return false;
/*     */   }
/*     */   
/*     */   public static byte getOrientation(ItemStack stack) {
/* 271 */     if ((stack.func_77942_o()) && (stack.func_77978_p().func_74764_b("or"))) {
/* 272 */       return stack.func_77978_p().func_74771_c("or");
/*     */     }
/* 274 */     return 0;
/*     */   }
/*     */   
/*     */   public static void setOrientation(ItemStack stack, byte o) {
/* 278 */     if (!stack.func_77942_o()) stack.func_77982_d(new NBTTagCompound());
/* 279 */     if (stack.func_77942_o()) {
/* 280 */       stack.func_77978_p().func_74774_a("or", (byte)(o % 3));
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/items/tools/ItemElementalShovel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */