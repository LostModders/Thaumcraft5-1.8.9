/*     */ package thaumcraft.common.entities.construct.golem.seals;
/*     */ 
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Random;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockDirectional;
/*     */ import net.minecraft.block.BlockFarmland;
/*     */ import net.minecraft.block.BlockPistonBase;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraft.world.WorldServer;
/*     */ import net.minecraftforge.common.IPlantable;
/*     */ import net.minecraftforge.common.util.FakePlayer;
/*     */ import net.minecraftforge.common.util.FakePlayerFactory;
/*     */ import thaumcraft.api.golems.EnumGolemTrait;
/*     */ import thaumcraft.api.golems.GolemHelper;
/*     */ import thaumcraft.api.golems.IGolemAPI;
/*     */ import thaumcraft.api.golems.seals.ISealConfigArea;
/*     */ import thaumcraft.api.golems.seals.ISealConfigToggles;
/*     */ import thaumcraft.api.golems.seals.ISealConfigToggles.SealToggle;
/*     */ import thaumcraft.api.golems.seals.ISealEntity;
/*     */ import thaumcraft.api.golems.tasks.Task;
/*     */ import thaumcraft.common.entities.construct.golem.gui.SealBaseContainer;
/*     */ import thaumcraft.common.entities.construct.golem.gui.SealBaseGUI;
/*     */ import thaumcraft.common.entities.construct.golem.tasks.TaskHandler;
/*     */ import thaumcraft.common.lib.utils.CropUtils;
/*     */ 
/*     */ public class SealHarvest implements thaumcraft.api.golems.seals.ISeal, thaumcraft.api.golems.seals.ISealGui, ISealConfigArea, ISealConfigToggles
/*     */ {
/*     */   int delay;
/*     */   int count;
/*     */   HashMap<Long, ReplantInfo> replantTasks;
/*     */   ResourceLocation icon;
/*     */   protected ISealConfigToggles.SealToggle[] props;
/*     */   
/*     */   public String getKey()
/*     */   {
/*  55 */     return "Thaumcraft:harvest";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void tickSeal(World world, ISealEntity seal)
/*     */   {
/*  66 */     if (this.delay % 100 == 0) {
/*  67 */       AxisAlignedBB area = GolemHelper.getBoundsForArea(seal);
/*     */       
/*  69 */       Iterator<Long> rt = this.replantTasks.keySet().iterator();
/*  70 */       while (rt.hasNext()) {
/*  71 */         BlockPos pp = BlockPos.func_177969_a(((Long)rt.next()).longValue());
/*  72 */         if (!area.func_72318_a(new Vec3(pp.func_177958_n() + 0.5D, pp.func_177956_o() + 0.5D, pp.func_177952_p() + 0.5D))) {
/*  73 */           if (this.replantTasks.get(rt) != null) {
/*  74 */             Task tt = TaskHandler.getTask(world.field_73011_w.func_177502_q(), ((ReplantInfo)this.replantTasks.get(rt)).taskid);
/*  75 */             if (tt != null) tt.setSuspended(true);
/*     */           }
/*  77 */           rt.remove();
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*  82 */     if (this.delay++ % 5 != 0) { return;
/*     */     }
/*  84 */     BlockPos p = GolemHelper.getPosInArea(seal, this.count++);
/*     */     
/*  86 */     if (CropUtils.isGrownCrop(world, p)) {
/*  87 */       Task task = new Task(seal.getSealPos(), p);
/*  88 */       task.setPriority(seal.getPriority());
/*  89 */       TaskHandler.addTask(world.field_73011_w.func_177502_q(), task);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     }
/*  96 */     else if ((getToggles()[0].value) && (this.replantTasks.containsKey(Long.valueOf(p.func_177986_g()))) && (world.func_175623_d(p))) {
/*  97 */       Task t = TaskHandler.getTask(world.field_73011_w.func_177502_q(), ((ReplantInfo)this.replantTasks.get(Long.valueOf(p.func_177986_g()))).taskid);
/*     */       
/*  99 */       if (t == null) {
/* 100 */         Task tt = new Task(seal.getSealPos(), ((ReplantInfo)this.replantTasks.get(Long.valueOf(p.func_177986_g()))).pos);
/* 101 */         tt.setPriority(seal.getPriority());
/* 102 */         TaskHandler.addTask(world.field_73011_w.func_177502_q(), tt);
/* 103 */         ((ReplantInfo)this.replantTasks.get(Long.valueOf(p.func_177986_g()))).taskid = tt.getId();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean onTaskCompletion(World world, IGolemAPI golem, Task task)
/*     */   {
/* 120 */     if (CropUtils.isGrownCrop(world, task.getPos()))
/*     */     {
/* 122 */       FakePlayer fp = FakePlayerFactory.get((WorldServer)world, new GameProfile((UUID)null, "FakeThaumcraftGolem"));
/* 123 */       fp.func_70107_b(golem.getGolemEntity().field_70165_t, golem.getGolemEntity().field_70163_u, golem.getGolemEntity().field_70161_v);
/*     */       
/* 125 */       EnumFacing face = BlockPistonBase.func_180695_a(world, task.getPos(), golem.getGolemEntity());
/* 126 */       IBlockState bs = world.func_180495_p(task.getPos());
/*     */       
/* 128 */       if (CropUtils.clickableCrops.contains(bs.func_177230_c().func_149739_a() + bs.func_177230_c().func_176201_c(bs))) {
/* 129 */         bs.func_177230_c().func_180639_a(world, task.getPos(), bs, fp, face, 0.0F, 0.0F, 0.0F);
/* 130 */         golem.addRankXp(1);
/* 131 */         golem.swingArm();
/*     */       }
/*     */       else {
/* 134 */         thaumcraft.common.lib.utils.BlockUtils.harvestBlock(world, fp, task.getPos(), true);
/* 135 */         golem.addRankXp(1);
/* 136 */         golem.swingArm();
/* 137 */         if (getToggles()[0].value) {
/* 138 */           ItemStack seed = thaumcraft.api.ThaumcraftApi.getSeed(bs.func_177230_c());
/*     */           
/* 140 */           if (seed != null) {
/* 141 */             IBlockState bb = world.func_180495_p(task.getPos().func_177977_b());
/* 142 */             EnumFacing rf = null;
/* 143 */             if (((seed.func_77973_b() instanceof IPlantable)) && (bb.func_177230_c().canSustainPlant(world, task.getPos().func_177977_b(), EnumFacing.UP, (IPlantable)seed.func_77973_b())))
/*     */             {
/*     */ 
/*     */ 
/*     */ 
/* 148 */               rf = EnumFacing.DOWN;
/* 149 */             } else if ((!(seed.func_77973_b() instanceof IPlantable)) && 
/* 150 */               ((bs.func_177230_c() instanceof BlockDirectional))) {
/* 151 */               rf = (EnumFacing)bs.func_177229_b(BlockDirectional.field_176387_N);
/*     */             }
/*     */             
/*     */ 
/* 155 */             if (rf != null) {
/* 156 */               Task tt = new Task(task.getSealPos(), task.getPos());
/*     */               
/* 158 */               tt.setPriority(task.getPriority());
/* 159 */               tt.setLifespan((short)300);
/* 160 */               this.replantTasks.put(Long.valueOf(tt.getPos().func_177986_g()), new ReplantInfo(tt.getPos(), rf, tt.getId(), seed.func_77946_l(), bb.func_177230_c() instanceof BlockFarmland));
/*     */               
/*     */ 
/*     */ 
/* 164 */               TaskHandler.addTask(world.field_73011_w.func_177502_q(), tt);
/*     */ 
/*     */             }
/*     */             
/*     */ 
/*     */           }
/*     */           
/*     */ 
/*     */         }
/*     */         
/*     */       }
/*     */       
/*     */ 
/*     */     }
/* 178 */     else if ((this.replantTasks.containsKey(Long.valueOf(task.getPos().func_177986_g()))) && (((ReplantInfo)this.replantTasks.get(Long.valueOf(task.getPos().func_177986_g()))).taskid == task.getId()) && (world.func_175623_d(task.getPos())) && (golem.isCarrying(((ReplantInfo)this.replantTasks.get(Long.valueOf(task.getPos().func_177986_g()))).stack)))
/*     */     {
/* 180 */       FakePlayer fp = FakePlayerFactory.get((WorldServer)world, new GameProfile((UUID)null, "FakeThaumcraftGolem"));
/* 181 */       fp.func_70107_b(golem.getGolemEntity().field_70165_t, golem.getGolemEntity().field_70163_u, golem.getGolemEntity().field_70161_v);
/* 182 */       IBlockState bb = world.func_180495_p(task.getPos().func_177977_b());
/* 183 */       ReplantInfo ri = (ReplantInfo)this.replantTasks.get(Long.valueOf(task.getPos().func_177986_g()));
/* 184 */       if ((((bb.func_177230_c() instanceof net.minecraft.block.BlockDirt)) || ((bb.func_177230_c() instanceof net.minecraft.block.BlockGrass))) && (ri.farmland)) {
/* 185 */         Items.field_151012_L.func_180614_a(new ItemStack(Items.field_151012_L), fp, world, task.getPos().func_177977_b(), EnumFacing.UP, 0.5F, 0.5F, 0.5F);
/*     */       }
/* 187 */       ItemStack seed = ri.stack.func_77946_l();
/* 188 */       seed.field_77994_a = 1;
/* 189 */       if (seed.func_77973_b().func_180614_a(seed.func_77946_l(), fp, world, task.getPos().func_177972_a(ri.face), ri.face.func_176734_d(), 0.5F, 0.5F, 0.5F))
/*     */       {
/* 191 */         world.func_175718_b(2001, task.getPos(), Block.func_176210_f(world.func_180495_p(task.getPos())));
/* 192 */         golem.dropItem(seed);
/* 193 */         golem.addRankXp(1);
/* 194 */         golem.swingArm();
/*     */       }
/*     */     }
/*     */     
/* 198 */     task.setSuspended(true);
/* 199 */     return true;
/*     */   }
/*     */   
/*     */   public boolean canGolemPerformTask(IGolemAPI golem, Task task)
/*     */   {
/* 204 */     if ((this.replantTasks.containsKey(Long.valueOf(task.getPos().func_177986_g()))) && (((ReplantInfo)this.replantTasks.get(Long.valueOf(task.getPos().func_177986_g()))).taskid == task.getId())) {
/* 205 */       boolean carry = golem.isCarrying(((ReplantInfo)this.replantTasks.get(Long.valueOf(task.getPos().func_177986_g()))).stack);
/* 206 */       if ((!carry) && (getToggles()[1].value)) {
/* 207 */         ISealEntity se = SealHandler.getSealEntity(golem.getGolemWorld().field_73011_w.func_177502_q(), task.getSealPos());
/* 208 */         if (se != null) GolemHelper.requestProvisioning(golem.getGolemWorld(), se, ((ReplantInfo)this.replantTasks.get(Long.valueOf(task.getPos().func_177986_g()))).stack);
/*     */       }
/* 210 */       return carry;
/*     */     }
/* 212 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void readCustomNBT(NBTTagCompound nbt)
/*     */   {
/* 221 */     NBTTagList nbttaglist = nbt.func_150295_c("replant", 10);
/* 222 */     for (int i = 0; i < nbttaglist.func_74745_c(); i++)
/*     */     {
/* 224 */       NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
/* 225 */       long loc = nbttagcompound1.func_74763_f("taskloc");
/* 226 */       byte face = nbttagcompound1.func_74771_c("taskface");
/* 227 */       boolean farmland = nbttagcompound1.func_74767_n("farmland");
/* 228 */       ItemStack stack = ItemStack.func_77949_a(nbttagcompound1);
/* 229 */       this.replantTasks.put(Long.valueOf(loc), new ReplantInfo(BlockPos.func_177969_a(loc), EnumFacing.field_82609_l[face], 0, stack, farmland));
/*     */     }
/*     */   }
/*     */   
/*     */   public void writeCustomNBT(NBTTagCompound nbt)
/*     */   {
/* 235 */     if (getToggles()[0].value) {
/* 236 */       NBTTagList nbttaglist = new NBTTagList();
/* 237 */       for (Long key : this.replantTasks.keySet())
/*     */       {
/* 239 */         ReplantInfo info = (ReplantInfo)this.replantTasks.get(key);
/* 240 */         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 241 */         nbttagcompound1.func_74772_a("taskloc", info.pos.func_177986_g());
/* 242 */         nbttagcompound1.func_74774_a("taskface", (byte)info.face.ordinal());
/* 243 */         nbttagcompound1.func_74757_a("farmland", info.farmland);
/* 244 */         info.stack.func_77955_b(nbttagcompound1);
/* 245 */         nbttaglist.func_74742_a(nbttagcompound1);
/*     */       }
/* 247 */       nbt.func_74782_a("replant", nbttaglist);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean canPlaceAt(World world, BlockPos pos, EnumFacing side)
/*     */   {
/* 253 */     return !world.func_175623_d(pos);
/*     */   }
/*     */   
/*     */   public ResourceLocation getSealIcon()
/*     */   {
/* 258 */     return this.icon;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object returnContainer(World world, EntityPlayer player, BlockPos pos, EnumFacing side, ISealEntity seal)
/*     */   {
/* 271 */     return new SealBaseContainer(player.field_71071_by, world, seal);
/*     */   }
/*     */   
/*     */   @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*     */   public Object returnGui(World world, EntityPlayer player, BlockPos pos, EnumFacing side, ISealEntity seal)
/*     */   {
/* 277 */     return new SealBaseGUI(player.field_71071_by, world, seal);
/*     */   }
/*     */   
/*     */   public int[] getGuiCategories() {
/* 281 */     return new int[] { 2, 3, 0, 4 };
/*     */   }
/*     */   
/*     */   public SealHarvest()
/*     */   {
/*  58 */     this.delay = new Random(System.nanoTime()).nextInt(33);
/*  59 */     this.count = 0;
/*  60 */     this.replantTasks = new HashMap();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 261 */     this.icon = new ResourceLocation("thaumcraft", "items/seals/seal_harvest");
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 283 */     this.props = new ISealConfigToggles.SealToggle[] { new ISealConfigToggles.SealToggle(true, "prep", "golem.prop.replant"), new ISealConfigToggles.SealToggle(false, "ppro", "golem.prop.provision") };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ISealConfigToggles.SealToggle[] getToggles()
/*     */   {
/* 290 */     return this.props;
/*     */   }
/*     */   
/*     */   public void setToggle(int indx, boolean value)
/*     */   {
/* 295 */     this.props[indx].setValue(value);
/*     */   }
/*     */   
/*     */   public EnumGolemTrait[] getRequiredTags()
/*     */   {
/* 300 */     return new EnumGolemTrait[] { EnumGolemTrait.DEFT, EnumGolemTrait.SMART };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/* 305 */   public EnumGolemTrait[] getForbiddenTags() { return null; }
/*     */   
/*     */   public void onTaskSuspension(World world, Task task) {}
/*     */   
/*     */   public void onRemoval(World world, BlockPos pos, EnumFacing side) {}
/*     */   
/*     */   public void onTaskStarted(World world, IGolemAPI golem, Task task) {}
/*     */   
/*     */   private class ReplantInfo { EnumFacing face;
/*     */     BlockPos pos;
/*     */     
/* 316 */     public ReplantInfo(BlockPos pos, EnumFacing face, int taskid, ItemStack stack, boolean farmland) { this.pos = pos;
/* 317 */       this.face = face;
/* 318 */       this.taskid = taskid;
/* 319 */       this.stack = stack;
/* 320 */       this.farmland = farmland;
/*     */     }
/*     */     
/*     */     int taskid;
/*     */     ItemStack stack;
/*     */     boolean farmland;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/construct/golem/seals/SealHarvest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */