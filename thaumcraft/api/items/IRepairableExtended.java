package thaumcraft.api.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public abstract interface IRepairableExtended
  extends IRepairable
{
  public abstract boolean doRepair(ItemStack paramItemStack, EntityPlayer paramEntityPlayer, int paramInt);
}


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/api/items/IRepairableExtended.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */