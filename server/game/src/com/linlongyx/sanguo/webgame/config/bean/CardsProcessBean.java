/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class CardsProcessBean {
/*    */   private int id;
/*    */   private int chapter;
/*    */   
/*    */   public int getId() {
/* 10 */     return this.id;
/*    */   }
/*    */   
/*    */   private int require;
/*    */   
/*    */   public int getChapter() {
/* 16 */     return this.chapter;
/*    */   }
/*    */   
/*    */   private ArrayList<RewardBean> reward;
/*    */   
/*    */   public int getRequire() {
/* 22 */     return this.require;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getReward() {
/* 28 */     return this.reward;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 33 */     String retVal = "id= " + this.id + ", chapter= " + this.chapter + ", require= " + this.require + ", reward= " + this.reward;
/* 34 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\CardsProcessBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */