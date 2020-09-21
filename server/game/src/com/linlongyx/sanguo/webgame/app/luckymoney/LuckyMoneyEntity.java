/*     */ package com.linlongyx.sanguo.webgame.app.luckymoney;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.annotation.Table;
/*     */ import com.linlongyx.core.framework.dao.annotation.TableField;
/*     */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
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
/*     */ @Table(tableName = "tb_luckymoney", prefix = "luckymoney_%serverId_%playerId", isPlayerIdKey = true, keyField = "id")
/*     */ public class LuckyMoneyEntity
/*     */   implements IMapEntity
/*     */ {
/*     */   @TableField(isKey = true)
/*     */   private final transient long playerId;
/*     */   @TableField(isKey = true)
/*     */   private final transient int id;
/*     */   private long goldMoney;
/*     */   private long silverMoney;
/*     */   private long goldMoneySum;
/*     */   private long silverMoneySum;
/*     */   private long taskPoint;
/*     */   private int battle;
/*     */   private boolean open;
/*     */   
/*     */   public LuckyMoneyEntity(long playerId, int id) {
/*  33 */     this.playerId = playerId;
/*  34 */     this.id = id;
/*     */   }
/*     */ 
/*     */   
/*     */   public long getPlayerId() {
/*  39 */     return this.playerId;
/*     */   }
/*     */   
/*     */   public int getId() {
/*  43 */     return this.id;
/*     */   }
/*     */   
/*     */   public long getGoldMoney() {
/*  47 */     return this.goldMoney;
/*     */   }
/*     */   
/*     */   public void setGoldMoney(long goldMoney) {
/*  51 */     this.goldMoney = goldMoney;
/*     */   }
/*     */   
/*     */   public long getSilverMoney() {
/*  55 */     return this.silverMoney;
/*     */   }
/*     */   
/*     */   public void setSilverMoney(long silverMoney) {
/*  59 */     this.silverMoney = silverMoney;
/*     */   }
/*     */   
/*     */   public boolean isOpen() {
/*  63 */     return this.open;
/*     */   }
/*     */   
/*     */   public void setOpen(boolean open) {
/*  67 */     this.open = open;
/*     */   }
/*     */   
/*     */   public long getTaskPoint() {
/*  71 */     return this.taskPoint;
/*     */   }
/*     */   
/*     */   public void setTaskPoint(long taskPoint) {
/*  75 */     this.taskPoint = taskPoint;
/*     */   }
/*     */   
/*     */   public int getBattle() {
/*  79 */     return this.battle;
/*     */   }
/*     */   
/*     */   public void setBattle(int battle) {
/*  83 */     this.battle = battle;
/*     */   }
/*     */   
/*     */   public long getGoldMoneySum() {
/*  87 */     return this.goldMoneySum;
/*     */   }
/*     */   
/*     */   public void setGoldMoneySum(long goldMoneySum) {
/*  91 */     this.goldMoneySum = goldMoneySum;
/*     */   }
/*     */   
/*     */   public long getSilverMoneySum() {
/*  95 */     return this.silverMoneySum;
/*     */   }
/*     */   
/*     */   public void setSilverMoneySum(long silverMoneySum) {
/*  99 */     this.silverMoneySum = silverMoneySum;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 104 */     return "LuckyMoneyEntity{playerId=" + this.playerId + ", id=" + this.id + ", goldMoney=" + this.goldMoney + ", silverMoney=" + this.silverMoney + ", goldMoneySum=" + this.goldMoneySum + ", silverMoneySum=" + this.silverMoneySum + ", taskPoint=" + this.taskPoint + ", battle=" + this.battle + ", open=" + this.open + '}';
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
/* 119 */     return Integer.valueOf(getId());
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\luckymoney\LuckyMoneyEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */