/*    */ package com.linlongyx.sanguo.webgame.processors.timelimit;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.limitbuy.LimitBuyComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.LimitBuyActParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.FestivalTime;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.timelimit.TimeLimitBuyOpenRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.timelimit.TimeLimitBuyOpenResponse;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TimeLimitBuyOpenProcessor
/*    */   extends ProcessorBase<TimeLimitBuyOpenRequest, TimeLimitBuyOpenResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 26 */     this.response = (ResponseBase)new TimeLimitBuyOpenResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, TimeLimitBuyOpenRequest request) {
/* 31 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 51))
/* 32 */       return 10061; 
/* 33 */     LimitBuyActParameter limitBuyActParameter = (LimitBuyActParameter)ParameterConstant.getParameter(68);
/* 34 */     LimitBuyComponent limitBuyComponent = (LimitBuyComponent)playerSession.getPlayer().createIfNotExist(LimitBuyComponent.class);
/* 35 */     List<Integer> list = limitBuyActParameter.getLimitBuyAct(true);
/* 36 */     if (!list.isEmpty()) {
/* 37 */       Map<Integer, FestivalTime> festivalTimes = limitBuyActParameter.getActTimes();
/* 38 */       for (Integer actId : list) {
/* 39 */         if (festivalTimes.containsKey(actId)) {
/* 40 */           ((TimeLimitBuyOpenResponse)this.response).list.add(festivalTimes.get(actId));
/* 41 */           limitBuyComponent.getEntity(actId.intValue());
/*    */         } 
/*    */       } 
/*    */     } 
/* 45 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\timelimit\TimeLimitBuyOpenProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */