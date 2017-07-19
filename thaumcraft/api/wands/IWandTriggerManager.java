package thaumcraft.api.wands;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public abstract interface IWandTriggerManager
{
  public abstract boolean performTrigger(World paramWorld, ItemStack paramItemStack, EntityPlayer paramEntityPlayer, BlockPos paramBlockPos, EnumFacing paramEnumFacing, int paramInt);
}


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/api/wands/IWandTriggerManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */