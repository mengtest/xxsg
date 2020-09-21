/*    */ package com.linlongyx.sanguo.webgame.processors.task;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.MainInsBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*    */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.task.GetChapterBoxRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.task.GetChapterBoxResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GetChapterBoxProcessor
/*    */   extends ProcessorBase<GetChapterBoxRequest, GetChapterBoxResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 24 */     this.response = (ResponseBase)new GetChapterBoxResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, GetChapterBoxRequest request) {
/* 29 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/*    */     
/* 31 */     if (!taskComponent.getInsMap().containsKey(Integer.valueOf(request.chapterId))) {
/* 32 */       return 12203;
/*    */     }
/* 34 */     if (taskComponent.getChapterReward().containsKey(Integer.valueOf(request.chapterId))) {
/* 35 */       return 12204;
/*    */     }
/* 37 */     int insId = ((Integer)taskComponent.getInsMap().get(Integer.valueOf(request.chapterId))).intValue();
/* 38 */     MainInsBean mainInsBean = (MainInsBean)JsonTableService.getJsonData(insId, MainInsBean.class);
/* 39 */     if (null != mainInsBean) {
/* 40 */       FinanceUtil.reward(FinanceUtil.transform(mainInsBean.getChapteReward()), playerSession.getPlayer(), ResourceEvent.MainTaskBox, true);
/* 41 */       taskComponent.getChapterReward().put(Integer.valueOf(request.chapterId), Integer.valueOf(TimeUtil.currentTime()));
/* 42 */       taskComponent.setChapterReward(taskComponent.getChapterReward());
/*    */     } 
/* 44 */     ((GetChapterBoxResponse)this.response).chapterReward.addAll(taskComponent.getChapterReward().keySet());
/* 45 */     taskComponent.refreshSchedule(TaskType.ChapterReward, 0, 0L);
/* 46 */     LogUtil.errorLog(new Object[] { "GetChapterBoxProcessor", Long.valueOf(playerSession.getPlayer().getPlayerId()), Integer.valueOf(request.chapterId) });
/* 47 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\task\GetChapterBoxProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */