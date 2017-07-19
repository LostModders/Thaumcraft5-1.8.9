/*     */ package thaumcraft.common.entities.construct.golem.seals;
/*     */ 
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import thaumcraft.api.golems.seals.ISeal;
/*     */ import thaumcraft.api.golems.seals.ISealConfigArea;
/*     */ import thaumcraft.api.golems.seals.ISealConfigToggles;
/*     */ import thaumcraft.api.golems.seals.ISealConfigToggles.SealToggle;
/*     */ import thaumcraft.api.golems.seals.SealPos;
/*     */ import thaumcraft.api.golems.tasks.Task;
/*     */ import thaumcraft.common.entities.construct.golem.tasks.TaskHandler;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.misc.PacketSealToClient;
/*     */ 
/*     */ public class SealEntity implements thaumcraft.api.golems.seals.ISealEntity
/*     */ {
/*     */   SealPos sealPos;
/*     */   ISeal seal;
/*  22 */   byte priority = 0;
/*  23 */   byte color = 0;
/*  24 */   boolean locked = false;
/*  25 */   boolean redstone = false;
/*  26 */   String owner = "";
/*     */   
/*     */ 
/*     */   public SealEntity() {}
/*     */   
/*     */   public SealEntity(World world, SealPos sealPos, ISeal seal)
/*     */   {
/*  33 */     this.sealPos = sealPos;
/*  34 */     this.seal = seal;
/*  35 */     if ((seal instanceof ISealConfigArea)) {
/*  36 */       int x = sealPos.face.func_82601_c() == 0 ? 3 : 1;
/*  37 */       int y = sealPos.face.func_96559_d() == 0 ? 3 : 1;
/*  38 */       int z = sealPos.face.func_82599_e() == 0 ? 3 : 1;
/*  39 */       this.area = new BlockPos(x, y, z);
/*     */     }
/*     */   }
/*     */   
/*     */   public void tickSealEntity(World world)
/*     */   {
/*  45 */     if (this.seal != null) {
/*  46 */       if (isStoppedByRedstone(world)) {
/*  47 */         if (!this.stopped) {
/*  48 */           for (Task t : TaskHandler.getTasks(world.field_73011_w.func_177502_q()).values()) {
/*  49 */             if ((t.getSealPos() != null) && (t.getSealPos().equals(this.sealPos))) {
/*  50 */               t.setSuspended(true);
/*     */             }
/*     */           }
/*     */         }
/*  54 */         this.stopped = true;
/*  55 */         return;
/*     */       }
/*  57 */       this.stopped = false;
/*  58 */       this.seal.tickSeal(world, this);
/*     */     } }
/*     */   
/*  61 */   boolean stopped = false;
/*     */   
/*     */   public boolean isStoppedByRedstone(World world)
/*     */   {
/*  65 */     return (isRedstoneSensitive()) && ((world.func_175640_z(getSealPos().pos)) || (world.func_175640_z(getSealPos().pos.func_177972_a(getSealPos().face))));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ISeal getSeal()
/*     */   {
/*  72 */     return this.seal;
/*     */   }
/*     */   
/*     */   public SealPos getSealPos()
/*     */   {
/*  77 */     return this.sealPos;
/*     */   }
/*     */   
/*     */   public byte getPriority()
/*     */   {
/*  82 */     return this.priority;
/*     */   }
/*     */   
/*     */   public void setPriority(byte priority)
/*     */   {
/*  87 */     this.priority = priority;
/*     */   }
/*     */   
/*     */   public byte getColor()
/*     */   {
/*  92 */     return this.color;
/*     */   }
/*     */   
/*     */   public void setColor(byte color)
/*     */   {
/*  97 */     this.color = color;
/*     */   }
/*     */   
/*     */   public String getOwner()
/*     */   {
/* 102 */     return this.owner;
/*     */   }
/*     */   
/*     */   public void setOwner(String owner)
/*     */   {
/* 107 */     this.owner = owner;
/*     */   }
/*     */   
/*     */   public boolean isLocked()
/*     */   {
/* 112 */     return this.locked;
/*     */   }
/*     */   
/*     */   public void setLocked(boolean locked)
/*     */   {
/* 117 */     this.locked = locked;
/*     */   }
/*     */   
/*     */   public boolean isRedstoneSensitive() {
/* 121 */     return this.redstone;
/*     */   }
/*     */   
/*     */   public void setRedstoneSensitive(boolean redstone) {
/* 125 */     this.redstone = redstone;
/*     */   }
/*     */   
/*     */ 
/*     */   public void readNBT(NBTTagCompound nbt)
/*     */   {
/* 131 */     BlockPos p = BlockPos.func_177969_a(nbt.func_74763_f("pos"));
/* 132 */     EnumFacing face = EnumFacing.field_82609_l[nbt.func_74771_c("face")];
/* 133 */     this.sealPos = new SealPos(p, face);
/* 134 */     setPriority(nbt.func_74771_c("priority"));
/* 135 */     setColor(nbt.func_74771_c("color"));
/* 136 */     setLocked(nbt.func_74767_n("locked"));
/* 137 */     setRedstoneSensitive(nbt.func_74767_n("redstone"));
/* 138 */     setOwner(nbt.func_74779_i("owner"));
/*     */     try {
/* 140 */       this.seal = ((ISeal)SealHandler.getSeal(nbt.func_74779_i("type")).getClass().newInstance());
/*     */     } catch (Exception e) {}
/* 142 */     if (this.seal != null) {
/* 143 */       this.seal.readCustomNBT(nbt);
/* 144 */       if ((this.seal instanceof ISealConfigArea)) {
/* 145 */         this.area = BlockPos.func_177969_a(nbt.func_74763_f("area"));
/*     */       }
/* 147 */       if ((this.seal instanceof ISealConfigToggles)) {
/* 148 */         for (ISealConfigToggles.SealToggle prop : ((ISealConfigToggles)this.seal).getToggles()) {
/* 149 */           if (nbt.func_74764_b(prop.getKey())) {
/* 150 */             prop.setValue(nbt.func_74767_n(prop.getKey()));
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public NBTTagCompound writeNBT()
/*     */   {
/* 159 */     NBTTagCompound nbt = new NBTTagCompound();
/* 160 */     nbt.func_74772_a("pos", this.sealPos.pos.func_177986_g());
/* 161 */     nbt.func_74774_a("face", (byte)this.sealPos.face.ordinal());
/* 162 */     nbt.func_74778_a("type", this.seal.getKey());
/* 163 */     nbt.func_74774_a("priority", getPriority());
/* 164 */     nbt.func_74774_a("color", getColor());
/* 165 */     nbt.func_74757_a("locked", isLocked());
/* 166 */     nbt.func_74757_a("redstone", isRedstoneSensitive());
/* 167 */     nbt.func_74778_a("owner", getOwner());
/* 168 */     if (this.seal != null) {
/* 169 */       this.seal.writeCustomNBT(nbt);
/* 170 */       if ((this.seal instanceof ISealConfigArea)) {
/* 171 */         nbt.func_74772_a("area", this.area.func_177986_g());
/*     */       }
/* 173 */       if ((this.seal instanceof ISealConfigToggles)) {
/* 174 */         for (ISealConfigToggles.SealToggle prop : ((ISealConfigToggles)this.seal).getToggles()) {
/* 175 */           nbt.func_74757_a(prop.getKey(), prop.getValue());
/*     */         }
/*     */       }
/*     */     }
/* 179 */     return nbt;
/*     */   }
/*     */   
/*     */   public void syncToClient(World world)
/*     */   {
/* 184 */     if (!world.field_72995_K) {
/* 185 */       PacketHandler.INSTANCE.sendToDimension(new PacketSealToClient(this), world.field_73011_w.func_177502_q());
/*     */     }
/*     */   }
/*     */   
/* 189 */   private BlockPos area = new BlockPos(1, 1, 1);
/*     */   
/*     */   public BlockPos getArea()
/*     */   {
/* 193 */     return this.area;
/*     */   }
/*     */   
/*     */   public void setArea(BlockPos v)
/*     */   {
/* 198 */     this.area = v;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/construct/golem/seals/SealEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */