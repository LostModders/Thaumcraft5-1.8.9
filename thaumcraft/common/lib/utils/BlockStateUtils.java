/*     */ package thaumcraft.common.lib.utils;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Set;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockStateUtils
/*     */ {
/*     */   public static EnumFacing getFacing(IBlockState state)
/*     */   {
/*  20 */     return EnumFacing.func_82600_a(state.func_177230_c().func_176201_c(state) & 0x7);
/*     */   }
/*     */   
/*     */   public static EnumFacing getFacing(int meta)
/*     */   {
/*  25 */     return EnumFacing.func_82600_a(meta & 0x7);
/*     */   }
/*     */   
/*     */   public static boolean isEnabled(IBlockState state)
/*     */   {
/*  30 */     return (state.func_177230_c().func_176201_c(state) & 0x8) != 8;
/*     */   }
/*     */   
/*     */   public static boolean isEnabled(int meta)
/*     */   {
/*  35 */     return (meta & 0x8) != 8;
/*     */   }
/*     */   
/*     */ 
/*     */   public static IProperty getPropertyByName(IBlockState blockState, String propertyName)
/*     */   {
/*  41 */     for (IProperty property : blockState.func_177228_b().keySet())
/*     */     {
/*  43 */       if (property.func_177701_a().equals(propertyName)) { return property;
/*     */       }
/*     */     }
/*  46 */     return null;
/*     */   }
/*     */   
/*     */   public static boolean isValidPropertyName(IBlockState blockState, String propertyName)
/*     */   {
/*  51 */     return getPropertyByName(blockState, propertyName) != null;
/*     */   }
/*     */   
/*     */   public static Comparable getPropertyValueByName(IBlockState blockState, IProperty property, String valueName)
/*     */   {
/*  56 */     for (Comparable value : (ImmutableSet)property.func_177700_c())
/*     */     {
/*  58 */       if (value.toString().equals(valueName)) { return value;
/*     */       }
/*     */     }
/*  61 */     return null;
/*     */   }
/*     */   
/*     */   public static ImmutableSet<IBlockState> getValidStatesForProperties(IBlockState baseState, IProperty... properties)
/*     */   {
/*  66 */     if (properties == null) { return null;
/*     */     }
/*  68 */     Set<IBlockState> validStates = Sets.newHashSet();
/*  69 */     PropertyIndexer propertyIndexer = new PropertyIndexer(properties, null);
/*     */     
/*     */     do
/*     */     {
/*  73 */       IBlockState currentState = baseState;
/*     */       
/*  75 */       for (IProperty property : properties)
/*     */       {
/*  77 */         IndexedProperty indexedProperty = propertyIndexer.getIndexedProperty(property);
/*     */         
/*  79 */         currentState = currentState.func_177226_a(property, indexedProperty.getCurrentValue());
/*     */       }
/*     */       
/*  82 */       validStates.add(currentState);
/*     */     }
/*  84 */     while (propertyIndexer.increment());
/*     */     
/*  86 */     return ImmutableSet.copyOf(validStates);
/*     */   }
/*     */   
/*     */   private static class PropertyIndexer
/*     */   {
/*  91 */     private HashMap<IProperty, BlockStateUtils.IndexedProperty> indexedProperties = new HashMap();
/*     */     
/*     */     private IProperty finalProperty;
/*     */     
/*     */     private PropertyIndexer(IProperty... properties)
/*     */     {
/*  97 */       this.finalProperty = properties[(properties.length - 1)];
/*     */       
/*  99 */       BlockStateUtils.IndexedProperty previousIndexedProperty = null;
/*     */       
/* 101 */       for (IProperty property : properties)
/*     */       {
/* 103 */         BlockStateUtils.IndexedProperty indexedProperty = new BlockStateUtils.IndexedProperty(property, null);
/*     */         
/* 105 */         if (previousIndexedProperty != null)
/*     */         {
/* 107 */           indexedProperty.parent = previousIndexedProperty;
/* 108 */           previousIndexedProperty.child = indexedProperty;
/*     */         }
/*     */         
/* 111 */         this.indexedProperties.put(property, indexedProperty);
/* 112 */         previousIndexedProperty = indexedProperty;
/*     */       }
/*     */     }
/*     */     
/*     */     public boolean increment()
/*     */     {
/* 118 */       return ((BlockStateUtils.IndexedProperty)this.indexedProperties.get(this.finalProperty)).increment();
/*     */     }
/*     */     
/*     */     public BlockStateUtils.IndexedProperty getIndexedProperty(IProperty property)
/*     */     {
/* 123 */       return (BlockStateUtils.IndexedProperty)this.indexedProperties.get(property);
/*     */     }
/*     */   }
/*     */   
/*     */   private static class IndexedProperty
/*     */   {
/* 129 */     private ArrayList<Comparable> validValues = new ArrayList();
/*     */     
/*     */     private int maxCount;
/*     */     
/*     */     private int counter;
/*     */     private IndexedProperty parent;
/*     */     private IndexedProperty child;
/*     */     
/*     */     private IndexedProperty(IProperty property)
/*     */     {
/* 139 */       this.validValues.addAll(property.func_177700_c());
/* 140 */       this.maxCount = (this.validValues.size() - 1);
/*     */     }
/*     */     
/*     */     public boolean increment()
/*     */     {
/* 145 */       if (this.counter < this.maxCount) { this.counter += 1;
/*     */       }
/*     */       else {
/* 148 */         if (hasParent())
/*     */         {
/* 150 */           resetSelfAndChildren();
/* 151 */           return this.parent.increment();
/*     */         }
/* 153 */         return false;
/*     */       }
/*     */       
/* 156 */       return true;
/*     */     }
/*     */     
/*     */     public void resetSelfAndChildren()
/*     */     {
/* 161 */       this.counter = 0;
/* 162 */       if (hasChild()) this.child.resetSelfAndChildren();
/*     */     }
/*     */     
/*     */     public boolean hasParent()
/*     */     {
/* 167 */       return this.parent != null;
/*     */     }
/*     */     
/*     */     public boolean hasChild()
/*     */     {
/* 172 */       return this.child != null;
/*     */     }
/*     */     
/*     */     public int getCounter()
/*     */     {
/* 177 */       return this.counter;
/*     */     }
/*     */     
/*     */     public int getMaxCount()
/*     */     {
/* 182 */       return this.maxCount;
/*     */     }
/*     */     
/*     */     public Comparable getCurrentValue()
/*     */     {
/* 187 */       return (Comparable)this.validValues.get(this.counter);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/utils/BlockStateUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */