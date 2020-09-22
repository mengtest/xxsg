/*    */ package com.linlongyx.sanguo.webgame.processors.task;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.LogUtils;
/*    */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.DailyBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.DailyProcessBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.processors.luckymoney.LuckyMoneyUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.task.TaskDailyRewardRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.task.TaskDailyRewardResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TaskDailyRewardProcessor
/*    */   extends ProcessorBase<TaskDailyRewardRequest, TaskDailyRewardResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 28 */     this.response = (ResponseBase)new TaskDailyRewardResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, TaskDailyRewardRequest request) {
/* 33 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 22)) {
/* 34 */       return 10061;
/*    */     }
/* 36 */     int type = request.type;
/* 37 */     int id = request.id;
/* 38 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 39 */     if (1 == type) {
/* 40 */       DailyBean dailyBean = (DailyBean)JsonTableService.getJsonData(id, DailyBean.class);
/* 41 */       if (null == dailyBean) {
/* 42 */         return 12211;
/*    */       }
/* 44 */       Set<Integer> dailyDone = taskComponent.getDailyDone();
/* 45 */       if (!dailyDone.contains(Integer.valueOf(id))) {
/* 46 */         return 12201;
/*    */       }
/* 48 */       Set<Integer> dailyReward = taskComponent.getDailyReward();
/* 49 */       if (dailyReward.contains(Integer.valueOf(id))) {
/* 50 */         return 12212;
/*    */       }
/* 52 */       dailyReward.add(Integer.valueOf(id));
/* 53 */       taskComponent.setDailyDone(dailyDone);
/* 54 */       taskComponent.setPoint(taskComponent.getPoint() + dailyBean.getActivity());
/*    */       
/* 56 */       LuckyMoneyUtil.addPoint(taskComponent.getPoint(), playerSession);
/*    */       
/* 58 */       FinanceUtil.reward(FinanceUtil.transform(dailyBean.getReward()), playerSession.getPlayer(), ResourceEvent.TaskDailyReward, true);
/* 59 */     } else if (2 == type) {
/* 60 */       DailyProcessBean dailyProcessBean = (DailyProcessBean)JsonTableService.getJsonData(id, DailyProcessBean.class);
/* 61 */       if (null == dailyProcessBean) {
/* 62 */         return 12211;
/*    */       }
/* 64 */       if (taskComponent.getPoint() < dailyProcessBean.getPoint()) {
/* 65 */         return 12213;
/*    */       }
/* 67 */       Set<Integer> pointReward = taskComponent.getPointReward();
/* 68 */       if (pointReward.contains(Integer.valueOf(id))) {
/* 69 */         return 12212;
/*    */       }
/* 71 */       pointReward.add(Integer.valueOf(id));
/* 72 */       taskComponent.setPointReward(pointReward);
/* 73 */       FinanceUtil.reward(FinanceUtil.transform(dailyProcessBean.getReward()), playerSession.getPlayer(), ResourceEvent.TaskDailyReward, true);
/*    */     } else {
/* 75 */       return 12214;
/*    */     } 
/* 77 */     ((TaskDailyRewardResponse)this.response).type = type;
/* 78 */     ((TaskDailyRewardResponse)this.response).id = id;
/* 79 */     ((TaskDailyRewardResponse)this.response).point = taskComponent.getPoint();
/* 80 */     LogUtils.errorLog(new Object[] { "TaskDailyReward", Long.valueOf(playerSession.getPlayer().getPlayerId()), ((TaskDailyRewardResponse)this.response).toString() });
/* 81 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\task\TaskDailyRewardProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */