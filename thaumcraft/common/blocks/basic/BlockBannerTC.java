/*     */ package thaumcraft.common.blocks.basic;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.block.ITileEntityProvider;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.IEssentiaContainerItem;
/*     */ import thaumcraft.common.blocks.BlockTC;
/*     */ import thaumcraft.common.items.resources.ItemPhial;
/*     */ import thaumcraft.common.tiles.misc.TileBanner;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockBannerTC
/*     */   extends BlockTC
/*     */   implements ITileEntityProvider
/*     */ {
/*     */   public BlockBannerTC()
/*     */   {
/*  36 */     super(Material.field_151575_d);
/*  37 */     func_149711_c(1.0F);
/*  38 */     func_149672_a(field_149766_f);
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*     */   {
/*  45 */     par3List.add(new ItemStack(par1, 1, 1));
/*  46 */     for (int a = 0; a < 16; a++) {
/*  47 */       ItemStack banner = new ItemStack(par1, 1, 0);
/*  48 */       banner.func_77982_d(new NBTTagCompound());
/*  49 */       banner.func_77978_p().func_74774_a("color", (byte)a);
/*  50 */       par3List.add(banner);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_149645_b()
/*     */   {
/*  57 */     return -1;
/*     */   }
/*     */   
/*     */   public void func_180654_a(IBlockAccess par1iBlockAccess, BlockPos pos)
/*     */   {
/*  62 */     TileEntity tile = par1iBlockAccess.func_175625_s(pos);
/*  63 */     if ((tile != null) && ((tile instanceof TileBanner))) {
/*  64 */       if (((TileBanner)tile).getWall()) {
/*  65 */         switch (((TileBanner)tile).getBannerFacing()) {
/*  66 */         case 0:  func_149676_a(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 0.25F); break;
/*  67 */         case 8:  func_149676_a(0.0F, -1.0F, 0.75F, 1.0F, 1.0F, 1.0F); break;
/*  68 */         case 12:  func_149676_a(0.0F, -1.0F, 0.0F, 0.25F, 1.0F, 1.0F); break;
/*  69 */         case 4:  func_149676_a(0.75F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */         }
/*     */       } else {
/*  72 */         func_149676_a(0.33F, 0.0F, 0.33F, 0.66F, 2.0F, 0.66F);
/*     */       }
/*     */     } else {
/*  75 */       func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */     }
/*  77 */     super.func_180654_a(par1iBlockAccess, pos);
/*     */   }
/*     */   
/*     */ 
/*     */   public AxisAlignedBB func_180640_a(World worldIn, BlockPos pos, IBlockState state)
/*     */   {
/*  83 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149686_d()
/*     */   {
/*  89 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149662_c()
/*     */   {
/*  95 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_180639_a(World w, BlockPos pos, IBlockState state, EntityPlayer p, EnumFacing side, float hitX, float hitY, float hitZ)
/*     */   {
/* 102 */     if ((p.func_70093_af()) || ((p.field_71071_by.func_70448_g() != null) && ((p.field_71071_by.func_70448_g().func_77973_b() instanceof ItemPhial))))
/*     */     {
/*     */ 
/* 105 */       TileBanner te = (TileBanner)w.func_175625_s(pos);
/* 106 */       if (te != null)
/*     */       {
/* 108 */         if (te.getColor() >= 0) {
/* 109 */           if (p.func_70093_af()) {
/* 110 */             te.setAspect(null);
/*     */           }
/* 112 */           else if (((IEssentiaContainerItem)p.func_70694_bm().func_77973_b()).getAspects(p.func_70694_bm()) != null) {
/* 113 */             te.setAspect(((IEssentiaContainerItem)(IEssentiaContainerItem)p.func_70694_bm().func_77973_b()).getAspects(p.func_70694_bm()).getAspects()[0]);
/* 114 */             p.func_70694_bm().field_77994_a -= 1;
/*     */           }
/*     */           
/* 117 */           w.func_175689_h(pos);
/* 118 */           te.func_70296_d();
/* 119 */           w.func_72908_a(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), "step.cloth", 1.0F, 1.0F);
/*     */         }
/*     */       }
/*     */     }
/* 123 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public TileEntity func_149915_a(World worldIn, int meta)
/*     */   {
/* 129 */     return new TileBanner();
/*     */   }
/*     */   
/*     */   public boolean hasTileEntity(IBlockState state)
/*     */   {
/* 134 */     return true;
/*     */   }
/*     */   
/*     */   public ItemStack getPickBlock(MovingObjectPosition target, World world, BlockPos pos, EntityPlayer player)
/*     */   {
/* 139 */     TileEntity te = world.func_175625_s(pos);
/* 140 */     if ((te instanceof TileBanner))
/*     */     {
/* 142 */       ItemStack drop = new ItemStack(this, 1, ((TileBanner)te).getColor() >= 0 ? 0 : 1);
/* 143 */       if ((((TileBanner)te).getColor() >= 0) || (((TileBanner)te).getAspect() != null)) {
/* 144 */         drop.func_77982_d(new NBTTagCompound());
/* 145 */         if (((TileBanner)te).getAspect() != null) {
/* 146 */           drop.func_77978_p().func_74778_a("aspect", ((TileBanner)te).getAspect().getTag());
/*     */         }
/* 148 */         drop.func_77978_p().func_74774_a("color", ((TileBanner)te).getColor());
/*     */       }
/* 150 */       return drop;
/*     */     }
/*     */     
/* 153 */     return super.getPickBlock(target, world, pos, player);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_180653_a(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune)
/*     */   {
/* 159 */     TileEntity te = worldIn.func_175625_s(pos);
/*     */     
/* 161 */     if ((te instanceof TileBanner))
/*     */     {
/* 163 */       ItemStack drop = new ItemStack(this, 1, ((TileBanner)te).getColor() >= 0 ? 0 : 1);
/* 164 */       if ((((TileBanner)te).getColor() >= 0) || (((TileBanner)te).getAspect() != null)) {
/* 165 */         drop.func_77982_d(new NBTTagCompound());
/* 166 */         if (((TileBanner)te).getAspect() != null) {
/* 167 */           drop.func_77978_p().func_74778_a("aspect", ((TileBanner)te).getAspect().getTag());
/*     */         }
/* 169 */         drop.func_77978_p().func_74774_a("color", ((TileBanner)te).getColor());
/*     */       }
/*     */       
/* 172 */       func_180635_a(worldIn, pos, drop);
/*     */     }
/*     */     else
/*     */     {
/* 176 */       super.func_180653_a(worldIn, pos, state, chance, fortune);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_180657_a(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity te)
/*     */   {
/* 183 */     if ((te instanceof TileBanner))
/*     */     {
/* 185 */       ItemStack drop = new ItemStack(this, 1, ((TileBanner)te).getColor() >= 0 ? 0 : 1);
/* 186 */       if ((((TileBanner)te).getColor() >= 0) || (((TileBanner)te).getAspect() != null)) {
/* 187 */         drop.func_77982_d(new NBTTagCompound());
/* 188 */         if (((TileBanner)te).getAspect() != null) {
/* 189 */           drop.func_77978_p().func_74778_a("aspect", ((TileBanner)te).getAspect().getTag());
/*     */         }
/* 191 */         drop.func_77978_p().func_74774_a("color", ((TileBanner)te).getColor());
/*     */       }
/*     */       
/* 194 */       func_180635_a(worldIn, pos, drop);
/*     */     }
/*     */     else
/*     */     {
/* 198 */       super.func_180657_a(worldIn, player, pos, state, (TileEntity)null);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/basic/BlockBannerTC.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */