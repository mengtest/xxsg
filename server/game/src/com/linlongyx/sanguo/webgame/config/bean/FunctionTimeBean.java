/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class FunctionTimeBean {
/*    */   private int functionId;
/*    */   private int serverType;
/*    */   
/*    */   public int getFunctionId() {
/* 10 */     return this.functionId;
/*    */   }
/*    */   private String name; private int kaifu; private ArrayList<LastTimeBean> lastTime; private ArrayList<Integer> circle; private int anotherID;
/*    */   private int languageTime;
/*    */   
/*    */   public int getServerType() {
/* 16 */     return this.serverType;
/*    */   }
/*    */   private int languageID; private ArrayList<Integer> cmds;
/*    */   private int push;
/*    */   
/*    */   public String getName() {
/* 22 */     return this.name;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getKaifu() {
/* 28 */     return this.kaifu;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<LastTimeBean> getLastTime() {
/* 34 */     return this.lastTime;
/*    */   }
/*    */   
/*    */   public class LastTimeBean { private int start;
/*    */     private int end;
/*    */     
/*    */     public int getStart() {
/* 41 */       return this.start;
/*    */     }
/*    */ 
/*    */     
/*    */     public int getEnd() {
/* 46 */       return this.end;
/*    */     }
/*    */ 
/*    */     
/*    */     public String toString() {
/* 51 */       String retVal = "start= " + this.start + ", end= " + this.end;
/* 52 */       return retVal;
/*    */     } }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<Integer> getCircle() {
/* 59 */     return this.circle;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getAnotherID() {
/* 65 */     return this.anotherID;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getLanguageTime() {
/* 71 */     return this.languageTime;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getLanguageID() {
/* 77 */     return this.languageID;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<Integer> getCmds() {
/* 83 */     return this.cmds;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPush() {
/* 89 */     return this.push;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 94 */     String retVal = "functionId= " + this.functionId + ", serverType= " + this.serverType + ", name= " + this.name + ", kaifu= " + this.kaifu + ", lastTime= " + this.lastTime + ", circle= " + this.circle + ", anotherID= " + this.anotherID + ", languageTime= " + this.languageTime + ", languageID= " + this.languageID + ", cmds= " + this.cmds + ", push= " + this.push;
/* 95 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\FunctionTimeBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */