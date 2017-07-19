/*     */ package thaumcraft.common.blocks.world;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyEnum;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.IStringSerializable;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.common.blocks.BlockTC;
/*     */ import thaumcraft.common.lib.utils.Utils;
/*     */ 
/*     */ public class BlockLoot
/*     */   extends BlockTC
/*     */ {
/*  24 */   public static final PropertyEnum TYPE = PropertyEnum.func_177709_a("type", LootType.class);
/*     */   
/*     */   public BlockLoot(Material mat) {
/*  27 */     super(mat);
/*  28 */     func_149711_c(0.15F);
/*  29 */     func_149752_b(0.0F);
/*  30 */     func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(TYPE, LootType.COMMON));
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149662_c()
/*     */   {
/*  36 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149686_d()
/*     */   {
/*  42 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   protected boolean func_149700_E()
/*     */   {
/*  48 */     return true;
/*     */   }
/*     */   
/*     */   public boolean canHarvestBlock(IBlockAccess world, BlockPos pos, EntityPlayer player)
/*     */   {
/*  53 */     return true;
/*     */   }
/*     */   
/*     */   public AxisAlignedBB func_180646_a(World w, BlockPos pos)
/*     */   {
/*  58 */     if (func_149688_o() == Material.field_151576_e) {
/*  59 */       func_149676_a(0.125F, 0.0625F, 0.125F, 0.875F, 0.8125F, 0.875F);
/*     */     } else {
/*  61 */       func_149676_a(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
/*     */     }
/*  63 */     return super.func_180646_a(w, pos);
/*     */   }
/*     */   
/*     */ 
/*     */   public IBlockState func_176203_a(int meta)
/*     */   {
/*  69 */     return func_176223_P().func_177226_a(TYPE, LootType.values()[meta]);
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_176201_c(IBlockState state)
/*     */   {
/*  75 */     int meta = ((LootType)state.func_177229_b(TYPE)).ordinal();
/*     */     
/*  77 */     return meta;
/*     */   }
/*     */   
/*     */ 
/*     */   protected BlockState func_180661_e()
/*     */   {
/*  83 */     return new BlockState(this, new IProperty[] { TYPE });
/*     */   }
/*     */   
/*     */ 
/*     */   public IProperty[] getProperties()
/*     */   {
/*  89 */     return new IProperty[] { TYPE };
/*     */   }
/*     */   
/*     */ 
/*     */   public String getStateName(IBlockState state, boolean fullName)
/*     */   {
/*  95 */     LootType type = (LootType)state.func_177229_b(TYPE);
/*     */     
/*  97 */     return (fullName ? (func_149688_o() == Material.field_151576_e ? "loot_urn" : "loot_crate") + "_" : "") + type.func_176610_l();
/*     */   }
/*     */   
/* 100 */   Random rand = new Random();
/*     */   
/*     */   public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
/*     */   {
/* 104 */     int md = func_176201_c(state);
/* 105 */     ArrayList<ItemStack> ret = new ArrayList();
/* 106 */     int q = 1 + md + this.rand.nextInt(3);
/* 107 */     for (int a = 0; a < q; a++) {
/* 108 */       ItemStack is = Utils.generateLoot(md, this.rand);
/* 109 */       if (is != null) {
/* 110 */         ret.add(is.func_77946_l());
/*     */       }
/*     */     }
/* 113 */     return ret;
/*     */   }
/*     */   
/*     */   public static enum LootType implements IStringSerializable
/*     */   {
/* 118 */     COMMON,  UNCOMMON,  RARE;
/*     */     
/*     */     private LootType() {}
/*     */     
/*     */     public String func_176610_l() {
/* 123 */       return name().toLowerCase();
/*     */     }
/*     */     
/*     */ 
/*     */     public String toString()
/*     */     {
/* 129 */       return func_176610_l();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/world/BlockLoot.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */