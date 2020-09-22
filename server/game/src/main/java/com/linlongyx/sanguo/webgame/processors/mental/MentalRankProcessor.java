/*    */ package com.linlongyx.sanguo.webgame.processors.mental;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.mental.MentalRankRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.mental.MentalRankResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.MentalRankService;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MentalRankProcessor
/*    */   extends ProcessorBase<MentalRankRequest, MentalRankResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 20 */     this.response = (ResponseBase)new MentalRankResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, MentalRankRequest request) {
/* 25 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 58))
/* 26 */       return 10061; 
/* 27 */     MentalRankService rankService = (MentalRankService)MContext.getBean("mentalRankService");
/* 28 */     rankService.getRanks(((MentalRankResponse)this.response).ranks);
/* 29 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\mental\MentalRankProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */