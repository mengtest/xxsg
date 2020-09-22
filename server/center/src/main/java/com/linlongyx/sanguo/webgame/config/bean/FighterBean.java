/*     */ package com.linlongyx.sanguo.webgame.config.bean;
/*     */ 
/*     */ public class FighterBean {
/*     */   private int id;
/*     */   private String name;
/*     */   private int camp;
/*     */   private String rid;
/*     */   
/*     */   public int getId() {
/*  10 */     return this.id;
/*     */   }
/*     */   private int fightType; private int skill; private int hotSkill; private int fitSkill; private ArrayList<Integer> fitPartner; private int quality; private int card; private int piece; private int fury;
/*     */   private int hurtType;
/*     */   
/*     */   public String getName() {
/*  16 */     return this.name;
/*     */   }
/*     */   private ArrayList<AttrBean> attr; private ArrayList<AttrParBean> attrPar; private ArrayList<Integer> relation; private int pieceNum;
/*     */   private ArrayList<FurnaceBean> furnace;
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
/*     */   public ArrayList<AttrParBean> getAttrPar() {
/* 100 */     return this.attrPar;
/*     */   }
/*     */   
/*     */   public class AttrParBean {
/*     */     private int id;
/*     */     private int par;
/*     */     
/*     */     public int getId() {
/* 108 */       return this.id;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getPar() {
/* 113 */       return this.par;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 118 */       String retVal = "id= " + this.id + ", par= " + this.par;
/* 119 */       return retVal;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<Integer> getRelation() {
/* 126 */     return this.relation;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPieceNum() {
/* 132 */     return this.pieceNum;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<FurnaceBean> getFurnace() {
/* 138 */     return this.furnace;
/*     */   }
/*     */   public class FurnaceBean { private int type;
/*     */     private int id;
/*     */     private int num;
/*     */     
/*     */     public int getType() {
/* 145 */       return this.type;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public int getId() {
/* 151 */       return this.id;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getNum() {
/* 156 */       return this.num;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 161 */       String retVal = "type= " + this.type + ", id= " + this.id + ", num= " + this.num;
/* 162 */       return retVal;
/*     */     } }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 168 */     String retVal = "id= " + this.id + ", name= " + this.name + ", camp= " + this.camp + ", rid= " + this.rid + ", fightType= " + this.fightType + ", skill= " + this.skill + ", hotSkill= " + this.hotSkill + ", fitSkill= " + this.fitSkill + ", fitPartner= " + this.fitPartner + ", quality= " + this.quality + ", card= " + this.card + ", piece= " + this.piece + ", fury= " + this.fury + ", hurtType= " + this.hurtType + ", attr= " + this.attr + ", attrPar= " + this.attrPar + ", relation= " + this.relation + ", pieceNum= " + this.pieceNum + ", furnace= " + this.furnace;
/* 169 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\FighterBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */