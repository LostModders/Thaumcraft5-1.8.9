package thaumcraft.api.crafting;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import thaumcraft.api.aspects.AspectList;

public abstract interface IArcaneRecipe
  extends IRecipe
{
  public abstract boolean func_77569_a(InventoryCrafting paramInventoryCrafting, World paramWorld);
  
  public abstract boolean matches(InventoryCrafting paramInventoryCrafting, World paramWorld, EntityPlayer paramEntityPlayer);
  
  public abstract ItemStack func_77572_b(InventoryCrafting paramInventoryCrafting);
  
  public abstract int func_77570_a();
  
  public abstract ItemStack func_77571_b();
  
  public abstract ItemStack[] func_179532_b(InventoryCrafting paramInventoryCrafting);
  
  public abstract AspectList getAspects();
  
  public abstract AspectList getAspects(InventoryCrafting paramInventoryCrafting);
  
  public abstract String[] getResearch();
}


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/api/crafting/IArcaneRecipe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */