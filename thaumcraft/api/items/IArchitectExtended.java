package thaumcraft.api.items;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public abstract interface IArchitectExtended
  extends IArchitect
{
  public abstract MovingObjectPosition getArchitectMOP(ItemStack paramItemStack, World paramWorld, EntityLivingBase paramEntityLivingBase);
  
  public abstract boolean useBlockHighlight(ItemStack paramItemStack);
}


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/api/items/IArchitectExtended.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */