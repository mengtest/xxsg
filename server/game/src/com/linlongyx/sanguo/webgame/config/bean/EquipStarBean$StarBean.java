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
/*    */ public class StarBean
/*    */ {
/*    */   private int att_star;
/*    */   private int cost;
/*    */   private ArrayList<AttrBean> star_one;
/*    */   private int recover;
/*    */   
/*    */   public int getAtt_star() {
/* 26 */     return this.att_star;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getCost() {
/* 32 */     return this.cost;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<AttrBean> getStar_one() {
/* 38 */     return this.star_one;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getRecover() {
/* 44 */     return this.recover;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 49 */     String retVal = "att_star= " + this.att_star + ", cost= " + this.cost + ", star_one= " + this.star_one + ", recover= " + this.recover;
/* 50 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\EquipStarBean$StarBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */