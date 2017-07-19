package thaumcraft.api.golems;

import java.util.Set;
import net.minecraft.item.ItemStack;
import thaumcraft.api.golems.parts.GolemAddon;
import thaumcraft.api.golems.parts.GolemArm;
import thaumcraft.api.golems.parts.GolemHead;
import thaumcraft.api.golems.parts.GolemLeg;
import thaumcraft.api.golems.parts.GolemMaterial;

public abstract interface IGolemProperties
{
  public abstract Set<EnumGolemTrait> getTraits();
  
  public abstract boolean hasTrait(EnumGolemTrait paramEnumGolemTrait);
  
  public abstract long toLong();
  
  public abstract ItemStack[] generateComponents();
  
  public abstract void setMaterial(GolemMaterial paramGolemMaterial);
  
  public abstract GolemMaterial getMaterial();
  
  public abstract void setHead(GolemHead paramGolemHead);
  
  public abstract GolemHead getHead();
  
  public abstract void setArms(GolemArm paramGolemArm);
  
  public abstract GolemArm getArms();
  
  public abstract void setLegs(GolemLeg paramGolemLeg);
  
  public abstract GolemLeg getLegs();
  
  public abstract void setAddon(GolemAddon paramGolemAddon);
  
  public abstract GolemAddon getAddon();
  
  public abstract void setRank(int paramInt);
  
  public abstract int getRank();
}


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/api/golems/IGolemProperties.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */