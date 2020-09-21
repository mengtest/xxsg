/*     */ package com.linlongyx.sanguo.webgame.app.mail;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.annotation.Table;
/*     */ import com.linlongyx.core.framework.dao.annotation.TableField;
/*     */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ @Table(tableName = "tb_mail", prefix = "mail_%serverId_%playerId", isPlayerIdKey = true, keyField = "id")
/*     */ public class MailEntity
/*     */   implements IMapEntity
/*     */ {
/*     */   @TableField(isKey = true)
/*     */   private final transient long playerId;
/*     */   @TableField(isKey = true)
/*     */   private final transient int id;
/*     */   private byte type;
/*     */   private long sendId;
/*     */   private String sendName;
/*     */   private int sendTime;
/*     */   private String title;
/*     */   private String context;
/*  25 */   private ArrayList<Reward> rewards = new ArrayList<>();
/*     */   private byte isRead;
/*     */   private byte isExtract;
/*     */   
/*     */   public MailEntity(long playerId, int id) {
/*  30 */     this.playerId = playerId;
/*  31 */     this.id = id;
/*     */   }
/*     */   
/*     */   public long getPlayerId() {
/*  35 */     return this.playerId;
/*     */   }
/*     */   
/*     */   public int getId() {
/*  39 */     return this.id;
/*     */   }
/*     */   
/*     */   public byte getType() {
/*  43 */     return this.type;
/*     */   }
/*     */   
/*     */   public void setType(byte type) {
/*  47 */     this.type = type;
/*     */   }
/*     */   
/*     */   public long getSendId() {
/*  51 */     return this.sendId;
/*     */   }
/*     */   
/*     */   public void setSendId(long sendId) {
/*  55 */     this.sendId = sendId;
/*     */   }
/*     */   
/*     */   public String getSendName() {
/*  59 */     return this.sendName;
/*     */   }
/*     */   
/*     */   public void setSendName(String sendName) {
/*  63 */     this.sendName = sendName;
/*     */   }
/*     */   
/*     */   public int getSendTime() {
/*  67 */     return this.sendTime;
/*     */   }
/*     */   
/*     */   public void setSendTime(int sendTime) {
/*  71 */     this.sendTime = sendTime;
/*     */   }
/*     */   
/*     */   public String getTitle() {
/*  75 */     return this.title;
/*     */   }
/*     */   
/*     */   public void setTitle(String title) {
/*  79 */     this.title = title;
/*     */   }
/*     */   
/*     */   public String getContext() {
/*  83 */     return this.context;
/*     */   }
/*     */   
/*     */   public void setContext(String context) {
/*  87 */     this.context = context;
/*     */   }
/*     */   
/*     */   public ArrayList<Reward> getRewards() {
/*  91 */     return this.rewards;
/*     */   }
/*     */   
/*     */   public void setRewards(ArrayList<Reward> rewards) {
/*  95 */     this.rewards = rewards;
/*     */   }
/*     */   
/*     */   public byte getIsRead() {
/*  99 */     return this.isRead;
/*     */   }
/*     */   
/*     */   public void setIsRead(byte isRead) {
/* 103 */     this.isRead = isRead;
/*     */   }
/*     */   
/*     */   public byte getIsExtract() {
/* 107 */     return this.isExtract;
/*     */   }
/*     */   
/*     */   public void setIsExtract(byte isExtract) {
/* 111 */     this.isExtract = isExtract;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 116 */     return "MailEntity{playerId=" + this.playerId + ", id=" + this.id + ", type=" + this.type + ", sendId=" + this.sendId + ", sendName='" + this.sendName + '\'' + ", sendTime=" + this.sendTime + ", title='" + this.title + '\'' + ", context='" + this.context + '\'' + ", rewards=" + this.rewards + ", isRead=" + this.isRead + ", isExtract=" + this.isExtract + '}';
/*     */   }
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
/*     */ 
/*     */ 
/*     */   
/*     */   public Object mapKey() {
/* 133 */     return Integer.valueOf(getId());
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\mail\MailEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */