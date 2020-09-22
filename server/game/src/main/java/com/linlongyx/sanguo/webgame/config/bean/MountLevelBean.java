/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class MountLevelBean {
/*    */   private int level;
/*    */   private int exp;
/*    */   
/*    */   public int getLevel() {
/* 10 */     return this.level;
/*    */   }
/*    */   
/*    */   private ArrayList<AttrBean> attr;
/*    */   
/*    */   public int getExp() {
/* 16 */     return this.exp;
/*    */   }
/*    */   
/*    */   private ArrayList<RewardBean> breakC;
/*    */   
/*    */   public ArrayList<AttrBean> getAttr() {
/* 22 */     return this.attr;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getBreakC() {
/* 28 */     return this.breakC;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 33 */     String retVal = "level= " + this.level + ", exp= " + this.exp + ", attr= " + this.attr + ", breakC= " + this.breakC;
/* 34 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\MountLevelBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */