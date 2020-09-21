/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class RuneSuitBean {
/*    */   private int id;
/*    */   private String name;
/*    */   
/*    */   public int getId() {
/* 11 */     return this.id;
/*    */   }
/*    */   
/*    */   private ArrayList<Integer> rune;
/*    */   
/*    */   public String getName() {
/* 17 */     return this.name;
/*    */   }
/*    */   
/*    */   private int num;
/*    */   
/*    */   public ArrayList<Integer> getRune() {
/* 23 */     return this.rune;
/*    */   }
/*    */   
/*    */   private Map<Integer, RankBean> rank;
/*    */   
/*    */   public int getNum() {
/* 29 */     return this.num;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Map<Integer, RankBean> getRank() {
/* 35 */     return this.rank;
/*    */   }
/*    */ 
/*    */   
/*    */   public class RankBean
/*    */   {
/*    */     private ArrayList<AttrBean> attr;
/*    */     
/*    */     public ArrayList<AttrBean> getAttr() {
/* 44 */       return this.attr;
/*    */     }
/*    */ 
/*    */     
/*    */     public String toString() {
/* 49 */       String retVal = "attr= " + this.attr;
/* 50 */       return retVal;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 56 */     String retVal = "id= " + this.id + ", name= " + this.name + ", rune= " + this.rune + ", num= " + this.num + ", rank= " + this.rank;
/* 57 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\RuneSuitBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */