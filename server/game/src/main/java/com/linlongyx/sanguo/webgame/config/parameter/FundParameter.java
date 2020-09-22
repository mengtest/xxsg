/*    */ package com.linlongyx.sanguo.webgame.config.parameter;
/*    */ 
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.ParameterBean;
/*    */ 
/*    */ public class FundParameter extends AbstractParameter {
/*    */   private int fundLimit;
/*    */   private int costCCB;
/*    */   private int oneyuanStartTime;
/*    */   
/*    */   public FundParameter() {
/* 12 */     super(25);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private int oneyuanEndTime;
/*    */   
/*    */   private int oneyuanDays;
/*    */   
/*    */   private int firstChargeDays;
/*    */ 
/*    */   
/*    */   void init(ParameterBean bean) {
/* 25 */     this.fundLimit = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(1))).getValue());
/* 26 */     this.costCCB = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(2))).getValue());
/*    */     
/* 28 */     this.oneyuanStartTime = TimeUtil.getTimeFromDate(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(3))).getValue());
/* 29 */     this.oneyuanEndTime = this.oneyuanStartTime + this.oneyuanDays * 86400;
/* 30 */     this.oneyuanDays = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(4))).getValue()).intValue();
/* 31 */     this.firstChargeDays = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(5))).getValue()).intValue();
/*    */   }
/*    */   
/*    */   public int getFundLimit() {
/* 35 */     return this.fundLimit;
/*    */   }
/*    */   
/*    */   public void setFundLimit(int fundLimit) {
/* 39 */     this.fundLimit = fundLimit;
/*    */   }
/*    */   
/*    */   public int getCostCCB() {
/* 43 */     return this.costCCB;
/*    */   }
/*    */   
/*    */   public void setCostCCB(int costCCB) {
/* 47 */     this.costCCB = costCCB;
/*    */   }
/*    */   
/*    */   public int getOneyuanStartTime() {
/* 51 */     return this.oneyuanStartTime;
/*    */   }
/*    */   
/*    */   public int getOneyuanEndTime() {
/* 55 */     return this.oneyuanEndTime;
/*    */   }
/*    */   
/*    */   public int getOneyuanDays() {
/* 59 */     return this.oneyuanDays;
/*    */   }
/*    */   
/*    */   public int getFirstChargeDays() {
/* 63 */     return this.firstChargeDays;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\parameter\FundParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */