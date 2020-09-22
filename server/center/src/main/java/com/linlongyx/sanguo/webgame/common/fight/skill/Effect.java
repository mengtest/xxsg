/*    */ package com.linlongyx.sanguo.webgame.common.fight.skill;
/*    */ 
/*    */ import com.linlongyx.core.utils.LogUtils;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.BuffBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.EffectBean;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Effect
/*    */ {
/*    */   private final int effectId;
/*    */   private final int level;
/*    */   private final EffectBean bean;
/* 20 */   private final List<BuffBean> buffs = new ArrayList<>();
/*    */   
/*    */   public Effect(int effectId, int level) {
/* 23 */     this.effectId = effectId;
/* 24 */     this.level = level;
/* 25 */     this.bean = (EffectBean)JsonTableService.getJsonData(effectId, EffectBean.class);
/* 26 */     if (null == this.bean) {
/* 27 */       LogUtils.errorLog(new Object[] { "effect id cannot found bean: " + effectId });
/*    */       return;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getEffectId() {
/* 47 */     return this.effectId;
/*    */   }
/*    */   
/*    */   public EffectBean getBean() {
/* 51 */     return this.bean;
/*    */   }
/*    */   
/*    */   public List<BuffBean> getBuffs() {
/* 55 */     return this.buffs;
/*    */   }
/*    */   
/*    */   public int getLevel() {
/* 59 */     return this.level;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getId() {
/* 64 */     return this.bean.getId();
/*    */   }
/*    */   
/*    */   public int getSide() {
/* 68 */     return this.bean.getSide();
/*    */   }
/*    */   
/*    */   public int getTarget() {
/* 72 */     return this.bean.getTarget();
/*    */   }
/*    */   
/*    */   public int getRange() {
/* 76 */     return this.bean.getRange();
/*    */   }
/*    */   
/*    */   public int getDemageType() {
/* 80 */     return this.bean.getDemageType();
/*    */   }
/*    */   
/*    */   public int getPowerVal(int index) {
/* 84 */     if (index >= 0 && index < this.bean.getPower().size())
/* 85 */       return ((Integer)this.bean.getPower().get(index)).intValue(); 
/* 86 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\skill\Effect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */