/*     */ package com.linlongyx.sanguo.webgame.app.mental;
/*     */ 
/*     */ import com.linlongyx.core.framework.logic.IPlayer;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.common.util.RandUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.RewardBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ZhenfaTempleBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ZhenfaTempleSeekBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ZhenfaTempleWorldBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.MentalParamter;
/*     */ import com.linlongyx.sanguo.webgame.constant.CurrencyType;
/*     */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*     */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.welfare.WelfareUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.mental.RaffleLotteryResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.MentalShowStruct;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import com.linlongyx.sanguo.webgame.service.MentalRankService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.TreeSet;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MentalUtil
/*     */ {
/*     */   public static final int MENTAL_RECORD_ITEM_TIMES = 10;
/*     */   public static final int MENTAL_SHOW_ITEM_TIMES = 10;
/*     */   public static final int MENTAL_TYPE_ONE = 0;
/*     */   public static final int MENTAL_TYPE_TEN = 1;
/*     */   private static final short MENTAL_RET_FREE = 1;
/*     */   private static final short MENTAL_RET_HASITEM = 2;
/*     */   private static final short MENTAL_RET_HASCCY = 3;
/*     */   private static final short MENTAL_RET_CCY_LESS = 10000;
/*     */   private static final int MENTAL_SULY_TIMES = 10;
/*  46 */   private static MentalParamter mentalParamter = (MentalParamter)ParameterConstant.getParameter(58);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static short raffleLottery(IPlayer player, int times, RaffleLotteryResponse response) {
/*  56 */     MentalComponent mentalComponent = (MentalComponent)player.getComponent(MentalComponent.class);
/*  57 */     if (null == mentalComponent) {
/*  58 */       return 17301;
/*     */     }
/*     */     
/*  61 */     BagComponent bagComponent = (BagComponent)player.getComponent(BagComponent.class);
/*  62 */     ArrayList<Reward> rewardList = new ArrayList<>();
/*  63 */     MentalRankService rankService = (MentalRankService)MContext.getBean("mentalRankService");
/*  64 */     boolean isShow = false;
/*  65 */     short ret = 0;
/*  66 */     if (times == 1) {
/*  67 */       ret = checkOneRaffle(player, mentalComponent);
/*     */     } else {
/*  69 */       ret = checkTenRaffle(player);
/*     */     } 
/*  71 */     if (ret == 10000) {
/*  72 */       return 17306;
/*     */     }
/*  74 */     int libId = getLibIds();
/*  75 */     ZhenfaTempleSeekBean zhenfaTempleSeekBean = (ZhenfaTempleSeekBean)JsonTableService.getJsonData(libId, ZhenfaTempleSeekBean.class);
/*  76 */     if (null == zhenfaTempleSeekBean) {
/*  77 */       return 10006;
/*     */     }
/*     */     
/*  80 */     if (ret == 1) {
/*  81 */       mentalComponent.setFreeTime(TimeUtil.currentTime() + mentalParamter.getFreeTime());
/*  82 */     } else if (ret == 2) {
/*  83 */       bagComponent.deleteItem(mentalParamter.getLotteryItemId(), times, ResourceEvent.MentalLottery, true);
/*  84 */     } else if (ret == 3) {
/*  85 */       if (times == 1) {
/*  86 */         FinanceUtil.decCurrency(player, CurrencyType.CCY, mentalParamter.getLotteryOneCost(), ResourceEvent.MentalLottery, true);
/*     */       } else {
/*  88 */         FinanceUtil.decCurrency(player, CurrencyType.CCY, mentalParamter.getLotteryTenCost(), ResourceEvent.MentalLottery, true);
/*     */       } 
/*     */     } 
/*  91 */     for (int i = 0; i < times; i++) {
/*     */       
/*  93 */       int totalWeight = 0;
/*  94 */       ArrayList<Integer> rewards = new ArrayList<>(zhenfaTempleSeekBean.getRewardId().keySet());
/*  95 */       for (ZhenfaTempleSeekBean.RewardIdBean rewardIdBean : zhenfaTempleSeekBean.getRewardId().values()) {
/*  96 */         totalWeight += rewardIdBean.getProbability();
/*     */       }
/*  98 */       int rand = RandUtil.randNum(totalWeight);
/*  99 */       Collections.shuffle(rewards);
/* 100 */       int weight = 0;
/* 101 */       ZhenfaTempleSeekBean.RewardIdBean rewardIdBean2 = null;
/* 102 */       for (Iterator<Integer> iterator = rewards.iterator(); iterator.hasNext(); ) { int rewardid = ((Integer)iterator.next()).intValue();
/* 103 */         ZhenfaTempleSeekBean.RewardIdBean rewardIdBean = (ZhenfaTempleSeekBean.RewardIdBean)zhenfaTempleSeekBean.getRewardId().get(Integer.valueOf(rewardid));
/* 104 */         weight += rewardIdBean.getProbability();
/* 105 */         if (weight >= rand) {
/* 106 */           rewardIdBean2 = rewardIdBean;
/*     */           break;
/*     */         }  }
/*     */       
/* 110 */       rewardList.addAll(FinanceUtil.transform(rewardIdBean2.getReward()));
/* 111 */       mentalComponent.setTimes(mentalComponent.getTimes() + 1);
/* 112 */       mentalComponent.setTotalTimes(mentalComponent.getTotalTimes() + 1);
/* 113 */       if (rewardIdBean2.getNotice() != 0) {
/* 114 */         rankService.addShowRecord(player.getPlayerName(), FinanceUtil.transform(rewardIdBean2.getReward()));
/* 115 */         isShow = true;
/* 116 */         if (rewardIdBean2.getNotice() == 2) {
/* 117 */           PlayerUtil.sendNotice(12, player, ((RewardBean)rewardIdBean2.getReward().get(0)).getId(), ((RewardBean)rewardIdBean2.getReward().get(0)).getNum() + "");
/*     */         }
/*     */       } 
/*     */     } 
/* 121 */     int addPoint = 0;
/* 122 */     TaskComponent taskComponent = (TaskComponent)player.getComponent(TaskComponent.class);
/* 123 */     if (null != taskComponent) {
/* 124 */       taskComponent.refreshSchedule(TaskType.ZhenFaDian, 0, times);
/*     */     }
/* 126 */     if (times == 1) {
/* 127 */       addPoint = mentalParamter.getOneAddPoint();
/*     */     } else {
/* 129 */       addPoint = mentalParamter.getTenAddPoint();
/*     */     } 
/*     */     
/* 132 */     FinanceUtil.reward(FinanceUtil.transformReward(mentalParamter.getRewards(), times), player, ResourceEvent.ZhenfaDian, true);
/* 133 */     FinanceUtil.reward(rewardList, player, ResourceEvent.ZhenfaDian, true);
/* 134 */     mentalComponent.setPoint(addPoint + mentalComponent.getPoint());
/* 135 */     response.rewards = rewardList;
/* 136 */     mentalComponent.incMyReward(rewardList, addPoint);
/* 137 */     response.point = mentalComponent.getPoint();
/* 138 */     response.nextFreeTime = mentalComponent.getFreeTime();
/* 139 */     rankService.updatePlayerPoint(player.getPlayerId(), player.getPlayerName(), mentalComponent.getPoint());
/* 140 */     if (isShow) {
/* 141 */       rankService.save();
/* 142 */       ArrayList<MentalShowStruct> list = new ArrayList<>();
/* 143 */       rankService.getShowRecord(list);
/* 144 */       LookUpService.pushMentalNotice(list);
/*     */     } 
/*     */     
/* 147 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static short checkOneRaffle(IPlayer player, MentalComponent mentalComponent) {
/* 156 */     int freeTime = mentalComponent.getFreeTime();
/* 157 */     BagComponent bagComponent = (BagComponent)player.getComponent(BagComponent.class);
/*     */ 
/*     */     
/* 160 */     if (freeTime <= TimeUtil.currentTime()) {
/* 161 */       return 1;
/*     */     }
/*     */     
/* 164 */     if (bagComponent.check(mentalParamter.getLotteryItemId(), 1L)) {
/* 165 */       return 2;
/*     */     }
/* 167 */     if (!FinanceUtil.checkCurrency(player, CurrencyType.CCY, mentalParamter.getLotteryOneCost())) {
/* 168 */       return 10000;
/*     */     }
/*     */     
/* 171 */     return 3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static short checkTenRaffle(IPlayer player) {
/* 181 */     BagComponent bagComponent = (BagComponent)player.getComponent(BagComponent.class);
/* 182 */     if (bagComponent.check(mentalParamter.getLotteryItemId(), 10L)) {
/* 183 */       return 2;
/*     */     }
/* 185 */     if (FinanceUtil.checkCurrency(player, CurrencyType.CCY, mentalParamter.getLotteryTenCost())) {
/* 186 */       return 3;
/*     */     }
/* 188 */     return 10000;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getShowLevel() {
/* 196 */     TreeSet<Integer> set = JsonTableService.getJsonTableKey(ZhenfaTempleWorldBean.class);
/* 197 */     int worldLevel = WelfareUtil.weekWorldLevel.get();
/* 198 */     int target = ((Integer)set.first()).intValue();
/* 199 */     for (Iterator<Integer> iterator = set.iterator(); iterator.hasNext(); ) { int level = ((Integer)iterator.next()).intValue();
/* 200 */       if (level >= worldLevel) {
/* 201 */         target = level;
/*     */         break;
/*     */       }  }
/*     */     
/* 205 */     return target;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getLibIds() {
/* 212 */     TreeSet<Integer> set = JsonTableService.getJsonTableKey(ZhenfaTempleBean.class);
/* 213 */     int day = TimeUtil.getDayDiffToOpen(MContext.getOpenTime());
/* 214 */     int target = ((Integer)set.first()).intValue();
/* 215 */     for (Iterator<Integer> iterator = set.iterator(); iterator.hasNext(); ) { int openday = ((Integer)iterator.next()).intValue();
/* 216 */       if (openday >= day) {
/* 217 */         target = openday;
/*     */         break;
/*     */       }  }
/*     */     
/* 221 */     ZhenfaTempleBean zhenfaTempleBean = (ZhenfaTempleBean)JsonTableService.getJsonData(target, ZhenfaTempleBean.class);
/* 222 */     return zhenfaTempleBean.getBaseId();
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\mental\MentalUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */