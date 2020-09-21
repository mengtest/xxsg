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
/*     */ public class WaveBean
/*     */ {
/*     */   private int round;
/*     */   private ArrayList<MonsterBean> monster;
/*     */   
/*     */   public int getRound() {
/*  92 */     return this.round;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<MonsterBean> getMonster() {
/*  98 */     return this.monster;
/*     */   }
/*     */   
/*     */   public class MonsterBean {
/*     */     private int posi;
/*     */     
/*     */     public int getPosi() {
/* 105 */       return this.posi;
/*     */     }
/*     */     private int id;
/*     */     
/*     */     public int getId() {
/* 110 */       return this.id;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 115 */       String retVal = "posi= " + this.posi + ", id= " + this.id;
/* 116 */       return retVal;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 122 */     String retVal = "round= " + this.round + ", monster= " + this.monster;
/* 123 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\BaguaInsBean$WaveBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */