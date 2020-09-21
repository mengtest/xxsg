/*    */ package com.linlongyx.sanguo.webgame.config.parameter;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.config.bean.ParameterBean;
/*    */ 
/*    */ public class TowerOwnerParameter
/*    */   extends AbstractParameter {
/*    */   private int zoneSize;
/*    */   private int rankLimit;
/*    */   private int showLimit;
/*    */   private int protectTime;
/*    */   
/*    */   public TowerOwnerParameter() {
/* 13 */     super(67);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   void init(ParameterBean bean) {
/* 23 */     this.zoneSize = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(1))).getValue()).intValue();
/* 24 */     this.rankLimit = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(2))).getValue()).intValue();
/* 25 */     this.showLimit = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(3))).getValue()).intValue();
/* 26 */     this.protectTime = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(6))).getValue());
/*    */   }
/*    */   
/*    */   public int getZoneSize() {
/* 30 */     return this.zoneSize;
/*    */   }
/*    */   
/*    */   public int getRankLimit() {
/* 34 */     return this.rankLimit;
/*    */   }
/*    */   
/*    */   public int getShowLimit() {
/* 38 */     return this.showLimit;
/*    */   }
/*    */   
/*    */   public int getProtectTime() {
/* 42 */     return this.protectTime;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\config\parameter\TowerOwnerParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */