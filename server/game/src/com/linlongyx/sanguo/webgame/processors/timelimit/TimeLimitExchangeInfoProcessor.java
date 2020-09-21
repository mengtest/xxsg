/*    */ package com.linlongyx.sanguo.webgame.processors.timelimit;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.limitexchange.LimitExchangeComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.SpecialExchargeBean;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.LimitBuyActParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.IntIntValue;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.timelimit.TimeLimitExchangeInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.timelimit.TimeLimitExchangeInfoResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TimeLimitExchangeInfoProcessor
/*    */   extends ProcessorBase<TimeLimitExchangeInfoRequest, TimeLimitExchangeInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 27 */     this.response = (ResponseBase)new TimeLimitExchangeInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, TimeLimitExchangeInfoRequest request) {
/* 32 */     LimitBuyActParameter limitBuyActParameter = (LimitBuyActParameter)ParameterConstant.getParameter(68);
/* 33 */     List<Integer> list = limitBuyActParameter.getLimitExchangeAct(true);
/*    */     
/* 35 */     if (!list.isEmpty()) {
/* 36 */       LimitExchangeComponent limitExchangeComponent = (LimitExchangeComponent)playerSession.getPlayer().createIfNotExist(LimitExchangeComponent.class);
/* 37 */       Map<Integer, Integer> limitExchangeGoods = limitExchangeComponent.getLimitExchangeGoods(((Integer)list.get(0)).intValue());
/* 38 */       SpecialExchargeBean specialExchargeBean = (SpecialExchargeBean)JsonTableService.getJsonData(((Integer)list.get(0)).intValue(), SpecialExchargeBean.class);
/* 39 */       for (Integer actId : specialExchargeBean.getActivityList()) {
/* 40 */         IntIntValue intIntValue = new IntIntValue();
/* 41 */         intIntValue.key = actId.intValue();
/* 42 */         intIntValue.value = ((Integer)limitExchangeGoods.getOrDefault(actId, Integer.valueOf(0))).intValue();
/* 43 */         ((TimeLimitExchangeInfoResponse)this.response).goods.add(intIntValue);
/*    */       } 
/*    */     } 
/* 46 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\timelimit\TimeLimitExchangeInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */