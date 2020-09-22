/*     */ package com.linlongyx.sanguo.webgame.common.team;
/*     */ 
/*     */ import com.linlongyx.sanguo.webgame.common.fight.fighter.IFighter;
/*     */ import com.linlongyx.sanguo.webgame.common.structure.Pair;
/*     */ import java.io.Serializable;
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
/*     */ public class TeamPlayer
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
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
/*     */   private int fashionId;
/*     */   private final boolean isRobot;
/*     */   private List<Pair<Integer, Integer>> candidateFighters;
/*     */   private Pair<Integer, Integer> zhenfa;
/*  37 */   private TeamPlayerFighter[] fighters = new TeamPlayerFighter[2];
/*     */   
/*     */   private TeamPlayerFighter petFighter;
/*     */   
/*     */   private TeamPlayerFighter stageFighter;
/*     */ 
/*     */   
/*     */   public TeamPlayer(boolean isRobot, long playerId, byte vip, byte sex, String head, String name, short level, long fightValue, boolean isLeader, int pos, int quality, int fashionId, IFighter[] fighters, IFighter petFighter, IFighter stageFighter, List<Pair<Integer, Integer>> candidateFighters, Pair<Integer, Integer> zhenfa) {
/*  45 */     this.playerId = playerId;
/*  46 */     this.vip = vip;
/*  47 */     this.sex = sex;
/*  48 */     this.head = head;
/*  49 */     this.name = name;
/*  50 */     this.level = level;
/*  51 */     this.fightValue = fightValue;
/*  52 */     this.isLeader = isLeader;
/*  53 */     this.pos = pos;
/*  54 */     this.isRobot = isRobot;
/*  55 */     this.needReInit = false;
/*  56 */     this.quality = quality;
/*  57 */     this.fashionId = fashionId;
/*  58 */     this.candidateFighters = candidateFighters;
/*  59 */     this.zhenfa = zhenfa;
/*  60 */     if (isRobot) {
/*  61 */       this.ready = true;
/*     */     }
/*  63 */     for (int i = 0; i < fighters.length; i++) {
/*  64 */       if (fighters[i] != null) {
/*  65 */         this.fighters[i] = new TeamPlayerFighter((pos == 1 && i == 0), fighters[i]);
/*     */       }
/*     */     } 
/*  68 */     if (petFighter != null) {
/*  69 */       this.petFighter = new TeamPlayerFighter(false, petFighter);
/*     */     }
/*  71 */     if (stageFighter != null) {
/*  72 */       this.stageFighter = new TeamPlayerFighter(false, stageFighter);
/*     */     }
/*     */   }
/*     */   
/*     */   public long getPlayerId() {
/*  77 */     return this.playerId;
/*     */   }
/*     */   
/*     */   public void setPlayerId(long playerId) {
/*  81 */     this.playerId = playerId;
/*     */   }
/*     */   
/*     */   public String getHead() {
/*  85 */     return this.head;
/*     */   }
/*     */   
/*     */   public void setHead(String head) {
/*  89 */     this.head = head;
/*     */   }
/*     */   
/*     */   public String getName() {
/*  93 */     return this.name;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/*  97 */     this.name = name;
/*     */   }
/*     */   
/*     */   public short getLevel() {
/* 101 */     return this.level;
/*     */   }
/*     */   
/*     */   public void setLevel(short level) {
/* 105 */     this.level = level;
/*     */   }
/*     */   
/*     */   public long getFightValue() {
/* 109 */     return this.fightValue;
/*     */   }
/*     */   
/*     */   public void setFightValue(long fightValue) {
/* 113 */     this.fightValue = fightValue;
/*     */   }
/*     */   
/*     */   public boolean isLeader() {
/* 117 */     return this.isLeader;
/*     */   }
/*     */   
/*     */   public void setLeader(boolean leader) {
/* 121 */     this.isLeader = leader;
/*     */   }
/*     */   
/*     */   public boolean isReady() {
/* 125 */     return this.ready;
/*     */   }
/*     */   
/*     */   public void setReady(boolean ready) {
/* 129 */     this.ready = ready;
/*     */   }
/*     */   
/*     */   public TeamPlayerFighter[] getFighters() {
/* 133 */     return this.fighters;
/*     */   }
/*     */   
/*     */   public void setFighters(TeamPlayerFighter[] fighters) {
/* 137 */     this.fighters = fighters;
/*     */   }
/*     */   
/*     */   public int getPos() {
/* 141 */     return this.pos;
/*     */   }
/*     */   
/*     */   public void setPos(int pos) {
/* 145 */     this.pos = pos;
/*     */   }
/*     */   
/*     */   public boolean isRobot() {
/* 149 */     return this.isRobot;
/*     */   }
/*     */   
/*     */   public boolean isNeedReInit() {
/* 153 */     return this.needReInit;
/*     */   }
/*     */   
/*     */   public void setNeedReInit(boolean needReInit) {
/* 157 */     this.needReInit = needReInit;
/*     */   }
/*     */   
/*     */   public byte getVip() {
/* 161 */     return this.vip;
/*     */   }
/*     */   
/*     */   public void setVip(byte vip) {
/* 165 */     this.vip = vip;
/*     */   }
/*     */   
/*     */   public byte getSex() {
/* 169 */     return this.sex;
/*     */   }
/*     */   
/*     */   public void setSex(byte sex) {
/* 173 */     this.sex = sex;
/*     */   }
/*     */   
/*     */   public int getQuality() {
/* 177 */     return this.quality;
/*     */   }
/*     */   
/*     */   public void setQuality(int quality) {
/* 181 */     this.quality = quality;
/*     */   }
/*     */   
/*     */   public int getFashionId() {
/* 185 */     return this.fashionId;
/*     */   }
/*     */   
/*     */   public void setFashionId(int fashionId) {
/* 189 */     this.fashionId = fashionId;
/*     */   }
/*     */   
/*     */   public TeamPlayerFighter getPetFighter() {
/* 193 */     return this.petFighter;
/*     */   }
/*     */   
/*     */   public void setPetFighter(TeamPlayerFighter petFighter) {
/* 197 */     this.petFighter = petFighter;
/*     */   }
/*     */   
/*     */   public TeamPlayerFighter getStageFighter() {
/* 201 */     return this.stageFighter;
/*     */   }
/*     */   
/*     */   public void setStageFighter(TeamPlayerFighter stageFighter) {
/* 205 */     this.stageFighter = stageFighter;
/*     */   }
/*     */   
/*     */   public List<Pair<Integer, Integer>> getCandidateFighters() {
/* 209 */     return this.candidateFighters;
/*     */   }
/*     */   
/*     */   public void setCandidateFighters(List<Pair<Integer, Integer>> candidateFighters) {
/* 213 */     this.candidateFighters = candidateFighters;
/*     */   }
/*     */   
/*     */   public Pair<Integer, Integer> getZhenfa() {
/* 217 */     return this.zhenfa;
/*     */   }
/*     */   
/*     */   public void setZhenfa(Pair<Integer, Integer> zhenfa) {
/* 221 */     this.zhenfa = zhenfa;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\team\TeamPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */