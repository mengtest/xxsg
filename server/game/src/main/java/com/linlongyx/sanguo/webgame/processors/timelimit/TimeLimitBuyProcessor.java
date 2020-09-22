/*    */ package com.linlongyx.sanguo.webgame.processors.timelimit;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.limitbuy.LimitBuyComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.SpecialOfferListBean;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.timelimit.TimeLimitBuyRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.timelimit.TimeLimitBuyResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TimeLimitBuyProcessor
/*    */   extends ProcessorBase<TimeLimitBuyRequest, TimeLimitBuyResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 21 */     this.response = (ResponseBase)new TimeLimitBuyResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, TimeLimitBuyRequest request) {
/* 26 */     LimitBuyComponent limitBuyComponent = (LimitBuyComponent)playerSession.getPlayer().createIfNotExist(LimitBuyComponent.class);
/* 27 */     Map<Integer, Integer> limitBuyGoods = limitBuyComponent.getLimitBuyGoods(request.id);
/* 28 */     int num = ((Integer)limitBuyGoods.getOrDefault(Integer.valueOf(request.packageType), Integer.valueOf(0))).intValue();
/* 29 */     SpecialOfferListBean specialOfferListBean = (SpecialOfferListBean)JsonTableService.getJsonData(request.packageType, SpecialOfferListBean.class);
/* 30 */     if (num >= specialOfferListBean.getNumber()) {
/* 31 */       return 11903;
/*    */     }
/* 33 */     ((TimeLimitBuyResponse)this.response).packageType = request.packageType;
/* 34 */     ((TimeLimitBuyResponse)this.response).num = num;
/* 35 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\timelimit\TimeLimitBuyProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */