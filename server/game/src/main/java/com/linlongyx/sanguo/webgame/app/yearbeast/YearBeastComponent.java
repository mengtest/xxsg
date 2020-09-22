/*     */ package com.linlongyx.sanguo.webgame.app.yearbeast;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*     */ import com.linlongyx.core.framework.logic.AbstractMapComponent;
/*     */ import com.linlongyx.core.framework.logic.IPlayer;
/*     */ import com.linlongyx.core.utils.LogUtils;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.FightUtil;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttributeType;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.fighter.IFighter;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.side.FightGroup;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.side.IFightSide;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.side.MonsterFightSide;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.side.PlayerFightSide;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.type.YearBeastBossFight;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.YearBeastBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.YearBeastListBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.BossHomeParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.processors.fight.ResultUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.fight.FightRecordResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.FestivalTime;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.service.MentalRankService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class YearBeastComponent
/*     */   extends AbstractMapComponent<YearBeastEntity> {
/*  38 */   BossHomeParameter bossHomeParameter = (BossHomeParameter)ParameterConstant.getParameter(3);
/*     */   
/*     */   private YearBeastListBean yearBeastListBean;
/*     */   private MonsterFightSide monsterFightSide;
/*     */   private long maxHp;
/*  43 */   private Map<Byte, Long> curHp = new HashMap<>();
/*     */   private int bossId;
/*     */   private boolean isInit;
/*     */   
/*     */   public YearBeastComponent(long playerId) {
/*  48 */     super(YearBeastEntity.class, playerId);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getType() {
/*  53 */     return getClass().getSimpleName();
/*     */   }
/*     */ 
/*     */   
/*     */   public void build(long playerId) {
/*  58 */     this.playerId = playerId;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean reset(int time) {
/*  64 */     if (time == 0) {
/*  65 */       resetTimes();
/*  66 */       resetActivity();
/*     */     } 
/*  68 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void init() {
/*  73 */     super.init();
/*     */   }
/*     */ 
/*     */   
/*     */   public YearBeastEntity getEntity(int id) {
/*  78 */     YearBeastEntity yearBeastEntity = (YearBeastEntity)getEntity(String.valueOf(id));
/*  79 */     if (null == yearBeastEntity) {
/*  80 */       yearBeastEntity = new YearBeastEntity(this.playerId, id);
/*  81 */       addEntity((IEntity)yearBeastEntity);
/*  82 */       saveToDB();
/*     */     } 
/*     */     
/*  85 */     FestivalTime festivalTime = this.bossHomeParameter.getActTime(id);
/*  86 */     if (null != festivalTime) {
/*  87 */       boolean nowOpen = this.bossHomeParameter.isActOpen(id);
/*  88 */       if (nowOpen && !yearBeastEntity.isOpen()) {
/*  89 */         yearBeastEntity.setOpen(true);
/*  90 */         initYearBeastBoss(yearBeastEntity, true);
/*     */       }
/*  92 */       else if (!this.isInit) {
/*  93 */         initYearBeastBoss(yearBeastEntity, false);
/*     */       } 
/*     */     } 
/*     */     
/*  97 */     return yearBeastEntity;
/*     */   }
/*     */ 
/*     */   
/*     */   public short initYearBeastBoss(YearBeastEntity yearBeastEntity, boolean force) {
/* 102 */     if (yearBeastEntity.getCurrBossId() == 0) {
/* 103 */       MentalRankService rankService = (MentalRankService)MContext.getBean("mentalRankService");
/* 104 */       int level = rankService.getLevel(yearBeastEntity.getId());
/* 105 */       Set<Integer> bossList = (Set<Integer>)this.bossHomeParameter.getLevelBoss().get(Integer.valueOf(level));
/* 106 */       YearBeastBean yearBeastBean = (YearBeastBean)JsonTableService.getJsonData(yearBeastEntity.getId(), YearBeastBean.class);
/* 107 */       Set<Integer> reallyBossList = new HashSet<>();
/* 108 */       for (Iterator<Integer> iterator = yearBeastBean.getBossEntry().iterator(); iterator.hasNext(); ) { int boss = ((Integer)iterator.next()).intValue();
/* 109 */         if (bossList.contains(Integer.valueOf(boss))) {
/* 110 */           reallyBossList.add(Integer.valueOf(boss));
/*     */         } }
/*     */       
/* 113 */       if (reallyBossList.size() == yearBeastEntity.getDeathList().size()) {
/* 114 */         return 10301;
/*     */       }
/* 116 */       initYearBeast(yearBeastEntity);
/* 117 */       YearBeastListBean yearBeastListBean2 = (YearBeastListBean)JsonTableService.getJsonData(yearBeastEntity.getCurrBossId(), YearBeastListBean.class);
/* 118 */       this.bossId = yearBeastListBean2.getId();
/* 119 */       this.yearBeastListBean = yearBeastListBean2;
/* 120 */       this.isInit = true;
/*     */     } else {
/* 122 */       this.bossId = yearBeastEntity.getCurrBossId();
/* 123 */       this.yearBeastListBean = (YearBeastListBean)JsonTableService.getJsonData(this.bossId, YearBeastListBean.class);
/* 124 */       this.isInit = true;
/*     */     } 
/* 126 */     this.monsterFightSide = new MonsterFightSide(this.yearBeastListBean, (byte)1);
/* 127 */     this.monsterFightSide.initGuid((byte)1);
/*     */     
/* 129 */     FightGroup fightGroup = this.monsterFightSide.getCurGroup();
/*     */     
/* 131 */     if (force) {
/* 132 */       for (IFighter fighter : fightGroup.getFighters(true)) {
/* 133 */         this.curHp.put(Byte.valueOf(fighter.getGuid()), Long.valueOf(fighter.getHP()));
/*     */       }
/* 135 */       this.maxHp = fightGroup.getAllMaxHp();
/* 136 */       yearBeastEntity.setMaxHp(fightGroup.getAllMaxHp());
/* 137 */       updateMaxHpDB(yearBeastEntity.getId());
/*     */     } else {
/* 139 */       this.curHp = yearBeastEntity.getCurrHp();
/* 140 */       if (this.curHp.isEmpty()) {
/* 141 */         for (IFighter fighter : fightGroup.getFighters(true)) {
/* 142 */           this.curHp.put(Byte.valueOf(fighter.getGuid()), Long.valueOf(fighter.getHP()));
/*     */         }
/*     */       }
/* 145 */       if (getCurTotalHp() <= 0L) {
/* 146 */         for (IFighter fighter : fightGroup.getFighters(true)) {
/* 147 */           this.curHp.put(Byte.valueOf(fighter.getGuid()), Long.valueOf(fighter.getHP()));
/*     */         }
/*     */       } else {
/*     */         
/* 151 */         for (IFighter fighter : this.monsterFightSide.getCurGroup().getFighters(true)) {
/* 152 */           if (this.curHp.containsKey(Byte.valueOf(fighter.getGuid()))) {
/* 153 */             fighter.setAttr(AttributeType.HP.getType(), ((Long)this.curHp.get(Byte.valueOf(fighter.getGuid()))).longValue());
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 158 */       if (yearBeastEntity.getMaxHp() == 0L) {
/* 159 */         yearBeastEntity.setMaxHp(fightGroup.getAllMaxHp());
/*     */       }
/* 161 */       this.maxHp = yearBeastEntity.getMaxHp();
/*     */     } 
/* 163 */     yearBeastEntity.setCurrHp(this.curHp);
/* 164 */     updateCurrHpDB(yearBeastEntity.getId());
/* 165 */     yearBeastEntity.setCurrBossId(this.bossId);
/* 166 */     updateCurrBossIdDB(yearBeastEntity.getId());
/* 167 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateHp(IPlayer iPlayer, YearBeastEntity yearBeastEntity) {
/* 175 */     FightGroup fightGroup = this.monsterFightSide.getCurGroup();
/* 176 */     long temp = fightGroup.getAllHp();
/* 177 */     long preHp = getCurTotalHp();
/* 178 */     long delta = preHp - temp;
/* 179 */     LogUtils.errorLog(new Object[] { "groupBossFight", Integer.valueOf(this.bossId), Long.valueOf(iPlayer.getPlayerId()), Long.valueOf(temp), Long.valueOf(preHp), Long.valueOf(delta) });
/* 180 */     updateCurHp();
/* 181 */     if (temp <= 0L) {
/* 182 */       yearBeastEntity.getDeathList().add(Integer.valueOf(this.bossId));
/* 183 */       yearBeastEntity.setDeathList(yearBeastEntity.getDeathList());
/* 184 */       updateDeathListDB(yearBeastEntity.getId());
/*     */       
/* 186 */       MentalRankService rankService = (MentalRankService)MContext.getBean("mentalRankService");
/* 187 */       int level = rankService.getLevel(yearBeastEntity.getId());
/* 188 */       Set<Integer> bossList = (Set<Integer>)this.bossHomeParameter.getLevelBoss().get(Integer.valueOf(level));
/* 189 */       YearBeastBean yearBeastBean = (YearBeastBean)JsonTableService.getJsonData(yearBeastEntity.getId(), YearBeastBean.class);
/* 190 */       Set<Integer> reallyBossList = new HashSet<>();
/* 191 */       for (Iterator<Integer> iterator1 = yearBeastBean.getBossEntry().iterator(); iterator1.hasNext(); ) { int boss = ((Integer)iterator1.next()).intValue();
/* 192 */         if (bossList.contains(Integer.valueOf(boss))) {
/* 193 */           reallyBossList.add(Integer.valueOf(boss));
/*     */         } }
/*     */       
/* 196 */       YearBeastListBean yearBeastListBean2 = null;
/* 197 */       for (Iterator<Integer> iterator2 = reallyBossList.iterator(); iterator2.hasNext(); ) { int boss = ((Integer)iterator2.next()).intValue();
/* 198 */         YearBeastListBean yearBeastListBean1 = (YearBeastListBean)JsonTableService.getJsonData(boss, YearBeastListBean.class);
/* 199 */         if (null != yearBeastListBean1 && yearBeastListBean1.getFront() == this.bossId) {
/* 200 */           yearBeastListBean2 = yearBeastListBean1;
/*     */           break;
/*     */         }  }
/*     */       
/* 204 */       LogUtils.errorLog(new Object[] { "yearBeastBossDead", Long.valueOf(iPlayer.getPlayerId()), Integer.valueOf(this.bossId), ", killer" });
/*     */       
/* 206 */       ArrayList<Reward> rewards = FinanceUtil.rewardGet(FinanceUtil.transform(this.yearBeastListBean.getChallReward()), iPlayer, ResourceEvent.YearBeastBoss, true);
/* 207 */       ResultUtil.saveResult(this.playerId, (byte)15, this.bossId, 3, rewards, 0);
/* 208 */       if (yearBeastListBean2 != null) {
/* 209 */         resetToTargetIns(yearBeastListBean2.getId(), yearBeastEntity);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public long getCurTotalHp() {
/* 217 */     long totalHp = 0L;
/* 218 */     for (Iterator<Long> iterator = this.curHp.values().iterator(); iterator.hasNext(); ) { long hp = ((Long)iterator.next()).longValue();
/* 219 */       totalHp += hp; }
/*     */     
/* 221 */     return totalHp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initYearBeast(YearBeastEntity yearBeastEntity) {
/* 228 */     MentalRankService rankService = (MentalRankService)MContext.getBean("mentalRankService");
/* 229 */     int level = rankService.getLevel(yearBeastEntity.getId());
/* 230 */     Set<Integer> bossList = (Set<Integer>)this.bossHomeParameter.getLevelBoss().get(Integer.valueOf(level));
/* 231 */     YearBeastBean yearBeastBean = (YearBeastBean)JsonTableService.getJsonData(yearBeastEntity.getId(), YearBeastBean.class);
/* 232 */     Set<Integer> reallyBossList = new HashSet<>();
/* 233 */     for (Iterator<Integer> iterator1 = yearBeastBean.getBossEntry().iterator(); iterator1.hasNext(); ) { int boss = ((Integer)iterator1.next()).intValue();
/* 234 */       if (bossList.contains(Integer.valueOf(boss))) {
/* 235 */         reallyBossList.add(Integer.valueOf(boss));
/*     */       } }
/*     */     
/* 238 */     int bossId = 0;
/* 239 */     for (Iterator<Integer> iterator2 = reallyBossList.iterator(); iterator2.hasNext(); ) { int boss = ((Integer)iterator2.next()).intValue();
/* 240 */       YearBeastListBean yearBeastListBean1 = (YearBeastListBean)JsonTableService.getJsonData(boss, YearBeastListBean.class);
/* 241 */       if (null != yearBeastListBean1 && yearBeastListBean1.getFront() == 0) {
/* 242 */         bossId = yearBeastListBean1.getId();
/* 243 */         this.yearBeastListBean = yearBeastListBean1;
/*     */         break;
/*     */       }  }
/*     */     
/* 247 */     if (null == this.yearBeastListBean) {
/* 248 */       LogUtils.errorLog(new Object[] { "yearBeastListBean1", Integer.valueOf(level), Integer.valueOf(yearBeastEntity.getId()) });
/*     */       return;
/*     */     } 
/* 251 */     yearBeastEntity.setCurrBossId(bossId);
/* 252 */     updateCurrBossIdDB(yearBeastEntity.getId());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void updateCurHp() {
/* 260 */     this.curHp.clear();
/* 261 */     FightGroup fightGroup = this.monsterFightSide.getCurGroup();
/* 262 */     for (IFighter fighter : fightGroup.getFighters(true)) {
/* 263 */       this.curHp.put(Byte.valueOf(fighter.getGuid()), Long.valueOf(fighter.getHP()));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public short resetToTargetIns(int insId, YearBeastEntity yearBeastEntity) {
/* 269 */     YearBeastListBean yearBeastBean = (YearBeastListBean)JsonTableService.getJsonData(insId, YearBeastListBean.class);
/* 270 */     if (yearBeastBean == null) {
/* 271 */       return 11129;
/*     */     }
/* 273 */     this.bossId = insId;
/* 274 */     this.yearBeastListBean = yearBeastBean;
/* 275 */     this.monsterFightSide = new MonsterFightSide(yearBeastBean, (byte)1);
/* 276 */     this.monsterFightSide.initGuid((byte)1);
/*     */     
/* 278 */     FightGroup fightGroup = this.monsterFightSide.getCurGroup();
/* 279 */     this.maxHp = fightGroup.getAllMaxHp();
/* 280 */     yearBeastEntity.setMaxHp(fightGroup.getAllMaxHp());
/* 281 */     updateMaxHpDB(yearBeastEntity.getId());
/* 282 */     this.curHp.clear();
/* 283 */     for (IFighter fighter : fightGroup.getFighters(true)) {
/* 284 */       this.curHp.put(Byte.valueOf(fighter.getGuid()), Long.valueOf(fighter.getHP()));
/*     */     }
/* 286 */     yearBeastEntity.setCurrHp(this.curHp);
/* 287 */     updateCurrHpDB(yearBeastEntity.getId());
/* 288 */     yearBeastEntity.setCurrBossId(this.bossId);
/* 289 */     updateCurrBossIdDB(yearBeastEntity.getId());
/* 290 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public short handleFight(IPlayer iPlayer, FightRecordResponse response, YearBeastEntity yearBeastEntity) {
/* 296 */     YearBeastBossFight yearBeastBossFight = new YearBeastBossFight();
/*     */     
/* 298 */     PlayerFightSide playerFightSide = FightUtil.initPlayerFightSide(iPlayer);
/* 299 */     FightUtil.addTempAttribute(iPlayer, 17, playerFightSide);
/*     */     
/* 301 */     playerFightSide.initGuid((byte)0);
/* 302 */     yearBeastBossFight.initSide(0, (IFightSide)playerFightSide);
/*     */     
/* 304 */     FightUtil.clearBuff((IFightSide)this.monsterFightSide);
/* 305 */     FightUtil.removeDeadFighters((IFightSide)this.monsterFightSide);
/* 306 */     yearBeastBossFight.initSide(1, (IFightSide)this.monsterFightSide);
/*     */     
/* 308 */     yearBeastBossFight.start(iPlayer, response);
/* 309 */     yearBeastEntity.setYearBeastTimes(yearBeastEntity.getYearBeastTimes() + 1);
/* 310 */     updateYearBeastTimesDB(yearBeastEntity.getId());
/* 311 */     response.id = yearBeastEntity.getCurrBossId() + "";
/* 312 */     updateHp(iPlayer, yearBeastEntity);
/* 313 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetActivity() {
/* 321 */     BossHomeParameter bossHomeParameter = (BossHomeParameter)ParameterConstant.getParameter(3);
/* 322 */     List<Integer> list = bossHomeParameter.getActId(false);
/* 323 */     for (Iterator<Integer> iterator = list.iterator(); iterator.hasNext(); ) { int actId = ((Integer)iterator.next()).intValue();
/* 324 */       YearBeastEntity yearBeastEntity = getEntity(actId);
/* 325 */       yearBeastEntity.setCurrBossId(0);
/* 326 */       updateCurrBossIdDB(actId);
/* 327 */       yearBeastEntity.setCurrHp(new HashMap<>());
/* 328 */       updateCurrHpDB(actId);
/* 329 */       yearBeastEntity.setDeathList(new HashSet<>());
/* 330 */       updateDeathListDB(actId);
/* 331 */       yearBeastEntity.setOpen(false);
/* 332 */       updateOpenDB(actId);
/* 333 */       yearBeastEntity.setBuyTimes(0);
/* 334 */       updateBuyTimesDB(actId);
/* 335 */       yearBeastEntity.setYearBeastTimes(0);
/* 336 */       updateYearBeastTimesDB(actId);
/* 337 */       MentalRankService rankService = (MentalRankService)MContext.getBean("mentalRankService");
/* 338 */       if (rankService.getWorldLevel(actId) != 0) {
/* 339 */         rankService.setLevel(actId);
/*     */       } }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetTimes() {
/* 349 */     BossHomeParameter bossHomeParameter = (BossHomeParameter)ParameterConstant.getParameter(3);
/* 350 */     List<Integer> list = bossHomeParameter.getActId(true);
/* 351 */     for (Iterator<Integer> iterator = list.iterator(); iterator.hasNext(); ) { int actId = ((Integer)iterator.next()).intValue();
/* 352 */       YearBeastEntity yearBeastEntity = getEntity(actId);
/* 353 */       yearBeastEntity.setBuyTimes(0);
/* 354 */       updateBuyTimesDB(actId);
/* 355 */       yearBeastEntity.setYearBeastTimes(0);
/* 356 */       updateYearBeastTimesDB(actId); }
/*     */   
/*     */   }
/*     */   
/*     */   public long getMaxHp() {
/* 361 */     return this.maxHp;
/*     */   }
/*     */   
/*     */   public void updateCurrBossIdDB(int id) {
/* 365 */     getProxy().setUpdate(String.valueOf(id), "currBossId");
/*     */   }
/*     */   
/*     */   public void updateBuyTimesDB(int id) {
/* 369 */     getProxy().setUpdate(String.valueOf(id), "buyTimes");
/*     */   }
/*     */   
/*     */   public void updateYearBeastTimesDB(int id) {
/* 373 */     getProxy().setUpdate(String.valueOf(id), "yearBeastTimes");
/*     */   }
/*     */   
/*     */   public void updateOpenDB(int id) {
/* 377 */     getProxy().setUpdate(String.valueOf(id), "open");
/*     */   }
/*     */   
/*     */   public void updateDeathListDB(int id) {
/* 381 */     getProxy().setUpdate(String.valueOf(id), "deathList");
/*     */   }
/*     */   
/*     */   public void updateCurrHpDB(int id) {
/* 385 */     getProxy().setUpdate(String.valueOf(id), "currHp");
/*     */   }
/*     */   
/*     */   public void updateMaxHpDB(int id) {
/* 389 */     getProxy().setUpdate(String.valueOf(id), "maxHp");
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\yearbeast\YearBeastComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */