package thaumcraft.api.internal;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.golems.seals.ISeal;
import thaumcraft.api.golems.seals.ISealEntity;
import thaumcraft.api.golems.seals.SealPos;
import thaumcraft.api.golems.tasks.Task;

public abstract interface IInternalMethodHandler
{
  public abstract boolean isResearchComplete(String paramString1, String paramString2);
  
  public abstract AspectList getObjectAspects(ItemStack paramItemStack);
  
  public abstract AspectList generateTags(Item paramItem, int paramInt);
  
  public abstract boolean consumeVisFromWand(ItemStack paramItemStack, EntityPlayer paramEntityPlayer, AspectList paramAspectList, boolean paramBoolean1, boolean paramBoolean2);
  
  public abstract boolean consumeVisFromInventory(EntityPlayer paramEntityPlayer, AspectList paramAspectList);
  
  public abstract void addWarpToPlayer(EntityPlayer paramEntityPlayer, int paramInt, EnumWarpType paramEnumWarpType);
  
  public abstract int getPlayerWarp(EntityPlayer paramEntityPlayer, EnumWarpType paramEnumWarpType);
  
  public abstract void markRunicDirty(Entity paramEntity);
  
  public abstract boolean completeResearch(EntityPlayer paramEntityPlayer, String paramString);
  
  public abstract boolean drainAura(World paramWorld, BlockPos paramBlockPos, Aspect paramAspect, int paramInt);
  
  public abstract int drainAuraAvailable(World paramWorld, BlockPos paramBlockPos, Aspect paramAspect, int paramInt);
  
  public abstract void addAura(World paramWorld, BlockPos paramBlockPos, Aspect paramAspect, int paramInt);
  
  public abstract void pollute(World paramWorld, BlockPos paramBlockPos, int paramInt, boolean paramBoolean);
  
  public abstract int getAura(World paramWorld, BlockPos paramBlockPos, Aspect paramAspect);
  
  public abstract int getAuraBase(World paramWorld, BlockPos paramBlockPos);
  
  public abstract void registerSeal(ISeal paramISeal);
  
  public abstract ISeal getSeal(String paramString);
  
  public abstract ISealEntity getSealEntity(int paramInt, SealPos paramSealPos);
  
  public abstract void addGolemTask(int paramInt, Task paramTask);
  
  public abstract boolean shouldPreserveAura(World paramWorld, EntityPlayer paramEntityPlayer, BlockPos paramBlockPos, Aspect paramAspect);
  
  public abstract ItemStack getSealStack(String paramString);
}


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/api/internal/IInternalMethodHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */