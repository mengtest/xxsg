/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RankBean
/*    */ {
/*    */   private ArrayList<AttrBean> attr;
/*    */   private ArrayList<Integer> effectSkill;
/*    */   
/*    */   public ArrayList<AttrBean> getAttr() {
/* 38 */     return this.attr;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<Integer> getEffectSkill() {
/* 44 */     return this.effectSkill;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 49 */     String retVal = "attr= " + this.attr + ", effectSkill= " + this.effectSkill;
/* 50 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\PartnerRelationBean$RankBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */