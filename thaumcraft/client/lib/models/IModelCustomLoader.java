package thaumcraft.client.lib.models;

import net.minecraft.util.ResourceLocation;

public abstract interface IModelCustomLoader
{
  public abstract String getType();
  
  public abstract String[] getSuffixes();
  
  public abstract IModelCustom loadInstance(ResourceLocation paramResourceLocation)
    throws WavefrontObject.ModelFormatException;
}


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/lib/models/IModelCustomLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */