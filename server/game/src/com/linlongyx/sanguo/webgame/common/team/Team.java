/*     */ package com.linlongyx.sanguo.webgame.common.team;
/*     */ 
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.fighter.IFighter;
/*     */ import com.linlongyx.sanguo.webgame.common.structure.Pair;
/*     */ import java.io.Serializable;
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
/*     */ public class Team
/*     */   implements Lock, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private long teamId;
/*     */   private long leaderId;
/*     */   private int firstHandVal;
/*     */   private int createtime;
/*     */   private volatile int status;
/*     */   
/*     */   public enum TeamStatus {
/*  28 */     CAN_JOIN(0),
/*  29 */     FULL(1),
/*  30 */     IN_FIGHT(2);
/*     */     
/*     */     private int value;
/*     */     
/*     */     TeamStatus(int value) {
/*  35 */       this.value = value;
/*     */     }
/*     */     
/*     */     public int getValue() {
/*  39 */       return this.value;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  48 */   private Lock lock = new ReentrantLock();
/*     */   
/*  50 */   private TeamPlayer[] teamPlayers = new TeamPlayer[3];
/*     */   
/*     */   public Team(long teamId, long leaderId) {
/*  53 */     this.teamId = teamId;
/*  54 */     this.leaderId = leaderId;
/*  55 */     this.status = TeamStatus.CAN_JOIN.getValue();
/*  56 */     this.createtime = TimeUtil.currentTime();
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
/*     */   public void setTeamPlayer(int index, boolean isRobot, long playerId, byte vip, byte sex, String head, String name, short level, long fightValue, int quality, int firstHandVal, int fashionId, IFighter[] fighters, IFighter petFighter, IFighter stageFighter, List<Pair<Integer, Integer>> candidateFighters, Pair<Integer, Integer> zhenfa) {
/*  72 */     if (fighters.length != 2) {
/*     */       return;
/*     */     }
/*     */     
/*  76 */     boolean isLeader = (this.leaderId == playerId);
/*  77 */     if (isLeader) {
/*  78 */       this.firstHandVal = firstHandVal;
/*     */     }
/*  80 */     TeamPlayer teamPlayer = new TeamPlayer(isRobot, playerId, vip, sex, head, name, level, fightValue, isLeader, index + 1, quality, fashionId, fighters, petFighter, stageFighter, candidateFighters, zhenfa);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFull() {
/*     */     try {
/* 124 */       lock();
/* 125 */       boolean full = true;
/* 126 */       for (TeamPlayer teamPlayer : this.teamPlayers) {
/* 127 */         if (teamPlayer == null) {
/* 128 */           full = false;
/*     */           break;
/*     */         } 
/*     */       } 
/* 132 */       return full;
/*     */     } finally {
/* 134 */       unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*     */     try {
/* 140 */       lock();
/* 141 */       int count = 0;
/* 142 */       for (TeamPlayer teamPlayer : this.teamPlayers) {
/* 143 */         if (teamPlayer != null && !teamPlayer.isRobot()) {
/* 144 */           count++;
/*     */         }
/*     */       } 
/* 147 */       return (count == 0);
/*     */     } finally {
/* 149 */       unlock();
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
/* 160 */     return (this.leaderId == playerId);
/*     */   }
/*     */   
/*     */   public TeamPlayer getTeamPlayerById(long playerId) {
/* 164 */     TeamPlayer target = null;
/* 165 */     for (TeamPlayer teamPlayer : this.teamPlayers) {
/* 166 */       if (teamPlayer != null && teamPlayer.getPlayerId() == playerId) {
/* 167 */         target = teamPlayer;
/*     */         break;
/*     */       } 
/*     */     } 
/* 171 */     return target;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int canJoin() {
/*     */     try {
/* 181 */       lock();
/* 182 */       int index = -1; int i;
/* 183 */       for (i = 0; i < this.teamPlayers.length; i++) {
/* 184 */         if (this.teamPlayers[i] == null) {
/* 185 */           index = i;
/*     */           break;
/*     */         } 
/*     */       } 
/* 189 */       i = index; return i;
/*     */     } finally {
/* 191 */       unlock();
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
/* 202 */       lock();
/* 203 */       boolean can = true;
/* 204 */       for (TeamPlayer teamPlayer : this.teamPlayers) {
/* 205 */         if (teamPlayer != null && !teamPlayer.isLeader() && !teamPlayer.isReady()) {
/* 206 */           can = false;
/*     */           break;
/*     */         } 
/*     */       } 
/* 210 */       return can;
/*     */     } finally {
/* 212 */       unlock();
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
/* 223 */     for (TeamPlayer teamPlayer : this.teamPlayers) {
/* 224 */       if (teamPlayer != null && !teamPlayer.isRobot() && teamPlayer.getPlayerId() == playerId) {
/* 225 */         teamPlayer.setReady(isReady);
/*     */         break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void lock() {
/* 233 */     this.lock.lock();
/*     */   }
/*     */ 
/*     */   
/*     */   public void lockInterruptibly() throws InterruptedException {
/* 238 */     this.lock.lockInterruptibly();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean tryLock() {
/* 243 */     return this.lock.tryLock();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean tryLock(long time, @NotNull TimeUnit unit) throws InterruptedException {
/* 248 */     if (SYNTHETIC_LOCAL_VARIABLE_3 == null) $$$reportNull$$$0(0);  return this.lock.tryLock(time, (TimeUnit)SYNTHETIC_LOCAL_VARIABLE_3);
/*     */   }
/*     */ 
/*     */   
/*     */   public void unlock() {
/* 253 */     this.lock.unlock();
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Condition newCondition() {
/* 259 */     if (this.lock.newCondition() == null) $$$reportNull$$$0(1);  return this.lock.newCondition();
/*     */   }
/*     */ 
/*     */   
/*     */   public long getTeamId() {
/* 264 */     return this.teamId;
/*     */   }
/*     */   
/*     */   public void setTeamId(long teamId) {
/* 268 */     this.teamId = teamId;
/*     */   }
/*     */   
/*     */   public long getLeaderId() {
/* 272 */     return this.leaderId;
/*     */   }
/*     */   
/*     */   public void setLeaderId(long leaderId) {
/* 276 */     this.leaderId = leaderId;
/*     */   }
/*     */   
/*     */   public int getStatus() {
/* 280 */     return this.status;
/*     */   }
/*     */   
/*     */   public void setStatus(int status) {
/* 284 */     this.status = status;
/*     */   }
/*     */   
/*     */   public List<TeamPlayer> getTeamPlayers() {
/* 288 */     return Collections.unmodifiableList((List<? extends TeamPlayer>)Arrays.<TeamPlayer>stream(this.teamPlayers).collect(Collectors.toList()));
/*     */   }
/*     */   
/*     */   public String getLeaderName() {
/* 292 */     if (this.teamPlayers[0] == null) {
/* 293 */       return "";
/*     */     }
/* 295 */     return this.teamPlayers[0].getName();
/*     */   }
/*     */   
/*     */   public TeamPlayer getLeader() {
/* 299 */     return this.teamPlayers[0];
/*     */   }
/*     */   
/*     */   public int getCreatetime() {
/* 303 */     return this.createtime;
/*     */   }
/*     */   
/*     */   public int getFirstHandVal() {
/* 307 */     return this.firstHandVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\team\Team.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */