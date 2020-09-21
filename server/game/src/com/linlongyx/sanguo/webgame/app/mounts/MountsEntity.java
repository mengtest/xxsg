/*     */ package com.linlongyx.sanguo.webgame.app.mounts;
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
/*     */ @Table(tableName = "tb_mounts", prefix = "mounts_%serverId_%playerId", isPlayerIdKey = true, keyField = "mountsId")
/*     */ public class MountsEntity
/*     */   implements IMapEntity
/*     */ {
/*     */   @TableField(isKey = true)
/*     */   private final transient long playerId;
/*     */   @TableField(isKey = true)
/*     */   private final transient int mountsId;
/*     */   private int level;
/*     */   private int star;
/*     */   private int battle;
/*     */   private int exp;
/*  24 */   private Map<Integer, Integer> breaksLevel = new HashMap<>();
/*     */   private long fightValue;
/*     */   
/*     */   public MountsEntity(long playerId, int mountsId) {
/*  28 */     this.playerId = playerId;
/*  29 */     this.mountsId = mountsId;
/*     */   }
/*     */   
/*     */   public long getPlayerId() {
/*  33 */     return this.playerId;
/*     */   }
/*     */   
/*     */   public int getMountsId() {
/*  37 */     return this.mountsId;
/*     */   }
/*     */   
/*     */   public int getLevel() {
/*  41 */     return this.level;
/*     */   }
/*     */   
/*     */   public void setLevel(int level) {
/*  45 */     this.level = level;
/*     */   }
/*     */   
/*     */   public int getStar() {
/*  49 */     return this.star;
/*     */   }
/*     */   
/*     */   public void setStar(int star) {
/*  53 */     this.star = star;
/*     */   }
/*     */   
/*     */   public int getBattle() {
/*  57 */     return this.battle;
/*     */   }
/*     */   
/*     */   public void setBattle(int battle) {
/*  61 */     this.battle = battle;
/*     */   }
/*     */   
/*     */   public int getExp() {
/*  65 */     return this.exp;
/*     */   }
/*     */   
/*     */   public void setExp(int exp) {
/*  69 */     this.exp = exp;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getBreaksLevel() {
/*  73 */     return this.breaksLevel;
/*     */   }
/*     */   
/*     */   public void setBreaksLevel(Map<Integer, Integer> breaksLevel) {
/*  77 */     this.breaksLevel = breaksLevel;
/*     */   }
/*     */   
/*     */   public long getFightValue() {
/*  81 */     return this.fightValue;
/*     */   }
/*     */   
/*     */   public void setFightValue(long fightValue) {
/*  85 */     this.fightValue = fightValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  90 */     return "WarPetEntity{playerId=" + this.playerId + "mountsId=" + this.mountsId + "level=" + this.level + "star=" + this.star + "battle=" + this.battle + "exp=" + this.exp + "breaksLevel=" + this.breaksLevel + "fightValue=" + this.fightValue + '}';
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
/*     */   public Object mapKey() {
/* 104 */     return Integer.valueOf(getMountsId());
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\mounts\MountsEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */