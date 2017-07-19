/*     */ package thaumcraft.common.blocks.devices;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyEnum;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.item.EntityXPOrb;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagInt;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumWorldBlockLayer;
/*     */ import net.minecraft.util.IStringSerializable;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.aspects.IEssentiaContainerItem;
/*     */ import thaumcraft.api.aura.AuraHelper;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.blocks.ILabelable;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.blocks.BlockTCDevice;
/*     */ import thaumcraft.common.lib.CustomSoundType;
/*     */ import thaumcraft.common.tiles.devices.TileJarBrain;
/*     */ import thaumcraft.common.tiles.essentia.TileJarFillable;
/*     */ import thaumcraft.common.tiles.essentia.TileJarFillableVoid;
/*     */ 
/*     */ public class BlockJar extends BlockTCDevice implements ILabelable
/*     */ {
/*  43 */   public static final PropertyEnum TYPE = PropertyEnum.func_177709_a("type", JarType.class);
/*     */   
/*     */   public BlockJar()
/*     */   {
/*  47 */     super(Material.field_151592_s, null);
/*  48 */     func_149711_c(0.3F);
/*  49 */     func_149672_a(new CustomSoundType("jar", 1.0F, 1.0F));
/*  50 */     func_149647_a(Thaumcraft.tabTC);
/*  51 */     func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(TYPE, JarType.NORMAL));
/*  52 */     func_149676_a(0.1875F, 0.0F, 0.1875F, 0.8125F, 0.75F, 0.8125F);
/*     */   }
/*     */   
/*     */   public boolean defineVariantsForItemBlock()
/*     */   {
/*  57 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public EnumWorldBlockLayer func_180664_k()
/*     */   {
/*  64 */     return EnumWorldBlockLayer.TRANSLUCENT;
/*     */   }
/*     */   
/*     */   public TileEntity func_149915_a(World world, int metadata)
/*     */   {
/*  69 */     if (metadata == 0) return new TileJarFillable();
/*  70 */     if (metadata == 1) return new TileJarFillableVoid();
/*  71 */     if (metadata == 2) return new TileJarBrain();
/*  72 */     return null;
/*     */   }
/*     */   
/*     */   public boolean func_149662_c()
/*     */   {
/*  77 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149686_d()
/*     */   {
/*  83 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public IBlockState func_180642_a(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
/*     */   {
/*  89 */     return func_176203_a(meta);
/*     */   }
/*     */   
/*     */ 
/*     */   public IBlockState func_176203_a(int meta)
/*     */   {
/*  95 */     return meta < JarType.values().length ? func_176223_P().func_177226_a(TYPE, JarType.values()[meta]) : func_176223_P();
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_176201_c(IBlockState state)
/*     */   {
/* 101 */     return ((JarType)state.func_177229_b(TYPE)).ordinal();
/*     */   }
/*     */   
/*     */ 
/*     */   protected BlockState func_180661_e()
/*     */   {
/* 107 */     return new BlockState(this, new IProperty[] { TYPE });
/*     */   }
/*     */   
/*     */ 
/*     */   public String getStateName(IBlockState state, boolean fullName)
/*     */   {
/* 113 */     JarType type = (JarType)state.func_177229_b(TYPE);
/* 114 */     return "jar_" + type.func_176610_l();
/*     */   }
/*     */   
/*     */ 
/*     */   public IProperty[] getProperties()
/*     */   {
/* 120 */     return new IProperty[] { TYPE };
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_180663_b(World worldIn, BlockPos pos, IBlockState state)
/*     */   {
/* 126 */     spillEssentia = false;
/* 127 */     super.func_180663_b(worldIn, pos, state);
/* 128 */     spillEssentia = true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_180653_a(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune)
/*     */   {
/* 135 */     TileEntity te = worldIn.func_175625_s(pos);
/* 136 */     if ((te instanceof TileJarFillable))
/*     */     {
/* 138 */       spawnFilledJar(worldIn, pos, state, (TileJarFillable)te);
/*     */ 
/*     */     }
/* 141 */     else if ((te instanceof TileJarBrain))
/*     */     {
/* 143 */       spawnBrainJar(worldIn, pos, state, (TileJarBrain)te);
/*     */     }
/*     */     else
/*     */     {
/* 147 */       super.func_180653_a(worldIn, pos, state, chance, fortune);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_180657_a(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity te)
/*     */   {
/* 154 */     if ((te instanceof TileJarFillable))
/*     */     {
/* 156 */       spawnFilledJar(worldIn, pos, state, (TileJarFillable)te);
/*     */ 
/*     */     }
/* 159 */     else if ((te instanceof TileJarBrain))
/*     */     {
/* 161 */       spawnBrainJar(worldIn, pos, state, (TileJarBrain)te);
/*     */     }
/*     */     else
/*     */     {
/* 165 */       super.func_180657_a(worldIn, player, pos, state, (TileEntity)null);
/*     */     }
/*     */   }
/*     */   
/*     */   private void spawnFilledJar(World world, BlockPos pos, IBlockState state, TileJarFillable te) {
/* 170 */     ItemStack drop = new ItemStack(BlocksTC.jar, 1, func_176201_c(state));
/* 171 */     if (te.amount > 0) {
/* 172 */       ((BlockJarItem)drop.func_77973_b()).setAspects(drop, new AspectList().add(te.aspect, te.amount));
/*     */     }
/* 174 */     if (te.aspectFilter != null) {
/* 175 */       if (!drop.func_77942_o()) drop.func_77982_d(new NBTTagCompound());
/* 176 */       drop.func_77978_p().func_74778_a("AspectFilter", te.aspectFilter.getTag());
/*     */     }
/* 178 */     if (te.blocked) func_180635_a(world, pos, new ItemStack(ItemsTC.jarBrace));
/* 179 */     func_180635_a(world, pos, drop);
/*     */   }
/*     */   
/*     */   private void spawnBrainJar(World world, BlockPos pos, IBlockState state, TileJarBrain te) {
/* 183 */     ItemStack drop = new ItemStack(BlocksTC.jar, 1, func_176201_c(state));
/* 184 */     if (te.xp > 0) {
/* 185 */       drop.func_77983_a("xp", new NBTTagInt(te.xp));
/*     */     }
/* 187 */     func_180635_a(world, pos, drop);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_180633_a(World world, BlockPos pos, IBlockState state, EntityLivingBase ent, ItemStack stack)
/*     */   {
/* 193 */     int l = MathHelper.func_76128_c(ent.field_70177_z * 4.0F / 360.0F + 0.5D) & 0x3;
/* 194 */     TileEntity tile = world.func_175625_s(pos);
/* 195 */     if ((tile instanceof TileJarFillable)) {
/* 196 */       if (l == 0) ((TileJarFillable)tile).facing = 2;
/* 197 */       if (l == 1) ((TileJarFillable)tile).facing = 5;
/* 198 */       if (l == 2) ((TileJarFillable)tile).facing = 3;
/* 199 */       if (l == 3) { ((TileJarFillable)tile).facing = 4;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_180639_a(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float fx, float fy, float fz)
/*     */   {
/* 207 */     TileEntity te = world.func_175625_s(pos);
/* 208 */     if ((te != null) && ((te instanceof TileJarBrain))) {
/* 209 */       ((TileJarBrain)te).eatDelay = 40;
/* 210 */       if (!world.field_72995_K)
/*     */       {
/* 212 */         int var6 = world.field_73012_v.nextInt(Math.min(((TileJarBrain)te).xp + 1, 64));
/* 213 */         if (var6 > 0) {
/* 214 */           ((TileJarBrain)te).xp -= var6;
/* 215 */           int xp = var6;
/* 216 */           while (xp > 0)
/*     */           {
/* 218 */             int var2 = EntityXPOrb.func_70527_a(xp);
/* 219 */             xp -= var2;
/* 220 */             world.func_72838_d(new EntityXPOrb(world, pos.func_177958_n() + 0.5D, pos.func_177956_o() + 0.5D, pos.func_177952_p() + 0.5D, var2));
/*     */           }
/* 222 */           world.func_175689_h(pos);
/* 223 */           te.func_70296_d();
/*     */         }
/*     */       } else {
/* 226 */         world.func_72980_b(pos.func_177958_n() + 0.5D, pos.func_177956_o() + 0.5D, pos.func_177952_p() + 0.5D, "thaumcraft:jar", 0.2F, 1.0F, false);
/*     */       }
/*     */     }
/*     */     
/* 230 */     if ((te != null) && ((te instanceof TileJarFillable)) && (!((TileJarFillable)te).blocked) && (player.func_70694_bm() != null) && (player.func_70694_bm().func_77973_b() == ItemsTC.jarBrace))
/*     */     {
/* 232 */       ((TileJarFillable)te).blocked = true;
/* 233 */       player.func_70694_bm().field_77994_a -= 1;
/* 234 */       if (world.field_72995_K) {
/* 235 */         world.func_72980_b(pos.func_177958_n() + 0.5D, pos.func_177956_o() + 0.5D, pos.func_177952_p() + 0.5D, "thaumcraft:key", 1.0F, 1.0F, false);
/*     */       } else {
/* 237 */         te.func_70296_d();
/*     */       }
/*     */       
/*     */     }
/* 241 */     else if ((te != null) && ((te instanceof TileJarFillable)) && (player.func_70093_af()) && (((TileJarFillable)te).aspectFilter != null) && (side.ordinal() == ((TileJarFillable)te).facing))
/*     */     {
/* 243 */       ((TileJarFillable)te).aspectFilter = null;
/* 244 */       if (world.field_72995_K) {
/* 245 */         world.func_72980_b(pos.func_177958_n() + 0.5D, pos.func_177956_o() + 0.5D, pos.func_177952_p() + 0.5D, "thaumcraft:page", 1.0F, 1.0F, false);
/*     */       } else {
/* 247 */         world.func_72838_d(new EntityItem(world, pos.func_177958_n() + 0.5F + side.func_82601_c() / 3.0F, pos.func_177956_o() + 0.5F, pos.func_177952_p() + 0.5F + side.func_82599_e() / 3.0F, new ItemStack(ItemsTC.label)));
/*     */ 
/*     */       }
/*     */       
/*     */ 
/*     */     }
/* 253 */     else if ((te != null) && ((te instanceof TileJarFillable)) && (player.func_70093_af()) && (player.func_70694_bm() == null)) {
/* 254 */       if (((TileJarFillable)te).aspectFilter == null)
/* 255 */         ((TileJarFillable)te).aspect = null;
/* 256 */       if (world.field_72995_K) {
/* 257 */         world.func_72980_b(pos.func_177958_n() + 0.5D, pos.func_177956_o() + 0.5D, pos.func_177952_p() + 0.5D, "thaumcraft:jar", 0.4F, 1.0F, false);
/* 258 */         world.func_72980_b(pos.func_177958_n() + 0.5D, pos.func_177956_o() + 0.5D, pos.func_177952_p() + 0.5D, "game.neutral.swim", 0.5F, 1.0F + (world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.3F, false);
/*     */       } else {
/* 260 */         AuraHelper.pollute(world, pos, ((TileJarFillable)te).amount, true);
/*     */       }
/* 262 */       ((TileJarFillable)te).amount = 0;
/* 263 */       te.func_70296_d();
/*     */     }
/*     */     
/* 266 */     return true;
/*     */   }
/*     */   
/*     */   public boolean applyLabel(EntityPlayer player, BlockPos pos, EnumFacing side, ItemStack labelstack)
/*     */   {
/* 271 */     TileEntity te = player.field_70170_p.func_175625_s(pos);
/* 272 */     if ((te != null) && ((te instanceof TileJarFillable)) && (((TileJarFillable)te).aspectFilter == null)) {
/* 273 */       if ((((TileJarFillable)te).amount == 0) && (((IEssentiaContainerItem)player.func_70694_bm().func_77973_b()).getAspects(player.func_70694_bm()) == null)) {
/* 274 */         return false;
/*     */       }
/* 276 */       if ((((TileJarFillable)te).amount == 0) && (((IEssentiaContainerItem)player.func_70694_bm().func_77973_b()).getAspects(player.func_70694_bm()) != null)) {
/* 277 */         ((TileJarFillable)te).aspect = ((IEssentiaContainerItem)(IEssentiaContainerItem)player.func_70694_bm().func_77973_b()).getAspects(player.func_70694_bm()).getAspects()[0];
/*     */       }
/* 279 */       func_180633_a(player.field_70170_p, pos, player.field_70170_p.func_180495_p(pos), player, null);
/* 280 */       ((TileJarFillable)te).aspectFilter = ((TileJarFillable)te).aspect;
/* 281 */       player.field_70170_p.func_175689_h(pos);
/* 282 */       te.func_70296_d();
/* 283 */       player.field_70170_p.func_72908_a(pos.func_177958_n() + 0.5D, pos.func_177956_o() + 0.5D, pos.func_177952_p() + 0.5D, "thaumcraft:jar", 0.4F, 1.0F);
/* 284 */       return true;
/*     */     }
/* 286 */     return false;
/*     */   }
/*     */   
/*     */   public float getEnchantPowerBonus(World world, BlockPos pos)
/*     */   {
/* 291 */     TileEntity te = world.func_175625_s(pos);
/* 292 */     if ((te != null) && ((te instanceof TileJarBrain))) {
/* 293 */       return 2.0F;
/*     */     }
/* 295 */     return super.getEnchantPowerBonus(world, pos);
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_180655_c(World world, BlockPos pos, IBlockState state, Random rand)
/*     */   {
/* 302 */     TileEntity tile = world.func_175625_s(pos);
/* 303 */     if ((tile != null) && ((tile instanceof TileJarBrain)) && (((TileJarBrain)tile).xp >= ((TileJarBrain)tile).xpMax)) {
/* 304 */       Thaumcraft.proxy.getFX().wispFX5(pos.func_177958_n() + 0.5F, pos.func_177956_o() + 0.8F, pos.func_177952_p() + 0.5F, pos.func_177958_n() + 0.3F + world.field_73012_v.nextFloat() * 0.4F, pos.func_177956_o() + 0.8F, pos.func_177952_p() + 0.3F + world.field_73012_v.nextFloat() * 0.4F, 0.5F, true, -0.025F, Aspect.DEATH.getColor());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_149740_M()
/*     */   {
/* 313 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int func_180641_l(World world, BlockPos pos)
/*     */   {
/* 320 */     TileEntity tile = world.func_175625_s(pos);
/* 321 */     if ((tile != null) && ((tile instanceof TileJarBrain))) {
/* 322 */       float r = ((TileJarBrain)tile).xp / ((TileJarBrain)tile).xpMax;
/* 323 */       return MathHelper.func_76141_d(r * 14.0F) + (((TileJarBrain)tile).xp > 0 ? 1 : 0);
/*     */     }
/* 325 */     if ((tile != null) && ((tile instanceof TileJarFillable))) {
/* 326 */       float r = ((TileJarFillable)tile).amount / ((TileJarFillable)tile).maxAmount;
/* 327 */       return MathHelper.func_76141_d(r * 14.0F) + (((TileJarFillable)tile).amount > 0 ? 1 : 0);
/*     */     }
/* 329 */     return super.func_180641_l(world, pos);
/*     */   }
/*     */   
/*     */   public static enum JarType implements IStringSerializable
/*     */   {
/* 334 */     NORMAL,  VOID,  BRAIN;
/*     */     
/*     */     private JarType() {}
/*     */     
/*     */     public String func_176610_l() {
/* 339 */       return name().toLowerCase();
/*     */     }
/*     */     
/*     */ 
/*     */     public String toString()
/*     */     {
/* 345 */       return func_176610_l();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/devices/BlockJar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */