/*     */ package com.linlongyx.sanguo.webgame.processors.bosshome;
/*     */ 
/*     */ import com.linlongyx.core.framework.logic.IPlayer;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.logic.ISession;
/*     */ import com.linlongyx.core.framework.logic.PlayerSession;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.bossHome.BossHomeComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.FightUtil;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.side.AlienRaceBossFightSide;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.side.IFightSide;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.side.PlayerFightSide;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.side.RankBossFightSide;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.type.AlienRaceFight;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.type.RankBossFight;
/*     */ import com.linlongyx.sanguo.webgame.common.player.Player;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.BossHomeBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.NeutralBossBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.PvpInfoBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.BossHomeParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.processors.fight.ResultUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.fight.FightRecordResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.BossDamageData;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.BossData;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.LongIntValue;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.NeutralBossData;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.ConcurrentMap;
/*     */ import java.util.concurrent.atomic.AtomicInteger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BossUtil
/*     */ {
/*     */   public static final int TYPE_RANK_BOSS = 1;
/*     */   public static final int TYPE_WORLD_BOSS = 2;
/*     */   public static final int TYPE_TEAM_BOSS = 3;
/*  50 */   private static final ConcurrentMap<Integer, RankBossFightSide> rankBosses = new ConcurrentHashMap<>();
/*     */   
/*  52 */   private static final ConcurrentMap<Integer, AlienRaceBossFightSide> alienRaceBosses = new ConcurrentHashMap<>();
/*     */   
/*  54 */   public static AtomicInteger startTime = new AtomicInteger();
/*  55 */   public static AtomicInteger endTime = new AtomicInteger();
/*     */ 
/*     */   
/*     */   public static final int WORLD_BOSS_REWARD_TYPE1 = 1;
/*     */   
/*     */   public static final int WORLD_BOSS_REWARD_TYPE2 = 2;
/*     */   
/*     */   public static final int WORLD_BOSS_REWARD_TYPE3 = 3;
/*     */ 
/*     */   
/*     */   public static void init() {
/*  66 */     Map<Integer, Object> map = JsonTableService.getJsonTable(BossHomeBean.class);
/*  67 */     for (Map.Entry<Integer, Object> entry : map.entrySet()) {
/*  68 */       BossHomeBean bossHomeBean = (BossHomeBean)entry.getValue();
/*  69 */       if (bossHomeBean.getType() == 1) {
/*  70 */         addRankFight(bossHomeBean.getInsId());
/*     */       }
/*     */     } 
/*  73 */     Map<Integer, Object> neutralBossMap = JsonTableService.getJsonTable(NeutralBossBean.class);
/*  74 */     for (Map.Entry<Integer, Object> entry : neutralBossMap.entrySet()) {
/*  75 */       NeutralBossBean neutralBossBean = (NeutralBossBean)entry.getValue();
/*  76 */       addAlienRaceFight(neutralBossBean.getId());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void addRankFight(int insId) {
/*  86 */     RankBossFightSide rankBossFightSide = new RankBossFightSide(insId);
/*  87 */     short code = rankBossFightSide.initRankBoss();
/*  88 */     if (0 == code) {
/*  89 */       rankBosses.putIfAbsent(Integer.valueOf(insId), rankBossFightSide);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void addAlienRaceFight(int insId) {
/*  99 */     AlienRaceBossFightSide alienRaceBossFightSide = new AlienRaceBossFightSide(insId);
/* 100 */     short code = alienRaceBossFightSide.initAlienRaceBoss();
/* 101 */     if (0 == code) {
/* 102 */       alienRaceBosses.putIfAbsent(Integer.valueOf(insId), alienRaceBossFightSide);
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
/*     */   public static synchronized short handRankBossEnter(IPlayerSession playerSession, int insId, FightRecordResponse fightRecordResponse) {
/* 115 */     RankBossFightSide rankBossFightSide = rankBosses.get(Integer.valueOf(insId));
/* 116 */     short code = rankBossFightSide.check();
/* 117 */     if (0 != code) {
/* 118 */       return code;
/*     */     }
/* 120 */     RankBossFight rankBossFight = new RankBossFight();
/* 121 */     rankBossFightSide.resetMonsterFightSide();
/* 122 */     rankBossFight.initSide(1, (IFightSide)rankBossFightSide.getMonsterFightSide());
/*     */     
/* 124 */     PlayerFightSide playerFightSide = FightUtil.initPlayerFightSide(playerSession.getPlayer());
/* 125 */     FightUtil.addTempAttribute(playerSession.getPlayer(), 3, playerFightSide);
/* 126 */     rankBossFight.initSide(0, (IFightSide)playerFightSide);
/* 127 */     playerFightSide.initGuid((byte)0);
/*     */     
/* 129 */     rankBossFight.start(playerSession.getPlayer(), rankBossFightSide, fightRecordResponse);
/* 130 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void bossDie(int insId) {
/* 135 */     RankBossFightSide rankBoss = rankBosses.get(Integer.valueOf(insId));
/* 136 */     rankBoss.bossDie();
/*     */   }
/*     */   
/*     */   public static void bossBorn(int insId) {
/* 140 */     RankBossFightSide rankBoss = rankBosses.get(Integer.valueOf(insId));
/* 141 */     rankBoss.bossBorn();
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
/*     */   public static synchronized short handAlienRaceBossEnter(IPlayerSession playerSession, int insId, FightRecordResponse fightRecordResponse) {
/* 154 */     AlienRaceBossFightSide alienRaceBossFightSide = alienRaceBosses.get(Integer.valueOf(insId));
/* 155 */     short code = alienRaceBossFightSide.check();
/* 156 */     if (0 != code) {
/* 157 */       return code;
/*     */     }
/* 159 */     BossHomeParameter bossHomeParameter = (BossHomeParameter)ParameterConstant.getParameter(3);
/* 160 */     BossHomeComponent bossHomeComponent = (BossHomeComponent)playerSession.getPlayer().createIfNotExist(BossHomeComponent.class);
/* 161 */     if (bossHomeComponent.getAlienCount() >= bossHomeComponent.getBuyAlienCount() + bossHomeParameter.getAlienRewardCount()) {
/* 162 */       return 10089;
/*     */     }
/* 164 */     for (AlienRaceBossFightSide alienRaceBossFightSide2 : alienRaceBosses.values()) {
/* 165 */       if (alienRaceBossFightSide2.getBelongToId() == playerSession.getPlayer().getPlayerId() && 
/* 166 */         alienRaceBossFightSide.getBelongToId() != playerSession.getPlayer().getPlayerId()) {
/* 167 */         return 13114;
/*     */       }
/*     */     } 
/*     */     
/* 171 */     long targetPlayerId = 0L;
/* 172 */     boolean isRobot = false;
/* 173 */     if (alienRaceBossFightSide.getBelongToId() != 0L && alienRaceBossFightSide.getBelongToId() != playerSession.getPlayer().getPlayerId()) {
/* 174 */       isRobot = false;
/* 175 */       targetPlayerId = alienRaceBossFightSide.getBelongToId();
/*     */     } else {
/* 177 */       isRobot = true;
/*     */     } 
/* 179 */     AlienRaceFight alienRaceFight = new AlienRaceFight(playerSession.getPlayer(), isRobot, targetPlayerId);
/* 180 */     if (isRobot) {
/* 181 */       alienRaceBossFightSide.resetMonsterFightSide();
/* 182 */       alienRaceFight.initSide(1, (IFightSide)alienRaceBossFightSide.getMonsterFightSide());
/*     */       
/* 184 */       PlayerFightSide playerFightSide = FightUtil.initPlayerFightSide(playerSession.getPlayer());
/* 185 */       FightUtil.addTempAttribute(playerSession.getPlayer(), 15, playerFightSide);
/* 186 */       alienRaceFight.initSide(0, (IFightSide)playerFightSide);
/* 187 */       playerFightSide.initGuid((byte)0);
/*     */     } else {
/*     */       Player player;
/* 190 */       PlayerComponent targetPlayerComponent = LookUpService.getPlayerComponent(targetPlayerId);
/* 191 */       if (null == targetPlayerComponent) {
/* 192 */         return 11703;
/*     */       }
/* 194 */       PvpInfoBean pvpInfoBean = (PvpInfoBean)JsonTableService.getJsonData(3, PvpInfoBean.class);
/* 195 */       if (null == pvpInfoBean) {
/* 196 */         return 11701;
/*     */       }
/* 198 */       IPlayer iPlayer = LookUpService.getByPlayerId(targetPlayerId);
/*     */       
/* 200 */       if (iPlayer == null) {
/* 201 */         PlayerSession.PlayerSessionBuilder builder = new PlayerSession.PlayerSessionBuilder();
/* 202 */         builder.validateAndSetValues();
/* 203 */         IPlayerSession targetPlayerSession = (IPlayerSession)builder.status(ISession.Status.CLOSED).isLogin(false).writeable(false).build();
/* 204 */         player = new Player(targetPlayerSession);
/* 205 */         player.setPlayerId(targetPlayerId);
/* 206 */         player.setPlayerName(targetPlayerComponent.getPlayerName());
/* 207 */         targetPlayerSession.setPlayer((IPlayer)player);
/*     */       } 
/*     */       
/* 210 */       PlayerFightSide playerFightSide = FightUtil.initPlayerFightSide(playerSession.getPlayer());
/* 211 */       alienRaceFight.initSide(0, (IFightSide)playerFightSide);
/* 212 */       PlayerFightSide targetPlayerFightSide = FightUtil.initPlayerPVPFightSide((IPlayer)player, pvpInfoBean);
/* 213 */       alienRaceFight.initSide(1, (IFightSide)targetPlayerFightSide);
/*     */     } 
/* 215 */     alienRaceFight.start(playerSession.getPlayer(), alienRaceBossFightSide, fightRecordResponse);
/* 216 */     if (1 == fightRecordResponse.result || isRobot)
/*     */     {
/* 218 */       if (alienRaceBossFightSide.getCurHp() > 0L) {
/* 219 */         alienRaceBossFightSide.updateBelongToId(playerSession.getPlayer().getPlayerId(), playerSession.getPlayer().getPlayerName());
/*     */       }
/*     */     }
/* 222 */     if (1 == fightRecordResponse.result) {
/* 223 */       int star = 3;
/* 224 */       ResultUtil.saveResult(playerSession.getPlayer().getPlayerId(), (byte)15, insId, star, new ArrayList(), 0);
/*     */     } 
/* 226 */     if (isRobot) {
/* 227 */       fightRecordResponse.result = 1;
/*     */     }
/* 229 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void alienRaceBossBorn(int insId) {
/* 234 */     AlienRaceBossFightSide alienRaceBossFightSide = alienRaceBosses.get(Integer.valueOf(insId));
/* 235 */     alienRaceBossFightSide.bossBorn();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void alienRaceBossDie(int insId) {
/* 240 */     AlienRaceBossFightSide alienRaceBossFightSide = alienRaceBosses.get(Integer.valueOf(insId));
/* 241 */     alienRaceBossFightSide.bossDie();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ArrayList<BossDamageData> getBossDamageData(int insId) {
/* 251 */     RankBossFightSide rankBoss = rankBosses.get(Integer.valueOf(insId));
/* 252 */     return new ArrayList<>(rankBoss.getDamageList());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static BossData getBossData(int insId) {
/* 262 */     RankBossFightSide rankBoss = rankBosses.get(Integer.valueOf(insId));
/* 263 */     BossData bossData = new BossData();
/* 264 */     bossData.insId = insId;
/* 265 */     bossData.hp = rankBoss.getCurHp();
/* 266 */     bossData.hpMax = rankBoss.getMaxHp();
/* 267 */     bossData.remainTime = rankBoss.getRemainTime();
/* 268 */     bossData.nextTime = rankBoss.getNextTime();
/* 269 */     bossData.isOpen = rankBoss.isOpen();
/* 270 */     List<BossDamageData> list = rankBoss.getDamageList();
/* 271 */     int size = 0;
/* 272 */     for (BossDamageData bossDamageData : list) {
/* 273 */       LongIntValue longIntValue = new LongIntValue();
/* 274 */       longIntValue.key = bossDamageData.fromId;
/* 275 */       longIntValue.value = bossDamageData.rank;
/* 276 */       bossData.rank.add(longIntValue);
/* 277 */       size++;
/*     */     } 
/* 279 */     bossData.size = size;
/* 280 */     return bossData;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static NeutralBossData getAlienRaceBossData(int insId) {
/* 291 */     AlienRaceBossFightSide alienBoss = alienRaceBosses.get(Integer.valueOf(insId));
/* 292 */     NeutralBossData bossData = new NeutralBossData();
/* 293 */     bossData.insId = insId;
/* 294 */     bossData.hp = alienBoss.getCurHp();
/* 295 */     bossData.hpMax = alienBoss.getMaxHp();
/* 296 */     bossData.remainTime = alienBoss.getRemainTime();
/* 297 */     bossData.nextTime = alienBoss.getNextTime();
/* 298 */     bossData.isOpen = alienBoss.isOpen();
/* 299 */     bossData.playerName = alienBoss.getPlayerName();
/* 300 */     bossData.playerTime = alienBoss.getPlayerTime();
/* 301 */     bossData.plyaerId = alienBoss.getBelongToId();
/* 302 */     if (alienBoss.getBelongToId() != 0L) {
/* 303 */       PlayerComponent playerComponent = (PlayerComponent)LookUpService.getComponent(alienBoss.getBelongToId(), PlayerComponent.class);
/* 304 */       if (null != playerComponent) {
/* 305 */         bossData.sex = playerComponent.getSex();
/* 306 */         bossData.fashion = playerComponent.getWearFashion();
/*     */       } 
/*     */     } 
/* 309 */     return bossData;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void handle() {
/* 316 */     rankBosses.values().forEach(RankBossFightSide::timeCheck);
/* 317 */     alienRaceBosses.values().forEach(AlienRaceBossFightSide::timeCheck);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getWorldBossLeftCount(BossHomeComponent bossHomeComponent) {
/* 327 */     int type = 2;
/* 328 */     BossHomeParameter bossHomeParameter = (BossHomeParameter)ParameterConstant.getParameter(3);
/* 329 */     Map<Integer, Integer> restores = bossHomeComponent.getRestores();
/* 330 */     int restoreCount = ((Integer)restores.getOrDefault(Integer.valueOf(type), Integer.valueOf(0))).intValue();
/* 331 */     int worldNormalCount = bossHomeParameter.getWorldNormalCount();
/* 332 */     Map<Integer, Integer> counts = bossHomeComponent.getCounts();
/* 333 */     int fightCount = ((Integer)counts.getOrDefault(Integer.valueOf(type), Integer.valueOf(0))).intValue();
/* 334 */     Map<Integer, Integer> buys = bossHomeComponent.getBuys();
/* 335 */     int buyCount = ((Integer)buys.getOrDefault(Integer.valueOf(type), Integer.valueOf(0))).intValue();
/* 336 */     return worldNormalCount + buyCount + restoreCount - fightCount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean checkStartRefrsh() {
/* 343 */     BossHomeParameter bossHomeParameter = (BossHomeParameter)ParameterConstant.getParameter(3);
/* 344 */     if (startTime.get() == 0 || endTime.get() == 0) {
/* 345 */       Calendar calendar = Calendar.getInstance();
/* 346 */       int currHour = calendar.get(11);
/* 347 */       int h = bossHomeParameter.getStartFresh() + bossHomeParameter.getEndFresh();
/* 348 */       if (h > 24 && h - 24 > currHour) {
/* 349 */         startTime.set((int)(TimeUtil.getTodayHourMillis(bossHomeParameter.getStartFresh()) / 1000L - 86400L));
/* 350 */         endTime.set(startTime.get() + bossHomeParameter.getEndFresh() * 60 * 60);
/*     */       } else {
/* 352 */         startTime.set((int)(TimeUtil.getTodayHourMillis(bossHomeParameter.getStartFresh()) / 1000L));
/* 353 */         endTime.set(startTime.get() + bossHomeParameter.getEndFresh() * 60 * 60);
/*     */       } 
/*     */     } 
/* 356 */     if (startTime.get() <= TimeUtil.currentTime() && TimeUtil.currentTime() <= endTime.get()) {
/* 357 */       return true;
/*     */     }
/* 359 */     if (TimeUtil.currentTime() >= endTime.get() && endTime.get() != 0) {
/* 360 */       startTime.set((int)(TimeUtil.getTodayHourMillis(bossHomeParameter.getStartFresh()) / 1000L));
/* 361 */       endTime.set(startTime.get() + bossHomeParameter.getEndFresh() * 60 * 60);
/*     */     } 
/*     */     
/* 364 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\bosshome\BossUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */