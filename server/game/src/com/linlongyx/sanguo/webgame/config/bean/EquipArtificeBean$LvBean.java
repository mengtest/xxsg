/*     */ package com.linlongyx.sanguo.webgame.config.bean;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LvBean
/*     */ {
/*     */   private int maxVal;
/*     */   private int valProb;
/*     */   private ArrayList<SucessProbBean> sucessProb;
/*     */   private ArrayList<RewardBean> cost;
/*     */   private ArrayList<CostLuckBean> costLuck;
/*     */   private ArrayList<CostValBean> costVal;
/*     */   private int costMoney;
/*     */   private ArrayList<CostMLuckBean> costMLuck;
/*     */   private ArrayList<CostMValBean> costMVal;
/*     */   private int add_att;
/*     */   private ArrayList<RewardBean> recover;
/*     */   private ArrayList<String> recover_cli;
/*     */   
/*     */   public int getMaxVal() {
/*  32 */     return this.maxVal;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getValProb() {
/*  38 */     return this.valProb;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<SucessProbBean> getSucessProb() {
/*  44 */     return this.sucessProb;
/*     */   }
/*     */   
/*     */   public class SucessProbBean {
/*     */     private int blessVal;
/*     */     
/*     */     public int getBlessVal() {
/*  51 */       return this.blessVal;
/*     */     }
/*     */     
/*     */     private int sucessProb;
/*     */     
/*     */     public int getSucessProb() {
/*  57 */       return this.sucessProb;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/*  62 */       String retVal = "blessVal= " + this.blessVal + ", sucessProb= " + this.sucessProb;
/*  63 */       return retVal;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<RewardBean> getCost() {
/*  70 */     return this.cost;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<CostLuckBean> getCostLuck() {
/*  76 */     return this.costLuck;
/*     */   }
/*     */   
/*     */   public class CostLuckBean {
/*     */     private int low;
/*     */     
/*     */     public int getLow() {
/*  83 */       return this.low;
/*     */     }
/*     */     private int high;
/*     */     
/*     */     public int getHigh() {
/*  88 */       return this.high;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/*  93 */       String retVal = "low= " + this.low + ", high= " + this.high;
/*  94 */       return retVal;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<CostValBean> getCostVal() {
/* 101 */     return this.costVal;
/*     */   }
/*     */   
/*     */   public class CostValBean { private int low;
/*     */     private int high;
/*     */     
/*     */     public int getLow() {
/* 108 */       return this.low;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getHigh() {
/* 113 */       return this.high;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 118 */       String retVal = "low= " + this.low + ", high= " + this.high;
/* 119 */       return retVal;
/*     */     } }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCostMoney() {
/* 126 */     return this.costMoney;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<CostMLuckBean> getCostMLuck() {
/* 132 */     return this.costMLuck;
/*     */   }
/*     */   
/*     */   public class CostMLuckBean { private int low;
/*     */     private int high;
/*     */     
/*     */     public int getLow() {
/* 139 */       return this.low;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getHigh() {
/* 144 */       return this.high;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 149 */       String retVal = "low= " + this.low + ", high= " + this.high;
/* 150 */       return retVal;
/*     */     } }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<CostMValBean> getCostMVal() {
/* 157 */     return this.costMVal;
/*     */   }
/*     */   
/*     */   public class CostMValBean { private int low;
/*     */     private int high;
/*     */     
/*     */     public int getLow() {
/* 164 */       return this.low;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getHigh() {
/* 169 */       return this.high;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 174 */       String retVal = "low= " + this.low + ", high= " + this.high;
/* 175 */       return retVal;
/*     */     } }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getAdd_att() {
/* 182 */     return this.add_att;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<RewardBean> getRecover() {
/* 188 */     return this.recover;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<String> getRecover_cli() {
/* 194 */     return this.recover_cli;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 199 */     String retVal = "maxVal= " + this.maxVal + ", valProb= " + this.valProb + ", sucessProb= " + this.sucessProb + ", cost= " + this.cost + ", costLuck= " + this.costLuck + ", costVal= " + this.costVal + ", costMoney= " + this.costMoney + ", costMLuck= " + this.costMLuck + ", costMVal= " + this.costMVal + ", add_att= " + this.add_att + ", recover= " + this.recover + ", recover_cli= " + this.recover_cli;
/* 200 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\EquipArtificeBean$LvBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */