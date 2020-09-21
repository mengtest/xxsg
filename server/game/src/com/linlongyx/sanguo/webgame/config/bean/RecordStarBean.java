/*     */ package com.linlongyx.sanguo.webgame.config.bean;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class RecordStarBean {
/*     */   private int recordStar;
/*     */   private int preStar;
/*     */   
/*     */   public int getRecordStar() {
/*  10 */     return this.recordStar;
/*     */   }
/*     */   private ArrayList<TaskTpyeBean> taskTpye; private ArrayList<RewardBean> cost; private ArrayList<AttrBean> attr;
/*     */   private int quality;
/*     */   
/*     */   public int getPreStar() {
/*  16 */     return this.preStar;
/*     */   }
/*     */   private ArrayList<Integer> recordSkill;
/*     */   private ArrayList<RewardBean> reward;
/*     */   
/*     */   public ArrayList<TaskTpyeBean> getTaskTpye() {
/*  22 */     return this.taskTpye;
/*     */   }
/*     */   public class TaskTpyeBean { private int type;
/*     */     private int targetId;
/*     */     private int num;
/*     */     
/*     */     public int getType() {
/*  29 */       return this.type;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getTargetId() {
/*  34 */       return this.targetId;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getNum() {
/*  39 */       return this.num;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/*  44 */       String retVal = "type= " + this.type + ", targetId= " + this.targetId + ", num= " + this.num;
/*  45 */       return retVal;
/*     */     } }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<RewardBean> getCost() {
/*  52 */     return this.cost;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<AttrBean> getAttr() {
/*  58 */     return this.attr;
/*     */   }
/*     */   
/*     */   public class AttrBean { private int id;
/*     */     private int num;
/*     */     
/*     */     public int getId() {
/*  65 */       return this.id;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getNum() {
/*  70 */       return this.num;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/*  75 */       String retVal = "id= " + this.id + ", num= " + this.num;
/*  76 */       return retVal;
/*     */     } }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getQuality() {
/*  83 */     return this.quality;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<Integer> getRecordSkill() {
/*  89 */     return this.recordSkill;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<RewardBean> getReward() {
/*  95 */     return this.reward;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 100 */     String retVal = "recordStar= " + this.recordStar + ", preStar= " + this.preStar + ", taskTpye= " + this.taskTpye + ", cost= " + this.cost + ", attr= " + this.attr + ", quality= " + this.quality + ", recordSkill= " + this.recordSkill + ", reward= " + this.reward;
/* 101 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\RecordStarBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */