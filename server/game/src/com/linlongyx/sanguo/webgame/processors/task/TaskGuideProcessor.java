/*    */ package com.linlongyx.sanguo.webgame.processors.task;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*    */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*    */ import com.linlongyx.sanguo.webgame.log.LogType;
/*    */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.task.TaskGuideRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.task.TaskGuideResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TaskGuideProcessor
/*    */   extends ProcessorBase<TaskGuideRequest, TaskGuideResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 22 */     this.response = (ResponseBase)new TaskGuideResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, TaskGuideRequest request) {
/* 27 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 28 */     taskComponent.setGuideId(request.guideId);
/* 29 */     ((TaskGuideResponse)this.response).guideId = request.guideId;
/* 30 */     int newGuide = (taskComponent.getId() << 8) + request.guideId;
/* 31 */     if (newGuide <= taskComponent.getTempGuide()) {
/* 32 */       return 0;
/*    */     }
/* 34 */     taskComponent.setTempGuide(newGuide);
/* 35 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/* 36 */     LogUtil.gameLog(LogType.GUIDE, new Object[] { Integer.valueOf(MContext.getServerIdInt()), Long.valueOf(playerComponent.getUserId()), Long.valueOf(playerSession.getPlayer().getPlayerId()), Integer.valueOf(playerComponent.getChannel()), Integer.valueOf(newGuide), TimeUtil.getFormatDate() });
/* 37 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\task\TaskGuideProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */