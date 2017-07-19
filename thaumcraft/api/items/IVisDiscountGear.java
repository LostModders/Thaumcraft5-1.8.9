package thaumcraft.api.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.Aspect;

public abstract interface IVisDiscountGear
{
  public abstract int getVisDiscount(ItemStack paramItemStack, EntityPlayer paramEntityPlayer, Aspect paramAspect);
}


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/api/items/IVisDiscountGear.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */