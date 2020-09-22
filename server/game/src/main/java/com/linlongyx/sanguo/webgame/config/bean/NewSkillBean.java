/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class NewSkillBean
/*    */ {
/*    */   private int id;
/*    */   
/*    */   public int getId() {
/* 10 */     return this.id;
/*    */   }
/*    */   private String name; private int skillLv; private int type;
/*    */   private int hurtTpye;
/*    */   
/*    */   public String getName() {
/* 16 */     return this.name;
/*    */   }
/*    */   private int furyCost;
/*    */   private ArrayList<Integer> effect;
/*    */   
/*    */   public int getSkillLv() {
/* 22 */     return this.skillLv;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getType() {
/* 28 */     return this.type;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getHurtTpye() {
/* 34 */     return this.hurtTpye;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getFuryCost() {
/* 40 */     return this.furyCost;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<Integer> getEffect() {
/* 46 */     return this.effect;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 51 */     String retVal = "id= " + this.id + ", name= " + this.name + ", skillLv= " + this.skillLv + ", type= " + this.type + ", hurtTpye= " + this.hurtTpye + ", furyCost= " + this.furyCost + ", effect= " + this.effect;
/* 52 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\NewSkillBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */