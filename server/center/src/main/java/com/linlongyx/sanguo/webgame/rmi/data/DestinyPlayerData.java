/*     */ package com.linlongyx.sanguo.webgame.rmi.data;
/*     */ 
/*     */ import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.fighter.CommonFighterData;
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
/*     */ 
/*     */ @JsonIgnoreProperties(ignoreUnknown = true)
/*     */ public class DestinyPlayerData
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private long userId;
/*     */   private int serverId;
/*     */   private long playerId;
/*     */   private String playerName;
/*     */   private short level;
/*     */   private byte sex;
/*     */   private String head;
/*     */   private String nickname;
/*     */   private short vip;
/*     */   private int firstHandVal;
/*     */   private long fightValue;
/*     */   private ModelData modelData;
/*     */   private int destinyStone;
/*     */   private int destinyPoint;
/*     */   private int quality;
/*     */   private int timestamp;
/*     */   private Pair<Integer, Integer> zhenfa;
/*     */   @Deprecated
/*  43 */   private Map<Long, Map<Integer, Integer>> talismanMap = new HashMap<>();
/*     */   
/*     */   private List<Pair<Integer, Integer>> candidateFighters;
/*     */   
/*     */   private Map<Integer, CommonFighterData> fighters;
/*     */ 
/*     */   
/*     */   public static long getSerialVersionUID() {
/*  51 */     return 1L;
/*     */   }
/*     */   
/*     */   public long getPlayerId() {
/*  55 */     return this.playerId;
/*     */   }
/*     */   
/*     */   public void setPlayerId(long playerId) {
/*  59 */     this.playerId = playerId;
/*     */   }
/*     */   
/*     */   public long getUserId() {
/*  63 */     return this.userId;
/*     */   }
/*     */   
/*     */   public void setUserId(long userId) {
/*  67 */     this.userId = userId;
/*     */   }
/*     */   
/*     */   public String getPlayerName() {
/*  71 */     return this.playerName;
/*     */   }
/*     */   
/*     */   public void setPlayerName(String playerName) {
/*  75 */     this.playerName = playerName;
/*     */   }
/*     */   
/*     */   public short getLevel() {
/*  79 */     return this.level;
/*     */   }
/*     */   
/*     */   public void setLevel(short level) {
/*  83 */     this.level = level;
/*     */   }
/*     */   
/*     */   public byte getSex() {
/*  87 */     return this.sex;
/*     */   }
/*     */   
/*     */   public void setSex(byte sex) {
/*  91 */     this.sex = sex;
/*     */   }
/*     */   
/*     */   public String getHead() {
/*  95 */     return this.head;
/*     */   }
/*     */   
/*     */   public void setHead(String head) {
/*  99 */     this.head = head;
/*     */   }
/*     */   
/*     */   public String getNickname() {
/* 103 */     return this.nickname;
/*     */   }
/*     */   
/*     */   public void setNickname(String nickname) {
/* 107 */     this.nickname = nickname;
/*     */   }
/*     */   
/*     */   public long getFightValue() {
/* 111 */     return this.fightValue;
/*     */   }
/*     */   
/*     */   public void setFightValue(long fightValue) {
/* 115 */     this.fightValue = fightValue;
/*     */   }
/*     */   
/*     */   public ModelData getModelData() {
/* 119 */     return this.modelData;
/*     */   }
/*     */   
/*     */   public void setModelData(ModelData modelData) {
/* 123 */     this.modelData = modelData;
/*     */   }
/*     */   
/*     */   public int getServerId() {
/* 127 */     return this.serverId;
/*     */   }
/*     */   
/*     */   public void setServerId(int serverId) {
/* 131 */     this.serverId = serverId;
/*     */   }
/*     */   
/*     */   public int getDestinyStone() {
/* 135 */     return this.destinyStone;
/*     */   }
/*     */   
/*     */   public void setDestinyStone(int destinyStone) {
/* 139 */     this.destinyStone = destinyStone;
/*     */   }
/*     */   
/*     */   public int getDestinyPoint() {
/* 143 */     return this.destinyPoint;
/*     */   }
/*     */   
/*     */   public void setDestinyPoint(int destinyPoint) {
/* 147 */     this.timestamp = TimeUtil.currentTime();
/* 148 */     this.destinyPoint = destinyPoint;
/*     */   }
/*     */   
/*     */   public short getVip() {
/* 152 */     return this.vip;
/*     */   }
/*     */   
/*     */   public void setVip(short vip) {
/* 156 */     this.vip = vip;
/*     */   }
/*     */   
/*     */   public Map<Integer, CommonFighterData> getFighters() {
/* 160 */     return this.fighters;
/*     */   }
/*     */   
/*     */   public void setFighters(Map<Integer, CommonFighterData> fighters) {
/* 164 */     this.fighters = fighters;
/*     */   }
/*     */   
/*     */   public int getQuality() {
/* 168 */     return this.quality;
/*     */   }
/*     */   
/*     */   public void setQuality(int quality) {
/* 172 */     this.quality = quality;
/*     */   }
/*     */   
/*     */   public int getTimestamp() {
/* 176 */     return this.timestamp;
/*     */   }
/*     */   
/*     */   public void setTimestamp(int timestamp) {
/* 180 */     this.timestamp = timestamp;
/*     */   }
/*     */   
/*     */   public int getFirstHandVal() {
/* 184 */     return this.firstHandVal;
/*     */   }
/*     */   
/*     */   public void setFirstHandVal(int firstHandVal) {
/* 188 */     this.firstHandVal = firstHandVal;
/*     */   }
/*     */   
/*     */   public List<Pair<Integer, Integer>> getCandidateFighters() {
/* 192 */     return this.candidateFighters;
/*     */   }
/*     */   
/*     */   public void setCandidateFighters(List<Pair<Integer, Integer>> candidateFighters) {
/* 196 */     this.candidateFighters = candidateFighters;
/*     */   }
/*     */   
/*     */   public Pair<Integer, Integer> getZhenfa() {
/* 200 */     return this.zhenfa;
/*     */   }
/*     */   
/*     */   public void setZhenfa(Pair<Integer, Integer> zhenfa) {
/* 204 */     this.zhenfa = zhenfa;
/*     */   }
/*     */   
/*     */   public Map<Long, Map<Integer, Integer>> getTalismanMap() {
/* 208 */     return this.talismanMap;
/*     */   }
/*     */   
/*     */   public void setTalismanMap(Map<Long, Map<Integer, Integer>> talismanMap) {
/* 212 */     this.talismanMap = talismanMap;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 217 */     return "DestinyPlayerData{userId=" + this.userId + ", serverId=" + this.serverId + ", playerId=" + this.playerId + ", playerName='" + this.playerName + '\'' + ", level=" + this.level + ", sex=" + this.sex + ", head='" + this.head + '\'' + ", nickname='" + this.nickname + '\'' + ", vip=" + this.vip + ", fightValue=" + this.fightValue + ", modelData=" + this.modelData + ", destinyStone=" + this.destinyStone + ", destinyPoint=" + this.destinyPoint + ", quality=" + this.quality + ", firstHandVal=" + this.firstHandVal + ", timestamp=" + this.timestamp + ", zhenfa=" + this.zhenfa + ", talismanMap=" + this.talismanMap + ", candidateFighters=" + this.candidateFighters + ", fighters=" + this.fighters + '}';
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\rmi\data\DestinyPlayerData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */