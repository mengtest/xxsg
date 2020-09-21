/*    */ package com.linlongyx.sanguo.webgame.app.eightGraphic;
/*    */ 
/*    */ import com.linlongyx.core.framework.dao.annotation.Table;
/*    */ import com.linlongyx.core.framework.dao.annotation.TableField;
/*    */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Table(tableName = "tb_bagua", prefix = "bagua_%serverId_%playerId", keyField = "playerId", isPlayerIdKey = true)
/*    */ public class BaguaEntity
/*    */   implements IEntity
/*    */ {
/*    */   @TableField(isKey = true)
/*    */   private final transient long playerId;
/* 19 */   private Map<Integer, Set<Integer>> chapterMap = new HashMap<>();
/*    */   
/*    */   private int curInsId;
/* 22 */   private Map<Integer, Integer> insIdMap = new HashMap<>();
/*    */   
/*    */   private int asistTime;
/*    */   private byte sweep;
/*    */   
/*    */   public BaguaEntity(long playerId) {
/* 28 */     this.playerId = playerId;
/*    */   }
/*    */   
/*    */   public long getPlayerId() {
/* 32 */     return this.playerId;
/*    */   }
/*    */ 
/*    */   
/*    */   public Map<Integer, Set<Integer>> getChapterMap() {
/* 37 */     return this.chapterMap;
/*    */   }
/*    */   
/*    */   public void setChapterMap(Map<Integer, Set<Integer>> chapterMap) {
/* 41 */     this.chapterMap = chapterMap;
/*    */   }
/*    */   
/*    */   public int getCurInsId() {
/* 45 */     return this.curInsId;
/*    */   }
/*    */   
/*    */   public void setCurInsId(int curInsId) {
/* 49 */     this.curInsId = curInsId;
/*    */   }
/*    */   
/*    */   public Map<Integer, Integer> getInsIdMap() {
/* 53 */     return this.insIdMap;
/*    */   }
/*    */   
/*    */   public void setInsIdMap(Map<Integer, Integer> insIdMap) {
/* 57 */     this.insIdMap = insIdMap;
/*    */   }
/*    */   
/*    */   public int getAsistTime() {
/* 61 */     return this.asistTime;
/*    */   }
/*    */   
/*    */   public void setAsistTime(int asistTime) {
/* 65 */     this.asistTime = asistTime;
/*    */   }
/*    */   
/*    */   public byte getSweep() {
/* 69 */     return this.sweep;
/*    */   }
/*    */   
/*    */   public void setSweep(byte sweep) {
/* 73 */     this.sweep = sweep;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 78 */     return "BaguaEntity{playerId=" + this.playerId + ", chapterMap=" + this.chapterMap + ", curInsId=" + this.curInsId + ", insIdMap=" + this.insIdMap + ", asistTime=" + this.asistTime + ", sweep=" + this.sweep + '}';
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\eightGraphic\BaguaEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */