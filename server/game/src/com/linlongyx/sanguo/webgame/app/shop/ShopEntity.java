/*     */ package com.linlongyx.sanguo.webgame.app.shop;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.annotation.Table;
/*     */ import com.linlongyx.core.framework.dao.annotation.TableField;
/*     */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Table(tableName = "tb_shop", prefix = "shop_%serverId_%playerId", keyField = "goodsType", isPlayerIdKey = true)
/*     */ public class ShopEntity
/*     */   implements IMapEntity
/*     */ {
/*     */   @TableField(isKey = true)
/*     */   private final transient long playerId;
/*     */   @TableField(isKey = true)
/*     */   private final transient int goodsType;
/*     */   private int actId;
/*     */   private int refreshTime;
/*  27 */   private Map<Integer, Integer> sells = new HashMap<>();
/*  28 */   private List<Integer> ids = new ArrayList<>();
/*     */   private int refreshNum;
/*  30 */   private Map<Integer, Integer> totalSells = new HashMap<>();
/*  31 */   private Map<Integer, Integer> weekSells = new HashMap<>();
/*  32 */   private Map<Integer, Integer> activitySells = new HashMap<>();
/*  33 */   private Map<Integer, Integer> currStock = new HashMap<>();
/*     */   
/*     */   public ShopEntity(long playerId, int goodsType) {
/*  36 */     this.playerId = playerId;
/*  37 */     this.goodsType = goodsType;
/*     */   }
/*     */   
/*     */   public int getSellTimes(int goodsId) {
/*  41 */     if (this.sells.containsKey(Integer.valueOf(goodsId))) return ((Integer)this.sells.get(Integer.valueOf(goodsId))).intValue(); 
/*  42 */     return 0;
/*     */   }
/*     */   
/*     */   public int getTotalSellTimes(int goodsId) {
/*  46 */     if (this.totalSells.containsKey(Integer.valueOf(goodsId))) return ((Integer)this.totalSells.get(Integer.valueOf(goodsId))).intValue(); 
/*  47 */     return 0;
/*     */   }
/*     */   
/*     */   public int getWeekSellTimes(int goodsId) {
/*  51 */     if (this.weekSells.containsKey(Integer.valueOf(goodsId))) return ((Integer)this.weekSells.get(Integer.valueOf(goodsId))).intValue(); 
/*  52 */     return 0;
/*     */   }
/*     */   
/*     */   public int getActivitySellTimes(int goodsId) {
/*  56 */     return ((Integer)this.activitySells.getOrDefault(Integer.valueOf(goodsId), Integer.valueOf(0))).intValue();
/*     */   }
/*     */   
/*     */   public void addSellTimes(int goodsId, int num) {
/*  60 */     if (this.sells.containsKey(Integer.valueOf(goodsId))) { this.sells.put(Integer.valueOf(goodsId), Integer.valueOf(((Integer)this.sells.get(Integer.valueOf(goodsId))).intValue() + num)); }
/*  61 */     else { this.sells.put(Integer.valueOf(goodsId), Integer.valueOf(num)); }
/*     */   
/*     */   }
/*     */   public void addTotalSellTimes(int goodsId, int num) {
/*  65 */     if (this.totalSells.containsKey(Integer.valueOf(goodsId))) { this.totalSells.put(Integer.valueOf(goodsId), Integer.valueOf(((Integer)this.totalSells.get(Integer.valueOf(goodsId))).intValue() + num)); }
/*  66 */     else { this.totalSells.put(Integer.valueOf(goodsId), Integer.valueOf(num)); }
/*     */   
/*     */   }
/*     */   public void addWeekSellTimes(int goodsId, int num) {
/*  70 */     if (this.weekSells.containsKey(Integer.valueOf(goodsId))) { this.weekSells.put(Integer.valueOf(goodsId), Integer.valueOf(((Integer)this.weekSells.get(Integer.valueOf(goodsId))).intValue() + num)); }
/*  71 */     else { this.weekSells.put(Integer.valueOf(goodsId), Integer.valueOf(num)); }
/*     */   
/*     */   }
/*     */   public void addActSellTimes(int goodsId, int num) {
/*  75 */     num += ((Integer)this.activitySells.getOrDefault(Integer.valueOf(goodsId), Integer.valueOf(0))).intValue();
/*  76 */     this.activitySells.put(Integer.valueOf(goodsId), Integer.valueOf(num));
/*     */   }
/*     */   
/*     */   public void updateStock(int goodsId, int num, int stock) {
/*  80 */     this.currStock.put(Integer.valueOf(goodsId), Integer.valueOf(((Integer)this.currStock.getOrDefault(Integer.valueOf(goodsId), Integer.valueOf(stock))).intValue() - num));
/*     */   }
/*     */   
/*     */   public int getGoodsType() {
/*  84 */     return this.goodsType;
/*     */   }
/*     */   
/*     */   public int getRefreshTime() {
/*  88 */     return this.refreshTime;
/*     */   }
/*     */   
/*     */   public void setRefreshTime(int refreshTime) {
/*  92 */     this.refreshTime = refreshTime;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getSells() {
/*  96 */     return this.sells;
/*     */   }
/*     */   
/*     */   public void setSells(Map<Integer, Integer> sells) {
/* 100 */     this.sells = sells;
/*     */   }
/*     */   
/*     */   public long getPlayerId() {
/* 104 */     return this.playerId;
/*     */   }
/*     */   
/*     */   public List<Integer> getIds() {
/* 108 */     return this.ids;
/*     */   }
/*     */   
/*     */   public void setIds(List<Integer> ids) {
/* 112 */     this.ids = ids;
/*     */   }
/*     */   
/*     */   public int getRefreshNum() {
/* 116 */     return this.refreshNum;
/*     */   }
/*     */   
/*     */   public void setRefreshNum(int refreshNum) {
/* 120 */     this.refreshNum = refreshNum;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getTotalSells() {
/* 124 */     return this.totalSells;
/*     */   }
/*     */   
/*     */   public void setTotalSells(Map<Integer, Integer> totalSells) {
/* 128 */     this.totalSells = totalSells;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getWeekSells() {
/* 132 */     return this.weekSells;
/*     */   }
/*     */   
/*     */   public void setWeekSells(Map<Integer, Integer> weekSells) {
/* 136 */     this.weekSells = weekSells;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getActivitySells() {
/* 140 */     return this.activitySells;
/*     */   }
/*     */   
/*     */   public void setActivitySells(Map<Integer, Integer> activitySells) {
/* 144 */     this.activitySells = activitySells;
/*     */   }
/*     */   
/*     */   public int getActId() {
/* 148 */     return this.actId;
/*     */   }
/*     */   
/*     */   public void setActId(int actId) {
/* 152 */     this.actId = actId;
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<Integer, Integer> getCurrStock() {
/* 157 */     return this.currStock;
/*     */   }
/*     */   
/*     */   public void setCurrStock(Map<Integer, Integer> currStock) {
/* 161 */     this.currStock = currStock;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 166 */     return "ShopEntity{playerId=" + this.playerId + ", goodsType=" + this.goodsType + ", refreshTime=" + this.refreshTime + ", sells=" + this.sells + ", ids=" + this.ids + ", refreshNum=" + this.refreshNum + ", totalSells=" + this.totalSells + ", weekSells=" + this.weekSells + ", actId=" + this.actId + ", activitySells=" + this.activitySells + ", currStock=" + this.currStock + '}';
/*     */   }
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
/*     */   public Object mapKey() {
/* 183 */     return Integer.valueOf(this.goodsType);
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\shop\ShopEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */