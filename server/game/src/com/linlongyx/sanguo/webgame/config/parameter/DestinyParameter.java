/*    */ package com.linlongyx.sanguo.webgame.config.parameter;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.ParameterBean;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class DestinyParameter extends AbstractParameter {
/*    */   private int initRobTimes;
/*    */   private int recordSize;
/*    */   
/*    */   public DestinyParameter() {
/* 14 */     super(74);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 21 */     this.refreshPriceMap = new HashMap<>();
/*    */   }
/*    */   private int playersSize; private int rankSize; private Map<Integer, Integer> refreshPriceMap; private int buyTimePrice;
/*    */   private int levelLimit;
/*    */   private ArrayList<Reward> destinyPrepareRewards;
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
/*    */     
/* 40 */     this.destinyPrepareRewards = new ArrayList<>();
/* 41 */     String[] rewardStrs = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(9))).getValue().split(";");
/* 42 */     for (String str : rewardStrs) {
/* 43 */       String[] rewardArr = str.split(",");
/* 44 */       Reward reward = new Reward();
/* 45 */       reward.type = Byte.parseByte(rewardArr[0]);
/* 46 */       reward.id = Integer.parseInt(rewardArr[1]);
/* 47 */       reward.num = Long.parseLong(rewardArr[2]);
/* 48 */       this.destinyPrepareRewards.add(reward);
/*    */     } 
/*    */   }
/*    */   
/*    */   public int getInitRobTimes() {
/* 53 */     return this.initRobTimes;
/*    */   }
/*    */   
/*    */   public int getRecordSize() {
/* 57 */     return this.recordSize;
/*    */   }
/*    */   
/*    */   public int getPlayersSize() {
/* 61 */     return this.playersSize;
/*    */   }
/*    */   
/*    */   public int getRankSize() {
/* 65 */     return this.rankSize;
/*    */   }
/*    */   
/*    */   public Map<Integer, Integer> getRefreshPriceMap() {
/* 69 */     return Collections.unmodifiableMap(this.refreshPriceMap);
/*    */   }
/*    */   
/*    */   public int getBuyTimePrice() {
/* 73 */     return this.buyTimePrice;
/*    */   }
/*    */   
/*    */   public int getLevelLimit() {
/* 77 */     return this.levelLimit;
/*    */   }
/*    */   
/*    */   public ArrayList<Reward> getDestinyPrepareRewards() {
/* 81 */     return this.destinyPrepareRewards;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\parameter\DestinyParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */