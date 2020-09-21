/*    */ package com.linlongyx.sanguo.webgame.app.turnplate;
/*    */ 
/*    */ import com.linlongyx.core.framework.dao.annotation.Table;
/*    */ import com.linlongyx.core.framework.dao.annotation.TableField;
/*    */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Table(tableName = "tb_turnplate", prefix = "turnplate%serverId_%playerId", isPlayerIdKey = true, keyField = "actId")
/*    */ public class TurnplateEntity
/*    */   implements IMapEntity
/*    */ {
/*    */   @TableField(isKey = true)
/*    */   private final transient long playerId;
/*    */   @TableField(isKey = true)
/*    */   private final transient int actId;
/* 24 */   private Map<Integer, Integer> itemDrawCount = new HashMap<>();
/* 25 */   private Map<Integer, Integer> rareRecord = new HashMap<>();
/*    */   private int drawCount;
/*    */   private int point;
/*    */   private int tenDrawCount;
/*    */   private int rareCount;
/*    */   
/*    */   public TurnplateEntity(long playerId, int actId) {
/* 32 */     this.playerId = playerId;
/* 33 */     this.actId = actId;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object mapKey() {
/* 38 */     return Integer.valueOf(this.actId);
/*    */   }
/*    */   
/*    */   public long getPlayerId() {
/* 42 */     return this.playerId;
/*    */   }
/*    */   
/*    */   public int getActId() {
/* 46 */     return this.actId;
/*    */   }
/*    */   
/*    */   public Map<Integer, Integer> getItemDrawCount() {
/* 50 */     return this.itemDrawCount;
/*    */   }
/*    */   
/*    */   public void setItemDrawCount(Map<Integer, Integer> itemDrawCount) {
/* 54 */     this.itemDrawCount = itemDrawCount;
/*    */   }
/*    */   
/*    */   public int getDrawCount() {
/* 58 */     return this.drawCount;
/*    */   }
/*    */   
/*    */   public void setDrawCount(int drawCount) {
/* 62 */     this.drawCount = drawCount;
/*    */   }
/*    */   
/*    */   public int getPoint() {
/* 66 */     return this.point;
/*    */   }
/*    */   
/*    */   public void setPoint(int point) {
/* 70 */     this.point = point;
/*    */   }
/*    */   
/*    */   public int getTenDrawCount() {
/* 74 */     return this.tenDrawCount;
/*    */   }
/*    */   
/*    */   public void setTenDrawCount(int tenDrawCount) {
/* 78 */     this.tenDrawCount = tenDrawCount;
/*    */   }
/*    */   
/*    */   public int getRareCount() {
/* 82 */     return this.rareCount;
/*    */   }
/*    */   
/*    */   public void setRareCount(int rareCount) {
/* 86 */     this.rareCount = rareCount;
/*    */   }
/*    */   
/*    */   public Map<Integer, Integer> getRareRecord() {
/* 90 */     return this.rareRecord;
/*    */   }
/*    */   
/*    */   public void setRareRecord(Map<Integer, Integer> rareRecord) {
/* 94 */     this.rareRecord = rareRecord;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\turnplate\TurnplateEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */