/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class ExpupBean {
/*    */   private int level;
/*    */   private long exp;
/*    */   
/*    */   public int getLevel() {
/* 10 */     return this.level;
/*    */   }
/*    */   
/*    */   private ArrayList<AttrBean> attr;
/*    */   
/*    */   public long getExp() {
/* 16 */     return this.exp;
/*    */   }
/*    */   
/*    */   private long fExp;
/*    */   
/*    */   public ArrayList<AttrBean> getAttr() {
/* 22 */     return this.attr;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public long getFExp() {
/* 28 */     return this.fExp;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 33 */     String retVal = "level= " + this.level + ", exp= " + this.exp + ", attr= " + this.attr + ", fExp= " + this.fExp;
/* 34 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\ExpupBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */