/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class BlocSkillBean
/*    */ {
/*    */   private int id;
/*    */   
/*    */   public int getId() {
/* 10 */     return this.id;
/*    */   }
/*    */   private int skillLevel; private int index;
/*    */   private ArrayList<AttrBean> attr;
/*    */   
/*    */   public int getSkillLevel() {
/* 16 */     return this.skillLevel;
/*    */   }
/*    */   private int cost;
/*    */   private int blocLevel;
/*    */   
/*    */   public int getIndex() {
/* 22 */     return this.index;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<AttrBean> getAttr() {
/* 28 */     return this.attr;
/*    */   }
/*    */   
/*    */   public class AttrBean {
/*    */     private int id;
/*    */     private int num;
/*    */     
/*    */     public int getId() {
/* 36 */       return this.id;
/*    */     }
/*    */ 
/*    */     
/*    */     public int getNum() {
/* 41 */       return this.num;
/*    */     }
/*    */ 
/*    */     
/*    */     public String toString() {
/* 46 */       String retVal = "id= " + this.id + ", num= " + this.num;
/* 47 */       return retVal;
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getCost() {
/* 54 */     return this.cost;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getBlocLevel() {
/* 60 */     return this.blocLevel;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 65 */     String retVal = "id= " + this.id + ", skillLevel= " + this.skillLevel + ", index= " + this.index + ", attr= " + this.attr + ", cost= " + this.cost + ", blocLevel= " + this.blocLevel;
/* 66 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\BlocSkillBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */