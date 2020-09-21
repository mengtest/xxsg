/*     */ package com.linlongyx.sanguo.webgame.app.player;
/*     */ 
/*     */ import com.linlongyx.core.framework.context.AppContext;
/*     */ import com.linlongyx.core.framework.dao.proxy.EntityProxy;
/*     */ import com.linlongyx.core.framework.dao.proxy.IEntityProxy;
/*     */ import com.linlongyx.core.framework.logic.AbstractComponent;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.user.UserComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.attribute.PartnerAttrUpdate;
/*     */ import com.linlongyx.sanguo.webgame.common.structure.ODTime;
/*     */ import com.linlongyx.sanguo.webgame.common.structure.Pair;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.FighterBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.LoginParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.CurrencyType;
/*     */ import com.linlongyx.sanguo.webgame.constant.EquipPart;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.processors.charge.ChargeUtil;
/*     */ import com.linlongyx.sanguo.webgame.service.DBIncrementService;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import com.linlongyx.sanguo.webgame.service.rank.RankingType;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.atomic.AtomicInteger;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PlayerComponent
/*     */   extends AbstractComponent<PlayerEntity>
/*     */ {
/*  34 */   private Pair<Integer, AtomicInteger> statisticRequest = new Pair(Integer.valueOf(TimeUtil.currentTime()), new AtomicInteger(0));
/*     */   
/*     */   public Pair<Integer, AtomicInteger> getStatisticRequest() {
/*  37 */     return this.statisticRequest;
/*     */   }
/*     */   
/*     */   public void setStatisticRequest(Pair<Integer, AtomicInteger> statisticRequest) {
/*  41 */     this.statisticRequest = statisticRequest;
/*     */   }
/*     */   
/*  44 */   private PartnerAttrUpdate playerAttrUp = new PartnerAttrUpdate();
/*     */   
/*     */   private int ip;
/*     */   
/*     */   private int preKillMonsterTime;
/*     */   
/*     */   private Date loginTime;
/*     */   private int channel;
/*     */   
/*     */   public EntityProxy getProxy() {
/*  54 */     return super.getProxy();
/*     */   }
/*     */   private int lastOnlineTime; private long collectId; private long collectTime; private int recommendCampId;
/*     */   public PlayerComponent(long userId, long playerId) {
/*  58 */     super(PlayerEntity.class);
/*  59 */     this.userId = userId;
/*  60 */     this.playerId = playerId;
/*  61 */     makeKey();
/*  62 */     getProxy().createProxy(new Object[] { Long.valueOf(playerId) });
/*     */   }
/*     */   
/*     */   public PlayerComponent(long playerId) {
/*  66 */     super(PlayerEntity.class);
/*  67 */     this.playerId = playerId;
/*  68 */     makeKey();
/*  69 */     getProxy().createProxy(new Object[] { Long.valueOf(playerId) });
/*     */   }
/*     */ 
/*     */   
/*     */   public void init() {
/*  74 */     super.init();
/*  75 */     this.playerAttrUp.initPlayer(this, -1L);
/*  76 */     checkIsWorshipSize();
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
/*     */   public long buildPlayer(long userId, String playerName, byte sex, int serverId) {
/*  89 */     DBIncrementService service = (DBIncrementService)AppContext.getBean("dbIncrementService");
/*  90 */     long playerId = service.generate(this.proxy.getKeyName() + "_" + serverId, AppContext.getPlayerInitId()).longValue();
/*  91 */     getProxy().createProxy(new Object[] { Long.valueOf(playerId) });
/*  92 */     setPlayerName(playerName);
/*  93 */     setUserId(userId);
/*  94 */     setSex(sex);
/*  95 */     setCreateTime(TimeUtil.currentTime());
/*     */ 
/*     */     
/*  98 */     ArrayList<Long> arrayList = new ArrayList<>(6);
/*  99 */     for (int i = 0; i < 6; i++) {
/* 100 */       arrayList.add(Long.valueOf(0L));
/*     */     }
/*     */ 
/*     */     
/* 104 */     arrayList.set(0, Long.valueOf(-1L));
/* 105 */     LoginParameter loginParameter = (LoginParameter)ParameterConstant.getParameter(0);
/* 106 */     int leaderId = loginParameter.getLeaderBySex(sex);
/* 107 */     setLeaderId(leaderId);
/* 108 */     FighterBean fighterBean = (FighterBean)JsonTableService.getJsonData(leaderId, FighterBean.class);
/* 109 */     if (null != fighterBean) {
/* 110 */       setQuality(fighterBean.getQuality());
/*     */     }
/* 112 */     for (EquipPart equipPart : EquipPart.values()) {
/* 113 */       if (null == getEquips().get(Integer.valueOf(equipPart.getPart()))) {
/* 114 */         getEquips().put(Integer.valueOf(equipPart.getPart()), Long.valueOf(0L));
/* 115 */         setEquips(getEquips());
/*     */       } 
/*     */     } 
/* 118 */     setFighters(arrayList);
/* 119 */     this.playerId = playerId;
/* 120 */     this.userId = userId;
/* 121 */     build(playerId);
/* 122 */     makeKey();
/* 123 */     return playerId;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getType() {
/* 128 */     return getClass().getSimpleName();
/*     */   }
/*     */ 
/*     */   
/*     */   public void build(long playerId) {
/* 133 */     setPlayerId(playerId);
/* 134 */     setLevel((short)1);
/* 135 */     setPubIdCount(-1);
/* 136 */     LoginParameter loginParameter = (LoginParameter)ParameterConstant.getParameter(0);
/* 137 */     setMainSceneId(loginParameter.getBornSceneId());
/* 138 */     setLoginOutTime(TimeUtil.currentTime());
/* 139 */     setVip((byte)loginParameter.getDefaultVip());
/* 140 */     this.proxy.setEntityStatus(IEntityProxy.ENTITY_STATUS.STATUS_ADD);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean reset(int time) {
/* 145 */     if (time == 0) {
/*     */       
/* 147 */       resetWorship();
/* 148 */       resetOnlineReward();
/* 149 */       setDesProgress(0);
/* 150 */       setTodayLevel(ChargeUtil.updateEveryDayChargeLevel(getLevel()));
/* 151 */     } else if (time == 18000) {
/*     */     
/*     */     } 
/* 154 */     return true;
/*     */   }
/*     */   
/*     */   private void resetOnlineReward() {
/* 158 */     getOnlineReward().clear();
/* 159 */     getOnlineReward().add(Integer.valueOf(0));
/* 160 */     getOnlineReward().add(Integer.valueOf(0));
/* 161 */     getOnlineReward().add(Integer.valueOf(0));
/* 162 */     setOnlineTime(0);
/* 163 */     setLastOnlineTime(TimeUtil.currentTime());
/*     */   }
/*     */ 
/*     */   
/*     */   private void resetWorship() {
/* 168 */     getIsWorship().clear();
/* 169 */     for (RankingType rankingType : RankingType.values()) {
/* 170 */       getIsWorship().add(Integer.valueOf(0));
/*     */     }
/* 172 */     getProxy().setUpdateStatus("isWorship");
/*     */   }
/*     */   
/*     */   public void addWorship(int type) {
/* 176 */     checkWorshipsSize();
/* 177 */     getWorships().set(type - 1, Integer.valueOf(((Integer)getWorships().get(type - 1)).intValue() + 1));
/* 178 */     getProxy().setUpdateStatus("worships");
/*     */   }
/*     */   
/*     */   public void isWorshop(int type) {
/* 182 */     getIsWorship().set(type - 1, Integer.valueOf(1));
/* 183 */     getProxy().setUpdateStatus("isWorship");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean checkIsWorshop(int type) {
/* 190 */     return (((Integer)getIsWorship().get(type - 1)).intValue() == 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getIsWorship(int type) {
/* 197 */     return ((Integer)getIsWorship().get(type - 1)).intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getWorship(int type) {
/* 204 */     checkWorshipsSize();
/* 205 */     return ((Integer)getWorships().get(type - 1)).intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void checkWorshipsSize() {
/* 212 */     int size = getWorships().size();
/* 213 */     if (size < (RankingType.values()).length) {
/* 214 */       for (int i = size; i < (RankingType.values()).length; i++) {
/* 215 */         getWorships().add(Integer.valueOf(0));
/*     */       }
/* 217 */       getProxy().setUpdateStatus("worships");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void checkIsWorshipSize() {
/* 225 */     int size = getIsWorship().size();
/* 226 */     if (size < (RankingType.values()).length) {
/* 227 */       for (int i = size; i < (RankingType.values()).length; i++) {
/* 228 */         getIsWorship().add(Integer.valueOf(0));
/*     */       }
/* 230 */       getProxy().setUpdateStatus("isWorship");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCurrency(CurrencyType type, long value) {
/* 237 */     getCurrencies().set(type.getType(), Long.valueOf(value));
/* 238 */     getProxy().setUpdateStatus("currencies");
/*     */   }
/*     */   
/*     */   public long getCurrency(CurrencyType type) {
/* 242 */     ArrayList<Long> currencies = getCurrencies();
/* 243 */     if (currencies.size() <= type.getType()) {
/* 244 */       return 0L;
/*     */     }
/* 246 */     if (currencies.get(type.getType()) == null) {
/* 247 */       return 0L;
/*     */     }
/* 249 */     return ((Long)currencies.get(type.getType())).longValue();
/*     */   }
/*     */   
/*     */   public synchronized int addAndGetMailId() {
/* 253 */     int mailId = getMailIdCount() + 1;
/* 254 */     setMailIdCount(mailId);
/* 255 */     saveToDB();
/* 256 */     return mailId;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPrivilege(int type) {
/* 261 */     if (AppContext.getDebug() && type == 4)
/* 262 */       return true; 
/* 263 */     ODTime odTime = getStatus().get(Integer.valueOf(type));
/* 264 */     return (null != odTime && odTime.isValidate(TimeUtil.currentTime()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPlayerStatus(int type, int originTime, int destinationTime) {
/* 274 */     ODTime odTime = new ODTime(originTime, destinationTime);
/* 275 */     getStatus().put(Integer.valueOf(type), odTime);
/* 276 */     this.proxy.setUpdateStatus("status");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearPlayerStatus(int type) {
/* 284 */     ((PlayerEntity)getEntity()).getStatus().remove(Integer.valueOf(type));
/* 285 */     this.proxy.setUpdateStatus("status");
/*     */   }
/*     */   
/*     */   public int getPreKillMonsterTime() {
/* 289 */     return this.preKillMonsterTime;
/*     */   }
/*     */   
/*     */   public void setPreKillMonsterTime(int preKillMonsterTime) {
/* 293 */     this.preKillMonsterTime = preKillMonsterTime;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCollectId(long value) {
/* 303 */     this.collectId = value;
/*     */   }
/*     */   
/*     */   public long getCollectId() {
/* 307 */     return this.collectId;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public long getCollectTime() {
/* 313 */     return this.collectTime;
/*     */   }
/*     */   
/*     */   public void setCollectTime(long value) {
/* 317 */     this.collectTime = value;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRecommendCampId() {
/* 323 */     return this.recommendCampId;
/*     */   }
/*     */   
/*     */   public void setRecommendCampId(int recommendCampId) {
/* 327 */     this.recommendCampId = recommendCampId;
/*     */   }
/*     */   
/*     */   public int getIp() {
/* 331 */     return this.ip;
/*     */   }
/*     */   
/*     */   public void setIp(int ip) {
/* 335 */     this.ip = ip;
/*     */   }
/*     */   
/*     */   public ArrayList<Long> getFighters() {
/* 339 */     return ((PlayerEntity)getEntity()).getFighters();
/*     */   }
/*     */   
/*     */   public void setFighters(ArrayList<Long> fighters) {
/* 343 */     ((PlayerEntity)getEntity()).setFighters(fighters);
/*     */   }
/*     */   
/*     */   public int getBreakthroughs() {
/* 347 */     return ((PlayerEntity)getEntity()).getBreakthroughs();
/*     */   }
/*     */   
/*     */   public void setBreakthroughs(int breakthroughs) {
/* 351 */     ((PlayerEntity)getEntity()).setBreakthroughs(breakthroughs);
/*     */   }
/*     */   
/*     */   public ArrayList<Integer> getTalents() {
/* 355 */     return ((PlayerEntity)getEntity()).getTalents();
/*     */   }
/*     */   
/*     */   public void setTalents(ArrayList<Integer> talents) {
/* 359 */     ((PlayerEntity)getEntity()).setTalents(talents);
/*     */   }
/*     */   
/*     */   public int getDesLv() {
/* 363 */     return ((PlayerEntity)getEntity()).getDesLv();
/*     */   }
/*     */   
/*     */   public void setDesLv(int desLv) {
/* 367 */     ((PlayerEntity)getEntity()).setDesLv(desLv);
/*     */   }
/*     */   
/*     */   public int getDesProgress() {
/* 371 */     return ((PlayerEntity)getEntity()).getDesProgress();
/*     */   }
/*     */   
/*     */   public void setDesProgress(int desProgress) {
/* 375 */     ((PlayerEntity)getEntity()).setDesProgress(desProgress);
/*     */   }
/*     */   
/*     */   public int getStars() {
/* 379 */     return ((PlayerEntity)getEntity()).getStars();
/*     */   }
/*     */   
/*     */   public void setStars(int stars) {
/* 383 */     ((PlayerEntity)getEntity()).setStars(stars);
/*     */   }
/*     */   
/*     */   public Map<Integer, Long> getEquips() {
/* 387 */     return ((PlayerEntity)getEntity()).getEquips();
/*     */   }
/*     */   
/*     */   public void setEquips(Map<Integer, Long> equips) {
/* 391 */     ((PlayerEntity)getEntity()).setEquips(equips);
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
/*     */   public PartnerAttrUpdate getPlayerAttrUp() {
/* 403 */     return this.playerAttrUp;
/*     */   }
/*     */   public long getPlayerId() {
/* 406 */     return ((PlayerEntity)getEntity()).getPlayerId();
/*     */   }
/*     */   
/*     */   public long getUserId() {
/* 410 */     return ((PlayerEntity)getEntity()).getUserId();
/*     */   }
/*     */   
/*     */   public String getPlayerName() {
/* 414 */     return ((PlayerEntity)getEntity()).getPlayerName();
/*     */   }
/*     */   
/*     */   public short getLevel() {
/* 418 */     return ((PlayerEntity)getEntity()).getLevel();
/*     */   }
/*     */   
/*     */   public byte getSex() {
/* 422 */     return ((PlayerEntity)getEntity()).getSex();
/*     */   }
/*     */   
/*     */   public void setSex(byte sex) {
/* 426 */     ((PlayerEntity)getEntity()).setSex(sex);
/*     */   }
/*     */   
/*     */   public int getMainSceneId() {
/* 430 */     return ((PlayerEntity)getEntity()).getMainSceneId();
/*     */   }
/*     */   
/*     */   public void setMainSceneId(int mainSceneId) {
/* 434 */     ((PlayerEntity)getEntity()).setMainSceneId(mainSceneId);
/*     */   }
/*     */   
/*     */   public String getHead() {
/* 438 */     return ((PlayerEntity)getEntity()).getHead();
/*     */   }
/*     */   
/*     */   public void setHead(String head) {
/* 442 */     ((PlayerEntity)getEntity()).setHead(head);
/*     */   }
/*     */   
/*     */   public ArrayList<Long> getCurrencies() {
/* 446 */     return ((PlayerEntity)getEntity()).getCurrencies();
/*     */   }
/*     */   
/*     */   public void setCurrencies(ArrayList<Long> currencies) {
/* 450 */     ((PlayerEntity)getEntity()).setCurrencies(currencies);
/*     */   }
/*     */   
/*     */   public void setUserId(long userId) {
/* 454 */     ((PlayerEntity)getEntity()).setUserId(userId);
/*     */   }
/*     */   
/*     */   public void setPlayerName(String playerName) {
/* 458 */     ((PlayerEntity)getEntity()).setPlayerName(playerName);
/*     */   }
/*     */   
/*     */   public void setLevel(short level) {
/* 462 */     ((PlayerEntity)getEntity()).setLevel(level);
/*     */   }
/*     */   
/*     */   public long getFightValue() {
/* 466 */     return ((PlayerEntity)getEntity()).getFightValue();
/*     */   }
/*     */   
/*     */   public void setFightValue(long fightValue) {
/* 470 */     ((PlayerEntity)getEntity()).setFightValue(fightValue);
/*     */   }
/*     */   
/*     */   public int getCreateTime() {
/* 474 */     return ((PlayerEntity)getEntity()).getCreateTime();
/*     */   }
/*     */   
/*     */   public void setCreateTime(int createTime) {
/* 478 */     ((PlayerEntity)getEntity()).setCreateTime(createTime);
/*     */   }
/*     */   
/*     */   public int getMailIdCount() {
/* 482 */     return ((PlayerEntity)getEntity()).getMailIdCount();
/*     */   }
/*     */   
/*     */   public void setMailIdCount(int mailIdCount) {
/* 486 */     ((PlayerEntity)getEntity()).setMailIdCount(mailIdCount);
/*     */   }
/*     */   
/*     */   public int getPubIdCount() {
/* 490 */     return ((PlayerEntity)getEntity()).getPubIdCount();
/*     */   }
/*     */   
/*     */   public void setPubIdCount(int pubIdCount) {
/* 494 */     ((PlayerEntity)getEntity()).setPubIdCount(pubIdCount);
/*     */   }
/*     */   
/*     */   public Map<Integer, ODTime> getStatus() {
/* 498 */     return ((PlayerEntity)getEntity()).getStatus();
/*     */   }
/*     */   
/*     */   public void setStatus(Map<Integer, ODTime> status) {
/* 502 */     ((PlayerEntity)getEntity()).setStatus(status);
/*     */   }
/*     */   
/*     */   public byte getVip() {
/* 506 */     return ((PlayerEntity)getEntity()).getVip();
/*     */   }
/*     */   
/*     */   public void setVip(byte vip) {
/* 510 */     ((PlayerEntity)getEntity()).setVip(vip);
/*     */   }
/*     */   
/*     */   public int getLoginOutTime() {
/* 514 */     return ((PlayerEntity)getEntity()).getLoginOutTime();
/*     */   }
/*     */   
/*     */   public void setLoginOutTime(int loginOutTime) {
/* 518 */     ((PlayerEntity)getEntity()).setLoginOutTime(loginOutTime);
/*     */   }
/*     */   
/*     */   public int getLeaderId() {
/* 522 */     return ((PlayerEntity)getEntity()).getLeaderId();
/*     */   }
/*     */   
/*     */   public void setLeaderId(int leaderId) {
/* 526 */     ((PlayerEntity)getEntity()).setLeaderId(leaderId);
/*     */   }
/*     */   
/*     */   public int getQuality() {
/* 530 */     return ((PlayerEntity)getEntity()).getQuality();
/*     */   }
/*     */   
/*     */   public void setQuality(int quality) {
/* 534 */     ((PlayerEntity)getEntity()).setQuality(quality);
/*     */   }
/*     */   
/*     */   public ArrayList<Integer> getWorships() {
/* 538 */     return ((PlayerEntity)getEntity()).getWorships();
/*     */   }
/*     */   
/*     */   public void setWorships(ArrayList<Integer> worships) {
/* 542 */     ((PlayerEntity)getEntity()).setWorships(worships);
/*     */   }
/*     */   
/*     */   public ArrayList<Integer> getIsWorship() {
/* 546 */     return ((PlayerEntity)getEntity()).getIsWorship();
/*     */   }
/*     */   
/*     */   public void setIsWorship(ArrayList<Integer> isWorship) {
/* 550 */     ((PlayerEntity)getEntity()).setIsWorship(isWorship);
/*     */   }
/*     */   
/*     */   public List<Integer> getVipReward() {
/* 554 */     return ((PlayerEntity)getEntity()).getVipReward();
/*     */   }
/*     */   
/*     */   public void setVipReward(List<Integer> vipReward) {
/* 558 */     ((PlayerEntity)getEntity()).setVipReward(vipReward);
/*     */   }
/*     */   
/*     */   public Date getLoginTime() {
/* 562 */     return this.loginTime;
/*     */   }
/*     */   
/*     */   public void setLoginTime(Date loginTime) {
/* 566 */     this.loginTime = loginTime;
/*     */   }
/*     */   
/*     */   public int getChannel() {
/* 570 */     if (this.channel == 0) {
/* 571 */       UserComponent userComponent = (UserComponent)LookUpService.getComponent(getUserId(), UserComponent.class);
/* 572 */       if (userComponent != null) {
/* 573 */         this.channel = userComponent.getChannel();
/*     */       }
/*     */     } 
/* 576 */     return this.channel;
/*     */   }
/*     */   
/*     */   public void setChannel(int channel) {
/* 580 */     this.channel = channel;
/*     */   }
/*     */   
/*     */   public int getSceneId() {
/* 584 */     return ((PlayerEntity)getEntity()).getSceneId();
/*     */   }
/*     */   
/*     */   public void setSceneId(int sceneId) {
/* 588 */     ((PlayerEntity)getEntity()).setSceneId(sceneId);
/*     */   }
/*     */   
/*     */   public long getTotalValue() {
/* 592 */     return ((PlayerEntity)getEntity()).getTotalValue();
/*     */   }
/*     */   
/*     */   public void setTotalValue(long totalValue) {
/* 596 */     ((PlayerEntity)getEntity()).setTotalValue(totalValue);
/*     */   }
/*     */   
/*     */   public int getWearTitle() {
/* 600 */     return ((PlayerEntity)getEntity()).getWearTitle();
/*     */   }
/*     */   
/*     */   public void setWearTitle(int wearTitle) {
/* 604 */     ((PlayerEntity)getEntity()).setWearTitle(wearTitle);
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getActiveTitles() {
/* 608 */     return ((PlayerEntity)getEntity()).getActiveTitles();
/*     */   }
/*     */   
/*     */   public void setActiveTitles(Map<Integer, Integer> activeTitles) {
/* 612 */     ((PlayerEntity)getEntity()).setActiveTitles(activeTitles);
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
/*     */   public int getWearFashion() {
/* 624 */     return ((PlayerEntity)getEntity()).getWearFashion();
/*     */   }
/*     */   
/*     */   public void setWearFashion(int wearFashion) {
/* 628 */     ((PlayerEntity)getEntity()).setWearFashion(wearFashion);
/*     */   }
/*     */   
/*     */   public int getOnlineTime() {
/* 632 */     return ((PlayerEntity)getEntity()).getOnlineTime();
/*     */   }
/*     */   
/*     */   public void setOnlineTime(int onlineTime) {
/* 636 */     ((PlayerEntity)getEntity()).setOnlineTime(onlineTime);
/*     */   }
/*     */   
/*     */   public ArrayList<Integer> getOnlineReward() {
/* 640 */     return ((PlayerEntity)getEntity()).getOnlineReward();
/*     */   }
/*     */   
/*     */   public void setOnlineReward(ArrayList<Integer> onlineReward) {
/* 644 */     ((PlayerEntity)getEntity()).setOnlineReward(onlineReward);
/*     */   }
/*     */   
/*     */   public int getLastOnlineTime() {
/* 648 */     return this.lastOnlineTime;
/*     */   }
/*     */   
/*     */   public void setLastOnlineTime(int lastOnlineTime) {
/* 652 */     this.lastOnlineTime = lastOnlineTime;
/*     */   }
/*     */   
/*     */   public int getTodayLevel() {
/* 656 */     return ((PlayerEntity)getEntity()).getTodayLevel();
/*     */   }
/*     */   
/*     */   public void setTodayLevel(int todayLevel) {
/* 660 */     ((PlayerEntity)getEntity()).setTodayLevel(todayLevel);
/*     */   }
/*     */   
/*     */   public long getFirstHand() {
/* 664 */     return ((PlayerEntity)getEntity()).getFirstHand();
/*     */   }
/*     */   
/*     */   public void setFirstHand(long firstHand) {
/* 668 */     ((PlayerEntity)getEntity()).setFirstHand(firstHand);
/*     */   }
/*     */   
/*     */   public int getSoulLevel() {
/* 672 */     return ((PlayerEntity)getEntity()).getSoulLevel();
/*     */   }
/*     */   
/*     */   public void setSoulLevel(int soulLevel) {
/* 676 */     ((PlayerEntity)getEntity()).setSoulLevel(soulLevel);
/*     */   }
/*     */   
/*     */   public ArrayList<Integer> getSoulsFighter() {
/* 680 */     return ((PlayerEntity)getEntity()).getSoulsFighter();
/*     */   }
/*     */   
/*     */   public void setSoulsFighter(ArrayList<Integer> soulsFighter) {
/* 684 */     ((PlayerEntity)getEntity()).setSoulsFighter(soulsFighter);
/*     */   }
/*     */   
/*     */   public long getTalisman() {
/* 688 */     return ((PlayerEntity)getEntity()).getTalisman();
/*     */   }
/*     */   
/*     */   public void setTalisman(long talisman) {
/* 692 */     ((PlayerEntity)getEntity()).setTalisman(talisman);
/*     */   }
/*     */   
/*     */   public Map<Integer, Map<Integer, Long>> getReincarnationMap() {
/* 696 */     return ((PlayerEntity)getEntity()).getReincarnationMap();
/*     */   }
/*     */   
/*     */   public void setReincarnationMap(Map<Integer, Map<Integer, Long>> reincarnationMap) {
/* 700 */     ((PlayerEntity)getEntity()).setReincarnationMap(reincarnationMap);
/*     */   }
/*     */   
/*     */   public HashSet<Integer> getReincarnationIds() {
/* 704 */     return ((PlayerEntity)getEntity()).getReincarnationIds();
/*     */   }
/*     */   
/*     */   public void setReincarnationIds(HashSet<Integer> reincarnationIds) {
/* 708 */     ((PlayerEntity)getEntity()).setReincarnationIds(reincarnationIds);
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\player\PlayerComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */