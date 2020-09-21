/*    */ package com.linlongyx.sanguo.webgame.config.parameter;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.config.bean.ParameterBean;
/*    */ 
/*    */ public class CrossRaceParameter
/*    */   extends AbstractParameter {
/*    */   private int fightTimes;
/*    */   private int limit;
/*    */   
/*    */   public CrossRaceParameter() {
/* 11 */     super(28);
/*    */   }
/*    */ 
/*    */   
/*    */   private int level;
/*    */   
/*    */   private int cdTime;
/*    */ 
/*    */   
/*    */   void init(ParameterBean bean) {
/* 21 */     this.fightTimes = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(1))).getValue()).intValue();
/* 22 */     this.limit = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(2))).getValue()).intValue();
/* 23 */     this.level = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(3))).getValue()).intValue();
/* 24 */     this.cdTime = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(4))).getValue()).intValue();
/*    */   }
/*    */   
/*    */   public int getFightTimes() {
/* 28 */     return this.fightTimes;
/*    */   }
/*    */   
/*    */   public int getLimit() {
/* 32 */     return this.limit;
/*    */   }
/*    */   
/*    */   public int getLevel() {
/* 36 */     return this.level;
/*    */   }
/*    */   
/*    */   public int getCdTime() {
/* 40 */     return this.cdTime;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\parameter\CrossRaceParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */