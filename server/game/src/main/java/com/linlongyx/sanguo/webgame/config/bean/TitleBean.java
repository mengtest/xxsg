/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class TitleBean
/*    */ {
/*    */   private int titleId;
/*    */   
/*    */   public int getTitleId() {
/* 10 */     return this.titleId;
/*    */   }
/*    */   private String titleName; private ArrayList<RewardBean> item;
/*    */   private ArrayList<AttrBean> attr;
/*    */   
/*    */   public String getTitleName() {
/* 16 */     return this.titleName;
/*    */   }
/*    */   private String icon;
/*    */   private int endTime;
/*    */   
/*    */   public ArrayList<RewardBean> getItem() {
/* 22 */     return this.item;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<AttrBean> getAttr() {
/* 28 */     return this.attr;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getIcon() {
/* 34 */     return this.icon;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getEndTime() {
/* 40 */     return this.endTime;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 45 */     String retVal = "titleId= " + this.titleId + ", titleName= " + this.titleName + ", item= " + this.item + ", attr= " + this.attr + ", icon= " + this.icon + ", endTime= " + this.endTime;
/* 46 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\TitleBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */