/*     */ package com.linlongyx.sanguo.webgame.common.team;
/*     */ 
/*     */ import com.fasterxml.jackson.annotation.JsonIgnore;
/*     */ import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.fighter.IFighter;
/*     */ import com.linlongyx.sanguo.webgame.common.structure.Pair;
/*     */ import java.util.List;
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
/*     */ @JsonIgnoreProperties(ignoreUnknown = true)
/*     */ public class TeamPlayer
/*     */ {
/*     */   private long playerId;
/*     */   private int pos;
/*     */   private String head;
/*     */   private String name;
/*     */   private short level;
/*     */   private long fightValue;
/*     */   private byte vip;
/*     */   private byte sex;
/*     */   private boolean isLeader;
/*     */   private volatile boolean ready;
/*     */   private boolean needReInit;
/*     */   private int quality;
/*     */   private final boolean isRobot;
/*     */   private List<Pair<Integer, Integer>> candidateFighters;
/*     */   private Pair<Integer, Integer> zhenfa;
/*     */   @JsonIgnore
/*  38 */   private TeamPlayerFighter[] fighters = new TeamPlayerFighter[2];
/*     */   
/*     */   @JsonIgnore
/*     */   private TeamPlayerFighter petFighter;
/*     */   
/*     */   @JsonIgnore
/*     */   private TeamPlayerFighter stageFighter;
/*     */ 
/*     */   
/*     */   public TeamPlayer(boolean isRobot, long playerId, byte vip, byte sex, String head, String name, short level, long fightValue, boolean isLeader, int pos, int quality, IFighter[] fighters, IFighter petFighter, IFighter stageFighter, List<Pair<Integer, Integer>> candidateFighters, Pair<Integer, Integer> zhenfa) {
/*  48 */     this.playerId = playerId;
/*  49 */     this.vip = vip;
/*  50 */     this.sex = sex;
/*  51 */     this.head = head;
/*  52 */     this.name = name;
/*  53 */     this.level = level;
/*  54 */     this.fightValue = fightValue;
/*  55 */     this.isLeader = isLeader;
/*  56 */     this.pos = pos;
/*  57 */     this.isRobot = isRobot;
/*  58 */     this.needReInit = false;
/*  59 */     this.quality = quality;
/*  60 */     this.candidateFighters = candidateFighters;
/*  61 */     this.zhenfa = zhenfa;
/*  62 */     if (isRobot) {
/*  63 */       this.ready = true;
/*     */     }
/*  65 */     for (int i = 0; i < fighters.length; i++) {
/*  66 */       if (fighters[i] != null) {
/*  67 */         this.fighters[i] = new TeamPlayerFighter((pos == 1 && i == 0), fighters[i]);
/*     */       }
/*     */     } 
/*  70 */     if (petFighter != null) {
/*  71 */       this.petFighter = new TeamPlayerFighter(false, petFighter);
/*     */     }
/*  73 */     if (stageFighter != null) {
/*  74 */       this.stageFighter = new TeamPlayerFighter(false, stageFighter);
/*     */     }
/*     */   }
/*     */   
/*     */   public long getPlayerId() {
/*  79 */     return this.playerId;
/*     */   }
/*     */   
/*     */   public void setPlayerId(long playerId) {
/*  83 */     this.playerId = playerId;
/*     */   }
/*     */   
/*     */   public String getHead() {
/*  87 */     return this.head;
/*     */   }
/*     */   
/*     */   public void setHead(String head) {
/*  91 */     this.head = head;
/*     */   }
/*     */   
/*     */   public String getName() {
/*  95 */     return this.name;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/*  99 */     this.name = name;
/*     */   }
/*     */   
/*     */   public short getLevel() {
/* 103 */     return this.level;
/*     */   }
/*     */   
/*     */   public void setLevel(short level) {
/* 107 */     this.level = level;
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
/*     */   public boolean isLeader() {
/* 119 */     return this.isLeader;
/*     */   }
/*     */   
/*     */   public void setLeader(boolean leader) {
/* 123 */     this.isLeader = leader;
/*     */   }
/*     */   
/*     */   public boolean isReady() {
/* 127 */     return this.ready;
/*     */   }
/*     */   
/*     */   public void setReady(boolean ready) {
/* 131 */     this.ready = ready;
/*     */   }
/*     */   
/*     */   public TeamPlayerFighter[] getFighters() {
/* 135 */     return this.fighters;
/*     */   }
/*     */   
/*     */   public void setFighters(TeamPlayerFighter[] fighters) {
/* 139 */     this.fighters = fighters;
/*     */   }
/*     */   
/*     */   public int getPos() {
/* 143 */     return this.pos;
/*     */   }
/*     */   
/*     */   public void setPos(int pos) {
/* 147 */     this.pos = pos;
/*     */   }
/*     */   
/*     */   public boolean isRobot() {
/* 151 */     return this.isRobot;
/*     */   }
/*     */   
/*     */   public boolean isNeedReInit() {
/* 155 */     return this.needReInit;
/*     */   }
/*     */   
/*     */   public void setNeedReInit(boolean needReInit) {
/* 159 */     this.needReInit = needReInit;
/*     */   }
/*     */   
/*     */   public byte getVip() {
/* 163 */     return this.vip;
/*     */   }
/*     */   
/*     */   public void setVip(byte vip) {
/* 167 */     this.vip = vip;
/*     */   }
/*     */   
/*     */   public byte getSex() {
/* 171 */     return this.sex;
/*     */   }
/*     */   
/*     */   public void setSex(byte sex) {
/* 175 */     this.sex = sex;
/*     */   }
/*     */   
/*     */   public int getQuality() {
/* 179 */     return this.quality;
/*     */   }
/*     */   
/*     */   public void setQuality(int quality) {
/* 183 */     this.quality = quality;
/*     */   }
/*     */   
/*     */   public TeamPlayerFighter getPetFighter() {
/* 187 */     return this.petFighter;
/*     */   }
/*     */   
/*     */   public void setPetFighter(TeamPlayerFighter petFighter) {
/* 191 */     this.petFighter = petFighter;
/*     */   }
/*     */   
/*     */   public TeamPlayerFighter getStageFighter() {
/* 195 */     return this.stageFighter;
/*     */   }
/*     */   
/*     */   public void setStageFighter(TeamPlayerFighter stageFighter) {
/* 199 */     this.stageFighter = stageFighter;
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
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\common\team\TeamPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */