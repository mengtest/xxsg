/*    */ package com.linlongyx.sanguo.webgame.processors.task;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.DailyBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.TaskTypeBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.IntLongValue;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.task.TaskDailyInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.task.TaskDailyInfoResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TaskDailyInfoProcessor
/*    */   extends ProcessorBase<TaskDailyInfoRequest, TaskDailyInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 27 */     this.response = (ResponseBase)new TaskDailyInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, TaskDailyInfoRequest request) {
/* 32 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 22))
/* 33 */       return 10061; 
/* 34 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 35 */     ((TaskDailyInfoResponse)this.response).point = taskComponent.getPoint();
/* 36 */     Map<Integer, Long> dailyProcess = taskComponent.getDailyProcess();
/*    */     
/* 38 */     Map<Integer, Object> map = JsonTableService.getJsonTable(DailyBean.class);
/* 39 */     for (Map.Entry<Integer, Object> entry : map.entrySet()) {
/* 40 */       DailyBean dailyBean = (DailyBean)entry.getValue();
/* 41 */       TaskTypeBean taskTypeBean = (TaskTypeBean)JsonTableService.getJsonData(dailyBean.getTaskType(), TaskTypeBean.class);
/* 42 */       if (taskTypeBean != null && taskTypeBean.getCountType() == 0) {
/* 43 */         taskComponent.refreshDailySchedule(TaskType.getTaskType(taskTypeBean.getTaskType()), dailyBean.getTargeId(), 0L);
/*    */       }
/* 45 */       IntLongValue intLongValue = new IntLongValue();
/* 46 */       intLongValue.key = dailyBean.getId();
/* 47 */       intLongValue.value = ((Long)dailyProcess.getOrDefault(Integer.valueOf(dailyBean.getId()), Long.valueOf(0L))).longValue();
/* 48 */       ((TaskDailyInfoResponse)this.response).process.add(intLongValue);
/*    */     } 
/* 50 */     ((TaskDailyInfoResponse)this.response).reward1 = new ArrayList(taskComponent.getPointReward());
/* 51 */     ((TaskDailyInfoResponse)this.response).reward2 = new ArrayList(taskComponent.getDailyReward());
/* 52 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\task\TaskDailyInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */