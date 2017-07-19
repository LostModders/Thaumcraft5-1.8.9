package thaumcraft.api.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

public abstract interface IRechargable
{
  @Deprecated
  public abstract Aspect handleRecharge(World paramWorld, ItemStack paramItemStack, BlockPos paramBlockPos, EntityPlayer paramEntityPlayer, int paramInt);
  
  @Deprecated
  public abstract AspectList getAspectsInChargable(ItemStack paramItemStack);
  
  @Deprecated
  public abstract float getChargeLevel(ItemStack paramItemStack);
}


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/api/items/IRechargable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */