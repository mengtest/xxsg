/*     */ package com.linlongyx.sanguo.webgame.app.friend;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.proxy.IEntityProxy;
/*     */ import com.linlongyx.core.framework.logic.AbstractComponent;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.friend.FriendFavorValueNoticeResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.KeyValue;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.LinkedBlockingQueue;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FriendComponent
/*     */   extends AbstractComponent<FriendEntity>
/*     */ {
/*     */   public FriendComponent(long playerId) {
/*  23 */     super(FriendEntity.class, playerId);
/*  24 */     getProxy().createProxy(new Object[] { Long.valueOf(playerId) });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasFriend(long friendId) {
/*  33 */     return getIds().contains(Long.valueOf(friendId));
/*     */   }
/*     */   
/*     */   public HashSet<Long> getIds() {
/*  37 */     return ((FriendEntity)getEntity()).getIds();
/*     */   }
/*     */   
/*     */   public void setIds(HashSet<Long> ids) {
/*  41 */     ((FriendEntity)getEntity()).setIds(ids);
/*     */   }
/*     */ 
/*     */   
/*     */   public ConcurrentHashMap<Long, Integer> getApplyMap() {
/*  46 */     return ((FriendEntity)getEntity()).getApplyMap();
/*     */   }
/*     */   
/*     */   public void setApplyMap(ConcurrentHashMap<Long, Integer> applyMap) {
/*  50 */     ((FriendEntity)getEntity()).setApplyMap(applyMap);
/*     */   }
/*     */   
/*     */   public ConcurrentHashMap<Long, LinkedBlockingQueue<KeyValue>> getChatMap() {
/*  54 */     return ((FriendEntity)getEntity()).getChatMap();
/*     */   }
/*     */   
/*     */   public void setChatMap(ConcurrentHashMap<Long, LinkedBlockingQueue<KeyValue>> chatMap) {
/*  58 */     ((FriendEntity)getEntity()).setChatMap(chatMap);
/*     */   }
/*     */   
/*     */   public HashSet<Long> getSendIds() {
/*  62 */     return ((FriendEntity)getEntity()).getSendIds();
/*     */   }
/*     */   
/*     */   public void setSendIds(HashSet<Long> sendIds) {
/*  66 */     ((FriendEntity)getEntity()).setSendIds(sendIds);
/*     */   }
/*     */   
/*     */   public HashSet<Long> getNeedReceiveIds() {
/*  70 */     return ((FriendEntity)getEntity()).getNeedReceiveIds();
/*     */   }
/*     */   
/*     */   public void setNeedReceiveIds(HashSet<Long> needReceiveIds) {
/*  74 */     ((FriendEntity)getEntity()).setNeedReceiveIds(needReceiveIds);
/*     */   }
/*     */   
/*     */   public HashSet<Long> getReceivedIds() {
/*  78 */     return ((FriendEntity)getEntity()).getReceivedIds();
/*     */   }
/*     */   
/*     */   public void setReceivedIds(HashSet<Long> receivedIds) {
/*  82 */     ((FriendEntity)getEntity()).setReceivedIds(receivedIds);
/*     */   }
/*     */   
/*     */   public int getTotalSendCount() {
/*  86 */     return ((FriendEntity)getEntity()).getTotalSendCount();
/*     */   }
/*     */   
/*     */   public void setTotalSendCount(int totalSendCount) {
/*  90 */     ((FriendEntity)getEntity()).setTotalSendCount(totalSendCount);
/*     */   }
/*     */   
/*     */   public HashSet<Long> getOfflineReceiveIds() {
/*  94 */     return ((FriendEntity)getEntity()).getOfflineReceiveIds();
/*     */   }
/*     */   
/*     */   public void setOfflineReceiveIds(HashSet<Long> offlineReceiveIds) {
/*  98 */     ((FriendEntity)getEntity()).setOfflineReceiveIds(offlineReceiveIds);
/*     */   }
/*     */   
/*     */   public ConcurrentHashMap<Long, Long> getFavors() {
/* 102 */     return ((FriendEntity)getEntity()).getFavors();
/*     */   }
/*     */   
/*     */   public void setFavors(ConcurrentHashMap<Long, Long> favors) {
/* 106 */     ((FriendEntity)getEntity()).setFavors(favors);
/*     */   }
/*     */   
/*     */   public void updateFavorsToDB() {
/* 110 */     getProxy().setUpdateStatus("favors");
/*     */   }
/*     */   
/*     */   public void updateIdsToDB() {
/* 114 */     getProxy().setUpdateStatus("ids");
/*     */   }
/*     */   
/*     */   public void updateApplyMapToDB() {
/* 118 */     getProxy().setUpdateStatus("applyMap");
/*     */   }
/*     */   
/*     */   public void updateChatMapToDB() {
/* 122 */     getProxy().setUpdateStatus("chatMap");
/*     */   }
/*     */   
/*     */   public void updateSendIdsToDB() {
/* 126 */     getProxy().setUpdateStatus("sendIds");
/*     */   }
/*     */   
/*     */   public void updateNeedReceiveIdsToDB() {
/* 130 */     getProxy().setUpdateStatus("needReceiveIds");
/*     */   }
/*     */   
/*     */   public void updateReceivedIdsToDB() {
/* 134 */     getProxy().setUpdateStatus("receivedIds");
/*     */   }
/*     */   
/*     */   public void updateTotalSendCountToDB() {
/* 138 */     getProxy().setUpdateStatus("totalSendCount");
/*     */   }
/*     */   
/*     */   public void updateOfflineReceiveIdsoDB() {
/* 142 */     getProxy().setUpdateStatus("offlineReceiveIds");
/*     */   }
/*     */   
/*     */   public Long getEaryApply() {
/* 146 */     int time = TimeUtil.currentTime();
/* 147 */     Long id = Long.valueOf(0L);
/* 148 */     for (Map.Entry<Long, Integer> entry : getApplyMap().entrySet()) {
/* 149 */       if (((Integer)entry.getValue()).intValue() < time) {
/* 150 */         time = ((Integer)entry.getValue()).intValue();
/* 151 */         id = entry.getKey();
/*     */       } 
/*     */     } 
/* 154 */     return id;
/*     */   }
/*     */   
/*     */   public void addFavorValue(long playerId, long favorValue) {
/* 158 */     if (!getFavors().containsKey(Long.valueOf(playerId))) {
/* 159 */       getFavors().put(Long.valueOf(playerId), Long.valueOf(favorValue));
/*     */     } else {
/* 161 */       getFavors().put(Long.valueOf(playerId), Long.valueOf(((Long)getFavors().get(Long.valueOf(playerId))).longValue() + favorValue));
/*     */     } 
/* 163 */     updateFavorsToDB();
/*     */ 
/*     */     
/* 166 */     FriendFavorValueNoticeResponse noticeResponse = new FriendFavorValueNoticeResponse();
/* 167 */     noticeResponse.playerId = playerId;
/* 168 */     noticeResponse.favorValue = getFavorValue(playerId);
/* 169 */     LookUpService.sendMessage(this.playerId, (ResponseBase)noticeResponse);
/*     */   }
/*     */   
/*     */   public void clearFavor(long playerId) {
/* 173 */     if (getFavors().containsKey(Long.valueOf(playerId))) {
/* 174 */       getFavors().remove(Long.valueOf(playerId));
/* 175 */       updateFavorsToDB();
/*     */     } 
/*     */   }
/*     */   
/*     */   public long getFavorValue(long playerId) {
/* 180 */     if (getFavors().containsKey(Long.valueOf(playerId)))
/* 181 */       return ((Long)getFavors().get(Long.valueOf(playerId))).longValue(); 
/* 182 */     return 0L;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getType() {
/* 187 */     return getClass().getSimpleName();
/*     */   }
/*     */ 
/*     */   
/*     */   public void build(long playerId) {
/* 192 */     this.proxy.setEntityStatus(IEntityProxy.ENTITY_STATUS.STATUS_ADD);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean reset(int time) {
/* 202 */     if (time == 0) {
/* 203 */       getSendIds().clear();
/* 204 */       updateSendIdsToDB();
/* 205 */       getNeedReceiveIds().clear();
/* 206 */       updateNeedReceiveIdsToDB();
/* 207 */       getReceivedIds().clear();
/* 208 */       updateReceivedIdsToDB();
/* 209 */       setNeedReceiveIds(getOfflineReceiveIds());
/* 210 */       updateNeedReceiveIdsToDB();
/* 211 */       setOfflineReceiveIds(new HashSet<>());
/* 212 */       updateOfflineReceiveIdsoDB();
/*     */     } 
/* 214 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\friend\FriendComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */