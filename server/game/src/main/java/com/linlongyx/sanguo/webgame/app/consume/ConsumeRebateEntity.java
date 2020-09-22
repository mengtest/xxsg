/*     */ package com.linlongyx.sanguo.webgame.app.consume;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.annotation.Table;
/*     */ import com.linlongyx.core.framework.dao.annotation.TableField;
/*     */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Table(tableName = "tb_consume", prefix = "consume_%serverId_%playerId", isPlayerIdKey = true, keyField = "id")
/*     */ public class ConsumeRebateEntity
/*     */   implements IMapEntity
/*     */ {
/*     */   @TableField(isKey = true)
/*     */   private final transient long playerId;
/*     */   @TableField(isKey = true)
/*     */   private final transient int id;
/*     */   private long takeConsume;
/*  22 */   private Map<Integer, Integer> takeReward = new HashMap<>();
/*     */   private long postureConsume;
/*  24 */   private Map<Integer, Integer> postureReward = new HashMap<>();
/*     */   private long zhenFaConsume;
/*  26 */   private Map<Integer, Integer> zhenFaReward = new HashMap<>();
/*     */   private boolean open;
/*     */   
/*     */   public ConsumeRebateEntity(long playerId, int id) {
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
/*     */   public long getTakeConsume() {
/*  43 */     return this.takeConsume;
/*     */   }
/*     */   
/*     */   public void setTakeConsume(long takeConsume) {
/*  47 */     this.takeConsume = takeConsume;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getTakeReward() {
/*  51 */     return this.takeReward;
/*     */   }
/*     */   
/*     */   public void setTakeReward(Map<Integer, Integer> takeReward) {
/*  55 */     this.takeReward = takeReward;
/*     */   }
/*     */   
/*     */   public long getPostureConsume() {
/*  59 */     return this.postureConsume;
/*     */   }
/*     */   
/*     */   public void setPostureConsume(long postureConsume) {
/*  63 */     this.postureConsume = postureConsume;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getPostureReward() {
/*  67 */     return this.postureReward;
/*     */   }
/*     */   
/*     */   public void setPostureReward(Map<Integer, Integer> postureReward) {
/*  71 */     this.postureReward = postureReward;
/*     */   }
/*     */   
/*     */   public boolean isOpen() {
/*  75 */     return this.open;
/*     */   }
/*     */   
/*     */   public void setOpen(boolean open) {
/*  79 */     this.open = open;
/*     */   }
/*     */   
/*     */   public long getZhenFaConsume() {
/*  83 */     return this.zhenFaConsume;
/*     */   }
/*     */   
/*     */   public void setZhenFaConsume(long zhenFaConsume) {
/*  87 */     this.zhenFaConsume = zhenFaConsume;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getZhenFaReward() {
/*  91 */     return this.zhenFaReward;
/*     */   }
/*     */   
/*     */   public void setZhenFaReward(Map<Integer, Integer> zhenFaReward) {
/*  95 */     this.zhenFaReward = zhenFaReward;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 100 */     return "ConsumeRebateEntity{playerId=" + this.playerId + ", id=" + this.id + ", takeConsume=" + this.takeConsume + ", takeReward=" + this.takeReward + ", postureConsume=" + this.postureConsume + ", postureReward=" + this.postureReward + ", open=" + this.open + ", zhenFaConsume=" + this.zhenFaConsume + ", zhenFaReward=" + this.zhenFaReward + '}';
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
/* 115 */     return Integer.valueOf(getId());
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\consume\ConsumeRebateEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */