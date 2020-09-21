/*    */ package com.linlongyx.sanguo.webgame.config.parameter;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.config.bean.ParameterBean;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.IntIntValue;
/*    */ 
/*    */ public class TurnplateParameter
/*    */   extends AbstractParameter
/*    */ {
/*    */   private int tax;
/*    */   private IntIntValue gold2Point;
/*    */   private int discount;
/*    */   private int maxTenDraw;
/*    */   
/*    */   public TurnplateParameter() {
/* 15 */     super(24);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private int initGold;
/*    */   
/*    */   private int maxRecord;
/*    */   
/*    */   private int goldToPool;
/*    */   
/*    */   private int maxRareDraw;
/*    */ 
/*    */   
/*    */   void init(ParameterBean bean) {
/* 30 */     this.tax = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(1))).getValue());
/* 31 */     String[] tmp = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(2))).getValue().split(";");
/* 32 */     this.gold2Point = new IntIntValue();
/* 33 */     this.gold2Point.key = Integer.parseInt(tmp[0]);
/* 34 */     this.gold2Point.value = Integer.parseInt(tmp[1]);
/* 35 */     this.discount = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(3))).getValue());
/* 36 */     this.maxTenDraw = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(4))).getValue());
/* 37 */     this.initGold = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(5))).getValue());
/* 38 */     this.maxRecord = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(6))).getValue());
/* 39 */     this.goldToPool = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(7))).getValue());
/* 40 */     this.maxRareDraw = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(8))).getValue());
/*    */   }
/*    */ 
/*    */   
/*    */   public int getTax() {
/* 45 */     return this.tax;
/*    */   }
/*    */   
/*    */   public IntIntValue getGold2Point() {
/* 49 */     return this.gold2Point;
/*    */   }
/*    */   
/*    */   public int getDiscount() {
/* 53 */     return this.discount;
/*    */   }
/*    */   
/*    */   public int getMaxTenDraw() {
/* 57 */     return this.maxTenDraw;
/*    */   }
/*    */   
/*    */   public int getInitGold() {
/* 61 */     return this.initGold;
/*    */   }
/*    */   
/*    */   public int getMaxRecord() {
/* 65 */     return this.maxRecord;
/*    */   }
/*    */   
/*    */   public int getGoldToPool() {
/* 69 */     return this.goldToPool;
/*    */   }
/*    */   
/*    */   public int getMaxRareDraw() {
/* 73 */     return this.maxRareDraw;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\parameter\TurnplateParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */