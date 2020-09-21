/*     */ package com.linlongyx.sanguo.webgame.app.extend;
/*     */ 
/*     */ import com.linlongyx.core.framework.logic.AbstractComponent;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.EverydayChargeBean;
/*     */ import com.linlongyx.sanguo.webgame.constant.LanguageConstant;
/*     */ import com.linlongyx.sanguo.webgame.processors.mail.MailUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.fight.InsRewardResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ExtendComponent
/*     */   extends AbstractComponent<ExtendEntity>
/*     */ {
/*  25 */   private InsRewardResponse insRewardResponse = new InsRewardResponse();
/*     */   
/*     */   public ExtendComponent(long playerId) {
/*  28 */     super(ExtendEntity.class);
/*  29 */     this.playerId = playerId;
/*  30 */     makeKey();
/*  31 */     getProxy().createProxy(new Object[] { Long.valueOf(playerId) });
/*     */   }
/*     */ 
/*     */   
/*     */   public void init() {
/*  36 */     super.init();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getType() {
/*  41 */     return getClass().getSimpleName();
/*     */   }
/*     */ 
/*     */   
/*     */   public void build(long playerId) {
/*  46 */     setZeroResetDate(TimeUtil.getCurrentYearMonthDay());
/*  47 */     setPlayerId(playerId);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean reset(int time) {
/*  52 */     if (time == 0) {
/*  53 */       setCombatTimes(0);
/*  54 */       setCombatCostTimes(0);
/*     */       
/*  56 */       setTodayRecharge(0L);
/*  57 */       setDailyConsume(0L);
/*  58 */       setTodayFirstCharge(0L);
/*  59 */       setTodayFirstReward(new HashMap<>());
/*  60 */       setBuyGoldTimes(0);
/*  61 */       setBuyGoldCost(0);
/*  62 */       setWanderReward(new HashMap<>());
/*     */     } 
/*  64 */     return true;
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
/*     */   public void setInsRewardResponse(byte type, int insId, int star, ArrayList<Reward> list, int percent) {
/*  76 */     this.insRewardResponse.reset();
/*  77 */     this.insRewardResponse.type = type;
/*  78 */     this.insRewardResponse.insId = insId;
/*  79 */     this.insRewardResponse.star = star;
/*  80 */     this.insRewardResponse.percent = percent;
/*  81 */     this.insRewardResponse.list.addAll(list);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void todayFirstChargeReward() {
/*  88 */     Map<Integer, Object> map = JsonTableService.getJsonTable(EverydayChargeBean.class);
/*  89 */     ArrayList<Reward> rewards = new ArrayList<>();
/*  90 */     PlayerComponent playerComponent = LookUpService.getPlayerComponent(this.playerId);
/*  91 */     for (Object object : map.values()) {
/*  92 */       EverydayChargeBean everydayChargeBean = (EverydayChargeBean)object;
/*  93 */       if (getFunctionIds().contains(Integer.valueOf(6403)) && playerComponent.getTodayLevel() == everydayChargeBean.getPlayerLevel() && everydayChargeBean.getRmb() <= getTodayRecharge() && !getTodayFirstReward().containsKey(Integer.valueOf(everydayChargeBean.getId()))) {
/*  94 */         rewards.addAll(FinanceUtil.transform(everydayChargeBean.getReward()));
/*     */       }
/*     */     } 
/*  97 */     if (rewards.size() > 0) {
/*  98 */       String title = LanguageConstant.getLanguage(7501);
/*  99 */       String content = LanguageConstant.getAndReplaceLanguage(7502, new String[0]);
/* 100 */       MailUtil.sendSysMail(this.playerId, rewards, title, content);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public InsRewardResponse getInsRewardResponse() {
/* 107 */     return this.insRewardResponse;
/*     */   }
/*     */   
/*     */   public void clearInsRewardResponse() {
/* 111 */     this.insRewardResponse.reset();
/*     */   }
/*     */   
/*     */   public int getBattleMaxCount() {
/* 115 */     return ((ExtendEntity)getEntity()).getBattleMaxCount();
/*     */   }
/*     */   
/*     */   public void setBattleMaxCount(int battleMaxCount) {
/* 119 */     ((ExtendEntity)getEntity()).setBattleMaxCount(battleMaxCount);
/*     */   }
/*     */   
/*     */   public Map<Integer, Long> getTotalCurrency() {
/* 123 */     return ((ExtendEntity)getEntity()).getTotalCurrency();
/*     */   }
/*     */   
/*     */   public void setTotalCurrency(Map<Integer, Long> totalCurrency) {
/* 127 */     ((ExtendEntity)getEntity()).setTotalCurrency(totalCurrency);
/*     */   }
/*     */   
/*     */   public int getBagSize() {
/* 131 */     return ((ExtendEntity)getEntity()).getBagSize();
/*     */   }
/*     */   
/*     */   public void setBagSize(int bagSize) {
/* 135 */     ((ExtendEntity)getEntity()).setBagSize(bagSize);
/*     */   }
/*     */   
/*     */   public int getOfflineSec() {
/* 139 */     return ((ExtendEntity)getEntity()).getOfflineSec();
/*     */   }
/*     */   
/*     */   public void setOfflineSec(int offlineSec) {
/* 143 */     ((ExtendEntity)getEntity()).setOfflineSec(offlineSec);
/*     */   }
/*     */   
/*     */   public Map<Integer, ArrayList<Integer>> getHandbookMap() {
/* 147 */     return ((ExtendEntity)getEntity()).getHandbookMap();
/*     */   }
/*     */   
/*     */   public void setHandbookMap(Map<Integer, ArrayList<Integer>> handbookMap) {
/* 151 */     ((ExtendEntity)getEntity()).setHandbookMap(handbookMap);
/*     */   }
/*     */   
/*     */   public int getZeroResetDate() {
/* 155 */     return ((ExtendEntity)getEntity()).getZeroResetDate();
/*     */   }
/*     */   
/*     */   public void setZeroResetDate(int zeroResetDate) {
/* 159 */     ((ExtendEntity)getEntity()).setZeroResetDate(zeroResetDate);
/*     */   }
/*     */   
/*     */   public int getCombatTimes() {
/* 163 */     return ((ExtendEntity)getEntity()).getCombatTimes();
/*     */   }
/*     */   
/*     */   public void setCombatTimes(int combatTimes) {
/* 167 */     ((ExtendEntity)getEntity()).setCombatTimes(combatTimes);
/*     */   }
/*     */   
/*     */   public int getCombatCostTimes() {
/* 171 */     return ((ExtendEntity)getEntity()).getCombatCostTimes();
/*     */   }
/*     */   
/*     */   public void setCombatCostTimes(int combatCostTimes) {
/* 175 */     ((ExtendEntity)getEntity()).setCombatCostTimes(combatCostTimes);
/*     */   }
/*     */   
/*     */   public Map<Integer, Long> getConsumeCurrency() {
/* 179 */     return ((ExtendEntity)getEntity()).getConsumeCurrency();
/*     */   }
/*     */   
/*     */   public void setConsumeCurrency(Map<Integer, Long> consumeCurrency) {
/* 183 */     ((ExtendEntity)getEntity()).setConsumeCurrency(consumeCurrency);
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getWpHandbook() {
/* 187 */     return ((ExtendEntity)getEntity()).getWpHandbook();
/*     */   }
/*     */   
/*     */   public void setWpHandbook(Map<Integer, Integer> wpHandbook) {
/* 191 */     ((ExtendEntity)getEntity()).setWpHandbook(wpHandbook);
/*     */   }
/*     */   
/*     */   public long getEquipId() {
/* 195 */     return ((ExtendEntity)getEntity()).getEquipId();
/*     */   }
/*     */   
/*     */   public void setEquipId(long equipId) {
/* 199 */     ((ExtendEntity)getEntity()).setEquipId(equipId);
/*     */   }
/*     */   
/*     */   public Set<Integer> getFunctionIds() {
/* 203 */     return ((ExtendEntity)getEntity()).getFunctionIds();
/*     */   }
/*     */   
/*     */   public void setFunctionIds(Set<Integer> functionIds) {
/* 207 */     ((ExtendEntity)getEntity()).setFunctionIds(functionIds);
/*     */   }
/*     */   
/*     */   public long getPartnerId() {
/* 211 */     return ((ExtendEntity)getEntity()).getPartnerId();
/*     */   }
/*     */   
/*     */   public void setPartnerId(long partnerId) {
/* 215 */     ((ExtendEntity)getEntity()).setPartnerId(partnerId);
/*     */   }
/*     */   
/*     */   public Set<Integer> getFirstReChargeSet() {
/* 219 */     return ((ExtendEntity)getEntity()).getFirstReChargeSet();
/*     */   }
/*     */   
/*     */   public void setFirstReChargeSet(Set<Integer> firstReChargeSet) {
/* 223 */     ((ExtendEntity)getEntity()).setFirstReChargeSet(firstReChargeSet);
/*     */   }
/*     */   
/*     */   public Set<Integer> getFirstReward() {
/* 227 */     return ((ExtendEntity)getEntity()).getFirstReward();
/*     */   }
/*     */   
/*     */   public void setFirstReward(Set<Integer> firstReward) {
/* 231 */     ((ExtendEntity)getEntity()).setFirstReward(firstReward);
/*     */   }
/*     */   
/*     */   public long getTodayRecharge() {
/* 235 */     return ((ExtendEntity)getEntity()).getTodayRecharge();
/*     */   }
/*     */   
/*     */   public void setTodayRecharge(long todayRecharge) {
/* 239 */     ((ExtendEntity)getEntity()).setTodayRecharge(todayRecharge);
/*     */   }
/*     */   
/*     */   public int getSpeedRadio() {
/* 243 */     return ((ExtendEntity)getEntity()).getSpeedRadio();
/*     */   }
/*     */   
/*     */   public void setSpeedRadio(int speedRadio) {
/* 247 */     ((ExtendEntity)getEntity()).setSpeedRadio(speedRadio);
/*     */   }
/*     */   
/*     */   public long getTotalChangeBoss() {
/* 251 */     return ((ExtendEntity)getEntity()).getTotalChangeBoss();
/*     */   }
/*     */   
/*     */   public void setTotalChangeBoss(long totalChangeBoss) {
/* 255 */     ((ExtendEntity)getEntity()).setTotalChangeBoss(totalChangeBoss);
/*     */   }
/*     */   
/*     */   public long getTotalChargeCCB() {
/* 259 */     return ((ExtendEntity)getEntity()).getTotalChargeCCB();
/*     */   }
/*     */   
/*     */   public void setTotalChargeCCB(long totalChargeCCB) {
/* 263 */     ((ExtendEntity)getEntity()).setTotalChargeCCB(totalChargeCCB);
/*     */   }
/*     */   
/*     */   public long getTotalComPartner() {
/* 267 */     return ((ExtendEntity)getEntity()).getTotalComPartner();
/*     */   }
/*     */   
/*     */   public void setTotalComPartner(long totalComPartner) {
/* 271 */     ((ExtendEntity)getEntity()).setTotalComPartner(totalComPartner);
/*     */   }
/*     */   
/*     */   public Set<Integer> getNewFirstReward() {
/* 275 */     return ((ExtendEntity)getEntity()).getNewFirstReward();
/*     */   }
/*     */   
/*     */   public void setNewFirstReward(Set<Integer> newFirstReward) {
/* 279 */     ((ExtendEntity)getEntity()).setNewFirstReward(newFirstReward);
/*     */   }
/*     */   
/*     */   public Set<Integer> getNewFirstCharge() {
/* 283 */     return ((ExtendEntity)getEntity()).getNewFirstCharge();
/*     */   }
/*     */   
/*     */   public void setNewFirstCharge(Set<Integer> newFirstCharge) {
/* 287 */     ((ExtendEntity)getEntity()).setNewFirstCharge(newFirstCharge);
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getGroupReward() {
/* 291 */     return ((ExtendEntity)getEntity()).getGroupReward();
/*     */   }
/*     */   
/*     */   public void setGroupReward(Map<Integer, Integer> groupReward) {
/* 295 */     ((ExtendEntity)getEntity()).setGroupReward(groupReward);
/*     */   }
/*     */   
/*     */   public boolean isChoose() {
/* 299 */     return ((ExtendEntity)getEntity()).isChoose();
/*     */   }
/*     */   
/*     */   public void setChoose(boolean choose) {
/* 303 */     ((ExtendEntity)getEntity()).setChoose(choose);
/*     */   }
/*     */   
/*     */   public int getSkipTimes() {
/* 307 */     return ((ExtendEntity)getEntity()).getSkipTimes();
/*     */   }
/*     */   
/*     */   public void setSkipTimes(int skipTimes) {
/* 311 */     ((ExtendEntity)getEntity()).setSkipTimes(skipTimes);
/*     */   }
/*     */   
/*     */   public long getTodayFirstCharge() {
/* 315 */     return ((ExtendEntity)getEntity()).getTodayFirstCharge();
/*     */   }
/*     */   
/*     */   public void setTodayFirstCharge(long todayFirstCharge) {
/* 319 */     ((ExtendEntity)getEntity()).setTodayFirstCharge(todayFirstCharge);
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getTodayFirstReward() {
/* 323 */     return ((ExtendEntity)getEntity()).getTodayFirstReward();
/*     */   }
/*     */   
/*     */   public void setTodayFirstReward(Map<Integer, Integer> todayFirstReward) {
/* 327 */     ((ExtendEntity)getEntity()).setTodayFirstReward(todayFirstReward);
/*     */   }
/*     */   
/*     */   public long getDailyConsume() {
/* 331 */     return ((ExtendEntity)getEntity()).getDailyConsume();
/*     */   }
/*     */   
/*     */   public void setDailyConsume(long dailyConsume) {
/* 335 */     ((ExtendEntity)getEntity()).setDailyConsume(dailyConsume);
/*     */   }
/*     */   
/*     */   public int getBuyGoldTimes() {
/* 339 */     return ((ExtendEntity)getEntity()).getBuyGoldTimes();
/*     */   }
/*     */   
/*     */   public void setBuyGoldTimes(int buyGoldTimes) {
/* 343 */     ((ExtendEntity)getEntity()).setBuyGoldTimes(buyGoldTimes);
/*     */   }
/*     */   
/*     */   public int getBuyGoldCost() {
/* 347 */     return ((ExtendEntity)getEntity()).getBuyGoldCost();
/*     */   }
/*     */   
/*     */   public void setBuyGoldCost(int buyGoldCost) {
/* 351 */     ((ExtendEntity)getEntity()).setBuyGoldCost(buyGoldCost);
/*     */   }
/*     */   
/*     */   public String getClientSets() {
/* 355 */     return ((ExtendEntity)getEntity()).getClientSets();
/*     */   }
/*     */   
/*     */   public void setClientSets(String value) {
/* 359 */     ((ExtendEntity)getEntity()).setClientSets(value);
/*     */   }
/*     */   
/*     */   public Set<Integer> getQuestionnaire() {
/* 363 */     return ((ExtendEntity)getEntity()).getQuestionnaire();
/*     */   }
/*     */   
/*     */   public void setQuestionnaire(Set<Integer> questionnaire) {
/* 367 */     ((ExtendEntity)getEntity()).setQuestionnaire(questionnaire);
/*     */   }
/*     */   
/*     */   public int getShortCut() {
/* 371 */     return ((ExtendEntity)getEntity()).getShortCut();
/*     */   }
/*     */   
/*     */   public void setShortCut(int shortCut) {
/* 375 */     ((ExtendEntity)getEntity()).setShortCut(shortCut);
/*     */   }
/*     */   
/*     */   public void setWanderReward(Map<Integer, Integer> wanderReward) {
/* 379 */     ((ExtendEntity)getEntity()).setWanderReward(wanderReward);
/*     */   }
/*     */   public Map<Integer, Integer> getWanderReward() {
/* 382 */     return ((ExtendEntity)getEntity()).getWanderReward();
/*     */   }
/*     */   public long getWanderEndtime() {
/* 385 */     return ((ExtendEntity)getEntity()).getWanderEndtime();
/*     */   }
/*     */   public void setWanderEndtime(long wanderEndtime) {
/* 388 */     ((ExtendEntity)getEntity()).setWanderEndtime(wanderEndtime);
/*     */   }
/*     */   
/*     */   public void setLastWanderReward(Set<Integer> lastWanderReward) {
/* 392 */     ((ExtendEntity)getEntity()).setLastWanderReward(lastWanderReward);
/*     */   }
/*     */   public Set<Integer> getLastWanderReward() {
/* 395 */     return ((ExtendEntity)getEntity()).getLastWanderReward();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getWeekResetTime() {
/* 400 */     return ((ExtendEntity)getEntity()).getWeekResetTime();
/*     */   }
/*     */   
/*     */   public void setWeekResetTime(int weekResetTime) {
/* 404 */     ((ExtendEntity)getEntity()).setWeekResetTime(weekResetTime);
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\extend\ExtendComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */