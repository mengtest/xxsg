/*     */ package com.linlongyx.sanguo.webgame.rmi.tower;
/*     */ import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/*     */ 
/*     */ @JsonIgnoreProperties(ignoreUnknown = true)
/*     */ public class TowerLayerData {
/*     */   private int layerId;
/*     */   
/*     */   public enum TowerLayerState {
/*   9 */     FREE(1),
/*  10 */     OWN(2),
/*  11 */     FIGHTING(3);
/*     */     
/*     */     int state;
/*     */     
/*     */     TowerLayerState(int state) {
/*  16 */       this.state = state;
/*     */     }
/*     */     
/*     */     public int getState() {
/*  20 */       return this.state;
/*     */     }
/*     */   }
/*     */   
/*     */   public enum TowerLayerFightState {
/*  25 */     UNKNOWN(0),
/*  26 */     CAN_OWN(1),
/*  27 */     FIGHTING_CAN_OWN(2),
/*  28 */     FIGHTING_CAN_NOT_OWN(3),
/*  29 */     OWN_CAN_FIGHT(4),
/*  30 */     OWN_PROTECT(5);
/*     */     
/*     */     int state;
/*     */     
/*     */     TowerLayerFightState(int state) {
/*  35 */       this.state = state;
/*     */     }
/*     */     
/*     */     public int getState() {
/*  39 */       return this.state;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*  44 */   private TowerLayerState state = TowerLayerState.FREE;
/*     */   
/*     */   private long ownerId;
/*     */   private long fighterId;
/*     */   private int expireTime;
/*     */   private int ownTime;
/*     */   
/*     */   public TowerLayerData() {}
/*     */   
/*     */   public TowerLayerData(int layerId) {
/*  54 */     this.layerId = layerId;
/*     */   }
/*     */   
/*     */   public int getLayerId() {
/*  58 */     return this.layerId;
/*     */   }
/*     */   
/*     */   public void setLayerId(int layerId) {
/*  62 */     this.layerId = layerId;
/*     */   }
/*     */   
/*     */   public TowerLayerState getState() {
/*  66 */     return this.state;
/*     */   }
/*     */   
/*     */   public void setState(TowerLayerState state) {
/*  70 */     this.state = state;
/*     */   }
/*     */   
/*     */   public long getOwnerId() {
/*  74 */     return this.ownerId;
/*     */   }
/*     */   
/*     */   public void setOwnerId(long ownerId) {
/*  78 */     this.ownerId = ownerId;
/*     */   }
/*     */   
/*     */   public int getExpireTime() {
/*  82 */     return this.expireTime;
/*     */   }
/*     */   
/*     */   public void setExpireTime(int expireTime) {
/*  86 */     this.expireTime = expireTime;
/*     */   }
/*     */   
/*     */   public long getFighterId() {
/*  90 */     return this.fighterId;
/*     */   }
/*     */   
/*     */   public void setFighterId(long fighterId) {
/*  94 */     this.fighterId = fighterId;
/*     */   }
/*     */   
/*     */   public int getOwnTime() {
/*  98 */     return this.ownTime;
/*     */   }
/*     */   
/*     */   public void setOwnTime(int ownTime) {
/* 102 */     this.ownTime = ownTime;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\rmi\tower\TowerLayerData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */