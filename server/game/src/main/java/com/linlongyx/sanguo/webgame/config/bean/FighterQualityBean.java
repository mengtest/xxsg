/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class FighterQualityBean {
/*    */   private int fightType;
/*    */   private Map<Integer, QualityBean> quality;
/*    */   
/*    */   public int getFightType() {
/* 11 */     return this.fightType;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Map<Integer, QualityBean> getQuality() {
/* 17 */     return this.quality;
/*    */   }
/*    */ 
/*    */   
/*    */   public class QualityBean
/*    */   {
/*    */     private ArrayList<AttrParBean> attrPar;
/*    */     
/*    */     public ArrayList<AttrParBean> getAttrPar() {
/* 26 */       return this.attrPar;
/*    */     }
/*    */     
/*    */     public class AttrParBean {
/*    */       private int id;
/*    */       
/*    */       public int getId() {
/* 33 */         return this.id;
/*    */       }
/*    */       private int par;
/*    */       
/*    */       public int getPar() {
/* 38 */         return this.par;
/*    */       }
/*    */ 
/*    */       
/*    */       public String toString() {
/* 43 */         String retVal = "id= " + this.id + ", par= " + this.par;
/* 44 */         return retVal;
/*    */       }
/*    */     }
/*    */ 
/*    */     
/*    */     public String toString() {
/* 50 */       String retVal = "attrPar= " + this.attrPar;
/* 51 */       return retVal;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 57 */     String retVal = "fightType= " + this.fightType + ", quality= " + this.quality;
/* 58 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\FighterQualityBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */