/*    */ package com.linlongyx.sanguo.webgame.processors.timelimit;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.limitbuy.LimitBuyComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.SpecialOfferBean;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.LimitBuyActParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.IntIntValue;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.timelimit.TimeLimitBuyInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.timelimit.TimeLimitBuyInfoResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TimeLimitBuyInfoProcessor
/*    */   extends ProcessorBase<TimeLimitBuyInfoRequest, TimeLimitBuyInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 27 */     this.response = (ResponseBase)new TimeLimitBuyInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, TimeLimitBuyInfoRequest request) {
/* 32 */     LimitBuyComponent limitBuyComponent = (LimitBuyComponent)playerSession.getPlayer().createIfNotExist(LimitBuyComponent.class);
/* 33 */     LimitBuyActParameter limitBuyActParameter = (LimitBuyActParameter)ParameterConstant.getParameter(68);
/* 34 */     List<Integer> list = limitBuyActParameter.getLimitBuyAct(true);
/*    */     
/* 36 */     if (!list.isEmpty()) {
/* 37 */       Map<Integer, Integer> limitBuyGoods = limitBuyComponent.getLimitBuyGoods(((Integer)list.get(0)).intValue());
/*    */       
/* 39 */       SpecialOfferBean specialOfferBean = (SpecialOfferBean)JsonTableService.getJsonData(((Integer)list.get(0)).intValue(), SpecialOfferBean.class);
/* 40 */       ArrayList<Integer> arr = specialOfferBean.getActivityList();
/* 41 */       for (Integer actId : arr) {
/* 42 */         IntIntValue intIntValue = new IntIntValue();
/* 43 */         intIntValue.key = actId.intValue();
/* 44 */         intIntValue.value = ((Integer)limitBuyGoods.getOrDefault(actId, Integer.valueOf(0))).intValue();
/* 45 */         ((TimeLimitBuyInfoResponse)this.response).goods.add(intIntValue);
/*    */       } 
/*    */     } 
/* 48 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\timelimit\TimeLimitBuyInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */