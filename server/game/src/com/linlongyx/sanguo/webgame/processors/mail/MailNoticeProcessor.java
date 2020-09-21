/*    */ package com.linlongyx.sanguo.webgame.processors.mail;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.mail.MailComponent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.mail.MailNoticeRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.mail.MailNoticeResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MailNoticeProcessor
/*    */   extends ProcessorBase<MailNoticeRequest, MailNoticeResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 17 */     this.response = (ResponseBase)new MailNoticeResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, MailNoticeRequest request) {
/* 22 */     MailComponent mailComponent = (MailComponent)playerSession.getPlayer().createIfNotExist(MailComponent.class);
/* 23 */     ((MailNoticeResponse)this.response).sum = mailComponent.getSize();
/* 24 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\mail\MailNoticeProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */