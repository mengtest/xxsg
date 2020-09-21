/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class CheckDeluxeBean {
/*    */   private int id;
/*    */   
/*    */   public int getId() {
/*  9 */     return this.id;
/*    */   }
/*    */   private int level; private int rmb; private int Type; private ArrayList<RewardBean> reward;
/*    */   private int connectortype;
/*    */   
/*    */   public int getLevel() {
/* 15 */     return this.level;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getRmb() {
/* 21 */     return this.rmb;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getType() {
/* 27 */     return this.Type;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getReward() {
/* 33 */     return this.reward;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getConnectortype() {
/* 39 */     return this.connectortype;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 44 */     String retVal = "id= " + this.id + ", level= " + this.level + ", rmb= " + this.rmb + ", Type= " + this.Type + ", reward= " + this.reward + ", connectortype= " + this.connectortype;
/* 45 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\CheckDeluxeBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */