package thaumcraft.api.aspects;

import net.minecraft.util.EnumFacing;

public abstract interface IEssentiaTransport
{
  public abstract boolean isConnectable(EnumFacing paramEnumFacing);
  
  public abstract boolean canInputFrom(EnumFacing paramEnumFacing);
  
  public abstract boolean canOutputTo(EnumFacing paramEnumFacing);
  
  public abstract void setSuction(Aspect paramAspect, int paramInt);
  
  public abstract Aspect getSuctionType(EnumFacing paramEnumFacing);
  
  public abstract int getSuctionAmount(EnumFacing paramEnumFacing);
  
  public abstract int takeEssentia(Aspect paramAspect, int paramInt, EnumFacing paramEnumFacing);
  
  public abstract int addEssentia(Aspect paramAspect, int paramInt, EnumFacing paramEnumFacing);
  
  public abstract Aspect getEssentiaType(EnumFacing paramEnumFacing);
  
  public abstract int getEssentiaAmount(EnumFacing paramEnumFacing);
  
  public abstract int getMinimumSuction();
}


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/api/aspects/IEssentiaTransport.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */