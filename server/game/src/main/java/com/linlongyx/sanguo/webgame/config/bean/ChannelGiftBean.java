/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class ChannelGiftBean
/*    */ {
/*    */   private int id;
/*    */   
/*    */   public int getId() {
/* 10 */     return this.id;
/*    */   }
/*    */   private int type; private String activeName;
/*    */   private int time;
/*    */   
/*    */   public int getType() {
/* 16 */     return this.type;
/*    */   }
/*    */   private String value;
/*    */   private ArrayList<RewardBean> reward;
/*    */   
/*    */   public String getActiveName() {
/* 22 */     return this.activeName;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getTime() {
/* 28 */     return this.time;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getValue() {
/* 34 */     return this.value;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getReward() {
/* 40 */     return this.reward;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 45 */     String retVal = "id= " + this.id + ", type= " + this.type + ", activeName= " + this.activeName + ", time= " + this.time + ", value= " + this.value + ", reward= " + this.reward;
/* 46 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\ChannelGiftBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */