/*    */ package com.linlongyx.sanguo.webgame.processors.achieve;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.achieve.AchieveComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.FameBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.TaskTypeBean;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.AchieveParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*    */ import com.linlongyx.sanguo.webgame.processors.task.TaskUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.task.TaskCompleteNoticeResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AchieveUtil
/*    */ {
/*    */   public static void refreshAchieve(TaskType taskType, int targetId, long value, long playerId) {
/* 30 */     int type = taskType.getType();
/* 31 */     AchieveParameter achieveParameter = (AchieveParameter)ParameterConstant.getParameter(4);
/* 32 */     List<Integer> list = achieveParameter.getAchieveList(type);
/* 33 */     TaskTypeBean taskTypeBean = (TaskTypeBean)JsonTableService.getJsonData(type, TaskTypeBean.class);
/* 34 */     if (null == list || null == taskTypeBean || list.isEmpty()) {
/*    */       return;
/*    */     }
/* 37 */     AchieveComponent achieveComponent = (AchieveComponent)LookUpService.getComponent(playerId, AchieveComponent.class);
/* 38 */     if (null != achieveComponent) {
/* 39 */       Map<Integer, Long> values = achieveComponent.getValue();
/* 40 */       Set<Integer> fameDone = achieveComponent.getFameDone();
/* 41 */       boolean isDirty = false;
/* 42 */       for (Integer id : list) {
/* 43 */         FameBean fameBean = (FameBean)JsonTableService.getJsonData(id.intValue(), FameBean.class);
/* 44 */         if (null == fameBean || fameDone.contains(id)) {
/*    */           continue;
/*    */         }
/*    */ 
/*    */ 
/*    */         
/* 50 */         long oldValue = ((Long)values.getOrDefault(id, Long.valueOf(0L))).longValue();
/* 51 */         if (taskTypeBean.getCountType() == 1) {
/* 52 */           oldValue += value;
/*    */         } else {
/* 54 */           oldValue = TaskUtil.getSchedule(playerId, taskType.getType(), fameBean.getTarget());
/*    */         } 
/* 56 */         boolean isDone = TaskUtil.isTaskDone(oldValue, fameBean.getNumber(), taskTypeBean.getCount());
/* 57 */         if (isDone) {
/* 58 */           fameDone.add(id);
/* 59 */           isDirty = true;
/* 60 */           oldValue = fameBean.getNumber();
/*    */ 
/*    */           
/* 63 */           TaskCompleteNoticeResponse noticeResp = new TaskCompleteNoticeResponse();
/* 64 */           noticeResp.type = type;
/* 65 */           noticeResp.id = id.intValue();
/* 66 */           if (LookUpService.isOnline(playerId)) {
/* 67 */             LookUpService.getByPlayerId(playerId).getSession().sendMessage((ResponseBase)noticeResp);
/*    */           }
/*    */         } 
/* 70 */         values.put(id, Long.valueOf(oldValue));
/*    */       } 
/* 72 */       achieveComponent.setValue(values);
/* 73 */       if (isDirty) {
/* 74 */         achieveComponent.setFameDone(fameDone);
/*    */       }
/* 76 */       achieveComponent.maybeSaveToDB();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\achieve\AchieveUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */