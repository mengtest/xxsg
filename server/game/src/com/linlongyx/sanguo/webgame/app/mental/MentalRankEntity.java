/*    */ package com.linlongyx.sanguo.webgame.app.mental;
/*    */ 
/*    */ import com.linlongyx.core.framework.dao.annotation.Table;
/*    */ import com.linlongyx.core.framework.dao.annotation.TableField;
/*    */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.MentalRankStruct;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.MentalShowStruct;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Table(tableName = "tb_mental_rank", prefix = "mentalRank_%serverId_%playerId", keyField = "playerId", isPlayerIdKey = true)
/*    */ public class MentalRankEntity
/*    */   implements IEntity
/*    */ {
/*    */   @TableField(isKey = true)
/* 20 */   private final transient long playerId = 1L;
/*    */ 
/*    */   
/* 23 */   private List<MentalShowStruct> showList = new ArrayList<>();
/* 24 */   private List<MentalRankStruct> rankList = new ArrayList<>();
/* 25 */   private Map<Integer, Integer> levelMap = new HashMap<>();
/*    */ 
/*    */   
/*    */   public MentalRankEntity(long playerId) {}
/*    */ 
/*    */   
/*    */   public long getPlayerId() {
/* 32 */     return 1L;
/*    */   }
/*    */   
/*    */   public List<MentalShowStruct> getShowList() {
/* 36 */     return this.showList;
/*    */   }
/*    */   
/*    */   public void setShowList(List<MentalShowStruct> showList) {
/* 40 */     this.showList = showList;
/*    */   }
/*    */   
/*    */   public List<MentalRankStruct> getRankList() {
/* 44 */     return this.rankList;
/*    */   }
/*    */   
/*    */   public void setRankList(List<MentalRankStruct> rankList) {
/* 48 */     this.rankList = rankList;
/*    */   }
/*    */   
/*    */   public Map<Integer, Integer> getLevelMap() {
/* 52 */     return this.levelMap;
/*    */   }
/*    */   
/*    */   public void setLevelMap(Map<Integer, Integer> levelMap) {
/* 56 */     this.levelMap = levelMap;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 61 */     return "MentalRankEntity{playerId=1, showList=" + this.showList + ", rankList=" + this.rankList + ", levelMap=" + this.levelMap + '}';
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\mental\MentalRankEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */