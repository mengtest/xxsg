/*     */ package com.linlongyx.sanguo.webgame.config.bean;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class TurnplateBean {
/*     */   private int id;
/*     */   private int serverType;
/*     */   
/*     */   public int getId() {
/*  11 */     return this.id;
/*     */   }
/*     */   private int day; private String beginTime; private String endTime;
/*     */   private int raffle;
/*     */   
/*     */   public int getServerType() {
/*  17 */     return this.serverType;
/*     */   }
/*     */   private ArrayList<Integer> goodsId; private ArrayList<RareTimesBean> rareTimes;
/*     */   private Map<Integer, RareItemBean> rareItem;
/*     */   
/*     */   public int getDay() {
/*  23 */     return this.day;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getBeginTime() {
/*  29 */     return this.beginTime;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getEndTime() {
/*  35 */     return this.endTime;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRaffle() {
/*  41 */     return this.raffle;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<Integer> getGoodsId() {
/*  47 */     return this.goodsId;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<RareTimesBean> getRareTimes() {
/*  53 */     return this.rareTimes;
/*     */   }
/*     */   
/*     */   public class RareTimesBean { private int min;
/*     */     private int max;
/*     */     
/*     */     public int getMin() {
/*  60 */       return this.min;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getMax() {
/*  65 */       return this.max;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/*  70 */       String retVal = "min= " + this.min + ", max= " + this.max;
/*  71 */       return retVal;
/*     */     } }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<Integer, RareItemBean> getRareItem() {
/*  78 */     return this.rareItem;
/*     */   }
/*     */   
/*     */   public class RareItemBean { private int id;
/*     */     private int num;
/*     */     private int isbroadcast;
/*     */     
/*     */     public int getId() {
/*  86 */       return this.id;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getNum() {
/*  91 */       return this.num;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getIsbroadcast() {
/*  96 */       return this.isbroadcast;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 101 */       String retVal = "id= " + this.id + ", num= " + this.num + ", isbroadcast= " + this.isbroadcast;
/* 102 */       return retVal;
/*     */     } }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 108 */     String retVal = "id= " + this.id + ", serverType= " + this.serverType + ", day= " + this.day + ", beginTime= " + this.beginTime + ", endTime= " + this.endTime + ", raffle= " + this.raffle + ", goodsId= " + this.goodsId + ", rareTimes= " + this.rareTimes + ", rareItem= " + this.rareItem;
/* 109 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\TurnplateBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */