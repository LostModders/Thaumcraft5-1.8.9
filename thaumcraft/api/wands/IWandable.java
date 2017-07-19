package thaumcraft.api.wands;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public abstract interface IWandable
{
  public abstract boolean onWandRightClick(World paramWorld, ItemStack paramItemStack, EntityPlayer paramEntityPlayer, BlockPos paramBlockPos, EnumFacing paramEnumFacing);
  
  public abstract void onUsingWandTick(ItemStack paramItemStack, EntityPlayer paramEntityPlayer, int paramInt);
  
  public abstract void onWandStoppedUsing(ItemStack paramItemStack, World paramWorld, EntityPlayer paramEntityPlayer, int paramInt);
}


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/api/wands/IWandable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */