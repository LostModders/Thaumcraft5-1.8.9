/*     */ package thaumcraft.api.crafting;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.InventoryCrafting;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.ForgeHooks;
/*     */ import net.minecraftforge.oredict.OreDictionary;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.research.ResearchHelper;
/*     */ 
/*     */ 
/*     */ public class ShapedArcaneRecipe
/*     */   implements IArcaneRecipe
/*     */ {
/*     */   private static final int MAX_CRAFT_GRID_WIDTH = 3;
/*     */   private static final int MAX_CRAFT_GRID_HEIGHT = 3;
/*  23 */   public ItemStack output = null;
/*  24 */   public Object[] input = null;
/*  25 */   public AspectList aspects = null;
/*     */   public String[] research;
/*  27 */   public int width = 0;
/*  28 */   public int height = 0;
/*  29 */   private boolean mirrored = true;
/*     */   
/*     */   public ShapedArcaneRecipe(String research, ItemStack result, AspectList aspects, Object... recipe) {
/*  32 */     this(new String[] { research }, result, aspects, recipe);
/*     */   }
/*     */   
/*     */   public ShapedArcaneRecipe(String[] research, ItemStack result, AspectList aspects, Object... recipe) {
/*  36 */     this.output = result.func_77946_l();
/*  37 */     this.research = research;
/*  38 */     this.aspects = aspects;
/*  39 */     String shape = "";
/*     */     
/*  41 */     int idx = 0;
/*     */     
/*  43 */     if ((recipe[idx] instanceof Boolean))
/*     */     {
/*  45 */       this.mirrored = ((Boolean)recipe[idx]).booleanValue();
/*  46 */       if ((recipe[(idx + 1)] instanceof Object[]))
/*     */       {
/*  48 */         recipe = (Object[])recipe[(idx + 1)];
/*     */       }
/*     */       else
/*     */       {
/*  52 */         idx = 1;
/*     */       }
/*     */     }
/*     */     
/*  56 */     if ((recipe[idx] instanceof String[]))
/*     */     {
/*  58 */       String[] parts = (String[])recipe[(idx++)];
/*     */       
/*  60 */       for (String s : parts)
/*     */       {
/*  62 */         this.width = s.length();
/*  63 */         shape = shape + s;
/*     */       }
/*     */       
/*  66 */       this.height = parts.length;
/*     */     }
/*     */     else
/*     */     {
/*  70 */       while ((recipe[idx] instanceof String))
/*     */       {
/*  72 */         String s = (String)recipe[(idx++)];
/*  73 */         shape = shape + s;
/*  74 */         this.width = s.length();
/*  75 */         this.height += 1;
/*     */       }
/*     */     }
/*     */     
/*  79 */     if (this.width * this.height != shape.length())
/*     */     {
/*  81 */       String ret = "Invalid shaped ore recipe: ";
/*  82 */       for (Object tmp : recipe)
/*     */       {
/*  84 */         ret = ret + tmp + ", ";
/*     */       }
/*  86 */       ret = ret + this.output;
/*  87 */       throw new RuntimeException(ret);
/*     */     }
/*     */     
/*  90 */     HashMap<Character, Object> itemMap = new HashMap();
/*  92 */     for (; 
/*  92 */         idx < recipe.length; idx += 2)
/*     */     {
/*  94 */       Character chr = (Character)recipe[idx];
/*  95 */       Object in = recipe[(idx + 1)];
/*     */       
/*  97 */       if ((in instanceof ItemStack))
/*     */       {
/*  99 */         itemMap.put(chr, ((ItemStack)in).func_77946_l());
/*     */       }
/* 101 */       else if ((in instanceof Item))
/*     */       {
/* 103 */         itemMap.put(chr, new ItemStack((Item)in));
/*     */       }
/* 105 */       else if ((in instanceof Block))
/*     */       {
/* 107 */         itemMap.put(chr, new ItemStack((Block)in, 1, 32767));
/*     */       }
/* 109 */       else if ((in instanceof String))
/*     */       {
/* 111 */         itemMap.put(chr, OreDictionary.getOres((String)in));
/*     */       }
/*     */       else
/*     */       {
/* 115 */         String ret = "Invalid shaped ore recipe: ";
/* 116 */         for (Object tmp : recipe)
/*     */         {
/* 118 */           ret = ret + tmp + ", ";
/*     */         }
/* 120 */         ret = ret + this.output;
/* 121 */         throw new RuntimeException(ret);
/*     */       }
/*     */     }
/*     */     
/* 125 */     this.input = new Object[this.width * this.height];
/* 126 */     int x = 0;
/* 127 */     for (char chr : shape.toCharArray())
/*     */     {
/* 129 */       this.input[(x++)] = itemMap.get(Character.valueOf(chr));
/*     */     }
/*     */   }
/*     */   
/*     */   public ItemStack func_77572_b(InventoryCrafting var1)
/*     */   {
/* 135 */     return this.output.func_77946_l();
/*     */   }
/*     */   
/* 138 */   public int func_77570_a() { return this.input.length; }
/*     */   
/*     */   public ItemStack func_77571_b() {
/* 141 */     return this.output;
/*     */   }
/*     */   
/*     */   public boolean func_77569_a(InventoryCrafting inv, World world)
/*     */   {
/* 146 */     return ((inv instanceof IArcaneWorkbench)) && (matches(inv, world, null));
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean matches(InventoryCrafting inv, World world, EntityPlayer player)
/*     */   {
/* 152 */     if ((player != null) && (this.research != null) && (this.research[0].length() > 0) && (!ResearchHelper.isResearchComplete(player.func_70005_c_(), this.research))) {
/* 153 */       return false;
/*     */     }
/* 155 */     for (int x = 0; x <= 3 - this.width; x++)
/*     */     {
/* 157 */       for (int y = 0; y <= 3 - this.height; y++)
/*     */       {
/* 159 */         if (checkMatch(inv, x, y, false))
/*     */         {
/* 161 */           return true;
/*     */         }
/*     */         
/* 164 */         if ((this.mirrored) && (checkMatch(inv, x, y, true)))
/*     */         {
/* 166 */           return true;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 171 */     return false;
/*     */   }
/*     */   
/*     */   private boolean checkMatch(InventoryCrafting inv, int startX, int startY, boolean mirror)
/*     */   {
/* 176 */     for (int x = 0; x < 3; x++)
/*     */     {
/* 178 */       for (int y = 0; y < 3; y++)
/*     */       {
/* 180 */         int subX = x - startX;
/* 181 */         int subY = y - startY;
/* 182 */         Object target = null;
/*     */         
/* 184 */         if ((subX >= 0) && (subY >= 0) && (subX < this.width) && (subY < this.height))
/*     */         {
/* 186 */           if (mirror)
/*     */           {
/* 188 */             target = this.input[(this.width - subX - 1 + subY * this.width)];
/*     */           }
/*     */           else
/*     */           {
/* 192 */             target = this.input[(subX + subY * this.width)];
/*     */           }
/*     */         }
/*     */         
/* 196 */         ItemStack slot = inv.func_70463_b(x, y);
/*     */         
/* 198 */         if ((target instanceof ItemStack))
/*     */         {
/* 200 */           if (!checkItemEquals((ItemStack)target, slot))
/*     */           {
/* 202 */             return false;
/*     */           }
/*     */         }
/* 205 */         else if ((target instanceof List))
/*     */         {
/* 207 */           boolean matched = false;
/*     */           
/* 209 */           for (ItemStack item : (List)target)
/*     */           {
/* 211 */             matched = (matched) || (checkItemEquals(item, slot));
/*     */           }
/*     */           
/* 214 */           if (!matched)
/*     */           {
/* 216 */             return false;
/*     */           }
/*     */         }
/* 219 */         else if ((target == null) && (slot != null))
/*     */         {
/* 221 */           return false;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 226 */     return true;
/*     */   }
/*     */   
/*     */   private boolean checkItemEquals(ItemStack target, ItemStack input)
/*     */   {
/* 231 */     if (((input == null) && (target != null)) || ((input != null) && (target == null)))
/*     */     {
/* 233 */       return false;
/*     */     }
/* 235 */     return (target.func_77973_b() == input.func_77973_b()) && ((!target.func_77942_o()) || (ThaumcraftApiHelper.areItemStackTagsEqualForCrafting(input, target))) && ((target.func_77952_i() == 32767) || (target.func_77952_i() == input.func_77952_i()));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ShapedArcaneRecipe setMirrored(boolean mirror)
/*     */   {
/* 242 */     this.mirrored = mirror;
/* 243 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object[] getInput()
/*     */   {
/* 253 */     return this.input;
/*     */   }
/*     */   
/*     */   public AspectList getAspects()
/*     */   {
/* 258 */     return this.aspects;
/*     */   }
/*     */   
/*     */   public AspectList getAspects(InventoryCrafting inv)
/*     */   {
/* 263 */     return this.aspects;
/*     */   }
/*     */   
/*     */   public String[] getResearch()
/*     */   {
/* 268 */     return this.research;
/*     */   }
/*     */   
/*     */ 
/*     */   public ItemStack[] func_179532_b(InventoryCrafting p_179532_1_)
/*     */   {
/* 274 */     ItemStack[] aitemstack = new ItemStack[p_179532_1_.func_70302_i_()];
/*     */     
/* 276 */     for (int i = 0; i < Math.min(9, aitemstack.length); i++)
/*     */     {
/* 278 */       ItemStack itemstack = p_179532_1_.func_70301_a(i);
/* 279 */       aitemstack[i] = ForgeHooks.getContainerItem(itemstack);
/*     */     }
/*     */     
/* 282 */     return aitemstack;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/api/crafting/ShapedArcaneRecipe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */