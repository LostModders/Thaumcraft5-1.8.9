/*     */ package thaumcraft.common.items.resources;
/*     */ 
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.common.items.ItemGenericVariants;
/*     */ 
/*     */ public class ItemShard extends ItemGenericVariants
/*     */ {
/*     */   public ItemShard()
/*     */   {
/*  15 */     super(new String[] { "base", "base", "base", "base", "base", "base", "flux", "balanced" });
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_82790_a(ItemStack stack, int par2)
/*     */   {
/*  22 */     if (stack.func_77952_i() == 7) {
/*  23 */       return super.func_82790_a(stack, par2);
/*     */     }
/*  25 */     if (stack.func_77952_i() == 6) {
/*  26 */       return Aspect.FLUX.getColor();
/*     */     }
/*  28 */     return ShardType.byMetadata(stack.func_77952_i()).getAspect().getColor();
/*     */   }
/*     */   
/*     */ 
/*     */   public String func_77667_c(ItemStack par1ItemStack)
/*     */   {
/*  34 */     return super.func_77658_a() + "." + ShardType.byMetadata(par1ItemStack.func_77952_i());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static enum ShardType
/*     */   {
/*  43 */     AIR(0, "air", Aspect.AIR, BlocksTC.crystalAir), 
/*  44 */     FIRE(1, "fire", Aspect.FIRE, BlocksTC.crystalFire), 
/*  45 */     WATER(2, "water", Aspect.WATER, BlocksTC.crystalWater), 
/*  46 */     EARTH(3, "earth", Aspect.EARTH, BlocksTC.crystalEarth), 
/*  47 */     ORDER(4, "order", Aspect.ORDER, BlocksTC.crystalOrder), 
/*  48 */     ENTROPY(5, "entropy", Aspect.ENTROPY, BlocksTC.crystalEntropy), 
/*  49 */     FLUX(6, "flux", Aspect.FLUX, BlocksTC.crystalTaint);
/*     */     
/*     */     private static final ShardType[] METADATA_LOOKUP;
/*     */     private final int metadata;
/*     */     private final String name;
/*     */     private final Aspect aspect;
/*     */     private final Block ore;
/*     */     
/*     */     private ShardType(int metadata, String unlocalizedName, Aspect aspect, Block ore)
/*     */     {
/*  59 */       this.metadata = metadata;
/*  60 */       this.name = unlocalizedName;
/*  61 */       this.aspect = aspect;
/*  62 */       this.ore = ore;
/*     */     }
/*     */     
/*     */     public int getMetadata()
/*     */     {
/*  67 */       return this.metadata;
/*     */     }
/*     */     
/*     */     public Aspect getAspect()
/*     */     {
/*  72 */       return this.aspect;
/*     */     }
/*     */     
/*     */     public Block getOre()
/*     */     {
/*  77 */       return this.ore;
/*     */     }
/*     */     
/*     */     public String getUnlocalizedName()
/*     */     {
/*  82 */       return this.name;
/*     */     }
/*     */     
/*     */     public String toString()
/*     */     {
/*  87 */       return getUnlocalizedName();
/*     */     }
/*     */     
/*     */     public static int getMetaByAspect(Aspect a)
/*     */     {
/*  92 */       ShardType[] var0 = values();
/*  93 */       int var1 = var0.length;
/*     */       
/*  95 */       for (int var2 = 0; var2 < var1; var2++)
/*     */       {
/*  97 */         if (var0[var2].getAspect() == a) { return var2;
/*     */         }
/*     */       }
/* 100 */       return -1;
/*     */     }
/*     */     
/*     */     public static ShardType byMetadata(int metadata)
/*     */     {
/* 105 */       if ((metadata < 0) || (metadata >= METADATA_LOOKUP.length))
/*     */       {
/* 107 */         metadata = 0;
/*     */       }
/*     */       
/* 110 */       return METADATA_LOOKUP[metadata];
/*     */     }
/*     */     
/*     */     public String getName()
/*     */     {
/* 115 */       return this.name;
/*     */     }
/*     */     
/*     */     static
/*     */     {
/*  51 */       METADATA_LOOKUP = new ShardType[values().length];
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 120 */       ShardType[] var0 = values();
/* 121 */       int var1 = var0.length;
/*     */       
/* 123 */       for (int var2 = 0; var2 < var1; var2++)
/*     */       {
/* 125 */         ShardType var3 = var0[var2];
/* 126 */         METADATA_LOOKUP[var3.getMetadata()] = var3;
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/items/resources/ItemShard.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */