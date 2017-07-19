/*     */ package thaumcraft.common.entities.construct.golem.seals;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.monster.EntityGolem;
/*     */ import net.minecraft.entity.monster.IMob;
/*     */ import net.minecraft.entity.passive.EntityAnimal;
/*     */ import net.minecraft.entity.passive.EntityTameable;
/*     */ import net.minecraft.entity.passive.IAnimals;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.nbt.NBTTagCompound;
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
/*     */ import thaumcraft.api.golems.seals.ISealEntity;
/*     */ import thaumcraft.api.golems.seals.ISealGui;
/*     */ import thaumcraft.api.golems.tasks.Task;
/*     */ import thaumcraft.common.entities.construct.golem.gui.SealBaseContainer;
/*     */ import thaumcraft.common.entities.construct.golem.gui.SealBaseGUI;
/*     */ import thaumcraft.common.entities.construct.golem.tasks.TaskHandler;
/*     */ 
/*     */ public class SealButcher
/*     */   implements ISeal, ISealGui, ISealConfigArea
/*     */ {
/*     */   public String getKey()
/*     */   {
/*  40 */     return "Thaumcraft:butcher";
/*     */   }
/*     */   
/*  43 */   int delay = new Random(System.nanoTime()).nextInt(200);
/*  44 */   boolean wait = false;
/*     */   
/*     */   public void tickSeal(World world, ISealEntity seal)
/*     */   {
/*  48 */     if ((this.delay++ % 200 != 0) || (this.wait)) { return;
/*     */     }
/*  50 */     AxisAlignedBB area = GolemHelper.getBoundsForArea(seal);
/*     */     
/*  52 */     List list = world.func_72872_a(EntityLivingBase.class, area);
/*  53 */     if (list.size() > 0) {
/*  54 */       for (Object e : list) {
/*  55 */         EntityLivingBase target = (EntityLivingBase)e;
/*     */         
/*  57 */         if (isValidTarget(target)) {
/*  58 */           List var55 = world.func_72872_a(target.getClass(), area);
/*  59 */           Iterator var22 = var55.iterator();
/*  60 */           int count = 0;
/*  61 */           while ((var22.hasNext()) && (count < 3))
/*     */           {
/*  63 */             EntityLivingBase var33 = (EntityLivingBase)var22.next();
/*  64 */             if (isValidTarget(var33)) { count++;
/*     */             }
/*     */           }
/*  67 */           if (count > 2) {
/*  68 */             Task task = new Task(seal.getSealPos(), target);
/*  69 */             task.setPriority(seal.getPriority());
/*  70 */             task.setLifespan((short)10);
/*  71 */             TaskHandler.addTask(world.field_73011_w.func_177502_q(), task);
/*     */             
/*  73 */             this.wait = true;
/*  74 */             break;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean isValidTarget(EntityLivingBase target)
/*     */   {
/*  83 */     if ((((target instanceof EntityAnimal)) || ((target instanceof IAnimals))) && (!(target instanceof IMob)) && ((!(target instanceof EntityTameable)) || (!((EntityTameable)target).func_70909_n())) && (!(target instanceof EntityGolem)))
/*     */     {
/*     */ 
/*     */ 
/*  87 */       if (((target instanceof EntityAnimal)) && (((EntityAnimal)target).func_70631_g_())) return false;
/*  88 */       return true;
/*     */     }
/*  90 */     return false;
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
/* 102 */     this.wait = false;
/*     */   }
/*     */   
/*     */   public boolean onTaskCompletion(World world, IGolemAPI golem, Task task)
/*     */   {
/* 107 */     task.setSuspended(true);
/* 108 */     this.wait = false;
/* 109 */     return true;
/*     */   }
/*     */   
/*     */   public boolean canGolemPerformTask(IGolemAPI golem, Task task)
/*     */   {
/* 114 */     return true;
/*     */   }
/*     */   
/*     */   public boolean canPlaceAt(World world, BlockPos pos, EnumFacing side)
/*     */   {
/* 119 */     return !world.func_175623_d(pos);
/*     */   }
/*     */   
/*     */   public ResourceLocation getSealIcon()
/*     */   {
/* 124 */     return this.icon;
/*     */   }
/*     */   
/* 127 */   ResourceLocation icon = new ResourceLocation("thaumcraft", "items/seals/seal_butcher");
/*     */   
/*     */   public int[] getGuiCategories()
/*     */   {
/* 131 */     return new int[] { 2, 0, 4 };
/*     */   }
/*     */   
/*     */   public EnumGolemTrait[] getRequiredTags()
/*     */   {
/* 136 */     return new EnumGolemTrait[] { EnumGolemTrait.FIGHTER, EnumGolemTrait.SMART };
/*     */   }
/*     */   
/*     */   public EnumGolemTrait[] getForbiddenTags()
/*     */   {
/* 141 */     return null;
/*     */   }
/*     */   
/*     */   public void onTaskSuspension(World world, Task task)
/*     */   {
/* 146 */     this.wait = false;
/*     */   }
/*     */   
/*     */ 
/*     */   public void readCustomNBT(NBTTagCompound nbt) {}
/*     */   
/*     */ 
/*     */   public void writeCustomNBT(NBTTagCompound nbt) {}
/*     */   
/*     */   public void onRemoval(World world, BlockPos pos, EnumFacing side)
/*     */   {
/* 157 */     this.wait = false;
/*     */   }
/*     */   
/*     */   public Object returnContainer(World world, EntityPlayer player, BlockPos pos, EnumFacing side, ISealEntity seal)
/*     */   {
/* 162 */     return new SealBaseContainer(player.field_71071_by, world, seal);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public Object returnGui(World world, EntityPlayer player, BlockPos pos, EnumFacing side, ISealEntity seal)
/*     */   {
/* 168 */     return new SealBaseGUI(player.field_71071_by, world, seal);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/construct/golem/seals/SealButcher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */