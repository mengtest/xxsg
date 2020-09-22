/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class PartnerRelationBean {
/*    */   private int id;
/*    */   private String name;
/*    */   
/*    */   public int getId() {
/* 11 */     return this.id;
/*    */   }
/*    */   
/*    */   private ArrayList<Integer> partners;
/*    */   
/*    */   public String getName() {
/* 17 */     return this.name;
/*    */   }
/*    */   
/*    */   private Map<Integer, RankBean> rank;
/*    */   
/*    */   public ArrayList<Integer> getPartners() {
/* 23 */     return this.partners;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Map<Integer, RankBean> getRank() {
/* 29 */     return this.rank;
/*    */   }
/*    */   
/*    */   public class RankBean
/*    */   {
/*    */     private ArrayList<AttrBean> attr;
/*    */     private ArrayList<Integer> effectSkill;
/*    */     
/*    */     public ArrayList<AttrBean> getAttr() {
/* 38 */       return this.attr;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public ArrayList<Integer> getEffectSkill() {
/* 44 */       return this.effectSkill;
/*    */     }
/*    */ 
/*    */     
/*    */     public String toString() {
/* 49 */       String retVal = "attr= " + this.attr + ", effectSkill= " + this.effectSkill;
/* 50 */       return retVal;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 56 */     String retVal = "id= " + this.id + ", name= " + this.name + ", partners= " + this.partners + ", rank= " + this.rank;
/* 57 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\PartnerRelationBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */