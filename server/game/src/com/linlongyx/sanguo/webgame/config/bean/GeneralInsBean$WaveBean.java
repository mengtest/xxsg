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
/*     */ public class WaveBean
/*     */ {
/*     */   private int round;
/*     */   private ArrayList<MonsterBean> monster;
/*     */   
/*     */   public int getRound() {
/*  80 */     return this.round;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<MonsterBean> getMonster() {
/*  86 */     return this.monster;
/*     */   }
/*     */   
/*     */   public class MonsterBean {
/*     */     private int posi;
/*     */     
/*     */     public int getPosi() {
/*  93 */       return this.posi;
/*     */     }
/*     */     private int id;
/*     */     
/*     */     public int getId() {
/*  98 */       return this.id;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 103 */       String retVal = "posi= " + this.posi + ", id= " + this.id;
/* 104 */       return retVal;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 110 */     String retVal = "round= " + this.round + ", monster= " + this.monster;
/* 111 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\GeneralInsBean$WaveBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */