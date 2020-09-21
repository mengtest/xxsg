/*     */ package com.linlongyx.sanguo.webgame.config.bean;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class MapTableBean {
/*     */   private int id;
/*     */   private String rid;
/*     */   private String name;
/*     */   
/*     */   public int getId() {
/*  10 */     return this.id;
/*     */   }
/*     */   private int levelNeeded; private ArrayList<RewardBean> mapIncome; private ArrayList<BornPointBean> bornPoint; private int bornArea; private ArrayList<Integer> monsterId; private ArrayList<BornPointBean> monsBornPoint; private ArrayList<Integer> monsBornArea;
/*     */   private ArrayList<Integer> monsNumber;
/*     */   
/*     */   public String getRid() {
/*  16 */     return this.rid;
/*     */   }
/*     */   private ArrayList<Integer> rebotId; private ArrayList<BornPointBean> robotBornPoint; private ArrayList<Integer> npcId;
/*     */   private int heal;
/*     */   
/*     */   public String getName() {
/*  22 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLevelNeeded() {
/*  28 */     return this.levelNeeded;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<RewardBean> getMapIncome() {
/*  34 */     return this.mapIncome;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<BornPointBean> getBornPoint() {
/*  40 */     return this.bornPoint;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBornArea() {
/*  46 */     return this.bornArea;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<Integer> getMonsterId() {
/*  52 */     return this.monsterId;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<BornPointBean> getMonsBornPoint() {
/*  58 */     return this.monsBornPoint;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<Integer> getMonsBornArea() {
/*  64 */     return this.monsBornArea;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<Integer> getMonsNumber() {
/*  70 */     return this.monsNumber;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<Integer> getRebotId() {
/*  76 */     return this.rebotId;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<BornPointBean> getRobotBornPoint() {
/*  82 */     return this.robotBornPoint;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<Integer> getNpcId() {
/*  88 */     return this.npcId;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHeal() {
/*  94 */     return this.heal;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  99 */     String retVal = "id= " + this.id + ", rid= " + this.rid + ", name= " + this.name + ", levelNeeded= " + this.levelNeeded + ", mapIncome= " + this.mapIncome + ", bornPoint= " + this.bornPoint + ", bornArea= " + this.bornArea + ", monsterId= " + this.monsterId + ", monsBornPoint= " + this.monsBornPoint + ", monsBornArea= " + this.monsBornArea + ", monsNumber= " + this.monsNumber + ", rebotId= " + this.rebotId + ", robotBornPoint= " + this.robotBornPoint + ", npcId= " + this.npcId + ", heal= " + this.heal;
/* 100 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\MapTableBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */