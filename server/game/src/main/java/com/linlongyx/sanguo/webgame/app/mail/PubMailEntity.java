/*     */ package com.linlongyx.sanguo.webgame.app.mail;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.annotation.Table;
/*     */ import com.linlongyx.core.framework.dao.annotation.TableField;
/*     */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ @Table(tableName = "tb_pubMail", prefix = "pubMail_%serverId", keyField = "id")
/*     */ public class PubMailEntity
/*     */   implements IMapEntity
/*     */ {
/*     */   @TableField(isKey = true)
/*     */   private final transient int id;
/*     */   private byte type;
/*     */   private long sendId;
/*     */   private String sendName;
/*     */   private int sendTime;
/*     */   private String title;
/*     */   private String context;
/*  25 */   private ArrayList<Reward> rewards = new ArrayList<>();
/*  26 */   private Map<Integer, String> rules = new HashMap<>();
/*     */   
/*     */   public PubMailEntity(int id) {
/*  29 */     this.id = id;
/*     */   }
/*     */   
/*     */   public int getId() {
/*  33 */     return this.id;
/*     */   }
/*     */   
/*     */   public byte getType() {
/*  37 */     return this.type;
/*     */   }
/*     */   
/*     */   public void setType(byte type) {
/*  41 */     this.type = type;
/*     */   }
/*     */   
/*     */   public long getSendId() {
/*  45 */     return this.sendId;
/*     */   }
/*     */   
/*     */   public void setSendId(long sendId) {
/*  49 */     this.sendId = sendId;
/*     */   }
/*     */   
/*     */   public String getSendName() {
/*  53 */     return this.sendName;
/*     */   }
/*     */   
/*     */   public void setSendName(String sendName) {
/*  57 */     this.sendName = sendName;
/*     */   }
/*     */   
/*     */   public int getSendTime() {
/*  61 */     return this.sendTime;
/*     */   }
/*     */   
/*     */   public void setSendTime(int sendTime) {
/*  65 */     this.sendTime = sendTime;
/*     */   }
/*     */   
/*     */   public String getTitle() {
/*  69 */     return this.title;
/*     */   }
/*     */   
/*     */   public void setTitle(String title) {
/*  73 */     this.title = title;
/*     */   }
/*     */   
/*     */   public String getContext() {
/*  77 */     return this.context;
/*     */   }
/*     */   
/*     */   public void setContext(String context) {
/*  81 */     this.context = context;
/*     */   }
/*     */   
/*     */   public ArrayList<Reward> getRewards() {
/*  85 */     return this.rewards;
/*     */   }
/*     */   
/*     */   public void setRewards(ArrayList<Reward> rewards) {
/*  89 */     this.rewards = rewards;
/*     */   }
/*     */   
/*     */   public Map<Integer, String> getRules() {
/*  93 */     return this.rules;
/*     */   }
/*     */   
/*     */   public void setRules(Map<Integer, String> rules) {
/*  97 */     this.rules = rules;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 102 */     return "PubMailEntity{id=" + this.id + ", type=" + this.type + ", sendId=" + this.sendId + ", sendName='" + this.sendName + '\'' + ", sendTime=" + this.sendTime + ", title='" + this.title + '\'' + ", context='" + this.context + '\'' + ", rewards=" + this.rewards + ", rules=" + this.rules + '}';
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
/*     */   public Object mapKey() {
/* 117 */     return Integer.valueOf(getId());
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\mail\PubMailEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */