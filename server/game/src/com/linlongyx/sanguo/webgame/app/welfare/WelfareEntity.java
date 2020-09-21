/*     */ package com.linlongyx.sanguo.webgame.app.welfare;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.annotation.Table;
/*     */ import com.linlongyx.core.framework.dao.annotation.TableField;
/*     */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Table(tableName = "tb_welfare", prefix = "welfare_%serverId_%playerId", keyField = "playerId", isPlayerIdKey = true)
/*     */ public class WelfareEntity
/*     */   implements IEntity
/*     */ {
/*     */   @TableField(isKey = true)
/*     */   private final transient long playerId;
/*  20 */   private Set<Integer> vipDailySet = new HashSet<>();
/*     */   private int vipWeekTime;
/*     */   private int vipWeekLevel;
/*  23 */   private Map<Integer, Integer> vipWeekGoods = new HashMap<>();
/*  24 */   private Map<Integer, Integer> vipGiftGoods = new HashMap<>();
/*     */ 
/*     */   
/*  27 */   private Map<Integer, Integer> monthEndTime = new HashMap<>();
/*  28 */   private Map<Integer, Integer> monthGetTime = new HashMap<>();
/*     */   
/*     */   private boolean buyFund;
/*  31 */   private Map<Integer, Integer> fundReward = new HashMap<>();
/*  32 */   private Map<Integer, Integer> dailyBuyReward = new HashMap<>();
/*     */   private long oneBuyCharge;
/*  34 */   private Map<Integer, Integer> oneBuyReward = new HashMap<>();
/*  35 */   private Set<Integer> gerenalGift = new HashSet<>();
/*     */   
/*     */   private int oneState;
/*     */   private int times;
/*     */   private int id;
/*  40 */   private Map<Integer, Integer> dailyHaoLi = new HashMap<>();
/*     */   
/*     */   private int backGiftId;
/*     */   
/*     */   public WelfareEntity(long playerId) {
/*  45 */     this.playerId = playerId;
/*     */   }
/*     */   
/*     */   public long getPlayerId() {
/*  49 */     return this.playerId;
/*     */   }
/*     */   
/*     */   public Set<Integer> getVipDailySet() {
/*  53 */     return this.vipDailySet;
/*     */   }
/*     */   
/*     */   public void setVipDailySet(Set<Integer> vipDailySet) {
/*  57 */     this.vipDailySet = vipDailySet;
/*     */   }
/*     */   
/*     */   public int getVipWeekTime() {
/*  61 */     return this.vipWeekTime;
/*     */   }
/*     */   
/*     */   public void setVipWeekTime(int vipWeekTime) {
/*  65 */     this.vipWeekTime = vipWeekTime;
/*     */   }
/*     */   
/*     */   public int getVipWeekLevel() {
/*  69 */     return this.vipWeekLevel;
/*     */   }
/*     */   
/*     */   public void setVipWeekLevel(int vipWeekLevel) {
/*  73 */     this.vipWeekLevel = vipWeekLevel;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getVipWeekGoods() {
/*  77 */     return this.vipWeekGoods;
/*     */   }
/*     */   
/*     */   public void setVipWeekGoods(Map<Integer, Integer> vipWeekGoods) {
/*  81 */     this.vipWeekGoods = vipWeekGoods;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getVipGiftGoods() {
/*  85 */     return this.vipGiftGoods;
/*     */   }
/*     */   
/*     */   public void setVipGiftGoods(Map<Integer, Integer> vipGiftGoods) {
/*  89 */     this.vipGiftGoods = vipGiftGoods;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getMonthEndTime() {
/*  93 */     return this.monthEndTime;
/*     */   }
/*     */   
/*     */   public void setMonthEndTime(Map<Integer, Integer> monthEndTime) {
/*  97 */     this.monthEndTime = monthEndTime;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getMonthGetTime() {
/* 101 */     return this.monthGetTime;
/*     */   }
/*     */   
/*     */   public void setMonthGetTime(Map<Integer, Integer> monthGetTime) {
/* 105 */     this.monthGetTime = monthGetTime;
/*     */   }
/*     */   
/*     */   public boolean isBuyFund() {
/* 109 */     return this.buyFund;
/*     */   }
/*     */   
/*     */   public void setBuyFund(boolean buyFund) {
/* 113 */     this.buyFund = buyFund;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getFundReward() {
/* 117 */     return this.fundReward;
/*     */   }
/*     */   
/*     */   public void setFundReward(Map<Integer, Integer> fundReward) {
/* 121 */     this.fundReward = fundReward;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getDailyBuyReward() {
/* 125 */     return this.dailyBuyReward;
/*     */   }
/*     */   
/*     */   public void setDailyBuyReward(Map<Integer, Integer> dailyBuyReward) {
/* 129 */     this.dailyBuyReward = dailyBuyReward;
/*     */   }
/*     */   
/*     */   public long getOneBuyCharge() {
/* 133 */     return this.oneBuyCharge;
/*     */   }
/*     */   
/*     */   public void setOneBuyCharge(long oneBuyCharge) {
/* 137 */     this.oneBuyCharge = oneBuyCharge;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getOneBuyReward() {
/* 141 */     return this.oneBuyReward;
/*     */   }
/*     */   
/*     */   public void setOneBuyReward(Map<Integer, Integer> oneBuyReward) {
/* 145 */     this.oneBuyReward = oneBuyReward;
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<Integer> getGerenalGift() {
/* 150 */     return this.gerenalGift;
/*     */   }
/*     */   
/*     */   public void setGerenalGift(Set<Integer> gerenalGift) {
/* 154 */     this.gerenalGift = gerenalGift;
/*     */   }
/*     */   
/*     */   public int getOneState() {
/* 158 */     return this.oneState;
/*     */   }
/*     */   
/*     */   public void setOneState(int oneState) {
/* 162 */     this.oneState = oneState;
/*     */   }
/*     */   
/*     */   public int getTimes() {
/* 166 */     return this.times;
/*     */   }
/*     */   
/*     */   public void setTimes(int times) {
/* 170 */     this.times = times;
/*     */   }
/*     */   
/*     */   public int getId() {
/* 174 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(int id) {
/* 178 */     this.id = id;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getDailyHaoLi() {
/* 182 */     return this.dailyHaoLi;
/*     */   }
/*     */   
/*     */   public void setDailyHaoLi(Map<Integer, Integer> dailyHaoLi) {
/* 186 */     this.dailyHaoLi = dailyHaoLi;
/*     */   }
/*     */   
/*     */   public int getBackGiftId() {
/* 190 */     return this.backGiftId;
/*     */   }
/*     */   
/*     */   public void setBackGiftId(int backGiftId) {
/* 194 */     this.backGiftId = backGiftId;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 199 */     return "WelfareEntity{playerId=" + this.playerId + ", vipDailySet=" + this.vipDailySet + ", vipWeekTime=" + this.vipWeekTime + ", vipWeekLevel=" + this.vipWeekLevel + ", vipWeekGoods=" + this.vipWeekGoods + ", vipGiftGoods=" + this.vipGiftGoods + ", monthEndTime=" + this.monthEndTime + ", monthGetTime=" + this.monthGetTime + ", buyFund=" + this.buyFund + ", fundReward=" + this.fundReward + ", dailyBuyReward=" + this.dailyBuyReward + ", oneBuyCharge=" + this.oneBuyCharge + ", oneBuyReward=" + this.oneBuyReward + ", gerenalGift=" + this.gerenalGift + ", oneState=" + this.oneState + ", times=" + this.times + ", id=" + this.id + ", dailyHaoLi=" + this.dailyHaoLi + ", backGiftId=" + this.backGiftId + '}';
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\welfare\WelfareEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */