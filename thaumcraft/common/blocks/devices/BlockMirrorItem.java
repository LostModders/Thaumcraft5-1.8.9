/*     */ package thaumcraft.common.blocks.devices;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.ItemBlock;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagInt;
/*     */ import net.minecraft.nbt.NBTTagString;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.ChatComponentTranslation;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraftforge.common.DimensionManager;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.common.tiles.devices.TileMirror;
/*     */ import thaumcraft.common.tiles.devices.TileMirrorEssentia;
/*     */ 
/*     */ public class BlockMirrorItem extends ItemBlock
/*     */ {
/*     */   public BlockMirrorItem(Block par1)
/*     */   {
/*  32 */     super(par1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
/*     */   {
/*  39 */     if ((world.func_180495_p(pos).func_177230_c() instanceof BlockMirror)) {
/*  40 */       if (world.field_72995_K) {
/*  41 */         player.func_71038_i();
/*  42 */         return super.onItemUseFirst(stack, player, world, pos, side, hitX, hitY, hitZ);
/*     */       }
/*  44 */       if (this.field_150939_a == BlocksTC.mirror) {
/*  45 */         TileEntity tm = world.func_175625_s(pos);
/*  46 */         if ((tm != null) && ((tm instanceof TileMirror)) && (!((TileMirror)tm).isLinkValid())) {
/*  47 */           ItemStack st = stack.func_77946_l();
/*  48 */           st.field_77994_a = 1;
/*  49 */           st.func_77964_b(1);
/*  50 */           st.func_77983_a("linkX", new NBTTagInt(tm.func_174877_v().func_177958_n()));
/*  51 */           st.func_77983_a("linkY", new NBTTagInt(tm.func_174877_v().func_177956_o()));
/*  52 */           st.func_77983_a("linkZ", new NBTTagInt(tm.func_174877_v().func_177952_p()));
/*  53 */           st.func_77983_a("linkDim", new NBTTagInt(world.field_73011_w.func_177502_q()));
/*  54 */           st.func_77983_a("dimname", new NBTTagString(DimensionManager.getProvider(world.field_73011_w.func_177502_q()).func_80007_l()));
/*  55 */           world.func_72908_a(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), "thaumcraft:jar", 1.0F, 2.0F);
/*  56 */           if ((!player.field_71071_by.func_70441_a(st)) && 
/*  57 */             (!world.field_72995_K)) {
/*  58 */             world.func_72838_d(new EntityItem(world, player.field_70165_t, player.field_70163_u, player.field_70161_v, st));
/*     */           }
/*     */           
/*  61 */           if (!player.field_71075_bZ.field_75098_d) {
/*  62 */             stack.field_77994_a -= 1;
/*     */           }
/*  64 */           player.field_71069_bz.func_75142_b();
/*  65 */         } else if ((tm != null) && ((tm instanceof TileMirror))) {
/*  66 */           player.func_145747_a(new ChatComponentTranslation("§5§oThat mirror is already linked to a valid destination.", new Object[0]));
/*     */         }
/*     */       } else {
/*  69 */         TileEntity tm = world.func_175625_s(pos);
/*  70 */         if ((tm != null) && ((tm instanceof TileMirrorEssentia)) && (!((TileMirrorEssentia)tm).isLinkValid())) {
/*  71 */           ItemStack st = stack.func_77946_l();
/*  72 */           st.field_77994_a = 1;
/*  73 */           st.func_77964_b(1);
/*  74 */           st.func_77983_a("linkX", new NBTTagInt(tm.func_174877_v().func_177958_n()));
/*  75 */           st.func_77983_a("linkY", new NBTTagInt(tm.func_174877_v().func_177956_o()));
/*  76 */           st.func_77983_a("linkZ", new NBTTagInt(tm.func_174877_v().func_177952_p()));
/*  77 */           st.func_77983_a("linkDim", new NBTTagInt(world.field_73011_w.func_177502_q()));
/*  78 */           st.func_77983_a("dimname", new NBTTagString(DimensionManager.getProvider(world.field_73011_w.func_177502_q()).func_80007_l()));
/*  79 */           world.func_72908_a(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), "thaumcraft:jar", 1.0F, 2.0F);
/*  80 */           if ((!player.field_71071_by.func_70441_a(st)) && 
/*  81 */             (!world.field_72995_K)) {
/*  82 */             world.func_72838_d(new EntityItem(world, player.field_70165_t, player.field_70163_u, player.field_70161_v, st));
/*     */           }
/*     */           
/*  85 */           if (!player.field_71075_bZ.field_75098_d) {
/*  86 */             stack.field_77994_a -= 1;
/*     */           }
/*  88 */           player.field_71069_bz.func_75142_b();
/*  89 */         } else if ((tm != null) && ((tm instanceof TileMirrorEssentia))) {
/*  90 */           player.func_145747_a(new ChatComponentTranslation("§5§oThat mirror is already linked to a valid destination.", new Object[0]));
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*  95 */     return super.onItemUseFirst(stack, player, world, pos, side, hitX, hitY, hitZ);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, IBlockState newState)
/*     */   {
/* 104 */     boolean ret = super.placeBlockAt(stack, player, world, pos, side, hitX, hitY, hitZ, newState);
/* 105 */     if ((ret) && (!world.field_72995_K)) {
/* 106 */       if (this.field_150939_a == BlocksTC.mirror) {
/* 107 */         TileEntity te = world.func_175625_s(pos);
/* 108 */         if ((te != null) && ((te instanceof TileMirror)) && 
/* 109 */           (stack.func_77942_o())) {
/* 110 */           ((TileMirror)te).linkX = stack.func_77978_p().func_74762_e("linkX");
/* 111 */           ((TileMirror)te).linkY = stack.func_77978_p().func_74762_e("linkY");
/* 112 */           ((TileMirror)te).linkZ = stack.func_77978_p().func_74762_e("linkZ");
/* 113 */           ((TileMirror)te).linkDim = stack.func_77978_p().func_74762_e("linkDim");
/* 114 */           ((TileMirror)te).restoreLink();
/*     */         }
/*     */       }
/*     */       else {
/* 118 */         TileEntity te = world.func_175625_s(pos);
/* 119 */         if ((te != null) && ((te instanceof TileMirrorEssentia)) && 
/* 120 */           (stack.func_77942_o())) {
/* 121 */           ((TileMirrorEssentia)te).linkX = stack.func_77978_p().func_74762_e("linkX");
/* 122 */           ((TileMirrorEssentia)te).linkY = stack.func_77978_p().func_74762_e("linkY");
/* 123 */           ((TileMirrorEssentia)te).linkZ = stack.func_77978_p().func_74762_e("linkZ");
/* 124 */           ((TileMirrorEssentia)te).linkDim = stack.func_77978_p().func_74762_e("linkDim");
/* 125 */           ((TileMirrorEssentia)te).restoreLink();
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 130 */     return ret;
/*     */   }
/*     */   
/*     */ 
/*     */   public EnumRarity func_77613_e(ItemStack itemstack)
/*     */   {
/* 136 */     return EnumRarity.UNCOMMON;
/*     */   }
/*     */   
/*     */   @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*     */   public void func_77624_a(ItemStack item, EntityPlayer par2EntityPlayer, List list, boolean par4)
/*     */   {
/* 142 */     if (item.func_77942_o()) {
/* 143 */       int lx = item.func_77978_p().func_74762_e("linkX");
/* 144 */       int ly = item.func_77978_p().func_74762_e("linkY");
/* 145 */       int lz = item.func_77978_p().func_74762_e("linkZ");
/* 146 */       int ldim = item.func_77978_p().func_74762_e("linkDim");
/* 147 */       String dimname = item.func_77978_p().func_74779_i("dimname");
/* 148 */       list.add("Linked to " + lx + "," + ly + "," + lz + " in " + dimname);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/devices/BlockMirrorItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */