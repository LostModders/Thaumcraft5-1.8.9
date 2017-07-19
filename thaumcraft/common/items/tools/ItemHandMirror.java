/*     */ package thaumcraft.common.items.tools;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagInt;
/*     */ import net.minecraft.nbt.NBTTagString;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraftforge.common.DimensionManager;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.tiles.devices.TileMirror;
/*     */ 
/*     */ public class ItemHandMirror
/*     */   extends Item
/*     */ {
/*     */   public ItemHandMirror()
/*     */   {
/*  35 */     func_77625_d(1);
/*  36 */     func_77627_a(false);
/*  37 */     func_77656_e(0);
/*  38 */     func_77637_a(Thaumcraft.tabTC);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_77651_p()
/*     */   {
/*  44 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public EnumRarity func_77613_e(ItemStack itemstack)
/*     */   {
/*  50 */     return EnumRarity.UNCOMMON;
/*     */   }
/*     */   
/*     */   public boolean func_77636_d(ItemStack par1ItemStack)
/*     */   {
/*  55 */     return par1ItemStack.func_77942_o();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean onItemUseFirst(ItemStack itemstack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float par8, float par9, float par10)
/*     */   {
/*  62 */     Block bi = world.func_180495_p(pos).func_177230_c();
/*  63 */     if (bi == BlocksTC.mirror) {
/*  64 */       if (world.field_72995_K) {
/*  65 */         player.func_71038_i();
/*  66 */         return super.onItemUseFirst(itemstack, player, world, pos, side, par8, par9, par10);
/*     */       }
/*  68 */       TileEntity tm = world.func_175625_s(pos);
/*  69 */       if ((tm != null) && ((tm instanceof TileMirror))) {
/*  70 */         itemstack.func_77983_a("linkX", new NBTTagInt(pos.func_177958_n()));
/*  71 */         itemstack.func_77983_a("linkY", new NBTTagInt(pos.func_177956_o()));
/*  72 */         itemstack.func_77983_a("linkZ", new NBTTagInt(pos.func_177952_p()));
/*  73 */         itemstack.func_77983_a("linkDim", new NBTTagInt(world.field_73011_w.func_177502_q()));
/*  74 */         itemstack.func_77983_a("dimname", new NBTTagString(DimensionManager.getProvider(world.field_73011_w.func_177502_q()).func_80007_l()));
/*  75 */         world.func_72908_a(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), "thaumcraft:jar", 1.0F, 2.0F);
/*  76 */         player.func_145747_a(new ChatComponentText("§5§o" + StatCollector.func_74838_a("tc.handmirrorlinked")));
/*  77 */         player.field_71069_bz.func_75142_b();
/*     */       }
/*     */       
/*  80 */       return true;
/*     */     }
/*     */     
/*  83 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
/*     */   {
/*  91 */     if ((!par2World.field_72995_K) && (par1ItemStack.func_77942_o()))
/*     */     {
/*  93 */       int lx = par1ItemStack.func_77978_p().func_74762_e("linkX");
/*  94 */       int ly = par1ItemStack.func_77978_p().func_74762_e("linkY");
/*  95 */       int lz = par1ItemStack.func_77978_p().func_74762_e("linkZ");
/*  96 */       int ldim = par1ItemStack.func_77978_p().func_74762_e("linkDim");
/*     */       
/*  98 */       World targetWorld = MinecraftServer.func_71276_C().func_71218_a(ldim);
/*  99 */       if (targetWorld == null) return super.func_77659_a(par1ItemStack, par2World, par3EntityPlayer);
/* 100 */       TileEntity te = targetWorld.func_175625_s(new BlockPos(lx, ly, lz));
/* 101 */       if ((te == null) || (!(te instanceof TileMirror))) {
/* 102 */         par1ItemStack.func_77982_d(null);
/* 103 */         par2World.func_72956_a(par3EntityPlayer, "thaumcraft:zap", 1.0F, 0.8F);
/* 104 */         par3EntityPlayer.func_145747_a(new ChatComponentText("§5§o" + StatCollector.func_74838_a("tc.handmirrorerror")));
/* 105 */         return super.func_77659_a(par1ItemStack, par2World, par3EntityPlayer);
/*     */       }
/*     */       
/* 108 */       par3EntityPlayer.openGui(Thaumcraft.instance, 4, par2World, MathHelper.func_76128_c(par3EntityPlayer.field_70165_t), MathHelper.func_76128_c(par3EntityPlayer.field_70163_u), MathHelper.func_76128_c(par3EntityPlayer.field_70161_v));
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 114 */     return super.func_77659_a(par1ItemStack, par2World, par3EntityPlayer);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_77624_a(ItemStack item, EntityPlayer par2EntityPlayer, List list, boolean par4)
/*     */   {
/* 120 */     if (item.func_77942_o()) {
/* 121 */       int lx = item.func_77978_p().func_74762_e("linkX");
/* 122 */       int ly = item.func_77978_p().func_74762_e("linkY");
/* 123 */       int lz = item.func_77978_p().func_74762_e("linkZ");
/* 124 */       int ldim = item.func_77978_p().func_74762_e("linkDim");
/* 125 */       String dimname = item.func_77978_p().func_74779_i("dimname");
/* 126 */       list.add(StatCollector.func_74838_a("tc.handmirrorlinkedto") + " " + lx + "," + ly + "," + lz + " in " + dimname);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static boolean transport(ItemStack mirror, ItemStack items, EntityPlayer player, World worldObj)
/*     */   {
/* 133 */     if (mirror.func_77942_o()) {
/* 134 */       int lx = mirror.func_77978_p().func_74762_e("linkX");
/* 135 */       int ly = mirror.func_77978_p().func_74762_e("linkY");
/* 136 */       int lz = mirror.func_77978_p().func_74762_e("linkZ");
/* 137 */       int ldim = mirror.func_77978_p().func_74762_e("linkDim");
/*     */       
/* 139 */       World targetWorld = MinecraftServer.func_71276_C().func_71218_a(ldim);
/* 140 */       if (targetWorld == null) return false;
/* 141 */       TileEntity te = targetWorld.func_175625_s(new BlockPos(lx, ly, lz));
/* 142 */       if ((te == null) || (!(te instanceof TileMirror))) {
/* 143 */         mirror.func_77982_d(null);
/* 144 */         worldObj.func_72956_a(player, "thaumcraft:zap", 1.0F, 0.8F);
/* 145 */         player.func_145747_a(new ChatComponentText("§5§o" + StatCollector.func_74838_a("tc.handmirrorerror")));
/* 146 */         return false;
/*     */       }
/* 148 */       TileMirror tm = (TileMirror)te;
/*     */       
/* 150 */       if (tm.transportDirect(items)) {
/* 151 */         items = null;
/* 152 */         worldObj.func_72956_a(player, "mob.endermen.portal", 0.1F, 1.0F);
/*     */       }
/* 154 */       return true;
/*     */     }
/* 156 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/items/tools/ItemHandMirror.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */