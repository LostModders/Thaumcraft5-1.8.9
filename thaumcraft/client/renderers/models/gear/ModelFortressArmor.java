/*     */ package thaumcraft.client.renderers.models.gear;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.common.items.armor.ItemFortressArmor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ModelFortressArmor
/*     */   extends ModelCustomArmor
/*     */ {
/*     */   ModelRenderer OrnamentL;
/*     */   ModelRenderer OrnamentL2;
/*     */   ModelRenderer OrnamentR;
/*     */   ModelRenderer OrnamentR2;
/*     */   ModelRenderer Helmet;
/*     */   ModelRenderer HelmetR;
/*     */   ModelRenderer HelmetL;
/*     */   ModelRenderer HelmetB;
/*     */   ModelRenderer capsthingy;
/*     */   ModelRenderer flapR;
/*     */   ModelRenderer flapL;
/*     */   ModelRenderer Gemornament;
/*     */   ModelRenderer Gem;
/*     */   ModelRenderer[] Mask;
/*     */   ModelRenderer Goggles;
/*     */   ModelRenderer BeltR;
/*     */   ModelRenderer Mbelt;
/*     */   ModelRenderer MbeltL;
/*     */   ModelRenderer MbeltR;
/*     */   ModelRenderer BeltL;
/*     */   ModelRenderer Chestplate;
/*     */   ModelRenderer Scroll;
/*     */   ModelRenderer Backplate;
/*     */   ModelRenderer Book;
/*     */   ModelRenderer ShoulderR;
/*     */   ModelRenderer GauntletR;
/*     */   ModelRenderer GauntletstrapR1;
/*     */   ModelRenderer GauntletstrapR2;
/*     */   ModelRenderer ShoulderplateRtop;
/*     */   ModelRenderer ShoulderplateR1;
/*     */   ModelRenderer ShoulderplateR2;
/*     */   ModelRenderer ShoulderplateR3;
/*     */   ModelRenderer ShoulderL;
/*     */   ModelRenderer GauntletL;
/*     */   ModelRenderer Gauntletstrapl1;
/*     */   ModelRenderer GauntletstrapL2;
/*     */   ModelRenderer ShoulderplateLtop;
/*     */   ModelRenderer ShoulderplateL1;
/*     */   ModelRenderer ShoulderplateL2;
/*     */   ModelRenderer ShoulderplateL3;
/*     */   ModelRenderer LegpanelR1;
/*     */   ModelRenderer LegpanelR2;
/*     */   ModelRenderer LegpanelR3;
/*     */   ModelRenderer LegpanelR4;
/*     */   ModelRenderer LegpanelR5;
/*     */   ModelRenderer LegpanelR6;
/*     */   ModelRenderer SidepanelR1;
/*     */   ModelRenderer SidepanelR2;
/*     */   ModelRenderer SidepanelR3;
/*     */   ModelRenderer BackpanelR1;
/*     */   ModelRenderer BackpanelR2;
/*     */   ModelRenderer BackpanelR3;
/*     */   ModelRenderer BackpanelL3;
/*     */   ModelRenderer LegpanelL1;
/*     */   ModelRenderer LegpanelL2;
/*     */   ModelRenderer LegpanelL3;
/*     */   ModelRenderer LegpanelL4;
/*     */   ModelRenderer LegpanelL5;
/*     */   ModelRenderer LegpanelL6;
/*     */   ModelRenderer SidepanelL1;
/*     */   ModelRenderer SidepanelL2;
/*     */   ModelRenderer SidepanelL3;
/*     */   ModelRenderer BackpanelL1;
/*     */   ModelRenderer BackpanelL2;
/*     */   
/*     */   public ModelFortressArmor(float f)
/*     */   {
/*  89 */     super(f, 0, 128, 64);
/*  90 */     this.field_78090_t = 128;
/*  91 */     this.field_78089_u = 64;
/*     */     
/*  93 */     this.Mask = new ModelRenderer[3];
/*  94 */     for (int a = 0; a < 3; a++) {
/*  95 */       this.Mask[a] = new ModelRenderer(this, 52 + a * 24, 2);
/*  96 */       this.Mask[a].func_78789_a(-4.5F, -5.0F, -4.6F, 9, 5, 1);
/*  97 */       this.Mask[a].func_78793_a(0.0F, 0.0F, 0.0F);
/*  98 */       this.Mask[a].func_78787_b(128, 64);
/*  99 */       setRotation(this.Mask[a], 0.0F, 0.0F, 0.0F);
/*     */     }
/*     */     
/* 102 */     this.Goggles = new ModelRenderer(this, 100, 18);
/* 103 */     this.Goggles.func_78789_a(-4.5F, -5.0F, -4.25F, 9, 5, 1);
/* 104 */     this.Goggles.func_78793_a(0.0F, 0.0F, 0.0F);
/* 105 */     this.Goggles.func_78787_b(128, 64);
/* 106 */     setRotation(this.Goggles, 0.0F, 0.0F, 0.0F);
/*     */     
/* 108 */     this.OrnamentL = new ModelRenderer(this, 78, 8);
/* 109 */     this.OrnamentL.field_78809_i = true;
/* 110 */     this.OrnamentL.func_78789_a(1.5F, -9.0F, -6.5F, 2, 2, 1);
/* 111 */     this.OrnamentL.func_78793_a(0.0F, 0.0F, 0.0F);
/* 112 */     this.OrnamentL.func_78787_b(128, 64);
/*     */     
/* 114 */     setRotation(this.OrnamentL, -0.1396263F, 0.0F, 0.0F);
/* 115 */     this.OrnamentL2 = new ModelRenderer(this, 78, 8);
/* 116 */     this.OrnamentL2.field_78809_i = true;
/* 117 */     this.OrnamentL2.func_78789_a(3.5F, -10.0F, -6.5F, 1, 2, 1);
/* 118 */     this.OrnamentL2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 119 */     this.OrnamentL2.func_78787_b(128, 64);
/*     */     
/* 121 */     setRotation(this.OrnamentL2, -0.1396263F, 0.0F, 0.0F);
/* 122 */     this.OrnamentR = new ModelRenderer(this, 78, 8);
/* 123 */     this.OrnamentR.func_78789_a(-3.5F, -9.0F, -6.5F, 2, 2, 1);
/* 124 */     this.OrnamentR.func_78793_a(0.0F, 0.0F, 0.0F);
/* 125 */     this.OrnamentR.func_78787_b(128, 64);
/* 126 */     setRotation(this.OrnamentR, -0.1396263F, 0.0F, 0.0F);
/* 127 */     this.OrnamentR2 = new ModelRenderer(this, 78, 8);
/* 128 */     this.OrnamentR2.func_78789_a(-4.5F, -10.0F, -6.5F, 1, 2, 1);
/* 129 */     this.OrnamentR2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 130 */     this.OrnamentR2.func_78787_b(128, 64);
/* 131 */     setRotation(this.OrnamentR2, -0.1396263F, 0.0F, 0.0F);
/* 132 */     this.Helmet = new ModelRenderer(this, 41, 8);
/* 133 */     this.Helmet.func_78789_a(-4.5F, -9.0F, -4.5F, 9, 4, 9);
/* 134 */     this.Helmet.func_78793_a(0.0F, 0.0F, 0.0F);
/* 135 */     this.Helmet.func_78787_b(128, 64);
/* 136 */     setRotation(this.Helmet, 0.0F, 0.0F, 0.0F);
/* 137 */     this.HelmetR = new ModelRenderer(this, 21, 13);
/* 138 */     this.HelmetR.func_78789_a(-6.5F, -3.0F, -4.5F, 1, 5, 9);
/* 139 */     this.HelmetR.func_78793_a(0.0F, 0.0F, 0.0F);
/* 140 */     this.HelmetR.func_78787_b(128, 64);
/* 141 */     setRotation(this.HelmetR, 0.0F, 0.0F, 0.5235988F);
/* 142 */     this.HelmetL = new ModelRenderer(this, 21, 13);
/* 143 */     this.HelmetL.field_78809_i = true;
/* 144 */     this.HelmetL.func_78789_a(5.5F, -3.0F, -4.5F, 1, 5, 9);
/* 145 */     this.HelmetL.func_78793_a(0.0F, 0.0F, 0.0F);
/* 146 */     this.HelmetL.func_78787_b(128, 64);
/*     */     
/* 148 */     setRotation(this.HelmetL, 0.0F, 0.0F, -0.5235988F);
/* 149 */     this.HelmetB = new ModelRenderer(this, 41, 21);
/* 150 */     this.HelmetB.func_78789_a(-4.5F, -3.0F, 5.5F, 9, 5, 1);
/* 151 */     this.HelmetB.func_78793_a(0.0F, 0.0F, 0.0F);
/* 152 */     this.HelmetB.func_78787_b(128, 64);
/* 153 */     setRotation(this.HelmetB, 0.5235988F, 0.0F, 0.0F);
/* 154 */     this.capsthingy = new ModelRenderer(this, 21, 0);
/* 155 */     this.capsthingy.func_78789_a(-4.5F, -6.0F, -6.5F, 9, 1, 2);
/* 156 */     this.capsthingy.func_78793_a(0.0F, 0.0F, 0.0F);
/* 157 */     this.capsthingy.func_78787_b(128, 64);
/* 158 */     setRotation(this.capsthingy, 0.0F, 0.0F, 0.0F);
/*     */     
/* 160 */     this.flapR = new ModelRenderer(this, 59, 10);
/* 161 */     this.flapR.func_78789_a(-10.0F, -2.0F, -1.0F, 3, 3, 1);
/* 162 */     this.flapR.func_78793_a(0.0F, 0.0F, 0.0F);
/* 163 */     this.flapR.func_78787_b(128, 64);
/* 164 */     setRotation(this.flapR, 0.0F, -0.5235988F, 0.5235988F);
/*     */     
/* 166 */     this.flapL = new ModelRenderer(this, 59, 10);
/* 167 */     this.flapL.field_78809_i = true;
/* 168 */     this.flapL.func_78789_a(7.0F, -2.0F, -1.0F, 3, 3, 1);
/* 169 */     this.flapL.func_78793_a(0.0F, 0.0F, 0.0F);
/* 170 */     this.flapL.func_78787_b(128, 64);
/* 171 */     setRotation(this.flapL, 0.0F, 0.5235988F, -0.5235988F);
/*     */     
/* 173 */     this.Gemornament = new ModelRenderer(this, 68, 11);
/* 174 */     this.Gemornament.func_78789_a(-1.5F, -9.0F, -7.0F, 3, 3, 2);
/* 175 */     this.Gemornament.func_78793_a(0.0F, 0.0F, 0.0F);
/* 176 */     this.Gemornament.func_78787_b(128, 64);
/* 177 */     setRotation(this.Gemornament, -0.1396263F, 0.0F, 0.0F);
/* 178 */     this.Gem = new ModelRenderer(this, 72, 8);
/* 179 */     this.Gem.func_78789_a(-1.0F, -8.5F, -7.5F, 2, 2, 1);
/* 180 */     this.Gem.func_78793_a(0.0F, 0.0F, 0.0F);
/* 181 */     this.Gem.func_78787_b(128, 64);
/* 182 */     setRotation(this.Gem, -0.1396263F, 0.0F, 0.0F);
/* 183 */     this.BeltR = new ModelRenderer(this, 76, 44);
/* 184 */     this.BeltR.func_78789_a(-5.0F, 4.0F, -3.0F, 1, 3, 6);
/* 185 */     this.BeltR.func_78793_a(0.0F, 0.0F, 0.0F);
/* 186 */     this.BeltR.func_78787_b(128, 64);
/* 187 */     setRotation(this.BeltR, 0.0F, 0.0F, 0.0F);
/* 188 */     this.Mbelt = new ModelRenderer(this, 56, 55);
/* 189 */     this.Mbelt.func_78789_a(-4.0F, 8.0F, -3.0F, 8, 4, 1);
/* 190 */     this.Mbelt.func_78793_a(0.0F, 0.0F, 0.0F);
/* 191 */     this.Mbelt.func_78787_b(128, 64);
/* 192 */     setRotation(this.Mbelt, 0.0F, 0.0F, 0.0F);
/* 193 */     this.MbeltL = new ModelRenderer(this, 76, 44);
/* 194 */     this.MbeltL.func_78789_a(4.0F, 8.0F, -3.0F, 1, 3, 6);
/* 195 */     this.MbeltL.func_78793_a(0.0F, 0.0F, 0.0F);
/* 196 */     this.MbeltL.func_78787_b(128, 64);
/* 197 */     setRotation(this.MbeltL, 0.0F, 0.0F, 0.0F);
/* 198 */     this.MbeltR = new ModelRenderer(this, 76, 44);
/* 199 */     this.MbeltR.func_78789_a(-5.0F, 8.0F, -3.0F, 1, 3, 6);
/* 200 */     this.MbeltR.func_78793_a(0.0F, 0.0F, 0.0F);
/* 201 */     this.MbeltR.func_78787_b(128, 64);
/* 202 */     setRotation(this.MbeltR, 0.0F, 0.0F, 0.0F);
/* 203 */     this.BeltL = new ModelRenderer(this, 76, 44);
/* 204 */     this.BeltL.func_78789_a(4.0F, 4.0F, -3.0F, 1, 3, 6);
/* 205 */     this.BeltL.func_78793_a(0.0F, 0.0F, 0.0F);
/* 206 */     this.BeltL.func_78787_b(128, 64);
/* 207 */     setRotation(this.BeltL, 0.0F, 0.0F, 0.0F);
/* 208 */     this.Chestplate = new ModelRenderer(this, 56, 45);
/* 209 */     this.Chestplate.func_78789_a(-4.0F, 1.0F, -4.0F, 8, 7, 2);
/* 210 */     this.Chestplate.func_78793_a(0.0F, 0.0F, 0.0F);
/* 211 */     this.Chestplate.func_78787_b(128, 64);
/* 212 */     setRotation(this.Chestplate, 0.0F, 0.0F, 0.0F);
/* 213 */     this.Scroll = new ModelRenderer(this, 34, 27);
/* 214 */     this.Scroll.func_78789_a(-2.0F, 9.5F, 4.0F, 8, 3, 3);
/* 215 */     this.Scroll.func_78793_a(0.0F, 0.0F, 0.0F);
/* 216 */     this.Scroll.func_78787_b(128, 64);
/* 217 */     setRotation(this.Scroll, 0.0F, 0.0F, 0.1919862F);
/* 218 */     this.Backplate = new ModelRenderer(this, 36, 45);
/* 219 */     this.Backplate.func_78789_a(-4.0F, 1.0F, 2.0F, 8, 11, 2);
/* 220 */     this.Backplate.func_78793_a(0.0F, 0.0F, 0.0F);
/* 221 */     this.Backplate.func_78787_b(128, 64);
/* 222 */     setRotation(this.Backplate, 0.0F, 0.0F, 0.0F);
/* 223 */     this.Book = new ModelRenderer(this, 100, 8);
/* 224 */     this.Book.func_78789_a(1.0F, -0.3F, 4.0F, 5, 7, 2);
/* 225 */     this.Book.func_78793_a(0.0F, 0.0F, 0.0F);
/* 226 */     this.Book.func_78787_b(128, 64);
/* 227 */     setRotation(this.Book, 0.0F, 0.0F, 0.7679449F);
/* 228 */     this.ShoulderR = new ModelRenderer(this, 56, 35);
/* 229 */     this.ShoulderR.func_78789_a(-3.5F, -2.5F, -2.5F, 5, 5, 5);
/* 230 */     this.ShoulderR.func_78793_a(0.0F, 0.0F, 0.0F);
/* 231 */     this.ShoulderR.func_78787_b(128, 64);
/* 232 */     setRotation(this.ShoulderR, 0.0F, 0.0F, 0.0F);
/* 233 */     this.GauntletR = new ModelRenderer(this, 100, 26);
/* 234 */     this.GauntletR.func_78789_a(-3.5F, 3.5F, -2.5F, 2, 6, 5);
/* 235 */     this.GauntletR.func_78793_a(0.0F, 0.0F, 0.0F);
/* 236 */     this.GauntletR.func_78787_b(128, 64);
/* 237 */     setRotation(this.GauntletR, 0.0F, 0.0F, 0.0F);
/* 238 */     this.GauntletstrapR1 = new ModelRenderer(this, 84, 31);
/* 239 */     this.GauntletstrapR1.func_78789_a(-1.5F, 3.5F, -2.5F, 3, 1, 5);
/* 240 */     this.GauntletstrapR1.func_78793_a(0.0F, 0.0F, 0.0F);
/* 241 */     this.GauntletstrapR1.func_78787_b(128, 64);
/* 242 */     setRotation(this.GauntletstrapR1, 0.0F, 0.0F, 0.0F);
/* 243 */     this.GauntletstrapR2 = new ModelRenderer(this, 84, 31);
/* 244 */     this.GauntletstrapR2.func_78789_a(-1.5F, 6.5F, -2.5F, 3, 1, 5);
/* 245 */     this.GauntletstrapR2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 246 */     this.GauntletstrapR2.func_78787_b(128, 64);
/* 247 */     setRotation(this.GauntletstrapR2, 0.0F, 0.0F, 0.0F);
/* 248 */     this.ShoulderplateRtop = new ModelRenderer(this, 110, 37);
/* 249 */     this.ShoulderplateRtop.func_78789_a(-5.5F, -2.5F, -3.5F, 2, 1, 7);
/* 250 */     this.ShoulderplateRtop.func_78793_a(0.0F, 0.0F, 0.0F);
/* 251 */     this.ShoulderplateRtop.func_78787_b(128, 64);
/* 252 */     setRotation(this.ShoulderplateRtop, 0.0F, 0.0F, 0.4363323F);
/* 253 */     this.ShoulderplateR1 = new ModelRenderer(this, 110, 45);
/* 254 */     this.ShoulderplateR1.func_78789_a(-4.5F, -1.5F, -3.5F, 1, 4, 7);
/* 255 */     this.ShoulderplateR1.func_78793_a(0.0F, 0.0F, 0.0F);
/* 256 */     this.ShoulderplateR1.func_78787_b(128, 64);
/* 257 */     setRotation(this.ShoulderplateR1, 0.0F, 0.0F, 0.4363323F);
/* 258 */     this.ShoulderplateR2 = new ModelRenderer(this, 94, 45);
/* 259 */     this.ShoulderplateR2.func_78789_a(-3.5F, 1.5F, -3.5F, 1, 3, 7);
/* 260 */     this.ShoulderplateR2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 261 */     this.ShoulderplateR2.func_78787_b(128, 64);
/* 262 */     setRotation(this.ShoulderplateR2, 0.0F, 0.0F, 0.4363323F);
/* 263 */     this.ShoulderplateR3 = new ModelRenderer(this, 94, 45);
/* 264 */     this.ShoulderplateR3.func_78789_a(-2.5F, 3.5F, -3.5F, 1, 3, 7);
/* 265 */     this.ShoulderplateR3.func_78793_a(0.0F, 0.0F, 0.0F);
/* 266 */     this.ShoulderplateR3.func_78787_b(128, 64);
/* 267 */     setRotation(this.ShoulderplateR3, 0.0F, 0.0F, 0.4363323F);
/*     */     
/* 269 */     this.ShoulderL = new ModelRenderer(this, 56, 35);
/* 270 */     this.ShoulderL.field_78809_i = true;
/* 271 */     this.ShoulderL.func_78789_a(-1.5F, -2.5F, -2.5F, 5, 5, 5);
/* 272 */     this.ShoulderL.func_78793_a(0.0F, 0.0F, 0.0F);
/* 273 */     this.ShoulderL.func_78787_b(128, 64);
/* 274 */     setRotation(this.ShoulderL, 0.0F, 0.0F, 0.0F);
/*     */     
/* 276 */     this.GauntletL = new ModelRenderer(this, 114, 26);
/* 277 */     this.GauntletL.func_78789_a(1.5F, 3.5F, -2.5F, 2, 6, 5);
/* 278 */     this.GauntletL.func_78793_a(0.0F, 0.0F, 0.0F);
/* 279 */     this.GauntletL.func_78787_b(128, 64);
/* 280 */     setRotation(this.GauntletL, 0.0F, 0.0F, 0.0F);
/*     */     
/* 282 */     this.Gauntletstrapl1 = new ModelRenderer(this, 84, 31);
/* 283 */     this.Gauntletstrapl1.field_78809_i = true;
/* 284 */     this.Gauntletstrapl1.func_78789_a(-1.5F, 3.5F, -2.5F, 3, 1, 5);
/* 285 */     this.Gauntletstrapl1.func_78793_a(0.0F, 0.0F, 0.0F);
/* 286 */     this.Gauntletstrapl1.func_78787_b(128, 64);
/* 287 */     setRotation(this.Gauntletstrapl1, 0.0F, 0.0F, 0.0F);
/*     */     
/* 289 */     this.GauntletstrapL2 = new ModelRenderer(this, 84, 31);
/* 290 */     this.GauntletstrapL2.field_78809_i = true;
/* 291 */     this.GauntletstrapL2.func_78789_a(-1.5F, 6.5F, -2.5F, 3, 1, 5);
/* 292 */     this.GauntletstrapL2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 293 */     this.GauntletstrapL2.func_78787_b(128, 64);
/* 294 */     setRotation(this.GauntletstrapL2, 0.0F, 0.0F, 0.0F);
/*     */     
/* 296 */     this.ShoulderplateLtop = new ModelRenderer(this, 110, 37);
/* 297 */     this.ShoulderplateLtop.field_78809_i = true;
/* 298 */     this.ShoulderplateLtop.func_78789_a(3.5F, -2.5F, -3.5F, 2, 1, 7);
/* 299 */     this.ShoulderplateLtop.func_78793_a(0.0F, 0.0F, 0.0F);
/* 300 */     this.ShoulderplateLtop.func_78787_b(128, 64);
/* 301 */     setRotation(this.ShoulderplateLtop, 0.0F, 0.0F, -0.4363323F);
/*     */     
/* 303 */     this.ShoulderplateL1 = new ModelRenderer(this, 110, 45);
/* 304 */     this.ShoulderplateL1.field_78809_i = true;
/* 305 */     this.ShoulderplateL1.func_78789_a(3.5F, -1.5F, -3.5F, 1, 4, 7);
/* 306 */     this.ShoulderplateL1.func_78793_a(0.0F, 0.0F, 0.0F);
/* 307 */     this.ShoulderplateL1.func_78787_b(128, 64);
/* 308 */     setRotation(this.ShoulderplateL1, 0.0F, 0.0F, -0.4363323F);
/*     */     
/* 310 */     this.ShoulderplateL2 = new ModelRenderer(this, 94, 45);
/* 311 */     this.ShoulderplateL2.field_78809_i = true;
/* 312 */     this.ShoulderplateL2.func_78789_a(2.5F, 1.5F, -3.5F, 1, 3, 7);
/* 313 */     this.ShoulderplateL2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 314 */     this.ShoulderplateL2.func_78787_b(128, 64);
/* 315 */     setRotation(this.ShoulderplateL2, 0.0F, 0.0F, -0.4363323F);
/*     */     
/* 317 */     this.ShoulderplateL3 = new ModelRenderer(this, 94, 45);
/* 318 */     this.ShoulderplateL3.field_78809_i = true;
/* 319 */     this.ShoulderplateL3.func_78789_a(1.5F, 3.5F, -3.5F, 1, 3, 7);
/* 320 */     this.ShoulderplateL3.func_78793_a(0.0F, 0.0F, 0.0F);
/* 321 */     this.ShoulderplateL3.func_78787_b(128, 64);
/* 322 */     setRotation(this.ShoulderplateL3, 0.0F, 0.0F, -0.4363323F);
/*     */     
/* 324 */     this.LegpanelR1 = new ModelRenderer(this, 0, 51);
/* 325 */     this.LegpanelR1.func_78789_a(-1.0F, 0.5F, -3.5F, 3, 4, 1);
/* 326 */     this.LegpanelR1.func_78793_a(0.0F, 0.0F, 0.0F);
/* 327 */     this.LegpanelR1.func_78787_b(128, 64);
/* 328 */     setRotation(this.LegpanelR1, -0.4363323F, 0.0F, 0.0F);
/*     */     
/* 330 */     this.LegpanelR2 = new ModelRenderer(this, 8, 51);
/* 331 */     this.LegpanelR2.func_78789_a(-1.0F, 3.5F, -2.5F, 3, 4, 1);
/* 332 */     this.LegpanelR2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 333 */     this.LegpanelR2.func_78787_b(128, 64);
/* 334 */     setRotation(this.LegpanelR2, -0.4363323F, 0.0F, 0.0F);
/*     */     
/* 336 */     this.LegpanelR3 = new ModelRenderer(this, 0, 56);
/* 337 */     this.LegpanelR3.func_78789_a(-1.0F, 6.5F, -1.5F, 3, 3, 1);
/* 338 */     this.LegpanelR3.func_78793_a(0.0F, 0.0F, 0.0F);
/* 339 */     this.LegpanelR3.func_78787_b(128, 64);
/* 340 */     setRotation(this.LegpanelR3, -0.4363323F, 0.0F, 0.0F);
/*     */     
/* 342 */     this.LegpanelR4 = new ModelRenderer(this, 0, 43);
/* 343 */     this.LegpanelR4.func_78789_a(-3.0F, 0.5F, -3.5F, 2, 3, 1);
/* 344 */     this.LegpanelR4.func_78793_a(0.0F, 0.0F, 0.0F);
/* 345 */     this.LegpanelR4.func_78787_b(128, 64);
/* 346 */     setRotation(this.LegpanelR4, -0.4363323F, 0.0F, 0.0F);
/*     */     
/* 348 */     this.LegpanelR5 = new ModelRenderer(this, 0, 47);
/* 349 */     this.LegpanelR5.func_78789_a(-3.0F, 2.5F, -2.5F, 2, 3, 1);
/* 350 */     this.LegpanelR5.func_78793_a(0.0F, 0.0F, 0.0F);
/* 351 */     this.LegpanelR5.func_78787_b(128, 64);
/* 352 */     setRotation(this.LegpanelR5, -0.4363323F, 0.0F, 0.0F);
/*     */     
/* 354 */     this.LegpanelR6 = new ModelRenderer(this, 6, 43);
/* 355 */     this.LegpanelR6.func_78789_a(-3.0F, 4.5F, -1.5F, 2, 3, 1);
/* 356 */     this.LegpanelR6.func_78793_a(0.0F, 0.0F, 0.0F);
/* 357 */     this.LegpanelR6.func_78787_b(128, 64);
/* 358 */     setRotation(this.LegpanelR6, -0.4363323F, 0.0F, 0.0F);
/*     */     
/* 360 */     this.SidepanelR1 = new ModelRenderer(this, 0, 22);
/* 361 */     this.SidepanelR1.func_78789_a(-2.5F, 0.5F, -2.5F, 1, 4, 5);
/* 362 */     this.SidepanelR1.func_78793_a(0.0F, 0.0F, 0.0F);
/* 363 */     this.SidepanelR1.func_78787_b(128, 64);
/* 364 */     setRotation(this.SidepanelR1, 0.0F, 0.0F, 0.4363323F);
/*     */     
/* 366 */     this.SidepanelR2 = new ModelRenderer(this, 0, 31);
/* 367 */     this.SidepanelR2.func_78789_a(-1.5F, 3.5F, -2.5F, 1, 3, 5);
/* 368 */     this.SidepanelR2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 369 */     this.SidepanelR2.func_78787_b(128, 64);
/* 370 */     setRotation(this.SidepanelR2, 0.0F, 0.0F, 0.4363323F);
/*     */     
/* 372 */     this.SidepanelR3 = new ModelRenderer(this, 12, 31);
/* 373 */     this.SidepanelR3.func_78789_a(-0.5F, 5.5F, -2.5F, 1, 3, 5);
/* 374 */     this.SidepanelR3.func_78793_a(0.0F, 0.0F, 0.0F);
/* 375 */     this.SidepanelR3.func_78787_b(128, 64);
/* 376 */     setRotation(this.SidepanelR3, 0.0F, 0.0F, 0.4363323F);
/*     */     
/* 378 */     this.BackpanelR1 = new ModelRenderer(this, 0, 18);
/* 379 */     this.BackpanelR1.func_78789_a(-3.0F, 0.5F, 2.5F, 5, 3, 1);
/* 380 */     this.BackpanelR1.func_78793_a(0.0F, 0.0F, 0.0F);
/* 381 */     this.BackpanelR1.func_78787_b(128, 64);
/* 382 */     setRotation(this.BackpanelR1, 0.4363323F, 0.0F, 0.0F);
/*     */     
/* 384 */     this.BackpanelR2 = new ModelRenderer(this, 0, 18);
/* 385 */     this.BackpanelR2.func_78789_a(-3.0F, 2.5F, 1.5F, 5, 3, 1);
/* 386 */     this.BackpanelR2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 387 */     this.BackpanelR2.func_78787_b(128, 64);
/* 388 */     setRotation(this.BackpanelR2, 0.4363323F, 0.0F, 0.0F);
/*     */     
/* 390 */     this.BackpanelR3 = new ModelRenderer(this, 0, 18);
/* 391 */     this.BackpanelR3.func_78789_a(-3.0F, 4.5F, 0.5F, 5, 3, 1);
/* 392 */     this.BackpanelR3.func_78793_a(0.0F, 0.0F, 0.0F);
/* 393 */     this.BackpanelR3.func_78787_b(128, 64);
/* 394 */     setRotation(this.BackpanelR3, 0.4363323F, 0.0F, 0.0F);
/*     */     
/* 396 */     this.BackpanelL3 = new ModelRenderer(this, 0, 18);
/* 397 */     this.BackpanelL3.field_78809_i = true;
/* 398 */     this.BackpanelL3.func_78789_a(-2.0F, 4.5F, 0.5F, 5, 3, 1);
/* 399 */     this.BackpanelL3.func_78793_a(0.0F, 0.0F, 0.0F);
/* 400 */     this.BackpanelL3.func_78787_b(128, 64);
/* 401 */     setRotation(this.BackpanelL3, 0.4363323F, 0.0F, 0.0F);
/*     */     
/* 403 */     this.LegpanelL1 = new ModelRenderer(this, 0, 51);
/* 404 */     this.LegpanelL1.field_78809_i = true;
/* 405 */     this.LegpanelL1.func_78789_a(-2.0F, 0.5F, -3.5F, 3, 4, 1);
/* 406 */     this.LegpanelL1.func_78793_a(0.0F, 0.0F, 0.0F);
/* 407 */     this.LegpanelL1.func_78787_b(128, 64);
/* 408 */     setRotation(this.LegpanelL1, -0.4363323F, 0.0F, 0.0F);
/*     */     
/* 410 */     this.LegpanelL2 = new ModelRenderer(this, 8, 51);
/* 411 */     this.LegpanelL2.field_78809_i = true;
/* 412 */     this.LegpanelL2.func_78789_a(-2.0F, 3.5F, -2.5F, 3, 4, 1);
/* 413 */     this.LegpanelL2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 414 */     this.LegpanelL2.func_78787_b(128, 64);
/* 415 */     setRotation(this.LegpanelL2, -0.4363323F, 0.0F, 0.0F);
/*     */     
/* 417 */     this.LegpanelL3 = new ModelRenderer(this, 0, 56);
/* 418 */     this.LegpanelL3.field_78809_i = true;
/* 419 */     this.LegpanelL3.func_78789_a(-2.0F, 6.5F, -1.5F, 3, 3, 1);
/* 420 */     this.LegpanelL3.func_78793_a(0.0F, 0.0F, 0.0F);
/* 421 */     this.LegpanelL3.func_78787_b(128, 64);
/* 422 */     setRotation(this.LegpanelL3, -0.4363323F, 0.0F, 0.0F);
/*     */     
/* 424 */     this.LegpanelL4 = new ModelRenderer(this, 0, 43);
/* 425 */     this.LegpanelL4.field_78809_i = true;
/* 426 */     this.LegpanelL4.func_78789_a(1.0F, 0.5F, -3.5F, 2, 3, 1);
/* 427 */     this.LegpanelL4.func_78793_a(0.0F, 0.0F, 0.0F);
/* 428 */     this.LegpanelL4.func_78787_b(128, 64);
/* 429 */     setRotation(this.LegpanelL4, -0.4363323F, 0.0F, 0.0F);
/*     */     
/* 431 */     this.LegpanelL5 = new ModelRenderer(this, 0, 47);
/* 432 */     this.LegpanelL5.field_78809_i = true;
/* 433 */     this.LegpanelL5.func_78789_a(1.0F, 2.5F, -2.5F, 2, 3, 1);
/* 434 */     this.LegpanelL5.func_78793_a(0.0F, 0.0F, 0.0F);
/* 435 */     this.LegpanelL5.func_78787_b(128, 64);
/* 436 */     setRotation(this.LegpanelL5, -0.4363323F, 0.0F, 0.0F);
/*     */     
/* 438 */     this.LegpanelL6 = new ModelRenderer(this, 6, 43);
/* 439 */     this.LegpanelL6.field_78809_i = true;
/* 440 */     this.LegpanelL6.func_78789_a(1.0F, 4.5F, -1.5F, 2, 3, 1);
/* 441 */     this.LegpanelL6.func_78793_a(0.0F, 0.0F, 0.0F);
/* 442 */     this.LegpanelL6.func_78787_b(128, 64);
/* 443 */     setRotation(this.LegpanelL6, -0.4363323F, 0.0F, 0.0F);
/*     */     
/* 445 */     this.SidepanelL1 = new ModelRenderer(this, 0, 22);
/* 446 */     this.SidepanelL1.field_78809_i = true;
/* 447 */     this.SidepanelL1.func_78789_a(1.5F, 0.5F, -2.5F, 1, 4, 5);
/* 448 */     this.SidepanelL1.func_78793_a(0.0F, 0.0F, 0.0F);
/* 449 */     this.SidepanelL1.func_78787_b(128, 64);
/* 450 */     setRotation(this.SidepanelL1, 0.0F, 0.0F, -0.4363323F);
/*     */     
/* 452 */     this.SidepanelL2 = new ModelRenderer(this, 0, 31);
/* 453 */     this.SidepanelL2.field_78809_i = true;
/* 454 */     this.SidepanelL2.func_78789_a(0.5F, 3.5F, -2.5F, 1, 3, 5);
/* 455 */     this.SidepanelL2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 456 */     this.SidepanelL2.func_78787_b(128, 64);
/* 457 */     setRotation(this.SidepanelL2, 0.0F, 0.0F, -0.4363323F);
/*     */     
/* 459 */     this.SidepanelL3 = new ModelRenderer(this, 12, 31);
/* 460 */     this.SidepanelL3.field_78809_i = true;
/* 461 */     this.SidepanelL3.func_78789_a(-0.5F, 5.5F, -2.5F, 1, 3, 5);
/* 462 */     this.SidepanelL3.func_78793_a(0.0F, 0.0F, 0.0F);
/* 463 */     this.SidepanelL3.func_78787_b(128, 64);
/* 464 */     setRotation(this.SidepanelL3, 0.0F, 0.0F, -0.4363323F);
/*     */     
/* 466 */     this.BackpanelL1 = new ModelRenderer(this, 0, 18);
/* 467 */     this.BackpanelL1.field_78809_i = true;
/* 468 */     this.BackpanelL1.func_78789_a(-2.0F, 0.5F, 2.5F, 5, 3, 1);
/* 469 */     this.BackpanelL1.func_78793_a(0.0F, 0.0F, 0.0F);
/* 470 */     this.BackpanelL1.func_78787_b(128, 64);
/* 471 */     setRotation(this.BackpanelL1, 0.4363323F, 0.0F, 0.0F);
/*     */     
/* 473 */     this.BackpanelL2 = new ModelRenderer(this, 0, 18);
/* 474 */     this.BackpanelL2.field_78809_i = true;
/* 475 */     this.BackpanelL2.func_78789_a(-2.0F, 2.5F, 1.5F, 5, 3, 1);
/* 476 */     this.BackpanelL2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 477 */     this.BackpanelL2.func_78787_b(128, 64);
/* 478 */     setRotation(this.BackpanelL2, 0.4363323F, 0.0F, 0.0F);
/*     */     
/* 480 */     this.field_178720_f.field_78804_l.clear();
/* 481 */     this.field_78116_c.field_78804_l.clear();
/* 482 */     this.field_78116_c.func_78792_a(this.OrnamentL);
/* 483 */     this.field_78116_c.func_78792_a(this.OrnamentL2);
/* 484 */     this.field_78116_c.func_78792_a(this.OrnamentR);
/* 485 */     this.field_78116_c.func_78792_a(this.OrnamentR2);
/* 486 */     this.field_78116_c.func_78792_a(this.Helmet);
/* 487 */     this.field_78116_c.func_78792_a(this.HelmetR);
/* 488 */     this.field_78116_c.func_78792_a(this.HelmetL);
/* 489 */     this.field_78116_c.func_78792_a(this.HelmetB);
/* 490 */     this.field_78116_c.func_78792_a(this.capsthingy);
/* 491 */     this.field_78116_c.func_78792_a(this.flapR);
/* 492 */     this.field_78116_c.func_78792_a(this.flapL);
/* 493 */     this.field_78116_c.func_78792_a(this.Gemornament);
/* 494 */     this.field_78116_c.func_78792_a(this.Gem);
/* 495 */     this.field_78116_c.func_78792_a(this.Goggles);
/* 496 */     this.field_78116_c.func_78792_a(this.Mask[0]);
/* 497 */     this.field_78116_c.func_78792_a(this.Mask[1]);
/* 498 */     this.field_78116_c.func_78792_a(this.Mask[2]);
/*     */     
/* 500 */     this.field_78115_e.field_78804_l.clear();
/* 501 */     if (f < 1.0F) {
/* 502 */       this.field_78115_e.func_78792_a(this.Mbelt);
/* 503 */       this.field_78115_e.func_78792_a(this.MbeltL);
/* 504 */       this.field_78115_e.func_78792_a(this.MbeltR);
/*     */     } else {
/* 506 */       this.field_78115_e.func_78792_a(this.BeltR);
/* 507 */       this.field_78115_e.func_78792_a(this.BeltL);
/* 508 */       this.field_78115_e.func_78792_a(this.Chestplate);
/* 509 */       this.field_78115_e.func_78792_a(this.Scroll);
/* 510 */       this.field_78115_e.func_78792_a(this.Backplate);
/* 511 */       this.field_78115_e.func_78792_a(this.Book);
/*     */     }
/*     */     
/* 514 */     this.field_178723_h.field_78804_l.clear();
/* 515 */     this.field_178723_h.func_78792_a(this.ShoulderR);
/* 516 */     this.field_178723_h.func_78792_a(this.GauntletR);
/* 517 */     this.field_178723_h.func_78792_a(this.GauntletstrapR1);
/* 518 */     this.field_178723_h.func_78792_a(this.GauntletstrapR2);
/* 519 */     this.field_178723_h.func_78792_a(this.ShoulderplateRtop);
/* 520 */     this.field_178723_h.func_78792_a(this.ShoulderplateR1);
/* 521 */     this.field_178723_h.func_78792_a(this.ShoulderplateR2);
/* 522 */     this.field_178723_h.func_78792_a(this.ShoulderplateR3);
/*     */     
/* 524 */     this.field_178724_i.field_78804_l.clear();
/* 525 */     this.field_178724_i.func_78792_a(this.ShoulderL);
/* 526 */     this.field_178724_i.func_78792_a(this.GauntletL);
/* 527 */     this.field_178724_i.func_78792_a(this.Gauntletstrapl1);
/* 528 */     this.field_178724_i.func_78792_a(this.GauntletstrapL2);
/* 529 */     this.field_178724_i.func_78792_a(this.ShoulderplateLtop);
/* 530 */     this.field_178724_i.func_78792_a(this.ShoulderplateL1);
/* 531 */     this.field_178724_i.func_78792_a(this.ShoulderplateL2);
/* 532 */     this.field_178724_i.func_78792_a(this.ShoulderplateL3);
/*     */     
/* 534 */     this.field_178721_j.field_78804_l.clear();
/* 535 */     this.field_178721_j.func_78792_a(this.LegpanelR1);
/* 536 */     this.field_178721_j.func_78792_a(this.LegpanelR2);
/* 537 */     this.field_178721_j.func_78792_a(this.LegpanelR3);
/* 538 */     this.field_178721_j.func_78792_a(this.LegpanelR4);
/* 539 */     this.field_178721_j.func_78792_a(this.LegpanelR5);
/* 540 */     this.field_178721_j.func_78792_a(this.LegpanelR6);
/* 541 */     this.field_178721_j.func_78792_a(this.SidepanelR1);
/* 542 */     this.field_178721_j.func_78792_a(this.SidepanelR2);
/* 543 */     this.field_178721_j.func_78792_a(this.SidepanelR3);
/* 544 */     this.field_178721_j.func_78792_a(this.BackpanelR1);
/* 545 */     this.field_178721_j.func_78792_a(this.BackpanelR2);
/* 546 */     this.field_178721_j.func_78792_a(this.BackpanelR3);
/*     */     
/* 548 */     this.field_178722_k.field_78804_l.clear();
/* 549 */     this.field_178722_k.func_78792_a(this.BackpanelL3);
/* 550 */     this.field_178722_k.func_78792_a(this.LegpanelL1);
/* 551 */     this.field_178722_k.func_78792_a(this.LegpanelL2);
/* 552 */     this.field_178722_k.func_78792_a(this.LegpanelL3);
/* 553 */     this.field_178722_k.func_78792_a(this.LegpanelL4);
/* 554 */     this.field_178722_k.func_78792_a(this.LegpanelL5);
/* 555 */     this.field_178722_k.func_78792_a(this.LegpanelL6);
/* 556 */     this.field_178722_k.func_78792_a(this.SidepanelL1);
/* 557 */     this.field_178722_k.func_78792_a(this.SidepanelL2);
/* 558 */     this.field_178722_k.func_78792_a(this.SidepanelL3);
/* 559 */     this.field_178722_k.func_78792_a(this.BackpanelL1);
/* 560 */     this.field_178722_k.func_78792_a(this.BackpanelL2);
/*     */   }
/*     */   
/*     */ 
/* 564 */   private static HashMap<Integer, Integer> hasSet = new HashMap();
/* 565 */   private static HashMap<Integer, Integer> hasMask = new HashMap();
/* 566 */   private static HashMap<Integer, Boolean> hasGoggles = new HashMap();
/*     */   
/*     */   private void checkSet(Entity entity) {
/* 569 */     if (((entity instanceof EntityLivingBase)) && (entity.field_70173_aa % 20 == 0)) {
/* 570 */       int set = 0;
/* 571 */       for (int a = 1; a < 4; a++) {
/* 572 */         ItemStack piece = ((EntityLivingBase)entity).func_71124_b(a + 1);
/* 573 */         if ((piece != null) && ((piece.func_77973_b() instanceof ItemFortressArmor))) {
/* 574 */           set++;
/* 575 */           if (a == 3) {
/* 576 */             if ((piece.func_77942_o()) && (piece.func_77978_p().func_74764_b("mask"))) {
/* 577 */               hasMask.put(Integer.valueOf(entity.func_145782_y()), Integer.valueOf(piece.func_77978_p().func_74762_e("mask")));
/*     */             } else {
/* 579 */               hasMask.remove(Integer.valueOf(entity.func_145782_y()));
/*     */             }
/* 581 */             if ((piece.func_77942_o()) && (piece.func_77978_p().func_74764_b("goggles"))) {
/* 582 */               hasGoggles.put(Integer.valueOf(entity.func_145782_y()), Boolean.valueOf(true));
/*     */             } else {
/* 584 */               hasGoggles.remove(Integer.valueOf(entity.func_145782_y()));
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 591 */       if (set > 0) {
/* 592 */         hasSet.put(Integer.valueOf(entity.func_145782_y()), Integer.valueOf(set));
/*     */       } else {
/* 594 */         hasSet.remove(Integer.valueOf(entity.func_145782_y()));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/* 605 */     checkSet(entity);
/* 606 */     int set = hasSet.containsKey(Integer.valueOf(entity.func_145782_y())) ? ((Integer)hasSet.get(Integer.valueOf(entity.func_145782_y()))).intValue() : -1;
/* 607 */     int mask = hasMask.containsKey(Integer.valueOf(entity.func_145782_y())) ? ((Integer)hasMask.get(Integer.valueOf(entity.func_145782_y()))).intValue() : -1;
/*     */     
/* 609 */     this.Goggles.field_78807_k = (!hasGoggles.containsKey(Integer.valueOf(entity.func_145782_y())));
/*     */     
/*     */ 
/* 612 */     for (int a = 0; a < 3; a++) {
/* 613 */       if (mask == a) {
/* 614 */         this.Mask[a].field_78807_k = false;
/*     */       } else {
/* 616 */         this.Mask[a].field_78807_k = true;
/*     */       }
/*     */     }
/*     */     
/* 620 */     this.Scroll.field_78807_k = (set < 3);
/* 621 */     this.Book.field_78807_k = (set < 2);
/* 622 */     this.OrnamentL.field_78807_k = (set < 3);
/* 623 */     this.OrnamentL2.field_78807_k = (set < 3);
/* 624 */     this.OrnamentR.field_78807_k = (set < 3);
/* 625 */     this.OrnamentR2.field_78807_k = (set < 3);
/* 626 */     this.Gemornament.field_78807_k = (set < 3);
/* 627 */     this.Gem.field_78807_k = (set < 3);
/* 628 */     this.flapL.field_78807_k = (set < 2);
/* 629 */     this.flapR.field_78807_k = (set < 2);
/* 630 */     this.ShoulderplateLtop.field_78807_k = (set < 2);
/* 631 */     this.ShoulderplateL1.field_78807_k = (set < 2);
/* 632 */     this.ShoulderplateL2.field_78807_k = (set < 3);
/* 633 */     this.ShoulderplateL3.field_78807_k = (set < 3);
/* 634 */     this.ShoulderplateRtop.field_78807_k = (set < 2);
/* 635 */     this.ShoulderplateR1.field_78807_k = (set < 2);
/* 636 */     this.ShoulderplateR2.field_78807_k = (set < 3);
/* 637 */     this.ShoulderplateR3.field_78807_k = (set < 3);
/* 638 */     this.SidepanelR2.field_78807_k = (set < 2);
/* 639 */     this.SidepanelL2.field_78807_k = (set < 2);
/* 640 */     this.SidepanelR3.field_78807_k = (set < 3);
/* 641 */     this.SidepanelL3.field_78807_k = (set < 3);
/*     */     
/* 643 */     func_78087_a(f, f1, f2, f3, f4, f5, entity);
/*     */     
/*     */ 
/* 646 */     if (this.field_78091_s)
/*     */     {
/* 648 */       float f6 = 2.0F;
/* 649 */       GL11.glPushMatrix();
/* 650 */       GL11.glScalef(1.5F / f6, 1.5F / f6, 1.5F / f6);
/* 651 */       GL11.glTranslatef(0.0F, 16.0F * f5, 0.0F);
/* 652 */       this.field_78116_c.func_78785_a(f5);
/* 653 */       GL11.glPopMatrix();
/* 654 */       GL11.glPushMatrix();
/* 655 */       GL11.glScalef(1.0F / f6, 1.0F / f6, 1.0F / f6);
/* 656 */       GL11.glTranslatef(0.0F, 24.0F * f5, 0.0F);
/* 657 */       this.field_78115_e.func_78785_a(f5);
/* 658 */       this.field_178723_h.func_78785_a(f5);
/* 659 */       this.field_178724_i.func_78785_a(f5);
/* 660 */       this.field_178721_j.func_78785_a(f5);
/* 661 */       this.field_178722_k.func_78785_a(f5);
/*     */       
/* 663 */       this.field_178720_f.func_78785_a(f5);
/*     */       
/* 665 */       GL11.glPopMatrix();
/*     */     }
/*     */     else
/*     */     {
/* 669 */       GL11.glPushMatrix();
/* 670 */       GL11.glScalef(1.01F, 1.01F, 1.01F);
/* 671 */       this.field_78116_c.func_78785_a(f5);
/* 672 */       GL11.glPopMatrix();
/* 673 */       this.field_78115_e.func_78785_a(f5);
/* 674 */       this.field_178723_h.func_78785_a(f5);
/* 675 */       this.field_178724_i.func_78785_a(f5);
/* 676 */       this.field_178721_j.func_78785_a(f5);
/* 677 */       this.field_178722_k.func_78785_a(f5);
/* 678 */       this.field_178720_f.func_78785_a(f5);
/*     */     }
/*     */   }
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z)
/*     */   {
/* 684 */     model.field_78795_f = x;
/* 685 */     model.field_78796_g = y;
/* 686 */     model.field_78808_h = z;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/models/gear/ModelFortressArmor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */