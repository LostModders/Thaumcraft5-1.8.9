/*     */ package thaumcraft.common.items.tools;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.settings.KeyBinding;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectHelper;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.research.ScanningManager;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.lib.aura.AuraChunk;
/*     */ import thaumcraft.common.lib.aura.AuraHandler;
/*     */ import thaumcraft.common.lib.network.misc.PacketAuraToClient;
/*     */ import thaumcraft.common.lib.utils.EntityUtils;
/*     */ 
/*     */ public class ItemThaumometer extends Item implements thaumcraft.common.items.ISubItems
/*     */ {
/*     */   public ItemThaumometer()
/*     */   {
/*  41 */     func_77625_d(1);
/*  42 */     setNoRepair();
/*  43 */     func_77627_a(true);
/*  44 */     func_77637_a(Thaumcraft.tabTC);
/*     */   }
/*     */   
/*     */   public int[] getSubItems()
/*     */   {
/*  49 */     return new int[] { 0, 1, 2, 3, 4, 5, 6, 7 };
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*     */   {
/*  55 */     par3List.add(new ItemStack(this, 1, 0));
/*     */   }
/*     */   
/*     */ 
/*     */   public EnumRarity func_77613_e(ItemStack itemstack)
/*     */   {
/*  61 */     return EnumRarity.UNCOMMON;
/*     */   }
/*     */   
/*     */   public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean par4)
/*     */   {
/*  66 */     super.func_77624_a(stack, player, list, par4);
/*  67 */     String text = StatCollector.func_74838_a("tc.thaumometer");
/*     */     try {
/*  69 */       text = text.replace("$s", org.lwjgl.input.Keyboard.getKeyName(Thaumcraft.proxy.getKeyBindings().keyF.func_151463_i()));
/*  70 */       list.add(EnumChatFormatting.DARK_PURPLE + text);
/*     */     }
/*     */     catch (Exception e) {}
/*     */   }
/*     */   
/*     */   public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer p)
/*     */   {
/*  77 */     if (world.field_72995_K) {
/*  78 */       drawFX(world, p);
/*  79 */       p.field_70170_p.func_72980_b(p.field_70165_t, p.field_70163_u, p.field_70161_v, "thaumcraft:scan", 0.5F, 1.0F, false);
/*     */     } else {
/*  81 */       doScan(world, p);
/*     */     }
/*  83 */     return stack;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_77663_a(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected)
/*     */   {
/*  89 */     if ((isSelected) && (!world.field_72995_K) && (entity.field_70173_aa % 20 == 0) && ((entity instanceof EntityPlayerMP))) {
/*  90 */       updateAura(stack, world, entity);
/*     */     }
/*     */     
/*  93 */     if ((isSelected) && (world.field_72995_K) && (entity.field_70173_aa % 5 == 0) && ((entity instanceof EntityPlayer)))
/*     */     {
/*  95 */       Entity target = EntityUtils.getPointedEntity(world, entity, 1.0D, 16.0D, 5.0F, true);
/*  96 */       if ((target != null) && (ScanningManager.isThingStillScannable((EntityPlayer)entity, target))) {
/*  97 */         for (int a = 0; a < Thaumcraft.proxy.getFX().particleCount(2); a++) {
/*  98 */           Thaumcraft.proxy.getFX().scanHighlight((float)target.field_70165_t + (world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * (target.field_70130_N * 0.7F), (float)target.field_70163_u + world.field_73012_v.nextFloat() * target.field_70131_O, (float)target.field_70161_v + (world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * (target.field_70130_N * 0.7F));
/*     */         }
/*     */       }
/*     */       else
/*     */       {
/* 103 */         MovingObjectPosition mop = getMovingObjectPositionFromPlayerWild(world, (EntityPlayer)entity, true);
/* 104 */         if ((mop != null) && (mop.func_178782_a() != null) && (ScanningManager.isThingStillScannable((EntityPlayer)entity, mop.func_178782_a())))
/*     */         {
/* 106 */           for (int a = 0; a < Thaumcraft.proxy.getFX().particleCount(2); a++) {
/* 107 */             Thaumcraft.proxy.getFX().scanHighlight(mop.func_178782_a().func_177958_n() - 0.3F + world.field_73012_v.nextFloat() * 1.6F, mop.func_178782_a().func_177956_o() - 0.3F + world.field_73012_v.nextFloat() * 1.6F, mop.func_178782_a().func_177952_p() - 0.3F + world.field_73012_v.nextFloat() * 1.6F);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected MovingObjectPosition getMovingObjectPositionFromPlayerWild(World worldIn, EntityPlayer playerIn, boolean useLiquids)
/*     */   {
/* 120 */     float f = playerIn.field_70127_C + (playerIn.field_70125_A - playerIn.field_70127_C) + worldIn.field_73012_v.nextInt(20) - worldIn.field_73012_v.nextInt(20);
/* 121 */     float f1 = playerIn.field_70126_B + (playerIn.field_70177_z - playerIn.field_70126_B) + worldIn.field_73012_v.nextInt(20) - worldIn.field_73012_v.nextInt(20);
/* 122 */     double d0 = playerIn.field_70169_q + (playerIn.field_70165_t - playerIn.field_70169_q);
/* 123 */     double d1 = playerIn.field_70167_r + (playerIn.field_70163_u - playerIn.field_70167_r) + playerIn.func_70047_e();
/* 124 */     double d2 = playerIn.field_70166_s + (playerIn.field_70161_v - playerIn.field_70166_s);
/* 125 */     Vec3 vec3 = new Vec3(d0, d1, d2);
/* 126 */     float f2 = MathHelper.func_76134_b(-f1 * 0.017453292F - 3.1415927F);
/* 127 */     float f3 = MathHelper.func_76126_a(-f1 * 0.017453292F - 3.1415927F);
/* 128 */     float f4 = -MathHelper.func_76134_b(-f * 0.017453292F);
/* 129 */     float f5 = MathHelper.func_76126_a(-f * 0.017453292F);
/* 130 */     float f6 = f3 * f4;
/* 131 */     float f7 = f2 * f4;
/* 132 */     double d3 = 16.0D;
/* 133 */     Vec3 vec31 = vec3.func_72441_c(f6 * d3, f5 * d3, f7 * d3);
/* 134 */     return worldIn.func_147447_a(vec3, vec31, useLiquids, !useLiquids, false);
/*     */   }
/*     */   
/*     */   private void updateAura(ItemStack stack, World world, Entity entity) {
/* 138 */     AuraChunk ac = AuraHandler.getAuraChunk(world.field_73011_w.func_177502_q(), (int)entity.field_70165_t >> 4, (int)entity.field_70161_v >> 4);
/*     */     
/* 140 */     if (ac != null) {
/* 141 */       short b = ac.getBase();
/* 142 */       AspectList c = ac.getCurrentAspects();
/* 143 */       AspectList r = new AspectList();
/* 144 */       for (Aspect aspect : AspectHelper.getAuraAspects(ac.getCurrentAspects()).getAspects()) {
/* 145 */         if (getAspect(stack.func_77952_i()) != null) {
/* 146 */           r.add(Aspect.CRYSTAL, b);
/* 147 */           if (getAspect(stack.func_77952_i()) != aspect) {
/*     */             continue;
/*     */           }
/* 150 */           r.add(Aspect.VOID, (int)(b * 1.1F));
/*     */         }
/*     */         else {
/* 153 */           r.add(Aspect.VOID, c.getAmount(aspect));
/*     */         }
/* 155 */         r.add(aspect, c.getAmount(aspect));
/*     */       }
/*     */       
/* 158 */       thaumcraft.common.lib.network.PacketHandler.INSTANCE.sendTo(new PacketAuraToClient(r), (EntityPlayerMP)entity);
/*     */     }
/*     */   }
/*     */   
/*     */   private void drawFX(World worldIn, EntityPlayer playerIn) {
/* 163 */     Entity target = EntityUtils.getPointedEntity(worldIn, playerIn, 1.0D, 9.0D, 0.0F, true);
/* 164 */     if (target != null) {
/* 165 */       for (int a = 0; a < Thaumcraft.proxy.getFX().particleCount(5); a++) {
/* 166 */         Thaumcraft.proxy.getFX().blockRunes(target.field_70165_t - 0.5D, target.field_70163_u + target.func_70047_e() / 2.0F, target.field_70161_v - 0.5D, 0.3F + worldIn.field_73012_v.nextFloat() * 0.7F, 0.0F, 0.3F + worldIn.field_73012_v.nextFloat() * 0.7F, (int)(target.field_70131_O * 15.0F), 0.03F);
/*     */       }
/*     */       
/*     */     }
/*     */     else
/*     */     {
/* 172 */       MovingObjectPosition mop = func_77621_a(worldIn, playerIn, true);
/* 173 */       if ((mop != null) && (mop.func_178782_a() != null)) {
/* 174 */         for (int a = 0; a < Thaumcraft.proxy.getFX().particleCount(5); a++) {
/* 175 */           Thaumcraft.proxy.getFX().blockRunes(mop.func_178782_a().func_177958_n(), mop.func_178782_a().func_177956_o() + 0.25D, mop.func_178782_a().func_177952_p(), 0.3F + worldIn.field_73012_v.nextFloat() * 0.7F, 0.0F, 0.3F + worldIn.field_73012_v.nextFloat() * 0.7F, 15, 0.03F);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private Aspect getAspect(int b)
/*     */   {
/* 183 */     switch (b) {
/* 184 */     default:  return null;
/* 185 */     case 1:  return Aspect.AIR;
/* 186 */     case 2:  return Aspect.FIRE;
/* 187 */     case 3:  return Aspect.WATER;
/* 188 */     case 4:  return Aspect.EARTH;
/* 189 */     case 5:  return Aspect.ORDER;
/* 190 */     case 6:  return Aspect.ENTROPY; }
/* 191 */     return Aspect.FLUX;
/*     */   }
/*     */   
/*     */   public void doScan(World worldIn, EntityPlayer playerIn)
/*     */   {
/* 196 */     if (!worldIn.field_72995_K) {
/* 197 */       Entity target = EntityUtils.getPointedEntity(worldIn, playerIn, 1.0D, 9.0D, 0.0F, true);
/* 198 */       if (target != null) {
/* 199 */         ScanningManager.scanTheThing(playerIn, target);
/*     */       } else {
/* 201 */         MovingObjectPosition mop = func_77621_a(worldIn, playerIn, true);
/* 202 */         if ((mop != null) && (mop.func_178782_a() != null)) {
/* 203 */           ScanningManager.scanTheThing(playerIn, mop.func_178782_a());
/*     */         } else {
/* 205 */           ScanningManager.scanTheThing(playerIn, null);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static void changeVis(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
/* 212 */     int d = itemStackIn.func_77952_i() + 1;
/* 213 */     if (d > 7) d = 0;
/* 214 */     itemStackIn.func_77964_b(d);
/* 215 */     if (!worldIn.field_72995_K) {
/* 216 */       if (d > 0) {
/* 217 */         playerIn.func_145747_a(new ChatComponentText(String.format(StatCollector.func_74838_a("tc.dioptra.1"), new Object[] { ((ItemThaumometer)itemStackIn.func_77973_b()).getAspect(d).getName() })));
/*     */       }
/*     */       else {
/* 220 */         playerIn.func_145747_a(new ChatComponentText(StatCollector.func_74838_a("tc.dioptra.3")));
/*     */       }
/* 222 */       ((ItemThaumometer)itemStackIn.func_77973_b()).updateAura(itemStackIn, worldIn, playerIn);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/items/tools/ItemThaumometer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */