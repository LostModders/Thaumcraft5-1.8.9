/*    */ package thaumcraft.api.research;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.item.EntityItem;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ import thaumcraft.api.aspects.AspectHelper;
/*    */ import thaumcraft.api.aspects.AspectList;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ScanAspect
/*    */   implements IScanThing
/*    */ {
/*    */   String research;
/*    */   Aspect aspect;
/*    */   
/*    */   public ScanAspect(String research, Aspect aspect)
/*    */   {
/* 25 */     this.research = research;
/* 26 */     this.aspect = aspect;
/*    */   }
/*    */   
/*    */   public boolean checkThing(EntityPlayer player, Object obj)
/*    */   {
/* 31 */     if (obj == null) { return false;
/*    */     }
/* 33 */     AspectList al = null;
/*    */     
/* 35 */     if (((obj instanceof Entity)) && (!(obj instanceof EntityItem))) {
/* 36 */       al = AspectHelper.getEntityAspects((Entity)obj);
/*    */     } else {
/* 38 */       ItemStack is = null;
/* 39 */       if ((obj instanceof ItemStack))
/* 40 */         is = (ItemStack)obj;
/* 41 */       if (((obj instanceof EntityItem)) && (((EntityItem)obj).func_92059_d() != null))
/* 42 */         is = ((EntityItem)obj).func_92059_d();
/* 43 */       if ((obj instanceof BlockPos)) {
/* 44 */         Block b = player.field_70170_p.func_180495_p((BlockPos)obj).func_177230_c();
/* 45 */         is = new ItemStack(b, 1, b.func_176201_c(player.field_70170_p.func_180495_p((BlockPos)obj)));
/*    */       }
/*    */       
/* 48 */       if (is != null) {
/* 49 */         al = AspectHelper.getObjectAspects(is);
/*    */       }
/*    */     }
/*    */     
/* 53 */     return (al != null) && (al.getAmount(this.aspect) > 0);
/*    */   }
/*    */   
/*    */   public String getResearchKey()
/*    */   {
/* 58 */     return this.research;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/api/research/ScanAspect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */