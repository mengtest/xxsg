/*     */ package com.linlongyx.sanguo.webgame.app.yearbeast;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.annotation.Table;
/*     */ import com.linlongyx.core.framework.dao.annotation.TableField;
/*     */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Table(tableName = "tb_yearbeast", prefix = "yearbeast_%serverId_%playerId", isPlayerIdKey = true, keyField = "id")
/*     */ public class YearBeastEntity
/*     */   implements IMapEntity
/*     */ {
/*     */   @TableField(isKey = true)
/*     */   private final transient long playerId;
/*     */   @TableField(isKey = true)
/*     */   private final transient int id;
/*     */   private int currBossId;
/*     */   private int buyTimes;
/*     */   private int yearBeastTimes;
/*     */   private boolean open;
/*  27 */   private Set<Integer> deathList = new HashSet<>();
/*  28 */   private Map<Byte, Long> currHp = new HashMap<>();
/*     */   private long maxHp;
/*     */   
/*     */   public YearBeastEntity(long playerId, int id) {
/*  32 */     this.playerId = playerId;
/*  33 */     this.id = id;
/*     */   }
/*     */   
/*     */   public long getPlayerId() {
/*  37 */     return this.playerId;
/*     */   }
/*     */   
/*     */   public int getId() {
/*  41 */     return this.id;
/*     */   }
/*     */   
/*     */   public Map<Byte, Long> getCurrHp() {
/*  45 */     return this.currHp;
/*     */   }
/*     */   
/*     */   public void setCurrHp(Map<Byte, Long> currHp) {
/*  49 */     this.currHp = currHp;
/*     */   }
/*     */   
/*     */   public int getCurrBossId() {
/*  53 */     return this.currBossId;
/*     */   }
/*     */   
/*     */   public void setCurrBossId(int currBossId) {
/*  57 */     this.currBossId = currBossId;
/*     */   }
/*     */   
/*     */   public int getBuyTimes() {
/*  61 */     return this.buyTimes;
/*     */   }
/*     */   
/*     */   public void setBuyTimes(int buyTimes) {
/*  65 */     this.buyTimes = buyTimes;
/*     */   }
/*     */   
/*     */   public int getYearBeastTimes() {
/*  69 */     return this.yearBeastTimes;
/*     */   }
/*     */   
/*     */   public void setYearBeastTimes(int yearBeastTimes) {
/*  73 */     this.yearBeastTimes = yearBeastTimes;
/*     */   }
/*     */   
/*     */   public boolean isOpen() {
/*  77 */     return this.open;
/*     */   }
/*     */   
/*     */   public void setOpen(boolean open) {
/*  81 */     this.open = open;
/*     */   }
/*     */   
/*     */   public Set<Integer> getDeathList() {
/*  85 */     return this.deathList;
/*     */   }
/*     */   
/*     */   public void setDeathList(Set<Integer> deathList) {
/*  89 */     this.deathList = deathList;
/*     */   }
/*     */   
/*     */   public long getMaxHp() {
/*  93 */     return this.maxHp;
/*     */   }
/*     */   
/*     */   public void setMaxHp(long maxHp) {
/*  97 */     this.maxHp = maxHp;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 102 */     return "YearBeastEntity{playerId=" + this.playerId + ", id=" + this.id + ", currHp=" + this.currHp + ", currBossId=" + this.currBossId + ", buyTimes=" + this.buyTimes + ", yearBeastTimes=" + this.yearBeastTimes + ", open=" + this.open + ", deathList=" + this.deathList + ", maxHp=" + this.maxHp + '}';
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
/*     */   public Object mapKey() {
/* 117 */     return Integer.valueOf(getId());
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\yearbeast\YearBeastEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */