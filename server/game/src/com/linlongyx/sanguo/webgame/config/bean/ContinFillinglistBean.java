/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class ContinFillinglistBean {
/*    */   private int id;
/*    */   private int day;
/*    */   
/*    */   public int getId() {
/* 10 */     return this.id;
/*    */   }
/*    */   
/*    */   private int rmb;
/*    */   
/*    */   public int getDay() {
/* 16 */     return this.day;
/*    */   }
/*    */   
/*    */   private ArrayList<RewardBean> Reward;
/*    */   
/*    */   public int getRmb() {
/* 22 */     return this.rmb;
/*    */   }
/*    */   
/*    */   private int connectortype;
/*    */   
/*    */   public ArrayList<RewardBean> getReward() {
/* 28 */     return this.Reward;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getConnectortype() {
/* 34 */     return this.connectortype;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 39 */     String retVal = "id= " + this.id + ", day= " + this.day + ", rmb= " + this.rmb + ", Reward= " + this.Reward + ", connectortype= " + this.connectortype;
/* 40 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\ContinFillinglistBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */