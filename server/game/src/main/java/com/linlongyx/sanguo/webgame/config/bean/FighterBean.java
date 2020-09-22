/*     */ package com.linlongyx.sanguo.webgame.config.bean;
/*     */ public class FighterBean {
/*     */   private int id;
/*     */   private String name;
/*     */   private int camp;
/*     */   private String rid;
/*     */   private int fightType;
/*     */   
/*     */   public int getId() {
/*  10 */     return this.id;
/*     */   }
/*     */   private int skill; private int hotSkill; private int fitSkill; private ArrayList<Integer> fitPartner; private int quality; private int card; private int piece; private int fury; private int hurtType;
/*     */   private ArrayList<AttrBean> attr;
/*     */   
/*     */   public String getName() {
/*  16 */     return this.name;
/*     */   }
/*     */   private ArrayList<Integer> relation; private int pieceNum; private ArrayList<FurnaceBean> furnace; private int cardBook; private int talismanId;
/*     */   private ArrayList<Integer> reincarn;
/*     */   
/*     */   public int getCamp() {
/*  22 */     return this.camp;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getRid() {
/*  28 */     return this.rid;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFightType() {
/*  34 */     return this.fightType;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSkill() {
/*  40 */     return this.skill;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHotSkill() {
/*  46 */     return this.hotSkill;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFitSkill() {
/*  52 */     return this.fitSkill;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<Integer> getFitPartner() {
/*  58 */     return this.fitPartner;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getQuality() {
/*  64 */     return this.quality;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCard() {
/*  70 */     return this.card;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPiece() {
/*  76 */     return this.piece;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFury() {
/*  82 */     return this.fury;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHurtType() {
/*  88 */     return this.hurtType;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<AttrBean> getAttr() {
/*  94 */     return this.attr;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<Integer> getRelation() {
/* 100 */     return this.relation;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPieceNum() {
/* 106 */     return this.pieceNum;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<FurnaceBean> getFurnace() {
/* 112 */     return this.furnace;
/*     */   }
/*     */   public class FurnaceBean { private int type;
/*     */     private int id;
/*     */     private int num;
/*     */     
/*     */     public int getType() {
/* 119 */       return this.type;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public int getId() {
/* 125 */       return this.id;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getNum() {
/* 130 */       return this.num;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 135 */       String retVal = "type= " + this.type + ", id= " + this.id + ", num= " + this.num;
/* 136 */       return retVal;
/*     */     } }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCardBook() {
/* 143 */     return this.cardBook;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTalismanId() {
/* 149 */     return this.talismanId;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<Integer> getReincarn() {
/* 155 */     return this.reincarn;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 160 */     String retVal = "id= " + this.id + ", name= " + this.name + ", camp= " + this.camp + ", rid= " + this.rid + ", fightType= " + this.fightType + ", skill= " + this.skill + ", hotSkill= " + this.hotSkill + ", fitSkill= " + this.fitSkill + ", fitPartner= " + this.fitPartner + ", quality= " + this.quality + ", card= " + this.card + ", piece= " + this.piece + ", fury= " + this.fury + ", hurtType= " + this.hurtType + ", attr= " + this.attr + ", relation= " + this.relation + ", pieceNum= " + this.pieceNum + ", furnace= " + this.furnace + ", cardBook= " + this.cardBook + ", talismanId= " + this.talismanId + ", reincarn= " + this.reincarn;
/* 161 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\FighterBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */