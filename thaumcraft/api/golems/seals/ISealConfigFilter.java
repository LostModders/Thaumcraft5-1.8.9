package thaumcraft.api.golems.seals;

import net.minecraft.item.ItemStack;

public abstract interface ISealConfigFilter
{
  public abstract ItemStack[] getInv();
  
  public abstract int getFilterSize();
  
  public abstract ItemStack getFilterSlot(int paramInt);
  
  public abstract void setFilterSlot(int paramInt, ItemStack paramItemStack);
  
  public abstract boolean isBlacklist();
  
  public abstract void setBlacklist(boolean paramBoolean);
  
  public abstract boolean hasStacksizeLimiters();
}


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/api/golems/seals/ISealConfigFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */