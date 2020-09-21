/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class RechargeTurntableBean {
/*    */   private int actId;
/*    */   private String name;
/*    */   
/*    */   public int getActId() {
/* 10 */     return this.actId;
/*    */   }
/*    */   private int serverType; private String beginTime; private String endTime;
/*    */   private int day;
/*    */   
/*    */   public String getName() {
/* 16 */     return this.name;
/*    */   }
/*    */   private int raffle;
/*    */   private ArrayList<RareTimesBean> rareTimes;
/*    */   
/*    */   public int getServerType() {
/* 22 */     return this.serverType;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getBeginTime() {
/* 28 */     return this.beginTime;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getEndTime() {
/* 34 */     return this.endTime;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getDay() {
/* 40 */     return this.day;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getRaffle() {
/* 46 */     return this.raffle;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RareTimesBean> getRareTimes() {
/* 52 */     return this.rareTimes;
/*    */   }
/*    */   public class RareTimesBean { private int min;
/*    */     private int max;
/*    */     private int id;
/*    */     
/*    */     public int getMin() {
/* 59 */       return this.min;
/*    */     }
/*    */ 
/*    */     
/*    */     public int getMax() {
/* 64 */       return this.max;
/*    */     }
/*    */ 
/*    */     
/*    */     public int getId() {
/* 69 */       return this.id;
/*    */     }
/*    */ 
/*    */     
/*    */     public String toString() {
/* 74 */       String retVal = "min= " + this.min + ", max= " + this.max + ", id= " + this.id;
/* 75 */       return retVal;
/*    */     } }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 81 */     String retVal = "actId= " + this.actId + ", name= " + this.name + ", serverType= " + this.serverType + ", beginTime= " + this.beginTime + ", endTime= " + this.endTime + ", day= " + this.day + ", raffle= " + this.raffle + ", rareTimes= " + this.rareTimes;
/* 82 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\RechargeTurntableBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */