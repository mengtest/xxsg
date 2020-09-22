/*     */ package com.linlongyx.sanguo.webgame.app.partner;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.annotation.Table;
/*     */ import com.linlongyx.core.framework.dao.annotation.TableField;
/*     */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ @Table(tableName = "tb_partner", prefix = "partner_%serverId_%playerId", isPlayerIdKey = true, keyField = "pid")
/*     */ public class PartnerEntity
/*     */   implements IMapEntity {
/*     */   @TableField(isKey = true)
/*     */   private final transient long playerId;
/*     */   @TableField(isKey = true)
/*     */   private final transient long pid;
/*     */   private int tableId;
/*     */   private int stars;
/*     */   private int level;
/*     */   private long exp;
/*     */   private int wearSkin;
/*  23 */   private Set<Integer> activeSkins = new HashSet<>();
/*     */   private int desLv;
/*     */   private int progress;
/*     */   private int primLv;
/*     */   private int breakthroughs;
/*  28 */   private Map<Integer, Long> equips = new HashMap<>();
/*     */   private int status;
/*     */   private long fightValue;
/*     */   private int soulLevel;
/*     */   private long talisman;
/*  33 */   private Map<Integer, Long> runeMap = new HashMap<>();
/*  34 */   private Map<Integer, Map<Integer, Long>> reincarnationMap = new HashMap<>();
/*  35 */   private HashSet<Integer> reincarnationIds = new HashSet<>();
/*     */   
/*     */   public PartnerEntity(long playerId, long pid) {
/*  38 */     this.playerId = playerId;
/*  39 */     this.pid = pid;
/*     */   }
/*     */   
/*     */   public long getPlayerId() {
/*  43 */     return this.playerId;
/*     */   }
/*     */   
/*     */   public long getPid() {
/*  47 */     return this.pid;
/*     */   }
/*     */   
/*     */   public int getLevel() {
/*  51 */     return this.level;
/*     */   }
/*     */   
/*     */   public void setLevel(int level) {
/*  55 */     this.level = level;
/*     */   }
/*     */   
/*     */   public long getExp() {
/*  59 */     return this.exp;
/*     */   }
/*     */   
/*     */   public void setExp(long exp) {
/*  63 */     this.exp = exp;
/*     */   }
/*     */   
/*     */   public int getWearSkin() {
/*  67 */     return this.wearSkin;
/*     */   }
/*     */   
/*     */   public void setWearSkin(int wearSkin) {
/*  71 */     this.wearSkin = wearSkin;
/*     */   }
/*     */   
/*     */   public Set<Integer> getActiveSkins() {
/*  75 */     return this.activeSkins;
/*     */   }
/*     */   
/*     */   public void setActiveSkins(Set<Integer> activeSkins) {
/*  79 */     this.activeSkins = activeSkins;
/*     */   }
/*     */   
/*     */   public int getDesLv() {
/*  83 */     return this.desLv;
/*     */   }
/*     */   
/*     */   public void setDesLv(int desLv) {
/*  87 */     this.desLv = desLv;
/*     */   }
/*     */   
/*     */   public int getProgress() {
/*  91 */     return this.progress;
/*     */   }
/*     */   
/*     */   public void setProgress(int progress) {
/*  95 */     this.progress = progress;
/*     */   }
/*     */   
/*     */   public int getPrimLv() {
/*  99 */     return this.primLv;
/*     */   }
/*     */   
/*     */   public void setPrimLv(int primLv) {
/* 103 */     this.primLv = primLv;
/*     */   }
/*     */   
/*     */   public int getTableId() {
/* 107 */     return this.tableId;
/*     */   }
/*     */   
/*     */   public void setTableId(int tableId) {
/* 111 */     this.tableId = tableId;
/*     */   }
/*     */   
/*     */   public int getStars() {
/* 115 */     return this.stars;
/*     */   }
/*     */   
/*     */   public void setStars(int stars) {
/* 119 */     this.stars = stars;
/*     */   }
/*     */   
/*     */   public int getBreakthroughs() {
/* 123 */     return this.breakthroughs;
/*     */   }
/*     */   
/*     */   public void setBreakthroughs(int breakthroughs) {
/* 127 */     this.breakthroughs = breakthroughs;
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<Integer, Long> getEquips() {
/* 132 */     return this.equips;
/*     */   }
/*     */   
/*     */   public void setEquips(Map<Integer, Long> equips) {
/* 136 */     this.equips = equips;
/*     */   }
/*     */   
/*     */   public int getStatus() {
/* 140 */     return this.status;
/*     */   }
/*     */   
/*     */   public void setStatus(int status) {
/* 144 */     this.status = status;
/*     */   }
/*     */   
/*     */   public long getFightValue() {
/* 148 */     return this.fightValue;
/*     */   }
/*     */   
/*     */   public void setFightValue(long fightValue) {
/* 152 */     this.fightValue = fightValue;
/*     */   }
/*     */   
/*     */   public int getSoulLevel() {
/* 156 */     return this.soulLevel;
/*     */   }
/*     */   
/*     */   public void setSoulLevel(int soulLevel) {
/* 160 */     this.soulLevel = soulLevel;
/*     */   }
/*     */   
/*     */   public long getTalisman() {
/* 164 */     return this.talisman;
/*     */   }
/*     */   
/*     */   public void setTalisman(long talisman) {
/* 168 */     this.talisman = talisman;
/*     */   }
/*     */   
/*     */   public Map<Integer, Long> getRuneMap() {
/* 172 */     return this.runeMap;
/*     */   }
/*     */   
/*     */   public void setRuneMap(Map<Integer, Long> runeMap) {
/* 176 */     this.runeMap = runeMap;
/*     */   }
/*     */   
/*     */   public Map<Integer, Map<Integer, Long>> getReincarnationMap() {
/* 180 */     return this.reincarnationMap;
/*     */   }
/*     */   
/*     */   public void setReincarnationMap(Map<Integer, Map<Integer, Long>> reincarnationMap) {
/* 184 */     this.reincarnationMap = reincarnationMap;
/*     */   }
/*     */   
/*     */   public HashSet<Integer> getReincarnationIds() {
/* 188 */     return this.reincarnationIds;
/*     */   }
/*     */   
/*     */   public void setReincarnationIds(HashSet<Integer> reincarnationIds) {
/* 192 */     this.reincarnationIds = reincarnationIds;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 197 */     return "PartnerEntity{playerId=" + this.playerId + ", pid=" + this.pid + ", tableId=" + this.tableId + ", stars=" + this.stars + ", level=" + this.level + ", exp=" + this.exp + ", wearSkin=" + this.wearSkin + ", activeSkins=" + this.activeSkins + ", desLv=" + this.desLv + ", progress=" + this.progress + ", primLv=" + this.primLv + ", breakthroughs=" + this.breakthroughs + ", equips=" + this.equips + ", status=" + this.status + ", fightValue=" + this.fightValue + ", soulLevel=" + this.soulLevel + ", talisman=" + this.talisman + ", runeMap=" + this.runeMap + ", reincarnationMap=" + this.reincarnationMap + ", reincarnationIds=" + this.reincarnationIds + '}';
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object mapKey() {
/* 223 */     return Long.valueOf(this.pid);
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\partner\PartnerEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */