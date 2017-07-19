package thaumcraft.api.aspects;

import net.minecraft.item.ItemStack;

public abstract interface IEssentiaContainerItem
{
  public abstract AspectList getAspects(ItemStack paramItemStack);
  
  public abstract void setAspects(ItemStack paramItemStack, AspectList paramAspectList);
  
  public abstract boolean ignoreContainedAspects();
}


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/api/aspects/IEssentiaContainerItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */