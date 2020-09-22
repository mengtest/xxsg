/*    */ package com.linlongyx.sanguo.webgame.processors.welfare;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.welfare.WelfareComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.GeneralGiftParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.welfare.GeneralGiftInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.welfare.GeneralGiftInfoResponse;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GeneralGiftInfoProcessor
/*    */   extends ProcessorBase<GeneralGiftInfoRequest, GeneralGiftInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 23 */     this.response = (ResponseBase)new GeneralGiftInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, GeneralGiftInfoRequest request) {
/* 28 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 46))
/* 29 */       return 10061; 
/* 30 */     WelfareComponent welfareComponent = (WelfareComponent)playerSession.getPlayer().createIfNotExist(WelfareComponent.class);
/* 31 */     GeneralGiftParameter generalGiftParameter = (GeneralGiftParameter)ParameterConstant.getParameter(46);
/* 32 */     ((GeneralGiftInfoResponse)this.response).endTime = WelfareUtil.getGiftTime(generalGiftParameter.getOpenDay());
/* 33 */     ((GeneralGiftInfoResponse)this.response).list = new ArrayList(welfareComponent.getGerenalGift());
/* 34 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\welfare\GeneralGiftInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */