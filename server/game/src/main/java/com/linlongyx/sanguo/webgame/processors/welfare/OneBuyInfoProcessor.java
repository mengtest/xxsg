/*    */ package com.linlongyx.sanguo.webgame.processors.welfare;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.welfare.WelfareComponent;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.welfare.OneBuyInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.welfare.OneBuyInfoResponse;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class OneBuyInfoProcessor
/*    */   extends ProcessorBase<OneBuyInfoRequest, OneBuyInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 21 */     this.response = (ResponseBase)new OneBuyInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, OneBuyInfoRequest request) {
/* 26 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 6401))
/* 27 */       return 10061; 
/* 28 */     WelfareComponent welfareComponent = (WelfareComponent)playerSession.getPlayer().createIfNotExist(WelfareComponent.class);
/* 29 */     ((OneBuyInfoResponse)this.response).gotReward = new ArrayList(welfareComponent.getOneBuyReward().keySet());
/* 30 */     ((OneBuyInfoResponse)this.response).oneBuyCharge = welfareComponent.getOneBuyCharge();
/* 31 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\welfare\OneBuyInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */