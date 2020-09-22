/*     */ package com.linlongyx.sanguo.webgame.rmi.data;
/*     */ 
/*     */ import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
/*  42 */   private Map<Long, Map<Integer, Integer>> talismanMap = new HashMap<>();
/*     */   
/*     */   private List<Pair<Integer, Integer>> candidateFighters;
/*     */   
/*     */   private Map<Integer, CommonFighterData> fighters;
/*     */ 
/*     */   
/*     */   public static long getSerialVersionUID() {
/*  50 */     return 1L;
/*     */   }
/*     */   
/*     */   public long getPlayerId() {
/*  54 */     return this.playerId;
/*     */   }
/*     */   
/*     */   public void setPlayerId(long playerId) {
/*  58 */     this.playerId = playerId;
/*     */   }
/*     */   
/*     */   public long getUserId() {
/*  62 */     return this.userId;
/*     */   }
/*     */   
/*     */   public void setUserId(long userId) {
/*  66 */     this.userId = userId;
/*     */   }
/*     */   
/*     */   public String getPlayerName() {
/*  70 */     return this.playerName;
/*     */   }
/*     */   
/*     */   public void setPlayerName(String playerName) {
/*  74 */     this.playerName = playerName;
/*     */   }
/*     */   
/*     */   public short getLevel() {
/*  78 */     return this.level;
/*     */   }
/*     */   
/*     */   public void setLevel(short level) {
/*  82 */     this.level = level;
/*     */   }
/*     */   
/*     */   public byte getSex() {
/*  86 */     return this.sex;
/*     */   }
/*     */   
/*     */   public void setSex(byte sex) {
/*  90 */     this.sex = sex;
/*     */   }
/*     */   
/*     */   public String getHead() {
/*  94 */     return this.head;
/*     */   }
/*     */   
/*     */   public void setHead(String head) {
/*  98 */     this.head = head;
/*     */   }
/*     */   
/*     */   public String getNickname() {
/* 102 */     return this.nickname;
/*     */   }
/*     */   
/*     */   public void setNickname(String nickname) {
/* 106 */     this.nickname = nickname;
/*     */   }
/*     */   
/*     */   public long getFightValue() {
/* 110 */     return this.fightValue;
/*     */   }
/*     */   
/*     */   public void setFightValue(long fightValue) {
/* 114 */     this.fightValue = fightValue;
/*     */   }
/*     */   
/*     */   public ModelData getModelData() {
/* 118 */     return this.modelData;
/*     */   }
/*     */   
/*     */   public void setModelData(ModelData modelData) {
/* 122 */     this.modelData = modelData;
/*     */   }
/*     */   
/*     */   public int getServerId() {
/* 126 */     return this.serverId;
/*     */   }
/*     */   
/*     */   public void setServerId(int serverId) {
/* 130 */     this.serverId = serverId;
/*     */   }
/*     */   
/*     */   public int getDestinyStone() {
/* 134 */     return this.destinyStone;
/*     */   }
/*     */   
/*     */   public void setDestinyStone(int destinyStone) {
/* 138 */     this.destinyStone = destinyStone;
/*     */   }
/*     */   
/*     */   public int getDestinyPoint() {
/* 142 */     return this.destinyPoint;
/*     */   }
/*     */   
/*     */   public void setDestinyPoint(int destinyPoint) {
/* 146 */     this.destinyPoint = destinyPoint;
/*     */   }
/*     */   
/*     */   public short getVip() {
/* 150 */     return this.vip;
/*     */   }
/*     */   
/*     */   public void setVip(short vip) {
/* 154 */     this.vip = vip;
/*     */   }
/*     */   
/*     */   public Map<Integer, CommonFighterData> getFighters() {
/* 158 */     return this.fighters;
/*     */   }
/*     */   
/*     */   public void setFighters(Map<Integer, CommonFighterData> fighters) {
/* 162 */     this.fighters = fighters;
/*     */   }
/*     */   
/*     */   public int getQuality() {
/* 166 */     return this.quality;
/*     */   }
/*     */   
/*     */   public void setQuality(int quality) {
/* 170 */     this.quality = quality;
/*     */   }
/*     */   
/*     */   public int getTimestamp() {
/* 174 */     return this.timestamp;
/*     */   }
/*     */   
/*     */   public void setTimestamp(int timestamp) {
/* 178 */     this.timestamp = timestamp;
/*     */   }
/*     */   
/*     */   public int getFirstHandVal() {
/* 182 */     return this.firstHandVal;
/*     */   }
/*     */   
/*     */   public void setFirstHandVal(int firstHandVal) {
/* 186 */     this.firstHandVal = firstHandVal;
/*     */   }
/*     */   
/*     */   public List<Pair<Integer, Integer>> getCandidateFighters() {
/* 190 */     return this.candidateFighters;
/*     */   }
/*     */   
/*     */   public void setCandidateFighters(List<Pair<Integer, Integer>> candidateFighters) {
/* 194 */     this.candidateFighters = candidateFighters;
/*     */   }
/*     */   
/*     */   public Pair<Integer, Integer> getZhenfa() {
/* 198 */     return this.zhenfa;
/*     */   }
/*     */   
/*     */   public void setZhenfa(Pair<Integer, Integer> zhenfa) {
/* 202 */     this.zhenfa = zhenfa;
/*     */   }
/*     */   
/*     */   public Map<Long, Map<Integer, Integer>> getTalismanMap() {
/* 206 */     return this.talismanMap;
/*     */   }
/*     */   
/*     */   public void setTalismanMap(Map<Long, Map<Integer, Integer>> talismanMap) {
/* 210 */     this.talismanMap = talismanMap;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 215 */     return "DestinyPlayerData{userId=" + this.userId + ", serverId=" + this.serverId + ", playerId=" + this.playerId + ", playerName='" + this.playerName + '\'' + ", level=" + this.level + ", sex=" + this.sex + ", head='" + this.head + '\'' + ", nickname='" + this.nickname + '\'' + ", vip=" + this.vip + ", fightValue=" + this.fightValue + ", modelData=" + this.modelData + ", destinyStone=" + this.destinyStone + ", destinyPoint=" + this.destinyPoint + ", quality=" + this.quality + ", firstHandVal=" + this.firstHandVal + ", timestamp=" + this.timestamp + ", zhenfa=" + this.zhenfa + ", talismanMap=" + this.talismanMap + ", candidateFighters=" + this.candidateFighters + ", fighters=" + this.fighters + '}';
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\rmi\data\DestinyPlayerData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */