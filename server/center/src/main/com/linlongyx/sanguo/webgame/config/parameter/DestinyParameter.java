/*    */ package com.linlongyx.sanguo.webgame.config.parameter;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.config.bean.ParameterBean;
/*    */ import java.util.Collections;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class DestinyParameter
/*    */   extends AbstractParameter {
/*    */   private int initRobTimes;
/*    */   private int recordSize;
/*    */   private int playersSize;
/*    */   
/*    */   public DestinyParameter() {
/* 15 */     super(74);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 22 */     this.refreshPriceMap = new HashMap<>();
/*    */   }
/*    */   private int rankSize; private Map<Integer, Integer> refreshPriceMap; private int buyTimePrice;
/*    */   private int levelLimit;
/*    */   
/*    */   void init(ParameterBean bean) {
/* 28 */     this.initRobTimes = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(1))).getValue());
/* 29 */     this.recordSize = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(2))).getValue());
/* 30 */     this.playersSize = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(3))).getValue());
/* 31 */     this.rankSize = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(4))).getValue());
/* 32 */     String[] strs = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(5))).getValue().split(";");
/* 33 */     for (String s : strs) {
/* 34 */       String[] tmp = s.split(":");
/* 35 */       this.refreshPriceMap.put(Integer.valueOf(Integer.parseInt(tmp[0])), Integer.valueOf(Integer.parseInt(tmp[1])));
/*    */     } 
/* 37 */     this.buyTimePrice = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(6))).getValue());
/* 38 */     this.levelLimit = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(7))).getValue());
/*    */   }
/*    */   
/*    */   public int getInitRobTimes() {
/* 42 */     return this.initRobTimes;
/*    */   }
/*    */   
/*    */   public int getRecordSize() {
/* 46 */     return this.recordSize;
/*    */   }
/*    */   
/*    */   public int getPlayersSize() {
/* 50 */     return this.playersSize;
/*    */   }
/*    */   
/*    */   public int getRankSize() {
/* 54 */     return this.rankSize;
/*    */   }
/*    */   
/*    */   public Map<Integer, Integer> getRefreshPriceMap() {
/* 58 */     return Collections.unmodifiableMap(this.refreshPriceMap);
/*    */   }
/*    */   
/*    */   public int getBuyTimePrice() {
/* 62 */     return this.buyTimePrice;
/*    */   }
/*    */   
/*    */   public int getLevelLimit() {
/* 66 */     return this.levelLimit;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\config\parameter\DestinyParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */