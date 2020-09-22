/*     */ package com.linlongyx.sanguo.webgame.rmi.data;
/*     */ 
/*     */ import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.fighter.CommonFighterData;
/*     */ import com.linlongyx.sanguo.webgame.common.structure.ODTime;
/*     */ import com.linlongyx.sanguo.webgame.common.structure.Pair;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.ModelData;
/*     */ import java.io.Serializable;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @JsonIgnoreProperties(ignoreUnknown = true)
/*     */ public class PlayerData
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private long playerId;
/*     */   private long userId;
/*     */   private String playerName;
/*     */   private short level;
/*     */   private byte sex;
/*     */   private String head;
/*     */   private byte vip;
/*     */   private int titleId;
/*     */   private long blocId;
/*     */   private String blocName;
/*     */   public int quality;
/*     */   public int firstHandVal;
/*     */   private long fightValue;
/*     */   private long totalValue;
/*     */   private ModelData modelData;
/*     */   private int serverId;
/*  40 */   private Map<Integer, ODTime> status = new HashMap<>();
/*     */ 
/*     */   
/*     */   private Pair<Integer, Integer> zhenfa;
/*     */   
/*     */   private List<Pair<Integer, Integer>> candidateFighters;
/*     */   
/*     */   private Map<Integer, CommonFighterData> fighters;
/*     */   
/*     */   @Deprecated
/*  50 */   private Map<Long, Map<Integer, Integer>> talismanMap = new HashMap<>();
/*     */ 
/*     */   
/*     */   public int getServerId() {
/*  54 */     return this.serverId;
/*     */   }
/*     */   
/*     */   public void setServerId(int serverId) {
/*  58 */     this.serverId = serverId;
/*     */   }
/*     */   
/*     */   public ModelData getModelData() {
/*  62 */     return this.modelData;
/*     */   }
/*     */   
/*     */   public void setModelData(ModelData modelData) {
/*  66 */     this.modelData = modelData;
/*     */   }
/*     */ 
/*     */   
/*     */   public long getFightValue() {
/*  71 */     return this.fightValue;
/*     */   }
/*     */   
/*     */   public void setFightValue(long fightValue) {
/*  75 */     this.fightValue = fightValue;
/*     */   }
/*     */   
/*     */   public static long getSerialVersionUID() {
/*  79 */     return 1L;
/*     */   }
/*     */   
/*     */   public long getPlayerId() {
/*  83 */     return this.playerId;
/*     */   }
/*     */   
/*     */   public void setPlayerId(long playerId) {
/*  87 */     this.playerId = playerId;
/*     */   }
/*     */   
/*     */   public long getUserId() {
/*  91 */     return this.userId;
/*     */   }
/*     */   
/*     */   public void setUserId(long userId) {
/*  95 */     this.userId = userId;
/*     */   }
/*     */   
/*     */   public String getPlayerName() {
/*  99 */     return this.playerName;
/*     */   }
/*     */   
/*     */   public void setPlayerName(String playerName) {
/* 103 */     this.playerName = playerName;
/*     */   }
/*     */   
/*     */   public short getLevel() {
/* 107 */     return this.level;
/*     */   }
/*     */   
/*     */   public void setLevel(short level) {
/* 111 */     this.level = level;
/*     */   }
/*     */   
/*     */   public byte getSex() {
/* 115 */     return this.sex;
/*     */   }
/*     */   
/*     */   public void setSex(byte sex) {
/* 119 */     this.sex = sex;
/*     */   }
/*     */   
/*     */   public String getHead() {
/* 123 */     return this.head;
/*     */   }
/*     */   
/*     */   public void setHead(String head) {
/* 127 */     this.head = head;
/*     */   }
/*     */   
/*     */   public byte getVip() {
/* 131 */     return this.vip;
/*     */   }
/*     */   
/*     */   public void setVip(byte vip) {
/* 135 */     this.vip = vip;
/*     */   }
/*     */   
/*     */   public int getTitleId() {
/* 139 */     return this.titleId;
/*     */   }
/*     */   
/*     */   public void setTitleId(int titleId) {
/* 143 */     this.titleId = titleId;
/*     */   }
/*     */   
/*     */   public long getBlocId() {
/* 147 */     return this.blocId;
/*     */   }
/*     */   
/*     */   public void setBlocId(long blocId) {
/* 151 */     this.blocId = blocId;
/*     */   }
/*     */   
/*     */   public String getBlocName() {
/* 155 */     return this.blocName;
/*     */   }
/*     */   
/*     */   public void setBlocName(String blocName) {
/* 159 */     this.blocName = blocName;
/*     */   }
/*     */   
/*     */   public int getQuality() {
/* 163 */     return this.quality;
/*     */   }
/*     */   
/*     */   public void setQuality(int quality) {
/* 167 */     this.quality = quality;
/*     */   }
/*     */   
/*     */   public Map<Integer, CommonFighterData> getFighters() {
/* 171 */     return this.fighters;
/*     */   }
/*     */   
/*     */   public void setFighters(Map<Integer, CommonFighterData> fighters) {
/* 175 */     this.fighters = fighters;
/*     */   }
/*     */   
/*     */   public int getFirstHandVal() {
/* 179 */     return this.firstHandVal;
/*     */   }
/*     */   
/*     */   public void setFirstHandVal(int firstHandVal) {
/* 183 */     this.firstHandVal = firstHandVal;
/*     */   }
/*     */   
/*     */   public Map<Integer, ODTime> getStatus() {
/* 187 */     return this.status;
/*     */   }
/*     */   
/*     */   public void setStatus(Map<Integer, ODTime> status) {
/* 191 */     this.status = status;
/*     */   }
/*     */   
/*     */   public long getTotalValue() {
/* 195 */     return this.totalValue;
/*     */   }
/*     */   
/*     */   public void setTotalValue(long totalValue) {
/* 199 */     this.totalValue = totalValue;
/*     */   }
/*     */   
/*     */   public List<Pair<Integer, Integer>> getCandidateFighters() {
/* 203 */     return this.candidateFighters;
/*     */   }
/*     */   
/*     */   public void setCandidateFighters(List<Pair<Integer, Integer>> candidateFighters) {
/* 207 */     this.candidateFighters = candidateFighters;
/*     */   }
/*     */   
/*     */   public Pair<Integer, Integer> getZhenfa() {
/* 211 */     return this.zhenfa;
/*     */   }
/*     */   
/*     */   public void setZhenfa(Pair<Integer, Integer> zhenfa) {
/* 215 */     this.zhenfa = zhenfa;
/*     */   }
/*     */   
/*     */   public Map<Long, Map<Integer, Integer>> getTalismanMap() {
/* 219 */     return this.talismanMap;
/*     */   }
/*     */   
/*     */   public void setTalismanMap(Map<Long, Map<Integer, Integer>> talismanMap) {
/* 223 */     this.talismanMap = talismanMap;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 228 */     return "PlayerData{playerId=" + this.playerId + ", userId=" + this.userId + ", playerName='" + this.playerName + '\'' + ", level=" + this.level + ", sex=" + this.sex + ", head='" + this.head + '\'' + ", vip=" + this.vip + ", titleId=" + this.titleId + ", blocId=" + this.blocId + ", blocName='" + this.blocName + '\'' + ", quality=" + this.quality + ", fightValue=" + this.fightValue + ", totalValue=" + this.totalValue + ", modelData=" + this.modelData + ", serverId=" + this.serverId + ", firstHandVal=" + this.firstHandVal + ", zhenfa=" + this.zhenfa + ", talismanMap=" + this.talismanMap + ", candidateFighters=" + this.candidateFighters + ", fighters=" + this.fighters + ", status=" + this.status + '}';
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\rmi\data\PlayerData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */