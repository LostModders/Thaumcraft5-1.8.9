/*     */ package thaumcraft.common.blocks.misc;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyEnum;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.IStringSerializable;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.blocks.BlockTC;
/*     */ import thaumcraft.common.blocks.devices.BlockGolemBuilder;
/*     */ import thaumcraft.common.blocks.devices.BlockInfernalFurnace;
/*     */ 
/*     */ 
/*     */ public class BlockPlaceholder
/*     */   extends BlockTC
/*     */ {
/*  26 */   public static final PropertyEnum VARIANT = PropertyEnum.func_177709_a("type", PlaceholderType.class);
/*     */   
/*  28 */   private final Random rand = new Random();
/*     */   
/*     */   public BlockPlaceholder() {
/*  31 */     super(Material.field_151576_e);
/*     */     
/*  33 */     func_149711_c(2.5F);
/*  34 */     func_149672_a(field_149769_e);
/*     */     
/*  36 */     IBlockState bs = this.field_176227_L.func_177621_b();
/*  37 */     bs.func_177226_a(VARIANT, PlaceholderType.FURNACE_BRICK);
/*  38 */     func_180632_j(bs);
/*     */     
/*  40 */     func_149647_a(null);
/*     */   }
/*     */   
/*     */ 
/*     */   protected boolean func_149700_E()
/*     */   {
/*  46 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149662_c()
/*     */   {
/*  52 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149686_d()
/*     */   {
/*  58 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_149645_b()
/*     */   {
/*  64 */     return -1;
/*     */   }
/*     */   
/*     */   public Item func_180660_a(IBlockState state, Random rand, int fortune)
/*     */   {
/*  69 */     int t = ((PlaceholderType)state.func_177229_b(VARIANT)).ordinal();
/*  70 */     if (t == 0) return Item.func_150898_a(Blocks.field_150385_bj);
/*  71 */     if (t == 1) return Item.func_150898_a(Blocks.field_150343_Z);
/*  72 */     if (t == 2) return Item.func_150898_a(Blocks.field_150411_aY);
/*  73 */     if (t == 3) return Item.func_150898_a(Blocks.field_150467_bQ);
/*  74 */     if (t == 4) return Item.func_150898_a(Blocks.field_150383_bp);
/*  75 */     if (t == 5) return Item.func_150898_a(BlocksTC.tableStone);
/*  76 */     return Item.func_150899_d(0);
/*     */   }
/*     */   
/*     */   public int func_180651_a(IBlockState state)
/*     */   {
/*  81 */     return 0;
/*     */   }
/*     */   
/*     */   public boolean func_180639_a(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ)
/*     */   {
/*  86 */     if (world.field_72995_K) return true;
/*  87 */     int t = ((PlaceholderType)state.func_177229_b(VARIANT)).ordinal();
/*  88 */     if ((t > 1) && (t <= 5)) {
/*  89 */       for (int a = -1; a <= 1; a++) {
/*  90 */         for (int b = -1; b <= 1; b++)
/*  91 */           for (int c = -1; c <= 1; c++) {
/*  92 */             IBlockState s = world.func_180495_p(pos.func_177982_a(a, b, c));
/*  93 */             if (s.func_177230_c() == BlocksTC.golemBuilder) {
/*  94 */               player.openGui(Thaumcraft.instance, 19, world, pos.func_177982_a(a, b, c).func_177958_n(), pos.func_177982_a(a, b, c).func_177956_o(), pos.func_177982_a(a, b, c).func_177952_p());
/*     */               
/*  96 */               return true;
/*     */             }
/*     */           }
/*     */       }
/*     */     }
/* 101 */     return super.func_180639_a(world, pos, state, player, side, hitX, hitY, hitZ);
/*     */   }
/*     */   
/*     */   public void func_180663_b(World worldIn, BlockPos pos, IBlockState state)
/*     */   {
/* 106 */     int t = ((PlaceholderType)state.func_177229_b(VARIANT)).ordinal();
/* 107 */     if ((t <= 1) && (!BlockInfernalFurnace.ignore) && (!worldIn.field_72995_K))
/*     */     {
/* 109 */       for (int a = -1; a <= 1; a++) {
/* 110 */         for (int b = -1; b <= 1; b++)
/* 111 */           for (int c = -1; c <= 1; c++) {
/* 112 */             IBlockState s = worldIn.func_180495_p(pos.func_177982_a(a, b, c));
/* 113 */             if (s.func_177230_c() == BlocksTC.infernalFurnace) {
/* 114 */               BlockInfernalFurnace.destroyFurnace(worldIn, pos.func_177982_a(a, b, c), s, pos);
/*     */               break label246;
/*     */             }
/*     */           }
/*     */       }
/* 119 */     } else if ((t <= 5) && (!BlockGolemBuilder.ignore) && (!worldIn.field_72995_K))
/*     */     {
/* 121 */       for (int a = -1; a <= 1; a++)
/* 122 */         for (int b = -1; b <= 1; b++)
/* 123 */           for (int c = -1; c <= 1; c++) {
/* 124 */             IBlockState s = worldIn.func_180495_p(pos.func_177982_a(a, b, c));
/* 125 */             if (s.func_177230_c() == BlocksTC.golemBuilder) {
/* 126 */               BlockGolemBuilder.destroy(worldIn, pos.func_177982_a(a, b, c), s, pos);
/*     */               break label246;
/*     */             }
/*     */           } }
/*     */     label246:
/* 131 */     super.func_180663_b(worldIn, pos, state);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public IBlockState func_176203_a(int meta)
/*     */   {
/* 138 */     return func_176223_P().func_177226_a(VARIANT, PlaceholderType.values()[meta]);
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_176201_c(IBlockState state)
/*     */   {
/* 144 */     int meta = ((PlaceholderType)state.func_177229_b(VARIANT)).ordinal();
/*     */     
/* 146 */     return meta;
/*     */   }
/*     */   
/*     */ 
/*     */   protected BlockState func_180661_e()
/*     */   {
/* 152 */     return new BlockState(this, new IProperty[] { VARIANT });
/*     */   }
/*     */   
/*     */ 
/*     */   public IProperty[] getProperties()
/*     */   {
/* 158 */     return new IProperty[] { VARIANT };
/*     */   }
/*     */   
/*     */ 
/*     */   public String getStateName(IBlockState state, boolean fullName)
/*     */   {
/* 164 */     PlaceholderType type = (PlaceholderType)state.func_177229_b(VARIANT);
/*     */     
/* 166 */     return type.func_176610_l() + (fullName ? "_placeholder" : "");
/*     */   }
/*     */   
/*     */ 
/*     */   public static enum PlaceholderType
/*     */     implements IStringSerializable
/*     */   {
/* 173 */     FURNACE_BRICK,  FURNACE_OBSIDIAN,  GB_BARS,  GB_ANVIL,  GB_CAULDRON,  GB_TABLE;
/*     */     
/*     */     private PlaceholderType() {}
/*     */     
/*     */     public String func_176610_l() {
/* 178 */       return name().toLowerCase();
/*     */     }
/*     */     
/*     */ 
/*     */     public String toString()
/*     */     {
/* 184 */       return func_176610_l();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/misc/BlockPlaceholder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */