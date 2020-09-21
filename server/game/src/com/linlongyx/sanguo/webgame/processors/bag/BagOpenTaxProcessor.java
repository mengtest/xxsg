/*    */ package com.linlongyx.sanguo.webgame.processors.bag;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.extend.ExtendComponent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.bag.BagOpenTaxRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.bag.BagOpenTaxResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BagOpenTaxProcessor
/*    */   extends ProcessorBase<BagOpenTaxRequest, BagOpenTaxResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 17 */     this.response = (ResponseBase)new BagOpenTaxResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, BagOpenTaxRequest request) {
/* 22 */     ExtendComponent extendComponent = (ExtendComponent)playerSession.getPlayer().createIfNotExist(ExtendComponent.class);
/* 23 */     ((BagOpenTaxResponse)this.response).buyGoldTimes = extendComponent.getBuyGoldTimes();
/* 24 */     ((BagOpenTaxResponse)this.response).buyGoldCost = extendComponent.getBuyGoldCost();
/* 25 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\bag\BagOpenTaxProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */