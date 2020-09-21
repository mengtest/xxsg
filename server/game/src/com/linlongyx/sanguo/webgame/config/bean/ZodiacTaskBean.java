/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class ZodiacTaskBean {
/*    */   private int id;
/*    */   private int level;
/*    */   
/*    */   public int getId() {
/* 10 */     return this.id;
/*    */   }
/*    */   
/*    */   private ArrayList<TypeBean> type;
/*    */   
/*    */   public int getLevel() {
/* 16 */     return this.level;
/*    */   }
/*    */   
/*    */   private ArrayList<RewardBean> reward;
/*    */   
/*    */   public ArrayList<TypeBean> getType() {
/* 22 */     return this.type;
/*    */   }
/*    */   
/*    */   public class TypeBean
/*    */   {
/*    */     private int type;
/*    */     
/*    */     public int getType() {
/* 30 */       return this.type;
/*    */     }
/*    */     private int targetId; private int num;
/*    */     
/*    */     public int getTargetId() {
/* 35 */       return this.targetId;
/*    */     }
/*    */ 
/*    */     
/*    */     public int getNum() {
/* 40 */       return this.num;
/*    */     }
/*    */ 
/*    */     
/*    */     public String toString() {
/* 45 */       String retVal = "type= " + this.type + ", targetId= " + this.targetId + ", num= " + this.num;
/* 46 */       return retVal;
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getReward() {
/* 53 */     return this.reward;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 58 */     String retVal = "id= " + this.id + ", level= " + this.level + ", type= " + this.type + ", reward= " + this.reward;
/* 59 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\ZodiacTaskBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */