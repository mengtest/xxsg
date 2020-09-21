/*     */ package com.linlongyx.sanguo.webgame.config.bean;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class MoneyPotBean {
/*     */   private int id;
/*     */   private int channelType;
/*     */   private String name;
/*     */   
/*     */   public int getId() {
/*  10 */     return this.id;
/*     */   }
/*     */   private int actType; private int serverType; private String beginTime; private String endTime; private String finalTime;
/*     */   private int day;
/*     */   
/*     */   public int getChannelType() {
/*  16 */     return this.channelType;
/*     */   }
/*     */   private int limit; private int receivePoints; private ArrayList<CostRewardBean> costReward;
/*     */   private ArrayList<RebateRewardBean> rebateReward;
/*     */   
/*     */   public String getName() {
/*  22 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getActType() {
/*  28 */     return this.actType;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getServerType() {
/*  34 */     return this.serverType;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getBeginTime() {
/*  40 */     return this.beginTime;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getEndTime() {
/*  46 */     return this.endTime;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFinalTime() {
/*  52 */     return this.finalTime;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDay() {
/*  58 */     return this.day;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLimit() {
/*  64 */     return this.limit;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getReceivePoints() {
/*  70 */     return this.receivePoints;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<CostRewardBean> getCostReward() {
/*  76 */     return this.costReward;
/*     */   }
/*     */   public class CostRewardBean { private int type;
/*     */     private int id;
/*     */     private int num;
/*     */     
/*     */     public int getType() {
/*  83 */       return this.type;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public int getId() {
/*  89 */       return this.id;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getNum() {
/*  94 */       return this.num;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/*  99 */       String retVal = "type= " + this.type + ", id= " + this.id + ", num= " + this.num;
/* 100 */       return retVal;
/*     */     } }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<RebateRewardBean> getRebateReward() {
/* 107 */     return this.rebateReward;
/*     */   }
/*     */   public class RebateRewardBean { private int type;
/*     */     private int id;
/*     */     private int num;
/*     */     
/*     */     public int getType() {
/* 114 */       return this.type;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public int getId() {
/* 120 */       return this.id;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getNum() {
/* 125 */       return this.num;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 130 */       String retVal = "type= " + this.type + ", id= " + this.id + ", num= " + this.num;
/* 131 */       return retVal;
/*     */     } }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 137 */     String retVal = "id= " + this.id + ", channelType= " + this.channelType + ", name= " + this.name + ", actType= " + this.actType + ", serverType= " + this.serverType + ", beginTime= " + this.beginTime + ", endTime= " + this.endTime + ", finalTime= " + this.finalTime + ", day= " + this.day + ", limit= " + this.limit + ", receivePoints= " + this.receivePoints + ", costReward= " + this.costReward + ", rebateReward= " + this.rebateReward;
/* 138 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\MoneyPotBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */