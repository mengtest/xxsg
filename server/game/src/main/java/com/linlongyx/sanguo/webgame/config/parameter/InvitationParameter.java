/*    */ package com.linlongyx.sanguo.webgame.config.parameter;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.config.bean.ParameterBean;
/*    */ 
/*    */ public class InvitationParameter extends AbstractParameter {
/*    */   private int dayLimit;
/*    */   private int totalLimit;
/*    */   private int inviteCD;
/*    */   
/*    */   public InvitationParameter() {
/* 11 */     super(36);
/*    */   }
/*    */ 
/*    */   
/*    */   private int inviteLevelNum;
/*    */   
/*    */   private int LevelCount;
/*    */   
/*    */   private int inviteCount;
/*    */   
/*    */   private int inviteRate;
/*    */   
/*    */   void init(ParameterBean bean) {
/* 24 */     this.dayLimit = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(1))).getValue());
/* 25 */     this.totalLimit = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(2))).getValue());
/* 26 */     this.inviteCD = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(3))).getValue());
/* 27 */     this.inviteLevelNum = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(4))).getValue());
/* 28 */     this.LevelCount = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(5))).getValue());
/* 29 */     this.inviteCount = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(6))).getValue());
/* 30 */     this.inviteRate = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(7))).getValue());
/*    */   }
/*    */   
/*    */   public int getDayLimit() {
/* 34 */     return this.dayLimit;
/*    */   }
/*    */   
/*    */   public int getTotalLimit() {
/* 38 */     return this.totalLimit;
/*    */   }
/*    */   
/*    */   public int getInviteCD() {
/* 42 */     return this.inviteCD;
/*    */   }
/*    */   
/*    */   public int getInviteLevelNum() {
/* 46 */     return this.inviteLevelNum;
/*    */   }
/*    */   
/*    */   public int getLevelCount() {
/* 50 */     return this.LevelCount;
/*    */   }
/*    */   
/*    */   public int getInviteCount() {
/* 54 */     return this.inviteCount;
/*    */   }
/*    */   
/*    */   public int getInviteRate() {
/* 58 */     return this.inviteRate;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\parameter\InvitationParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */