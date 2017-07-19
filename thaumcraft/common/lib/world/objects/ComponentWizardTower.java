/*     */ package thaumcraft.common.lib.world.objects;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.WeightedRandomChestContent;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.gen.structure.StructureBoundingBox;
/*     */ import net.minecraft.world.gen.structure.StructureComponent;
/*     */ import net.minecraft.world.gen.structure.StructureVillagePieces.Start;
/*     */ import net.minecraft.world.gen.structure.StructureVillagePieces.Village;
/*     */ import net.minecraftforge.common.ChestGenHooks;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.common.config.ConfigEntities;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ComponentWizardTower
/*     */   extends StructureVillagePieces.Village
/*     */ {
/*     */   public ComponentWizardTower(StructureVillagePieces.Start par1ComponentVillageStartPiece, int par2, Random par3Random, StructureBoundingBox par4StructureBoundingBox, EnumFacing par5)
/*     */   {
/*  31 */     super(par1ComponentVillageStartPiece, par2);
/*  32 */     this.field_74885_f = par5;
/*  33 */     this.field_74887_e = par4StructureBoundingBox;
/*     */   }
/*     */   
/*  36 */   private int averageGroundLevel = -1;
/*     */   
/*  38 */   public static final List towerChestContents = Lists.newArrayList(new WeightedRandomChestContent[] { new WeightedRandomChestContent(Items.field_151114_aO, 0, 1, 3, 3), new WeightedRandomChestContent(Items.field_151069_bo, 0, 1, 5, 10), new WeightedRandomChestContent(Items.field_151074_bl, 0, 1, 3, 5), new WeightedRandomChestContent(Items.field_151059_bz, 0, 1, 1, 5), new WeightedRandomChestContent(Items.field_151144_bL, 0, 1, 1, 3), new WeightedRandomChestContent(ItemsTC.knowledgeFragment, 0, 1, 3, 1), new WeightedRandomChestContent(ItemsTC.alumentum, 0, 1, 1, 5), new WeightedRandomChestContent(Item.func_150898_a(BlocksTC.nitor), 1, 1, 1, 5), new WeightedRandomChestContent(ItemsTC.thaumonomicon, 0, 1, 1, 20) });
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
/*     */   static
/*     */   {
/*  51 */     ChestGenHooks.init("towerChestContents", towerChestContents, 4, 9);
/*     */   }
/*     */   
/*     */   public static StructureVillagePieces.Village buildComponent(StructureVillagePieces.Start startPiece, List pieces, Random random, int par3, int par4, int par5, EnumFacing par6, int par7)
/*     */   {
/*  56 */     StructureBoundingBox var8 = StructureBoundingBox.func_175897_a(par3, par4, par5, 0, 0, 0, 5, 12, 5, par6);
/*  57 */     return (!func_74895_a(var8)) || (StructureComponent.func_74883_a(pieces, var8) != null) ? null : new ComponentWizardTower(startPiece, par7, random, var8, par6);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_74875_a(World world, Random par2Random, StructureBoundingBox bb)
/*     */   {
/*  68 */     if (this.averageGroundLevel < 0)
/*     */     {
/*  70 */       this.averageGroundLevel = func_74889_b(world, bb);
/*     */       
/*  72 */       if (this.averageGroundLevel < 0)
/*     */       {
/*  74 */         return true;
/*     */       }
/*     */       
/*  77 */       this.field_74887_e.func_78886_a(0, this.averageGroundLevel - this.field_74887_e.field_78894_e + 12 - 1, 0);
/*     */     }
/*     */     
/*  80 */     func_175804_a(world, bb, 2, 1, 2, 4, 11, 4, Blocks.field_150350_a.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);
/*  81 */     func_175804_a(world, bb, 2, 0, 2, 4, 0, 4, Blocks.field_150344_f.func_176223_P(), Blocks.field_150344_f.func_176223_P(), false);
/*  82 */     func_175804_a(world, bb, 2, 5, 2, 4, 5, 4, Blocks.field_150344_f.func_176223_P(), Blocks.field_150344_f.func_176223_P(), false);
/*  83 */     func_175804_a(world, bb, 2, 10, 2, 4, 10, 4, Blocks.field_150344_f.func_176223_P(), Blocks.field_150344_f.func_176223_P(), false);
/*  84 */     func_175804_a(world, bb, 1, 0, 2, 1, 11, 4, Blocks.field_150347_e.func_176223_P(), Blocks.field_150347_e.func_176223_P(), false);
/*  85 */     func_175804_a(world, bb, 2, 0, 1, 4, 11, 1, Blocks.field_150347_e.func_176223_P(), Blocks.field_150347_e.func_176223_P(), false);
/*  86 */     func_175804_a(world, bb, 5, 0, 2, 5, 11, 4, Blocks.field_150347_e.func_176223_P(), Blocks.field_150347_e.func_176223_P(), false);
/*  87 */     func_175804_a(world, bb, 2, 0, 5, 4, 11, 5, Blocks.field_150347_e.func_176223_P(), Blocks.field_150347_e.func_176223_P(), false);
/*  88 */     func_175804_a(world, bb, 2, 0, 5, 4, 11, 5, Blocks.field_150347_e.func_176223_P(), Blocks.field_150347_e.func_176223_P(), false);
/*  89 */     func_175811_a(world, Blocks.field_150347_e.func_176203_a(3), 1, 0, 1, bb);
/*  90 */     func_175811_a(world, Blocks.field_150347_e.func_176203_a(3), 1, 0, 5, bb);
/*  91 */     func_175811_a(world, Blocks.field_150347_e.func_176203_a(3), 5, 0, 1, bb);
/*  92 */     func_175811_a(world, Blocks.field_150347_e.func_176203_a(3), 5, 0, 5, bb);
/*     */     
/*  94 */     func_175811_a(world, Blocks.field_150347_e.func_176203_a(3), 1, 5, 1, bb);
/*  95 */     func_175811_a(world, Blocks.field_150347_e.func_176203_a(3), 1, 5, 5, bb);
/*  96 */     func_175811_a(world, Blocks.field_150347_e.func_176203_a(3), 5, 5, 1, bb);
/*  97 */     func_175811_a(world, Blocks.field_150347_e.func_176203_a(3), 5, 5, 5, bb);
/*     */     
/*  99 */     func_175811_a(world, Blocks.field_150347_e.func_176203_a(3), 1, 10, 1, bb);
/* 100 */     func_175811_a(world, Blocks.field_150347_e.func_176203_a(3), 1, 10, 5, bb);
/* 101 */     func_175811_a(world, Blocks.field_150347_e.func_176203_a(3), 5, 10, 1, bb);
/* 102 */     func_175811_a(world, Blocks.field_150347_e.func_176203_a(3), 5, 10, 5, bb);
/*     */     
/* 104 */     func_175811_a(world, Blocks.field_150410_aZ.func_176223_P(), 3, 7, 1, bb);
/* 105 */     func_175811_a(world, Blocks.field_150410_aZ.func_176223_P(), 3, 8, 1, bb);
/* 106 */     func_175811_a(world, Blocks.field_150410_aZ.func_176223_P(), 3, 7, 5, bb);
/* 107 */     func_175811_a(world, Blocks.field_150410_aZ.func_176223_P(), 3, 8, 5, bb);
/* 108 */     func_175811_a(world, Blocks.field_150410_aZ.func_176223_P(), 3, 2, 5, bb);
/* 109 */     func_175811_a(world, Blocks.field_150410_aZ.func_176223_P(), 3, 3, 5, bb);
/*     */     
/* 111 */     int var4 = func_151555_a(Blocks.field_150468_ap, 4);
/* 112 */     for (int var5 = 1; var5 <= 9; var5++)
/*     */     {
/* 114 */       func_175811_a(world, Blocks.field_150468_ap.func_176203_a(var4), 4, var5, 3, bb);
/*     */     }
/* 116 */     func_175811_a(world, Blocks.field_150415_aT.func_176223_P(), 4, 10, 3, bb);
/* 117 */     func_175811_a(world, Blocks.field_150426_aN.func_176223_P(), 3, 5, 3, bb);
/*     */     
/* 119 */     func_180778_a(world, bb, par2Random, 2, 6, 2, ChestGenHooks.getItems("towerChestContents", par2Random), ChestGenHooks.getCount("towerChestContents", par2Random));
/*     */     
/*     */ 
/*     */ 
/* 123 */     func_175811_a(world, Blocks.field_150350_a.func_176223_P(), 3, 1, 1, bb);
/* 124 */     func_175811_a(world, Blocks.field_150350_a.func_176223_P(), 3, 2, 1, bb);
/* 125 */     func_175810_a(world, bb, par2Random, 3, 1, 1, EnumFacing.func_176731_b(func_151555_a(Blocks.field_180413_ao, 1)));
/*     */     
/*     */ 
/* 128 */     if ((func_175807_a(world, 3, 0, 0, bb).func_177230_c().func_149688_o() == Material.field_151579_a) && (func_175807_a(world, 3, -1, 0, bb).func_177230_c().func_149688_o() != Material.field_151579_a))
/*     */     {
/*     */ 
/* 131 */       func_175811_a(world, Blocks.field_150446_ar.func_176203_a(func_151555_a(Blocks.field_150446_ar, 3)), 3, 0, 0, bb);
/*     */     }
/*     */     
/*     */ 
/* 135 */     for (int var5 = 0; var5 < 12; var5++)
/*     */     {
/* 137 */       for (int var6 = 0; var6 < 5; var6++)
/*     */       {
/* 139 */         func_74871_b(world, var6, 12, var5, bb);
/* 140 */         func_175808_b(world, Blocks.field_150347_e.func_176223_P(), var6, -1, var5, bb);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 145 */     func_74893_a(world, bb, 7, 1, 1, 1);
/* 146 */     return true;
/*     */   }
/*     */   
/*     */   protected int func_180779_c(int p_180779_1_, int p_180779_2_)
/*     */   {
/* 151 */     return ConfigEntities.entWizardId;
/*     */   }
/*     */   
/*     */   public ComponentWizardTower() {}
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/world/objects/ComponentWizardTower.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */