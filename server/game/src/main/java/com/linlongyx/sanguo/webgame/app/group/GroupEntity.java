/*     */ package com.linlongyx.sanguo.webgame.app.group;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.annotation.Table;
/*     */ import com.linlongyx.core.framework.dao.annotation.TableField;
/*     */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.GroupDamageData;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.KeyValue;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ @Table(tableName = "tb_group", prefix = "group_%serverId_%playerId", keyField = "id")
/*     */ public class GroupEntity
/*     */   implements IMapEntity
/*     */ {
/*     */   @TableField(isKey = true)
/*     */   private final transient long id;
/*     */   private String groupName;
/*     */   private long leader;
/*  24 */   private List<Long> applyList = new ArrayList<>(); private String leaderName; private int level; private int rank; private String notice;
/*  25 */   private Set<Long> memberList = new HashSet<>();
/*  26 */   private Set<Long> vices = new HashSet<>();
/*     */   private int sacrificePoint;
/*     */   private int sacrificeNum;
/*     */   private long exp;
/*     */   private long dayExp;
/*  31 */   private HashMap<Long, KeyValue> officeList = new HashMap<>();
/*     */   
/*     */   private volatile int insId;
/*  34 */   private Map<Byte, Long> bossHp = new HashMap<>();
/*     */   private byte reset;
/*  36 */   private HashMap<Long, GroupDamageData> damageRecords = new HashMap<>();
/*     */   private int autoAdd;
/*     */   private int nextRecruitTime;
/*     */   
/*     */   public GroupEntity(long id) {
/*  41 */     this.id = id;
/*     */   }
/*     */   
/*     */   public long getId() {
/*  45 */     return this.id;
/*     */   }
/*     */   
/*     */   public String getGroupName() {
/*  49 */     return this.groupName;
/*     */   }
/*     */   
/*     */   public void setGroupName(String groupName) {
/*  53 */     this.groupName = groupName;
/*     */   }
/*     */   
/*     */   public long getLeader() {
/*  57 */     return this.leader;
/*     */   }
/*     */   
/*     */   public void setLeader(long leader) {
/*  61 */     this.leader = leader;
/*     */   }
/*     */   
/*     */   public String getLeaderName() {
/*  65 */     return this.leaderName;
/*     */   }
/*     */   
/*     */   public void setLeaderName(String leaderName) {
/*  69 */     this.leaderName = leaderName;
/*     */   }
/*     */   
/*     */   public int getLevel() {
/*  73 */     return this.level;
/*     */   }
/*     */   
/*     */   public void setLevel(int level) {
/*  77 */     this.level = level;
/*     */   }
/*     */   
/*     */   public int getRank() {
/*  81 */     return this.rank;
/*     */   }
/*     */   
/*     */   public void setRank(int rank) {
/*  85 */     this.rank = rank;
/*     */   }
/*     */   
/*     */   public String getNotice() {
/*  89 */     return this.notice;
/*     */   }
/*     */   
/*     */   public void setNotice(String notice) {
/*  93 */     this.notice = notice;
/*     */   }
/*     */   
/*     */   public List<Long> getApplyList() {
/*  97 */     return this.applyList;
/*     */   }
/*     */   
/*     */   public void setApplyList(List<Long> applyList) {
/* 101 */     this.applyList = applyList;
/*     */   }
/*     */   
/*     */   public Set<Long> getMemberList() {
/* 105 */     return this.memberList;
/*     */   }
/*     */   
/*     */   public void setMemberList(Set<Long> memberList) {
/* 109 */     this.memberList = memberList;
/*     */   }
/*     */   
/*     */   public Set<Long> getVices() {
/* 113 */     return this.vices;
/*     */   }
/*     */   
/*     */   public void setVices(Set<Long> vices) {
/* 117 */     this.vices = vices;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSacrificePoint() {
/* 122 */     return this.sacrificePoint;
/*     */   }
/*     */   
/*     */   public void setSacrificePoint(int sacrificePoint) {
/* 126 */     this.sacrificePoint = sacrificePoint;
/*     */   }
/*     */   
/*     */   public int getSacrificeNum() {
/* 130 */     return this.sacrificeNum;
/*     */   }
/*     */   
/*     */   public void setSacrificeNum(int sacrificeNum) {
/* 134 */     this.sacrificeNum = sacrificeNum;
/*     */   }
/*     */   
/*     */   public long getExp() {
/* 138 */     return this.exp;
/*     */   }
/*     */   
/*     */   public void setExp(long exp) {
/* 142 */     this.exp = exp;
/*     */   }
/*     */   
/*     */   public long getDayExp() {
/* 146 */     return this.dayExp;
/*     */   }
/*     */   
/*     */   public void setDayExp(long dayExp) {
/* 150 */     this.dayExp = dayExp;
/*     */   }
/*     */   
/*     */   public HashMap<Long, KeyValue> getOfficeList() {
/* 154 */     return this.officeList;
/*     */   }
/*     */   
/*     */   public void setOfficeList(HashMap<Long, KeyValue> officeList) {
/* 158 */     this.officeList = officeList;
/*     */   }
/*     */   
/*     */   public int getInsId() {
/* 162 */     return this.insId;
/*     */   }
/*     */   
/*     */   public void setInsId(int insId) {
/* 166 */     this.insId = insId;
/*     */   }
/*     */   
/*     */   public Map<Byte, Long> getBossHp() {
/* 170 */     return this.bossHp;
/*     */   }
/*     */   
/*     */   public void setBossHp(Map<Byte, Long> bossHp) {
/* 174 */     this.bossHp = bossHp;
/*     */   }
/*     */   
/*     */   public HashMap<Long, GroupDamageData> getDamageRecords() {
/* 178 */     return this.damageRecords;
/*     */   }
/*     */   
/*     */   public void setDamageRecords(HashMap<Long, GroupDamageData> damageRecords) {
/* 182 */     this.damageRecords = damageRecords;
/*     */   }
/*     */   
/*     */   public byte getReset() {
/* 186 */     return this.reset;
/*     */   }
/*     */   
/*     */   public void setReset(byte reset) {
/* 190 */     this.reset = reset;
/*     */   }
/*     */   
/*     */   public int getAutoAdd() {
/* 194 */     return this.autoAdd;
/*     */   }
/*     */   
/*     */   public void setAutoAdd(int autoAdd) {
/* 198 */     this.autoAdd = autoAdd;
/*     */   }
/*     */   
/*     */   public int getNextRecruitTime() {
/* 202 */     return this.nextRecruitTime;
/*     */   }
/*     */   
/*     */   public void setNextRecruitTime(int nextRecruitTime) {
/* 206 */     this.nextRecruitTime = nextRecruitTime;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 211 */     return "GroupEntity{id=" + this.id + ", groupName='" + this.groupName + '\'' + ", leader=" + this.leader + ", leaderName='" + this.leaderName + '\'' + ", level=" + this.level + ", rank=" + this.rank + ", notice='" + this.notice + '\'' + ", applyList=" + this.applyList + ", memberList=" + this.memberList + ", vices=" + this.vices + ", sacrificePoint=" + this.sacrificePoint + ", sacrificeNum=" + this.sacrificeNum + ", exp=" + this.exp + ", dayExp=" + this.dayExp + ", officeList=" + this.officeList + ", insId=" + this.insId + ", bossHp=" + this.bossHp + ", damageRecords=" + this.damageRecords + ", autoAdd=" + this.autoAdd + ", nextRecruitTime=" + this.nextRecruitTime + '}';
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
/* 237 */     return Long.valueOf(getId());
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\group\GroupEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */