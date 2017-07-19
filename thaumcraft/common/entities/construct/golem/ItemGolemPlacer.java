/*     */ package thaumcraft.common.entities.construct.golem;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagLong;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.golems.EnumGolemTrait;
/*     */ import thaumcraft.api.golems.IGolemProperties;
/*     */ import thaumcraft.api.golems.ISealDisplayer;
/*     */ import thaumcraft.api.golems.parts.GolemMaterial;
/*     */ 
/*     */ public class ItemGolemPlacer extends Item implements ISealDisplayer
/*     */ {
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*     */   {
/*  31 */     ItemStack is = new ItemStack(this, 1, 0);
/*  32 */     is.func_77983_a("props", new NBTTagLong(0L));
/*  33 */     par3List.add(is.func_77946_l());
/*  34 */     IGolemProperties props = new GolemProperties();
/*  35 */     props.setHead(thaumcraft.api.golems.parts.GolemHead.getHeads()[1]);
/*  36 */     props.setArms(thaumcraft.api.golems.parts.GolemArm.getArms()[1]);
/*  37 */     is.func_77983_a("props", new NBTTagLong(props.toLong()));
/*  38 */     par3List.add(is.func_77946_l());
/*     */     
/*  40 */     props = new GolemProperties();
/*  41 */     props.setMaterial(GolemMaterial.getMaterials()[1]);
/*  42 */     props.setHead(thaumcraft.api.golems.parts.GolemHead.getHeads()[1]);
/*  43 */     props.setArms(thaumcraft.api.golems.parts.GolemArm.getArms()[2]);
/*  44 */     is.func_77983_a("props", new NBTTagLong(props.toLong()));
/*  45 */     par3List.add(is.func_77946_l());
/*     */     
/*  47 */     props = new GolemProperties();
/*  48 */     props.setMaterial(GolemMaterial.getMaterials()[4]);
/*  49 */     props.setHead(thaumcraft.api.golems.parts.GolemHead.getHeads()[1]);
/*  50 */     props.setArms(thaumcraft.api.golems.parts.GolemArm.getArms()[3]);
/*  51 */     is.func_77983_a("props", new NBTTagLong(props.toLong()));
/*  52 */     par3List.add(is.func_77946_l());
/*     */   }
/*     */   
/*     */   public int func_82790_a(ItemStack stack, int renderPass)
/*     */   {
/*  57 */     if ((stack.func_77942_o()) && (stack.func_77978_p().func_74764_b("props"))) {
/*  58 */       IGolemProperties props = GolemProperties.fromLong(stack.func_77978_p().func_74763_f("props"));
/*  59 */       return props.getMaterial().itemColor;
/*     */     }
/*  61 */     return super.func_82790_a(stack, renderPass);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean par4)
/*     */   {
/*  67 */     if ((stack.func_77942_o()) && (stack.func_77978_p().func_74764_b("props"))) {
/*  68 */       IGolemProperties props = GolemProperties.fromLong(stack.func_77978_p().func_74763_f("props"));
/*     */       
/*  70 */       if (props.hasTrait(EnumGolemTrait.SMART)) {
/*  71 */         if (props.getRank() >= 10) {
/*  72 */           list.add("§6" + StatCollector.func_74838_a("golem.rank") + " " + props.getRank());
/*     */         } else {
/*  74 */           int rx = stack.func_77978_p().func_74762_e("xp");
/*  75 */           int xn = (props.getRank() + 1) * (props.getRank() + 1) * 1000;
/*  76 */           list.add("§6" + StatCollector.func_74838_a("golem.rank") + " " + props.getRank() + " §2(" + rx + "/" + xn + ")");
/*     */         }
/*     */       }
/*     */       
/*  80 */       list.add("§a" + props.getMaterial().getLocalizedName());
/*     */       
/*  82 */       for (EnumGolemTrait tag : props.getTraits()) {
/*  83 */         list.add("§9-" + tag.getLocalizedName());
/*     */       }
/*     */     }
/*  86 */     super.func_77624_a(stack, player, list, par4);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
/*     */   {
/*  94 */     IBlockState bs = world.func_180495_p(pos);
/*     */     
/*  96 */     if (!bs.func_177230_c().func_149688_o().func_76220_a()) return false;
/*  97 */     if (world.field_72995_K) { return false;
/*     */     }
/*  99 */     pos = pos.func_177972_a(side);
/* 100 */     bs = world.func_180495_p(pos);
/*     */     
/* 102 */     if (!player.func_175151_a(pos, side, stack)) { return false;
/*     */     }
/* 104 */     EntityThaumcraftGolem golem = new EntityThaumcraftGolem(world);
/* 105 */     golem.func_70080_a(pos.func_177958_n() + 0.5D, pos.func_177956_o(), pos.func_177952_p() + 0.5D, 0.0F, 0.0F);
/* 106 */     if ((golem != null) && (world.func_72838_d(golem))) {
/* 107 */       golem.setOwned(true);
/* 108 */       golem.setValidSpawn();
/* 109 */       golem.setOwnerId(player.func_110124_au().toString());
/* 110 */       if ((stack.func_77942_o()) && (stack.func_77978_p().func_74764_b("props"))) {
/* 111 */         golem.setProperties(GolemProperties.fromLong(stack.func_77978_p().func_74763_f("props")));
/*     */       }
/* 113 */       if ((stack.func_77942_o()) && (stack.func_77978_p().func_74764_b("xp"))) {
/* 114 */         golem.rankXp = stack.func_77978_p().func_74762_e("xp");
/*     */       }
/* 116 */       golem.func_180482_a(world.func_175649_E(pos), (IEntityLivingData)null);
/* 117 */       if (!player.field_71075_bZ.field_75098_d) { stack.field_77994_a -= 1;
/*     */       }
/*     */     }
/*     */     
/* 121 */     return true;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/construct/golem/ItemGolemPlacer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */