package thaumcraft.api.golems.seals;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public abstract interface ISealEntity
{
  public abstract void tickSealEntity(World paramWorld);
  
  public abstract ISeal getSeal();
  
  public abstract SealPos getSealPos();
  
  public abstract byte getPriority();
  
  public abstract void setPriority(byte paramByte);
  
  public abstract void readNBT(NBTTagCompound paramNBTTagCompound);
  
  public abstract NBTTagCompound writeNBT();
  
  public abstract void syncToClient(World paramWorld);
  
  public abstract BlockPos getArea();
  
  public abstract void setArea(BlockPos paramBlockPos);
  
  public abstract boolean isLocked();
  
  public abstract void setLocked(boolean paramBoolean);
  
  public abstract boolean isRedstoneSensitive();
  
  public abstract void setRedstoneSensitive(boolean paramBoolean);
  
  public abstract String getOwner();
  
  public abstract void setOwner(String paramString);
  
  public abstract byte getColor();
  
  public abstract void setColor(byte paramByte);
  
  public abstract boolean isStoppedByRedstone(World paramWorld);
}


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/api/golems/seals/ISealEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */