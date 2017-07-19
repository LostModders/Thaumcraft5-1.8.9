/*     */ package thaumcraft.api.aspects;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Set;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ 
/*     */ public class AspectList implements Serializable
/*     */ {
/*  12 */   public LinkedHashMap<Aspect, Integer> aspects = new LinkedHashMap();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public AspectList(ItemStack stack)
/*     */   {
/*     */     try
/*     */     {
/*  21 */       AspectList temp = AspectHelper.getObjectAspects(stack);
/*  22 */       if (temp != null) {
/*  23 */         for (Aspect tag : temp.getAspects()) {
/*  24 */           add(tag, temp.getAmount(tag));
/*     */         }
/*     */       }
/*     */     } catch (Exception e) {}
/*     */   }
/*     */   
/*     */   public AspectList() {}
/*     */   
/*     */   public AspectList copy() {
/*  33 */     AspectList out = new AspectList();
/*  34 */     for (Aspect a : getAspects())
/*  35 */       out.add(a, getAmount(a));
/*  36 */     return out;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int size()
/*     */   {
/*  43 */     return this.aspects.size();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int visSize()
/*     */   {
/*  50 */     int q = 0;
/*     */     
/*  52 */     for (Aspect as : this.aspects.keySet()) {
/*  53 */       q += getAmount(as);
/*     */     }
/*     */     
/*  56 */     return q;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Aspect[] getAspects()
/*     */   {
/*  63 */     return (Aspect[])this.aspects.keySet().toArray(new Aspect[0]);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Aspect[] getAspectsSortedByName()
/*     */   {
/*     */     try
/*     */     {
/*  72 */       Aspect[] out = (Aspect[])this.aspects.keySet().toArray(new Aspect[0]);
/*  73 */       boolean change = false;
/*     */       do {
/*  75 */         change = false;
/*  76 */         for (int a = 0; a < out.length - 1; a++) {
/*  77 */           Aspect e1 = out[a];
/*  78 */           Aspect e2 = out[(a + 1)];
/*  79 */           if ((e1 != null) && (e2 != null) && (e1.getTag().compareTo(e2.getTag()) > 0)) {
/*  80 */             out[a] = e2;
/*  81 */             out[(a + 1)] = e1;
/*  82 */             change = true;
/*  83 */             break;
/*     */           }
/*     */         }
/*  86 */       } while (change == true);
/*  87 */       return out;
/*     */     } catch (Exception e) {}
/*  89 */     return getAspects();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Aspect[] getAspectsSortedByAmount()
/*     */   {
/*     */     try
/*     */     {
/*  98 */       Aspect[] out = (Aspect[])this.aspects.keySet().toArray(new Aspect[0]);
/*  99 */       boolean change = false;
/*     */       do {
/* 101 */         change = false;
/* 102 */         for (int a = 0; a < out.length - 1; a++) {
/* 103 */           int e1 = getAmount(out[a]);
/* 104 */           int e2 = getAmount(out[(a + 1)]);
/* 105 */           if ((e1 > 0) && (e2 > 0) && (e2 > e1)) {
/* 106 */             Aspect ea = out[a];
/* 107 */             Aspect eb = out[(a + 1)];
/* 108 */             out[a] = eb;
/* 109 */             out[(a + 1)] = ea;
/* 110 */             change = true;
/* 111 */             break;
/*     */           }
/*     */         }
/* 114 */       } while (change == true);
/* 115 */       return out;
/*     */     } catch (Exception e) {}
/* 117 */     return getAspects();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getAmount(Aspect key)
/*     */   {
/* 126 */     return this.aspects.get(key) == null ? 0 : ((Integer)this.aspects.get(key)).intValue();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean reduce(Aspect key, int amount)
/*     */   {
/* 136 */     if (getAmount(key) >= amount) {
/* 137 */       int am = getAmount(key) - amount;
/* 138 */       this.aspects.put(key, Integer.valueOf(am));
/* 139 */       return true;
/*     */     }
/* 141 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public AspectList remove(Aspect key, int amount)
/*     */   {
/* 152 */     int am = getAmount(key) - amount;
/* 153 */     if (am <= 0) this.aspects.remove(key); else
/* 154 */       this.aspects.put(key, Integer.valueOf(am));
/* 155 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public AspectList remove(Aspect key)
/*     */   {
/* 165 */     this.aspects.remove(key);
/* 166 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public AspectList add(Aspect aspect, int amount)
/*     */   {
/* 177 */     if (this.aspects.containsKey(aspect)) {
/* 178 */       int oldamount = ((Integer)this.aspects.get(aspect)).intValue();
/* 179 */       amount += oldamount;
/*     */     }
/* 181 */     this.aspects.put(aspect, Integer.valueOf(amount));
/* 182 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public AspectList merge(Aspect aspect, int amount)
/*     */   {
/* 194 */     if (this.aspects.containsKey(aspect)) {
/* 195 */       int oldamount = ((Integer)this.aspects.get(aspect)).intValue();
/* 196 */       if (amount < oldamount) { amount = oldamount;
/*     */       }
/*     */     }
/* 199 */     this.aspects.put(aspect, Integer.valueOf(amount));
/* 200 */     return this;
/*     */   }
/*     */   
/*     */   public AspectList add(AspectList in) {
/* 204 */     for (Aspect a : in.getAspects())
/* 205 */       add(a, in.getAmount(a));
/* 206 */     return this;
/*     */   }
/*     */   
/*     */   public AspectList merge(AspectList in) {
/* 210 */     for (Aspect a : in.getAspects())
/* 211 */       merge(a, in.getAmount(a));
/* 212 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void readFromNBT(NBTTagCompound nbttagcompound)
/*     */   {
/* 222 */     this.aspects.clear();
/* 223 */     NBTTagList tlist = nbttagcompound.func_150295_c("Aspects", 10);
/* 224 */     for (int j = 0; j < tlist.func_74745_c(); j++) {
/* 225 */       NBTTagCompound rs = tlist.func_150305_b(j);
/* 226 */       if (rs.func_74764_b("key")) {
/* 227 */         add(Aspect.getAspect(rs.func_74779_i("key")), rs.func_74762_e("amount"));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void readFromNBT(NBTTagCompound nbttagcompound, String label)
/*     */   {
/* 235 */     this.aspects.clear();
/* 236 */     NBTTagList tlist = nbttagcompound.func_150295_c(label, 10);
/* 237 */     for (int j = 0; j < tlist.func_74745_c(); j++) {
/* 238 */       NBTTagCompound rs = tlist.func_150305_b(j);
/* 239 */       if (rs.func_74764_b("key")) {
/* 240 */         add(Aspect.getAspect(rs.func_74779_i("key")), rs.func_74762_e("amount"));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void writeToNBT(NBTTagCompound nbttagcompound)
/*     */   {
/* 253 */     NBTTagList tlist = new NBTTagList();
/* 254 */     nbttagcompound.func_74782_a("Aspects", tlist);
/* 255 */     for (Aspect aspect : getAspects()) {
/* 256 */       if (aspect != null) {
/* 257 */         NBTTagCompound f = new NBTTagCompound();
/* 258 */         f.func_74778_a("key", aspect.getTag());
/* 259 */         f.func_74768_a("amount", getAmount(aspect));
/* 260 */         tlist.func_74742_a(f);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void writeToNBT(NBTTagCompound nbttagcompound, String label) {
/* 266 */     NBTTagList tlist = new NBTTagList();
/* 267 */     nbttagcompound.func_74782_a(label, tlist);
/* 268 */     for (Aspect aspect : getAspects()) {
/* 269 */       if (aspect != null) {
/* 270 */         NBTTagCompound f = new NBTTagCompound();
/* 271 */         f.func_74778_a("key", aspect.getTag());
/* 272 */         f.func_74768_a("amount", getAmount(aspect));
/* 273 */         tlist.func_74742_a(f);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/api/aspects/AspectList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */