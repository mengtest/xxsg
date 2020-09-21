/*     */ package com.linlongyx.sanguo.webgame.app.equip;
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
/*     */ @Table(tableName = "tb_equip", prefix = "equip_%serverId_%playerId", keyField = "mid", isPlayerIdKey = true)
/*     */ public class EquipEntity
/*     */   implements IMapEntity
/*     */ {
/*     */   @TableField(isKey = true)
/*     */   private final transient long playerId;
/*     */   @TableField(isKey = true)
/*     */   private final transient long mid;
/*     */   private int itemId;
/*     */   private int strengthLv;
/*     */   private int quaity;
/*     */   private int refineLv;
/*     */   private int zhuLv;
/*  26 */   private Map<Integer, Integer> stones = new HashMap<>();
/*     */   private byte isWear;
/*     */   private long fightValue;
/*     */   private int artificeProcess;
/*     */   private int artificeLevel;
/*     */   private int artificeLucky;
/*     */   private int talismanRank;
/*     */   private long belondTo;
/*     */   private int star;
/*     */   
/*     */   public EquipEntity(long playerId, long mid) {
/*  37 */     this.playerId = playerId;
/*  38 */     this.mid = mid;
/*     */   }
/*     */   
/*     */   public int getItemId() {
/*  42 */     return this.itemId;
/*     */   }
/*     */   
/*     */   public void setItemId(int itemId) {
/*  46 */     this.itemId = itemId;
/*     */   }
/*     */   
/*     */   public long getMid() {
/*  50 */     return this.mid;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getStrengthLv() {
/*  55 */     return this.strengthLv;
/*     */   }
/*     */   
/*     */   public void setStrengthLv(int strengthLv) {
/*  59 */     this.strengthLv = strengthLv;
/*     */   }
/*     */   
/*     */   public int getRefineLv() {
/*  63 */     return this.refineLv;
/*     */   }
/*     */   
/*     */   public void setRefineLv(int refineLv) {
/*  67 */     this.refineLv = refineLv;
/*     */   }
/*     */   
/*     */   public int getQuaity() {
/*  71 */     return this.quaity;
/*     */   }
/*     */   
/*     */   public void setQuaity(int quaity) {
/*  75 */     this.quaity = quaity;
/*     */   }
/*     */   
/*     */   public int getZhuLv() {
/*  79 */     return this.zhuLv;
/*     */   }
/*     */   
/*     */   public void setZhuLv(int zhuLv) {
/*  83 */     this.zhuLv = zhuLv;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getStones() {
/*  87 */     return this.stones;
/*     */   }
/*     */   
/*     */   public void setStones(Map<Integer, Integer> stones) {
/*  91 */     this.stones = stones;
/*     */   }
/*     */   
/*     */   public long getPlayerId() {
/*  95 */     return this.playerId;
/*     */   }
/*     */   
/*     */   public byte getIsWear() {
/*  99 */     return this.isWear;
/*     */   }
/*     */   
/*     */   public void setIsWear(byte isWear) {
/* 103 */     this.isWear = isWear;
/*     */   }
/*     */   
/*     */   public long getFightValue() {
/* 107 */     return this.fightValue;
/*     */   }
/*     */   
/*     */   public void setFightValue(long fightValue) {
/* 111 */     this.fightValue = fightValue;
/*     */   }
/*     */   
/*     */   public int getArtificeProcess() {
/* 115 */     return this.artificeProcess;
/*     */   }
/*     */   
/*     */   public void setArtificeProcess(int artificeProcess) {
/* 119 */     this.artificeProcess = artificeProcess;
/*     */   }
/*     */   
/*     */   public int getArtificeLevel() {
/* 123 */     return this.artificeLevel;
/*     */   }
/*     */   
/*     */   public void setArtificeLevel(int artificeLevel) {
/* 127 */     this.artificeLevel = artificeLevel;
/*     */   }
/*     */   
/*     */   public int getArtificeLucky() {
/* 131 */     return this.artificeLucky;
/*     */   }
/*     */   
/*     */   public void setArtificeLucky(int artificeLucky) {
/* 135 */     this.artificeLucky = artificeLucky;
/*     */   }
/*     */   
/*     */   public int getTalismanRank() {
/* 139 */     return this.talismanRank;
/*     */   }
/*     */   
/*     */   public void setTalismanRank(int talismanRank) {
/* 143 */     this.talismanRank = talismanRank;
/*     */   }
/*     */   
/*     */   public long getBelondTo() {
/* 147 */     return this.belondTo;
/*     */   }
/*     */   
/*     */   public void setBelondTo(long belondTo) {
/* 151 */     this.belondTo = belondTo;
/*     */   }
/*     */   
/*     */   public int getStar() {
/* 155 */     return this.star;
/*     */   }
/*     */   
/*     */   public void setStar(int star) {
/* 159 */     this.star = star;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean wear() {
/* 167 */     return (this.isWear != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 172 */     return "EquipEntity{playerId=" + this.playerId + ", mid=" + this.mid + ", itemId=" + this.itemId + ", strengthLv=" + this.strengthLv + ", quaity=" + this.quaity + ", refineLv=" + this.refineLv + ", zhuLv=" + this.zhuLv + ", stones=" + this.stones + ", isWear=" + this.isWear + ", fightValue=" + this.fightValue + ", artificeProcess=" + this.artificeProcess + ", artificeLevel=" + this.artificeLevel + ", artificeLucky=" + this.artificeLucky + ", talismanRank=" + this.talismanRank + ", belondTo=" + this.belondTo + ", star=" + this.star + '}';
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object mapKey() {
/* 194 */     return Long.valueOf(this.mid);
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\equip\EquipEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */