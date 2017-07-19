package thaumcraft.api.wands;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

public abstract interface IWand
{
  public abstract int getMaxVis(ItemStack paramItemStack);
  
  public abstract int getVis(ItemStack paramItemStack, Aspect paramAspect);
  
  public abstract AspectList getAllVis(ItemStack paramItemStack);
  
  public abstract AspectList getAspectsWithRoom(ItemStack paramItemStack);
  
  public abstract float getConsumptionModifier(ItemStack paramItemStack, EntityPlayer paramEntityPlayer, Aspect paramAspect, boolean paramBoolean);
  
  public abstract boolean consumeVis(ItemStack paramItemStack, EntityPlayer paramEntityPlayer, Aspect paramAspect, int paramInt, boolean paramBoolean);
  
  public abstract boolean consumeAllVis(ItemStack paramItemStack, EntityPlayer paramEntityPlayer, AspectList paramAspectList, boolean paramBoolean1, boolean paramBoolean2);
  
  public abstract int addVis(ItemStack paramItemStack, Aspect paramAspect, int paramInt, boolean paramBoolean);
  
  public abstract ItemFocusBasic getFocus(ItemStack paramItemStack);
  
  public abstract ItemStack getFocusStack(ItemStack paramItemStack);
  
  public abstract void setFocus(ItemStack paramItemStack1, ItemStack paramItemStack2);
  
  public abstract WandRod getRod(ItemStack paramItemStack);
  
  public abstract boolean isStaff(ItemStack paramItemStack);
  
  public abstract boolean isSceptre(ItemStack paramItemStack);
  
  public abstract void setRod(ItemStack paramItemStack, WandRod paramWandRod);
  
  public abstract WandCap getCap(ItemStack paramItemStack);
  
  public abstract void setCap(ItemStack paramItemStack, WandCap paramWandCap);
  
  public abstract int getFocusPotency(ItemStack paramItemStack);
  
  public abstract int getFocusTreasure(ItemStack paramItemStack);
  
  public abstract int getFocusFrugal(ItemStack paramItemStack);
  
  public abstract int getFocusEnlarge(ItemStack paramItemStack);
  
  public abstract int getFocusExtend(ItemStack paramItemStack);
}


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/api/wands/IWand.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */