/*    */ package com.linlongyx.sanguo.webgame.processors.login;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.extend.ExtendComponent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.login.ConstantInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.login.ConstantInfoResponse;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ConstantInfoProcessor
/*    */   extends ProcessorBase<ConstantInfoRequest, ConstantInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 19 */     this.response = (ResponseBase)new ConstantInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, ConstantInfoRequest request) {
/* 24 */     ExtendComponent extendComponent = (ExtendComponent)playerSession.getPlayer().createIfNotExist(ExtendComponent.class);
/* 25 */     ((ConstantInfoResponse)this.response).radio = extendComponent.getSpeedRadio();
/* 26 */     ((ConstantInfoResponse)this.response).isChoose = extendComponent.isChoose() ? 1 : 0;
/* 27 */     ((ConstantInfoResponse)this.response).skipTimes = extendComponent.getSkipTimes();
/* 28 */     ((ConstantInfoResponse)this.response).questionnaire = new ArrayList(extendComponent.getQuestionnaire());
/* 29 */     ((ConstantInfoResponse)this.response).clientSets = extendComponent.getClientSets();
/* 30 */     ((ConstantInfoResponse)this.response).shortCut = extendComponent.getShortCut();
/* 31 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\login\ConstantInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */