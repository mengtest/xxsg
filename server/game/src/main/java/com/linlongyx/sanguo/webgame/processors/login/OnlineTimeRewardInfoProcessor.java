/*    */ package com.linlongyx.sanguo.webgame.processors.login;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.OnlineRewardlistBean;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.login.OnlineTimeRewardInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.login.OnlineTimeRewardInfoResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class OnlineTimeRewardInfoProcessor
/*    */   extends ProcessorBase<OnlineTimeRewardInfoRequest, OnlineTimeRewardInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 24 */     this.response = (ResponseBase)new OnlineTimeRewardInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, OnlineTimeRewardInfoRequest request) {
/* 29 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/* 30 */     ArrayList<Integer> onlineReward = playerComponent.getOnlineReward();
/* 31 */     if (onlineReward.isEmpty()) {
/* 32 */       onlineReward.add(Integer.valueOf(0));
/* 33 */       onlineReward.add(Integer.valueOf(0));
/* 34 */       onlineReward.add(Integer.valueOf(0));
/*    */     } 
/* 36 */     Set<Integer> onlineTimeSet = JsonTableService.getJsonTableKey(OnlineRewardlistBean.class);
/* 37 */     int onlineSeconds = playerComponent.getOnlineTime() + TimeUtil.currentTime() - playerComponent.getLastOnlineTime();
/* 38 */     int onlineMinutes = onlineSeconds / 60;
/* 39 */     if (request.type == 0) {
/* 40 */       if (((Integer)onlineReward.get(0)).intValue() > 0 && ((Integer)onlineReward.get(1)).intValue() == 0) {
/* 41 */         ((OnlineTimeRewardInfoResponse)this.response).canGet = 1;
/* 42 */         ((OnlineTimeRewardInfoResponse)this.response).time = ((Integer)onlineReward.get(0)).intValue();
/* 43 */         return 0;
/*    */       } 
/* 45 */       int targetId = 0;
/* 46 */       for (Integer k : onlineTimeSet) {
/* 47 */         if (((Integer)onlineReward.get(0)).intValue() < k.intValue()) {
/* 48 */           targetId = k.intValue();
/*    */           break;
/*    */         } 
/*    */       } 
/* 52 */       if (targetId == 0) {
/* 53 */         ((OnlineTimeRewardInfoResponse)this.response).canGet = 0;
/* 54 */         ((OnlineTimeRewardInfoResponse)this.response).time = 0;
/*    */       }
/* 56 */       else if (onlineMinutes >= targetId) {
/* 57 */         onlineReward.set(0, Integer.valueOf(targetId));
/* 58 */         onlineReward.set(1, Integer.valueOf(0));
/* 59 */         onlineReward.set(2, Integer.valueOf(0));
/* 60 */         ((OnlineTimeRewardInfoResponse)this.response).canGet = 1;
/* 61 */         ((OnlineTimeRewardInfoResponse)this.response).time = targetId;
/*    */       } else {
/* 63 */         ((OnlineTimeRewardInfoResponse)this.response).canGet = 0;
/* 64 */         ((OnlineTimeRewardInfoResponse)this.response).time = targetId * 60 - onlineSeconds;
/*    */       } 
/*    */       
/* 67 */       return 0;
/*    */     } 
/* 69 */     if (request.type == 1) {
/* 70 */       ((OnlineTimeRewardInfoResponse)this.response).canGet = (byte)((((Integer)onlineReward.get(2)).intValue() == 0) ? 1 : 0);
/* 71 */       ((OnlineTimeRewardInfoResponse)this.response).time = ((Integer)onlineReward.get(0)).intValue();
/*    */     } 
/* 73 */     playerComponent.setOnlineReward(onlineReward);
/* 74 */     System.out.println("online time reward " + request.type);
/* 75 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\login\OnlineTimeRewardInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */