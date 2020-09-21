/*     */ package com.linlongyx.sanguo.webgame.config.parameter;
/*     */ 
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ParameterBean;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FightParameter
/*     */   extends AbstractParameter
/*     */ {
/*     */   private static final int TYPE_HIT_BASE_VALUE = 1;
/*     */   private static final int TYPE_CRIT_BASE_VALUE = 2;
/*     */   private static final int TYPE_CRIT_HURT_BASE_VALUE = 5;
/*     */   private static final int TYPE_HIT_RANGE = 6;
/*     */   private static final int TYPE_CRITICAL_RANGE = 7;
/*     */   private int hitBaseValue;
/*     */   private int critBaseValue;
/*     */   private int critHurtBaseValue;
/*     */   private int hitRange;
/*     */   private int criticalRange;
/*     */   private int killMonsterCD;
/*     */   private int minHurtRate;
/*     */   private int minDogeRate;
/*     */   
/*     */   public FightParameter() {
/*  37 */     super(1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void init(ParameterBean bean) {
/*  46 */     String str = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(1))).getValue();
/*  47 */     this.hitBaseValue = Integer.parseInt(str);
/*     */     
/*  49 */     str = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(2))).getValue();
/*  50 */     this.critBaseValue = Integer.parseInt(str);
/*     */     
/*  52 */     str = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(5))).getValue();
/*  53 */     this.critHurtBaseValue = Integer.parseInt(str);
/*     */ 
/*     */     
/*  56 */     str = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(6))).getValue();
/*  57 */     this.hitRange = Integer.parseInt(str);
/*     */     
/*  59 */     str = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(7))).getValue();
/*  60 */     this.criticalRange = Integer.parseInt(str);
/*     */     
/*  62 */     str = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(9))).getValue();
/*  63 */     this.killMonsterCD = Integer.parseInt(str);
/*     */     
/*  65 */     str = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(11))).getValue();
/*  66 */     this.minDogeRate = Integer.parseInt(str);
/*     */     
/*  68 */     str = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(12))).getValue();
/*  69 */     this.minHurtRate = Integer.parseInt(str);
/*     */   }
/*     */   
/*     */   public int getHitBaseValue() {
/*  73 */     return this.hitBaseValue;
/*     */   }
/*     */   
/*     */   public int getCritBaseValue() {
/*  77 */     return this.critBaseValue;
/*     */   }
/*     */   
/*     */   public int getCritHurtBaseValue() {
/*  81 */     return this.critHurtBaseValue;
/*     */   }
/*     */   
/*     */   public int getHitRange() {
/*  85 */     return this.hitRange;
/*     */   }
/*     */   
/*     */   public int getCriticalRange() {
/*  89 */     return this.criticalRange;
/*     */   }
/*     */   
/*     */   public int getKillMonsterCD() {
/*  93 */     return this.killMonsterCD;
/*     */   }
/*     */   
/*     */   public int getMinHurtRate() {
/*  97 */     return this.minHurtRate;
/*     */   }
/*     */   
/*     */   public int getMinDogeRate() {
/* 101 */     return this.minDogeRate;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\parameter\FightParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */