/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class StageHandbookBean
/*    */ {
/*    */   private int id;
/*    */   
/*    */   public int getId() {
/* 11 */     return this.id;
/*    */   }
/*    */   private ArrayList<Integer> stage;
/*    */   private Map<Integer, StarBean> star;
/*    */   
/*    */   public ArrayList<Integer> getStage() {
/* 17 */     return this.stage;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Map<Integer, StarBean> getStar() {
/* 23 */     return this.star;
/*    */   }
/*    */ 
/*    */   
/*    */   public class StarBean
/*    */   {
/*    */     private ArrayList<AttrBean> attr;
/*    */     
/*    */     public ArrayList<AttrBean> getAttr() {
/* 32 */       return this.attr;
/*    */     }
/*    */ 
/*    */     
/*    */     public String toString() {
/* 37 */       String retVal = "attr= " + this.attr;
/* 38 */       return retVal;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 44 */     String retVal = "id= " + this.id + ", stage= " + this.stage + ", star= " + this.star;
/* 45 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\StageHandbookBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */