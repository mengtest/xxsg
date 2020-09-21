/*     */ package com.linlongyx.sanguo.webgame.common.fight.side;
/*     */ 
/*     */ import com.linlongyx.core.framework.concurrent.Fibers;
/*     */ import com.linlongyx.core.framework.logic.IPlayer;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.core.utils.LogUtils;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.FightUtil;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttributeType;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.comparator.DamageComparator;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.fighter.IFighter;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.type.WorldBossFight;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.BossHomeBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.BossWorldRewardBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.BossHomeParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.LanguageConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.bosshome.WorldBossUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.mail.MailUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.bosshome.WorldInfoResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.bosshome.WorldPushResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.fight.FightRecordResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.BossDamageData;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ScheduledExecutorService;
/*     */ import java.util.concurrent.ScheduledFuture;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import java.util.concurrent.locks.Lock;
/*     */ import java.util.concurrent.locks.ReentrantLock;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WorldBossFightSide
/*     */ {
/*     */   private int timeKey;
/*     */   private int insId;
/*     */   private long maxHp;
/*  52 */   private Map<Byte, Long> curHp = new HashMap<>();
/*  53 */   private int killNum = 0;
/*     */   private int nextTime;
/*     */   private int openTime;
/*     */   private volatile byte status;
/*     */   private BossHomeBean bossHomeBean;
/*     */   private MonsterFightSide monsterFightSide;
/*  59 */   private ScheduledExecutorService executorService = Fibers.createScheduler();
/*     */   private ScheduledFuture scheduledFuture;
/*  61 */   private Map<Long, BossDamageData> damageMap = new HashMap<>();
/*  62 */   private BossHomeParameter bossHomeParameter = (BossHomeParameter)ParameterConstant.getParameter(3);
/*  63 */   private Lock lock = new ReentrantLock();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initWorldBossFromDb(WorldBossUtil.WorldBossData worldBossData, List<BossDamageData> damageList) {
/*  69 */     this.damageMap.clear();
/*  70 */     for (BossDamageData bossDamageData : damageList) {
/*  71 */       this.damageMap.put(Long.valueOf(bossDamageData.fromId), bossDamageData);
/*     */     }
/*  73 */     initMonsterFightSide(worldBossData);
/*     */   }
/*     */   
/*     */   private void initMonsterFightSide(int insId) {
/*     */     try {
/*  78 */       this.lock.lock();
/*  79 */       int curTime = TimeUtil.currentTime();
/*  80 */       this.insId = insId;
/*  81 */       this.bossHomeBean = (BossHomeBean)JsonTableService.getJsonData(this.insId, BossHomeBean.class);
/*  82 */       this.monsterFightSide = new MonsterFightSide(this.bossHomeBean, (byte)1);
/*  83 */       this.openTime = (this.openTime == 0) ? curTime : this.openTime;
/*  84 */       FightGroup fightGroup = this.monsterFightSide.getCurGroup();
/*  85 */       this.maxHp = fightGroup.getAllMaxHp();
/*  86 */       for (IFighter fighter : fightGroup.getFighters(true)) {
/*  87 */         this.curHp.put(Byte.valueOf(fighter.getGuid()), Long.valueOf(fighter.getHP()));
/*     */       }
/*     */       
/*  90 */       this.nextTime = (this.nextTime == 0) ? (curTime + this.bossHomeParameter.getWorldRevive()) : this.nextTime;
/*  91 */       this.timeKey = (this.timeKey == 0) ? TimeUtil.getCurrentYearMonthDay() : this.timeKey;
/*  92 */       this.status = WorldBossUtil.WORLD_BOSS_STATUS.OPENING.getType();
/*  93 */       this.monsterFightSide.initGuid((byte)1);
/*     */     } finally {
/*  95 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   private long getCurTotalHp() {
/* 100 */     long totalHp = 0L;
/* 101 */     for (Iterator<Long> iterator = this.curHp.values().iterator(); iterator.hasNext(); ) { long hp = ((Long)iterator.next()).longValue();
/* 102 */       totalHp += hp; }
/*     */     
/* 104 */     return totalHp;
/*     */   }
/*     */   
/*     */   private void initMonsterFightSide(WorldBossUtil.WorldBossData worldBossData) {
/*     */     try {
/* 109 */       this.lock.lock();
/* 110 */       this.curHp = worldBossData.curHp;
/* 111 */       this.insId = (worldBossData.insId == 0) ? this.bossHomeParameter.getWorldBossInsId() : worldBossData.insId;
/* 112 */       this.maxHp = worldBossData.maxHp;
/* 113 */       this.curHp = worldBossData.curHp;
/* 114 */       this.killNum = worldBossData.killNum;
/* 115 */       this.nextTime = worldBossData.nextTime;
/* 116 */       this.openTime = worldBossData.openTime;
/* 117 */       this.timeKey = worldBossData.timeKey;
/* 118 */       this.status = worldBossData.status;
/* 119 */       int curTime = TimeUtil.currentTime();
/* 120 */       if (getCurTotalHp() <= 0L && worldBossData.status == WorldBossUtil.WORLD_BOSS_STATUS.WAITING.getType() && worldBossData.nextTime > 0 && worldBossData.nextTime <= curTime) {
/*     */         
/* 122 */         int nextInsId = worldBossData.insId + 1;
/* 123 */         BossHomeBean bossHomeBean = (BossHomeBean)JsonTableService.getJsonData(nextInsId, BossHomeBean.class);
/* 124 */         if (bossHomeBean == null) {
/* 125 */           this.status = WorldBossUtil.WORLD_BOSS_STATUS.END.getType();
/*     */           return;
/*     */         } 
/* 128 */         initMonsterFightSide(nextInsId);
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 133 */       this.bossHomeBean = (BossHomeBean)JsonTableService.getJsonData(this.insId, BossHomeBean.class);
/* 134 */       this.monsterFightSide = new MonsterFightSide(this.bossHomeBean, (byte)1);
/* 135 */       this.monsterFightSide.initGuid((byte)1);
/*     */ 
/*     */       
/* 138 */       for (IFighter fighter : this.monsterFightSide.getCurGroup().getFighters(true)) {
/* 139 */         if (this.curHp.containsKey(Byte.valueOf(fighter.getGuid()))) {
/* 140 */           fighter.setAttr(AttributeType.HP.getType(), ((Long)this.curHp.get(Byte.valueOf(fighter.getGuid()))).longValue());
/*     */         }
/*     */       } 
/*     */     } finally {
/*     */       
/* 145 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void open() {
/*     */     try {
/* 152 */       this.lock.lock();
/* 153 */       if (this.status == WorldBossUtil.WORLD_BOSS_STATUS.OPENING.getType()) {
/* 154 */         LogUtil.debugLog(new Object[] { "world boss already open", Integer.valueOf(this.timeKey) });
/*     */         return;
/*     */       } 
/* 157 */       this.damageMap.clear();
/* 158 */       initMonsterFightSide(this.bossHomeParameter.getWorldBossInsId());
/* 159 */       LogUtil.debugLog(new Object[] { "world boss open", Integer.valueOf(this.timeKey) });
/*     */     } finally {
/* 161 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void close() {
/*     */     try {
/* 167 */       this.lock.lock();
/* 168 */       if (this.scheduledFuture != null) {
/* 169 */         this.scheduledFuture.cancel(true);
/*     */       }
/* 171 */       if (this.status == WorldBossUtil.WORLD_BOSS_STATUS.CLOSE.getType()) {
/* 172 */         LogUtil.debugLog(new Object[] { "world boss already close", Integer.valueOf(this.timeKey) });
/*     */         return;
/*     */       } 
/* 175 */       sendRankReward();
/* 176 */       this.status = WorldBossUtil.WORLD_BOSS_STATUS.CLOSE.getType();
/* 177 */       LogUtil.debugLog(new Object[] { "world boss close", Integer.valueOf(this.timeKey) });
/*     */     } finally {
/* 179 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public short handleFight(IPlayer iPlayer, FightRecordResponse response) {
/*     */     try {
/* 186 */       this.lock.lock();
/* 187 */       if (!canFight()) {
/* 188 */         return 10312;
/*     */       }
/* 190 */       WorldBossFight groupBossFight = new WorldBossFight();
/*     */       
/* 192 */       PlayerFightSide playerFightSide = FightUtil.initPlayerFightSide(iPlayer);
/* 193 */       playerFightSide.initGuid((byte)0);
/* 194 */       groupBossFight.initSide(0, playerFightSide);
/*     */       
/* 196 */       FightUtil.clearBuff(this.monsterFightSide);
/* 197 */       FightUtil.removeDeadFighters(this.monsterFightSide);
/* 198 */       groupBossFight.initSide(1, this.monsterFightSide);
/*     */ 
/*     */       
/* 201 */       groupBossFight.start(iPlayer, response);
/* 202 */       calDamage(iPlayer);
/* 203 */       return 0;
/*     */     } finally {
/* 205 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void updateCurHp() {
/*     */     try {
/* 214 */       this.lock.lock();
/* 215 */       this.curHp.clear();
/* 216 */       FightGroup fightGroup = this.monsterFightSide.getCurGroup();
/* 217 */       for (IFighter fighter : fightGroup.getFighters(true)) {
/* 218 */         this.curHp.put(Byte.valueOf(fighter.getGuid()), Long.valueOf(fighter.getHP()));
/*     */       }
/*     */     } finally {
/*     */       
/* 222 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void calDamage(IPlayer iPlayer) {
/*     */     try {
/* 228 */       this.lock.lock();
/* 229 */       FightGroup fightGroup = this.monsterFightSide.getCurGroup();
/* 230 */       long temp = fightGroup.getAllHp();
/* 231 */       long curTotalHp = getCurTotalHp();
/* 232 */       long hurt = curTotalHp - temp;
/*     */       
/* 234 */       updateCurHp();
/*     */       
/* 236 */       LogUtil.errorLog(new Object[] { "world boss damage,", Long.valueOf(iPlayer.getPlayerId()), iPlayer.getPlayerName(), Long.valueOf(hurt) });
/* 237 */       if (this.damageMap.containsKey(Long.valueOf(iPlayer.getPlayerId()))) {
/* 238 */         ((BossDamageData)this.damageMap.get(Long.valueOf(iPlayer.getPlayerId()))).damage += hurt;
/*     */       } else {
/* 240 */         BossDamageData bossDamageData = new BossDamageData();
/* 241 */         bossDamageData.fromId = iPlayer.getPlayerId();
/* 242 */         bossDamageData.name = iPlayer.getPlayerName();
/* 243 */         bossDamageData.damage = hurt;
/* 244 */         this.damageMap.put(Long.valueOf(iPlayer.getPlayerId()), bossDamageData);
/*     */       } 
/* 246 */       broadcast(iPlayer.getPlayerName(), hurt);
/* 247 */       getDamageRankList();
/* 248 */       if (getCurTotalHp() <= 0L) {
/* 249 */         int nextInsId = this.insId + 1;
/* 250 */         BossHomeBean bossHomeBean = (BossHomeBean)JsonTableService.getJsonData(nextInsId, BossHomeBean.class);
/* 251 */         if (bossHomeBean != null) {
/* 252 */           if (this.status == WorldBossUtil.WORLD_BOSS_STATUS.OPENING.getType()) {
/* 253 */             this.status = WorldBossUtil.WORLD_BOSS_STATUS.WAITING.getType();
/* 254 */             this.nextTime = TimeUtil.currentTime() + this.bossHomeParameter.getWorldRevive();
/* 255 */             this.killNum++;
/* 256 */             this.scheduledFuture = this.executorService.schedule(() -> initMonsterFightSide(nextInsId), this.bossHomeParameter.getWorldRevive(), TimeUnit.SECONDS);
/*     */           } 
/*     */         } else {
/* 259 */           this.status = WorldBossUtil.WORLD_BOSS_STATUS.END.getType();
/*     */         } 
/*     */       } 
/*     */     } finally {
/* 263 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void broadcast(String name, long hurt) {
/* 268 */     WorldPushResponse response = new WorldPushResponse();
/* 269 */     response.insId = this.insId;
/* 270 */     response.curHp = getCurTotalHp();
/* 271 */     response.maxHp = this.maxHp;
/* 272 */     response.name = name;
/* 273 */     response.hurt = hurt;
/* 274 */     LookUpService.broadcast((ResponseBase)response);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void sendRankReward() {
/* 282 */     List<Integer> list = this.bossHomeParameter.getWorldBossRewardByType(3);
/*     */     
/* 284 */     String title = LanguageConstant.getLanguage(303);
/*     */     
/* 286 */     List<BossDamageData> damageList = getDamageRankList();
/* 287 */     for (BossDamageData bossDamageData : damageList) {
/* 288 */       for (Integer id : list) {
/* 289 */         BossWorldRewardBean bossWorldRewardBean = (BossWorldRewardBean)JsonTableService.getJsonData(id.intValue(), BossWorldRewardBean.class);
/* 290 */         if (null == bossWorldRewardBean) {
/*     */           continue;
/*     */         }
/* 293 */         if (bossWorldRewardBean.getLowrank() <= bossDamageData.rank && bossDamageData.rank <= bossWorldRewardBean.getHighrank()) {
/* 294 */           String content = LanguageConstant.getAndReplaceLanguage(304, new String[] { String.valueOf(bossDamageData.rank) });
/* 295 */           ArrayList<Reward> rewards = new ArrayList<>();
/* 296 */           rewards.addAll(FinanceUtil.transform(bossWorldRewardBean.getReward()));
/* 297 */           MailUtil.sendSysMail(bossDamageData.fromId, FinanceUtil.rewardGet(rewards), title, content);
/*     */           break;
/*     */         } 
/*     */       } 
/* 301 */       LogUtils.errorLog(new Object[] { "worldRankReward", Long.valueOf(bossDamageData.fromId), bossDamageData.toString() });
/*     */     } 
/*     */   }
/*     */   
/*     */   public List<BossDamageData> getDamageRankList() {
/*     */     try {
/* 307 */       this.lock.lock();
/* 308 */       List<BossDamageData> damageList = new ArrayList<>(this.damageMap.values());
/* 309 */       damageList.sort((Comparator<? super BossDamageData>)new DamageComparator());
/* 310 */       for (int i = 0; i < damageList.size(); i++) {
/* 311 */         BossDamageData bossDamageData = damageList.get(i);
/* 312 */         bossDamageData.rank = i + 1;
/*     */       } 
/* 314 */       return damageList;
/*     */     } finally {
/* 316 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getResponse(long playerId, WorldInfoResponse response) {
/*     */     try {
/* 328 */       this.lock.lock();
/* 329 */       response.curHp = getCurTotalHp();
/* 330 */       response.maxHp = this.maxHp;
/* 331 */       response.insId = this.insId;
/* 332 */       response.num = this.killNum;
/* 333 */       response.nextTime = this.nextTime;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 338 */       BossDamageData bossDamageData = this.damageMap.get(Long.valueOf(playerId));
/* 339 */       if (null != bossDamageData) {
/* 340 */         response.rank = bossDamageData.rank;
/* 341 */         response.hurt = isOpen() ? bossDamageData.damage : 0L;
/*     */       } 
/* 343 */       response.status = this.status;
/*     */     } finally {
/* 345 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpen() {
/* 351 */     return (this.status != WorldBossUtil.WORLD_BOSS_STATUS.CLOSE.getType());
/*     */   }
/*     */   
/*     */   public boolean canFight() {
/* 355 */     return (this.status == WorldBossUtil.WORLD_BOSS_STATUS.OPENING.getType());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MonsterFightSide getMonsterFightSide() {
/* 364 */     return this.monsterFightSide;
/*     */   }
/*     */   
/*     */   public int getTimeKey() {
/* 368 */     return this.timeKey;
/*     */   }
/*     */   
/*     */   public int getInsId() {
/* 372 */     return this.insId;
/*     */   }
/*     */   
/*     */   public long getMaxHp() {
/* 376 */     return this.maxHp;
/*     */   }
/*     */   
/*     */   public Map<Byte, Long> getCurHp() {
/* 380 */     return this.curHp;
/*     */   }
/*     */   
/*     */   public int getKillNum() {
/* 384 */     return this.killNum;
/*     */   }
/*     */   
/*     */   public int getNextTime() {
/* 388 */     return this.nextTime;
/*     */   }
/*     */   
/*     */   public int getOpenTime() {
/* 392 */     return this.openTime;
/*     */   }
/*     */   
/*     */   public byte getStatus() {
/* 396 */     return this.status;
/*     */   }
/*     */   
/*     */   public Map<Long, BossDamageData> getDamageMap() {
/* 400 */     return this.damageMap;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\side\WorldBossFightSide.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */