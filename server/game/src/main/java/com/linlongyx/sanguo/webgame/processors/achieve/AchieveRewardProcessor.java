/*    */ package com.linlongyx.sanguo.webgame.processors.achieve;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.LogUtils;
/*    */ import com.linlongyx.sanguo.webgame.app.achieve.AchieveComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.FameBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.FameProcessBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.achieve.AchieveRewardRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.achieve.AchieveRewardResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AchieveRewardProcessor
/*    */   extends ProcessorBase<AchieveRewardRequest, AchieveRewardResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 27 */     this.response = (ResponseBase)new AchieveRewardResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, AchieveRewardRequest request) {
/* 32 */     AchieveComponent achieveComponent = (AchieveComponent)playerSession.getPlayer().createIfNotExist(AchieveComponent.class);
/* 33 */     int type = request.type;
/* 34 */     int id = request.id;
/* 35 */     int point = achieveComponent.getPoint();
/* 36 */     if (1 == type) {
/* 37 */       FameBean fameBean = (FameBean)JsonTableService.getJsonData(id, FameBean.class);
/* 38 */       if (null == fameBean) {
/* 39 */         return 10401;
/*    */       }
/* 41 */       Set<Integer> fameDone = achieveComponent.getFameDone();
/* 42 */       if (!fameDone.contains(Integer.valueOf(id))) {
/* 43 */         return 10405;
/*    */       }
/* 45 */       Set<Integer> fameReward = achieveComponent.getFameReward();
/* 46 */       if (fameReward.contains(Integer.valueOf(id))) {
/* 47 */         return 10402;
/*    */       }
/* 49 */       fameReward.add(Integer.valueOf(id));
/* 50 */       achieveComponent.setFameReward(fameReward);
/* 51 */       point += fameBean.getFame();
/* 52 */       achieveComponent.setPoint(point);
/*    */       
/* 54 */       TaskComponent taskComponent1 = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 55 */       if (taskComponent1 != null) {
/* 56 */         taskComponent1.refreshSchedule(TaskType.AchieveDone, 0, fameBean.getFame());
/*    */       }
/* 58 */       FinanceUtil.reward(FinanceUtil.transform(fameBean.getFameReward()), playerSession.getPlayer(), ResourceEvent.AchieveReward, true);
/* 59 */     } else if (2 == type) {
/* 60 */       FameProcessBean fameProcessBean = (FameProcessBean)JsonTableService.getJsonData(id, FameProcessBean.class);
/* 61 */       if (null == fameProcessBean) {
/* 62 */         return 10401;
/*    */       }
/* 64 */       if (point < fameProcessBean.getFame()) {
/* 65 */         return 10403;
/*    */       }
/* 67 */       Set<Integer> processReward = achieveComponent.getProcessReward();
/* 68 */       if (processReward.contains(Integer.valueOf(id))) {
/* 69 */         return 10402;
/*    */       }
/* 71 */       processReward.add(Integer.valueOf(id));
/* 72 */       achieveComponent.setProcessReward(processReward);
/* 73 */       FinanceUtil.reward(FinanceUtil.transform(fameProcessBean.getReward()), playerSession.getPlayer(), ResourceEvent.AchieveReward, true);
/*    */     } else {
/* 75 */       return 10404;
/*    */     } 
/* 77 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 78 */     if (taskComponent != null) {
/* 79 */       taskComponent.refreshSchedule(TaskType.FinishRecice, 0, 1L);
/*    */     }
/* 81 */     long playerId = playerSession.getPlayer().getPlayerId();
/* 82 */     ((AchieveRewardResponse)this.response).type = type;
/* 83 */     ((AchieveRewardResponse)this.response).id = id;
/* 84 */     ((AchieveRewardResponse)this.response).point = point;
/* 85 */     LogUtils.errorLog(new Object[] { "AchieveReward", Long.valueOf(playerId), Integer.valueOf(type), Integer.valueOf(id), Integer.valueOf(point) });
/* 86 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\achieve\AchieveRewardProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */