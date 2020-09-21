/*     */ package com.linlongyx.sanguo.logclient.event;
/*     */ 
/*     */ import org.msgpack.annotation.Message;
/*     */ 
/*     */ 
/*     */ 
/*     */ @Message
/*     */ public class DefaultChatEvent
/*     */   extends DefaultEvent
/*     */ {
/*     */   private static final long serialVersionUID = -5107517633801056556L;
/*     */   private long serverId;
/*     */   private long playerId;
/*     */   private String playerName;
/*     */   private String content;
/*     */   private long createtime;
/*     */   private long targetId;
/*     */   private String targetName;
/*     */   private long channelUID;
/*     */   private String uid;
/*     */   private String touid;
/*     */   private int ip;
/*     */   
/*     */   public DefaultChatEvent() {}
/*     */   
/*     */   public DefaultChatEvent(int eventId) {
/*  27 */     super(eventId);
/*     */   }
/*     */   
/*     */   public DefaultChatEvent(int eventId, long serverId, long playerId, String playerName, String content, long channelUID, String uid, int ip) {
/*  31 */     this(eventId, serverId, playerId, playerName, content, 0L, null, channelUID, uid, null, ip);
/*     */   }
/*     */ 
/*     */   
/*     */   public DefaultChatEvent(int eventId, long serverId, long playerId, String playerName, String content, long targetId, String targetName, long channelUID, String uid, String touid, int ip) {
/*  36 */     super(eventId);
/*  37 */     this.serverId = serverId;
/*  38 */     this.playerId = playerId;
/*  39 */     this.playerName = playerName;
/*  40 */     this.content = content;
/*  41 */     this.createtime = System.currentTimeMillis();
/*  42 */     this.targetId = targetId;
/*  43 */     this.targetName = targetName;
/*  44 */     this.channelUID = channelUID;
/*  45 */     this.uid = uid;
/*  46 */     this.touid = touid;
/*  47 */     this.ip = ip;
/*     */   }
/*     */   
/*     */   public long getServerId() {
/*  51 */     return this.serverId;
/*     */   }
/*     */   
/*     */   public void setServerId(long serverId) {
/*  55 */     this.serverId = serverId;
/*     */   }
/*     */   
/*     */   public long getPlayerId() {
/*  59 */     return this.playerId;
/*     */   }
/*     */   
/*     */   public void setPlayerId(long playerId) {
/*  63 */     this.playerId = playerId;
/*     */   }
/*     */   
/*     */   public String getPlayerName() {
/*  67 */     return this.playerName;
/*     */   }
/*     */   
/*     */   public void setPlayerName(String playerName) {
/*  71 */     this.playerName = playerName;
/*     */   }
/*     */   
/*     */   public String getContent() {
/*  75 */     return this.content;
/*     */   }
/*     */   
/*     */   public void setContent(String content) {
/*  79 */     this.content = content;
/*     */   }
/*     */   
/*     */   public long getCreatetime() {
/*  83 */     return this.createtime;
/*     */   }
/*     */   
/*     */   public void setCreatetime(long createtime) {
/*  87 */     this.createtime = createtime;
/*     */   }
/*     */   
/*     */   public long getTargetId() {
/*  91 */     return this.targetId;
/*     */   }
/*     */   
/*     */   public void setTargetId(long targetId) {
/*  95 */     this.targetId = targetId;
/*     */   }
/*     */   
/*     */   public String getTargetName() {
/*  99 */     return this.targetName;
/*     */   }
/*     */   
/*     */   public void setTargetName(String targetName) {
/* 103 */     this.targetName = targetName;
/*     */   }
/*     */   
/*     */   public long getChannelUID() {
/* 107 */     return this.channelUID;
/*     */   }
/*     */   
/*     */   public void setChannelUID(long channelUID) {
/* 111 */     this.channelUID = channelUID;
/*     */   }
/*     */   
/*     */   public String getUid() {
/* 115 */     return this.uid;
/*     */   }
/*     */   
/*     */   public void setUid(String uid) {
/* 119 */     this.uid = uid;
/*     */   }
/*     */   
/*     */   public String getTouid() {
/* 123 */     return this.touid;
/*     */   }
/*     */   
/*     */   public void setTouid(String touid) {
/* 127 */     this.touid = touid;
/*     */   }
/*     */   
/*     */   public int getIp() {
/* 131 */     return this.ip;
/*     */   }
/*     */   
/*     */   public void setIp(int ip) {
/* 135 */     this.ip = ip;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 140 */     return "DefaultChatEvent{serverId=" + this.serverId + ", playerId=" + this.playerId + ", playerName='" + this.playerName + '\'' + ", content='" + this.content + '\'' + ", createtime=" + this.createtime + ", targetId=" + this.targetId + ", targetName='" + this.targetName + '\'' + ", channelUID=" + this.channelUID + ", uid='" + this.uid + '\'' + ", touid='" + this.touid + '\'' + ", ip=" + this.ip + "} " + super
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
/*     */       
/* 152 */       .toString();
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\logclient\event\DefaultChatEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */