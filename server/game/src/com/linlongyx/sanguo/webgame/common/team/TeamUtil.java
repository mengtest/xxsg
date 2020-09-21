/*     */ package com.linlongyx.sanguo.webgame.common.team;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.partner.PartnerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.partner.PartnerEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
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
/*     */ import com.linlongyx.sanguo.webgame.config.bean.BaguaInsBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.FighterBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.MonsterBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.MultiInsBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.MultiRobotBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.TeamParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.LanguageConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.chat.ChatByChannelResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.ChatInfo;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.TeamFighterData;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.TeamPlayerData;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.team.TeamFighterChangeNoticeResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.team.TeamKickoutNoticeResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.team.TeamPlayerDataNoticeResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.team.TeamPlayerReadyNoticeResponse;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.ConcurrentMap;
/*     */ import java.util.concurrent.atomic.AtomicLong;
/*     */ 
/*     */ public class TeamUtil {
/*     */   public static final int TEAM_PLAYER_SIZE = 3;
/*     */   public static final int TEAM_PLAYER_FIGHTER_SIZE = 2;
/*     */   public static final int TEAM_LIST_LIMIT = 20;
/*     */   private static boolean hasSendNotice = false;
/*  52 */   private static final long MIN_TEAM_ID = Long.parseLong(MContext.getServerId()) * 1000000L;
/*  53 */   private static final long MAX_TEAM_ID = MIN_TEAM_ID + 999999L;
/*  54 */   private static final long MIN_ROBOT_PLAYER_ID = Long.parseLong(MContext.getServerId()) * 100L;
/*  55 */   private static final AtomicLong TEAM_ID_ASC = new AtomicLong(MIN_TEAM_ID);
/*  56 */   private static final AtomicLong ROBOT_PLAYER_ID = new AtomicLong(MIN_ROBOT_PLAYER_ID);
/*     */   
/*  58 */   private static Map<Integer, ConcurrentMap<Long, Team>> INS_TO_TEAM_MAP = new HashMap<>();
/*  59 */   private static ConcurrentMap<Long, Long> PLAYERID_TO_TEAMID_MAP = new ConcurrentHashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static List<Team> getTeamListByInsId(int insId) {
/*  66 */     INS_TO_TEAM_MAP.putIfAbsent(Integer.valueOf(insId), new ConcurrentHashMap<>());
/*  67 */     return new ArrayList<>(((ConcurrentMap)INS_TO_TEAM_MAP.get(Integer.valueOf(insId))).values());
/*     */   }
/*     */   
/*     */   public static Team getTeamByInsIdAndTeamId(int insId, long teamId) {
/*  71 */     if (INS_TO_TEAM_MAP.containsKey(Integer.valueOf(insId))) {
/*  72 */       return (Team)((ConcurrentMap)INS_TO_TEAM_MAP.get(Integer.valueOf(insId))).get(Long.valueOf(teamId));
/*     */     }
/*  74 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getInsIdByTeamId(long teamId) {
/*  83 */     for (Map.Entry<Integer, ConcurrentMap<Long, Team>> kv : INS_TO_TEAM_MAP.entrySet()) {
/*  84 */       if (((ConcurrentMap)kv.getValue()).containsKey(Long.valueOf(teamId))) {
/*  85 */         return ((Integer)kv.getKey()).intValue();
/*     */       }
/*     */     } 
/*  88 */     return -1;
/*     */   }
/*     */   
/*     */   public static Team getTeamByPlayerIdExact(long playerId) {
/*  92 */     for (Map.Entry<Integer, ConcurrentMap<Long, Team>> kv : INS_TO_TEAM_MAP.entrySet()) {
/*  93 */       for (Team t : ((ConcurrentMap)kv.getValue()).values()) {
/*  94 */         for (TeamPlayer p : t.getTeamPlayers()) {
/*  95 */           if (p != null && !p.isRobot() && p.getPlayerId() == playerId) {
/*  96 */             return t;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 101 */     return null;
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
/* 112 */       long teamId = TEAM_ID_ASC.getAndIncrement();
/* 113 */       if (teamId > MAX_TEAM_ID) {
/* 114 */         TEAM_ID_ASC.set(MIN_TEAM_ID);
/*     */       }
/* 116 */       if (!containsTeamId(teamId))
/* 117 */         return teamId; 
/*     */     } 
/*     */   }
/*     */   private static long generateRobotPlayerId() {
/* 121 */     while (ROBOT_PLAYER_ID.get() >= MIN_TEAM_ID) {
/* 122 */       TEAM_ID_ASC.set(MIN_ROBOT_PLAYER_ID);
/*     */     }
/* 124 */     return ROBOT_PLAYER_ID.getAndIncrement();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean containsTeamId(long teamId) {
/* 134 */     for (ConcurrentMap<Long, Team> map : INS_TO_TEAM_MAP.values()) {
/* 135 */       if (map.containsKey(Long.valueOf(teamId))) {
/* 136 */         return true;
/*     */       }
/*     */     } 
/* 139 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Team getTeamById(long teamId) {
/* 149 */     for (ConcurrentMap<Long, Team> map : INS_TO_TEAM_MAP.values()) {
/* 150 */       if (map.containsKey(Long.valueOf(teamId))) {
/* 151 */         return map.get(Long.valueOf(teamId));
/*     */       }
/*     */     } 
/* 154 */     return null;
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
/* 165 */     for (Team team : ((ConcurrentMap)INS_TO_TEAM_MAP.get(Integer.valueOf(insId))).values()) {
/* 166 */       for (TeamPlayer teamPlayer : team.getTeamPlayers()) {
/* 167 */         if (teamPlayer != null && teamPlayer.getPlayerId() == playerId) {
/* 168 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/* 172 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isPlayerInTeam(long playerId) {
/* 182 */     return PLAYERID_TO_TEAMID_MAP.containsKey(Long.valueOf(playerId));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static long getTeamIdByPlayerId(long playerId) {
/* 192 */     return ((Long)PLAYERID_TO_TEAMID_MAP.getOrDefault(Long.valueOf(playerId), Long.valueOf(-1L))).longValue();
/*     */   }
/*     */   
/*     */   public static void removePlayerFromTeam(long playerId) {
/* 196 */     PLAYERID_TO_TEAMID_MAP.remove(Long.valueOf(playerId));
/* 197 */     for (ConcurrentMap<Long, Team> map : INS_TO_TEAM_MAP.values()) {
/* 198 */       for (Team team : map.values()) {
/* 199 */         TeamPlayer teamPlayer = team.getTeamPlayerById(playerId);
/* 200 */         if (teamPlayer != null) {
/* 201 */           team.removeTeamPlayer(teamPlayer.getPos() - 1);
/* 202 */           if (team.isEmpty()) {
/* 203 */             map.remove(Long.valueOf(team.getTeamId()));
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Team createTeam(long playerId, int insId, int insType) {
/* 220 */     if (isPlayerInTeam(playerId)) {
/* 221 */       return null;
/*     */     }
/* 223 */     INS_TO_TEAM_MAP.putIfAbsent(Integer.valueOf(insId), new ConcurrentHashMap<>());
/* 224 */     long teamId = generateTeamId();
/* 225 */     PLAYERID_TO_TEAMID_MAP.putIfAbsent(Long.valueOf(playerId), Long.valueOf(teamId));
/* 226 */     Team team = new Team(teamId, playerId);
/* 227 */     short code = initTeamLeader(playerId, team);
/* 228 */     if (code != 0) {
/* 229 */       PLAYERID_TO_TEAMID_MAP.remove(Long.valueOf(playerId));
/* 230 */       return null;
/*     */     } 
/* 232 */     ((ConcurrentMap<Long, Team>)INS_TO_TEAM_MAP.get(Integer.valueOf(insId))).put(Long.valueOf(teamId), team);
/* 233 */     sendTeamPlayerDataNotice(team);
/*     */ 
/*     */     
/* 236 */     sendCreateTeamChatNotice(playerId, insId, teamId, insType);
/* 237 */     return team;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static short initTeamLeader(long playerId, Team team) {
/*     */     PetFighter petFighter1;
/*     */     StageFighter stageFighter1;
/* 247 */     IFighter[] fighters = new IFighter[2];
/* 248 */     IFighter petFighter = null, stageFighter = null;
/* 249 */     CommonFighterData playerFighterData = FightUtil.getFighterData(-1L, playerId, (byte)0);
/* 250 */     if (playerFighterData == null) {
/* 251 */       return 13711;
/*     */     }
/* 253 */     fighters[0] = (IFighter)new PlayerFighter(playerFighterData, (byte)1);
/* 254 */     CommonFighterData fighterFighterData = getHighestFightValueFighter(playerId);
/* 255 */     if (fighterFighterData == null) {
/* 256 */       return 13711;
/*     */     }
/*     */     
/* 259 */     fighters[1] = (IFighter)new FighterFighter(fighterFighterData, (byte)4, fighterFighterData.getPid());
/*     */     
/* 261 */     CommonFighterData petFighterData = FightUtil.getFighterData(-1L, playerId, (byte)3);
/* 262 */     if (petFighterData != null) {
/* 263 */       petFighter1 = new PetFighter(petFighterData);
/*     */     }
/* 265 */     CommonFighterData stageFighterData = FightUtil.getFighterData(-1L, playerId, (byte)4);
/* 266 */     if (stageFighterData != null) {
/* 267 */       stageFighter1 = new StageFighter(stageFighterData);
/*     */     }
/* 269 */     List<Pair<Integer, Integer>> candidateFighters = FightUtil.getCandidateFighterList(playerId);
/* 270 */     Pair<Integer, Integer> zhenfa = FightUtil.getZhenfa(playerId);
/* 271 */     team.setLeaderId(playerId);
/* 272 */     team.setStatus(Team.TeamStatus.CAN_JOIN.getValue());
/* 273 */     team.setTeamPlayer(0, false, playerId, playerFighterData.getVip(), playerFighterData.getSex(), playerFighterData.getHead(), playerFighterData.getName(), playerFighterData.getLevel(), playerFighterData.getFightValue(), playerFighterData.getQuality(), playerFighterData.getFirstHandVal(), playerFighterData.getFashionId(), fighters, (IFighter)petFighter1, (IFighter)stageFighter1, candidateFighters, zhenfa);
/* 274 */     return 0;
/*     */   }
/*     */   
/*     */   private static CommonFighterData getHighestFightValueFighter(long playerId) throws NullPointerException {
/* 278 */     CommonFighterData fighterFighterData = null;
/* 279 */     if (!PlayerUtil.isRemotePlayer(playerId)) {
/* 280 */       PartnerComponent partnerComponent = (PartnerComponent)LookUpService.getComponent(playerId, PartnerComponent.class);
/* 281 */       if (partnerComponent == null) {
/* 282 */         throw new NullPointerException("partnerComponent not exist, playerId: " + playerId);
/*     */       }
/*     */       
/* 285 */       PartnerEntity targetPartnerEntity = partnerComponent.getMaxFightValue();
/* 286 */       if (targetPartnerEntity != null) {
/* 287 */         fighterFighterData = FightUtil.getFighterData(targetPartnerEntity.getPid(), playerId, (byte)1);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 292 */     return fighterFighterData;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static CommonFighterData[] getTwoHighestFightValueFighter(long playerId, int index) throws NullPointerException {
/* 298 */     CommonFighterData[] fighterFighterDatas = new CommonFighterData[2];
/* 299 */     if (!PlayerUtil.isRemotePlayer(playerId)) {
/* 300 */       PartnerComponent partnerComponent = (PartnerComponent)LookUpService.getComponent(playerId, PartnerComponent.class);
/* 301 */       if (partnerComponent == null) {
/* 302 */         throw new NullPointerException("partnerComponent not exist, playerId: " + playerId);
/*     */       }
/* 304 */       long firstValue = 0L, secondValue = 0L;
/* 305 */       PartnerEntity targetPartnerEntity = null;
/*     */       
/* 307 */       for (IMapEntity iMapEntity : partnerComponent.getEntityMap().values()) {
/* 308 */         PartnerEntity partnerEntity = (PartnerEntity)iMapEntity;
/* 309 */         if (firstValue < partnerEntity.getFightValue()) {
/* 310 */           firstValue = partnerEntity.getFightValue();
/* 311 */           targetPartnerEntity = partnerEntity;
/*     */         } 
/*     */       } 
/* 314 */       if (targetPartnerEntity != null) {
/* 315 */         fighterFighterDatas[0] = FightUtil.getFighterData(targetPartnerEntity.getPid(), playerId, (byte)1);
/* 316 */         targetPartnerEntity = null;
/* 317 */         for (IMapEntity iMapEntity : partnerComponent.getEntityMap().values()) {
/* 318 */           PartnerEntity partnerEntity = (PartnerEntity)iMapEntity;
/* 319 */           if (partnerEntity.getFightValue() < firstValue && secondValue < partnerEntity.getFightValue()) {
/* 320 */             secondValue = partnerEntity.getFightValue();
/* 321 */             targetPartnerEntity = partnerEntity;
/*     */           } 
/*     */         } 
/* 324 */         if (targetPartnerEntity != null) {
/* 325 */           fighterFighterDatas[1] = FightUtil.getFighterData(targetPartnerEntity.getPid(), playerId, (byte)1);
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 331 */     return fighterFighterDatas;
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
/* 343 */     if (isPlayerInTeam(playerId)) {
/* 344 */       return 13705;
/*     */     }
/* 346 */     if (!INS_TO_TEAM_MAP.containsKey(Integer.valueOf(insId)) || !((ConcurrentMap)INS_TO_TEAM_MAP.get(Integer.valueOf(insId))).containsKey(Long.valueOf(teamId))) {
/* 347 */       return 13701;
/*     */     }
/* 349 */     Team team = (Team)((ConcurrentMap)INS_TO_TEAM_MAP.get(Integer.valueOf(insId))).get(Long.valueOf(teamId));
/* 350 */     if (team.getStatus() != Team.TeamStatus.CAN_JOIN.getValue()) {
/* 351 */       return 13704;
/*     */     }
/* 353 */     int index = team.canJoin();
/* 354 */     if (index == -1 && team.getStatus() == Team.TeamStatus.CAN_JOIN.getValue()) {
/* 355 */       team.setStatus(Team.TeamStatus.FULL.getValue());
/* 356 */       return 13702;
/*     */     } 
/*     */     
/* 359 */     IFighter[] fighters = new IFighter[2];
/* 360 */     CommonFighterData[] fighterDatas = getTwoHighestFightValueFighter(playerId, index);
/* 361 */     for (int i = 0; i < fighters.length; i++) {
/* 362 */       if (fighterDatas[i] != null) {
/* 363 */         fighters[i] = (IFighter)new FighterFighter(fighterDatas[i], (byte)(index + 1 + i * 3), fighterDatas[i].getPid());
/*     */       }
/*     */     } 
/* 366 */     if (!PlayerUtil.isRemotePlayer(playerId)) {
/* 367 */       PlayerComponent playerComponent = (PlayerComponent)LookUpService.getComponent(playerId, PlayerComponent.class);
/* 368 */       team.setTeamPlayer(index, false, playerId, playerComponent.getVip(), playerComponent.getSex(), playerComponent.getHead(), playerComponent.getPlayerName(), playerComponent.getLevel(), playerComponent.getTotalValue(), playerComponent.getQuality(), 0, 0, fighters, null, null, null, null);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 373 */     PLAYERID_TO_TEAMID_MAP.put(Long.valueOf(playerId), Long.valueOf(teamId));
/* 374 */     if (team.isFull()) {
/* 375 */       team.setStatus(Team.TeamStatus.FULL.getValue());
/*     */     }
/* 377 */     sendTeamPlayerDataNotice(team);
/* 378 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Team quickJoinTeam(long playerId, int insId, int insType) {
/* 389 */     Team target = null;
/* 390 */     for (Team team : getTeamListByInsId(insId)) {
/* 391 */       if (team.getStatus() == Team.TeamStatus.CAN_JOIN.getValue()) {
/* 392 */         target = team;
/*     */         break;
/*     */       } 
/*     */     } 
/* 396 */     if (target != null) {
/* 397 */       short code = joinTeam(playerId, insId, target.getTeamId());
/* 398 */       return (code == 0) ? target : null;
/*     */     } 
/* 400 */     return createTeam(playerId, insId, insType);
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
/* 414 */     Team team = getTeamById(teamId);
/* 415 */     if (team == null) {
/* 416 */       return 13701;
/*     */     }
/* 418 */     if (team.getLeaderId() != playerId) {
/* 419 */       return 13708;
/*     */     }
/*     */     
/* 422 */     List<TeamPlayer> teamPlayerList = team.getTeamPlayers();
/* 423 */     int index = -1;
/* 424 */     for (int i = 0; i < teamPlayerList.size(); i++) {
/* 425 */       if (teamPlayerList.get(i) != null && ((TeamPlayer)teamPlayerList.get(i)).getPlayerId() == targetPlayerId) {
/* 426 */         index = i;
/*     */         break;
/*     */       } 
/*     */     } 
/* 430 */     if (index == -1) {
/* 431 */       return 13706;
/*     */     }
/*     */     
/* 434 */     TeamKickoutNoticeResponse response = new TeamKickoutNoticeResponse();
/* 435 */     response.playerId = targetPlayerId;
/* 436 */     for (TeamPlayer teamPlayer : teamPlayerList) {
/* 437 */       if (teamPlayer != null && !teamPlayer.isRobot() && 
/* 438 */         !PlayerUtil.isRemotePlayer(teamPlayer.getPlayerId())) {
/* 439 */         LookUpService.getByPlayerId(teamPlayer.getPlayerId()).getSession().sendMessage((ResponseBase)response);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 446 */     team.removeTeamPlayer(index);
/* 447 */     team.setStatus(Team.TeamStatus.CAN_JOIN.getValue());
/* 448 */     removePlayerFromTeam(targetPlayerId);
/*     */ 
/*     */     
/* 451 */     return 0;
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
/* 463 */     Team team = getTeamById(teamId);
/* 464 */     if (team == null) {
/* 465 */       return 13701;
/*     */     }
/* 467 */     TeamPlayer teamPlayer = team.getTeamPlayerById(playerId);
/* 468 */     if (teamPlayer == null) {
/* 469 */       return 13706;
/*     */     }
/* 471 */     if (teamPlayer.isReady()) {
/* 472 */       return 13709;
/*     */     }
/* 474 */     if (fighterIdList.size() > (teamPlayer.getFighters()).length || teamPlayer.isRobot()) {
/* 475 */       return 13710;
/*     */     }
/* 477 */     if (team.isLeader(playerId)) {
/* 478 */       if (!fighterIdList.contains(Integer.valueOf(-1))) {
/* 479 */         return 13710;
/*     */       }
/* 481 */       for (int i = 0; i < fighterIdList.size(); i++) {
/* 482 */         if (((Integer)fighterIdList.get(i)).longValue() == -1L) {
/* 483 */           CommonFighterData commonFighterData = FightUtil.getFighterData(-1L, playerId, (byte)0);
/* 484 */           teamPlayer.getFighters()[i] = new TeamPlayerFighter(true, (IFighter)new PlayerFighter(commonFighterData, (byte)(teamPlayer.getPos() + 3 * i)));
/*     */         } else {
/* 486 */           CommonFighterData commonFighterData = FightUtil.getFighterData(((Integer)fighterIdList.get(i)).intValue(), playerId, (byte)1);
/* 487 */           teamPlayer.getFighters()[i] = new TeamPlayerFighter(false, (IFighter)new FighterFighter(commonFighterData, (byte)(teamPlayer.getPos() + 3 * i), ((Integer)fighterIdList.get(i)).intValue()));
/*     */         } 
/*     */       } 
/*     */     } else {
/* 491 */       for (int i = 0; i < fighterIdList.size(); i++) {
/* 492 */         CommonFighterData commonFighterData = FightUtil.getFighterData(((Integer)fighterIdList.get(i)).intValue(), playerId, (byte)1);
/* 493 */         teamPlayer.getFighters()[i] = new TeamPlayerFighter(false, (IFighter)new FighterFighter(commonFighterData, (byte)(teamPlayer.getPos() + 3 * i), ((Integer)fighterIdList.get(i)).intValue()));
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 498 */     TeamFighterChangeNoticeResponse response = new TeamFighterChangeNoticeResponse();
/* 499 */     response.playerId = playerId;
/* 500 */     response.fighters.addAll(createTeamFighterDataList(teamPlayer));
/* 501 */     for (TeamPlayer p : team.getTeamPlayers()) {
/* 502 */       if (p != null && !p.isRobot() && 
/* 503 */         !PlayerUtil.isRemotePlayer(p.getPlayerId())) {
/* 504 */         LookUpService.getByPlayerId(p.getPlayerId()).getSession().sendMessage((ResponseBase)response);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 510 */     return 0;
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
/* 522 */     Team team = getTeamById(teamId);
/* 523 */     if (team == null) {
/* 524 */       team = getTeamByPlayerIdExact(playerId);
/*     */     }
/* 526 */     if (team == null) {
/* 527 */       return 13701;
/*     */     }
/*     */     try {
/* 530 */       team.lock();
/* 531 */       if (team.isLeader(playerId)) {
/* 532 */         team.removeTeamPlayer(0);
/* 533 */         int index = -1;
/* 534 */         List<TeamPlayer> teamPlayerList = team.getTeamPlayers(); int i;
/* 535 */         for (i = 1; i < teamPlayerList.size(); i++) {
/* 536 */           if (teamPlayerList.get(i) != null && !((TeamPlayer)teamPlayerList.get(i)).isRobot()) {
/* 537 */             index = i;
/*     */             break;
/*     */           } 
/*     */         } 
/* 541 */         if (index == -1) {
/* 542 */           for (ConcurrentMap<Long, Team> map : INS_TO_TEAM_MAP.values()) {
/* 543 */             map.remove(Long.valueOf(team.getTeamId()));
/*     */           }
/* 545 */           removePlayerFromTeam(playerId);
/* 546 */           i = 0; return i;
/*     */         } 
/* 548 */         if (teamPlayerList.get(index) != null) {
/* 549 */           long targetPlayerId = ((TeamPlayer)teamPlayerList.get(index)).getPlayerId();
/* 550 */           team.removeTeamPlayer(index);
/* 551 */           initTeamLeader(targetPlayerId, team);
/*     */           
/* 553 */           sendTeamPlayerDataNotice(team);
/*     */         } 
/*     */       } else {
/*     */         
/* 557 */         TeamPlayer teamPlayer = team.getTeamPlayerById(playerId);
/* 558 */         if (teamPlayer == null) {
/* 559 */           return 13706;
/*     */         }
/* 561 */         team.removeTeamPlayer(teamPlayer.getPos() - 1);
/*     */ 
/*     */         
/* 564 */         TeamKickoutNoticeResponse response = new TeamKickoutNoticeResponse();
/* 565 */         response.playerId = playerId;
/* 566 */         for (TeamPlayer tp : team.getTeamPlayers()) {
/* 567 */           if (tp != null && !tp.isRobot() && 
/* 568 */             !PlayerUtil.isRemotePlayer(tp.getPlayerId())) {
/* 569 */             LookUpService.getByPlayerId(tp.getPlayerId()).getSession().sendMessage((ResponseBase)response);
/*     */           }
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 576 */       team.setStatus(Team.TeamStatus.CAN_JOIN.getValue());
/* 577 */       removePlayerFromTeam(playerId);
/*     */     } finally {
/* 579 */       team.unlock();
/*     */     } 
/* 581 */     return 0;
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
/* 592 */     int insId = getInsIdByTeamId(team.getTeamId());
/* 593 */     if (insId == -1) {
/* 594 */       return 13701;
/*     */     }
/* 596 */     if (team.isFull()) {
/* 597 */       return 0;
/*     */     }
/*     */     
/* 600 */     MultiInsBean multiInsBean = (MultiInsBean)JsonTableService.getJsonData(insId, MultiInsBean.class);
/* 601 */     if (multiInsBean == null) {
/* 602 */       return 13712;
/*     */     }
/* 604 */     int robotNum = multiInsBean.getRobotId().size(), robotId = 0;
/*     */     try {
/* 606 */       team.lock();
/* 607 */       List<TeamPlayer> teamPlayerList = team.getTeamPlayers();
/* 608 */       for (int i = 0; i < teamPlayerList.size(); i++) {
/* 609 */         if (teamPlayerList.get(i) == null) {
/* 610 */           if (robotNum > 1) {
/* 611 */             robotId = ((Integer)multiInsBean.getRobotId().get(RandUtil.randNum(robotNum - 1))).intValue();
/*     */           } else {
/* 613 */             robotId = ((Integer)multiInsBean.getRobotId().get(0)).intValue();
/*     */           } 
/* 615 */           MultiRobotBean robotBean = (MultiRobotBean)JsonTableService.getJsonData(robotId, MultiRobotBean.class);
/* 616 */           IFighter[] fighters = new IFighter[2];
/* 617 */           if (robotBean.getMonster().size() != 2) {
/* 618 */             return 13713;
/*     */           }
/* 620 */           for (int j = 0; j < robotBean.getMonster().size(); j++) {
/* 621 */             int pos = i + 1 + j * 3;
/* 622 */             int monsterId = ((Integer)robotBean.getMonster().get(j)).intValue();
/* 623 */             fighters[j] = (IFighter)new MonsterFighter(monsterId, (byte)pos);
/*     */           } 
/* 625 */           int fightValue = RandUtil.randNum(((MultiRobotBean.FightingBean)robotBean.getFighting().get(0)).getLow(), ((MultiRobotBean.FightingBean)robotBean.getFighting().get(0)).getHight());
/* 626 */           int level = RandUtil.randNum(((MultiRobotBean.LevelBean)robotBean.getLevel().get(0)).getLow(), ((MultiRobotBean.LevelBean)robotBean.getLevel().get(0)).getHight());
/* 627 */           team.setTeamPlayer(i, true, generateRobotPlayerId(), (byte)0, (byte)1, "head=sex:" + robotBean.getHead(), "玩家" + RandUtil.randNum(), (short)level, fightValue, robotBean.getQuality(), 0, 0, fighters, null, null, null, null);
/*     */         } 
/* 629 */         team.setStatus(Team.TeamStatus.FULL.getValue());
/*     */       } 
/*     */     } finally {
/* 632 */       team.unlock();
/*     */     } 
/* 634 */     sendTeamPlayerDataNotice(team);
/* 635 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static List<TeamFighterData> createTeamFighterDataList(TeamPlayer teamPlayer) {
/* 645 */     List<TeamFighterData> teamFighterDataList = new ArrayList<>();
/* 646 */     for (TeamPlayerFighter teamPlayerFighter : teamPlayer.getFighters()) {
/* 647 */       TeamFighterData teamFighterData = new TeamFighterData();
/* 648 */       if (teamPlayerFighter != null) {
/* 649 */         if (!teamPlayer.isRobot()) {
/* 650 */           FighterBean fighterBean = (FighterBean)JsonTableService.getJsonData((int)teamPlayerFighter.getFighter().getId(), FighterBean.class);
/* 651 */           teamFighterData.fighterId = (int)teamPlayerFighter.getFighter().getId();
/* 652 */           teamFighterData.pid = (int)teamPlayerFighter.getFighter().getPid();
/* 653 */           teamFighterData.pos = (byte)teamPlayerFighter.getPos();
/* 654 */           teamFighterData.name = teamPlayerFighter.isLead() ? teamPlayer.getName() : fighterBean.getName();
/*     */         } else {
/* 656 */           MonsterBean monsterBean = (MonsterBean)JsonTableService.getJsonData((int)teamPlayerFighter.getFighter().getId(), MonsterBean.class);
/* 657 */           teamFighterData.fighterId = (int)teamPlayerFighter.getFighter().getId();
/* 658 */           teamFighterData.pid = (int)teamPlayerFighter.getFighter().getPid();
/* 659 */           teamFighterData.pos = (byte)teamPlayerFighter.getPos();
/* 660 */           teamFighterData.name = monsterBean.getName();
/*     */         } 
/*     */       }
/* 663 */       teamFighterDataList.add(teamFighterData);
/*     */     } 
/* 665 */     return teamFighterDataList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void teamFightEnd(Team team) {
/* 674 */     boolean teamPlayerChange = false;
/*     */     try {
/* 676 */       team.lock();
/* 677 */       List<TeamPlayer> teamPlayerList = team.getTeamPlayers();
/* 678 */       for (int i = 0; i < teamPlayerList.size(); i++) {
/* 679 */         if (teamPlayerList.get(i) != null) {
/* 680 */           teamPlayerChange = true;
/* 681 */           if (((TeamPlayer)teamPlayerList.get(i)).isRobot()) {
/* 682 */             team.removeTeamPlayer(i);
/*     */           } else {
/* 684 */             ((TeamPlayer)teamPlayerList.get(i)).setReady(false);
/* 685 */             ((TeamPlayer)teamPlayerList.get(i)).setNeedReInit(true);
/*     */           } 
/*     */         } 
/*     */       } 
/* 689 */       if (teamPlayerChange) {
/* 690 */         sendTeamPlayerDataNotice(team);
/*     */       }
/* 692 */       team.setStatus(team.isFull() ? Team.TeamStatus.FULL.getValue() : Team.TeamStatus.CAN_JOIN.getValue());
/*     */     } finally {
/* 694 */       team.unlock();
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
/* 705 */     List<TeamPlayerData> teamPlayerDataList = new ArrayList<>();
/* 706 */     for (TeamPlayer teamPlayer : team.getTeamPlayers()) {
/* 707 */       if (teamPlayer != null) {
/* 708 */         TeamPlayerData teamPlayerData = new TeamPlayerData();
/* 709 */         teamPlayerData.playerId = teamPlayer.getPlayerId();
/* 710 */         teamPlayerData.head = teamPlayer.getHead();
/* 711 */         teamPlayerData.name = teamPlayer.getName();
/* 712 */         teamPlayerData.level = teamPlayer.getLevel();
/* 713 */         teamPlayerData.fightValue = teamPlayer.getFightValue();
/* 714 */         teamPlayerData.isLeader = (byte)(teamPlayer.isLeader() ? 1 : 0);
/* 715 */         teamPlayerData.status = (byte)(teamPlayer.isReady() ? 1 : 0);
/* 716 */         teamPlayerData.isRobot = (byte)(teamPlayer.isRobot() ? 1 : 0);
/* 717 */         teamPlayerData.quality = teamPlayer.getQuality();
/* 718 */         teamPlayerData.fashionId = teamPlayer.getFashionId();
/* 719 */         teamPlayerData.fighterList.addAll(createTeamFighterDataList(teamPlayer));
/* 720 */         teamPlayerDataList.add(teamPlayerData); continue;
/*     */       } 
/* 722 */       teamPlayerDataList.add(new TeamPlayerData());
/*     */     } 
/*     */     
/* 725 */     return teamPlayerDataList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void sendTeamPlayerDataNotice(Team team) {
/* 734 */     TeamPlayerDataNoticeResponse response = new TeamPlayerDataNoticeResponse();
/* 735 */     response.players.addAll(createTeamPlayerDataList(team));
/* 736 */     for (TeamPlayer tp : team.getTeamPlayers()) {
/* 737 */       if (tp != null && !tp.isRobot() && 
/* 738 */         !PlayerUtil.isRemotePlayer(tp.getPlayerId())) {
/* 739 */         LookUpService.getByPlayerId(tp.getPlayerId()).getSession().sendMessage((ResponseBase)response);
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
/*     */   public static void sendTeamPlayerReadyNotice(Team team, long playerId, byte isReady) {
/* 755 */     TeamPlayerReadyNoticeResponse teamPlayerReadyNoticeResponse = new TeamPlayerReadyNoticeResponse();
/* 756 */     teamPlayerReadyNoticeResponse.isReady = isReady;
/* 757 */     teamPlayerReadyNoticeResponse.teamId = team.getTeamId();
/* 758 */     teamPlayerReadyNoticeResponse.playerId = playerId;
/* 759 */     for (TeamPlayer tp : team.getTeamPlayers()) {
/* 760 */       if (tp != null && !tp.isRobot() && 
/* 761 */         !PlayerUtil.isRemotePlayer(tp.getPlayerId())) {
/* 762 */         LookUpService.getByPlayerId(tp.getPlayerId()).getSession().sendMessage((ResponseBase)teamPlayerReadyNoticeResponse);
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
/*     */   public static void sendDoubletimeNotice() {
/* 774 */     TeamParameter teamParameter = (TeamParameter)ParameterConstant.getParameter(37);
/* 775 */     if (teamParameter.isInDoubleTime(TimeUtil.getNowMinutes())) {
/* 776 */       if (!hasSendNotice) {
/* 777 */         LookUpService.radiate(3702, new ArrayList());
/* 778 */         hasSendNotice = true;
/*     */       }
/*     */     
/* 781 */     } else if (hasSendNotice) {
/* 782 */       hasSendNotice = false;
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
/*     */   public static void sendCreateTeamChatNotice(long playerId, int insId, long teamId, int insType) {
/*     */     String context;
/* 797 */     if (insType == 1) {
/* 798 */       MultiInsBean multiInsBean = (MultiInsBean)JsonTableService.getJsonData(insId, MultiInsBean.class);
/* 799 */       int languageId = 3701;
/* 800 */       context = LanguageConstant.getAndReplaceLanguage(languageId, new String[] { String.valueOf(multiInsBean.getLevel()), String.valueOf(insId), String.valueOf(teamId) });
/*     */     } else {
/* 802 */       BaguaInsBean baguaInsBean = (BaguaInsBean)JsonTableService.getJsonData(insId, BaguaInsBean.class);
/* 803 */       int languageId = 3703;
/* 804 */       context = LanguageConstant.getAndReplaceLanguage(languageId, new String[] { baguaInsBean.getName(), String.valueOf(insId), String.valueOf(teamId) });
/*     */     } 
/* 806 */     Team team = getTeamById(teamId);
/* 807 */     if (team != null) {
/* 808 */       TeamPlayer leader = team.getLeader();
/* 809 */       ChatByChannelResponse response = new ChatByChannelResponse();
/*     */       
/* 811 */       ArrayList<ChatInfo> list = new ArrayList<>();
/* 812 */       ChatInfo chatInfo = new ChatInfo();
/* 813 */       chatInfo.type = 0;
/* 814 */       chatInfo.sendPlayerId = playerId;
/* 815 */       chatInfo.sendPlayerName = leader.getName();
/* 816 */       chatInfo.vip = leader.getVip();
/*     */       
/* 818 */       chatInfo.sex = leader.getSex();
/* 819 */       chatInfo.head = leader.getHead();
/* 820 */       chatInfo.context = context;
/* 821 */       chatInfo.time = TimeUtil.currentTime();
/* 822 */       chatInfo.teamChat = 1;
/* 823 */       chatInfo.quality = leader.getQuality();
/* 824 */       list.add(chatInfo);
/* 825 */       response.list = list;
/* 826 */       LookUpService.broadcast((ResponseBase)response);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\team\TeamUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */