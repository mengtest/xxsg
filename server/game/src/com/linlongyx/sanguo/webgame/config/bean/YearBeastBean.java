/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class YearBeastBean {
/*    */   private int id;
/*    */   private int channelType;
/*    */   
/*    */   public int getId() {
/* 10 */     return this.id;
/*    */   }
/*    */   private int serverType; private String beginTime; private String endTime;
/*    */   private int day;
/*    */   
/*    */   public int getChannelType() {
/* 16 */     return this.channelType;
/*    */   }
/*    */   private int challLimit; private int purchaseLimit;
/*    */   private ArrayList<Integer> bossEntry;
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
/*    */   public int getChallLimit() {
/* 46 */     return this.challLimit;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPurchaseLimit() {
/* 52 */     return this.purchaseLimit;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<Integer> getBossEntry() {
/* 58 */     return this.bossEntry;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 63 */     String retVal = "id= " + this.id + ", channelType= " + this.channelType + ", serverType= " + this.serverType + ", beginTime= " + this.beginTime + ", endTime= " + this.endTime + ", day= " + this.day + ", challLimit= " + this.challLimit + ", purchaseLimit= " + this.purchaseLimit + ", bossEntry= " + this.bossEntry;
/* 64 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\YearBeastBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */