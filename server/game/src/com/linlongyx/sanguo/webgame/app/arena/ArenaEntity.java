/*     */ package com.linlongyx.sanguo.webgame.app.arena;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.annotation.Table;
/*     */ import com.linlongyx.core.framework.dao.annotation.TableField;
/*     */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.ArenaReportData;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ @Table(tableName = "tb_arena", prefix = "arena_%serverId_%playerId", keyField = "playerId", isPlayerIdKey = true)
/*     */ public class ArenaEntity
/*     */   implements IEntity
/*     */ {
/*     */   @TableField(isKey = true)
/*     */   private final transient long playerId;
/*     */   private int rank;
/*     */   private int maxRank;
/*  19 */   private ArrayList<ArenaReportData> reports = new ArrayList<>();
/*     */   private int fightTimes;
/*     */   private int lastTime;
/*     */   private int buyTimes;
/*     */   private int eliminateTimes;
/*     */   private int totalFightTimes;
/*     */   
/*     */   public ArenaEntity(long playerId) {
/*  27 */     this.playerId = playerId;
/*     */   }
/*     */   
/*     */   public long getPlayerId() {
/*  31 */     return this.playerId;
/*     */   }
/*     */   
/*     */   public int getRank() {
/*  35 */     return this.rank;
/*     */   }
/*     */   
/*     */   public void setRank(int rank) {
/*  39 */     this.rank = rank;
/*     */   }
/*     */   
/*     */   public int getMaxRank() {
/*  43 */     return this.maxRank;
/*     */   }
/*     */   
/*     */   public void setMaxRank(int maxRank) {
/*  47 */     this.maxRank = maxRank;
/*     */   }
/*     */   
/*     */   public ArrayList<ArenaReportData> getReports() {
/*  51 */     return this.reports;
/*     */   }
/*     */   
/*     */   public void setReports(ArrayList<ArenaReportData> reports) {
/*  55 */     this.reports = reports;
/*     */   }
/*     */   
/*     */   public int getFightTimes() {
/*  59 */     return this.fightTimes;
/*     */   }
/*     */   
/*     */   public void setFightTimes(int fightTimes) {
/*  63 */     this.fightTimes = fightTimes;
/*     */   }
/*     */   
/*     */   public int getLastTime() {
/*  67 */     return this.lastTime;
/*     */   }
/*     */   
/*     */   public void setLastTime(int lastTime) {
/*  71 */     this.lastTime = lastTime;
/*     */   }
/*     */   
/*     */   public int getBuyTimes() {
/*  75 */     return this.buyTimes;
/*     */   }
/*     */   
/*     */   public void setBuyTimes(int buyTimes) {
/*  79 */     this.buyTimes = buyTimes;
/*     */   }
/*     */   
/*     */   public int getEliminateTimes() {
/*  83 */     return this.eliminateTimes;
/*     */   }
/*     */   
/*     */   public void setEliminateTimes(int eliminateTimes) {
/*  87 */     this.eliminateTimes = eliminateTimes;
/*     */   }
/*     */   
/*     */   public int getTotalFightTimes() {
/*  91 */     return this.totalFightTimes;
/*     */   }
/*     */   
/*     */   public void setTotalFightTimes(int totalFightTimes) {
/*  95 */     this.totalFightTimes = totalFightTimes;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 100 */     return "ArenaEntity{playerId=" + this.playerId + ", rank=" + this.rank + ", maxRank=" + this.maxRank + ", reports=" + this.reports + ", fightTimes=" + this.fightTimes + ", lastTime=" + this.lastTime + ", buyTimes=" + this.buyTimes + ", eliminateTimes=" + this.eliminateTimes + ", totalFightTimes=" + this.totalFightTimes + '}';
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\arena\ArenaEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */