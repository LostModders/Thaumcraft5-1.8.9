/*     */ package thaumcraft.api.wands;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.world.World;
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
/*     */ public class WandTriggerRegistry
/*     */ {
/*  27 */   private static HashMap<String, LinkedHashMap<IBlockState, List<Trigger>>> triggers = new HashMap();
/*     */   private static final String DEFAULT = "default";
/*     */   
/*     */   private static class Trigger {
/*     */     IWandTriggerManager manager;
/*     */     int event;
/*     */     
/*     */     public Trigger(IWandTriggerManager manager, int event) {
/*  35 */       this.manager = manager;
/*  36 */       this.event = event;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void registerWandBlockTrigger(IWandTriggerManager manager, int event, IBlockState state, String modid)
/*     */   {
/*  50 */     if (!triggers.containsKey(modid)) {
/*  51 */       triggers.put(modid, new LinkedHashMap());
/*     */     }
/*  53 */     LinkedHashMap<IBlockState, List<Trigger>> temp = (LinkedHashMap)triggers.get(modid);
/*  54 */     List<Trigger> ts = (List)temp.get(state);
/*  55 */     if (ts == null) ts = new ArrayList();
/*  56 */     ts.add(new Trigger(manager, event));
/*  57 */     temp.put(state, ts);
/*  58 */     triggers.put(modid, temp);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void registerWandBlockTrigger(IWandTriggerManager manager, int event, IBlockState state)
/*     */   {
/*  65 */     registerWandBlockTrigger(manager, event, state, "default");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean hasTrigger(IBlockState state)
/*     */   {
/*  75 */     for (String modid : triggers.keySet()) {
/*  76 */       LinkedHashMap<IBlockState, List<Trigger>> temp = (LinkedHashMap)triggers.get(modid);
/*  77 */       if (temp.containsKey(state)) return true;
/*     */     }
/*  79 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static boolean hasTrigger(IBlockState state, String modid)
/*     */   {
/*  86 */     if (!triggers.containsKey(modid)) return false;
/*  87 */     LinkedHashMap<IBlockState, List<Trigger>> temp = (LinkedHashMap)triggers.get(modid);
/*  88 */     if (temp.containsKey(state)) return true;
/*  89 */     return false;
/*     */   }
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
/*     */   public static boolean performTrigger(World world, ItemStack wand, EntityPlayer player, BlockPos pos, EnumFacing side, IBlockState state)
/*     */   {
/* 109 */     for (String modid : triggers.keySet()) {
/* 110 */       LinkedHashMap<IBlockState, List<Trigger>> temp = (LinkedHashMap)triggers.get(modid);
/* 111 */       List<Trigger> l = (List)temp.get(state);
/* 112 */       if ((l != null) && (l.size() != 0))
/* 113 */         for (Trigger trig : l) {
/* 114 */           boolean result = trig.manager.performTrigger(world, wand, player, pos, side, trig.event);
/* 115 */           if (result) return true;
/*     */         }
/*     */     }
/* 118 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean performTrigger(World world, ItemStack wand, EntityPlayer player, BlockPos pos, EnumFacing side, IBlockState state, String modid)
/*     */   {
/* 126 */     if (!triggers.containsKey(modid)) return false;
/* 127 */     LinkedHashMap<IBlockState, List<Trigger>> temp = (LinkedHashMap)triggers.get(modid);
/* 128 */     List<Trigger> l = (List)temp.get(state);
/* 129 */     if ((l == null) || (l.size() == 0)) return false;
/* 130 */     for (Trigger trig : l) {
/* 131 */       boolean result = trig.manager.performTrigger(world, wand, player, pos, side, trig.event);
/* 132 */       if (result) return true;
/*     */     }
/* 134 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/api/wands/WandTriggerRegistry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */