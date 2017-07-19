/*     */ package thaumcraft.client.lib;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.client.renderer.entity.RendererLivingEntity;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.passive.EntityVillager;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.client.event.RenderLivingEvent.Post;
/*     */ import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.lib.math.Box;
/*     */ import thaumcraft.client.lib.math.Intersection;
/*     */ import thaumcraft.client.lib.math.Line;
/*     */ import thaumcraft.client.lib.math.Vec4;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ 
/*     */ public class ClothHandler
/*     */ {
/*     */   private class ClothNode
/*     */   {
/*     */     Vec4 pos;
/*     */     Vec4 vel;
/*     */     Vec4 acc;
/*     */     double mass;
/*  41 */     boolean fixed = false;
/*  42 */     double g = -0.01D;
/*     */     
/*     */     private ClothNode(double x, double y, double z, double d, double g, boolean fixed) {
/*  45 */       this.pos = new Vec4(x, y, z);
/*  46 */       this.vel = new Vec4(0.0D, 0.0D, 0.0D);
/*  47 */       this.acc = new Vec4(0.0D, 0.0D, 0.0D);
/*  48 */       this.mass = d;
/*  49 */       this.fixed = fixed;
/*  50 */       this.g = g;
/*     */     }
/*     */     
/*     */ 
/*  54 */     private void applyForce(Vec4 force) { this.acc = this.acc.add3(force.multiply3(this.mass)); }
/*     */   }
/*     */   
/*     */   private class NodeLink {
/*     */     int a;
/*     */     int b;
/*     */     double length;
/*     */     
/*     */     private NodeLink(int a, int b, double length) {
/*  63 */       this.a = a;
/*  64 */       this.b = b;
/*  65 */       this.length = length;
/*     */     }
/*     */   }
/*     */   
/*     */   private static abstract interface IAttachPoint {
/*     */     public abstract Vec3 getFinalOffset(ClothHandler.ClothMesh paramClothMesh);
/*     */   }
/*     */   
/*     */   private class AttachPointBlock implements ClothHandler.IAttachPoint {
/*     */     Vec3 offset;
/*     */     
/*     */     private AttachPointBlock(Vec3 offset) {
/*  77 */       this.offset = offset;
/*     */     }
/*     */     
/*     */     public Vec3 getFinalOffset(ClothHandler.ClothMesh mesh)
/*     */     {
/*  82 */       return this.offset;
/*     */     }
/*     */   }
/*     */   
/*     */   private class AttachPointEntity implements ClothHandler.IAttachPoint {
/*     */     Vec3 offset;
/*     */     EntityLiving entity;
/*     */     
/*     */     private AttachPointEntity(Vec3 offset, EntityLiving entity) {
/*  91 */       this.offset = offset;
/*  92 */       this.entity = entity;
/*     */     }
/*     */     
/*     */     public Vec3 getFinalOffset(ClothHandler.ClothMesh mesh)
/*     */     {
/*  97 */       double xx = MathHelper.func_76134_b(this.entity.field_70761_aq / 180.0F * 3.1415927F) * this.offset.field_72450_a;
/*  98 */       double zz = MathHelper.func_76126_a(this.entity.field_70761_aq / 180.0F * 3.1415927F) * this.offset.field_72450_a;
/*  99 */       xx -= MathHelper.func_76134_b((this.entity.field_70761_aq + 90.0F) / 180.0F * 3.1415927F) * this.offset.field_72449_c;
/* 100 */       zz -= MathHelper.func_76126_a((this.entity.field_70761_aq + 90.0F) / 180.0F * 3.1415927F) * this.offset.field_72449_c;
/* 101 */       return new Vec3(xx, this.offset.field_72448_b, zz);
/*     */     } }
/*     */   
/*     */   private class ClothMesh { private ClothMesh() {}
/*     */     
/* 106 */     boolean blockPhysics = false;
/* 107 */     ArrayList<Integer> entityPhysics = new ArrayList();
/*     */     ClothMesh mesh;
/*     */     private ClothHandler.IAttachPoint attachPoint;
/*     */     private int sizeX;
/* 111 */     private int sizeY; private ArrayList<ClothHandler.ClothNode> nodes = new ArrayList();
/* 112 */     private ArrayList<ClothHandler.NodeLink> links = new ArrayList();
/*     */     
/* 114 */     Vec4[] lastCorners = null;
/*     */     
/*     */      }
/* 117 */   HashMap<Integer, ClothMesh> clothMeshes = new HashMap();
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   @SubscribeEvent
/*     */   public void livingTick(LivingEvent.LivingUpdateEvent event) {
/* 122 */     if (!event.entity.field_70170_p.field_72995_K) {
/* 123 */       return;
/*     */     }
/* 125 */     if (((event.entity instanceof EntityVillager)) && 
/* 126 */       (this.lastRender.containsKey(Integer.valueOf(event.entity.func_145782_y()))) && (((Long)this.lastRender.get(Integer.valueOf(event.entity.func_145782_y()))).longValue() + 80L < System.currentTimeMillis()))
/*     */     {
/* 128 */       processCapeTick(event.entityLiving, 1.0F);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void processCapeTick(EntityLivingBase entity, float timestep)
/*     */   {
/* 136 */     ClothMesh mesh = (ClothMesh)this.clothMeshes.get(Integer.valueOf(entity.func_145782_y()));
/* 137 */     if (mesh == null) {
/* 138 */       mesh = createInitialState(entity);
/* 139 */       this.clothMeshes.put(Integer.valueOf(entity.func_145782_y()), mesh);
/*     */     }
/*     */     
/* 142 */     calculateForces(entity, mesh, timestep);
/*     */   }
/*     */   
/*     */   public void addLink(ClothMesh mesh, NodeLink link) {
/* 146 */     mesh.links.add(link);
/*     */   }
/*     */   
/*     */ 
/*     */   private ClothMesh createInitialState(EntityLivingBase entity)
/*     */   {
/* 152 */     ClothMesh mesh = new ClothMesh(null);
/* 153 */     float ew = entity.field_70130_N / 4.0F;
/* 154 */     for (int i = 0; i < 4; i++)
/*     */     {
/* 156 */       float offset = -(ew * 1.5F) + ew * i;
/* 157 */       double xx = MathHelper.func_76134_b(entity.field_70761_aq / 180.0F * 3.1415927F) * offset;
/* 158 */       double zz = MathHelper.func_76126_a(entity.field_70761_aq / 180.0F * 3.1415927F) * offset;
/* 159 */       xx -= MathHelper.func_76134_b((entity.field_70761_aq + 90.0F) / 180.0F * 3.1415927F) * ew * 1.4F;
/* 160 */       zz -= MathHelper.func_76126_a((entity.field_70761_aq + 90.0F) / 180.0F * 3.1415927F) * ew * 1.4F;
/* 161 */       mesh.nodes.add(new ClothNode(xx + entity.field_70165_t, entity.field_70163_u + entity.field_70131_O * 0.75D, zz + entity.field_70161_v, 0.02D, 0.0D, true, null));
/* 162 */       addLink(mesh, new NodeLink(i + 4, i, 0.1D, null));
/*     */     }
/* 164 */     int ind = 4;
/* 165 */     for (int i = 1; i < 5; i++)
/* 166 */       for (int q = 0; q < 4; q++)
/*     */       {
/* 168 */         ClothNode nd = (ClothNode)mesh.nodes.get(q);
/* 169 */         mesh.nodes.add(new ClothNode(nd.pos.x, nd.pos.y - i * 0.2D, nd.pos.z, 0.02D, -0.02D + i * 0.001F, false, null));
/* 170 */         if (q < 3) {
/* 171 */           addLink(mesh, new NodeLink(ind + 1, ind, 0.05D, null));
/*     */         }
/* 173 */         if (i < 4) {
/* 174 */           addLink(mesh, new NodeLink(ind + 4, ind, 0.04D, null));
/*     */         }
/* 176 */         ind++;
/*     */       }
/* 178 */     return mesh;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/* 183 */   Box body = null;
/*     */   
/*     */   private void calculateForces(EntityLivingBase entity, ClothMesh mesh, float timestep)
/*     */   {
/* 187 */     boolean first = true;
/* 188 */     float ew = entity.field_70130_N / 4.0F;
/*     */     
/* 190 */     double ex = interpolateValue(entity.field_70169_q, entity.field_70165_t, UtilsFX.sysPartialTicks);
/* 191 */     double ey = interpolateValue(entity.field_70167_r, entity.field_70163_u, UtilsFX.sysPartialTicks) + entity.field_70131_O * 0.75D;
/* 192 */     double eb = interpolateValue(entity.field_70167_r, entity.field_70163_u, UtilsFX.sysPartialTicks);
/* 193 */     double ez = interpolateValue(entity.field_70166_s, entity.field_70161_v, UtilsFX.sysPartialTicks);
/* 194 */     double fx = MathHelper.func_76134_b((entity.field_70761_aq + 90.0F) / 180.0F * 3.1415927F) * ew * 1.4F;
/* 195 */     double fz = MathHelper.func_76126_a((entity.field_70761_aq + 90.0F) / 180.0F * 3.1415927F) * ew * 1.4F;
/* 196 */     double fx2 = MathHelper.func_76134_b((entity.field_70761_aq - 90.0F) / 180.0F * 3.1415927F) * ew * 1.4F;
/* 197 */     double fz2 = MathHelper.func_76126_a((entity.field_70761_aq - 90.0F) / 180.0F * 3.1415927F) * ew * 1.4F;
/* 198 */     ArrayList<Vec4> points = new ArrayList();
/*     */     
/* 200 */     float repulsion = 0.1F;
/* 201 */     float stiffness = 9.0F;
/* 202 */     float damping = 0.85F;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 210 */     for (int i = 0; i < mesh.nodes.size(); i++)
/*     */     {
/* 212 */       ClothNode v = (ClothNode)mesh.nodes.get(i);
/*     */       
/*     */ 
/* 215 */       if ((i < 4) && (v.fixed)) {
/* 216 */         float offset = -(ew * 1.5F) + ew * i;
/* 217 */         double xx = MathHelper.func_76134_b(entity.field_70761_aq / 180.0F * 3.1415927F) * offset;
/* 218 */         double zz = MathHelper.func_76126_a(entity.field_70761_aq / 180.0F * 3.1415927F) * offset;
/* 219 */         xx -= fx;
/* 220 */         zz -= fz;
/* 221 */         v.pos = new Vec4(xx + ex, ey, zz + ez);
/*     */         
/* 223 */         if ((i == 0) || (i == 3)) {
/* 224 */           points.add(new Vec4(v.pos.x, v.pos.y, v.pos.z));
/* 225 */           points.add(new Vec4(v.pos.x, eb, v.pos.z));
/* 226 */           points.add(new Vec4(v.pos.x + fx * 2.0D, v.pos.y, v.pos.z + fz * 2.0D));
/* 227 */           points.add(new Vec4(v.pos.x + fx * 2.0D, eb, v.pos.z + fz * 2.0D));
/*     */         }
/*     */       }
/*     */       
/* 231 */       for (int q = 0; q < mesh.nodes.size(); q++)
/*     */       {
/* 233 */         if (i != q) {
/* 234 */           ClothNode u = (ClothNode)mesh.nodes.get(q);
/*     */           
/* 236 */           Vec4 d = v.pos.subtract3(u.pos);
/* 237 */           double distance = d.getLength3() + 0.1D;
/* 238 */           Vec4 direction = d.normalize3();
/*     */           
/* 240 */           v.applyForce(direction.multiply3(repulsion).divide3(distance * distance * 0.5D));
/* 241 */           u.applyForce(direction.multiply3(repulsion).divide3(distance * distance * -0.5D));
/*     */         } }
/*     */     }
/* 244 */     ClothNode v = null;
/* 245 */     ClothNode u = null;
/*     */     
/*     */ 
/* 248 */     for (NodeLink link : mesh.links) {
/* 249 */       v = (ClothNode)mesh.nodes.get(link.a);
/* 250 */       u = (ClothNode)mesh.nodes.get(link.b);
/* 251 */       if ((u != null) && (v != null)) {
/* 252 */         Vec4 d = u.pos.subtract3(v.pos);
/* 253 */         double displacement = link.length - d.getLength3();
/* 254 */         Vec4 direction = d.normalize3();
/*     */         
/* 256 */         v.applyForce(direction.multiply3(stiffness * stiffness * displacement * displacement * 0.5D));
/* 257 */         u.applyForce(direction.multiply3(stiffness * stiffness * displacement * displacement * -0.5D));
/*     */       }
/*     */     }
/* 260 */     this.body = Box.computeBoundingBox(points);
/*     */     
/*     */ 
/* 263 */     for (ClothNode n : mesh.nodes)
/*     */     {
/* 265 */       n.acc = n.acc.add3(0.0D, n.g * timestep, 0.0D);
/* 266 */       n.vel = n.vel.add3(n.acc.multiply3(timestep)).multiply3(damping);
/* 267 */       n.acc = new Vec4(0.0D, 0.0D, 0.0D);
/* 268 */       if (!n.fixed) {
/* 269 */         n.pos = n.pos.add3(n.vel.multiply3(timestep));
/* 270 */         doCollisions(mesh, n, entity);
/*     */       }
/*     */     }
/*     */     
/* 274 */     mesh.lastCorners = this.body.getCorners();
/*     */   }
/*     */   
/*     */   private void doCollisions(ClothMesh mesh, ClothNode node, EntityLivingBase entity)
/*     */   {
/* 279 */     Line line = new Line(node.pos, node.vel);
/* 280 */     Intersection[] is = this.body.intersect(line);
/* 281 */     if ((is != null) && (is.length == 1) && (mesh.lastCorners != null)) {
/* 282 */       Vec4[] bc = this.body.getCorners();
/* 283 */       double dist = Double.MAX_VALUE;
/* 284 */       int c = -1;
/* 285 */       for (int ic = 0; ic < mesh.lastCorners.length; ic++) {
/* 286 */         if (!mesh.lastCorners[ic].equals(bc[ic])) {
/* 287 */           double d = node.pos.distanceToSquared3(bc[ic]);
/* 288 */           if (d < dist) {
/* 289 */             dist = d;
/* 290 */             c = ic;
/*     */           }
/*     */         }
/*     */       }
/* 294 */       if (c >= 0) {
/* 295 */         Line line2 = new Line(node.pos, bc[c].subtract3(mesh.lastCorners[c]));
/* 296 */         Intersection[] is2 = this.body.intersect(line2);
/* 297 */         if ((is2 != null) && (is2.length == 1))
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/* 302 */           node.pos = is2[0].getIntersectionPoint();
/* 303 */           node.vel = new Vec4(0.0D, 0.0D, 0.0D);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 308 */     BlockPos bp = new BlockPos(node.pos.x, node.pos.y, node.pos.z);
/*     */     
/* 310 */     List<AxisAlignedBB> list = entity.field_70170_p.func_147461_a(new AxisAlignedBB(node.pos.x, node.pos.y, node.pos.z, node.pos.x, node.pos.y, node.pos.z).func_72314_b(0.1D, 0.1D, 0.1D));
/*     */     
/*     */ 
/* 313 */     if ((!list.isEmpty()) || (entity.field_70170_p.func_175665_u(bp))) {
/* 314 */       IBlockState block = entity.field_70170_p.func_180495_p(bp);
/* 315 */       AxisAlignedBB bb = block.func_177230_c().func_180640_a(entity.field_70170_p, bp, block);
/* 316 */       Vec3 vv = pushOutOfBB(entity.field_70170_p, node.pos.x, node.pos.y, node.pos.z, bb);
/* 317 */       if (vv != null) {
/* 318 */         node.pos = new Vec4(vv.field_72450_a, vv.field_72448_b, vv.field_72449_c);
/* 319 */         node.vel = new Vec4(0.0D, 0.0D, 0.0D);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private Vec3 pushOutOfBB(World worldObj, double x, double y, double z, AxisAlignedBB bb)
/*     */   {
/* 328 */     double mX = x;
/* 329 */     double mY = y;
/* 330 */     double mZ = z;
/* 331 */     BlockPos blockpos = new BlockPos(x, y, z);
/* 332 */     double d0 = x - blockpos.func_177958_n();
/* 333 */     double d1 = y - blockpos.func_177956_o();
/* 334 */     double d2 = z - blockpos.func_177952_p();
/*     */     
/* 336 */     if (bb == null) return null;
/* 337 */     int i = 3;
/* 338 */     double d3 = 9999.0D;
/*     */     
/* 340 */     if ((!worldObj.func_175665_u(blockpos.func_177976_e())) && (d0 < d3))
/*     */     {
/* 342 */       d3 = d0;
/* 343 */       i = 0;
/*     */     }
/*     */     
/* 346 */     if ((!worldObj.func_175665_u(blockpos.func_177974_f())) && (1.0D - d0 < d3))
/*     */     {
/* 348 */       d3 = 1.0D - d0;
/* 349 */       i = 1;
/*     */     }
/*     */     
/* 352 */     if ((!worldObj.func_175665_u(blockpos.func_177984_a())) && (1.0D - d1 < d3))
/*     */     {
/* 354 */       d3 = 1.0D - d1;
/* 355 */       i = 3;
/*     */     }
/*     */     
/* 358 */     if ((!worldObj.func_175665_u(blockpos.func_177978_c())) && (d2 < d3))
/*     */     {
/* 360 */       d3 = d2;
/* 361 */       i = 4;
/*     */     }
/*     */     
/* 364 */     if ((!worldObj.func_175665_u(blockpos.func_177968_d())) && (1.0D - d2 < d3))
/*     */     {
/* 366 */       d3 = 1.0D - d2;
/* 367 */       i = 5;
/*     */     }
/*     */     
/* 370 */     float f = worldObj.field_73012_v.nextFloat() * 0.2F + 0.1F;
/*     */     
/* 372 */     if (i == 0)
/*     */     {
/* 374 */       mX = bb.field_72340_a - 0.01D;
/*     */     }
/*     */     
/* 377 */     if (i == 1)
/*     */     {
/* 379 */       mX = bb.field_72336_d + 0.01D;
/*     */     }
/*     */     
/* 382 */     if (i == 3)
/*     */     {
/* 384 */       mY = bb.field_72337_e + 0.01D;
/*     */     }
/*     */     
/* 387 */     if (i == 4)
/*     */     {
/* 389 */       mZ = bb.field_72339_c - 0.01D;
/*     */     }
/*     */     
/* 392 */     if (i == 5)
/*     */     {
/* 394 */       mZ = bb.field_72334_f + 0.01D;
/*     */     }
/*     */     
/* 397 */     return new Vec3(mX, mY, mZ);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 406 */   HashMap<Integer, Long> lastRender = new HashMap();
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   @SubscribeEvent
/*     */   public void renderLivingPost(RenderLivingEvent.Post event) {
/* 411 */     if (!(event.entity instanceof EntityVillager)) { return;
/*     */     }
/* 413 */     long time = System.currentTimeMillis();
/*     */     
/* 415 */     GlStateManager.func_179094_E();
/* 416 */     GlStateManager.func_179137_b(-event.renderer.func_177068_d().field_78730_l, -event.renderer.func_177068_d().field_78731_m, -event.renderer.func_177068_d().field_78728_n);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 421 */     if ((!this.lastRender.containsKey(Integer.valueOf(event.entity.func_145782_y()))) || (((Long)this.lastRender.get(Integer.valueOf(event.entity.func_145782_y()))).longValue() < time))
/*     */     {
/*     */ 
/* 424 */       processCapeTick(event.entity, 1.0F);
/* 425 */       this.lastRender.put(Integer.valueOf(event.entity.func_145782_y()), Long.valueOf(time + 20L));
/*     */       
/* 427 */       if (this.body != null) {
/* 428 */         for (Vec4 corner : this.body.getCorners()) {
/* 429 */           Thaumcraft.proxy.getFX().ssparkle((float)corner.x, (float)corner.y, (float)corner.z, 1);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 434 */     ClothMesh mc = (ClothMesh)this.clothMeshes.get(Integer.valueOf(event.entity.func_145782_y()));
/* 435 */     if (mc == null) { return;
/*     */     }
/*     */     
/*     */ 
/* 439 */     for (NodeLink link : mc.links)
/*     */     {
/* 441 */       ClothNode vc = (ClothNode)mc.nodes.get(link.a);
/* 442 */       ClothNode uc = (ClothNode)mc.nodes.get(link.b);
/*     */       
/* 444 */       drawLine(vc.pos.x, vc.pos.y, vc.pos.z, uc.pos.x, uc.pos.y, uc.pos.z);
/*     */     }
/*     */     
/*     */ 
/* 448 */     GlStateManager.func_179121_F();
/*     */   }
/*     */   
/*     */   private double interpolateValue(double start, double end, double pct)
/*     */   {
/* 453 */     return start + (end - start) * pct;
/*     */   }
/*     */   
/*     */   private void drawLine(double x, double y, double z, double x2, double y2, double z2) {
/* 457 */     Tessellator var12 = Tessellator.func_178181_a();
/* 458 */     GL11.glPushMatrix();
/* 459 */     GL11.glLineWidth(2.0F);
/* 460 */     GL11.glDisable(3553);
/* 461 */     GL11.glBlendFunc(770, 1);
/* 462 */     var12.func_178180_c().func_181668_a(3, net.minecraft.client.renderer.vertex.DefaultVertexFormats.field_181706_f);
/* 463 */     var12.func_178180_c().func_181662_b(x, y, z).func_181666_a(0.0F, 0.6F, 0.8F, 1.0F).func_181675_d();
/* 464 */     var12.func_178180_c().func_181662_b(x2, y2, z2).func_181666_a(0.0F, 0.6F, 0.8F, 1.0F).func_181675_d();
/* 465 */     var12.func_78381_a();
/* 466 */     GL11.glBlendFunc(770, 771);
/* 467 */     GL11.glDisable(32826);
/* 468 */     GL11.glEnable(3553);
/* 469 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 470 */     GL11.glPopMatrix();
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/lib/ClothHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */