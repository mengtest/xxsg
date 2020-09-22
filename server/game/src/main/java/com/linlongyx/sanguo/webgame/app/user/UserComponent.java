/*     */ package com.linlongyx.sanguo.webgame.app.user;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.proxy.IEntityProxy;
/*     */ import com.linlongyx.core.framework.logic.AbstractComponent;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.PlayerInfo;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class UserComponent
/*     */   extends AbstractComponent<UserEntity>
/*     */ {
/*     */   private boolean isCreatingPlayer;
/*     */   private int serverId;
/*     */   
/*     */   public UserComponent(long userId) {
/*  20 */     super(UserEntity.class);
/*  21 */     this.userId = userId;
/*  22 */     this.isCreatingPlayer = false;
/*  23 */     makeKey();
/*  24 */     getProxy().createProxy(new Object[] { Long.valueOf(userId) });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initUser(long userId, String userName, String token, int channel) {
/*  35 */     this.userId = userId;
/*  36 */     UserEntity entity = (UserEntity)getEntity();
/*     */     
/*  38 */     entity.setUserName(userName);
/*  39 */     entity.setChannel(channel);
/*  40 */     entity.setToken(token);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addPlayer(long playerId, String playerName, byte sex) {
/*  49 */     this.playerId = playerId;
/*  50 */     UserEntity entity = (UserEntity)getEntity();
/*     */     
/*  52 */     PlayerInfo playerInfo = new PlayerInfo();
/*  53 */     playerInfo.name = playerName;
/*  54 */     playerInfo.playerId = playerId;
/*  55 */     playerInfo.sex = sex;
/*  56 */     entity.getPlayers().add(playerInfo);
/*  57 */     if (isDBInit()) {
/*  58 */       this.proxy.setEntityStatus(IEntityProxy.ENTITY_STATUS.STATUS_MOD);
/*     */     } else {
/*  60 */       build(playerId);
/*     */     } 
/*     */   }
/*     */   public boolean isCreatingPlayer() {
/*  64 */     return this.isCreatingPlayer;
/*     */   }
/*     */   
/*     */   public synchronized void setCreatingPlayer(boolean creatingPlayer) {
/*  68 */     this.isCreatingPlayer = creatingPlayer;
/*     */   }
/*     */   
/*     */   public boolean hasPlayer(long playerId) {
/*  72 */     for (PlayerInfo playerInfo : ((UserEntity)getEntity()).getPlayers()) {
/*  73 */       if (playerId == playerInfo.playerId)
/*  74 */         return true; 
/*     */     } 
/*  76 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getType() {
/*  83 */     return getClass().getSimpleName();
/*     */   }
/*     */ 
/*     */   
/*     */   public void build(long playerId) {
/*  88 */     this.proxy.setEntityStatus(IEntityProxy.ENTITY_STATUS.STATUS_ADD);
/*     */   }
/*     */   
/*     */   public int getAccountId() {
/*  92 */     return ((UserEntity)getEntity()).getAccountId();
/*     */   }
/*     */   
/*     */   public void setAccountId(int accountId) {
/*  96 */     ((UserEntity)getEntity()).setAccountId(accountId);
/*     */   }
/*     */   
/*     */   public String getUserName() {
/* 100 */     return ((UserEntity)getEntity()).getUserName();
/*     */   }
/*     */   
/*     */   public void setUserName(String userName) {
/* 104 */     ((UserEntity)getEntity()).setUserName(userName);
/*     */   }
/*     */   
/*     */   public int getChannel() {
/* 108 */     return ((UserEntity)getEntity()).getChannel();
/*     */   }
/*     */   
/*     */   public void setChannel(int channel) {
/* 112 */     ((UserEntity)getEntity()).setChannel(channel);
/*     */   }
/*     */   
/*     */   public String getToken() {
/* 116 */     return ((UserEntity)getEntity()).getToken();
/*     */   }
/*     */   
/*     */   public void setToken(String token) {
/* 120 */     ((UserEntity)getEntity()).setToken(token);
/*     */   }
/*     */   
/*     */   public Set<PlayerInfo> getPlayers() {
/* 124 */     return ((UserEntity)getEntity()).getPlayers();
/*     */   }
/*     */   
/*     */   public void setPlayers(Set<PlayerInfo> players) {
/* 128 */     ((UserEntity)getEntity()).setPlayers(players);
/*     */   }
/*     */   
/*     */   public int getLastLoginTime() {
/* 132 */     return ((UserEntity)getEntity()).getLastLoginTime();
/*     */   }
/*     */   
/*     */   public void setLastLoginTime(int lastLoginTime) {
/* 136 */     ((UserEntity)getEntity()).setLastLoginTime(lastLoginTime);
/*     */   }
/*     */   
/*     */   public int getServerId() {
/* 140 */     return this.serverId;
/*     */   }
/*     */   
/*     */   public void setServerId(int serverId) {
/* 144 */     this.serverId = serverId;
/*     */   }
/*     */   
/*     */   public String getOpenid() {
/* 148 */     return ((UserEntity)getEntity()).getOpenid();
/*     */   }
/*     */   
/*     */   public void setOpenid(String openid) {
/* 152 */     ((UserEntity)getEntity()).setOpenid(openid);
/*     */   }
/*     */   
/*     */   public long getOrderByPlayerId() {
/* 156 */     return ((UserEntity)getEntity()).getOrderByPlayerId();
/*     */   }
/*     */   
/*     */   public void setOrderByPlayerId(long orderByPlayerId) {
/* 160 */     ((UserEntity)getEntity()).setOrderByPlayerId(orderByPlayerId);
/*     */   }
/*     */   
/*     */   public String getUid() {
/* 164 */     return ((UserEntity)getEntity()).getUid();
/*     */   }
/*     */   
/*     */   public void setUid(String uid) {
/* 168 */     ((UserEntity)getEntity()).setUid(uid);
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\ap\\user\UserComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */