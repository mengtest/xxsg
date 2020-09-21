/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class RelationBean {
/*    */   private int id;
/*    */   private String name;
/*    */   
/*    */   public int getId() {
/* 10 */     return this.id;
/*    */   }
/*    */   
/*    */   private int type;
/*    */   
/*    */   public String getName() {
/* 16 */     return this.name;
/*    */   }
/*    */   
/*    */   private ArrayList<Integer> para;
/*    */   
/*    */   public int getType() {
/* 22 */     return this.type;
/*    */   }
/*    */   
/*    */   private ArrayList<AttrBean> attr;
/*    */   
/*    */   public ArrayList<Integer> getPara() {
/* 28 */     return this.para;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<AttrBean> getAttr() {
/* 34 */     return this.attr;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 39 */     String retVal = "id= " + this.id + ", name= " + this.name + ", type= " + this.type + ", para= " + this.para + ", attr= " + this.attr;
/* 40 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\RelationBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */