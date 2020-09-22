/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class MasterForgingBean
/*    */ {
/*    */   private int id;
/*    */   
/*    */   public int getId() {
/* 10 */     return this.id;
/*    */   }
/*    */   private int type; private int step;
/*    */   private int level;
/*    */   
/*    */   public int getType() {
/* 16 */     return this.type;
/*    */   }
/*    */   private ArrayList<AttrBonusBean> attrBonus;
/*    */   private String desc;
/*    */   
/*    */   public int getStep() {
/* 22 */     return this.step;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getLevel() {
/* 28 */     return this.level;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<AttrBonusBean> getAttrBonus() {
/* 34 */     return this.attrBonus;
/*    */   }
/*    */   
/*    */   public class AttrBonusBean {
/*    */     private int id;
/*    */     private int num;
/*    */     
/*    */     public int getId() {
/* 42 */       return this.id;
/*    */     }
/*    */ 
/*    */     
/*    */     public int getNum() {
/* 47 */       return this.num;
/*    */     }
/*    */ 
/*    */     
/*    */     public String toString() {
/* 52 */       String retVal = "id= " + this.id + ", num= " + this.num;
/* 53 */       return retVal;
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getDesc() {
/* 60 */     return this.desc;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 65 */     String retVal = "id= " + this.id + ", type= " + this.type + ", step= " + this.step + ", level= " + this.level + ", attrBonus= " + this.attrBonus + ", desc= " + this.desc;
/* 66 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\MasterForgingBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */