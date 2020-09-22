/*      */ package com.linlongyx.sanguo.webgame.processors.fight;
/*      */ import com.linlongyx.core.framework.logic.IPlayer;
/*      */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*      */ import com.linlongyx.core.utils.LogUtils;
/*      */ import com.linlongyx.core.utils.TimeUtil;
/*      */ import com.linlongyx.sanguo.webgame.app.arena.ArenaComponent;
/*      */ import com.linlongyx.sanguo.webgame.app.bossHome.BossHomeComponent;
/*      */ import com.linlongyx.sanguo.webgame.app.crossRace.CrossRaceComponent;
/*      */ import com.linlongyx.sanguo.webgame.app.destiny.DestinyComponent;
/*      */ import com.linlongyx.sanguo.webgame.app.eightGraphic.BaguaComponent;
/*      */ import com.linlongyx.sanguo.webgame.app.extend.ExtendComponent;
/*      */ import com.linlongyx.sanguo.webgame.app.general.GeneralComponent;
/*      */ import com.linlongyx.sanguo.webgame.app.group.GroupEntity;
/*      */ import com.linlongyx.sanguo.webgame.app.group.GroupMemberComponent;
/*      */ import com.linlongyx.sanguo.webgame.app.partner.PartnerComponent;
/*      */ import com.linlongyx.sanguo.webgame.app.partner.PartnerEntity;
/*      */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*      */ import com.linlongyx.sanguo.webgame.app.rune.RuneComponent;
/*      */ import com.linlongyx.sanguo.webgame.app.secreti.SecretiComponent;
/*      */ import com.linlongyx.sanguo.webgame.app.singleIns.SingleInsComponent;
/*      */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*      */ import com.linlongyx.sanguo.webgame.app.tower.TowerComponent;
/*      */ import com.linlongyx.sanguo.webgame.app.unparalleled.UnparalleledComponent;
/*      */ import com.linlongyx.sanguo.webgame.app.yearbeast.YearBeastComponent;
/*      */ import com.linlongyx.sanguo.webgame.app.yearbeast.YearBeastEntity;
/*      */ import com.linlongyx.sanguo.webgame.common.fight.type.InstanceFight;
/*      */ import com.linlongyx.sanguo.webgame.common.team.Team;
/*      */ import com.linlongyx.sanguo.webgame.common.team.TeamPlayer;
/*      */ import com.linlongyx.sanguo.webgame.common.team.TeamUtil;
/*      */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.BaguaChapterBean;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.BaguaInsBean;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.BossHomeBean;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.CrossRankBean;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.DestinyWarBean;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.GeneralInsBean;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.MainInsBean;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.MultiInsBean;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.NeutralBossBean;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.PersonalInsBean;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.RewardBean;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.RuneWarBean;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.SecretiBean;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.TowerBean;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.WushuangInsBean;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.YearBeastBean;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.YearBeastListBean;
/*      */ import com.linlongyx.sanguo.webgame.config.parameter.ArenaParameter;
/*      */ import com.linlongyx.sanguo.webgame.config.parameter.BossHomeParameter;
/*      */ import com.linlongyx.sanguo.webgame.config.parameter.GeneralParameter;
/*      */ import com.linlongyx.sanguo.webgame.config.parameter.RunewarParameter;
/*      */ import com.linlongyx.sanguo.webgame.config.parameter.TaskParameter;
/*      */ import com.linlongyx.sanguo.webgame.config.parameter.TeamParameter;
/*      */ import com.linlongyx.sanguo.webgame.constant.MilitaryInsType;
/*      */ import com.linlongyx.sanguo.webgame.constant.MilitaryInteriorType;
/*      */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*      */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*      */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*      */ import com.linlongyx.sanguo.webgame.processors.bagua.BaguaUtil;
/*      */ import com.linlongyx.sanguo.webgame.processors.cross.CrossUtil;
/*      */ import com.linlongyx.sanguo.webgame.processors.offices.MilitaryUtil;
/*      */ import com.linlongyx.sanguo.webgame.processors.player.VersionUtil;
/*      */ import com.linlongyx.sanguo.webgame.processors.task.FindRewardType;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.fight.FightRecordRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.fight.FightRecordResponse;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.struct.ArenaData;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.struct.BattleRecordData;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.struct.LongStringValue;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*      */ import com.linlongyx.sanguo.webgame.rmi.data.DestinyPlayerData;
/*      */ import com.linlongyx.sanguo.webgame.rmi.data.PkRecord;
/*      */ import com.linlongyx.sanguo.webgame.rmi.data.PlayerData;
/*      */ import com.linlongyx.sanguo.webgame.rmi.data.RacePlayerData;
/*      */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*      */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Iterator;
/*      */ import java.util.ListIterator;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ 
/*      */ public class FightRecordProcessor extends ProcessorBase<FightRecordRequest, FightRecordResponse> {
/*      */   protected void initResponse() {
/*   84 */     this.response = (ResponseBase)new FightRecordResponse();
/*      */   }
/*      */ 
/*      */   
/*      */   protected short handleRequest(IPlayerSession playerSession, FightRecordRequest request) {
/*   89 */     byte type = request.type;
/*   90 */     ((FightRecordResponse)this.response).type = type;
/*   91 */     ((FightRecordResponse)this.response).id = request.id;
/*   92 */     short res = 0;
/*   93 */     if (type == 1) {
/*   94 */       res = handleMainIns(Integer.parseInt(request.id), playerSession, request);
/*   95 */     } else if (type == 2) {
/*   96 */       res = handleNormalIns(Integer.parseInt(request.id), playerSession, request);
/*   97 */     } else if (type == 3) {
/*   98 */       res = handleRankBoss(Integer.parseInt(request.id), playerSession, request);
/*   99 */     } else if (type == 4) {
/*  100 */       res = handleArena(Integer.parseInt(request.id), playerSession);
/*  101 */     } else if (type == 5) {
/*  102 */       res = handleUnparalleledIns(Integer.parseInt(request.id), playerSession, request);
/*  103 */     } else if (type == 6) {
/*  104 */       res = handleWorldBoss(playerSession);
/*  105 */     } else if (type == 7) {
/*  106 */       res = handleGeneralIns(Integer.parseInt(request.id), playerSession, request);
/*  107 */     } else if (type == 8) {
/*  108 */       res = handleSingleBoss(Integer.parseInt(request.id), playerSession, request);
/*  109 */     } else if (type == 9) {
/*  110 */       res = handleTowerFight(Integer.parseInt(request.id), playerSession, request);
/*  111 */     } else if (type == 10) {
/*  112 */       res = handleTeamFight(playerSession.getPlayer(), Integer.parseInt(request.id));
/*  113 */     } else if (type == 11) {
/*  114 */       res = handleDestinyFight(playerSession.getPlayer(), Long.parseLong(request.id));
/*  115 */     } else if (type == 12) {
/*  116 */       res = handleMatchFight(playerSession.getPlayer(), request.id);
/*  117 */     } else if (type == 13) {
/*  118 */       res = handleBaguaFight(playerSession.getPlayer(), Integer.parseInt(request.id));
/*  119 */     } else if (type == 14) {
/*  120 */       res = handleGroupFight(playerSession.getPlayer(), Integer.parseInt(request.id));
/*  121 */     } else if (type == 15) {
/*  122 */       res = handleAlienRaceBoss(Integer.parseInt(request.id), playerSession, request);
/*  123 */     } else if (type == 16) {
/*  124 */       res = handleCrossRaceFight(Long.parseLong(request.id), playerSession);
/*  125 */     } else if (type == 17) {
/*  126 */       res = handleYearBeastBoss(Integer.parseInt(request.id), playerSession, request);
/*  127 */     } else if (type == 18) {
/*  128 */       res = handleTowerOwnerFight(Integer.parseInt(request.id), playerSession);
/*  129 */     } else if (type == 19) {
/*  130 */       res = handleSecretiFight(request.id, playerSession, request);
/*  131 */     } else if (type == 20) {
/*  132 */       res = handleRunewarFight(request.id, playerSession);
/*      */     } 
/*  134 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/*  135 */     if (((FightRecordResponse)this.response).result != 1 && taskComponent.getFirstFail() == 0) {
/*  136 */       taskComponent.setFirstFail(1);
/*      */     }
/*  138 */     return res;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private short handleMainIns(int insId, IPlayerSession playerSession, FightRecordRequest request) {
/*  149 */     IPlayer player = playerSession.getPlayer();
/*  150 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/*  151 */     if (insId <= taskComponent.getChapter()) {
/*  152 */       return 14016;
/*      */     }
/*  154 */     InstanceFight fight = new InstanceFight(insId, player);
/*  155 */     short retCode = fight.initFight();
/*  156 */     if (retCode != 0) {
/*  157 */       return retCode;
/*      */     }
/*  159 */     fight.start((FightRecordResponse)this.response);
/*      */     
/*  161 */     if (((FightRecordResponse)this.response).result == 1) {
/*      */       
/*  163 */       taskComponent.refreshSchedule(TaskType.MainIns, insId, 1L);
/*  164 */       MainInsBean mainInsBean = (MainInsBean)JsonTableService.getJsonData(insId, MainInsBean.class);
/*  165 */       if (null != mainInsBean && !mainInsBean.getChapteReward().isEmpty()) {
/*  166 */         taskComponent.getInsMap().put(Integer.valueOf(mainInsBean.getChapter()), Integer.valueOf(insId));
/*  167 */         taskComponent.setInsMap(taskComponent.getInsMap());
/*  168 */         taskComponent.refreshSchedule(TaskType.MainChapter, 0, 0L);
/*      */       } 
/*  170 */       if (null != mainInsBean) {
/*  171 */         taskComponent.setChapter(insId);
/*  172 */         PlayerUtil.updateKeyValueInfo(playerSession, KeyValueConstant.Chapter.getKey(), taskComponent.getChapter());
/*      */ 
/*      */         
/*  175 */         int star = 3;
/*  176 */         ArrayList<Reward> arrayList = new ArrayList<>();
/*  177 */         arrayList = FinanceUtil.transform(mainInsBean.getReward());
/*  178 */         int[] value = MilitaryUtil.getRewardResult(arrayList, MilitaryInsType.ThreadIns.getType(), player.getPlayerId(), 0);
/*  179 */         ArrayList<Reward> rewards = FinanceUtil.rewardGet(arrayList, playerSession.getPlayer(), ResourceEvent.MainInsChallenge, false);
/*  180 */         ResultUtil.saveResult(playerSession.getPlayer().getPlayerId(), request.type, insId, star, rewards, value[MilitaryInteriorType.Exp.ordinal()]);
/*      */       } 
/*      */     } 
/*  183 */     String str = fight.getDebugStr();
/*  184 */     System.out.println(str);
/*  185 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private short handleNormalIns(int insId, IPlayerSession playerSession, FightRecordRequest request) {
/*  196 */     PersonalInsBean personalInsBean = (PersonalInsBean)JsonTableService.getJsonData(insId, PersonalInsBean.class);
/*  197 */     if (null == personalInsBean) {
/*  198 */       return 13002;
/*      */     }
/*  200 */     int intType = personalInsBean.getIntType();
/*  201 */     if (!SingleInsUtil.isDayValue(personalInsBean.getDay())) {
/*  202 */       return 13005;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  208 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/*  209 */     if (playerComponent.getLevel() < personalInsBean.getLevel() || playerComponent.getLevel() > personalInsBean.getLevelMax()) {
/*  210 */       return 13007;
/*      */     }
/*  212 */     if (playerComponent.getVip() < personalInsBean.getVip()) {
/*  213 */       return 10088;
/*      */     }
/*  215 */     SingleInsComponent singleInsComponent = (SingleInsComponent)playerSession.getPlayer().createIfNotExist(SingleInsComponent.class);
/*  216 */     Map<Integer, Integer> maxPoints = singleInsComponent.getMaxPoints();
/*  217 */     int maxPoint = ((Integer)maxPoints.getOrDefault(Integer.valueOf(intType), Integer.valueOf(0))).intValue();
/*  218 */     if (personalInsBean.getCheckPoint() > maxPoint + 1) {
/*  219 */       return 13006;
/*      */     }
/*  221 */     Map<Integer, Integer> times = singleInsComponent.getTimes();
/*  222 */     int time = ((Integer)times.getOrDefault(Integer.valueOf(intType), Integer.valueOf(0))).intValue();
/*  223 */     int dailyTime = VipUtil.getNum(playerComponent.getVip(), 8);
/*  224 */     if (time >= dailyTime) {
/*  225 */       return 13004;
/*      */     }
/*      */     
/*  228 */     NormalFight normalFight = new NormalFight(insId, playerSession.getPlayer());
/*  229 */     short code = normalFight.initFight();
/*  230 */     if (0 != code) {
/*  231 */       return code;
/*      */     }
/*  233 */     byte result = normalFight.start((FightRecordResponse)this.response);
/*  234 */     if (1 == result) {
/*  235 */       ArrayList<RewardBean> rewardBeans; if (null != singleInsComponent && maxPoint == 0) {
/*  236 */         TaskParameter taskParameter = (TaskParameter)ParameterConstant.getParameter(22);
/*  237 */         int maxTimes = TaskUtil.getMaxCheckPoint(personalInsBean.getIntType(), playerSession.getPlayer().getPlayerId());
/*  238 */         singleInsComponent.saveFindTimes(false, personalInsBean.getIntType(), taskParameter.getDays() * maxTimes);
/*      */       } 
/*  240 */       if (personalInsBean.getCheckPoint() > maxPoint) {
/*  241 */         maxPoints.put(Integer.valueOf(intType), Integer.valueOf(personalInsBean.getCheckPoint()));
/*  242 */         singleInsComponent.setMaxPoints(maxPoints);
/*      */       } 
/*      */       
/*  245 */       Set<Integer> clears = singleInsComponent.getClears();
/*      */       
/*  247 */       if (!clears.contains(Integer.valueOf(insId))) {
/*  248 */         rewardBeans = personalInsBean.getFirstReward();
/*  249 */         clears.add(Integer.valueOf(insId));
/*  250 */         singleInsComponent.setClears(clears);
/*      */       } else {
/*  252 */         rewardBeans = personalInsBean.getPassReward();
/*      */       } 
/*  254 */       int star = 3;
/*  255 */       ArrayList<Reward> arrayList = new ArrayList<>();
/*  256 */       arrayList = FinanceUtil.transform(rewardBeans);
/*  257 */       int[] value = MilitaryUtil.DailyPoint(arrayList, intType, playerSession, 0);
/*  258 */       ArrayList<Reward> rewards = FinanceUtil.rewardGet(arrayList, playerSession.getPlayer(), ResourceEvent.SingleInsSweep, false);
/*  259 */       ResultUtil.saveResult(playerSession.getPlayer().getPlayerId(), request.type, insId, star, rewards, value[MilitaryInteriorType.Exp.ordinal()]);
/*      */     } 
/*  261 */     time++;
/*  262 */     times.put(Integer.valueOf(intType), Integer.valueOf(time));
/*  263 */     singleInsComponent.setTimes(times);
/*  264 */     singleInsComponent.setTotalTime(singleInsComponent.getTotalTime() + 1);
/*  265 */     singleInsComponent.saveFindTimes(true, personalInsBean.getIntType(), 1);
/*      */     
/*  267 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/*  268 */     taskComponent.refreshSchedule(TaskType.ClearNormal, 0, 1L);
/*  269 */     taskComponent.refreshSchedule(TaskType.TotalNormal, 0, 0L);
/*  270 */     taskComponent.refreshSchedule(TaskType.NormalChallenge, personalInsBean.getIntType(), 1L);
/*  271 */     singleInsComponent.getTotalChallengeMap().put(Integer.valueOf(personalInsBean.getIntType()), Integer.valueOf(((Integer)singleInsComponent.getTotalChallengeMap().getOrDefault(Integer.valueOf(personalInsBean.getIntType()), Integer.valueOf(0))).intValue() + 1));
/*  272 */     singleInsComponent.setTotalChallengeMap(singleInsComponent.getTotalChallengeMap());
/*  273 */     taskComponent.refreshSchedule(TaskType.TotalNormalChallenge, personalInsBean.getIntType(), 1L);
/*  274 */     LogUtils.errorLog(new Object[] { "handleNormalIns", Long.valueOf(playerSession.getPlayer().getPlayerId()), Integer.valueOf(insId), Integer.valueOf(time), Byte.valueOf(((FightRecordResponse)this.response).result) });
/*  275 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private short handleRankBoss(int insId, IPlayerSession playerSession, FightRecordRequest request) {
/*  286 */     BossHomeBean bossHomeBean = (BossHomeBean)JsonTableService.getJsonData(insId, BossHomeBean.class);
/*  287 */     if (null == bossHomeBean) {
/*  288 */       return 10301;
/*      */     }
/*  290 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/*  291 */     if (playerComponent.getLevel() < bossHomeBean.getLevel()) {
/*  292 */       return 10084;
/*      */     }
/*  294 */     BossHomeComponent bossHomeComponent = (BossHomeComponent)playerSession.getPlayer().createIfNotExist(BossHomeComponent.class);
/*  295 */     BossHomeParameter bossHomeParameter = (BossHomeParameter)ParameterConstant.getParameter(3);
/*  296 */     Map<Integer, Integer> counts = bossHomeComponent.getCounts();
/*  297 */     Map<Integer, Integer> restores = bossHomeComponent.getRestores();
/*  298 */     int count = ((Integer)counts.getOrDefault(Integer.valueOf(bossHomeBean.getType()), Integer.valueOf(0))).intValue();
/*  299 */     int restore = ((Integer)restores.getOrDefault(Integer.valueOf(bossHomeBean.getType()), Integer.valueOf(0))).intValue();
/*  300 */     Map<Integer, Integer> buys = bossHomeComponent.getBuys();
/*  301 */     int buyCount = ((Integer)buys.getOrDefault(Integer.valueOf(1), Integer.valueOf(0))).intValue();
/*  302 */     int leftCount = bossHomeParameter.getRankMaxCount() + restore + buyCount - count;
/*  303 */     if (leftCount <= 0) {
/*  304 */       return 10089;
/*      */     }
/*  306 */     if (bossHomeBean.getType() == 1) {
/*  307 */       short code = BossUtil.handRankBossEnter(playerSession, insId, (FightRecordResponse)this.response);
/*  308 */       if (0 != code) {
/*  309 */         return code;
/*      */       }
/*      */     } 
/*  312 */     count++;
/*  313 */     counts.put(Integer.valueOf(bossHomeBean.getType()), Integer.valueOf(count));
/*  314 */     bossHomeComponent.setCounts(counts);
/*  315 */     Map<Integer, Integer> times = bossHomeComponent.getTimes();
/*  316 */     int time = ((Integer)times.getOrDefault(Integer.valueOf(bossHomeBean.getType()), Integer.valueOf(0))).intValue();
/*  317 */     if (0 == time) {
/*  318 */       time = TimeUtil.currentTime() + bossHomeParameter.getRankRestoreCD();
/*  319 */       times.put(Integer.valueOf(bossHomeBean.getType()), Integer.valueOf(time));
/*  320 */       bossHomeComponent.setTimes(times);
/*      */     } 
/*  322 */     if (1 == ((FightRecordResponse)this.response).result) {
/*  323 */       int star = 3;
/*  324 */       ResultUtil.saveResult(playerSession.getPlayer().getPlayerId(), request.type, insId, star, new ArrayList<>(), 0);
/*      */     } 
/*  326 */     ((FightRecordResponse)this.response).result = 1;
/*  327 */     ExtendComponent extendComponent = (ExtendComponent)playerSession.getPlayer().createIfNotExist(ExtendComponent.class);
/*  328 */     extendComponent.setTotalChangeBoss(extendComponent.getTotalChangeBoss() + 1L);
/*  329 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/*  330 */     taskComponent.refreshSchedule(TaskType.ChallengeRank, 0, 1L);
/*  331 */     taskComponent.refreshSchedule(TaskType.TotalChangeRankBoss, 0, 1L);
/*  332 */     LogUtils.errorLog(new Object[] { "handleRankBoss", Long.valueOf(playerSession.getPlayer().getPlayerId()), Integer.valueOf(insId), Integer.valueOf(count), Integer.valueOf(leftCount), Byte.valueOf(((FightRecordResponse)this.response).result) });
/*  333 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private short handleArena(int targetRank, IPlayerSession playerSession) {
/*  344 */     ArenaComponent arenaComponent = (ArenaComponent)playerSession.getPlayer().createIfNotExist(ArenaComponent.class);
/*  345 */     int rank = arenaComponent.getRank();
/*  346 */     ArenaParameter arenaParameter = (ArenaParameter)ParameterConstant.getParameter(17);
/*  347 */     int needRank = arenaParameter.getNeedRank(targetRank);
/*  348 */     if (0 != needRank && rank > needRank) {
/*  349 */       return 11711;
/*      */     }
/*  351 */     int dt = TimeUtil.currentTime() - arenaComponent.getLastTime();
/*  352 */     if (dt < arenaParameter.getRefreshCD()) {
/*  353 */       return 11708;
/*      */     }
/*  355 */     int buyTimes = arenaComponent.getBuyTimes();
/*  356 */     int fightTimes = arenaComponent.getFightTimes();
/*  357 */     if (fightTimes >= arenaParameter.getMaxChallengeTime() + buyTimes) {
/*  358 */       return 11707;
/*      */     }
/*      */     
/*  361 */     if (rank == targetRank) {
/*  362 */       return 11704;
/*      */     }
/*  364 */     ArenaData myArenaData = ArenaUtil.getArenaData(playerSession.getPlayer().getPlayerId());
/*  365 */     ArenaData targetArenaData = new ArenaData();
/*  366 */     short code = ArenaUtil.getTargetArenaPlayerData(rank, targetRank, targetArenaData, arenaComponent.getShowMap());
/*  367 */     if (0 != code) {
/*  368 */       return code;
/*      */     }
/*      */ 
/*      */     
/*  372 */     ArenaFight arenaFight = new ArenaFight(playerSession.getPlayer(), myArenaData, targetArenaData);
/*  373 */     code = arenaFight.check();
/*  374 */     if (0 != code) {
/*  375 */       ArenaUtil.releaseRanks(rank, targetRank);
/*  376 */       return code;
/*      */     } 
/*      */     
/*  379 */     fightTimes++;
/*  380 */     arenaComponent.setFightTimes(fightTimes);
/*  381 */     SingleInsComponent singleInsComponent = (SingleInsComponent)playerSession.getPlayer().createIfNotExist(SingleInsComponent.class);
/*  382 */     singleInsComponent.saveFindTimes(true, FindRewardType.ARENA.getType(), 1);
/*  383 */     ((FightRecordResponse)this.response).id = targetRank + "";
/*  384 */     byte result = arenaFight.start((FightRecordResponse)this.response);
/*  385 */     if (1 == result)
/*      */     {
/*  387 */       if (arenaComponent.getRank() <= 3) {
/*  388 */         PlayerUtil.sendNotice(8, playerSession.getPlayer(), arenaComponent.getRank(), null);
/*      */       }
/*      */     }
/*      */     
/*  392 */     ArenaUtil.releaseRanks(rank, targetRank);
/*  393 */     arenaComponent.setTotalFightTimes(arenaComponent.getTotalFightTimes() + 1);
/*  394 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/*  395 */     taskComponent.refreshSchedule(TaskType.ChallengeArena, 0, 1L);
/*  396 */     taskComponent.refreshSchedule(TaskType.TotalFightTimes, 0, 1L);
/*  397 */     LogUtils.errorLog(new Object[] { "handleArena", Long.valueOf(playerSession.getPlayer().getPlayerId()), Integer.valueOf(targetRank), Integer.valueOf(fightTimes), Byte.valueOf(((FightRecordResponse)this.response).result) });
/*  398 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private short handleUnparalleledIns(int insId, IPlayerSession playerSession, FightRecordRequest request) {
/*  410 */     WushuangInsBean wushuangInsBean = (WushuangInsBean)JsonTableService.getJsonData(insId, WushuangInsBean.class);
/*  411 */     if (null == wushuangInsBean) {
/*  412 */       return 13002;
/*      */     }
/*      */     
/*  415 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/*  416 */     if (wushuangInsBean.getLevel() > playerComponent.getLevel()) {
/*  417 */       return 13007;
/*      */     }
/*  419 */     UnparalleledComponent unparalleledComponent = (UnparalleledComponent)playerSession.getPlayer().createIfNotExist(UnparalleledComponent.class);
/*      */     
/*  421 */     if (wushuangInsBean.getCheckPoint() > unparalleledComponent.getCurPoint() + 1) {
/*  422 */       return 13006;
/*      */     }
/*      */     
/*  425 */     ListIterator<Long> partnerIt2 = unparalleledComponent.getBattlePartner().listIterator();
/*  426 */     boolean hash = false;
/*  427 */     while (partnerIt2.hasNext()) {
/*  428 */       Long partnerId = partnerIt2.next();
/*  429 */       if (partnerId.longValue() != 0L) {
/*  430 */         hash = true;
/*      */         break;
/*      */       } 
/*      */     } 
/*  434 */     if (!hash) {
/*  435 */       return 13304;
/*      */     }
/*      */     
/*  438 */     for (Iterator<Long> iterator = unparalleledComponent.getBattlePartner().iterator(); iterator.hasNext(); ) { long partnerId = ((Long)iterator.next()).longValue();
/*  439 */       if (unparalleledComponent.getPartneredHpMap().containsKey(Long.valueOf(partnerId)) && ((Long)unparalleledComponent.getPartneredHpMap().get(Long.valueOf(partnerId))).longValue() <= 0L) {
/*  440 */         return 14015;
/*      */       } }
/*      */ 
/*      */ 
/*      */     
/*  445 */     if (unparalleledComponent.getPlayedPoints().containsKey(Integer.valueOf(wushuangInsBean.getCheckPoint()))) {
/*  446 */       return 14016;
/*      */     }
/*      */     
/*  449 */     int res = wushuangInsBean.getCheckPoint() % 3;
/*  450 */     if (res != 0 && wushuangInsBean.getCheckPoint() > 3) {
/*  451 */       int lastPoint = wushuangInsBean.getCheckPoint() - res;
/*  452 */       if (unparalleledComponent.getLayerAddition().indexOf(Integer.valueOf(lastPoint)) < 0) {
/*  453 */         return 14017;
/*      */       }
/*      */     } 
/*      */     
/*  457 */     UnparalleledFight unparalleledFight = new UnparalleledFight(insId, playerSession.getPlayer());
/*      */     
/*  459 */     if (unparalleledComponent.getPartneredHpMap().isEmpty()) {
/*  460 */       PartnerComponent partnerComponent = (PartnerComponent)playerSession.getPlayer().createIfNotExist(PartnerComponent.class);
/*  461 */       long vals = playerComponent.getPlayerAttrUp().getAttrByType(AttributeType.HP.getType(), -1L);
/*  462 */       unparalleledComponent.getPartneredHpMap().put(Long.valueOf(-1L), Long.valueOf(vals));
/*      */       
/*  464 */       for (IMapEntity iMapEntity : partnerComponent.getEntityMap().values()) {
/*  465 */         PartnerEntity partnerEntity = (PartnerEntity)iMapEntity;
/*  466 */         if (null != partnerEntity) {
/*  467 */           long val = partnerComponent.getPartnerAttrUp().getAttrByType(AttributeType.HP.getType(), partnerEntity.getPid());
/*  468 */           unparalleledComponent.getPartneredHpMap().put(Long.valueOf(partnerEntity.getPid()), Long.valueOf(val));
/*  469 */           unparalleledComponent.setPartneredHpMap(unparalleledComponent.getPartneredHpMap());
/*      */         } 
/*      */       } 
/*      */     } 
/*  473 */     short code = unparalleledFight.initFight();
/*  474 */     if (0 != code) {
/*  475 */       return code;
/*      */     }
/*  477 */     byte result = unparalleledFight.start((FightRecordResponse)this.response);
/*  478 */     if (1 == result) {
/*  479 */       if (wushuangInsBean.getCheckPoint() > unparalleledComponent.getCurPoint()) {
/*  480 */         unparalleledComponent.setCurPoint(wushuangInsBean.getCheckPoint());
/*      */       }
/*  482 */       TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/*  483 */       if (wushuangInsBean.getCheckPoint() > unparalleledComponent.getMaxPoint()) {
/*  484 */         unparalleledComponent.setMaxPoint(wushuangInsBean.getCheckPoint());
/*  485 */         taskComponent.refreshSchedule(TaskType.UnparalleledMaxPoint, 0, 0L);
/*      */       } 
/*  487 */       unparalleledComponent.setChallengeNum(unparalleledComponent.getChallengeNum() + 1);
/*      */ 
/*      */ 
/*      */       
/*  491 */       ArrayList<RewardBean> passRewardBeans = wushuangInsBean.getPassReward();
/*      */       
/*  493 */       int point = wushuangInsBean.getCheckPoint();
/*  494 */       int pointStar = wushuangInsBean.getStar();
/*      */       
/*  496 */       SingleInsComponent singleInsComponent = (SingleInsComponent)playerSession.getPlayer().createIfNotExist(SingleInsComponent.class);
/*  497 */       if (null != singleInsComponent && unparalleledComponent.getInsMap().isEmpty()) {
/*  498 */         TaskParameter taskParameter = (TaskParameter)ParameterConstant.getParameter(22);
/*  499 */         singleInsComponent.saveFindTimes(false, FindRewardType.UNPARM.getType(), taskParameter.getDays());
/*      */       } 
/*  501 */       if (unparalleledComponent.getInsMap().containsKey(Integer.valueOf(point))) {
/*  502 */         WushuangInsBean wushuangInsBean1 = (WushuangInsBean)JsonTableService.getJsonData(((Integer)unparalleledComponent.getInsMap().get(Integer.valueOf(point))).intValue(), WushuangInsBean.class);
/*  503 */         if (wushuangInsBean1.getStar() < pointStar) {
/*  504 */           unparalleledComponent.getInsMap().put(Integer.valueOf(point), Integer.valueOf(wushuangInsBean.getInsId()));
/*  505 */           unparalleledComponent.setInsMap(unparalleledComponent.getInsMap());
/*  506 */           int star = pointStar - wushuangInsBean1.getStar();
/*  507 */           unparalleledComponent.setLastMaxStar(unparalleledComponent.getLastMaxStar() + star);
/*  508 */           unparalleledComponent.setLastPassTime(System.currentTimeMillis());
/*      */         
/*      */         }
/*      */ 
/*      */       
/*      */       }
/*      */       else {
/*      */         
/*  516 */         unparalleledComponent.setLastMaxStar(unparalleledComponent.getLastMaxStar() + pointStar);
/*  517 */         unparalleledComponent.getInsMap().put(Integer.valueOf(point), Integer.valueOf(wushuangInsBean.getInsId()));
/*  518 */         unparalleledComponent.setInsMap(unparalleledComponent.getInsMap());
/*  519 */         unparalleledComponent.setLastPassTime(System.currentTimeMillis());
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  527 */       unparalleledComponent.setSurpStar(unparalleledComponent.getSurpStar() + pointStar);
/*  528 */       unparalleledComponent.getPlayedPoints().put(Integer.valueOf(point), Integer.valueOf(TimeUtil.currentTime()));
/*  529 */       unparalleledComponent.setPlayedPoints(unparalleledComponent.getPlayedPoints());
/*      */       
/*  531 */       if (point % 3 == 0) {
/*  532 */         unparalleledComponent.createAttrbuteList();
/*      */       }
/*  534 */       ArrayList<Reward> arrayList = new ArrayList<>();
/*  535 */       arrayList = FinanceUtil.transform(passRewardBeans);
/*      */       
/*  537 */       int[] value = MilitaryUtil.getRewardResult(arrayList, MilitaryInsType.UnparaledIns.getType(), playerSession.getPlayer().getPlayerId(), 0);
/*  538 */       ArrayList<Reward> rewards = FinanceUtil.rewardGet(arrayList, playerSession.getPlayer(), ResourceEvent.SingleInsSweep, false);
/*  539 */       ResultUtil.saveResult(playerSession.getPlayer().getPlayerId(), request.type, insId, pointStar, rewards, value[MilitaryInteriorType.Exp.ordinal()]);
/*  540 */       unparalleledComponent.setCurrMaxStar(unparalleledComponent.getCurrMaxStar() + pointStar);
/*  541 */       unparalleledComponent.setLastPassTime(System.currentTimeMillis());
/*  542 */       taskComponent.refreshSchedule(TaskType.UnparalleledNum, 0, 0L);
/*  543 */       taskComponent.refreshSchedule(TaskType.UnparaMaxStar, 0, 0L);
/*      */     } 
/*      */     
/*  546 */     ListIterator<Long> partnerIt = unparalleledComponent.getBattlePartner().listIterator();
/*  547 */     while (partnerIt.hasNext()) {
/*  548 */       Long partnerId = partnerIt.next();
/*  549 */       if (unparalleledComponent.getPartneredHpMap().containsKey(partnerId) && ((Long)unparalleledComponent
/*  550 */         .getPartneredHpMap().get(partnerId)).longValue() <= 0L) {
/*  551 */         partnerIt.set(Long.valueOf(0L));
/*      */       }
/*      */     } 
/*  554 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private short handleWorldBoss(IPlayerSession playerSession) {
/*  564 */     BossHomeComponent bossHomeComponent = (BossHomeComponent)playerSession.getPlayer().createIfNotExist(BossHomeComponent.class);
/*  565 */     BossHomeParameter bossHomeParameter = (BossHomeParameter)ParameterConstant.getParameter(3);
/*  566 */     int worldBossTime = bossHomeComponent.getWorldBossTime();
/*  567 */     int curTime = TimeUtil.currentTime();
/*  568 */     if (curTime < worldBossTime) {
/*  569 */       return 10308;
/*      */     }
/*  571 */     bossHomeComponent.setWorldBossTime(curTime + bossHomeParameter.getWorldBattleSec());
/*  572 */     int leftCount = BossUtil.getWorldBossLeftCount(bossHomeComponent);
/*  573 */     if (leftCount <= 0) {
/*  574 */       return 10307;
/*      */     }
/*      */     
/*  577 */     WorldBossFightSide worldBossFightSide = WorldBossUtil.getWorldBossFightSide();
/*  578 */     if (worldBossFightSide == null) {
/*  579 */       return 10302;
/*      */     }
/*  581 */     short code = worldBossFightSide.handleFight(playerSession.getPlayer(), (FightRecordResponse)this.response);
/*  582 */     if (0 != code) {
/*  583 */       return code;
/*      */     }
/*  585 */     int type = 2;
/*  586 */     Map<Integer, Integer> counts = bossHomeComponent.getCounts();
/*  587 */     int fightCount = ((Integer)counts.getOrDefault(Integer.valueOf(type), Integer.valueOf(0))).intValue();
/*  588 */     fightCount++;
/*  589 */     counts.put(Integer.valueOf(type), Integer.valueOf(fightCount));
/*  590 */     bossHomeComponent.setCounts(counts);
/*  591 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/*  592 */     taskComponent.refreshSchedule(TaskType.ChallengeWorld, 0, 1L);
/*  593 */     LogUtils.errorLog(new Object[] { "handleWorldBoss", Long.valueOf(playerSession.getPlayer().getPlayerId()), Integer.valueOf(fightCount), Byte.valueOf(((FightRecordResponse)this.response).result) });
/*  594 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private short handleGeneralIns(int insId, IPlayerSession playerSession, FightRecordRequest request) {
/*  606 */     GeneralInsBean generalInsBean = (GeneralInsBean)JsonTableService.getJsonData(insId, GeneralInsBean.class);
/*  607 */     if (null == generalInsBean) {
/*  608 */       return 11203;
/*      */     }
/*  610 */     GeneralParameter generalParameter = (GeneralParameter)ParameterConstant.getParameter(12);
/*  611 */     GeneralComponent generalComponent = (GeneralComponent)playerSession.getPlayer().createIfNotExist(GeneralComponent.class);
/*  612 */     Map<Integer, Integer> stars = generalComponent.getStars();
/*  613 */     if (generalComponent.getCurChapter() == 0) {
/*  614 */       GeneralTotalInfoRequest request1 = new GeneralTotalInfoRequest();
/*  615 */       request1.level = generalInsBean.getDifficult();
/*  616 */       (new GeneralTotalInfoProcessor()).handle(playerSession, (RequestBase)request1);
/*      */     } 
/*  618 */     if (generalInsBean.getDifficult() == 2) {
/*  619 */       if (!FunctionOpenConstant.isFunctionOpen(playerSession, 1201)) {
/*  620 */         return 10061;
/*      */       }
/*  622 */     } else if (generalInsBean.getDifficult() == 3 && 
/*  623 */       !FunctionOpenConstant.isFunctionOpen(playerSession, 1202)) {
/*  624 */       return 10061;
/*      */     } 
/*      */     
/*  627 */     if (generalInsBean.getFrontIns() != 0 && !stars.containsKey(Integer.valueOf(generalInsBean.getFrontIns()))) {
/*  628 */       return 11210;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  633 */     Map<Integer, Integer> pointIdMap = generalParameter.getPointIdMap(generalInsBean.getChapter());
/*  634 */     if (null == pointIdMap) {
/*  635 */       return 11203;
/*      */     }
/*  637 */     if (generalInsBean.getCheckPoint() > 1 && 
/*  638 */       pointIdMap.containsKey(Integer.valueOf(generalInsBean.getCheckPoint() - 1))) {
/*  639 */       int preId = ((Integer)pointIdMap.get(Integer.valueOf(generalInsBean.getCheckPoint() - 1))).intValue();
/*  640 */       if (((Integer)stars.getOrDefault(Integer.valueOf(preId), Integer.valueOf(0))).intValue() == 0) {
/*  641 */         return 11211;
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  646 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/*  647 */     if (generalInsBean.getLimit() > playerComponent.getLevel()) {
/*  648 */       return 10084;
/*      */     }
/*  650 */     Map<Integer, Integer> challenges = generalComponent.getChallenges();
/*  651 */     int costOrder = generalComponent.getCostOrder();
/*  652 */     int restore = generalComponent.getRestore();
/*  653 */     int buyTime = generalComponent.getBuyTime();
/*  654 */     int maxOrder = generalParameter.getMaxOrder();
/*  655 */     int leftOrder = maxOrder + buyTime + restore - costOrder;
/*  656 */     if (leftOrder < generalInsBean.getCost()) {
/*  657 */       return 11206;
/*      */     }
/*      */     
/*  660 */     Map<Integer, Integer> resetTimes = generalComponent.getResetTimes();
/*  661 */     int challenge = ((Integer)challenges.getOrDefault(Integer.valueOf(insId), Integer.valueOf(0))).intValue();
/*  662 */     int resetTime = ((Integer)resetTimes.getOrDefault(Integer.valueOf(insId), Integer.valueOf(0))).intValue();
/*  663 */     int leftNum = generalInsBean.getDailyTimes() + resetTime * generalInsBean.getDailyTimes() - challenge;
/*  664 */     if (leftNum <= 0) {
/*  665 */       return 11205;
/*      */     }
/*      */ 
/*      */     
/*  669 */     GeneralFight generalFight = new GeneralFight(insId, playerSession.getPlayer());
/*  670 */     short code = generalFight.initFight();
/*  671 */     if (0 != code) {
/*  672 */       return code;
/*      */     }
/*  674 */     int star = generalFight.start((FightRecordResponse)this.response);
/*  675 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/*  676 */     if (star > 0) {
/*      */       
/*  678 */       challenge++;
/*  679 */       challenges.put(Integer.valueOf(insId), Integer.valueOf(challenge));
/*  680 */       generalComponent.setChallenges(challenges);
/*      */       
/*  682 */       costOrder += generalInsBean.getCost();
/*  683 */       generalComponent.setCostOrder(costOrder);
/*      */ 
/*      */       
/*  686 */       if (generalComponent.getNextTime() == 0) {
/*  687 */         int refuseTime = MilitaryUtil.getTime(playerSession.getPlayer().getPlayerId(), MilitaryInsType.GeneralIns.getType(), 0);
/*  688 */         generalComponent.setNextTime(TimeUtil.currentTime() + generalParameter.getRestoreCd() - refuseTime);
/*      */       } 
/*      */ 
/*      */       
/*  692 */       if (((Integer)stars.getOrDefault(Integer.valueOf(insId), (V)Integer.valueOf(0))).intValue() < star) {
/*  693 */         stars.put(Integer.valueOf(insId), Integer.valueOf(star));
/*  694 */         generalComponent.setStars(stars);
/*      */         
/*  696 */         int sum = 0;
/*  697 */         for (Map.Entry<Integer, Integer> entry : stars.entrySet()) {
/*  698 */           GeneralInsBean generalInsBeanSt = (GeneralInsBean)JsonTableService.getJsonData(((Integer)entry.getKey()).intValue(), GeneralInsBean.class);
/*  699 */           if (null != generalInsBeanSt && generalInsBean.getDifficult() == generalInsBeanSt.getDifficult()) {
/*  700 */             sum += ((Integer)entry.getValue()).intValue();
/*      */           }
/*      */         } 
/*  703 */         if (generalInsBean.getDifficult() == 1) {
/*  704 */           generalComponent.setAllStar(sum);
/*  705 */         } else if (generalInsBean.getDifficult() == 2) {
/*  706 */           generalComponent.setDifficultyStar(sum);
/*  707 */         } else if (generalInsBean.getDifficult() == 3) {
/*  708 */           generalComponent.setNightmareStar(sum);
/*      */         } 
/*      */       } 
/*      */       
/*  712 */       ArrayList<Reward> rewards = new ArrayList<>();
/*  713 */       GeneralUtil.getReward(generalInsBean, rewards);
/*      */       
/*  715 */       int[] value = MilitaryUtil.getRewardResult(rewards, MilitaryInsType.GeneralIns.getType(), playerSession.getPlayer().getPlayerId(), 0);
/*  716 */       ArrayList<Reward> returns = FinanceUtil.rewardGet(rewards, playerSession.getPlayer(), ResourceEvent.SingleInsSweep, false);
/*  717 */       ResultUtil.saveResult(playerSession.getPlayer().getPlayerId(), request.type, insId, star, returns, value[MilitaryInteriorType.Exp.ordinal()]);
/*      */       
/*  719 */       taskComponent.refreshSchedule(TaskType.GeneralChapter, 0, 0L);
/*  720 */       LogUtils.errorLog(new Object[] { "handleGeneralIns", Long.valueOf(playerSession.getPlayer().getPlayerId()), Integer.valueOf(insId), Integer.valueOf(challenge), Integer.valueOf(star) });
/*      */     } 
/*      */     
/*  723 */     generalComponent.setTotalNum(generalComponent.getTotalNum() + 1);
/*  724 */     taskComponent.refreshSchedule(TaskType.GeneralChallenge, 0, 1L);
/*  725 */     taskComponent.refreshSchedule(TaskType.OnGeneralChallege, 0, 1L);
/*  726 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private short handleSingleBoss(int insId, IPlayerSession playerSession, FightRecordRequest request) {
/*  738 */     BossHomeBean bossHomeBean = (BossHomeBean)JsonTableService.getJsonData(insId, BossHomeBean.class);
/*  739 */     if (null == bossHomeBean) {
/*  740 */       return 10301;
/*      */     }
/*  742 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/*  743 */     if (playerComponent.getLevel() < bossHomeBean.getLevel()) {
/*  744 */       return 10084;
/*      */     }
/*  746 */     SingleInsComponent singleInsComponent = (SingleInsComponent)playerSession.getPlayer().createIfNotExist(SingleInsComponent.class);
/*  747 */     int currTimes = SingleInsUtil.getChallengeTimes(playerSession.getPlayer(), insId);
/*  748 */     if (((Integer)singleInsComponent.getTimesMap().getOrDefault(Integer.valueOf(insId), (V)Integer.valueOf(0))).intValue() >= currTimes) {
/*  749 */       return 13116;
/*      */     }
/*  751 */     SingleBossFight singleBossFight = new SingleBossFight(insId, playerSession.getPlayer());
/*  752 */     short code = singleBossFight.initFight();
/*  753 */     if (0 != code) {
/*  754 */       return code;
/*      */     }
/*  756 */     byte result = singleBossFight.start((FightRecordResponse)this.response);
/*  757 */     if (1 == result) {
/*  758 */       singleInsComponent.getBossMap().put(Integer.valueOf(insId), Integer.valueOf(TimeUtil.currentTime()));
/*  759 */       singleInsComponent.setBossMap(singleInsComponent.getBossMap());
/*  760 */       singleInsComponent.getTimesMap().put(Integer.valueOf(insId), Integer.valueOf(((Integer)singleInsComponent.getTimesMap().getOrDefault(Integer.valueOf(insId), Integer.valueOf(0))).intValue() + 1));
/*  761 */       singleInsComponent.setTimesMap(singleInsComponent.getTimesMap());
/*  762 */       ArrayList<Reward> arrayList = new ArrayList<>();
/*  763 */       arrayList = FinanceUtil.transform(bossHomeBean.getLastHitReward());
/*  764 */       int[] value = MilitaryUtil.getRewardResult(arrayList, MilitaryInsType.SingleBoss.getType(), playerSession.getPlayer().getPlayerId(), 0);
/*  765 */       ArrayList<Reward> rewards = FinanceUtil.rewardGet(arrayList, playerSession.getPlayer(), ResourceEvent.SingleBossSweep, false);
/*  766 */       ResultUtil.saveResult(playerSession.getPlayer().getPlayerId(), request.type, insId, 3, rewards, value[MilitaryInteriorType.Exp.ordinal()]);
/*  767 */       if (singleInsComponent.getBossMap().size() == 1) {
/*  768 */         TaskParameter taskParameter = (TaskParameter)ParameterConstant.getParameter(22);
/*  769 */         singleInsComponent.saveFindTimes(true, FindRewardType.PERSONAL_BOSS.getType(), 1);
/*  770 */         singleInsComponent.saveFindTimes(false, FindRewardType.PERSONAL_BOSS.getType(), taskParameter.getDays());
/*      */       } 
/*      */     } 
/*      */     
/*  774 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/*  775 */     taskComponent.refreshSchedule(TaskType.ChallegeSingeBoss, bossHomeBean.getLevel(), 1L);
/*  776 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private short handleTowerFight(int insId, IPlayerSession playerSession, FightRecordRequest request) {
/*  789 */     TowerComponent towerComponent = (TowerComponent)playerSession.getPlayer().createIfNotExist(TowerComponent.class);
/*      */ 
/*      */     
/*  792 */     if (towerComponent.getActPlayTime().containsKey(Integer.valueOf(insId))) {
/*  793 */       return 16801;
/*      */     }
/*  795 */     if (insId > towerComponent.getCurLayers() + 1) {
/*  796 */       return 16802;
/*      */     }
/*  798 */     TowerBean towerBean = (TowerBean)JsonTableService.getJsonData(insId, TowerBean.class);
/*  799 */     if (null == towerBean) {
/*  800 */       return 13002;
/*      */     }
/*  802 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/*  803 */     if (towerBean.getChallengeLevel() > playerComponent.getLevel()) {
/*  804 */       return 13007;
/*      */     }
/*  806 */     TowerFight towerFight = new TowerFight(insId, playerSession.getPlayer());
/*  807 */     short code = towerFight.initFight();
/*  808 */     if (0 != code) {
/*  809 */       return code;
/*      */     }
/*  811 */     byte result = towerFight.start((FightRecordResponse)this.response);
/*  812 */     if (1 == result) {
/*  813 */       towerComponent.getActPlayTime().put(Integer.valueOf(insId), Integer.valueOf(TimeUtil.currentTime()));
/*  814 */       towerComponent.setCurLayers(insId);
/*  815 */       towerComponent.setPassTime(TimeUtil.currentTime());
/*  816 */       ArrayList<Reward> rewards2 = new ArrayList<>();
/*  817 */       rewards2.addAll(FinanceUtil.transform(towerBean.getBasicReward()));
/*  818 */       rewards2.addAll(FinanceUtil.transform(towerBean.getBossReward()));
/*      */       
/*  820 */       int[] value = MilitaryUtil.getRewardResult(rewards2, MilitaryInsType.TowerIns.getType(), playerSession.getPlayer().getPlayerId(), 0);
/*  821 */       ArrayList<Reward> rewards = FinanceUtil.rewardGet(rewards2, playerSession.getPlayer(), ResourceEvent.TowerPassReward, false);
/*  822 */       ResultUtil.saveResult(playerSession.getPlayer().getPlayerId(), request.type, insId, 3, rewards, value[MilitaryInteriorType.Exp.ordinal()]);
/*      */     } 
/*  824 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/*  825 */     taskComponent.refreshSchedule(TaskType.ChangeTower, 0, 1L);
/*  826 */     taskComponent.refreshSchedule(TaskType.TowerMaxPoint, insId, 0L);
/*  827 */     return 0;
/*      */   }
/*      */   
/*      */   public short handleTeamFight(IPlayer player, int insId) {
/*  831 */     MultiInsBean multiInsBean = (MultiInsBean)JsonTableService.getJsonData(insId, MultiInsBean.class);
/*  832 */     if (null == multiInsBean) {
/*  833 */       return 13712;
/*      */     }
/*  835 */     long teamId = TeamUtil.getTeamIdByPlayerId(player.getPlayerId());
/*  836 */     if (teamId == -1L) {
/*  837 */       return 13701;
/*      */     }
/*  839 */     Team team = TeamUtil.getTeamById(teamId);
/*  840 */     if (team == null) {
/*  841 */       return 13701;
/*      */     }
/*  843 */     if (!team.isLeader(player.getPlayerId())) {
/*  844 */       return 13708;
/*      */     }
/*  846 */     if (!team.canEnterFight()) {
/*  847 */       return 13714;
/*      */     }
/*  849 */     TeamFight teamFight = new TeamFight(insId, team);
/*  850 */     byte result = teamFight.start((FightRecordResponse)this.response);
/*      */     
/*  852 */     TeamParameter teamParameter = (TeamParameter)ParameterConstant.getParameter(37);
/*  853 */     for (TeamPlayer teamPlayer : team.getTeamPlayers()) {
/*  854 */       BossHomeComponent bossHomeComponent = null;
/*  855 */       TaskComponent taskComponent = null;
/*  856 */       if (teamPlayer != null && !teamPlayer.isRobot()) {
/*  857 */         bossHomeComponent = (BossHomeComponent)LookUpService.getComponent(teamPlayer.getPlayerId(), BossHomeComponent.class);
/*  858 */         taskComponent = (TaskComponent)LookUpService.getComponent(teamPlayer.getPlayerId(), TaskComponent.class);
/*      */       } 
/*  860 */       if (1 == result && teamPlayer != null && !teamPlayer.isRobot()) {
/*  861 */         ArrayList<Reward> rewardList = FinanceUtil.transform(multiInsBean.getReward());
/*      */         
/*  863 */         MilitaryUtil.getRewardResult(rewardList, MilitaryInsType.TeamIns.getType(), player.getPlayerId(), 0);
/*  864 */         ArrayList<Reward> doubleRewardList = FinanceUtil.transform(multiInsBean.getDoubleReward());
/*  865 */         ArrayList<Reward> arrayList = FinanceUtil.transform(multiInsBean.getDoubleReward());
/*      */         
/*  867 */         int[] value = MilitaryUtil.getRewardResult(arrayList, MilitaryInsType.TeamIns.getType(), player.getPlayerId(), 0);
/*  868 */         int totalRate = 10000;
/*  869 */         if (teamParameter.isInDoubleTime(TimeUtil.getNowMinutes())) {
/*  870 */           totalRate *= teamParameter.getDoubleTimeRate() / 10000;
/*      */         }
/*  872 */         if (team.isFull()) {
/*  873 */           totalRate += teamParameter.getTeamFullRate();
/*      */         }
/*  875 */         for (Reward reward : doubleRewardList) {
/*  876 */           reward.num = (long)((reward.num * (totalRate + value[MilitaryInteriorType.Exp.ordinal()])) / 10000.0D);
/*      */         }
/*      */         
/*  879 */         rewardList.addAll(doubleRewardList);
/*      */         
/*  881 */         if (bossHomeComponent != null) {
/*  882 */           if (bossHomeComponent.getTeamFightTimes() >= teamParameter.getMaxFightTimes()) {
/*  883 */             bossHomeComponent.setTeamFightTimes(teamParameter.getMaxFightTimes());
/*      */           } else {
/*  885 */             bossHomeComponent.setTeamFightTimes(bossHomeComponent.getTeamFightTimes() + 1);
/*  886 */             ArrayList<Reward> rewards = FinanceUtil.rewardGet(rewardList, LookUpService.getByPlayerId(teamPlayer.getPlayerId()), ResourceEvent.TeamFight, false);
/*  887 */             ResultUtil.saveResult(teamPlayer.getPlayerId(), (byte)10, insId, 3, rewards, totalRate + value[MilitaryInteriorType.Exp.ordinal()] - 10000);
/*  888 */             SingleInsComponent singleInsComponent = (SingleInsComponent)player.createIfNotExist(SingleInsComponent.class);
/*  889 */             singleInsComponent.saveFindTimes(true, FindRewardType.TEAM.getType(), 1);
/*      */           } 
/*      */         }
/*      */       } 
/*      */       
/*  894 */       if (null != bossHomeComponent) {
/*  895 */         bossHomeComponent.setTotalFightTimes(bossHomeComponent.getTotalFightTimes() + 1);
/*      */       }
/*  897 */       if (null != taskComponent) {
/*  898 */         taskComponent.refreshSchedule(TaskType.ChangeTeamIns, 0, 1L);
/*  899 */         taskComponent.refreshSchedule(TaskType.TotalTeamTimes, 0, 0L);
/*  900 */         taskComponent.refreshSchedule(TaskType.JoinTeamIns, 0, 1L);
/*      */       } 
/*      */     } 
/*      */     
/*  904 */     TeamUtil.teamFightEnd(team);
/*  905 */     return 0;
/*      */   }
/*      */   
/*      */   private short handleBaguaFight(IPlayer player, int insId) {
/*  909 */     BaguaInsBean insBean = (BaguaInsBean)JsonTableService.getJsonData(insId, BaguaInsBean.class);
/*  910 */     BaguaParameter baguaParameter = (BaguaParameter)ParameterConstant.getParameter(41);
/*  911 */     if (null == insBean) {
/*  912 */       return 13712;
/*      */     }
/*      */     
/*  915 */     int index = -1;
/*  916 */     BaguaChapterBean baguaChapterBean = null, nextChapterBean = null;
/*  917 */     Iterator<Integer> iterator = JsonTableService.getJsonTableKey(BaguaChapterBean.class).iterator();
/*  918 */     while (iterator.hasNext()) {
/*  919 */       int id = ((Integer)iterator.next()).intValue();
/*  920 */       BaguaChapterBean bean = (BaguaChapterBean)JsonTableService.getJsonData(id, BaguaChapterBean.class);
/*  921 */       index = bean.getIns().indexOf(Integer.valueOf(insId));
/*  922 */       if (index != -1) {
/*  923 */         baguaChapterBean = bean;
/*      */         
/*      */         break;
/*      */       } 
/*      */     } 
/*  928 */     if (iterator.hasNext()) {
/*  929 */       int id = ((Integer)iterator.next()).intValue();
/*  930 */       nextChapterBean = (BaguaChapterBean)JsonTableService.getJsonData(id, BaguaChapterBean.class);
/*      */     } 
/*      */     
/*  933 */     if (index == -1) {
/*  934 */       return 13712;
/*      */     }
/*      */     
/*  937 */     int prevInsId = (index == 0) ? -1 : ((Integer)baguaChapterBean.getIns().get(index - 1)).intValue();
/*  938 */     int nextInsId = (index == baguaChapterBean.getIns().size() - 1) ? -1 : ((Integer)baguaChapterBean.getIns().get(index + 1)).intValue();
/*      */     
/*  940 */     long teamId = TeamUtil.getTeamIdByPlayerId(player.getPlayerId());
/*  941 */     if (teamId == -1L) {
/*  942 */       return 13701;
/*      */     }
/*  944 */     Team team = TeamUtil.getTeamById(teamId);
/*  945 */     if (team == null) {
/*  946 */       return 13701;
/*      */     }
/*  948 */     if (!team.isLeader(player.getPlayerId())) {
/*  949 */       return 13708;
/*      */     }
/*  951 */     if (!team.canEnterFight()) {
/*  952 */       return 13714;
/*      */     }
/*  954 */     BaguaFight baguaFight = new BaguaFight(insId, team);
/*  955 */     byte result = baguaFight.start((FightRecordResponse)this.response);
/*  956 */     if (1 == result) {
/*  957 */       boolean isFirst = false;
/*  958 */       ArrayList<LongStringValue> playerList = new ArrayList<>();
/*  959 */       for (TeamPlayer teamPlayer : team.getTeamPlayers()) {
/*  960 */         BaguaComponent baguaComponent = null;
/*  961 */         if (teamPlayer != null && !teamPlayer.isRobot()) {
/*  962 */           LongStringValue lsv = new LongStringValue();
/*  963 */           lsv.key = teamPlayer.getPlayerId();
/*  964 */           lsv.value = teamPlayer.getName();
/*  965 */           playerList.add(lsv);
/*  966 */           baguaComponent = (BaguaComponent)LookUpService.getComponent(teamPlayer.getPlayerId(), BaguaComponent.class);
/*  967 */           if (baguaComponent == null || (
/*  968 */             prevInsId != -1 && !((Set)baguaComponent.getChapterMap().get(Integer.valueOf(baguaChapterBean.getId()))).contains(Integer.valueOf(prevInsId))) || (baguaComponent
/*  969 */             .getCurInsId() != insId && !((Set)baguaComponent.getChapterMap().get(Integer.valueOf(baguaChapterBean.getId()))).contains(Integer.valueOf(insId)))) {
/*      */             continue;
/*      */           }
/*  972 */           ArrayList<Reward> rewardList = new ArrayList<>();
/*  973 */           SingleInsComponent singleInsComponent = (SingleInsComponent)player.createIfNotExist(SingleInsComponent.class);
/*  974 */           if (null != singleInsComponent && baguaComponent.getChapterMap().isEmpty()) {
/*  975 */             TaskParameter taskParameter = (TaskParameter)ParameterConstant.getParameter(22);
/*  976 */             singleInsComponent.saveFindTimes(true, FindRewardType.BAGUA.getType(), 1);
/*  977 */             singleInsComponent.saveFindTimes(false, FindRewardType.BAGUA.getType(), taskParameter.getDays());
/*      */           } 
/*  979 */           if (baguaComponent.getCurInsId() == insId && !((Set)baguaComponent.getChapterMap().get(Integer.valueOf(baguaChapterBean.getId()))).contains(Integer.valueOf(insId))) {
/*  980 */             ((Set<Integer>)baguaComponent.getChapterMap().get(Integer.valueOf(baguaChapterBean.getId()))).add(Integer.valueOf(insId));
/*  981 */             if (nextInsId != -1) {
/*  982 */               baguaComponent.setCurInsId(nextInsId);
/*      */             }
/*  984 */             else if (nextChapterBean != null) {
/*  985 */               baguaComponent.getChapterMap().put(Integer.valueOf(nextChapterBean.getId()), new TreeSet());
/*  986 */               baguaComponent.setCurInsId(((Integer)nextChapterBean.getIns().get(0)).intValue());
/*      */             } else {
/*  988 */               baguaComponent.setCurInsId(-1);
/*      */             } 
/*      */             
/*  991 */             baguaComponent.getInsIdMap().put(Integer.valueOf(insId), Integer.valueOf(1));
/*      */             
/*  993 */             rewardList = FinanceUtil.transform(insBean.getSureReward());
/*  994 */             rewardList.addAll(FinanceUtil.transform(insBean.getProReward()));
/*  995 */             isFirst = true;
/*      */           }
/*  997 */           else if (baguaComponent.getCurInsId() != insId && ((Set)baguaComponent.getChapterMap().get(Integer.valueOf(baguaChapterBean.getId()))).contains(Integer.valueOf(insId))) {
/*      */             
/*  999 */             if (!baguaComponent.getInsIdMap().containsKey(Integer.valueOf(insId))) {
/* 1000 */               baguaComponent.getInsIdMap().put(Integer.valueOf(insId), Integer.valueOf(1));
/* 1001 */               rewardList.addAll(FinanceUtil.transform(insBean.getSweep()));
/* 1002 */               rewardList.addAll(FinanceUtil.transform(insBean.getProReward()));
/* 1003 */               if (baguaComponent.getAsistTime() < baguaParameter.getAsistTime()) {
/* 1004 */                 baguaComponent.setAsistTime(baguaComponent.getAsistTime() + 1);
/* 1005 */                 rewardList = FinanceUtil.transform(insBean.getAssist());
/*      */               }
/*      */             
/* 1008 */             } else if (baguaComponent.getAsistTime() < baguaParameter.getAsistTime()) {
/* 1009 */               rewardList.addAll(FinanceUtil.transform(insBean.getProReward()));
/* 1010 */               baguaComponent.setAsistTime(baguaComponent.getAsistTime() + 1);
/* 1011 */               rewardList = FinanceUtil.transform(insBean.getAssist());
/*      */             } 
/*      */           } 
/*      */           
/* 1015 */           if (!rewardList.isEmpty()) {
/* 1016 */             ArrayList<Reward> rewards = FinanceUtil.rewardGet(rewardList, LookUpService.getByPlayerId(teamPlayer.getPlayerId()), ResourceEvent.BaguaFight, false);
/* 1017 */             ResultUtil.saveResult(teamPlayer.getPlayerId(), (byte)13, insId, 3, rewards, 0);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 1023 */       if (isFirst) {
/* 1024 */         BaguaUtil.BaguaRecordTable baguaRecordTable = new BaguaUtil.BaguaRecordTable();
/* 1025 */         baguaRecordTable.chapterId = baguaChapterBean.getId();
/* 1026 */         baguaRecordTable.insId = insId;
/* 1027 */         baguaRecordTable.createtime = TimeUtil.currentTime();
/* 1028 */         baguaRecordTable.playerList = playerList;
/* 1029 */         BaguaUtil.addRecord(baguaRecordTable);
/*      */       } 
/* 1031 */       TaskComponent taskComponent = (TaskComponent)player.createIfNotExist(TaskComponent.class);
/* 1032 */       taskComponent.refreshSchedule(TaskType.BaGuaPass, 0, 0L);
/*      */     } 
/*      */ 
/*      */     
/* 1036 */     TeamUtil.teamFightEnd(team);
/* 1037 */     return 0;
/*      */   }
/*      */   
/*      */   public short handleDestinyFight(IPlayer player, long targetPlayerId) {
/* 1041 */     if (!FunctionOpenConstant.isFunctionOpen(player.getSession(), 74)) {
/* 1042 */       return 10061;
/*      */     }
/* 1044 */     DestinyComponent destinyComponent = (DestinyComponent)player.getComponent(DestinyComponent.class);
/*      */     
/* 1046 */     if (destinyComponent.getRobTimes() <= 0) {
/* 1047 */       return 17407;
/*      */     }
/*      */     
/* 1050 */     if (!destinyComponent.getBattles().containsKey(Long.valueOf(targetPlayerId))) {
/* 1051 */       return 10081;
/*      */     }
/* 1053 */     if (((Byte)destinyComponent.getBattles().get(Long.valueOf(targetPlayerId))).byteValue() == 1) {
/* 1054 */       return 17406;
/*      */     }
/*      */     
/* 1057 */     destinyComponent.addRobTimes(-1);
/* 1058 */     destinyComponent.setTotalRobTimes(destinyComponent.getTotalRobTimes() + 1);
/* 1059 */     for (Integer key : JsonTableService.getJsonTableKey(DestinyProcessBean.class)) {
/* 1060 */       DestinyProcessBean bean = (DestinyProcessBean)JsonTableService.getJsonData(key.intValue(), DestinyProcessBean.class);
/* 1061 */       if (bean.getTimes() == destinyComponent.getTotalRobTimes()) {
/* 1062 */         PlayerUtil.sendRedNotice(Long.valueOf(player.getPlayerId()), RedNoticeConstant.DestinyReward.getSys(), RedNoticeConstant.DestinyReward.getIndex());
/*      */         
/*      */         break;
/*      */       } 
/*      */     } 
/* 1067 */     int now = TimeUtil.currentTime();
/*      */     
/* 1069 */     DestinyParameter destinyParameter = (DestinyParameter)ParameterConstant.getParameter(74);
/*      */ 
/*      */ 
/*      */     
/* 1073 */     DestinyPlayerData destinyPlayerData = (DestinyPlayerData)destinyComponent.getRecommentsCacheData().get(Long.valueOf(targetPlayerId));
/* 1074 */     if (null == destinyPlayerData)
/* 1075 */       return 17408; 
/* 1076 */     if (null == destinyPlayerData.getCandidateFighters()) {
/* 1077 */       destinyPlayerData.setCandidateFighters(new ArrayList());
/*      */     }
/* 1079 */     DestinyFight destinyFight = new DestinyFight(player, destinyPlayerData);
/* 1080 */     byte result = destinyFight.start((FightRecordResponse)this.response);
/*      */     
/* 1082 */     if (1 == result) {
/* 1083 */       destinyComponent.getBattles().put(Long.valueOf(targetPlayerId), Byte.valueOf((byte)1));
/*      */       
/* 1085 */       DestinyWarBean bean = null;
/* 1086 */       for (Iterator<Integer> iterator = JsonTableService.getJsonTableKey(DestinyWarBean.class).iterator(); iterator.hasNext(); ) { int id = ((Integer)iterator.next()).intValue();
/* 1087 */         bean = (DestinyWarBean)JsonTableService.getJsonData(id, DestinyWarBean.class);
/* 1088 */         if (destinyPlayerData.getDestinyPoint() <= bean.getAnger()) {
/*      */           break;
/*      */         } }
/*      */       
/* 1092 */       int addDestinyPoint = bean.getPoint();
/* 1093 */       ArrayList<Reward> rewardList = FinanceUtil.transform(bean.getMaxReward());
/*      */       
/* 1095 */       int[] value = MilitaryUtil.getRewardResult(rewardList, MilitaryInsType.DestinyIns.getType(), player.getPlayerId(), 0);
/* 1096 */       FinanceUtil.reward(rewardList, player, ResourceEvent.DestinyBattleFight, true);
/* 1097 */       ResultUtil.saveResult(player.getPlayerId(), (byte)11, 0, 0, rewardList, value[MilitaryInteriorType.Exp.ordinal()]);
/* 1098 */       Reward reward = rewardList.get(0);
/* 1099 */       BattleRecordData attackRecord = new BattleRecordData();
/* 1100 */       attackRecord.type = 1;
/* 1101 */       attackRecord.time = now;
/* 1102 */       attackRecord.playerId = destinyPlayerData.getPlayerId();
/* 1103 */       attackRecord.playerName = destinyPlayerData.getPlayerName();
/* 1104 */       attackRecord.robNum = (int)reward.num;
/* 1105 */       attackRecord.win = true;
/* 1106 */       destinyComponent.addAtkBattleRecordData(attackRecord, destinyParameter.getRecordSize());
/*      */ 
/*      */       
/* 1109 */       int myDestinyPoint = CrossUtil.addDestinyPoint("FightRecordProcessor::handleDestinyFight -> " + player.getPlayerId(), MContext.getServerIdInt(), player.getPlayerId(), addDestinyPoint);
/*      */ 
/*      */       
/* 1112 */       Fibers.createExecutorService().execute(() -> CrossUtil.addDestinyDef("FightRecordProcessor::handleDestinyFight -> " + player.getPlayerId(), destinyPlayerData.getPlayerId(), 0, now, (int)reward.num, false, player.getPlayerId(), VersionUtil.getCrossPlayerName(player.getPlayerId(), player.getPlayerName()), reward));
/*      */     } else {
/*      */       
/* 1115 */       BattleRecordData attackRecord = new BattleRecordData();
/* 1116 */       attackRecord.type = 1;
/* 1117 */       attackRecord.time = now;
/* 1118 */       attackRecord.playerId = destinyPlayerData.getPlayerId();
/* 1119 */       attackRecord.playerName = destinyPlayerData.getPlayerName();
/* 1120 */       attackRecord.robNum = 0;
/* 1121 */       attackRecord.win = false;
/*      */       
/* 1123 */       destinyComponent.addAtkBattleRecordData(attackRecord, destinyParameter.getRecordSize());
/* 1124 */       Fibers.createExecutorService().execute(() -> CrossUtil.addDestinyDef("FightRecordProcessor::handleDestinyFight -> " + player.getPlayerId(), destinyPlayerData.getPlayerId(), 0, now, 0, true, player.getPlayerId(), VersionUtil.getCrossPlayerName(player.getPlayerId(), player.getPlayerName()), null));
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1130 */     TaskComponent taskComponent = (TaskComponent)player.getComponent(TaskComponent.class);
/* 1131 */     if (null != taskComponent) {
/* 1132 */       taskComponent.refreshSchedule(TaskType.JoinDestiny, 0, 1L);
/*      */     }
/* 1134 */     SingleInsComponent singleInsComponent = (SingleInsComponent)player.createIfNotExist(SingleInsComponent.class);
/* 1135 */     singleInsComponent.saveFindTimes(true, FindRewardType.DENSTINY.getType(), 1);
/*      */     
/* 1137 */     CrossUtil.updateDestinyPlayerData("FightRecordProcessor::handleDestinyFight", CrossUtil.buildLocalDestinyPlayerData(player.getPlayerId()));
/* 1138 */     return 0;
/*      */   }
/*      */   
/*      */   public short handleMatchFight(IPlayer player, String pkId) {
/* 1142 */     PkRecord pkRecord = DestinyUtil.getPkRecord(pkId);
/* 1143 */     if (pkRecord == null) return 17414; 
/* 1144 */     ((FightRecordResponse)this.response).type = 12;
/* 1145 */     ((FightRecordResponse)this.response).id = pkRecord.pkId;
/* 1146 */     ((FightRecordResponse)this.response).result = pkRecord.result;
/* 1147 */     ((FightRecordResponse)this.response).lGroups = pkRecord.lGroups;
/* 1148 */     ((FightRecordResponse)this.response).rGroups = pkRecord.rGroups;
/* 1149 */     ((FightRecordResponse)this.response).boutPlayData = pkRecord.boutPlayData;
/* 1150 */     ((FightRecordResponse)this.response).fightersHp = pkRecord.fightersHp;
/* 1151 */     ((FightRecordResponse)this.response).debugStr = "";
/* 1152 */     ((FightRecordResponse)this.response).totalHurt = pkRecord.totalHurt;
/* 1153 */     return 0;
/*      */   }
/*      */   
/*      */   public short handleGroupFight(IPlayer player, int insId) {
/* 1157 */     GroupParameter groupParameter = (GroupParameter)ParameterConstant.getParameter(11);
/* 1158 */     GroupEntity groupEntity = GroupUtil.getCurGroup(player);
/* 1159 */     if (groupEntity == null) {
/* 1160 */       return 11101;
/*      */     }
/* 1162 */     if (groupEntity.getLevel() < groupParameter.getOpenLevel()) {
/* 1163 */       return 11130;
/*      */     }
/* 1165 */     GroupMemberComponent groupMemberComponent = (GroupMemberComponent)player.createIfNotExist(GroupMemberComponent.class);
/*      */     
/* 1167 */     if (groupMemberComponent.getFightTimes() >= groupParameter.getTimeLimit()) {
/* 1168 */       return 10307;
/*      */     }
/* 1170 */     long hp = groupEntity.getBossHp().values().stream().mapToLong(num -> num.longValue()).sum();
/* 1171 */     if (hp <= 0L) {
/* 1172 */       return 11132;
/*      */     }
/* 1174 */     GroupBossFightSide groupBossFightSide = GroupBossUtil.getGroupBossFightSide(groupEntity.getId());
/*      */     
/* 1176 */     short code = groupBossFightSide.handleFight(player, (FightRecordResponse)this.response);
/* 1177 */     if (0 != code) {
/* 1178 */       return code;
/*      */     }
/* 1180 */     groupMemberComponent.setFightTimes(groupMemberComponent.getFightTimes() + 1);
/* 1181 */     groupMemberComponent.setTotalTimes(groupMemberComponent.getTotalTimes() + 1);
/* 1182 */     LogUtils.errorLog(new Object[] { "handleGroupBoss", Long.valueOf(player.getPlayerId()), Integer.valueOf(groupMemberComponent.getFightTimes()), Byte.valueOf(((FightRecordResponse)this.response).result) });
/*      */     
/* 1184 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private short handleAlienRaceBoss(int insId, IPlayerSession playerSession, FightRecordRequest request) {
/* 1195 */     NeutralBossBean neutralBossBean = (NeutralBossBean)JsonTableService.getJsonData(insId, NeutralBossBean.class);
/* 1196 */     if (null == neutralBossBean) {
/* 1197 */       return 10301;
/*      */     }
/* 1199 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/* 1200 */     if (playerComponent.getLevel() < neutralBossBean.getLevel()) {
/* 1201 */       return 10084;
/*      */     }
/*      */     
/* 1204 */     short code = BossUtil.handAlienRaceBossEnter(playerSession, insId, (FightRecordResponse)this.response);
/* 1205 */     if (0 != code) {
/* 1206 */       return code;
/*      */     }
/* 1208 */     BossHomeComponent bossHomeComponent = (BossHomeComponent)playerSession.getPlayer().createIfNotExist(BossHomeComponent.class);
/* 1209 */     bossHomeComponent.setTotalNeutralBoss(bossHomeComponent.getTotalNeutralBoss() + 1);
/*      */     
/* 1211 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 1212 */     if (null != taskComponent) {
/* 1213 */       taskComponent.refreshSchedule(TaskType.TatalChangeNeutralBoss, 0, 0L);
/*      */     }
/* 1215 */     LogUtils.errorLog(new Object[] { "handleAlienBoss", Long.valueOf(playerSession.getPlayer().getPlayerId()), Integer.valueOf(insId), Byte.valueOf(((FightRecordResponse)this.response).result) });
/* 1216 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private short handleCrossRaceFight(long targetPlayerId, IPlayerSession playerSession) {
/* 1227 */     boolean isOpen = CrossUtil.isRaceOpen("handleCrossRaceFight");
/* 1228 */     if (!isOpen) {
/* 1229 */       return 12801;
/*      */     }
/* 1231 */     CrossRaceParameter crossRaceParameter = (CrossRaceParameter)ParameterConstant.getParameter(28);
/* 1232 */     CrossRaceComponent crossRaceComponent = (CrossRaceComponent)playerSession.getPlayer().getComponent(CrossRaceComponent.class);
/*      */     
/* 1234 */     int curTime = TimeUtil.currentTime();
/* 1235 */     if (curTime - crossRaceComponent.getLastFightTime() < crossRaceParameter.getCdTime()) {
/* 1236 */       return 12803;
/*      */     }
/* 1238 */     crossRaceComponent.setLastFightTime(curTime);
/*      */     
/* 1240 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().getComponent(PlayerComponent.class);
/* 1241 */     RacePlayerData racePlayerData = null;
/* 1242 */     if (crossRaceComponent.getCacheRacePlayerData() == null) {
/* 1243 */       racePlayerData = CrossUtil.recommentOne("handleCrossRaceFight", playerComponent.getPlayerId(), playerComponent.getTotalValue(), crossRaceComponent.getPoint());
/*      */     } else {
/* 1245 */       racePlayerData = crossRaceComponent.getCacheRacePlayerData();
/*      */     } 
/* 1247 */     if (racePlayerData == null) {
/* 1248 */       return 12802;
/*      */     }
/*      */     
/* 1251 */     CrossRaceFight crossRaceFight = new CrossRaceFight(playerSession.getPlayer(), racePlayerData.getPlayerData());
/* 1252 */     byte result = crossRaceFight.start((FightRecordResponse)this.response);
/*      */     
/* 1254 */     CrossRankBean crossRankBean = CrossRaceUtil.chooseTargetBean(crossRaceComponent.getPoint());
/* 1255 */     if (crossRankBean == null) {
/* 1256 */       return 10006;
/*      */     }
/* 1258 */     if (crossRaceComponent.getFightTimes() > 0) {
/* 1259 */       crossRaceComponent.setFightTimes(crossRaceComponent.getFightTimes() - 1);
/* 1260 */       FinanceUtil.reward(FinanceUtil.transform(crossRankBean.getReward()), playerSession.getPlayer(), ResourceEvent.CrossRace, true);
/*      */     } 
/* 1262 */     crossRaceComponent.setTimes(crossRaceComponent.getTimes() + 1);
/*      */ 
/*      */     
/* 1265 */     CrossRankRewardBean crossRankRewardBean = (CrossRankRewardBean)JsonTableService.getJsonData(crossRaceComponent.getTimes(), CrossRankRewardBean.class);
/* 1266 */     if (crossRankRewardBean != null && !crossRaceComponent.getJoinReward().containsKey(Integer.valueOf(crossRaceComponent.getTimes())))
/*      */     {
/* 1268 */       crossRaceComponent.getJoinReward().put(Integer.valueOf(crossRaceComponent.getTimes()), Integer.valueOf(1));
/*      */     }
/* 1270 */     if (1 == result) {
/* 1271 */       crossRaceComponent.setWinTimes(crossRaceComponent.getWinTimes() + 1);
/* 1272 */       crossRaceComponent.setPoint(crossRaceComponent.getPoint() + crossRankBean.getWin());
/*      */     } else {
/*      */       
/* 1275 */       crossRaceComponent.setPoint(crossRaceComponent.getPoint() + crossRankBean.getLose());
/*      */     } 
/* 1277 */     crossRaceComponent.setTotalTimes(crossRaceComponent.getTotalTimes() + 1);
/* 1278 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 1279 */     if (null != taskComponent) {
/* 1280 */       taskComponent.refreshSchedule(TaskType.KuaFuTimes, 0, 1L);
/* 1281 */       taskComponent.refreshSchedule(TaskType.TotalKuaFuTimes, 0, 0L);
/*      */     } 
/* 1283 */     SingleInsComponent singleInsComponent = (SingleInsComponent)playerSession.getPlayer().createIfNotExist(SingleInsComponent.class);
/* 1284 */     singleInsComponent.saveFindTimes(true, FindRewardType.CROSS.getType(), 1);
/* 1285 */     crossRaceComponent.setCacheRacePlayerData(null);
/* 1286 */     Fibers.createExecutorService().execute(() -> CrossUtil.updatePlayerRacePoint("FightRecordProcessor::handleCrossRaceFight -> " + playerComponent.getPlayerId(), playerComponent.getPlayerId(), crossRaceComponent.getPoint()));
/*      */     
/* 1288 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private short handleYearBeastBoss(int actId, IPlayerSession playerSession, FightRecordRequest request) {
/* 1300 */     YearBeastComponent yearBeastComponent = (YearBeastComponent)playerSession.getPlayer().createIfNotExist(YearBeastComponent.class);
/* 1301 */     BossHomeParameter bossHomeParameter = (BossHomeParameter)ParameterConstant.getParameter(3);
/* 1302 */     if (!bossHomeParameter.isActOpen(actId)) {
/* 1303 */       return 12702;
/*      */     }
/* 1305 */     YearBeastBean yearBeastBean = (YearBeastBean)JsonTableService.getJsonData(actId, YearBeastBean.class);
/* 1306 */     if (null == yearBeastBean) {
/* 1307 */       return 11808;
/*      */     }
/* 1309 */     if (!PlayerUtil.platformActIsOpen(yearBeastBean.getChannelType())) {
/* 1310 */       return 12702;
/*      */     }
/*      */     
/* 1313 */     YearBeastEntity yearBeastEntity = yearBeastComponent.getEntity(actId);
/* 1314 */     MentalRankService rankService = (MentalRankService)MContext.getBean("mentalRankService");
/* 1315 */     int level = rankService.getLevel(yearBeastEntity.getId());
/* 1316 */     Set<Integer> bossList = (Set<Integer>)bossHomeParameter.getLevelBoss().get(Integer.valueOf(level));
/* 1317 */     Set<Integer> reallyBossList = new HashSet<>();
/* 1318 */     for (Iterator<Integer> iterator = yearBeastBean.getBossEntry().iterator(); iterator.hasNext(); ) { int boss = ((Integer)iterator.next()).intValue();
/* 1319 */       if (bossList.contains(Integer.valueOf(boss))) {
/* 1320 */         reallyBossList.add(Integer.valueOf(boss));
/*      */       } }
/*      */ 
/*      */     
/* 1324 */     YearBeastListBean yearBeastListBean = (YearBeastListBean)JsonTableService.getJsonData(yearBeastEntity.getCurrBossId(), YearBeastListBean.class);
/* 1325 */     if (yearBeastEntity.getCurrBossId() != 0 && null == yearBeastListBean) {
/* 1326 */       return 10006;
/*      */     }
/* 1328 */     FestivalTime festivalTime = bossHomeParameter.getActTime(actId);
/* 1329 */     int currDay = TimeUtil.getDayDisTime(festivalTime.startTime);
/* 1330 */     if (yearBeastListBean.getOpenDay() > currDay) {
/* 1331 */       return 14023;
/*      */     }
/* 1333 */     if (yearBeastEntity.getDeathList().size() >= reallyBossList.size()) {
/* 1334 */       return 11132;
/*      */     }
/* 1336 */     if (yearBeastComponent.getCurTotalHp() <= 0L && yearBeastEntity.getCurrBossId() != 0) {
/* 1337 */       return 11132;
/*      */     }
/* 1339 */     if (yearBeastEntity.getYearBeastTimes() >= yearBeastBean.getChallLimit() + yearBeastEntity.getBuyTimes()) {
/* 1340 */       return 10089;
/*      */     }
/* 1342 */     if (yearBeastBean.getChallLimit() + yearBeastEntity.getBuyTimes() - yearBeastEntity.getYearBeastTimes() <= 0) {
/* 1343 */       return 11208;
/*      */     }
/* 1345 */     short code = yearBeastComponent.handleFight(playerSession.getPlayer(), (FightRecordResponse)this.response, yearBeastEntity);
/* 1346 */     if (0 != code) {
/* 1347 */       return code;
/*      */     }
/* 1349 */     LogUtils.errorLog(new Object[] { "handleYearBeastBoss", Long.valueOf(playerSession.getPlayer().getPlayerId()), Integer.valueOf(actId), Byte.valueOf(((FightRecordResponse)this.response).result) });
/* 1350 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private short handleTowerOwnerFight(int insId, IPlayerSession playerSession) {
/* 1361 */     TowerComponent towerComponent = (TowerComponent)playerSession.getPlayer().createIfNotExist(TowerComponent.class);
/* 1362 */     PlayerData targetPlayerData = towerComponent.getTargetPlayerData();
/* 1363 */     insId = (towerComponent.getCacheLayerId() == 0) ? insId : towerComponent.getCacheLayerId();
/* 1364 */     if (targetPlayerData == null) {
/* 1365 */       return 16704;
/*      */     }
/*      */     
/* 1368 */     TowerOwnerFight towerOwnerFight = new TowerOwnerFight(playerSession.getPlayer(), targetPlayerData);
/* 1369 */     byte result = towerOwnerFight.start((FightRecordResponse)this.response);
/*      */     
/* 1371 */     boolean win = (1 == result);
/* 1372 */     towerComponent.setTargetPlayerData(null);
/* 1373 */     towerComponent.setCacheLayerId(0);
/* 1374 */     boolean realResult = CrossUtil.fightTowerResult("", playerSession.getPlayer().getPlayerId(), targetPlayerData.getPlayerId(), insId, win);
/* 1375 */     if (!realResult) {
/* 1376 */       return 16705;
/*      */     }
/* 1378 */     int curtime = TimeUtil.currentTime();
/*      */     
/* 1380 */     BattleRecordData attackRecord = new BattleRecordData();
/* 1381 */     attackRecord.type = 1;
/* 1382 */     attackRecord.time = curtime;
/* 1383 */     attackRecord.playerId = targetPlayerData.getPlayerId();
/* 1384 */     attackRecord.playerName = targetPlayerData.getPlayerName();
/* 1385 */     attackRecord.robNum = insId;
/* 1386 */     attackRecord.win = win;
/* 1387 */     towerComponent.addRecord(attackRecord);
/*      */ 
/*      */     
/* 1390 */     int finalInsId = insId;
/* 1391 */     Fibers.createExecutorService().execute(() -> CrossUtil.addTowerDefRecord("FightRecordProcessor::handleTowerOwnerFight -> " + playerSession.getPlayer().getPlayerId(), finalInsId, targetPlayerData.getPlayerId(), 0, curtime, !win, playerSession.getPlayer().getPlayerId(), VersionUtil.getCrossPlayerName(playerSession.getPlayer().getPlayerId(), playerSession.getPlayer().getPlayerName())));
/*      */     
/* 1393 */     return 0;
/*      */   }
/*      */ 
/*      */   
/*      */   private short handleSecretiFight(String id, IPlayerSession playerSession, FightRecordRequest request) {
/* 1398 */     String[] params = id.split("_");
/* 1399 */     if (params.length != 3) {
/* 1400 */       return 11808;
/*      */     }
/* 1402 */     ((FightRecordResponse)this.response).id = params[1];
/* 1403 */     ((FightRecordResponse)this.response).type = 19;
/* 1404 */     int layerId = Integer.parseInt(params[0]);
/* 1405 */     int insId = Integer.parseInt(params[1]);
/* 1406 */     boolean useRewardTimes = !params[2].equals("0");
/* 1407 */     SecretiComponent secretiComponent = (SecretiComponent)playerSession.getPlayer().createIfNotExist(SecretiComponent.class);
/*      */     
/* 1409 */     if (!secretiComponent.getInsMap().containsKey(Integer.valueOf(layerId))) {
/* 1410 */       return 14401;
/*      */     }
/* 1412 */     IPlayer player = playerSession.getPlayer();
/*      */     
/* 1414 */     SecretiFight secretiFight = new SecretiFight(insId, player);
/* 1415 */     short retCode = secretiFight.initFight();
/* 1416 */     if (retCode != 0) {
/* 1417 */       return retCode;
/*      */     }
/* 1419 */     secretiFight.start((FightRecordResponse)this.response);
/*      */     
/* 1421 */     if (((FightRecordResponse)this.response).result == 1) {
/* 1422 */       SecretiBean secretiBean = (SecretiBean)JsonTableService.getJsonData(layerId, SecretiBean.class);
/* 1423 */       if (!((Set)secretiComponent.getInsMap().get(Integer.valueOf(layerId))).contains(Integer.valueOf(insId))) {
/* 1424 */         int total = secretiComponent.getTotal() + 1;
/* 1425 */         ((Set<Integer>)secretiComponent.getInsMap().get(Integer.valueOf(layerId))).add(Integer.valueOf(insId));
/* 1426 */         secretiComponent.setTotal(total);
/*      */ 
/*      */         
/* 1429 */         boolean affect = false;
/* 1430 */         for (Iterator<Integer> iterator = JsonTableService.getJsonTableKey(SecretiRewardBean.class).iterator(); iterator.hasNext(); ) { int progressId = ((Integer)iterator.next()).intValue();
/* 1431 */           SecretiRewardBean bean = (SecretiRewardBean)JsonTableService.getJsonData(progressId, SecretiRewardBean.class);
/* 1432 */           if (total >= bean.getPassNum() && !secretiComponent.getRewards().containsKey(Integer.valueOf(progressId))) {
/* 1433 */             secretiComponent.getRewards().put(Integer.valueOf(progressId), Integer.valueOf(1));
/* 1434 */             affect = true;
/*      */           }  }
/*      */         
/* 1437 */         if (affect) {
/* 1438 */           secretiComponent.setRewards(secretiComponent.getRewards());
/*      */         }
/*      */ 
/*      */         
/* 1442 */         SecretiBean nextBean = (SecretiBean)JsonTableService.getJsonData(layerId + 1, SecretiBean.class);
/* 1443 */         if (!secretiComponent.getInsMap().containsKey(Integer.valueOf(layerId + 1)) && nextBean != null && ((Set)secretiComponent
/* 1444 */           .getInsMap().get(Integer.valueOf(layerId))).size() >= secretiBean.getPassNum()) {
/* 1445 */           secretiComponent.getInsMap().put(Integer.valueOf(layerId + 1), new TreeSet());
/*      */         }
/* 1447 */         secretiComponent.setInsMap(secretiComponent.getInsMap());
/*      */       } 
/*      */     } 
/*      */     
/* 1451 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 1452 */     secretiComponent.setTotalTimes(secretiComponent.getTotalTimes() + 1);
/* 1453 */     if (null != taskComponent) {
/* 1454 */       taskComponent.refreshSchedule(TaskType.SecretiTimes, 0, 1L);
/* 1455 */       taskComponent.refreshSchedule(TaskType.TotalSecretiTimes, 0, 0L);
/* 1456 */       taskComponent.refreshSchedule(TaskType.SecretiFloor, 0, 0L);
/*      */     } 
/* 1458 */     if (!useRewardTimes || secretiComponent.getRewardTimes() <= 0) {
/* 1459 */       return 0;
/*      */     }
/* 1461 */     SecretiInsBean secretiInsBean = (SecretiInsBean)JsonTableService.getJsonData(insId, SecretiInsBean.class);
/* 1462 */     ArrayList<Reward> rewardList = new ArrayList<>();
/* 1463 */     if (((FightRecordResponse)this.response).result == 1) {
/* 1464 */       rewardList.addAll(FinanceUtil.transform(secretiInsBean.getVitoryReward()));
/*      */     } else {
/* 1466 */       rewardList.addAll(FinanceUtil.transform(secretiInsBean.getLoseReward()));
/*      */     } 
/* 1468 */     secretiComponent.setRewardTimes(secretiComponent.getRewardTimes() - 1);
/* 1469 */     FinanceUtil.reward(rewardList, player, ResourceEvent.SecretiFightReward, true);
/*      */ 
/*      */ 
/*      */     
/* 1473 */     return 0;
/*      */   }
/*      */   
/*      */   private short handleRunewarFight(String id, IPlayerSession playerSession) {
/*      */     int changePoint;
/* 1478 */     long targetPlayerId = Long.parseLong(id);
/*      */     
/* 1480 */     RuneComponent runeComponent = (RuneComponent)playerSession.getPlayer().createIfNotExist(RuneComponent.class);
/* 1481 */     if (runeComponent.getHp() <= 0) {
/* 1482 */       return 14502;
/*      */     }
/*      */     
/* 1485 */     boolean open = CrossUtil.isRunewarOpen("handleRunewarFight");
/* 1486 */     if (!open) {
/* 1487 */       return 14503;
/*      */     }
/*      */     
/* 1490 */     RunewarParameter runewarParameter = (RunewarParameter)ParameterConstant.getParameter(45);
/*      */ 
/*      */     
/* 1493 */     int curtime = TimeUtil.currentTime();
/* 1494 */     if (curtime - runeComponent.getLasfFightTime() < runewarParameter.getFightCd()) {
/* 1495 */       return 14506;
/*      */     }
/*      */     
/* 1498 */     PlayerData targetPlayerData = CrossUtil.getRunewarTargetPlayerdata("handleRunewarFight", playerSession.getPlayer().getPlayerId(), targetPlayerId);
/* 1499 */     if (targetPlayerData == null) {
/* 1500 */       return 14501;
/*      */     }
/* 1502 */     RunewarFight runewarFight = new RunewarFight(playerSession.getPlayer(), targetPlayerData);
/* 1503 */     byte result = runewarFight.start((FightRecordResponse)this.response);
/*      */     
/* 1505 */     boolean win = (1 == result);
/*      */     
/* 1507 */     int point = runeComponent.getPoint();
/* 1508 */     int times = runeComponent.getTimes();
/*      */     
/* 1510 */     RuneWarBean targetBean = null;
/* 1511 */     for (Iterator<Integer> iterator1 = JsonTableService.getJsonTableKey(RuneWarBean.class).iterator(); iterator1.hasNext(); ) { int runeId = ((Integer)iterator1.next()).intValue();
/* 1512 */       targetBean = (RuneWarBean)JsonTableService.getJsonData(runeId, RuneWarBean.class);
/* 1513 */       if (point <= targetBean.getScore()) {
/*      */         break;
/*      */       } }
/*      */     
/* 1517 */     if (targetBean == null) {
/* 1518 */       return 10006;
/*      */     }
/* 1520 */     if (win) {
/* 1521 */       runeComponent.addPoint(targetBean.getWin());
/* 1522 */       changePoint = targetBean.getLose();
/*      */     } else {
/* 1524 */       runeComponent.addPoint(targetBean.getLose());
/* 1525 */       changePoint = targetBean.getWin();
/*      */     } 
/* 1527 */     times++;
/*      */     
/* 1529 */     BattleRecordData attackRecord = new BattleRecordData();
/* 1530 */     attackRecord.type = 1;
/* 1531 */     attackRecord.time = curtime;
/* 1532 */     attackRecord.playerId = targetPlayerData.getPlayerId();
/* 1533 */     attackRecord.playerName = targetPlayerData.getPlayerName();
/* 1534 */     attackRecord.robNum = win ? targetBean.getWin() : targetBean.getLose();
/* 1535 */     attackRecord.win = win;
/* 1536 */     runeComponent.addRecord(attackRecord);
/* 1537 */     runeComponent.setTimes(times);
/*      */     
/* 1539 */     runeComponent.setLasfFightTime(curtime);
/* 1540 */     boolean affect = false; Iterator<Integer> iterator2;
/* 1541 */     for (iterator2 = JsonTableService.getJsonTableKey(RuneWarBean.class).iterator(); iterator2.hasNext(); ) { int runeId = ((Integer)iterator2.next()).intValue();
/* 1542 */       targetBean = (RuneWarBean)JsonTableService.getJsonData(runeId, RuneWarBean.class);
/* 1543 */       if (runeComponent.getPoint() < targetBean.getScore()) {
/*      */         break;
/*      */       }
/* 1546 */       if (!runeComponent.getStageReward().containsKey(Integer.valueOf(runeId))) {
/* 1547 */         runeComponent.getStageReward().put(Integer.valueOf(runeId), Integer.valueOf(1));
/* 1548 */         affect = true;
/*      */       }  }
/*      */ 
/*      */     
/* 1552 */     if (affect) {
/* 1553 */       runeComponent.setStageReward(runeComponent.getStageReward());
/*      */     }
/* 1555 */     affect = false;
/* 1556 */     for (iterator2 = JsonTableService.getJsonTableKey(RuneWarRewardBean.class).iterator(); iterator2.hasNext(); ) { int rewardId = ((Integer)iterator2.next()).intValue();
/* 1557 */       if (runeComponent.getTimes() < rewardId) {
/*      */         break;
/*      */       }
/* 1560 */       if (!runeComponent.getDailyReward().containsKey(Integer.valueOf(rewardId))) {
/* 1561 */         runeComponent.getDailyReward().put(Integer.valueOf(rewardId), Integer.valueOf(1));
/* 1562 */         affect = true;
/*      */       }  }
/*      */ 
/*      */     
/* 1566 */     if (affect) {
/* 1567 */       runeComponent.setDailyReward(runeComponent.getDailyReward());
/*      */     }
/*      */     
/* 1570 */     BattleRecordData defRecord = new BattleRecordData();
/* 1571 */     defRecord.type = 0;
/* 1572 */     defRecord.time = curtime;
/* 1573 */     defRecord.playerId = playerSession.getPlayer().getPlayerId();
/* 1574 */     defRecord.playerName = VersionUtil.getCrossPlayerName(playerSession.getPlayer().getPlayerId(), playerSession.getPlayer().getPlayerName());
/* 1575 */     defRecord.robNum = changePoint;
/* 1576 */     defRecord.win = !win;
/*      */     
/* 1578 */     int decHp = CrossUtil.runewarFightResult("handleRunewarFight -> " + playerSession.getPlayer().getPlayerId(), playerSession
/* 1579 */         .getPlayer().getPlayerId(), targetPlayerData.getPlayerId(), targetPlayerData.getServerId(), runeComponent.getPoint(), win, defRecord);
/*      */     
/* 1581 */     if (decHp > 0) {
/* 1582 */       runeComponent.setHp((runeComponent.getHp() - decHp > 0) ? (runeComponent.getHp() - decHp) : 0);
/*      */     }
/*      */     
/* 1585 */     int recorverHp = (curtime - runeComponent.getLastRecoverTime()) / runewarParameter.getCdTime();
/* 1586 */     if (recorverHp > 0) {
/* 1587 */       runeComponent.setHp((runeComponent.getHp() + recorverHp > runewarParameter.getHp()) ? runewarParameter.getHp() : (runeComponent.getHp() + recorverHp));
/*      */     }
/*      */     
/* 1590 */     return 0;
/*      */   }
/*      */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\fight\FightRecordProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */