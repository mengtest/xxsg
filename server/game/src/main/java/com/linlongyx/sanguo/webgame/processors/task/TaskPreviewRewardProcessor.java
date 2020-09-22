/*    */ package com.linlongyx.sanguo.webgame.processors.task;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.LogUtils;
/*    */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.FunctionPredictBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.task.TaskPreviewRewardRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.task.TaskPreviewRewardResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TaskPreviewRewardProcessor
/*    */   extends ProcessorBase<TaskPreviewRewardRequest, TaskPreviewRewardResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 22 */     this.response = (ResponseBase)new TaskPreviewRewardResponse();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, TaskPreviewRewardRequest request) {
/* 28 */     FunctionPredictBean functionPredictBean = (FunctionPredictBean)JsonTableService.getJsonData(request.id, FunctionPredictBean.class);
/* 29 */     if (null == functionPredictBean) {
/* 30 */       return 10006;
/*    */     }
/* 32 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 33 */     if (taskComponent.getPreviewReward().contains(Integer.valueOf(request.id))) {
/* 34 */       return 10091;
/*    */     }
/* 36 */     if (functionPredictBean.getTargetType() == 0) {
/* 37 */       return 14008;
/*    */     }
/* 39 */     long value = ((Long)taskComponent.getPreviewValue().getOrDefault(Integer.valueOf(request.id), Long.valueOf(0L))).longValue();
/* 40 */     if (value < functionPredictBean.getNum()) {
/* 41 */       return 12201;
/*    */     }
/* 43 */     taskComponent.getPreviewReward().add(Integer.valueOf(request.id));
/* 44 */     taskComponent.setPreviewReward(taskComponent.getPreviewReward());
/* 45 */     FinanceUtil.rewardBean(functionPredictBean.getReward(), playerSession.getPlayer(), ResourceEvent.PreviewReward, true);
/* 46 */     LogUtils.errorLog(new Object[] { "TaskPreviewRewardProcessor", Long.valueOf(playerSession.getPlayer().getPlayerId()), Integer.valueOf(request.id) });
/* 47 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\task\TaskPreviewRewardProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */