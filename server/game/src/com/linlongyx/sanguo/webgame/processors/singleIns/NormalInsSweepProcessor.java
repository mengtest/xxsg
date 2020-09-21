/*    */ package com.linlongyx.sanguo.webgame.processors.singleIns;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.LogUtils;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.singleIns.SingleInsComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.PersonalInsBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*    */ import com.linlongyx.sanguo.webgame.processors.login.VipUtil;
/*    */ import com.linlongyx.sanguo.webgame.processors.offices.MilitaryUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.singleIns.NormalInsSweepRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.singleIns.NormalInsSweepResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ 
/*    */ public class NormalInsSweepProcessor
/*    */   extends ProcessorBase<NormalInsSweepRequest, NormalInsSweepResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 30 */     this.response = (ResponseBase)new NormalInsSweepResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, NormalInsSweepRequest request) {
/* 35 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 13)) {
/* 36 */       return 10061;
/*    */     }
/* 38 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/* 39 */     if (!VipUtil.isAllow(playerComponent.getVip(), 13)) {
/* 40 */       return 10088;
/*    */     }
/* 42 */     int insId = request.insId;
/* 43 */     PersonalInsBean personalInsBean = (PersonalInsBean)JsonTableService.getJsonData(insId, PersonalInsBean.class);
/* 44 */     if (null == personalInsBean) {
/* 45 */       return 13002;
/*    */     }
/* 47 */     int type = personalInsBean.getIntType();
/* 48 */     if (!SingleInsUtil.isDayValue(personalInsBean.getDay())) {
/* 49 */       return 13005;
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 55 */     SingleInsComponent singleInsComponent = (SingleInsComponent)playerSession.getPlayer().createIfNotExist(SingleInsComponent.class);
/* 56 */     Map<Integer, Integer> times = singleInsComponent.getTimes();
/* 57 */     Map<Integer, Integer> maxPoints = singleInsComponent.getMaxPoints();
/* 58 */     int point = ((Integer)maxPoints.getOrDefault(Integer.valueOf(type), Integer.valueOf(0))).intValue();
/* 59 */     Set<Integer> clears = singleInsComponent.getClears();
/* 60 */     if (personalInsBean.getCheckPoint() > point || !clears.contains(Integer.valueOf(insId))) {
/* 61 */       return 13003;
/*    */     }
/*    */     
/* 64 */     int dailyTime = VipUtil.getNum(playerComponent.getVip(), 8);
/* 65 */     int time = ((Integer)times.getOrDefault(Integer.valueOf(type), Integer.valueOf(0))).intValue();
/* 66 */     if (time >= dailyTime) {
/* 67 */       return 13004;
/*    */     }
/* 69 */     time++;
/* 70 */     times.put(Integer.valueOf(type), Integer.valueOf(time));
/* 71 */     singleInsComponent.setTimes(times);
/* 72 */     ArrayList<Reward> arrayList = new ArrayList<>();
/* 73 */     arrayList = FinanceUtil.transform(personalInsBean.getPassReward());
/* 74 */     MilitaryUtil.DailyPoint(arrayList, type, playerSession, 0);
/* 75 */     ArrayList<Reward> rewards = FinanceUtil.rewardGet(arrayList, playerSession.getPlayer(), ResourceEvent.SingleInsSweep, true);
/*    */     
/* 77 */     ((NormalInsSweepResponse)this.response).insId = insId;
/* 78 */     ((NormalInsSweepResponse)this.response).leftCount = dailyTime - time;
/* 79 */     ((NormalInsSweepResponse)this.response).list = rewards;
/*    */     
/* 81 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 82 */     taskComponent.refreshSchedule(TaskType.ClearNormal, 0, 1L);
/* 83 */     singleInsComponent.setTotalTime(singleInsComponent.getTotalTime() + 1);
/* 84 */     taskComponent.refreshSchedule(TaskType.TotalNormal, 0, 0L);
/* 85 */     taskComponent.refreshSchedule(TaskType.NormalChallenge, personalInsBean.getIntType(), 1L);
/* 86 */     singleInsComponent.getTotalChallengeMap().put(Integer.valueOf(personalInsBean.getIntType()), Integer.valueOf(((Integer)singleInsComponent.getTotalChallengeMap().getOrDefault(Integer.valueOf(personalInsBean.getIntType()), Integer.valueOf(0))).intValue() + 1));
/* 87 */     singleInsComponent.setTotalChallengeMap(singleInsComponent.getTotalChallengeMap());
/* 88 */     taskComponent.refreshSchedule(TaskType.TotalNormalChallenge, personalInsBean.getIntType(), 1L);
/* 89 */     singleInsComponent.saveFindTimes(true, personalInsBean.getIntType(), 1);
/* 90 */     LogUtils.errorLog(new Object[] { "NormalInsSweep", Long.valueOf(playerSession.getPlayer().getPlayerId()), Integer.valueOf(insId), Integer.valueOf(time), Integer.valueOf(((NormalInsSweepResponse)this.response).leftCount) });
/* 91 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\singleIns\NormalInsSweepProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */