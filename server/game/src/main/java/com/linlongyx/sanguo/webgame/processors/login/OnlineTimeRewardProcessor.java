/*    */ package com.linlongyx.sanguo.webgame.processors.login;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.OnlineRewardlistBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.login.OnlineTimeRewardRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.login.OnlineTimeRewardResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class OnlineTimeRewardProcessor
/*    */   extends ProcessorBase<OnlineTimeRewardRequest, OnlineTimeRewardResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 26 */     this.response = (ResponseBase)new OnlineTimeRewardResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, OnlineTimeRewardRequest request) {
/* 31 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/* 32 */     ArrayList<Integer> onlineReward = playerComponent.getOnlineReward();
/* 33 */     if (((Integer)onlineReward.get(0)).intValue() != request.min) {
/* 34 */       return 10095;
/*    */     }
/* 36 */     OnlineRewardlistBean bean = (OnlineRewardlistBean)JsonTableService.getJsonData(((Integer)onlineReward.get(0)).intValue(), OnlineRewardlistBean.class);
/* 37 */     if (request.type == 0) {
/* 38 */       if (((Integer)onlineReward.get(1)).intValue() != 0) {
/* 39 */         return 10091;
/*    */       }
/* 41 */       onlineReward.set(1, onlineReward.get(0));
/* 42 */       playerComponent.setOnlineTime(0);
/* 43 */       playerComponent.setLastOnlineTime(TimeUtil.currentTime());
/*    */       
/* 45 */       FinanceUtil.reward(FinanceUtil.transform(bean.getOnlineReward()), playerSession.getPlayer(), ResourceEvent.OnlineReward, true);
/* 46 */     } else if (request.type == 1) {
/* 47 */       if (((Integer)onlineReward.get(1)).intValue() == 0) {
/* 48 */         return 10095;
/*    */       }
/* 50 */       if (((Integer)onlineReward.get(2)).intValue() != 0) {
/* 51 */         return 10091;
/*    */       }
/* 53 */       onlineReward.set(2, onlineReward.get(0));
/*    */       
/* 55 */       FinanceUtil.reward(FinanceUtil.transform(bean.getExtraReward()), playerSession.getPlayer(), ResourceEvent.OnlineReward, true);
/*    */     } 
/* 57 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 58 */     if (null != taskComponent) {
/* 59 */       taskComponent.refreshSchedule(TaskType.GetOnlineGift, 0, 1L);
/*    */     }
/* 61 */     System.out.println("online time reward get " + request.type);
/* 62 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\login\OnlineTimeRewardProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */