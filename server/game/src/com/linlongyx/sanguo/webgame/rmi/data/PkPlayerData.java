/*     */ package com.linlongyx.sanguo.webgame.rmi.data;
/*     */ 
/*     */ import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.fighter.CommonFighterData;
/*     */ import com.linlongyx.sanguo.webgame.common.structure.Pair;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.ModelData;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @JsonIgnoreProperties(ignoreUnknown = true)
/*     */ public class PkPlayerData
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private long playerId;
/*     */   private String playerName;
/*     */   private String head;
/*     */   private int quality;
/*     */   private long fightValue;
/*     */   private int destinyPoint;
/*     */   private int firstHandVal;
/*     */   private ModelData modelData;
/*  30 */   private List<Pair<Integer, Integer>> candidateFighters = new ArrayList<>();
/*     */   @Deprecated
/*  32 */   private Map<Long, Map<Integer, Integer>> talismanMap = new HashMap<>();
/*     */   private Pair<Integer, Integer> zhenfa;
/*  34 */   private Map<Integer, CommonFighterData> fighterData = new HashMap<>();
/*     */   
/*  36 */   private Map<Integer, CommonFighterData> defaultFighterData = new HashMap<>();
/*     */ 
/*     */   
/*     */   public PkPlayerData() {}
/*     */   
/*     */   public PkPlayerData(long playerId, long fightValue) {
/*  42 */     this.playerId = playerId;
/*  43 */     this.fightValue = fightValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public long getPlayerId() {
/*  48 */     return this.playerId;
/*     */   }
/*     */   
/*     */   public void setPlayerId(long playerId) {
/*  52 */     this.playerId = playerId;
/*     */   }
/*     */   
/*     */   public long getFightValue() {
/*  56 */     return this.fightValue;
/*     */   }
/*     */   
/*     */   public void setFightValue(long fightValue) {
/*  60 */     this.fightValue = fightValue;
/*     */   }
/*     */   
/*     */   public Map<Integer, CommonFighterData> getFighterData() {
/*  64 */     return this.fighterData;
/*     */   }
/*     */   
/*     */   public void setFighterData(Map<Integer, CommonFighterData> fighterData) {
/*  68 */     this.fighterData = fighterData;
/*     */   }
/*     */   
/*     */   public Map<Integer, CommonFighterData> getDefaultFighterData() {
/*  72 */     return this.defaultFighterData;
/*     */   }
/*     */   
/*     */   public void setDefaultFighterData(Map<Integer, CommonFighterData> defaultFighterData) {
/*  76 */     this.defaultFighterData = defaultFighterData;
/*     */   }
/*     */   
/*     */   public String getPlayerName() {
/*  80 */     return this.playerName;
/*     */   }
/*     */   
/*     */   public void setPlayerName(String playerName) {
/*  84 */     this.playerName = playerName;
/*     */   }
/*     */   
/*     */   public int getDestinyPoint() {
/*  88 */     return this.destinyPoint;
/*     */   }
/*     */   
/*     */   public void setDestinyPoint(int destinyPoint) {
/*  92 */     this.destinyPoint = destinyPoint;
/*     */   }
/*     */   
/*     */   public ModelData getModelData() {
/*  96 */     return this.modelData;
/*     */   }
/*     */   
/*     */   public void setModelData(ModelData modelData) {
/* 100 */     this.modelData = modelData;
/*     */   }
/*     */   
/*     */   public String getHead() {
/* 104 */     return this.head;
/*     */   }
/*     */   
/*     */   public void setHead(String head) {
/* 108 */     this.head = head;
/*     */   }
/*     */   
/*     */   public int getQuality() {
/* 112 */     return this.quality;
/*     */   }
/*     */   
/*     */   public void setQuality(int quality) {
/* 116 */     this.quality = quality;
/*     */   }
/*     */   
/*     */   public List<Pair<Integer, Integer>> getCandidateFighters() {
/* 120 */     return this.candidateFighters;
/*     */   }
/*     */   
/*     */   public void setCandidateFighters(List<Pair<Integer, Integer>> candidateFighters) {
/* 124 */     this.candidateFighters = candidateFighters;
/*     */   }
/*     */   
/*     */   public Pair<Integer, Integer> getZhenfa() {
/* 128 */     return this.zhenfa;
/*     */   }
/*     */   
/*     */   public void setZhenfa(Pair<Integer, Integer> zhenfa) {
/* 132 */     this.zhenfa = zhenfa;
/*     */   }
/*     */   
/*     */   public Map<Long, Map<Integer, Integer>> getTalismanMap() {
/* 136 */     return this.talismanMap;
/*     */   }
/*     */   
/*     */   public void setTalismanMap(Map<Long, Map<Integer, Integer>> talismanMap) {
/* 140 */     this.talismanMap = talismanMap;
/*     */   }
/*     */   
/*     */   public int getFirstHandVal() {
/* 144 */     return this.firstHandVal;
/*     */   }
/*     */   
/*     */   public void setFirstHandVal(int firstHandVal) {
/* 148 */     this.firstHandVal = firstHandVal;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 153 */     return "PkPlayerData{playerId=" + this.playerId + ", playerName='" + this.playerName + '\'' + ", head='" + this.head + '\'' + ", quality=" + this.quality + ", fightValue=" + this.fightValue + ", destinyPoint=" + this.destinyPoint + ", firstHandVal=" + this.firstHandVal + ", modelData=" + this.modelData + ", candidateFighters=" + this.candidateFighters + ", zhenfa=" + this.zhenfa + ", talismanMap=" + this.talismanMap + ", fighterData=" + this.fighterData + ", defaultFighterData=" + this.defaultFighterData + '}';
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\rmi\data\PkPlayerData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */