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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GeneralGiftParameter
/*    */   extends AbstractParameter
/*    */ {
/*    */   private int openDay;
/*    */   
/*    */   public GeneralGiftParameter() {
/* 21 */     super(46);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   void init(ParameterBean bean) {
/* 28 */     this.openDay = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(1))).getValue()).intValue();
/*    */   }
/*    */   
/*    */   public int getOpenDay() {
/* 32 */     return this.openDay;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\parameter\GeneralGiftParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */