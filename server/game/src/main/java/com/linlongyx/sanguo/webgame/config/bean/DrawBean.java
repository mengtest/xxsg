/*     */ package com.linlongyx.sanguo.webgame.config.bean;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class DrawBean {
/*     */   private int id;
/*     */   private int serverType;
/*     */   
/*     */   public int getId() {
/*  10 */     return this.id;
/*     */   }
/*     */   private int day; private String beginTime; private String endTime;
/*     */   private int raffle;
/*     */   
/*     */   public int getServerType() {
/*  16 */     return this.serverType;
/*     */   }
/*     */   private ArrayList<RareItemBean> rareItem;
/*     */   private ArrayList<DisplayRewardBean> displayReward;
/*     */   
/*     */   public int getDay() {
/*  22 */     return this.day;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getBeginTime() {
/*  28 */     return this.beginTime;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getEndTime() {
/*  34 */     return this.endTime;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRaffle() {
/*  40 */     return this.raffle;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<RareItemBean> getRareItem() {
/*  46 */     return this.rareItem;
/*     */   }
/*     */   
/*     */   public class RareItemBean {
/*     */     private int id;
/*     */     private int num;
/*     */     
/*     */     public int getId() {
/*  54 */       return this.id;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getNum() {
/*  59 */       return this.num;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/*  64 */       String retVal = "id= " + this.id + ", num= " + this.num;
/*  65 */       return retVal;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<DisplayRewardBean> getDisplayReward() {
/*  72 */     return this.displayReward;
/*     */   }
/*     */   public class DisplayRewardBean { private int type;
/*     */     private int id;
/*     */     private int num;
/*     */     
/*     */     public int getType() {
/*  79 */       return this.type;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public int getId() {
/*  85 */       return this.id;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getNum() {
/*  90 */       return this.num;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/*  95 */       String retVal = "type= " + this.type + ", id= " + this.id + ", num= " + this.num;
/*  96 */       return retVal;
/*     */     } }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 102 */     String retVal = "id= " + this.id + ", serverType= " + this.serverType + ", day= " + this.day + ", beginTime= " + this.beginTime + ", endTime= " + this.endTime + ", raffle= " + this.raffle + ", rareItem= " + this.rareItem + ", displayReward= " + this.displayReward;
/* 103 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\DrawBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */