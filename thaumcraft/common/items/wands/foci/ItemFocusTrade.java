/*     */ package thaumcraft.common.items.wands.foci;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.MovingObjectPosition.MovingObjectType;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.ThaumcraftMaterials;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.items.IArchitect.EnumAxis;
/*     */ import thaumcraft.api.wands.FocusUpgradeType;
/*     */ import thaumcraft.api.wands.IWand;
/*     */ import thaumcraft.api.wands.ItemFocusBasic;
/*     */ import thaumcraft.common.items.wands.WandManager;
/*     */ import thaumcraft.common.lib.events.ServerTickEventsFML;
/*     */ import thaumcraft.common.lib.utils.BlockUtils;
/*     */ 
/*     */ public class ItemFocusTrade extends ItemFocusBasic implements thaumcraft.api.items.IArchitect, thaumcraft.api.items.IFocusPicker
/*     */ {
/*     */   public ItemFocusTrade()
/*     */   {
/*  35 */     super("equal_trade", 8747923);
/*     */   }
/*     */   
/*     */ 
/*     */   protected MovingObjectPosition getMovingObjectPositionFromPlayer(World par1World, EntityPlayer par2EntityPlayer)
/*     */   {
/*  41 */     float f = 1.0F;
/*  42 */     float f1 = par2EntityPlayer.field_70127_C + (par2EntityPlayer.field_70125_A - par2EntityPlayer.field_70127_C) * f;
/*  43 */     float f2 = par2EntityPlayer.field_70126_B + (par2EntityPlayer.field_70177_z - par2EntityPlayer.field_70126_B) * f;
/*  44 */     double d0 = par2EntityPlayer.field_70169_q + (par2EntityPlayer.field_70165_t - par2EntityPlayer.field_70169_q) * f;
/*  45 */     double d1 = par2EntityPlayer.field_70167_r + (par2EntityPlayer.field_70163_u - par2EntityPlayer.field_70167_r) * f + (par1World.field_72995_K ? par2EntityPlayer.func_70047_e() - par2EntityPlayer.getDefaultEyeHeight() : par2EntityPlayer.func_70047_e());
/*  46 */     double d2 = par2EntityPlayer.field_70166_s + (par2EntityPlayer.field_70161_v - par2EntityPlayer.field_70166_s) * f;
/*  47 */     Vec3 vec3 = new Vec3(d0, d1, d2);
/*  48 */     float f3 = MathHelper.func_76134_b(-f2 * 0.017453292F - 3.1415927F);
/*  49 */     float f4 = MathHelper.func_76126_a(-f2 * 0.017453292F - 3.1415927F);
/*  50 */     float f5 = -MathHelper.func_76134_b(-f1 * 0.017453292F);
/*  51 */     float f6 = MathHelper.func_76126_a(-f1 * 0.017453292F);
/*  52 */     float f7 = f4 * f5;
/*  53 */     float f8 = f3 * f5;
/*  54 */     double d3 = 5.0D;
/*  55 */     if ((par2EntityPlayer instanceof EntityPlayerMP))
/*     */     {
/*  57 */       d3 = ((EntityPlayerMP)par2EntityPlayer).field_71134_c.getBlockReachDistance();
/*     */     }
/*  59 */     Vec3 vec31 = vec3.func_72441_c(f7 * d3, f6 * d3, f8 * d3);
/*  60 */     return par1World.func_72901_a(vec3, vec31, false);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean onFocusActivation(ItemStack itemstack, World world, EntityLivingBase player, MovingObjectPosition mop, int count)
/*     */   {
/*  66 */     IWand wand = (IWand)itemstack.func_77973_b();
/*     */     
/*  68 */     if (((player instanceof EntityPlayer)) && (mop != null) && (mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK))
/*     */     {
/*  70 */       IBlockState bi = world.func_180495_p(mop.func_178782_a());
/*     */       
/*  72 */       if (player.func_70093_af()) {
/*  73 */         if ((!world.field_72995_K) && (world.func_175625_s(mop.func_178782_a()) == null)) {
/*  74 */           ItemStack isout = new ItemStack(bi.func_177230_c(), 1, bi.func_177230_c().func_176201_c(bi));
/*     */           try {
/*  76 */             if (bi != Blocks.field_150350_a) {
/*  77 */               ItemStack is = BlockUtils.createStackedBlock(bi);
/*  78 */               if (is != null) {
/*  79 */                 isout = is.func_77946_l();
/*     */               }
/*     */             }
/*     */           } catch (Exception e) {}
/*  83 */           storePickedBlock(itemstack, isout);
/*     */         } else {
/*  85 */           player.func_71038_i();
/*     */         }
/*     */       } else {
/*  88 */         mop = func_77621_a(world, (EntityPlayer)player, false);
/*  89 */         ItemStack pb = getPickedBlock(itemstack);
/*  90 */         if ((pb != null) && (world.field_72995_K)) {
/*  91 */           player.func_71038_i();
/*     */         }
/*  93 */         else if ((pb != null) && (world.func_175625_s(mop.func_178782_a()) == null) && (world.func_180495_p(mop.func_178782_a()).func_177230_c().func_149688_o() != ThaumcraftMaterials.MATERIAL_TAINT))
/*     */         {
/*     */ 
/*  96 */           if (isUpgradedWith(wand.getFocusStack(itemstack), FocusUpgradeType.architect)) {
/*  97 */             int sizeX = WandManager.getAreaX(itemstack);
/*  98 */             int sizeZ = WandManager.getAreaZ(itemstack);
/*  99 */             ArrayList<BlockPos> blocks = getArchitectBlocks(itemstack, world, mop.func_178782_a(), mop.field_178784_b, (EntityPlayer)player);
/*     */             
/* 101 */             for (BlockPos c : blocks) {
/* 102 */               ServerTickEventsFML.addSwapper(world, c, world.func_180495_p(c), pb, 0, (EntityPlayer)player, ((EntityPlayer)player).field_71071_by.field_70461_c);
/*     */             }
/*     */           }
/*     */           else {
/* 106 */             ServerTickEventsFML.addSwapper(world, mop.func_178782_a(), world.func_180495_p(mop.func_178782_a()), pb, 3 + wand.getFocusEnlarge(itemstack), (EntityPlayer)player, ((EntityPlayer)player).field_71071_by.field_70461_c);
/*     */           }
/*     */           
/*     */ 
/* 110 */           return true;
/*     */         }
/*     */       }
/*     */     }
/* 114 */     return false;
/*     */   }
/*     */   
/*     */   public boolean onEntitySwing(EntityLivingBase player, ItemStack stack)
/*     */   {
/* 119 */     if ((!player.field_70170_p.field_72995_K) && ((player instanceof EntityPlayer))) {
/* 120 */       ItemStack pb = getPickedBlock(stack);
/* 121 */       MovingObjectPosition mop = getMovingObjectPositionFromPlayer(player.field_70170_p, (EntityPlayer)player);
/* 122 */       if ((mop != null) && (mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) && 
/* 123 */         (pb != null) && (player.field_70170_p.func_175625_s(mop.func_178782_a()) == null) && (player.field_70170_p.func_180495_p(mop.func_178782_a()).func_177230_c().func_149688_o() != ThaumcraftMaterials.MATERIAL_TAINT))
/*     */       {
/*     */ 
/* 126 */         ServerTickEventsFML.addSwapper(player.field_70170_p, mop.func_178782_a(), player.field_70170_p.func_180495_p(mop.func_178782_a()), pb, 0, (EntityPlayer)player, ((EntityPlayer)player).field_71071_by.field_70461_c);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 133 */     return super.onEntitySwing(player, stack);
/*     */   }
/*     */   
/*     */   public void storePickedBlock(ItemStack stack, ItemStack stackout)
/*     */   {
/* 138 */     NBTTagCompound item = new NBTTagCompound();
/* 139 */     stack.func_77983_a("picked", stackout.func_77955_b(item));
/*     */   }
/*     */   
/*     */ 
/*     */   public ItemStack getPickedBlock(ItemStack stack)
/*     */   {
/* 145 */     ItemStack out = null;
/* 146 */     if ((stack.func_77942_o()) && (stack.func_77978_p().func_74764_b("picked"))) {
/* 147 */       out = new ItemStack(Blocks.field_150350_a);
/* 148 */       out.func_77963_c(stack.func_77978_p().func_74775_l("picked"));
/*     */     }
/* 150 */     return out;
/*     */   }
/*     */   
/* 153 */   private static final AspectList cost = new AspectList().add(Aspect.ENTROPY, 1).add(Aspect.EARTH, 1).add(Aspect.ORDER, 1);
/*     */   
/*     */   public AspectList getVisCost(ItemStack itemstack)
/*     */   {
/* 157 */     if (isUpgradedWith(itemstack, FocusUpgradeType.silktouch)) {
/* 158 */       AspectList cost2 = new AspectList().add(Aspect.AIR, 1).add(Aspect.FIRE, 1).add(Aspect.EARTH, 1).add(Aspect.WATER, 1).add(Aspect.ORDER, 1);
/* 159 */       cost2.add(cost);
/* 160 */       return cost2;
/*     */     }
/* 162 */     return cost;
/*     */   }
/*     */   
/*     */ 
/*     */   public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack itemstack, int rank)
/*     */   {
/* 168 */     switch (rank) {
/* 169 */     case 1:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.enlarge };
/* 170 */     case 2:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.enlarge };
/* 171 */     case 3:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.enlarge, FocusUpgradeType.treasure, FocusUpgradeType.architect };
/* 172 */     case 4:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.enlarge };
/* 173 */     case 5:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.enlarge, FocusUpgradeType.silktouch };
/*     */     }
/* 175 */     return null;
/*     */   }
/*     */   
/*     */   public int getMaxAreaSize(ItemStack focusstack)
/*     */   {
/* 180 */     return 3 + getUpgradeLevel(focusstack, FocusUpgradeType.enlarge) * 2;
/*     */   }
/*     */   
/* 183 */   ArrayList<BlockPos> checked = new ArrayList();
/*     */   
/*     */   public ArrayList<BlockPos> getArchitectBlocks(ItemStack stack, World world, BlockPos pos, EnumFacing side, EntityPlayer player)
/*     */   {
/* 187 */     IWand wand = (IWand)stack.func_77973_b();
/* 188 */     ItemFocusBasic focus = wand.getFocus(stack);
/*     */     
/* 190 */     if (!focus.isUpgradedWith(wand.getFocusStack(stack), FocusUpgradeType.architect)) { return null;
/*     */     }
/* 192 */     IBlockState bi = world.func_180495_p(pos);
/*     */     
/* 194 */     ArrayList<BlockPos> out = new ArrayList();
/* 195 */     this.checked.clear();
/* 196 */     if (side.func_176740_k() == net.minecraft.util.EnumFacing.Axis.Z) {
/* 197 */       checkNeighbours(world, pos, bi, new BlockPos(pos), side, WandManager.getAreaZ(stack), WandManager.getAreaY(stack), WandManager.getAreaX(stack), out, player);
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/* 202 */       checkNeighbours(world, pos, bi, new BlockPos(pos), side, WandManager.getAreaX(stack), WandManager.getAreaY(stack), WandManager.getAreaZ(stack), out, player);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 207 */     return out;
/*     */   }
/*     */   
/*     */   public void checkNeighbours(World world, BlockPos pos1, IBlockState bi, BlockPos pos2, EnumFacing side, int sizeX, int sizeY, int sizeZ, ArrayList<BlockPos> list, EntityPlayer player)
/*     */   {
/* 212 */     if (this.checked.contains(pos2)) return;
/* 213 */     this.checked.add(pos2);
/* 214 */     switch (side.func_176740_k()) {
/*     */     case Y: 
/* 216 */       if (Math.abs(pos2.func_177958_n() - pos1.func_177958_n()) > sizeX) return;
/* 217 */       if (Math.abs(pos2.func_177952_p() - pos1.func_177952_p()) > sizeZ)
/*     */         return;
/*     */       break;
/* 220 */     case Z:  if (Math.abs(pos2.func_177958_n() - pos1.func_177958_n()) > sizeX) return;
/* 221 */       if (Math.abs(pos2.func_177956_o() - pos1.func_177956_o()) > sizeZ)
/*     */         return;
/*     */       break;
/* 224 */     case X:  if (Math.abs(pos2.func_177956_o() - pos1.func_177956_o()) > sizeX) return;
/* 225 */       if (Math.abs(pos2.func_177952_p() - pos1.func_177952_p()) > sizeZ)
/*     */         return;
/*     */       break;
/*     */     }
/* 229 */     if ((world.func_180495_p(pos2) == bi) && (BlockUtils.isBlockExposed(world, pos2)) && (!world.func_175623_d(pos2)) && (world.func_180495_p(pos2).func_177230_c().func_176195_g(world, pos2) >= 0.0F) && (world.canMineBlockBody(player, pos2)))
/*     */     {
/*     */ 
/*     */ 
/* 233 */       list.add(pos2);
/*     */     } else {
/* 235 */       return;
/*     */     }
/* 237 */     for (EnumFacing dir : EnumFacing.values()) {
/* 238 */       if ((dir != side) && (dir.func_176734_d() != side)) {
/* 239 */         checkNeighbours(world, pos1, bi, pos2.func_177972_a(dir), side, sizeX, sizeY, sizeZ, list, player);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean showAxis(ItemStack stack, World world, EntityPlayer player, EnumFacing side, IArchitect.EnumAxis axis) {
/* 245 */     int dim = WandManager.getAreaDim(stack);
/* 246 */     switch (side.func_176740_k()) {
/*     */     case Y: 
/* 248 */       if (((axis == IArchitect.EnumAxis.X) && ((dim == 0) || (dim == 1))) || ((axis == IArchitect.EnumAxis.Z) && ((dim == 0) || (dim == 2)))) return true;
/*     */       break;
/*     */     case Z: 
/* 251 */       if (((axis == IArchitect.EnumAxis.Y) && ((dim == 0) || (dim == 1))) || ((axis == IArchitect.EnumAxis.X) && ((dim == 0) || (dim == 2)))) return true;
/*     */       break;
/*     */     case X: 
/* 254 */       if (((axis == IArchitect.EnumAxis.Y) && ((dim == 0) || (dim == 1))) || ((axis == IArchitect.EnumAxis.Z) && ((dim == 0) || (dim == 2)))) return true;
/*     */       break;
/*     */     }
/* 257 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/items/wands/foci/ItemFocusTrade.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */