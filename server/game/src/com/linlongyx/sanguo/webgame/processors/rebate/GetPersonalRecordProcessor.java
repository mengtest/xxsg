/*    */ package com.linlongyx.sanguo.webgame.processors.rebate;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.rebate.RechargeRebateComponent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.rebate.GetPersonalRecordRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.rebate.GetPersonalRecordResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GetPersonalRecordProcessor
/*    */   extends ProcessorBase<GetPersonalRecordRequest, GetPersonalRecordResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 17 */     this.response = (ResponseBase)new GetPersonalRecordResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, GetPersonalRecordRequest request) {
/* 22 */     RechargeRebateComponent rechargeRebateComponent = (RechargeRebateComponent)playerSession.getPlayer().createIfNotExist(RechargeRebateComponent.class);
/* 23 */     ((GetPersonalRecordResponse)this.response).personalRecords = rechargeRebateComponent.getRecords();
/* 24 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\rebate\GetPersonalRecordProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */