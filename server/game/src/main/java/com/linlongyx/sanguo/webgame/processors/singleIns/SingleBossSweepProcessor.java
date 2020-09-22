/*    */ package com.linlongyx.sanguo.webgame.processors.singleIns;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.singleIns.SingleInsComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.BossHomeBean;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.BossHomeParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.MilitaryInsType;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*    */ import com.linlongyx.sanguo.webgame.processors.offices.MilitaryUtil;
/*    */ import com.linlongyx.sanguo.webgame.processors.task.FindRewardType;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.singleIns.SingleBossSweepRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.singleIns.SingleBossSweepResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class SingleBossSweepProcessor extends ProcessorBase<SingleBossSweepRequest, SingleBossSweepResponse> {
/*    */   protected void initResponse() {
/* 30 */     this.response = (ResponseBase)new SingleBossSweepResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, SingleBossSweepRequest request) {
/* 35 */     SingleInsComponent singleInsComponent = (SingleInsComponent)playerSession.getPlayer().createIfNotExist(SingleInsComponent.class);
/* 36 */     BossHomeParameter bossHomeParameter = (BossHomeParameter)ParameterConstant.getParameter(3);
/* 37 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 38 */     ArrayList<Reward> arrayList = new ArrayList<>();
/* 39 */     if (request.type == 0) {
/* 40 */       if (!singleInsComponent.getBossMap().containsKey(Integer.valueOf(request.bossId))) {
/* 41 */         return 13114;
/*    */       }
/* 43 */       BossHomeBean bossHomeBean = (BossHomeBean)JsonTableService.getJsonData(request.bossId, BossHomeBean.class);
/* 44 */       if (null == bossHomeBean) {
/* 45 */         return 10006;
/*    */       }
/*    */       
/* 48 */       int currTimes = SingleInsUtil.getChallengeTimes(playerSession.getPlayer(), request.bossId);
/*    */       
/* 50 */       int challenge = 0;
/* 51 */       if (null != singleInsComponent.getTimesMap().get(Integer.valueOf(request.bossId))) {
/* 52 */         challenge = ((Integer)singleInsComponent.getTimesMap().get(Integer.valueOf(request.bossId))).intValue();
/* 53 */         if (challenge >= currTimes) {
/* 54 */           return 13116;
/*    */         }
/*    */       } 
/* 57 */       challenge++;
/* 58 */       singleInsComponent.getTimesMap().put(Integer.valueOf(request.bossId), Integer.valueOf(challenge));
/* 59 */       singleInsComponent.setTimesMap(singleInsComponent.getTimesMap());
/*    */       
/* 61 */       arrayList = FinanceUtil.transform(bossHomeBean.getLastHitReward());
/* 62 */       int[] value = MilitaryUtil.getRewardResult(arrayList, MilitaryInsType.SingleBoss.getType(), playerSession.getPlayer().getPlayerId(), 0);
/* 63 */       ArrayList<Reward> rewards = FinanceUtil.rewardGet(arrayList, playerSession.getPlayer(), ResourceEvent.SingleBossSweep, false);
/* 64 */       ((SingleBossSweepResponse)this.response).list = rewards;
/*    */       
/* 66 */       if (null != taskComponent) {
/* 67 */         taskComponent.refreshSchedule(TaskType.ChallegeSingeBoss, bossHomeBean.getLevel(), 1L);
/*    */       }
/* 69 */     } else if (request.type == 1) {
/* 70 */       PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/* 71 */       if (bossHomeParameter.getSinleLimit() > playerComponent.getLevel()) {
/* 72 */         return 10084;
/*    */       }
/*    */       
/* 75 */       for (Iterator<Integer> iterator = singleInsComponent.getBossMap().keySet().iterator(); iterator.hasNext(); ) { int insId = ((Integer)iterator.next()).intValue();
/* 76 */         if (!singleInsComponent.getTimesMap().containsKey(Integer.valueOf(insId))) {
/* 77 */           BossHomeBean bossHomeBean = (BossHomeBean)JsonTableService.getJsonData(insId, BossHomeBean.class);
/* 78 */           if (null == bossHomeBean) {
/*    */             continue;
/*    */           }
/* 81 */           singleInsComponent.getTimesMap().put(Integer.valueOf(insId), Integer.valueOf(((Integer)singleInsComponent.getTimesMap().getOrDefault(Integer.valueOf(insId), Integer.valueOf(0))).intValue() + 1));
/* 82 */           singleInsComponent.setTimesMap(singleInsComponent.getTimesMap());
/* 83 */           arrayList.addAll(FinanceUtil.transform(bossHomeBean.getLastHitReward()));
/* 84 */           taskComponent.refreshSchedule(TaskType.ChallegeSingeBoss, bossHomeBean.getLevel(), 1L);
/*    */         }  }
/*    */       
/* 87 */       MilitaryUtil.getRewardResult(arrayList, MilitaryInsType.SingleBoss.getType(), playerSession.getPlayer().getPlayerId(), 0);
/* 88 */       Map<Long, Long> mapRewards = new HashMap<>();
/* 89 */       FinanceUtil.overlyingMap(mapRewards, arrayList);
/* 90 */       ArrayList<Reward> reward3 = FinanceUtil.overlyingReward(mapRewards);
/* 91 */       ArrayList<Reward> rewards = FinanceUtil.rewardGet(reward3, playerSession.getPlayer(), ResourceEvent.SingleBossSweep, false);
/* 92 */       ((SingleBossSweepResponse)this.response).list = rewards;
/*    */     } 
/* 94 */     singleInsComponent.saveFindTimes(true, FindRewardType.PERSONAL_BOSS.getType(), 1);
/* 95 */     ((SingleBossSweepResponse)this.response).type = request.type;
/* 96 */     ((SingleBossSweepResponse)this.response).bossId = request.bossId;
/* 97 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\singleIns\SingleBossSweepProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */