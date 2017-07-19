/*     */ package thaumcraft.common.items.resources;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.items.ItemGenericEssentiaContainer;
/*     */ import thaumcraft.common.tiles.crafting.TileAlembic;
/*     */ import thaumcraft.common.tiles.essentia.TileJarFillable;
/*     */ 
/*     */ public class ItemPhial extends ItemGenericEssentiaContainer implements thaumcraft.common.items.IVariantItems
/*     */ {
/*     */   public ItemPhial()
/*     */   {
/*  29 */     super(8);
/*     */   }
/*     */   
/*     */   public String[] getVariantNames()
/*     */   {
/*  34 */     return new String[] { "empty", "filled" };
/*     */   }
/*     */   
/*     */   public int[] getVariantMeta()
/*     */   {
/*  39 */     return new int[] { 0, 1 };
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_82790_a(ItemStack stack, int layer)
/*     */   {
/*  46 */     if ((stack.func_77952_i() == 1) && (getAspects(stack) != null) && (layer == 1)) {
/*  47 */       return getAspects(stack).getAspects()[0].getColor();
/*     */     }
/*  49 */     return 16777215;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*     */   {
/*  55 */     par3List.add(new ItemStack(this, 1, 0));
/*  56 */     for (Aspect tag : Aspect.aspects.values()) {
/*  57 */       ItemStack i = new ItemStack(this, 1, 1);
/*  58 */       setAspects(i, new AspectList().add(tag, 8));
/*  59 */       par3List.add(i);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public String func_77667_c(ItemStack stack)
/*     */   {
/*  66 */     return super.func_77658_a() + "." + getVariantNames()[stack.func_77952_i()];
/*     */   }
/*     */   
/*     */   public void func_77663_a(ItemStack stack, World world, Entity entity, int par4, boolean par5)
/*     */   {
/*  71 */     if ((!world.field_72995_K) && (!stack.func_77942_o()) && (stack.func_77952_i() == 1)) {
/*  72 */       stack.func_77964_b(0);
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_77622_d(ItemStack stack, World world, EntityPlayer player)
/*     */   {
/*  78 */     if ((!world.field_72995_K) && (!stack.func_77942_o()) && (stack.func_77952_i() == 1)) {
/*  79 */       stack.func_77964_b(0);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean onItemUseFirst(ItemStack itemstack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float f1, float f2, float f3)
/*     */   {
/*  87 */     Block bi = world.func_180495_p(pos).func_177230_c();
/*  88 */     int md = bi.func_176201_c(world.func_180495_p(pos));
/*     */     
/*     */ 
/*  91 */     if ((itemstack.func_77952_i() == 0) && (bi == BlocksTC.alembic)) {
/*  92 */       TileAlembic tile = (TileAlembic)world.func_175625_s(pos);
/*  93 */       if (tile.amount >= 8) {
/*  94 */         if (world.field_72995_K) {
/*  95 */           player.func_71038_i();
/*  96 */           return false;
/*     */         }
/*  98 */         ItemStack phial = new ItemStack(this, 1, 1);
/*  99 */         setAspects(phial, new AspectList().add(tile.aspect, 8));
/* 100 */         if (tile.takeFromContainer(tile.aspect, 8)) {
/* 101 */           itemstack.field_77994_a -= 1;
/* 102 */           if (!player.field_71071_by.func_70441_a(phial)) {
/* 103 */             world.func_72838_d(new EntityItem(world, player.field_70165_t, player.field_70163_u, player.field_70161_v, phial));
/*     */           }
/* 105 */           world.func_72956_a(player, "game.neutral.swim", 0.25F, 1.0F);
/* 106 */           player.field_71069_bz.func_75142_b();
/* 107 */           return true;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 113 */     if ((itemstack.func_77952_i() == 0) && (bi == BlocksTC.jar) && ((md == 0) || (md == 1))) {
/* 114 */       TileJarFillable tile = (TileJarFillable)world.func_175625_s(pos);
/* 115 */       if (tile.amount >= 8) {
/* 116 */         if (world.field_72995_K) {
/* 117 */           player.func_71038_i();
/* 118 */           return false;
/*     */         }
/* 120 */         Aspect asp = Aspect.getAspect(tile.aspect.getTag());
/* 121 */         if (tile.takeFromContainer(asp, 8)) {
/* 122 */           itemstack.field_77994_a -= 1;
/* 123 */           ItemStack phial = new ItemStack(this, 1, 1);
/* 124 */           setAspects(phial, new AspectList().add(asp, 8));
/* 125 */           if (!player.field_71071_by.func_70441_a(phial)) {
/* 126 */             world.func_72838_d(new EntityItem(world, pos.func_177958_n() + 0.5F, pos.func_177956_o() + 0.5F, pos.func_177952_p() + 0.5F, phial));
/*     */           }
/* 128 */           world.func_72956_a(player, "game.neutral.swim", 0.25F, 1.0F);
/* 129 */           player.field_71069_bz.func_75142_b();
/* 130 */           return true;
/*     */         }
/*     */       }
/*     */     }
/* 134 */     AspectList al = getAspects(itemstack);
/* 135 */     if ((al != null) && (al.size() == 1)) {
/* 136 */       Aspect aspect = al.getAspects()[0];
/* 137 */       if ((itemstack.func_77952_i() != 0) && (bi == BlocksTC.jar) && ((md == 0) || (md == 1))) {
/* 138 */         TileJarFillable tile = (TileJarFillable)world.func_175625_s(pos);
/* 139 */         if ((tile.amount <= tile.maxAmount - 8) && (tile.doesContainerAccept(aspect))) {
/* 140 */           if (world.field_72995_K) {
/* 141 */             player.func_71038_i();
/* 142 */             return false;
/*     */           }
/* 144 */           if (tile.addToContainer(aspect, 8) == 0) {
/* 145 */             world.func_175689_h(pos);
/* 146 */             tile.func_70296_d();
/* 147 */             itemstack.field_77994_a -= 1;
/* 148 */             if (!player.field_71071_by.func_70441_a(new ItemStack(this, 1, 0))) {
/* 149 */               world.func_72838_d(new EntityItem(world, pos.func_177958_n() + 0.5F, pos.func_177956_o() + 0.5F, pos.func_177952_p() + 0.5F, new ItemStack(this, 1, 0)));
/*     */             }
/* 151 */             world.func_72956_a(player, "game.neutral.swim", 0.25F, 1.0F);
/* 152 */             player.field_71069_bz.func_75142_b();
/* 153 */             return true;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 160 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/items/resources/ItemPhial.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */