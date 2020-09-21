/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class ExpLeaderBean
/*    */ {
/*    */   private int level;
/*    */   
/*    */   public int getLevel() {
/* 10 */     return this.level;
/*    */   }
/*    */   private long exp;
/*    */   private ArrayList<AttrBean> attr;
/*    */   
/*    */   public long getExp() {
/* 16 */     return this.exp;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<AttrBean> getAttr() {
/* 22 */     return this.attr;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 27 */     String retVal = "level= " + this.level + ", exp= " + this.exp + ", attr= " + this.attr;
/* 28 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\ExpLeaderBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */