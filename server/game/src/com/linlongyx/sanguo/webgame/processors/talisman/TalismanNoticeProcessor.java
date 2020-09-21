/*    */ package com.linlongyx.sanguo.webgame.processors.talisman;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.TalismanBoxBean;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.talisman.TalismanNoticeRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.talisman.TalismanNoticeResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TalismanNoticeProcessor
/*    */   extends ProcessorBase<TalismanNoticeRequest, TalismanNoticeResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 18 */     this.response = (ResponseBase)new TalismanNoticeResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, TalismanNoticeRequest request) {
/* 23 */     ((TalismanNoticeResponse)this.response).actList.addAll(JsonTableService.getJsonTableKey(TalismanBoxBean.class));
/* 24 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\talisman\TalismanNoticeProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */