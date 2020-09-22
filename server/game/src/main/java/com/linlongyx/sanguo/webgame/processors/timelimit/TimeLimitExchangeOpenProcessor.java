/*    */ package com.linlongyx.sanguo.webgame.processors.timelimit;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.limitexchange.LimitExchangeComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.LimitBuyActParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.FestivalTime;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.timelimit.TimeLimitExchangeOpenRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.timelimit.TimeLimitExchangeOpenResponse;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TimeLimitExchangeOpenProcessor
/*    */   extends ProcessorBase<TimeLimitExchangeOpenRequest, TimeLimitExchangeOpenResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 26 */     this.response = (ResponseBase)new TimeLimitExchangeOpenResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, TimeLimitExchangeOpenRequest request) {
/* 31 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 51))
/* 32 */       return 10061; 
/* 33 */     LimitBuyActParameter limitBuyActParameter = (LimitBuyActParameter)ParameterConstant.getParameter(68);
/* 34 */     LimitExchangeComponent limitExchangeComponent = (LimitExchangeComponent)playerSession.getPlayer().createIfNotExist(LimitExchangeComponent.class);
/* 35 */     List<Integer> list = limitBuyActParameter.getLimitExchangeAct(true);
/* 36 */     if (!list.isEmpty()) {
/* 37 */       Map<Integer, FestivalTime> festivalTimes = limitBuyActParameter.getActTimesExchange();
/* 38 */       for (Integer actId : list) {
/* 39 */         ((TimeLimitExchangeOpenResponse)this.response).list.add(festivalTimes.get(actId));
/* 40 */         limitExchangeComponent.getEntity(actId.intValue());
/*    */       } 
/*    */     } 
/* 43 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\timelimit\TimeLimitExchangeOpenProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */