/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class ZhenfaHandbookBean
/*    */ {
/*    */   private int id;
/*    */   
/*    */   public int getId() {
/* 11 */     return this.id;
/*    */   }
/*    */   private ArrayList<Integer> zhenfa;
/*    */   private Map<Integer, StarBean> star;
/*    */   
/*    */   public ArrayList<Integer> getZhenfa() {
/* 17 */     return this.zhenfa;
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
/* 44 */     String retVal = "id= " + this.id + ", zhenfa= " + this.zhenfa + ", star= " + this.star;
/* 45 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\ZhenfaHandbookBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */