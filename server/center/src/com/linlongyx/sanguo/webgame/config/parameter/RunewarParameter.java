/*    */ package com.linlongyx.sanguo.webgame.config.parameter;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.config.bean.ParameterBean;
/*    */ 
/*    */ public class RunewarParameter
/*    */   extends AbstractParameter
/*    */ {
/*    */   private int rankLimit;
/*    */   private int enemySize;
/*    */   private int hp;
/*    */   
/*    */   public RunewarParameter() {
/* 13 */     super(45);
/*    */   }
/*    */ 
/*    */   
/*    */   private int cdTime;
/*    */   
/*    */   private int pointRange;
/*    */   
/*    */   private int levelLimit;
/*    */   
/*    */   private int recordLimit;
/*    */   private int rankSize;
/*    */   private int refreshCd;
/*    */   private int fightCd;
/*    */   private long fightValRangeLow;
/*    */   private long fightValRangeHigh;
/*    */   private int leapPoint;
/*    */   
/*    */   void init(ParameterBean bean) {
/* 32 */     this.rankLimit = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(1))).getValue()).intValue();
/* 33 */     this.enemySize = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(2))).getValue()).intValue();
/* 34 */     this.hp = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(3))).getValue()).intValue();
/* 35 */     this.cdTime = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(4))).getValue()).intValue();
/* 36 */     this.pointRange = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(5))).getValue()).intValue();
/* 37 */     this.levelLimit = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(6))).getValue()).intValue();
/* 38 */     this.recordLimit = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(7))).getValue()).intValue();
/* 39 */     this.rankSize = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(8))).getValue()).intValue();
/* 40 */     this.refreshCd = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(9))).getValue()).intValue();
/* 41 */     this.fightCd = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(10))).getValue()).intValue();
/* 42 */     String[] tmp = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(11))).getValue().split(",");
/* 43 */     this.fightValRangeLow = Long.parseLong(tmp[0]);
/* 44 */     this.fightValRangeHigh = Long.parseLong(tmp[1]);
/* 45 */     this.leapPoint = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(12))).getValue()).intValue();
/*    */   }
/*    */   
/*    */   public int getRankLimit() {
/* 49 */     return this.rankLimit;
/*    */   }
/*    */   
/*    */   public int getEnemySize() {
/* 53 */     return this.enemySize;
/*    */   }
/*    */   
/*    */   public int getHp() {
/* 57 */     return this.hp;
/*    */   }
/*    */   
/*    */   public int getCdTime() {
/* 61 */     return this.cdTime;
/*    */   }
/*    */   
/*    */   public int getPointRange() {
/* 65 */     return this.pointRange;
/*    */   }
/*    */   
/*    */   public int getLevelLimit() {
/* 69 */     return this.levelLimit;
/*    */   }
/*    */   
/*    */   public int getRecordLimit() {
/* 73 */     return this.recordLimit;
/*    */   }
/*    */   
/*    */   public int getRankSize() {
/* 77 */     return this.rankSize;
/*    */   }
/*    */   
/*    */   public int getRefreshCd() {
/* 81 */     return this.refreshCd;
/*    */   }
/*    */   
/*    */   public int getFightCd() {
/* 85 */     return this.fightCd;
/*    */   }
/*    */   
/*    */   public long getFightValRangeLow() {
/* 89 */     return this.fightValRangeLow;
/*    */   }
/*    */   
/*    */   public long getFightValRangeHigh() {
/* 93 */     return this.fightValRangeHigh;
/*    */   }
/*    */   
/*    */   public int getLeapPoint() {
/* 97 */     return this.leapPoint;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\config\parameter\RunewarParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */