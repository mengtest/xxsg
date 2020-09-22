/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class SoulsTalentBean {
/*    */   private int id;
/*    */   private Map<Integer, LevelClassBean> levelClass;
/*    */   
/*    */   public int getId() {
/* 11 */     return this.id;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Map<Integer, LevelClassBean> getLevelClass() {
/* 17 */     return this.levelClass;
/*    */   }
/*    */   
/*    */   public class LevelClassBean
/*    */   {
/*    */     private String name;
/*    */     private ArrayList<AttrBean> beAttr;
/*    */     
/*    */     public String getName() {
/* 26 */       return this.name;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public ArrayList<AttrBean> getBeAttr() {
/* 32 */       return this.beAttr;
/*    */     }
/*    */ 
/*    */     
/*    */     public String toString() {
/* 37 */       String retVal = "name= " + this.name + ", beAttr= " + this.beAttr;
/* 38 */       return retVal;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 44 */     String retVal = "id= " + this.id + ", levelClass= " + this.levelClass;
/* 45 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\SoulsTalentBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */