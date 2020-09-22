/*     */ package com.linlongyx.sanguo.webgame.rmi.battle;
/*     */ 
/*     */ import com.fasterxml.jackson.annotation.JsonIgnore;
/*     */ import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.FightUtil;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.fighter.CommonFighterData;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.fighter.FighterFighter;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.fighter.IFighter;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.fighter.PlayerFighter;
/*     */ import com.linlongyx.sanguo.webgame.common.team.Team;
/*     */ import com.linlongyx.sanguo.webgame.common.team.TeamPlayer;
/*     */ import com.linlongyx.sanguo.webgame.common.team.TeamUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.cross.CrossUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.cross.CrossBattleRebornNoticeResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.CrossBattleTeamData;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.PlayerData;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.stream.Collectors;
/*     */ 
/*     */ 
/*     */ 
/*     */ @JsonIgnoreProperties(ignoreUnknown = true)
/*     */ public class BattlePlayer
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private String campKey;
/*     */   private int campIndex;
/*     */   private Battle.CampTag campTag;
/*     */   private long playerId;
/*  40 */   private volatile PlayerState state = PlayerState.BORN; private int resourceId;
/*     */   private int[] pos;
/*     */   private int lastCollectTime;
/*     */   private int point;
/*     */   private PlayerTeamStatus playerTeamStatus;
/*     */   private PlayerData playerData;
/*     */   private Map<Integer, CommonFighterData> fighters;
/*     */   
/*     */   public BattlePlayer(PlayerData playerData, String campKey, Battle.CampTag campTag, int campIndex, long playerId, int resourceId, int[] pos) {
/*  49 */     this.playerData = playerData;
/*  50 */     this.campKey = campKey;
/*  51 */     this.campTag = campTag;
/*  52 */     this.campIndex = campIndex;
/*  53 */     this.playerId = playerId;
/*  54 */     this.resourceId = resourceId;
/*  55 */     this.pos = Arrays.copyOf(pos, pos.length);
/*  56 */     this.playerTeamStatus = PlayerTeamStatus.NOTEAM;
/*  57 */     this.fighters = (Map<Integer, CommonFighterData>)CrossUtil.deepCopy(playerData.getFighters());
/*     */   }
/*     */   
/*     */   public void resetFighters() {
/*  61 */     if (LookUpService.isOnline(this.playerId)) {
/*  62 */       this.fighters = (Map<Integer, CommonFighterData>)CrossUtil.deepCopy(LookUpService.getPlayerData(this.playerId).getFighters());
/*     */     } else {
/*  64 */       this.fighters = (Map<Integer, CommonFighterData>)CrossUtil.deepCopy(this.playerData.getFighters());
/*     */     } 
/*     */     
/*  67 */     Team team = TeamUtil.getTeamByPlayerId(this.playerId);
/*  68 */     if (team != null) {
/*  69 */       TeamPlayer tp = team.getTeamPlayerById(this.playerId);
/*     */       
/*  71 */       if (team.isLeader(this.playerId)) {
/*  72 */         CommonFighterData playerFighterData = FightUtil.getFighterData(-1L, this.playerId, (byte)0);
/*  73 */         if (playerFighterData != null && tp.getFighters()[0] != null) {
/*  74 */           byte pos = tp.getFighters()[0].getFighter().getPos();
/*  75 */           tp.getFighters()[0].setFighter((IFighter)new PlayerFighter(playerFighterData, pos, true));
/*     */         } 
/*  77 */         CommonFighterData fighterFighterData = this.fighters.values().stream().filter(fighter -> (fighter.getType() == 1)).max((f1, f2) -> (int)(f1.getFightValue() - f2.getFightValue())).orElse(null);
/*  78 */         if (fighterFighterData != null && tp.getFighters()[1] != null) {
/*  79 */           byte pos = tp.getFighters()[1].getFighter().getPos();
/*  80 */           tp.getFighters()[1].setFighter((IFighter)new FighterFighter(fighterFighterData, pos, fighterFighterData.getPid(), true));
/*     */         } 
/*     */       } else {
/*  83 */         CommonFighterData[] fighterDatas = new CommonFighterData[2];
/*  84 */         List<CommonFighterData> list = (List<CommonFighterData>)this.fighters.values().stream().filter(fighter -> (fighter.getType() == 1)).sorted(Comparator.comparing(CommonFighterData::getFightValue).reversed()).limit(2L).collect(Collectors.toList()); int i;
/*  85 */         for (i = 0; i < list.size(); i++) {
/*  86 */           fighterDatas[i] = list.get(i);
/*     */         }
/*  88 */         for (i = 0; i < (tp.getFighters()).length; i++) {
/*  89 */           if (fighterDatas[i] != null && tp.getFighters()[i] != null) {
/*  90 */             byte pos = tp.getFighters()[i].getFighter().getPos();
/*  91 */             tp.getFighters()[i].setFighter((IFighter)new FighterFighter(fighterDatas[i], pos, fighterDatas[i].getPid(), true));
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void reset(Battle battle) {
/*  99 */     this.state = PlayerState.BORN;
/* 100 */     this.fighters = (Map<Integer, CommonFighterData>)CrossUtil.deepCopy(this.playerData.getFighters());
/* 101 */     BattleCamp battleCamp = battle.getBattleCamps()[this.campIndex];
/* 102 */     updatePos(battleCamp.getBornPoint()[0], battleCamp.getBornPoint()[1]);
/* 103 */     this.resourceId = battleCamp.getResourceId();
/* 104 */     this.lastCollectTime = TimeUtil.currentTime();
/* 105 */     resetFighters();
/*     */   }
/*     */   
/*     */   public void reborn(Battle battle) {
/* 109 */     reset(battle);
/*     */     
/* 111 */     CrossBattleRebornNoticeResponse response = new CrossBattleRebornNoticeResponse();
/* 112 */     Team team = TeamUtil.getTeamByPlayerId(this.playerId);
/* 113 */     response.type = 0;
/* 114 */     response.leaderId = (team == null) ? this.playerId : team.getLeaderId();
/* 115 */     response.playerId = this.playerId;
/* 116 */     response.x = this.pos[0];
/* 117 */     response.y = this.pos[1];
/* 118 */     response.playerData = battle.getPlayerBattleTeamData(this.playerId);
/* 119 */     BattleUtil.broadcastRebornNotice(battle, response);
/*     */   }
/*     */   
/*     */   public void exitBattle(Battle battle) {
/* 123 */     BattleResource battleResource = battle.getResourceMap().get(Integer.valueOf(getResourceId()));
/* 124 */     Team team = TeamUtil.getTeamByPlayerId(this.playerId);
/* 125 */     CrossBattleRebornNoticeResponse response = new CrossBattleRebornNoticeResponse();
/* 126 */     response.type = 1;
/* 127 */     response.playerId = this.playerId;
/* 128 */     response.x = 0;
/* 129 */     response.y = 0;
/* 130 */     response.playerData = new CrossBattleTeamData();
/*     */     
/* 132 */     if (getPlayerTeamStatus() == PlayerTeamStatus.TEAMLEADER) {
/* 133 */       if (team != null) {
/* 134 */         List<Long> memberList = new ArrayList<>();
/* 135 */         for (TeamPlayer teamPlayer : team.getTeamPlayers()) {
/* 136 */           if (teamPlayer != null && !teamPlayer.isRobot() && !teamPlayer.isLeader()) {
/* 137 */             memberList.add(Long.valueOf(teamPlayer.getPlayerId()));
/*     */           }
/*     */         } 
/* 140 */         TeamUtil.dismissTeam(this.playerId);
/* 141 */         for (Iterator<Long> iterator = memberList.iterator(); iterator.hasNext(); ) { long id = ((Long)iterator.next()).longValue();
/* 142 */           BattlePlayer p = battle.getBattlePlayers().get(Long.valueOf(id));
/* 143 */           p.playerTeamStatus = PlayerTeamStatus.NOTEAM;
/* 144 */           p.reborn(battle);
/* 145 */           if (battleResource.getType() == BattleResource.ResourceType.RESOURCE) {
/* 146 */             battleResource.removePlayer(id);
/*     */           } }
/*     */         
/* 149 */         response.leaderId = this.playerId;
/*     */       } 
/* 151 */       battleResource.removePlayer(this.playerId);
/* 152 */     } else if (getPlayerTeamStatus() == PlayerTeamStatus.HASTEAM) {
/* 153 */       if (team != null) {
/* 154 */         TeamUtil.leaveTeam(this.playerId, team.getTeamId());
/* 155 */         battleResource.removePlayer(this.playerId);
/* 156 */         response.leaderId = team.getLeaderId();
/*     */       } 
/*     */     } else {
/* 159 */       battleResource.removePlayer(this.playerId);
/* 160 */       response.leaderId = this.playerId;
/*     */     } 
/* 162 */     this.playerTeamStatus = PlayerTeamStatus.NOTEAM;
/* 163 */     reset(battle);
/* 164 */     battle.removeBattlePlayer(this.playerId);
/* 165 */     BattleUtil.broadcastRebornNotice(battle, response);
/*     */   }
/*     */ 
/*     */   
/*     */   public short createTeam(Battle battle) {
/* 170 */     Team team = TeamUtil.createTeam(this.playerId, 1);
/* 171 */     if (team == null) {
/* 172 */       return 13711;
/*     */     }
/* 174 */     Set<Long> targets = (Set<Long>)battle.getBattlePlayers().values().stream().filter(p -> (p.getCampIndex() == getCampIndex())).map(BattlePlayer::getPlayerId).collect(Collectors.toSet());
/* 175 */     TeamUtil.sendCreateTeamChatNotice(this.playerId, 1, team.getTeamId(), targets);
/* 176 */     this.playerTeamStatus = PlayerTeamStatus.TEAMLEADER;
/*     */     
/* 178 */     CrossBattleRebornNoticeResponse response = new CrossBattleRebornNoticeResponse();
/*     */     
/* 180 */     response.type = 2;
/* 181 */     response.leaderId = team.getLeaderId();
/* 182 */     response.playerId = this.playerId;
/* 183 */     response.x = this.pos[0];
/* 184 */     response.y = this.pos[1];
/* 185 */     response.playerData = battle.getPlayerBattleTeamData(this.playerId);
/* 186 */     BattleUtil.broadcastRebornNotice(battle, response);
/* 187 */     return 0;
/*     */   }
/*     */   
/*     */   public short joinTeam(Battle battle, Team team) {
/* 191 */     short code = TeamUtil.joinTeam(this.playerId, 1, team.getTeamId());
/* 192 */     if (code != 0) return code; 
/* 193 */     this.playerTeamStatus = PlayerTeamStatus.HASTEAM;
/* 194 */     long leaderId = team.getLeaderId();
/* 195 */     BattlePlayer leader = battle.getBattlePlayers().get(Long.valueOf(leaderId));
/* 196 */     this.resourceId = leader.getResourceId();
/* 197 */     updatePos(leader.getPos()[0], leader.getPos()[1]);
/* 198 */     this.lastCollectTime = leader.getLastCollectTime();
/* 199 */     this.state = leader.getState();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 206 */     CrossBattleRebornNoticeResponse response = new CrossBattleRebornNoticeResponse();
/*     */     
/* 208 */     response.type = 2;
/* 209 */     response.leaderId = team.getLeaderId();
/* 210 */     response.playerId = this.playerId;
/* 211 */     response.x = this.pos[0];
/* 212 */     response.y = this.pos[1];
/* 213 */     response.playerData = battle.getPlayerBattleTeamData(this.playerId);
/* 214 */     BattleUtil.broadcastRebornNotice(battle, response);
/* 215 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public short leaveTeam(Battle battle, Team team) {
/* 220 */     CrossBattleRebornNoticeResponse response = new CrossBattleRebornNoticeResponse();
/*     */     
/* 222 */     if (getPlayerTeamStatus() == PlayerTeamStatus.TEAMLEADER) {
/* 223 */       if (team != null) {
/*     */         
/* 225 */         List<Long> memberList = new ArrayList<>();
/* 226 */         for (TeamPlayer teamPlayer : team.getTeamPlayers()) {
/* 227 */           if (teamPlayer != null && !teamPlayer.isRobot() && !teamPlayer.isLeader()) {
/* 228 */             memberList.add(Long.valueOf(teamPlayer.getPlayerId()));
/*     */           }
/*     */         } 
/*     */         
/* 232 */         TeamUtil.dismissTeam(this.playerId);
/* 233 */         this.playerTeamStatus = PlayerTeamStatus.NOTEAM;
/* 234 */         reset(battle);
/* 235 */         response.type = 4;
/* 236 */         response.leaderId = this.playerId;
/* 237 */         response.playerId = this.playerId;
/* 238 */         response.x = getPos()[0];
/* 239 */         response.y = getPos()[1];
/* 240 */         response.playerData = battle.getPlayerBattleTeamData(this.playerId);
/* 241 */         BattleUtil.broadcastRebornNotice(battle, response);
/* 242 */         for (Iterator<Long> iterator = memberList.iterator(); iterator.hasNext(); ) { long id = ((Long)iterator.next()).longValue();
/* 243 */           BattlePlayer p = battle.getBattlePlayers().get(Long.valueOf(id));
/* 244 */           p.playerTeamStatus = PlayerTeamStatus.NOTEAM;
/* 245 */           p.reborn(battle); }
/*     */       
/*     */       } 
/* 248 */     } else if (getPlayerTeamStatus() == PlayerTeamStatus.HASTEAM) {
/* 249 */       this.playerTeamStatus = PlayerTeamStatus.NOTEAM;
/* 250 */       if (team != null) {
/* 251 */         TeamUtil.leaveTeam(this.playerId, team.getTeamId());
/*     */         
/* 253 */         reset(battle);
/*     */         
/* 255 */         response.type = 3;
/* 256 */         response.leaderId = team.getLeaderId();
/* 257 */         response.playerId = this.playerId;
/* 258 */         response.x = getPos()[0];
/* 259 */         response.y = getPos()[1];
/* 260 */         response.playerData = battle.getPlayerBattleTeamData(this.playerId);
/*     */       } 
/* 262 */       BattleUtil.broadcastRebornNotice(battle, response);
/*     */     } 
/* 264 */     return 0;
/*     */   }
/*     */   
/*     */   public void updatePos(int x, int y) {
/* 268 */     this.pos[0] = x;
/* 269 */     this.pos[1] = y;
/*     */   }
/*     */   
/*     */   public enum PlayerState {
/* 273 */     BORN(0),
/* 274 */     MOVING(1),
/* 275 */     SEIZING(2);
/*     */     
/*     */     private int state;
/*     */     
/*     */     PlayerState(int state) {
/* 280 */       this.state = state;
/*     */     }
/*     */     
/*     */     public int getState() {
/* 284 */       return this.state;
/*     */     }
/*     */   }
/*     */   
/*     */   public enum PlayerTeamStatus {
/* 289 */     NOTEAM(0),
/* 290 */     HASTEAM(1),
/* 291 */     TEAMLEADER(2);
/*     */     int status;
/*     */     
/*     */     PlayerTeamStatus(int status) {
/* 295 */       this.status = status;
/*     */     }
/*     */     
/*     */     public int getStatus() {
/* 299 */       return this.status;
/*     */     }
/*     */   }
/*     */   
/*     */   @JsonIgnore
/*     */   public int getServerId() {
/* 305 */     return this.playerData.getServerId();
/*     */   }
/*     */   
/*     */   public String getCampKey() {
/* 309 */     return this.campKey;
/*     */   }
/*     */   
/*     */   public void setCampKey(String campKey) {
/* 313 */     this.campKey = campKey;
/*     */   }
/*     */   
/*     */   public int getCampIndex() {
/* 317 */     return this.campIndex;
/*     */   }
/*     */   
/*     */   public void setCampIndex(int campIndex) {
/* 321 */     this.campIndex = campIndex;
/*     */   }
/*     */   
/*     */   public long getPlayerId() {
/* 325 */     return this.playerId;
/*     */   }
/*     */   
/*     */   public void setPlayerId(long playerId) {
/* 329 */     this.playerId = playerId;
/*     */   }
/*     */   
/*     */   public int[] getPos() {
/* 333 */     return this.pos;
/*     */   }
/*     */   
/*     */   public void setPos(int[] pos) {
/* 337 */     this.pos = pos;
/*     */   }
/*     */   
/*     */   public PlayerTeamStatus getPlayerTeamStatus() {
/* 341 */     return this.playerTeamStatus;
/*     */   }
/*     */   
/*     */   public void setPlayerTeamStatus(PlayerTeamStatus playerTeamStatus) {
/* 345 */     this.playerTeamStatus = playerTeamStatus;
/*     */   }
/*     */   
/*     */   public PlayerState getState() {
/* 349 */     return this.state;
/*     */   }
/*     */   
/*     */   public void setState(PlayerState state) {
/* 353 */     this.state = state;
/*     */   }
/*     */   
/*     */   public int getResourceId() {
/* 357 */     return this.resourceId;
/*     */   }
/*     */   
/*     */   public void setResourceId(int resourceId) {
/* 361 */     this.resourceId = resourceId;
/*     */   }
/*     */   
/*     */   public int getLastCollectTime() {
/* 365 */     return this.lastCollectTime;
/*     */   }
/*     */   
/*     */   public void setLastCollectTime(int lastCollectTime) {
/* 369 */     this.lastCollectTime = lastCollectTime;
/*     */   }
/*     */   
/*     */   public int getPoint() {
/* 373 */     return this.point;
/*     */   }
/*     */   
/*     */   public void setPoint(int point) {
/* 377 */     this.point = point;
/*     */   }
/*     */   
/*     */   public PlayerData getPlayerData() {
/* 381 */     return this.playerData;
/*     */   }
/*     */   
/*     */   public void setPlayerData(PlayerData playerData) {
/* 385 */     this.playerData = playerData;
/*     */   }
/*     */   
/*     */   public Map<Integer, CommonFighterData> getFighters() {
/* 389 */     return this.fighters;
/*     */   }
/*     */   
/*     */   public void setFighters(Map<Integer, CommonFighterData> fighters) {
/* 393 */     this.fighters = fighters;
/*     */   }
/*     */   
/*     */   public Battle.CampTag getCampTag() {
/* 397 */     return this.campTag;
/*     */   }
/*     */   
/*     */   public void setCampTag(Battle.CampTag campTag) {
/* 401 */     this.campTag = campTag;
/*     */   }
/*     */   
/*     */   public BattlePlayer() {}
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\rmi\battle\BattlePlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */