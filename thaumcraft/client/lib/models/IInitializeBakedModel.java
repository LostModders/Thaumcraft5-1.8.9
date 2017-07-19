package thaumcraft.client.lib.models;

import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.util.ResourceLocation;

public abstract interface IInitializeBakedModel
{
  public abstract void initialize(ItemCameraTransforms paramItemCameraTransforms, ResourceLocation paramResourceLocation, ModelManager paramModelManager);
}


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/lib/models/IInitializeBakedModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */