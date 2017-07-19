/*     */ package thaumcraft.common.blocks.devices;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyEnum;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.IStringSerializable;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.IEssentiaContainerItem;
/*     */ import thaumcraft.api.aura.AuraHelper;
/*     */ import thaumcraft.api.blocks.ILabelable;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.common.blocks.BlockTCDevice;
/*     */ import thaumcraft.common.tiles.crafting.TileAlembic;
/*     */ 
/*     */ public class BlockAlembic extends BlockTCDevice implements ILabelable
/*     */ {
/*  31 */   public static final PropertyEnum TYPE = PropertyEnum.func_177709_a("type", AlembicType.class);
/*     */   
/*     */   public BlockAlembic()
/*     */   {
/*  35 */     super(Material.field_151575_d, null);
/*  36 */     func_149672_a(Block.field_149766_f);
/*  37 */     func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(TYPE, AlembicType.NORMAL));
/*     */   }
/*     */   
/*     */   public TileEntity func_149915_a(World world, int metadata)
/*     */   {
/*  42 */     if (metadata == 0) return new TileAlembic();
/*  43 */     return null;
/*     */   }
/*     */   
/*     */   public boolean func_149662_c()
/*     */   {
/*  48 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149686_d()
/*     */   {
/*  54 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public IBlockState func_180642_a(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
/*     */   {
/*  60 */     return func_176203_a(meta);
/*     */   }
/*     */   
/*     */ 
/*     */   public IBlockState func_176203_a(int meta)
/*     */   {
/*  66 */     return func_176223_P().func_177226_a(TYPE, AlembicType.values()[meta]);
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_176201_c(IBlockState state)
/*     */   {
/*  72 */     return ((AlembicType)state.func_177229_b(TYPE)).ordinal();
/*     */   }
/*     */   
/*     */ 
/*     */   protected BlockState func_180661_e()
/*     */   {
/*  78 */     return new BlockState(this, new IProperty[] { TYPE });
/*     */   }
/*     */   
/*     */ 
/*     */   public String getStateName(IBlockState state, boolean fullName)
/*     */   {
/*  84 */     AlembicType type = (AlembicType)state.func_177229_b(TYPE);
/*  85 */     return "alembic_" + type.func_176610_l();
/*     */   }
/*     */   
/*     */ 
/*     */   public IProperty[] getProperties()
/*     */   {
/*  91 */     return new IProperty[] { TYPE };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_180639_a(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float fx, float fy, float fz)
/*     */   {
/*  98 */     TileEntity te = world.func_175625_s(pos);
/*     */     
/* 100 */     if ((te != null) && ((te instanceof TileAlembic)) && (player.func_70093_af()) && (((TileAlembic)te).aspectFilter != null) && (side.ordinal() == ((TileAlembic)te).facing))
/*     */     {
/* 102 */       ((TileAlembic)te).aspectFilter = null;
/* 103 */       ((TileAlembic)te).facing = EnumFacing.DOWN.ordinal();
/* 104 */       te.func_70296_d();
/* 105 */       world.func_175689_h(pos);
/* 106 */       if (world.field_72995_K) {
/* 107 */         world.func_72980_b(pos.func_177958_n() + 0.5D, pos.func_177956_o() + 0.5D, pos.func_177952_p() + 0.5D, "thaumcraft:page", 1.0F, 1.0F, false);
/*     */       } else {
/* 109 */         world.func_72838_d(new EntityItem(world, pos.func_177958_n() + 0.5F + side.func_82601_c() / 3.0F, pos.func_177956_o() + 0.5F, pos.func_177952_p() + 0.5F + side.func_82599_e() / 3.0F, new ItemStack(ItemsTC.label)));
/*     */ 
/*     */       }
/*     */       
/*     */ 
/*     */     }
/* 115 */     else if ((te != null) && ((te instanceof TileAlembic)) && (player.func_70093_af()) && (player.func_70694_bm() == null) && ((((TileAlembic)te).aspectFilter == null) || (side.ordinal() != ((TileAlembic)te).facing)))
/*     */     {
/* 117 */       ((TileAlembic)te).aspect = null;
/* 118 */       if (world.field_72995_K) {
/* 119 */         world.func_72980_b(pos.func_177958_n() + 0.5D, pos.func_177956_o() + 0.5D, pos.func_177952_p() + 0.5D, "thaumcraft:jar", 0.4F, 1.0F, false);
/* 120 */         world.func_72980_b(pos.func_177958_n() + 0.5D, pos.func_177956_o() + 0.5D, pos.func_177952_p() + 0.5D, "game.neutral.swim", 0.5F, 1.0F + (world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.3F, false);
/*     */       } else {
/* 122 */         AuraHelper.pollute(world, pos, ((TileAlembic)te).amount, true);
/*     */       }
/* 124 */       ((TileAlembic)te).amount = 0;
/* 125 */       te.func_70296_d();
/* 126 */       world.func_175689_h(pos);
/*     */     }
/*     */     
/* 129 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_180654_a(IBlockAccess world, BlockPos pos)
/*     */   {
/* 135 */     func_149676_a(0.125F, 0.0F, 0.125F, 0.875F, 1.0F, 0.875F);
/*     */   }
/*     */   
/*     */   public boolean func_149740_M()
/*     */   {
/* 140 */     return true;
/*     */   }
/*     */   
/*     */   public int func_180641_l(World world, BlockPos pos)
/*     */   {
/* 145 */     TileEntity tile = world.func_175625_s(pos);
/* 146 */     if ((tile != null) && ((tile instanceof TileAlembic))) {
/* 147 */       float r = ((TileAlembic)tile).amount / ((TileAlembic)tile).maxAmount;
/* 148 */       return MathHelper.func_76141_d(r * 14.0F) + (((TileAlembic)tile).amount > 0 ? 1 : 0);
/*     */     }
/* 150 */     return super.func_180641_l(world, pos);
/*     */   }
/*     */   
/*     */   public static enum AlembicType implements IStringSerializable
/*     */   {
/* 155 */     NORMAL;
/*     */     
/*     */     private AlembicType() {}
/*     */     
/*     */     public String func_176610_l() {
/* 160 */       return name().toLowerCase();
/*     */     }
/*     */     
/*     */ 
/*     */     public String toString()
/*     */     {
/* 166 */       return func_176610_l();
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean applyLabel(EntityPlayer player, BlockPos pos, EnumFacing side, ItemStack labelstack)
/*     */   {
/* 172 */     TileEntity te = player.field_70170_p.func_175625_s(pos);
/* 173 */     if ((te != null) && ((te instanceof TileAlembic)) && (side.ordinal() > 1) && (((TileAlembic)te).aspectFilter == null)) {
/* 174 */       Aspect la = null;
/* 175 */       if (((IEssentiaContainerItem)labelstack.func_77973_b()).getAspects(labelstack) != null) {
/* 176 */         la = ((IEssentiaContainerItem)(IEssentiaContainerItem)labelstack.func_77973_b()).getAspects(labelstack).getAspects()[0];
/*     */       }
/* 178 */       if ((((TileAlembic)te).amount == 0) && (la == null)) {
/* 179 */         return false;
/*     */       }
/* 181 */       Aspect aspect = null;
/* 182 */       if ((((TileAlembic)te).amount == 0) && (la != null)) {
/* 183 */         aspect = la;
/*     */       }
/* 185 */       if (((TileAlembic)te).amount > 0) {
/* 186 */         aspect = ((TileAlembic)te).aspect;
/*     */       }
/*     */       
/* 189 */       if (aspect == null) { return false;
/*     */       }
/* 191 */       func_180633_a(player.field_70170_p, pos, player.field_70170_p.func_180495_p(pos), player, null);
/* 192 */       ((TileAlembic)te).aspectFilter = aspect;
/* 193 */       ((TileAlembic)te).facing = side.ordinal();
/* 194 */       te.func_70296_d();
/* 195 */       player.field_70170_p.func_175689_h(pos);
/* 196 */       player.field_70170_p.func_72908_a(pos.func_177958_n() + 0.5D, pos.func_177956_o() + 0.5D, pos.func_177952_p() + 0.5D, "thaumcraft:page", 1.0F, 1.0F);
/* 197 */       return true;
/*     */     }
/* 199 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/devices/BlockAlembic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */