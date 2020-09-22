/*    */ package com.linlongyx.sanguo.webgame.processors.task;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*    */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.task.TaskOpenAskRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.task.TaskOpenAskResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TaskOpenAskProcessor
/*    */   extends ProcessorBase<TaskOpenAskRequest, TaskOpenAskResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 18 */     this.response = (ResponseBase)new TaskOpenAskResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, TaskOpenAskRequest request) {
/* 23 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 24 */     taskComponent.refreshSchedule(TaskType.OpenAskPanel, 0, 1L);
/* 25 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\task\TaskOpenAskProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */