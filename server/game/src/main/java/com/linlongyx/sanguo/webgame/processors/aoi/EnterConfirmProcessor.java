/*    */ package com.linlongyx.sanguo.webgame.processors.aoi;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*    */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.aoi.EnterConfirmRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.aoi.EnterConfirmResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnterConfirmProcessor
/*    */   extends ProcessorBase<EnterConfirmRequest, EnterConfirmResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 18 */     this.response = (ResponseBase)new EnterConfirmResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, EnterConfirmRequest request) {
/* 23 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 24 */     if (taskComponent != null) {
/* 25 */       taskComponent.refreshSchedule(TaskType.EnterScene, request.sceneId, 1L);
/*    */     }
/* 27 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\aoi\EnterConfirmProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */