/*     */ package com.linlongyx.sanguo.webgame.app.user;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.annotation.Table;
/*     */ import com.linlongyx.core.framework.dao.annotation.TableField;
/*     */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.PlayerInfo;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Table(tableName = "tb_user", prefix = "user_%serverId_%userId", keyField = "userId")
/*     */ public class UserEntity
/*     */   implements IEntity
/*     */ {
/*     */   @TableField(isKey = true)
/*     */   private final transient long userId;
/*     */   private String userName;
/*     */   private int channel;
/*     */   private String token;
/*  28 */   private Set<PlayerInfo> players = new HashSet<>();
/*     */   
/*     */   private int lastLoginTime;
/*     */   
/*     */   private int accountId;
/*     */   
/*     */   private String openid;
/*     */   
/*     */   private long orderByPlayerId;
/*     */   
/*     */   private String uid;
/*     */   
/*     */   public UserEntity(long userId) {
/*  41 */     this.userId = userId;
/*     */   }
/*     */   
/*     */   public long getUserId() {
/*  45 */     return this.userId;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getUserName() {
/*  50 */     return this.userName;
/*     */   }
/*     */   
/*     */   public void setUserName(String userName) {
/*  54 */     this.userName = userName;
/*     */   }
/*     */   
/*     */   public int getChannel() {
/*  58 */     return this.channel;
/*     */   }
/*     */   
/*     */   public void setChannel(int channel) {
/*  62 */     this.channel = channel;
/*     */   }
/*     */   
/*     */   public String getToken() {
/*  66 */     return this.token;
/*     */   }
/*     */   
/*     */   public void setToken(String token) {
/*  70 */     this.token = token;
/*     */   }
/*     */   
/*     */   public Set<PlayerInfo> getPlayers() {
/*  74 */     if (null == this.players) {
/*  75 */       this.players = new HashSet<>();
/*     */     }
/*  77 */     return this.players;
/*     */   }
/*     */   
/*     */   public void setPlayers(Set<PlayerInfo> players) {
/*  81 */     this.players = players;
/*     */   }
/*     */   
/*     */   public int getLastLoginTime() {
/*  85 */     return this.lastLoginTime;
/*     */   }
/*     */   
/*     */   public void setLastLoginTime(int lastLoginTime) {
/*  89 */     this.lastLoginTime = lastLoginTime;
/*     */   }
/*     */   
/*     */   public int getAccountId() {
/*  93 */     return this.accountId;
/*     */   }
/*     */   
/*     */   public void setAccountId(int accountId) {
/*  97 */     this.accountId = accountId;
/*     */   }
/*     */   
/*     */   public String getOpenid() {
/* 101 */     return this.openid;
/*     */   }
/*     */   
/*     */   public void setOpenid(String openid) {
/* 105 */     this.openid = openid;
/*     */   }
/*     */   
/*     */   public long getOrderByPlayerId() {
/* 109 */     return this.orderByPlayerId;
/*     */   }
/*     */   
/*     */   public void setOrderByPlayerId(long orderByPlayerId) {
/* 113 */     this.orderByPlayerId = orderByPlayerId;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getUid() {
/* 118 */     return this.uid;
/*     */   }
/*     */   
/*     */   public void setUid(String uid) {
/* 122 */     this.uid = uid;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 126 */     return "UserEntity{userId=" + this.userId + ", userName='" + this.userName + '\'' + ", channel=" + this.channel + ", token='" + this.token + '\'' + ", players=" + this.players + ", lastLoginTime=" + this.lastLoginTime + ", accountId=" + this.accountId + ", openid='" + this.openid + '\'' + ", orderByPlayerId='" + this.orderByPlayerId + '\'' + ", uid='" + this.uid + '\'' + '}';
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\ap\\user\UserEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */