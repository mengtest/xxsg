/*    */ package com.linlongyx.sanguo.webgame.processors.task;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.ShareRewardBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.task.TaskGetAskRewardRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.task.TaskGetAskRewardResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TaskGetAskRewardProcessor
/*    */   extends ProcessorBase<TaskGetAskRewardRequest, TaskGetAskRewardResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 24 */     this.response = (ResponseBase)new TaskGetAskRewardResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, TaskGetAskRewardRequest request) {
/* 29 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 30 */     int id = request.id;
/* 31 */     ShareRewardBean shareRewardBean = (ShareRewardBean)JsonTableService.getJsonData(id, ShareRewardBean.class);
/* 32 */     if (null == shareRewardBean) {
/* 33 */       return 10006;
/*    */     }
/* 35 */     if (taskComponent.getId() < shareRewardBean.getMainTaskID()) {
/* 36 */       return 15003;
/*    */     }
/* 38 */     if (taskComponent.getAskReward().containsKey(Integer.valueOf(id))) {
/* 39 */       return 15002;
/*    */     }
/* 41 */     taskComponent.getAskReward().put(Integer.valueOf(id), Integer.valueOf(TimeUtil.currentTime()));
/* 42 */     taskComponent.setAskReward(taskComponent.getAskReward());
/* 43 */     ((TaskGetAskRewardResponse)this.response).rewards = FinanceUtil.rewardGet(FinanceUtil.transform(shareRewardBean.getReward()), playerSession.getPlayer(), ResourceEvent.AskShareReward, true);
/* 44 */     ((TaskGetAskRewardResponse)this.response).id = id;
/* 45 */     ((TaskGetAskRewardResponse)this.response).list = new ArrayList(taskComponent.getAskReward().keySet());
/* 46 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\task\TaskGetAskRewardProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */