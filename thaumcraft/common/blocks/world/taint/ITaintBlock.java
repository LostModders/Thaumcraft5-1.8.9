package thaumcraft.common.blocks.world.taint;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public abstract interface ITaintBlock
{
  public abstract void die(World paramWorld, BlockPos paramBlockPos, IBlockState paramIBlockState);
}


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/world/taint/ITaintBlock.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */