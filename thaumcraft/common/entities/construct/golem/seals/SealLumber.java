/*     */ package thaumcraft.common.entities.construct.golem.seals;
/*     */ 
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraft.world.WorldServer;
/*     */ import net.minecraftforge.common.util.FakePlayer;
/*     */ import net.minecraftforge.common.util.FakePlayerFactory;
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
/*     */ import thaumcraft.common.lib.utils.BlockUtils;
/*     */ import thaumcraft.common.lib.utils.Utils;
/*     */ 
/*     */ public class SealLumber implements ISeal, ISealGui, ISealConfigArea
/*     */ {
/*     */   public String getKey()
/*     */   {
/*  41 */     return "Thaumcraft:lumber";
/*     */   }
/*     */   
/*  44 */   int delay = new Random(System.nanoTime()).nextInt(33);
/*  45 */   HashMap<Integer, Long> cache = new HashMap();
/*     */   
/*     */ 
/*     */ 
/*     */   public void tickSeal(World world, ISealEntity seal)
/*     */   {
/*  51 */     if (this.delay % 100 == 0) {
/*  52 */       Iterator<Integer> it = this.cache.keySet().iterator();
/*  53 */       while (it.hasNext()) {
/*  54 */         Task t = TaskHandler.getTask(world.field_73011_w.func_177502_q(), ((Integer)it.next()).intValue());
/*  55 */         if (t == null) {
/*  56 */           it.remove();
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*  61 */     this.delay += 1;
/*     */     
/*  63 */     BlockPos p = GolemHelper.getPosInArea(seal, this.delay);
/*     */     
/*  65 */     if ((!this.cache.containsValue(Long.valueOf(p.func_177986_g()))) && (Utils.isWoodLog(world, p))) {
/*  66 */       Task task = new Task(seal.getSealPos(), p);
/*  67 */       task.setPriority(seal.getPriority());
/*  68 */       TaskHandler.addTask(world.field_73011_w.func_177502_q(), task);
/*  69 */       this.cache.put(Integer.valueOf(task.getId()), Long.valueOf(p.func_177986_g()));
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean onTaskCompletion(World world, IGolemAPI golem, Task task)
/*     */   {
/*  75 */     if ((this.cache.containsKey(Integer.valueOf(task.getId()))) && (Utils.isWoodLog(world, task.getPos()))) {
/*  76 */       FakePlayer fp = FakePlayerFactory.get((WorldServer)world, new GameProfile((UUID)null, "FakeThaumcraftGolem"));
/*  77 */       fp.func_70107_b(golem.getGolemEntity().field_70165_t, golem.getGolemEntity().field_70163_u, golem.getGolemEntity().field_70161_v);
/*  78 */       IBlockState bs = world.func_180495_p(task.getPos());
/*  79 */       golem.swingArm();
/*  80 */       if (BlockUtils.breakFurthestBlock(world, task.getPos(), bs, fp)) {
/*  81 */         task.setLifespan((short)(int)Math.max(task.getLifespan(), 10L));
/*  82 */         golem.addRankXp(1);
/*  83 */         return false;
/*     */       }
/*  85 */       this.cache.remove(Integer.valueOf(task.getId()));
/*     */     }
/*     */     
/*  88 */     task.setSuspended(true);
/*  89 */     return true;
/*     */   }
/*     */   
/*     */   public boolean canGolemPerformTask(IGolemAPI golem, Task task)
/*     */   {
/*  94 */     if ((this.cache.containsKey(Integer.valueOf(task.getId()))) && (Utils.isWoodLog(golem.getGolemWorld(), task.getPos()))) return true;
/*  95 */     task.setSuspended(true);
/*  96 */     return false;
/*     */   }
/*     */   
/*     */   public void onTaskSuspension(World world, Task task)
/*     */   {
/* 101 */     this.cache.remove(Integer.valueOf(task.getId()));
/*     */   }
/*     */   
/*     */ 
/*     */   public void readCustomNBT(NBTTagCompound nbt) {}
/*     */   
/*     */ 
/*     */   public void writeCustomNBT(NBTTagCompound nbt) {}
/*     */   
/*     */   public boolean canPlaceAt(World world, BlockPos pos, EnumFacing side)
/*     */   {
/* 112 */     return !world.func_175623_d(pos);
/*     */   }
/*     */   
/*     */   public ResourceLocation getSealIcon()
/*     */   {
/* 117 */     return this.icon;
/*     */   }
/*     */   
/* 120 */   ResourceLocation icon = new ResourceLocation("thaumcraft", "items/seals/seal_lumber");
/*     */   
/*     */ 
/*     */   public void onRemoval(World world, BlockPos pos, EnumFacing side) {}
/*     */   
/*     */   public Object returnContainer(World world, EntityPlayer player, BlockPos pos, EnumFacing side, ISealEntity seal)
/*     */   {
/* 127 */     return new SealBaseContainer(player.field_71071_by, world, seal);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public Object returnGui(World world, EntityPlayer player, BlockPos pos, EnumFacing side, ISealEntity seal)
/*     */   {
/* 133 */     return new SealBaseGUI(player.field_71071_by, world, seal);
/*     */   }
/*     */   
/*     */   public int[] getGuiCategories() {
/* 137 */     return new int[] { 2, 0, 4 };
/*     */   }
/*     */   
/*     */   public EnumGolemTrait[] getRequiredTags() {
/* 141 */     return new EnumGolemTrait[] { EnumGolemTrait.BREAKER, EnumGolemTrait.SMART };
/*     */   }
/*     */   
/*     */   public EnumGolemTrait[] getForbiddenTags()
/*     */   {
/* 146 */     return null;
/*     */   }
/*     */   
/*     */   public void onTaskStarted(World world, IGolemAPI golem, Task task) {}
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/construct/golem/seals/SealLumber.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */