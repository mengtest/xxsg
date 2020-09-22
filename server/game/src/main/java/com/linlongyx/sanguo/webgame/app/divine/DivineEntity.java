/*    */ package com.linlongyx.sanguo.webgame.app.divine;
/*    */ 
/*    */ import com.linlongyx.core.framework.dao.annotation.Table;
/*    */ import com.linlongyx.core.framework.dao.annotation.TableField;
/*    */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.IntIntValue;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Table(tableName = "tb_divine", prefix = "divine_%serverId_%playerId", isPlayerIdKey = true, keyField = "actId")
/*    */ public class DivineEntity
/*    */   implements IMapEntity
/*    */ {
/*    */   @TableField(isKey = true)
/*    */   private final transient long playerId;
/*    */   @TableField(isKey = true)
/*    */   private final transient int actId;
/*    */   private int times;
/*    */   private int yuanbao;
/*    */   private int maxDivineNum;
/* 28 */   private List<IntIntValue> recordList = new ArrayList<>();
/*    */   
/*    */   public DivineEntity(long playerId, int actId) {
/* 31 */     this.playerId = playerId;
/* 32 */     this.actId = actId;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object mapKey() {
/* 37 */     return Integer.valueOf(this.actId);
/*    */   }
/*    */   
/*    */   public long getPlayerId() {
/* 41 */     return this.playerId;
/*    */   }
/*    */   
/*    */   public int getActId() {
/* 45 */     return this.actId;
/*    */   }
/*    */   
/*    */   public int getTimes() {
/* 49 */     return this.times;
/*    */   }
/*    */   
/*    */   public void setTimes(int times) {
/* 53 */     this.times = times;
/*    */   }
/*    */   
/*    */   public int getYuanbao() {
/* 57 */     return this.yuanbao;
/*    */   }
/*    */   
/*    */   public void setYuanbao(int yuanbao) {
/* 61 */     this.yuanbao = yuanbao;
/*    */   }
/*    */   
/*    */   public int getMaxDivineNum() {
/* 65 */     return this.maxDivineNum;
/*    */   }
/*    */   
/*    */   public void setMaxDivineNum(int maxDivineNum) {
/* 69 */     this.maxDivineNum = maxDivineNum;
/*    */   }
/*    */   
/*    */   public List<IntIntValue> getRecordList() {
/* 73 */     return this.recordList;
/*    */   }
/*    */   
/*    */   public void setRecordList(List<IntIntValue> recordList) {
/* 77 */     this.recordList = recordList;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\divine\DivineEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */