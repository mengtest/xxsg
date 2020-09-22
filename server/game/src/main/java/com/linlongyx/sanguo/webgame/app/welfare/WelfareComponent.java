/*     */ package com.linlongyx.sanguo.webgame.app.welfare;
/*     */ 
/*     */ import com.linlongyx.core.framework.logic.AbstractComponent;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.OneyuanBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.OneyuanRewardBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.VipGiftBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.VipWeekBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.DailyHaoLiParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.FundParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.WelfareParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.LanguageConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.processors.charge.ChargeUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.mail.MailUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.welfare.MonthCardUtil;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class WelfareComponent
/*     */   extends AbstractComponent<WelfareEntity> {
/*  30 */   private double monthCardRate = 0.0D;
/*     */   
/*     */   public WelfareComponent(long playerId) {
/*  33 */     super(WelfareEntity.class, playerId);
/*  34 */     getProxy().createProxy(new Object[] { Long.valueOf(playerId) });
/*     */   }
/*     */ 
/*     */   
/*     */   public String getType() {
/*  39 */     return getClass().getSimpleName();
/*     */   }
/*     */ 
/*     */   
/*     */   public void build(long playerId) {
/*  44 */     setPlayerId(playerId);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean reset(int time) {
/*  49 */     if (time == 0) {
/*  50 */       resetVipDaily();
/*  51 */       resetVipWeek();
/*  52 */       updateCardRate();
/*  53 */       setDailyBuyReward(new HashMap<>());
/*  54 */       resetOneState();
/*  55 */       resetDailyHaoLi();
/*     */     } 
/*  57 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void init() {
/*  62 */     super.init();
/*  63 */     updateCardRate();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void resetVipDaily() {
/*  70 */     PlayerComponent playerComponent = LookUpService.getPlayerComponent(getPlayerId());
/*  71 */     if (null == playerComponent) {
/*     */       return;
/*     */     }
/*  74 */     int vip = playerComponent.getVip();
/*  75 */     Set<Integer> vipDailySet = getVipDailySet();
/*  76 */     if (vipDailySet.contains(Integer.valueOf(vip))) {
/*  77 */       vipDailySet.remove(Integer.valueOf(vip));
/*  78 */       setVipDailySet(vipDailySet);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void resetVipWeek() {
/*  86 */     checkVipWeekLevel();
/*  87 */     PlayerComponent playerComponent = LookUpService.getPlayerComponent(getPlayerId());
/*  88 */     if (null == playerComponent) {
/*     */       return;
/*     */     }
/*  91 */     WelfareParameter welfareParameter = (WelfareParameter)ParameterConstant.getParameter(19);
/*  92 */     Map<Integer, Integer> vipWeekGoods = getVipWeekGoods();
/*  93 */     int level = welfareParameter.getVipWeekLevel(playerComponent.getLevel());
/*  94 */     List<Integer> list = welfareParameter.getVipWeekList(level);
/*  95 */     Map<Integer, Integer> vipGiftGoods = getVipGiftGoods();
/*     */     
/*  97 */     if (isNextWeek()) {
/*  98 */       Map<Integer, Object> map = JsonTableService.getJsonTable(VipGiftBean.class);
/*  99 */       for (Map.Entry<Integer, Object> entry : map.entrySet()) {
/* 100 */         VipGiftBean vipGiftBean = (VipGiftBean)entry.getValue();
/* 101 */         if (vipGiftBean.getLimitType() != 3) {
/* 102 */           vipGiftGoods.put(Integer.valueOf(vipGiftBean.getPackageType()), Integer.valueOf(0));
/*     */         }
/*     */       } 
/* 105 */       setVipWeekLevel(level);
/* 106 */       vipWeekGoods.clear();
/* 107 */       for (Integer id : list) {
/* 108 */         vipWeekGoods.put(id, Integer.valueOf(0));
/*     */       }
/* 110 */       PlayerUtil.resetEveryWeek(this.playerId);
/* 111 */       setVipWeekTime(TimeUtil.currentTime() + 1);
/*     */     } else {
/* 113 */       Set<Integer> resetSet = new HashSet<>();
/* 114 */       for (Integer id : list) {
/* 115 */         if (!vipWeekGoods.containsKey(id)) {
/* 116 */           vipWeekGoods.put(id, Integer.valueOf(0));
/*     */         }
/*     */       } 
/* 119 */       for (Map.Entry<Integer, Integer> entry : vipWeekGoods.entrySet()) {
/* 120 */         VipWeekBean vipWeekBean = (VipWeekBean)JsonTableService.getJsonData(((Integer)entry.getKey()).intValue(), VipWeekBean.class);
/* 121 */         if (null == vipWeekBean) {
/*     */           continue;
/*     */         }
/* 124 */         if (vipWeekBean.getLimitType() == 0 && ((Integer)entry.getValue()).intValue() > 0) {
/* 125 */           resetSet.add(entry.getKey());
/*     */         }
/*     */       } 
/* 128 */       if (!resetSet.isEmpty()) {
/* 129 */         for (Integer id : resetSet) {
/* 130 */           vipWeekGoods.put(id, Integer.valueOf(0));
/*     */         }
/* 132 */         setVipWeekGoods(vipWeekGoods);
/*     */       } 
/*     */ 
/*     */       
/* 136 */       Map<Integer, Object> map = JsonTableService.getJsonTable(VipGiftBean.class);
/* 137 */       for (Map.Entry<Integer, Object> entry : map.entrySet()) {
/* 138 */         VipGiftBean vipGiftBean = (VipGiftBean)entry.getValue();
/* 139 */         if (vipGiftBean.getLimitType() == 1) {
/* 140 */           vipGiftGoods.put(Integer.valueOf(vipGiftBean.getPackageType()), Integer.valueOf(0));
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
/*     */   private boolean isNextWeek() {
/* 152 */     int week = TimeUtil.getWeek();
/* 153 */     int time = TimeUtil.timingTime(0) - (week - 1) * 86400;
/* 154 */     return (getVipWeekTime() < time);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void checkVipWeekLevel() {
/* 162 */     PlayerComponent playerComponent = LookUpService.getPlayerComponent(getPlayerId());
/* 163 */     if (null == playerComponent) {
/*     */       return;
/*     */     }
/* 166 */     WelfareParameter welfareParameter = (WelfareParameter)ParameterConstant.getParameter(19);
/* 167 */     Map<Integer, Integer> vipWeekGoods = getVipWeekGoods();
/* 168 */     int level = welfareParameter.getVipWeekLevel(playerComponent.getLevel());
/* 169 */     List<Integer> list = welfareParameter.getVipWeekList(level);
/* 170 */     if (null == list) {
/*     */       return;
/*     */     }
/* 173 */     if (getVipWeekLevel() != level) {
/* 174 */       setVipWeekLevel(level);
/* 175 */       vipWeekGoods.clear();
/* 176 */       for (Integer id : list) {
/* 177 */         vipWeekGoods.put(id, Integer.valueOf(0));
/*     */       }
/* 179 */       setVipWeekGoods(vipWeekGoods);
/* 180 */       setVipWeekTime(TimeUtil.currentTime());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateCardRate() {
/* 188 */     Map<Integer, Integer> monthEndTime = getMonthEndTime();
/* 189 */     int normalEndTime = ((Integer)monthEndTime.getOrDefault(Integer.valueOf(1), Integer.valueOf(0))).intValue();
/* 190 */     int specialEndTime = ((Integer)monthEndTime.getOrDefault(Integer.valueOf(2), Integer.valueOf(0))).intValue();
/* 191 */     this.monthCardRate = MonthCardUtil.getRate(normalEndTime, specialEndTime);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetOneState() {
/* 198 */     if (getOneState() != 0) {
/* 199 */       if (getOneState() == 1) {
/* 200 */         if (getId() == 0) {
/* 201 */           int now = TimeUtil.currentTime();
/* 202 */           FundParameter fundParameter = (FundParameter)ParameterConstant.getParameter(25);
/* 203 */           int id = ChargeUtil.getOneyuan(now - 86400, fundParameter);
/* 204 */           setId(id);
/*     */         } 
/*     */         
/* 207 */         OneyuanBean oneyuanBean = (OneyuanBean)JsonTableService.getJsonData(getId(), OneyuanBean.class);
/* 208 */         if (null != oneyuanBean) {
/* 209 */           int rewardId = ((Integer)oneyuanBean.getRewardId().get(getTimes())).intValue();
/* 210 */           OneyuanRewardBean oneyuanRewardBean = (OneyuanRewardBean)JsonTableService.getJsonData(rewardId, OneyuanRewardBean.class);
/* 211 */           if (null != oneyuanRewardBean) {
/* 212 */             String title = LanguageConstant.getAndReplaceLanguage(4101, new String[] { oneyuanRewardBean.getName() });
/* 213 */             String content = LanguageConstant.getAndReplaceLanguage(4102, new String[] { oneyuanRewardBean.getName() });
/* 214 */             MailUtil.sendSysRewardBeanMail(getPlayerId(), oneyuanRewardBean.getReward(), title, content);
/*     */           } 
/*     */         } 
/*     */       } 
/* 218 */       setOneState(0);
/*     */     } 
/* 220 */     setId(0);
/* 221 */     if (getTimes() > 0) {
/* 222 */       setTimes(0);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void resetDailyHaoLi() {
/* 230 */     DailyHaoLiParameter dailyHaoLiParameter = (DailyHaoLiParameter)ParameterConstant.getParameter(56);
/* 231 */     List<Integer> list = dailyHaoLiParameter.getActId(true);
/* 232 */     for (Iterator<Integer> iterator1 = list.iterator(); iterator1.hasNext(); ) { int actId = ((Integer)iterator1.next()).intValue();
/* 233 */       getDailyHaoLi().put(Integer.valueOf(actId), Integer.valueOf(0));
/* 234 */       setDailyHaoLi(getDailyHaoLi()); }
/*     */     
/* 236 */     List<Integer> list2 = dailyHaoLiParameter.getActId(false);
/* 237 */     for (Iterator<Integer> iterator2 = list2.iterator(); iterator2.hasNext(); ) { int actId = ((Integer)iterator2.next()).intValue();
/* 238 */       getDailyHaoLi().put(Integer.valueOf(actId), Integer.valueOf(0));
/* 239 */       setDailyHaoLi(getDailyHaoLi()); }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getMonthCardRate() {
/* 249 */     return this.monthCardRate;
/*     */   }
/*     */   
/*     */   public Set<Integer> getVipDailySet() {
/* 253 */     return ((WelfareEntity)getEntity()).getVipDailySet();
/*     */   }
/*     */   
/*     */   public void setVipDailySet(Set<Integer> vipDailySet) {
/* 257 */     ((WelfareEntity)getEntity()).setVipDailySet(vipDailySet);
/*     */   }
/*     */   
/*     */   public int getVipWeekTime() {
/* 261 */     return ((WelfareEntity)getEntity()).getVipWeekTime();
/*     */   }
/*     */   
/*     */   public void setVipWeekTime(int vipWeekTime) {
/* 265 */     ((WelfareEntity)getEntity()).setVipWeekTime(vipWeekTime);
/*     */   }
/*     */   
/*     */   public int getVipWeekLevel() {
/* 269 */     return ((WelfareEntity)getEntity()).getVipWeekLevel();
/*     */   }
/*     */   
/*     */   public void setVipWeekLevel(int vipWeekLevel) {
/* 273 */     ((WelfareEntity)getEntity()).setVipWeekLevel(vipWeekLevel);
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getVipWeekGoods() {
/* 277 */     return ((WelfareEntity)getEntity()).getVipWeekGoods();
/*     */   }
/*     */   
/*     */   public void setVipWeekGoods(Map<Integer, Integer> vipWeekGoods) {
/* 281 */     ((WelfareEntity)getEntity()).setVipWeekGoods(vipWeekGoods);
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getVipGiftGoods() {
/* 285 */     return ((WelfareEntity)getEntity()).getVipGiftGoods();
/*     */   }
/*     */   
/*     */   public void setVipGiftGoods(Map<Integer, Integer> vipGiftGoods) {
/* 289 */     ((WelfareEntity)getEntity()).setVipGiftGoods(vipGiftGoods);
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getMonthEndTime() {
/* 293 */     return ((WelfareEntity)getEntity()).getMonthEndTime();
/*     */   }
/*     */   
/*     */   public void setMonthEndTime(Map<Integer, Integer> monthEndTime) {
/* 297 */     ((WelfareEntity)getEntity()).setMonthEndTime(monthEndTime);
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getMonthGetTime() {
/* 301 */     return ((WelfareEntity)getEntity()).getMonthGetTime();
/*     */   }
/*     */   
/*     */   public void setMonthGetTime(Map<Integer, Integer> monthGetTime) {
/* 305 */     ((WelfareEntity)getEntity()).setMonthGetTime(monthGetTime);
/*     */   }
/*     */   
/*     */   public boolean isBuyFund() {
/* 309 */     return ((WelfareEntity)getEntity()).isBuyFund();
/*     */   }
/*     */   
/*     */   public void setBuyFund(boolean buyFund) {
/* 313 */     ((WelfareEntity)getEntity()).setBuyFund(buyFund);
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getFundReward() {
/* 317 */     return ((WelfareEntity)getEntity()).getFundReward();
/*     */   }
/*     */   
/*     */   public void setFundReward(Map<Integer, Integer> fundReward) {
/* 321 */     ((WelfareEntity)getEntity()).setFundReward(fundReward);
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getDailyBuyReward() {
/* 325 */     return ((WelfareEntity)getEntity()).getDailyBuyReward();
/*     */   }
/*     */   
/*     */   public void setDailyBuyReward(Map<Integer, Integer> dailyBuyReward) {
/* 329 */     ((WelfareEntity)getEntity()).setDailyBuyReward(dailyBuyReward);
/*     */   }
/*     */   
/*     */   public long getOneBuyCharge() {
/* 333 */     return ((WelfareEntity)getEntity()).getOneBuyCharge();
/*     */   }
/*     */   
/*     */   public void setOneBuyCharge(long oneBuyCharge) {
/* 337 */     ((WelfareEntity)getEntity()).setOneBuyCharge(oneBuyCharge);
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getOneBuyReward() {
/* 341 */     return ((WelfareEntity)getEntity()).getOneBuyReward();
/*     */   }
/*     */   
/*     */   public void setOneBuyReward(Map<Integer, Integer> oneBuyReward) {
/* 345 */     ((WelfareEntity)getEntity()).setOneBuyReward(oneBuyReward);
/*     */   }
/*     */   
/*     */   public Set<Integer> getGerenalGift() {
/* 349 */     return ((WelfareEntity)getEntity()).getGerenalGift();
/*     */   }
/*     */   
/*     */   public void setGerenalGift(Set<Integer> gerenalGift) {
/* 353 */     ((WelfareEntity)getEntity()).setGerenalGift(gerenalGift);
/*     */   }
/*     */   
/*     */   public int getOneState() {
/* 357 */     return ((WelfareEntity)getEntity()).getOneState();
/*     */   }
/*     */   
/*     */   public void setOneState(int oneState) {
/* 361 */     ((WelfareEntity)getEntity()).setOneState(oneState);
/*     */   }
/*     */   
/*     */   public int getTimes() {
/* 365 */     return ((WelfareEntity)getEntity()).getTimes();
/*     */   }
/*     */   
/*     */   public void setTimes(int times) {
/* 369 */     ((WelfareEntity)getEntity()).setTimes(times);
/*     */   }
/*     */   
/*     */   public int getId() {
/* 373 */     return ((WelfareEntity)getEntity()).getId();
/*     */   }
/*     */   
/*     */   public void setId(int id) {
/* 377 */     ((WelfareEntity)getEntity()).setId(id);
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getDailyHaoLi() {
/* 381 */     return ((WelfareEntity)getEntity()).getDailyHaoLi();
/*     */   }
/*     */   
/*     */   public void setDailyHaoLi(Map<Integer, Integer> dailyHaoLi) {
/* 385 */     ((WelfareEntity)getEntity()).setDailyHaoLi(dailyHaoLi);
/*     */   }
/*     */   
/*     */   public int getBackGiftId() {
/* 389 */     return ((WelfareEntity)getEntity()).getBackGiftId();
/*     */   }
/*     */   
/*     */   public void setBackGiftId(int backGiftId) {
/* 393 */     ((WelfareEntity)getEntity()).setBackGiftId(backGiftId);
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\welfare\WelfareComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */