/*    */ package thaumcraft.common.blocks.world.ore;
/*    */ 
/*    */ import com.google.common.collect.ImmutableList;
/*    */ import com.google.common.collect.UnmodifiableIterator;
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.state.BlockState;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.api.blocks.BlocksTC;
/*    */ import thaumcraft.api.items.ItemsTC;
/*    */ import thaumcraft.common.blocks.BlockTC;
/*    */ 
/*    */ public class BlockOreTC extends BlockTC
/*    */ {
/*    */   public BlockOreTC()
/*    */   {
/* 22 */     super(net.minecraft.block.material.Material.field_151576_e);
/* 23 */     func_149752_b(5.0F);
/* 24 */     func_149672_a(Block.field_149769_e);
/*    */   }
/*    */   
/*    */ 
/*    */   public Item func_180660_a(IBlockState state, Random rand, int fortune)
/*    */   {
/* 30 */     return this == BlocksTC.oreAmber ? ItemsTC.amber : Item.func_150898_a(this);
/*    */   }
/*    */   
/*    */ 
/*    */   public int func_149745_a(Random random)
/*    */   {
/* 36 */     return this == BlocksTC.oreAmber ? 1 + random.nextInt(2) : 1;
/*    */   }
/*    */   
/*    */ 
/*    */   public int getExpDrop(IBlockAccess world, BlockPos pos, int fortune)
/*    */   {
/* 42 */     IBlockState state = world.func_180495_p(pos);
/* 43 */     Random rand = (world instanceof World) ? ((World)world).field_73012_v : new Random();
/* 44 */     if (func_180660_a(state, rand, fortune) != Item.func_150898_a(this))
/*    */     {
/* 46 */       int j = 0;
/*    */       
/* 48 */       if (this == BlocksTC.oreAmber)
/*    */       {
/* 50 */         j = MathHelper.func_76136_a(rand, 1, 4);
/*    */       }
/*    */       
/* 53 */       return j;
/*    */     }
/* 55 */     return 0;
/*    */   }
/*    */   
/*    */ 
/*    */   public int func_149679_a(int fortune, Random random)
/*    */   {
/* 61 */     if ((fortune > 0) && (Item.func_150898_a(this) != func_180660_a((IBlockState)func_176194_O().func_177619_a().iterator().next(), random, fortune)))
/*    */     {
/* 63 */       int j = random.nextInt(fortune + 2) - 1;
/*    */       
/* 65 */       if (j < 0)
/*    */       {
/* 67 */         j = 0;
/*    */       }
/*    */       
/* 70 */       return func_149745_a(random) * (j + 1);
/*    */     }
/*    */     
/*    */ 
/* 74 */     return func_149745_a(random);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public int func_176222_j(World worldIn, BlockPos pos)
/*    */   {
/* 81 */     return 0;
/*    */   }
/*    */   
/*    */ 
/*    */   public int func_180651_a(IBlockState state)
/*    */   {
/* 87 */     return 0;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/world/ore/BlockOreTC.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */