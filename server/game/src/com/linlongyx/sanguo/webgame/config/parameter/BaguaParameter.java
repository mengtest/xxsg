/*    */ package com.linlongyx.sanguo.webgame.config.parameter;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.config.bean.ParameterBean;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BaguaParameter
/*    */   extends AbstractParameter
/*    */ {
/*    */   private int asistTime;
/*    */   
/*    */   public BaguaParameter() {
/* 13 */     super(41);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   void init(ParameterBean bean) {
/* 21 */     this.asistTime = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(1))).getValue()).intValue();
/*    */   }
/*    */   
/*    */   public int getAsistTime() {
/* 25 */     return this.asistTime;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\parameter\BaguaParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */