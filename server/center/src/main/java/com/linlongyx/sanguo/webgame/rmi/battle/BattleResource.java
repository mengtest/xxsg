/*     */ package com.linlongyx.sanguo.webgame.rmi.battle;
/*     */ 
/*     */ import com.fasterxml.jackson.annotation.JsonIgnore;
/*     */ import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.FightUtil;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.side.PlayerFightSide;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.type.CrossPvpFight;
/*     */ import com.linlongyx.sanguo.webgame.common.team.Team;
/*     */ import com.linlongyx.sanguo.webgame.common.team.TeamPlayer;
/*     */ import com.linlongyx.sanguo.webgame.common.team.TeamUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.StarcraftBean;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.cross.CrossBattleResourceNoticeResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.fight.CrossFightRecordResponse;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.PlayerData;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.io.Serializable;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import java.util.concurrent.locks.Condition;
/*     */ import java.util.concurrent.locks.Lock;
/*     */ import java.util.concurrent.locks.ReentrantLock;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @JsonIgnoreProperties(ignoreUnknown = true)
/*     */ public class BattleResource
/*     */   implements Lock, Serializable
/*     */ {
/*     */   private int resourceId;
/*     */   private boolean campOwn;
/*  38 */   private int[] pos = new int[2];
/*     */   private ResourceType type;
/*     */   private volatile long playerId;
/*  41 */   private volatile ResourceState curState = ResourceState.FREE;
/*  42 */   private Battle.CampTag ownCamp = Battle.CampTag.NONE;
/*     */   private int limit;
/*     */   private int collectTime;
/*  45 */   private int count = 0;
/*  46 */   private Set<Long> playerSet = new HashSet<>();
/*     */   @JsonIgnore
/*  48 */   private Lock lock = new ReentrantLock();
/*     */ 
/*     */ 
/*     */   
/*     */   public BattleResource() {}
/*     */ 
/*     */   
/*     */   public BattleResource(int resourceId, boolean campOwn, int[] pos, ResourceType type, ResourceState state, Battle.CampTag campTag, int limit, int collectTime) {
/*  56 */     this.resourceId = resourceId;
/*  57 */     this.campOwn = campOwn;
/*  58 */     this.pos = pos;
/*  59 */     this.type = type;
/*  60 */     this.curState = state;
/*  61 */     this.ownCamp = campTag;
/*  62 */     this.limit = limit;
/*  63 */     this.collectTime = collectTime;
/*  64 */     this.count = 0;
/*     */   }
/*     */   
/*     */   public void reset() {
/*  68 */     StarcraftBean bean = (StarcraftBean)JsonTableService.getJsonData(this.resourceId, StarcraftBean.class);
/*  69 */     this.campOwn = (bean.getCamp() != Battle.CampTag.NONE.getTag());
/*  70 */     this.pos = new int[] { ((StarcraftBean.BuildingLocationBean)bean.getBuildingLocation().get(0)).getX(), ((StarcraftBean.BuildingLocationBean)bean.getBuildingLocation().get(0)).getY() };
/*  71 */     this.type = (bean.getBuildingType() == 0) ? ResourceType.BORNPOINT : ResourceType.RESOURCE;
/*  72 */     this.curState = (bean.getCamp() == 0) ? ResourceState.FREE : ResourceState.SEIZED;
/*  73 */     this.ownCamp = Battle.CampTag.getCampTag(bean.getCamp());
/*  74 */     this.limit = bean.getAccommodateCap();
/*  75 */     this.collectTime = bean.getAcquisitionTime();
/*  76 */     this.count = 0;
/*  77 */     this.playerSet.clear();
/*     */   }
/*     */   
/*     */   public int incrementCount(int num) {
/*     */     try {
/*  82 */       lock();
/*  83 */       this.count += num;
/*  84 */       return this.count;
/*     */     } finally {
/*  86 */       unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public int decrementCount(int num) {
/*     */     try {
/*  92 */       lock();
/*  93 */       this.count = (this.count - num < 0) ? 0 : (this.count - num);
/*  94 */       return this.count;
/*     */     } finally {
/*  96 */       unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void removePlayer(long playerId) {
/*     */     try {
/* 102 */       lock();
/* 103 */       if (this.playerSet.remove(Long.valueOf(playerId))) {
/* 104 */         decrementCount(1);
/* 105 */         if (this.playerId == playerId) {
/* 106 */           Battle battle = BattleUtil.getCurBattle(this.playerId);
/* 107 */           if (this.playerSet.isEmpty()) {
/* 108 */             if (!this.campOwn) {
/* 109 */               this.playerId = 0L;
/* 110 */               this.curState = ResourceState.FREE;
/* 111 */               this.ownCamp = Battle.CampTag.NONE;
/* 112 */               if (battle != null) {
/* 113 */                 CrossBattleResourceNoticeResponse resp = new CrossBattleResourceNoticeResponse();
/* 114 */                 resp.resourceId = this.resourceId;
/* 115 */                 resp.state = 0;
/* 116 */                 resp.playerId = 0L;
/* 117 */                 BattleUtil.broadcastResourceNotice(battle, resp);
/*     */               } 
/*     */             } 
/*     */           } else {
/* 121 */             this.playerId = ((Long)this.playerSet.iterator().next()).longValue();
/* 122 */             Team team = TeamUtil.getTeamByPlayerId(this.playerId);
/* 123 */             if (team != null && team.getLeaderId() != this.playerId) {
/* 124 */               this.playerId = team.getLeaderId();
/*     */             }
/* 126 */             if (battle != null) {
/* 127 */               CrossBattleResourceNoticeResponse resp = new CrossBattleResourceNoticeResponse();
/* 128 */               resp.resourceId = this.resourceId;
/* 129 */               resp.state = 1;
/* 130 */               resp.playerId = this.playerId;
/* 131 */               BattleUtil.broadcastResourceNotice(battle, resp);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } finally {
/* 137 */       unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addPlayer(BattlePlayer battlePlayer) {
/*     */     try {
/* 143 */       lock();
/* 144 */       if (this.count >= this.limit)
/* 145 */         return;  if (this.playerSet.isEmpty()) {
/* 146 */         this.playerId = battlePlayer.getPlayerId();
/* 147 */         this.ownCamp = battlePlayer.getCampTag();
/* 148 */         this.curState = ResourceState.SEIZED;
/*     */       } 
/* 150 */       if (!this.playerSet.contains(Long.valueOf(battlePlayer.getPlayerId()))) {
/* 151 */         incrementCount(1);
/* 152 */         this.playerSet.add(Long.valueOf(battlePlayer.getPlayerId()));
/*     */       } 
/*     */     } finally {
/* 155 */       unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public short battle(Battle battle, long playerId, CrossFightRecordResponse response) {
/*     */     try {
/* 161 */       lock();
/* 162 */       if (this.type == ResourceType.BORNPOINT) {
/* 163 */         return 11308;
/*     */       }
/* 165 */       BattlePlayer battlePlayer = battle.getBattlePlayers().get(Long.valueOf(playerId));
/* 166 */       Battle.CampTag playerCampTag = battle.getBattleCamps()[battlePlayer.getCampIndex()].getTag();
/* 167 */       int size = 1, curTime = TimeUtil.currentTime();
/* 168 */       Team team = TeamUtil.getTeamByPlayerId(battlePlayer.getPlayerId());
/* 169 */       if (battlePlayer.getPlayerTeamStatus() != BattlePlayer.PlayerTeamStatus.NOTEAM) {
/*     */         
/* 171 */         if (team == null) {
/* 172 */           battlePlayer.setPlayerTeamStatus(BattlePlayer.PlayerTeamStatus.NOTEAM);
/* 173 */           return 13701;
/*     */         } 
/* 175 */         size = team.getRealPlayerSize();
/*     */       } 
/* 177 */       if (this.curState == ResourceState.FREE) {
/* 178 */         this.curState = ResourceState.SEIZED;
/* 179 */         this.playerId = battlePlayer.getPlayerId();
/* 180 */         this.ownCamp = playerCampTag;
/* 181 */         updateBattlePlayer(battle, team, battlePlayer, curTime);
/* 182 */       } else if (this.curState == ResourceState.SEIZED) {
/* 183 */         if (this.ownCamp == playerCampTag) {
/* 184 */           if (incrementCount(size) > this.limit) {
/* 185 */             decrementCount(size);
/* 186 */             return 11309;
/*     */           } 
/* 188 */           updateBattlePlayer(battle, team, battlePlayer, curTime);
/*     */         } else {
/* 190 */           PlayerFightSide leftSide, rightSide; BattlePlayer targetPlayer = battle.getBattlePlayers().get(Long.valueOf(this.playerId));
/* 191 */           if (targetPlayer == null) {
/* 192 */             updateBattlePlayer(battle, team, battlePlayer, curTime);
/* 193 */             LogUtil.errorLog(new Object[] { "资源点被敌方阵营占领, 但不存在敌人" });
/* 194 */             return 0;
/*     */           } 
/* 196 */           Team targetTeam = TeamUtil.getTeamByPlayerId(targetPlayer.getPlayerId());
/*     */           
/* 198 */           if (targetPlayer.getPlayerTeamStatus() != BattlePlayer.PlayerTeamStatus.NOTEAM) {
/* 199 */             if (targetTeam == null) {
/* 200 */               updateBattlePlayer(battle, team, battlePlayer, curTime);
/* 201 */               targetPlayer.setPlayerTeamStatus(BattlePlayer.PlayerTeamStatus.NOTEAM);
/* 202 */               LogUtil.errorLog(new Object[] { "敌方数据异常,敌方队伍不存在" });
/* 203 */               return 0;
/*     */             } 
/* 205 */             if (team != null) {
/* 206 */               leftSide = FightUtil.createTeamPlayerFightSide(team, false, false);
/* 207 */               rightSide = FightUtil.createTeamPlayerFightSide(targetTeam, false, true);
/*     */             } else {
/* 209 */               PlayerData playerData = battlePlayer.getPlayerData();
/* 210 */               if (playerData == null) {
/* 211 */                 LogUtil.errorLog(new Object[] { Long.valueOf(playerId), "playerData not exist" });
/* 212 */                 return 10019;
/*     */               } 
/* 214 */               leftSide = FightUtil.createPvpFightSide(battlePlayer.getFighters(), playerData.getCandidateFighters(), playerData.getZhenfa(), playerData.getPlayerId(), playerData.getPlayerName(), playerData.getFirstHandVal(), false);
/* 215 */               rightSide = FightUtil.createTeamPlayerFightSide(targetTeam, false, true);
/*     */             }
/*     */           
/* 218 */           } else if (team != null) {
/* 219 */             leftSide = FightUtil.createTeamPlayerFightSide(team, false, false);
/* 220 */             PlayerData playerData = targetPlayer.getPlayerData();
/* 221 */             if (playerData == null) {
/* 222 */               LogUtil.errorLog(new Object[] { Long.valueOf(targetPlayer.getPlayerId()), "playerData not exist" });
/*     */               
/* 224 */               return 10019;
/*     */             } 
/* 226 */             rightSide = FightUtil.createPvpFightSide(targetPlayer.getFighters(), playerData.getCandidateFighters(), playerData.getZhenfa(), playerData.getPlayerId(), playerData.getPlayerName(), playerData.getFirstHandVal(), false);
/*     */           } else {
/* 228 */             PlayerData leftPlayerData = battlePlayer.getPlayerData();
/* 229 */             PlayerData rightPlayerData = targetPlayer.getPlayerData();
/* 230 */             if (leftPlayerData == null) {
/* 231 */               LogUtil.errorLog(new Object[] { Long.valueOf(playerId), "playerData not exist" });
/* 232 */               return 10019;
/*     */             } 
/* 234 */             if (rightPlayerData == null) {
/* 235 */               LogUtil.errorLog(new Object[] { Long.valueOf(targetPlayer.getPlayerId()), "playerData not exist" });
/*     */               
/* 237 */               return 10019;
/*     */             } 
/* 239 */             leftSide = FightUtil.createPvpFightSide(battlePlayer.getFighters(), leftPlayerData.getCandidateFighters(), leftPlayerData.getZhenfa(), leftPlayerData.getPlayerId(), leftPlayerData.getPlayerName(), leftPlayerData.getFirstHandVal(), false);
/* 240 */             rightSide = FightUtil.createPvpFightSide(targetPlayer.getFighters(), rightPlayerData.getCandidateFighters(), rightPlayerData.getZhenfa(), rightPlayerData.getPlayerId(), rightPlayerData.getPlayerName(), rightPlayerData.getFirstHandVal(), false);
/*     */           } 
/*     */           
/* 243 */           CrossPvpFight crossPvpFight = new CrossPvpFight(leftSide, rightSide);
/* 244 */           int result = crossPvpFight.start(response);
/* 245 */           if (result == 1) {
/*     */             Iterator<Long> iterator;
/*     */             
/* 248 */             for (iterator = this.playerSet.iterator(); iterator.hasNext(); ) { long id = ((Long)iterator.next()).longValue();
/* 249 */               BattlePlayer p = battle.getBattlePlayers().get(Long.valueOf(id));
/* 250 */               p.reborn(battle); }
/*     */             
/* 252 */             this.playerSet.clear();
/* 253 */             this.curState = ResourceState.SEIZED;
/* 254 */             this.playerId = battlePlayer.getPlayerId();
/* 255 */             this.ownCamp = playerCampTag;
/* 256 */             updateBattlePlayer(battle, team, battlePlayer, curTime);
/*     */             
/* 258 */             if (team != null) {
/* 259 */               for (TeamPlayer p : team.getTeamPlayers()) {
/* 260 */                 if (p != null && !p.isRobot()) {
/* 261 */                   BattlePlayer b = battle.getBattlePlayers().get(Long.valueOf(p.getPlayerId()));
/* 262 */                   if (b != null) {
/* 263 */                     b.resetFighters();
/*     */                   }
/*     */                 } 
/*     */               } 
/*     */             } else {
/* 268 */               battlePlayer.resetFighters();
/*     */             } 
/*     */ 
/*     */             
/* 272 */             CrossBattleResourceNoticeResponse resp = new CrossBattleResourceNoticeResponse();
/* 273 */             resp.resourceId = this.resourceId;
/* 274 */             resp.state = 1;
/* 275 */             resp.playerId = this.playerId;
/* 276 */             BattleUtil.broadcastResourceNotice(battle, resp);
/*     */             
/* 278 */             BattleUtil.broadcastResourceOwnNotice(battle, this.resourceId, this.ownCamp.getTag(), battlePlayer.getPlayerData().getPlayerName());
/*     */             
/* 280 */             BattleUtil.sendBeHitNotice(this.ownCamp.getTag(), battlePlayer.getPlayerData().getPlayerName(), targetPlayer.getPlayerId());
/*     */           }
/* 282 */           else if (team != null) {
/* 283 */             for (TeamPlayer p : team.getTeamPlayers()) {
/* 284 */               if (p != null && !p.isRobot()) {
/* 285 */                 BattlePlayer b = battle.getBattlePlayers().get(Long.valueOf(p.getPlayerId()));
/* 286 */                 if (b != null) {
/* 287 */                   b.reborn(battle);
/*     */                 }
/*     */               } 
/*     */             } 
/*     */           } else {
/* 292 */             battlePlayer.reborn(battle);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 297 */       return 0;
/*     */     } finally {
/* 299 */       unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void updateBattlePlayer(Battle battle, Team team, BattlePlayer battlePlayer, int curTime) {
/*     */     try {
/* 305 */       lock();
/* 306 */       if (team == null) {
/* 307 */         battlePlayer.setState(BattlePlayer.PlayerState.SEIZING);
/* 308 */         battlePlayer.setLastCollectTime(curTime);
/* 309 */         battlePlayer.updatePos(this.pos[0], this.pos[1]);
/* 310 */         battlePlayer.setResourceId(this.resourceId);
/* 311 */         this.playerSet.add(Long.valueOf(battlePlayer.getPlayerId()));
/*     */       } else {
/* 313 */         for (TeamPlayer p : team.getTeamPlayers()) {
/* 314 */           if (p != null && !p.isRobot()) {
/* 315 */             BattlePlayer b = battle.getBattlePlayers().get(Long.valueOf(p.getPlayerId()));
/* 316 */             if (b != null) {
/* 317 */               b.setState(BattlePlayer.PlayerState.SEIZING);
/* 318 */               b.setLastCollectTime(curTime);
/* 319 */               b.updatePos(this.pos[0], this.pos[1]);
/* 320 */               b.setResourceId(this.resourceId);
/* 321 */               this.playerSet.add(Long.valueOf(b.getPlayerId()));
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } finally {
/* 327 */       unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void lock() {
/* 333 */     this.lock.lock();
/*     */   }
/*     */ 
/*     */   
/*     */   public void lockInterruptibly() throws InterruptedException {
/* 338 */     this.lock.lockInterruptibly();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean tryLock() {
/* 343 */     return this.lock.tryLock();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean tryLock(long time, @NotNull TimeUnit unit) throws InterruptedException {
/* 348 */     if (SYNTHETIC_LOCAL_VARIABLE_3 == null) $$$reportNull$$$0(0);  return this.lock.tryLock(time, (TimeUnit)SYNTHETIC_LOCAL_VARIABLE_3);
/*     */   }
/*     */ 
/*     */   
/*     */   public void unlock() {
/* 353 */     this.lock.unlock();
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Condition newCondition() {
/* 359 */     if (null == null) $$$reportNull$$$0(1);  return null;
/*     */   }
/*     */   
/*     */   public enum ResourceState {
/* 363 */     FREE(0),
/* 364 */     SEIZED(1);
/*     */     
/*     */     private int state;
/*     */     
/*     */     ResourceState(int state) {
/* 369 */       this.state = state;
/*     */     }
/*     */     
/*     */     public int getState() {
/* 373 */       return this.state;
/*     */     }
/*     */   }
/*     */   
/*     */   public enum ResourceType {
/* 378 */     BORNPOINT(0),
/* 379 */     RESOURCE(1);
/*     */     
/*     */     int type;
/*     */     
/*     */     ResourceType(int type) {
/* 384 */       this.type = type;
/*     */     }
/*     */     
/*     */     public int getType() {
/* 388 */       return this.type;
/*     */     }
/*     */   }
/*     */   
/*     */   public int getResourceId() {
/* 393 */     return this.resourceId;
/*     */   }
/*     */   
/*     */   public void setResourceId(int resourceId) {
/* 397 */     this.resourceId = resourceId;
/*     */   }
/*     */   
/*     */   public boolean isCampOwn() {
/* 401 */     return this.campOwn;
/*     */   }
/*     */   
/*     */   public void setCampOwn(boolean campOwn) {
/* 405 */     this.campOwn = campOwn;
/*     */   }
/*     */   
/*     */   public int[] getPos() {
/* 409 */     return this.pos;
/*     */   }
/*     */   
/*     */   public void setPos(int[] pos) {
/* 413 */     this.pos = pos;
/*     */   }
/*     */   
/*     */   public ResourceType getType() {
/* 417 */     return this.type;
/*     */   }
/*     */   
/*     */   public void setType(ResourceType type) {
/* 421 */     this.type = type;
/*     */   }
/*     */   
/*     */   public long getPlayerId() {
/* 425 */     return this.playerId;
/*     */   }
/*     */   
/*     */   public void setPlayerId(long playerId) {
/* 429 */     this.playerId = playerId;
/*     */   }
/*     */   
/*     */   public ResourceState getCurState() {
/* 433 */     return this.curState;
/*     */   }
/*     */   
/*     */   public void setCurState(ResourceState curState) {
/* 437 */     this.curState = curState;
/*     */   }
/*     */   
/*     */   public Battle.CampTag getOwnCamp() {
/* 441 */     return this.ownCamp;
/*     */   }
/*     */   
/*     */   public void setOwnCamp(Battle.CampTag ownCamp) {
/* 445 */     this.ownCamp = ownCamp;
/*     */   }
/*     */   
/*     */   public int getLimit() {
/* 449 */     return this.limit;
/*     */   }
/*     */   
/*     */   public void setLimit(int limit) {
/* 453 */     this.limit = limit;
/*     */   }
/*     */   
/*     */   public int getCollectTime() {
/* 457 */     return this.collectTime;
/*     */   }
/*     */   
/*     */   public void setCollectTime(int collectTime) {
/* 461 */     this.collectTime = collectTime;
/*     */   }
/*     */   
/*     */   public int getCount() {
/* 465 */     return this.count;
/*     */   }
/*     */   
/*     */   public void setCount(int count) {
/* 469 */     this.count = count;
/*     */   }
/*     */   
/*     */   public Set<Long> getPlayerSet() {
/* 473 */     return this.playerSet;
/*     */   }
/*     */   
/*     */   public void setPlayerSet(Set<Long> playerSet) {
/* 477 */     this.playerSet = playerSet;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\rmi\battle\BattleResource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */