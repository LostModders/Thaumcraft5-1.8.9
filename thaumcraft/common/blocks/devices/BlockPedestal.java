/*     */ package thaumcraft.common.blocks.devices;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyEnum;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.IStringSerializable;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.common.blocks.BlockTCDevice;
/*     */ import thaumcraft.common.lib.utils.InventoryUtils;
/*     */ import thaumcraft.common.tiles.crafting.TilePedestal;
/*     */ 
/*     */ public class BlockPedestal extends BlockTCDevice
/*     */ {
/*  23 */   public static final PropertyEnum VARIANT = PropertyEnum.func_177709_a("variant", PedestalType.class);
/*     */   
/*     */   public BlockPedestal() {
/*  26 */     super(net.minecraft.block.material.Material.field_151576_e, TilePedestal.class);
/*  27 */     func_149672_a(Block.field_149769_e);
/*  28 */     func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(VARIANT, PedestalType.NORMAL));
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149662_c()
/*     */   {
/*  34 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149686_d()
/*     */   {
/*  40 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_180639_a(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ)
/*     */   {
/*  49 */     if (world.field_72995_K) { return true;
/*     */     }
/*  51 */     net.minecraft.tileentity.TileEntity tile = world.func_175625_s(pos);
/*     */     
/*  53 */     if ((tile != null) && ((tile instanceof TilePedestal)))
/*     */     {
/*  55 */       TilePedestal ped = (TilePedestal)tile;
/*  56 */       if ((ped.func_70301_a(0) == null) && (player.field_71071_by.func_70448_g() != null))
/*     */       {
/*  58 */         ItemStack i = player.func_71045_bC().func_77946_l();
/*  59 */         i.field_77994_a = 1;
/*  60 */         ped.func_70299_a(0, i);
/*  61 */         player.func_71045_bC().field_77994_a -= 1;
/*  62 */         if (player.func_71045_bC().field_77994_a == 0) {
/*  63 */           player.func_70062_b(0, null);
/*     */         }
/*  65 */         player.field_71071_by.func_70296_d();
/*  66 */         world.func_72908_a(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), "random.pop", 0.2F, ((world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.7F + 1.0F) * 1.6F);
/*     */         
/*     */ 
/*  69 */         return true; }
/*  70 */       if (ped.func_70301_a(0) != null) {
/*  71 */         InventoryUtils.dropItemsAtEntity(world, pos, player);
/*  72 */         world.func_72908_a(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), "random.pop", 0.2F, ((world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.7F + 1.0F) * 1.5F);
/*     */         
/*     */ 
/*  75 */         return true;
/*     */       }
/*     */     }
/*     */     
/*  79 */     return super.func_180639_a(world, pos, state, player, side, hitX, hitY, hitZ);
/*     */   }
/*     */   
/*     */ 
/*     */   public IBlockState func_180642_a(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
/*     */   {
/*  85 */     return func_176203_a(meta);
/*     */   }
/*     */   
/*     */ 
/*     */   public IBlockState func_176203_a(int meta)
/*     */   {
/*  91 */     return func_176223_P().func_177226_a(VARIANT, PedestalType.values()[meta]);
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_176201_c(IBlockState state)
/*     */   {
/*  97 */     int meta = ((PedestalType)state.func_177229_b(VARIANT)).ordinal();
/*     */     
/*  99 */     return meta;
/*     */   }
/*     */   
/*     */ 
/*     */   protected BlockState func_180661_e()
/*     */   {
/* 105 */     return new BlockState(this, new IProperty[] { VARIANT });
/*     */   }
/*     */   
/*     */ 
/*     */   public IProperty[] getProperties()
/*     */   {
/* 111 */     return new IProperty[] { VARIANT };
/*     */   }
/*     */   
/*     */ 
/*     */   public String getStateName(IBlockState state, boolean fullName)
/*     */   {
/* 117 */     PedestalType type = (PedestalType)state.func_177229_b(VARIANT);
/*     */     
/* 119 */     return (fullName ? "pedestal_" : "") + type.func_176610_l();
/*     */   }
/*     */   
/*     */   public static enum PedestalType
/*     */     implements IStringSerializable
/*     */   {
/* 125 */     NORMAL,  ELDRITCH,  ANCIENT;
/*     */     
/*     */     private PedestalType() {}
/*     */     
/*     */     public String func_176610_l() {
/* 130 */       return name().toLowerCase();
/*     */     }
/*     */     
/*     */ 
/*     */     public String toString()
/*     */     {
/* 136 */       return func_176610_l();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/devices/BlockPedestal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */