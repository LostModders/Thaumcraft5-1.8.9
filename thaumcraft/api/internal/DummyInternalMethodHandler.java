/*     */ package thaumcraft.api.internal;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.golems.seals.ISeal;
/*     */ import thaumcraft.api.golems.seals.ISealEntity;
/*     */ import thaumcraft.api.golems.seals.SealPos;
/*     */ import thaumcraft.api.golems.tasks.Task;
/*     */ 
/*     */ public class DummyInternalMethodHandler implements IInternalMethodHandler
/*     */ {
/*     */   public boolean isResearchComplete(String username, String researchkey)
/*     */   {
/*  20 */     return false;
/*     */   }
/*     */   
/*     */   public AspectList getObjectAspects(ItemStack is)
/*     */   {
/*  25 */     return null;
/*     */   }
/*     */   
/*     */   public AspectList generateTags(Item item, int meta)
/*     */   {
/*  30 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean consumeVisFromWand(ItemStack wand, EntityPlayer player, AspectList cost, boolean doit, boolean crafting)
/*     */   {
/*  36 */     return false;
/*     */   }
/*     */   
/*     */   public boolean consumeVisFromInventory(EntityPlayer player, AspectList cost)
/*     */   {
/*  41 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public void addWarpToPlayer(EntityPlayer player, int amount, EnumWarpType type) {}
/*     */   
/*     */ 
/*     */   public int getPlayerWarp(EntityPlayer player, EnumWarpType type)
/*     */   {
/*  50 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void markRunicDirty(Entity entity) {}
/*     */   
/*     */ 
/*     */   public boolean completeResearch(EntityPlayer player, String researchkey)
/*     */   {
/*  60 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean drainAura(World world, BlockPos pos, Aspect aspect, int amount)
/*     */   {
/*  67 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void addAura(World world, BlockPos pos, Aspect aspect, int amount) {}
/*     */   
/*     */ 
/*     */ 
/*     */   public void pollute(World world, BlockPos pos, int amount, boolean showEffect) {}
/*     */   
/*     */ 
/*     */   public int getAura(World world, BlockPos pos, Aspect aspect)
/*     */   {
/*  81 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public int getAuraBase(World world, BlockPos pos)
/*     */   {
/*  87 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int drainAuraAvailable(World world, BlockPos pos, Aspect aspect, int amount)
/*     */   {
/*  94 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void registerSeal(ISeal seal) {}
/*     */   
/*     */ 
/*     */ 
/*     */   public ISeal getSeal(String key)
/*     */   {
/* 106 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public ISealEntity getSealEntity(int dim, SealPos pos)
/*     */   {
/* 112 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addGolemTask(int dim, Task task) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean shouldPreserveAura(World world, EntityPlayer player, BlockPos pos, Aspect aspect)
/*     */   {
/* 125 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public ItemStack getSealStack(String key)
/*     */   {
/* 131 */     return null;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/api/internal/DummyInternalMethodHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */