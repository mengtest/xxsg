/*     */ package com.linlongyx.sanguo.webgame.common.team;
/*     */ 
/*     */ import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.fighter.IFighter;
/*     */ import com.linlongyx.sanguo.webgame.common.structure.Pair;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import java.util.concurrent.locks.Condition;
/*     */ import java.util.concurrent.locks.Lock;
/*     */ import java.util.concurrent.locks.ReentrantLock;
/*     */ import java.util.stream.Collectors;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ @JsonIgnoreProperties(ignoreUnknown = true)
/*     */ public class Team
/*     */   implements Lock {
/*     */   private long teamId;
/*     */   private long leaderId;
/*     */   private int firstHandVal;
/*     */   private int createtime;
/*     */   private volatile int status;
/*     */   
/*     */   public enum TeamStatus {
/*  27 */     CAN_JOIN(0),
/*  28 */     FULL(1),
/*  29 */     IN_FIGHT(2);
/*     */     
/*     */     private int value;
/*     */     
/*     */     TeamStatus(int value) {
/*  34 */       this.value = value;
/*     */     }
/*     */     
/*     */     public int getValue() {
/*  38 */       return this.value;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  47 */   private Lock lock = new ReentrantLock();
/*     */   
/*  49 */   private TeamPlayer[] teamPlayers = new TeamPlayer[3];
/*     */   
/*     */   public Team(long teamId, long leaderId) {
/*  52 */     this.teamId = teamId;
/*  53 */     this.leaderId = leaderId;
/*  54 */     this.status = TeamStatus.CAN_JOIN.getValue();
/*  55 */     this.createtime = TimeUtil.currentTime();
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
/*     */   public void setTeamPlayer(int index, boolean isRobot, long playerId, byte vip, byte sex, String head, String name, short level, long fightValue, int quality, int firstHandVal, IFighter[] fighters, IFighter petFighter, IFighter stageFighter, List<Pair<Integer, Integer>> candidateFighters, Pair<Integer, Integer> zhenfa) {
/*  71 */     if (fighters.length != 2) {
/*     */       return;
/*     */     }
/*     */     
/*  75 */     boolean isLeader = (this.leaderId == playerId);
/*  76 */     if (isLeader) {
/*  77 */       this.firstHandVal = firstHandVal;
/*     */     }
/*  79 */     TeamPlayer teamPlayer = new TeamPlayer(isRobot, playerId, vip, sex, head, name, level, fightValue, isLeader, index + 1, quality, fighters, petFighter, stageFighter, candidateFighters, zhenfa);
/*     */     
/*  81 */     setTeamPlayer(index, teamPlayer);
/*     */   }
/*     */   
/*     */   public void removeTeamPlayer(int index) {
/*  85 */     setTeamPlayer(index, null);
/*     */   }
/*     */   
/*     */   private void setTeamPlayer(int index, TeamPlayer teamPlayer) {
/*  89 */     if (index < 0 || index >= 3) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*  95 */       lock();
/*  96 */       this.teamPlayers[index] = teamPlayer;
/*     */     } finally {
/*  98 */       unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getPlayerSize() {
/*     */     try {
/* 104 */       lock();
/* 105 */       int count = 0;
/* 106 */       for (TeamPlayer teamPlayer : this.teamPlayers) {
/* 107 */         if (teamPlayer != null) {
/* 108 */           count++;
/*     */         }
/*     */       } 
/* 111 */       return count;
/*     */     } finally {
/* 113 */       unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getRealPlayerSize() {
/*     */     try {
/* 119 */       lock();
/* 120 */       int count = 0;
/* 121 */       for (TeamPlayer teamPlayer : this.teamPlayers) {
/* 122 */         if (teamPlayer != null && !teamPlayer.isRobot()) {
/* 123 */           count++;
/*     */         }
/*     */       } 
/* 126 */       return count;
/*     */     } finally {
/* 128 */       unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFull() {
/*     */     try {
/* 139 */       lock();
/* 140 */       boolean full = true;
/* 141 */       for (TeamPlayer teamPlayer : this.teamPlayers) {
/* 142 */         if (teamPlayer == null) {
/* 143 */           full = false;
/*     */           break;
/*     */         } 
/*     */       } 
/* 147 */       return full;
/*     */     } finally {
/* 149 */       unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*     */     try {
/* 155 */       lock();
/* 156 */       int count = 0;
/* 157 */       for (TeamPlayer teamPlayer : this.teamPlayers) {
/* 158 */         if (teamPlayer != null && !teamPlayer.isRobot()) {
/* 159 */           count++;
/*     */         }
/*     */       } 
/* 162 */       return (count == 0);
/*     */     } finally {
/* 164 */       unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isLeader(long playerId) {
/* 175 */     return (this.leaderId == playerId);
/*     */   }
/*     */   
/*     */   public TeamPlayer getTeamPlayerById(long playerId) {
/* 179 */     TeamPlayer target = null;
/* 180 */     for (TeamPlayer teamPlayer : this.teamPlayers) {
/* 181 */       if (teamPlayer != null && teamPlayer.getPlayerId() == playerId) {
/* 182 */         target = teamPlayer;
/*     */         break;
/*     */       } 
/*     */     } 
/* 186 */     return target;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int canJoin() {
/*     */     try {
/* 196 */       lock();
/* 197 */       int index = -1; int i;
/* 198 */       for (i = 0; i < this.teamPlayers.length; i++) {
/* 199 */         if (this.teamPlayers[i] == null) {
/* 200 */           index = i;
/*     */           break;
/*     */         } 
/*     */       } 
/* 204 */       i = index; return i;
/*     */     } finally {
/* 206 */       unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canEnterFight() {
/*     */     try {
/* 217 */       lock();
/* 218 */       boolean can = true;
/* 219 */       for (TeamPlayer teamPlayer : this.teamPlayers) {
/* 220 */         if (teamPlayer != null && !teamPlayer.isLeader() && !teamPlayer.isReady()) {
/* 221 */           can = false;
/*     */           break;
/*     */         } 
/*     */       } 
/* 225 */       return can;
/*     */     } finally {
/* 227 */       unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPlayerReady(long playerId, boolean isReady) {
/* 238 */     for (TeamPlayer teamPlayer : this.teamPlayers) {
/* 239 */       if (teamPlayer != null && !teamPlayer.isRobot() && teamPlayer.getPlayerId() == playerId) {
/* 240 */         teamPlayer.setReady(isReady);
/*     */         break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void lock() {
/* 248 */     this.lock.lock();
/*     */   }
/*     */ 
/*     */   
/*     */   public void lockInterruptibly() throws InterruptedException {
/* 253 */     this.lock.lockInterruptibly();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean tryLock() {
/* 258 */     return this.lock.tryLock();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean tryLock(long time, @NotNull TimeUnit unit) throws InterruptedException {
/* 263 */     if (SYNTHETIC_LOCAL_VARIABLE_3 == null) $$$reportNull$$$0(0);  return this.lock.tryLock(time, (TimeUnit)SYNTHETIC_LOCAL_VARIABLE_3);
/*     */   }
/*     */ 
/*     */   
/*     */   public void unlock() {
/* 268 */     this.lock.unlock();
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Condition newCondition() {
/* 274 */     if (this.lock.newCondition() == null) $$$reportNull$$$0(1);  return this.lock.newCondition();
/*     */   }
/*     */ 
/*     */   
/*     */   public long getTeamId() {
/* 279 */     return this.teamId;
/*     */   }
/*     */   
/*     */   public void setTeamId(long teamId) {
/* 283 */     this.teamId = teamId;
/*     */   }
/*     */   
/*     */   public long getLeaderId() {
/* 287 */     return this.leaderId;
/*     */   }
/*     */   
/*     */   public void setLeaderId(long leaderId) {
/* 291 */     this.leaderId = leaderId;
/*     */   }
/*     */   
/*     */   public int getStatus() {
/* 295 */     return this.status;
/*     */   }
/*     */   
/*     */   public void setStatus(int status) {
/* 299 */     this.status = status;
/*     */   }
/*     */   
/*     */   public List<TeamPlayer> getTeamPlayers() {
/* 303 */     return Collections.unmodifiableList((List<? extends TeamPlayer>)Arrays.<TeamPlayer>stream(this.teamPlayers).collect(Collectors.toList()));
/*     */   }
/*     */   
/*     */   public String getLeaderName() {
/* 307 */     if (this.teamPlayers[0] == null) {
/* 308 */       return "";
/*     */     }
/* 310 */     return this.teamPlayers[0].getName();
/*     */   }
/*     */   
/*     */   public TeamPlayer getLeader() {
/* 314 */     return this.teamPlayers[0];
/*     */   }
/*     */   
/*     */   public int getCreatetime() {
/* 318 */     return this.createtime;
/*     */   }
/*     */   
/*     */   public int getFirstHandVal() {
/* 322 */     return this.firstHandVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\common\team\Team.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */