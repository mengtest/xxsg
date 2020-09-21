/*    */ package com.linlongyx.sanguo.webgame.config.parameter;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.config.bean.ParameterBean;
/*    */ 
/*    */ public class WanderParameter
/*    */   extends AbstractParameter
/*    */ {
/*    */   private int spaceTime;
/*    */   private int spaceSend;
/*    */   private int randNum;
/*    */   
/*    */   public WanderParameter() {
/* 13 */     super(71);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   void init(ParameterBean bean) {
/* 19 */     String str = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(1))).getValue();
/* 20 */     this.spaceTime = Integer.parseInt(str);
/* 21 */     str = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(2))).getValue();
/* 22 */     this.spaceSend = Integer.parseInt(str);
/* 23 */     str = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(3))).getValue();
/* 24 */     this.randNum = Integer.parseInt(str);
/*    */   }
/*    */   
/*    */   public int getSpaceTime() {
/* 28 */     return this.spaceTime;
/*    */   }
/*    */   
/*    */   public int getSpaceSend() {
/* 32 */     return this.spaceSend;
/*    */   }
/*    */   
/*    */   public int getRandNum() {
/* 36 */     return this.randNum;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\parameter\WanderParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */