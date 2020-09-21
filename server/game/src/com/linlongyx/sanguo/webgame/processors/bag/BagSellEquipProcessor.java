/*    */ package com.linlongyx.sanguo.webgame.processors.bag;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.bag.BagSellEquipRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.bag.BagSellEquipResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BagSellEquipProcessor
/*    */   extends ProcessorBase<BagSellEquipRequest, BagSellEquipResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 16 */     this.response = (ResponseBase)new BagSellEquipResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, BagSellEquipRequest request) {
/* 21 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\bag\BagSellEquipProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */