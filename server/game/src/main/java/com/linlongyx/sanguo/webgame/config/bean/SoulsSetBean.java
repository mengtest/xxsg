/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ public class SoulsSetBean {
/*    */   private int id;
/*    */   private String name;
/*    */   
/*    */   public int getId() {
/*  8 */     return this.id;
/*    */   }
/*    */   private String rid; private int fightType; private int skill;
/*    */   private int quality;
/*    */   
/*    */   public String getName() {
/* 14 */     return this.name;
/*    */   }
/*    */   private int fury; private int hurtType;
/*    */   private int pieceNum;
/*    */   
/*    */   public String getRid() {
/* 20 */     return this.rid;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getFightType() {
/* 26 */     return this.fightType;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getSkill() {
/* 32 */     return this.skill;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getQuality() {
/* 38 */     return this.quality;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getFury() {
/* 44 */     return this.fury;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getHurtType() {
/* 50 */     return this.hurtType;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPieceNum() {
/* 56 */     return this.pieceNum;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 61 */     String retVal = "id= " + this.id + ", name= " + this.name + ", rid= " + this.rid + ", fightType= " + this.fightType + ", skill= " + this.skill + ", quality= " + this.quality + ", fury= " + this.fury + ", hurtType= " + this.hurtType + ", pieceNum= " + this.pieceNum;
/* 62 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\SoulsSetBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */