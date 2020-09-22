/*     */ package com.linlongyx.sanguo.webgame.app.friend;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.annotation.Table;
/*     */ import com.linlongyx.core.framework.dao.annotation.TableField;
/*     */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.KeyValue;
/*     */ import java.util.HashSet;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.LinkedBlockingQueue;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Table(tableName = "tb_friend", prefix = "friend_%serverId_%playerId", isPlayerIdKey = true)
/*     */ public class FriendEntity
/*     */   implements IEntity
/*     */ {
/*     */   @TableField(isKey = true)
/*     */   private final transient long playerId;
/*  23 */   private HashSet<Long> ids = new HashSet<>();
/*     */   
/*  25 */   private ConcurrentHashMap<Long, Integer> applyMap = new ConcurrentHashMap<>();
/*     */   
/*  27 */   private ConcurrentHashMap<Long, LinkedBlockingQueue<KeyValue>> chatMap = new ConcurrentHashMap<>();
/*     */   
/*  29 */   private HashSet<Long> sendIds = new HashSet<>();
/*     */   
/*  31 */   private HashSet<Long> needReceiveIds = new HashSet<>();
/*     */   
/*  33 */   private HashSet<Long> receivedIds = new HashSet<>();
/*     */   
/*     */   private int totalSendCount;
/*     */   
/*  37 */   private ConcurrentHashMap<Long, Long> favors = new ConcurrentHashMap<>();
/*     */   
/*  39 */   private HashSet<Long> offlineReceiveIds = new HashSet<>();
/*     */   
/*     */   public FriendEntity(long playerId) {
/*  42 */     this.playerId = playerId;
/*     */   }
/*     */   
/*     */   public long getPlayerId() {
/*  46 */     return this.playerId;
/*     */   }
/*     */ 
/*     */   
/*     */   public HashSet<Long> getIds() {
/*  51 */     return this.ids;
/*     */   }
/*     */   
/*     */   public void setIds(HashSet<Long> ids) {
/*  55 */     this.ids = ids;
/*     */   }
/*     */ 
/*     */   
/*     */   public ConcurrentHashMap<Long, Integer> getApplyMap() {
/*  60 */     return this.applyMap;
/*     */   }
/*     */   
/*     */   public void setApplyMap(ConcurrentHashMap<Long, Integer> applyMap) {
/*  64 */     this.applyMap = applyMap;
/*     */   }
/*     */   
/*     */   public ConcurrentHashMap<Long, LinkedBlockingQueue<KeyValue>> getChatMap() {
/*  68 */     return this.chatMap;
/*     */   }
/*     */   
/*     */   public void setChatMap(ConcurrentHashMap<Long, LinkedBlockingQueue<KeyValue>> chatMap) {
/*  72 */     this.chatMap = chatMap;
/*     */   }
/*     */   
/*     */   public HashSet<Long> getSendIds() {
/*  76 */     return this.sendIds;
/*     */   }
/*     */   
/*     */   public void setSendIds(HashSet<Long> sendIds) {
/*  80 */     this.sendIds = sendIds;
/*     */   }
/*     */   
/*     */   public HashSet<Long> getNeedReceiveIds() {
/*  84 */     return this.needReceiveIds;
/*     */   }
/*     */   
/*     */   public void setNeedReceiveIds(HashSet<Long> needReceiveIds) {
/*  88 */     this.needReceiveIds = needReceiveIds;
/*     */   }
/*     */   
/*     */   public HashSet<Long> getReceivedIds() {
/*  92 */     return this.receivedIds;
/*     */   }
/*     */   
/*     */   public void setReceivedIds(HashSet<Long> receivedIds) {
/*  96 */     this.receivedIds = receivedIds;
/*     */   }
/*     */   
/*     */   public int getTotalSendCount() {
/* 100 */     return this.totalSendCount;
/*     */   }
/*     */   
/*     */   public void setTotalSendCount(int totalSendCount) {
/* 104 */     this.totalSendCount = totalSendCount;
/*     */   }
/*     */   
/*     */   public ConcurrentHashMap<Long, Long> getFavors() {
/* 108 */     return this.favors;
/*     */   }
/*     */   
/*     */   public void setFavors(ConcurrentHashMap<Long, Long> favors) {
/* 112 */     this.favors = favors;
/*     */   }
/*     */   
/*     */   public HashSet<Long> getOfflineReceiveIds() {
/* 116 */     return this.offlineReceiveIds;
/*     */   }
/*     */   
/*     */   public void setOfflineReceiveIds(HashSet<Long> offlineReceiveIds) {
/* 120 */     this.offlineReceiveIds = offlineReceiveIds;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 125 */     return "FriendEntity{playerId=" + this.playerId + ", ids=" + this.ids + ", applyMap=" + this.applyMap + ", chatMap=" + this.chatMap + ", sendIds=" + this.sendIds + ", needReceiveIds=" + this.needReceiveIds + ", receivedIds=" + this.receivedIds + ", totalSendCount=" + this.totalSendCount + ", favors=" + this.favors + ", offlineReceiveIds=" + this.offlineReceiveIds + '}';
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\friend\FriendEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */