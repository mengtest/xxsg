/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class MainInsBean
/*    */ {
/*    */   private int insId;
/*    */   
/*    */   public int getInsId() {
/* 11 */     return this.insId;
/*    */   }
/*    */   private int chapter; private int ins; private int level;
/*    */   private ArrayList<RewardBean> reward;
/*    */   
/*    */   public int getChapter() {
/* 17 */     return this.chapter;
/*    */   }
/*    */   private ArrayList<RewardBean> chapteReward;
/*    */   private Map<Integer, WaveBean> wave;
/*    */   
/*    */   public int getIns() {
/* 23 */     return this.ins;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getLevel() {
/* 29 */     return this.level;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getReward() {
/* 35 */     return this.reward;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getChapteReward() {
/* 41 */     return this.chapteReward;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Map<Integer, WaveBean> getWave() {
/* 47 */     return this.wave;
/*    */   }
/*    */   
/*    */   public class WaveBean
/*    */   {
/*    */     private int round;
/*    */     private ArrayList<MonsterBean> monster;
/*    */     
/*    */     public int getRound() {
/* 56 */       return this.round;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public ArrayList<MonsterBean> getMonster() {
/* 62 */       return this.monster;
/*    */     }
/*    */     
/*    */     public class MonsterBean { private int posi;
/*    */       private int id;
/*    */       
/*    */       public int getPosi() {
/* 69 */         return this.posi;
/*    */       }
/*    */ 
/*    */       
/*    */       public int getId() {
/* 74 */         return this.id;
/*    */       }
/*    */ 
/*    */       
/*    */       public String toString() {
/* 79 */         String retVal = "posi= " + this.posi + ", id= " + this.id;
/* 80 */         return retVal;
/*    */       } }
/*    */ 
/*    */ 
/*    */     
/*    */     public String toString() {
/* 86 */       String retVal = "round= " + this.round + ", monster= " + this.monster;
/* 87 */       return retVal;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 93 */     String retVal = "insId= " + this.insId + ", chapter= " + this.chapter + ", ins= " + this.ins + ", level= " + this.level + ", reward= " + this.reward + ", chapteReward= " + this.chapteReward + ", wave= " + this.wave;
/* 94 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\MainInsBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */