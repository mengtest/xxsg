/*    */ package com.linlongyx.sanguo.webgame.app.artifact;
/*    */ 
/*    */ import com.linlongyx.core.framework.dao.annotation.Table;
/*    */ import com.linlongyx.core.framework.dao.annotation.TableField;
/*    */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.TrainData;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Table(tableName = "tb_artifact", prefix = "artifact_%serverId_%playerId", isPlayerIdKey = true, keyField = "id")
/*    */ public class ArtifactEntity
/*    */   implements IMapEntity
/*    */ {
/*    */   @TableField(isKey = true)
/*    */   private final transient long playerId;
/*    */   @TableField(isKey = true)
/*    */   private final transient int id;
/* 21 */   private Map<Integer, Integer> attrs = new HashMap<>();
/* 22 */   private Map<Integer, TrainData> trainDataMap = new HashMap<>();
/*    */   private int trianType;
/*    */   
/*    */   public ArtifactEntity(long playerId, int id) {
/* 26 */     this.playerId = playerId;
/* 27 */     this.id = id;
/*    */   }
/*    */   
/*    */   public long getPlayerId() {
/* 31 */     return this.playerId;
/*    */   }
/*    */   
/*    */   public int getId() {
/* 35 */     return this.id;
/*    */   }
/*    */   
/*    */   public Map<Integer, Integer> getAttrs() {
/* 39 */     return this.attrs;
/*    */   }
/*    */   
/*    */   public void setAttrs(Map<Integer, Integer> attrs) {
/* 43 */     this.attrs = attrs;
/*    */   }
/*    */   
/*    */   public Map<Integer, TrainData> getTrainDataMap() {
/* 47 */     return this.trainDataMap;
/*    */   }
/*    */   
/*    */   public void setTrainDataMap(Map<Integer, TrainData> trainDataMap) {
/* 51 */     this.trainDataMap = trainDataMap;
/*    */   }
/*    */   
/*    */   public int getTrianType() {
/* 55 */     return this.trianType;
/*    */   }
/*    */   
/*    */   public void setTrianType(int trianType) {
/* 59 */     this.trianType = trianType;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 64 */     return "ArtifactEntity{playerId=" + this.playerId + ", id=" + this.id + ", attrs=" + this.attrs + ", trainDataMap=" + this.trainDataMap + ", trianType=" + this.trianType + '}';
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object mapKey() {
/* 75 */     return Integer.valueOf(getId());
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\artifact\ArtifactEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */