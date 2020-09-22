/*     */ package com.linlongyx.sanguo.webgame.app.group;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.annotation.Table;
/*     */ import com.linlongyx.core.framework.dao.annotation.TableField;
/*     */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ @Table(tableName = "tb_groupMember", prefix = "groupMember_%serverId_%playerId", keyField = "playerId", isPlayerIdKey = true)
/*     */ public class GroupMemberEntity
/*     */   implements IEntity {
/*     */   @TableField(isKey = true)
/*     */   private final transient long playerId;
/*     */   private long id;
/*     */   private int position;
/*  18 */   private Set<Long> applySet = new HashSet<>();
/*     */   private long totalOffer;
/*  20 */   private Set<Integer> sacrificeBox = new HashSet<>();
/*     */   private int sacrificeType;
/*  22 */   private Map<Integer, Integer> skills = new HashMap<>();
/*     */   private boolean listOpen;
/*  24 */   private HashMap<Integer, Integer> playerRewards = new HashMap<>();
/*     */   private int fightTimes;
/*     */   private int lastResettime;
/*     */   private int totalTimes;
/*     */   
/*     */   public GroupMemberEntity(long playerId) {
/*  30 */     this.playerId = playerId;
/*     */   }
/*     */   
/*     */   public long getPlayerId() {
/*  34 */     return this.playerId;
/*     */   }
/*     */   
/*     */   public long getId() {
/*  38 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(long id) {
/*  42 */     this.id = id;
/*     */   }
/*     */   
/*     */   public int getPosition() {
/*  46 */     return this.position;
/*     */   }
/*     */   
/*     */   public void setPosition(int position) {
/*  50 */     this.position = position;
/*     */   }
/*     */   
/*     */   public Set<Long> getApplySet() {
/*  54 */     return this.applySet;
/*     */   }
/*     */   
/*     */   public void setApplySet(Set<Long> applySet) {
/*  58 */     this.applySet = applySet;
/*     */   }
/*     */   
/*     */   public long getTotalOffer() {
/*  62 */     return this.totalOffer;
/*     */   }
/*     */   
/*     */   public void setTotalOffer(long totalOffer) {
/*  66 */     this.totalOffer = totalOffer;
/*     */   }
/*     */   
/*     */   public Set<Integer> getSacrificeBox() {
/*  70 */     return this.sacrificeBox;
/*     */   }
/*     */   
/*     */   public void setSacrificeBox(Set<Integer> sacrificeBox) {
/*  74 */     this.sacrificeBox = sacrificeBox;
/*     */   }
/*     */   
/*     */   public int getSacrificeType() {
/*  78 */     return this.sacrificeType;
/*     */   }
/*     */   
/*     */   public void setSacrificeType(int sacrificeType) {
/*  82 */     this.sacrificeType = sacrificeType;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getSkills() {
/*  86 */     return this.skills;
/*     */   }
/*     */   
/*     */   public void setSkills(Map<Integer, Integer> skills) {
/*  90 */     this.skills = skills;
/*     */   }
/*     */   
/*     */   public boolean isListOpen() {
/*  94 */     return this.listOpen;
/*     */   }
/*     */   
/*     */   public void setListOpen(boolean listOpen) {
/*  98 */     this.listOpen = listOpen;
/*     */   }
/*     */   
/*     */   public HashMap<Integer, Integer> getPlayerRewards() {
/* 102 */     return this.playerRewards;
/*     */   }
/*     */   
/*     */   public void setPlayerRewards(HashMap<Integer, Integer> playerRewards) {
/* 106 */     this.playerRewards = playerRewards;
/*     */   }
/*     */   
/*     */   public int getFightTimes() {
/* 110 */     return this.fightTimes;
/*     */   }
/*     */   
/*     */   public void setFightTimes(int fightTimes) {
/* 114 */     this.fightTimes = fightTimes;
/*     */   }
/*     */   
/*     */   public int getLastResettime() {
/* 118 */     return this.lastResettime;
/*     */   }
/*     */   
/*     */   public void setLastResettime(int lastResettime) {
/* 122 */     this.lastResettime = lastResettime;
/*     */   }
/*     */   
/*     */   public int getTotalTimes() {
/* 126 */     return this.totalTimes;
/*     */   }
/*     */   
/*     */   public void setTotalTimes(int totalTimes) {
/* 130 */     this.totalTimes = totalTimes;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 135 */     return "GroupMemberEntity{playerId=" + this.playerId + ", id=" + this.id + ", position=" + this.position + ", applySet=" + this.applySet + ", totalOffer=" + this.totalOffer + ", sacrificeBox=" + this.sacrificeBox + ", sacrificeType=" + this.sacrificeType + ", skills=" + this.skills + ", listOpen=" + this.listOpen + ", playerRewards=" + this.playerRewards + ", fightTimes=" + this.fightTimes + ", lastResettime=" + this.lastResettime + ", totalTimes=" + this.totalTimes + '}';
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\group\GroupMemberEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */