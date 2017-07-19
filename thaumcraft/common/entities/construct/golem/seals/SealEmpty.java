/*     */ package thaumcraft.common.entities.construct.golem.seals;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import thaumcraft.api.golems.EnumGolemTrait;
/*     */ import thaumcraft.api.golems.IGolemAPI;
/*     */ import thaumcraft.api.golems.seals.ISealConfigToggles.SealToggle;
/*     */ import thaumcraft.api.golems.seals.ISealEntity;
/*     */ import thaumcraft.api.golems.seals.SealPos;
/*     */ import thaumcraft.api.golems.tasks.Task;
/*     */ import thaumcraft.common.entities.construct.golem.tasks.TaskHandler;
/*     */ import thaumcraft.common.lib.utils.InventoryUtils;
/*     */ 
/*     */ public class SealEmpty extends SealFiltered
/*     */ {
/*     */   public String getKey()
/*     */   {
/*  28 */     return "Thaumcraft:empty";
/*     */   }
/*     */   
/*  31 */   int delay = new Random(System.nanoTime()).nextInt(30);
/*  32 */   int filterInc = 0;
/*     */   
/*  34 */   HashMap<Integer, ItemStack> cache = new HashMap();
/*     */   
/*     */ 
/*     */   public void tickSeal(World world, ISealEntity seal)
/*     */   {
/*  39 */     if (this.delay % 100 == 0) {
/*  40 */       Iterator<Integer> it = this.cache.keySet().iterator();
/*  41 */       while (it.hasNext()) {
/*  42 */         Task t = TaskHandler.getTask(world.field_73011_w.func_177502_q(), ((Integer)it.next()).intValue());
/*  43 */         if (t == null) {
/*  44 */           it.remove();
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*  49 */     if (this.delay++ % 20 != 0) { return;
/*     */     }
/*  51 */     TileEntity te = world.func_175625_s(seal.getSealPos().pos);
/*  52 */     if ((te != null) && ((te instanceof IInventory)))
/*     */     {
/*  54 */       ItemStack stack = InventoryUtils.findFirstMatchFromFilter(getInv(this.filterInc), isBlacklist(), (IInventory)te, seal.getSealPos().face, !this.props[0].value, !this.props[1].value, this.props[2].value, this.props[3].value, this.props[5].value);
/*     */       
/*     */ 
/*  57 */       if (stack != null) {
/*  58 */         Task task = new Task(seal.getSealPos(), seal.getSealPos().pos);
/*  59 */         task.setPriority(seal.getPriority());
/*  60 */         task.setLifespan((short)5);
/*  61 */         TaskHandler.addTask(world.field_73011_w.func_177502_q(), task);
/*  62 */         this.cache.put(Integer.valueOf(task.getId()), stack);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean onTaskCompletion(World world, IGolemAPI golem, Task task)
/*     */   {
/*  70 */     TileEntity te = world.func_175625_s(task.getSealPos().pos);
/*  71 */     if ((te != null) && ((te instanceof IInventory))) {
/*  72 */       ItemStack stack = (ItemStack)this.cache.get(Integer.valueOf(task.getId()));
/*     */       
/*  74 */       int sa = 0;
/*  75 */       if ((stack != null) && (this.props[5].value)) { if ((sa = InventoryUtils.inventoryContainsAmount((IInventory)te, stack, task.getSealPos().face, !this.props[0].value, !this.props[1].value, this.props[2].value, this.props[3].value)) <= stack.field_77994_a)
/*     */         {
/*  77 */           stack = stack.func_77946_l();
/*  78 */           stack.field_77994_a = (sa - 1);
/*     */         }
/*     */       }
/*  81 */       if ((stack != null) && (golem.canCarry(stack, true))) {
/*  82 */         ItemStack s = golem.holdItem(InventoryUtils.extractStack((IInventory)te, stack.func_77946_l(), task.getSealPos().face, !this.props[0].value, !this.props[1].value, this.props[2].value, this.props[3].value, true));
/*     */         
/*  84 */         if (s != null) {
/*  85 */           ItemStack q = InventoryUtils.placeItemStackIntoInventory(s, (IInventory)te, task.getSealPos().face, true);
/*  86 */           if (q != null) ((Entity)golem).func_70099_a(q, 0.25F);
/*     */         }
/*  88 */         world.func_72956_a((Entity)golem, "random.pop", 0.125F, ((world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.7F + 1.0F) * 2.0F);
/*     */         
/*  90 */         golem.swingArm();
/*     */       }
/*  92 */       this.cache.remove(Integer.valueOf(task.getId()));
/*  93 */       this.filterInc += 1;
/*     */     }
/*  95 */     task.setSuspended(true);
/*  96 */     return true;
/*     */   }
/*     */   
/*     */   public boolean canGolemPerformTask(IGolemAPI golem, Task task)
/*     */   {
/* 101 */     ItemStack stack = (ItemStack)this.cache.get(Integer.valueOf(task.getId()));
/* 102 */     return (stack != null) && (golem.canCarry(stack, true));
/*     */   }
/*     */   
/*     */   public boolean canPlaceAt(World world, BlockPos pos, EnumFacing side)
/*     */   {
/* 107 */     TileEntity te = world.func_175625_s(pos);
/* 108 */     if ((te != null) && ((te instanceof IInventory))) {
/* 109 */       return true;
/*     */     }
/* 111 */     return false;
/*     */   }
/*     */   
/*     */   public ItemStack[] getInv(int c) {
/* 115 */     return super.getInv();
/*     */   }
/*     */   
/*     */   public ResourceLocation getSealIcon()
/*     */   {
/* 120 */     return this.icon;
/*     */   }
/*     */   
/* 123 */   ResourceLocation icon = new ResourceLocation("thaumcraft", "items/seals/seal_empty");
/*     */   
/*     */   public int[] getGuiCategories()
/*     */   {
/* 127 */     return new int[] { 1, 0, 4 };
/*     */   }
/*     */   
/* 130 */   protected ISealConfigToggles.SealToggle[] props = { new ISealConfigToggles.SealToggle(true, "pmeta", "golem.prop.meta"), new ISealConfigToggles.SealToggle(true, "pnbt", "golem.prop.nbt"), new ISealConfigToggles.SealToggle(false, "pore", "golem.prop.ore"), new ISealConfigToggles.SealToggle(false, "pmod", "golem.prop.mod"), new ISealConfigToggles.SealToggle(false, "pcycle", "golem.prop.cycle"), new ISealConfigToggles.SealToggle(false, "pleave", "golem.prop.leave") };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public EnumGolemTrait[] getRequiredTags()
/*     */   {
/* 141 */     return null;
/*     */   }
/*     */   
/*     */   public EnumGolemTrait[] getForbiddenTags()
/*     */   {
/* 146 */     return new EnumGolemTrait[] { EnumGolemTrait.CLUMSY };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void onTaskStarted(World world, IGolemAPI golem, Task task) {}
/*     */   
/*     */ 
/*     */ 
/*     */   public void onTaskSuspension(World world, Task task)
/*     */   {
/* 157 */     this.cache.remove(Integer.valueOf(task.getId()));
/*     */   }
/*     */   
/*     */   public void onRemoval(World world, BlockPos pos, EnumFacing side) {}
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/construct/golem/seals/SealEmpty.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */