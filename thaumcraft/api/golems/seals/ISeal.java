package thaumcraft.api.golems.seals;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thaumcraft.api.golems.EnumGolemTrait;
import thaumcraft.api.golems.IGolemAPI;
import thaumcraft.api.golems.tasks.Task;

public abstract interface ISeal
{
  public abstract String getKey();
  
  public abstract boolean canPlaceAt(World paramWorld, BlockPos paramBlockPos, EnumFacing paramEnumFacing);
  
  public abstract void tickSeal(World paramWorld, ISealEntity paramISealEntity);
  
  public abstract void onTaskStarted(World paramWorld, IGolemAPI paramIGolemAPI, Task paramTask);
  
  public abstract boolean onTaskCompletion(World paramWorld, IGolemAPI paramIGolemAPI, Task paramTask);
  
  public abstract void onTaskSuspension(World paramWorld, Task paramTask);
  
  public abstract boolean canGolemPerformTask(IGolemAPI paramIGolemAPI, Task paramTask);
  
  public abstract void readCustomNBT(NBTTagCompound paramNBTTagCompound);
  
  public abstract void writeCustomNBT(NBTTagCompound paramNBTTagCompound);
  
  public abstract ResourceLocation getSealIcon();
  
  public abstract void onRemoval(World paramWorld, BlockPos paramBlockPos, EnumFacing paramEnumFacing);
  
  public abstract Object returnContainer(World paramWorld, EntityPlayer paramEntityPlayer, BlockPos paramBlockPos, EnumFacing paramEnumFacing, ISealEntity paramISealEntity);
  
  @SideOnly(Side.CLIENT)
  public abstract Object returnGui(World paramWorld, EntityPlayer paramEntityPlayer, BlockPos paramBlockPos, EnumFacing paramEnumFacing, ISealEntity paramISealEntity);
  
  public abstract EnumGolemTrait[] getRequiredTags();
  
  public abstract EnumGolemTrait[] getForbiddenTags();
}


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/api/golems/seals/ISeal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */