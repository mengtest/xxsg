/*     */ package com.linlongyx.sanguo.webgame.app.limit;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.annotation.Table;
/*     */ import com.linlongyx.core.framework.dao.annotation.TableField;
/*     */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Table(tableName = "tb_limit", prefix = "limit_%serverId_%playerId", isPlayerIdKey = true, keyField = "id")
/*     */ public class LimitEntity
/*     */   implements IMapEntity
/*     */ {
/*     */   @TableField(isKey = true)
/*     */   private final transient long playerId;
/*     */   @TableField(isKey = true)
/*     */   private final transient int id;
/*  20 */   private Map<Integer, Integer> rewardCounts = new HashMap<>();
/*  21 */   private Map<Integer, Long> values = new HashMap<>();
/*  22 */   private Map<Integer, Integer> states = new HashMap<>();
/*  23 */   private Map<Integer, Long> dateCharges = new HashMap<>();
/*     */   private long consumeValue;
/*     */   private boolean open;
/*     */   private long chargeValue;
/*     */   private int loginDay;
/*     */   private int loginTime;
/*     */   
/*     */   public LimitEntity(long playerId, int id) {
/*  31 */     this.playerId = playerId;
/*  32 */     this.id = id;
/*     */   }
/*     */   
/*     */   public long getPlayerId() {
/*  36 */     return this.playerId;
/*     */   }
/*     */   
/*     */   public int getId() {
/*  40 */     return this.id;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getRewardCounts() {
/*  44 */     return this.rewardCounts;
/*     */   }
/*     */   
/*     */   public void setRewardCounts(Map<Integer, Integer> rewardCounts) {
/*  48 */     this.rewardCounts = rewardCounts;
/*     */   }
/*     */   
/*     */   public Map<Integer, Long> getValues() {
/*  52 */     return this.values;
/*     */   }
/*     */   
/*     */   public void setValues(Map<Integer, Long> values) {
/*  56 */     this.values = values;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getStates() {
/*  60 */     return this.states;
/*     */   }
/*     */   
/*     */   public void setStates(Map<Integer, Integer> states) {
/*  64 */     this.states = states;
/*     */   }
/*     */   
/*     */   public boolean isOpen() {
/*  68 */     return this.open;
/*     */   }
/*     */   
/*     */   public void setOpen(boolean open) {
/*  72 */     this.open = open;
/*     */   }
/*     */   
/*     */   public Map<Integer, Long> getDateCharges() {
/*  76 */     return this.dateCharges;
/*     */   }
/*     */   
/*     */   public void setDateCharges(Map<Integer, Long> dateCharges) {
/*  80 */     this.dateCharges = dateCharges;
/*     */   }
/*     */   
/*     */   public long getConsumeValue() {
/*  84 */     return this.consumeValue;
/*     */   }
/*     */   
/*     */   public void setConsumeValue(long consumeValue) {
/*  88 */     this.consumeValue = consumeValue;
/*     */   }
/*     */   
/*     */   public long getChargeValue() {
/*  92 */     return this.chargeValue;
/*     */   }
/*     */   
/*     */   public void setChargeValue(long chargeValue) {
/*  96 */     this.chargeValue = chargeValue;
/*     */   }
/*     */   
/*     */   public int getLoginDay() {
/* 100 */     return this.loginDay;
/*     */   }
/*     */   
/*     */   public void setLoginDay(int loginDay) {
/* 104 */     this.loginDay = loginDay;
/*     */   }
/*     */   
/*     */   public int getLoginTime() {
/* 108 */     return this.loginTime;
/*     */   }
/*     */   
/*     */   public void setLoginTime(int loginTime) {
/* 112 */     this.loginTime = loginTime;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object mapKey() {
/* 117 */     return Integer.valueOf(getId());
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 122 */     return "LimitEntity{playerId=" + this.playerId + ", id=" + this.id + ", rewardCounts=" + this.rewardCounts + ", values=" + this.values + ", states=" + this.states + ", dateCharges=" + this.dateCharges + ", consumeValue=" + this.consumeValue + ", open=" + this.open + ", chargeValue=" + this.chargeValue + ", loginDay=" + this.loginDay + ", loginTime=" + this.loginTime + '}';
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\limit\LimitEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */