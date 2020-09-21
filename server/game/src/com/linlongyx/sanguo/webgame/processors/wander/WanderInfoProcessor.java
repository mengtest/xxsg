/*    */ package com.linlongyx.sanguo.webgame.processors.wander;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.app.extend.ExtendComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.WanderBean;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.WanderParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.wander.WanderInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.wander.WanderInfoResponse;
/*    */ import java.util.HashSet;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ 
/*    */ public class WanderInfoProcessor
/*    */   extends ProcessorBase<WanderInfoRequest, WanderInfoResponse> {
/*    */   protected void initResponse() {
/* 22 */     this.response = (ResponseBase)new WanderInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, WanderInfoRequest request) {
/* 27 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 71))
/* 28 */       return 10061; 
/* 29 */     ExtendComponent extendComponent = (ExtendComponent)playerSession.getPlayer().createIfNotExist(ExtendComponent.class);
/* 30 */     Map<Integer, Integer> curWanderReward = extendComponent.getWanderReward();
/* 31 */     WanderBean wanderReward = WanderUtil.getWander(playerSession.getPlayer().getPlayerId());
/* 32 */     Set<Integer> last = extendComponent.getLastWanderReward();
/* 33 */     WanderParameter wanderParameter = (WanderParameter)ParameterConstant.getParameter(71);
/* 34 */     ((WanderInfoResponse)this.response).todayRecharge = extendComponent.getTodayRecharge() / 100L;
/* 35 */     int curTime = TimeUtil.currentTime();
/* 36 */     if (curTime >= extendComponent.getWanderEndtime() + wanderParameter.getSpaceSend()) {
/* 37 */       ((WanderInfoResponse)this.response).endTime = (TimeUtil.currentTime() + wanderParameter.getSpaceTime());
/* 38 */       extendComponent.setWanderEndtime(((WanderInfoResponse)this.response).endTime);
/* 39 */       extendComponent.setLastWanderReward(new HashSet());
/*    */     } else {
/* 41 */       ((WanderInfoResponse)this.response).endTime = extendComponent.getWanderEndtime();
/* 42 */       for (Integer i : wanderReward.getEntry()) {
/* 43 */         if (!curWanderReward.containsKey(i)) {
/* 44 */           last.add(i);
/*    */         }
/*    */       } 
/* 47 */       extendComponent.setLastWanderReward(last);
/*    */     } 
/* 49 */     for (Integer i : wanderReward.getEntry()) {
/* 50 */       if (!curWanderReward.containsKey(i)) {
/* 51 */         ((WanderInfoResponse)this.response).rewards.add(i);
/*    */       }
/*    */     } 
/* 54 */     Set<Integer> oprLast = new HashSet<>();
/* 55 */     for (Integer i : extendComponent.getLastWanderReward()) {
/* 56 */       if (!curWanderReward.containsKey(i)) {
/* 57 */         oprLast.add(i);
/*    */       }
/* 59 */       extendComponent.setLastWanderReward(oprLast);
/*    */     } 
/* 61 */     for (Integer i : extendComponent.getLastWanderReward()) {
/* 62 */       if (!((WanderInfoResponse)this.response).rewards.contains(i)) {
/* 63 */         ((WanderInfoResponse)this.response).rewards.add(i);
/*    */       }
/*    */     } 
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 82 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\wander\WanderInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */