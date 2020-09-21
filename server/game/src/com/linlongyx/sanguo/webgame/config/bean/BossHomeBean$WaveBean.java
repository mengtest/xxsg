/*     */ package com.linlongyx.sanguo.webgame.config.bean;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WaveBean
/*     */ {
/*     */   private int round;
/*     */   private ArrayList<MonsterBean> monster;
/*     */   
/*     */   public int getRound() {
/* 106 */     return this.round;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<MonsterBean> getMonster() {
/* 112 */     return this.monster;
/*     */   }
/*     */   
/*     */   public class MonsterBean {
/*     */     private int posi;
/*     */     
/*     */     public int getPosi() {
/* 119 */       return this.posi;
/*     */     }
/*     */     private int id;
/*     */     
/*     */     public int getId() {
/* 124 */       return this.id;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 129 */       String retVal = "posi= " + this.posi + ", id= " + this.id;
/* 130 */       return retVal;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 136 */     String retVal = "round= " + this.round + ", monster= " + this.monster;
/* 137 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\BossHomeBean$WaveBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */