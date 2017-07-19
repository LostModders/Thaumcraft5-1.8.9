/*     */ package thaumcraft.common.blocks.basic;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyEnum;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.IStringSerializable;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.blocks.BlockTC;
/*     */ 
/*     */ public class BlockPavingStone extends BlockTC
/*     */ {
/*  31 */   public static final PropertyEnum VARIANT = PropertyEnum.func_177709_a("variant", PavingStoneType.class);
/*     */   
/*     */   public BlockPavingStone()
/*     */   {
/*  35 */     super(Material.field_151576_e);
/*  36 */     func_149647_a(Thaumcraft.tabTC);
/*  37 */     func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(VARIANT, PavingStoneType.BARRIER));
/*  38 */     func_149711_c(2.5F);
/*  39 */     func_149672_a(Block.field_149769_e);
/*  40 */     func_149675_a(true);
/*  41 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.9375F, 1.0F);
/*     */   }
/*     */   
/*     */   public boolean canHarvestBlock(IBlockAccess world, BlockPos pos, EntityPlayer player)
/*     */   {
/*  46 */     return true;
/*     */   }
/*     */   
/*     */   public boolean hasTileEntity(IBlockState state)
/*     */   {
/*  51 */     return state.func_177230_c().func_176201_c(state) == 0;
/*     */   }
/*     */   
/*     */   public TileEntity createTileEntity(World world, IBlockState state)
/*     */   {
/*  56 */     return state.func_177230_c().func_176201_c(state) == 0 ? new thaumcraft.common.tiles.misc.TileBarrierStone() : null;
/*     */   }
/*     */   
/*     */ 
/*     */   public AxisAlignedBB func_180640_a(World worldIn, BlockPos pos, IBlockState state)
/*     */   {
/*  62 */     return new AxisAlignedBB(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), pos.func_177958_n() + 1, pos.func_177956_o() + 1, pos.func_177952_p() + 1);
/*     */   }
/*     */   
/*     */   public void func_176199_a(World worldIn, BlockPos pos, Entity e)
/*     */   {
/*  67 */     IBlockState state = worldIn.func_180495_p(pos);
/*  68 */     if ((func_176201_c(state) == 1) && ((e instanceof EntityLivingBase))) {
/*  69 */       if ((worldIn.field_72995_K) && (worldIn.field_73012_v.nextBoolean())) {
/*  70 */         Thaumcraft.proxy.getFX().drawWispyMotesOnBlock(pos.func_177984_a(), 30);
/*     */       } else {
/*  72 */         ((EntityLivingBase)e).func_70690_d(new PotionEffect(Potion.field_76424_c.field_76415_H, 40, 1, true, false));
/*  73 */         ((EntityLivingBase)e).func_70690_d(new PotionEffect(Potion.field_76430_j.field_76415_H, 40, 0, true, false));
/*     */       }
/*     */     }
/*  76 */     super.func_176199_a(worldIn, pos, e);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149662_c()
/*     */   {
/*  82 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149686_d()
/*     */   {
/*  88 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_180655_c(World world, BlockPos pos, IBlockState state, Random random)
/*     */   {
/*  94 */     if (state.func_177230_c().func_176201_c(state) == 0) {
/*  95 */       if (world.func_175687_A(pos) > 0) {
/*  96 */         for (int a = 0; a < Thaumcraft.proxy.getFX().particleCount(2); a++) {
/*  97 */           Thaumcraft.proxy.getFX().blockRunes(pos.func_177958_n(), pos.func_177956_o() + 0.7F, pos.func_177952_p(), 0.2F + random.nextFloat() * 0.4F, random.nextFloat() * 0.3F, 0.8F + random.nextFloat() * 0.2F, 20, -0.02F);
/*     */         }
/*     */       }
/* 100 */       else if (((world.func_180495_p(pos.func_177981_b(1)) == BlocksTC.barrier.func_176223_P()) && (world.func_180495_p(pos.func_177981_b(1)).func_177230_c().func_176205_b(world, pos.func_177981_b(1)))) || ((world.func_180495_p(pos.func_177981_b(2)) == BlocksTC.barrier.func_176223_P()) && (world.func_180495_p(pos.func_177981_b(2)).func_177230_c().func_176205_b(world, pos.func_177981_b(2)))))
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/* 105 */         for (int a = 0; a < Thaumcraft.proxy.getFX().particleCount(3); a++) {
/* 106 */           Thaumcraft.proxy.getFX().blockRunes(pos.func_177958_n(), pos.func_177956_o() + 0.7F, pos.func_177952_p(), 0.9F + random.nextFloat() * 0.1F, random.nextFloat() * 0.3F, random.nextFloat() * 0.3F, 24, -0.02F);
/*     */         }
/*     */       }
/*     */       else {
/* 110 */         List list = world.func_72839_b((Entity)null, AxisAlignedBB.func_178781_a(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), pos.func_177958_n() + 1, pos.func_177956_o() + 1, pos.func_177952_p() + 1).func_72314_b(1.0D, 1.0D, 1.0D));
/*     */         
/*     */ 
/* 113 */         if (!list.isEmpty())
/*     */         {
/* 115 */           Iterator iterator = list.iterator();
/* 116 */           while (iterator.hasNext())
/*     */           {
/* 118 */             Entity entity = (Entity)iterator.next();
/* 119 */             if (((entity instanceof EntityLivingBase)) && (!(entity instanceof EntityPlayer)))
/*     */             {
/* 121 */               Thaumcraft.proxy.getFX().blockRunes(pos.func_177958_n(), pos.func_177956_o() + 0.6F + random.nextFloat() * Math.max(0.8F, entity.func_70047_e()), pos.func_177952_p(), 0.6F + random.nextFloat() * 0.4F, 0.0F, 0.3F + random.nextFloat() * 0.7F, 20, 0.0F);
/*     */               
/* 123 */               break;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public IBlockState func_176203_a(int meta)
/*     */   {
/* 135 */     return meta < PavingStoneType.values().length ? func_176223_P().func_177226_a(VARIANT, PavingStoneType.values()[meta]) : func_176223_P();
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_176201_c(IBlockState state)
/*     */   {
/* 141 */     int meta = ((PavingStoneType)state.func_177229_b(VARIANT)).ordinal();
/*     */     
/* 143 */     return meta;
/*     */   }
/*     */   
/*     */ 
/*     */   protected BlockState func_180661_e()
/*     */   {
/* 149 */     return new BlockState(this, new IProperty[] { VARIANT });
/*     */   }
/*     */   
/*     */ 
/*     */   public IProperty[] getProperties()
/*     */   {
/* 155 */     return new IProperty[] { VARIANT };
/*     */   }
/*     */   
/*     */ 
/*     */   public String getStateName(IBlockState state, boolean fullName)
/*     */   {
/* 161 */     PavingStoneType type = (PavingStoneType)state.func_177229_b(VARIANT);
/*     */     
/* 163 */     return type.func_176610_l() + (fullName ? "_stone" : "");
/*     */   }
/*     */   
/*     */   public static enum PavingStoneType
/*     */     implements IStringSerializable
/*     */   {
/* 169 */     BARRIER, 
/* 170 */     TRAVEL;
/*     */     
/*     */     private PavingStoneType() {}
/*     */     
/*     */     public String func_176610_l()
/*     */     {
/* 176 */       return name().toLowerCase();
/*     */     }
/*     */     
/*     */ 
/*     */     public String toString()
/*     */     {
/* 182 */       return func_176610_l();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/basic/BlockPavingStone.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */