/*    */ package com.linlongyx.sanguo.webgame.app.talisman;
/*    */ 
/*    */ import com.linlongyx.core.framework.dao.annotation.Table;
/*    */ import com.linlongyx.core.framework.dao.annotation.TableField;
/*    */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.IntIntValue;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Table(tableName = "tb_talisman", prefix = "talisman_%serverId_%playerId", isPlayerIdKey = true, keyField = "id")
/*    */ public class TalismanEntity
/*    */   implements IMapEntity
/*    */ {
/*    */   @TableField(isKey = true)
/*    */   private final transient long playerId;
/*    */   @TableField(isKey = true)
/*    */   private final transient int id;
/*    */   private int freeTimes;
/*    */   private int refreshTimes;
/*    */   private int freeRefresh;
/*    */   private int totalTimes;
/*    */   private int hitTimes;
/* 26 */   private ArrayList<IntIntValue> items = new ArrayList<>();
/*    */   
/*    */   public TalismanEntity(long playerId, int id) {
/* 29 */     this.playerId = playerId;
/* 30 */     this.id = id;
/*    */   }
/*    */   
/*    */   public int getId() {
/* 34 */     return this.id;
/*    */   }
/*    */   
/*    */   public long getPlayerId() {
/* 38 */     return this.playerId;
/*    */   }
/*    */   
/*    */   public int getFreeTimes() {
/* 42 */     return this.freeTimes;
/*    */   }
/*    */   
/*    */   public void setFreeTimes(int freeTimes) {
/* 46 */     this.freeTimes = freeTimes;
/*    */   }
/*    */   
/*    */   public int getRefreshTimes() {
/* 50 */     return this.refreshTimes;
/*    */   }
/*    */   
/*    */   public void setRefreshTimes(int refreshTimes) {
/* 54 */     this.refreshTimes = refreshTimes;
/*    */   }
/*    */   
/*    */   public int getFreeRefresh() {
/* 58 */     return this.freeRefresh;
/*    */   }
/*    */   
/*    */   public void setFreeRefresh(int freeRefresh) {
/* 62 */     this.freeRefresh = freeRefresh;
/*    */   }
/*    */   
/*    */   public int getTotalTimes() {
/* 66 */     return this.totalTimes;
/*    */   }
/*    */   
/*    */   public void setTotalTimes(int totalTimes) {
/* 70 */     this.totalTimes = totalTimes;
/*    */   }
/*    */   
/*    */   public int getHitTimes() {
/* 74 */     return this.hitTimes;
/*    */   }
/*    */   
/*    */   public void setHitTimes(int hitTimes) {
/* 78 */     this.hitTimes = hitTimes;
/*    */   }
/*    */   
/*    */   public ArrayList<IntIntValue> getItems() {
/* 82 */     return this.items;
/*    */   }
/*    */   
/*    */   public void setItems(ArrayList<IntIntValue> items) {
/* 86 */     this.items = items;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object mapKey() {
/* 91 */     return Integer.valueOf(getId());
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\talisman\TalismanEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */