/*     */ package com.linlongyx.sanguo.webgame.processors.recruit;
/*     */ 
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.logic.IPlayer;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.recruit.RecruitComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.RecruitBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.RecruitLibBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.RecruitRedBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.RewardBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.RecruitParamter;
/*     */ import com.linlongyx.sanguo.webgame.constant.CrossRankActType;
/*     */ import com.linlongyx.sanguo.webgame.constant.CurrencyType;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.RankActType;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*     */ import com.linlongyx.sanguo.webgame.processors.crossRankAct.CrossRankActUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.rankAct.RankActUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.recruit.StartRecruitRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.recruit.StartRecruitResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.KeyValue;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.util.CostUtil;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class StartRecruitProcessor extends ProcessorBase<StartRecruitRequest, StartRecruitResponse> {
/*     */   private static final int RECRUIT_ONE_COST = 1;
/*     */   private static final int RECRUIT_TEN_COST = 10;
/*     */   private static final int RECRUIT_ONE_TIME = 1;
/*     */   private static final int RECRUIT_TEN_TIMES = 10;
/*     */   
/*     */   protected void initResponse() {
/*  41 */     this.response = (ResponseBase)new StartRecruitResponse();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, StartRecruitRequest request) {
/*  47 */     IPlayer player = playerSession.getPlayer();
/*  48 */     RecruitComponent recruitComponent = (RecruitComponent)player.createIfNotExist(RecruitComponent.class);
/*  49 */     PlayerComponent playerComponent = (PlayerComponent)player.createIfNotExist(PlayerComponent.class);
/*  50 */     BagComponent bagComponent = (BagComponent)player.createIfNotExist(BagComponent.class);
/*  51 */     if (null == recruitComponent) {
/*  52 */       return 14001;
/*     */     }
/*  54 */     int level = playerComponent.getLevel();
/*  55 */     int totalTimes = recruitComponent.getTotalRecruitTimes();
/*     */ 
/*     */     
/*  58 */     int type = request.type;
/*  59 */     int addTimes = request.times;
/*  60 */     if (addTimes == 0) {
/*  61 */       addTimes = 1;
/*     */     } else {
/*  63 */       addTimes = 10;
/*     */     } 
/*  65 */     RecruitParamter parameter = (RecruitParamter)ParameterConstant.getParameter(15);
/*     */     
/*  67 */     RecruitBean.TimesBean libTimesBean = RecruitUtil.getLibBean(level, totalTimes);
/*  68 */     if (null == libTimesBean) {
/*  69 */       return 14002;
/*     */     }
/*  71 */     ArrayList<Reward> rewardList = new ArrayList<>();
/*     */     
/*  73 */     if (type == 0) {
/*     */       ArrayList<Reward> rewards;
/*  75 */       int recruitTimes = recruitComponent.getRecruitTimes();
/*     */       
/*  77 */       if (request.times == 0) {
/*     */         
/*  79 */         boolean isfree = true;
/*  80 */         if (recruitComponent.getCurrFreeCount() <= 0) {
/*  81 */           isfree = false;
/*  82 */           if (!bagComponent.check(parameter.getRecruitItemId(), 1L)) {
/*  83 */             return 14003;
/*     */           }
/*     */         } 
/*     */         
/*  87 */         RecruitLibBean.IdBean idBean = RecruitUtil.recruitReward(libTimesBean, ++recruitTimes, type, (recruitTimes == 10));
/*  88 */         ArrayList<RewardBean> arrayList = idBean.getGoods();
/*  89 */         if (null == arrayList || arrayList.isEmpty()) {
/*  90 */           return 14005;
/*     */         }
/*  92 */         rewardList = FinanceUtil.rewardGet(FinanceUtil.transform(arrayList), player, ResourceEvent.Recruit, true);
/*     */         
/*  94 */         if (isfree) {
/*  95 */           recruitComponent.setCurrFreeCount(recruitComponent.getCurrFreeCount() - 1);
/*     */         } else {
/*  97 */           bagComponent.deleteItem(parameter.getRecruitItemId(), 1, ResourceEvent.Recruit, true);
/*     */         } 
/*  99 */         if (recruitTimes == 10) {
/* 100 */           recruitComponent.setRecruitTimes(0);
/*     */         } else {
/* 102 */           recruitComponent.setRecruitTimes(recruitTimes);
/*     */         } 
/* 104 */         recruitComponent.setTotalRecruitTimes(recruitComponent.getTotalRecruitTimes() + addTimes);
/*     */       } else {
/*     */         
/* 107 */         if (!bagComponent.check(parameter.getRecruitItemId(), 10L)) {
/* 108 */           return 14003;
/*     */         }
/* 110 */         rewardList = RecruitUtil.recruitReardList(player, recruitTimes, libTimesBean, type);
/*     */         
/* 112 */         bagComponent.deleteItem(parameter.getRecruitItemId(), 10, ResourceEvent.Recruit, true);
/*     */       } 
/*     */       
/* 115 */       ((StartRecruitResponse)this.response).rewards = rewardList;
/* 116 */       ((StartRecruitResponse)this.response).freeCount = recruitComponent.getCurrFreeCount();
/* 117 */       ((StartRecruitResponse)this.response).nextFreeTime = recruitComponent.getNextFreeTime();
/* 118 */       ((StartRecruitResponse)this.response).times = recruitComponent.getCcyRecruitTimes();
/*     */ 
/*     */       
/* 121 */       if (addTimes == 1) {
/* 122 */         rewards = FinanceUtil.transformReward(parameter.getYingOnceRecruit(), 1);
/*     */       } else {
/* 124 */         rewards = FinanceUtil.transformReward(parameter.getYingOnceRecruit(), 10);
/*     */       } 
/* 126 */       FinanceUtil.rewardGet(rewards, player, ResourceEvent.Recruit, true);
/* 127 */       TaskComponent taskComponent1 = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 128 */       if (taskComponent1 != null) {
/* 129 */         taskComponent1.refreshSchedule(TaskType.CoinRecruit, 0, addTimes);
/*     */       }
/* 131 */     } else if (request.type == 1) {
/*     */       
/* 133 */       int ccyTimes = recruitComponent.getCcyRecruitTimes();
/* 134 */       if (request.times == 0) {
/* 135 */         RecruitLibBean.IdBean idBean = RecruitUtil.recruitReward(libTimesBean, ++ccyTimes, type, (ccyTimes == 10));
/* 136 */         ArrayList<RewardBean> arrayList = idBean.getGoods();
/* 137 */         if (null == arrayList || arrayList.isEmpty()) {
/* 138 */           return 14005;
/*     */         }
/* 140 */         short ret = RecruitUtil.checkOneRaffle(playerSession, recruitComponent, parameter, bagComponent, false);
/* 141 */         if (ret == 1) {
/* 142 */           recruitComponent.setTime(TimeUtil.currentTime());
/* 143 */           recruitComponent.setNextFreeTime(TimeUtil.currentTime() + parameter.getFreeRecruitTime());
/*     */         } 
/* 145 */         if (ret == 2) {
/* 146 */           return 14004;
/*     */         }
/* 148 */         if (ret == 4) {
/* 149 */           bagComponent.deleteItem(parameter.getOneRecruitItem(), 1, ResourceEvent.Recruit, true);
/*     */         }
/* 151 */         if (ret == 3) {
/* 152 */           CostUtil.cost(parameter.getRecruitOnePrice(), playerSession, bagComponent, ResourceEvent.Recruit);
/*     */         }
/* 154 */         rewardList = FinanceUtil.rewardGet(FinanceUtil.transform(arrayList), player, ResourceEvent.Recruit, true);
/* 155 */         if (ccyTimes == 10) {
/* 156 */           recruitComponent.setCcyRecruitTimes(0);
/*     */         } else {
/* 158 */           recruitComponent.setCcyRecruitTimes(ccyTimes);
/*     */         } 
/* 160 */         recruitComponent.setTotalRecruitTimes(recruitComponent.getTotalRecruitTimes() + addTimes);
/* 161 */         RankActUtil.refreshRankValue(RankActType.Recruit.getType(), parameter.getRecruitPoint(), playerComponent.getPlayerId());
/* 162 */         RankActUtil.refreshRankValue(RankActType.Recruit2.getType(), parameter.getRecruitPoint(), playerComponent.getPlayerId());
/* 163 */         CrossRankActUtil.refreshRankValue(CrossRankActType.Recruit.getType(), parameter.getRecruitPoint(), playerComponent.getPlayerId());
/* 164 */         recruitComponent.setRebateScore(recruitComponent.getRebateScore() + parameter.getNormalRebateOne());
/*     */       } else {
/* 166 */         short ret = RecruitUtil.checkOneRaffle(playerSession, recruitComponent, parameter, bagComponent, true);
/* 167 */         if (ret == 1) {
/* 168 */           recruitComponent.setTenCCYRecruit(1);
/*     */         }
/* 170 */         if (ret == 4) {
/* 171 */           bagComponent.deleteItem(parameter.getTenRecruitItem(), 1, ResourceEvent.Recruit, true);
/*     */         }
/* 173 */         if (ret == 2) {
/* 174 */           return 10052;
/*     */         }
/* 176 */         if (ret == 3) {
/* 177 */           CostUtil.cost(parameter.getRecruitTenPrice(), playerSession, bagComponent, ResourceEvent.Recruit);
/*     */         }
/* 179 */         if (ret == 1) {
/* 180 */           if (!recruitComponent.getFirstGetList().isEmpty()) {
/* 181 */             for (KeyValue keyValue : recruitComponent.getFirstGetList()) {
/* 182 */               Reward reward = new Reward();
/* 183 */               reward.type = Byte.parseByte(keyValue.valueStr);
/* 184 */               reward.id = (int)keyValue.key;
/* 185 */               reward.num = keyValue.value;
/* 186 */               rewardList.add(reward);
/*     */             } 
/* 188 */             recruitComponent.setTotalRecruitTimes(recruitComponent.getTotalRecruitTimes() + 10);
/* 189 */             FinanceUtil.reward(rewardList, player, ResourceEvent.FirstRecruit, true);
/*     */           } else {
/* 191 */             rewardList = RecruitUtil.recruitReardList(player, ccyTimes, libTimesBean, type);
/*     */           } 
/*     */         } else {
/* 194 */           rewardList = RecruitUtil.recruitReardList(player, ccyTimes, libTimesBean, type);
/*     */         } 
/*     */         
/* 197 */         TaskComponent taskComponent2 = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 198 */         if (taskComponent2 != null) {
/* 199 */           taskComponent2.refreshSchedule(TaskType.CCYTenRecruit, 0, 10L);
/*     */         }
/* 201 */         RankActUtil.refreshRankValue(RankActType.Recruit.getType(), parameter.getNormalTenPoint(), playerComponent.getPlayerId());
/* 202 */         RankActUtil.refreshRankValue(RankActType.Recruit2.getType(), parameter.getNormalTenPoint(), playerComponent.getPlayerId());
/* 203 */         CrossRankActUtil.refreshRankValue(CrossRankActType.Recruit.getType(), parameter.getNormalTenPoint(), playerComponent.getPlayerId());
/* 204 */         recruitComponent.setRebateScore(recruitComponent.getRebateScore() + parameter.getNormalRebateTen());
/*     */       } 
/*     */       
/* 207 */       ((StartRecruitResponse)this.response).rewards = rewardList;
/* 208 */       ((StartRecruitResponse)this.response).freeCount = recruitComponent.getCurrFreeCount();
/* 209 */       ((StartRecruitResponse)this.response).nextFreeTime = recruitComponent.getNextFreeTime();
/* 210 */       ((StartRecruitResponse)this.response).times = recruitComponent.getCcyRecruitTimes();
/*     */       
/* 212 */       ArrayList<Reward> rewards = new ArrayList<>();
/* 213 */       if (addTimes == 1) {
/* 214 */         rewards = FinanceUtil.transformReward(parameter.getCcyOnceRecruit(), 1);
/*     */       } else {
/* 216 */         rewards = FinanceUtil.transformReward(parameter.getCcyOnceRecruit(), 10);
/*     */       } 
/* 218 */       FinanceUtil.rewardGet(rewards, player, ResourceEvent.Recruit, true);
/* 219 */       TaskComponent taskComponent1 = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 220 */       taskComponent1.refreshSchedule(TaskType.RecruitCCY, 0, addTimes);
/* 221 */     } else if (request.type == 2) {
/* 222 */       RecruitParamter recruitParamter = (RecruitParamter)ParameterConstant.getParameter(15);
/* 223 */       ArrayList<Integer> arrayList = recruitParamter.getActId(true, true, 1);
/* 224 */       if (arrayList.isEmpty()) {
/* 225 */         return 14023;
/*     */       }
/* 227 */       if (recruitComponent.getToday().isEmpty() || recruitComponent.getTommorow().isEmpty()) {
/* 228 */         recruitComponent.refreshRedRecruit(1);
/*     */       }
/* 230 */       if (recruitComponent.getToday().isEmpty()) {
/* 231 */         return 14023;
/*     */       }
/*     */       
/* 234 */       if (request.times == 0) {
/* 235 */         if (recruitComponent.getFree() == 1 && 
/* 236 */           !FinanceUtil.checkCurrency(player, CurrencyType.RecruitToken, parameter.getRedOneCost())) {
/* 237 */           return 14019;
/*     */         }
/*     */         
/* 240 */         ArrayList<RecruitRedBean.IdBean> idBeans = RecruitUtil.recruitRedReward(player, recruitComponent, request.times, 1);
/* 241 */         if (idBeans.isEmpty()) {
/* 242 */           return 15001;
/*     */         }
/* 244 */         if (recruitComponent.getFree() == 1) {
/* 245 */           FinanceUtil.decCurrency(player, CurrencyType.RecruitToken, parameter.getRedOneCost(), ResourceEvent.RecruitRed, true);
/*     */         }
/* 247 */         for (RecruitRedBean.IdBean idBean : idBeans) {
/* 248 */           recruitComponent.setRecruitTimes(recruitComponent.getRecruitTimes() + 1);
/* 249 */           if (recruitComponent.getRecruitTimes() == 10) {
/* 250 */             rewardList.addAll(FinanceUtil.transform(RecruitUtil.recruitRedTen(((Integer)recruitComponent.getToday().get(0)).intValue())));
/* 251 */             recruitComponent.setRecruitTimes(0); continue;
/*     */           } 
/* 253 */           rewardList.addAll(FinanceUtil.transform(idBean.getGoods()));
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 258 */         FinanceUtil.reward(rewardList, player, ResourceEvent.RecruitRed, false);
/* 259 */         recruitComponent.setFree(1);
/* 260 */         recruitComponent.setScore(recruitComponent.getScore() + parameter.getAddScore() * addTimes);
/* 261 */         for (RecruitRedBean.IdBean bean : idBeans) {
/* 262 */           if (bean.getIsShow() == 1);
/*     */         } 
/*     */ 
/*     */         
/* 266 */         RankActUtil.refreshRankValue(RankActType.Recruit.getType(), parameter.getRecruitRedPoint(), playerComponent.getPlayerId());
/* 267 */         RankActUtil.refreshRankValue(RankActType.Recruit2.getType(), parameter.getRecruitRedPoint(), playerComponent.getPlayerId());
/* 268 */         CrossRankActUtil.refreshRankValue(CrossRankActType.Recruit.getType(), parameter.getRecruitRedPoint(), playerComponent.getPlayerId());
/* 269 */         recruitComponent.setRebateScore(recruitComponent.getRebateScore() + parameter.getRedRebateOne());
/*     */       } else {
/* 271 */         if (!FinanceUtil.checkCurrency(player, CurrencyType.RecruitToken, parameter.getRedTenCost())) {
/* 272 */           return 14019;
/*     */         }
/* 274 */         ArrayList<RecruitRedBean.IdBean> idBeans = RecruitUtil.recruitRedReward(player, recruitComponent, request.times, 1);
/* 275 */         if (idBeans.isEmpty() || idBeans.size() < 10) {
/* 276 */           return 15001;
/*     */         }
/* 278 */         for (RecruitRedBean.IdBean idBean : idBeans) {
/* 279 */           recruitComponent.setRecruitTimes(recruitComponent.getRecruitTimes() + 1);
/* 280 */           if (recruitComponent.getRecruitTimes() == 10) {
/* 281 */             rewardList.addAll(FinanceUtil.transform(RecruitUtil.recruitRedTen(((Integer)recruitComponent.getToday().get(0)).intValue())));
/* 282 */             recruitComponent.setRecruitTimes(0); continue;
/*     */           } 
/* 284 */           rewardList.addAll(FinanceUtil.transform(idBean.getGoods()));
/*     */         } 
/*     */ 
/*     */         
/* 288 */         FinanceUtil.reward(rewardList, player, ResourceEvent.RecruitRed, false);
/* 289 */         FinanceUtil.decCurrency(player, CurrencyType.RecruitToken, parameter.getRedTenCost(), ResourceEvent.Recruit, true);
/* 290 */         recruitComponent.setScore(recruitComponent.getScore() + parameter.getAddScore() * addTimes);
/* 291 */         for (RecruitRedBean.IdBean bean : idBeans) {
/* 292 */           if (bean.getIsShow() == 1);
/*     */         } 
/*     */ 
/*     */         
/* 296 */         RankActUtil.refreshRankValue(RankActType.Recruit.getType(), parameter.getRedTenPoint(), playerComponent.getPlayerId());
/* 297 */         RankActUtil.refreshRankValue(RankActType.Recruit2.getType(), parameter.getRedTenPoint(), playerComponent.getPlayerId());
/* 298 */         CrossRankActUtil.refreshRankValue(CrossRankActType.Recruit.getType(), parameter.getRedTenPoint(), playerComponent.getPlayerId());
/* 299 */         recruitComponent.setRebateScore(recruitComponent.getRebateScore() + parameter.getRedRebateten());
/*     */       } 
/* 301 */       ((StartRecruitResponse)this.response).rewards = rewardList;
/* 302 */       ((StartRecruitResponse)this.response).freeCount = recruitComponent.getCurrFreeCount();
/* 303 */       ((StartRecruitResponse)this.response).nextFreeTime = recruitComponent.getNextFreeTime();
/* 304 */       ((StartRecruitResponse)this.response).times = recruitComponent.getCcyRecruitTimes();
/* 305 */       ((StartRecruitResponse)this.response).score = recruitComponent.getScore();
/* 306 */       ((StartRecruitResponse)this.response).redFree = recruitComponent.getFree();
/* 307 */     } else if (type == 3) {
/* 308 */       RecruitParamter recruitParamter = (RecruitParamter)ParameterConstant.getParameter(15);
/* 309 */       ArrayList<Integer> arrayList = recruitParamter.getActId(true, true, 2);
/* 310 */       if (arrayList.isEmpty()) {
/* 311 */         return 14023;
/*     */       }
/* 313 */       if (recruitComponent.getGoldToday().isEmpty() || recruitComponent.getGoldTommorow().isEmpty()) {
/* 314 */         recruitComponent.refreshRedRecruit(2);
/*     */       }
/* 316 */       if (recruitComponent.getGoldToday().isEmpty()) {
/* 317 */         return 14023;
/*     */       }
/*     */       
/* 320 */       if (request.times == 0) {
/* 321 */         if (recruitComponent.getGoldFree() == 1 && 
/* 322 */           !FinanceUtil.checkCurrency(player, CurrencyType.RecruitToken, parameter.getGoldOneCost())) {
/* 323 */           return 14019;
/*     */         }
/*     */         
/* 326 */         ArrayList<RecruitRedBean.IdBean> idBeans = RecruitUtil.recruitRedReward(player, recruitComponent, request.times, 2);
/* 327 */         if (idBeans.isEmpty()) {
/* 328 */           return 15001;
/*     */         }
/* 330 */         if (recruitComponent.getGoldFree() == 1) {
/* 331 */           FinanceUtil.decCurrency(player, CurrencyType.RecruitToken, parameter.getGoldOneCost(), ResourceEvent.RecruitRed, true);
/*     */         }
/* 333 */         for (RecruitRedBean.IdBean idBean : idBeans) {
/* 334 */           recruitComponent.setGoldRecruitTimes(recruitComponent.getGoldRecruitTimes() + 1);
/* 335 */           if (recruitComponent.getGoldRecruitTimes() == 10) {
/* 336 */             rewardList.addAll(FinanceUtil.transform(RecruitUtil.recruitRedTen(((Integer)recruitComponent.getGoldToday().get(0)).intValue())));
/* 337 */             recruitComponent.setGoldRecruitTimes(0); continue;
/*     */           } 
/* 339 */           rewardList.addAll(FinanceUtil.transform(idBean.getGoods()));
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 344 */         FinanceUtil.reward(rewardList, player, ResourceEvent.RecruitGold, false);
/* 345 */         recruitComponent.setGoldFree(1);
/* 346 */         recruitComponent.setGoldScore(recruitComponent.getGoldScore() + parameter.getAddScore() * addTimes);
/* 347 */         for (RecruitRedBean.IdBean bean : idBeans) {
/* 348 */           if (bean.getIsShow() == 1);
/*     */         } 
/*     */ 
/*     */         
/* 352 */         RankActUtil.refreshRankValue(RankActType.Recruit.getType(), parameter.getRecruitRedPoint(), playerComponent.getPlayerId());
/* 353 */         RankActUtil.refreshRankValue(RankActType.Recruit2.getType(), parameter.getRecruitRedPoint(), playerComponent.getPlayerId());
/* 354 */         CrossRankActUtil.refreshRankValue(CrossRankActType.Recruit.getType(), parameter.getRecruitRedPoint(), playerComponent.getPlayerId());
/* 355 */         recruitComponent.setRebateScore(recruitComponent.getRebateScore() + parameter.getRedRebateOne());
/*     */       } else {
/* 357 */         if (!FinanceUtil.checkCurrency(player, CurrencyType.RecruitToken, parameter.getGoldTenCost())) {
/* 358 */           return 14019;
/*     */         }
/* 360 */         ArrayList<RecruitRedBean.IdBean> idBeans = RecruitUtil.recruitRedReward(player, recruitComponent, request.times, 2);
/* 361 */         if (idBeans.isEmpty() || idBeans.size() < 10) {
/* 362 */           return 15001;
/*     */         }
/* 364 */         for (RecruitRedBean.IdBean idBean : idBeans) {
/* 365 */           recruitComponent.setGoldRecruitTimes(recruitComponent.getGoldRecruitTimes() + 1);
/* 366 */           if (recruitComponent.getGoldRecruitTimes() == 10) {
/* 367 */             rewardList.addAll(FinanceUtil.transform(RecruitUtil.recruitRedTen(((Integer)recruitComponent.getGoldToday().get(0)).intValue())));
/* 368 */             recruitComponent.setGoldRecruitTimes(0); continue;
/*     */           } 
/* 370 */           rewardList.addAll(FinanceUtil.transform(idBean.getGoods()));
/*     */         } 
/*     */ 
/*     */         
/* 374 */         FinanceUtil.reward(rewardList, player, ResourceEvent.RecruitGold, false);
/* 375 */         FinanceUtil.decCurrency(player, CurrencyType.RecruitToken, parameter.getGoldTenCost(), ResourceEvent.RecruitGold, true);
/* 376 */         recruitComponent.setGoldScore(recruitComponent.getGoldScore() + parameter.getAddScore() * addTimes);
/* 377 */         for (RecruitRedBean.IdBean bean : idBeans) {
/* 378 */           if (bean.getIsShow() == 1);
/*     */         } 
/*     */ 
/*     */         
/* 382 */         RankActUtil.refreshRankValue(RankActType.Recruit.getType(), parameter.getRedTenPoint(), playerComponent.getPlayerId());
/* 383 */         RankActUtil.refreshRankValue(RankActType.Recruit2.getType(), parameter.getRedTenPoint(), playerComponent.getPlayerId());
/* 384 */         CrossRankActUtil.refreshRankValue(CrossRankActType.Recruit.getType(), parameter.getRedTenPoint(), playerComponent.getPlayerId());
/* 385 */         recruitComponent.setRebateScore(recruitComponent.getRebateScore() + parameter.getRedRebateten());
/*     */       } 
/* 387 */       ((StartRecruitResponse)this.response).rewards = rewardList;
/* 388 */       ((StartRecruitResponse)this.response).freeCount = recruitComponent.getCurrFreeCount();
/* 389 */       ((StartRecruitResponse)this.response).nextFreeTime = recruitComponent.getNextFreeTime();
/* 390 */       ((StartRecruitResponse)this.response).times = recruitComponent.getCcyRecruitTimes();
/* 391 */       ((StartRecruitResponse)this.response).score = recruitComponent.getGoldScore();
/* 392 */       ((StartRecruitResponse)this.response).redFree = recruitComponent.getGoldFree();
/*     */     } 
/* 394 */     ((StartRecruitResponse)this.response).type = request.type;
/* 395 */     ((StartRecruitResponse)this.response).tenCCYRecruit = recruitComponent.getTenCCYRecruit();
/* 396 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 397 */     taskComponent.refreshSchedule(TaskType.TotalRecruit, 0, addTimes);
/*     */     
/* 399 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\recruit\StartRecruitProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */