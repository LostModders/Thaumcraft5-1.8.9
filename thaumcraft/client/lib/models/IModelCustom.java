package thaumcraft.client.lib.models;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract interface IModelCustom
{
  public abstract String getType();
  
  @SideOnly(Side.CLIENT)
  public abstract void renderAll();
  
  @SideOnly(Side.CLIENT)
  public abstract void renderOnly(String... paramVarArgs);
  
  @SideOnly(Side.CLIENT)
  public abstract void renderPart(String paramString);
  
  @SideOnly(Side.CLIENT)
  public abstract void renderAllExcept(String... paramVarArgs);
  
  @SideOnly(Side.CLIENT)
  public abstract String[] getPartNames();
}


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/lib/models/IModelCustom.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */