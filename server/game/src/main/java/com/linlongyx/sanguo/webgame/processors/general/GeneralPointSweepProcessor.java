/*     */ package com.linlongyx.sanguo.webgame.processors.general;
/*     */ 
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.core.utils.LogUtils;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.general.GeneralComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.GeneralInsBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.GeneralParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.MilitaryInsType;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*     */ import com.linlongyx.sanguo.webgame.processors.offices.MilitaryUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.general.GeneralPointSweepRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.general.GeneralPointSweepResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.PlotExpSweepData;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class GeneralPointSweepProcessor
/*     */   extends ProcessorBase<GeneralPointSweepRequest, GeneralPointSweepResponse> {
/*     */   protected void initResponse() {
/*  30 */     this.response = (ResponseBase)new GeneralPointSweepResponse();
/*     */   }
/*     */ 
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, GeneralPointSweepRequest request) {
/*  35 */     int chapter = request.chapter;
/*  36 */     GeneralParameter generalParameter = (GeneralParameter)ParameterConstant.getParameter(12);
/*  37 */     Map<Integer, Integer> pointIdMap = generalParameter.getPointIdMap(chapter);
/*  38 */     if (null == pointIdMap) {
/*  39 */       return 11203;
/*     */     }
/*  41 */     int point = request.point;
/*  42 */     int id = ((Integer)pointIdMap.getOrDefault(Integer.valueOf(point), Integer.valueOf(0))).intValue();
/*  43 */     GeneralInsBean generalInsBean = (GeneralInsBean)JsonTableService.getJsonData(id, GeneralInsBean.class);
/*  44 */     if (null == generalInsBean) {
/*  45 */       return 11203;
/*     */     }
/*  47 */     GeneralComponent generalComponent = (GeneralComponent)playerSession.getPlayer().createIfNotExist(GeneralComponent.class);
/*  48 */     Map<Integer, Integer> stars = generalComponent.getStars();
/*  49 */     if (((Integer)stars.getOrDefault(Integer.valueOf(id), (V)Integer.valueOf(0))).intValue() < 3) {
/*  50 */       return 11204;
/*     */     }
/*  52 */     int num = request.num;
/*  53 */     if (num <= 0) {
/*  54 */       return 10090;
/*     */     }
/*  56 */     Map<Integer, Integer> challenges = generalComponent.getChallenges();
/*     */     
/*  58 */     int leftOrder = GeneralUtil.getLeftOrder(generalComponent);
/*  59 */     int cost = num * generalInsBean.getCost();
/*  60 */     if (leftOrder < cost) {
/*  61 */       return 11206;
/*     */     }
/*     */     
/*  64 */     Map<Integer, Integer> resetTimes = generalComponent.getResetTimes();
/*  65 */     int challenge = ((Integer)challenges.getOrDefault(Integer.valueOf(id), Integer.valueOf(0))).intValue();
/*  66 */     int resetTime = ((Integer)resetTimes.getOrDefault(Integer.valueOf(id), Integer.valueOf(0))).intValue();
/*  67 */     int leftNum = generalInsBean.getDailyTimes() + resetTime * generalInsBean.getDailyTimes() - challenge;
/*  68 */     if (leftNum < num) {
/*  69 */       return 11205;
/*     */     }
/*     */ 
/*     */     
/*  73 */     challenge += num;
/*  74 */     challenges.put(Integer.valueOf(id), Integer.valueOf(challenge));
/*  75 */     generalComponent.setChallenges(challenges);
/*     */     
/*  77 */     int costOrder = generalComponent.getCostOrder();
/*  78 */     costOrder += cost;
/*  79 */     generalComponent.setCostOrder(costOrder);
/*     */     
/*  81 */     generalComponent.setTotalNum(generalComponent.getTotalNum() + num);
/*     */ 
/*     */     
/*  84 */     ArrayList<Reward> rewards = new ArrayList<>();
/*  85 */     for (int i = 1; i <= num; i++) {
/*  86 */       ArrayList<Reward> temp = new ArrayList<>();
/*  87 */       GeneralUtil.getReward(generalInsBean, temp);
/*  88 */       temp = FinanceUtil.rewardGet(temp);
/*     */       
/*  90 */       MilitaryUtil.getRewardResult(temp, MilitaryInsType.GeneralIns.getType(), playerSession.getPlayer().getPlayerId(), 0);
/*  91 */       PlotExpSweepData plotExpSweepData = new PlotExpSweepData();
/*  92 */       plotExpSweepData.insId = id;
/*  93 */       plotExpSweepData.list.addAll(temp);
/*  94 */       ((GeneralPointSweepResponse)this.response).rewards.add(plotExpSweepData);
/*     */       
/*  96 */       rewards.addAll(temp);
/*     */     } 
/*  98 */     rewards = FinanceUtil.handleRepeat(rewards);
/*     */     
/* 100 */     FinanceUtil.reward(rewards, playerSession.getPlayer(), ResourceEvent.GeneralPointSweep, true);
/*     */     
/* 102 */     if (generalComponent.getNextTime() == 0) {
/* 103 */       int refuseTime = MilitaryUtil.getTime(playerSession.getPlayer().getPlayerId(), MilitaryInsType.GeneralIns.getType(), 0);
/* 104 */       generalComponent.setNextTime(TimeUtil.currentTime() + generalParameter.getRestoreCd() - refuseTime);
/*     */     } 
/*     */     
/* 107 */     ((GeneralPointSweepResponse)this.response).chapter = chapter;
/* 108 */     ((GeneralPointSweepResponse)this.response).point = point;
/* 109 */     ((GeneralPointSweepResponse)this.response).num = num;
/* 110 */     ((GeneralPointSweepResponse)this.response).time = challenge - resetTime * generalInsBean.getDailyTimes();
/* 111 */     ((GeneralPointSweepResponse)this.response).orderNum = GeneralUtil.getLeftOrder(generalComponent);
/* 112 */     ((GeneralPointSweepResponse)this.response).orderTime = generalComponent.getNextTime();
/*     */     
/* 114 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 115 */     taskComponent.refreshSchedule(TaskType.GeneralChallenge, 0, num);
/* 116 */     taskComponent.refreshSchedule(TaskType.OnGeneralChallege, 0, num);
/* 117 */     LogUtils.errorLog(new Object[] { "GeneralPointSweep", Long.valueOf(playerSession.getPlayer().getPlayerId()), ((GeneralPointSweepResponse)this.response).toString() });
/* 118 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\general\GeneralPointSweepProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */