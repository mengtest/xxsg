/*    */ package com.linlongyx.sanguo.webgame.processors.task;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.KeyValue;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.task.TaskInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.task.TaskInfoResponse;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TaskInfoProcessor
/*    */   extends ProcessorBase<TaskInfoRequest, TaskInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 21 */     this.response = (ResponseBase)new TaskInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, TaskInfoRequest request) {
/* 26 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 27 */     ((TaskInfoResponse)this.response).id = taskComponent.getId();
/* 28 */     ((TaskInfoResponse)this.response).schedule = taskComponent.getSchedule();
/* 29 */     ((TaskInfoResponse)this.response).chapter = taskComponent.getChapter();
/* 30 */     ((TaskInfoResponse)this.response).chapterReward.addAll(taskComponent.getChapterReward().keySet());
/* 31 */     ((TaskInfoResponse)this.response).guideId = taskComponent.getGuideId();
/* 32 */     ((TaskInfoResponse)this.response).askList = new ArrayList(taskComponent.getAskReward().keySet());
/* 33 */     Set<Integer> set = taskComponent.getPreviewReward();
/* 34 */     ((TaskInfoResponse)this.response).previewReward = new ArrayList<>(set);
/* 35 */     for (Iterator<Integer> iterator = taskComponent.getPreviewValue().keySet().iterator(); iterator.hasNext(); ) { int id = ((Integer)iterator.next()).intValue();
/* 36 */       KeyValue keyValue = new KeyValue();
/* 37 */       keyValue.key = id;
/* 38 */       keyValue.value = ((Long)taskComponent.getPreviewValue().getOrDefault(Integer.valueOf(id), Long.valueOf(0L))).longValue();
/* 39 */       ((TaskInfoResponse)this.response).previewValue.add(keyValue); }
/*    */     
/* 41 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\task\TaskInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */