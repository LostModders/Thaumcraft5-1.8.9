package thaumcraft.api.golems;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract interface IGolemAPI
{
  public abstract EntityLivingBase getGolemEntity();
  
  public abstract IGolemProperties getProperties();
  
  public abstract void setProperties(IGolemProperties paramIGolemProperties);
  
  public abstract World getGolemWorld();
  
  public abstract ItemStack holdItem(ItemStack paramItemStack);
  
  public abstract ItemStack dropItem(ItemStack paramItemStack);
  
  public abstract boolean canCarry(ItemStack paramItemStack, boolean paramBoolean);
  
  public abstract boolean isCarrying(ItemStack paramItemStack);
  
  public abstract ItemStack[] getCarrying();
  
  public abstract void addRankXp(int paramInt);
  
  public abstract byte getGolemColor();
  
  public abstract void swingArm();
  
  public abstract boolean isInCombat();
}


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/api/golems/IGolemAPI.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */