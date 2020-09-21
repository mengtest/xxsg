/*    */ package com.linlongyx.sanguo.webgame.config.parameter;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.config.bean.ParameterBean;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SecretiParameter
/*    */   extends AbstractParameter
/*    */ {
/*    */   private int rewardTimes;
/*    */   
/*    */   public SecretiParameter() {
/* 17 */     super(44);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   void init(ParameterBean bean) {
/* 24 */     this.rewardTimes = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(1))).getValue());
/*    */   }
/*    */   
/*    */   public int getRewardTimes() {
/* 28 */     return this.rewardTimes;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\parameter\SecretiParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */