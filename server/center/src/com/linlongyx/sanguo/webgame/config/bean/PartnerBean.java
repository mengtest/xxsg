/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class PartnerBean {
/*    */   private int id;
/*    */   private String name;
/*    */   
/*    */   public int getId() {
/* 10 */     return this.id;
/*    */   }
/*    */   private String rid; private ArrayList<Integer> skill; private int rank;
/*    */   private int piece;
/*    */   
/*    */   public String getName() {
/* 16 */     return this.name;
/*    */   }
/*    */   private int card;
/*    */   private int system;
/*    */   
/*    */   public String getRid() {
/* 22 */     return this.rid;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<Integer> getSkill() {
/* 28 */     return this.skill;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getRank() {
/* 34 */     return this.rank;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPiece() {
/* 40 */     return this.piece;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getCard() {
/* 46 */     return this.card;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getSystem() {
/* 52 */     return this.system;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 57 */     String retVal = "id= " + this.id + ", name= " + this.name + ", rid= " + this.rid + ", skill= " + this.skill + ", rank= " + this.rank + ", piece= " + this.piece + ", card= " + this.card + ", system= " + this.system;
/* 58 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\PartnerBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */