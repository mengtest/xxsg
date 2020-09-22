/*     */ package com.linlongyx.sanguo.webgame.common.team;
/*     */ 
/*     */ import com.linlongyx.core.framework.logic.IPlayer;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.FightUtil;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.fighter.CommonFighterData;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.fighter.FighterFighter;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.fighter.IFighter;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.fighter.MonsterFighter;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.fighter.PetFighter;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.fighter.PlayerFighter;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.fighter.StageFighter;
/*     */ import com.linlongyx.sanguo.webgame.common.structure.Pair;
/*     */ import com.linlongyx.sanguo.webgame.common.util.RandUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.FighterBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.MonsterBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.MultiInsBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.MultiRobotBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.TeamParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.LanguageConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.cross.CrossChatByChannelResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.crossTeam.CrossTeamKickoutNoticeResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.crossTeam.CrossTeamPlayerDataNoticeResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.ChatInfo;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.TeamFighterData;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.TeamPlayerData;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.PlayerData;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.ConcurrentMap;
/*     */ import java.util.concurrent.atomic.AtomicLong;
/*     */ import java.util.stream.Collectors;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TeamUtil
/*     */ {
/*     */   public static final int TEAM_PLAYER_SIZE = 3;
/*     */   public static final int TEAM_PLAYER_FIGHTER_SIZE = 2;
/*     */   public static final int TEAM_LIST_LIMIT = 20;
/*     */   public static final int BATTLE_INS_ID = 1;
/*     */   private static boolean hasSendNotice = false;
/*     */   private static final long MIN_TEAM_ID = 1L;
/*     */   private static final long MAX_TEAM_ID = 1000000L;
/*     */   private static final long MIN_ROBOT_PLAYER_ID = 100L;
/*  57 */   private static final AtomicLong TEAM_ID_ASC = new AtomicLong(1L);
/*  58 */   private static final AtomicLong ROBOT_PLAYER_ID = new AtomicLong(100L);
/*     */   
/*  60 */   private static Map<Integer, ConcurrentMap<Long, Team>> INS_TO_TEAM_MAP = new HashMap<>();
/*  61 */   private static ConcurrentMap<Long, Long> PLAYERID_TO_TEAMID_MAP = new ConcurrentHashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static List<Team> getTeamListByInsId(int insId) {
/*  68 */     INS_TO_TEAM_MAP.putIfAbsent(Integer.valueOf(insId), new ConcurrentHashMap<>());
/*  69 */     return new ArrayList<>(((ConcurrentMap)INS_TO_TEAM_MAP.get(Integer.valueOf(insId))).values());
/*     */   }
/*     */   
/*     */   public static Team getTeamByInsIdAndTeamId(int insId, long teamId) {
/*  73 */     if (INS_TO_TEAM_MAP.containsKey(Integer.valueOf(insId))) {
/*  74 */       return (Team)((ConcurrentMap)INS_TO_TEAM_MAP.get(Integer.valueOf(insId))).get(Long.valueOf(teamId));
/*     */     }
/*  76 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getInsIdByTeamId(long teamId) {
/*  86 */     for (Map.Entry<Integer, ConcurrentMap<Long, Team>> kv : INS_TO_TEAM_MAP.entrySet()) {
/*  87 */       if (((ConcurrentMap)kv.getValue()).containsKey(Long.valueOf(teamId))) {
/*  88 */         return ((Integer)kv.getKey()).intValue();
/*     */       }
/*     */     } 
/*  91 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static long generateTeamId() {
/*     */     while (true) {
/* 102 */       long teamId = TEAM_ID_ASC.getAndIncrement();
/* 103 */       if (teamId > 1000000L) {
/* 104 */         TEAM_ID_ASC.set(1L);
/*     */       }
/* 106 */       if (!containsTeamId(teamId))
/* 107 */         return teamId; 
/*     */     } 
/*     */   }
/*     */   private static long generateRobotPlayerId() {
/* 111 */     while (ROBOT_PLAYER_ID.get() >= 1L) {
/* 112 */       TEAM_ID_ASC.set(100L);
/*     */     }
/* 114 */     return ROBOT_PLAYER_ID.getAndIncrement();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean containsTeamId(long teamId) {
/* 124 */     for (ConcurrentMap<Long, Team> map : INS_TO_TEAM_MAP.values()) {
/* 125 */       if (map.containsKey(Long.valueOf(teamId))) {
/* 126 */         return true;
/*     */       }
/*     */     } 
/* 129 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Team getTeamById(long teamId) {
/* 139 */     for (ConcurrentMap<Long, Team> map : INS_TO_TEAM_MAP.values()) {
/* 140 */       if (map.containsKey(Long.valueOf(teamId))) {
/* 141 */         return map.get(Long.valueOf(teamId));
/*     */       }
/*     */     } 
/* 144 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isPlayerInTeamWithInsId(int insId, long playerId) {
/* 155 */     for (Team team : ((ConcurrentMap)INS_TO_TEAM_MAP.get(Integer.valueOf(insId))).values()) {
/* 156 */       for (TeamPlayer teamPlayer : team.getTeamPlayers()) {
/* 157 */         if (teamPlayer != null && teamPlayer.getPlayerId() == playerId) {
/* 158 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/* 162 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isPlayerInTeam(long playerId) {
/* 172 */     return PLAYERID_TO_TEAMID_MAP.containsKey(Long.valueOf(playerId));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static long getTeamIdByPlayerId(long playerId) {
/* 182 */     return ((Long)PLAYERID_TO_TEAMID_MAP.getOrDefault(Long.valueOf(playerId), Long.valueOf(-1L))).longValue();
/*     */   }
/*     */   
/*     */   public static Team getTeamByPlayerId(long playerId) {
/* 186 */     if (!PLAYERID_TO_TEAMID_MAP.containsKey(Long.valueOf(playerId))) {
/* 187 */       return null;
/*     */     }
/* 189 */     return getTeamById(((Long)PLAYERID_TO_TEAMID_MAP.get(Long.valueOf(playerId))).longValue());
/*     */   }
/*     */   
/*     */   public static void removePlayerFromTeam(long playerId) {
/* 193 */     PLAYERID_TO_TEAMID_MAP.remove(Long.valueOf(playerId));
/* 194 */     for (ConcurrentMap<Long, Team> map : INS_TO_TEAM_MAP.values()) {
/* 195 */       for (Team team : map.values()) {
/* 196 */         TeamPlayer teamPlayer = team.getTeamPlayerById(playerId);
/* 197 */         if (teamPlayer != null) {
/* 198 */           team.removeTeamPlayer(teamPlayer.getPos() - 1);
/* 199 */           if (team.isEmpty()) {
/* 200 */             map.remove(Long.valueOf(team.getTeamId()));
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void dismissTeam(long playerId) {
/* 213 */     Team team = getTeamByPlayerId(playerId);
/* 214 */     if (team == null || !team.isLeader(playerId)) {
/*     */       return;
/*     */     }
/* 217 */     for (Map.Entry<Long, Long> kv : PLAYERID_TO_TEAMID_MAP.entrySet()) {
/* 218 */       if (((Long)kv.getValue()).longValue() == team.getTeamId()) {
/* 219 */         PLAYERID_TO_TEAMID_MAP.remove(kv.getKey());
/*     */       }
/*     */     } 
/* 222 */     for (ConcurrentMap<Long, Team> map : INS_TO_TEAM_MAP.values()) {
/* 223 */       if (map.containsKey(Long.valueOf(team.getTeamId()))) {
/* 224 */         map.remove(Long.valueOf(team.getTeamId()));
/*     */       }
/*     */     } 
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
/*     */   public static Team createTeam(long playerId, int insId) {
/* 238 */     if (isPlayerInTeam(playerId)) {
/* 239 */       return null;
/*     */     }
/* 241 */     INS_TO_TEAM_MAP.putIfAbsent(Integer.valueOf(insId), new ConcurrentHashMap<>());
/* 242 */     long teamId = generateTeamId();
/* 243 */     PLAYERID_TO_TEAMID_MAP.putIfAbsent(Long.valueOf(playerId), Long.valueOf(teamId));
/* 244 */     Team team = new Team(teamId, playerId);
/* 245 */     short code = initTeamLeader(playerId, team);
/* 246 */     if (code != 0) {
/* 247 */       PLAYERID_TO_TEAMID_MAP.remove(Long.valueOf(playerId));
/* 248 */       return null;
/*     */     } 
/* 250 */     ((ConcurrentMap<Long, Team>)INS_TO_TEAM_MAP.get(Integer.valueOf(insId))).put(Long.valueOf(teamId), team);
/*     */ 
/*     */     
/* 253 */     return team;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static short initTeamLeader(long playerId, Team team) {
/*     */     PetFighter petFighter1;
/*     */     StageFighter stageFighter1;
/* 263 */     IFighter[] fighters = new IFighter[2];
/* 264 */     IFighter petFighter = null, stageFighter = null;
/* 265 */     CommonFighterData playerFighterData = FightUtil.getFighterData(-1L, playerId, (byte)0);
/* 266 */     if (playerFighterData == null) {
/* 267 */       return 13711;
/*     */     }
/* 269 */     fighters[0] = (IFighter)new PlayerFighter(playerFighterData, (byte)1, true);
/* 270 */     CommonFighterData fighterFighterData = getHighestFightValueFighter(playerId);
/* 271 */     if (fighterFighterData == null) {
/* 272 */       return 13711;
/*     */     }
/*     */     
/* 275 */     fighters[1] = (IFighter)new FighterFighter(fighterFighterData, (byte)4, fighterFighterData.getPid(), true);
/* 276 */     team.setLeaderId(playerId);
/* 277 */     team.setStatus(Team.TeamStatus.CAN_JOIN.getValue());
/*     */     
/* 279 */     CommonFighterData petFighterData = FightUtil.getFighterData(-1L, playerId, (byte)3);
/* 280 */     if (petFighterData != null) {
/* 281 */       petFighter1 = new PetFighter(petFighterData, true);
/*     */     }
/* 283 */     CommonFighterData stageFighterData = FightUtil.getFighterData(-1L, playerId, (byte)4);
/* 284 */     if (stageFighterData != null) {
/* 285 */       stageFighter1 = new StageFighter(stageFighterData, true);
/*     */     }
/*     */     
/* 288 */     List<Pair<Integer, Integer>> candidateFighters = FightUtil.getCandidateFighterList(playerId);
/* 289 */     Pair<Integer, Integer> zhenfa = FightUtil.getZhenfa(playerId);
/* 290 */     team.setTeamPlayer(0, false, playerId, playerFighterData.getVip(), playerFighterData.getSex(), playerFighterData.getHead(), playerFighterData.getName(), playerFighterData.getLevel(), playerFighterData
/* 291 */         .getFightValue(), playerFighterData.getQuality(), playerFighterData.getFirstHandVal(), fighters, (IFighter)petFighter1, (IFighter)stageFighter1, candidateFighters, zhenfa);
/* 292 */     return 0;
/*     */   }
/*     */   
/*     */   public static CommonFighterData getHighestFightValueFighter(long playerId) throws NullPointerException {
/* 296 */     PlayerData playerData = LookUpService.getPlayerData(playerId);
/* 297 */     if (playerData == null) return null;
/*     */     
/* 299 */     return playerData.getFighters().values().stream().filter(fighter -> (fighter.getType() == 1)).max((f1, f2) -> (int)(f1.getFightValue() - f2.getFightValue())).orElse(null);
/*     */   }
/*     */   
/*     */   public static CommonFighterData[] getTwoHighestFightValueFighter(long playerId) throws NullPointerException {
/* 303 */     CommonFighterData[] fighterFighterDatas = new CommonFighterData[2];
/* 304 */     PlayerData playerData = LookUpService.getPlayerData(playerId);
/* 305 */     if (playerData == null) {
/* 306 */       return null;
/*     */     }
/* 308 */     List<CommonFighterData> list = (List<CommonFighterData>)playerData.getFighters().values().stream().filter(fighter -> (fighter.getType() == 1)).sorted(Comparator.comparing(CommonFighterData::getFightValue).reversed()).limit(2L).collect(Collectors.toList());
/* 309 */     if (list.size() == 0) {
/* 310 */       return null;
/*     */     }
/* 312 */     for (int i = 0; i < list.size(); i++) {
/* 313 */       fighterFighterDatas[i] = list.get(i);
/*     */     }
/* 315 */     return fighterFighterDatas;
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
/*     */   public static short joinTeam(long playerId, int insId, long teamId) {
/* 327 */     if (isPlayerInTeam(playerId)) {
/* 328 */       return 13705;
/*     */     }
/* 330 */     if (!INS_TO_TEAM_MAP.containsKey(Integer.valueOf(insId)) || !((ConcurrentMap)INS_TO_TEAM_MAP.get(Integer.valueOf(insId))).containsKey(Long.valueOf(teamId))) {
/* 331 */       return 13701;
/*     */     }
/* 333 */     Team team = (Team)((ConcurrentMap)INS_TO_TEAM_MAP.get(Integer.valueOf(insId))).get(Long.valueOf(teamId));
/* 334 */     if (team.getStatus() != Team.TeamStatus.CAN_JOIN.getValue()) {
/* 335 */       return 13704;
/*     */     }
/* 337 */     int index = team.canJoin();
/* 338 */     if (index == -1 && team.getStatus() == Team.TeamStatus.CAN_JOIN.getValue()) {
/* 339 */       team.setStatus(Team.TeamStatus.FULL.getValue());
/* 340 */       return 13702;
/*     */     } 
/*     */     
/* 343 */     IFighter[] fighters = new IFighter[2];
/* 344 */     CommonFighterData[] fighterDatas = getTwoHighestFightValueFighter(playerId);
/* 345 */     if (fighterDatas == null) {
/* 346 */       return 13704;
/*     */     }
/* 348 */     for (int i = 0; i < fighters.length; i++) {
/* 349 */       if (fighterDatas[i] != null) {
/* 350 */         fighters[i] = (IFighter)new FighterFighter(fighterDatas[i], (byte)(index + 1 + i * 3), fighterDatas[i].getPid(), true);
/*     */       }
/*     */     } 
/* 353 */     PlayerData playerData = LookUpService.getPlayerData(playerId);
/* 354 */     team.setTeamPlayer(index, false, playerId, playerData.getVip(), playerData.getSex(), playerData.getHead(), playerData.getPlayerName(), playerData
/* 355 */         .getLevel(), playerData.getFightValue(), playerData.getQuality(), playerData.getFirstHandVal(), fighters, null, null, null, null);
/*     */     
/* 357 */     PLAYERID_TO_TEAMID_MAP.put(Long.valueOf(playerId), Long.valueOf(teamId));
/* 358 */     if (team.isFull()) {
/* 359 */       team.setStatus(Team.TeamStatus.FULL.getValue());
/*     */     }
/* 361 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Team quickJoinTeam(long playerId, int insId) {
/* 372 */     Team target = null;
/* 373 */     for (Team team : getTeamListByInsId(insId)) {
/* 374 */       if (team.getStatus() == Team.TeamStatus.CAN_JOIN.getValue()) {
/* 375 */         target = team;
/*     */         break;
/*     */       } 
/*     */     } 
/* 379 */     if (target != null) {
/* 380 */       short code = joinTeam(playerId, insId, target.getTeamId());
/* 381 */       return (code == 0) ? target : null;
/*     */     } 
/* 383 */     return createTeam(playerId, insId);
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
/*     */   public static short kickoutTeam(long playerId, long teamId, long targetPlayerId) {
/* 397 */     Team team = getTeamById(teamId);
/* 398 */     if (team == null) {
/* 399 */       return 13701;
/*     */     }
/* 401 */     if (team.getLeaderId() != playerId) {
/* 402 */       return 13708;
/*     */     }
/*     */     
/* 405 */     List<TeamPlayer> teamPlayerList = team.getTeamPlayers();
/* 406 */     int index = -1;
/* 407 */     for (int i = 0; i < teamPlayerList.size(); i++) {
/* 408 */       if (teamPlayerList.get(i) != null && ((TeamPlayer)teamPlayerList.get(i)).getPlayerId() == targetPlayerId) {
/* 409 */         index = i;
/*     */         break;
/*     */       } 
/*     */     } 
/* 413 */     if (index == -1) {
/* 414 */       return 13706;
/*     */     }
/*     */     
/* 417 */     CrossTeamKickoutNoticeResponse response = new CrossTeamKickoutNoticeResponse();
/* 418 */     response.playerId = targetPlayerId;
/* 419 */     for (TeamPlayer teamPlayer : teamPlayerList) {
/* 420 */       if (teamPlayer != null && !teamPlayer.isRobot() && LookUpService.isOnline(teamPlayer.getPlayerId())) {
/* 421 */         LookUpService.getByPlayerId(teamPlayer.getPlayerId()).getSession().sendMessage((ResponseBase)response);
/*     */       }
/*     */     } 
/*     */     
/* 425 */     team.removeTeamPlayer(index);
/* 426 */     team.setStatus(Team.TeamStatus.CAN_JOIN.getValue());
/* 427 */     removePlayerFromTeam(targetPlayerId);
/*     */     
/* 429 */     return 0;
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
/*     */   public static short changeFighters(long playerId, long teamId, List<Integer> fighterIdList) {
/* 441 */     Team team = getTeamById(teamId);
/* 442 */     if (team == null) {
/* 443 */       return 13701;
/*     */     }
/* 445 */     TeamPlayer teamPlayer = team.getTeamPlayerById(playerId);
/* 446 */     if (teamPlayer == null) {
/* 447 */       return 13706;
/*     */     }
/* 449 */     if (teamPlayer.isReady()) {
/* 450 */       return 13709;
/*     */     }
/* 452 */     if (fighterIdList.size() > (teamPlayer.getFighters()).length || teamPlayer.isRobot()) {
/* 453 */       return 13710;
/*     */     }
/* 455 */     if (team.isLeader(playerId)) {
/* 456 */       if (!fighterIdList.contains(Integer.valueOf(-1))) {
/* 457 */         return 13710;
/*     */       }
/* 459 */       for (int i = 0; i < fighterIdList.size(); i++) {
/* 460 */         if (((Integer)fighterIdList.get(i)).longValue() == -1L) {
/* 461 */           CommonFighterData commonFighterData = FightUtil.getFighterData(-1L, playerId, (byte)0);
/* 462 */           teamPlayer.getFighters()[i] = new TeamPlayerFighter(true, (IFighter)new PlayerFighter(commonFighterData, (byte)(teamPlayer.getPos() + 3 * i), true));
/*     */         } else {
/* 464 */           CommonFighterData commonFighterData = FightUtil.getFighterData(((Integer)fighterIdList.get(i)).intValue(), playerId, (byte)1);
/* 465 */           teamPlayer.getFighters()[i] = new TeamPlayerFighter(false, (IFighter)new FighterFighter(commonFighterData, (byte)(teamPlayer.getPos() + 3 * i), ((Integer)fighterIdList.get(i)).intValue(), true));
/*     */         } 
/*     */       } 
/*     */     } else {
/* 469 */       for (int i = 0; i < fighterIdList.size(); i++) {
/* 470 */         CommonFighterData commonFighterData = FightUtil.getFighterData(((Integer)fighterIdList.get(i)).intValue(), playerId, (byte)1);
/* 471 */         teamPlayer.getFighters()[i] = new TeamPlayerFighter(false, (IFighter)new FighterFighter(commonFighterData, (byte)(teamPlayer.getPos() + 3 * i), ((Integer)fighterIdList.get(i)).intValue(), true));
/*     */       } 
/*     */     } 
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
/*     */ 
/*     */     
/* 489 */     return 0;
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
/*     */   public static short leaveTeam(long playerId, long teamId) {
/* 501 */     Team team = getTeamById(teamId);
/* 502 */     if (team == null) {
/* 503 */       return 13701;
/*     */     }
/*     */     try {
/* 506 */       team.lock();
/* 507 */       if (team.isLeader(playerId)) {
/* 508 */         team.removeTeamPlayer(0);
/* 509 */         int index = -1;
/* 510 */         List<TeamPlayer> teamPlayerList = team.getTeamPlayers(); int i;
/* 511 */         for (i = 1; i < teamPlayerList.size(); i++) {
/* 512 */           if (teamPlayerList.get(i) != null && !((TeamPlayer)teamPlayerList.get(i)).isRobot()) {
/* 513 */             index = i;
/*     */             break;
/*     */           } 
/*     */         } 
/* 517 */         if (index == -1) {
/* 518 */           for (ConcurrentMap<Long, Team> map : INS_TO_TEAM_MAP.values()) {
/* 519 */             map.remove(Long.valueOf(team.getTeamId()));
/*     */           }
/* 521 */           removePlayerFromTeam(playerId);
/* 522 */           i = 0; return i;
/*     */         } 
/* 524 */         if (teamPlayerList.get(index) != null) {
/* 525 */           long targetPlayerId = ((TeamPlayer)teamPlayerList.get(index)).getPlayerId();
/* 526 */           team.removeTeamPlayer(index);
/* 527 */           initTeamLeader(targetPlayerId, team);
/*     */         }
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 533 */         TeamPlayer teamPlayer = team.getTeamPlayerById(playerId);
/* 534 */         if (teamPlayer == null) {
/* 535 */           return 13706;
/*     */         }
/* 537 */         team.removeTeamPlayer(teamPlayer.getPos() - 1);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 547 */       team.setStatus(Team.TeamStatus.CAN_JOIN.getValue());
/* 548 */       removePlayerFromTeam(playerId);
/*     */     } finally {
/* 550 */       team.unlock();
/*     */     } 
/* 552 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static short matchRobot(Team team) {
/* 563 */     int insId = getInsIdByTeamId(team.getTeamId());
/* 564 */     if (insId == -1) {
/* 565 */       return 13701;
/*     */     }
/* 567 */     if (team.isFull()) {
/* 568 */       return 0;
/*     */     }
/*     */     
/* 571 */     MultiInsBean multiInsBean = (MultiInsBean)JsonTableService.getJsonData(insId, MultiInsBean.class);
/* 572 */     if (multiInsBean == null) {
/* 573 */       return 13712;
/*     */     }
/* 575 */     int robotNum = multiInsBean.getRobotId().size(), robotId = 0;
/*     */     try {
/* 577 */       team.lock();
/* 578 */       List<TeamPlayer> teamPlayerList = team.getTeamPlayers();
/* 579 */       for (int i = 0; i < teamPlayerList.size(); i++) {
/* 580 */         if (teamPlayerList.get(i) == null) {
/* 581 */           if (robotNum > 1) {
/* 582 */             robotId = ((Integer)multiInsBean.getRobotId().get(RandUtil.randNum(robotNum - 1))).intValue();
/*     */           } else {
/* 584 */             robotId = ((Integer)multiInsBean.getRobotId().get(0)).intValue();
/*     */           } 
/* 586 */           MultiRobotBean robotBean = (MultiRobotBean)JsonTableService.getJsonData(robotId, MultiRobotBean.class);
/* 587 */           IFighter[] fighters = new IFighter[2];
/* 588 */           if (robotBean.getMonster().size() != 2) {
/* 589 */             return 13713;
/*     */           }
/* 591 */           for (int j = 0; j < robotBean.getMonster().size(); j++) {
/* 592 */             int pos = i + 1 + j * 3;
/* 593 */             int monsterId = ((Integer)robotBean.getMonster().get(j)).intValue();
/* 594 */             fighters[j] = (IFighter)new MonsterFighter(monsterId, (byte)pos);
/*     */           } 
/* 596 */           int fightValue = RandUtil.randNum(((MultiRobotBean.FightingBean)robotBean.getFighting().get(0)).getLow(), ((MultiRobotBean.FightingBean)robotBean.getFighting().get(0)).getHight());
/* 597 */           int level = RandUtil.randNum(((MultiRobotBean.LevelBean)robotBean.getLevel().get(0)).getLow(), ((MultiRobotBean.LevelBean)robotBean.getLevel().get(0)).getHight());
/* 598 */           team.setTeamPlayer(i, true, generateRobotPlayerId(), (byte)0, (byte)1, "head=sex:" + robotBean.getHead(), "玩家" + RandUtil.randNum(), (short)level, fightValue, 0, 0, fighters, null, null, null, null);
/*     */         } 
/* 600 */         team.setStatus(Team.TeamStatus.FULL.getValue());
/*     */       } 
/*     */     } finally {
/* 603 */       team.unlock();
/*     */     } 
/* 605 */     sendTeamPlayerDataNotice(team);
/* 606 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static List<TeamFighterData> createTeamFighterDataList(TeamPlayer teamPlayer) {
/* 616 */     List<TeamFighterData> teamFighterDataList = new ArrayList<>();
/* 617 */     for (TeamPlayerFighter teamPlayerFighter : teamPlayer.getFighters()) {
/* 618 */       TeamFighterData teamFighterData = new TeamFighterData();
/* 619 */       if (teamPlayerFighter != null) {
/* 620 */         if (!teamPlayer.isRobot()) {
/* 621 */           FighterBean fighterBean = (FighterBean)JsonTableService.getJsonData((int)teamPlayerFighter.getFighter().getId(), FighterBean.class);
/* 622 */           teamFighterData.fighterId = (int)teamPlayerFighter.getFighter().getId();
/* 623 */           teamFighterData.pid = (int)teamPlayerFighter.getFighter().getPid();
/* 624 */           teamFighterData.pos = (byte)teamPlayerFighter.getPos();
/* 625 */           teamFighterData.name = teamPlayerFighter.isLead() ? teamPlayer.getName() : fighterBean.getName();
/*     */         } else {
/* 627 */           MonsterBean monsterBean = (MonsterBean)JsonTableService.getJsonData((int)teamPlayerFighter.getFighter().getId(), MonsterBean.class);
/* 628 */           teamFighterData.fighterId = (int)teamPlayerFighter.getFighter().getId();
/* 629 */           teamFighterData.pid = (int)teamPlayerFighter.getFighter().getPid();
/* 630 */           teamFighterData.pos = (byte)teamPlayerFighter.getPos();
/* 631 */           teamFighterData.name = monsterBean.getName();
/*     */         } 
/*     */       }
/* 634 */       teamFighterDataList.add(teamFighterData);
/*     */     } 
/* 636 */     return teamFighterDataList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void teamFightEnd(Team team) {
/* 645 */     boolean teamPlayerChange = false;
/*     */     try {
/* 647 */       team.lock();
/* 648 */       List<TeamPlayer> teamPlayerList = team.getTeamPlayers();
/* 649 */       for (int i = 0; i < teamPlayerList.size(); i++) {
/* 650 */         if (teamPlayerList.get(i) != null) {
/* 651 */           teamPlayerChange = true;
/* 652 */           if (((TeamPlayer)teamPlayerList.get(i)).isRobot()) {
/* 653 */             team.removeTeamPlayer(i);
/*     */           } else {
/* 655 */             ((TeamPlayer)teamPlayerList.get(i)).setReady(false);
/* 656 */             ((TeamPlayer)teamPlayerList.get(i)).setNeedReInit(true);
/*     */           } 
/*     */         } 
/*     */       } 
/* 660 */       if (teamPlayerChange);
/*     */ 
/*     */ 
/*     */       
/* 664 */       team.setStatus(team.isFull() ? Team.TeamStatus.FULL.getValue() : Team.TeamStatus.CAN_JOIN.getValue());
/*     */     } finally {
/* 666 */       team.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static List<TeamPlayerData> createTeamPlayerDataList(Team team) {
/* 677 */     List<TeamPlayerData> teamPlayerDataList = new ArrayList<>();
/* 678 */     for (TeamPlayer teamPlayer : team.getTeamPlayers()) {
/* 679 */       if (teamPlayer != null) {
/* 680 */         TeamPlayerData teamPlayerData = new TeamPlayerData();
/* 681 */         teamPlayerData.playerId = teamPlayer.getPlayerId();
/* 682 */         teamPlayerData.head = teamPlayer.getHead();
/* 683 */         teamPlayerData.name = teamPlayer.getName();
/* 684 */         teamPlayerData.level = teamPlayer.getLevel();
/* 685 */         teamPlayerData.fightValue = teamPlayer.getFightValue();
/* 686 */         teamPlayerData.isLeader = (byte)(teamPlayer.isLeader() ? 1 : 0);
/* 687 */         teamPlayerData.status = (byte)(teamPlayer.isReady() ? 1 : 0);
/* 688 */         teamPlayerData.isRobot = (byte)(teamPlayer.isRobot() ? 1 : 0);
/* 689 */         teamPlayerData.fighterList.addAll(createTeamFighterDataList(teamPlayer));
/* 690 */         teamPlayerDataList.add(teamPlayerData); continue;
/*     */       } 
/* 692 */       teamPlayerDataList.add(new TeamPlayerData());
/*     */     } 
/*     */     
/* 695 */     return teamPlayerDataList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void sendTeamPlayerDataNotice(Team team) {
/* 704 */     CrossTeamPlayerDataNoticeResponse response = new CrossTeamPlayerDataNoticeResponse();
/* 705 */     response.players.addAll(createTeamPlayerDataList(team));
/* 706 */     for (TeamPlayer tp : team.getTeamPlayers()) {
/* 707 */       if (tp != null && !tp.isRobot() && LookUpService.isOnline(tp.getPlayerId())) {
/* 708 */         LookUpService.getByPlayerId(tp.getPlayerId()).getSession().sendMessage((ResponseBase)response);
/*     */       }
/*     */     } 
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
/*     */   public static void sendTeamPlayerReadyNotice(Team team, long playerId, byte isReady) {}
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
/*     */   public static void sendDoubletimeNotice() {
/* 740 */     TeamParameter teamParameter = (TeamParameter)ParameterConstant.getParameter(37);
/* 741 */     if (teamParameter.isInDoubleTime(TimeUtil.getNowMinutes())) {
/* 742 */       if (!hasSendNotice)
/*     */       {
/* 744 */         hasSendNotice = true;
/*     */       }
/*     */     }
/* 747 */     else if (hasSendNotice) {
/* 748 */       hasSendNotice = false;
/*     */     } 
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
/*     */   public static void sendCreateTeamChatNotice(long playerId, int insId, long teamId, Set<Long> targetPlayers) {
/* 761 */     Team team = getTeamById(teamId);
/* 762 */     if (team != null) {
/* 763 */       TeamPlayer leader = team.getLeader();
/* 764 */       CrossChatByChannelResponse response = new CrossChatByChannelResponse();
/* 765 */       String context = LanguageConstant.getAndReplaceLanguage(8101, new String[] { leader.getName(), String.valueOf(teamId) });
/* 766 */       ArrayList<ChatInfo> list = new ArrayList<>();
/* 767 */       ChatInfo chatInfo = new ChatInfo();
/* 768 */       chatInfo.type = 0;
/* 769 */       chatInfo.sendPlayerId = playerId;
/* 770 */       chatInfo.sendPlayerName = leader.getName();
/* 771 */       chatInfo.vip = leader.getVip();
/*     */       
/* 773 */       chatInfo.sex = leader.getSex();
/* 774 */       chatInfo.head = leader.getHead();
/* 775 */       chatInfo.context = context;
/* 776 */       chatInfo.time = TimeUtil.currentTime();
/* 777 */       chatInfo.teamChat = 1;
/* 778 */       list.add(chatInfo);
/* 779 */       response.list = list;
/* 780 */       for (Iterator<Long> iterator = targetPlayers.iterator(); iterator.hasNext(); ) { long target = ((Long)iterator.next()).longValue();
/* 781 */         IPlayer player = LookUpService.getByPlayerId(target);
/* 782 */         if (player != null)
/* 783 */           player.getSession().sendMessage((ResponseBase)response);  }
/*     */     
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\common\team\TeamUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */