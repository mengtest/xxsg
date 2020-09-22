/*    */ package com.linlongyx.sanguo.webgame.processors.mental;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.mental.MentalUtil;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.mental.RaffleLotteryRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.mental.RaffleLotteryResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RaffleLotteryProcessor
/*    */   extends ProcessorBase<RaffleLotteryRequest, RaffleLotteryResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 19 */     this.response = (ResponseBase)new RaffleLotteryResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, RaffleLotteryRequest request) {
/* 24 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 58))
/* 25 */       return 10061; 
/* 26 */     int times = 0;
/* 27 */     if (request.type == 0) {
/* 28 */       times = 1;
/*    */     } else {
/* 30 */       times = 10;
/*    */     } 
/* 32 */     short ret = MentalUtil.raffleLottery(playerSession.getPlayer(), times, (RaffleLotteryResponse)this.response);
/* 33 */     if (ret != 0) {
/* 34 */       return ret;
/*    */     }
/* 36 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\mental\RaffleLotteryProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */