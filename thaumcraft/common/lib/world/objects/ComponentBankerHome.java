/*     */ package thaumcraft.common.lib.world.objects;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.gen.structure.StructureBoundingBox;
/*     */ import net.minecraft.world.gen.structure.StructureComponent;
/*     */ import net.minecraft.world.gen.structure.StructureVillagePieces.Start;
/*     */ import net.minecraft.world.gen.structure.StructureVillagePieces.Village;
/*     */ import thaumcraft.common.config.ConfigEntities;
/*     */ 
/*     */ public class ComponentBankerHome extends StructureVillagePieces.Village
/*     */ {
/*     */   private boolean isTallHouse;
/*     */   private int tablePosition;
/*     */   
/*     */   public ComponentBankerHome() {}
/*     */   
/*     */   public ComponentBankerHome(StructureVillagePieces.Start p_i2101_1_, int p_i2101_2_, Random p_i2101_3_, StructureBoundingBox p_i2101_4_, EnumFacing p_i2101_5_)
/*     */   {
/*  27 */     super(p_i2101_1_, p_i2101_2_);
/*  28 */     this.field_74885_f = p_i2101_5_;
/*  29 */     this.field_74887_e = p_i2101_4_;
/*  30 */     this.isTallHouse = p_i2101_3_.nextBoolean();
/*  31 */     this.tablePosition = p_i2101_3_.nextInt(3);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_143012_a(NBTTagCompound p_143012_1_)
/*     */   {
/*  37 */     super.func_143012_a(p_143012_1_);
/*  38 */     p_143012_1_.func_74768_a("T", this.tablePosition);
/*  39 */     p_143012_1_.func_74757_a("C", this.isTallHouse);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_143011_b(NBTTagCompound p_143011_1_)
/*     */   {
/*  45 */     super.func_143011_b(p_143011_1_);
/*  46 */     this.tablePosition = p_143011_1_.func_74762_e("T");
/*  47 */     this.isTallHouse = p_143011_1_.func_74767_n("C");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static StructureVillagePieces.Village buildComponent(StructureVillagePieces.Start p_74908_0_, List p_74908_1_, Random p_74908_2_, int p_74908_3_, int p_74908_4_, int p_74908_5_, EnumFacing p_74908_6_, int p_74908_7_)
/*     */   {
/*  54 */     StructureBoundingBox structureboundingbox = StructureBoundingBox.func_175897_a(p_74908_3_, p_74908_4_, p_74908_5_, 0, 0, 0, 4, 6, 5, p_74908_6_);
/*  55 */     return (func_74895_a(structureboundingbox)) && (StructureComponent.func_74883_a(p_74908_1_, structureboundingbox) == null) ? new ComponentBankerHome(p_74908_0_, p_74908_7_, p_74908_2_, structureboundingbox, p_74908_6_) : null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_)
/*     */   {
/*  65 */     if (this.field_143015_k < 0)
/*     */     {
/*  67 */       this.field_143015_k = func_74889_b(p_74875_1_, p_74875_3_);
/*     */       
/*  69 */       if (this.field_143015_k < 0)
/*     */       {
/*  71 */         return true;
/*     */       }
/*     */       
/*  74 */       this.field_74887_e.func_78886_a(0, this.field_143015_k - this.field_74887_e.field_78894_e + 6 - 1, 0);
/*     */     }
/*     */     
/*  77 */     func_175804_a(p_74875_1_, p_74875_3_, 1, 1, 1, 3, 5, 4, Blocks.field_150350_a.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);
/*  78 */     func_175804_a(p_74875_1_, p_74875_3_, 0, 0, 0, 3, 0, 4, Blocks.field_150347_e.func_176223_P(), Blocks.field_150347_e.func_176223_P(), false);
/*  79 */     func_175804_a(p_74875_1_, p_74875_3_, 1, 0, 1, 2, 0, 3, Blocks.field_150346_d.func_176223_P(), Blocks.field_150346_d.func_176223_P(), false);
/*     */     
/*  81 */     if (this.isTallHouse)
/*     */     {
/*  83 */       func_175804_a(p_74875_1_, p_74875_3_, 1, 4, 1, 2, 4, 3, Blocks.field_150364_r.func_176223_P(), Blocks.field_150364_r.func_176223_P(), false);
/*     */     }
/*     */     else
/*     */     {
/*  87 */       func_175804_a(p_74875_1_, p_74875_3_, 1, 5, 1, 2, 5, 3, Blocks.field_150364_r.func_176223_P(), Blocks.field_150364_r.func_176223_P(), false);
/*     */     }
/*     */     
/*  90 */     func_175811_a(p_74875_1_, Blocks.field_150364_r.func_176223_P(), 1, 4, 0, p_74875_3_);
/*  91 */     func_175811_a(p_74875_1_, Blocks.field_150364_r.func_176223_P(), 2, 4, 0, p_74875_3_);
/*  92 */     func_175811_a(p_74875_1_, Blocks.field_150364_r.func_176223_P(), 1, 4, 4, p_74875_3_);
/*  93 */     func_175811_a(p_74875_1_, Blocks.field_150364_r.func_176223_P(), 2, 4, 4, p_74875_3_);
/*  94 */     func_175811_a(p_74875_1_, Blocks.field_150364_r.func_176223_P(), 0, 4, 1, p_74875_3_);
/*  95 */     func_175811_a(p_74875_1_, Blocks.field_150364_r.func_176223_P(), 0, 4, 2, p_74875_3_);
/*  96 */     func_175811_a(p_74875_1_, Blocks.field_150364_r.func_176223_P(), 0, 4, 3, p_74875_3_);
/*  97 */     func_175811_a(p_74875_1_, Blocks.field_150364_r.func_176223_P(), 3, 4, 1, p_74875_3_);
/*  98 */     func_175811_a(p_74875_1_, Blocks.field_150364_r.func_176223_P(), 3, 4, 2, p_74875_3_);
/*  99 */     func_175811_a(p_74875_1_, Blocks.field_150364_r.func_176223_P(), 3, 4, 3, p_74875_3_);
/* 100 */     func_175804_a(p_74875_1_, p_74875_3_, 0, 1, 0, 0, 3, 0, Blocks.field_150364_r.func_176223_P(), Blocks.field_150364_r.func_176223_P(), false);
/* 101 */     func_175804_a(p_74875_1_, p_74875_3_, 3, 1, 0, 3, 3, 0, Blocks.field_150364_r.func_176223_P(), Blocks.field_150364_r.func_176223_P(), false);
/* 102 */     func_175804_a(p_74875_1_, p_74875_3_, 0, 1, 4, 0, 3, 4, Blocks.field_150364_r.func_176223_P(), Blocks.field_150364_r.func_176223_P(), false);
/* 103 */     func_175804_a(p_74875_1_, p_74875_3_, 3, 1, 4, 3, 3, 4, Blocks.field_150364_r.func_176223_P(), Blocks.field_150364_r.func_176223_P(), false);
/* 104 */     func_175804_a(p_74875_1_, p_74875_3_, 0, 1, 1, 0, 3, 3, Blocks.field_150344_f.func_176223_P(), Blocks.field_150344_f.func_176223_P(), false);
/* 105 */     func_175804_a(p_74875_1_, p_74875_3_, 3, 1, 1, 3, 3, 3, Blocks.field_150344_f.func_176223_P(), Blocks.field_150344_f.func_176223_P(), false);
/* 106 */     func_175804_a(p_74875_1_, p_74875_3_, 1, 1, 0, 2, 3, 0, Blocks.field_150344_f.func_176223_P(), Blocks.field_150344_f.func_176223_P(), false);
/* 107 */     func_175804_a(p_74875_1_, p_74875_3_, 1, 1, 4, 2, 3, 4, Blocks.field_150344_f.func_176223_P(), Blocks.field_150344_f.func_176223_P(), false);
/* 108 */     func_175811_a(p_74875_1_, Blocks.field_150411_aY.func_176223_P(), 0, 2, 2, p_74875_3_);
/* 109 */     func_175811_a(p_74875_1_, Blocks.field_150411_aY.func_176223_P(), 3, 2, 2, p_74875_3_);
/*     */     
/* 111 */     if (this.tablePosition > 0)
/*     */     {
/* 113 */       func_175811_a(p_74875_1_, Blocks.field_150463_bK.func_176223_P(), this.tablePosition, 1, 3, p_74875_3_);
/* 114 */       func_175811_a(p_74875_1_, Blocks.field_150456_au.func_176223_P(), this.tablePosition, 2, 3, p_74875_3_);
/*     */     }
/*     */     
/* 117 */     func_175811_a(p_74875_1_, Blocks.field_150350_a.func_176223_P(), 1, 1, 0, p_74875_3_);
/* 118 */     func_175811_a(p_74875_1_, Blocks.field_150350_a.func_176223_P(), 1, 2, 0, p_74875_3_);
/* 119 */     func_175810_a(p_74875_1_, p_74875_3_, p_74875_2_, 1, 1, 0, EnumFacing.func_176731_b(func_151555_a(Blocks.field_180413_ao, 1)));
/*     */     
/*     */ 
/* 122 */     if ((func_175807_a(p_74875_1_, 1, 0, -1, p_74875_3_).func_177230_c().func_149688_o() == Material.field_151579_a) && (func_175807_a(p_74875_1_, 1, -1, -1, p_74875_3_).func_177230_c().func_149688_o() != Material.field_151579_a))
/*     */     {
/*     */ 
/* 125 */       func_175811_a(p_74875_1_, Blocks.field_150446_ar.func_176203_a(func_151555_a(Blocks.field_150446_ar, 3)), 1, 0, -1, p_74875_3_);
/*     */     }
/*     */     
/* 128 */     for (int i = 0; i < 5; i++)
/*     */     {
/* 130 */       for (int j = 0; j < 4; j++)
/*     */       {
/* 132 */         func_74871_b(p_74875_1_, j, 6, i, p_74875_3_);
/* 133 */         func_175808_b(p_74875_1_, Blocks.field_150347_e.func_176223_P(), j, -1, i, p_74875_3_);
/*     */       }
/*     */     }
/*     */     
/* 137 */     func_74893_a(p_74875_1_, p_74875_3_, 1, 1, 2, 1);
/* 138 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected int func_180779_c(int p_180779_1_, int p_180779_2_)
/*     */   {
/* 145 */     return ConfigEntities.entBankerId;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/world/objects/ComponentBankerHome.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */