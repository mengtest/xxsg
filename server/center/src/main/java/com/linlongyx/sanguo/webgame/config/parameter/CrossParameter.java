/*    */ package com.linlongyx.sanguo.webgame.config.parameter;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.config.bean.ParameterBean;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CrossParameter
/*    */   extends AbstractParameter
/*    */ {
/*    */   private int days;
/*    */   
/*    */   public CrossParameter() {
/* 13 */     super(81);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   void init(ParameterBean bean) {
/* 20 */     this.days = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(2))).getValue()).intValue();
/*    */   }
/*    */   
/*    */   public int getDays() {
/* 24 */     return this.days;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\config\parameter\CrossParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */