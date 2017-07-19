/*     */ package thaumcraft.common.entities.construct.golem.seals;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.entity.monster.IMob;
/*     */ import net.minecraft.entity.passive.EntityAnimal;
/*     */ import net.minecraft.entity.passive.IAnimals;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.golems.EnumGolemTrait;
/*     */ import thaumcraft.api.golems.GolemHelper;
/*     */ import thaumcraft.api.golems.IGolemAPI;
/*     */ import thaumcraft.api.golems.seals.ISeal;
/*     */ import thaumcraft.api.golems.seals.ISealConfigArea;
/*     */ import thaumcraft.api.golems.seals.ISealConfigToggles.SealToggle;
/*     */ import thaumcraft.api.golems.seals.ISealEntity;
/*     */ import thaumcraft.api.golems.seals.ISealGui;
/*     */ import thaumcraft.api.golems.tasks.Task;
/*     */ import thaumcraft.common.entities.construct.golem.gui.SealBaseContainer;
/*     */ import thaumcraft.common.entities.construct.golem.gui.SealBaseGUI;
/*     */ import thaumcraft.common.entities.construct.golem.tasks.TaskHandler;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SealGuard
/*     */   implements ISeal, ISealGui, ISealConfigArea
/*     */ {
/*     */   public String getKey()
/*     */   {
/*  44 */     return "Thaumcraft:guard";
/*     */   }
/*     */   
/*  47 */   int delay = new Random(System.nanoTime()).nextInt(22);
/*     */   
/*     */   public void tickSeal(World world, ISealEntity seal)
/*     */   {
/*  51 */     if (this.delay++ % 20 != 0) { return;
/*     */     }
/*  53 */     AxisAlignedBB area = GolemHelper.getBoundsForArea(seal);
/*     */     
/*  55 */     List list = world.func_72872_a(EntityLivingBase.class, area);
/*  56 */     if (list.size() > 0) {
/*  57 */       for (Object e : list) {
/*  58 */         EntityLivingBase target = (EntityLivingBase)e;
/*     */         
/*  60 */         if (isValidTarget(target)) {
/*  61 */           Task task = new Task(seal.getSealPos(), target);
/*  62 */           task.setPriority(seal.getPriority());
/*  63 */           task.setLifespan((short)10);
/*  64 */           TaskHandler.addTask(world.field_73011_w.func_177502_q(), task);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*  70 */   protected ISealConfigToggles.SealToggle[] props = { new ISealConfigToggles.SealToggle(true, "pmob", "golem.prop.mob"), new ISealConfigToggles.SealToggle(false, "panimal", "golem.prop.animal"), new ISealConfigToggles.SealToggle(false, "pplayer", "golem.prop.player") };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean isValidTarget(EntityLivingBase target)
/*     */   {
/*  77 */     boolean valid = false;
/*  78 */     if ((this.props[0].value) && (((target instanceof IMob)) || ((target instanceof EntityMob)))) {
/*  79 */       valid = true;
/*     */     }
/*     */     
/*  82 */     if ((this.props[1].value) && (((target instanceof EntityAnimal)) || ((target instanceof IAnimals)))) {
/*  83 */       valid = true;
/*     */     }
/*     */     
/*  86 */     if ((this.props[2].value) && (MinecraftServer.func_71276_C().func_71219_W()) && ((target instanceof EntityPlayer))) {
/*  87 */       valid = true;
/*     */     }
/*     */     
/*  90 */     return valid;
/*     */   }
/*     */   
/*     */ 
/*     */   public void onTaskStarted(World world, IGolemAPI golem, Task task)
/*     */   {
/*  96 */     if ((task.getEntity() != null) && ((task.getEntity() instanceof EntityLivingBase)) && (isValidTarget((EntityLivingBase)task.getEntity()))) {
/*  97 */       ((EntityLiving)golem).func_70624_b((EntityLivingBase)task.getEntity());
/*  98 */       golem.addRankXp(1);
/*     */     }
/*     */     
/* 101 */     task.setSuspended(true);
/*     */   }
/*     */   
/*     */   public boolean onTaskCompletion(World world, IGolemAPI golem, Task task)
/*     */   {
/* 106 */     task.setSuspended(true);
/* 107 */     return true;
/*     */   }
/*     */   
/*     */   public boolean canGolemPerformTask(IGolemAPI golem, Task task)
/*     */   {
/* 112 */     return !golem.getGolemEntity().func_142014_c((EntityLivingBase)task.getEntity());
/*     */   }
/*     */   
/*     */   public boolean canPlaceAt(World world, BlockPos pos, EnumFacing side)
/*     */   {
/* 117 */     return !world.func_175623_d(pos);
/*     */   }
/*     */   
/*     */   public ResourceLocation getSealIcon()
/*     */   {
/* 122 */     return this.icon;
/*     */   }
/*     */   
/* 125 */   ResourceLocation icon = new ResourceLocation("thaumcraft", "items/seals/seal_guard");
/*     */   
/*     */   public int[] getGuiCategories()
/*     */   {
/* 129 */     return new int[] { 2, 0, 4 };
/*     */   }
/*     */   
/*     */   public EnumGolemTrait[] getRequiredTags()
/*     */   {
/* 134 */     return new EnumGolemTrait[] { EnumGolemTrait.FIGHTER };
/*     */   }
/*     */   
/*     */   public EnumGolemTrait[] getForbiddenTags()
/*     */   {
/* 139 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void onTaskSuspension(World world, Task task) {}
/*     */   
/*     */ 
/*     */   public void readCustomNBT(NBTTagCompound nbt) {}
/*     */   
/*     */ 
/*     */   public void writeCustomNBT(NBTTagCompound nbt) {}
/*     */   
/*     */ 
/*     */   public void onRemoval(World world, BlockPos pos, EnumFacing side) {}
/*     */   
/*     */ 
/*     */   public Object returnContainer(World world, EntityPlayer player, BlockPos pos, EnumFacing side, ISealEntity seal)
/*     */   {
/* 158 */     return new SealBaseContainer(player.field_71071_by, world, seal);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public Object returnGui(World world, EntityPlayer player, BlockPos pos, EnumFacing side, ISealEntity seal)
/*     */   {
/* 164 */     return new SealBaseGUI(player.field_71071_by, world, seal);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/construct/golem/seals/SealGuard.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */