/*     */ package thaumcraft.common.blocks.devices;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.resources.model.ModelResourceLocation;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.aspects.IEssentiaContainerItem;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.common.blocks.BlockTC;
/*     */ import thaumcraft.common.blocks.ItemBlockTC;
/*     */ import thaumcraft.common.tiles.crafting.TileAlembic;
/*     */ import thaumcraft.common.tiles.devices.TileJarBrain;
/*     */ import thaumcraft.common.tiles.essentia.TileJarFillable;
/*     */ 
/*     */ public class BlockJarItem
/*     */   extends ItemBlockTC implements IEssentiaContainerItem
/*     */ {
/*     */   public BlockJarItem(Block block)
/*     */   {
/*  34 */     super(block);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean onItemUseFirst(ItemStack itemstack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float f1, float f2, float f3)
/*     */   {
/*  40 */     Block bi = world.func_180495_p(pos).func_177230_c();
/*     */     
/*     */ 
/*  43 */     if ((bi == BlocksTC.alembic) && (!world.field_72995_K)) {
/*  44 */       TileAlembic tile = (TileAlembic)world.func_175625_s(pos);
/*  45 */       if (tile.amount > 0) {
/*  46 */         if ((getFilter(itemstack) != null) && (getFilter(itemstack) != tile.aspect)) return false;
/*  47 */         if ((getAspects(itemstack) != null) && (getAspects(itemstack).getAspects()[0] != tile.aspect)) { return false;
/*     */         }
/*     */         
/*  50 */         int amt = tile.amount;
/*  51 */         if ((getAspects(itemstack) != null) && (getAspects(itemstack).visSize() + amt > 64)) amt = Math.abs(getAspects(itemstack).visSize() - 64);
/*  52 */         if (amt <= 0) return false;
/*  53 */         Aspect a = tile.aspect;
/*  54 */         if (tile.takeFromContainer(tile.aspect, amt)) {
/*  55 */           int base = getAspects(itemstack) == null ? 0 : getAspects(itemstack).visSize();
/*  56 */           if (itemstack.field_77994_a > 1) {
/*  57 */             ItemStack stack = itemstack.func_77946_l();
/*  58 */             setAspects(stack, new AspectList().add(a, base + amt));
/*  59 */             itemstack.field_77994_a -= 1;
/*  60 */             stack.field_77994_a = 1;
/*  61 */             if (!player.field_71071_by.func_70441_a(stack)) {
/*  62 */               world.func_72838_d(new EntityItem(world, player.field_70165_t, player.field_70163_u, player.field_70161_v, stack));
/*     */             }
/*     */           } else {
/*  65 */             setAspects(itemstack, new AspectList().add(a, base + amt));
/*     */           }
/*     */           
/*  68 */           world.func_72956_a(player, "game.neutral.swim", 0.25F, 1.0F);
/*  69 */           player.field_71069_bz.func_75142_b();
/*  70 */           return true;
/*     */         }
/*     */       }
/*     */     }
/*  74 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, IBlockState newState)
/*     */   {
/*  82 */     boolean b = super.placeBlockAt(stack, player, world, pos, side, hitX, hitY, hitZ, newState);
/*     */     
/*  84 */     if ((b) && (!world.field_72995_K)) {
/*  85 */       TileEntity te = world.func_175625_s(pos);
/*  86 */       if ((te != null) && ((te instanceof TileJarFillable))) {
/*  87 */         TileJarFillable jar = (TileJarFillable)te;
/*  88 */         jar.setAspects(getAspects(stack));
/*  89 */         if ((stack.func_77942_o()) && (stack.func_77978_p().func_74764_b("AspectFilter"))) {
/*  90 */           jar.aspectFilter = Aspect.getAspect(stack.func_77978_p().func_74779_i("AspectFilter"));
/*     */         }
/*  92 */         te.func_70296_d();
/*  93 */         world.func_175689_h(pos);
/*     */       }
/*  95 */       if ((te != null) && ((te instanceof TileJarBrain))) {
/*  96 */         TileJarBrain jar = (TileJarBrain)te;
/*  97 */         if (stack.func_77942_o()) {
/*  98 */           jar.xp = stack.func_77978_p().func_74762_e("xp");
/*     */         }
/* 100 */         te.func_70296_d();
/* 101 */         world.func_175689_h(pos);
/*     */       }
/*     */     }
/*     */     
/* 105 */     return b;
/*     */   }
/*     */   
/*     */   public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean par4)
/*     */   {
/* 110 */     if ((stack.func_77942_o()) && (stack.func_77978_p().func_74764_b("AspectFilter"))) {
/* 111 */       String tf = stack.func_77978_p().func_74779_i("AspectFilter");
/* 112 */       Aspect tag = Aspect.getAspect(tf);
/* 113 */       list.add("§5" + tag.getName());
/*     */     }
/* 115 */     if ((stack.func_77942_o()) && (stack.func_77978_p().func_74764_b("xp"))) {
/* 116 */       int tf = stack.func_77978_p().func_74762_e("xp");
/* 117 */       list.add("§5" + tf + " xp");
/*     */     }
/* 119 */     super.func_77624_a(stack, player, list, par4);
/*     */   }
/*     */   
/*     */   public AspectList getAspects(ItemStack itemstack)
/*     */   {
/* 124 */     if (itemstack.func_77942_o()) {
/* 125 */       AspectList aspects = new AspectList();
/* 126 */       aspects.readFromNBT(itemstack.func_77978_p());
/* 127 */       return aspects.size() > 0 ? aspects : null;
/*     */     }
/* 129 */     return null;
/*     */   }
/*     */   
/*     */   public Aspect getFilter(ItemStack itemstack) {
/* 133 */     if (itemstack.func_77942_o()) {
/* 134 */       return Aspect.getAspect(itemstack.func_77978_p().func_74779_i("AspectFilter"));
/*     */     }
/* 136 */     return null;
/*     */   }
/*     */   
/*     */   public void setAspects(ItemStack itemstack, AspectList aspects)
/*     */   {
/* 141 */     if (!itemstack.func_77942_o()) itemstack.func_77982_d(new NBTTagCompound());
/* 142 */     aspects.writeToNBT(itemstack.func_77978_p());
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_82790_a(ItemStack stack, int layer)
/*     */   {
/* 149 */     if (getAspects(stack) != null) {
/* 150 */       return getAspects(stack).getAspects()[0].getColor();
/*     */     }
/*     */     
/* 153 */     return super.func_82790_a(stack, layer);
/*     */   }
/*     */   
/*     */ 
/*     */   public ModelResourceLocation getModel(ItemStack stack, EntityPlayer player, int useRemaining)
/*     */   {
/* 159 */     BlockTC block = (BlockTC)this.field_150939_a;
/* 160 */     ModelResourceLocation modelresourcelocation = null;
/*     */     
/* 162 */     if ((getAspects(stack) != null) && (getAspects(stack).visSize() > 0))
/*     */     {
/*     */ 
/* 165 */       if (getAspects(stack).visSize() < 16)
/*     */       {
/* 167 */         modelresourcelocation = new ModelResourceLocation("Thaumcraft:" + block.getStateName(block.func_176203_a(stack.func_77960_j()), false) + "_0", "inventory");
/*     */ 
/*     */       }
/* 170 */       else if (getAspects(stack).visSize() < 32)
/*     */       {
/* 172 */         modelresourcelocation = new ModelResourceLocation("Thaumcraft:" + block.getStateName(block.func_176203_a(stack.func_77960_j()), false) + "_1", "inventory");
/*     */ 
/*     */       }
/* 175 */       else if (getAspects(stack).visSize() < 48)
/*     */       {
/* 177 */         modelresourcelocation = new ModelResourceLocation("Thaumcraft:" + block.getStateName(block.func_176203_a(stack.func_77960_j()), false) + "_2", "inventory");
/*     */ 
/*     */       }
/*     */       else
/*     */       {
/* 182 */         modelresourcelocation = new ModelResourceLocation("Thaumcraft:" + block.getStateName(block.func_176203_a(stack.func_77960_j()), false) + "_3", "inventory");
/*     */       }
/*     */     }
/*     */     
/* 186 */     return modelresourcelocation;
/*     */   }
/*     */   
/*     */   public boolean ignoreContainedAspects()
/*     */   {
/* 191 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/devices/BlockJarItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */