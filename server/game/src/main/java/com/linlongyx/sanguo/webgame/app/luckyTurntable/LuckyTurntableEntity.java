/*     */ package com.linlongyx.sanguo.webgame.app.luckyTurntable;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.annotation.Table;
/*     */ import com.linlongyx.core.framework.dao.annotation.TableField;
/*     */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Table(tableName = "tb_luckyTurntable", prefix = "luckyTurntable_%serverId_%playerId", isPlayerIdKey = true, keyField = "actId")
/*     */ public class LuckyTurntableEntity
/*     */   implements IMapEntity
/*     */ {
/*     */   @TableField(isKey = true)
/*     */   private final transient long playerId;
/*     */   @TableField(isKey = true)
/*     */   private final transient int actId;
/*     */   private int luckyPoint;
/*     */   private byte isFirstDraw;
/*     */   private int drawTimes;
/*     */   private int freeTimes;
/*     */   private int tenDrawCount;
/*     */   private int totalPoint;
/*     */   private long lasttime;
/*     */   
/*     */   public LuckyTurntableEntity(long playerId, int actId) {
/*  30 */     this.playerId = playerId;
/*  31 */     this.actId = actId;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object mapKey() {
/*  36 */     return Integer.valueOf(this.actId);
/*     */   }
/*     */   
/*     */   public long getPlayerId() {
/*  40 */     return this.playerId;
/*     */   }
/*     */   
/*     */   public int getActId() {
/*  44 */     return this.actId;
/*     */   }
/*     */   
/*     */   public int getLuckyPoint() {
/*  48 */     return this.luckyPoint;
/*     */   }
/*     */   
/*     */   public void setLuckyPoint(int luckyPoint) {
/*  52 */     this.luckyPoint = luckyPoint;
/*     */   }
/*     */   
/*     */   public byte getIsFirstDraw() {
/*  56 */     return this.isFirstDraw;
/*     */   }
/*     */   
/*     */   public void setIsFirstDraw(byte isFirstDraw) {
/*  60 */     this.isFirstDraw = isFirstDraw;
/*     */   }
/*     */   
/*     */   public int getDrawTimes() {
/*  64 */     return this.drawTimes;
/*     */   }
/*     */   
/*     */   public void setDrawTimes(int drawTimes) {
/*  68 */     this.drawTimes = drawTimes;
/*     */   }
/*     */   
/*     */   public int getFreeTimes() {
/*  72 */     return this.freeTimes;
/*     */   }
/*     */   
/*     */   public void setFreeTimes(int freeTimes) {
/*  76 */     this.freeTimes = freeTimes;
/*     */   }
/*     */   
/*     */   public int getTenDrawCount() {
/*  80 */     return this.tenDrawCount;
/*     */   }
/*     */   
/*     */   public void setTenDrawCount(int tenDrawCount) {
/*  84 */     this.tenDrawCount = tenDrawCount;
/*     */   }
/*     */   
/*     */   public int getTotalPoint() {
/*  88 */     return this.totalPoint;
/*     */   }
/*     */   
/*     */   public void setTotalPoint(int totalPoint) {
/*  92 */     this.totalPoint = totalPoint;
/*     */   }
/*     */   
/*     */   public long getLasttime() {
/*  96 */     return this.lasttime;
/*     */   }
/*     */   
/*     */   public void setLasttime(long lasttime) {
/* 100 */     this.lasttime = lasttime;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\luckyTurntable\LuckyTurntableEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */