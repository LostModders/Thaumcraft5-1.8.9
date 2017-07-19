/*     */ package thaumcraft.api.aspects;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import thaumcraft.api.ThaumcraftApi;
/*     */ import thaumcraft.api.ThaumcraftApi.EntityTags;
/*     */ import thaumcraft.api.ThaumcraftApi.EntityTagsNBT;
/*     */ import thaumcraft.api.internal.IInternalMethodHandler;
/*     */ 
/*     */ public class AspectHelper
/*     */ {
/*     */   public static AspectList cullTags(AspectList temp)
/*     */   {
/*  20 */     AspectList temp2 = new AspectList();
/*  21 */     for (Aspect tag : temp.getAspects()) {
/*  22 */       if (tag != null)
/*  23 */         temp2.add(tag, temp.getAmount(tag));
/*     */     }
/*  25 */     while ((temp2 != null) && (temp2.size() > 6)) {
/*  26 */       Aspect lowest = null;
/*  27 */       float low = 32767.0F;
/*  28 */       for (Aspect tag : temp2.getAspects())
/*  29 */         if (tag != null) {
/*  30 */           float ta = temp2.getAmount(tag);
/*  31 */           if (tag.isPrimal()) {
/*  32 */             ta *= 0.9F;
/*     */           } else {
/*  34 */             if (!tag.getComponents()[0].isPrimal()) {
/*  35 */               ta *= 1.1F;
/*  36 */               if (!tag.getComponents()[0].getComponents()[0].isPrimal()) {
/*  37 */                 ta *= 1.05F;
/*     */               }
/*  39 */               if (!tag.getComponents()[0].getComponents()[1].isPrimal()) {
/*  40 */                 ta *= 1.05F;
/*     */               }
/*     */             }
/*  43 */             if (!tag.getComponents()[1].isPrimal()) {
/*  44 */               ta *= 1.1F;
/*  45 */               if (!tag.getComponents()[1].getComponents()[0].isPrimal()) {
/*  46 */                 ta *= 1.05F;
/*     */               }
/*  48 */               if (!tag.getComponents()[1].getComponents()[1].isPrimal()) {
/*  49 */                 ta *= 1.05F;
/*     */               }
/*     */             }
/*     */           }
/*     */           
/*  54 */           if (ta < low) {
/*  55 */             low = ta;
/*  56 */             lowest = tag;
/*     */           }
/*     */         }
/*  59 */       temp2.aspects.remove(lowest);
/*     */     }
/*  61 */     return temp2;
/*     */   }
/*     */   
/*     */   public static AspectList getObjectAspects(net.minecraft.item.ItemStack is) {
/*  65 */     return ThaumcraftApi.internalMethods.getObjectAspects(is);
/*     */   }
/*     */   
/*     */   public static AspectList generateTags(Item item, int meta) {
/*  69 */     return ThaumcraftApi.internalMethods.generateTags(item, meta);
/*     */   }
/*     */   
/*     */   public static AspectList getEntityAspects(Entity entity) {
/*  73 */     AspectList tags = null;
/*  74 */     if ((entity instanceof EntityPlayer)) {
/*  75 */       tags = new AspectList();
/*  76 */       tags.add(Aspect.MAN, 4);
/*  77 */       Random rand = new Random(((EntityPlayer)entity).func_70005_c_().hashCode());
/*  78 */       Aspect[] posa = (Aspect[])Aspect.aspects.values().toArray(new Aspect[0]);
/*  79 */       tags.add(posa[rand.nextInt(posa.length)], 4);
/*  80 */       tags.add(posa[rand.nextInt(posa.length)], 4);
/*  81 */       tags.add(posa[rand.nextInt(posa.length)], 4);
/*     */     }
/*     */     else {
/*  84 */       for (ThaumcraftApi.EntityTags et : ThaumcraftApi.scanEntities)
/*  85 */         if (et.entityName.equals(net.minecraft.entity.EntityList.func_75621_b(entity)))
/*  86 */           if ((et.nbts == null) || (et.nbts.length == 0)) {
/*  87 */             tags = et.aspects;
/*     */           } else {
/*  89 */             NBTTagCompound tc = new NBTTagCompound();
/*  90 */             entity.func_70109_d(tc);
/*  91 */             ThaumcraftApi.EntityTagsNBT[] arr$ = et.nbts;int len$ = arr$.length; for (int i$ = 0;; i$++) { if (i$ >= len$) break label271; ThaumcraftApi.EntityTagsNBT nbt = arr$[i$];
/*  92 */               if ((!tc.func_74764_b(nbt.name)) || 
/*  93 */                 (!thaumcraft.api.ThaumcraftApiHelper.getNBTDataFromId(tc, tc.func_150299_b(nbt.name), nbt.name).equals(nbt.value))) {
/*     */                 break;
/*     */               }
/*     */             }
/*     */             
/*  98 */             tags = et.aspects;
/*     */           }
/*     */     }
/*     */     label271:
/* 102 */     return tags;
/*     */   }
/*     */   
/*     */   public static Aspect getCombinationResult(Aspect aspect1, Aspect aspect2) {
/* 106 */     Collection<Aspect> aspects = Aspect.aspects.values();
/* 107 */     for (Aspect aspect : aspects) {
/* 108 */       if ((aspect.getComponents() != null) && (((aspect.getComponents()[0] == aspect1) && (aspect.getComponents()[1] == aspect2)) || ((aspect.getComponents()[0] == aspect2) && (aspect.getComponents()[1] == aspect1))))
/*     */       {
/*     */ 
/*     */ 
/* 112 */         return aspect;
/*     */       }
/*     */     }
/* 115 */     return null;
/*     */   }
/*     */   
/*     */   public static Aspect getRandomPrimal(Random rand, Aspect aspect) {
/* 119 */     ArrayList<Aspect> list = new ArrayList();
/* 120 */     if (aspect != null) {
/* 121 */       AspectList temp = new AspectList();
/* 122 */       temp.add(aspect, 1);
/* 123 */       AspectList temp2 = reduceToPrimals(temp);
/* 124 */       for (Aspect a : temp2.getAspects()) {
/* 125 */         for (int b = 0; b < temp2.getAmount(a); b++) {
/* 126 */           list.add(a);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 131 */     return list.size() > 0 ? (Aspect)list.get(rand.nextInt(list.size())) : null;
/*     */   }
/*     */   
/*     */   public static AspectList reduceToPrimals(AspectList in) {
/* 135 */     AspectList out = new AspectList();
/* 136 */     for (Aspect aspect : in.getAspects()) {
/* 137 */       if (aspect != null) {
/* 138 */         if (aspect.isPrimal()) {
/* 139 */           out.add(aspect, in.getAmount(aspect));
/*     */         } else {
/* 141 */           AspectList temp = new AspectList();
/* 142 */           temp.add(aspect.getComponents()[0], in.getAmount(aspect));
/* 143 */           temp.add(aspect.getComponents()[1], in.getAmount(aspect));
/* 144 */           AspectList temp2 = reduceToPrimals(temp);
/*     */           
/* 146 */           for (Aspect a : temp2.getAspects()) {
/* 147 */             out.add(a, temp2.getAmount(a));
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 152 */     return out;
/*     */   }
/*     */   
/*     */   public static AspectList getPrimalAspects(AspectList in) {
/* 156 */     AspectList t = new AspectList();
/* 157 */     t.add(Aspect.AIR, in.getAmount(Aspect.AIR));
/* 158 */     t.add(Aspect.FIRE, in.getAmount(Aspect.FIRE));
/* 159 */     t.add(Aspect.WATER, in.getAmount(Aspect.WATER));
/* 160 */     t.add(Aspect.EARTH, in.getAmount(Aspect.EARTH));
/* 161 */     t.add(Aspect.ORDER, in.getAmount(Aspect.ORDER));
/* 162 */     t.add(Aspect.ENTROPY, in.getAmount(Aspect.ENTROPY));
/* 163 */     return t;
/*     */   }
/*     */   
/*     */   public static AspectList getAuraAspects(AspectList in) {
/* 167 */     AspectList t = new AspectList();
/* 168 */     t.add(Aspect.AIR, in.getAmount(Aspect.AIR));
/* 169 */     t.add(Aspect.FIRE, in.getAmount(Aspect.FIRE));
/* 170 */     t.add(Aspect.WATER, in.getAmount(Aspect.WATER));
/* 171 */     t.add(Aspect.EARTH, in.getAmount(Aspect.EARTH));
/* 172 */     t.add(Aspect.ORDER, in.getAmount(Aspect.ORDER));
/* 173 */     t.add(Aspect.ENTROPY, in.getAmount(Aspect.ENTROPY));
/* 174 */     t.add(Aspect.FLUX, in.getAmount(Aspect.FLUX));
/* 175 */     return t;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/api/aspects/AspectHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */